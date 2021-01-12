package com.path.imco.bo.mxmessagedefinition.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.struts2.json.JSONException;

import com.path.bo.common.ConstantsCommon;
import com.path.bo.common.MessageCodes;
import com.path.dbmaps.vo.DGTL_MSG_DEFVO;
import com.path.dbmaps.vo.DGTL_XML_MSG_TAGSVO;
import com.path.imco.bo.common.ImcoCommonConstants;
import com.path.imco.bo.mxmessagedefinition.MxMessageDefinitionBO;
import com.path.imco.dao.mxmessagedefinition.MxMessageDefinitionDAO;
import com.path.imco.vo.mxmessagedefinition.MxMessageDefinitionCO;
import com.path.imco.vo.mxmessagedefinition.MxMessageDefinitionSC;
import com.path.imco.vo.mxmessagedefinition.TagsCo;
import com.path.lib.common.base.BaseBO;
import com.path.lib.common.exception.BOException;
import com.path.lib.common.exception.BaseException;
import com.path.lib.common.util.NumberUtil;
import com.path.lib.common.util.StringUtil;

/**
 * 
 * Copyright 2020, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 * 
 * MxMessageDefinitionBOImpl.java used to
 */
public class MxMessageDefinitionBOImpl extends BaseBO implements MxMessageDefinitionBO {
	private MxMessageDefinitionDAO mxMessageDefinitionDAO;

	@Override
	public List returnMxMessageDefinitionTagsList(MxMessageDefinitionSC criteria) throws BaseException {
		return mxMessageDefinitionDAO.returnMxMessageDefinitionTagsList(criteria);
	}

	public int returnMxMessageDefinitionListCount(MxMessageDefinitionSC criteria) throws BaseException {
		return mxMessageDefinitionDAO.returnMxMessageDefinitionListCount(criteria);
	}

	@Override
	public Integer save(MxMessageDefinitionCO co) throws BaseException {
		DGTL_MSG_DEFVO dgtl_MSG_DEFVO = co.getDgtl_MSG_DEFVO();
		dgtl_MSG_DEFVO.setMSG_TYPE("XML");
		dgtl_MSG_DEFVO.setSTATUS(ConstantsCommon.ACTIVE_RECORD);

		Integer rows = 0;

		if (NumberUtil.isEmptyDecimal(dgtl_MSG_DEFVO.getDGTL_MSG_DEF_ID())) {
			dgtl_MSG_DEFVO.setCREATED_BY(co.getUserId());
			dgtl_MSG_DEFVO.setCREATED_DATE(commonLibBO.returnSystemDateWithTime());
			rows = genericDAO.insert(dgtl_MSG_DEFVO);

			co.setDgtl_MSG_DEFVO(dgtl_MSG_DEFVO);

			// call audit for insert
			auditBO.callAudit(null, dgtl_MSG_DEFVO, co.getAuditRefCO());
		} else {
			dgtl_MSG_DEFVO.setMODIFIED_BY(co.getUserId());
			dgtl_MSG_DEFVO.setMODIFIED_DATE(commonLibBO.returnSystemDateWithTime());
			rows = genericDAO.update(dgtl_MSG_DEFVO);

			if (rows == null || rows < 1) {
				throw new BOException(MessageCodes.RECORD_CHANGED);
			}

			// retrieve old Audit Object
			MxMessageDefinitionCO auditObj = (MxMessageDefinitionCO) co.getAuditObj();

			// call Audit
			auditBO.callAudit(auditObj.getDgtl_MSG_DEFVO(), dgtl_MSG_DEFVO, co.getAuditRefCO());

		}

		/**
		 * save tags
		 */
		saveTags(co);

		// insert Audit
		auditBO.insertAudit(co.getAuditRefCO());

		return rows;
	}

