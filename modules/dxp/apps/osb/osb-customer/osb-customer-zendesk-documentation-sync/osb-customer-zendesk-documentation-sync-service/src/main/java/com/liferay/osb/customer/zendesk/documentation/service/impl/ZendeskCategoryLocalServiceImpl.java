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

package com.liferay.osb.customer.zendesk.documentation.service.impl;

import com.liferay.osb.customer.zendesk.documentation.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.service.base.ZendeskCategoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ZendeskCategoryLocalServiceImpl
	extends ZendeskCategoryLocalServiceBaseImpl {

	public ZendeskCategory addZendeskCategory(
			String documentationKey, long remoteId)
		throws PortalException {

		long zendeskCategoryId = counterLocalService.increment();

		ZendeskCategory zendeskCategory = zendeskCategoryPersistence.create(
			zendeskCategoryId);

		zendeskCategory.setDocumentationKey(documentationKey);
		zendeskCategory.setRemoteId(remoteId);

		return zendeskCategoryPersistence.update(zendeskCategory);
	}

	public ZendeskCategory getZendeskCategory(String documentationKey)
		throws PortalException {

		return zendeskCategoryPersistence.findByDocumentationKey(
			documentationKey);
	}

}