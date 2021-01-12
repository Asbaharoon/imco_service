package com.path.imco.bo.appsettings;

import java.util.List;

import com.path.imco.vo.appsettings.AppSetCO;
import com.path.imco.vo.appsettings.AppSetSC;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * AppSetBO.java used to
 */
public interface AppSetBO
{
    public int returnAppSetListCount(AppSetSC criteria) throws BaseException;

    public List returnAppSetList(AppSetSC criteria) throws BaseException;

    /**
     * @param criteria
     * @return
     */
    public List returnGroupGridMapList(AppSetSC criteria) throws BaseException;

    /**
     * @param appsetCO
     * @return
     */
    public AppSetCO saveGroupData(AppSetCO appsetCO) throws BaseException;

}