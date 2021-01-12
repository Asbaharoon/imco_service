package com.path.imco.bo.channel;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * ReportQueryConstant.java used to define Constant variables
 */
public class ChannelConstant
{
    public static BigDecimal LOV_LK_TYPE = new BigDecimal(1301);
    public static BigDecimal LOV_TYPE_STATUS = new BigDecimal(1305);
    public static final String STATUS_NEW_CREADTED = "C";
    public static final String STATUS_ACTIVE = "A";
    public static final String STATUS_MODIFIED = "M";
    public static final String STATUS_DELETED = "D";
    public static final String STATUS_APPROVED = "P";
    public static final String STATUS_SUSPENDED = "S";
    public static final String STATUS_REACTIVATE = "RA";
    public static final String IV_CRUD_UPDATE_AFTER_APPROVE = "UP";
    
    
    public static final String CHANNEL_PARENT_REF = "ROOT";
    public static final String CHANNEL_PROG_REF = "CHNL";
    public static final String CHANNEL_CTRL_PROG_REF = "CHNLCTRL";
    public static final String CHANNEL_CTRL_TITLE = "Channel Control";
    public static final String CHANNEL_CTRL_URL = "/path/channel/ChannelMaintAction_loadChannelPage?iv_crud=R";
    
    public static final String CHANNEL_MONITOR_PROG_REF = "CHNLMNTR";
    public static final String CHANNEL_MONITOR_TITLE = "Channel Monitor";
    public static final String CHANNEL_MONITOR_URL = "/path/channel/ChannelMaintAction_loadChannelPage?iv_crud=R";
    
    public static final String MAINTENANCE_IVCRUD = "R";
    
    
    public static final String HTTP_CLIENT = "HC";
    public static final String HTTP_SERVER = "HS";
    public static final String TCP_CLIENT = "TC";
    public static final String TCP_SERVER = "TS";
    public static final String TCP  = "T";
    public static final String HTTP = "H";

    public static final List<String> channelStatusLst = new ArrayList<String>();
    static
    {
	channelStatusLst.add("CREATED_BY," + STATUS_ACTIVE + ",CREATED_DATE,SERVER_CREATED_DATE");
	channelStatusLst.add("MODIFIED_BY," + STATUS_MODIFIED + ",MODIFIED_DATE,SERVER_MODIFIED_DATE");
	channelStatusLst.add("DELETED_BY," + STATUS_DELETED + ",DELETED_DATE,SERVER_DELETED_DATE");
	channelStatusLst.add("APPROVED_BY," + STATUS_APPROVED + ",APPROVED_DATE,SERVER_APPROVED_DATE");
	channelStatusLst.add("SUSPENDED_BY," + STATUS_SUSPENDED + ",SUSPENDED_DATE,SERVER_SUSPENDED_DATE");
	channelStatusLst.add("REACTIVATED_BY," + STATUS_REACTIVATE + ",REACTIVATED_DATE,SERVER_REACTIVATED_DATE");
    }
}
