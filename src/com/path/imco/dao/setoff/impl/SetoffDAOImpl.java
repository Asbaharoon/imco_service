package com.path.imco.dao.setoff.impl;

import java.util.List;

import com.path.imco.dao.setoff.SetoffDAO;
import com.path.imco.vo.setoff.SetoffCO;
import com.path.imco.vo.setoff.SetoffSC;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SetoffDAOImpl.java used to
 */
public class SetoffDAOImpl extends BaseDAO implements SetoffDAO
{

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.setoff.SetoffDAO#returnSetOffList(com.path.imco.vo.
     * setoff.SetoffSC)
     */

    @Override
    public List returnSetOffList(SetoffSC criteria) throws DAOException
    {

	DAOHelper.fixGridMaps(criteria, getSqlMap(), "setoffMapper.resSetoffListMap");
	if(criteria.getNbRec() == -1)
	{
	    return getSqlMap().queryForList("setoffMapper.returnSetoffList", criteria);
	}
	else
	{
	    return getSqlMap().queryForList("setoffMapper.returnSetoffList", criteria, criteria.getRecToskip(),
		    criteria.getNbRec());
	}

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.setoff.SetoffDAO#returnSetOffListCount(com.path.imco.vo
     * .setoff.SetoffSC)
     */
    @Override
    public int returnSetOffListCount(SetoffSC criteria) throws DAOException
    {
	DAOHelper.fixGridMaps(criteria, getSqlMap(), "setoffMapper.resSetoffListMap");
	return ((Integer) getSqlMap().queryForObject("setoffMapper.returnSetoffListCount", criteria)).intValue();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.setoff.SetoffDAO#returnActionsList(com.path.imco.vo.
     * setoff.SetoffSC)
     */
    @Override
    public List<SetoffCO> returnActionList(SetoffSC criteria) throws DAOException
    {
	return getSqlMap().queryForList("setoffMapper.returnAction", criteria);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * setoff.SetoffSC)
     */
    @Override
    public int deleteFromSetoff(SetoffSC criteria) throws DAOException
    {
	return getSqlMap().delete("setoffMapper.deleteFromSetoff", criteria);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.dao.setoff.SetoffDAO#returnNetworkList(com.path.imco.vo.
     * setoff.SetoffSC)
     */

}
