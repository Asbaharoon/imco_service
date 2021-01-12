package com.path.imco.dao.common;

import java.util.List;
import java.util.Map;

import com.path.dbmaps.vo.DGTL_GTW_MAPPING_DETAILSVO;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * ChannelDAO.java used to
 */
public interface ImcoPwsMappingDAO
{
	/**
	 * @param parameters
	 * @throws BaseException
	 */
	public void savePWSMapping(Map<String,Object> parameters) throws DAOException;
	
	/**
	 * @param mapId
	 * @param mappingIdsMap
	 * @return
	 * @throws BaseException
	 */
	public List<DGTL_GTW_MAPPING_DETAILSVO> returnPWSMappingParams(String mapId) throws DAOException;
}