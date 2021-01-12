package com.path.atm.bo.atminterface.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.path.atm.bo.atminterface.AtmInterfaceBO;
import com.path.atm.bo.atminterface.AtmInterfaceConstants;
import com.path.atm.bo.common.AtmCommonConstants;
import com.path.atm.dao.atminterface.AtmInterfaceDAO;
import com.path.atm.netty.iso.PathCustomFieldHandler;
import com.path.atm.netty.isoutils.BaseConvertor;
import com.path.atm.vo.atminterface.AtmInterfaceCO;
import com.path.atm.vo.atminterface.AtmInterfaceSC;
import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.dbmaps.vo.AXSVO;
import com.path.dbmaps.vo.DGTL_ATM_INTERFACESVO;
import com.path.dbmaps.vo.DGTL_ISO_FLDSVO;
import com.path.dbmaps.vo.DGTL_ISO_MSGSVO;
import com.path.dbmaps.vo.DGTL_ISO_SUB_FLDSVO;
import com.path.dbmaps.vo.OPTVO;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.struts2.json.PathJSONUtil;
import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.IsoValue;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.AlphaParseInfo;
import com.solab.iso8583.parse.AmountParseInfo;
import com.solab.iso8583.parse.Date10ParseInfo;
import com.solab.iso8583.parse.Date4ParseInfo;
import com.solab.iso8583.parse.DateExpParseInfo;
import com.solab.iso8583.parse.FieldParseInfo;
import com.solab.iso8583.parse.LllvarParseInfo;
import com.solab.iso8583.parse.NumericParseInfo;
import com.solab.iso8583.parse.TimeParseInfo;

/**
 * 
 * Copyright 2013, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * IsoMessageBOImpl.java used to
 */
public class AtmInterfaceBOImpl extends BaseBO implements AtmInterfaceBO
{
	private AtmInterfaceDAO atmInterfaceDAO;

	public void setAtmInterfaceDAO(AtmInterfaceDAO atmInterfaceDAO)
	{
		this.atmInterfaceDAO = atmInterfaceDAO;
	}

	@Override
	public List retIsoFieldsList(AtmInterfaceSC criteria) throws BaseException
	{
		return atmInterfaceDAO.retIsoFieldsList(criteria);
	}

	@Override
	public int retIsoFieldsListCount(AtmInterfaceSC criteria) throws BaseException
	{
		return atmInterfaceDAO.retIsoFieldsListCount(criteria);
	}

