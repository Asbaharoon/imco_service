package com.path.imco.dao.queuesettings.impl;

import java.util.List;

import com.path.imco.dao.queuesettings.QueueSettingsDAO;
import com.path.imco.vo.queuesettings.QueueSettingsSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * QueueSettingsDAOImpl.java used to
 */
public class QueueSettingsDAOImpl extends BaseDAO implements QueueSettingsDAO
{
    @Override
    public List returnQueueSettingsList(QueueSettingsSC queueSettingsSC) throws DAOException
    {
	DAOHelper.fixGridMaps(queueSettingsSC, getSqlMap(), "queueSettingsMapper.resQueueSettingsMap");
//	if(serviceSetSC.getNbRec() == -1)
//	{
	  if(queueSettingsSC.getSortOrder()==null)
	  {
		  queueSettingsSC.setSortOrder(" ORDER BY ENTITY_ORDER ");
	  }
	  return getSqlMap().queryForList("queueSettingsMapper.returnQueueSettingsList", queueSettingsSC);
//	}
//	else
//	{
//	    return getSqlMap().queryForList("serviceSetMapper.returnServiceSettingsList", serviceSetSC, serviceSetSC.getRecToskip(),serviceSetSC.getNbRec());
//	}

    }

    @Override
    public int updateData(QueueSettingsSC criteria) throws DAOException
    {
	// TODO Auto-generated method stub
	return getSqlMap().update("queueSettingsMapper.updateData", criteria);
    }

  
}
