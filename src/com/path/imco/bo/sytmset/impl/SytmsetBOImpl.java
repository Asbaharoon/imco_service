package com.path.imco.bo.sytmset.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.List;

import com.path.bo.common.ConstantsCommon;
import com.path.dbmaps.vo.SYNC_BRANCHVO;
import com.path.imco.bo.sytmset.SytmsetBO;
import com.path.imco.dao.sytmset.SytmsetDAO;
import com.path.imco.vo.sytmset.SytmsetCO;
import com.path.imco.vo.sytmset.SytmsetSC;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.NumberUtil;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * SytmsetBOImpl.java used to
 */
public class SytmsetBOImpl extends BaseBO implements SytmsetBO
{
    SytmsetDAO sytmsetDAO;

    /**
     * Method used to return Count of Sytmset
     *
     * @param criteria Search Criteria Parameter
     * @return int Result number of Records
     * @throws BaseException
     */
    @Override
    public int returnSytmsetListCount(SytmsetSC criteria) throws BaseException
    {
	return sytmsetDAO.returnSytmsetListCount(criteria);
    }

    /**
     * Method used to return List of Sytmset
     *
     * @param criteria Search Criteria Parameter
     * @return List Result
     * @throws BaseException
     */
    @Override
    public List returnSytmsetList(SytmsetSC criteria) throws BaseException
    {
	return sytmsetDAO.returnSytmsetList(criteria);
    }

    public SytmsetDAO getSytmsetDAO()
    {
	return sytmsetDAO;
    }

    public void setSytmsetDAO(SytmsetDAO sytmsetDAO)
    {
	this.sytmsetDAO = sytmsetDAO;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.bo.sytmset.SytmsetBO#returnSytmsetDetails(com.path.imco.vo.
     * sytmset.SytmsetCO)
     */
    @Override
    public SytmsetCO returnSytmsetDetails(SytmsetCO sytmsetCO) throws BaseException
    {
	if(sytmsetCO != null && sytmsetCO.getSyncBranchVO() != null
		&& !NumberUtil.isEmptyDecimal(sytmsetCO.getSyncBranchVO().getBR_CODE()))
	{
	    SytmsetSC SytmsetSC = new SytmsetSC();
	    SytmsetSC.setSytmCode(sytmsetCO.getSyncBranchVO().getBR_CODE());
	    return sytmsetDAO.returnSytmsetDetails(SytmsetSC);

	}

	return sytmsetCO;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.path.imco.bo.sytmset.SytmsetBO#onChangeSytmID(com.path.imco.vo.
     * sytmset.SytmsetCO)
     */

    /*
     * (non-Javadoc)
     *
     * @see com.path.imco.bo.sytmset.SytmsetBO#saveNew(com.path.imco.vo.newapi.
     * NewApiCO)
     */
    @Override
    public SytmsetCO saveNew(SytmsetCO sytmsetCO) throws BaseException
    {
	SYNC_BRANCHVO syncBranchVO = sytmsetCO.getSyncBranchVO();
	if(NumberUtil.isEmptyDecimal(syncBranchVO.getBR_CODE()))
	{
	    throw new BaseException("please_specify_system_code_key");
	}


	if(ConstantsCommon.YES.equals(sytmsetCO.getUpdateMode()))
	{
	    genericDAO.update(syncBranchVO);
	    SytmsetCO auditObj = (SytmsetCO) sytmsetCO.getAuditObj();
	    auditBO.callAudit(auditObj.getSyncBranchVO(), syncBranchVO, sytmsetCO.getAuditRefCO());
	}
	else
	{
	    genericDAO.insert(syncBranchVO);
	    auditBO.callAudit(null, syncBranchVO, sytmsetCO.getAuditRefCO());
	}


	auditBO.insertAudit(sytmsetCO.getAuditRefCO());
	return sytmsetCO;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.path.imco.bo.sytmset.SytmsetBO#deleteApi(com.path.imco.vo.newapi.
     * NewApiCO)
     */
    @Override
    public SytmsetCO deleteSytmset(SytmsetCO sytmsetCO) throws BaseException
    {
	SYNC_BRANCHVO syncBranchVO = sytmsetCO.getSyncBranchVO();
	SytmsetSC SytmsetSC = new SytmsetSC();
	int i = genericDAO.delete(syncBranchVO);
	return sytmsetCO;
    }

