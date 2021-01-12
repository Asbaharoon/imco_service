package com.path.atm.dao.merchantposmgnt.posmgnt;

import java.util.List;

import com.path.atm.vo.merchantposmgnt.posmgnt.MerchantPosMgntCO;
import com.path.atm.vo.merchantposmgnt.posmgnt.MerchantPosMgntSC;
import com.path.dbmaps.vo.CTS_MERCHANT_POSVO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;

/**
 * 
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: FatimaSalam
 * 
 *          
 */
public interface MerchantPosMgntDAO
{
    public Integer returnMerchantPosMgntListCount(MerchantPosMgntSC merchantPosMgntSC) throws DAOException;

    public List<MerchantPosMgntCO> returnMerchantPosMgntList(MerchantPosMgntSC merchantPosMgntSC) throws DAOException;

    public String returnMerchantCodeCif(MerchantPosMgntCO merchantPosMgntCO) throws DAOException;
    public MerchantPosMgntCO returnMerchantCifDetails(MerchantPosMgntCO merchantPosMgntCO) throws DAOException;


    public int returnServicesCount(MerchantPosMgntCO merchantPosMgntCO) throws DAOException;

    public CTS_MERCHANT_POSVO returnCtsMerchantPos(MerchantPosMgntCO merchantPosMgntCO) throws DAOException;

    public MerchantPosMgntCO returnMerchantPosMgntDetails(MerchantPosMgntSC merchantPosMgntSC) throws BaseException;

}   