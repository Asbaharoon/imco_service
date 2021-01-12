package com.path.imco.vo.mxmessagedefinition;

import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2020, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * XmlTagsCO.java used to
 */
public class XmlTagsCO extends BaseVO
{
	private String tagName;
	private String parentTagName;
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getParentTagName() {
		return parentTagName;
	}
	public void setParentTagName(String parentTagName) {
		this.parentTagName = parentTagName;
	}
}