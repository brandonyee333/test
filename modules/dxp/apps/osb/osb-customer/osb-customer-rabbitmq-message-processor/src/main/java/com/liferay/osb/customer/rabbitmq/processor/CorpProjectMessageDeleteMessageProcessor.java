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
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=entity.corpprojectmessage.delete",
	service = CorpProjectMessageDeleteMessageProcessor.class
)
public class CorpProjectMessageDeleteMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		CorpProjectMessage corpProjectMessage =
			CorpProjectMessageLocalServiceUtil.fetchCorpProjectMessageByUuid(
				jsonObject.getString("uuid"));

		if (corpProjectMessage == null) {
			return;
		}

		CorpProjectMessageLocalServiceUtil.deleteCorpProjectMessage(
			corpProjectMessage.getCorpProjectMessageId());
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}