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

		return super.isAdd(zendeskArticle);
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