package com.path.imco.bo.serviceset.impl;

import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.SYNC_CONNECTIONVO;
import com.path.imco.bo.serviceset.ServiceSetBO;
import com.path.imco.dao.serviceset.ServiceSetDAO;
import com.path.imco.vo.serviceset.ServiceSetCO;
import com.path.imco.vo.serviceset.ServiceSetSC;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * ServiceSetBOImpl.java used to
 */
public class ServiceSetBOImpl extends BaseBO implements ServiceSetBO
{
    ServiceSetDAO serviceSetDAO;

    @Override
    public List returnServiceSettingsList(ServiceSetSC serviceSetSC) throws BaseException
    {
	return serviceSetDAO.returnServiceSettingsList(serviceSetSC);
    }

    @Override
    public ServiceSetCO saveNew(ServiceSetCO serviceSetCO) throws BaseException
    {
	//SYNC_CONNECTIONVO synconnVO = new SYNC_CONNECTIONVO();
	
	/*SYNC_CONNECTIONVO synconnctVO =  serviceSetCO.getSyncConnVO();*/
	
	
	for(ServiceSetCO serviceSet : serviceSetCO.getServiceGridList())
	   {
	
//	    synconnVO.setBR_CODE(serviceSet.getSyncConnVO().getBR_CODE());
//	    synconnVO.setPROCESS(serviceSet.getSyncConnVO().getPROCESS());
//	    synconnVO.setTIMER(serviceSet.getSyncConnVO().getTIMER());
	    if(!serviceSet.getSyncConnVO().getPROCESS().equals("R")  && !serviceSet.getSyncConnVO().getPROCESS().equals("S") ){

	    serviceSet.getSyncConnVO().setRECONNECT(null);
	    
	   }
	    genericDAO.update(serviceSet.getSyncConnVO());
	  }
	
	//genericDAO.update(serviceSetCO.getSyncConnVO());
	return serviceSetCO;
    }

    public ServiceSetDAO getServiceSetDAO()
    {
	return serviceSetDAO;
    }

    public void setServiceSetDAO(ServiceSetDAO serviceSetDAO)
    {
	this.serviceSetDAO = serviceSetDAO;
    }
}
