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
	immediate = true, property = "routing.key=zendesk.user.create.or.update",
	service = ZendeskUserCreateMessageProcessor.class
)
public class ZendeskUserCreateMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
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