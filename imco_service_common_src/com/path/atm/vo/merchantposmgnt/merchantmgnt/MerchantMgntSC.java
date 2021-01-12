package com.path.atm.vo.merchantposmgnt.merchantmgnt;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;


/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: jihanemazloum
 * GridParamsSC: contains the parameters needed for Search, Flipping and Sorting 
 *          
 */
public class MerchantMgntSC extends GridParamsSC{


    private String progRef;
    private String appName;
    private String lang;
    private String ivCrud;
    private BigDecimal lovType;
    private BigDecimal lovTypeContact;
    private BigDecimal code; 
    private BigDecimal scannedCIF; 
    private String showInPos;
    
    
   
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getIvCrud() {
		return ivCrud;
	}
	public void setIvCrud(String ivCrud) {
		this.ivCrud = ivCrud;
	}
	public BigDecimal getLovType() {
		return lovType;
	}
	public void setLovType(BigDecimal lovType) {
		this.lovType = lovType;
	}
	public String getProgRef() {
		return progRef;
	}
	public void setProgRef(String progRef) {
		this.progRef = progRef;
	}
	public BigDecimal getCode() {
		return code;
	}
	public void setCode(BigDecimal code) {
		this.code = code;
	}
	public BigDecimal getLovTypeContact()
	{
	    return lovTypeContact;
	}
	public void setLovTypeContact(BigDecimal lovTypeContact)
	{
	    this.lovTypeContact = lovTypeContact;
	}
	public String getShowInPos()
	{
	    return showInPos;
	}
	public void setShowInPos(String showInPos)
	{
	    this.showInPos = showInPos;
	}
	public BigDecimal getScannedCIF()
	{
	    return scannedCIF;
	}
	public void setScannedCIF(BigDecimal scannedCIF)
	{
	    this.scannedCIF = scannedCIF;
	}
	 
}
