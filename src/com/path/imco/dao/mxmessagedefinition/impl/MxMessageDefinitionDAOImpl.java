package com.path.imco.dao.mxmessagedefinition.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.path.bo.common.ConstantsCommon;
import com.path.dbmaps.vo.DF_DATA_IMPORT_TMPVO;
import com.path.dbmaps.vo.DGTL_MSG_DEFVO;
import com.path.dbmaps.vo.DGTL_XML_MSG_TAGSVO;
import com.path.imco.dao.mxmessagedefinition.MxMessageDefinitionDAO;
import com.path.imco.vo.mxmessagedefinition.MxMessageDefinitionCO;
import com.path.imco.vo.mxmessagedefinition.MxMessageDefinitionSC;
import com.path.imco.vo.mxmessagedefinition.TagsCo;
import com.path.lib.common.base.BaseDAO;
import com.path.lib.common.exception.DAOException;
import com.path.lib.common.util.DAOHelper;

/**
 * 
 * Copyright 2020, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * MxMessageDefinitionDAOImpl.java used to
 */
public class MxMessageDefinitionDAOImpl extends BaseDAO implements MxMessageDefinitionDAO 
{
	
	@Override
	public List returnMxMessageDefinitionList(MxMessageDefinitionSC criteria) throws DAOException 
	{
		if(criteria.getSortOrder() == null)
		{
		    criteria.setSortOrder(" ORDER BY DGTL_MSG_DEF_ID DESC");
		}
		
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "mxMessageDefinitionMapper.mxMessageDefinitionListMap");
		if (criteria.getNbRec() == -1) 
		{
			return getSqlMap().queryForList("mxMessageDefinitionMapper.returnMxMessageDefinitionList", criteria);
		} else 
		{
			return getSqlMap().queryForList("mxMessageDefinitionMapper.returnMxMessageDefinitionList", criteria,
					criteria.getRecToskip(), criteria.getNbRec());
		}
	}

	@Override
	public int returnMxMessageDefinitionListCount(MxMessageDefinitionSC criteria) throws DAOException {
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "mxMessageDefinitionMapper.mxMessageDefinitionListMap");
		return ((Integer) getSqlMap().queryForObject("mxMessageDefinitionMapper.returnMxMessageDefinitionListCount",
				criteria)).intValue();
	}

	@Override
	public MxMessageDefinitionCO returnMxMessageDefinitionDetails(MxMessageDefinitionSC criteria) throws DAOException {
		return (MxMessageDefinitionCO) getSqlMap()
				.queryForObject("mxMessageDefinitionMapper.returnMxMessageDefinitionDetails", criteria);
	}

	@Override
	public List returnMxMessageDefinitionTagsList(MxMessageDefinitionSC criteria) throws DAOException {
		DAOHelper.fixGridMaps(criteria, getSqlMap(), "mxMessageDefinitionMapper.mxMessageDefTagsListMap");
		return getSqlMap().queryForList("mxMessageDefinitionMapper.returnMxMessageDefinitionTagsList", criteria);
	}
	
	@Override
    public int deleteMxMessageDefinitionTags(BigDecimal mxMessageDefId) throws DAOException
    {
		return (Integer) getSqlMap().delete("mxMessageDefinitionMapper.deleteMxMessageDefinitionTagsSQL", mxMessageDefId);
    }
	
    @Override
    public int validateMxMsgDefByCode(DGTL_MSG_DEFVO dgtl_MSG_DEFVO) throws DAOException
    {
    	return ((Integer) getSqlMap().queryForObject("mxMessageDefinitionMapper.validateMxMsgDefByCode", dgtl_MSG_DEFVO)).intValue();
    }
    
    @Override
    public int insertDGTL_XML_MSG_TAGS(MxMessageDefinitionCO mxMessageDefinitionCO) throws DAOException
    {
    	SqlSession sqlSess = null;
    	int rows = 0;
    	try
    	{
    		
    	    long start = Calendar.getInstance().getTimeInMillis();
    	    sqlSess = getSqlMap().returnBatchSession(null);
    	    sqlSess.flushStatements();
    	    for(int i = 0; i < mxMessageDefinitionCO.getAllTagsCos().size(); i++)
    	    {
	    		TagsCo co = mxMessageDefinitionCO.getAllTagsCos().get(i);
	    		co.setDgtlMsgDefId(mxMessageDefinitionCO.getDgtlMsgDefId());
	    		co.setIsOracle(ConstantsCommon.CURR_DBMS_ORACLE);
	    		co.setIsSybase(ConstantsCommon.CURR_DBMS_SYBASE);
	    		rows += sqlSess.insert("mxMessageDefinitionMapper.insertDGTL_XML_MSG_TAGS1", co);
    	    }
    	    long end = Calendar.getInstance().getTimeInMillis();
    	    System.out.println(" Time take for looping " + mxMessageDefinitionCO.getAllTagsCos().size() + " took= " + (end - start)
    			    + "Ms");
    		    sqlSess.commit();
    		    end = Calendar.getInstance().getTimeInMillis();
    		    System.out.println(" Time take for insert " + mxMessageDefinitionCO.getAllTagsCos().size() + " took= " + (end - start)
    			    + "Ms");

    	}
    	catch(Exception e)
    	{
    	    log.error(e, "Error insertDynFilesTempData");
    	    throw new DAOException("Error insertDynFilesTempData" + e.getMessage(), e);
    	}
    	finally
    	{
    	    if(sqlSess != null)
    	    {
    		sqlSess.close();
    	    }
    	}
    	
    	return rows;
    //	return (Integer) getSqlMap().insert("mxMessageDefinitionMapper.insertDGTL_XML_MSG_TAGS", mxMessageDefinitionCO);
    }
    
    @Override
    public void updateDGTL_XML_MSG_TAGS(DGTL_XML_MSG_TAGSVO xml_MSG_TAGSVO) throws DAOException
    {
    	getSqlMap().queryForObject("mxMessageDefinitionMapper.updateDGTL_XML_MSG_TAGS", xml_MSG_TAGSVO);
    }
}
