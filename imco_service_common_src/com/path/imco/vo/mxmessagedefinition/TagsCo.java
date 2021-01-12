package com.path.imco.vo.mxmessagedefinition;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.path.imco.vo.common.PAYHBaseCO;
import com.path.lib.vo.BaseVO;

public class TagsCo extends PAYHBaseCO {

	private String parentId;
	private String id;
	private BigDecimal dgtlMsgDefId;
	private BigDecimal tagId;
	private String parentComplexName;
	private String complexTypeName;
	private String parentTagName;
	private String tagName;
	private String type;
	private String minOccur;
	private String maxOccur;
	private String isLeafYN;
	private String isMandatoryYN;
	private TagsCo parentTagsCo;
	private String childTags = new String();
	private SimpleTypeCO simpleTypeCO ;
	private List<TagsCo> childTagList = new ArrayList<TagsCo>();
	private String TagHierarchy;
	private String description;
	
	
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public BigDecimal getDgtlMsgDefId() {
		return dgtlMsgDefId;
	}

	public void setDgtlMsgDefId(BigDecimal dgtlMsgDefId) {
		this.dgtlMsgDefId = dgtlMsgDefId;
	}

	public BigDecimal getTagId() {
		return tagId;
	}

	public void setTagId(BigDecimal tagId) {
		this.tagId = tagId;
	}

	public String getParentComplexName() {
		return parentComplexName;
	}

	public void setParentComplexName(String parentComplexName) {
		this.parentComplexName = parentComplexName;
	}

	public String getComplexTypeName() {
		return complexTypeName;
	}

	public void setComplexTypeName(String complexTypeName) {
		this.complexTypeName = complexTypeName;
	}

	public String getParentTagName() {
		return parentTagName;
	}

	public void setParentTagName(String parentTagName) {
		this.parentTagName = parentTagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMinOccur() {
		return minOccur;
	}

	public void setMinOccur(String minOccur) {
		this.minOccur = minOccur;
	}

	public String getMaxOccur() {
		return maxOccur;
	}

	public void setMaxOccur(String maxOccur) {
		this.maxOccur = maxOccur;
	}

	public SimpleTypeCO getSimpleTypeCO() {
		return simpleTypeCO;
	}

	public void setSimpleTypeCO(SimpleTypeCO simpleTypeCO) {
		this.simpleTypeCO = simpleTypeCO;
	}

	public String getIsLeafYN() {
		return isLeafYN;
	}

	public void setIsLeafYN(String isLeafYN) {
		this.isLeafYN = isLeafYN;
	}

	public String getIsMandatoryYN() {
		return isMandatoryYN;
	}

	public void setIsMandatoryYN(String isMandatoryYN) {
		this.isMandatoryYN = isMandatoryYN;
	}

	public String getChildTags() {
		return childTags;
	}

	public void setChildTags(String childTags) {
		this.childTags = childTags;
	}

	public List<TagsCo> getChildTagList() {
		return childTagList;
	}

	public void setChildTagList(List<TagsCo> childTagList) {
		this.childTagList = childTagList;
	}

	public TagsCo getParentTagsCo() {
		return parentTagsCo;
	}

	public void setParentTagsCo(TagsCo parentTagsCo) {
		this.parentTagsCo = parentTagsCo;
	}
	
	public String getTagHierarchy() {
		return TagHierarchy;
	}

	public void setTagHierarchy(String tagHierarchy) {
		TagHierarchy = tagHierarchy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addChild(TagsCo child) {
	        if (!this.childTagList.contains(child) && child != null)
	        {
	        	this.childTagList.add(child);
	        }
    }
	 
	 /**
	  * get Parent CO based on tags and complex type
	  * @param tags
	  * @param tagName
	  * @return
	  */
	 public TagsCo getParentCO(List<TagsCo> tags, String id,String tagName) 
	 {
		for(TagsCo tagCo : tags) 
		{
			if(tagCo.getId().equals(id) 
					&& tagCo.getTagName().equals(tagName))
			{
				return tagCo;
			}
		}
		return null;
	 }

}