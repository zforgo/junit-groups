package hu.zforgo.resteasy.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for viMessage complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="viMessage">
 *   &lt;complexContent>
 *     &lt;extension base="{}SimpleEntryElement">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="variable" type="{}KeyValue"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
		"variable"
})
public class InputMessageType extends SimpleEntryElement {

	@XmlElement(required = true)
	protected List<KeyValue> variable;

	/**
	 * Gets the value of the variable property.
	 * <p/>
	 * <p/>
	 * This accessor method returns a reference to the live list,
	 * not a snapshot. Therefore any modification you make to the
	 * returned list will be present inside the JAXB object.
	 * This is why there is not a <CODE>set</CODE> method for the variable property.
	 * <p/>
	 * <p/>
	 * For example, to add a new item, do as follows:
	 * <pre>
	 *    getVariable().add(newItem);
	 * </pre>
	 * <p/>
	 * <p/>
	 * <p/>
	 * Objects of the following type(s) are allowed in the list
	 * {@link hu.zforgo.resteasy.model.KeyValue }
	 */
	public List<KeyValue> getVariable() {
		if (variable == null) {
			variable = new ArrayList<KeyValue>();
		}
		return this.variable;
	}

	public KeyValue getVariableByName(String name) {
		for (KeyValue key : getVariable()) {
			if (key.getName().equals(name)) {
				return key;
			}
		}
		return null;
	}

	public void setVariableValue(String name, String newValue) {
		KeyValue key = getVariableByName(name);
		if (key == null) {
			addVariable(name, newValue);
		} else {
			key.setValue(newValue);
		}
	}

	public void addVariable(String name, String value) {
		variable.add(new KeyValue(name, value));
	}

}
