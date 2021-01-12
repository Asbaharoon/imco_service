package com.path.imco.dao.appsettings;

import java.util.List;

import com.path.imco.vo.appsettings.AppSetSC;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * AppSetDAO.java used to
 */
public interface AppSetDAO
{
    public int returnAppSetListCount(AppSetSC criteria) throws DAOException;

    public List returnAppSetList(AppSetSC criteria) throws DAOException;

    /**
     * @param criteria
     * @return
     */
    public List returnGroupGridMapList(AppSetSC criteria) throws DAOException;
}