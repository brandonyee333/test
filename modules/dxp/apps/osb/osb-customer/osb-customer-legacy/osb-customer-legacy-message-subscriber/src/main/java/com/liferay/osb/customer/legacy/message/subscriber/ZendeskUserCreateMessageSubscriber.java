/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.model.ExternalIdMapper;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=zendesk.user.create.or.update",
	service = ZendeskUserCreateMessageSubscriber.class
)
public class ZendeskUserCreateMessageSubscriber extends BaseMessageSubscriber {

	protected void doReceive(JSONObject jsonObject) throws Exception {
		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		String uuid = userJSONObject.getString("external_id");

		if (Validator.isNull(uuid)) {
			return;
		}

		String zendeskUserId = userJSONObject.getString("id");

		long classNameId = classNameLocalService.getClassNameId(User.class);

		User user = userLocalService.getUserByUuidAndCompanyId(
			uuid, OSBCustomerConstants.COMPANY_ID);

		List<ExternalIdMapper> externalIdMappers =
			_externalIdMapperLocalService.getExternalIdMappers(
				classNameId, user.getUserId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers.isEmpty()) {
			_externalIdMapperLocalService.addExternalIdMapper(
				classNameId, user.getUserId(),
				ExternalIdMapperConstants.TYPE_ZENDESK, zendeskUserId);
		}
		else {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			if (externalIdMapper.getExternalId() != zendeskUserId) {
				_externalIdMapperLocalService.updateExternalIdMapper(
					externalIdMapper.getExternalIdMapperId(), classNameId,
					user.getUserId(), ExternalIdMapperConstants.TYPE_ZENDESK,
					zendeskUserId);
			}
		}
	}

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

}