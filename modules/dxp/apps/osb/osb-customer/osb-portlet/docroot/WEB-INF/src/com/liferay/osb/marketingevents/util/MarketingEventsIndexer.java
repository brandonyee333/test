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

package com.liferay.osb.marketingevents.util;

import com.liferay.compat.portal.kernel.search.BaseIndexer;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.MarketingEvent;
import com.liferay.osb.model.MarketingEventConstants;
import com.liferay.osb.service.MarketingEventLocalServiceUtil;
import com.liferay.osb.service.persistence.MarketingEventActionableDynamicQuery;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import java.text.Format;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Peter Shin
 */
public class MarketingEventsIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {
		MarketingEvent.class.getName()
	};

	public static final String PORTLET_ID = OSBPortletKeys.OSB_MARKETING_EVENTS;

	public MarketingEventsIndexer() {
		setSortableTextFields(new String[] {"name"});
	}

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public String getPortletId() {
		return PORTLET_ID;
	}

	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(searchQuery, searchContext, Field.TITLE, true);
		addSearchArrayQuery(searchQuery, searchContext, "globalRegion");
		addSearchLocalizedTerm(searchQuery, searchContext, "summary", true);
		addSearchArrayQuery(searchQuery, searchContext, "type");

		Date startDateGT = (Date)searchContext.getAttribute("startDateGT");
		Date startDateLT = (Date)searchContext.getAttribute("startDateLT");

		addSearchDateQuery(
			searchQuery, searchContext, "startDate", startDateGT, startDateLT);
	}

	protected void addLocalizedText(
		Document document, String field, String xml) {

		String defaultLanguageId = LocalizationUtil.getDefaultLocale(xml);

		Map<Locale, String> valueMap = LocalizationUtil.getLocalizationMap(xml);

		for (Map.Entry<Locale, String> valueEntry : valueMap.entrySet()) {
			Locale locale = valueEntry.getKey();
			String value = valueEntry.getValue();

			if (Validator.isNull(value)) {
				continue;
			}

			String languageId = LocaleUtil.toLanguageId(locale);

			if (languageId.equals(defaultLanguageId)) {
				document.addText(field, value);
			}

			document.addText(
				DocumentImpl.getLocalizedName(locale, field), value);
		}
	}

	protected void addSearchDateQuery(
			BooleanQuery searchQuery, SearchContext searchContext, String field,
			Date dateGT, Date dateLT)
		throws ParseException {

		if ((dateGT == null) && (dateLT == null)) {
			return;
		}

		String startValue = null;
		String endValue = null;

		if (dateGT != null) {
			startValue = _dateFormat.format(dateGT);
		}

		if (dateLT != null) {
			endValue = _dateFormat.format(dateLT);
		}

		BooleanQuery booleanQuery = BooleanQueryFactoryUtil.create(
			searchContext);

		booleanQuery.addRangeTerm(field, startValue, endValue);

		if (searchContext.isAndSearch()) {
			searchQuery.add(booleanQuery, BooleanClauseOccur.MUST);
		}
		else {
			searchQuery.add(booleanQuery, BooleanClauseOccur.SHOULD);
		}
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		MarketingEvent marketingEvent = (MarketingEvent)obj;

		deleteDocument(
			marketingEvent.getCompanyId(),
			marketingEvent.getMarketingEventId());
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		MarketingEvent marketingEvent = (MarketingEvent)obj;

		Document document = getBaseModelDocument(PORTLET_ID, marketingEvent);

		document.addKeyword(
			Field.CLASS_PK, marketingEvent.getMarketingEventId());
		document.addKeyword(Field.COMPANY_ID, marketingEvent.getCompanyId());
		document.addDate(Field.CREATE_DATE, marketingEvent.getCreateDate());
		document.addDate(Field.MODIFIED_DATE, marketingEvent.getModifiedDate());
		document.addKeyword(Field.TYPE, marketingEvent.getType());
		document.addKeyword(Field.USER_ID, marketingEvent.getUserId());
		document.addKeyword("globalRegion", marketingEvent.getGlobalRegion());
		document.addDate("startDate", marketingEvent.getStartDate());

		addLocalizedText(
			document, Field.TITLE,
			HtmlUtil.extractText(marketingEvent.getTitle()));
		addLocalizedText(
			document, "summary",
			HtmlUtil.extractText(marketingEvent.getSummary()));

		addSortableText(
			document, MarketingEventsUtil.getSortField(null, "start-date"),
			_dateFormat.format(marketingEvent.getStartDate()));

		for (Locale locale : LanguageUtil.getAvailableLocales()) {
			String globalRegionLabel =
				MarketingEventConstants.getGlobalRegionLabel(
					marketingEvent.getGlobalRegion());
			String typeLabel = MarketingEventConstants.getTypeLabel(
				marketingEvent.getType());

			addSortableText(
				document,
				MarketingEventsUtil.getSortField(locale, "global-region"),
				LanguageUtil.get(locale, globalRegionLabel));
			addSortableText(
				document, MarketingEventsUtil.getSortField(locale, "title"),
				marketingEvent.getTitle(locale));
			addSortableText(
				document, MarketingEventsUtil.getSortField(locale, "type"),
				LanguageUtil.get(locale, typeLabel));
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		String title = document.get(Field.TITLE);

		String content = snippet;

		if (Validator.isNull(snippet)) {
			content = StringUtil.shorten(
				HtmlUtil.extractText(document.get("summary")), 200);
		}

		String classPK = document.get(Field.ENTRY_CLASS_PK);

		portletURL.setParameter(
			"mvcPath", "/marketing_events/view_marketing_event.jsp");
		portletURL.setParameter("marketingEventId", classPK);

		return new Summary(title, content, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		MarketingEvent marketingEvent = (MarketingEvent)obj;

		reindex(marketingEvent);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		MarketingEvent marketingEvent =
			MarketingEventLocalServiceUtil.getMarketingEvent(classPK);

		doReindex(marketingEvent);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		ActionableDynamicQuery actionableDynamicQuery =
			new MarketingEventActionableDynamicQuery() {

			@Override
			protected void performAction(Object object) throws PortalException {
				MarketingEvent marketingEvent = (MarketingEvent)object;

				addDocument(getDocument(marketingEvent));
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindex(MarketingEvent marketingEvent)
		throws SearchException {

		Document document = getDocument(marketingEvent);

		SearchEngineUtil.updateDocument(
			getSearchEngineId(), marketingEvent.getCompanyId(), document);
	}

	private static Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat(
			PropsUtil.get(PropsKeys.INDEX_DATE_FORMAT_PATTERN));

}