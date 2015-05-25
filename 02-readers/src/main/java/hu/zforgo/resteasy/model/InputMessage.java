package hu.zforgo.resteasy.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "entry")
@XmlType(name = "viMessage")
public class InputMessage extends InputMessageType {

	public InputMessage() {
		super();
		getVariable();
	}

	public InputMessage(String id) {
		super();
		setId(id);
	}


}