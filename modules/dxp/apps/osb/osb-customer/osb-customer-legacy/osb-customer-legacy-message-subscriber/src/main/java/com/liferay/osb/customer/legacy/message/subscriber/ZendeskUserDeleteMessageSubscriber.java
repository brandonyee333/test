/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.model.ExternalIdMapper;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=zendesk.user.delete",
	service = ZendeskUserDeleteMessageSubscriber.class
)
public class ZendeskUserDeleteMessageSubscriber extends BaseMessageSubscriber {

	protected void doReceive(JSONObject jsonObject) throws Exception {
		long classNameId = classNameLocalService.getClassNameId(User.class);

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		String zendeskUserId = userJSONObject.getString("id");

		List<ExternalIdMapper> externalIdMappers =
			_externalIdMapperLocalService.getExternalIdMappers(
				classNameId, ExternalIdMapperConstants.TYPE_ZENDESK,
				zendeskUserId);

		for (ExternalIdMapper externalIdMapper : externalIdMappers) {
			_externalIdMapperLocalService.deleteExternalIdMapper(
				externalIdMapper.getExternalIdMapperId());
		}
	}

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

}