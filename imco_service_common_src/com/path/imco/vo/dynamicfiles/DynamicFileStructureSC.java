package com.path.imco.vo.dynamicfiles;

import java.math.BigDecimal;

import com.path.struts2.lib.common.GridParamsSC;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * DynamicFileEditorSC.java used to
 */
public class DynamicFileStructureSC extends GridParamsSC
{
	private BigDecimal UPL_ID;
	private BigDecimal dynamicStructureFileId;
	private BigDecimal messageId;
	private BigDecimal fileId;
	private BigDecimal tagValueId;
	private BigDecimal tagId;
	private BigDecimal mappingId;
	
	private String status;
	private String pageRef;
	private String parentTag;
	private String fileReference;
	private String fileType;
	
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

	public void setUPL_ID(BigDecimal UPL_ID)
	{
		this.UPL_ID = UPL_ID;
	}
	
	public BigDecimal getUPL_ID()
	{
		return UPL_ID;
	}
	
	public BigDecimal getDynamicStructureFileId() 
	{
		return dynamicStructureFileId;
	}
	
	public void setDynamicStructureFileId(BigDecimal dynamicStructureFileId) 
	{
		this.dynamicStructureFileId = dynamicStructureFileId;
	}
	
	public BigDecimal getMessageId() 
	{
		return messageId;
	}
	
	public void setMessageId(BigDecimal messageId) 
	{
		this.messageId = messageId;
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
	
	
	public DynamicFileStructureSC() 
	{
		super.setSearchCols(new String[] {"dyn_FILE_STRUCTUREVO.REFERENCE", "dyn_FILE_STRUCTUREVO.FILE_TYPE", "dyn_FILE_STRUCTUREVO.DESCRIPTION"});
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
