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

package com.liferay.osb.customer.rabbitmq.processors;

import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
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
			uuid, OSBConstants.COMPANY_ID);

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, user.getUserId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers.isEmpty()) {
			ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
				classNameId, user.getUserId(),
				ExternalIdMapperConstants.TYPE_ZENDESK, zendeskUserId);
		}
		else {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			if (externalIdMapper.getExternalId() != zendeskUserId) {
				ExternalIdMapperLocalServiceUtil.updateExternalIdMapper(
					externalIdMapper.getExternalIdMapperId(), classNameId,
					user.getUserId(), ExternalIdMapperConstants.TYPE_ZENDESK,
					zendeskUserId);
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