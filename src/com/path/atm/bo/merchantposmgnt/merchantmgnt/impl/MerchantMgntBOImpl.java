package com.path.atm.bo.merchantposmgnt.merchantmgnt.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.path.atm.bo.merchantposmgnt.merchantmgnt.MerchantMgntBO;
import com.path.atm.bo.merchantposmgnt.merchantmgnt.MerchantMgntConstant;
import com.path.atm.dao.merchantposmgnt.merchantmgnt.MerchantMgntDAO;
import com.path.atm.vo.merchantposmgnt.merchantmgnt.MerchantMgntCO;
import com.path.atm.vo.merchantposmgnt.merchantmgnt.MerchantMgntSC;
import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.bo.common.alerts.AlertsBO;
import com.path.bo.common.alerts.AlertsConstants;
import com.path.bo.common.audit.AuditConstant;
import com.path.bo.common.login.LoginBO;
import com.path.bo.core.account.AccountBO;
import com.path.bo.core.cif.CifBO;
import com.path.bo.core.common.base.RetailBaseBO;
import com.path.bo.core.csmfom.NCifMaintenanceConstant;
import com.path.bo.core.gl.GLBO;
import com.path.dbmaps.vo.AMFVO;
import com.path.dbmaps.vo.CIFVO;
import com.path.dbmaps.vo.CIFVOKey;
import com.path.dbmaps.vo.CTSCONTROLVO;
import com.path.dbmaps.vo.CTSCONTROL_ALERTVO;
import com.path.dbmaps.vo.CTS_MERCHANTVO;
import com.path.dbmaps.vo.CURRENCIESVO;
import com.path.dbmaps.vo.GEN_LEDGERVO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.StringUtil;
import com.path.vo.admin.cif.CIFCO;
import com.path.vo.admin.user.UsrCO;
import com.path.vo.common.AlertsParamCO;
import com.path.vo.common.CheckRequiredCO;
import com.path.vo.common.CommonLibSC;
import com.path.vo.common.audit.AuditRefCO;
import com.path.vo.common.reportresponse.ReportResponseCO;
import com.path.vo.core.account.AccountCO;
import com.path.vo.core.account.AccountSC;
import com.path.vo.core.alerts.AlertsSC;
import com.path.vo.core.cif.CifSC;
import com.path.vo.core.csmfom.FomCO;
import com.path.vo.core.ctsreparg.CtsReportArgSC;
import com.path.vo.core.gl.GLSC;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: jihanemazloum
 * 
 *          
 */

public class MerchantMgntBOImpl extends RetailBaseBO implements MerchantMgntBO{
 
	private MerchantMgntDAO merchantMgntDAO;
	private CifBO cifBO;
	//private NCifMaintenanceBO nCifMaintenanceBO;
	private LoginBO  loginBO;
	private AccountBO accountBO;
	private AlertsBO alertsBO; 
	private GLBO glBO;

	public MerchantMgntDAO getMerchantMgntDAO() {
		return merchantMgntDAO;
	}

	public void setMerchantMgntDAO(MerchantMgntDAO merchantMgntDAO) {
		this.merchantMgntDAO = merchantMgntDAO;
	}

    /**
     * @return list of MerchantMgntCO for the main grid
     */
    public List<MerchantMgntCO> returnMerchantMgntList(MerchantMgntSC merchantMgntSC) throws BaseException
    {
	return merchantMgntDAO.returnMerchantMgntList(merchantMgntSC);
    }

	/**
	 *  @return number of record for the main grid
	 */
	public Integer returnMerchantMgntListCount(MerchantMgntSC merchantMgntSC) throws BaseException {
		return merchantMgntDAO.returnMerchantMgntListCount(merchantMgntSC);
	}

	 /*
	  * to open FOM screen upon clicking on CIF details button
	  */
	public MerchantMgntCO onCIFDetailsClicked(MerchantMgntCO merchantMgntCO, CifSC cifSc) throws BaseException
	{
	       // get cif details
		CIFVOKey cifKey = new CIFVOKey();
		cifKey.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
		cifKey.setCIF_NO(cifSc.getCifCode());
		CIFVO cifvo = (CIFVO) genericDAO.selectByPK(cifKey);
		//Bug # 339474 -- [John Massaad] 
		if(cifvo!=null)
		{
		    merchantMgntCO.setCifVO(cifvo);
		}
		//Bug # 339474 -- [John Massaad] 
        BigDecimal access = new BigDecimal(1);//cifBO.checkTellerCifAccess(cifSc);
		if(access != null && access.intValue() == 1)
		{
		    FomCO fomCO = new FomCO();
		    // fill user details
		    UsrCO usrCO = loginBO.userLoginDet(merchantMgntCO.getLoginUserId());
		    fomCO.setUsrCO(usrCO);
		    fomCO.setCifVO(cifvo);
		    
		    // in PBD:f_check_teller_cif_role 
		    Integer role = 1;//nCifMaintenanceBO.checkTellerRoleCode(fomCO);
		    if(role == -1)
		    {
			throw new BOException(MessageCodes.USER_DOES_NOT_HAVE_PRIVILEGES_ACCESS, new String[] { cifSc.getCifCode().toString() });
		    }
		}
		else
		{
		    throw new BOException(MessageCodes.USER_DOES_NOT_HAVE_PRIVILEGES_ACCESS, new String[] { cifSc.getCifCode().toString() });
		}
		return merchantMgntCO;
	}

	//load selected record from the Grid into the maint form
	public MerchantMgntCO returnMerchantMgntDetails(MerchantMgntSC merchantMgntSC) throws BaseException
	{ 
	    MerchantMgntCO merchantMgntCO =  merchantMgntDAO.returnMerchantMgntDetails(merchantMgntSC);
	    applyMerchantTypeBusinessElement(merchantMgntCO);
	    return merchantMgntCO;
	}
	
