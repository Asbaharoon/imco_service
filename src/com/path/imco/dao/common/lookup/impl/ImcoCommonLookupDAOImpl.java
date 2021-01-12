package com.path.imco.dao.common.lookup.impl;

import java.util.List;

import com.path.imco.dao.common.lookup.ImcoCommonLookupDAO;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;

public class ImcoCommonLookupDAOImpl extends BaseDAO implements ImcoCommonLookupDAO
{

	@Override
	public List<Object> retLookupList(Object obj) throws DAOException
	{
		return getSqlMap().queryForList("imcoCommonLookupMapper.selectSYS_PARAM_LOV_TRANS", obj);
	}
}