	/**
	 * Save Mx Message Definition Tags
	 * 
	 * @param co
	 * @return
	 * @throws BaseException
	 * @throws JSONException
	 */
	private Integer saveTags(MxMessageDefinitionCO mxMessageDefinitionCO) throws BaseException {
		Integer rows = 0;

		mxMessageDefinitionCO.setDgtlMsgDefId(mxMessageDefinitionCO.getDgtl_MSG_DEFVO().getDGTL_MSG_DEF_ID());

		HashMap<String, TagsCo> mandatorytags = new HashMap<String, TagsCo>();
		HashMap<String, TagsCo> parentTags = new HashMap<String, TagsCo>();
		List<TagsCo> tobeInserted = new ArrayList<TagsCo>();

		/**
		 * Prepare All Parent Tags
		 */
		/*if (null != mxMessageDefinitionCO.getParentTags() && mxMessageDefinitionCO.getParentTags().size() > 0) {
			for (TagsCo tagsCo : mxMessageDefinitionCO.getParentTags()) {
				String key = StringUtil.isEmptyString(tagsCo.getTagHierarchy()) ? tagsCo.getTagName()
						: tagsCo.getTagHierarchy() + "_" + tagsCo.getTagName();
				parentTags.put(key, tagsCo);
			}
		}*/

		/**
		 * Prepare Required fields which we need to insert by default
		 */
		//prepareMandatoryTags(mandatorytags, parentTags, mxMessageDefinitionCO.getMandatoryTags(),
		//		mxMessageDefinitionCO);
		/**
		 * delete all tags of mxMessageDefinition id
		 */
		mxMessageDefinitionDAO.deleteMxMessageDefinitionTags(mxMessageDefinitionCO.getDgtl_MSG_DEFVO().getDGTL_MSG_DEF_ID());

		/**
		 * if there is no any user selected tags then return
		 */
/*		if (mxMessageDefinitionCO.getTagsCos() != null && mxMessageDefinitionCO.getTagsCos().size() > 0) {
			*//**
			 * Loop over user selected tags
			 *//*
			for (TagsCo tagsCo : mxMessageDefinitionCO.getTagsCos()) {

				*//**
				 * generate key of mandatroy tags which is saved already
				 *//*
				String key = mxMessageDefinitionCO.getDgtlMsgDefId() + "-" + tagsCo.getTagName() + "-"
						+ tagsCo.getTagHierarchy();
				TagsCo tagsCo2 = mandatorytags.get(key);

				if (tagsCo2 == null) {
					mandatorytags.put(key, tagsCo);
				} else if (tagsCo2 != null && StringUtil.isEmptyString(tagsCo2.getDescription())) 
				{
					tagsCo2.setDescription(tagsCo.getDescription());
					mandatorytags.put(key, tagsCo2);
				}
			}
		}

		tobeInserted.addAll(mandatorytags.values());
		mxMessageDefinitionCO.setAllTagsCos(tobeInserted);*/
		
		if(mxMessageDefinitionCO.getAllTagsCos() != null && mxMessageDefinitionCO.getAllTagsCos().size() > 0)
			mxMessageDefinitionDAO.insertDGTL_XML_MSG_TAGS(mxMessageDefinitionCO);
		
		return rows;
	}

