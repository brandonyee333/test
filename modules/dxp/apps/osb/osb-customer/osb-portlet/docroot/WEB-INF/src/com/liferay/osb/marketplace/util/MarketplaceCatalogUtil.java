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

import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.persistence.AppEntryActionableDynamicQuery;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.util.xml.DocUtil;

import java.util.List;

/**
 * @author Ryan Park
 */
public class MarketplaceCatalogUtil {

	public static Document getAppEntriesXML(
			final long developerEntryId, final int status)
		throws Exception {

		final Element rootElement = SAXReaderUtil.createElement("marketplace");

		rootElement.addAttribute(
			"time", String.valueOf(System.currentTimeMillis()));

		ActionableDynamicQuery actionableDynamicQuery =
			new AppEntryActionableDynamicQuery() {

			@Override
			protected void addCriteria(DynamicQuery dynamicQuery) {
				if (developerEntryId > 0) {
					Property developerEntryIdProperty =
						PropertyFactoryUtil.forName("developerEntryId");

					dynamicQuery.add(
						developerEntryIdProperty.eq(developerEntryId));
				}

				if (status != WorkflowConstants.STATUS_ANY) {
					Property statusProperty = PropertyFactoryUtil.forName(
						"status");

					dynamicQuery.add(statusProperty.eq(status));
				}
			}

			@Override
			protected void performAction(Object object) throws PortalException {
				AppEntry appEntry = (AppEntry)object;

				Element element = null;

				try {
					element = getAppEntryXML(appEntry, status);
				}
				catch (Exception e) {
					if (_log.isErrorEnabled()) {
						_log.error(e, e);
					}
				}

				rootElement.add(element);
			}

		};

		actionableDynamicQuery.performActions();

		return SAXReaderUtil.createDocument(rootElement);
	}

	public static Document getAppEntriesXML(int status) throws Exception {
		return getAppEntriesXML(0, status);
	}

	public static Document getAppEntryXML(long appEntryId, int status)
		throws Exception {

		Element rootElement = SAXReaderUtil.createElement("marketplace");

		rootElement.addAttribute(
			"time", String.valueOf(System.currentTimeMillis()));

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		rootElement.add(getAppEntryXML(appEntry, status));

		return SAXReaderUtil.createDocument(rootElement);
	}

	protected static Element getAppEntryXML(AppEntry appEntry, int status)
		throws Exception {

		Element element = SAXReaderUtil.createElement("app");

		element.addAttribute("id", String.valueOf(appEntry.getAppEntryId()));

		DocUtil.add(element, "title", appEntry.getTitle());
		DocUtil.add(element, "developer", appEntry.getDeveloperEntryName());

		Element descriptionElement = element.addElement("description");

		descriptionElement.addCDATA(appEntry.getDescriptionCurrentValue());

		DocUtil.add(element, "developer-url", appEntry.getWebsite());
		DocUtil.add(element, "demo-website", appEntry.getDemoWebsite());
		DocUtil.add(
			element, "documentation-url", appEntry.getDocumentationWebsite());
		DocUtil.add(element, "support-url", appEntry.getSupportWebsite());
		DocUtil.add(
			element, "source-code-url", appEntry.getSourceCodeWebsite());
		DocUtil.add(element, "labs", appEntry.isLabs());
		DocUtil.add(element, "status", appEntry.getStatusLabel());

		Element versionsElement = element.addElement("versions");

		getAppVersionsXML(appEntry, status, versionsElement);

		return element;
	}

	protected static void getAppPackagesXML(
			AppVersion appVersion, Element element)
		throws Exception {

		List<AppPackage> appPackages =
			AppPackageLocalServiceUtil.getAppPackages(
				appVersion.getAppVersionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (AppPackage appPackage : appPackages) {
			Element appPackageElement = element.addElement("compatibility");

			appPackageElement.addAttribute(
				"id", String.valueOf(appPackage.getAppPackageId()));

			DocUtil.add(
				appPackageElement, "portal-name",
				PortalReleaseUtil.getVersionName(appPackage));
			DocUtil.add(
				appPackageElement, "build-number",
				appPackage.getCompatibility());
			DocUtil.add(
				appPackageElement, "future-compatible",
				appPackage.getCompatibilityPlus());
		}
	}

	protected static void getAppVersionsXML(
			AppEntry appEntry, int status, Element element)
		throws Exception {

		List<AppVersion> appVersions =
			AppVersionLocalServiceUtil.getAppVersions(
				appEntry.getAppEntryId(), status, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (AppVersion appVersion : appVersions) {
			Element appVersionElement = element.addElement("version");

			appVersionElement.addAttribute(
				"id", String.valueOf(appVersion.getAppVersionId()));

			DocUtil.add(
				appVersionElement, "version-name", appVersion.getVersion());

			Element changeLogElement = appVersionElement.addElement(
				"change-log");

			changeLogElement.addCDATA(appVersion.getChangeLogCurrentValue());

			DocUtil.add(
				appVersionElement, "status", appVersion.getStatusLabel());

			Element compatibilitiesElement = appVersionElement.addElement(
				"compatibilities");

			getAppPackagesXML(appVersion, compatibilitiesElement);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceCatalogUtil.class);

}