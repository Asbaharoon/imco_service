package com.path.imco.bo.setoff.impl;

import java.math.BigDecimal;
import java.util.List;

import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.dbmaps.vo.SYNC_ACTIONSVO;
import com.path.imco.bo.setoff.SetoffBO;
import com.path.imco.dao.setoff.SetoffDAO;
import com.path.imco.vo.setoff.SetoffCO;
import com.path.imco.vo.setoff.SetoffSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.StringUtil;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SetoffBOImpl.java used to
 */
public class SetoffBOImpl extends BaseBO implements SetoffBO
{
    SetoffDAO setoffDAO;

    @Override


    public List<SetoffCO> returnSetOffList(SetoffSC criteria) throws BaseException
    {

	List<SetoffCO> returnSetOffList = setoffDAO.returnSetOffList(criteria);
	String rAction, sAction, terminal, userID;
	BigDecimal syncID;
	if(returnSetOffList != null && !returnSetOffList.isEmpty())
	{
	    for(SetoffCO setoffCO : returnSetOffList)
	    {
		rAction = "";
		sAction = "";
		terminal = null;
		userID = null;
		syncID = null;
		if(setoffCO != null && setoffCO.getSyncActionsVO() != null)
		{
		    terminal = setoffCO.getSyncActionsVO().getTERMINAL();
		    userID = setoffCO.getSyncActionsVO().getUSER_ID();
		    syncID = setoffCO.getSyncActionsVO().getSYNC_ID();
		    if(StringUtil.isNotEmpty(terminal) && StringUtil.isNotEmpty(userID)
			    && !NumberUtil.isEmptyDecimal(syncID))
		    {
			criteria.setUserId(userID);
			criteria.setTerminal(terminal);
			criteria.setSyncId(setoffCO.getSyncActionsVO().getSYNC_ID());

			List<SetoffCO> actionList = setoffDAO.returnActionList(criteria);
			if(actionList != null && !actionList.isEmpty())
			{
			    for(SetoffCO setoffCOLocal : actionList)
			    {
				if("R".equals(setoffCOLocal.getSyncActionsVO().getPROCESS()))
				{

				    rAction = rAction.concat(setoffCOLocal.getSyncActionsVO().getACTION()).concat(",");
				    rAction = rAction.replaceAll(",$", "");
				}
				else
				{
				    sAction = sAction.concat(setoffCOLocal.getSyncActionsVO().getACTION()).concat(",");
				    sAction = sAction.replaceAll(",$", "");
				}
			    }
			}
		    }
		}
		setoffCO.setrActions(rAction);
		setoffCO.setsActions(sAction);

	    }
	}
	return returnSetOffList;
    }

    public SetoffDAO getSetoffDAO()
    {
	return setoffDAO;
    }

    public void setSetoffDAO(SetoffDAO setoffDAO)
    {
	this.setoffDAO = setoffDAO;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.bo.setoff.SetoffBO#returnSetOffListCount(com.path.imco.vo.
     * setoff.SetoffSC)
     */
    @Override
    public int returnSetOffListCount(SetoffSC criteria) throws BaseException
    {
	return setoffDAO.returnSetOffListCount(criteria);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.bo.setoff.SetoffBO#setoff(com.path.imco.vo.setoff.SetoffCO)
     */
    @Override
    public SetoffCO setoff(SetoffCO setoffCO) throws BaseException
    {
	SYNC_ACTIONSVO syncActionsVO = setoffCO.getSyncActionsVO();
	int d = 0;

	for(SetoffCO seToffCO : setoffCO.getSetoffGridList())
	{

	    if(ConstantsCommon.YES.equals(seToffCO.getSetoffchck()))
	    {

		d++;

	    }
	}
	if(d == 0)
	{

	    throw new BOException(MessageCodes.iis_invalid_code,
		    new String[] { "no_users_selected_please_select_at_least_one_user_key" },
		    false);
	}


	for(SetoffCO seToffCO : setoffCO.getSetoffGridList())
	{
	    if(ConstantsCommon.YES.equals(seToffCO.getSetoffchck()))
	    {
		SetoffSC criteria = new SetoffSC();
		criteria.setSyncId(seToffCO.getSyncActionsVO().getSYNC_ID());
		criteria.setTerminal(seToffCO.getSyncActionsVO().getTERMINAL());
		criteria.setUserId(seToffCO.getSyncActionsVO().getUSER_ID());



		setoffDAO.deleteFromSetoff(criteria);
	    }
	}
	return setoffCO;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.path.imco.bo.setoff.SetoffBO#refresh(com.path.imco.vo.setoff.
     * SetoffCO)
     */
    @Override
    public SetoffCO refresh(SetoffCO setoffCO) throws BaseException
    {

	return setoffCO;
    }

}
