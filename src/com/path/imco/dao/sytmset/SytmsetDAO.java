package com.path.imco.dao.sytmset;

import java.math.BigDecimal;
import java.util.List;

import com.path.imco.vo.sytmset.SytmsetCO;
import com.path.imco.vo.sytmset.SytmsetSC;
import com.path.lib.common.exception.DAOException;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SytmsetDAO.java used to
 */
public interface SytmsetDAO
{
    public int returnSytmsetListCount(SytmsetSC criteria) throws DAOException;

    public List returnSytmsetList(SytmsetSC criteria) throws DAOException;

    public SytmsetCO returnSytmsetDetails(SytmsetSC SytmsetSC) throws DAOException;

    public void deleteGridData(SytmsetSC criteria) throws DAOException;

    public SytmsetCO returnNetworkList(SytmsetSC sytmsetSC) throws DAOException;

    public int deleteFromSyncPingho(SytmsetSC criteria) throws DAOException;

    public BigDecimal returnCountFromSyncPingho(SytmsetSC criteria) throws DAOException;
}