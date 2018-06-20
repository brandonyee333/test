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

import com.liferay.compat.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;

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

		long accountEntryId = ParamUtil.getLong(
			resourceRequest, "accountEntryId");
		String productEntryRootName = ParamUtil.getString(
			resourceRequest, "productEntryRootName");
		int productMinorVersion = ParamUtil.getInteger(
			resourceRequest, "productMinorVersion");
		String licenseEntryType = ParamUtil.getString(
			resourceRequest, "licenseEntryType");

		LicenseKey licenseKey = LicenseKeyServiceUtil.addDeveloperLicenseKey(
			accountEntryId, productEntryRootName, productMinorVersion,
			licenseEntryType);

		String fileName = LicenseUtil.getLicenseKeyFileName(licenseKey);
		String licenseXML = LicenseUtil.exportToXML(licenseKey);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, licenseXML.getBytes(),
			ContentTypes.TEXT_XML);
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
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

}