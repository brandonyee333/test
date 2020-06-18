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

package com.liferay.osb.customer.provisioning.web.internal.portlet;

import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.service.LicenseKeyService;
import com.liferay.osb.customer.license.util.LicenseKeyExporter;
import com.liferay.osb.customer.provisioning.web.internal.constants.CustomerProvisioningPortletKeys;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jeremy Fu
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-customer-self-provisioning-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.display-name=OSB Customer Self Provisioning",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/view",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + CustomerProvisioningPortletKeys.SELF_PROVISIONING,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class SelfProvisioningPortlet extends MVCPortlet {

	public void generateLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long accountEntryId = ParamUtil.getLong(
			resourceRequest, "accountEntryId");
		long productEntryId = ParamUtil.getLong(
			resourceRequest, "productEntryId");
		int productMinorVersion = ParamUtil.getInteger(
			resourceRequest, "productMinorVersion");

		LicenseKey licenseKey = _licenseKeyService.addDeveloperLicenseKey(
			accountEntryId, productEntryId, productMinorVersion);

		String fileName = _licenseKeyExporter.getFileName(licenseKey);
		String licenseXML = _licenseKeyExporter.toXML(licenseKey);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, licenseXML.getBytes(),
			ContentTypes.TEXT_XML);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			ProductPurchaseViewWebService.class.getName(),
			_productPurchaseViewWebService);

		super.render(renderRequest, renderResponse);
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

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private LicenseKeyService _licenseKeyService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

}