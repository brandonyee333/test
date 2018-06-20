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

package com.liferay.osb.selfprovisioning.portlet;

import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

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

		LicenseKey licenseKey = LicenseKeyServiceUtil.addDeveloperLicenseKey(
			accountEntryId, productEntryRootName, productMinorVersion);

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