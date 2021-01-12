package com.path.atm.dao.merchantposmgnt.posmgnt.impl;

import java.util.List;

import com.path.dbmaps.vo.CTS_MERCHANT_POSVO;
import com.path.lib.common.base.BaseDAO;
import com.path.atm.dao.merchantposmgnt.posmgnt.MerchantPosMgntDAO;
import com.path.atm.vo.merchantposmgnt.posmgnt.MerchantPosMgntCO;
import com.path.atm.vo.merchantposmgnt.posmgnt.MerchantPosMgntSC;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
import com.path.lib.common.util.StringUtil;

/**
 * 
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: fatimasalam
 * 
 *          MerchantPosMgntDAOImpl.java used to
 */
public class MerchantPosMgntDAOImpl extends BaseDAO implements MerchantPosMgntDAO
{
   
    /**
     * this function will load data of the main grid
     * @param merchantPosMgntSC
     * 
     * @throws DAOException
     * @author fatimasalam
     */
    public List<MerchantPosMgntCO> returnMerchantPosMgntList(MerchantPosMgntSC merchantPosMgntSC) throws DAOException
    {
	// check if no order Specified then Order by POS CODE Descending
	if(StringUtil.nullToEmpty(merchantPosMgntSC.getSidx()).isEmpty())
	{
	    merchantPosMgntSC.setSidx("CODE");
	    merchantPosMgntSC.setSord("DESC");
	}
	DAOHelper.fixGridMaps(merchantPosMgntSC, getSqlMap(), "merchantPosMgntMapper.merchantPosMgntResultMap");
	if(merchantPosMgntSC.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("merchantPosMgntMapper.returnMerchantPosMgntList", merchantPosMgntSC);
	}
	else
	{
	    return getSqlMap().queryForList("merchantPosMgntMapper.returnMerchantPosMgntList", merchantPosMgntSC,
		    merchantPosMgntSC.getRecToskip(), merchantPosMgntSC.getNbRec());
	}
    }

    /**
     * this function will return the number of record for the main grid
     * @param merchantPosMgntSC
     * 
     * @throws DAOException
     * @author fatimasalam
     */
    public Integer returnMerchantPosMgntListCount(MerchantPosMgntSC merchantPosMgntSC) throws DAOException
    {
	DAOHelper.fixGridMaps(merchantPosMgntSC, getSqlMap(), "merchantPosMgntMapper.merchantPosMgntResultMap");
	return (Integer) getSqlMap().queryForObject("merchantPosMgntMapper.returnMerchantPosMgntListCount",
		merchantPosMgntSC);
    }

    /**
     * this function will return the cif code of record for the main grid
     * @param merchantPosMgntCO
     * 
     * @throws DAOException
     * @author fatimasalam
     */
    public String returnMerchantCodeCif(MerchantPosMgntCO merchantPosMgntCO) throws DAOException
    {
	return (String) getSqlMap().queryForObject("merchantPosMgntMapper.returnMerchantCodeCif", merchantPosMgntCO);
    }

    /**
     * this function will return the service count
     * @param merchantPosMgntCO
     * 
     * @throws DAOException
     * @author fatimasalam
     */
    public int returnServicesCount(MerchantPosMgntCO merchantPosMgntCO) throws DAOException
    {
	return ((Integer) getSqlMap().queryForObject("merchantPosMgntMapper.returnServicesCount", merchantPosMgntCO))
		.intValue();
    }

    /**
     * this function will return the records in CTS_MERCHANT_POS
     * @param merchantPosMgntCO
     * 
     * @throws DAOException
     * @author fatimasalam
     */
    public CTS_MERCHANT_POSVO returnCtsMerchantPos(MerchantPosMgntCO merchantPosMgntCO) throws DAOException
    {
	return ((CTS_MERCHANT_POSVO) getSqlMap().queryForObject("merchantPosMgntMapper.returnCtsMerchantPos",
		merchantPosMgntCO));
    }

    /**
     * this function will return the details of merchant pos 
     * @param merchantPosMgntSC
     * 
     * @throws DAOException
     * @author fatimasalam
     */
    public MerchantPosMgntCO returnMerchantPosMgntDetails(MerchantPosMgntSC merchantPosMgntSC) throws BaseException
    {
	List<MerchantPosMgntCO> lst = getSqlMap().queryForList("merchantPosMgntMapper.returnMerchantPosMgntList",
		merchantPosMgntSC);
	return (MerchantPosMgntCO) ((lst == null || lst.isEmpty()) ? (new MerchantPosMgntCO()) : lst.get(0));
    }

    /**
     * this function will return the cif details
     * @param merchantPosMgntCO
     * 
     * @throws DAOException
     * @author fatimasalam
     */
    public MerchantPosMgntCO returnMerchantCifDetails(MerchantPosMgntCO merchantPosMgntCO) throws DAOException
    {
	return (MerchantPosMgntCO) getSqlMap().queryForObject("merchantPosMgntMapper.returnMerchantCifDetails",
		merchantPosMgntCO);

    }

}
