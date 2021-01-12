package com.path.imco.bo.integrationsettings.impl;

import java.util.List;

import com.path.bo.common.MessageCodes;
import com.path.dbmaps.vo.SYNC_BRANCHVO;
import com.path.dbmaps.vo.SYNC_BRANCHVOKey;
import com.path.imco.bo.integrationsettings.IntegrationEventMgmtBO;
//import com.path.imco.dao.integrationsettings.impl.maps.IntegrationEventMgmtDAO;
import com.path.imco.vo.integrationsettings.IntegrationSettingsCO;
import com.path.imco.vo.integrationsettings.IntegrationSettingsSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;

public class IntegrationEventMgmtBOImpl extends BaseBO implements IntegrationEventMgmtBO
{
    
   // private IntegrationEventMgmtDAO integrationEventMgmtDAO;
    IntegrationSettingsCO integrationSettingsCO = new IntegrationSettingsCO();
    @Override
    public IntegrationSettingsCO onChangeCode(IntegrationSettingsCO integrationSettingsCO) throws BaseException
    {
	SYNC_BRANCHVOKey syncbranchVOKey = new SYNC_BRANCHVOKey();
	
	syncbranchVOKey.setBR_CODE(integrationSettingsCO.getSync_branchVO().getBR_CODE());
	
	SYNC_BRANCHVO syncbranchVO = (SYNC_BRANCHVO) genericDAO.selectByPK(syncbranchVOKey);

	if(syncbranchVO == null)
	    {
		throw new BOException(MessageCodes.iis_invalid_code,
			new String[] { "There_is_no_BR_CODE_is_present_in_this_key" }, false);
	    }
	integrationSettingsCO.setSync_branchVO(syncbranchVO);
	return integrationSettingsCO;
}
  

    }
    	
