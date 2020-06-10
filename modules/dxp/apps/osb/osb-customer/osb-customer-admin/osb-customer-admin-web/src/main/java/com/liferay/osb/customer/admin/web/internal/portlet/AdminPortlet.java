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

package com.liferay.osb.customer.admin.web.internal.portlet;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.osb.customer.admin.constants.AccountAttachmentConstants;
import com.liferay.osb.customer.admin.exception.AccountEntryLanguageIdException;
import com.liferay.osb.customer.admin.exception.AccountEntrySupportRegionException;
import com.liferay.osb.customer.admin.exception.DuplicateProductEntryException;
import com.liferay.osb.customer.admin.exception.DuplicateSupportRegionException;
import com.liferay.osb.customer.admin.exception.LicenseEntryNameException;
import com.liferay.osb.customer.admin.exception.LicenseEntryPortalVersionException;
import com.liferay.osb.customer.admin.exception.NoSuchAccountEntryException;
import com.liferay.osb.customer.admin.exception.NoSuchProductEntryException;
import com.liferay.osb.customer.admin.exception.ProductEntryEnvironmentException;
import com.liferay.osb.customer.admin.exception.ProductEntryKoroneikiProductKeyException;
import com.liferay.osb.customer.admin.exception.RequiredProductEntryException;
import com.liferay.osb.customer.admin.exception.RequiredSupportRegionException;
import com.liferay.osb.customer.admin.exception.SupportRegionNameException;
import com.liferay.osb.customer.admin.exception.ZendeskTagException;
import com.liferay.osb.customer.admin.model.AccountAttachment;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.osb.customer.admin.service.AccountAttachmentLocalService;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.LicenseEntryLocalService;
import com.liferay.osb.customer.admin.service.ProductEntryLocalService;
import com.liferay.osb.customer.admin.service.SupportRegionLocalService;
import com.liferay.osb.customer.admin.web.internal.constants.CustomerAdminPortletKeys;
import com.liferay.osb.customer.admin.web.internal.constants.CustomerAdminWebKeys;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.UserEmailAddressException;
import com.liferay.portal.kernel.io.Base64InputStream;
import com.liferay.portal.kernel.io.ProtectedObjectInputStream;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Haote Chou
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-customer-admin-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.display-name=OSB Customer Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.mvc-command-names-default-views=/view",
		"javax.portlet.name=" + CustomerAdminPortletKeys.ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class AdminPortlet extends MVCPortlet {

	public void debugLicenseFiles(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		try {

			// License file

			File licenseFile = uploadPortletRequest.getFile("license");

			if ((licenseFile != null) && (licenseFile.length() > 0)) {
				fileInputStream = new FileInputStream(licenseFile);

				objectInputStream = new ProtectedObjectInputStream(
					new Base64InputStream(fileInputStream));

				int binaryLicenseVersion = objectInputStream.readInt();

				if (binaryLicenseVersion >= 3) {
					Properties properties = getLicenseProperties(
						binaryLicenseVersion, objectInputStream);

					actionRequest.setAttribute("licenseProperties", properties);
				}
			}

			// Server ID

			File serverIdFile = uploadPortletRequest.getFile("serverId");

			if ((serverIdFile != null) && (serverIdFile.length() > 0)) {
				/*
				byte[] bytes = FileUtil.getBytes(serverIdFile);

				Properties serverProperties = KeyGenerator.decryptServerId(
					bytes);

				actionRequest.setAttribute(
					"serverProperties", serverProperties);

				String serverId = serverProperties.getProperty("serverId");

				byte[] serverIdBytes = (byte[])Base64.stringToObject(serverId);

				Properties serverIdProperties = KeyGenerator.decryptServerId(
					serverIdBytes);

				actionRequest.setAttribute(
					"serverIdProperties", serverIdProperties);
				*/
			}
		}
		finally {
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				}
				catch (IOException ioe) {
				}
			}

			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				}
				catch (IOException ioe) {
				}
			}

			uploadPortletRequest.cleanUp();
		}
	}

	public void deleteAccountEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		_accountEntryLocalService.deleteAccountEntry(
			themeDisplay.getUserId(), accountEntryId);
	}

	public void deleteLicenseEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long licenseEntryId = ParamUtil.getLong(
			actionRequest, "licenseEntryId");

		_licenseEntryLocalService.deleteLicenseEntry(licenseEntryId);
	}

	public void deleteProductEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");

		ProductEntry productEntry = _productEntryLocalService.getProductEntry(
			productEntryId);

		Product product = _productWebService.fetchProduct(
			productEntry.getKoroneikiProductKey());

		if (product != null) {
			throw new RequiredProductEntryException();
		}

		_productEntryLocalService.deleteProductEntry(productEntryId);
	}

	public void deleteSupportRegion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long supportRegionId = ParamUtil.getLong(
			actionRequest, "supportRegionId");

		_supportRegionLocalService.deleteSupportRegion(supportRegionId);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(
			AccountWebService.class.getName(), _accountWebService);
		renderRequest.setAttribute(
			ProductWebService.class.getName(), _productWebService);
		renderRequest.setAttribute(
			TeamRoleWebService.class.getName(), _teamRoleWebService);
		renderRequest.setAttribute(
			TeamWebService.class.getName(), _teamWebService);

		long accountEntryId = ParamUtil.getLong(
			renderRequest, "accountEntryId");

		if (accountEntryId > 0) {
			AccountEntry accountEntry =
				_accountEntryLocalService.fetchAccountEntry(accountEntryId);

			if (accountEntry != null) {
				renderRequest.setAttribute(
					CustomerAdminWebKeys.ACCOUNT_ENTRY, accountEntry);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("accountAttachment")) {
				serveAccountAttachment(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("accountEntries")) {
				serveAccountEntries(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void syncToZendesk(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		Message message = new Message();

		message.put("accountEntryId", accountEntryId);

		MessageBusUtil.sendMessage(
			"liferay/zendesk_account_entry_sync", message);
	}

	public void updateAccountEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long accountEntryId = ParamUtil.getLong(
			actionRequest, "accountEntryId");

		String koroneikiAccountKey = ParamUtil.getString(
			actionRequest, "koroneikiAccountKey");
		String dossieraAccountKey = ParamUtil.getString(
			actionRequest, "dossieraAccountKey");
		String instructions = ParamUtil.getString(
			actionRequest, "instructions");
		String[] languageIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "languageIds"));
		long[] supportRegionIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "supportRegionIds"), 0L);

		if (accountEntryId <= 0) {
			_accountEntryLocalService.addAccountEntry(
				themeDisplay.getUserId(), koroneikiAccountKey,
				dossieraAccountKey, null, null, instructions,
				WorkflowConstants.STATUS_APPROVED, languageIds,
				supportRegionIds);
		}
		else {
			AccountEntry accountEntry =
				_accountEntryLocalService.getAccountEntry(accountEntryId);

			_accountEntryLocalService.updateAccountEntry(
				themeDisplay.getUserId(), accountEntryId, koroneikiAccountKey,
				dossieraAccountKey, accountEntry.getName(),
				accountEntry.getCode(), instructions, accountEntry.getStatus(),
				languageIds, supportRegionIds);
		}

		updateAccountAttachment(actionRequest);
	}

	public void updateLicenseEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long licenseEntryId = ParamUtil.getLong(
			actionRequest, "licenseEntryId");

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");
		String name = ParamUtil.getString(actionRequest, "name");
		String type = ParamUtil.getString(actionRequest, "type");
		int versionMin = ParamUtil.getInteger(actionRequest, "versionMin");
		int versionMax = ParamUtil.getInteger(actionRequest, "versionMax");

		if (licenseEntryId <= 0) {
			_licenseEntryLocalService.addLicenseEntry(
				themeDisplay.getUserId(), productEntryId, name, type,
				versionMin, versionMax);
		}
		else {
			licenseEntryLocalService.updateLicenseEntry(
				licenseEntryId, productEntryId, name, type, versionMin,
				versionMax);
		}
	}

	public void updateProductEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long productEntryId = ParamUtil.getLong(
			actionRequest, "productEntryId");

		String koroneikiProductKey = ParamUtil.getString(
			actionRequest, "koroneikiProductKey");
		int environment = ParamUtil.getInteger(actionRequest, "environment");
		boolean licenses = ParamUtil.getBoolean(actionRequest, "licenses");
		String versionsListType = ParamUtil.getString(
			actionRequest, "versionsListType");
		String zendeskTag = ParamUtil.getString(actionRequest, "zendeskTag");

		ProductEntry productEntry = null;

		if (productEntryId <= 0) {
			productEntry = _productEntryLocalService.addProductEntry(
				themeDisplay.getUserId(), koroneikiProductKey, null,
				environment, licenses, versionsListType, zendeskTag);
		}
		else {
			productEntry = _productEntryLocalService.getProductEntry(
				productEntryId);

			_productEntryLocalService.updateProductEntry(
				productEntryId, koroneikiProductKey, productEntry.getName(),
				environment, licenses, versionsListType, zendeskTag);
		}

		Product product = _productWebService.fetchProduct(koroneikiProductKey);

		if (product != null) {
			_productEntryLocalService.updateName(
				productEntry.getProductEntryId(), product.getName());
		}
	}

	public void updateSupportRegion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long supportRegionId = ParamUtil.getLong(
			actionRequest, "supportRegionId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");

		if (supportRegionId > 0) {
			_supportRegionLocalService.updateSupportRegion(
				supportRegionId, name, description, timeZoneId);
		}
		else {
			_supportRegionLocalService.addSupportRegion(
				themeDisplay.getUserId(), name, description, timeZoneId);
		}
	}

	protected Properties getLicenseProperties(
			int binaryLicenseVersion, ObjectInputStream objectInputStream)
		throws ClassNotFoundException, IOException {

		String accountEntryName = objectInputStream.readUTF();
		String description = objectInputStream.readUTF();
		Date expirationDate = (Date)objectInputStream.readObject();
		String[] hostNames = (String[])objectInputStream.readObject();
		String[] ipAddresses = (String[])objectInputStream.readObject();
		String key = objectInputStream.readUTF();
		long lastAccessedTime = objectInputStream.readLong();
		String licenseEntryName = objectInputStream.readUTF();
		String licenseEntryType = objectInputStream.readUTF();
		String licenseVersion = objectInputStream.readUTF();
		String[] macAddresses = (String[])objectInputStream.readObject();
		int maxHttpSessions = objectInputStream.readInt();
		int maxServers = objectInputStream.readInt();
		long maxConcurrentUsers = objectInputStream.readLong();
		long maxUsers = objectInputStream.readLong();

		String instanceSize = StringPool.BLANK;

		if (binaryLicenseVersion >= 4) {
			instanceSize = objectInputStream.readUTF();
		}

		String owner = objectInputStream.readUTF();
		String productEntryName = objectInputStream.readUTF();
		String productId = objectInputStream.readUTF();
		String productVersion = objectInputStream.readUTF();
		String[] serverIds = (String[])objectInputStream.readObject();
		Date startDate = (Date)objectInputStream.readObject();

		DateFormat longDateFormatDateTime = DateFormat.getDateTimeInstance(
			DateFormat.FULL, DateFormat.FULL, LocaleUtil.US);

		Properties properties = new Properties();

		properties.put("accountEntryName", accountEntryName);
		properties.put("description", description);
		properties.put(
			"expirationDate", longDateFormatDateTime.format(expirationDate));
		properties.put("hostNames", StringUtil.merge(hostNames));
		properties.put("ipAddresses", StringUtil.merge(ipAddresses));
		properties.put("key", key);
		properties.put("instanceSize", instanceSize);
		properties.put("lastAccessedTime", lastAccessedTime);
		properties.put("licenseEntryName", licenseEntryName);
		properties.put("licenseEntryType", licenseEntryType);
		properties.put("licenseVersion", licenseVersion);
		properties.put("macAddresses", StringUtil.merge(macAddresses));
		properties.put("maxHttpSessions", maxHttpSessions);
		properties.put("maxServers", maxServers);
		properties.put("maxConcurrentUsers", maxConcurrentUsers);
		properties.put("maxUsers", maxUsers);
		properties.put("owner", owner);
		properties.put("productEntryName", productEntryName);
		properties.put("productId", productId);
		properties.put("productVersion", productVersion);
		properties.put("serverIds", StringUtil.merge(serverIds));
		properties.put("startDate", longDateFormatDateTime.format(startDate));

		return properties;
	}

	@Override
	protected String getRedirect(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String redirect = null;

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(
				actionRequest, "assignmentsRedirect");
		}

		if (Validator.isNull(redirect)) {
			redirect = super.getRedirect(actionRequest, actionResponse);
		}

		return redirect;
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (_roleLocalService.hasUserRole(
					themeDisplay.getUserId(),
					OSBCustomerConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

				return true;
			}

			if (_roleLocalService.hasUserRole(
					themeDisplay.getUserId(),
					OSBCustomerConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

				return true;
			}

			if (_roleLocalService.hasUserRole(
					themeDisplay.getUserId(),
					OSBCustomerConstants.ROLE_OSB_SUPPORT_ADMIN_ID)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}

		if (cause instanceof AccountEntryLanguageIdException ||
			cause instanceof AccountEntrySupportRegionException ||
			cause instanceof DuplicateProductEntryException ||
			cause instanceof DuplicateSupportRegionException ||
			cause instanceof FileExtensionException ||
			cause instanceof FileNameException ||
			cause instanceof LicenseEntryNameException ||
			cause instanceof LicenseEntryVersionException ||
			cause instanceof ModelListenerException ||
			cause instanceof NoSuchAccountEntryException ||
			cause instanceof NoSuchListTypeException ||
			cause instanceof NoSuchProductEntryException ||
			cause instanceof PrincipalException ||
			cause instanceof ProductEntryEnvironmentException ||
			cause instanceof ProductEntryKoroneikiProductKeyException ||
			cause instanceof RequiredProductEntryException ||
			cause instanceof RequiredSupportRegionException ||
			cause instanceof SupportRegionNameException ||
			cause instanceof UserEmailAddressException ||
			cause instanceof ZendeskTagException) {

			return true;
		}

		return false;
	}

	protected void serveAccountAttachment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException {

		long accountAttachmentId = ParamUtil.getLong(
			resourceRequest, "accountAttachmentId");

		AccountAttachment accountAttachment =
			_accountAttachmentLocalService.getAccountAttachment(
				accountAttachmentId);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, accountAttachment.getFileName(),
			_accountAttachmentLocalService.getFileAsStream(accountAttachment),
			accountAttachment.getContentLength(),
			MimeTypesUtil.getContentType(accountAttachment.getFileName()),
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	protected void serveAccountEntries(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		String code = ParamUtil.getString(resourceRequest, "code");
		String name = ParamUtil.getString(resourceRequest, "name");

		List<AccountEntry> accountEntries = new ArrayList<>();

		JSONArray accountEntriesArray = JSONFactoryUtil.createJSONArray();

		for (AccountEntry accountEntry : accountEntries) {

			// TODO

			if (Validator.isNotNull(code)) {
				accountEntriesArray.put(accountEntry.getName());
			}

			if (Validator.isNotNull(name)) {
				accountEntriesArray.put(accountEntry.getName());
			}
		}

		writeJSON(resourceRequest, resourceResponse, accountEntriesArray);
	}

	protected void updateAccountAttachment(ActionRequest actionRequest)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		long accountAttachmentId = ParamUtil.getLong(
			uploadPortletRequest, "accountAttachmentId");

		boolean deleteAccountAttachment = ParamUtil.getBoolean(
			uploadPortletRequest, "deleteAccountAttachment");

		if (deleteAccountAttachment) {
			_accountAttachmentLocalService.deleteAccountAttachment(
				accountAttachmentId);
		}

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)uploadPortletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			long accountEntryId = ParamUtil.getLong(
				uploadPortletRequest, "accountEntryId");
			long accountProjectId = ParamUtil.getLong(
				uploadPortletRequest, "accountProjectId");

			String fileName = uploadPortletRequest.getFileName(
				"accountAttachment");

			File file = uploadPortletRequest.getFile("accountAttachment");

			if ((file == null) || (file.length() <= 0)) {
				return;
			}

			ObjectValuePair<String, File> ovp = new ObjectValuePair<>(
				fileName, file);

			_accountAttachmentLocalService.addAccountAttachment(
				themeDisplay.getUserId(), accountEntryId, accountProjectId, ovp,
				AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

	@Reference
	private AccountAttachmentLocalService _accountAttachmentLocalService;

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private LicenseEntryLocalService _licenseEntryLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

	@Reference
	private ProductWebService _productWebService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private SupportRegionLocalService _supportRegionLocalService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

}