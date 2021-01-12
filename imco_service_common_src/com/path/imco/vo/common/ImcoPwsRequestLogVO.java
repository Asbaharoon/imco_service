package com.path.imco.vo.common;

import java.math.BigDecimal;
import java.util.Date;

import com.path.lib.vo.BaseVO;

public class ImcoPwsRequestLogVO extends BaseVO{
	
public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHashKey() {
		return hashKey;
	}
	public void setHashKey(String hashKey) {
		this.hashKey = hashKey;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getChannelID() {
		return channelID;
	}
	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}
private String requestID;
private String userID; 	
private String password; 
private String hashKey; 
private String hostName ;
private String channelID;
private Date coreRequestTimeStamp; 
private BigDecimal companyCode;
private BigDecimal branchCode; 
private BigDecimal cifNo;
private BigDecimal dealNo;
private BigDecimal transactionNo;
private BigDecimal branch;
private BigDecimal currency;
private BigDecimal accGl;
private BigDecimal serialNo;
private String additionalRef;
private String ibanAccNo;
private BigDecimal opNo;
private String cardNo;
private String requestKernelDetails;
private String businessArea;
private String businessDomain;
private String serviceDomain;
private String operationName;
private String version;
private String serviceID;
private String responseID; 
private Date coreResponseTimeStamp;
private String statusCode;
private String severity;
private String errorType;
private String statusDesc;


public String getRequestID() {
	return requestID;
}
public void setRequestID(String requestID) {
	this.requestID = requestID;
}
public Date getCoreRequestTimeStamp() {
	return coreRequestTimeStamp;
}
public void setCoreRequestTimeStamp(Date coreRequestTimeStamp) {
	this.coreRequestTimeStamp = coreRequestTimeStamp;
}
public BigDecimal getCompanyCode() {
	return companyCode;
}
public void setCompanyCode(BigDecimal companyCode) {
	this.companyCode = companyCode;
}
public BigDecimal getBranchCode() {
	return branchCode;
}
public void setBranchCode(BigDecimal branchCode) {
	this.branchCode = branchCode;
}
public BigDecimal getCifNo() {
	return cifNo;
}
public void setCifNo(BigDecimal cifNo) {
	this.cifNo = cifNo;
}
public BigDecimal getDealNo() {
	return dealNo;
}
public void setDealNo(BigDecimal dealNo) {
	this.dealNo = dealNo;
}
public BigDecimal getTransactionNo() {
	return transactionNo;
}
public void setTransactionNo(BigDecimal transactionNo) {
	this.transactionNo = transactionNo;
}
public BigDecimal getBranch() {
	return branch;
}
public void setBranch(BigDecimal branch) {
	this.branch = branch;
}
public BigDecimal getCurrency() {
	return currency;
}
public void setCurrency(BigDecimal currency) {
	this.currency = currency;
}
public BigDecimal getAccGl() {
	return accGl;
}
public void setAccGl(BigDecimal accGl) {
	this.accGl = accGl;
}
public BigDecimal getSerialNo() {
	return serialNo;
}
public void setSerialNo(BigDecimal serialNo) {
	this.serialNo = serialNo;
}
public String getAdditionalRef() {
	return additionalRef;
}
public void setAdditionalRef(String additionalRef) {
	this.additionalRef = additionalRef;
}
public String getIbanAccNo() {
	return ibanAccNo;
}
public void setIbanAccNo(String ibanAccNo) {
	this.ibanAccNo = ibanAccNo;
}
public BigDecimal getOpNo() {
	return opNo;
}
public void setOpNo(BigDecimal opNo) {
	this.opNo = opNo;
}
public String getCardNo() {
	return cardNo;
}
public void setCardNo(String cardNo) {
	this.cardNo = cardNo;
}
public String getBusinessArea() {
	return businessArea;
}
public void setBusinessArea(String businessArea) {
	this.businessArea = businessArea;
}
public String getBusinessDomain() {
	return businessDomain;
}
public void setBusinessDomain(String businessDomain) {
	this.businessDomain = businessDomain;
}
public String getServiceDomain() {
	return serviceDomain;
}
public void setServiceDomain(String serviceDomain) {
	this.serviceDomain = serviceDomain;
}
public String getOperationName() {
	return operationName;
}
public void setOperationName(String operationName) {
	this.operationName = operationName;
}
public String getVersion() {
	return version;
}
public void setVersion(String version) {
	this.version = version;
}
public String getServiceID() {
	return serviceID;
}
public void setServiceID(String serviceID) {
	this.serviceID = serviceID;
}
public String getResponseID() {
	return responseID;
}
public void setResponseID(String responseID) {
	this.responseID = responseID;
}
public Date getCoreResponseTimeStamp() {
	return coreResponseTimeStamp;
}
public void setCoreResponseTimeStamp(Date coreResponseTimeStamp) {
	this.coreResponseTimeStamp = coreResponseTimeStamp;
}
public String getStatusDesc() {
	return statusDesc;
}
public void setStatusDesc(String statusDesc) {
	this.statusDesc = statusDesc;
}
public String getStatusCode() {
	return statusCode;
}
public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
}
public String getSeverity() {
	return severity;
}
public void setSeverity(String severity) {
	this.severity = severity;
}
public String getErrorType() {
	return errorType;
}
public void setErrorType(String errorType) {
	this.errorType = errorType;
}
public String getRequestKernelDetails() {
	return requestKernelDetails;
}
public void setRequestKernelDetails(String requestKernelDetails) {
	this.requestKernelDetails = requestKernelDetails;
}
 

}
