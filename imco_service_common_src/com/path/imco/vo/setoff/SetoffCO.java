package com.path.imco.vo.setoff;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.path.dbmaps.vo.SYNC_ACTIONSVO;
import com.path.lib.vo.BaseVO;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * SetoffCO.java used to
 */
public class SetoffCO extends BaseVO
{
    private SYNC_ACTIONSVO syncActionsVO = new SYNC_ACTIONSVO();
    private String sActions;
    private String rActions;
    private String selected;
    private String setoff;
    private String terminal;
    private String syncId;
    private String process;
    private Date runningDate;
    private String userId;
    private String setoffGridListString;
    private List<SetoffCO> setoffGridList = new ArrayList<>();
    private String setoffchck;

    public SYNC_ACTIONSVO getSyncActionsVO()
    {
	return syncActionsVO;
    }

    public void setSyncActionsVO(SYNC_ACTIONSVO syncActionsVO)
    {
	this.syncActionsVO = syncActionsVO;
    }

    public String getsActions()
    {
	return sActions;
    }

    public void setsActions(String sActions)
    {
	this.sActions = sActions;
    }

    public String getrActions()
    {
	return rActions;
    }

    public void setrActions(String rActions)
    {
	this.rActions = rActions;
    }

    public String getSelected()
    {
	return selected;
    }

    public void setSelected(String selected)
    {
	this.selected = selected;
    }

    public String getSetoff()
    {
	return setoff;
    }

    public void setSetoff(String setoff)
    {
	this.setoff = setoff;
    }

    public String getProcess()
    {
	return process;
    }

    public void setProcess(String process)
    {
	this.process = process;
    }

    public Date getRunningDate()
    {
	return runningDate;
    }

    public void setRunningDate(Date runningDate)
    {
	this.runningDate = runningDate;
    }

    public String getUserId()
    {
	return userId;
    }

    public void setUserId(String userId)
    {
	this.userId = userId;
    }

    public String getSetoffGridListString()
    {
	return setoffGridListString;
    }

    public void setSetoffGridListString(String setoffGridListString)
    {
	this.setoffGridListString = setoffGridListString;
    }

    public List<SetoffCO> getSetoffGridList()
    {
	return setoffGridList;
    }

    public void setSetoffGridList(List<SetoffCO> setoffGridList)
    {
	this.setoffGridList = setoffGridList;
    }

    public String getSetoffchck()
    {
	return setoffchck;
    }

    public void setSetoffchck(String setoffchck)
    {
	this.setoffchck = setoffchck;
    }

    public String getTerminal()
    {
	return terminal;
    }

    public void setTerminal(String terminal)
    {
	this.terminal = terminal;
    }

    public String getSyncId()
    {
	return syncId;
    }

    public void setSyncId(String syncId)
    {
	this.syncId = syncId;
    }

}