	@Override
	public AtmInterfaceCO saveUpdateATMInterface(AtmInterfaceCO isoInterfaceCO, HashMap<String, Object> gridsDataMap, Map<Integer, List> subGirdsDataMap) throws BaseException
	{
		List messageListAdd    =  (ArrayList<AtmInterfaceCO>) gridsDataMap.get("messageAdd");
		List messageListUpdate =  (ArrayList<AtmInterfaceCO>) gridsDataMap.get("messageModify"); 
		List messageListDelete =  (ArrayList<AtmInterfaceCO>) gridsDataMap.get("messageDelete");
		List fieldsListAdd    =  (ArrayList<AtmInterfaceCO>) gridsDataMap.get("fieldAdd");
		
		AtmInterfaceCO co = null;
		DGTL_ISO_FLDSVO fldsvo = null;
		DGTL_ISO_MSGSVO msgsvo = null;
		AtmInterfaceSC atmInterfaceSC = new AtmInterfaceSC();
		
		if(commonLibBO.returnIsSybase() == 1)
		{
			atmInterfaceSC.setIsOracle(0);
			atmInterfaceSC.setIsSybase(1);
		}
		else
		{ 
			atmInterfaceSC.setIsOracle(1);
			atmInterfaceSC.setIsSybase(0);
		}
		
		DGTL_ATM_INTERFACESVO iso_INTERFACESVO = isoInterfaceCO.getIso_INTERFACESVO();
		iso_INTERFACESVO.setCOMP_CODE(isoInterfaceCO.getCompCode());
		/**Insert New Records*/
		if(iso_INTERFACESVO.getCODE()==null || ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(iso_INTERFACESVO.getCODE()))
		{
			/**Insert Interface*/
			BigDecimal interfaceId = new BigDecimal(atmInterfaceDAO.retNewInterfaceId(atmInterfaceSC));
			iso_INTERFACESVO.setCODE(interfaceId);
			iso_INTERFACESVO.setCREATED_BY(isoInterfaceCO.getUserId());
			iso_INTERFACESVO.setCREATED_DATE(commonLibBO.returnSystemDateWithTime());
			iso_INTERFACESVO.setSTATUS(AtmCommonConstants.STATUS_ACTIVE);
			genericDAO.insert(iso_INTERFACESVO);
			
			/**Insert Fields of Interface*/
			for (int i = 0; i < fieldsListAdd.size(); i++) 
			{
				co = (AtmInterfaceCO) fieldsListAdd.get(i);
				fldsvo = co.getIso_INT_FLDSVO();
				fldsvo.setCOMP_CODE(isoInterfaceCO.getCompCode());
				fldsvo.setINT_CODE(interfaceId);
				genericDAO.insert(fldsvo);
			}
			
			/**Insert Messages of Interface*/
			if(messageListAdd != null && !messageListAdd.isEmpty()) 
			{
				for (int i = 0; i < messageListAdd.size(); i++) 
				{
					co = (AtmInterfaceCO) messageListAdd.get(i);
					msgsvo = co.getIso_INT_MSGSVO();
					msgsvo.setMESSAGE_CODE(new BigDecimal(atmInterfaceDAO.retNewMessageCode(atmInterfaceSC)));
					//msgsvo.setHEADER_DATA(isoInterfaceCO.getHEADER());
					msgsvo.setCOMP_CODE(isoInterfaceCO.getCompCode());
					msgsvo.setINT_CODE(interfaceId);
					genericDAO.insert(msgsvo);
				}
			}
		}
		else
		{
			/**Update Interface*/
			iso_INTERFACESVO.setMODIFIED_BY(isoInterfaceCO.getUserId());
			iso_INTERFACESVO.setMODIFIED_DATE(commonLibBO.returnSystemDateWithTime());
			iso_INTERFACESVO.setSTATUS(AtmCommonConstants.STATUS_ACTIVE);
			Integer row= genericDAO.update(iso_INTERFACESVO);
			
			if (row == null || row < 1)
			{
				throw new BOException(MessageCodes.RECORD_CHANGED);
			}
			
			/**Update Fields*/
			for (int i = 0; i < fieldsListAdd.size(); i++) 
			{
				co = (AtmInterfaceCO) fieldsListAdd.get(i);
				fldsvo = co.getIso_INT_FLDSVO();
				fldsvo.setCOMP_CODE(isoInterfaceCO.getCompCode());
				fldsvo.setINT_CODE(iso_INTERFACESVO.getCODE());
				genericDAO.update(fldsvo);
			}
			
			/**New Messages Added*/
			if(messageListAdd != null && !messageListAdd.isEmpty()) 
			{
				for (int i = 0; i < messageListAdd.size(); i++) 
				{
					co = (AtmInterfaceCO) messageListAdd.get(i);
					msgsvo = co.getIso_INT_MSGSVO();
					msgsvo.setMESSAGE_CODE(new BigDecimal(atmInterfaceDAO.retNewMessageCode(atmInterfaceSC)));
					//msgsvo.setHEADER_DATA(isoInterfaceCO.getHEADER());
					msgsvo.setCOMP_CODE(isoInterfaceCO.getCompCode());
					msgsvo.setINT_CODE(iso_INTERFACESVO.getCODE());
					genericDAO.insert(msgsvo);
				}
			}
			
			/**Update Messages*/
			if(messageListUpdate!=null && !messageListUpdate.isEmpty())
			{
				for (int i = 0; i < messageListUpdate.size(); i++) 
				{
					co = (AtmInterfaceCO) messageListUpdate.get(i);
					msgsvo = co.getIso_INT_MSGSVO();
					//msgsvo.setHEADER_DATA(isoInterfaceCO.getHEADER());
					msgsvo.setCOMP_CODE(isoInterfaceCO.getCompCode());
					msgsvo.setINT_CODE(iso_INTERFACESVO.getCODE());
					genericDAO.update(msgsvo);
				}
			}
			
			/**Delete Messages*/
			if(messageListDelete!=null && !messageListDelete.isEmpty())
			{
				for (int i = 0; i < messageListDelete.size(); i++) 
				{
					co = (AtmInterfaceCO) messageListDelete.get(i);
					msgsvo = co.getIso_INT_MSGSVO();
					msgsvo.setCOMP_CODE(isoInterfaceCO.getCompCode());
					msgsvo.setINT_CODE(iso_INTERFACESVO.getCODE());
					genericDAO.delete(msgsvo);
				}
			}
			/**Update Description of OPT*/
			updateOPT(isoInterfaceCO, iso_INTERFACESVO);
		}
		//Save Sub Fields 
		saveSubGridsData(subGirdsDataMap, iso_INTERFACESVO);
		return isoInterfaceCO;
	}
	
