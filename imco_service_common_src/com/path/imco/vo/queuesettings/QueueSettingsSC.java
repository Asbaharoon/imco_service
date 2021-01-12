package com.path.imco.vo.queuesettings;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * QueueSettingsSC.java used to
 */
public class QueueSettingsSC extends GridParamsSC
{
    private String orderFlag;
    private BigDecimal brCode ;

    public BigDecimal getBrCode()
    {
        return brCode;
    }

    public void setBrCode(BigDecimal brCode)
    {
        this.brCode = brCode;
    }

    public String getOrderFlag()
    {
	return orderFlag;
    }

    public void setOrderFlag(String orderFlag)
    {
	this.orderFlag = orderFlag;
    }
}
