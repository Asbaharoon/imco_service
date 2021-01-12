package com.path.atm.vo.merchantposmgnt.merchantmgnt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.path.dbmaps.vo.AMFVO;
import com.path.dbmaps.vo.CIFVO;
import com.path.dbmaps.vo.CTSTELLERVO;
import com.path.dbmaps.vo.CTS_MERCHANTVO;
import com.path.dbmaps.vo.GEN_LEDGERVO;
import com.path.dbmaps.vo.SYS_PARAM_SCREEN_DISPLAYVO;
import com.path.vo.common.AlertsParamCO;
import com.path.vo.common.reportresponse.ReportResponseCO;
import com.path.vo.core.account.AccountCO;
import com.path.vo.core.common.RetailBaseVO;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: jihanemazloum
 * 
 *          
 */

public class MerchantMgntCO extends RetailBaseVO{

	private CTS_MERCHANTVO ctsMerchantVO = new CTS_MERCHANTVO();
	private String ecoSectorDesc ;
	private String addReference ;
	private String statusDesc;
	private String contactTypeDesc;
	private String userIsBranchManager;
	private AlertsParamCO alertsParamCO = new AlertsParamCO();
	private String isFromAlert;
	private BigDecimal scannedCIFNo;
	private boolean messageConfirmed;

	//private String confirmationFlag;
	
	//print confirmation
	private ReportResponseCO reportResponseCO = new ReportResponseCO();
	
	private String warningMessages;
	private List<String> listWarningMsg = new ArrayList<String>();
	private String confirmMsg;
	private String colName;
	private String allowInternAcc;
	private String 	userName;
	
	private CIFVO cifVO = new CIFVO();
	private AMFVO amfVO = new AMFVO();
	private GEN_LEDGERVO genledgerVO = new GEN_LEDGERVO();
	private AccountCO accountCO = new AccountCO();
	
	private HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO > hm = new HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO>();
	 
	/*
	 * declare teller to be used under action class to open FOM screen from CIF details button
	 */
	//private CTSTELLERVO ctsTellerVO=new CTSTELLERVO() ;
	
	
	public CTS_MERCHANTVO getCtsMerchantVO() {
		return ctsMerchantVO;
	}
	public void setCtsMerchantVO(CTS_MERCHANTVO ctsMerchantVO) {
		this.ctsMerchantVO = ctsMerchantVO;
	}
	public String getEcoSectorDesc() {
		return ecoSectorDesc;
	}
	public void setEcoSectorDesc(String ecoSectorDesc) {
		this.ecoSectorDesc = ecoSectorDesc;
	}
	public String getAddReference() {
		return addReference;
	}
	public void setAddReference(String addReference) {
		this.addReference = addReference;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getContactTypeDesc()
	{
	    return contactTypeDesc;
	}
	public void setContactTypeDesc(String contactTypeDesc)
	{
	    this.contactTypeDesc = contactTypeDesc;
	}
	/*public CTSTELLERVO getCtsTellerVO()
	{
	    return ctsTellerVO;
	}
	public void setCtsTellerVO(CTSTELLERVO ctsTellerVO)
	{
	    this.ctsTellerVO = ctsTellerVO;
	}*/
	public String getUserIsBranchManager()
	{
	    return userIsBranchManager;
	}
	public void setUserIsBranchManager(String userIsBranchManager)
	{
	    this.userIsBranchManager = userIsBranchManager;
	}
	public AlertsParamCO getAlertsParamCO()
	{
	    return alertsParamCO;
	}
	public void setAlertsParamCO(AlertsParamCO alertsParamCO)
	{
	    this.alertsParamCO = alertsParamCO;
	}
	public String getIsFromAlert()
	{
	    return isFromAlert;
	}
	public void setIsFromAlert(String isFromAlert)
	{
	    this.isFromAlert = isFromAlert;
	}
	public ReportResponseCO getReportResponseCO()
	{
	    return reportResponseCO;
	}
	public void setReportResponseCO(ReportResponseCO reportResponseCO)
	{
	    this.reportResponseCO = reportResponseCO;
	}
	public BigDecimal getScannedCIFNo()
	{
	    return scannedCIFNo;
	}
	public void setScannedCIFNo(BigDecimal scannedCIFNo)
	{
	    this.scannedCIFNo = scannedCIFNo;
	}
	/*public String getConfirmationFlag()
	{
	    return confirmationFlag;
	}
	public void setConfirmationFlag(String confirmationFlag)
	{
	    this.confirmationFlag = confirmationFlag;
	}*/
	public String getWarningMessages()
	{
	    return warningMessages;
	}
	public void setWarningMessages(String warningMessages)
	{
	    this.warningMessages = warningMessages;
	}
	public List<String> getListWarningMsg()
	{
	    return listWarningMsg;
	}
	public void setListWarningMsg(List<String> listWarningMsg)
	{
	    this.listWarningMsg = listWarningMsg;
	}
	public String getConfirmMsg()
	{
	    return confirmMsg;
	}
	public void setConfirmMsg(String confirmMsg)
	{
	    this.confirmMsg = confirmMsg;
	}
	public String getColName()
	{
	    return colName;
	}
	public void setColName(String colName)
	{
	    this.colName = colName;
	}
	public CIFVO getCifVO()
	{
	    return cifVO;
	}
	public void setCifVO(CIFVO cifVO)
	{
	    this.cifVO = cifVO;
	}
	public HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> getHm()
	{
	    return hm;
	}
	public void setHm(HashMap<String, SYS_PARAM_SCREEN_DISPLAYVO> hm)
	{
	    this.hm = hm;
	}
	public AMFVO getAmfVO()
	{
	    return amfVO;
	}
	public void setAmfVO(AMFVO amfVO)
	{
	    this.amfVO = amfVO;
	}
	public GEN_LEDGERVO getGenledgerVO()
	{
	    return genledgerVO;
	}
	public void setGenledgerVO(GEN_LEDGERVO genledgerVO)
	{
	    this.genledgerVO = genledgerVO;
	}
	public String getAllowInternAcc()
	{
	    return allowInternAcc;
	}
	public void setAllowInternAcc(String allowInternAcc)
	{
	    this.allowInternAcc = allowInternAcc;
	}
	public String getUserName()
	{
	    return userName;
	}
	public void setUserName(String userName)
	{
	    this.userName = userName;
	}
	public AccountCO getAccountCO()
	{
	    return accountCO;
	}
	public void setAccountCO(AccountCO accountCO)
	{
	    this.accountCO = accountCO;
	}
	public boolean isMessageConfirmed()
	{
	    return messageConfirmed;
	}
	public void setMessageConfirmed(boolean messageConfirmed)
	{
	    this.messageConfirmed = messageConfirmed;
	}
}