	private void saveSubGridsData(Map<Integer, List> subGirdsDataMap, DGTL_ATM_INTERFACESVO iso_INTERFACESVO) throws BaseException
	{
		//Check if Map is not Empty
		if(subGirdsDataMap != null && !subGirdsDataMap.isEmpty())
		{
			List subGridList = null;
			AtmInterfaceCO subGridCO = null;
			DGTL_ISO_SUB_FLDSVO sub_FLDSVO = null;
			//Iterate All subGrids
			for (Integer key : subGirdsDataMap.keySet()) 
			{
				subGridList = new ArrayList();
				subGridList = subGirdsDataMap.get(key);
				//Iterate Each Sub-grid and Add Sub-fields in DB
				for (int i = 0; i < subGridList.size(); i++) 
				{
					subGridCO = (AtmInterfaceCO) subGridList.get(i);
					sub_FLDSVO = subGridCO.getSub_FLDSVO();
					sub_FLDSVO.setINT_CODE(iso_INTERFACESVO.getCODE());
					sub_FLDSVO.setCOMP_CODE(iso_INTERFACESVO.getCOMP_CODE());
					
					if(subGridCO.getSTATUS() != null && AtmCommonConstants.STATUS_DELETED.equals(subGridCO.getSTATUS()))
					{
						genericDAO.delete(sub_FLDSVO);
					}
					else
					{
						int row = genericDAO.update(sub_FLDSVO);
						if(row == 0)
						{
							sub_FLDSVO.setSUB_FLD_CODE(new BigDecimal(i+1));
							genericDAO.insert(sub_FLDSVO);
						}//End If
					}
				}//End List Loop
			}//End foreach
		}//end if
	}
		
	@Override
	public List retInterfaceList(AtmInterfaceSC criteria) throws BaseException
	{
		return this.atmInterfaceDAO.retInterfaceList(criteria);
	}

	@Override
	public int retInterfaceListCount(AtmInterfaceSC criteria) throws BaseException
	{
		return atmInterfaceDAO.retInterfaceListCount(criteria);
	}

	@Override
	public List returnMessageList(AtmInterfaceSC criteria) throws BaseException
	{
		return atmInterfaceDAO.returnMessageList(criteria);
	}

	@Override
	public int returnMessageListCount(AtmInterfaceSC criteria) throws BaseException
	{
		return atmInterfaceDAO.returnMessageListCount(criteria);
	}

	@Override
	public List returnFieldsList(AtmInterfaceSC criteria) throws BaseException
	{
		return atmInterfaceDAO.returnFieldsList(criteria);
	}

	@Override
	public int returnFieldsListCount(AtmInterfaceSC criteria) throws BaseException
	{
		return atmInterfaceDAO.returnFieldsListCount(criteria);
	}

	@Override
	public AtmInterfaceCO returnInterfaceById(AtmInterfaceSC criteria) throws BaseException
	{
		return atmInterfaceDAO.returnInterfaceById(criteria);
	}

	@Override
	public List retIsoSubFieldsList(AtmInterfaceSC criteria) throws BaseException 
	{
		return atmInterfaceDAO.retIsoSubFieldsList(criteria);
	}

	@Override
	public int retIsoSubFieldsListCount(AtmInterfaceSC criteria) throws BaseException 
	{
		return atmInterfaceDAO.retIsoSubFieldsListCount(criteria);
	}

	@Override
	public AtmInterfaceCO deleteInterface(AtmInterfaceCO atmInterfaceCO) throws BaseException 
	{
		DGTL_ATM_INTERFACESVO iso_INTERFACESVO = atmInterfaceCO.getIso_INTERFACESVO();
		iso_INTERFACESVO.setCOMP_CODE(atmInterfaceCO.getCompCode());
		iso_INTERFACESVO.setDELETED_BY(atmInterfaceCO.getUserId());
		iso_INTERFACESVO.setDELETED_DATE(commonLibBO.returnSystemDateWithTime());
		iso_INTERFACESVO.setSTATUS(AtmCommonConstants.STATUS_DELETED);
		
		Integer update = genericDAO.update(iso_INTERFACESVO);
		if(update == null || update < 1)
		{
		    throw new BOException(MessageCodes.RECORD_CHANGED);
		}
		else {
			//Delete OPT and Access
			deleteOPT_OPTEXTENDED_AXS(atmInterfaceCO, iso_INTERFACESVO);
		}
		return atmInterfaceCO;
	}

