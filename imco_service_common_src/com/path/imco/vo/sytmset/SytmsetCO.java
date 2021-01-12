package com.path.imco.vo.sytmset;

import java.math.BigDecimal;

import com.path.dbmaps.vo.SYNC_BRANCHVO;
import com.path.lib.vo.BaseVO;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SytmsetCO.java used to
 */
public class SytmsetCO extends BaseVO
{
    private SYNC_BRANCHVO syncBranchVO;
    private String UpdateMode;
    private String pingStatus;
    private String process;
    private String Sendcartridge;
    private String Recivecartridge;
    public BigDecimal count;
    private String pingdatabase;

    public SYNC_BRANCHVO getSyncBranchVO()
    {
	return syncBranchVO;
    }

    public void setSyncBranchVO(SYNC_BRANCHVO syncBranchVO)
    {
	this.syncBranchVO = syncBranchVO;
    }

    public String getUpdateMode()
    {
	return UpdateMode;
    }

    public void setUpdateMode(String updateMode)
    {
	UpdateMode = updateMode;
    }

    /**
     * @param one
     */
    public void setBranchCode(BigDecimal one)
    {
	// TODO Auto-generated method stub

    }


    public String getSendcartridge()
    {
	return Sendcartridge;
    }

    public void setSendcartridge(String sendcartridge)
    {
	Sendcartridge = sendcartridge;
    }

    public BigDecimal getCount()
    {
	return count;
    }

    public void setCount(BigDecimal count)
    {
	this.count = count;
    }

    public String getRecivecartridge()
    {
	return Recivecartridge;
    }

    public void setRecivecartridge(String recivecartridge)
    {
	Recivecartridge = recivecartridge;
    }

    public String getProcess()
    {
	return process;
    }

    public void setProcess(String process)
    {
	this.process = process;
    }

    public String getPingdatabase()
    {
	return pingdatabase;
    }

    public void setPingdatabase(String pingdatabase)
    {
	this.pingdatabase = pingdatabase;
    }

    public String getPingStatus()
    {
	return pingStatus;
    }

    public void setPingStatus(String pingStatus)
    {
	this.pingStatus = pingStatus;
    }

}