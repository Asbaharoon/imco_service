/**
 * PWSGenerationSC.java - Oct 22, 2018  
 *
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Raed Saad
 *
 */
package com.path.imco.vo.pwsgeneration;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.ALL_ARGUMENTSVO;
import com.path.dbmaps.vo.DGTL_GTW_ADAPTER_PARAM_MAPVO;
import com.path.dbmaps.vo.DGTL_GTW_DB_CONNVO;
import com.path.dbmaps.vo.DGTL_GTW_WS_ADAPTERVO;
import com.path.struts2.lib.common.GridParamsSC;

public class PWSGenerationSC extends GridParamsSC {
	private String operationName;
	private String operationMapToType;
	
	private String procedureName;
	private String schemaName;
	
	private BigDecimal adapterId;
	
	private String appName;
	private String progRef;
	private String ivCrud;
	private String language;
	private String connId;
	private String connName;
	
	private ALL_ARGUMENTSVO allArgumentsVO;
	private DGTL_GTW_WS_ADAPTERVO dgtlAdapterVO;
	private List<DGTL_GTW_WS_ADAPTERVO> lstDgtlAdapterVO;
	private DGTL_GTW_ADAPTER_PARAM_MAPVO dgtlAdapterParamVO;
	private List<DGTL_GTW_ADAPTER_PARAM_MAPVO> lstDgtlAdapterParamVO;
	private DGTL_GTW_DB_CONNVO dgtlConnVO;
	
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationMapToType() {
		return operationMapToType;
	}
	public void setOperationMapToType(String operationMapToType) {
		this.operationMapToType = operationMapToType;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getProgRef() {
		return progRef;
	}
	public void setProgRef(String progRef) {
		this.progRef = progRef;
	}
	public String getIvCrud() {
		return ivCrud;
	}
	public void setIvCrud(String ivCrud) {
		this.ivCrud = ivCrud;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public ALL_ARGUMENTSVO getAllArgumentsVO() {
		return allArgumentsVO;
	}
	public void setAllArgumentsVO(ALL_ARGUMENTSVO allArgumentsVO) {
		this.allArgumentsVO = allArgumentsVO;
	}
	public String getConnId() {
		return connId;
	}
	public void setConnId(String connId) {
		this.connId = connId;
	}
	public String getConnName() {
		return connName;
	}
	public void setConnName(String connName) {
		this.connName = connName;
	}
	public DGTL_GTW_WS_ADAPTERVO getDgtlAdapterVO() {
		return dgtlAdapterVO;
	}
	public void setDgtlAdapterVO(DGTL_GTW_WS_ADAPTERVO dgtlAdapterVO) {
		this.dgtlAdapterVO = dgtlAdapterVO;
	}
	public List<DGTL_GTW_WS_ADAPTERVO> getLstDgtlAdapterVO() {
		return lstDgtlAdapterVO;
	}
	public void setLstDgtlAdapterVO(List<DGTL_GTW_WS_ADAPTERVO> lstDgtlAdapterVO) {
		this.lstDgtlAdapterVO = lstDgtlAdapterVO;
	}
	public DGTL_GTW_ADAPTER_PARAM_MAPVO getDgtlAdapterParamVO() {
		return dgtlAdapterParamVO;
	}
	public void setDgtlAdapterParamVO(DGTL_GTW_ADAPTER_PARAM_MAPVO dgtlAdapterParamVO) {
		this.dgtlAdapterParamVO = dgtlAdapterParamVO;
	}
	public List<DGTL_GTW_ADAPTER_PARAM_MAPVO> getLstDgtlAdapterParamVO() {
		return lstDgtlAdapterParamVO;
	}
	public void setLstDgtlAdapterParamVO(List<DGTL_GTW_ADAPTER_PARAM_MAPVO> lstDgtlAdapterParamVO) {
		this.lstDgtlAdapterParamVO = lstDgtlAdapterParamVO;
	}
	public DGTL_GTW_DB_CONNVO getDgtlConnVO() {
		return dgtlConnVO;
	}
	public void setDgtlConnVO(DGTL_GTW_DB_CONNVO dgtlConnVO) {
		this.dgtlConnVO = dgtlConnVO;
	}
	public BigDecimal getAdapterId() {
		return adapterId;
	}
	public void setAdapterId(BigDecimal adapterId) {
		this.adapterId = adapterId;
	}
}
