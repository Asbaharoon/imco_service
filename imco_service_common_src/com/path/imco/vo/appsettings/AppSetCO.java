package com.path.imco.vo.appsettings;

import java.util.ArrayList;
import java.util.List;

import com.path.dbmaps.vo.SYNC_INT_LEVELSVO;
import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code
 * 
 * AppSetCO.java used to
 */
public class AppSetCO extends BaseVO
{
    SYNC_INT_LEVELSVO syncintlevelVO = new SYNC_INT_LEVELSVO();
    String appsetGroupGridListString;
    private List<AppSetCO> appGroupGridList = new ArrayList<>();

    /**
     * @return the syncintlevelVO
     */
    public SYNC_INT_LEVELSVO getSyncintlevelVO()
    {
	return syncintlevelVO;
    }

    /**
     * @param syncintlevelVO the syncintlevelVO to set
     */
    public void setSyncintlevelVO(SYNC_INT_LEVELSVO syncintlevelVO)
    {
	this.syncintlevelVO = syncintlevelVO;
    }

    /**
     * @return the appsetGroupGridListString
     */
    public String getAppsetGroupGridListString()
    {
	return appsetGroupGridListString;
    }

    /**
     * @param appsetGroupGridListString the appsetGroupGridListString to set
     */
    public void setAppsetGroupGridListString(String appsetGroupGridListString)
    {
	this.appsetGroupGridListString = appsetGroupGridListString;
    }

    /**
     * @return the appGroupGridList
     */
    public List<AppSetCO> getAppGroupGridList()
    {
	return appGroupGridList;
    }

    /**
     * @param appGroupGridList the appGroupGridList to set
     */
    public void setAppGroupGridList(List<AppSetCO> appGroupGridList)
    {
	this.appGroupGridList = appGroupGridList;
    }

    /**
     * @return the appGroupGridList
     */


}
