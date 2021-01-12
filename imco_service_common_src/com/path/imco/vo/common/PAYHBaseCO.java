package com.path.imco.vo.common;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.lib.vo.BaseVO;

public class PAYHBaseCO extends BaseVO
{

    private String statusColorCode;
    private String userId;
    private BigDecimal compCode;
    private BigDecimal branchCode;
    private Date runningDate;
    private String updateMode;
    private String statusDesc;
    private String toBeStatusDesc;
    private String appName;
    private String progRef;
    private String userID;
    private String language;
    private String pageResult;
    private String operationType;
    private String auditKey;
    
    private String globalExpression;

    private HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementMap = new HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO>();
    private List addGridList = new ArrayList<>();
    private List modifyGridList = new ArrayList<>(); 
    private List deleteGridList = new ArrayList<>(); 
    private List allGridList = new ArrayList<>();
    private File uploadFile;
    private String mode;
    
    public String getStatusColorCode()
    {
	return statusColorCode;
    }

    public void setStatusColorCode(String statusColorCode)
    {
	this.statusColorCode = statusColorCode;
    }

    public String getUserId()
    {
	return userId;
    }

    public void setUserId(String userId)
    {
	this.userId = userId;
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

    public String getToBeStatusDesc()
    {
	return toBeStatusDesc;
    }

    public void setToBeStatusDesc(String toBeStatusDesc)
    {
	this.toBeStatusDesc = toBeStatusDesc;
    }

    public String getUpdateMode()
    {
	return updateMode;
    }

    public void setUpdateMode(String updateMode)
    {
	this.updateMode = updateMode;
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

    public String getLanguage()
    {
	return language;
    }

    public void setLanguage(String language)
    {
	this.language = language;
    }

    public HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> getElementMap()
    {
	return elementMap;
    }

    public void setElementMap(HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementMap)
    {
	this.elementMap = elementMap;
    }

    public String getPageResult()
    {
        return pageResult;
    }

    public void setPageResult(String pageResult)
    {
        this.pageResult = pageResult;
    }

    public String getGlobalExpression()
    {
        return globalExpression;
    }

    public void setGlobalExpression(String globalExpression)
    {
        this.globalExpression = globalExpression;
    }

    public List getAddGridList()
    {
        return addGridList;
    }

    public void setAddGridList(List addGridList)
    {
        this.addGridList = addGridList;
    }

    public List getModifyGridList()
    {
        return modifyGridList;
    }

    public void setModifyGridList(List modifyGridList)
    {
        this.modifyGridList = modifyGridList;
    }

    public List getDeleteGridList()
    {
        return deleteGridList;
    }

    public void setDeleteGridList(List deleteGridList)
    {
        this.deleteGridList = deleteGridList;
    }

    public List getAllGridList()
    {
        return allGridList;
    }

    public void setAllGridList(List allGridList)
    {
        this.allGridList = allGridList;
    }

    public String getOperationType()
    {
        return operationType;
    }

    public void setOperationType(String operationType)
    {
        this.operationType = operationType;
    }

    public String getAuditKey()
    {
        return auditKey;
    }

    public void setAuditKey(String auditKey)
    {
        this.auditKey = auditKey;
    }

    public File getUploadFile()
    {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile)
    {
        this.uploadFile = uploadFile;
    }

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}