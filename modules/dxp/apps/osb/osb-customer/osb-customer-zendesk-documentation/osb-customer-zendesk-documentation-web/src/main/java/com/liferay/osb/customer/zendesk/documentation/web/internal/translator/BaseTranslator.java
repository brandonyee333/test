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

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskLocales;
import com.liferay.osb.customer.zendesk.model.TranslatableModel;
import com.liferay.osb.customer.zendesk.model.ZendeskTranslation;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTranslationWebService;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseTranslator<T extends TranslatableModel> {

	public void autoTranslate(Date createdAfterDate) throws PortalException {
		Query query = queryFactory.createQuery();

		query.addSideload("translations");
		query.setPage(1);
		query.setSortBy("created_at");
		query.setSortOrder(false);

		while (query.getPage() > 0) {
			SearchHits<T> searchHits = search(query);

			List<T> results = searchHits.getResults();

			for (T result : results) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Checking " + result.getSourceType() +
							StringPool.POUND + result.getSourceId());
				}

				checkZendeskTranslations(result.getZendeskTranslations());
			}

			if (createdAfterDate != null) {
				if (results.isEmpty()) {
					break;
				}

				T lastResult = results.get(results.size() - 1);

				if (createdAfterDate.after(lastResult.getCreateDate())) {
					break;
				}
			}

			query.setPage(searchHits.getNextPage());
		}
	}

	protected void checkZendeskTranslations(
			List<ZendeskTranslation> zendeskTranslations)
		throws PortalException {

		if (zendeskTranslations.size() != 1) {
			return;
		}

		ZendeskTranslation zendeskTranslation = zendeskTranslations.get(0);

		String locale = zendeskTranslation.getLocale();

		if (!locale.equals(ZendeskLocales.US)) {
			return;
		}

		for (String curLocale : ZendeskLocales.LOCALES_ENABLED) {
			if (curLocale.equals(ZendeskLocales.US)) {
				continue;
			}

			zendeskTranslationWebService.addZendeskTranslation(
				zendeskTranslation.getSourceType(),
				zendeskTranslation.getSourceId(), curLocale,
				zendeskTranslation.getTitle(), zendeskTranslation.getBody());
		}
	}

	protected abstract SearchHits<T> search(Query query) throws PortalException;

	@Reference
	protected QueryFactory queryFactory;

	@Reference
	protected ZendeskTranslationWebService zendeskTranslationWebService;

	private static final Log _log = LogFactoryUtil.getLog(BaseTranslator.class);

}