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

package com.liferay.osb.corpmembers.util;

import com.liferay.compat.portal.kernel.search.BaseIndexer;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.CorpMembershipRequest;
import com.liferay.osb.service.CorpMembershipRequestLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Douglas Wong
 */
public class CorpMembersIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {
		CorpMembershipRequest.class.getName()
	};

	public static final String PORTLET_ID = OSBPortletKeys.OSB_CORP_MEMBERS;

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

		long corpEntryId = GetterUtil.getLong(
			searchContext.getAttribute("corpEntryId"));

		if (corpEntryId > 0) {
			BooleanQuery corpEntryQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			corpEntryQuery.addExactTerm("corpEntryId", corpEntryId);

			fullQuery.add(corpEntryQuery, BooleanClauseOccur.MUST);
		}

		int[] statuses = (int[])searchContext.getAttribute("statuses");

		if (ArrayUtil.isNotEmpty(statuses)) {
			BooleanQuery statusQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			for (int status : statuses) {
				statusQuery.addExactTerm(Field.STATUS, status);
			}

			fullQuery.add(statusQuery, BooleanClauseOccur.MUST);
		}

		String userName = GetterUtil.getString(
			searchContext.getAttribute(Field.USER_NAME));

		if (Validator.isNotNull(userName)) {
			BooleanQuery nameQuery = BooleanQueryFactoryUtil.create(
				searchContext);

			nameQuery.addTerm(Field.USER_NAME, userName, true);

			fullQuery.add(nameQuery, BooleanClauseOccur.MUST);
		}
	}

	@Override
	protected void addSearchGroupId(
			BooleanQuery contextQuery, SearchContext searchContext)
		throws Exception {
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		CorpMembershipRequest corpMembershipRequest =
			(CorpMembershipRequest)obj;

		Document document = new DocumentImpl();

		document.addUID(
			PORTLET_ID, corpMembershipRequest.getCorpMembershipRequestId());

		SearchEngineUtil.deleteDocument(
			OSBConstants.COMPANY_ID, document.get(Field.UID));
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		CorpMembershipRequest corpMembershipRequest =
			(CorpMembershipRequest)obj;

		Document document = getBaseModelDocument(
			PORTLET_ID, corpMembershipRequest);

		document.addKeyword(
			Field.CLASS_PK, corpMembershipRequest.getCorpMembershipRequestId());
		document.addDate(
			Field.CREATE_DATE, corpMembershipRequest.getCreateDate());
		document.addDate(
			Field.MODIFIED_DATE, corpMembershipRequest.getModifiedDate());
		document.addKeyword(Field.STATUS, corpMembershipRequest.getStatus());
		document.addKeyword(Field.USER_ID, corpMembershipRequest.getUserId());

		String userName = PortalUtil.getUserName(
			corpMembershipRequest.getUserId(),
			corpMembershipRequest.getUserName());

		document.addKeyword(Field.USER_NAME, userName, true);

		document.addKeyword(
			"corpEntryId", corpMembershipRequest.getCorpEntryId());
		document.addKeyword(
			"emailAddress", corpMembershipRequest.getEmailAddress());

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		String userName = document.get(Field.USER_NAME);

		String description = snippet;

		if (Validator.isNull(snippet)) {
			description = StringUtil.shorten(
				document.get(Field.DESCRIPTION), 200);
		}

		String corpMembershipRequestId = document.get(Field.CLASS_PK);

		portletURL.setParameter(
			"corpMembershipRequestId", corpMembershipRequestId);

		return new Summary(userName, description, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		CorpMembershipRequest corpMembershipRequest =
			(CorpMembershipRequest)obj;

		reindex(corpMembershipRequest);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CorpMembershipRequest corpMembershipRequest =
			CorpMembershipRequestLocalServiceUtil.getCorpMembershipRequest(
				classPK);

		doReindex(corpMembershipRequest);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		if (companyId != OSBConstants.COMPANY_ID) {
			return;
		}

		int count =
			CorpMembershipRequestLocalServiceUtil.
				getCorpMembershipRequestsCount();

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindex(companyId, start, end);
		}
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected void reindex(CorpMembershipRequest corpMembershipRequest)
		throws SearchException {

		Document document = getDocument(corpMembershipRequest);

		SearchEngineUtil.updateDocument(OSBConstants.COMPANY_ID, document);
	}

	protected void reindex(long companyId, int start, int end)
		throws Exception {

		List<CorpMembershipRequest> corpMembershipRequests =
			CorpMembershipRequestLocalServiceUtil.getCorpMembershipRequests(
				start, end);

		if (corpMembershipRequests.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>();

		for (CorpMembershipRequest corpMembershipRequest :
				corpMembershipRequests) {

			Document document = getDocument(corpMembershipRequest);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(companyId, documents);
	}

}