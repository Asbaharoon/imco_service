package com.path.atm.bo.merchantposmgnt.posmgnt.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import com.path.atm.bo.merchantposmgnt.posmgnt.MerchantPosMgntBO;
import com.path.atm.bo.merchantposmgnt.posmgnt.MerchantPosMgntConstant;
import com.path.atm.dao.merchantposmgnt.posmgnt.MerchantPosMgntDAO;
import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.bo.common.alerts.AlertsBO;
import com.path.bo.common.alerts.AlertsConstants;
import com.path.bo.common.audit.AuditConstant;
import com.path.bo.core.common.base.RetailBaseBO;
import com.path.dbmaps.vo.CIFCONTROLVO;
import com.path.dbmaps.vo.COUNTRIESVO;
import com.path.dbmaps.vo.CTSCONTROL_ALERTVO;
import com.path.dbmaps.vo.CTS_MERCHANT_POSVO;
import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.dbmaps.vo.S_TODO_DETVO;
import com.path.atm.vo.merchantposmgnt.posmgnt.MerchantPosMgntCO;
import com.path.atm.vo.merchantposmgnt.posmgnt.MerchantPosMgntSC;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.DateUtil;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.PathPropertyUtil;
import com.path.lib.common.util.StringUtil;
import com.path.vo.common.AlertsParamCO;
import com.path.vo.common.CheckRequiredCO;
import com.path.vo.common.CommonLibSC;
import com.path.vo.common.audit.AuditRefCO;
import com.path.vo.core.alerts.AlertsSC;
/**
 * 
 * Copyright 2015, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: fatimasalam
 * 
 *          MerchantPosMgntBOImpl.java used to
 */
public class MerchantPosMgntBOImpl extends RetailBaseBO implements MerchantPosMgntBO
{
    private MerchantPosMgntDAO merchantPosMgntDAO;
    private AlertsBO alertsBO;

    /**
     * @return list of MerchantPosMgntCO for the main grid
     */
    public List<MerchantPosMgntCO> returnMerchantPosMgntList(MerchantPosMgntSC merchantPosMgntSC) throws BaseException
    {
	return merchantPosMgntDAO.returnMerchantPosMgntList(merchantPosMgntSC);
    }
    /**
     *  @return number of record for the main grid
     */
    public Integer returnMerchantPosMgntListCount(MerchantPosMgntSC merchantPosMgntSC) throws BaseException
    {
	return merchantPosMgntDAO.returnMerchantPosMgntListCount(merchantPosMgntSC);
    }


    /**
     * 
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     * @author fatimasalam
     */
    public MerchantPosMgntCO returnMerchantPosMgntBusinessDisplay(MerchantPosMgntCO merchantPosMgntCO)
	    throws BaseException
    {
	returnAddressDetails(merchantPosMgntCO);

	SYS_PARAM_SCREEN_DISPLAYVO screenParamStatusRemark = new SYS_PARAM_SCREEN_DISPLAYVO();
	screenParamStatusRemark.setIS_READONLY(BigDecimal.ONE);
	if(Boolean.valueOf(merchantPosMgntCO.getIsFromAlert()))
	{
	    screenParamStatusRemark.setIS_READONLY(BigDecimal.ZERO);
	    screenParamStatusRemark.setOverWriteReadOnly(true);
	}
	//merchantPosMgntCO.getElementHashmap().put("merchantPosMgntCO.ctsMerchantPosVO.STATUS_REMARK", screenParamStatusRemark);

	return merchantPosMgntCO;
    }

    /**
     * return the display of ddress
     * @param merchantPosMgntCO
     * 
     * @throws BaseException
     * @author fatimasalam
     */
    private void returnAddressDetails(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS1_ENG", "addressCO.ADDRESS1_ENG",
		    ConstantsCommon.ACTION_TYPE_LABEL, "Block_key", merchantPosMgntCO
			    .getElementHashmap(), null);
	
	CIFCONTROLVO cifControlVO = new CIFCONTROLVO();
	cifControlVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	cifControlVO = commonLibBO.returnCifControlDetails(cifControlVO);
	if(cifControlVO != null)
	{
	    if("1".equals(cifControlVO.getUSE_ADDRESS_DESC()))
	    {
		if(StringUtil.isNotEmpty(cifControlVO.getADDRESS1_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS1_ENG", "addressCO.ADDRESS1_ENG",
			    ConstantsCommon.ACTION_TYPE_LABEL, cifControlVO.getADDRESS1_ENG(), merchantPosMgntCO
				    .getElementHashmap(), null);

		}
		else
		{
		    commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS1_ENG", "addressCO.ADDRESS1_ENG",
			    ConstantsCommon.ACTION_TYPE_VISIBLE, "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getADDRESS2_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS2_ENG", "addressCO.ADDRESS2_ENG",
			    ConstantsCommon.ACTION_TYPE_LABEL, cifControlVO.getADDRESS2_ENG(), merchantPosMgntCO
				    .getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS2_ENG", "addressCO.ADDRESS2_ENG",
			    ConstantsCommon.ACTION_TYPE_VISIBLE, "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getADDRESS3_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS3_ENG", "addressCO.ADDRESS3_ENG",
			    ConstantsCommon.ACTION_TYPE_LABEL, cifControlVO.getADDRESS3_ENG(), merchantPosMgntCO
				    .getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS3_ENG", "addressCO.ADDRESS3_ENG",
			    ConstantsCommon.ACTION_TYPE_VISIBLE, "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getADDRESS4_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS4_ENG", "addressCO.ADDRESS4_ENG",
			    ConstantsCommon.ACTION_TYPE_LABEL, cifControlVO.getADDRESS4_ENG(), merchantPosMgntCO
				    .getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS4_ENG", "addressCO.ADDRESS4_ENG",
			    ConstantsCommon.ACTION_TYPE_VISIBLE, "0", merchantPosMgntCO.getElementHashmap(), null);
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.STREET_DETAILS_ENG",
			    ConstantsCommon.ACTION_TYPE_VISIBLE, "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getREGION_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.REGION", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getREGION_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.REGION", ConstantsCommon.ACTION_TYPE_VISIBLE,
			    "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getCITY_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.CITY_ENG", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getCITY_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.CITY_ENG", ConstantsCommon.ACTION_TYPE_VISIBLE,
			    "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getCOUNTRY_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.COUNTRY", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getCOUNTRY_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.COUNTRY", ConstantsCommon.ACTION_TYPE_VISIBLE,
			    "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getTEL_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.TEL", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getTEL_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.TEL", ConstantsCommon.ACTION_TYPE_VISIBLE, "0",
			    merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getWORK_TEL_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.WORK_TEL", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getWORK_TEL_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.WORK_TEL", ConstantsCommon.ACTION_TYPE_VISIBLE,
			    "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getMOBILE_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.MOBILE", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getMOBILE_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.MOBILE", ConstantsCommon.ACTION_TYPE_VISIBLE,
			    "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getOTHER_TEL_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.OTHER_TEL", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getOTHER_TEL_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.OTHER_TEL", ConstantsCommon.ACTION_TYPE_VISIBLE,
			    "0", merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getFAX_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.FAX", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getFAX_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.FAX", ConstantsCommon.ACTION_TYPE_VISIBLE, "0",
			    merchantPosMgntCO.getElementHashmap(), null);
		}

