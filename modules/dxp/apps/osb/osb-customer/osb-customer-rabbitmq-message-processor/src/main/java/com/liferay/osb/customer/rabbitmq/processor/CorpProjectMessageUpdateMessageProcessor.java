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

package com.liferay.osb.customer.rabbitmq.processor;

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