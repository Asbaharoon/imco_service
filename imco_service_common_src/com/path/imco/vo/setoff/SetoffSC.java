package com.path.imco.vo.setoff;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * SetoffSC.java used to
 */
public class SetoffSC extends GridParamsSC
{
    private BigDecimal syncId;
    private String userId;
    private String terminal;


    public BigDecimal getSyncId()
    {
	return syncId;
    }

    public void setSyncId(BigDecimal syncId)
    {
	this.syncId = syncId;
    }

    @Override
    public String getUserId()
    {
	return userId;
    }

    @Override
    public void setUserId(String userId)
    {
	this.userId = userId;
    }

    public String getTerminal()
    {
	return terminal;
    }

    public void setTerminal(String terminal)
    {
	this.terminal = terminal;
    }



}
