package com.path.imco.dao.queuesettings;

import java.util.List;

import com.path.imco.vo.queuesettings.QueueSettingsSC;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * QueueSettingsDAO.java used to
 */
public interface QueueSettingsDAO 
{
    public List returnQueueSettingsList(QueueSettingsSC queueSettingsSC) throws DAOException;

    public int updateData(QueueSettingsSC criteria) throws DAOException;


}