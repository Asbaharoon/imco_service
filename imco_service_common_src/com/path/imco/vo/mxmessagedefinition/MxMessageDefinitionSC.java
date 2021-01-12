package com.path.imco.vo.mxmessagedefinition;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * DynamicFileEditorSC.java used to
 */
public class MxMessageDefinitionSC extends GridParamsSC
{
	private BigDecimal mxMsgDefinitionId;
	private BigDecimal messageId;
	private String tagHierarchy;
	
	private BigDecimal fileId;
	private BigDecimal tagValueId;
	private BigDecimal tagId;
	private BigDecimal mappingId;
	
	private String status;
	private String pageRef;
	private String parentTag;
	private String fileReference;
	private String fileType;
	private BigDecimal statuslOV;
	
	public BigDecimal getTagId()
	{
		return tagId;
	}

	public void setTagId(BigDecimal tagId)
	{
		this.tagId = tagId;
	}

	public BigDecimal getTagValueId()
	{
		return tagValueId;
	}

	public void setTagValueId(BigDecimal tagValueId)
	{
		this.tagValueId = tagValueId;
	}

	public String getFileReference() 
	{
		return fileReference;
	}

	public void setFileReference(String fileReference)
	{
		this.fileReference = fileReference;
	}

	public String getParentTag() 
	{
		return parentTag;
	}

	public void setParentTag(String parentTag) 
	{
		this.parentTag = parentTag;
	}

	public BigDecimal getMxMsgDefinitionId() {
		return mxMsgDefinitionId;
	}

	public void setMxMsgDefinitionId(BigDecimal mxMsgDefinitionId) {
		this.mxMsgDefinitionId = mxMsgDefinitionId;
	}

	public BigDecimal getMessageId() 
	{
		return messageId;
	}
	
	public void setMessageId(BigDecimal messageId) 
	{
		this.messageId = messageId;
	}
	
	public String getTagHierarchy() {
		return tagHierarchy;
	}

	public void setTagHierarchy(String tagHierarchy) {
		this.tagHierarchy = tagHierarchy;
	}

	public String getStatus() 
	{
		return status;
	}
	
	public void setStatus(String status) 
	{
		this.status = status;
	}
	
	public String getPageRef() 
	{
		return pageRef;
	}
	
	public void setPageRef(String pageRef) 
	{
		this.pageRef = pageRef;
	}
	public BigDecimal getFileId() {
		return fileId;
	}
	public void setFileId(BigDecimal fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public BigDecimal getStatuslOV() {
		return statuslOV;
	}

	public void setStatuslOV(BigDecimal statuslOV) {
		this.statuslOV = statuslOV;
	}

	public BigDecimal getMappingId()
	{
	    return mappingId;
	}

	public void setMappingId(BigDecimal mappingId)
	{
	    this.mappingId = mappingId;
	}
}
