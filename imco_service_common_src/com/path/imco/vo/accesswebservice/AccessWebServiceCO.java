package com.path.imco.vo.accesswebservice;

import java.math.BigDecimal;
import java.util.Date;

import com.path.dbmaps.vo.IMCO_PWS_CHANNELVO;
import com.path.dbmaps.vo.IMCO_PWS_TMPLT_DETVO;
import com.path.dbmaps.vo.IMCO_PWS_TMPLT_DET_PARAMVO;
import com.path.dbmaps.vo.IMCO_PWS_TMPLT_MASTERVO;
import com.path.dbmaps.vo.IMCO_PWS_TMPLT_USRVO;
import com.path.lib.vo.BaseVO;
  
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * AccessWebServiceCO.java used to
 */
public class AccessWebServiceCO extends BaseVO
{
    private IMCO_PWS_TMPLT_MASTERVO imcoPwsTmpltMasterVO;
    private IMCO_PWS_TMPLT_USRVO imcoPwsTmpltUsrVO;
    private IMCO_PWS_TMPLT_DETVO imcoPwsTmpltDetVO;
    private IMCO_PWS_TMPLT_DET_PARAMVO imcoPwsTmplDetParamVO;
    private IMCO_PWS_CHANNELVO imcoPwsChannelVO;
    private String statusDesc;
    /*properties added for grid tree*/
    private String nodeId;
    private String level;
    private String parent;
    private String isLeaf,feName;
    private String appName,value,previousRowId;
    private BigDecimal compCode;
    private BigDecimal branchCode;
    private String progRef;
    private String userID;
    private Date runningDate;
    private String ivCrud;
    private String language;
    private String CUSTOM_DETAILS;
    
    public IMCO_PWS_TMPLT_MASTERVO getImcoPwsTmpltMasterVO()
    {
        return imcoPwsTmpltMasterVO;
    }
    public void setImcoPwsTmpltMasterVO(IMCO_PWS_TMPLT_MASTERVO imcoPwsTmpltMasterVO)
    {
        this.imcoPwsTmpltMasterVO = imcoPwsTmpltMasterVO;
    }
    public IMCO_PWS_TMPLT_USRVO getImcoPwsTmpltUsrVO()
    {
        return imcoPwsTmpltUsrVO;
    }
    public void setImcoPwsTmpltUsrVO(IMCO_PWS_TMPLT_USRVO imcoPwsTmpltUsrVO)
    {
        this.imcoPwsTmpltUsrVO = imcoPwsTmpltUsrVO;
    }
    public IMCO_PWS_TMPLT_DETVO getImcoPwsTmpltDetVO()
    {
        return imcoPwsTmpltDetVO;
    }
    public void setImcoPwsTmpltDetVO(IMCO_PWS_TMPLT_DETVO imcoPwsTmpltDetVO)
    {
        this.imcoPwsTmpltDetVO = imcoPwsTmpltDetVO;
    }
    public IMCO_PWS_TMPLT_DET_PARAMVO getImcoPwsTmplDetParamVO()
    {
        return imcoPwsTmplDetParamVO;
    }
    public void setImcoPwsTmplDetParamVO(IMCO_PWS_TMPLT_DET_PARAMVO imcoPwsTmplDetParamVO)
    {
        this.imcoPwsTmplDetParamVO = imcoPwsTmplDetParamVO;
    }
    public IMCO_PWS_CHANNELVO getImcoPwsChannelVO()
    {
        return imcoPwsChannelVO;
    }
    public void setImcoPwsChannelVO(IMCO_PWS_CHANNELVO imcoPwsChannelVO)
    {
        this.imcoPwsChannelVO = imcoPwsChannelVO;
    }
    public String getStatusDesc()
    {
        return statusDesc;
    }
    public void setStatusDesc(String statusDesc)
    {
        this.statusDesc = statusDesc;
    }
    public String getLevel()
    {
        return level;
    }
    public void setLevel(String level)
    {
        this.level = level;
    }
    public String getParent()
    {
        return parent;
    }
    public void setParent(String parent)
    {
        this.parent = parent;
    }
    public String getIsLeaf()
    {
        return isLeaf;
    }
    public void setIsLeaf(String isLeaf)
    {
        this.isLeaf = isLeaf;
    }
    public String getFeName()
    {
        return feName;
    }
    public void setFeName(String feName)
    {
        this.feName = feName;
    }
    public String getNodeId()
    {
        return nodeId;
    }
    public void setNodeId(String nodeId)
    {
        this.nodeId = nodeId;
    }
    public String getAppName()
    {
        return appName;
    }
    public void setAppName(String appName)
    {
        this.appName = appName;
    }
    public String getValue()
    {
        return value;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    public String getPreviousRowId()
    {
        return previousRowId;
    }
    public void setPreviousRowId(String previousRowId)
    {
        this.previousRowId = previousRowId;
    }
    public BigDecimal getCompCode()
    {
        return compCode;
    }
    public void setCompCode(BigDecimal compCode)
    {
        this.compCode = compCode;
    }
    public BigDecimal getBranchCode()
    {
        return branchCode;
    }
    public void setBranchCode(BigDecimal branchCode)
    {
        this.branchCode = branchCode;
    }
    public String getProgRef()
    {
        return progRef;
    }
    public void setProgRef(String progRef)
    {
        this.progRef = progRef;
    }
    public String getUserID()
    {
        return userID;
    }
    public void setUserID(String userID)
    {
        this.userID = userID;
    }
    public Date getRunningDate()
    {
        return runningDate;
    }
    public void setRunningDate(Date runningDate)
    {
        this.runningDate = runningDate;
    }
    public String getIvCrud()
    {
        return ivCrud;
    }
    public void setIvCrud(String ivCrud)
    {
        this.ivCrud = ivCrud;
    }
    public String getLanguage()
    {
        return language;
    }
    public void setLanguage(String language)
    {
        this.language = language;
    }
    public String getCUSTOM_DETAILS()
    {
        return CUSTOM_DETAILS;
    }
    public void setCUSTOM_DETAILS(String cUSTOM_DETAILS)
    {
        CUSTOM_DETAILS = cUSTOM_DETAILS;
    }
}
