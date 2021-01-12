package com.path.imco.vo.entities;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * EntitiesSC.java used to
 */
public class EntitiesSC extends GridParamsSC
{
    private BigDecimal entityCode;

    public BigDecimal getEntityCode()
    {
	return entityCode;
    }

    public void setEntityCode(BigDecimal entityCode)
    {
	this.entityCode = entityCode;
    }
}
