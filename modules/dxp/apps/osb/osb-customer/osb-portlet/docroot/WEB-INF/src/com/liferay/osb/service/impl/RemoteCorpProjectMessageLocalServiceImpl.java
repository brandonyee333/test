/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.impl;

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.model.impl.CorpProjectMessageImpl;
import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteCorpProjectMessageLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class RemoteCorpProjectMessageLocalServiceImpl
	extends RemoteCorpProjectMessageLocalServiceBaseImpl {

	@Override
	public CorpProjectMessage addCorpProjectMessage(
			long userId, long corpProjectId, int type, int severityLevel,
			String title, String content, boolean displayCP, boolean displayLCS,
			boolean displayLESA)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		JSONObject jsonObject = WebRESTWebServiceUtil.postCorpProjectMessages(
			user.getUuid(), corpProject.getUuid(), type, severityLevel, title,
			content, displayCP, displayLCS, displayLESA);

		User responseUser = userLocalService.fetchUserByUuidAndCompanyId(
			jsonObject.getString("userUuid"), OSBConstants.COMPANY_ID);
		CorpProject responseCorpProject =
			corpProjectLocalService.getCorpProjectByUuid(
				jsonObject.getString("corpProjectUuid"));

		CorpProjectMessage corpProjectMessage = new CorpProjectMessageImpl();

		corpProjectMessage.setContent(jsonObject.getString("content"));
		corpProjectMessage.setCorpProjectId(
			responseCorpProject.getCorpProjectId());
		corpProjectMessage.setCreateDate(
			new Date(jsonObject.getLong("createDate")));
		corpProjectMessage.setDisplayCP(jsonObject.getBoolean("displayCP"));
		corpProjectMessage.setDisplayLCS(jsonObject.getBoolean("displayLCS"));
		corpProjectMessage.setDisplayLESA(jsonObject.getBoolean("displayLESA"));
		corpProjectMessage.setModifiedDate(
			new Date(jsonObject.getLong("modifiedDate")));
		corpProjectMessage.setSeverityLevel(jsonObject.getInt("severityLevel"));
		corpProjectMessage.setTitle(jsonObject.getString("title"));
		corpProjectMessage.setType(jsonObject.getInt("type"));
		corpProjectMessage.setUserId(responseUser.getUserId());
		corpProjectMessage.setUuid(jsonObject.getString("uuid"));

		return corpProjectMessage;
	}

	@Override
	public void deleteCorpProjectMessage(long corpProjectMessageId)
		throws PortalException {

		CorpProjectMessage corpProjectMessage =
			corpProjectMessagePersistence.findByPrimaryKey(
				corpProjectMessageId);

		WebRESTWebServiceUtil.deleteCorpProjectMessages(
			corpProjectMessage.getUuid());
	}

}