	    /**
	     * BURJ130313
	     * @author FatimaSalam
	     */
    private void applyMerchantTypeBusinessElement(MerchantMgntCO merchantMgntCO) throws BaseException
    {
	if("1".equals(merchantMgntCO.getCtsMerchantVO().getMERCHANT_TYPE()))
	{
	    commonLibBO.applyDynScreenDisplay("pathAccount_merchantAccount", "pathAccount_merchantAccount",
		    ConstantsCommon.ACTION_TYPE_VISIBLE, "0", merchantMgntCO.getHm(), null);
	    commonLibBO.applyDynScreenDisplay("tbl_pathAccount_merchantAccount", "tbl_pathAccount_merchantAccount",
		    ConstantsCommon.ACTION_TYPE_VISIBLE, "0", merchantMgntCO.getHm(), null);

	    commonLibBO.applyDynScreenDisplay(new String[] { "acc_br", "acc_cy", "acc_cif", "acc_gl",
		    "lookuptxt_acc_sl", "lookuptxt_acc_addRef","cif", "cifNew" }, ConstantsCommon.ACTION_TYPE_VISIBLE, "0",
		    merchantMgntCO.getHm(), null);

	    commonLibBO.applyDynScreenDisplay(new String[] { "acc_br", "acc_cy", "acc_cif", "acc_gl",
		    "lookuptxt_acc_sl", "lookuptxt_acc_addRef" }, ConstantsCommon.ACTION_TYPE_MANDATORY, "0",
		    merchantMgntCO.getHm(), null);
	}
	else
	{

	    commonLibBO.applyDynScreenDisplay("pathAccount_merchantAccount", "pathAccount_merchantAccount",
		    ConstantsCommon.ACTION_TYPE_VISIBLE, "1", merchantMgntCO.getHm(), null);
	    commonLibBO.applyDynScreenDisplay("tbl_pathAccount_merchantAccount", "tbl_pathAccount_merchantAccount",
		    ConstantsCommon.ACTION_TYPE_VISIBLE, "1", merchantMgntCO.getHm(), null);

	    commonLibBO.applyDynScreenDisplay(new String[] { "acc_br", "acc_cy", "acc_cif", "acc_gl",
		    "lookuptxt_acc_sl", "lookuptxt_acc_addRef","cif", "cifNew" }, ConstantsCommon.ACTION_TYPE_VISIBLE, "1",
		    merchantMgntCO.getHm(), null);

	    commonLibBO.applyDynScreenDisplay(new String[] { "acc_br", "acc_cy", "acc_cif", "acc_gl",
		    "lookuptxt_acc_sl", "lookuptxt_acc_addRef" }, ConstantsCommon.ACTION_TYPE_MANDATORY, "1",
		    merchantMgntCO.getHm(), null);
	}
    }
	
	
	//save data
	public MerchantMgntCO saveMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException
	{
	    NumberUtil.resetEmptyValues(merchantMgntCO);
	    checkData(merchantMgntCO);
	  
	  //save new record
	    if(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getCODE())) // update existing record
	    {

		merchantMgntCO.getCtsMerchantVO().setSTATUS(MerchantMgntConstant.STATUS_ACTIVE);
		BigDecimal merchantMgntCode = commonLibBO.returnCTSCTR(merchantMgntCO.getCtsMerchantVO().getCOMP_CODE(),
		merchantMgntCO.getCtsMerchantVO().getBRANCH_CODE(), "MER", "C", merchantMgntCO.getAppName(), merchantMgntCO.getLoginUserId() , merchantMgntCO.getOpt() );  //SQL Session Tracing for R14 Java Modules 
		merchantMgntCO.getCtsMerchantVO().setCODE(merchantMgntCode);
		merchantMgntCO.getCtsMerchantVO().setCREATED_BY(merchantMgntCO.getLoginUserId());
		Date dateToSetTime = merchantMgntCO.getRunningDate();
		merchantMgntCO.getCtsMerchantVO().setDATE_CREATED(commonLibBO.addSystemTimeToDate(dateToSetTime));
		genericDAO.insert(merchantMgntCO.getCtsMerchantVO());

		// insert audit
		AuditRefCO refCO = merchantMgntCO.getAuditRefCO();
		refCO.setOperationType(AuditConstant.CREATED);
		refCO.setKeyRef(AuditConstant.MERCHANTMGNT_KEY_REF);
		auditBO.callAudit(null, merchantMgntCO.getCtsMerchantVO(), merchantMgntCO.getAuditRefCO());  
	    }
	    else //update existing record 
	    {
		merchantMgntCO.getCtsMerchantVO().setSTATUS(MerchantMgntConstant.STATUS_ACTIVE); //IN CASE UPDATE AFTER APPROVE RETURN STATUS TO ACTIVE
		merchantMgntCO.getCtsMerchantVO().setMODIFIED_BY(merchantMgntCO.getLoginUserId());
		Date dateToSetTime = merchantMgntCO.getRunningDate();
		merchantMgntCO.getCtsMerchantVO().setDATE_MODIFIED(commonLibBO.addSystemTimeToDate(dateToSetTime));
		int result = genericDAO.update(merchantMgntCO.getCtsMerchantVO());
		//double check upon saving existing record, if it was modified by another user or from other CRUD
		if(result == 0)
		    {
			throw new BOException(MessageCodes.RECORD_CHANGED);
		    }

		// Audit
		MerchantMgntCO oldMerchantMgntCO = (MerchantMgntCO) merchantMgntCO.getAuditObj();
		auditBO.callAudit(oldMerchantMgntCO.getCtsMerchantVO(), merchantMgntCO.getCtsMerchantVO(),
			merchantMgntCO.getAuditRefCO());
		auditBO.insertAudit(merchantMgntCO.getAuditRefCO());
	    }

