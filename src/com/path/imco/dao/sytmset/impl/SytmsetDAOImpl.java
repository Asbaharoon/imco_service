package com.path.imco.dao.sytmset.impl;

import java.math.BigDecimal;
import java.util.List;

import com.path.imco.dao.sytmset.SytmsetDAO;
import com.path.imco.vo.sytmset.SytmsetCO;
import com.path.imco.vo.sytmset.SytmsetSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SytmsetDAOImpl.java used to
 */
public class SytmsetDAOImpl extends BaseDAO implements SytmsetDAO
{
    /**
     * Method used to return Count of Sytmset
     *
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws DAOException
     */
    @Override
    public int returnSytmsetListCount(SytmsetSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "sytmsetMapper.resSytmsetListMap");
	return ((Integer) getSqlMap().queryForObject("sytmsetMapper.returnSytmsetListCount", criteria)).intValue();
    }

    /**
     * Method used to return List of Sytmset
     *
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws DAOException
     */
    @Override
    public List returnSytmsetList(SytmsetSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "sytmsetMapper.resSytmsetListMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("sytmsetMapper.returnSytmsetList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("sytmsetMapper.returnSytmsetList", criteria, criteria.getRecToskip(),
		    criteria.getNbRec());
	}
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.sytmset.SytmsetDAO#returnSytmsetDetails(com.path.imco.
     * vo.sytmset.SytmsetSC)
     */
    @Override
    public SytmsetCO returnSytmsetDetails(SytmsetSC SytmsetSC) throws DAOException
    {
	return (SytmsetCO) getSqlMap().queryForObject("sytmsetMapper.returnSytmsetDetails", SytmsetSC);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.sytmset.SytmsetDAO#deleteGridData(com.path.imco.vo.
     * sytmset.SytmsetSC)
     */
    @Override
    public void deleteGridData(SytmsetSC criteria) throws DAOException
    {
	getSqlMap().delete("sytmsetMapper.deleteGridData", criteria);

    }

    @Override
    public SytmsetCO returnNetworkList(SytmsetSC criteria) throws DAOException
    {
	return (SytmsetCO) getSqlMap().queryForObject("sytmsetMapper.returnNetworkList", criteria);
    }

    @Override
    public int deleteFromSyncPingho(SytmsetSC criteria) throws DAOException
    {
	return getSqlMap().delete("sytmsetMapper.deleteFromSyncPingho", criteria);
    }

    @Override
    public BigDecimal returnCountFromSyncPingho(SytmsetSC criteria) throws DAOException
    {
	return (BigDecimal) getSqlMap().queryForObject("sytmsetMapper.returnCountFromSyncPingho", criteria);
    }

}
