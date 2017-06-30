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

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpMembershipRequestLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.ContentUtil;

import java.io.File;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
public class CorpMembersUtil {

	public static List<User> getCorpEntryUsers(
			long corpEntryId, String keywords, long roleId, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
			corpEntryId);

		Group group = corpEntry.getGroup();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (roleId > 0) {
			params.put(
				"userGroupRole", new Long[] {group.getGroupId(), roleId});
		}

		params.put("usersOrgs", corpEntry.getOrganizationId());

		return UserLocalServiceUtil.search(
			group.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED,
			params, start, end, obc);
	}

	public static int getCorpEntryUsersCount(
			long corpEntryId, String keywords, long roleId)
		throws PortalException, SystemException {

		CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
			corpEntryId);

		Group group = corpEntry.getGroup();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (roleId > 0) {
			params.put(
				"userGroupRole", new Long[] {group.getGroupId(), roleId});
		}

		params.put("usersOrgs", corpEntry.getOrganizationId());

		return UserLocalServiceUtil.searchCount(
			group.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED,
			params);
	}

	public static List<User> getCorpProjectUsers(
			long corpProjectId, String keywords, long roleId, int start,
			int end, OrderByComparator obc)
		throws PortalException, SystemException {

		CorpProject corpProject = CorpProjectLocalServiceUtil.getCorpProject(
			corpProjectId);

		Group group = corpProject.getGroup();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (roleId > 0) {
			params.put(
				"userGroupRole", new Long[] {group.getGroupId(), roleId});
		}

		params.put("usersOrgs", corpProject.getOrganizationId());

		return UserLocalServiceUtil.search(
			group.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED,
			params, start, end, obc);
	}

	public static int getCorpProjectUsersCount(
			long corpProjectId, String keywords, long roleId)
		throws PortalException, SystemException {

		CorpProject corpProject = CorpProjectLocalServiceUtil.getCorpProject(
			corpProjectId);

		Group group = corpProject.getGroup();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (roleId > 0) {
			params.put(
				"userGroupRole", new Long[] {group.getGroupId(), roleId});
		}

		params.put("usersOrgs", corpProject.getOrganizationId());

		return UserLocalServiceUtil.searchCount(
			group.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED,
			params);
	}

	public static Map<Locale, String> getEmailCorpMembershipRequestBodyMap(
		PortletPreferences preferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, "emailCorpMembershipRequestBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/corpmembers/dependencies/" +
					"email_membership_request_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailCorpMembershipRequestSubjectMap(
		PortletPreferences preferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, "emailCorpMembershipRequestSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/corpmembers/dependencies/" +
					"email_membership_request_subject.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailCorpMembershipValidateBodyMap(
		PortletPreferences preferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, "emailCorpMembershipValidateBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/corpmembers/dependencies/" +
					"email_membership_validate_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailCorpMembershipValidateSubjectMap(
		PortletPreferences preferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, "emailCorpMembershipValidateSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/corpmembers/dependencies/" +
					"email_membership_validate_subject.tmpl"));

		return map;
	}

	public static JSONObject sendCorpMembershipRequest(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long corpEntryId = ParamUtil.getLong(actionRequest, "corpEntryId");

		CorpMembershipRequestLocalServiceUtil.addCorpMembershipRequest(
			themeDisplay.getUserId(), corpEntryId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", "success");

		return jsonObject;
	}

	public static void updateCorpEntry(
			UploadPortletRequest uploadPortletRequest,
			ActionResponse actionResponse, boolean updateName)
		throws Exception {

		long corpEntryId = ParamUtil.getLong(
			uploadPortletRequest, "corpEntryId");

		CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
			corpEntryId);

		String name = ParamUtil.getString(
			uploadPortletRequest, "name", corpEntry.getName());

		if (!updateName) {
			name = corpEntry.getName();
		}

		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(
				uploadPortletRequest, "description");
		File logoFile = uploadPortletRequest.getFile("logo");
		String street1 = ParamUtil.getString(uploadPortletRequest, "street1");
		String street2 = ParamUtil.getString(uploadPortletRequest, "street2");
		String street3 = ParamUtil.getString(uploadPortletRequest, "street3");
		String city = ParamUtil.getString(uploadPortletRequest, "city");
		String zip = ParamUtil.getString(uploadPortletRequest, "zip");
		long regionId = ParamUtil.getLong(uploadPortletRequest, "regionId");
		long countryId = ParamUtil.getLong(uploadPortletRequest, "countryId");
		String contactEmailAddress = ParamUtil.getString(
			uploadPortletRequest, "contactEmailAddress");
		String profileEmailAddress = ParamUtil.getString(
			uploadPortletRequest, "profileEmailAddress");
		String phoneNumber = ParamUtil.getString(
			uploadPortletRequest, "phoneNumber");
		String faxNumber = ParamUtil.getString(
			uploadPortletRequest, "faxNumber");
		String website = ParamUtil.getString(uploadPortletRequest, "website");
		String taxDocumentType = ParamUtil.getString(
			uploadPortletRequest, "taxDocumentType");
		File taxDocumentFile = uploadPortletRequest.getFile("taxDocument");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			uploadPortletRequest);

		CorpMembersCommitUtil.updateCorpEntry(
			corpEntryId, name, descriptionMap, logoFile, street1, street2,
			street3, city, zip, regionId, countryId, contactEmailAddress,
			profileEmailAddress, phoneNumber, faxNumber, website,
			taxDocumentType, taxDocumentFile, serviceContext);
	}

}