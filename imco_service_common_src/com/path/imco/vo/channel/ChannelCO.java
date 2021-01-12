package com.path.imco.vo.channel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.path.dbmaps.vo.AXSVO;
import com.path.dbmaps.vo.GTW_CHANNELVO;
import com.path.dbmaps.vo.GTW_CHANNEL_DETVO;
import com.path.dbmaps.vo.GTW_PWS_TMPLT_MASTERVO;
import com.path.dbmaps.vo.GTW_PWS_TMPLT_USRVO;
import com.path.dbmaps.vo.OPTVO;
import com.path.dbmaps.vo.OPT_EXTENDEDVO;
import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.lib.vo.BaseVO;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * ChannelCO.java used to
 */
public class ChannelCO extends BaseVO
{

	private GTW_CHANNELVO imApiChannelsVO;
	private GTW_CHANNEL_DETVO imApiChannelsDetVO;
	private GTW_PWS_TMPLT_MASTERVO imcoPwsTmpltMasterVO;
	private GTW_PWS_TMPLT_USRVO imcoPwsTmpltUserVO;
    private String updateMode, channelUserPassword;
    private HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementMap = new HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO>();
    private BigDecimal compCode;
    private BigDecimal branchCode;
    private String appName;
    private String progRef;
    private String userID;
    private String language;
    private Integer allowUserAccess;
    private List<String> listMachineId;
    private Date runningDate;
    private String statusDesc;
    private String jsonMultiselectArray;
    
    private String communicationType;
    private String ip;
    private String port;
    private String folderLocation;
    private String communicationFormal;
    private String activeJMS;
    private BigDecimal parallelismControl;
    
    private OPTVO optVO = new OPTVO();
    private OPT_EXTENDEDVO optExtVO = new OPT_EXTENDEDVO();
    private AXSVO axsVO = new AXSVO();
    
    

    public List<String> getListMachineId()
    {
        return listMachineId;
    }

    public void setListMachineId(List<String> listMachineId)
    {
        this.listMachineId = listMachineId;
    }

    public Integer getAllowUserAccess()
    {
	return allowUserAccess;
    }

    public void setAllowUserAccess(Integer allowUserAccess)
    {
	this.allowUserAccess = allowUserAccess;
    }

    public String getLanguage()
    {
	return language;
    }

    public void setLanguage(String language)
    {
	this.language = language;
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

    public String getAppName()
    {
	return appName;
    }

    public void setAppName(String appName)
    {
	this.appName = appName;
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

    /**
     * @return the elementMap
     */
    public HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> getElementMap()
    {
	return elementMap;
    }

    /**
     * @param elementMap the elementMap to set
     */
    public void setElementMap(HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementMap)
    {
	this.elementMap = elementMap;
    }

    /**
     * @return the updateMode
     */
    public String getUpdateMode()
    {
	return updateMode;
    }

    /**
     * @param updateMode the updateMode to set
     */
    public void setUpdateMode(String updateMode)
    {
	this.updateMode = updateMode;
    }

    public String getChannelUserPassword()
    {
	return channelUserPassword;
    }

    public void setChannelUserPassword(String channelUserPassword)
    {
	this.channelUserPassword = channelUserPassword;
    }

    public GTW_CHANNELVO getImApiChannelsVO()
    {
        return imApiChannelsVO;
    }

    public void setImApiChannelsVO(GTW_CHANNELVO imApiChannelsVO)
    {
        this.imApiChannelsVO = imApiChannelsVO;
    }

    public GTW_CHANNEL_DETVO getImApiChannelsDetVO()
    {
        return imApiChannelsDetVO;
    }

    public void setImApiChannelsDetVO(GTW_CHANNEL_DETVO imApiChannelsDetVO)
    {
        this.imApiChannelsDetVO = imApiChannelsDetVO;
    }

    public Date getRunningDate()
    {
        return runningDate;
    }

    public void setRunningDate(Date runningDate)
    {
        this.runningDate = runningDate;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc)
    {
        this.statusDesc = statusDesc;
    }

    public GTW_PWS_TMPLT_MASTERVO getImcoPwsTmpltMasterVO()
    {
        return imcoPwsTmpltMasterVO;
    }

    public void setImcoPwsTmpltMasterVO(GTW_PWS_TMPLT_MASTERVO imcoPwsTmpltMasterVO)
    {
        this.imcoPwsTmpltMasterVO = imcoPwsTmpltMasterVO;
    }

    public String getJsonMultiselectArray()
    {
        return jsonMultiselectArray;
    }

    public void setJsonMultiselectArray(String jsonMultiselectArray)
    {
        this.jsonMultiselectArray = jsonMultiselectArray;
    }

    public GTW_PWS_TMPLT_USRVO getImcoPwsTmpltUserVO()
    {
        return imcoPwsTmpltUserVO;
    }

    public void setImcoPwsTmpltUserVO(GTW_PWS_TMPLT_USRVO imcoPwsTmpltUserVO)
    {
        this.imcoPwsTmpltUserVO = imcoPwsTmpltUserVO;
    }

	public String getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getFolderLocation() {
		return folderLocation;
	}

	public void setFolderLocation(String folderLocation) {
		this.folderLocation = folderLocation;
	}

	public String getCommunicationFormal() {
		return communicationFormal;
	}

	public void setCommunicationFormal(String communicationFormal) {
		this.communicationFormal = communicationFormal;
	}

	public String getActiveJMS() {
		return activeJMS;
	}

	public void setActiveJMS(String activeJMS) {
		this.activeJMS = activeJMS;
	}

	public BigDecimal getParallelismControl() {
		return parallelismControl;
	}

	public void setParallelismControl(BigDecimal parallelismControl) {
		this.parallelismControl = parallelismControl;
	}

	public OPTVO getOptVO() {
		return optVO;
	}

	public void setOptVO(OPTVO optVO) {
		this.optVO = optVO;
	}

	public OPT_EXTENDEDVO getOptExtVO() {
		return optExtVO;
	}

	public void setOptExtVO(OPT_EXTENDEDVO optExtVO) {
		this.optExtVO = optExtVO;
	}

	public AXSVO getAxsVO() {
		return axsVO;
	}

	public void setAxsVO(AXSVO axsVO) {
		this.axsVO = axsVO;
	}
}
