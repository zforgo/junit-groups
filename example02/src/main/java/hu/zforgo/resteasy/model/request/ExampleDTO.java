package hu.zforgo.resteasy.model.request;

import hu.zforgo.resteasy.InvalidInputException;

public class ExampleDTO extends RequestBase {

	@MappedValue
	private String name;
	@MappedValue(mandatory = false)
	private Integer age;
	@MappedValue
	private String country;
	@MappedValue
	private String city;

	public ExampleDTO(String xml) throws InvalidInputException {
		super(xml);
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}
}
