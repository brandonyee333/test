/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.data.migration.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OSBJournalArticleService}.
 *
 * @author Brian Wing Shun Chan
 * @see OSBJournalArticleService
 * @generated
 */
@ProviderType
public class OSBJournalArticleServiceWrapper implements OSBJournalArticleService,
	ServiceWrapper<OSBJournalArticleService> {
	public OSBJournalArticleServiceWrapper(
		OSBJournalArticleService osbJournalArticleService) {
		_osbJournalArticleService = osbJournalArticleService;
	}

	@Override
	public com.liferay.journal.model.JournalArticle addArticle(long groupId,
		long folderId, long classNameId, long classPK,
		java.lang.String articleId, boolean autoArticleId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String content, java.lang.String ddmStructureKey,
		java.lang.String ddmTemplateKey, java.lang.String layoutUuid,
		int displayDateMonth, int displayDateDay, int displayDateYear,
		int displayDateHour, int displayDateMinute, int expirationDateMonth,
		int expirationDateDay, int expirationDateYear, int expirationDateHour,
		int expirationDateMinute, boolean neverExpire, int reviewDateMonth,
		int reviewDateDay, int reviewDateYear, int reviewDateHour,
		int reviewDateMinute, boolean neverReview, boolean indexable,
		java.lang.String articleURL,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _osbJournalArticleService.addArticle(groupId, folderId,
			classNameId, classPK, articleId, autoArticleId, titleMap,
			descriptionMap, content, ddmStructureKey, ddmTemplateKey,
			layoutUuid, displayDateMonth, displayDateDay, displayDateYear,
			displayDateHour, displayDateMinute, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay,
			reviewDateYear, reviewDateHour, reviewDateMinute, neverReview,
			indexable, articleURL, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _osbJournalArticleService.getOSGiServiceIdentifier();
	}

	@Override
	public OSBJournalArticleService getWrappedService() {
		return _osbJournalArticleService;
	}

	@Override
	public void setWrappedService(
		OSBJournalArticleService osbJournalArticleService) {
		_osbJournalArticleService = osbJournalArticleService;
	}

	private OSBJournalArticleService _osbJournalArticleService;
}