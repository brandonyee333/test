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

package com.liferay.osb.marketplaceregistration.util;

import com.liferay.compat.portal.kernel.search.BaseIndexer;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.service.persistence.DeveloperEntryActionableDynamicQuery;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.service.CountryServiceUtil;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Ryan Park
 */
public class MarketplaceRegistrationIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {
		DeveloperEntry.class.getName()
	};

	public static final String PORTLET_ID =
		OSBPortletKeys.OSB_MARKETPLACE_REGISTRATION;

	public MarketplaceRegistrationIndexer() {
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

		long developerClassNameId = GetterUtil.getLong(
			searchContext.getAttribute("developerClassNameId"));

		if (developerClassNameId > 0) {
			BooleanQuery developerClassNameIdQuery =
				BooleanQueryFactoryUtil.create(searchContext);

			developerClassNameIdQuery.addExactTerm(
				"developerClassNameId", developerClassNameId);

			fullQuery.add(developerClassNameIdQuery, BooleanClauseOccur.MUST);
		}

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

		addSearchTerm(searchQuery, searchContext, "firstName", true);
		addSearchTerm(searchQuery, searchContext, "lastName", true);
		addSearchTerm(searchQuery, searchContext, "legalEntityName", true);
		addSearchTerm(searchQuery, searchContext, "middleName", true);
	}

	@Override
	protected void addSearchGroupId(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		DeveloperEntry corpEntry = (DeveloperEntry)obj;

		Document document = new DocumentImpl();

		document.addUID(PORTLET_ID, corpEntry.getDeveloperEntryId());

		SearchEngineUtil.deleteDocument(
			OSBConstants.COMPANY_ID, document.get(Field.UID));
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		DeveloperEntry developerEntry = (DeveloperEntry)obj;

		Document document = getBaseModelDocument(PORTLET_ID, developerEntry);

		document.addKeyword(
			Field.CLASS_PK, developerEntry.getDeveloperEntryId());
		document.addKeyword(Field.COMPANY_ID, OSBConstants.COMPANY_ID);
		document.addDate(Field.CREATE_DATE, developerEntry.getCreateDate());
		addSortableText(document, Field.NAME, developerEntry.getName());

		String countryName = StringPool.BLANK;

		if (developerEntry.getCountryId() > 0) {
			Country country = CountryServiceUtil.getCountry(
				developerEntry.getCountryId());

			countryName = country.getName();
		}
		else if (developerEntry.getAddressId() > 0) {
			Address address = developerEntry.getAddress();

			Country country = address.getCountry();

			countryName = country.getName();
		}

		document.addKeyword("country", countryName);

		document.addKeyword(
			"dossieraAccountKey", developerEntry.getDossieraAccountKey());
		document.addText("emailAddress", developerEntry.getEmailAddress());
		document.addText("firstName", developerEntry.getFirstName());
		document.addText("lastName", developerEntry.getLastName());
		document.addText(
			"legalEntityName", developerEntry.getLegalEntityName());
		document.addText("middleName", developerEntry.getMiddleName());
		document.addKeyword("phoneNumber", developerEntry.getPhoneNumber());
		document.addKeyword("screenName", developerEntry.getScreenName());
		document.addKeyword("status", developerEntry.getStatus());
		document.addKeyword("type", developerEntry.getType());

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

		String name = document.get(Field.NAME);

		String developerEntryId = document.get(Field.CLASS_PK);

		portletURL.setParameter("developerEntryId", developerEntryId);

		return new Summary(name, snippet, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		DeveloperEntry corpEntry = (DeveloperEntry)obj;

		reindex(corpEntry);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntry(classPK);

		doReindex(developerEntry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		if (companyId != OSBConstants.COMPANY_ID) {
			return;
		}

		reindexDeveloperEntries(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindex(DeveloperEntry developerEntry)
		throws SearchException {

		Document document = getDocument(developerEntry);

		SearchEngineUtil.updateDocument(OSBConstants.COMPANY_ID, document);
	}

	protected void reindexDeveloperEntries(long companyId) throws Exception {
		ActionableDynamicQuery actionableDynamicQuery =
			new DeveloperEntryActionableDynamicQuery() {

			@Override
			protected void addDefaultCriteria(DynamicQuery dynamicQuery) {
			}

			@Override
			protected void performAction(Object object) throws PortalException {
				DeveloperEntry developerEntry = (DeveloperEntry)object;

				Document document = getDocument(developerEntry);

				addDocument(document);
			}

		};

		actionableDynamicQuery.setCompanyId(companyId);
		actionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		actionableDynamicQuery.performActions();
	}

}