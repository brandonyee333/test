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

package com.liferay.osb.marketplace.util;

import com.liferay.compat.portal.kernel.search.BaseIndexer;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetAuditLocalServiceUtil;
import com.liferay.osb.service.persistence.AppEntryActionableDynamicQuery;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Ryan Schuhler
 */
public class MarketplaceIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {
		AppEntry.class.getName()
	};

	public static final String PORTLET_ID = OSBPortletKeys.OSB_MARKETPLACE;

	public MarketplaceIndexer() {
		setSortableTextFields(new String[] {"developerName", "title"});
	}

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public String getPortletId() {
		return PORTLET_ID;
	}

	@Override
	public void postProcessContextQuery(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {

		LinkedHashMap<String, Object> params =
			(LinkedHashMap<String, Object>)searchContext.getAttribute("params");

		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				if (value == null) {
					continue;
				}

				Class<?> clazz = value.getClass();

				if (clazz.isArray()) {
					Object[] values = (Object[])value;

					if (values.length == 0) {
						continue;
					}
				}

				addContextQueryParams(contextQuery, searchContext, key, value);
			}
		}
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, false);

		addSearchTerm(searchQuery, searchContext, "developerName", false);
	}

	protected void addContextQueryParams(
			BooleanQuery contextQuery, SearchContext searchContext, String key,
			Object value)
		throws Exception {

		if (key.equals("appEntryStatus")) {
			if (((Integer)value).intValue() >= 0) {
				contextQuery.addRequiredTerm(
					"appEntryStatus", String.valueOf(value));
			}
		}
		else if (key.equals("appVersionStatus")) {
			if (value instanceof Integer[]) {
				Integer[] values = (Integer[])value;

				BooleanQuery appVersionStatusQuery =
					BooleanQueryFactoryUtil.create(searchContext);

				for (int status : values) {
					appVersionStatusQuery.addExactTerm(
						"appVersionStatus", status);
				}

				contextQuery.add(
					appVersionStatusQuery, BooleanClauseOccur.MUST);
			}
			else if (((Integer)value).intValue() >= 0) {
				contextQuery.addRequiredTerm(
					"appVersionStatus", String.valueOf(value));
			}
		}
		else if (key.equals("assetCategoryIds")) {
			if (value instanceof Long[]) {
				Long[] values = (Long[])value;

				BooleanQuery assetCategoryQuery =
					BooleanQueryFactoryUtil.create(searchContext);

				for (long assetCategoryId : values) {
					assetCategoryQuery.addExactTerm(
						Field.ASSET_CATEGORY_IDS, assetCategoryId);
				}

				contextQuery.add(assetCategoryQuery, BooleanClauseOccur.MUST);
			}
			else {
				contextQuery.addRequiredTerm(
					Field.ASSET_CATEGORY_IDS, String.valueOf(value));
			}
		}
		else if (key.equals("assetListIds")) {
			if (value instanceof Long[]) {
				Long[] values = (Long[])value;

				BooleanQuery assetListQuery = BooleanQueryFactoryUtil.create(
					searchContext);

				for (long assetListId : values) {
					assetListQuery.addExactTerm(
						"assetListIds", String.valueOf(assetListId));
				}

				contextQuery.add(assetListQuery, BooleanClauseOccur.MUST);
			}
			else {
				contextQuery.addRequiredTerm(
					"assetListIds", String.valueOf(value));
			}
		}
		else if (key.equals("assetTagNames")) {
			if (value instanceof String[]) {
				String[] values = (String[])value;

				BooleanQuery assetTagQuery = BooleanQueryFactoryUtil.create(
					searchContext);

				for (String assetTagName : values) {
					assetTagQuery.addExactTerm(
						Field.ASSET_TAG_NAMES, assetTagName);
				}

				contextQuery.add(assetTagQuery, BooleanClauseOccur.MUST);
			}
			else {
				contextQuery.addRequiredTerm(
					Field.ASSET_TAG_NAMES, String.valueOf(value));
			}
		}
		else if (key.equals("availableCountryIds")) {
			BooleanQuery availableCountryQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			if (value instanceof Long[]) {
				Long[] values = (Long[])value;

				for (long countryId : values) {
					availableCountryQuery.addExactTerm(
						"availableCountryIds", String.valueOf(countryId));
				}
			}
			else {
				availableCountryQuery.addExactTerm(
					"availableCountryIds", String.valueOf(value));
			}

			availableCountryQuery.addExactTerm("free", true);

			contextQuery.add(availableCountryQuery, BooleanClauseOccur.MUST);
		}
		else if (key.equals("compatibility")) {
			if (value instanceof Integer) {
				Integer buildNumber = (Integer)value;

				String[] versions = PortalReleaseUtil.getSupportedVersions(
					buildNumber.intValue());

				BooleanQuery compatiblityQuery = BooleanQueryFactoryUtil.create(
					searchContext);

				for (String version : versions) {
					compatiblityQuery.addExactTerm(
						"compatibility", getEscapedCompatibility(version));
				}

				contextQuery.add(compatiblityQuery, BooleanClauseOccur.MUST);
			}
			else {
				contextQuery.addRequiredTerm(
					"compatibility", String.valueOf(value));
			}
		}
		else if (key.equals("developerEntryId")) {
			contextQuery.addRequiredTerm(
				"developerEntryId", String.valueOf(value));
		}
		else if (key.equals("free")) {
			contextQuery.addRequiredTerm("free", String.valueOf(value));
		}
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
	protected void addSearchKeywords(
			BooleanQuery searchQuery, SearchContext searchContext)
		throws Exception {

		String keywords = searchContext.getKeywords();

		if (Validator.isNull(keywords)) {
			return;
		}

		searchQuery.addTerm(Field.ASSET_CATEGORY_TITLES, keywords);
		searchQuery.addTerm(Field.ASSET_TAG_NAMES, keywords);
		searchQuery.addTerm(Field.DESCRIPTION, keywords);
		searchQuery.addTerm(Field.TITLE, keywords);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		AppEntry appEntry = (AppEntry)obj;

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, appEntry.getAppEntryId());

		SearchEngineUtil.deleteDocument(
			OSBConstants.COMPANY_ID, document.get(Field.UID));
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		AppEntry appEntry = (AppEntry)obj;

		String userName = PortalUtil.getUserName(
			appEntry.getUserId(), appEntry.getUserName());

		Document document = getBaseModelDocument(PORTLET_ID, appEntry);

		document.addKeyword(Field.COMPANY_ID, OSBConstants.COMPANY_ID);
		document.addDate(Field.CREATE_DATE, appEntry.getCreateDate());

		addLocalizedText(
			document, Field.DESCRIPTION, appEntry.getDescription());

		document.addKeyword(Field.ENTRY_CLASS_NAME, AppEntry.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, appEntry.getAppEntryId());
		document.addDate(Field.MODIFIED_DATE, appEntry.getModifiedDate());
		addSortableText(document, Field.TITLE, appEntry.getTitle());
		document.addKeyword(Field.USER_ID, appEntry.getUserId());
		document.addKeyword(Field.USER_NAME, userName, true);

		document.addKeyword("appEntryStatus", appEntry.getStatus());

		AppVersion appVersion = appEntry.getActionableAppVersion();

		document.addKeyword("appVersionStatus", appVersion.getStatus());

		document.addKeyword("assetListIds", appEntry.getAssetListIds());
		document.addKeyword(
			"availableCountryIds", appEntry.getAvailableCountryIds());

		List<String> compatibilities = new ArrayList<String>();

		for (String compatibility : appEntry.getCompatibilities()) {
			compatibilities.add(getEscapedCompatibility(compatibility));
		}

		document.addKeyword(
			"compatibility", compatibilities.toArray(new String[0]));

		document.addKeyword("demoWebsite", appEntry.getDemoWebsite());
		document.addKeyword("developerEntryId", appEntry.getDeveloperEntryId());
		addSortableText(
			document, "developerName", appEntry.getDeveloperEntryName());
		document.addKeyword(
			"documentationWebsite", appEntry.getDocumentationWebsite());
		document.addKeyword("free", appEntry.isFree());
		document.addKeyword("labs", appEntry.getLabs());

		Date createDateLE = new Date();
		Date createDateGE = new Date(createDateLE.getTime() - Time.MONTH);

		int count = AssetAuditLocalServiceUtil.getAssetAuditsCount(
			createDateGE, createDateLE, AppEntry.class.getName(),
			appEntry.getAppEntryId(), AssetAuditConstants.TYPE_VIEW);

		document.addNumber("monthlyViewCount", count);
		document.addKeyword("productType", appEntry.getProductType());

		String subcategory = MarketplaceUtil.getSubcategoryName(
			appEntry.getAssetEntry());

		document.addKeyword("subcategory", subcategory);

		document.addKeyword(
			"sourceCodeWebsite", appEntry.getSourceCodeWebsite());
		document.addKeyword("supportWebsite", appEntry.getSupportWebsite());
		document.addKeyword("website", appEntry.getWebsite());

		return document;
	}

	@Override
	protected String doGetSortField(String orderByCol) {
		if (orderByCol.equals("author") || orderByCol.equals("developer") ||
			orderByCol.equals("developer-name")) {

			return "developerName";
		}
		else if (orderByCol.equals("category")) {
			return "subcategory";
		}
		else if (orderByCol.equals("modified-date")) {
			return Field.MODIFIED_DATE;
		}

		return orderByCol;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		String title = document.get(locale, Field.TITLE);

		String description = snippet;

		if (Validator.isNull(snippet)) {
			description = StringUtil.shorten(
				document.get(locale, Field.DESCRIPTION), 200);
		}

		String appEntryId = document.get(Field.ENTRY_CLASS_PK);

		portletURL.setParameter("appEntryId", appEntryId);

		return new Summary(title, description, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		AppEntry appEntry = (AppEntry)obj;

		reindex(appEntry);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(classPK);

		doReindex(appEntry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		if (companyId != OSBConstants.COMPANY_ID) {
			return;
		}

		reindexAppEntries(companyId);
	}

	protected String getEscapedCompatibility(String compatibility) {
		return compatibility.replace(StringPool.PLUS, "plus");
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindex(AppEntry appEntry) throws SearchException {
		Document document = getDocument(appEntry);

		SearchEngineUtil.updateDocument(OSBConstants.COMPANY_ID, document);
	}

	protected void reindexAppEntries(long companyId) throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new AppEntryActionableDynamicQuery() {

			@Override
			protected void addDefaultCriteria(DynamicQuery dynamicQuery) {
			}

			@Override
			protected void performAction(Object object) throws PortalException {
				AppEntry appEntry = (AppEntry)object;

				Document document = getDocument(appEntry);

				addDocument(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

}