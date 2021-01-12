package com.path.imco.bo.queuesettings.impl;

import java.util.List;

import com.path.imco.bo.queuesettings.QueueSettingsBO;
import com.path.imco.dao.queuesettings.QueueSettingsDAO;
import com.path.imco.vo.queuesettings.QueueSettingsCO;
import com.path.imco.vo.queuesettings.QueueSettingsSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * QueueSettingsBOImpl.java used to
 */
public class QueueSettingsBOImpl extends BaseBO implements QueueSettingsBO
{
    QueueSettingsDAO queueSettingsDAO;

    @Override
    public List returnQueueSettingsList(QueueSettingsSC queueSettingsSC) throws BaseException
    {
	return queueSettingsDAO.returnQueueSettingsList(queueSettingsSC);
    }

    @Override
    public QueueSettingsCO saveNew(QueueSettingsCO queueSettingsCO) throws BaseException
    {
	QueueSettingsSC criteria = new QueueSettingsSC();
	int i = queueSettingsCO.getQueueGridList().size();

	if(i > 1)
	{
	    criteria.setBrCode(queueSettingsCO.getBrCode());
	    criteria.setOrderFlag(queueSettingsCO.getSyncBranchVO().getORDER_FLAG());
	    int j = queueSettingsDAO.updateData(criteria);
	}
	return queueSettingsCO;
    }



    public QueueSettingsDAO getQueueSettingsDAO()
    {
	return queueSettingsDAO;
    }

    public void setQueueSettingsDAO(QueueSettingsDAO queueSettingsDAO)
    {
	this.queueSettingsDAO = queueSettingsDAO;
    }

}
