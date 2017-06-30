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

package com.liferay.osb.corpadmin.util;

import com.liferay.compat.portal.kernel.search.BaseIndexer;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.persistence.CorpEntryActionableDynamicQuery;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class CorpAdminIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {
		CorpEntry.class.getName()
	};

	public static final String PORTLET_ID = OSBPortletKeys.OSB_CORP_ADMIN;

	public CorpAdminIndexer() {
		setSortableTextFields(new String[] {"name"});
	}

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	public void postProcessFullQuery(
			BooleanQuery fullQuery, SearchContext searchContext)
		throws Exception {

		String name = GetterUtil.getString(
			searchContext.getAttribute(Field.NAME));

		if (Validator.isNotNull(name)) {
			BooleanQuery nameQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			nameQuery.addTerm(Field.NAME, name, true);

			fullQuery.add(nameQuery, BooleanClauseOccur.MUST);
		}

		int status = GetterUtil.getInteger(
			searchContext.getAttribute("status"));

		if (status != WorkflowConstants.STATUS_ANY) {
			BooleanQuery statusQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			statusQuery.addExactTerm("status", status);

			fullQuery.add(statusQuery, BooleanClauseOccur.MUST);
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, false);
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
				field.concat(StringPool.UNDERLINE).concat(languageId), value);
		}
	}

	@Override
	protected void addSearchGroupId(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		CorpEntry corpEntry = (CorpEntry)obj;

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, corpEntry.getCorpEntryId());

		SearchEngineUtil.deleteDocument(
			OSBConstants.COMPANY_ID, document.get(Field.UID));
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		CorpEntry corpEntry = (CorpEntry)obj;

		Document document = getBaseModelDocument(PORTLET_ID, corpEntry);

		document.addKeyword(Field.CLASS_PK, corpEntry.getCorpEntryId());
		document.addKeyword(Field.COMPANY_ID, OSBConstants.COMPANY_ID);
		document.addDate(Field.CREATE_DATE, corpEntry.getCreateDate());
		addLocalizedText(
			document, Field.DESCRIPTION, corpEntry.getDescription());
		document.addDate(Field.MODIFIED_DATE, corpEntry.getModifiedDate());
		addSortableText(document, Field.NAME, corpEntry.getName());
		document.addText(Field.TITLE, corpEntry.getName());
		document.addText(Field.URL, corpEntry.getWebsite());
		document.addKeyword(Field.USER_ID, corpEntry.getUserId());

		String userName = PortalUtil.getUserName(
			corpEntry.getUserId(), corpEntry.getUserName());

		document.addKeyword(Field.USER_NAME, userName, true);

		document.addKeyword(
			"contactEmailAddress", corpEntry.getContactEmailAddress(), true);
		document.addKeyword("countryCode", corpEntry.getCountryCode(), true);
		document.addKeyword("countryId", corpEntry.getCountryId());
		document.addKeyword("faxNumber", corpEntry.getFaxNumber());
		document.addKeyword("phoneNumber", corpEntry.getPhoneNumber());
		document.addKeyword(
			"profileEmailAddress", corpEntry.getProfileEmailAddress(), true);
		document.addKeyword("status", corpEntry.getStatus());

		String statusByUserName = PortalUtil.getUserName(
			corpEntry.getStatusByUserId(), corpEntry.getStatusByUserName());

		document.addKeyword("statusByUserName", statusByUserName, true);
		document.addDate("statusDate", corpEntry.getStatusDate());

		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (orderByCol.equals("create-date")) {
			return "createDate";
		}

		return orderByCol;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		String name = document.get(Field.TITLE);

		String description = snippet;

		if (Validator.isNull(snippet)) {
			description = StringUtil.shorten(
				document.get(Field.DESCRIPTION), 200);
		}

		String corpEntryId = document.get(Field.CLASS_PK);

		portletURL.setParameter("corpEntryId", corpEntryId);

		return new Summary(name, description, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		CorpEntry corpEntry = (CorpEntry)obj;

		reindex(corpEntry);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(classPK);

		doReindex(corpEntry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		if (companyId != OSBConstants.COMPANY_ID) {
			return;
		}

		reindexCorpEntries(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindex(CorpEntry corpEntry) throws SearchException {
		Document document = getDocument(corpEntry);

		SearchEngineUtil.updateDocument(OSBConstants.COMPANY_ID, document);
	}

	protected void reindexCorpEntries(long companyId) throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new CorpEntryActionableDynamicQuery() {

			@Override
			protected void performAction(Object object) throws PortalException {
				CorpEntry corpEntry = (CorpEntry)object;

				Document document = getDocument(corpEntry);

				addDocument(document);
			}

		};

		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

}