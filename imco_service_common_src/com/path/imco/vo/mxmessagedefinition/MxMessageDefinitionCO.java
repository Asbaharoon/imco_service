package com.path.imco.vo.mxmessagedefinition;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.path.dbmaps.vo.DGTL_MSG_DEFVO;
import com.path.dbmaps.vo.DGTL_XML_MSG_TAGSVO;
import com.path.imco.vo.common.PAYHBaseCO;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * MxMessageDefinitionCO.java used to
 */
public class MxMessageDefinitionCO extends PAYHBaseCO {
	
	private BigDecimal dgtlMsgDefId;
	private BigDecimal dgtlXmlMsgTagsId;
	private DGTL_MSG_DEFVO dgtl_MSG_DEFVO = new DGTL_MSG_DEFVO();
	private List<DGTL_XML_MSG_TAGSVO> tagsvos = new ArrayList<DGTL_XML_MSG_TAGSVO>();
    private String oldCode;
    
	private String xmlContent;
	private File xsdFile;
	private File xmlFile;
	private List<XmlTagsCO> xmlTagsCOs;
	private List<TagsCo> tagsCos;
	private String subGridId;
	private String parentGridId;
	private String parentRowId;
	private String gridData[];
	private String filePath;
	private String selectedTagsJson;
	
	private List<TagsCo> allTagsCos;
	
	private List<TagsCo> parentTags;
	private List<TagsCo> mandatoryTags;
	private String parentTagsJson;
	private String mandatoryTagsJson;
	private String allTagsJson;
	
	private String nonMandatoryParentSelectedRequiredChildsJSON;
	
	
	
	public BigDecimal getDgtlMsgDefId() {
		return dgtlMsgDefId;
	}

	public void setDgtlMsgDefId(BigDecimal dgtlMsgDefId) {
		this.dgtlMsgDefId = dgtlMsgDefId;
	}
	
	

	public BigDecimal getDgtlXmlMsgTagsId() {
		return dgtlXmlMsgTagsId;
	}

	public void setDgtlXmlMsgTagsId(BigDecimal dgtlXmlMsgTagsId) {
		this.dgtlXmlMsgTagsId = dgtlXmlMsgTagsId;
	}

	public DGTL_MSG_DEFVO getDgtl_MSG_DEFVO() {
		return dgtl_MSG_DEFVO;
	}

	public void setDgtl_MSG_DEFVO(DGTL_MSG_DEFVO dgtl_MSG_DEFVO) {
		this.dgtl_MSG_DEFVO = dgtl_MSG_DEFVO;
	}

	public List<DGTL_XML_MSG_TAGSVO> getTagsvos() {
		return tagsvos;
	}

	public void setTagsvos(List<DGTL_XML_MSG_TAGSVO> tagsvos) {
		this.tagsvos = tagsvos;
	}
	
	public String getOldCode() {
		return oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	public String getXmlContent() {
		return xmlContent;
	}

	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}

	public File getXmlFile() {
		return xmlFile;
	}

	public void setXmlFile(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public File getXsdFile() {
		return xsdFile;
	}

	public void setXsdFile(File xsdFile) {
		this.xsdFile = xsdFile;
	}

	public List<XmlTagsCO> getXmlTagsCOs() {
		return xmlTagsCOs;
	}

	public void setXmlTagsCOs(List<XmlTagsCO> xmlTagsCOs) {
		this.xmlTagsCOs = xmlTagsCOs;
	}

	public List<TagsCo> getTagsCos() {
		return tagsCos;
	}

	public void setTagsCos(List<TagsCo> tagsCos) {
		this.tagsCos = tagsCos;
	}

	public String getSubGridId() {
		return subGridId;
	}

	public void setSubGridId(String subGridId) {
		this.subGridId = subGridId;
	}

	public String getParentGridId() {
		return parentGridId;
	}

	public void setParentGridId(String parentGridId) {
		this.parentGridId = parentGridId;
	}

	public String getParentRowId() {
		return parentRowId;
	}

	public void setParentRowId(String parentRowId) {
		this.parentRowId = parentRowId;
	}

	public String[] getGridData() {
		return gridData;
	}

	public void setGridData(String[] gridData) {
		this.gridData = gridData;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSelectedTagsJson() {
		return selectedTagsJson;
	}

	public void setSelectedTagsJson(String selectedTagsJson) {
		this.selectedTagsJson = selectedTagsJson;
	}

	public String getAllTagsJson() {
		return allTagsJson;
	}

	public void setAllTagsJson(String allTagsJson) {
		this.allTagsJson = allTagsJson;
	}

	public List<TagsCo> getAllTagsCos() {
		return allTagsCos;
	}

	public void setAllTagsCos(List<TagsCo> allTagsCos) {
		this.allTagsCos = allTagsCos;
	}
	
	public String getMandatoryTagsJson() {
		return mandatoryTagsJson;
	}

	public void setMandatoryTagsJson(String mandatoryTagsJson) {
		this.mandatoryTagsJson = mandatoryTagsJson;
	}
	
	public List<TagsCo> getParentTags() {
		return parentTags;
	}

	public void setParentTags(List<TagsCo> parentTags) {
		this.parentTags = parentTags;
	}

	public List<TagsCo> getMandatoryTags() {
		return mandatoryTags;
	}

	public void setMandatoryTags(List<TagsCo> mandatoryTags) {
		this.mandatoryTags = mandatoryTags;
	}

	public String getParentTagsJson() {
		return parentTagsJson;
	}

	public void setParentTagsJson(String parentTagsJson) {
		this.parentTagsJson = parentTagsJson;
	}

	public String getNonMandatoryParentSelectedRequiredChildsJSON() {
		return nonMandatoryParentSelectedRequiredChildsJSON;
	}

	public void setNonMandatoryParentSelectedRequiredChildsJSON(String nonMandatoryParentSelectedRequiredChildsJSON) {
		this.nonMandatoryParentSelectedRequiredChildsJSON = nonMandatoryParentSelectedRequiredChildsJSON;
	}
	
	
	
}