package com.path.imco.dao.channel.impl;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.GTW_PWS_TMPLT_MASTERVO;
import com.path.dbmaps.vo.GTW_PWS_TMPLT_USRVO;
import com.path.dbmaps.vo.OPTVO;
import com.path.imco.dao.channel.ChannelDAO;
import com.path.imco.vo.channel.ChannelCO;
import com.path.imco.vo.channel.ChannelSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * ChannelDAOImpl.java used to
 */
public class ChannelDAOImpl extends BaseDAO implements ChannelDAO
{
    /**
     * Method used to return Count of Channel
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws DAOException
     */
    @Override
    public int returnChannelListCount(ChannelSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "channelMapper.resChannelListMap");
	return ((Integer) getSqlMap().queryForObject("channelMapper.returnChannelListCount", criteria)).intValue();
    }
    /**
     * Method used to return List of Channel
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws DAOException
     */
    @Override
    public List returnChannelList(ChannelSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "channelMapper.resChannelListMap");
	if(criteria.getSortOrder() == null)
	{
	    criteria.setSortOrder(" ORDER BY CHANNEL_ID");
	}
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("channelMapper.returnChannelList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("channelMapper.returnChannelList", criteria, criteria.getRecToskip(),criteria.getNbRec());
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.dao.channel.ChannelDAO#returnChannelDetails(com.path.imco.
     * vo.channel.ChannelSC)
     */
    @Override
    public ChannelCO returnChannelDetails(ChannelSC channelSC) throws DAOException
    {
	return (ChannelCO) getSqlMap().queryForObject("channelMapper.returnChannelDetails", channelSC);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.path.imco.dao.channel.ChannelDAO#deleteChannelDetails(com.path.imco.
     * vo.channel.ChannelSC)
     */
    @Override
    public ChannelCO deleteChannelDetails(ChannelSC channelSC) throws DAOException
    {
	return (ChannelCO) getSqlMap().queryForObject("channelMapper.deleteChannelDetails", channelSC);
    }

    /* @Override
    public ChannelCO returnConsumerDetails(ChannelSC channelSC) throws DAOException
    {
	return (ChannelCO) getSqlMap().queryForObject("ebillMapper.returnConsumerDetails", channelSC);
    }*/
    
    @Override
    public int returnMachineIdListCount(ChannelSC sc) throws DAOException
    {
	DAOHelper.fixGridMaps(sc, getSqlMap(), "channelMapper.resMachineIdListMap");
	int nb = 0;
	nb = ((Integer) getSqlMap().queryForObject("channelMapper.returnMachineIdListCount", sc))
		.intValue();
	return nb;
    }
    
    @Override
    public List<ChannelCO> returnMachineIdList(ChannelSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "channelMapper.resMachineIdListMap");
	if(criteria.getSortOrder() == null)
	{
	    criteria.setSortOrder(" ORDER BY HOST_NAME");
	}
	if(criteria.getNbRec() == -1)
	{
	    return (List<ChannelCO>) getSqlMap().queryForList("channelMapper.returnMachineIdList", criteria);
	}
	else
	{
	    return (List<ChannelCO>) getSqlMap().queryForList("channelMapper.returnMachineIdList", criteria,
		    criteria.getRecToskip(), criteria.getNbRec());
	}
    }
    
    @Override
    public void deleteAllMachineId(ChannelCO channelCO) throws DAOException
    {
	getSqlMap().delete("channelMapper.deleteAllMachineId", channelCO);
    }
    
    @Override
    public void deleteAllTemplateId(ChannelCO channelCO) throws DAOException
    {
	getSqlMap().delete("channelMapper.deleteAllTemplateId", channelCO);
    }
    
    @Override
    public String returnHashKey(ChannelSC sc) throws DAOException
    {
	return ((String) getSqlMap().queryForObject("channelMapper.returnHashKey", sc));
    }
    
    @Override
    public int returnTempIdListCount(ChannelSC sc) throws DAOException
    {
	DAOHelper.fixGridMaps(sc, getSqlMap(), "channelMapper.resTempIdLkupMap");
	int nb = 0;
	nb = ((Integer) getSqlMap().queryForObject("channelMapper.returnTempIdListCount", sc)).intValue();
	return nb;
    }
    
    @Override
    public List<GTW_PWS_TMPLT_MASTERVO> returnTempIdList(ChannelSC sc) throws DAOException
    {
	if(sc.getSortOrder() == null)
	{
	    sc.setSortOrder(" ORDER BY TEMPLATE_ID");
	}
	DAOHelper.fixGridMaps(sc, getSqlMap(), "channelMapper.resTempIdLkupMap");
	return getSqlMap().queryForList("channelMapper.returnTempIdList", sc,
		sc.getRecToskip(), sc.getNbRec());
    }
    
    @Override
    public List<GTW_PWS_TMPLT_USRVO> loadAssignedTemplateIdListGrid(ChannelSC sc) throws DAOException
    {
	return getSqlMap().queryForList("channelMapper.loadAssignedTemplateIdListGrid", sc);
    }
	@Override
	public int validateUserId(ChannelSC channelSC) throws DAOException {
		// TODO Auto-generated method stub
		return ((Integer) getSqlMap().queryForObject("channelMapper.validateUserId", channelSC)).intValue();
	}
	
	
	@Override
	public BigDecimal returnLatestDisplayOrder(OPTVO optVO)
			throws DAOException {
		BigDecimal latestDisplayOrder = ((BigDecimal) getSqlMap()
				.queryForObject("channelMapper.getLatestDisplayOrder",
						optVO));
		
		// if null then return one
		if( latestDisplayOrder  == null){
			return BigDecimal.ONE;
		}
		return latestDisplayOrder.add(BigDecimal.ONE);
	}
}
