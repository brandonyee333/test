/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.license.portlet;

import com.liferay.osb.exception.DuplicateHostNameException;
import com.liferay.osb.exception.DuplicateIPAddressException;
import com.liferay.osb.exception.DuplicateMACAddressException;
import com.liferay.osb.exception.LicenseKeyDescriptionException;
import com.liferay.osb.exception.LicenseKeyHostNameException;
import com.liferay.osb.exception.LicenseKeyIPAddressException;
import com.liferay.osb.exception.LicenseKeyMACAddressException;
import com.liferay.osb.exception.LicenseKeyMaxServersException;
import com.liferay.osb.exception.LicenseKeyOwnerException;
import com.liferay.osb.exception.LicenseKeyProductVersionException;
import com.liferay.osb.exception.LicenseKeyRegistrationException;
import com.liferay.osb.exception.LicenseKeyRenewException;
import com.liferay.osb.exception.LicenseKeyServerIdException;
import com.liferay.osb.exception.LicenseKeySetNameException;
import com.liferay.osb.exception.MaximumLicenseKeyException;
import com.liferay.osb.exception.OfferingEntryStatusException;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.osb.service.LicenseKeySetServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.mvc.OSBPortlet;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicensePortlet extends OSBPortlet {

	public void buyLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		LicenseKeyLocalServiceUtil.buyLicenseKey(
			themeDisplay.getCompanyId(), themeDisplay.getUserId());

		SessionMessages.add(actionRequest, "purchased");

		addSuccessMessage(actionRequest, actionResponse);

		sendRedirect(actionRequest, actionResponse);
	}

	public void renewLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		long licenseKeyId = ParamUtil.getLong(actionRequest, "licenseKeyId");

		LicenseKey licenseKey = null;

		if (licenseKeyId > 0) {
			int renewTime = ParamUtil.getInteger(
				actionRequest, "renewTime_" + licenseKeyId);

			int startDay = ParamUtil.getInteger(
				actionRequest, "startDay_" + licenseKeyId);
			int startMonth = ParamUtil.getInteger(
				actionRequest, "startMonth_" + licenseKeyId);
			int startYear = ParamUtil.getInteger(
				actionRequest, "startYear_" + licenseKeyId);

			Calendar cal = Calendar.getInstance(
				TimeZone.getTimeZone(user.getTimeZoneId()));

			cal.set(startYear, startMonth, startDay);

			licenseKey = LicenseKeyServiceUtil.renewLicenseKey(
				licenseKeyId, cal.getTime(), renewTime);
		}
		else {
			long licenseKeySetId = ParamUtil.getLong(
				actionRequest, "licenseKeySetId");

			if (!LicenseUtil.isRenewAggregate(licenseKeySetId)) {
				throw new LicenseKeyRenewException();
			}

			int renewTime = ParamUtil.getInteger(
				actionRequest, "aggregateRenewTime");

			int startDay = ParamUtil.getInteger(
				actionRequest, "aggregateStartDay");
			int startMonth = ParamUtil.getInteger(
				actionRequest, "aggregateStartMonth");
			int startYear = ParamUtil.getInteger(
				actionRequest, "aggregateStartYear");

			Calendar cal = Calendar.getInstance(
				TimeZone.getTimeZone(user.getTimeZoneId()));

			cal.set(startYear, startMonth, startDay);

			List<LicenseKey> licenseKeys =
				LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
					licenseKeySetId);

			for (LicenseKey curLicenseKey : licenseKeys) {
				if (!curLicenseKey.isActive()) {
					continue;
				}

				licenseKey = LicenseKeyServiceUtil.renewLicenseKey(
					curLicenseKey.getLicenseKeyId(), cal.getTime(), renewTime);
			}
		}

		syncToLCS(
			actionRequest, actionResponse, licenseKey.getAccountEntryId());
	}

	public void renewTrialLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.fetchUserTrialAccountEntry(
				user.getUserId());

		if ((accountEntry != null) &&
			(LicenseKeyLocalServiceUtil.getUserLicenseKeysCount(
				user.getUserId(), accountEntry.getAccountEntryId()) > 2)) {

			LicenseKeyLocalServiceUtil.sendTrialRenewalNotificationEmail(
				"sales@liferay.com", accountEntry.getAccountEntryId());

			SessionMessages.add(
				actionRequest, "salesNotified", "sales@liferay.com");
		}
		else {
			LicenseKeyLocalServiceUtil.renewTrialLicenseKey(user.getUserId());

			SessionMessages.add(
				actionRequest, "licenseKeySent", user.getEmailAddress());
		}

		addSuccessMessage(actionRequest, actionResponse);

		sendRedirect(actionRequest, actionResponse);
	}

	public void resendLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long licenseKeyId = ParamUtil.getLong(actionRequest, "licenseKeyId");

		LicenseKey licenseKey = LicenseKeyServiceUtil.getLicenseKey(
			licenseKeyId);

		User user = themeDisplay.getUser();

		LicenseKeyLocalServiceUtil.sendRegisteredEmail(user, licenseKey);

		SessionMessages.add(
			actionRequest, "licenseKeySent", user.getEmailAddress());

		addSuccessMessage(actionRequest, actionResponse);

		sendRedirect(actionRequest, actionResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (Validator.isNull(resourceID)) {
				return;
			}
			else if (resourceID.equals("licenseKeySet")) {
				serveLicenseKeySet(resourceRequest, resourceResponse);
			}
			else {
				serveLicenseKey(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long licenseKeyId = ParamUtil.getLong(actionRequest, "licenseKeyId");

		long licenseKeySetId = ParamUtil.getLong(
			actionRequest, "licenseKeySetId");
		String name = ParamUtil.getString(actionRequest, "name");
		long offeringEntryId = ParamUtil.getLong(
			actionRequest, "offeringEntryId");
		long licenseEntryId = ParamUtil.getLong(
			actionRequest, "licenseEntryId");
		long clusterId = ParamUtil.getLong(actionRequest, "clusterId");
		int productVersion = ParamUtil.getInteger(
			actionRequest, "productVersion");
		String owner = ParamUtil.getString(actionRequest, "owner");
		int maxServers = ParamUtil.getInteger(actionRequest, "maxServers");
		int maxHttpSessions = ParamUtil.getInteger(
			actionRequest, "maxHttpSessions");
		String description = ParamUtil.getString(actionRequest, "description");
		String[] serverIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "serverIds"), "\n");
		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear,
			themeDisplay.getTimeZone(), (Class<? extends PortalException>)null);

		List<String> hostNames = new ArrayList<>();
		List<String> ipAddresses = new ArrayList<>();
		List<String> macAddresses = new ArrayList<>();

		int[] serverIdsIndexes = StringUtil.split(
			ParamUtil.getString(actionRequest, "serverIdsIndexes"), 0);

		for (int serverIdsIndex : serverIdsIndexes) {
			String hostName = ParamUtil.getString(
				actionRequest, "hostName" + serverIdsIndex);

			String[] curIpAddresses = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "ipAddresses" + serverIdsIndex),
				StringPool.NEW_LINE);
			String[] curMacAddresses = StringUtil.split(
				ParamUtil.getString(
					actionRequest, "macAddresses" + serverIdsIndex),
				StringPool.NEW_LINE);

			curIpAddresses = ArrayUtil.distinct(curIpAddresses);
			curMacAddresses = ArrayUtil.distinct(curMacAddresses);

			if (Validator.isNull(hostName) && (curIpAddresses.length <= 0) &&
				(curMacAddresses.length <= 0)) {

				continue;
			}

			hostNames.add(hostName);
			ipAddresses.add(StringUtil.merge(curIpAddresses));
			macAddresses.add(StringUtil.merge(curMacAddresses));
		}

		LicenseKey licenseKey = null;

		if (licenseKeyId <= 0) {
			licenseKey = LicenseKeyServiceUtil.addLicenseKey(
				themeDisplay.getUserId(), licenseKeySetId, name,
				offeringEntryId, licenseEntryId, 0, productVersion, clusterId,
				owner, maxServers, maxHttpSessions, description,
				hostNames.toArray(new String[hostNames.size()]),
				ipAddresses.toArray(new String[ipAddresses.size()]),
				macAddresses.toArray(new String[macAddresses.size()]),
				serverIds, startDate, false, true);

			actionRequest.setAttribute("clusterId", licenseKey.getClusterId());
			actionRequest.setAttribute(
				"licenseKeySetId", licenseKey.getLicenseKeySetId());
			actionRequest.setAttribute(
				"offeringEntryId", licenseKey.getOfferingEntryId());
		}
		else {
			licenseKey = LicenseKeyServiceUtil.updateLicenseKey(
				themeDisplay.getUserId(), licenseKeyId, licenseKeySetId,
				offeringEntryId, name, active);
		}

		syncToLCS(
			actionRequest, actionResponse, licenseKey.getAccountEntryId());
	}

	public void updateLicenseKeySet(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long licenseKeySetId = ParamUtil.getLong(
			actionRequest, "licenseKeySetId");

		String name = ParamUtil.getString(actionRequest, "name");

		LicenseKeySetServiceUtil.updateLicenseKeySet(licenseKeySetId, name);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

		super.doDispatch(renderRequest, renderResponse);
	}

	@Override
	protected String getRedirect(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String redirect = null;

		Long licenseKeySetId = (Long)actionRequest.getAttribute(
			"licenseKeySetId");

		if (licenseKeySetId != null) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				actionRequest);

			Long offeringEntryId = (Long)actionRequest.getAttribute(
				"offeringEntryId");
			Long clusterId = (Long)actionRequest.getAttribute("clusterId");

			PortletURL portletURL = PortletURLFactoryUtil.create(
				request, OSBPortletKeys.OSB_LICENSE, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"scroll",
				actionResponse.getNamespace() + offeringEntryId +
					StringPool.DASH + clusterId);
			portletURL.setParameter(
				"mvcPath", "/license/edit_license_key_set.jsp");
			portletURL.setParameter(
				"licenseKeySetId", String.valueOf(licenseKeySetId));
			portletURL.setParameter(
				"offeringEntryId", String.valueOf(offeringEntryId));
			portletURL.setParameter("clusterId", String.valueOf(clusterId));

			redirect = portletURL.toString();
		}

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(actionRequest, "redirect");
		}

		return redirect;
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (!themeDisplay.isSignedIn()) {
				return false;
			}

			if (OrganizationLocalServiceUtil.hasUserOrganization(
					themeDisplay.getUserId(),
					OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				return true;
			}

			if (AccountEntryLocalServiceUtil.hasValidLicenseAccountEntry(
					themeDisplay.getUserId())) {

				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateHostNameException ||
			cause instanceof DuplicateIPAddressException ||
			cause instanceof DuplicateMACAddressException ||
			cause instanceof LicenseKeyDescriptionException ||
			cause instanceof LicenseKeyHostNameException ||
			cause instanceof LicenseKeyIPAddressException ||
			cause instanceof LicenseKeyMACAddressException ||
			cause instanceof LicenseKeyMaxServersException ||
			cause instanceof LicenseKeyOwnerException ||
			cause instanceof LicenseKeyProductVersionException ||
			cause instanceof LicenseKeyRegistrationException ||
			cause instanceof LicenseKeyServerIdException ||
			cause instanceof LicenseKeySetNameException ||
			cause instanceof MaximumLicenseKeyException ||
			cause instanceof OfferingEntryStatusException) {

			return true;
		}
		else {
			return false;
		}
	}

	protected void serveLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long licenseKeyId = ParamUtil.getLong(resourceRequest, "licenseKeyId");

		LicenseKey licenseKey = LicenseKeyServiceUtil.getLicenseKey(
			licenseKeyId);

		if (!OrganizationLocalServiceUtil.hasUserOrganization(
				themeDisplay.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return;
		}

		int licenseVersion = licenseKey.getLicenseVersion();

		if (licenseVersion == 1) {
			String encodedLicenseFile = LicenseUtil.exportToEncodedLicenseFile(
				licenseKey);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, "license",
				encodedLicenseFile.getBytes(),
				ContentTypes.APPLICATION_OCTET_STREAM);
		}
		else if (licenseVersion >= 2) {
			String fileName = LicenseUtil.getLicenseKeyFileName(licenseKey);
			String licenseXML = LicenseUtil.exportToXML(licenseKey);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				licenseXML.getBytes(), ContentTypes.TEXT_XML);
		}
	}

	protected void serveLicenseKeySet(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!OrganizationLocalServiceUtil.hasUserOrganization(
				themeDisplay.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return;
		}

		long licenseKeySetId = ParamUtil.getLong(
			resourceRequest, "licenseKeySetId");

		LicenseKeySet licenseKeySet = LicenseKeySetServiceUtil.getLicenseKeySet(
			licenseKeySetId);

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
				licenseKeySet.getLicenseKeySetId());

		if (licenseKeys.isEmpty()) {
			return;
		}

		LicenseKey licenseKey = licenseKeys.get(0);

		String fileName = LicenseUtil.getLicenseKeyFileName(licenseKey);

		String licenseXML = LicenseUtil.exportToXML(licenseKeySet);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, licenseXML.getBytes(),
			ContentTypes.TEXT_XML);
	}

	private static final Log _log = LogFactoryUtil.getLog(LicensePortlet.class);

}