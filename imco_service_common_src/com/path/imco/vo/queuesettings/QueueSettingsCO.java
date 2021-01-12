package com.path.imco.vo.queuesettings;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.path.dbmaps.vo.SYNC_BRANCHVO;
import com.path.dbmaps.vo.SYNC_ENTITYVO;
import com.path.dbmaps.vo.SYNC_ENTITY_PARAMETERSVO;
import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * QueueSettingsCO.java used to
 */
public class QueueSettingsCO extends BaseVO
{
    
    private SYNC_BRANCHVO syncBranchVO = new SYNC_BRANCHVO();
    private SYNC_ENTITYVO syncEntityVO = new SYNC_ENTITYVO();
    private SYNC_ENTITY_PARAMETERSVO syncEntityParametersVO = new SYNC_ENTITY_PARAMETERSVO();
    private BigDecimal brCode;
    private String orderFlag;
    
    private List<QueueSettingsCO> queueGridList = new ArrayList<>();
    private String queueGridListString;
    
    public SYNC_BRANCHVO getSyncBranchVO()
    {
        return syncBranchVO;
    }
    public void setSyncBranchVO(SYNC_BRANCHVO syncBranchVO)
    {
        this.syncBranchVO = syncBranchVO;
    }
    public SYNC_ENTITYVO getSyncEntityVO()
    {
        return syncEntityVO;
    }
    public void setSyncEntityVO(SYNC_ENTITYVO syncEntityVO)
    {
        this.syncEntityVO = syncEntityVO;
    }
    public SYNC_ENTITY_PARAMETERSVO getSyncEntityParametersVO()
    {
        return syncEntityParametersVO;
    }
    public void setSyncEntityParametersVO(SYNC_ENTITY_PARAMETERSVO syncEntityParametersVO)
    {
        this.syncEntityParametersVO = syncEntityParametersVO;
    }
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

    public List<QueueSettingsCO> getQueueGridList()
    {
	return queueGridList;
    }

    public void setQueueGridList(List<QueueSettingsCO> queueGridList)
    {
	this.queueGridList = queueGridList;
    }

    public String getQueueGridListString()
    {
	return queueGridListString;
    }

    public void setQueueGridListString(String queueGridListString)
    {
	this.queueGridListString = queueGridListString;
    }
   
}
