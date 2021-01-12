package com.path.atm.vo.atminterface;

import java.math.BigDecimal;
import java.util.Date;

import com.path.dbmaps.vo.DGTL_ATM_INTERFACESVO;
import com.path.dbmaps.vo.DGTL_ISO_FLDSVO;
import com.path.dbmaps.vo.DGTL_ISO_MSGSVO;
import com.path.dbmaps.vo.DGTL_ISO_SUB_FLDSVO;
import com.path.dbmaps.vo.SYS_PARAM_ISO_FLDSVO;
import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IsoMessageCO.java used to
 */
public class AtmInterfaceCO extends BaseVO
{		
	//VO
	SYS_PARAM_ISO_FLDSVO iso_FIELDVO = new SYS_PARAM_ISO_FLDSVO();
	DGTL_ISO_MSGSVO iso_INT_MSGSVO = new DGTL_ISO_MSGSVO();
	DGTL_ISO_FLDSVO iso_INT_FLDSVO = new DGTL_ISO_FLDSVO();
	DGTL_ATM_INTERFACESVO iso_INTERFACESVO = new DGTL_ATM_INTERFACESVO();
	DGTL_ISO_SUB_FLDSVO sub_FLDSVO = new DGTL_ISO_SUB_FLDSVO();
	
	//Strings
	private String appName;
	private String userId;
	private String tcpTypeDesc;
	private String PARTIAL_MASK;
	private String TYPE;
	private String EXPRESSION;
	private String IS_DECIMAL_YN;
	private String HEADER;
	private String STATUS;
	private String statusDesc;
	private String mode;
	private String data;
	private String subGridData;
	
	//BigDedcimals
	private BigDecimal FIELD_LENL;
	private BigDecimal TOTAL_MASK;
	private BigDecimal branchCode;
	private BigDecimal compCode;
	
	//Date
	private Date runningDate;
	
	public String getStatusDesc() {
		return statusDesc;
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	public String getEXPRESSION()
	{
		return EXPRESSION;
	}
	public void setEXPRESSION(String eXPRESSION)
	{
		EXPRESSION = eXPRESSION;
	}
	public String getIS_DECIMAL_YN()
	{
		return IS_DECIMAL_YN;
	}
	public void setIS_DECIMAL_YN(String iS_DECIMAL_YN)
	{
		IS_DECIMAL_YN = iS_DECIMAL_YN;
	}
	public String getTYPE()
	{
		return TYPE;
	}
	public void setTYPE(String tYPE)
	{
		TYPE = tYPE;
	}
	public String getAppName()
	{
		return appName;
	}
	public void setAppName(String appName)
	{
		this.appName = appName;
	}
	public BigDecimal getBranchCode()
	{
		return branchCode;
	}
	public void setBranchCode(BigDecimal branchCode)
	{
		this.branchCode = branchCode;
	}
	public BigDecimal getFIELD_LENL()
	{
		return FIELD_LENL;
	}
	public void setFIELD_LENL(BigDecimal fIELD_LENL)
	{
		FIELD_LENL = fIELD_LENL;
	}
	public BigDecimal getTOTAL_MASK()
	{
		return TOTAL_MASK;
	}
	public void setTOTAL_MASK(BigDecimal tOTAL_MASK)
	{
		TOTAL_MASK = tOTAL_MASK;
	}
	public String getPARTIAL_MASK()
	{
		return PARTIAL_MASK;
	}
	public void setPARTIAL_MASK(String pARTIAL_MASK)
	{
		PARTIAL_MASK = pARTIAL_MASK;
	}
	public String getTcpTypeDesc()
	{
		return tcpTypeDesc;
	}
	public void setTcpTypeDesc(String tcpTypeDesc)
	{
		this.tcpTypeDesc = tcpTypeDesc;
	}
	public String getHEADER()
	{
		return HEADER;
	}
	public void setHEADER(String hEADER)
	{
		HEADER = hEADER;
	}
	
	public SYS_PARAM_ISO_FLDSVO getIso_FIELDVO()
	{
		return iso_FIELDVO;
	}
	public void setIso_FIELDVO(SYS_PARAM_ISO_FLDSVO iso_FIELDVO)
	{
		this.iso_FIELDVO = iso_FIELDVO;
	}
	public Date getRunningDate()
	{
		return runningDate;
	}
	public void setRunningDate(Date runningDate)
	{
		this.runningDate = runningDate;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public BigDecimal getCompCode()
	{
		return compCode;
	}
	public void setCompCode(BigDecimal compCode)
	{
		this.compCode = compCode;
	}
	public String getSTATUS()
	{
		return STATUS;
	}
	public void setSTATUS(String sTATUS)
	{
		STATUS = sTATUS;
	}

	public DGTL_ISO_MSGSVO getIso_INT_MSGSVO() {
		return iso_INT_MSGSVO;
	}

	public void setIso_INT_MSGSVO(DGTL_ISO_MSGSVO iso_INT_MSGSVO) {
		this.iso_INT_MSGSVO = iso_INT_MSGSVO;
	}

	public DGTL_ISO_FLDSVO getIso_INT_FLDSVO() {
		return iso_INT_FLDSVO;
	}

	public void setIso_INT_FLDSVO(DGTL_ISO_FLDSVO iso_INT_FLDSVO) {
		this.iso_INT_FLDSVO = iso_INT_FLDSVO;
	}

	public DGTL_ATM_INTERFACESVO getIso_INTERFACESVO() {
		return iso_INTERFACESVO;
	}

	public void setIso_INTERFACESVO(DGTL_ATM_INTERFACESVO iso_INTERFACESVO) {
		this.iso_INTERFACESVO = iso_INTERFACESVO;
	}

	public DGTL_ISO_SUB_FLDSVO getSub_FLDSVO() {
		return sub_FLDSVO;
	}

	public void setSub_FLDSVO(DGTL_ISO_SUB_FLDSVO sub_FLDSVO) {
		this.sub_FLDSVO = sub_FLDSVO;
	}

	public String getData() 
	{
		return data;
	}

	public void setData(String data) 
	{
		this.data = data;
	}

	public String getSubGridData() 
	{
		return subGridData;
	}

	public void setSubGridData(String subGridData) 
	{
		this.subGridData = subGridData;
	}
}
