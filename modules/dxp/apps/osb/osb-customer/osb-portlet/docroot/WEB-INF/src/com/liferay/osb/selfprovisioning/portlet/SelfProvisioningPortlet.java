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

package com.liferay.osb.selfprovisioning.portlet;

import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Jeremy Fu
 * @author Amos Fong
 */
public class SelfProvisioningPortlet extends MVCPortlet {

	public void generateLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			resourceRequest, "accountEntryId");
		String productDisplayName = ParamUtil.getString(
			resourceRequest, "productDisplayName");
		String licenseEntryType = ParamUtil.getString(
			resourceRequest, "licenseEntryType");

		checkPermission(themeDisplay.getUserId(), accountEntryId);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("generateLicenseKey")) {
				generateLicenseKey(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("productDisplayNames")) {
				serveProductDisplayNames(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void checkPermission(long userId, long accountEntryId)
		throws PrincipalException, SystemException {

		if (!AccountCustomerLocalServiceUtil.hasAccountCustomer(
				userId, accountEntryId)) {

			throw new PrincipalException();
		}
	}

	protected void serveProductDisplayNames(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			resourceRequest, "accountEntryId");

		checkPermission(themeDisplay.getUserId(), accountEntryId);

		TreeSet<String> productDisplayNames = new TreeSet<String>();

		LinkedHashMap params = new LinkedHashMap();

		params.put("license", StringPool.BLANK);
		params.put("productEntry", ProductEntryConstants.TYPE_PRIMARY);

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.search(
				0, accountEntryId,
				new int[] {OfferingEntryConstants.TYPE_REGULAR},
				new int[] {OfferingEntryConstants.STATUS_ACTIVE}, 0, 0, 0, 0, 0,
				0, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			productDisplayNames.add(productEntry.getLESADisplayName());
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"productDisplayNames",
			JSONFactoryUtil.looseSerialize(productDisplayNames));

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

}