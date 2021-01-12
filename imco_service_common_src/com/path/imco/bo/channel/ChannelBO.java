package com.path.imco.bo.channel;

import java.util.ArrayList;
import java.util.List;

import com.path.dbmaps.vo.GTW_PWS_TMPLT_MASTERVO;
import com.path.dbmaps.vo.GTW_PWS_TMPLT_USRVO;
import com.path.imco.vo.channel.ChannelCO;
import com.path.imco.vo.channel.ChannelSC;
import com.path.lib.common.exception.BaseException;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * ChannelBO.java used to
 */
public interface ChannelBO
{
    public int returnChannelListCount(ChannelSC criteria) throws BaseException;
    public List returnChannelList(ChannelSC criteria) throws BaseException;


    public ChannelCO saveNew(ChannelCO channelCO,ArrayList<GTW_PWS_TMPLT_MASTERVO> lstMultiselect) throws BaseException;

    /**
     * @param channelCO
     * @return
     */
    public ChannelCO returnChannelDetails(ChannelSC sc) throws BaseException;

    /**
     * @param channelCO
     * @return
     */
    public void deleteChannel(ChannelCO channelCO) throws BaseException;

    /**
     * @param channelCO
     * @return
     */
    public ChannelCO update(ChannelCO channelCO) throws BaseException;

    /**
     * @param channelCO
     * @return
     */
    public ChannelCO onChangeChannelID(ChannelCO channelCO) throws BaseException;
    
    /**
     * on Change UserID
     * @param channelCO
     * @return
     * @throws BaseException
     */
    public ChannelCO onChangeUserID(ChannelCO channelCO) throws BaseException;

    /**
     * @param channelCO
     * @return
     */
    public ChannelCO applySysParamSettings(ChannelCO channelCO);
    
    /**
     * chanel Validate Usr and Pwd
     * @param channelCO
     * @return
     */
    public Integer chanelCheckUsrPwd(ChannelCO channelCO) throws BaseException;
    
    /**
     * generate Key
     * @param channelCO
     * @return
     * @throws BaseException
     */
    public String generateKey(ChannelCO channelCO) throws BaseException;
    
    /**
     * return Machine Id list Count
     * @param sc
     * @return
     * @throws BaseException
     */
    public int returnMachineIdListCount(ChannelSC sc) throws BaseException;
    
    /**
     * return Machine Id List
     * @param sc
     * @return
     * @throws BaseException
     */
    public List<ChannelCO> returnMachineIdList(ChannelSC sc) throws BaseException;
    
    /**
     * load Assigned TemplateId ListGrid
     * @param sc
     * @return
     * @throws BaseException
     */
    public List<GTW_PWS_TMPLT_USRVO> loadAssignedTemplateIdListGrid(ChannelSC sc) throws BaseException;
    
    /**
     * save Channel Machine Ids
     * @param channelCO
     * @param lstMod
     * @throws BaseException
     */
    public void saveChannelMachineIds(ChannelCO channelCO,ArrayList lstMod) throws BaseException;
    
    /**
     * return HashKey
     * @param channelCO
     * @return
     * @throws BaseException
     */
    public String returnHashKey(ChannelCO channelCO) throws BaseException;
    
    /**
     * approve ChannelId
     * @param channelCO
     * @throws BaseException
     */
    public void approveChannelId(ChannelCO channelCO) throws BaseException;
    
    /**
     * suspend ChannelId
     * @param channelCO
     * @throws BaseException
     */
    public void suspendChannelId(ChannelCO channelCO) throws BaseException;
    
    /**
     * reactivate ChannelId
     * @param channelCO
     * @throws BaseException
     */
    public void reactivateChannelId(ChannelCO channelCO) throws BaseException;
    
    /**
     * return TempId List Count
     * @param sc
     * @return
     * @throws BaseException
     */
    public int returnTempIdListCount(ChannelSC sc) throws BaseException;

    /**
     * return TempId List
     * @param sc
     * @return
     * @throws BaseException
     */
    public List<GTW_PWS_TMPLT_MASTERVO> returnTempIdList(ChannelSC sc) throws BaseException;

    int validateUserId(ChannelSC channelSC) throws BaseException ;

}