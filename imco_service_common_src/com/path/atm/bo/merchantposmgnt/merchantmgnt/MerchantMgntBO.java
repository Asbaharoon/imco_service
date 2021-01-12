package com.path.atm.bo.merchantposmgnt.merchantmgnt;

import java.util.List;

import com.path.atm.vo.merchantposmgnt.merchantmgnt.MerchantMgntCO;
import com.path.atm.vo.merchantposmgnt.merchantmgnt.MerchantMgntSC;
import com.path.lib.common.exception.BaseException;
import com.path.vo.common.AlertsParamCO;
import com.path.vo.core.cif.CifSC;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: jihanemazloum
 * 
 *          
 */

public interface MerchantMgntBO
{

    public List<MerchantMgntCO> returnMerchantMgntList(MerchantMgntSC merchantMgntSC) throws BaseException;

    public Integer returnMerchantMgntListCount(MerchantMgntSC merchantMgntSC) throws BaseException;

    public MerchantMgntCO onCIFDetailsClicked(MerchantMgntCO merchantMgntCO, CifSC cifSC) throws BaseException;

    public MerchantMgntCO returnMerchantMgntDetails(MerchantMgntSC merchantMgntSC) throws BaseException;

    public MerchantMgntCO saveMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException;

    public MerchantMgntCO approveMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException;

    public MerchantMgntCO deleteMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException;

    public MerchantMgntCO toCancelMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException;

    public MerchantMgntCO cancelMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException;

    public AlertsParamCO returnAlertsParamCO(MerchantMgntCO merchantMgntCO, boolean isNewAction, String pageRef)
	    throws BaseException;

    public MerchantMgntCO alertReject(MerchantMgntCO merchantMgntCO) throws BaseException;

    public MerchantMgntCO loadAlertMerchantDetails(MerchantMgntCO merchantMgntCO) throws BaseException;

    // public void checkIrisWindow(MerchantMgntCO merchantMgntCO)throws
    // BaseException; //MAY BE USED LATER
    public MerchantMgntCO dependencyByAccCIF(MerchantMgntCO merchantMgntCO) throws BaseException;
    
    public MerchantMgntCO dependencyByAccSL(MerchantMgntCO merchantMgntCO) throws BaseException;

    public MerchantMgntCO dependencyByAddRef(MerchantMgntCO merchantMgntCO) throws BaseException;
    public MerchantMgntCO dependencyOnMerchantType(MerchantMgntCO merchantMgntCO) throws BaseException;
}
