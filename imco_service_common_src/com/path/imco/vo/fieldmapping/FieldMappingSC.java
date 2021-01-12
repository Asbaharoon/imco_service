package com.path.imco.vo.fieldmapping;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * FieldMappingSC.java used to
 */
public class FieldMappingSC extends GridParamsSC
{
    BigDecimal colNBR;
    BigDecimal entityCode;
    BigDecimal brcode;

    /**
     * @return the colNBR
     */
    public BigDecimal getColNBR()
    {
	return colNBR;
    }

    /**
     * @param colNBR the colNBR to set
     */
    public void setColNBR(BigDecimal colNBR)
    {
	this.colNBR = colNBR;
    }

    /**
     * @return the entityCode
     */
    public BigDecimal getEntityCode()
    {
	return entityCode;
    }

    /**
     * @param entityCode the entityCode to set
     */
    public void setEntityCode(BigDecimal entityCode)
    {
	this.entityCode = entityCode;
    }

    /**
     * @return the brcode
     */
    public BigDecimal getBrcode()
    {
	return brcode;
    }

    /**
     * @param brcode the brcode to set
     */
    public void setBrcode(BigDecimal brcode)
    {
	this.brcode = brcode;
    }
}
