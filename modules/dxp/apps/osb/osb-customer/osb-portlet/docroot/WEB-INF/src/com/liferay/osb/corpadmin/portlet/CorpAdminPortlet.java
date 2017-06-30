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

package com.liferay.osb.corpadmin.portlet;

import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.WebKeys;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.corpadmin.util.CorpAdminCommitUtil;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpGroup;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpEntryServiceUtil;
import com.liferay.osb.service.CorpGroupLocalServiceUtil;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.CorpEntryNameComparator;
import com.liferay.osb.util.comparator.CorpGroupNameComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Peter Shin
 * @author Joan Kim
 * @author Rachael Koestartyo
 * @author Haote Chou
 */
public class CorpAdminPortlet extends MVCPortlet {

	public void deleteCorpEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpEntryId = ParamUtil.getLong(actionRequest, "corpEntryId");

		CorpEntryServiceUtil.deleteCorpEntry(corpEntryId);
	}

	public void expireCorpEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long corpEntryId = ParamUtil.getLong(actionRequest, "corpEntryId");

		String statusMessage = ParamUtil.getString(
			actionRequest, "statusMessage");

		CorpAdminCommitUtil.updateStatus(
			themeDisplay.getUserId(), corpEntryId,
			WorkflowConstants.STATUS_EXPIRED, statusMessage);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = GetterUtil.getString(
				resourceRequest.getResourceID());

			if (resourceID.equals("serveAssetAttachmentDocument")) {
				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_DOCUMENT);
			}
			else if (resourceID.equals("serveAssetAttachmentMedia")) {
				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_MEDIA);
			}
			else if (resourceID.equals("serveCorpEntries")) {
				serveCorpEntries(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveCorpEntry")) {
				serveCorpEntry(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveCorpGroups")) {
				serveCorpGroups(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateCorpEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			long corpEntryId = ParamUtil.getLong(
				uploadPortletRequest, "corpEntryId");

			String name = ParamUtil.getString(uploadPortletRequest, "name");
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					uploadPortletRequest, "description");
			File logo = uploadPortletRequest.getFile("logo");
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
			String contactEmailAddress = ParamUtil.getString(
				uploadPortletRequest, "contactEmailAddress");
			String profileEmailAddress = ParamUtil.getString(
				uploadPortletRequest, "profileEmailAddress");
			String phoneNumber = ParamUtil.getString(
				uploadPortletRequest, "phoneNumber");
			String faxNumber = ParamUtil.getString(
				uploadPortletRequest, "faxNumber");
			String website = ParamUtil.getString(
				uploadPortletRequest, "website");
			String dossieraAccountKey = ParamUtil.getString(
				uploadPortletRequest, "dossieraAccountKey");
			long[] corpGroupIds = ParamUtil.getLongValues(
				uploadPortletRequest, "corpGroupIds");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				uploadPortletRequest);

			CorpAdminCommitUtil.updateCorpEntry(
				corpEntryId, name, descriptionMap, logo, street1, street2,
				street3, city, zip, regionId, countryId, contactEmailAddress,
				profileEmailAddress, phoneNumber, faxNumber, website,
				dossieraAccountKey, corpGroupIds, null, null, serviceContext);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateCorpGroup(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			long corpGroupId = ParamUtil.getLong(actionRequest, "corpGroupId");

			String name = ParamUtil.getString(actionRequest, "name");
			Map<Locale, String> descriptionMap =
				LocalizationUtil.getLocalizationMap(
					actionRequest, "description");
			File logo = uploadPortletRequest.getFile("logo");
			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");
			String website = ParamUtil.getString(actionRequest, "website");
			long[] corpEntryIds = ParamUtil.getLongValues(
				actionRequest, "corpEntryIds");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			CorpAdminCommitUtil.updateCorpGroup(
				corpGroupId, name, descriptionMap, logo, emailAddress, website,
				corpEntryIds, serviceContext);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	protected void serveCorpEntries(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String keywords = ParamUtil.getString(resourceRequest, "keywords");
		int status = ParamUtil.getInteger(resourceRequest, "status");
		long[] notCorpEntryIds = ParamUtil.getLongValues(
			resourceRequest, "notCorpEntryIds");
		int start = ParamUtil.getInteger(resourceRequest, "start");
		int end = ParamUtil.getInteger(resourceRequest, "end");

		List<CorpEntry> corpEntries = CorpEntryLocalServiceUtil.search(
			keywords, status, notCorpEntryIds, start, end,
			new CorpEntryNameComparator());

		for (CorpEntry corpEntry : corpEntries) {
			JSONObject corpEntryJSONObject = JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(corpEntry));

			Address address = AddressLocalServiceUtil.fetchAddress(
				corpEntry.getAddressId());

			if (address != null) {
				JSONObject addressJSONObject = JSONFactoryUtil.createJSONObject(
					JSONFactoryUtil.looseSerialize(address));

				corpEntryJSONObject.put("address", addressJSONObject);
			}

			jsonArray.put(corpEntryJSONObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	protected void serveCorpEntry(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		long corpEntryId = ParamUtil.getLong(resourceRequest, "corpEntryId");

		CorpEntry corpEntry = CorpEntryLocalServiceUtil.fetchCorpEntry(
			corpEntryId);

		if (corpEntry != null) {
			JSONObject corpEntryJSONObject = JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(corpEntry));

			Address address = AddressLocalServiceUtil.fetchAddress(
				corpEntry.getAddressId());

			if (address != null) {
				JSONObject addressJSONObject = JSONFactoryUtil.createJSONObject(
					JSONFactoryUtil.looseSerialize(address));

				corpEntryJSONObject.put("address", addressJSONObject);
			}

			jsonArray.put(corpEntryJSONObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	protected void serveCorpGroups(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String keywords = ParamUtil.getString(resourceRequest, "keywords");
		long[] notCorpGroupIds = ParamUtil.getLongValues(
			resourceRequest, "notCorpGroupIds");
		int start = ParamUtil.getInteger(resourceRequest, "start");
		int end = ParamUtil.getInteger(resourceRequest, "end");

		List<CorpGroup> corpGroups = CorpGroupLocalServiceUtil.search(
			keywords, notCorpGroupIds, start, end,
			new CorpGroupNameComparator());

		for (CorpGroup corpGroup : corpGroups) {
			JSONObject corpGroupJSONObject = JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerialize(corpGroup));

			jsonArray.put(corpGroupJSONObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

}