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

package com.liferay.osb.licenseform.portlet;

import com.liferay.osb.exception.LicenseKeySingleUseException;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.util.CMDConstants;
import com.liferay.portal.kernel.captcha.CaptchaTextException;
import com.liferay.portal.kernel.captcha.CaptchaUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.exception.ReservedUserEmailAddressException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Amos Fong
 */
public class LicenseFormPortlet extends MVCPortlet {

	public void registerLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		checkRequiredFields(actionRequest);

		CaptchaUtil.check(actionRequest);

		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String osbCompany = ParamUtil.getString(
			actionRequest, "ExpandoAttribute--osbCompany--");
		String osbDepartment = ParamUtil.getString(
			actionRequest, "ExpandoAttribute--osbDepartment--");
		String osbCompanyRole = ParamUtil.getString(
			actionRequest, "ExpandoAttribute--osbCompanyRole--");
		String osbIndustry = ParamUtil.getString(
			actionRequest, "ExpandoAttribute--osbIndustry--");
		String osbCountry = ParamUtil.getString(
			actionRequest, "ExpandoAttribute--osbCountry--");

		String number = ParamUtil.getString(actionRequest, "number");
		String extension = ParamUtil.getString(actionRequest, "extension");
		int typeId = ParamUtil.getInteger(actionRequest, "typeId");

		boolean osbAgreedToContactEvents = ParamUtil.getBoolean(
			actionRequest, "ExpandoAttribute--osbAgreedToContactEvents--");
		boolean osbAgreedToContactSales = ParamUtil.getBoolean(
			actionRequest, "ExpandoAttribute--osbAgreedToContactSales--");
		boolean osbAgreedToContactTrainings = ParamUtil.getBoolean(
			actionRequest, "ExpandoAttribute--osbAgreedToContactTrainings--");
		boolean agreedToTermsOfUse = ParamUtil.getBoolean(
			actionRequest, "agreedToTermsOfUse");
		boolean agreedToContactTrialLicenses = ParamUtil.getBoolean(
			actionRequest, "agreedToContactTrialLicenses");

		validate(themeDisplay.getCompanyId(), emailAddress);

		PortletPreferences preferences =
			PortletPreferencesFactoryUtil.getPortletSetup(actionRequest);

		String orderUuid = GetterUtil.getString(
			preferences.getValue("orderUuid", StringPool.BLANK));
		int productVersion = GetterUtil.getInteger(
			preferences.getValue("productVersion", StringPool.BLANK));

		String fullName = firstName + StringPool.SPACE + lastName;

		String additionalInfo = getAdditionalInfo(
			firstName, lastName, osbCompany, osbDepartment, osbCompanyRole,
			osbIndustry, osbCountry, number, extension, typeId,
			osbAgreedToContactEvents, osbAgreedToContactSales,
			osbAgreedToContactTrainings, agreedToTermsOfUse,
			agreedToContactTrialLicenses);

		LicenseKeyLocalServiceUtil.addSingleUseLicenseKey(
			orderUuid, productVersion, emailAddress, fullName, additionalInfo);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		if (Validator.isNull(redirect)) {
			SessionMessages.add(actionRequest, "licenseKeySent", emailAddress);
		}
	}

	@Override
	public void serveResource(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		String cmd = ParamUtil.getString(resourceRequest, CMDConstants.CMD);

		try {
			if (cmd.equals(CMDConstants.CAPTCHA)) {
				serveCaptcha(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void checkRequiredFields(ActionRequest actionRequest)
		throws RequiredFieldException {

		String[] requiredFields = getRequiredFields();

		List<String> blankParams = new ArrayList<>();
		List<String> blankLabels = new ArrayList<>();

		for (String field : requiredFields) {
			String[] fieldArray = StringUtil.split(field);

			String param = fieldArray[0];
			String label = fieldArray[1];
			String type = fieldArray[2];

			if (type.equals("boolean")) {
				boolean value = ParamUtil.getBoolean(actionRequest, param);

				if (!value) {
					blankParams.add(param);
					blankLabels.add(label);
				}
			}
			else {
				String value = ParamUtil.getString(actionRequest, param);

				if (Validator.isNull(value)) {
					blankParams.add(param);
					blankLabels.add(label);
				}
			}
		}

		if (!blankParams.isEmpty()) {
			throw new RequiredFieldException(
				StringUtil.merge(blankParams.toArray()),
				StringUtil.merge(blankLabels.toArray()));
		}
	}

	protected String getAdditionalInfo(
		String firstName, String lastName, String company, String department,
		String role, String industry, String country, String number,
		String extension, int typeId, boolean agreedToContactEvents,
		boolean agreedToContactSales, boolean agreedToContactTrainings,
		boolean agreedToTermsOfUse, boolean agreedToContactTrialLicenses) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("agreedToContactEvents", agreedToContactEvents);
		jsonObject.put("agreedToContactSales", agreedToContactSales);
		jsonObject.put("agreedToContactTrainings", agreedToContactTrainings);
		jsonObject.put(
			"agreedToContactTrialLicenses", agreedToContactTrialLicenses);
		jsonObject.put("agreedToTermsOfUse", agreedToTermsOfUse);
		jsonObject.put("company", company);
		jsonObject.put("country", country);
		jsonObject.put("department", department);
		jsonObject.put("extension", extension);
		jsonObject.put("firstName", firstName);
		jsonObject.put("industry", industry);
		jsonObject.put("lastName", lastName);
		jsonObject.put("number", number);
		jsonObject.put("role", role);
		jsonObject.put("typeId", typeId);

		return jsonObject.toString();
	}

	protected String[] getRequiredFields() {
		return _REQUIRED_FIELDS;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof CaptchaTextException ||
			cause instanceof LicenseKeySingleUseException ||
			cause instanceof RequiredFieldException ||
			cause instanceof ReservedUserEmailAddressException ||
			cause instanceof UserEmailAddressException) {

			return true;
		}
		else {
			return false;
		}
	}

	protected void serveCaptcha(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		CaptchaUtil.serveImage(resourceRequest, resourceResponse);
	}

	protected void validate(long companyId, String emailAddress)
		throws PortalException {

		if (Validator.isNull(emailAddress)) {
			throw new UserEmailAddressException();
		}

		if (!Validator.isEmailAddress(emailAddress) ||
			emailAddress.startsWith("postmaster@") ||
			emailAddress.startsWith("root@")) {

			throw new UserEmailAddressException();
		}
	}

	private static final String[] _REQUIRED_FIELDS = {
		"agreedToContactTrialLicenses,contactTrialLicensesLabel,boolean",
		"agreedToTermsOfUse,termsOfUseLabel,boolean",
		"emailAddress,emailAddressLabel,string",
		"firstName,firstNameLabel,string", "lastName,lastNameLabel,string",
		"ExpandoAttribute--osbCompany--,companyLabel,string",
		"ExpandoAttribute--osbCountry--,countryLabel,array"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		LicenseFormPortlet.class);

}