		if(StringUtil.isNotEmpty(cifControlVO.getEMAIL_ENG()))
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.EMAIL", ConstantsCommon.ACTION_TYPE_LABEL,
			    cifControlVO.getEMAIL_ENG(), merchantPosMgntCO.getElementHashmap(), null);
		}
		else
		{
		    commonLibBO.applyDynScreenDisplay(null, "addressCO.EMAIL", ConstantsCommon.ACTION_TYPE_VISIBLE,
			    "0", merchantPosMgntCO.getElementHashmap(), null);
		}
	    }

	    // wf_set_mandatory_color_address
	    if("1".equals(cifControlVO.getMENDATORY_ADDRESS()))
	    {
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS1_ENG", "addressCO.ADDRESS1_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);

	    }
	    else if("2".equals(cifControlVO.getMENDATORY_ADDRESS()))
	    {
		commonLibBO.applyDynScreenDisplay("addressCO.REGION", "addressCO.REGION",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS1_ENG", "addressCO.ADDRESS1_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS2_ENG", "addressCO.ADDRESS2_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
	    }
	    else if("3".equals(cifControlVO.getMENDATORY_ADDRESS()))
	    {
		commonLibBO.applyDynScreenDisplay("addressCO.REGION", "addressCO.REGION",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS1_ENG", "addressCO.ADDRESS1_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS2_ENG", "addressCO.ADDRESS2_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		applyDynScreenDisplay("addressCO.ADDRESS3_ENG", "addressCO.ADDRESS3_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
	    }
	    else if("4".equals(cifControlVO.getMENDATORY_ADDRESS()))
	    {
		commonLibBO.applyDynScreenDisplay("addressCO.REGION", "addressCO.REGION",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS1_ENG", "addressCO.ADDRESS1_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS2_ENG", "addressCO.ADDRESS2_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS3_ENG", "addressCO.ADDRESS3_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS4_ENG", "addressCO.ADDRESS4_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
	    }
	    else if("5".equals(cifControlVO.getMENDATORY_ADDRESS()))
	    {
		commonLibBO.applyDynScreenDisplay("addressCO.REGION", "addressCO.REGION",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS1_ENG", "addressCO.ADDRESS1_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS2_ENG", "addressCO.ADDRESS2_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS3_ENG", "addressCO.ADDRESS3_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.ADDRESS4_ENG", "addressCO.ADDRESS4_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
		commonLibBO.applyDynScreenDisplay("addressCO.CITY_ENG", "addressCO.CITY_ENG",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
	    }

	    if("Y".equals(cifControlVO.getREGION_IS_MANDATORY()))
	    {
		commonLibBO.applyDynScreenDisplay("addressCO.REGION", "addressCO.REGION",
			ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);
	    }
	}
	commonLibBO.applyDynScreenDisplay("addressCO.COUNTRY", "addressCO.COUNTRY",
		ConstantsCommon.ACTION_TYPE_MANDATORY, "1", merchantPosMgntCO.getElementHashmap(), null);

	commonLibBO.applyDynScreenDisplay("addressCO_TEL", "addressCO_TEL", ConstantsCommon.ACTION_TYPE_MANDATORY, "1",
		merchantPosMgntCO.getElementHashmap(), null);

	if(!NumberUtil.isEmptyDecimal(merchantPosMgntCO.getCtsMerchantPosVO().getCOUNTRY()))
	{
	    COUNTRIESVO countriesVO = new COUNTRIESVO();
	    countriesVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	    countriesVO.setCOUNTRY_CODE(merchantPosMgntCO.getCtsMerchantPosVO().getCOUNTRY());
	    countriesVO = (COUNTRIESVO) genericDAO.selectByPK(countriesVO);

	    String format = "###############";
	    if(countriesVO != null && StringUtil.isNotEmpty(countriesVO.getTELEPHONE_FORMAT()))
	    {
		format = StringUtil.replaceInString(countriesVO.getTELEPHONE_FORMAT(),
			    ConstantsCommon.PHONE_REPLACE_WHAT, ConstantsCommon.PHONE_REPLACE_WITH);
	    }

	    applyDynScreenDisplay(null, "addressCO.MOBILE", ConstantsCommon.ACTION_TYPE_TXTFORMAT, format,
		    merchantPosMgntCO.getElementHashmap(), null);
	    applyDynScreenDisplay(null, "addressCO.TEL", ConstantsCommon.ACTION_TYPE_TXTFORMAT, format,
		    merchantPosMgntCO.getElementHashmap(), null);
	    applyDynScreenDisplay(null, "addressCO.OTHER_TEL", ConstantsCommon.ACTION_TYPE_TXTFORMAT, format,
		    merchantPosMgntCO.getElementHashmap(), null);
	    applyDynScreenDisplay(null, "addressCO.WORK_TEL", ConstantsCommon.ACTION_TYPE_TXTFORMAT, format,
		    merchantPosMgntCO.getElementHashmap(), null);
	    applyDynScreenDisplay(null, "addressCO.FAX", ConstantsCommon.ACTION_TYPE_TXTFORMAT, format,
		    merchantPosMgntCO.getElementHashmap(), null);
	}

	SYS_PARAM_SCREEN_DISPLAYVO screenParamAddress = new SYS_PARAM_SCREEN_DISPLAYVO();
	screenParamAddress.setIS_VISIBLE(BigDecimal.ZERO);
	merchantPosMgntCO.getElementHashmap().put("addressCO.ADDRESS_DESC", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.CONTACT_NAME", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.SALUTATION_ENG", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.CONTACT_NAME_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.SALUTATION_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.ADDRESS1_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.ADDRESS2_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.ADDRESS3_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.ADDRESS4_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.STREET_DETAILS_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.CITY_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.GOVERNERATE_ARAB", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.EXPIRY_DATE", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.FROM_DATE", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.TO_DATE", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.permnAddrFlag", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.defaultAddrFlag", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.POSTAL_CODE", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.PO_BOX", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.POBOX_AREA", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.POSTAL_CODE", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.POSTAL_CODE", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.poBoxAreaDesc", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.postalcodeDesc", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.GOVERNERATE_ENG", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.ADDRESS_DESCRIPTION", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.residenceTypeDesc", screenParamAddress);
	merchantPosMgntCO.getElementHashmap().put("addressCO.printAddrFlag", screenParamAddress);

    }
    
    /**
     * dependencyByMerchantCode
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO dependencyByMerchantCode(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	if(NumberUtil.isEmptyDecimal(merchantPosMgntCO.getCtsMerchantPosVO().getMERCHANT_CODE()))
	{
	    merchantPosMgntCO.setMerchantCodeDesc(null);
	}
	else
	{
	    String merchantCodeDesc = merchantPosMgntDAO.returnMerchantCodeCif(merchantPosMgntCO);
	    if(merchantCodeDesc == null || ("").equals(merchantCodeDesc))
	    {
		throw new BOException(MessageCodes.VALUE_DOES_NOT_EXIST);
	    }
	    merchantPosMgntCO.setMerchantCodeDesc(merchantCodeDesc);
	}
	return merchantPosMgntCO;
    }

    /**
     * dependencyByTerminalId
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO dependencyByTerminalId(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	if(!"".equals(StringUtil.nullToEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getTERMINAL_ID())))
	{
	    int resultCount = merchantPosMgntDAO.returnServicesCount(merchantPosMgntCO);
	    if(resultCount == 0)
	    {
		throw new BOException(MessageCodes.VALUE_DOES_NOT_EXIST);
	    }
	    CTS_MERCHANT_POSVO ctsMerchantPosVO = merchantPosMgntDAO.returnCtsMerchantPos(merchantPosMgntCO);
	    if(ctsMerchantPosVO != null)
	    {
		BigDecimal code = NumberUtil.emptyDecimalToZero(merchantPosMgntCO.getCtsMerchantPosVO().getCODE());
		if(code.compareTo(ctsMerchantPosVO.getCODE()) != 0
			|| merchantPosMgntCO.getLoginBraCode().compareTo(ctsMerchantPosVO.getBRANCH_CODE()) != 0)
		{
		    throw new BOException(MessageCodes.DUPLICATE_VALUE);
		}
	    }
	}
	return merchantPosMgntCO;
    }

    /**
     * dependencyByInstallationDate
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO dependencyByInstallationDate(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	Date installationDate = merchantPosMgntCO.getCtsMerchantPosVO().getINSTALLATION_DATE();
	if(installationDate != null)
	{
	    Date oldDate = DateUtil.createDate(1880, 01, 01);
	    if(installationDate.compareTo(oldDate) < 0
		    || installationDate.compareTo(merchantPosMgntCO.getRunningDate()) > 0)
	    {
		throw new BOException(MessageCodes.INVALID_DATE);
	    }
	}
	return merchantPosMgntCO;
    }

    /**
     * load selected record from the Grid into the maint form
     * @param merchantPosMgntSC
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO returnMerchantPosMgntDetails(MerchantPosMgntSC merchantPosMgntSC) throws BaseException
    {
	MerchantPosMgntCO merchantPosMgntCO = merchantPosMgntDAO.returnMerchantPosMgntDetails(merchantPosMgntSC);
	if(merchantPosMgntCO != null && merchantPosMgntCO.getCtsMerchantPosVO() != null
		&& merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS() != null
		&& merchantPosMgntSC.getPosStatusCode() != null
		&& !merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS().equals(merchantPosMgntSC.getPosStatusCode()))
	{
	    throw new BOException(MessageCodes.DATA_NEEDS_TO_BE_REFRESHED);
	}

	PathPropertyUtil.copyProperties(merchantPosMgntCO, merchantPosMgntCO.getAddressCO(),
		"ctsMerchantPosVO.ADDRESS1 ADDRESS1_ENG", "ctsMerchantPosVO.ADDRESS2 ADDRESS2_ENG",
		"ctsMerchantPosVO.ADDRESS3 ADDRESS3_ENG", "ctsMerchantPosVO.ADDRESS4 ADDRESS4_ENG",
		"ctsMerchantPosVO.STREET_DETAILS STREET_DETAILS_ENG", "ctsMerchantPosVO.CITY CITY_ENG",
		"ctsMerchantPosVO.TEL TEL", "ctsMerchantPosVO.WORK_TEL WORK_TEL", "ctsMerchantPosVO.MOBILE MOBILE",
		"ctsMerchantPosVO.OTHER_TEL OTHER_TEL", "ctsMerchantPosVO.FAX FAX", "ctsMerchantPosVO.EMAIL EMAIL",
		"ctsMerchantPosVO.REGION REGION", "ctsMerchantPosVO.COUNTRY COUNTRY",
		"ctsMerchantPosVO.CITY_CODE CITY_CODE", "ctsMerchantPosVO.SECTOR_CODE SECTOR_CODE",
		"countryDesc countryDesc", "regionDesc regionDesc", "cityDesc cityDesc", "sectorDesc sectorDesc");

	merchantPosMgntCO.setLoginCompCode(merchantPosMgntSC.getCompCode());
	merchantPosMgntCO.setIsFromAlert(merchantPosMgntSC.getIsFromAlert());

	returnMerchantPosMgntBusinessDisplay(merchantPosMgntCO);
	return merchantPosMgntCO;
    }

    /**
     * apply the save of new record and the update of existing record
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO saveMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	PathPropertyUtil.copyProperties(merchantPosMgntCO.getAddressCO(), merchantPosMgntCO,
		"ADDRESS1_ENG ctsMerchantPosVO.ADDRESS1", "ADDRESS2_ENG ctsMerchantPosVO.ADDRESS2",
		"ADDRESS3_ENG ctsMerchantPosVO.ADDRESS3", "ADDRESS4_ENG ctsMerchantPosVO.ADDRESS4",
		"STREET_DETAILS_ENG ctsMerchantPosVO.STREET_DETAILS", "CITY_ENG ctsMerchantPosVO.CITY",
		"TEL ctsMerchantPosVO.TEL", "WORK_TEL ctsMerchantPosVO.WORK_TEL", "MOBILE ctsMerchantPosVO.MOBILE",
		"OTHER_TEL ctsMerchantPosVO.OTHER_TEL", "FAX ctsMerchantPosVO.FAX", "EMAIL ctsMerchantPosVO.EMAIL",
		"REGION ctsMerchantPosVO.REGION", "COUNTRY ctsMerchantPosVO.COUNTRY",
		"CITY_CODE ctsMerchantPosVO.CITY_CODE", "SECTOR_CODE ctsMerchantPosVO.SECTOR_CODE");

	merchantPosMgntCO.getCtsMerchantPosVO().setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	merchantPosMgntCO.getCtsMerchantPosVO().setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());
	posCheckData(merchantPosMgntCO);

	Date dateToSetTime = merchantPosMgntCO.getRunningDate();
	NumberUtil.resetEmptyValues(merchantPosMgntCO);
	merchantPosMgntCO.getCtsMerchantPosVO().setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	merchantPosMgntCO.getCtsMerchantPosVO().setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());

	// save new record
	if(NumberUtil.isEmptyDecimal(merchantPosMgntCO.getCtsMerchantPosVO().getCODE()))
	{
	    BigDecimal merchantPosMgntCode = commonLibBO.returnCTSCTR(merchantPosMgntCO.getCtsMerchantPosVO()
		    .getCOMP_CODE(), merchantPosMgntCO.getCtsMerchantPosVO().getBRANCH_CODE(), "POS", "C",
		    merchantPosMgntCO.getAppName(), merchantPosMgntCO.getLoginUserId(), merchantPosMgntCO.getOpt());
	    merchantPosMgntCO.getCtsMerchantPosVO().setCODE(merchantPosMgntCode);
	    merchantPosMgntCO.getCtsMerchantPosVO().setSTATUS(MerchantPosMgntConstant.STATUS_ACTIVE);
	    merchantPosMgntCO.getCtsMerchantPosVO().setCREATED_BY(merchantPosMgntCO.getLoginUserId());
	    merchantPosMgntCO.getCtsMerchantPosVO().setDATE_CREATED(commonLibBO.addSystemTimeToDate(dateToSetTime));
	    genericDAO.insert(merchantPosMgntCO.getCtsMerchantPosVO());
	    AuditRefCO refCO = merchantPosMgntCO.getAuditRefCO();
	    refCO.setOperationType(AuditConstant.CREATED);
	    refCO.setKeyRef(AuditConstant.MERCHANT_POS_MANAGEMENT_KEY_REF);
	    auditBO.callAudit(null, merchantPosMgntCO.getCtsMerchantPosVO(), merchantPosMgntCO.getAuditRefCO());
	}
	else
	// update existing record
	{
	    merchantPosMgntCO.getCtsMerchantPosVO().setSTATUS(MerchantPosMgntConstant.STATUS_ACTIVE);
	    merchantPosMgntCO.getCtsMerchantPosVO().setMODIFIED_BY(merchantPosMgntCO.getLoginUserId());
	    merchantPosMgntCO.getCtsMerchantPosVO().setDATE_MODIFIED(commonLibBO.addSystemTimeToDate(dateToSetTime));
	    merchantPosMgntCO.getCtsMerchantPosVO().setADDRESS1(merchantPosMgntCO.getAddressCO().getADDRESS1_ENG());
	    int result = genericDAO.update(merchantPosMgntCO.getCtsMerchantPosVO());
	    if(result == 0)
	    {
		throw new BOException(MessageCodes.RECORD_CHANGED);
	    }
	    MerchantPosMgntCO oldMerchantPosMgntCO = (MerchantPosMgntCO) merchantPosMgntCO.getAuditObj();
	    NumberUtil.resetEmptyValues(oldMerchantPosMgntCO);
	    auditBO.callAudit(oldMerchantPosMgntCO.getCtsMerchantPosVO(), merchantPosMgntCO.getCtsMerchantPosVO(),
		    merchantPosMgntCO.getAuditRefCO());
	    auditBO.insertAudit(merchantPosMgntCO.getAuditRefCO());
	}
	return merchantPosMgntCO;
    }

    /**
     * mandatory fields management
     * @param merchantPosMgntCO
     * 
     * @throws BaseException
     *@author fatimasalam
     */
    private void posCheckData(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	returnMerchantPosMgntBusinessDisplay(merchantPosMgntCO);

	CheckRequiredCO checkRequiredCO = new CheckRequiredCO();
	checkRequiredCO.setCompCode(merchantPosMgntCO.getCtsMerchantPosVO().getCOMP_CODE());
	checkRequiredCO.setObj_value(merchantPosMgntCO);
	checkRequiredCO.setOpt(merchantPosMgntCO.getOpt());
	checkRequiredCO.setLanguage(merchantPosMgntCO.getLoginPreferrredLanguage());
	checkRequiredCO.setApp(merchantPosMgntCO.getAppName());
	commonLibBO.checkRequired(checkRequiredCO);

	CIFCONTROLVO cifControlVO = new CIFCONTROLVO();
	cifControlVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	cifControlVO = commonLibBO.returnCifControlDetails(cifControlVO);
	if(cifControlVO != null)
	{
	    // wf_check_data
	    if("1".equals(cifControlVO.getMENDATORY_ADDRESS())&& !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS1()))
	    {
		    throw new BOException(MessageCodes.ADREES_ARE_MISING);
		
	    }
	   
	    if(("2".equals(cifControlVO.getMENDATORY_ADDRESS())) && (!StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS1())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS2())))
	    {
		    throw new BOException(MessageCodes.ADREES_ARE_MISING);
	    }
	   

	  
	    if(("3".equals(cifControlVO.getMENDATORY_ADDRESS()))&&(!StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS1())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS2())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS3())))
	    {
		    throw new BOException(MessageCodes.ADREES_ARE_MISING);
	    }
	   

	   
	    if(("4".equals(cifControlVO.getMENDATORY_ADDRESS()))&&(!StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS1())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS2())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS3())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS4())))
	    {
		    throw new BOException(MessageCodes.ADREES_ARE_MISING);
	    }
	  

	    
	    if(("5".equals(cifControlVO.getMENDATORY_ADDRESS()))&&(!StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS1())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS2())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS3())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS4())
			|| !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getCITY())))
	    {
		    throw new BOException(MessageCodes.ADREES_ARE_MISING);
	    }
	    

	  
	    if(("6".equals(cifControlVO.getMENDATORY_ADDRESS()))&&(!StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS1())
			&& !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS2())
			&& !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getADDRESS3())))
		{
		    throw new BOException(MessageCodes.ADREES_ARE_MISING);
		}
	    

	    if(NumberUtil.isEmptyDecimal(merchantPosMgntCO.getCtsMerchantPosVO().getCOUNTRY()))
	    {
		throw new BOException(MessageCodes.COUNTRY_CODE_VALUE_MISSING);
	    }

	    if("Y".equals(cifControlVO.getREGION_IS_MANDATORY())
		    && NumberUtil.isEmptyDecimal(merchantPosMgntCO.getCtsMerchantPosVO().getREGION()))
	    {
		throw new BOException(MessageCodes.PLEASE_ENTER_REGION);
	    }
	}
	if(!StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getTEL())
		&& !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getWORK_TEL())
		&& !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getOTHER_TEL())
		&& !StringUtil.isNotEmpty(merchantPosMgntCO.getCtsMerchantPosVO().getMOBILE()))
	{
	    throw new BOException(MessageCodes.TELEPHONES_ARE_MISING);
	}

	if(!NumberUtil.isEmptyDecimal(merchantPosMgntCO.getCtsMerchantPosVO().getCOUNTRY()))
	{
	    COUNTRIESVO countriesVO = new COUNTRIESVO();
	    countriesVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	    countriesVO.setCOUNTRY_CODE(merchantPosMgntCO.getCtsMerchantPosVO().getCOUNTRY());
	    countriesVO = (COUNTRIESVO) genericDAO.selectByPK(countriesVO);

	    String format = "###############";
	    if(countriesVO != null && StringUtil.isNotEmpty(countriesVO.getTELEPHONE_FORMAT()))
	    {
		format = StringUtil.replaceInString(countriesVO.getTELEPHONE_FORMAT(),
			ConstantsCommon.PHONE_REPLACE_WHAT, ConstantsCommon.PHONE_REPLACE_WITH);
	    }
	    String decryptValue = unformatPhoneNumber(format);

	    String tel = merchantPosMgntCO.getCtsMerchantPosVO().getTEL();
	    String unformatTel = unformatPhoneNumber(tel);
	    unformatTel = unformatTel.replace("_", "");

	    String workTel = merchantPosMgntCO.getCtsMerchantPosVO().getWORK_TEL();
	    String unformatWorkTel = unformatPhoneNumber(workTel);
	    unformatWorkTel = unformatWorkTel.replace("_", "");

	    String mobil = merchantPosMgntCO.getCtsMerchantPosVO().getMOBILE();
	    String unformatMobil = unformatPhoneNumber(mobil);
	    unformatMobil = unformatMobil.replace("_", "");

	    String otherTel = merchantPosMgntCO.getCtsMerchantPosVO().getOTHER_TEL();
	    String unformatOtherTel = unformatPhoneNumber(otherTel);
	    unformatOtherTel = unformatOtherTel.replace("_", "");

	    String fax = merchantPosMgntCO.getCtsMerchantPosVO().getFAX();
	    String unformatFax = unformatPhoneNumber(fax);
	    unformatFax = unformatFax.replace("_", "");

	    // if there is a format applied on the telephone
	    if(StringUtil.isNotEmpty(decryptValue) && (decryptValue.length() > unformatTel.length())
		    && (decryptValue.length() > unformatWorkTel.length())
		    && (decryptValue.length() > unformatMobil.length())
		    && (decryptValue.length() > unformatOtherTel.length())
		    && (decryptValue.length() > unformatFax.length()))
	    {
		throw new BOException(MessageCodes.TELEPHONES_ARE_MISING);
	    }

	    if(StringUtil.isNotEmpty(decryptValue)
		    && (StringUtil.isNotEmpty(unformatTel) && decryptValue.length() > unformatTel.length()))
	    {
		merchantPosMgntCO.getCtsMerchantPosVO().setTEL(null);
	    }
	    if(StringUtil.isNotEmpty(decryptValue)
		    && (StringUtil.isNotEmpty(unformatWorkTel) && decryptValue.length() > unformatWorkTel.length()))
	    {
		merchantPosMgntCO.getCtsMerchantPosVO().setWORK_TEL(null);
	    }
	    if(StringUtil.isNotEmpty(decryptValue)
		    && (StringUtil.isNotEmpty(unformatMobil) && decryptValue.length() > unformatMobil.length()))
	    {
		merchantPosMgntCO.getCtsMerchantPosVO().setMOBILE(null);
	    }
	    if(StringUtil.isNotEmpty(decryptValue)
		    && (StringUtil.isNotEmpty(unformatOtherTel) && decryptValue.length() > unformatOtherTel.length()))
	    {
		merchantPosMgntCO.getCtsMerchantPosVO().setOTHER_TEL(null);
	    }
	    if(StringUtil.isNotEmpty(decryptValue)
		    && (StringUtil.isNotEmpty(unformatFax) && decryptValue.length() > unformatFax.length()))
	    {
		merchantPosMgntCO.getCtsMerchantPosVO().setFAX(null);
	    }
	}

    }
    
    /**
     * @description this function is used to unformat a phone number in order to
     *              be saved in the database
     * @param phoneFormatted
     * TODO should be moved to StringUtil but no access
     */
    private String unformatPhoneNumber(String phoneFormatted)
    {
	 //if the value is null it doesn't need unformat
	if(StringUtil.isNotEmpty(phoneFormatted))
	{
	    Pattern p = Pattern.compile( ConstantsCommon.PHONE_REGEX, Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
	    return p.matcher(phoneFormatted).replaceAll("");
	}
	return phoneFormatted;
    }

    /**
     * approve Merchant Pos Mgnt record
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO approveMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	CTS_MERCHANT_POSVO ctsMerchantPosVO = new CTS_MERCHANT_POSVO();
	ctsMerchantPosVO.setSTATUS(MerchantPosMgntConstant.STATUS_APPROVED);
	ctsMerchantPosVO.setAPPROVED_BY(merchantPosMgntCO.getLoginUserId());
	Date dateToSetTime = merchantPosMgntCO.getRunningDate();
	ctsMerchantPosVO.setDATE_APPROVED(commonLibBO.addSystemTimeToDate(dateToSetTime));
	ctsMerchantPosVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	ctsMerchantPosVO.setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());
	ctsMerchantPosVO.setCODE(merchantPosMgntCO.getCtsMerchantPosVO().getCODE());
	ctsMerchantPosVO.setSENT_FLAG("0");

	int result = genericDAO.update(ctsMerchantPosVO);
	if(result <= 0)
	{
	    throw new BOException(MessageCodes.RECORD_CHANGED);
	}

	MerchantPosMgntCO oldMerchantPosMgntCO = (MerchantPosMgntCO) merchantPosMgntCO.getAuditObj();
	auditBO.callAudit(oldMerchantPosMgntCO.getCtsMerchantPosVO(), ctsMerchantPosVO, merchantPosMgntCO
		.getAuditRefCO());
	auditBO.insertAudit(merchantPosMgntCO.getAuditRefCO());

	if(merchantPosMgntCO.getAlertsParamCO() != null && Boolean.valueOf(merchantPosMgntCO.getIsFromAlert()))
	{
	    alertsBO.updateTodoAndSendAck(merchantPosMgntCO.getAlertsParamCO());
	}

	return merchantPosMgntCO;
    }

    /**
     * approve cancel Merchant Pos Mgnt record
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO cancelMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	CTS_MERCHANT_POSVO ctsMerchantPosVO = new CTS_MERCHANT_POSVO();
	ctsMerchantPosVO.setSTATUS(MerchantPosMgntConstant.STATUS_CANCELLED);
	ctsMerchantPosVO.setCANCELED_BY(merchantPosMgntCO.getLoginUserId());
	Date dateToSetTime = merchantPosMgntCO.getRunningDate();
	ctsMerchantPosVO.setDATE_CANCELED(commonLibBO.addSystemTimeToDate(dateToSetTime));
	ctsMerchantPosVO.setSENT_FLAG(MerchantPosMgntConstant.MERCHANTPOSMGNT_SENT_FLAG);
	ctsMerchantPosVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	ctsMerchantPosVO.setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());
	ctsMerchantPosVO.setCODE(merchantPosMgntCO.getCtsMerchantPosVO().getCODE());
	int result = genericDAO.update(ctsMerchantPosVO);
	if(result <= 0)
	{
	    throw new BOException(MessageCodes.RECORD_CHANGED);
	}

	MerchantPosMgntCO oldMerchantPosMgntCO = (MerchantPosMgntCO) merchantPosMgntCO.getAuditObj();
	auditBO.callAudit(oldMerchantPosMgntCO.getCtsMerchantPosVO(), ctsMerchantPosVO, merchantPosMgntCO
		.getAuditRefCO());
	auditBO.insertAudit(merchantPosMgntCO.getAuditRefCO());

	if(merchantPosMgntCO.getAlertsParamCO() != null && Boolean.valueOf(merchantPosMgntCO.getIsFromAlert()))
	{
	    alertsBO.updateTodoAndSendAck(merchantPosMgntCO.getAlertsParamCO());
	}
	return merchantPosMgntCO;
    }

    /**
     * cancel Merchant Pos Mgnt record
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO toCancelMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	CTS_MERCHANT_POSVO ctsMerchantPosVO = new CTS_MERCHANT_POSVO();
	ctsMerchantPosVO.setSTATUS(MerchantPosMgntConstant.STATUS_TOCANCEL);
	ctsMerchantPosVO.setTOBE_CANCELED_BY(merchantPosMgntCO.getLoginUserId());
	Date dateToSetTime = merchantPosMgntCO.getRunningDate();
	ctsMerchantPosVO.setDATE_TOBE_CANCELED(commonLibBO.addSystemTimeToDate(dateToSetTime));
	ctsMerchantPosVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	ctsMerchantPosVO.setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());
	ctsMerchantPosVO.setCODE(merchantPosMgntCO.getCtsMerchantPosVO().getCODE());
	int result = genericDAO.update(ctsMerchantPosVO);
	if(result <= 0)
	{
	    throw new BOException(MessageCodes.RECORD_CHANGED);
	}
	merchantPosMgntCO.getCtsMerchantPosVO().setSTATUS(ctsMerchantPosVO.getSTATUS());
	MerchantPosMgntCO oldMerchantPosMgntCO = (MerchantPosMgntCO) merchantPosMgntCO.getAuditObj();
	auditBO.callAudit(oldMerchantPosMgntCO.getCtsMerchantPosVO(), ctsMerchantPosVO, merchantPosMgntCO
		.getAuditRefCO());
	auditBO.insertAudit(merchantPosMgntCO.getAuditRefCO());
	return merchantPosMgntCO;
    }

    /**
     * delete Merchant Pos Mgnt record
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO deleteMerchantPosMgnt(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	if(MerchantPosMgntConstant.STATUS_ACTIVE.equals(merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS()))
	{
	    CTS_MERCHANT_POSVO ctsMerchantPosVO = new CTS_MERCHANT_POSVO();
	    ctsMerchantPosVO.setSTATUS(MerchantPosMgntConstant.STATUS_DELETED);
	    ctsMerchantPosVO.setDELETED_BY(merchantPosMgntCO.getLoginUserId());
	    Date dateToSetTime = merchantPosMgntCO.getRunningDate();
	    ctsMerchantPosVO.setDATE_DELETED(commonLibBO.addSystemTimeToDate(dateToSetTime));
	    ctsMerchantPosVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	    ctsMerchantPosVO.setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());
	    ctsMerchantPosVO.setCODE(merchantPosMgntCO.getCtsMerchantPosVO().getCODE());
	    int result = genericDAO.update(ctsMerchantPosVO);
	    if(result <= 0)
	    {
		throw new BOException(MessageCodes.RECORD_CHANGED);
	    }

	    MerchantPosMgntCO oldMerchantPosMgntCO = (MerchantPosMgntCO) merchantPosMgntCO.getAuditObj();
	    auditBO.callAudit(oldMerchantPosMgntCO.getCtsMerchantPosVO(), ctsMerchantPosVO, merchantPosMgntCO
		    .getAuditRefCO());
	    auditBO.insertAudit(merchantPosMgntCO.getAuditRefCO());
	}

	else
	{
	    throw new BOException(MessageCodes.CANNOT_DELETE);
	}
	return merchantPosMgntCO;
    }
  
    /**
     * This function will return the alerts param co
     * @param merchantPosMgntCO
     * @return alertsParamCO
     * @throws BaseException
     *@author fatimasalam
     */
    public AlertsParamCO returnAlertsParamCO(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	AlertsParamCO alertsParamCO = new AlertsParamCO();
	String status = merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS();
	if(status == null)
	{
	    status = MerchantPosMgntConstant.STATUS_ACTIVE;
	}

	AlertsParamCO returedAlertsParamCO = checkSendAlert(merchantPosMgntCO, alertsParamCO);
	if(returedAlertsParamCO.getAllowToSend() != null
		&& BigDecimal.ZERO.equals(returedAlertsParamCO.getAllowToSend()))
	{
	    return null;
	}
	alertsParamCO.setCompCode(merchantPosMgntCO.getCtsMerchantPosVO().getCOMP_CODE());
	alertsParamCO.setBranchCode(merchantPosMgntCO.getCtsMerchantPosVO().getBRANCH_CODE());
	alertsParamCO.setStatus(merchantPosMgntCO.getStatusDesc());
	alertsParamCO.setAmount(BigDecimal.ZERO);
	String alertDescriptionPattern = AlertsConstants.MERCHANTPOSMGNT_ALERT_DESCRIPTION_PATTERN;

	MerchantPosMgntCO merchantPosMgntForDescCO = merchantPosMgntDAO.returnMerchantCifDetails(merchantPosMgntCO);
	String alertDescription = MessageFormat.format(alertDescriptionPattern, merchantPosMgntCO.getCtsMerchantPosVO()
		.getCODE(), merchantPosMgntCO.getCtsMerchantPosVO().getPOS_DESC(), merchantPosMgntCO
		.getCtsMerchantPosVO().getMERCHANT_CODE(), merchantPosMgntForDescCO.getCifNo(),
		merchantPosMgntForDescCO.getCifDesc());

	alertsParamCO.setAlertDescription(alertDescription);
	alertsParamCO.setReasonCode(AlertsConstants.MERCHANTPOSMGNT_ALERT_REASON_CODE);
	alertsParamCO.setAlertType(AlertsConstants.MERCHANTPOSMGNT_ALERT_TYPE);
	alertsParamCO.setTrsNo(merchantPosMgntCO.getCtsMerchantPosVO().getCODE());
	alertsParamCO.setTodoAlert(AlertsConstants.MERCHANTPOSMGNT_TODO_ALERT);
	alertsParamCO.setTodoParam(merchantPosMgntCO.getCtsMerchantPosVO().getCODE().toString());
	alertsParamCO.setTodoAlertOldStatus(merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS());
	alertsParamCO.setActionType(AlertsConstants.ACTION_TYPE_X);
	alertsParamCO.setDistributionType(AlertsConstants.DISTRIBUTION_TYPE_B);
	alertsParamCO.setDistributionTo(AlertsConstants.DISTRIBUTION_TO_U);
	alertsParamCO.setTodoType(AlertsConstants.TODO_TYPE_P);
	alertsParamCO.setTodoPriority(AlertsConstants.TODO_PRIORITY_A);
	alertsParamCO.setTodoChecked(AlertsConstants.TODO_CHECKED_U);
	alertsParamCO.setTodoExecution(AlertsConstants.TODO_EXECUTION_N);
	alertsParamCO.setAllowToSend(BigDecimal.ONE);
	alertsParamCO.setTodoExternal("0");
	alertsParamCO.setTrsNo(BigDecimal.ZERO);
	alertsParamCO.setTodoTellerBranch(BigDecimal.ZERO);
	alertsParamCO.setTodoTellerId(merchantPosMgntCO.getLoginTellerCode());
	alertsParamCO.setCIF_NO(BigDecimal.ZERO);
	NumberUtil.resetEmptyValues(alertsParamCO);
	return alertsParamCO;
    }

    /**
     * This function will check if we should send the alert
     * @param merchantPosMgntCO
     * @param alertParams
     * @return alertsParamCO
     * @throws BaseException
     *@author fatimasalam
     */
    private AlertsParamCO checkSendAlert(MerchantPosMgntCO merchantPosMgntCO, AlertsParamCO alertParams)
	    throws BaseException
    {
	if("1".equals(merchantPosMgntCO.getUserIsBranchManager()))
	{
	    alertParams.setAllowToSend(BigDecimal.ZERO);
	    return alertParams;
	}
	String pageRef = merchantPosMgntCO.getOpt();
	AlertsParamCO saveAsCO = alertsBO.returnSaveAsOptDetails(merchantPosMgntCO.getAppName(), pageRef);
	if(saveAsCO != null && saveAsCO.isFromSaveAs())
	{
	    pageRef = saveAsCO.getOriginalProgRef();
	}

	String status = merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS();
	AlertsSC alertSC = new AlertsSC();
	alertSC.setCompCode(merchantPosMgntCO.getLoginCompCode());
	alertSC.setBranchCode(merchantPosMgntCO.getLoginBraCode());
	alertSC.setAppName(merchantPosMgntCO.getAppName());
	alertSC.setAlertType(AlertsConstants.MERCHANTPOSMGNT_ALERT_REASON_CODE);
	alertSC.setProgRef(pageRef);
	if(!MerchantPosMgntConstant.STATUS_APPROVED.equals(status)
		&& !MerchantPosMgntConstant.STATUS_CANCELLED.equals(status))
	{
	    alertSC.setAlertAddCode(AlertsConstants.MERCHANTPOSMGNT_ALERT_ADD_CODE_RI);
	    CTSCONTROL_ALERTVO ctsControlAlert = alertsBO.returnCtsControlAlert(alertSC); // f_fill_check_alert_flag
	    if(ctsControlAlert != null && "1".equals(ctsControlAlert.getACTIVATE()))
	    {
		if(MerchantPosMgntConstant.STATUS_ACTIVE.equals(status))
		{
		    alertParams.setProgRef(AlertsConstants.MERCHANTPOSMGNT_PAGE_P);
		}
		else if(MerchantPosMgntConstant.STATUS_TOCANCEL.equals(status))
		{
		    alertParams.setProgRef(AlertsConstants.MERCHANTPOSMGNT_PAGE_N);
		}
		alertParams.setAllowToSend(BigDecimal.ONE);
	    }
	    else
	    {
		alertParams.setAllowToSend(BigDecimal.ZERO);
	    }
	}
	if(StringUtil.isNotEmpty(alertParams.getProgRef()) && saveAsCO != null && saveAsCO.isFromSeriesSaveAs())
	{
	    CommonLibSC commonLibSc = new CommonLibSC();
	    commonLibSc.setAppName(merchantPosMgntCO.getAppName());
	    commonLibSc.setOptReference(alertParams.getProgRef());
	    commonLibSc.setProgRef(saveAsCO.getSeriesProgRef());
	    alertParams.setProgRef(commonLibBO.returnSeriesOptByRef(commonLibSc));
	}
	return alertParams;
    }

    /**
     * This function will reject the alert
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO alertReject(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	checkMerchantPosStatus(merchantPosMgntCO);

	// if(merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS_REMARK() == null
	// ||
	// merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS_REMARK().isEmpty())
	// {
	// throw new BOException(MessageCodes.PLEASE_FILL, new String[] {
	// "remarks_key" });
	// }

	CTS_MERCHANT_POSVO ctsMerchantPosVO = new CTS_MERCHANT_POSVO();
	ctsMerchantPosVO.setSTATUS_REMARK(merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS_REMARK());
	if(MerchantPosMgntConstant.STATUS_ACTIVE.equals(merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS()))
	{
	    ctsMerchantPosVO.setSTATUS(MerchantPosMgntConstant.STATUS_ACTIVE);
	}
	else if(MerchantPosMgntConstant.STATUS_TOCANCEL.equals(merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS()))
	{
	    ctsMerchantPosVO.setSTATUS(MerchantPosMgntConstant.STATUS_APPROVED);
	}

	ctsMerchantPosVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	ctsMerchantPosVO.setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());
	ctsMerchantPosVO.setCODE(merchantPosMgntCO.getCtsMerchantPosVO().getCODE());
	int result = genericDAO.update(ctsMerchantPosVO);
	if(result <= 0)
	{
	    throw new BOException(MessageCodes.RECORD_CHANGED);
	}

	/*
	 * merchant_response\\ue_check_alert.f_update_todo ; Send an acknowledge
	 * alert in case of remote approve through openItem screen. In local
	 * approve the acknowledgment will not be senT f_alert_acknowledgment
	 */
	if(merchantPosMgntCO.getAlertsParamCO() != null && Boolean.valueOf(merchantPosMgntCO.getIsFromAlert()))
	{
	    alertsBO.updateTodoAndSendAck(merchantPosMgntCO.getAlertsParamCO());
	}
	return merchantPosMgntCO;
    }

    /**
     * This function will load the alert details
     * @param merchantPosMgntCO
     * @return merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public MerchantPosMgntCO loadAlertMerchantPosDetails(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	CTS_MERCHANT_POSVO merchantPosVO = new CTS_MERCHANT_POSVO();
	merchantPosVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	merchantPosVO.setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());
	merchantPosVO.setCODE(new BigDecimal(merchantPosMgntCO.getAlertsParamCO().getTodoParam()));
	merchantPosVO = (CTS_MERCHANT_POSVO) genericDAO.selectByPK(merchantPosVO);
	if(merchantPosVO != null)
	{
	    String alertDescriptionPattern = AlertsConstants.MERCHANTPOSMGNT_ALERT_DESCRIPTION_PATTERN;
	    merchantPosMgntCO.getCtsMerchantPosVO().setMERCHANT_CODE(merchantPosVO.getMERCHANT_CODE());
	    MerchantPosMgntCO merchantPosMgntForDescCO = merchantPosMgntDAO.returnMerchantCifDetails(merchantPosMgntCO);

	    String alertDescription = MessageFormat.format(alertDescriptionPattern, merchantPosVO.getCODE(),
		    merchantPosVO.getPOS_DESC(), merchantPosVO.getMERCHANT_CODE(), merchantPosMgntForDescCO.getCifNo(),
		    merchantPosMgntForDescCO.getCifDesc());

	    merchantPosMgntCO.getAlertsParamCO().setAlertDescription(alertDescription);
	}

	return merchantPosMgntCO;
    }

    /**
     * This function will check is the status of the recored in the DB is the
     * same like merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS
     * @param merchantPosMgntCO
     * @throws BaseException
     *@author fatimasalam
     */
    public void checkMerchantPosStatus(MerchantPosMgntCO merchantPosMgntCO) throws BaseException
    {
	// check if the merchant pos found record still has the same
	// status. PB = IF f_check_alert_trx_status(istr_alert) = -1 THEN
	CTS_MERCHANT_POSVO merchantPosVO = new CTS_MERCHANT_POSVO();
	merchantPosVO.setCOMP_CODE(merchantPosMgntCO.getLoginCompCode());
	merchantPosVO.setBRANCH_CODE(merchantPosMgntCO.getLoginBraCode());
	merchantPosVO.setCODE(merchantPosMgntCO.getCtsMerchantPosVO().getCODE());
	merchantPosVO = (CTS_MERCHANT_POSVO) genericDAO.selectByPK(merchantPosVO);

	if(!(merchantPosVO != null && merchantPosVO.getSTATUS() != null && merchantPosVO.getSTATUS().equals(
		merchantPosMgntCO.getCtsMerchantPosVO().getSTATUS()))

		&& merchantPosMgntCO.getAlertsParamCO() != null)
	{
	    S_TODO_DETVO currentSTodoDet = new S_TODO_DETVO();
	    currentSTodoDet.setTODO_CODE(merchantPosMgntCO.getAlertsParamCO().getTodoCode());
	    currentSTodoDet.setTODO_LINE(merchantPosMgntCO.getAlertsParamCO().getTodoLine());
	    alertsBO.fUpdateTodo(currentSTodoDet, AlertsConstants.UPDATE_NO_TRX);
	    throw new BOException(MessageCodes.RECORD_CHANGED);
	}

	// Check if the alerts has the same status
	if(merchantPosMgntCO.getAlertsParamCO() != null
		&& !Boolean.valueOf(merchantPosMgntCO.getAlertsParamCO().getIsLocalApprove()))
	{
	    alertsBO.checkIfSameStatus(merchantPosMgntCO.getAlertsParamCO());
	}
    }

    public MerchantPosMgntDAO getMerchantPosMgntDAO()
    {
	return merchantPosMgntDAO;
    }

    public void setMerchantPosMgntDAO(MerchantPosMgntDAO merchantPosMgntDAO)
    {
	this.merchantPosMgntDAO = merchantPosMgntDAO;
    }

    public void setAlertsBO(AlertsBO alertsBO)
    {
	this.alertsBO = alertsBO;
    }

    public AlertsBO getAlertsBO()
    {
	return alertsBO;
    }

}
