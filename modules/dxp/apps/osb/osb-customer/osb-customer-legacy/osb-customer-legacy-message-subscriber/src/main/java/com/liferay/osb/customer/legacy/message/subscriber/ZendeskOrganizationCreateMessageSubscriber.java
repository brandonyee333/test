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
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.koroneiki.constants.ExternalLinkConstants;
import com.liferay.osb.customer.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
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

	protected void addExternalLink(String accountKey, String entityId)
		throws Exception {

		List<ExternalLink> externalLinks =
			_externalLinkWebService.getExternalLinks(accountKey, 1, 1000);

		for (ExternalLink externalLink : externalLinks) {
			String domain = externalLink.getDomain();
			String entityName = externalLink.getEntityName();

			if (domain.equals(ExternalLinkConstants.DOMAIN_ZENDESK) &&
				entityName.equals(
					ExternalLinkConstants.ENTITY_NAME_ZENDESK_ORGANIZATION)) {

				return;
			}
		}

		ExternalLink externalLink = new ExternalLink();

		externalLink.setDomain(ExternalLinkConstants.DOMAIN_ZENDESK);
		externalLink.setEntityName(
			ExternalLinkConstants.ENTITY_NAME_ZENDESK_ORGANIZATION);
		externalLink.setEntityId(entityId);

		_externalLinkWebService.postExternalLink(accountKey, externalLink);
	}

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

		JSONObject organizationFieldsJSONObject =
			organizationJSONObject.getJSONObject("organization_fields");

		String koroneikiAccountKey = organizationFieldsJSONObject.getString(
			"account_key");

		addExternalLink(koroneikiAccountKey, zendeskOrganizationId);
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	@Reference
	private ExternalLinkWebService _externalLinkWebService;

}