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

package com.liferay.osb.customer.rabbitmq.processors;

import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.service.CorpProjectMessageLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=entity.corpprojectmessage.update",
	service = CorpProjectMessageUpdateMessageProcessor.class
)
public class CorpProjectMessageUpdateMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		long userId = 0;

		User user = fetchUser(jsonObject.getJSONObject("user"));

		if (user != null) {
			userId = user.getUserId();
		}

		CorpProjectMessage corpProjectMessage =
			CorpProjectMessageLocalServiceUtil.fetchCorpProjectMessageByUuid(
				jsonObject.getString("uuid"));

		if (corpProjectMessage == null) {
			return;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(
			new Date(jsonObject.getLong("modifiedDate")));

		CorpProjectMessageLocalServiceUtil.updateCorpProjectMessage(
			userId, corpProjectMessage.getCorpProjectMessageId(),
			jsonObject.getInt("type"), jsonObject.getInt("severityLevel"),
			jsonObject.getString("title"), jsonObject.getString("content"),
			jsonObject.getBoolean("displayCP"),
			jsonObject.getBoolean("displayLCS"), serviceContext);
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}