    @Override
    public SytmsetCO pingNetwork(SytmsetCO sytmsetCO) throws BaseException, IOException
    {
	SytmsetSC criteria = new SytmsetSC();

	if(NumberUtil.isEmptyDecimal(sytmsetCO.getSyncBranchVO().getBR_CODE()))
	{
	    // throw
	}
	criteria.setBranchCode(sytmsetCO.getSyncBranchVO().getBR_CODE());
	SytmsetCO sytmsetCOFromDB = sytmsetDAO.returnNetworkList(criteria);
	if(sytmsetCOFromDB!=null)
	{
	    String ipAddress = sytmsetCOFromDB.getSyncBranchVO().getIP_ADDRESS();

	    InetAddress inet;

	    inet = InetAddress.getByName(ipAddress);
	    boolean status = inet.isReachable(5000); // Timeout = 5000 milli
	    // seconds

	    if(status)
	    {
		sytmsetCO.setPingStatus("true");
		System.out.println("host is reachable_key");
		// throw new BOException("Status : Host is reachable");
	    }
	    else
	    {
		sytmsetCO.setPingStatus("fales");
		System.out.println("host_is_not_reachable_key");
		// throw new BOException("Status : Host is not reachable");
	    }

	}

	return sytmsetCO;
    }

    @Override
    public SytmsetCO pingDatabase(SytmsetCO sytmsetCO) throws BaseException
    {

	try
	{
	    // Connection connection = null;
	    Class.forName("oracle.jdbc.driver.OracleDriver");

	    // SELECT SYNC_BRANCH.IP_ADDRESS,
	    // SYNC_BRANCH.SERVICE_NAME
	    // FROM SYNC_BRANCH
	    // WHERE SYNC_BRANCH.BR_CODE = :li_branch
	    SytmsetSC criteria = new SytmsetSC();

	    if(NumberUtil.isEmptyDecimal(sytmsetCO.getSyncBranchVO().getBR_CODE()))
	    {
		// throw
	    }
	    criteria.setBranchCode(sytmsetCO.getSyncBranchVO().getBR_CODE());
	    SytmsetCO sytmsetCOFromDB = sytmsetDAO.returnNetworkList(criteria);
	    if(sytmsetCOFromDB != null)
	    {
		String ipPort = sytmsetCOFromDB.getSyncBranchVO().getIP_ADDRESS();
		String sid = sytmsetCOFromDB.getSyncBranchVO().getSERVICE_NAME();

		// String ipPort = "192.168.128.21:1521";
		// String sid = "ORA11CS2";
		String url = "jdbc:oracle:thin:@" + ipPort + ":" + sid;
		Connection connection = DriverManager.getConnection(url, "IMAL1401_DEV_O11", "IMAL1401_DEV_O11");
		if(connection == null)
		{
		    sytmsetCO.setPingStatus("false");
		}
		else
		{
		    connection.close();
		    sytmsetCO.setPingStatus("true");
		}

	    }
	}
	catch(Exception e)
	{

	    sytmsetCO.setPingStatus("false");
	}

	return sytmsetCO;

    }

    @Override
    public SytmsetCO pingSendCartridge(SytmsetCO sytmsetCO) throws BaseException
    {
	sytmsetCO.setProcess("S");
	return processValidateCatridge(sytmsetCO);
    }

    @Override
    public SytmsetCO pingReciveCartridge(SytmsetCO sytmsetCO) throws BaseException
    {
	sytmsetCO.setProcess("R");
	return processValidateCatridge(sytmsetCO);
    }

    private SytmsetCO processValidateCatridge(SytmsetCO sytmsetCO) throws BaseException
    {
	SytmsetSC criteria = new SytmsetSC();
	if(NumberUtil.isEmptyDecimal(sytmsetCO.getSyncBranchVO().getBR_CODE()))
	{
	    throw new BOException("invalied_br_code_key");
	}
	criteria.setBrCode(sytmsetCO.getSyncBranchVO().getBR_CODE());
	criteria.setProcess(sytmsetCO.getProcess());
	sytmsetDAO.deleteFromSyncPingho(criteria);

	long timeInMillis = Calendar.getInstance().getTimeInMillis();
	long timeLimit = timeInMillis + 5000l;
	while(timeLimit > timeInMillis)
	{
	    timeInMillis = Calendar.getInstance().getTimeInMillis();
	}

	BigDecimal count = sytmsetDAO.returnCountFromSyncPingho(criteria);

	if(count.compareTo(BigDecimal.ZERO) > 0)
	{
	    sytmsetCO.setPingStatus("true");
	}
	else
	{
	    sytmsetCO.setPingStatus("fales");
	}

	return sytmsetCO;
    }

}
