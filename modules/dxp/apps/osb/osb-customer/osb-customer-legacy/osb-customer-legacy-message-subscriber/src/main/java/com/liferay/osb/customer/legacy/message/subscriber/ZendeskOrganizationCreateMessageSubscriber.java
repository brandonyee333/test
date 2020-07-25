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

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.ExternalIdMapper;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=zendesk.organization.create",
	service = ZendeskOrganizationCreateMessageSubscriber.class
)
public class ZendeskOrganizationCreateMessageSubscriber
	extends BaseMessageSubscriber {

	protected void doReceive(JSONObject jsonObject) throws Exception {
		JSONObject organizationJSONObject = jsonObject.getJSONObject(
			"organization");

		long accountEntryId = organizationJSONObject.getLong("external_id");
		String zendeskOrganizationId = organizationJSONObject.getString("id");

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class);

		List<ExternalIdMapper> externalIdMappers =
			_externalIdMapperLocalService.getExternalIdMappers(
				classNameId, accountEntryId,
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers.isEmpty()) {
			_externalIdMapperLocalService.addExternalIdMapper(
				classNameId, accountEntryId,
				ExternalIdMapperConstants.TYPE_ZENDESK, zendeskOrganizationId);
		}
		else {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			if (externalIdMapper.getExternalId() != zendeskOrganizationId) {
				_externalIdMapperLocalService.updateExternalIdMapper(
					externalIdMapper.getExternalIdMapperId(), classNameId,
					accountEntryId, ExternalIdMapperConstants.TYPE_ZENDESK,
					zendeskOrganizationId);
			}
		}
	}

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

}