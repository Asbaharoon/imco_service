package com.path.imco.vo.newapi;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * NewApiSC.java used to
 */
public class NewApiSC extends GridParamsSC
{
    private BigDecimal apiCode;
    private String procedureName;

    /**
     * @return the apiCode
     */
    public BigDecimal getApiCode()
    {
	return apiCode;
    }

    /**
     * @param apiCode the apiCode to set
     */
    public void setApiCode(BigDecimal apiCode)
    {
	this.apiCode = apiCode;
    }

    /**
     * @return the procedureName
     */
	public String getProcedureName() {
		return procedureName;
	}

	/**
     * @param procedureName the procedureName to set
     */
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}




}
