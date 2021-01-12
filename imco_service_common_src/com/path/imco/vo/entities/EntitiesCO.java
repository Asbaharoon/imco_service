package com.path.imco.vo.entities;

import java.math.BigDecimal;

import com.path.dbmaps.vo.SYNC_ENTITYVO;
import com.path.lib.vo.BaseVO;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * EntitiesCO.java used to
 */
public class EntitiesCO extends BaseVO
{
    private SYNC_ENTITYVO syncEntityVO;
    private String UpdateMode;
    private BigDecimal ENTITY_CODE;
    private String ENTITY_NAME;
    private String DESCRIPTION;
    private String BR_FIELD_NAME;
    private BigDecimal REQUEST_API_CODE;
    private BigDecimal RESPONSE_API_CODE;

    public void setENTITY_CODE(BigDecimal ENTITY_CODE)
    {
	this.ENTITY_CODE = ENTITY_CODE;
    }
    public BigDecimal getENTITY_CODE()
    {
	return ENTITY_CODE;
    }
    public void setENTITY_NAME(String ENTITY_NAME)
    {
	this.ENTITY_NAME = ENTITY_NAME;
    }
    public String getENTITY_NAME()
    {
	return ENTITY_NAME;
    }
    public void setDESCRIPTION(String DESCRIPTION)
    {
	this.DESCRIPTION = DESCRIPTION;
    }
    public String getDESCRIPTION()
    {
	return DESCRIPTION;
    }
    public void setBR_FIELD_NAME(String BR_FIELD_NAME)
    {
	this.BR_FIELD_NAME = BR_FIELD_NAME;
    }
    public String getBR_FIELD_NAME()
    {
	return BR_FIELD_NAME;
    }
    public void setREQUEST_API_CODE(BigDecimal REQUEST_API_CODE)
    {
	this.REQUEST_API_CODE = REQUEST_API_CODE;
    }
    public BigDecimal getREQUEST_API_CODE()
    {
	return REQUEST_API_CODE;
    }
    public void setRESPONSE_API_CODE(BigDecimal RESPONSE_API_CODE)
    {
	this.RESPONSE_API_CODE = RESPONSE_API_CODE;
    }
    public BigDecimal getRESPONSE_API_CODE()
    {
	return RESPONSE_API_CODE;
    }

    public SYNC_ENTITYVO getSyncEntityVO()
    {
	return syncEntityVO;
    }

    public void setSyncEntityVO(SYNC_ENTITYVO syncEntityVO)
    {
	this.syncEntityVO = syncEntityVO;
    }

    public String getUpdateMode()
    {
	return UpdateMode;
    }

    public void setUpdateMode(String updateMode)
    {
	UpdateMode = updateMode;
    }

}
