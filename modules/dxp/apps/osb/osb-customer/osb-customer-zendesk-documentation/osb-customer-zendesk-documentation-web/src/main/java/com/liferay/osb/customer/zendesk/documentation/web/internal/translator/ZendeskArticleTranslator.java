/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.web.internal.translator;

import com.liferay.osb.customer.zendesk.constants.ZendeskLocales;
import com.liferay.osb.customer.zendesk.documentation.sync.configuration.ZendeskDocumentationSyncConfigurationValues;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskArticleLocalService;
import com.liferay.osb.customer.zendesk.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.model.ZendeskTranslation;
import com.liferay.osb.customer.zendesk.web.service.ZendeskArticleWebService;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskArticleTranslator.class)
public class ZendeskArticleTranslator extends BaseTranslator<ZendeskArticle> {

	protected boolean continueTranslating(
		ZendeskArticle zendeskArticle, Date stopDate) {

		if (stopDate.after(zendeskArticle.getUpdateDate())) {
			return false;
		}

		return true;
	}

	protected String getSortBy() {
		return "updated_at";
	}

	@Override
	protected boolean isAdd(ZendeskArticle zendeskArticle) {
		if (zendeskArticle.isDraft()) {
			return false;
		}

		List<ZendeskTranslation> zendeskTranslations =
			zendeskArticle.getZendeskTranslations();

		String[] zendeskLocales = ZendeskLocales.ZENDESK_LOCALES_ENABLED;

		if (zendeskTranslations.size() >= zendeskLocales.length) {
			return false;
		}

		for (ZendeskTranslation zendeskTranslation : zendeskTranslations) {
			String zendeskLocale = zendeskTranslation.getLocale();

			if (zendeskLocale.equals(ZendeskLocales.US)) {
				return true;
			}
		}

		return false;
	}

	protected boolean isManualTranslation(
		ZendeskArticle zendeskArticle, String zendeskLocale) {

		Set<String> labelNames = zendeskArticle.getLabelNames();

		if (labelNames.contains(zendeskLocale)) {
			return true;
		}

		return false;
	}

	protected boolean isUpdate(ZendeskArticle zendeskArticle) {
		if (zendeskArticle.isDraft()) {
			return false;
		}

		List<ZendeskTranslation> zendeskTranslations =
			zendeskArticle.getZendeskTranslations();

		if (zendeskTranslations.size() == 1) {
			return false;
		}

		String sourceLocale = zendeskArticle.getSourceLocale();

		if (!sourceLocale.equals(ZendeskLocales.US)) {
			return false;
		}

		return true;
	}

	protected SearchHits<ZendeskArticle> search(Query query)
		throws PortalException {

		query.addParameter(
			"category",
			ZendeskDocumentationSyncConfigurationValues.
				ZENDESK_DOCUMENTATION_TRANSLATOR_CATEGORY_IDS);

		return _zendeskArticleWebService.getZendeskArticles(query);
	}

	@Reference
	private ZendeskArticleLocalService _zendeskArticleLocalService;

	@Reference
	private ZendeskArticleWebService _zendeskArticleWebService;

}