package com.path.imco.dao.channel;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.GTW_PWS_TMPLT_MASTERVO;
import com.path.dbmaps.vo.GTW_PWS_TMPLT_USRVO;
import com.path.dbmaps.vo.OPTVO;
import com.path.imco.vo.channel.ChannelCO;
import com.path.imco.vo.channel.ChannelSC;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.exception.DAOException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * ChannelDAO.java used to
 */
public interface ChannelDAO
{
    public int returnChannelListCount(ChannelSC criteria) throws DAOException;

    public List returnChannelList(ChannelSC criteria) throws DAOException;

    /**
     * @param channelSC
     * @return
     */
    public ChannelCO returnChannelDetails(ChannelSC channelSC) throws DAOException;

    /**
     * @param channelSC
     * @return
     */
    public ChannelCO deleteChannelDetails(ChannelSC channelSC) throws DAOException;

    /**
     * return Machine Id list Count
     * @param sc
     * @return
     * @throws BaseException
     */
    public int returnMachineIdListCount(ChannelSC sc) throws DAOException;
    
    /**
     * return Machine Id List
     * @param sc
     * @return
     * @throws BaseException
     */
    public List<ChannelCO> returnMachineIdList(ChannelSC sc) throws DAOException;
    
    /**
     * delete All Machine Id
     * @param channelCO
     * @throws DAOException
     */
    public void deleteAllMachineId(ChannelCO channelCO) throws DAOException;
    
    /**
     * delete All TemplateId
     * @param channelCO
     * @throws DAOException
     */
    public void deleteAllTemplateId(ChannelCO channelCO) throws DAOException;
    
    /**
     * return HashKey
     * @param sc
     * @throws DAOException
     */
    public String returnHashKey(ChannelSC sc) throws DAOException;
    
    /**
     * return TempId List Count
     * @param sc
     * @return
     * @throws BaseException
     */
    public int returnTempIdListCount(ChannelSC sc) throws DAOException;

    /**
     * return TempId List
     * @param sc
     * @return
     * @throws BaseException
     */
    public List<GTW_PWS_TMPLT_MASTERVO> returnTempIdList(ChannelSC sc) throws DAOException;
    
    /**
     * load Assigned TemplateId ListGrid
     * @param sc
     * @return
     * @throws BaseException
     */
    public List<GTW_PWS_TMPLT_USRVO> loadAssignedTemplateIdListGrid(ChannelSC sc) throws DAOException;
    
    /**
     * 
     * @param channelSC
     * @return
     * @throws DAOException
     */
    int validateUserId(ChannelSC channelSC) throws DAOException;
    
    /**
     * insert OPT
     * @param channelCO
     * @throws DAOException
     */
	public BigDecimal returnLatestDisplayOrder(OPTVO optvo) throws DAOException;

}