	    return merchantMgntCO;
	}
	
	
    private void checkData(MerchantMgntCO merchantMgntCO) throws BaseException
    {
	/**
	 * mandatory fields management
	 */
	CheckRequiredCO checkRequiredCO = new CheckRequiredCO();
	checkRequiredCO.setCompCode(merchantMgntCO.getCtsMerchantVO().getCOMP_CODE());
	checkRequiredCO.setObj_value(merchantMgntCO);
	checkRequiredCO.setOpt(merchantMgntCO.getOpt());
	checkRequiredCO.setLanguage(merchantMgntCO.getLoginPreferrredLanguage());
	checkRequiredCO.setApp(merchantMgntCO.getAppName());
	commonLibBO.checkRequired(checkRequiredCO);

	  /**
	   * BURJ130313
	   * @author FatimaSalam
	   */
	
	    if((!"1".equals(merchantMgntCO.getCtsMerchantVO().getMERCHANT_TYPE()))
		    &&(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_BR())
		    || NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CIF())
		    || NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CY())
		    || NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_GL())
		    || NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_SL())))
	    {
		throw new BOException(MessageCodes.PARAM1_IS_MISSING_INVALID);
	    }
	
    }
	 
	 //delete record
    public MerchantMgntCO deleteMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException
    {
	{
	    if(!NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getCODE()))
	    {
		chequeStatus(merchantMgntCO,"DELETE");
		
		CTS_MERCHANTVO ctsMerchantVO = merchantMgntCO.getCtsMerchantVO();
		    
		if( MerchantMgntConstant.STATUS_ACTIVE.equals(merchantMgntCO.getCtsMerchantVO().getSTATUS()))

		{
		    BigDecimal merchantMgntCode = merchantMgntCO.getCtsMerchantVO().getCODE();

		    
		    ctsMerchantVO.setSTATUS(MerchantMgntConstant.STATUS_DELETED);
		    ctsMerchantVO.setDELETED_BY(merchantMgntCO.getLoginUserId());
		    Date dateToSetTime = merchantMgntCO.getRunningDate();
		    ctsMerchantVO.setDATE_DELETED(commonLibBO.addSystemTimeToDate(dateToSetTime));
		    ctsMerchantVO.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
		    ctsMerchantVO.setBRANCH_CODE(merchantMgntCO.getLoginBraCode());
		    ctsMerchantVO.setCODE(merchantMgntCode);
		    int result = genericDAO.update(ctsMerchantVO);
		    if(result <= 0)
		    {
			throw new BOException(MessageCodes.RECORD_CHANGED);
		    }

		}

		else
		{
		    throw new BOException(MessageCodes.CANNOT_DELETE);
		}
		// Audit
		MerchantMgntCO oldMerchantMgntCO = (MerchantMgntCO) merchantMgntCO.getAuditObj();
		auditBO.callAudit(oldMerchantMgntCO.getCtsMerchantVO(), ctsMerchantVO ,merchantMgntCO.getAuditRefCO());
		auditBO.insertAudit(merchantMgntCO.getAuditRefCO());

	    }

	    return merchantMgntCO;
	}
    }
    
    public MerchantMgntCO approveMerchantMgnt(MerchantMgntCO merchantMgntCO1) throws BaseException
    {
	MerchantMgntCO merchantMgntCO = merchantMgntCO1;
	chequeStatus(merchantMgntCO,"APPROVE");
	
	validateMakerChecker(merchantMgntCO);
	
	BigDecimal merchantCode = merchantMgntCO.getCtsMerchantVO().getCODE();
	CTS_MERCHANTVO ctsMerchantVO = merchantMgntCO.getCtsMerchantVO();

	ctsMerchantVO.setSTATUS(MerchantMgntConstant.STATUS_APPROVED);
	ctsMerchantVO.setAPPROVED_BY(merchantMgntCO.getLoginUserId());

	Date dateToSetTime = merchantMgntCO.getRunningDate();
	ctsMerchantVO.setDATE_APPROVED(commonLibBO.addSystemTimeToDate(dateToSetTime));

	ctsMerchantVO.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
	ctsMerchantVO.setBRANCH_CODE(merchantMgntCO.getLoginBraCode());
	ctsMerchantVO.setCODE(merchantCode);
	ctsMerchantVO.setSENT_FLAG(MerchantMgntConstant.MERCHANTMGNT_SENT_FLAG);
	
	MerchantMgntCO oldMerchantMgntCO = (MerchantMgntCO) merchantMgntCO.getAuditObj();	
	ctsMerchantVO.setSTATUS_REMARK(oldMerchantMgntCO.getCtsMerchantVO().getSTATUS_REMARK());//restore same value
	
	int result = genericDAO.update(ctsMerchantVO);
	if(result <= 0)
	{
	    throw new BOException(MessageCodes.RECORD_CHANGED);
	}
	
	//print confirmation PB~=f_print_merchant_confirmation
	//IF ib_alert_mode = False then f_print_merchant_confirmation
	if(!Boolean.valueOf(merchantMgntCO.getIsFromAlert()))
	{
	    merchantMgntCO = printMerchantMgntConfirmation(merchantMgntCO, MerchantMgntConstant.APPROVE_PRINT);
	}

	/*
	 * merchant_response\\ue_check_alert.f_update_todo ; f_alert_acknowledgment
	 * Send an acknowledge alert in case of remote approve through
	 * openItem screen. In local approve the acknowledgment will not be sent.
	 */
	if(merchantMgntCO.getAlertsParamCO() != null  && Boolean.valueOf(merchantMgntCO.getIsFromAlert()))
	{
	    alertsBO.updateTodoAndSendAck(merchantMgntCO.getAlertsParamCO());
	}
	
	// Audit
	auditBO.callAudit(oldMerchantMgntCO.getCtsMerchantVO(), ctsMerchantVO,merchantMgntCO.getAuditRefCO());
	auditBO.insertAudit(merchantMgntCO.getAuditRefCO());

	 return merchantMgntCO;
    }
    
    public MerchantMgntCO toCancelMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException
    {
    
	/*
	 * If retrieved record then set Audit Values
	 */
	if(NumberUtil.emptyDecimalToNull(merchantMgntCO.getCtsMerchantVO().getCODE()) != null)
	{

	  

	    CTS_MERCHANTVO ctsMerchantVO = merchantMgntCO.getCtsMerchantVO();

	    ctsMerchantVO.setSTATUS(MerchantMgntConstant.STATUS_TOCANCEL);
	    ctsMerchantVO.setTOBE_CANCELED_BY(merchantMgntCO.getLoginUserId());
	   
	    /**
	     * validate active POS wf_check_active_pos
	     * @author FatimaSalam
	     */
	    if(!merchantMgntCO.isMessageConfirmed())
	    {
		int activeResult = merchantMgntDAO.returnActiveResult(merchantMgntCO);
		if(activeResult > 0)
		{
		    throw new BOException(MessageCodes.POS_WILL_BE_CANCELED_DOYOU_WANT_TO_CONT,
			    ConstantsCommon.CONFIRM_MSG_TYPE);
		}
	    }

	    BigDecimal merchantCode = merchantMgntCO.getCtsMerchantVO().getCODE();
	    Date dateToSetTime = merchantMgntCO.getRunningDate();
	    ctsMerchantVO.setDATE_TOBE_CANCELED(commonLibBO.addSystemTimeToDate(dateToSetTime));

	    ctsMerchantVO.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
	    ctsMerchantVO.setBRANCH_CODE(merchantMgntCO.getLoginBraCode());
	    ctsMerchantVO.setCODE(merchantCode);

	    // alert to be sent if User is not a branch manager

	    int result = genericDAO.update(ctsMerchantVO);
	    if(result <= 0)
	    {
		throw new BOException(MessageCodes.RECORD_CHANGED);
	    }

	    // Set the new status to be used later in alert -
	    // returnalertparams()
	    merchantMgntCO.getCtsMerchantVO().setSTATUS(ctsMerchantVO.getSTATUS());

	    // Audit
	    MerchantMgntCO oldMerchantMgntCO = (MerchantMgntCO) merchantMgntCO.getAuditObj();
	    auditBO.callAudit(oldMerchantMgntCO.getCtsMerchantVO(), ctsMerchantVO, merchantMgntCO.getAuditRefCO());
	    auditBO.insertAudit(merchantMgntCO.getAuditRefCO());
	}
	return merchantMgntCO;
    }
    
    
    public MerchantMgntCO cancelMerchantMgnt(MerchantMgntCO merchantMgntCO) throws BaseException
    {
	if( NumberUtil.emptyDecimalToNull(merchantMgntCO.getCtsMerchantVO().getCODE()) != null)
	{
	    BigDecimal merchantCode = merchantMgntCO.getCtsMerchantVO().getCODE();
	    
	    CTS_MERCHANTVO ctsMerchantVO = merchantMgntCO.getCtsMerchantVO();

	    ctsMerchantVO.setSTATUS(MerchantMgntConstant.STATUS_CANCELLED);

	    ctsMerchantVO.setCANCELED_BY(merchantMgntCO.getLoginUserId());

	    Date dateToSetTime = merchantMgntCO.getRunningDate();
	    ctsMerchantVO.setDATE_CANCELED(commonLibBO.addSystemTimeToDate(dateToSetTime));

	    ctsMerchantVO.setSENT_FLAG(MerchantMgntConstant.MERCHANTMGNT_SENT_FLAG);

	    ctsMerchantVO.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
	    ctsMerchantVO.setBRANCH_CODE(merchantMgntCO.getLoginBraCode());
	    ctsMerchantVO.setCODE(merchantCode);

	    int result = genericDAO.update(ctsMerchantVO);
	    if(result <= 0)
	    {
		throw new BOException(MessageCodes.RECORD_CHANGED);
	    }

	    /**
	     * update POS wf_update_pos('N')
	     * @author FatimaSalam
	     */
	    merchantMgntCO.setRunningDate(commonLibBO.addSystemTimeToDate(dateToSetTime));
	    merchantMgntDAO.updateCtsMerchantPos(merchantMgntCO);
	    
	    
	    /*
	     * merchant_response\\ue_check_alert.f_update_todo ;
	     * f_alert_acknowledgment
	     */
	     if(merchantMgntCO.getAlertsParamCO() != null &&
	     Boolean.valueOf(merchantMgntCO.getIsFromAlert()))
	     {
	     alertsBO.updateTodoAndSendAck(merchantMgntCO.getAlertsParamCO());
	     }

	    // Audit
	     MerchantMgntCO oldMerchantMgntCO = (MerchantMgntCO) merchantMgntCO.getAuditObj();
	    auditBO.callAudit(oldMerchantMgntCO.getCtsMerchantVO(), ctsMerchantVO,merchantMgntCO.getAuditRefCO());
	    auditBO.insertAudit(merchantMgntCO.getAuditRefCO());

	}
	return merchantMgntCO;
    }
	
    /**
     * @author jihanemazloum
     * This function will return the alerts param co
     * @param merchantMgntCO
     * @return
     */
    public AlertsParamCO returnAlertsParamCO(MerchantMgntCO merchantMgntCO,boolean isNewAction, String pageRef) throws BaseException
    {
	AlertsParamCO alertsParamCO = new AlertsParamCO();
	String status = merchantMgntCO.getCtsMerchantVO().getSTATUS();
	if(isNewAction && status == null )
	{
	    status = MerchantMgntConstant.STATUS_ACTIVE;
	}
	
	AlertsParamCO returedAlertsParamCO = checkSendAlert(merchantMgntCO, alertsParamCO, isNewAction, status , pageRef);
	if(returedAlertsParamCO.getAllowToSend() != null && BigDecimal.ZERO.equals(returedAlertsParamCO.getAllowToSend()))
	{
	    //The alert should not be sent, so we should return null.
	    return null;
	}
	
	//Set the next progRef based on the record status
	//alertsParamCO.setProgRef(returedAlertsParamCO.getProgRef());
	
	//SAVE AS FUNCTIONALITY
	/**
	 * commented by EAoun because it overrides alertsParamCO.ProgRef that is filled in above checkSendAlert()
	 */
//	alertsParamCO.setProgRef(commonLibBO.returnOrginProgRef(merchantMgntCO.getAppName(), returedAlertsParamCO.getProgRef()));

	alertsParamCO.setCompCode(merchantMgntCO.getCtsMerchantVO().getCOMP_CODE());
	alertsParamCO.setBranchCode(merchantMgntCO.getCtsMerchantVO().getBRANCH_CODE());
	alertsParamCO.setStatus(merchantMgntCO.getStatusDesc());
	alertsParamCO.setAmount(BigDecimal.ZERO);

	/*
	 * information collected upon sending alert PowerBuilder Equivalent: w_maintain_merchant.wf_get_merchant_info
	 */
	
	String alertDescriptionPattern = AlertsConstants.MERCHANTMGNT_ALERT_DESCRIPTION_PATTERN;
	
	String cifCodeName = "";
	String accNoName = "";
	if(!NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CIF()))
	{
	    /*
	     * information collected upon sending alert PowerBuilder Equivalent:
	     * w_send_alert.wf_get_merchant_info get merchant code - CIF Short
	     * name - Account - Account Brief name get short name description of
	     * CIF to be used for alert
	     */
	    CifSC cifSC = new CifSC();
	    cifSC.setComp_code(merchantMgntCO.getLoginCompCode());
	    cifSC.setCif_no(merchantMgntCO.getCtsMerchantVO().getACC_CIF());
	    cifSC.setLang(merchantMgntCO.getLoginPreferrredLanguage());
	    CIFCO cIFCO = cifBO.returnCIFinfo(cifSC);
	    if(cIFCO != null)
	    {
		cifCodeName = merchantMgntCO.getCtsMerchantVO().getACC_CIF() + "  " + cIFCO.getSHORT_NAME();
	    }
	
	
	    /*
	     * 
	     * get brief name description of AMF to be used for alert
	     */
	    AccountSC accountSC = new AccountSC();
	    accountSC.setCompCode(merchantMgntCO.getLoginCompCode());
	    accountSC.setBranchCode(merchantMgntCO.getCtsMerchantVO().getACC_BR());
	    accountSC.setCurrencyCode(merchantMgntCO.getCtsMerchantVO().getACC_CY());
	    accountSC.setGlCode(merchantMgntCO.getCtsMerchantVO().getACC_GL());
	    accountSC.setCifCode(merchantMgntCO.getCtsMerchantVO().getACC_CIF());
	    accountSC.setSlNbr(merchantMgntCO.getCtsMerchantVO().getACC_SL());
	    accountSC.setUserId(merchantMgntCO.getLoginUserId());
	    accountSC.setLangCode(merchantMgntCO.getLoginPreferrredLanguage());
	    AccountCO accountCO = accountBO.returnAMFDetails(accountSC);
	    accNoName   = merchantMgntCO.getCtsMerchantVO().getACC_BR() + " - " + merchantMgntCO.getCtsMerchantVO().getACC_CY()+ " - " + merchantMgntCO.getCtsMerchantVO().getACC_GL() + " - " + merchantMgntCO.getCtsMerchantVO().getACC_CIF() + " - " + merchantMgntCO.getCtsMerchantVO().getACC_SL() + " - " + ( accountCO == null ? "" : accountCO.getBRIEF_NAME());
	}
	
	String alertDescription = MessageFormat.format(alertDescriptionPattern, merchantMgntCO.getCtsMerchantVO().getCODE(), cifCodeName, accNoName );
	
	alertsParamCO.setAlertDescription(alertDescription);
	//alertsParamCO.setAlertLabelKey(getText(AlertsConstants.MERCHANTMGNT_TODO_ALERT + AlertsConstants.ALERT_SEND_TITLE_SUFFIX));
	
	/*
	 * information sent upon clicking send alert PowerBuilder Equivalent:w_send_alert.cb_send.clicked (Line 383)
	 */
	alertsParamCO.setReasonCode(AlertsConstants.MERCHANTMGNT_ALERT_REASON_CODE);
	alertsParamCO.setAlertType(AlertsConstants.MERCHANTMGNT_TODO_ALERT);
	alertsParamCO.setTrsNo(merchantMgntCO.getCtsMerchantVO().getCODE());
	alertsParamCO.setTodoAlert(AlertsConstants.MERCHANTMGNT_TODO_ALERT);
	alertsParamCO.setTodoParam(merchantMgntCO.getCtsMerchantVO().getCODE().toString());
	alertsParamCO.setTodoAlertOldStatus(merchantMgntCO.getCtsMerchantVO().getSTATUS());
	alertsParamCO.setActionType(AlertsConstants.ACTION_TYPE_X);
	alertsParamCO.setDistributionType(AlertsConstants.DISTRIBUTION_TYPE_B);
	alertsParamCO.setDistributionTo(AlertsConstants.DISTRIBUTION_TO_U);
	//alertsParamCO.setBriefNameEnglish(getText(AlertsConstants.MERCHANTMGNT_APPROVE_DESC) ); 
	alertsParamCO.setTodoType(AlertsConstants.TODO_TYPE_P);
	alertsParamCO.setTodoPriority(AlertsConstants.TODO_PRIORITY_A);
	alertsParamCO.setTodoChecked(AlertsConstants.TODO_CHECKED_U);
	alertsParamCO.setTodoExecution(AlertsConstants.TODO_EXECUTION_N);
	alertsParamCO.setAllowToSend(BigDecimal.ONE);

	alertsParamCO.setTodoExternal("0");
	alertsParamCO.setTrsNo(BigDecimal.ZERO);
	alertsParamCO.setTodoTellerBranch(BigDecimal.ZERO);
	alertsParamCO.setTodoTellerId(BigDecimal.ZERO);
	alertsParamCO.setCIF_NO(BigDecimal.ZERO);
	
	NumberUtil.resetEmptyValues(alertsParamCO);
	return alertsParamCO;
    }
    
    
    public MerchantMgntCO alertReject(MerchantMgntCO merchantMgntCO) throws BaseException
    {

	BigDecimal merchantCode = merchantMgntCO.getCtsMerchantVO().getCODE();
	if(!NumberUtil.isEmptyDecimal(merchantCode))
	{
	    CTS_MERCHANTVO ctsMerchantVO = new CTS_MERCHANTVO();
	    ctsMerchantVO.setSTATUS_REMARK(merchantMgntCO.getCtsMerchantVO().getSTATUS_REMARK());

	    if(MerchantMgntConstant.STATUS_TOCANCEL.equals(merchantMgntCO.getCtsMerchantVO().getSTATUS()))
	    {
		ctsMerchantVO.setSTATUS(MerchantMgntConstant.STATUS_APPROVED);
	    }
	    ctsMerchantVO.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
	    ctsMerchantVO.setBRANCH_CODE(merchantMgntCO.getLoginBraCode());
	    ctsMerchantVO.setCODE(merchantCode);
	    int result = genericDAO.update(ctsMerchantVO);
	    if(result <= 0)
	    {
		throw new BOException(MessageCodes.RECORD_CHANGED);
	    }
	}

	/*
	 * merchant_response\\ue_check_alert.f_update_todo ;
	 *  Send an acknowledge alert in case of remote approve through
	 *  openItem screen. In local approve the acknowledgment will not be senT
	 * f_alert_acknowledgment
	 */
	if(merchantMgntCO.getAlertsParamCO() != null && Boolean.valueOf(merchantMgntCO.getIsFromAlert()))
	{
	    alertsBO.updateTodoAndSendAck(merchantMgntCO.getAlertsParamCO());
	}

	return merchantMgntCO;
    }
    
    
    
    /**
     * This function will check if we should send the alert
     * PB=wf_send_alert
     * If AllowToSend = 0 then we should not send the alert.
     * If AllowToSend = 1 then we should send the alert.
     * @author jihanemazloum
     * @param alertParams
     * @param newAction
     * @return
     */
    private AlertsParamCO checkSendAlert(MerchantMgntCO merchantMgntCOParam, AlertsParamCO alertParams , Boolean isNewAction , String status, String pageRef) throws BaseException
    {
	
	//AvoidReassigningParameters
	MerchantMgntCO merchantMgntCO = merchantMgntCOParam;
	//TODO OR ib_alert_mode = TRUE 
	if("1".equals(merchantMgntCO.getUserIsBranchManager()))
	{
	    alertParams.setAllowToSend(BigDecimal.ZERO);
	    return alertParams;
	}
	
	//#206033 In case of SAVE AS opt, we should use the original pageRef, to continue the flow. at the end we will change the next progRef base on series SAVE AS
	AlertsParamCO saveAsCO = alertsBO.returnSaveAsOptDetails(merchantMgntCO.getAppName(), pageRef);
	if(saveAsCO != null && saveAsCO.isFromSaveAs())
	{
	    pageRef = saveAsCO.getOriginalProgRef();
	}
	
	AlertsSC alertSC = new AlertsSC();
	alertSC.setCompCode(merchantMgntCO.getLoginCompCode());
	alertSC.setBranchCode(merchantMgntCO.getLoginBraCode());
	alertSC.setAppName(merchantMgntCO.getAppName());
	alertSC.setAlertType(AlertsConstants.MERCHANTMGNT_ALERT_REASON_CODE);
	//alertSC.setProgRef(pageRef);
	
	//SAVE AS FUNCTIONALITY
	alertSC.setProgRef(commonLibBO.returnOrginProgRef(merchantMgntCO.getAppName(), pageRef));
	
	alertSC.setStatus(status);
	alertSC.setUserId(merchantMgntCO.getLoginUserId());
	
	//JM:w_maintain_merchant.ue_alert
	//condition  active to cancel update after approve ; not branch mngr ; f_fill_check_alert_flag ~= ctsControlAlert
	/*
	 * 
	 * 
	 */
	if( (!isNewAction && (MerchantMgntConstant.STATUS_ACTIVE.equals(status)) || MerchantMgntConstant.STATUS_TOCANCEL.equals(status)))
	{
	    alertSC.setAlertAddCode(AlertsConstants.MERCHANTMGNT_ALERT_ADD_CODE_RI);
	    CTSCONTROL_ALERTVO ctsControlAlert = alertsBO.returnCtsControlAlert(alertSC);   //f_fill_check_alert_flag
	    
	    if(ctsControlAlert != null && "1".equals(ctsControlAlert.getACTIVATE()))
	    {
		
		if(MerchantMgntConstant.STATUS_ACTIVE.equals(status))
		    {
		    	alertParams.setProgRef(AlertsConstants.MERCHANTMGNT_PAGE_P);
		    }
		else if(MerchantMgntConstant.STATUS_TOCANCEL.equals(status))
		    {
		    	alertParams.setProgRef(AlertsConstants.MERCHANTMGNT_PAGE_N);
		    }
		alertParams.setAllowToSend(BigDecimal.ONE);
	    }
	    
	    else
	    {
		alertParams.setAllowToSend(BigDecimal.ZERO);
	    }
	}
	//#206033 In case of SAVE AS opt from series, we should replace the original next progRef by the SAVE AS next progRef
	//In case of SAVE AS opt without series, we should keep the original next progRef
	if(StringUtil.isNotEmpty(alertParams.getProgRef()) && saveAsCO != null && saveAsCO.isFromSeriesSaveAs())
	{
	    CommonLibSC commonLibSc  = new CommonLibSC();
	    commonLibSc.setAppName(merchantMgntCO.getAppName());
	    commonLibSc.setOptReference(alertParams.getProgRef());
	    commonLibSc.setProgRef(saveAsCO.getSeriesProgRef());
	    alertParams.setProgRef(commonLibBO.returnSeriesOptByRef(commonLibSc));
	}
	
	return alertParams;
    }
    
    /**
     * PB = w_show_alerts.wf_get_merchant_info
     * @param merchantMgntCO
     * @return
     * @throws BaseException
     */
    public MerchantMgntCO loadAlertMerchantDetails(MerchantMgntCO merchantMgntCO) throws BaseException
    {

	CTS_MERCHANTVO merchantVO = new CTS_MERCHANTVO();
	merchantVO.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
	merchantVO.setBRANCH_CODE(merchantMgntCO.getLoginBraCode());
	merchantVO.setCODE(new BigDecimal(merchantMgntCO.getAlertsParamCO().getTodoParam()));
	merchantVO = (CTS_MERCHANTVO) genericDAO.selectByPK(merchantVO);
	if(merchantVO != null)
	{
	    BigDecimal cifNo = merchantVO.getACC_CIF();

	    CifSC cifSC = new CifSC();
	    cifSC.setComp_code(merchantMgntCO.getLoginCompCode());
	    cifSC.setCif_no(cifNo);
	    cifSC.setLang(merchantMgntCO.getLoginPreferrredLanguage());
	    CIFCO cIFCO = cifBO.returnCIFinfo(cifSC);
	    String cifCodeName = "";
	    if(cIFCO != null)
	    {
		cifCodeName = merchantVO.getACC_CIF() + "  " + cIFCO.getSHORT_NAME();
		if(AlertsConstants.MERCHANTMGNT_ACK_APPROVE.equals(merchantMgntCO.getAlertsParamCO().getTodoAlert()))
		{
		    merchantMgntCO.getAlertsParamCO().setAlertDescription(AlertsConstants.MERCHANTMGNT_ALERT_DESCRIPTION_APPROVE_PATTERN + " " + cifCodeName);
		}
		else if(AlertsConstants.MERCHANTMGNT_ACK_CANCEL.equals(merchantMgntCO.getAlertsParamCO().getTodoAlert()))
		{
		    merchantMgntCO.getAlertsParamCO().setAlertDescription(AlertsConstants.MERCHANTMGNT_ALERT_DESCRIPTION_CANCEL_PATTERN + " " + cifCodeName);
		}
	    }

	}
	return merchantMgntCO;
    }
    
    
    public void validateMakerChecker(MerchantMgntCO merchantMgntCO) throws BaseException
    {
	int errorCode = commonLibBO.validateMakerChecker(merchantMgntCO.getCtsMerchantVO().getCREATED_BY(),
		merchantMgntCO.getCtsMerchantVO().getMODIFIED_BY(), merchantMgntCO.getLoginUserId());

	if(errorCode > 0)
	{
	    throw new BOException(errorCode);
	}
    }
