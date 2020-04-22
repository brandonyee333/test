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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskCategoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Amos Fong
 */
public class ZendeskCategoryLocalServiceImpl
	extends ZendeskCategoryLocalServiceBaseImpl {

	public ZendeskCategory addZendeskCategory(
			String documentationKey, String documentationOriginalURL,
			String articleLabels, long remoteId, long remoteUserSegmentId)
		throws PortalException {

		long zendeskCategoryId = counterLocalService.increment();

		ZendeskCategory zendeskCategory = zendeskCategoryPersistence.create(
			zendeskCategoryId);

		zendeskCategory.setDocumentationKey(documentationKey);
		zendeskCategory.setDocumentationOriginalURL(documentationOriginalURL);
		zendeskCategory.setArticleLabels(articleLabels);
		zendeskCategory.setRemoteId(remoteId);
		zendeskCategory.setRemoteUserSegmentId(remoteUserSegmentId);

		return zendeskCategoryPersistence.update(zendeskCategory);
	}

	public ZendeskCategory deleteZendeskCategory(long zendeskCategoryId)
		throws PortalException {

		List<ZendeskArticle> zendeskArticles =
			zendeskArticleLocalService.getZendeskCategoryArticles(
				zendeskCategoryId);

		for (ZendeskArticle zendeskArticle : zendeskArticles) {
			List<ZendeskArticleAttachment> zendeskArticleAttachments =
				zendeskArticleAttachmentLocalService.
					getZendeskArticleAttachments(
						zendeskArticle.getZendeskArticleId());

			for (ZendeskArticleAttachment zendeskArticleAttachment :
					zendeskArticleAttachments) {

				zendeskArticleAttachmentLocalService.
					deleteZendeskArticleAttachment(zendeskArticleAttachment);
			}

			zendeskArticleLocalService.deleteZendeskArticle(zendeskArticle);
		}

		List<ZendeskSection> zendeskSections =
			zendeskSectionLocalService.getZendeskSections(zendeskCategoryId);

		for (ZendeskSection zendeskSection : zendeskSections) {
			zendeskSectionLocalService.deleteZendeskSection(zendeskSection);
		}

		return zendeskCategoryPersistence.remove(zendeskCategoryId);
	}

	public ZendeskCategory fetchZendeskCategory(String documentationKey) {
		return zendeskCategoryPersistence.fetchByDocumentationKey(
			documentationKey);
	}

	public ZendeskCategory updateZendeskCategory(
			long zendeskCategoryId, String documentationKey,
			String documentationOriginalURL, String articleLabels,
			long remoteUserSegmentId)
		throws PortalException {

		ZendeskCategory zendeskCategory =
			zendeskCategoryPersistence.findByPrimaryKey(zendeskCategoryId);

		long oldRemoteUserSegmentId = zendeskCategory.getRemoteUserSegmentId();

		zendeskCategory.setDocumentationKey(documentationKey);
		zendeskCategory.setDocumentationOriginalURL(documentationOriginalURL);
		zendeskCategory.setArticleLabels(articleLabels);
		zendeskCategory.setRemoteUserSegmentId(remoteUserSegmentId);

		zendeskCategory = zendeskCategoryPersistence.update(zendeskCategory);

		if (oldRemoteUserSegmentId != remoteUserSegmentId) {
			List<ZendeskArticle> zendeskArticles =
				zendeskArticleLocalService.getZendeskCategoryArticles(
					zendeskCategoryId);

			for (ZendeskArticle zendeskArticle : zendeskArticles) {
				zendeskArticleLocalService.updateRemoteUserSegmentId(
					zendeskArticle.getZendeskArticleId(), remoteUserSegmentId);
			}
		}

		return zendeskCategory;
	}

}