package hu.zforgo.resteasy;

import hu.zforgo.resteasy.model.request.RequestBase;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

@Provider
public class InputMessageReader implements MessageBodyReader<RequestBase> {

	@Override
	public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
		return RequestBase.class.isAssignableFrom(aClass);
	}

	@Override
	public RequestBase readFrom(Class<RequestBase> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
		try {
			Constructor<RequestBase> c = aClass.getConstructor(String.class);
			return c.newInstance(IOUtils.toString(inputStream, "UTF-8"));
		} catch (Exception e) {
			throw new WebApplicationException(e);
		}
	}
}
