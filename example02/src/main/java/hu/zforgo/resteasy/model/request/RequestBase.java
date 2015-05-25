package hu.zforgo.resteasy.model.request;

import hu.zforgo.resteasy.InvalidInputException;
import hu.zforgo.resteasy.Utils;
import hu.zforgo.resteasy.model.InputMessage;
import hu.zforgo.resteasy.model.KeyValue;

import javax.xml.bind.JAXB;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public abstract class RequestBase implements Serializable {

	private static final long serialVersionUID = 1L;

	@MappedValue(name = "requestId")
	private String requestId;
	@MappedValue(name = "customerId")
	private String customerId;

	public RequestBase(String xml) throws InvalidInputException {
		fromSource(xml);
	}

	public String getRequestId() {
		return requestId;
	}

	public String getCustomerId() {
		return customerId;
	}

	private void fromSource(final String source) throws InvalidInputException {

		Class<?> klazz = this.getClass();
		Set<Field> fields = new HashSet<Field>();
		while (klazz != null) {
			Field[] tmp = klazz.getDeclaredFields();
			for (Field f : tmp) {
				if (f.isAnnotationPresent(MappedValue.class)) {
					fields.add(f);
				}
			}
			klazz = klazz.getSuperclass();
		}

		if (!fields.isEmpty()) {
			InputMessage message = JAXB.unmarshal(new StringReader(source), InputMessage.class);
			for (Field f : fields) {
				MappedValue ann = f.getAnnotation(MappedValue.class);
				String variableName = Utils.isEmpty(ann.name()) ? f.getName() : ann.name();
				KeyValue keyValue = message.getVariableByName(variableName);
				if (ann.mandatory() && (keyValue == null || Utils.isEmpty(keyValue.getValue()))) {

					throw new InvalidInputException(variableName + " is missing, but mandatory");//TODO normális hiba
				}
				if (keyValue != null) {
					Class<?> typeClass = f.getType();
					String value = keyValue.getValue();
					f.setAccessible(true);
					try {
						if (typeClass == String.class) {
							f.set(this, value);
						} else if (typeClass == Boolean.TYPE || typeClass == Boolean.TYPE) {
							Boolean bVal = null;
							if (Utils.isNotEmpty(value)) {
								bVal = !"0".equals(value) && ("1".equals(value) || Boolean.parseBoolean(value));
							}
							f.set(this, bVal);
						} else if (!typeClass.isPrimitive()) {
							try {
								Method m = typeClass.getMethod("valueOf", String.class);
								Object v = m.invoke(typeClass, value);
								f.set(this, v);
							} catch (Exception e) {
								throw new InvalidInputException(e);
							}
						} else {
							if (typeClass == Byte.TYPE) {
								f.setByte(this, Byte.parseByte(value));
							} else if (typeClass == Character.TYPE) {
								f.setChar(this, value.charAt(0));
							} else if (typeClass == Double.TYPE) {
								f.setDouble(this, Double.parseDouble(value));
							} else if (typeClass == Float.TYPE) {
								f.setFloat(this, Float.parseFloat(value));
							} else if (typeClass == Integer.TYPE) {
								f.setInt(this, Integer.parseInt(value));
							} else if (typeClass == Long.TYPE) {
								f.setLong(this, Long.parseLong(value));
							} else if (typeClass == Short.TYPE) {
								f.setShort(this, Short.parseShort(value));
							}
						}
					} catch (IllegalAccessException e) {
						throw new InvalidInputException(e);
					}
				}
			}
		}
	}
}
