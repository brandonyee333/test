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

package com.liferay.osb.marketplaceapps.portlet;

import com.liferay.compat.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.LicenseKeyActiveException;
import com.liferay.osb.LicenseKeyIPAddressException;
import com.liferay.osb.LicenseKeyMACAddressException;
import com.liferay.osb.LicenseKeyServerInfoException;
import com.liferay.osb.MaximumLicenseKeyException;
import com.liferay.osb.NoSuchAssetReceiptLicenseException;
import com.liferay.osb.NoSuchLicenseKeyException;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.marketplace.portlet.MarketplacePortlet;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.ContractAuditLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Douglas Wong
 * @author Joan Kim
 * @author Ryan Schuhler
 */
public class MarketplaceAppsPortlet extends MarketplacePortlet {

	public void addLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long assetReceiptLicenseId = ParamUtil.getLong(
			actionRequest, "assetReceiptLicenseId");

		String hostName = ParamUtil.getString(actionRequest, "hostName");
		String ipAddresses = ParamUtil.getString(actionRequest, "ipAddresses");
		String macAddresses = ParamUtil.getString(
			actionRequest, "macAddresses");
		long appPackageId = ParamUtil.getLong(actionRequest, "appPackageId");

		AssetReceiptLicense assetReceiptLicense =
			AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(
				assetReceiptLicenseId);

		AssetReceipt assetReceipt = assetReceiptLicense.getAssetReceipt();
		AssetLicense assetLicense = assetReceiptLicense.getAssetLicense();

		String[] ipAddressesArray = StringUtil.split(
			ipAddresses, StringPool.NEW_LINE);
		String[] macAddressesArray = StringUtil.split(
			macAddresses, StringPool.NEW_LINE);

		ipAddressesArray = ArrayUtil.distinct(ipAddressesArray);
		macAddressesArray = ArrayUtil.distinct(macAddressesArray);

		ipAddresses = StringUtil.merge(ipAddressesArray);
		macAddresses = StringUtil.merge(macAddressesArray);

		Calendar cal = Calendar.getInstance();

		cal.setTime(assetReceiptLicense.getStartDate());

		LicenseKey licenseKey = LicenseKeyServiceUtil.addLicenseKey(
			themeDisplay.getUserId(),
			assetReceiptLicense.getAssetReceiptLicenseId(),
			assetReceipt.getFormalLegalEntityName(),
			assetLicense.getLicenseId(), new String[] {hostName},
			new String[] {ipAddresses}, new String[] {macAddresses},
			new String[] {StringPool.BLANK}, cal.get(Calendar.MONTH),
			cal.get(Calendar.DATE), cal.get(Calendar.YEAR));

		try {
			if (appPackageId > 0) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				LiferayPortletResponse liferayPortletResponse =
					(LiferayPortletResponse)actionResponse;

				ResourceURL downloadURL =
					liferayPortletResponse.createResourceURL();

				downloadURL.setParameter(
					"appPackageId", String.valueOf(appPackageId));
				downloadURL.setParameter(
					"licenseKeyId",
					String.valueOf(licenseKey.getLicenseKeyId()));
				downloadURL.setResourceID("serveApp");

				jsonObject.put("downloadURL", downloadURL.toString());

				jsonObject.put("success", Boolean.TRUE);

				writeJSON(actionRequest, actionResponse, jsonObject);
			}
		}
		catch (Exception e) {
			if (appPackageId > 0) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("exception", e.getClass().getName());
				jsonObject.put("success", Boolean.FALSE);

				writeJSON(actionRequest, actionResponse, jsonObject);
			}
			else {
				throw e;
			}
		}
	}

	public void deactivateLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long licenseKeyId = ParamUtil.getLong(actionRequest, "licenseKeyId");
		long assetReceiptLicenseId = ParamUtil.getLong(
			actionRequest, "assetReceiptLicenseId");

		LicenseKeyServiceUtil.updateLicenseKey(
			themeDisplay.getUserId(), licenseKeyId, assetReceiptLicenseId,
			false);
	}

	public void serveApp(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long licenseKeyId = ParamUtil.getLong(resourceRequest, "licenseKeyId");

		if (licenseKeyId > 0) {
			LicenseKeyServiceUtil.renewLicenseKey(licenseKeyId);
		}

		super.serveApp(resourceRequest, resourceResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("serveLicenseKey")) {
				serveLicenseKey(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveApp")) {
				serveApp(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String className = StringPool.BLANK;
			long classPK = 0;

			Group group = themeDisplay.getScopeGroup();

			if (group.isOrganization()) {
				CorpEntry corpEntry =
					CorpEntryLocalServiceUtil.getOrganizationCorpEntry(
						group.getOrganizationId());

				className = CorpEntry.class.getName();
				classPK = corpEntry.getCorpEntryId();
			}
			else {
				className = User.class.getName();
				classPK = themeDisplay.getUserId();
			}

			if (!ContractAuditLocalServiceUtil.hasLatestContractAudit(
					ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
					ContractEntryConstants.DEFAULT_CLASS_PK,
					ContractEntryConstants.TYPE_TERMS_OF_SERVICE, className,
					classPK)) {

				include(
					"/marketplace_apps/view_contract_entry.jsp", renderRequest,
					renderResponse);

				return;
			}
		}
		catch (Exception e) {
			include(
				"/marketplace_apps/error.jsp", renderRequest, renderResponse);

			return;
		}

		super.doDispatch(renderRequest, renderResponse);
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}

		if (cause instanceof LicenseKeyActiveException ||
			cause instanceof LicenseKeyIPAddressException ||
			cause instanceof LicenseKeyMACAddressException ||
			cause instanceof LicenseKeyServerInfoException ||
			cause instanceof MaximumLicenseKeyException ||
			cause instanceof NoSuchAssetReceiptLicenseException ||
			cause instanceof NoSuchLicenseKeyException) {

			return true;
		}
		else {
			return false;
		}
	}

	protected void serveLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long licenseKeyId = ParamUtil.getLong(resourceRequest, "licenseKeyId");

		LicenseKey licenseKey = LicenseKeyServiceUtil.getLicenseKey(
			licenseKeyId);

		String fileName = LicenseUtil.getLicenseKeyFileName(licenseKey);
		String licenseXML = LicenseUtil.exportToXML(licenseKey);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, licenseXML.getBytes(),
			ContentTypes.TEXT_XML);
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceAppsPortlet.class);

}