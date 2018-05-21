/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.service.impl;

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.model.impl.CorpProjectMessageImpl;
import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteCorpProjectMessageLocalServiceBaseImpl;
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
			String title, String content, boolean displayCP, boolean displayLCS)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		JSONObject jsonObject = WebRESTWebServiceUtil.postCorpProjectMessages(
			user.getUuid(), corpProject.getUuid(), type, severityLevel, title,
			content, displayCP, displayLCS);

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
		corpProjectMessage.setModifiedDate(
			new Date(jsonObject.getLong("modifiedDate")));
		corpProjectMessage.setSeverityLevel(jsonObject.getInt("severityLevel"));
		corpProjectMessage.setTitle(jsonObject.getString("title"));
		corpProjectMessage.setType(jsonObject.getInt("type"));
		corpProjectMessage.setUserId(user.getUserId());
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