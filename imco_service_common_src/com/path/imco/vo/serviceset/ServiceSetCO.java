package com.path.imco.vo.serviceset;

import java.util.ArrayList;
import java.util.List;

import com.path.dbmaps.vo.SYNC_CONNECTIONVO;
import com.path.lib.vo.BaseVO;
/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * ServiceSetCO.java used to
 */
public class ServiceSetCO extends BaseVO
{
   private SYNC_CONNECTIONVO syncConnVO = new SYNC_CONNECTIONVO();
   private String processName;
   private String subDescription;
  private String serviceGridListString;
  private List<ServiceSetCO> serviceGridList = new ArrayList<>();

public SYNC_CONNECTIONVO getSyncConnVO()
{
    return syncConnVO;
}

public void setSyncConnVO(SYNC_CONNECTIONVO syncConnVO)
{
    this.syncConnVO = syncConnVO;
}

public String getProcessName()
{
    return processName;
}

public void setProcessName(String processName)
{
    this.processName = processName;
}

public String getSubDescription()
{
    return subDescription;
}

public void setSubDescription(String subDescription)
{
    this.subDescription = subDescription;
}

public String getServiceGridListString()
{
    return serviceGridListString;
}

public void setServiceGridListString(String serviceGridListString)
{
    this.serviceGridListString = serviceGridListString;
}

public List<ServiceSetCO> getServiceGridList()
{
    return serviceGridList;
}

public void setServiceGridList(List<ServiceSetCO> serviceGridList)
{
    this.serviceGridList = serviceGridList;
}

}
