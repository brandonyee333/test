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

package com.liferay.osb.marketplaceregistration.portlet;

import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.corpmembers.util.CorpMembersUtil;
import com.liferay.osb.marketplace.util.MarketplaceEcommerceUtil;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpMembershipRequest;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.service.ContractEntryLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpMembershipRequestLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryServiceUtil;
import com.liferay.osb.service.permission.OSBDeveloperEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.mvc.OSBPortlet;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Haote Chou
 */
public class MarketplaceRegistrationPortlet extends OSBPortlet {

	public void addUserDeveloperEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String screenName = ParamUtil.getString(actionRequest, "screenName");
		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String middleName = ParamUtil.getString(actionRequest, "middleName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		long countryId = ParamUtil.getLong(actionRequest, "countryId");

		boolean acceptContractEntry = ParamUtil.getBoolean(
			actionRequest, "acceptContractEntry");

		long contractEntryId = 0;

		if (acceptContractEntry) {
			ContractEntry contractEntry =
				ContractEntryLocalServiceUtil.getLatestContractEntry(
					ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
					ContractEntryConstants.DEFAULT_CLASS_PK,
					ContractEntryConstants.TYPE_DEVELOPER_AGREEMENT);

			contractEntryId = contractEntry.getContractEntryId();
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			User.class.getName(), actionRequest);

		Map<String, Serializable> expandoBridgeAttributes =
			serviceContext.getExpandoBridgeAttributes();

		String phoneNumber = (String)expandoBridgeAttributes.get(
			"osbPhoneNumber");

		serviceContext.setAttribute("status", WorkflowConstants.STATUS_PENDING);

		DeveloperEntryServiceUtil.addUserDeveloperEntry(
			screenName, emailAddress, firstName, middleName, lastName,
			contractEntryId, countryId, phoneNumber, serviceContext);

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)actionResponse;

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcPath",
			"/marketplace_registration/thank_you_user_developer_entry.jsp");

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void sendCorpMembershipRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = CorpMembersUtil.sendCorpMembershipRequest(
			actionRequest, actionResponse);

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("serveMedia")) {
				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_MEDIA);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateCompanyDeveloperEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		File profileLogoFile = null;

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)uploadPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			long developerEntryId = ParamUtil.getLong(
				uploadPortletRequest, "developerEntryId");

			long corpEntryId = ParamUtil.getLong(
				uploadPortletRequest, "corpEntryId");
			String emailAddress = ParamUtil.getString(
				uploadPortletRequest, "emailAddress");
			String legalEntityName = ParamUtil.getString(
				uploadPortletRequest, "legalEntityName");
			String phoneNumber = ParamUtil.getString(
				uploadPortletRequest, "phoneNumber");
			String faxNumber = ParamUtil.getString(
				uploadPortletRequest, "faxNumber");
			String street1 = ParamUtil.getString(
				uploadPortletRequest, "street1");
			String street2 = ParamUtil.getString(
				uploadPortletRequest, "street2");
			String street3 = ParamUtil.getString(
				uploadPortletRequest, "street3");
			String city = ParamUtil.getString(uploadPortletRequest, "city");
			String zip = ParamUtil.getString(uploadPortletRequest, "zip");
			long regionId = ParamUtil.getLong(uploadPortletRequest, "regionId");
			long countryId = ParamUtil.getLong(
				uploadPortletRequest, "countryId");
			Map<Locale, String> profileDescriptionMap =
				LocalizationUtil.getLocalizationMap(
					uploadPortletRequest, "profileDescription");
			String profileEmailAddress = ParamUtil.getString(
				uploadPortletRequest, "profileEmailAddress");
			profileLogoFile = uploadPortletRequest.getFile("profileLogo");
			String profileWebsite = ParamUtil.getString(
				uploadPortletRequest, "profileWebsite");
			String taxDocumentType = ParamUtil.getString(
				uploadPortletRequest, "taxDocumentType");
			File taxDocumentFile = uploadPortletRequest.getFile("taxDocument");
			boolean acceptContractEntries = ParamUtil.getBoolean(
				uploadPortletRequest, "acceptContractEntries");

			String dossieraAccountKey = StringPool.BLANK;

			if (CorpEntryLocalServiceUtil.hasUserCorpEntry(
					themeDisplay.getUserId(), corpEntryId)) {

				CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
					corpEntryId);

				if ((profileLogoFile == null) || !profileLogoFile.exists()) {
					AssetAttachment assetAttachment =
						AssetAttachmentLocalServiceUtil.getAssetAttachment(
							corpEntry.getLogoId());

					profileLogoFile = FileUtil.createTempFile(
						FileUtil.getExtension(assetAttachment.getFileName()));

					FileUtil.write(
						profileLogoFile, assetAttachment.getFileAsStream());

					profileWebsite = corpEntry.getWebsite();
				}

				dossieraAccountKey = corpEntry.getDossieraAccountKey();
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				uploadPortletRequest);

			long tosContractEntryId = 0;
			long developerContractEntryId = 0;

			if (acceptContractEntries) {
				ContractEntry tosContractEntry =
					ContractEntryLocalServiceUtil.getLatestContractEntry(
						ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
						ContractEntryConstants.DEFAULT_CLASS_PK,
						ContractEntryConstants.TYPE_TERMS_OF_SERVICE);

				tosContractEntryId = tosContractEntry.getContractEntryId();

				ContractEntry developerAgreementContractEntry =
					ContractEntryLocalServiceUtil.getLatestContractEntry(
						ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
						ContractEntryConstants.DEFAULT_CLASS_PK,
						ContractEntryConstants.TYPE_DEVELOPER_AGREEMENT);

				developerContractEntryId =
					developerAgreementContractEntry.getContractEntryId();
			}

			if (developerEntryId > 0) {
				DeveloperEntryServiceUtil.updateCompanyDeveloperEntry(
					developerEntryId, emailAddress, legalEntityName,
					phoneNumber, faxNumber, street1, street2, street3, city,
					zip, regionId, countryId, profileLogoFile,
					profileDescriptionMap, profileEmailAddress, profileWebsite,
					taxDocumentType, taxDocumentFile, serviceContext);
			}
			else {
				DeveloperEntryServiceUtil.addCompanyDeveloperEntry(
					emailAddress, legalEntityName, phoneNumber, faxNumber,
					street1, street2, street3, city, zip, regionId, countryId,
					profileDescriptionMap, profileEmailAddress, profileLogoFile,
					profileWebsite, dossieraAccountKey, taxDocumentType,
					taxDocumentFile, tosContractEntryId,
					developerContractEntryId, serviceContext);
			}

			LiferayPortletResponse liferayPortletResponse =
				(LiferayPortletResponse)actionResponse;

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter(
				"mvcPath",
				"/marketplace_registration/" +
					"thank_you_company_developer_entry.jsp");

			actionResponse.sendRedirect(portletURL.toString());
		}
		finally {
			uploadPortletRequest.cleanUp();

			FileUtil.delete(profileLogoFile);
		}
	}

	public void updateCorpEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			CorpMembersUtil.updateCorpEntry(
				uploadPortletRequest, actionResponse, true);

			String redirect = ParamUtil.getString(
				uploadPortletRequest, "languageRedirect");

			if (Validator.isNull(redirect)) {
				redirect = ParamUtil.getString(
					uploadPortletRequest, "redirect");
			}

			actionResponse.sendRedirect(redirect);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateDeveloperEntryDetails(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			doUpdateDeveloperEntryDetails(uploadPortletRequest, actionResponse);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateDeveloperEntryEmail(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long developerEntryId = ParamUtil.getLong(
			actionRequest, "developerEntryId");

		String paymentFirstName = ParamUtil.getString(
			actionRequest, "paymentFirstName");
		String paymentLastName = ParamUtil.getString(
			actionRequest, "paymentLastName");
		String paymentEmailAddress = ParamUtil.getString(
			actionRequest, "paymentEmailAddress");

		DeveloperEntryServiceUtil.updateDeveloperEntry(
			developerEntryId, paymentFirstName, paymentLastName,
			paymentEmailAddress);
	}

	public void updateDeveloperEntryPay(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long developerEntryId = ParamUtil.getLong(
			actionRequest, "developerEntryId");

		String backURL = ParamUtil.getString(actionRequest, "backURL");

		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(new Date());

		if (OSBConstants.MARKETPLACE_SUBSCRIPTION_PROMOTION_YEAR <
				calendar.get(Calendar.YEAR)) {

			ECDocumentEntry ecDocumentEntry =
				MarketplaceEcommerceUtil.purchaseDeveloperSubscription(
					themeDisplay.getUserId(), developerEntryId);

			String confirmURL = HttpUtil.addParameter(
				backURL, "ecDocumentEntryId",
				ecDocumentEntry.getEcDocumentEntryId());

			LiferayPortletResponse liferayPortletResponse =
				(LiferayPortletResponse)actionResponse;

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter(
				"mvcPath",
				"/marketplace_registration/upgrade_developer_entry.jsp");
			portletURL.setParameter("backURL", backURL);
			portletURL.setParameter("upgradeStep", "pay");
			portletURL.setParameter(
				"developerEntryId", String.valueOf(developerEntryId));

			String paymentURL =
				ECDocumentEntryLocalServiceUtil.getECDocumentEntryPaymentURL(
					ecDocumentEntry.getEcDocumentEntryId(), confirmURL,
					portletURL.toString());

			actionResponse.sendRedirect(paymentURL);
		}
		else {
			calendar = CalendarFactoryUtil.getCalendar(
				OSBConstants.MARKETPLACE_SUBSCRIPTION_PROMOTION_YEAR + 1,
				Calendar.JANUARY, 1);

			DeveloperEntryServiceUtil.updateSubscription(
				developerEntryId, calendar.getTime(),
				WorkflowConstants.STATUS_DRAFT);

			actionResponse.sendRedirect(backURL);
		}
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

		if (mvcPath.equals("/marketplace_registration/view_validate.jsp")) {
			validateCorpMembershipRequest(renderRequest, renderResponse);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			portletDisplay.setURLBack(PortalUtil.getPortalURL(renderRequest));
		}

		if (mvcPath.equals(
				"/marketplace_registration/edit_company_developer_entry.jsp")) {

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			long developerEntryId = ParamUtil.getLong(
				renderRequest, "developerEntryId");

			if (developerEntryId > 0) {
				try {
					OSBDeveloperEntryPermission.check(
						themeDisplay.getPermissionChecker(), developerEntryId,
						OSBActionKeys.UPDATE);
				}
				catch (Exception e) {
					include(
						"/marketplace_registration/error.jsp", renderRequest,
						renderResponse);

					return;
				}
			}
		}

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.TRUE);

		super.doDispatch(renderRequest, renderResponse);
	}

	protected void doUpdateDeveloperEntryDetails(
			UploadPortletRequest uploadPortletRequest,
			ActionResponse actionResponse)
		throws Exception {

		long developerEntryId = ParamUtil.getLong(
			uploadPortletRequest, "developerEntryId");

		String firstName = ParamUtil.getString(
			uploadPortletRequest, "firstName");
		String middleName = ParamUtil.getString(
			uploadPortletRequest, "middleName");
		String lastName = ParamUtil.getString(uploadPortletRequest, "lastName");
		String legalEntityName = ParamUtil.getString(
			uploadPortletRequest, "legalEntityName");
		String phoneNumber = ParamUtil.getString(
			uploadPortletRequest, "phoneNumber");
		String street1 = ParamUtil.getString(uploadPortletRequest, "street1");
		String street2 = ParamUtil.getString(uploadPortletRequest, "street2");
		String street3 = ParamUtil.getString(uploadPortletRequest, "street3");
		String city = ParamUtil.getString(uploadPortletRequest, "city");
		String zip = ParamUtil.getString(uploadPortletRequest, "zip");
		long regionId = ParamUtil.getLong(uploadPortletRequest, "regionId");
		long countryId = ParamUtil.getLong(uploadPortletRequest, "countryId");
		String taxDocumentType = ParamUtil.getString(
			uploadPortletRequest, "taxDocumentType");
		File taxDocumentFile = uploadPortletRequest.getFile("taxDocumentFile");

		DeveloperEntryServiceUtil.updateUserDeveloperEntry(
			developerEntryId, firstName, middleName, lastName, legalEntityName,
			phoneNumber, street1, street2, street3, city, zip, regionId,
			countryId, taxDocumentType, taxDocumentFile);
	}

	protected boolean isSessionErrorException(Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}

		return super.isSessionErrorException(cause);
	}

	protected void validateCorpMembershipRequest(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		try {
			String key = ParamUtil.getString(renderRequest, "key");

			CorpMembershipRequest corpMembershipRequest =
				CorpMembershipRequestLocalServiceUtil.getCorpMembershipRequest(
					key);

			CorpMembershipRequestLocalServiceUtil.updateStatus(
				corpMembershipRequest.getCorpMembershipRequestId(),
				WorkflowConstants.STATUS_APPROVED);
		}
		catch (Exception e) {
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceRegistrationPortlet.class);

}