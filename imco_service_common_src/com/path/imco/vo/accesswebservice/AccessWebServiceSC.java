package com.path.imco.vo.accesswebservice;

import java.math.BigDecimal;
import java.util.List;

import com.path.struts2.lib.common.GridParamsSC;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * AccessWebServiceSC.java used to
 */
public class AccessWebServiceSC extends GridParamsSC
{
    public AccessWebServiceSC()
    {
	super.setSearchCols(new String[] { "CHANNEL_ID", "USER_ID"});
    }
    
    private String progRef,accessKey;
    private String channelUserId;
    private String menuRef;
    private String oldStatus;
    private BigDecimal lovTypeSubsc;
    private BigDecimal lovTypeLkOpt;
    private BigDecimal tempID;
    private List<AccessWebServiceCO> accessCOList;
    private String operationName,serviceName,appName,feName,level;
    private AccessWebServiceCO accessCO;
    
    public String getProgRef()
    {
        return progRef;
    }

    public void setProgRef(String progRef)
    {
        this.progRef = progRef;
    }

    public String getAccessKey()
    {
        return accessKey;
    }

    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
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

    public BigDecimal getLovTypeSubsc()
    {
        return lovTypeSubsc;
    }

    public void setLovTypeSubsc(BigDecimal lovTypeSubsc)
    {
        this.lovTypeSubsc = lovTypeSubsc;
    }

    public BigDecimal getLovTypeLkOpt()
    {
        return lovTypeLkOpt;
    }

    public void setLovTypeLkOpt(BigDecimal lovTypeLkOpt)
    {
        this.lovTypeLkOpt = lovTypeLkOpt;
    }

    public BigDecimal getTempID()
    {
        return tempID;
    }

    public void setTempID(BigDecimal tempID)
    {
        this.tempID = tempID;
    }

    public List<AccessWebServiceCO> getAccessCOList()
    {
        return accessCOList;
    }

    public void setAccessCOList(List<AccessWebServiceCO> accessCOList)
    {
        this.accessCOList = accessCOList;
    }

    public String getOperationName()
    {
        return operationName;
    }

    public void setOperationName(String operationName)
    {
        this.operationName = operationName;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    public String getFeName()
    {
        return feName;
    }

    public void setFeName(String feName)
    {
        this.feName = feName;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public AccessWebServiceCO getAccessCO()
    {
        return accessCO;
    }

    public void setAccessCO(AccessWebServiceCO accessCO)
    {
        this.accessCO = accessCO;
    }
}
