package com.path.imco.vo.channel;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * ChannelSC.java used to
 */
public class ChannelSC extends GridParamsSC
{
    BigDecimal ChannelId;
    private String channelHostName;
    private String channelUserId;
    private String menuRef;
    private String oldStatus;
    private BigDecimal lovTypeLkOpt;
    
    private String  communicationProtocol;

    /**
     * @return the channelId
     */
    public BigDecimal getChannelId()
    {
	return ChannelId;
    }

    /**
     * @param channelId the channelId to set
     */
    public void setChannelId(BigDecimal channelId)
    {
	ChannelId = channelId;
    }

    public String getChannelHostName()
    {
        return channelHostName;
    }

    public void setChannelHostName(String channelHostName)
    {
        this.channelHostName = channelHostName;
    }

    public String getChannelUserId()
    {
        return channelUserId;
    }

    public void setChannelUserId(String channelUserId)
    {
        this.channelUserId = channelUserId;
    }

    public String getMenuRef()
    {
        return menuRef;
    }

    public void setMenuRef(String menuRef)
    {
        this.menuRef = menuRef;
    }

    public String getOldStatus()
    {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus)
    {
        this.oldStatus = oldStatus;
    }

    public BigDecimal getLovTypeLkOpt()
    {
        return lovTypeLkOpt;
    }

    public void setLovTypeLkOpt(BigDecimal lovTypeLkOpt)
    {
        this.lovTypeLkOpt = lovTypeLkOpt;
    }

	public String getCommunicationProtocol() {
		return communicationProtocol;
	}

	public void setCommunicationProtocol(String communicationProtocol) {
		this.communicationProtocol = communicationProtocol;
	}
    

}
