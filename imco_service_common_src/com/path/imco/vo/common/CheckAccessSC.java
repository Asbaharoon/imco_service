package com.path.imco.vo.common;

import java.math.BigDecimal;

public class CheckAccessSC
{
    private int access;
    private String userID;
    private String hostName;
    private BigDecimal channelID;
    private String hashKey;
    private String password;
    private String langId;
    private String wsAppName;
    private String wsName;
    private String wsOperationName;
    
    private BigDecimal apiCode;

    public int getAccess()
    {
	return access;
    }

    public void setAccess(int access)
    {
	this.access = access;
    }

    public BigDecimal getApiCode()
    {
	return apiCode;
    }

    public void setApiCode(BigDecimal apiCode)
    {
	this.apiCode = apiCode;
    }

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public BigDecimal getChannelID() {
		return channelID;
	}

	public void setChannelID(BigDecimal channelID) {
		this.channelID = channelID;
	}

	public String getHashKey() {
		return hashKey;
	}

	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getWsAppName() {
		return wsAppName;
	}

	public void setWsAppName(String wsAppName) {
		this.wsAppName = wsAppName;
	}

	public String getWsName() {
		return wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	public String getWsOperationName() {
		return wsOperationName;
	}

	public void setWsOperationName(String wsOperationName) {
		this.wsOperationName = wsOperationName;
	}

}
