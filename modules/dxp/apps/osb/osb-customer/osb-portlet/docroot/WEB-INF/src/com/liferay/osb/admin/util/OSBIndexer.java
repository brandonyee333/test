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

package com.liferay.osb.admin.util;

import com.liferay.compat.portal.kernel.search.BaseIndexer;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.Region;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Raymond Augé
 * @author Amos Fong
 */
public class OSBIndexer extends BaseIndexer {

	public static final String[] CLASS_NAMES = {
		TrainingEvent.class.getName()
	};

	public static final String PORTLET_ID = OSBPortletKeys.OSB_ADMIN;

	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	public String getPortletId() {
		return PORTLET_ID;
	}

	protected void deleteTrainingEvent(TrainingEvent trainingEvent)
		throws SearchException {

		Document document = new DocumentImpl();

		document.addUID(
			PORTLET_ID, TrainingEvent.class.getName(),
			String.valueOf(trainingEvent.getTrainingEventId()));

		SearchEngineUtil.deleteDocument(
			OSBConstants.COMPANY_ID, document.get(Field.UID));
	}

	@Override
	protected void doDelete(Object obj) throws Exception {
		if (obj instanceof TrainingEvent) {
			deleteTrainingEvent((TrainingEvent)obj);
		}
	}

	@Override
	protected Document doGetDocument(Object obj) throws Exception {
		Document document = new DocumentImpl();

		if (obj instanceof TrainingEvent) {
			document = getTrainingEventDocument((TrainingEvent)obj);
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletURL portletURL) {

		// Title

		String title = document.get(Field.TITLE);

		// Content

		String description = snippet;

		if (Validator.isNull(snippet)) {
			description = StringUtil.shorten(
				document.get(Field.DESCRIPTION), 200);
		}

		// Portlet URL

		String entryClassName = document.get(Field.ENTRY_CLASS_NAME);
		String entryClassPK = document.get(Field.ENTRY_CLASS_PK);

		if (entryClassName.equals(TrainingEvent.class.getName())) {
			portletURL.setParameter("trainingEventId", entryClassPK);
		}

		return new Summary(title, description, portletURL);
	}

	@Override
	protected void doReindex(Object obj) throws Exception {
		if (obj instanceof TrainingEvent) {
			reindexTrainingEvent((TrainingEvent)obj);
		}
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		if (className.equals(TrainingEvent.class.getName())) {
			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEvent(classPK);

			doReindex(trainingEvent);
		}
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		if (companyId != OSBConstants.COMPANY_ID) {
			return;
		}

		reindexEvents(companyId);
	}

	@Override
	protected String getPortletId(SearchContext searchContext) {
		return PORTLET_ID;
	}

	protected Document getTrainingEventDocument(TrainingEvent trainingEvent)
		throws Exception {

		long companyId = OSBConstants.COMPANY_ID;
		long userId = trainingEvent.getUserId();
		String userName = trainingEvent.getUserName();
		Date createDate = trainingEvent.getCreateDate();
		Date modifiedDate = trainingEvent.getModifiedDate();
		long trainingEventId = trainingEvent.getTrainingEventId();
		long trainingCertificateTemplateId =
			trainingEvent.getTrainingCertificateTemplateId();
		long trainingCourseId = trainingEvent.getTrainingCourseId();
		long trainingLocationId = trainingEvent.getTrainingLocationId();
		int type = trainingEvent.getType();
		Date startDate = trainingEvent.getStartDate();
		Date endDate = trainingEvent.getEndDate();
		int maxCustomers = trainingEvent.getMaxCustomers();
		String enrollmentURL = trainingEvent.getEnrollmentURL();

		TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();

		String name = trainingCourse.getName();
		String description = trainingCourse.getDescription();
		int creditAmount = trainingCourse.getCreditAmount();

		Address address = trainingEvent.getAddress();

		String city = address.getCity();
		String street1 = address.getStreet1();
		String street2 = address.getStreet2();
		String street3 = address.getStreet3();
		String zip = address.getZip();

		Country country = address.getCountry();

		String countryName = country.getName();

		Region region = address.getRegion();

		String regionCode = region.getRegionCode();

		userName = PortalUtil.getUserName(userId, userName);

		Document document = new DocumentImpl();

		document.addUID(
			PORTLET_ID, TrainingEvent.class.getName(),
			String.valueOf(trainingEventId));

		document.addDate(Field.CREATE_DATE, createDate);
		document.addDate(Field.MODIFIED_DATE, modifiedDate);

		document.addKeyword(Field.COMPANY_ID, companyId);
		document.addKeyword(Field.PORTLET_ID, PORTLET_ID);
		document.addKeyword(Field.USER_ID, userId);
		document.addText(Field.USER_NAME, userName);

		document.addText(Field.NAME, name);
		document.addText(Field.DESCRIPTION, description);

		document.addKeyword(
			Field.ENTRY_CLASS_NAME, TrainingEvent.class.getName());
		document.addKeyword(Field.ENTRY_CLASS_PK, trainingEventId);

		document.addDate("endDate", endDate);
		document.addDate("startDate", startDate);

		document.addKeyword("city", city);
		document.addKeyword("countryName", countryName);
		document.addKeyword("creditAmount", creditAmount);
		document.addKeyword("enrollmentURL", enrollmentURL);
		document.addKeyword("maxCustomers", maxCustomers);
		document.addKeyword("regionCode", regionCode);
		document.addKeyword("street1", street1);
		document.addKeyword("street2", street2);
		document.addKeyword("street3", street3);
		document.addKeyword(
			"trainingCertificateTemplateId", trainingCertificateTemplateId);
		document.addKeyword("trainingCourseId", trainingCourseId);
		document.addKeyword("trainingLocationId", trainingLocationId);
		document.addKeyword("type", type);
		document.addKeyword("zip", zip);

		return document;
	}

	protected void reindexEvents(long companyId) throws Exception {
		int count = TrainingEventLocalServiceUtil.getTrainingEventsCount();

		int pages = count / Indexer.DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * Indexer.DEFAULT_INTERVAL);
			int end = start + Indexer.DEFAULT_INTERVAL;

			reindexTrainingEvents(companyId, start, end);
		}
	}

	protected void reindexTrainingEvent(TrainingEvent trainingEvent)
		throws SearchException {

		Document document = getDocument(trainingEvent);

		SearchEngineUtil.updateDocument(OSBConstants.COMPANY_ID, document);
	}

	protected void reindexTrainingEvents(long companyId, int start, int end)
		throws Exception {

		List<TrainingEvent> trainingEvents =
			TrainingEventLocalServiceUtil.getTrainingEvents(start, end);

		if (trainingEvents.isEmpty()) {
			return;
		}

		Collection<Document> documents = new ArrayList<Document>();

		for (TrainingEvent trainingEvent : trainingEvents) {
			Document document = getDocument(trainingEvent);

			documents.add(document);
		}

		SearchEngineUtil.updateDocuments(companyId, documents);
	}

}