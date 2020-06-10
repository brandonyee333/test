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

package com.liferay.osb.customer.license.web.internal.portlet;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.service.permission.AccountPermission;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductConsumptionWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.license.exception.DuplicateHostNameException;
import com.liferay.osb.customer.license.exception.DuplicateIPAddressException;
import com.liferay.osb.customer.license.exception.DuplicateMACAddressException;
import com.liferay.osb.customer.license.exception.LicenseKeyDescriptionException;
import com.liferay.osb.customer.license.exception.LicenseKeyHostNameException;
import com.liferay.osb.customer.license.exception.LicenseKeyIPAddressException;
import com.liferay.osb.customer.license.exception.LicenseKeyMACAddressException;
import com.liferay.osb.customer.license.exception.LicenseKeyMaxServersException;
import com.liferay.osb.customer.license.exception.LicenseKeyOwnerException;
import com.liferay.osb.customer.license.exception.LicenseKeyProductVersionException;
import com.liferay.osb.customer.license.exception.LicenseKeyRegistrationException;
import com.liferay.osb.customer.license.exception.LicenseKeyServerIdException;
import com.liferay.osb.customer.license.exception.LicenseKeySetNameException;
import com.liferay.osb.customer.license.exception.MaximumLicenseKeyException;
import com.liferay.osb.customer.license.model.LicenseKey;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.service.LicenseKeyLocalService;
import com.liferay.osb.customer.license.service.LicenseKeyService;
import com.liferay.osb.customer.license.service.LicenseKeySetService;
import com.liferay.osb.customer.license.util.LicenseKeyExporter;
import com.liferay.osb.customer.license.web.internal.constants.CustomerLicensePortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-customer-license-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.display-name=OSB Customer License",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/view",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.name=" + CustomerLicensePortletKeys.LICENSE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class LicensePortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			AccountPermission.class.getName(), _accountPermission);
		renderRequest.setAttribute(
			AccountWebService.class.getName(), _accountWebService);
		renderRequest.setAttribute(
			ProductConsumptionWebService.class.getName(),
			_productConsumptionWebService);
		renderRequest.setAttribute(
			ProductPurchaseViewWebService.class.getName(),
			_productPurchaseViewWebService);
		renderRequest.setAttribute(
			ProductPurchaseWebService.class.getName(),
			_productPurchaseWebService);

		super.render(renderRequest, renderResponse);
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

			if (resourceID.equals("licenseKeySet")) {
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
		long licenseEntryId = ParamUtil.getLong(
			actionRequest, "licenseEntryId");
		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		String koroneikiAccountKey = ParamUtil.getString(
			actionRequest, "koroneikiAccountKey");
		String koroneikiProductPurchaseKey = ParamUtil.getString(
			actionRequest, "koroneikiProductPurchaseKey");
		String accountEntryName = ParamUtil.getString(
			actionRequest, "accountEntryName");
		long clusterId = ParamUtil.getLong(actionRequest, "clusterId");
		int productVersion = ParamUtil.getInteger(
			actionRequest, "productVersion");
		String owner = ParamUtil.getString(actionRequest, "owner");
		int maxServers = ParamUtil.getInteger(actionRequest, "maxServers");
		int maxHttpSessions = ParamUtil.getInteger(
			actionRequest, "maxHttpSessions");
		int maxConcurrentUsers = ParamUtil.getInteger(
			actionRequest, "maxConcurrentUsers");
		int maxUsers = ParamUtil.getInteger(actionRequest, "maxUsers");
		int sizing = ParamUtil.getInteger(actionRequest, "sizing");
		String description = ParamUtil.getString(actionRequest, "description");
		String[] serverIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "serverIds"), "\n");
		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");
		int expirationDateMonth = ParamUtil.getInteger(
			actionRequest, "expirationDateMonth");
		int expirationDateDay = ParamUtil.getInteger(
			actionRequest, "expirationDateDay");
		int expirationDateYear = ParamUtil.getInteger(
			actionRequest, "expirationDateYear");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		Date startDate = _portal.getDate(
			startDateMonth, startDateDay, startDateYear,
			themeDisplay.getTimeZone(), (Class<? extends PortalException>)null);
		Date expirationDate = _portal.getDate(
			expirationDateMonth, expirationDateDay, expirationDateYear,
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
			Date expirationDate = null;

			boolean aggregateLicense = ParamUtil.getBoolean(
				actionRequest, "aggregateLicense");

			if (aggregateLicense && LicenseUtil.isAggregate(licenseKeySetId)) {
				List<LicenseKey> licenseKeys =
					_licenseKeyLocalService.getLicenseKeySetLicenseKeys(
						licenseKeySetId);

				for (LicenseKey curLicenseKey : licenseKeys) {
					if (curLicenseKey.isActive()) {
						startDate = curLicenseKey.getStartDate();
						expirationDate = curLicenseKey.getExpirationDate();

						break;
					}
				}
			}

			licenseKey = _licenseKeyService.addLicenseKey(
				themeDisplay.getUserId(), licenseKeySetId, name, licenseEntryId,
				productEntryId, koroneikiAccountKey,
				koroneikiProductPurchaseKey, accountEntryName, productVersion,
				clusterId, owner, maxServers, maxHttpSessions,
				maxConcurrentUsers, maxUsers, sizing, description,
				hostNames.toArray(new String[0]),
				ipAddresses.toArray(new String[0]),
				macAddresses.toArray(new String[0]), serverIds, startDate,
				expirationDate, false, true);

			actionRequest.setAttribute("clusterId", licenseKey.getClusterId());
			actionRequest.setAttribute(
				"licenseKeySetId", licenseKey.getLicenseKeySetId());
		}
		else {
			//licenseKey = _licenseKeyService.updateLicenseKey(
			//	themeDisplay.getUserId(), licenseKeyId, licenseKeySetId,
			//	offeringEntryId, name, active);
		}
	}

	public void updateLicenseKeySet(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long licenseKeySetId = ParamUtil.getLong(
			actionRequest, "licenseKeySetId");

		String name = ParamUtil.getString(actionRequest, "name");

		_licenseKeySetService.updateLicenseKeySet(licenseKeySetId, name);
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

			HttpServletRequest request = _portal.getHttpServletRequest(
				actionRequest);

			Long offeringEntryId = (Long)actionRequest.getAttribute(
				"offeringEntryId");
			Long clusterId = (Long)actionRequest.getAttribute("clusterId");

			PortletURL portletURL = PortletURLFactoryUtil.create(
				request, CustomerLicensePortletKeys.LICENSE,
				themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

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

			if (_organizationLocalService.hasUserOrganization(
					themeDisplay.getUserId(),
					OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				return true;
			}

			/*

			if (AccountEntryLocalServiceUtil.hasValidLicenseAccountEntry(
					themeDisplay.getUserId())) {

				return true;
			}
			*/
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
			cause instanceof MaximumLicenseKeyException) {

			//cause instanceof OfferingEntryStatusException) {

			return true;
		}

		return false;
	}

	protected void serveLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long licenseKeyId = ParamUtil.getLong(resourceRequest, "licenseKeyId");

		LicenseKey licenseKey = _licenseKeyService.getLicenseKey(licenseKeyId);

		if (!_organizationLocalService.hasUserOrganization(
				themeDisplay.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return;
		}

		int licenseVersion = licenseKey.getLicenseVersion();

		if (licenseVersion == 1) {
			String encodedLicenseFile =
				_licenseKeyExporter.toEncodedLicenseFile(licenseKey);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, "license",
				encodedLicenseFile.getBytes(),
				ContentTypes.APPLICATION_OCTET_STREAM);
		}
		else if (licenseVersion >= 2) {
			String fileName = _licenseKeyExporter.getFileName(licenseKey);
			String licenseXML = _licenseKeyExporter.toXML(licenseKey);

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

		if (!_organizationLocalService.hasUserOrganization(
				themeDisplay.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return;
		}

		long licenseKeySetId = ParamUtil.getLong(
			resourceRequest, "licenseKeySetId");

		LicenseKeySet licenseKeySet = _licenseKeySetService.getLicenseKeySet(
			licenseKeySetId);

		List<LicenseKey> licenseKeys =
			_licenseKeyLocalService.getLicenseKeySetLicenseKeys(
				licenseKeySet.getLicenseKeySetId());

		if (licenseKeys.isEmpty()) {
			return;
		}

		LicenseKey licenseKey = licenseKeys.get(0);

		String fileName = _licenseKeyExporter.getFileName(licenseKey);

		String licenseXML = _licenseKeyExporter.toXML(licenseKeySet);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, licenseXML.getBytes(),
			ContentTypes.TEXT_XML);
	}

	private static final Log _log = LogFactoryUtil.getLog(LicensePortlet.class);

	@Reference
	private AccountPermission _accountPermission;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private LicenseKeyExporter _licenseKeyExporter;

	@Reference
	private LicenseKeyLocalService _licenseKeyLocalService;

	@Reference
	private LicenseKeyService _licenseKeyService;

	@Reference
	private LicenseKeySetService _licenseKeySetService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private ProductConsumptionWebService _productConsumptionWebService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

}