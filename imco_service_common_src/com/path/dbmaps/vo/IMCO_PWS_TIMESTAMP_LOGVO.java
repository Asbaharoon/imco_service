package com.path.dbmaps.vo;

import java.util.Date;

import com.path.lib.vo.BaseVO;

public class IMCO_PWS_TIMESTAMP_LOGVO extends BaseVO{

	private String REQUEST_ID;
	private String STEP_NAME;
	private String STEP_STAGE;
	private Date STEP_TIMESTAMP;
	private String BUSINESS_AREA;
	private String SERVICE_NAME;
	private String OPERATION_NAME;
	
	public String getREQUEST_ID() {
		return REQUEST_ID;
	}
	public void setREQUEST_ID(String rEQUEST_ID) {
		REQUEST_ID = rEQUEST_ID;
	}
	public String getSTEP_NAME() {
		return STEP_NAME;
	}
	public void setSTEP_NAME(String sTEP_NAME) {
		STEP_NAME = sTEP_NAME;
	}
	public String getSTEP_STAGE() {
		return STEP_STAGE;
	}
	public void setSTEP_STAGE(String sTEP_STAGE) {
		STEP_STAGE = sTEP_STAGE;
	}
	public Date getSTEP_TIMESTAMP() {
		return STEP_TIMESTAMP;
	}
	public void setSTEP_TIMESTAMP(Date sTEP_TIMESTAMP) {
		STEP_TIMESTAMP = sTEP_TIMESTAMP;
	}
	public String getBUSINESS_AREA() {
		return BUSINESS_AREA;
	}
	public void setBUSINESS_AREA(String bUSINESS_AREA) {
		BUSINESS_AREA = bUSINESS_AREA;
	}
	public String getSERVICE_NAME() {
		return SERVICE_NAME;
	}
	public void setSERVICE_NAME(String sERVICE_NAME) {
		SERVICE_NAME = sERVICE_NAME;
	}
	public String getOPERATION_NAME() {
		return OPERATION_NAME;
	}
	public void setOPERATION_NAME(String oPERATION_NAME) {
		OPERATION_NAME = oPERATION_NAME;
	}
}