	/**
	 * prepare Mandatory Tags
	 * 
	 * @param mapForMandatory
	 * @param mandatoryTagsCos
	 * @param mxMessageDefinitionCO
	 * @throws JSONException
	 */
	private void prepareMandatoryTags(HashMap<String, TagsCo> mandatoryTagsMap, HashMap<String, TagsCo> parentTags,
			List<TagsCo> mandatoryTagsCos, MxMessageDefinitionCO mxMessageDefinitionCO) {
		List<TagsCo> list = new ArrayList<>();
		HashMap<String, TagsCo> mandatoryTagsParent = new HashMap<String, TagsCo>();

		for (TagsCo tagsCo : mandatoryTagsCos) {
			String tagsHier[] = tagsCo.getTagHierarchy().split("_");
			String temp = "";
			boolean isParentRequired = true;
			HashMap<String, TagsCo> tempMap = new HashMap<String, TagsCo>();

			for (String h : tagsHier) {
				temp += h;

				TagsCo co = parentTags.get(temp);
				if (co == null) {
					temp += "_";
					continue;
				}

				if (StringUtil.nullToEmpty(co.getMinOccur()).equals("0")) {
					isParentRequired = false;
					break;
				}

				/**
				 * Add parent tags for saving
				 */
				tempMap.put(temp + "_" + co.getTagName(), co);

				temp += "_";
			}

			if (isParentRequired) {

				/**
				 * Add All mandatory leaf tags for update if its saved alread
				 */
				String key = mxMessageDefinitionCO.getDgtlMsgDefId() + "-" + tagsCo.getTagName() + "-"
						+ tagsCo.getTagHierarchy();
				mandatoryTagsMap.put(key, tagsCo);

				/**
				 * Add All parent of mandatory tags for update if its saved alread
				 */
				mandatoryTagsMap.putAll(tempMap);

				/**
				 * Add All Parents of mandatory tags
				 */
				mandatoryTagsParent.putAll(tempMap);

				// list.add(tagsCo);
			}
		}

		// List<TagsCo> parents =
		// mandatoryTagsParent.values().stream().collect(Collectors.toList());

		// list.addAll(parents);
		// mxMessageDefinitionCO.setAllTagsCos(list);
	}

	@Override
	public Integer handleStatusProcess(MxMessageDefinitionCO mxMessageDefinitionCO) throws BaseException {
		DGTL_MSG_DEFVO dgtl_MSG_DEFVO = mxMessageDefinitionCO.getDgtl_MSG_DEFVO();

		if (ImcoCommonConstants.STATUS_APPROVED.equals(mxMessageDefinitionCO.getMode())) {
			dgtl_MSG_DEFVO.setAPPROVED_BY(mxMessageDefinitionCO.getUserId());
			dgtl_MSG_DEFVO.setAPPROVED_DATE(commonLibBO.returnSystemDateWithTime());
			dgtl_MSG_DEFVO.setSTATUS(ImcoCommonConstants.STATUS_APPROVED);
		} else if (ImcoCommonConstants.STATUS_DELETED.equals(mxMessageDefinitionCO.getMode())) {
			dgtl_MSG_DEFVO.setDELETED_BY(mxMessageDefinitionCO.getUserId());
			dgtl_MSG_DEFVO.setDELETED_DATE(commonLibBO.returnSystemDateWithTime());
			dgtl_MSG_DEFVO.setSTATUS(ImcoCommonConstants.STATUS_DELETED);
		}

		Integer rows = genericDAO.update(dgtl_MSG_DEFVO);
		if (rows == null || rows < 1) {
			throw new BOException(MessageCodes.RECORD_CHANGED);
		}

		// retrieve old Audit Object
		MxMessageDefinitionCO auditObj = (MxMessageDefinitionCO) mxMessageDefinitionCO.getAuditObj();

		// call Audit
		auditBO.callAudit(auditObj.getDgtl_MSG_DEFVO(), dgtl_MSG_DEFVO, mxMessageDefinitionCO.getAuditRefCO());

		// insert Audit
		auditBO.insertAudit(mxMessageDefinitionCO.getAuditRefCO());

		return rows;
	}

	@Override
	public MxMessageDefinitionCO validateMxMsgDefinitionCode(MxMessageDefinitionCO mxMessageDefinitionCO)
			throws BaseException {

		return mxMessageDefinitionCO;
	}

	@Override
	public MxMessageDefinitionCO returnMxMessageDefinitionDetails(MxMessageDefinitionSC criteria) throws BaseException {
		return mxMessageDefinitionDAO.returnMxMessageDefinitionDetails(criteria);
	}

	@Override
	public List returnMxMessageDefinitionList(MxMessageDefinitionSC criteria) throws BaseException {
		return mxMessageDefinitionDAO.returnMxMessageDefinitionList(criteria);
	}

	public void setMxMessageDefinitionDAO(MxMessageDefinitionDAO mxMessageDefinitionDAO) {
		this.mxMessageDefinitionDAO = mxMessageDefinitionDAO;
	}

}
