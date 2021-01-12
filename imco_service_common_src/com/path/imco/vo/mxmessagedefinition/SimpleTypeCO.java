package com.path.imco.vo.mxmessagedefinition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleTypeCO implements Serializable {

	private String typeName;
	private String restrictionType;
	
	private String fractionDigits;
	private String totalDigits;
	private String minExclusive;
	private String maxExclusive;
	private String minInclusive;
	private String maxInclusive;
	private String pattern;
	private String minLength;
	private String maxLength;
	private String whiteSpace;
	private String length;
	
	private List<String> enumValues = new ArrayList<String>();

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRestrictionType() {
		return restrictionType;
	}

	public void setRestrictionType(String restrictionType) {
		this.restrictionType = restrictionType;
	}

	public String getFractionDigits() {
		return fractionDigits;
	}

	public void setFractionDigits(String fractionDigits) {
		this.fractionDigits = fractionDigits;
	}

	public String getTotalDigits() {
		return totalDigits;
	}

	public void setTotalDigits(String totalDigits) {
		this.totalDigits = totalDigits;
	}

	public String getMinExclusive() {
		return minExclusive;
	}

	public void setMinExclusive(String minExclusive) {
		this.minExclusive = minExclusive;
	}

	public String getMaxExclusive() {
		return maxExclusive;
	}

	public void setMaxExclusive(String maxExclusive) {
		this.maxExclusive = maxExclusive;
	}

	public String getMinInclusive() {
		return minInclusive;
	}

	public void setMinInclusive(String minInclusive) {
		this.minInclusive = minInclusive;
	}

	public String getMaxInclusive() {
		return maxInclusive;
	}

	public void setMaxInclusive(String maxInclusive) {
		this.maxInclusive = maxInclusive;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getMinLength() {
		return minLength;
	}

	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public String getWhiteSpace() {
		return whiteSpace;
	}

	public void setWhiteSpace(String whiteSpace) {
		this.whiteSpace = whiteSpace;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public List<String> getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(List<String> enumValues) {
		this.enumValues = enumValues;
	}
	
}
