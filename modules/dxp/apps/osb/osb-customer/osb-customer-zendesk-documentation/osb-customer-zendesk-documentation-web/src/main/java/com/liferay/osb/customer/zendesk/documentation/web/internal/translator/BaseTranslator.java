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
import com.liferay.osb.customer.zendesk.documentation.sync.configuration.ZendeskDocumentationSyncConfigurationKeys;
import com.liferay.osb.customer.zendesk.documentation.sync.configuration.ZendeskDocumentationSyncConfigurationUtil;
import com.liferay.osb.customer.zendesk.model.TranslatableModel;
import com.liferay.osb.customer.zendesk.model.ZendeskTranslation;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTranslationWebService;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
public abstract class BaseTranslator<T extends TranslatableModel> {

	public void autoTranslate(Date stopDate) throws PortalException {
		Map<String, List<T>> manualTranslationModels = new HashMap<>();

		Query query = queryFactory.createQuery();

		query.addSideload("translations");
		query.setPage(1);
		query.setSortBy(getSortBy());
		query.setSortOrder(false);

		while (query.getPage() > 0) {
			SearchHits<T> searchHits = search(query);

			List<T> results = searchHits.getResults();

			for (T model : results) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Checking " + model.getSourceType() + StringPool.POUND +
							model.getSourceId());
				}

				if (isAdd(model)) {
					addZendeskTranslations(model);
				}
				else if (isUpdate(model)) {
					updateZendeskTranslations(model, manualTranslationModels);
				}
			}

			if (stopDate != null) {
				if (results.isEmpty()) {
					break;
				}

				T lastResult = results.get(results.size() - 1);

				if (!continueTranslating(lastResult, stopDate)) {
					break;
				}
			}

			query.setPage(searchHits.getNextPage());
		}

		notifyTranslators(manualTranslationModels);
	}

	protected void addZendeskTranslations(T model) throws PortalException {
		for (String locale : ZendeskLocales.LOCALES_ENABLED) {
			if (locale.equals(ZendeskLocales.US)) {
				continue;
			}

			zendeskTranslationWebService.addZendeskTranslation(
				model.getSourceType(), model.getSourceId(), locale,
				model.getTitle(), model.getBody());
		}
	}

	protected abstract boolean continueTranslating(T model, Date stopDate);

	protected abstract String getSortBy();

	protected boolean isAdd(T model) {
		List<ZendeskTranslation> zendeskTranslations =
			model.getZendeskTranslations();

		if (zendeskTranslations.size() != 1) {
			return false;
		}

		ZendeskTranslation zendeskTranslation = zendeskTranslations.get(0);

		String locale = zendeskTranslation.getLocale();

		if (!locale.equals(ZendeskLocales.US)) {
			return false;
		}

		return true;
	}

	protected boolean isManualTranslation(T model, String locale) {
		return false;
	}

	protected boolean isUpdate(T model) {
		return false;
	}

	protected void notifyTranslators(
			Map<String, List<T>> manualTranslationModels)
		throws PortalException {

		for (Map.Entry<String, List<T>> entry :
				manualTranslationModels.entrySet()) {

			String locale = entry.getKey();
			List<T> models = entry.getValue();

			SubscriptionSender subscriptionSender = new SubscriptionSender();

			String body = ContentUtil.get(
				BaseTranslator.class.getClassLoader(),
				"com/liferay/osb/customer/zendesk/documentation/sync" +
					"/dependencies/email_article_updated_body.tmpl");

			StringBundler sb = new StringBundler(models.size() * 5);

			for (T model : models) {
				sb.append("<a href=\"");
				sb.append(model.getHtmlUrl());
				sb.append("\">");
				sb.append(model.getHtmlUrl());
				sb.append("</a><br />");
			}

			body = StringUtil.replace(
				body, new String[] {"[$LOCALE$]", "[$ZENDESK_ARTICLES$]"},
				new String[] {locale, sb.toString()});

			subscriptionSender.setBody(body);

			subscriptionSender.setCompanyId(portal.getDefaultCompanyId());
			subscriptionSender.setFrom(
				"noreply@liferay.com", "Liferay Help Center Team");
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setMailId("translation");
			subscriptionSender.setReplyToAddress("noreply@liferay.com");

			String subject = ContentUtil.get(
				BaseTranslator.class.getClassLoader(),
				"com/liferay/osb/customer/zendesk/documentation/sync" +
					"/dependencies/email_article_updated_subject.tmpl");

			subject = StringUtil.replace(subject, "[$LOCALE$]", locale);

			subscriptionSender.setSubject(subject);

			String emailAddress = ZendeskDocumentationSyncConfigurationUtil.get(
				ZendeskDocumentationSyncConfigurationKeys.
					ZENDESK_DOCUMENTATION_TRANSLATOR,
				new Filter(locale));

			if (Validator.isNull(emailAddress)) {
				emailAddress = ZendeskDocumentationSyncConfigurationUtil.get(
					ZendeskDocumentationSyncConfigurationKeys.
						ZENDESK_DOCUMENTATION_TRANSLATOR,
					new Filter("global"));
			}

			subscriptionSender.addRuntimeSubscribers(
				emailAddress, "Help Center Translator");

			subscriptionSender.flushNotificationsAsync();
		}
	}

	protected abstract SearchHits<T> search(Query query) throws PortalException;

	protected void updateZendeskTranslations(
			T model, Map<String, List<T>> manualTranslationModels)
		throws PortalException {

		boolean translationUpdated = false;
		Set<String> manualTranslationLocales = new HashSet<>();

		List<ZendeskTranslation> zendeskTranslations =
			model.getZendeskTranslations();

		for (ZendeskTranslation zendeskTranslation : zendeskTranslations) {
			String locale = zendeskTranslation.getLocale();

			if (locale.equals(ZendeskLocales.US)) {
				continue;
			}

			String body = zendeskTranslation.getBody();
			String title = zendeskTranslation.getTitle();

			if (body.equals(model.getBody()) &&
				title.equals(model.getTitle())) {

				continue;
			}

			if (isManualTranslation(model, locale)) {
				manualTranslationLocales.add(locale);

				continue;
			}

			zendeskTranslationWebService.updateZendeskTranslation(
				model.getSourceType(), model.getSourceId(), locale,
				model.getTitle(), model.getBody());

			translationUpdated = true;
		}

		if (translationUpdated) {
			for (String locale : manualTranslationLocales) {
				List<T> models = manualTranslationModels.get(locale);

				if (models == null) {
					models = new ArrayList<>();

					manualTranslationModels.put(locale, models);
				}

				models.add(model);
			}
		}
	}

	@Reference
	protected Portal portal;

	@Reference
	protected QueryFactory queryFactory;

	@Reference
	protected ZendeskTranslationWebService zendeskTranslationWebService;

	private static final Log _log = LogFactoryUtil.getLog(BaseTranslator.class);

}