	@Override
	public AtmInterfaceCO handleStatusProcess(AtmInterfaceCO atmInterfaceCO) throws BaseException 
	{
		DGTL_ATM_INTERFACESVO iso_INTERFACESVO = atmInterfaceCO.getIso_INTERFACESVO();
		iso_INTERFACESVO.setCOMP_CODE(atmInterfaceCO.getCompCode());
		
		if(AtmCommonConstants.STATUS_APPROVED.equals(atmInterfaceCO.getMode()))
		{
			iso_INTERFACESVO.setAPPROVED_BY(atmInterfaceCO.getUserId());
			iso_INTERFACESVO.setAPPROVED_DATE(commonLibBO.returnSystemDateWithTime());
			iso_INTERFACESVO.setSTATUS(AtmCommonConstants.STATUS_APPROVED);
			
			//Insert OPT, OPT_EXTENDED and AXS
			insertOPT_OPTEXTENDED_AXS(atmInterfaceCO, iso_INTERFACESVO);
		}
		else if(AtmCommonConstants.STATUS_SUSPENDED.equals(atmInterfaceCO.getMode()))
		{
			iso_INTERFACESVO.setSUSPENDED_BY(atmInterfaceCO.getUserId());
			iso_INTERFACESVO.setSUSPENDED_DATE(commonLibBO.returnSystemDateWithTime());
			iso_INTERFACESVO.setSTATUS(AtmCommonConstants.STATUS_SUSPENDED);
		}
		else if(AtmCommonConstants.STATUS_REACTIVATE.equals(atmInterfaceCO.getMode()))
		{
			iso_INTERFACESVO.setREACTIVATED_BY(atmInterfaceCO.getUserId());
			iso_INTERFACESVO.setREACTIVATED_DATE(commonLibBO.returnSystemDateWithTime());
			iso_INTERFACESVO.setSTATUS(AtmCommonConstants.STATUS_APPROVED);
		}
		
		Integer update = genericDAO.update(iso_INTERFACESVO);
		if(update == null || update < 1)
		{
		    throw new BOException(MessageCodes.RECORD_CHANGED);
		}
		return atmInterfaceCO;
	}
	
