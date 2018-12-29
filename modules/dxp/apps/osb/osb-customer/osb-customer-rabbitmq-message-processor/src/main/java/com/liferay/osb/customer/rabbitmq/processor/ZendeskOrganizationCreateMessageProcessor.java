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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "routing.key=zendesk.organization.create",
	service = ZendeskOrganizationCreateMessageProcessor.class
)
public class ZendeskOrganizationCreateMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject organizationJSONObject = jsonObject.getJSONObject(
			"organization");

		long accountEntryId = organizationJSONObject.getLong("external_id");
		String zendeskOrganizationId = organizationJSONObject.getString("id");

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class);

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, accountEntryId,
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers.isEmpty()) {
			ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
				classNameId, accountEntryId,
				ExternalIdMapperConstants.TYPE_ZENDESK, zendeskOrganizationId);
		}
		else {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			if (externalIdMapper.getExternalId() != zendeskOrganizationId) {
				ExternalIdMapperLocalServiceUtil.updateExternalIdMapper(
					externalIdMapper.getExternalIdMapperId(), classNameId,
					accountEntryId, ExternalIdMapperConstants.TYPE_ZENDESK,
					zendeskOrganizationId);
			}
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}