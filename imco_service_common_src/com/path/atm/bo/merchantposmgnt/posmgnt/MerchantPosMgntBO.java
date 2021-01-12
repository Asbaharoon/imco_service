package com.path.atm.bo.merchantposmgnt.posmgnt;

import java.util.List;

import com.path.atm.vo.merchantposmgnt.posmgnt.MerchantPosMgntCO;
import com.path.atm.vo.merchantposmgnt.posmgnt.MerchantPosMgntSC;
import com.path.lib.common.exception.BaseException;
import com.path.vo.common.AlertsParamCO;

/**
 * 
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: FatimaSalam
 * 
 *          
 */
public interface MerchantPosMgntBO
{
    public Integer returnMerchantPosMgntListCount(MerchantPosMgntSC merchantPosMgntSC) throws BaseException;

    public List<MerchantPosMgntCO> returnMerchantPosMgntList(MerchantPosMgntSC merchantPosMgntSC) throws BaseException;

    public MerchantPosMgntCO dependencyByMerchantCode(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO returnMerchantPosMgntBusinessDisplay(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;
    
    public MerchantPosMgntCO loadAlertMerchantPosDetails(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO dependencyByTerminalId(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO dependencyByInstallationDate(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO returnMerchantPosMgntDetails(MerchantPosMgntSC merchantPosMgntSC) throws BaseException;
    
    public MerchantPosMgntCO alertReject(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO saveMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO approveMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO cancelMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO toCancelMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public MerchantPosMgntCO deleteMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

    public AlertsParamCO returnAlertsParamCO(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;
    
    public void checkMerchantPosStatus(MerchantPosMgntCO merchantPosMgntCO) throws BaseException;

}