//TODO(!) WARNINING Duplicate code of ({@see CertificateBOImpl.java#chequeStatus)
    private void chequeStatus(MerchantMgntCO merchantMgntCO, String option) throws BaseException
    {
	if("DELETE".equals(option)
		&& !merchantMgntCO.getCtsMerchantVO().getSTATUS().equals(MerchantMgntConstant.STATUS_ACTIVE))
	{
	    throw new BOException(MessageCodes.CANNOT_DELETE);
	}

	if("APPROVE".equals(option)
		&& !MerchantMgntConstant.STATUS_ACTIVE.equals(merchantMgntCO.getCtsMerchantVO().getSTATUS()))
	{
	    throw new BOException(MessageCodes.CANNOT_APPROVE);
	}
    }
		  
		  
     /********************************************** print confirmation***************************************/
    /**
     * PB=f_print_merchant_confirmation
     * @author jihanemazloum
     */
    
    public MerchantMgntCO printMerchantMgntConfirmation (MerchantMgntCO merchantMgntCO, String flag) throws BaseException
    {
      BigDecimal engRepId = null;
      BigDecimal arabRepId = null;
      BigDecimal engArgId = null;
      BigDecimal arabArgId = null;
 
      CTSCONTROLVO ctsControlVO = returnCtsControlDetails(merchantMgntCO);
      if(ctsControlVO != null)
      {
          engRepId = ctsControlVO.getMER_APP_REP_ID_ENG();
          arabRepId = ctsControlVO.getMER_APP_REP_ID_ARAB();
          engArgId = ctsControlVO.getMER_APP_ARG_ID_ENG();
          arabArgId = ctsControlVO.getMER_APP_ARG_ID_ARAB();
      }
 
      ReportResponseCO reportResponseCO = new ReportResponseCO();
      reportResponseCO.setReportRef(null);
  	  //TP #372259 automatically invoking ctrl+p to print CSM advices --[Elias Aoun]  
  	  reportResponseCO.setAutoPrint(true);
      reportResponseCO.setSelectReportLanguage(true);
 
      Integer arabValidationError = validateReportAndArgumentId(arabRepId, arabArgId);
      Integer engValidationError = validateReportAndArgumentId(engRepId, engArgId);
      if(arabValidationError == null && engValidationError == null)
      {
          reportResponseCO.setEngReportId(engRepId);
          reportResponseCO.setArabReportId(arabRepId);
          CtsReportArgSC engCtsReportArgSC = returnCtsReportArgSC(merchantMgntCO, engArgId);
          CtsReportArgSC arabCtsReportArgSC = returnCtsReportArgSC(merchantMgntCO, arabArgId);
          Map<BigDecimal, CtsReportArgSC> argumentMap = new HashMap<BigDecimal, CtsReportArgSC>();
          argumentMap.put(engArgId, engCtsReportArgSC);
          argumentMap.put(arabArgId, arabCtsReportArgSC);
          argumentMap = commonLibBO.createMultiDynamicReportParams(argumentMap, merchantMgntCO
                .getLoginCompCode(), null);
          if(argumentMap == null)
          {
              reportResponseCO.setReportRef(null);
              reportResponseCO.setReportId(null);
              reportResponseCO.setEngReportId(null);
              reportResponseCO.setArabReportId(null);
          }
          else
          {
              reportResponseCO.setEngReportParams(argumentMap.get(engArgId).getParameters());
              reportResponseCO.setArabReportParams(argumentMap.get(arabArgId).getParameters());
              reportResponseCO.setSelectReportLanguage(true);
              
          }
      }
      else
      {
          return merchantMgntCO;
      }
 
      merchantMgntCO.setReportResponseCO(reportResponseCO);
 
      return merchantMgntCO;
    }
   
    
    
    public CTSCONTROLVO returnCtsControlDetails(MerchantMgntCO merchantMgntCO) throws BaseException
    {
	CTSCONTROLVO ctsControlVO = new CTSCONTROLVO();
	ctsControlVO.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
	ctsControlVO.setBRANCH_CODE(merchantMgntCO.getLoginBraCode());
	ctsControlVO = commonLibBO.returnCtsControlDetails(ctsControlVO);
	return ctsControlVO;
    }
   
   /**
    * Return null if validation is ok, otherwise 0 or error code
    * @param reportId
    * @param argumentId
    * @return
    * @throws BaseException
    */
   private Integer validateReportAndArgumentId(BigDecimal reportId, BigDecimal argumentId) throws BaseException
   {
	if(!NumberUtil.isEmptyDecimal(reportId) && reportId.compareTo(BigDecimal.ZERO) > 0 && !NumberUtil.isEmptyDecimal(argumentId) && argumentId.compareTo(BigDecimal.ZERO) > 0 )
	{
	    // Validate the report id
	    return commonLibBO.validateConfirmationReport(reportId);
	}
	else
	{    
	    return 0;
	}
	    
   }
    
   
   private CtsReportArgSC returnCtsReportArgSC(MerchantMgntCO merchantMgntCO, BigDecimal argumentId) throws BaseException
   {
	CtsReportArgSC ctsReportArgSC = new CtsReportArgSC();
	ctsReportArgSC.setArgCode(argumentId);
	ctsReportArgSC.setUserId(merchantMgntCO.getLoginUserId());
	ctsReportArgSC.setRunningDate(merchantMgntCO.getRunningDate());
	ctsReportArgSC.setCompCode(merchantMgntCO.getLoginCompCode());
	ctsReportArgSC.setCurrency(BigDecimal.ZERO);
	ctsReportArgSC.setGenLedger(BigDecimal.ZERO);
	ctsReportArgSC.setCifNo(BigDecimal.ZERO);
	ctsReportArgSC.setSerialNo(BigDecimal.ZERO);
	ctsReportArgSC.setTrxNo(merchantMgntCO.getCtsMerchantVO().getCODE());
	ctsReportArgSC.setTrxTypeCode(BigDecimal.ZERO);
	ctsReportArgSC.setBranchCode(merchantMgntCO.getLoginBraCode());
	return ctsReportArgSC;
   }

   
   /************************************************ end print confirmation *****************************************/ 
   
    /******************************************** Account dependency by CIF *****************************************/
    	
   /**
    * dependencyByAccCIF
    * @param merchantMgntCO
    * @return MerchantMgntCO
    * @throws BaseException
    */
   public MerchantMgntCO dependencyByAccCIF(MerchantMgntCO merchantMgntCO) throws BaseException
   {
	if(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CIF()))
	{
	    return merchantMgntCO;
	}

	GLSC glSC = new GLSC();
	glSC.setCompCode(merchantMgntCO.getLoginCompCode());
	glSC.setGlCode(merchantMgntCO.getCtsMerchantVO().getACC_GL());
	glSC.setBranchCode(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_BR()) ? merchantMgntCO.getLoginBraCode() : merchantMgntCO
		.getCtsMerchantVO().getACC_BR());
	glSC.setCurrencyCode(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CY()) ? merchantMgntCO.getBaseCurrencyCode() : merchantMgntCO
		.getCtsMerchantVO().getACC_CY()) ;
	
	Boolean genledgerVO = glBO.checkGLValidation(glSC);//only validate if GL exists
	if(!genledgerVO)
	{
	    return merchantMgntCO;
	}
	//Bug # 339474 -- [John Massaad] -- Adding condition to check if the gl is internal to do not check on CIF_NO
	GEN_LEDGERVO genledgerVO1 = new GEN_LEDGERVO();
	genledgerVO1.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
	genledgerVO1.setGL_CODE(merchantMgntCO.getCtsMerchantVO().getACC_GL());

	genledgerVO1 = commonLibBO.returnGenralLegder(genledgerVO1);
	//Bug # 339474 -- [John Massaad]	
	if(genledgerVO1!=null && !genledgerVO1.getGL_TYPE().equals("I"))
	{
       	CifSC criteria = new CifSC();
       	criteria.setCompCode(merchantMgntCO.getLoginCompCode());
       	criteria.setCif_no(merchantMgntCO.getCtsMerchantVO().getACC_CIF());
       	
       	CIFVO cif = cifBO.returnCifByNo(criteria);
       	if(null == cif)
       	{
       	    merchantMgntCO.getCtsMerchantVO().setACC_CIF(null);
       	    
       	}
       	else
       	{
       	    if(NumberUtil.nullToZero(cif.getBLACK_LISTED()).intValue() == 1)
       	    {
       		throw new BOException(NCifMaintenanceConstant.CIF_IS_BLACKLISTED, new String[] { cif.getCIF_NO()
       			.toString() });
       	    } 
       	}
	}
	return merchantMgntCO;
   }

   /******************************************** End Account dependency by CIF *********************************************************/
    
    
    
    
    
    
    
    /******************************************** Account dependency by ACC_SL *****************************************/
    /**
     * dependencyByAccSL
     * @param merchantMgntCO
     * @return MerchantMgntCO
     * @throws BaseException
     */
   public MerchantMgntCO dependencyByAccSL(MerchantMgntCO merchantMgntCO) throws BaseException
   {
	if(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_BR())
		|| NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CY())
		|| NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_GL())
		|| NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CIF())
		|| NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_SL()))
	{
	    if(!NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_SL()))
	    {
		if(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_BR()))
		{
		    throw new BOException(MessageCodes.INVALID_MISSING_BRANCH_CODE);
		}
		if(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CY()))
		{
		    throw new BOException(MessageCodes.INVALID_MISSING_CURRENCY);
		}
		if(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_GL()))
		{
		    throw new BOException(MessageCodes.INVALID_MISSING_GL);
		}
		if(NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CIF()))
		{
		    throw new BOException(MessageCodes.INVALID_MISSING_CIF_NO);
		}
	    } 
	}
	else
	{
	    //Bug # 339474 -- [John Massaad]-- rechecking on Currency Code on SL Dependency
	    CURRENCIESVO currenciesVO = new CURRENCIESVO();
	    currenciesVO.setCOMP_CODE(merchantMgntCO.getLoginCompCode());
	    currenciesVO.setCURRENCY_CODE(merchantMgntCO.getCtsMerchantVO().getACC_CY());
	    currenciesVO = commonLibBO.returnCurrency(currenciesVO);
	    if(currenciesVO == null)
	    {
		    throw new BOException(MessageCodes.INVALID_MISSING_CURRENCY);
	    }
	    //Bug # 339474 -- [John Massaad]
	    dependencyByAccCIF(merchantMgntCO);  // 
	    AccountSC accountSC = new AccountSC();
	    accountSC.setCompCode(merchantMgntCO.getCtsMerchantVO().getCOMP_CODE());
	    accountSC.setUserId(merchantMgntCO.getUserName());
	    accountSC.setLangCode(merchantMgntCO.getLanguage());
	    accountSC.setLovType(ConstantsCommon.STATUS_LOV);
	    accountSC.setPreferredLanguage(merchantMgntCO.getLoginPreferrredLanguage());
	    accountSC.setFlag("AMF");
	    accountSC.setBranchCode(merchantMgntCO.getCtsMerchantVO().getACC_BR());
	    accountSC.setCurrencyCode(merchantMgntCO.getCtsMerchantVO().getACC_CY());
	    accountSC.setGlCode(merchantMgntCO.getCtsMerchantVO().getACC_GL());
	    accountSC.setCifCode(merchantMgntCO.getCtsMerchantVO().getACC_CIF());
	    accountSC.setSlNbr(merchantMgntCO.getCtsMerchantVO().getACC_SL());
	    accountSC.setPreferredLanguage(merchantMgntCO.getLoginPreferrredLanguage());
	    accountSC.setBS_CONTRA("X");
	    //JIHANE TP225849 TP225887 pop up message on account validation.
	    NumberUtil.resetEmptyValues(accountSC);
	    List<AMFVO> amfList = accountBO.checkAccountInAMF(accountSC);
	    if(amfList.isEmpty())
	    {
		String account = constructAccStrForException(accountSC);
		throw new BOException(MessageCodes.ACCOUNT_NO_IS_INVALID_OR_MISSING, new String[] { account });
	    }
		
	    AMFVO amfVO = accountBO.returnAMFBySLNo(accountSC);
	    merchantMgntCO.setAmfVO(amfVO);
	    if(null != amfVO)
	    {
		merchantMgntCO.getCtsMerchantVO().setACC_ADDITIONAL_REF(amfVO.getADDITIONAL_REFERENCE());
	    }
	    
	    
	}
	return merchantMgntCO;
   }
    
    /******************************************** End Account dependency by ACC_SL *****************************************/
    
    
    /**
     *  //JIHANE TP225849 TP225887 pop up message on account validation.
     * account string is constructed and used from multiple function to be used
     * in BOException, the function below construct a string for account passed
     * by parameter
     * 
     * @param accountSC
     * @return
     */
    public String constructAccStrForException(AccountSC accountSC)
    {
	StringBuffer account = new StringBuffer();
        account.append( "/*" + "0000".substring(accountSC.getCompCode().toString().length())+ accountSC.getCompCode().toString());
        account.append('-');
        if(accountSC.getBranchCode() != null)
        {
            account.append( "0000".substring(accountSC.getBranchCode().toString().length())+ accountSC.getBranchCode().toString() );
        }
        account.append('-');
        if(accountSC.getCurrencyCode() != null)
        {
            account.append( "000".substring(accountSC.getCurrencyCode().toString().length())+ accountSC.getCurrencyCode().toString() );
        }
        account.append('-');
        if(accountSC.getGlCode() != null)
        {
            account.append( "000000".substring(accountSC.getGlCode().toString().length())+ accountSC.getGlCode().toString() );
        }
        account.append('-');
        if(accountSC.getCifCode() != null) 
        {
            account.append( "00000000".substring(accountSC.getCifCode().toString().length())+ accountSC.getCifCode().toString() );
        }
        account.append('-');
        if(accountSC.getSlNbr() != null) 
        {
            account.append( "000".substring(accountSC.getSlNbr().toString().length())+ accountSC.getSlNbr().toString() );
        }
        account.append( "*/" );
	return account.toString();
    }
    
    
    /******************************************** Account dependency by ACC_ADD_REF *****************************************/
    
    /**
     * dependencyByAddRef
     */
    public MerchantMgntCO dependencyByAddRef(MerchantMgntCO merchantMgntCO) throws BaseException
    {
	if(!"".equals(merchantMgntCO.getCtsMerchantVO().getACC_ADDITIONAL_REF()))
	{
		
	    AccountSC accountSC = new AccountSC();
	    accountSC.setCompCode(merchantMgntCO.getCtsMerchantVO().getCOMP_CODE());
	    accountSC.setPreferredLanguage(merchantMgntCO.getLoginPreferrredLanguage());
	    accountSC.setLangCode(merchantMgntCO.getLanguage());
	    accountSC.setLovType(ConstantsCommon.STATUS_LOV);

	    accountSC.setBS_CONTRA("X");
	    accountSC.setFlag("AMF");

	    accountSC.setAddRef(merchantMgntCO.getCtsMerchantVO().getACC_ADDITIONAL_REF());
	    if(!NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_BR()))
	    {
		accountSC.setBranchCode(merchantMgntCO.getCtsMerchantVO().getACC_BR());
	    }
	    if(!NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CY()))
	    {
		accountSC.setCurrencyCode(merchantMgntCO.getCtsMerchantVO().getACC_CY());
	    }
	    if(!NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_GL()))
	    {
		accountSC.setGlCode(merchantMgntCO.getCtsMerchantVO().getACC_GL());
	    }
	    if(!NumberUtil.isEmptyDecimal(merchantMgntCO.getCtsMerchantVO().getACC_CIF()))
	    {
		accountSC.setCifCode(merchantMgntCO.getCtsMerchantVO().getACC_CIF());
	    }
	    AMFVO amfVO = accountBO.returnAccountByReference(accountSC);
	    merchantMgntCO.setAmfVO(amfVO);
        	
	    if(amfVO == null)
	    {
		merchantMgntCO.getCtsMerchantVO().setACC_ADDITIONAL_REF(null);
		return merchantMgntCO;
	    }

	    AccountCO accountCO = accountBO.returnAMFDetails(accountSC);
	    merchantMgntCO.setAccountCO(accountCO);
	    merchantMgntCO.getCtsMerchantVO().setACC_BR(amfVO.getBRANCH_CODE());
	    merchantMgntCO.getCtsMerchantVO().setACC_CY(amfVO.getCURRENCY_CODE());
	    merchantMgntCO.getCtsMerchantVO().setACC_GL(amfVO.getGL_CODE());
	    merchantMgntCO.getCtsMerchantVO().setACC_CIF(amfVO.getCIF_SUB_NO());
	    merchantMgntCO.getCtsMerchantVO().setACC_SL(amfVO.getSL_NO());
	    merchantMgntCO.getCtsMerchantVO().setACC_ADDITIONAL_REF(amfVO.getADDITIONAL_REFERENCE());
	    
	    dependencyByAccCIF(merchantMgntCO);
	}
	
	return merchantMgntCO;
    }
    
    public MerchantMgntCO dependencyOnMerchantType(MerchantMgntCO merchantMgntCO) throws BaseException
    {
	applyMerchantTypeBusinessElement(merchantMgntCO);
	
	return merchantMgntCO;
    }
    
    /******************************************** End Account dependency by ACC_ADD_REF *****************************************/
    
    
	public CifBO getCifBO()
	{
	    return cifBO;
	}

	public void setCifBO(CifBO cifBO)
	{
	    this.cifBO = cifBO;
	}

	public LoginBO getLoginBO()
	{
	    return loginBO;
	}

	public void setLoginBO(LoginBO loginBO)
	{
	    this.loginBO = loginBO;
	}

	/*public NCifMaintenanceBO getnCifMaintenanceBO()
	{
	    return nCifMaintenanceBO;
	}

	public void setnCifMaintenanceBO(NCifMaintenanceBO nCifMaintenanceBO)
	{
	    this.nCifMaintenanceBO = nCifMaintenanceBO;
	}*/

	public void setAccountBO(AccountBO accountBO)
	{
	    this.accountBO = accountBO;
	}

	public void setAlertsBO(AlertsBO alertsBO)
	{
	    this.alertsBO = alertsBO;
	}

	public void setGlBO(GLBO glBO)
	{
	    this.glBO = glBO;
	}

	

}
