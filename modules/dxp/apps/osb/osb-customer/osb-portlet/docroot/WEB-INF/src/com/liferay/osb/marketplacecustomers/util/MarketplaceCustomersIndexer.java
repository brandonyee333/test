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

package com.liferay.osb.marketplacecustomers.util;

import com.liferay.compat.portal.kernel.search.BaseIndexer;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.persistence.AssetReceiptActionableDynamicQuery;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Ryan Park
 */
public class MarketplaceCustomersIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {AssetReceipt.class.getName()};

	public static final String PORTLET_ID =
		OSBPortletKeys.OSB_MARKETPLACE_CUSTOMERS;

	public MarketplaceCustomersIndexer() {
		setSortableTextFields(new String[] {"ownerName"});
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

	protected void addContextQueryParams(
		BooleanQuery contextQuery, SearchContext searchContext, String key,
		Object value) {

		contextQuery.addRequiredTerm(key, String.valueOf(value));
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

		searchQuery.addTerm(Field.USER_NAME, keywords, true);

		searchQuery.addTerm("legalEntityName", keywords, true);
		searchQuery.addTerm("ownerName", keywords, true);
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		AssetReceipt assetReceipt = (AssetReceipt)obj;

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, assetReceipt.getAssetReceiptId());

		SearchEngineUtil.deleteDocument(
			OSBConstants.COMPANY_ID, document.get(Field.UID));
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		AssetReceipt assetReceipt = (AssetReceipt)obj;

		Document document = getBaseModelDocument(PORTLET_ID, assetReceipt);

		document.addKeyword(Field.COMPANY_ID, OSBConstants.COMPANY_ID);
		document.addDate(Field.CREATE_DATE, assetReceipt.getCreateDate());
		document.addKeyword(
			Field.ENTRY_CLASS_NAME, AssetReceipt.class.getName());
		document.addKeyword(
			Field.ENTRY_CLASS_PK, assetReceipt.getAssetReceiptId());
		document.addKeyword(Field.USER_ID, assetReceipt.getUserId());

		String userName = PortalUtil.getUserName(
			assetReceipt.getUserId(), assetReceipt.getUserName());

		document.addKeyword(Field.USER_NAME, userName, true);

		document.addText("legalEntityName", assetReceipt.getLegalEntityName());
		document.addKeyword(
			"ownerClassNameId", assetReceipt.getOwnerClassNameId());
		document.addKeyword("ownerClassPK", assetReceipt.getOwnerClassPK());

		String ownerName = assetReceipt.getOwnerName();

		addSortableText(document, "ownerName", ownerName);

		document.addKeyword(
			"productClassNameId", assetReceipt.getProductClassNameId());
		document.addKeyword("productClassPK", assetReceipt.getProductClassPK());

		String productClassName = assetReceipt.getProductClassName();

		if (productClassName.equals(AppEntry.class.getName())) {
			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
				assetReceipt.getProductClassPK());

			document.addKeyword(
				"developerEntryId", appEntry.getDeveloperEntryId());
			document.addKeyword("free", appEntry.isFree());
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		String title = document.get(locale, "ownerName");

		String assetReceiptId = document.get(Field.ENTRY_CLASS_PK);

		portletURL.setParameter("assetReceiptId", assetReceiptId);

		return new Summary(title, snippet, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		AssetReceipt assetReceipt = (AssetReceipt)obj;

		reindex(assetReceipt);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		AssetReceipt assetReceipt =
			AssetReceiptLocalServiceUtil.getAssetReceipt(classPK);

		doReindex(assetReceipt);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		if (companyId != OSBConstants.COMPANY_ID) {
			return;
		}

		reindexAssetReceipts(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindex(AssetReceipt assetReceipt) throws SearchException {
		Document document = getDocument(assetReceipt);

		SearchEngineUtil.updateDocument(OSBConstants.COMPANY_ID, document);
	}

	protected void reindexAssetReceipts(long companyId) throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new AssetReceiptActionableDynamicQuery() {

			@Override
			protected void addDefaultCriteria(DynamicQuery dynamicQuery) {
			}

			@Override
			protected void performAction(Object object) throws PortalException {
				AssetReceipt assetReceipt = (AssetReceipt)object;

				Document document = getDocument(assetReceipt);

				addDocument(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

}