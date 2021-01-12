package com.path.atm.dao.atminterface.impl;

import java.util.List;

import com.path.atm.dao.atminterface.AtmInterfaceDAO;
import com.path.atm.vo.atminterface.AtmInterfaceCO;
import com.path.atm.vo.atminterface.AtmInterfaceSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;

/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IsoMessageDAOImpl.java used to
 */
public class AtmInterfaceDAOImpl extends BaseDAO implements AtmInterfaceDAO 
{

	@Override
	public List retIsoFieldsList(AtmInterfaceSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.isoFieldsMap");
		if(criteria.getNbRec() == -1)
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retIsoFieldsList", criteria);
		}
		else
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retIsoFieldsList", criteria, criteria.getRecToskip(),criteria.getNbRec());
		}
	}

	@Override
	public int retIsoFieldsListCount(AtmInterfaceSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.isoFieldsMap");
		return ((Integer) getSqlMap().queryForObject("atmInterfaceMapper.retIsoFieldsListCount", criteria)).intValue();
	}

	@Override
	public List retInterfaceList(AtmInterfaceSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.interfaceMap");
		if(criteria.getNbRec() == -1)
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retInterfaceList", criteria);
		}
		else
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retInterfaceList", criteria, criteria.getRecToskip(),criteria.getNbRec());
		}
	}

	@Override
	public int retInterfaceListCount(AtmInterfaceSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.interfaceMap");
		return ((Integer) getSqlMap().queryForObject("atmInterfaceMapper.retInterfaceListCount", criteria)).intValue();
	}

	@Override
	public int retNewInterfaceId(AtmInterfaceSC criteria) throws DAOException
	{
		int result = 1;
		if(getSqlMap().queryForObject("atmInterfaceMapper.retNewInterfaceId", criteria) != null) {
			result = ((Integer) getSqlMap().queryForObject("atmInterfaceMapper.retNewInterfaceId", criteria)).intValue();
		}
		return result;
	}

	@Override
	public int retNewMessageCode(AtmInterfaceSC criteria) throws DAOException
	{
		int result = 1;
		if(getSqlMap().queryForObject("atmInterfaceMapper.retNewMessageCode", criteria) != null) {
			result = ((Integer) getSqlMap().queryForObject("atmInterfaceMapper.retNewMessageCode", criteria)).intValue();
		}
		return result;
	}

	@Override
	public List returnMessageList(AtmInterfaceSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.messagesMap");
		if(criteria.getNbRec() == -1)
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retMessagesList", criteria);
		}
		else
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retMessagesList", criteria, criteria.getRecToskip(),criteria.getNbRec());
		}
	}

	@Override
	public int returnMessageListCount(AtmInterfaceSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.messagesMap");
		return ((Integer) getSqlMap().queryForObject("atmInterfaceMapper.retMessagesListCount", criteria)).intValue();
	}

	@Override
	public List returnFieldsList(AtmInterfaceSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.fieldsMap");
		if(criteria.getNbRec() == -1)
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retFieldsList", criteria);
		}
		else
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retFieldsList", criteria, criteria.getRecToskip(),criteria.getNbRec());
		}
	}

	@Override
	public int returnFieldsListCount(AtmInterfaceSC criteria) throws DAOException
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.fieldsMap");
		return ((Integer) getSqlMap().queryForObject("atmInterfaceMapper.retFieldsListCount", criteria)).intValue();
	}

	@Override
	public AtmInterfaceCO returnInterfaceById(AtmInterfaceSC criteria) throws DAOException
	{
		return (AtmInterfaceCO) getSqlMap().queryForObject("atmInterfaceMapper.retInterfaceByCode", criteria);
	}
	
	@Override
    public int retProgOrder() throws DAOException
    {
		  return ((Integer) getSqlMap().queryForObject("atmInterfaceMapper.retProgOrder", null));
    }

	@Override
	public List retIsoSubFieldsList(AtmInterfaceSC criteria) throws DAOException 
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.isoSubFieldsMap");
		if(criteria.getNbRec() == -1)
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retIsoSubFieldsList", criteria);
		}
		else
		{
			return getSqlMap().queryForList("atmInterfaceMapper.retIsoSubFieldsList", criteria, criteria.getRecToskip(),criteria.getNbRec());
		}
	}

	@Override
	public int retIsoSubFieldsListCount(AtmInterfaceSC criteria) throws DAOException 
	{
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "atmInterfaceMapper.isoSubFieldsMap");
		return ((Integer) getSqlMap().queryForObject("atmInterfaceMapper.retIsoSubFieldsListCount", criteria)).intValue();
	}

	@Override
	public List retFieldListByBitmap(AtmInterfaceSC criteria) throws DAOException 
	{
		return getSqlMap().queryForList("atmInterfaceMapper.retFieldListByBitmap", criteria);
	}
}
