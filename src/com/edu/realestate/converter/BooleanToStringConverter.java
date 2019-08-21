package com.edu.realestate.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean value) {
		return value.booleanValue() ? "Y" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String value) {
		return value == null || value.equals("N") ? false : true;
	}
}