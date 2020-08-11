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

package com.liferay.osb.customer.web.internal.service;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.JournalArticleLocalServiceWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ServiceWrapper.class)
public class OSBJournalArticleLocalServiceWrapper
	extends JournalArticleLocalServiceWrapper {

	public OSBJournalArticleLocalServiceWrapper() {
		super(null);
	}

	public OSBJournalArticleLocalServiceWrapper(
		JournalArticleLocalService journalArticleLocalService) {

		super(journalArticleLocalService);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JournalArticle updateArticle(
			long userId, long groupId, long folderId, String articleId,
			double version, Map<Locale, String> titleMap,
			Map<Locale, String> descriptionMap, String content,
			String ddmStructureKey, String ddmTemplateKey, String layoutUuid,
			int displayDateMonth, int displayDateDay, int displayDateYear,
			int displayDateHour, int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute,
			boolean neverExpire, int reviewDateMonth, int reviewDateDay,
			int reviewDateYear, int reviewDateHour, int reviewDateMinute,
			boolean neverReview, boolean indexable, boolean smallImage,
			String smallImageURL, File smallImageFile,
			Map<String, byte[]> images, String articleURL,
			ServiceContext serviceContext)
		throws PortalException {

		JournalArticle article = super.updateArticle(
			userId, groupId, folderId, articleId, version, titleMap,
			descriptionMap, content, ddmStructureKey, ddmTemplateKey,
			layoutUuid, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay,
			reviewDateYear, reviewDateHour, reviewDateMinute, neverReview,
			indexable, smallImage, smallImageURL, smallImageFile, images,
			articleURL, serviceContext);

		String urlTitle = ParamUtil.getString(serviceContext, "urlTitle");

		updateUrlTitles(groupId, articleId, urlTitle);

		return article;
	}

	protected void updateUrlTitles(
			long groupId, String articleId, String urlTitle)
		throws PortalException {

		if (Validator.isNull(urlTitle)) {
			urlTitle = getUniqueUrlTitle(groupId, articleId, urlTitle);
		}

		JournalArticle article = fetchArticleByUrlTitle(groupId, urlTitle);

		if ((article != null) && !articleId.equals(article.getArticleId())) {
			urlTitle = getUniqueUrlTitle(groupId, articleId, urlTitle);
		}

		List<JournalArticle> articles = getArticles(groupId, articleId);

		for (JournalArticle curArticle : articles) {
			if (!urlTitle.equals(curArticle.getUrlTitle())) {
				curArticle.setUrlTitle(urlTitle);

				updateJournalArticle(curArticle);
			}
		}
	}

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

}