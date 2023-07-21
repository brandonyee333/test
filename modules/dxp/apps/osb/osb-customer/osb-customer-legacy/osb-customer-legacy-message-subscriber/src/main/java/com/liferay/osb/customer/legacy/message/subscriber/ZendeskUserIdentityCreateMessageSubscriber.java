/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.model.ExternalIdMapper;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.zendesk.util.PhoneUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=zendesk.user.identity.create",
	service = ZendeskUserIdentityCreateMessageSubscriber.class
)
public class ZendeskUserIdentityCreateMessageSubscriber
	extends BaseMessageSubscriber {

	protected void doReceive(JSONObject jsonObject) throws Exception {
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
			_externalIdMapperLocalService.getExternalIdMappers(
				classNameLocalService.getClassNameId(User.class),
				ExternalIdMapperConstants.TYPE_ZENDESK, userId);

		ExternalIdMapper userExternalIdMapper = userExternalIdMappers.get(0);

		User user = userLocalService.getUser(userExternalIdMapper.getClassPK());

		for (Phone phone : user.getPhones()) {
			if (value.equals(_phoneUtil.convertToE164(phone))) {
				classPK = phone.getPhoneId();

				break;
			}
		}

		List<ExternalIdMapper> externalIdMappers =
			_externalIdMapperLocalService.getExternalIdMappers(
				classNameId, classPK, ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers.isEmpty()) {
			_externalIdMapperLocalService.addExternalIdMapper(
				classNameId, classPK, ExternalIdMapperConstants.TYPE_ZENDESK,
				id);
		}
		else {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			if (externalIdMapper.getExternalId() != id) {
				_externalIdMapperLocalService.updateExternalIdMapper(
					externalIdMapper.getExternalIdMapperId(), classNameId,
					classPK, ExternalIdMapperConstants.TYPE_ZENDESK, id);
			}
		}
	}

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	@Reference
	private PhoneUtil _phoneUtil;

}