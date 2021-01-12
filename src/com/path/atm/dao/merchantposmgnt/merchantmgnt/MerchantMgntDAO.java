package com.path.atm.dao.merchantposmgnt.merchantmgnt;
import java.util.List;

import com.path.atm.vo.merchantposmgnt.merchantmgnt.MerchantMgntCO;
import com.path.atm.vo.merchantposmgnt.merchantmgnt.MerchantMgntSC;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: jihanemazloum
 * 
 *          
 */
public interface MerchantMgntDAO{
	public List<MerchantMgntCO> returnMerchantMgntList(MerchantMgntSC merchantMgntSC)throws DAOException ;
	public Integer returnMerchantMgntListCount(MerchantMgntSC merchantMgntSC) throws DAOException;
	public MerchantMgntCO returnMerchantMgntDetails(MerchantMgntSC merchantMgntSC)throws BaseException;
	public void updateCtsMerchantPos(MerchantMgntCO merchantMgntCO)throws BaseException;
	public int returnActiveResult(MerchantMgntCO merchantMgntCO)throws BaseException;
}

