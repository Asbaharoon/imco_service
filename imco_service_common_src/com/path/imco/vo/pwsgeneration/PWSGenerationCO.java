/**
 * PWSGenerationCO.java - Oct 19, 2018  
 *
 * Copyright 2018, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * @author: Raed Saad
 *
 */
package com.path.imco.vo.pwsgeneration;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.zip.ZipFile;

import com.path.dbmaps.vo.ALL_ARGUMENTSVO;
import com.path.dbmaps.vo.DGTL_GTW_ADAPTER_PARAM_MAPVO;
import com.path.dbmaps.vo.DGTL_GTW_DB_CONNVO;
import com.path.dbmaps.vo.DGTL_GTW_WS_ADAPTERVO;
//import com.path.dbmaps.vo.IRP_CONNECTIONSVO;
import com.path.lib.vo.BaseVO;

public class PWSGenerationCO extends BaseVO implements Serializable,Cloneable{
	private String operationName;
	private String operationMapToType;
	private String serviceName;
	private String fieldName;
	private String mapFieldTo;
	private String procedureName;
	private String procedureDescription;
	private String schemaName;
	private String recordData;
	private String methodType;
	private String wsdl;
	
	private ALL_ARGUMENTSVO allArgumentsVO;
	private DGTL_GTW_WS_ADAPTERVO dgtlAdapterVO;
	private List<DGTL_GTW_WS_ADAPTERVO> lstDgtlAdapterVO;
	
	private DGTL_GTW_ADAPTER_PARAM_MAPVO dgtlAdapterParamVO;
	private List<DGTL_GTW_ADAPTER_PARAM_MAPVO> lstDgtlAdapterParamVO;
	private DGTL_GTW_DB_CONNVO dgtlConnVO;
	
	private File bpelFile;
	private ZipFile zipFile;
	private String bpelFileName;
	private String businessDomain;

	/**/
	/*private PWS_OPERATIONVO pwsOperationVO;
	private List<PWS_OPERATIONVO> lstPWSOperationVO;
	private PWS_OPERATION_FIELDSVO pwsOperationFieldsVO;
	private List<PWS_OPERATION_FIELDSVO> lstPWSOperationFieldsVO;*/
	/**/
	private BigDecimal adapterIdSequence;
	
	private String pwsGenerationRecordUpdates;
	private String ivCrud;
	
	private String connId;
	private String connDesc;
	private String dbms;
	private String url;
	private String dbPass;
	private String userId;
	
	private String statusDesc;
	private String wsdlUrl;
	
	
	public Object clone() throws CloneNotSupportedException 
	{
		return super.clone();
	} 
	
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
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getMapFieldTo() {
		return mapFieldTo;
	}
	public void setMapFieldTo(String mapFieldTo) {
		this.mapFieldTo = mapFieldTo;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getProcedureDescription() {
		return procedureDescription;
	}
	public void setProcedureDescription(String procedureDescription) {
		this.procedureDescription = procedureDescription;
	}
	public ALL_ARGUMENTSVO getAllArgumentsVO() {
		return allArgumentsVO;
	}
	public void setAllArgumentsVO(ALL_ARGUMENTSVO allArgumentsVO) {
		this.allArgumentsVO = allArgumentsVO;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	public String getRecordData() {
		return recordData;
	}
	public void setRecordData(String recordData) {
		this.recordData = recordData;
	}
	public String getPwsGenerationRecordUpdates() {
		return pwsGenerationRecordUpdates;
	}
	public void setPwsGenerationRecordUpdates(String pwsGenerationRecordUpdates) {
		this.pwsGenerationRecordUpdates = pwsGenerationRecordUpdates;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getIvCrud() {
		return ivCrud;
	}

	public void setIvCrud(String ivCrud) {
		this.ivCrud = ivCrud;
	}

	public String getConnId() {
		return connId;
	}

	public void setConnId(String connId) {
		this.connId = connId;
	}

	public String getConnDesc() {
		return connDesc;
	}

	public void setConnDesc(String connDesc) {
		this.connDesc = connDesc;
	}

	public String getDbms() {
		return dbms;
	}

	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
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

	public BigDecimal getAdapterIdSequence() {
		return adapterIdSequence;
	}

	public void setAdapterIdSequence(BigDecimal adapterIdSequence) {
		this.adapterIdSequence = adapterIdSequence;
	}

	public File getBpelFile() {
		return bpelFile;
	}

	public void setBpelFile(File bpelFile) {
		this.bpelFile = bpelFile;
	}

	public ZipFile getZipFile() {
		return zipFile;
	}

	public void setZipFile(ZipFile zipFile) {
		this.zipFile = zipFile;
	}

	public String getBpelFileName() {
		return bpelFileName;
	}

	public void setBpelFileName(String bpelFileName) {
		this.bpelFileName = bpelFileName;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getWsdl() {
		return wsdl;
	}

	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}

	public String getBusinessDomain() {
		return businessDomain;
	}

	public void setBusinessDomain(String businessDomain) {
		this.businessDomain = businessDomain;
	}

	public String getWsdlUrl() {
		return wsdlUrl;
	}

	public void setWsdlUrl(String wsdlUrl) {
		this.wsdlUrl = wsdlUrl;
	}
}
