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

package com.liferay.osb.customer.zendesk.documentation.sync.service.impl;

import com.liferay.osb.customer.zendesk.documentation.sync.exception.RequiredZendeskCategoryException;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskCategoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ZendeskCategoryLocalServiceImpl
	extends ZendeskCategoryLocalServiceBaseImpl {

	public ZendeskCategory addZendeskCategory(
			String documentationKey, String documentationOriginalURL,
			String articleLabels, long remoteId)
		throws PortalException {

		long zendeskCategoryId = counterLocalService.increment();

		ZendeskCategory zendeskCategory = zendeskCategoryPersistence.create(
			zendeskCategoryId);

		zendeskCategory.setDocumentationKey(documentationKey);
		zendeskCategory.setDocumentationOriginalURL(documentationOriginalURL);
		zendeskCategory.setArticleLabels(articleLabels);
		zendeskCategory.setRemoteId(remoteId);

		return zendeskCategoryPersistence.update(zendeskCategory);
	}

	public ZendeskCategory deleteZendeskCategory(long zendeskCategoryId)
		throws PortalException {

		if (zendeskSectionLocalService.getZendeskSectionsCount(
				zendeskCategoryId) > 0) {

			throw new RequiredZendeskCategoryException();
		}

		return zendeskCategoryPersistence.remove(zendeskCategoryId);
	}

	public ZendeskCategory fetchZendeskCategory(String documentationKey) {
		return zendeskCategoryPersistence.fetchByDocumentationKey(
			documentationKey);
	}

	public ZendeskCategory updateZendeskCategory(
			long zendeskCategoryId, String documentationOriginalURL,
			String articleLabels)
		throws PortalException {

		ZendeskCategory zendeskCategory =
			zendeskCategoryPersistence.findByPrimaryKey(zendeskCategoryId);

		zendeskCategory.setDocumentationOriginalURL(documentationOriginalURL);
		zendeskCategory.setArticleLabels(articleLabels);

		return zendeskCategoryPersistence.update(zendeskCategory);
	}

}