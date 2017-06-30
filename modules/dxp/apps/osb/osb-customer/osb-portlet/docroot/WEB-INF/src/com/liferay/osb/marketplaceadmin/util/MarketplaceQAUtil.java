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

package com.liferay.osb.marketplaceadmin.util;

import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.RequiredAppManualQADirException;
import com.liferay.osb.admin.util.KeyGenerator;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.marketplace.util.MarketplaceLiferayPackageUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppFlag;
import com.liferay.osb.model.AppFlagConstants;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.PortalRelease;
import com.liferay.osb.service.AppFlagLocalServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Ticket;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.TicketLocalServiceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

/**
 * @author Haote Chou
 */
public class MarketplaceQAUtil {

	public static void removeAppVersion(final AppVersion appVersion)
		throws SystemException {

		final List<AppPackage> appPackages =
			AppPackageLocalServiceUtil.getAppPackages(
				appVersion.getAppVersionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Thread thread = new Thread() {

			@Override
			public void run() {
				try {
					doRemoveAppEntry(appPackages);

					AppFlag appFlag = AppFlagLocalServiceUtil.fetchAppFlag(
						appVersion.getAppVersionId(),
						AppFlagConstants.TYPE_QA_TEST_FILE_AVAILABLE);

					if (appFlag != null) {
						AppFlagLocalServiceUtil.deleteAppFlag(appFlag);
					}
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}

		};

		thread.start();
	}

	public static void sendAppVersion(final AppVersion appVersion)
		throws SystemException {

		final List<AppPackage> appPackages =
			AppPackageLocalServiceUtil.getAppPackages(
				appVersion.getAppVersionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		if (appVersion.isDeveloperEntryLiferayInc() || appPackages.isEmpty()) {
			return;
		}

		Thread thread = new Thread() {

			@Override
			public void run() {
				try {
					doSendAppPackages(appPackages);

					AppFlagLocalServiceUtil.addAppFlag(
						appVersion.getAppEntryId(),
						appVersion.getAppVersionId(),
						AppFlagConstants.TYPE_QA_TEST_FILE_AVAILABLE);
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}

		};

		thread.start();
	}

	public static void sendManualQAAppVersion(final AppVersion appVersion)
		throws SystemException {

		final List<AppPackage> appPackages =
			AppPackageLocalServiceUtil.getAppPackages(
				appVersion.getAppVersionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		if (appVersion.isDeveloperEntryLiferayInc() || appPackages.isEmpty()) {
			return;
		}

		Thread thread = new Thread() {

			@Override
			public void run() {
				for (AppPackage appPackage : appPackages) {
					try {
						createManualQAAppPackage(appPackage);
					}
					catch (Exception e) {
						_log.error(
							"Unable to save LPKG for app package " +
								appPackage.getAppPackageId(),
							e);
					}
				}
			}

		};

		thread.start();
	}

	protected static void createManualQAAppPackage(AppPackage appPackage)
		throws Exception {

		File file = new File(getManualQAFileName(appPackage));

		File tempFile = null;

		try {
			tempFile = MarketplaceLiferayPackageUtil.buildLiferayPackage(
				appPackage, true);

			ZipWriter zipWriter = ZipWriterFactoryUtil.getZipWriter(tempFile);

			AppEntry appEntry = appPackage.getAppEntry();

			if (appEntry.getLicenseType() ==
					AssetLicenseConstants.LICENSE_TYPE_PRODUCTION) {

				String licenseXML = LicenseUtil.exportToXML(
					createQALicenseKey(
						appPackage.getAppEntry(), appPackage.getAppVersion()));

				zipWriter.addEntry("license.xml", licenseXML);
			}

			FileUtil.write(file, new FileInputStream(zipWriter.getFile()));
		}
		finally {
			FileUtil.delete(tempFile);
		}
	}

	protected static LicenseKey createQALicenseKey(
		AppEntry appEntry, AppVersion appVersion) {

		LicenseKey licenseKey = LicenseKeyLocalServiceUtil.createLicenseKey(0);

		licenseKey.setDescription("30-Day Trial License");
		licenseKey.setLicenseEntryType(
			AssetLicenseConstants.LICENSE_KEY_TYPE_ENTERPRISE);
		licenseKey.setLicenseVersion(3);
		licenseKey.setOwner("Liferay QA Team");
		licenseKey.setProductEntryName(appEntry.getTitle());
		licenseKey.setProductId(appEntry.getUuid());

		int productVersion = appVersion.getVersionId();

		licenseKey.setProductVersion(productVersion);

		licenseKey.setProductVersionLabel(String.valueOf(productVersion));

		Date startDate = DateUtils.round(new Date(), Calendar.SECOND);

		licenseKey.setStartDate(startDate);

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(startDate);

		calendar.add(Calendar.MONTH, 1);

		Date expirationDate = DateUtils.round(
			calendar.getTime(), Calendar.SECOND);

		licenseKey.setExpirationDate(expirationDate);

		String key = KeyGenerator.generate(
			StringPool.BLANK, StringPool.BLANK,
			AssetLicenseConstants.LICENSE_KEY_TYPE_ENTERPRISE, 3,
			appEntry.getTitle(), appEntry.getUuid(),
			String.valueOf(productVersion), "Liferay QA Team", 0, 0, 0, 1,
			"30-Day Trial License", StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, new String[] {}, startDate, expirationDate);

		licenseKey.setKey(key);

		return licenseKey;
	}

	protected static void doRemoveAppEntry(List<AppPackage> appPackages) {
		for (AppPackage appPackage : appPackages) {
			try {
				File file = new File(getManualQAFileName(appPackage));

				FileUtil.delete(file);
			}
			catch (Exception e) {
				_log.error(
					"Unable to save LPKG for app package " +
						appPackage.getAppPackageId(),
					e);
			}
		}
	}

	protected static void doSendAppPackages(List<AppPackage> appPackages)
		throws PortalException, SystemException {

		Company company = CompanyLocalServiceUtil.getCompany(
			OSBConstants.COMPANY_ID);

		String portalURL = PortalUtil.getPortalURL(
			company.getVirtualHostname(), Http.HTTPS_PORT, true);

		for (AppPackage appPackage : appPackages) {
			try {
				createManualQAAppPackage(appPackage);
			}
			catch (Exception e) {
				_log.error(
					"Unable to save LPKG for app package " +
						appPackage.getAppPackageId(),
					e);
			}

			try {
				sendAutoQAAppPackage(appPackage, portalURL);
			}
			catch (Exception e) {
				_log.error(
					"Unable to send app package " +
						appPackage.getAppPackageId() + " to Jenkins",
					e);
			}
		}
	}

	protected static String getAutoQACallbackURL(
		long appEntryId, String portalURL) {

		StringBundler sb = new StringBundler(7);

		sb.append(portalURL);
		sb.append("/osb-portlet/mp_auto_qa/callback/");
		sb.append(appEntryId);
		sb.append(StringPool.SLASH);
		sb.append(PortletPropsValues.MARKETPLACE_APP_AUTO_QA_TOKEN);
		sb.append(StringPool.SLASH);
		sb.append(_AUTO_QA_LICENSED);

		return sb.toString();
	}

	protected static String getAutoQADownloadURL(
			AppPackage appPackage, String portalURL)
		throws SystemException {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		calendar.add(Calendar.HOUR, 1);

		Ticket ticket = TicketLocalServiceUtil.addTicket(
			PortalUtil.getDefaultCompanyId(), AppPackage.class.getName(),
			appPackage.getAppPackageId(), _TICKET_TYPE_AUTO_QA, null,
			calendar.getTime(), null);

		StringBundler sb = new StringBundler(7);

		sb.append(portalURL);
		sb.append("/marketplace/-/mp/download/auto_qa/");
		sb.append(String.valueOf(appPackage.getAppPackageId()));
		sb.append(StringPool.SLASH);

		String key = ticket.getKey();

		sb.append(Base64.encode(key.getBytes()));

		sb.append(StringPool.SLASH);
		sb.append(_AUTO_QA_LICENSED);

		return sb.toString();
	}

	protected static String getManualQAFileName(AppPackage appPackage)
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler(5);

		if (Validator.isNull(
				PortletPropsValues.MARKETPLACE_APP_MANUAL_QA_DIR)) {

			throw new RequiredAppManualQADirException();
		}

		sb.append(PortletPropsValues.MARKETPLACE_APP_MANUAL_QA_DIR);

		AppEntry appEntry = appPackage.getAppEntry();

		sb.append(appEntry.getTitle());

		sb.append(" - ");

		PortalRelease portalRelease =
			PortalReleaseLocalServiceUtil.getPortalRelease(
				appPackage.getCompatibility());

		sb.append(portalRelease.getVersionName());

		sb.append(".lpkg");

		return sb.toString();
	}

	protected static void sendAutoQAAppPackage(
			AppPackage appPackage, String portalURL)
		throws IOException, PortalException, SystemException {

		Http.Options options = new Http.Options();

		String login =
			PortletPropsValues.MARKETPLACE_APP_AUTO_QA_LOGIN_USERNAME +
				StringPool.COLON +
					PortletPropsValues.MARKETPLACE_APP_AUTO_QA_LOGIN_PASSWORD;

		options.addHeader(
			"Authorization", "Basic " + Base64.encode(login.getBytes()));

		options.addPart("TEST_APP_TYPE", "community");
		options.addPart(
			"TEST_CALLBACK_URL",
			getAutoQACallbackURL(appPackage.getAppEntryId(), portalURL));
		options.addPart(
			"TEST_MARKETPLACE_APP_ZIP_URL",
			getAutoQADownloadURL(appPackage, portalURL));

		AppEntry appEntry = appPackage.getAppEntry();

		options.addPart(
			"TEST_PACKAGE_FILE_NAME", appEntry.getTitle() + ".lpkg");

		options.addPart(
			"TEST_PORTAL_BUILD_NUMBER",
			String.valueOf(appPackage.getCompatibility()));
		options.setLocation(PortletPropsValues.MARKETPLACE_APP_AUTO_QA_URL);
		options.setPost(true);

		HttpUtil.URLtoByteArray(options);
	}

	private static final boolean _AUTO_QA_LICENSED = false;

	private static final int _TICKET_TYPE_AUTO_QA = 4;

	private static Log _log = LogFactoryUtil.getLog(MarketplaceQAUtil.class);

}