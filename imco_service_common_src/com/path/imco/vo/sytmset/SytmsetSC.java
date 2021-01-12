package com.path.imco.vo.sytmset;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SytmsetSC.java used to
 */
public class SytmsetSC extends GridParamsSC
{
    private BigDecimal sytmCode;
    private BigDecimal count;
    private BigDecimal brCode;
    private String process;


    public BigDecimal getCount()
    {
	return count;
    }

    public void setCount(BigDecimal count)
    {
	this.count = count;
    }

    public BigDecimal getSytmCode()
    {
	return sytmCode;
    }

    public void setSytmCode(BigDecimal sytmCode)
    {
	this.sytmCode = sytmCode;
    }

    public BigDecimal getBrCode()
    {
	return brCode;
    }

    public void setBrCode(BigDecimal brCode)
    {
	this.brCode = brCode;
    }

    public String getProcess()
    {
	return process;
    }

    public void setProcess(String process)
    {
	this.process = process;
    }

}
