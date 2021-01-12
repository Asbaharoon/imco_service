package com.path.imco.vo.integrationsettings;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IntegrationSettingsSC.java used to
 */
public class IntegrationSettingsSC extends GridParamsSC
{
    private BigDecimal br_code ;
    private BigDecimal entitycode;

    public BigDecimal getBr_code()
    {
	return br_code;
    }

    public void setBr_code(BigDecimal br_code)
    {
	this.br_code = br_code;
    }

    public BigDecimal getEntitycode()
    {
        return entitycode;
    }

    public void setEntitycode(BigDecimal entitycode)
    {
        this.entitycode = entitycode;
    }
}
