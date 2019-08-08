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

import com.liferay.osb.customer.zendesk.util.PhoneUtil;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "routing.key=zendesk.user.identity.create",
	service = ZendeskUserIdentityCreateMessageProcessor.class
)
public class ZendeskUserIdentityCreateMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject identityJSONObject = jsonObject.getJSONObject("identity");

		String id = identityJSONObject.getString("id");
		String type = identityJSONObject.getString("type");
		String userId = identityJSONObject.getString("user_id");
		String value = identityJSONObject.getString("value");

		if (type.equals("phone_number")) {
			processPhone(id, type, userId, value);
		}
	}

	protected void processPhone(
			String id, String type, String userId, String value)
		throws Exception {

		long classNameId = classNameLocalService.getClassNameId(Phone.class);
		long classPK = 0;

		List<ExternalIdMapper> userExternalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameLocalService.getClassNameId(User.class),
				ExternalIdMapperConstants.TYPE_ZENDESK, userId);

		ExternalIdMapper userExternalIdMapper = userExternalIdMappers.get(0);

		User user = userLocalService.getUser(userExternalIdMapper.getClassPK());

		for (Phone phone : user.getPhones()) {
			if (value.equals(PhoneUtil.convertToE164(phone))) {
				classPK = phone.getPhoneId();

				break;
			}
		}

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, classPK, ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers.isEmpty()) {
			ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
				classNameId, classPK, ExternalIdMapperConstants.TYPE_ZENDESK,
				id);
		}
		else {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			if (externalIdMapper.getExternalId() != id) {
				ExternalIdMapperLocalServiceUtil.updateExternalIdMapper(
					externalIdMapper.getExternalIdMapperId(), classNameId,
					classPK, ExternalIdMapperConstants.TYPE_ZENDESK, id);
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