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

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=koroneiki.account.update",
	service = AccountUpdateMessageProcessor.class
)
public class AccountUpdateMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		CorpProject corpProject =
			CorpProjectLocalServiceUtil.fetchCorpProjectByUuid(
				jsonObject.getString("key"));

		if (corpProject == null) {
			return;
		}

		String dossieraProjectKey = StringPool.BLANK;
		String salesforceProjectKey = StringPool.BLANK;

		JSONArray jsonArray = jsonObject.getJSONArray("externalLinks");

		if (jsonArray != null) {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject externalLinkJSONObject = jsonArray.getJSONObject(i);

				String domain = externalLinkJSONObject.getString("domain");
				String entityName = externalLinkJSONObject.getString(
					"entityName");

				if (domain.equals("dossiera") && entityName.equals("project")) {
					dossieraProjectKey = externalLinkJSONObject.getString(
						"entityId");
				}
				else if (domain.equals("salesforce") &&
						 entityName.equals("project")) {

					salesforceProjectKey = externalLinkJSONObject.getString(
						"entityId");
				}
			}
		}

		CorpProjectLocalServiceUtil.updateCorpProject(
			corpProject.getCorpProjectId(), dossieraProjectKey,
			salesforceProjectKey, jsonObject.getString("name"),
			new ServiceContext());
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}