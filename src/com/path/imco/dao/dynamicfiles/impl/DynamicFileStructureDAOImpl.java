package com.path.imco.dao.dynamicfiles.impl;

import java.util.List;

import com.path.dbmaps.vo.IMCO_DYN_FILE_MESSAGEVO;
import com.path.dbmaps.vo.IMCO_DYN_FILE_STRUCTUREVO;
import com.path.imco.dao.dynamicfiles.DynamicFileStructureDAO;
import com.path.imco.vo.dynamicfiles.DynamicFileStructureCO;
import com.path.imco.vo.dynamicfiles.DynamicFileStructureSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * DynamicFileStructureDAOImpl.java used to
 */
public class DynamicFileStructureDAOImpl extends BaseDAO implements DynamicFileStructureDAO
{
	@Override
	public List returnDynamicFileStructureList(DynamicFileStructureSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "dynamicFileStructureMapper.reDynamicFileStructureListMap");
		if(criteria.getNbRec() == -1)
		{
			return getSqlMap().queryForList("dynamicFileStructureMapper.returnDynamicFileStructureList", criteria);
		}
		else
		{
			return getSqlMap().queryForList("dynamicFileStructureMapper.returnDynamicFileStructureList", criteria, criteria.getRecToskip(),criteria.getNbRec());
		}
	}
	
	@Override
	public int returnDynamicFileStructureListCount(DynamicFileStructureSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "dynamicFileStructureMapper.reDynamicFileStructureListMap");
		return ((Integer) getSqlMap().queryForObject("dynamicFileStructureMapper.returnDynamicFileStructureListCount", criteria)).intValue();
	}
	
	@Override
	public DynamicFileStructureCO returnDynamicFileStructureDetails(DynamicFileStructureSC dynamicFileStructureSC) throws DAOException 
	{
		return (DynamicFileStructureCO) getSqlMap().queryForObject("dynamicFileStructureMapper.returnDynamicFileStructureDetails", dynamicFileStructureSC);
	}

	@Override
	public int returnDynamicFileStructureMessageListCount(DynamicFileStructureSC criteria) throws DAOException 
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "dynamicFileStructureMapper.reDynamicFileStructureListMap");
		return ((Integer) getSqlMap().queryForObject("dynamicFileStructureMapper.returnDynamicFileStructureMessageListCount", criteria)).intValue();
	}

	@Override
	public List returnDynamicFileStructureMessageList(DynamicFileStructureSC criteria) throws DAOException 
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "dynamicFileStructureMapper.reDynamicFileStructureListMap");
		if(criteria.getNbRec() == -1)
		{
			return getSqlMap().queryForList("dynamicFileStructureMapper.returnDynamicFileStructureMessageList", criteria);
		}
		else
		{
			return getSqlMap().queryForList("dynamicFileStructureMapper.returnDynamicFileStructureMessageList", criteria, criteria.getRecToskip(),criteria.getNbRec());
		}
	}

	@Override
	public List returnDynamicFileStructureTagsList(DynamicFileStructureSC criteria) throws DAOException 
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "dynamicFileStructureMapper.reDynamicFileStructureTagListMap");
		return getSqlMap().queryForList("dynamicFileStructureMapper.returnDynamicFileStructureTagsList", criteria);
	}

	@Override
	public void insertFileStructure(IMCO_DYN_FILE_STRUCTUREVO file_STRUCTUREVO) throws DAOException 
	{
		getSqlMap().insert("dynamicFileStructureMapper.insertFileStructure", file_STRUCTUREVO);
	}

	@Override
	public void insertFileMessage(IMCO_DYN_FILE_MESSAGEVO file_MESSAGEVO) throws DAOException 
	{
		getSqlMap().insert("dynamicFileStructureMapper.insertFileMessage", file_MESSAGEVO);
	}

	@Override
	public int checkFileRefUnique(DynamicFileStructureSC criteria) throws DAOException
	{
		return ((Integer) getSqlMap().queryForObject("dynamicFileStructureMapper.returnFileCountByFileRef", criteria)).intValue();
	}
}
