package com.path.imco.bo.queuesettings;

import java.util.List;

import com.path.imco.vo.queuesettings.QueueSettingsCO;
import com.path.imco.vo.queuesettings.QueueSettingsSC;
import com.path.imco.vo.serviceset.ServiceSetCO;
import com.path.imco.vo.serviceset.ServiceSetSC;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * QueueSettingsBO.java used to
 */
public interface QueueSettingsBO 
{
    public List returnQueueSettingsList(QueueSettingsSC queueSettingsSC) throws BaseException;
   

    public QueueSettingsCO saveNew(QueueSettingsCO queueSettingsCO) throws BaseException;
}