package com.path.imco.dao.common.impl;

import java.util.List;
import java.util.Map;

import com.path.dbmaps.vo.DGTL_GTW_MAPPING_DETAILSVO;
import com.path.imco.dao.common.ImcoPwsMappingDAO;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * ChannelDAO.java used to
 */
public class ImcoPwsMappingDAOImpl extends BaseDAO implements ImcoPwsMappingDAO
{

	@Override
	public void savePWSMapping(Map<String, Object> parameters) throws DAOException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<DGTL_GTW_MAPPING_DETAILSVO> returnPWSMappingParams(String mapId) throws DAOException {
		// TODO Auto-generated method stub
		return getSqlMap().queryForList("imcoPwsMappingMapper.returnPWSMappingParams", mapId);
	}

}