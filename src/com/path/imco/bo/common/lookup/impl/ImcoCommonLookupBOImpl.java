package com.path.imco.bo.common.lookup.impl;

import java.util.List;

import com.path.imco.bo.common.lookup.ImcoCommonLookupBO;
import com.path.imco.dao.common.lookup.ImcoCommonLookupDAO;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BaseException;

public class ImcoCommonLookupBOImpl extends  BaseBO implements ImcoCommonLookupBO
{
	private ImcoCommonLookupDAO imcoCommonLookupDAO;
	
	@Override
	public List<Object> retLookupList(Object obj) throws BaseException
	{
		return this.imcoCommonLookupDAO.retLookupList(obj);
	}

	public void setImcoCommonLookupDAO(ImcoCommonLookupDAO imcoCommonLookupDAO)
	{
		this.imcoCommonLookupDAO = imcoCommonLookupDAO;
	}
}