	private void insertOPT_OPTEXTENDED_AXS(AtmInterfaceCO isoInterfaceCO, DGTL_ATM_INTERFACESVO iso_INTERFACESVO) throws BaseException
	{
		/**Insert OPT and AXS*/
		OPTVO optVO = new OPTVO();
		optVO.setAPP_NAME(isoInterfaceCO.getAppName());
		optVO.setPROG_REF(AtmInterfaceConstants.INTERFACE_OPT+iso_INTERFACESVO.getCODE());
		optVO.setPROG_DESC(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setALLOW_ADD_OPTIONS("0");
		optVO.setADD_OPT_REF(AtmInterfaceConstants.INTERFACE_OPT+iso_INTERFACESVO.getCODE());
		optVO.setAUDIT_SAVE("0");
		optVO.setAUDIT_DELETE("0");
		optVO.setAUDIT_RETRIEVE("0");
		optVO.setMAIN_OPTION_1("0");
		optVO.setMAIN_OPTION_2("1");
		optVO.setDYNAMIC_OPT("2");
		optVO.setPROG_DESC_FR(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_ENG(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_ARAB(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_FR(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_FA(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_ARAB(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_FA(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_TK(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_TK(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_RU(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_RU(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPARENT_REF(AtmInterfaceConstants.ATM_INTERFACE_ROOT);
		optVO.setPROG_TYPE("P");
		optVO.setPROG_ORDER(new BigDecimal(atmInterfaceDAO.retProgOrder()));
		optVO.setAPP_VERSION(BigDecimal.ONE);
		optVO.setDISP_ORDER(new BigDecimal(20));
					    
	    AXSVO axsVO = fillAxsVOProps(isoInterfaceCO.getCompCode(), isoInterfaceCO.getBranchCode(), optVO.getAPP_NAME(),
	    		isoInterfaceCO.getUserId(), isoInterfaceCO.getRunningDate(), optVO.getPROG_REF(),
	    		AtmInterfaceConstants.AXS_STATUS_P, AtmInterfaceConstants.AXS_TO_BE_DELETED_N);
	    
	    int row = genericDAO.update(optVO);
		if(row == 0)
		{
			genericDAO.insert(optVO);
			 /**Insert into axs table*/
			genericDAO.insert(axsVO);
		}
	}
	
	private void updateOPT(AtmInterfaceCO isoInterfaceCO, DGTL_ATM_INTERFACESVO iso_INTERFACESVO) throws BaseException
	{
		/**Update OPT */
		OPTVO optVO = new OPTVO();
		optVO.setAPP_NAME(isoInterfaceCO.getAppName());
		optVO.setPROG_REF(AtmInterfaceConstants.INTERFACE_OPT+iso_INTERFACESVO.getCODE());
		optVO.setPROG_DESC(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_FR(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_ENG(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_ARAB(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_FR(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_FA(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_ARAB(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_FA(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_TK(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_TK(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setMENU_TITLE_RU(iso_INTERFACESVO.getDESCRIPTION());
		optVO.setPROG_DESC_RU(iso_INTERFACESVO.getDESCRIPTION());
		
		genericDAO.update(optVO);
	}

	private void deleteOPT_OPTEXTENDED_AXS(AtmInterfaceCO isoInterfaceCO, DGTL_ATM_INTERFACESVO iso_INTERFACESVO) throws BaseException
	{
		/**Delete from and AXS*/
		OPTVO optVO = new OPTVO();
		optVO.setAPP_NAME(isoInterfaceCO.getAppName());
		optVO.setPROG_REF("ATMINT" +iso_INTERFACESVO.getCODE());
			    
	    /** Delete from Access Table */
	    AXSVO axsVO = new AXSVO();
		axsVO.setCOMP_CODE(isoInterfaceCO.getCompCode());
		axsVO.setBRANCH_CODE(isoInterfaceCO.getBranchCode());
		axsVO.setAPP_NAME(isoInterfaceCO.getAppName());
		axsVO.setPROG_REF(AtmInterfaceConstants.INTERFACE_OPT+iso_INTERFACESVO.getCODE());
		axsVO.setUSER_ID(isoInterfaceCO.getUserId());
		genericDAO.delete(axsVO);
		genericDAO.delete(optVO);
	}
	
	/**
     * fill Axs VOProps
     * @param compCode
     * @param branchCode
     * @param appName
     * @param userName
     * @param runningDate
     * @param progRef
     * @param status
     * @param toBeDeleted
     * @return
     * @throws BaseException
     */
    private AXSVO fillAxsVOProps(BigDecimal compCode, BigDecimal branchCode, String appName, String userName,
	    Date runningDate, String progRef, String status, String toBeDeleted) throws BaseException
    {
		AXSVO axsVO = new AXSVO();
		axsVO.setCOMP_CODE(compCode);
		axsVO.setBRANCH_CODE(branchCode);
		axsVO.setAPP_NAME(appName);
		axsVO.setPROG_REF(progRef);
		axsVO.setUSER_ID(userName);
		axsVO.setCREATED_BY(userName);
		axsVO.setAUTHORIZED_BY(userName);
		axsVO.setSTATUS(status);
		axsVO.setDIRECT_ACCESS(userName);
		axsVO.setDATE_CREATED(runningDate);
		axsVO.setDATE_AUTHORIZED(runningDate);
		axsVO.setTO_BE_DELETED(toBeDeleted);
		return axsVO;
    }

    /**Test ISO Message and Return List of Values*/
	@Override
	public List testISOMessage(AtmInterfaceSC criteria) throws BaseException 
	{
		//Initialize bitmaps fields 
		criteria.setBitMapFields(extractFieldsFromBitMaps(criteria));
		//Incase of Empty Fields
		if(criteria.getBitMapFields() == null || criteria.getBitMapFields().isEmpty())
		{
			criteria.getBitMapFields().add(new BigDecimal(-1));
		}
		/** Load available fields configuration from DB*/
		List fieldList = atmInterfaceDAO.retFieldListByBitmap(criteria);
		
		//Parser Configuration Settings Map
		HashMap<Integer, FieldParseInfo> fieldsInfoMap = new HashMap<Integer, FieldParseInfo>();
		MessageFactory<IsoMessage> messageFactory = new MessageFactory<IsoMessage>();
		
		AtmInterfaceCO fldsco = null;
		int fieldLength = 0;
		for (Object object : fieldList) 
		{
			fldsco = (AtmInterfaceCO) object;
			/** Check if the Field has Dynamic Length*/
			if(fldsco.getFIELD_LENL()!= null && !ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(fldsco.getFIELD_LENL()))
			{
				//Set length as per Dynamic
				fieldLength = fldsco.getFIELD_LENL().intValue();
			}
			else 
			{
				//Set static length
				fieldLength = fldsco.getIso_FIELDVO().getLENGTH().intValue();
			}
			
			/** Initialize Field Types according to saved configuration */
			initFieldType(fieldsInfoMap, fldsco, fieldLength);
			
			/** Check Sub Fields*/
			ArrayList<IsoValue> subFieldsConfigList = retSubFieldsISOConfiguratio(criteria, fldsco);
			if(subFieldsConfigList != null && !subFieldsConfigList.isEmpty())
			{
				int fieldCode = fldsco.getIso_FIELDVO().getID().intValue();
				fieldsInfoMap.remove(fieldCode);
				fieldsInfoMap.put(fieldCode, new LllvarParseInfo());
				messageFactory.setCustomField(fieldCode, new PathCustomFieldHandler(subFieldsConfigList));
			}//If there are child Fields
		}// End Loop
		
		try 
		{			
			fieldsInfoMap.remove(1);
			messageFactory.setParseMap(0x800, fieldsInfoMap);
			
			IsoMessage isoMsg = messageFactory.parseMessage(criteria.getIsoMessage().getBytes(), 0);
			return print(isoMsg, fieldList, criteria);
		} catch (Exception e) 
		{
			throw new BOException("Error while parsing ISO Message Due to "+e.getMessage());
		}
	}
	
	/**Initialize Field Types from configuration*/
	private void initFieldType(HashMap<Integer, FieldParseInfo> fieldsInfo, AtmInterfaceCO fldsco, int fieldLength) 
	{
		/** Check field Type and add in Configuration map*/
		//If type is Nemeric
		if(AtmCommonConstants.ISO_FIELD_TYPE_NUMERIC.equals(fldsco.getIso_FIELDVO().getFIELD_TYPE()))
		{
			fieldsInfo.put(fldsco.getIso_FIELDVO().getID().intValue(), new NumericParseInfo(fieldLength));
		}
		//Amount Type
		else if(AtmCommonConstants.ISO_FIELD_TYPE_AMOUNT.equals(fldsco.getIso_FIELDVO().getFIELD_TYPE()))
		{
			fieldsInfo.put(fldsco.getIso_FIELDVO().getID().intValue(), new AmountParseInfo());
		}
		//Expiration Date(mm/yy) Type
		else if(AtmCommonConstants.ISO_FIELD_TYPE_EXP_DATE.equals(fldsco.getIso_FIELDVO().getFIELD_TYPE()))
		{
			fieldsInfo.put(fldsco.getIso_FIELDVO().getID().intValue(), new DateExpParseInfo());
		}
		//Time Type
		else if(AtmCommonConstants.ISO_FIELD_TYPE_TIME.equals(fldsco.getIso_FIELDVO().getFIELD_TYPE()))
		{
			fieldsInfo.put(fldsco.getIso_FIELDVO().getID().intValue(), new TimeParseInfo());
		}
		//Date(dd/mm) type
		else if(AtmCommonConstants.ISO_FIELD_TYPE_DATE4.equals(fldsco.getIso_FIELDVO().getFIELD_TYPE()))
		{
			fieldsInfo.put(fldsco.getIso_FIELDVO().getID().intValue(), new Date4ParseInfo());
		}
		//Date(mmddHHMMSS) Type
		else if(AtmCommonConstants.ISO_FIELD_TYPE_DATE10.equals(fldsco.getIso_FIELDVO().getFIELD_TYPE()))
		{
			fieldsInfo.put(fldsco.getIso_FIELDVO().getID().intValue(), new Date10ParseInfo());
		}
		else
		{
			fieldsInfo.put(fldsco.getIso_FIELDVO().getID().intValue(), new AlphaParseInfo(fieldLength));			
		}
	}
		
	/** return List of Values Parsed from ISO Message */
	private List print(IsoMessage m, List fieldList, AtmInterfaceSC criteria) throws Exception 
	{
		List messageDataList = new ArrayList();
		AtmInterfaceCO mainFieldCO = null;
		AtmInterfaceSC criteriaSubFields = new AtmInterfaceSC();
		List subFieldList = null;
		
		for (int i = 0; i < fieldList.size(); i++) {
			mainFieldCO = (AtmInterfaceCO) fieldList.get(i);
			int code = mainFieldCO.getIso_FIELDVO().getID().intValue();
			if (m.hasField(code)) 
			{
				criteriaSubFields.setNbRec(-1);
				criteriaSubFields.setCompCode(criteria.getCompCode());
				criteriaSubFields.setInterfaceId(criteria.getInterfaceId());
				criteriaSubFields.setFieldCode(mainFieldCO.getIso_FIELDVO().getID());
				/** Retrive sub Fields */
				subFieldList = atmInterfaceDAO.retIsoSubFieldsList(criteriaSubFields);
				
				if(subFieldList != null && !subFieldList.isEmpty())
				{
					AtmInterfaceCO subFldsCo = null;
					/*Loop all sub fields and set values as Type*/
					List<IsoValue> customFields = m.getObjectValue(code);
					for (int j =0; j < customFields.size(); j++) 
					{
						IsoValue isoValue = customFields.get(j);
						subFldsCo = (AtmInterfaceCO) subFieldList.get(j);
						
						//Extract Fields value as per Type 
						if(isoValue.getType() == IsoType.NUMERIC)
						{//if NUMERIC
							subFldsCo.setData((new BigDecimal(isoValue.getValue()+"")+""));
						}
						else if(isoValue.getType() == IsoType.DATE_EXP)
						{//If Expire Date
							DateFormat formatter = new SimpleDateFormat("yyMM"); 
							try {
								Date date = (Date)formatter.parse(isoValue.getValue().toString());
								subFldsCo.setData(date.toString());
								System.out.println("Expiry Date : "+date);
							} catch (ParseException e) {
								subFldsCo.setData("");
							}
						}
						else if(isoValue.getType() == IsoType.DATE4)
						{//If Date4
							DateFormat formatter = new SimpleDateFormat("MMdd"); 
							try {
								Date date = (Date)formatter.parse(isoValue.getValue().toString());
								subFldsCo.setData(date.toString());
								System.out.println("Date-4 : "+date);
							} catch (ParseException e) {
								subFldsCo.setData("");
							}
						}
						else if(isoValue.getType() == IsoType.DATE10)
						{//If Date10
							DateFormat formatter = new SimpleDateFormat("MMddHHmmss"); 
							try {
								Date date = (Date)formatter.parse(isoValue.getValue().toString());
								subFldsCo.setData(date.toString());
								System.out.println("mmddHHMMSS Date : "+date);
							} catch (ParseException e) {
								subFldsCo.setData("");
							}
						}
						else if(isoValue.getType() == IsoType.TIME)
						{//If Time
							DateFormat formatter = new SimpleDateFormat("HHmmss"); 
							try {
								Date date = (Date)formatter.parse(isoValue.getValue().toString());
								subFldsCo.setData(date.toString());
								System.out.println("HHMMSS Time : "+date);
							} catch (ParseException e) {
								subFldsCo.setData("");
							}
						}
						else {
							subFldsCo.setData(isoValue.getValue()+"");
						}
						subFieldList.set(j, subFldsCo);
					}
					/*Set Json as Grid*/
					mainFieldCO.setSubGridData("{\"root\":".concat(PathJSONUtil.serialize(subFieldList, null, null, false, true)).concat("}"));
				}
				else if(m.getField(code).getType() == IsoType.NUMERIC)
				{
					mainFieldCO.setData((new BigDecimal(m.getObjectValue(code)+"")+""));
				}
				else {
					mainFieldCO.setData(m.getObjectValue(code)+"");
				}
				messageDataList.add(mainFieldCO);
			}
		}
		return messageDataList;
	}//method
	
	/**Return ISO Fields Configuration*/
	private ArrayList<IsoValue> retSubFieldsISOConfiguratio(AtmInterfaceSC criteria, AtmInterfaceCO fldsco) throws BaseException
	{
		//Sub Fields Handling
		ArrayList<IsoValue> customFieldList = null;
		AtmInterfaceSC criteriaSubFields = new AtmInterfaceSC();
		List subFieldList = null;
		AtmInterfaceCO subFldsCo = null;
		
		criteriaSubFields.setNbRec(-1);
		criteriaSubFields.setCompCode(criteria.getCompCode());
		criteriaSubFields.setInterfaceId(criteria.getInterfaceId());
		criteriaSubFields.setFieldCode(fldsco.getIso_FIELDVO().getID());
		subFieldList = atmInterfaceDAO.retIsoSubFieldsList(criteriaSubFields);
		
		if(subFieldList != null && !subFieldList.isEmpty())
		{
			customFieldList = new ArrayList<>();
			for (Object object : subFieldList) 
			{
				subFldsCo = (AtmInterfaceCO) object;
				//Check field Type and add in Configuration map
				int length = 0;
				if(subFldsCo.getSub_FLDSVO().getSUB_FLD_LENGTH() != null && !ConstantsCommon.EMPTY_BIGDECIMAL_VALUE.equals(subFldsCo.getSub_FLDSVO().getSUB_FLD_LENGTH()))
				{
					length = subFldsCo.getSub_FLDSVO().getSUB_FLD_LENGTH().intValue();
				}
				
				//If type is Nemeric
				if(AtmCommonConstants.ISO_FIELD_TYPE_NUMERIC.equals(subFldsCo.getSub_FLDSVO().getSUB_FLD_TYPE()))
				{
					customFieldList.add(new IsoValue(IsoType.NUMERIC, null, length));
				}
				//Amount Type
				else if(AtmCommonConstants.ISO_FIELD_TYPE_AMOUNT.equals(subFldsCo.getSub_FLDSVO().getSUB_FLD_TYPE()))
				{
					customFieldList.add(new IsoValue(IsoType.AMOUNT, null));
				}
				//Expiration Date(mm/yy) Type
				else if(AtmCommonConstants.ISO_FIELD_TYPE_EXP_DATE.equals(subFldsCo.getSub_FLDSVO().getSUB_FLD_TYPE()))
				{
					customFieldList.add(new IsoValue(IsoType.DATE_EXP, null));
				}
				//Time Type
				else if(AtmCommonConstants.ISO_FIELD_TYPE_TIME.equals(subFldsCo.getSub_FLDSVO().getSUB_FLD_TYPE()))
				{
					customFieldList.add(new IsoValue(IsoType.TIME, null));
				}
				//Date(dd/mm) type
				else if(AtmCommonConstants.ISO_FIELD_TYPE_DATE4.equals(subFldsCo.getSub_FLDSVO().getSUB_FLD_TYPE()))
				{
					customFieldList.add(new IsoValue(IsoType.DATE4, null));
				}
				//Date(mmddHHMMSS) Type
				else if(AtmCommonConstants.ISO_FIELD_TYPE_DATE10.equals(subFldsCo.getSub_FLDSVO().getSUB_FLD_TYPE()))
				{
					customFieldList.add(new IsoValue(IsoType.DATE10, null));
				}
				else
				{
					customFieldList.add(new IsoValue(IsoType.ALPHA, null, length));
				}
			}//End foreach loop
		}
		return customFieldList;
	}
		
	/**Extract Fields From Bitmap*/
	private List<BigDecimal> extractFieldsFromBitMaps(AtmInterfaceSC criteria)
	{
		criteria.setPrimaryBitmap(BaseConvertor.hexaToBinary(criteria.getPrimaryBitmap()));
		criteria.setSecondaryBitmap(BaseConvertor.hexaToBinary(criteria.getSecondaryBitmap()));
		char primaryBitmap[] = criteria.getPrimaryBitmap().toCharArray(); 
		char secondaryBitmap[] = criteria.getSecondaryBitmap().toCharArray(); 
		List<BigDecimal> bitMapFields = new ArrayList();
		
		for (int i = 0; i < primaryBitmap.length; i++) 
		{
			if("1".equals(""+primaryBitmap[i]))
			{
				bitMapFields.add(new BigDecimal(i+1));
			}
		}
		
		for (int i = 0; i < secondaryBitmap.length; i++) 
		{
			if("1".equals(""+secondaryBitmap[i]))
			{
				bitMapFields.add(new BigDecimal(i+65));
			}
		}
		return bitMapFields;
	}
}
