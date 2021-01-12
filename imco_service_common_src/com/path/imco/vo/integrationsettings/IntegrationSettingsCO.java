package com.path.imco.vo.integrationsettings;

import com.path.dbmaps.vo.SYNC_BRANCHVO;
import com.path.dbmaps.vo.SYNC_ENTITYVO;
import com.path.dbmaps.vo.SYNC_ENTITY_PARAMETERSVO;
import com.path.dbmaps.vo.SYNC_ENTITY_PARAMETERSVOWithBLOBs;
import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.lib.vo.BaseVO;
import java.util.Date;
import java.util.HashMap;
import java.math.BigDecimal;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * IntegrationSettingsCO.java used to
 */
public class IntegrationSettingsCO extends BaseVO
{
    private String syncEntityEntityName;
    private HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementMap = new HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO>();
    private SYNC_BRANCHVO sync_branchVO = new SYNC_BRANCHVO();
    private SYNC_ENTITYVO sync_entityVO = new SYNC_ENTITYVO();
    private SYNC_ENTITY_PARAMETERSVO sync_entity_parametersVO = new SYNC_ENTITY_PARAMETERSVO();
    private SYNC_ENTITY_PARAMETERSVOWithBLOBs sync_entity_parametersblobVO = new SYNC_ENTITY_PARAMETERSVOWithBLOBs();
    private BigDecimal relativeBr;
    private String replica;
    private String resend;
    private String period;
    private String entity;

    public SYNC_ENTITY_PARAMETERSVOWithBLOBs getSync_entity_parametersblobVO()
    {
	return sync_entity_parametersblobVO;
    }

    public void setSync_entity_parametersblobVO(SYNC_ENTITY_PARAMETERSVOWithBLOBs sync_entity_parametersblobVO)
    {
	this.sync_entity_parametersblobVO = sync_entity_parametersblobVO;
    }

    public SYNC_ENTITY_PARAMETERSVO getSync_entity_parametersVO()
    {
	return sync_entity_parametersVO;
    }

    public void setSync_entity_parametersVO(SYNC_ENTITY_PARAMETERSVO sync_entity_parametersVO)
    {
	this.sync_entity_parametersVO = sync_entity_parametersVO;
    }

    public SYNC_ENTITYVO getSync_entityVO()
    {
	return sync_entityVO;
    }

    public void setSync_entityVO(SYNC_ENTITYVO sync_entityVO)
    {
	this.sync_entityVO = sync_entityVO;
    }

    public String getSyncEntityEntityName()
    {
	return syncEntityEntityName;
    }

    public void setSyncEntityEntityName(String syncEntityEntityName)
    {
	this.syncEntityEntityName = syncEntityEntityName;
    }

    public SYNC_BRANCHVO getSync_branchVO()
    {
	return sync_branchVO;
    }

    public void setSync_branchVO(SYNC_BRANCHVO sync_branchVO)
    {
	this.sync_branchVO = sync_branchVO;
    }

    public BigDecimal getRelativeBr()
    {
	return relativeBr;
    }

    public void setRelativeBr(BigDecimal relativeBr)
    {
	this.relativeBr = relativeBr;
    }

    public String getReplica()
    {
        return replica;
    }

    public void setReplica(String replica)
    {
        this.replica = replica;
    }

    public String getResend()
    {
        return resend;
    }

    public void setResend(String resend)
    {
        this.resend = resend;
    }

    public String getPeriod()
    {
        return period;
    }

    public void setPeriod(String period)
    {
        this.period = period;
    }

    public String getEntity()
    {
        return entity;
    }

    public void setEntity(String entity)
    {
        this.entity = entity;
    }

    public HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> getElementMap()
    {
        return elementMap;
    }

    public void setElementMap(HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> elementMap)
    {
        this.elementMap = elementMap;
    }
}
