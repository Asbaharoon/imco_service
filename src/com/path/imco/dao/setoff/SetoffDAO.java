package com.path.imco.dao.setoff;

import java.util.List;

import com.path.imco.vo.setoff.SetoffCO;
import com.path.imco.vo.setoff.SetoffSC;
import com.path.lib.common.exception.DAOException;
/**
 *
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 *
 * SetoffDAO.java used to
 */
public interface SetoffDAO
{

    public List returnSetOffList(SetoffSC criteria) throws DAOException;

    public int returnSetOffListCount(SetoffSC criteria) throws DAOException;

    public List<SetoffCO> returnActionList(SetoffSC criteria) throws DAOException;

    public int deleteFromSetoff(SetoffSC criteria) throws DAOException;


}