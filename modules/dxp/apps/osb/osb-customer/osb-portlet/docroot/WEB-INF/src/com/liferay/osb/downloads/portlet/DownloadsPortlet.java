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

package com.liferay.osb.downloads.portlet;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.downloads.util.DownloadsUtil;
import com.liferay.osb.util.AgreementUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouterUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class DownloadsPortlet extends MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		if (!isProcessActionRequest(actionRequest)) {
			return;
		}

		String fileName = ParamUtil.getString(actionRequest, "fileName");

		if (requiresStudioEULA(actionRequest, fileName)) {
			String downloadPage = getDownloadPage(actionRequest);

			actionResponse.sendRedirect(downloadPage);
		}
		else {
			String url = _URL_PREFIX + fileName;

			String key = HttpUtil.URLtoString(url, true);

			url = _URL_PREFIX + StringPool.SLASH + key;

			actionResponse.sendRedirect(url);

			sendAudit(actionRequest, fileName);
		}
	}

	protected String generateCode(String fileName) {
		String shortFileName = FileUtil.getShortFileName(fileName);

		return DigesterUtil.digestHex(Digester.MD5, shortFileName + _CODE_SALT);
	}

	protected String getDownloadPage(ActionRequest actionRequest) {
		String downloadPage = StringPool.BLANK;

		try {
			PortletPreferences preferences = actionRequest.getPreferences();

			String portletResource = ParamUtil.getString(
				actionRequest, "portletResource");

			if (Validator.isNotNull(portletResource)) {
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(
					actionRequest, portletResource);
			}

			downloadPage = preferences.getValue("downloadPage", null);
		}
		catch (Exception e) {
		}

		return downloadPage;
	}

	protected boolean isCustomerAccess(
		ThemeDisplay themeDisplay, PortletPreferences preferences,
		String fileName) {

		if (themeDisplay.getScopeGroupId() == OSBConstants.GROUP_CUSTOMER_ID) {
			return true;
		}

		String customerAccessPatternString = preferences.getValue(
			"customerAccessPattern", null);

		if (Validator.isNull(customerAccessPatternString)) {
			return false;
		}

		if ((_customerAccessPattern == null) ||
			!customerAccessPatternString.equals(_customerAccessPatternString)) {

			_customerAccessPatternString = customerAccessPatternString;

			_customerAccessPattern = Pattern.compile(
				customerAccessPatternString);
		}

		Matcher matcher = _customerAccessPattern.matcher(fileName);

		if (!matcher.matches()) {
			return false;
		}

		if (OrganizationLocalServiceUtil.hasUserOrganization(
				themeDisplay.getUserId(),
				OSBConstants.ORGANIZATION_CUSTOMER_DXP_ID) ||
			OrganizationLocalServiceUtil.hasUserOrganization(
				themeDisplay.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID) ||
			OrganizationLocalServiceUtil.hasUserOrganization(
				themeDisplay.getUserId(),
				OSBConstants.ORGANIZATION_PARTNER_ID)) {

			return true;
		}

		return false;
	}

	protected boolean isGuestAccess(
		PortletPreferences preferences, String fileName, String code) {

		String guestAccessPatternString = preferences.getValue(
			"guestAccessPattern", null);

		if (Validator.isNull(guestAccessPatternString)) {
			return false;
		}

		if ((_guestAccessPattern == null) ||
			!guestAccessPatternString.equals(_guestAccessPatternString)) {

			_guestAccessPatternString = guestAccessPatternString;

			_guestAccessPattern = Pattern.compile(guestAccessPatternString);
		}

		Matcher matcher = _guestAccessPattern.matcher(fileName);

		if (!matcher.matches()) {
			return false;
		}

		if (!code.equals(generateCode(fileName))) {
			return false;
		}

		return true;
	}

	protected boolean isPrivateFixPack(String fileName) throws IOException {
		if (!fileName.startsWith(_FIX_PACK_DIR)) {
			return false;
		}

		if (!fileName.endsWith(".zip")) {
			return true;
		}

		String[] fileNameArray = StringUtil.split(fileName, CharPool.SLASH);

		if (fileNameArray.length != 5) {
			return true;
		}

		String liferayVersion = fileNameArray[2];
		String componentName = fileNameArray[3];

		StringBundler sb = new StringBundler(4);

		sb.append(_URL_PREFIX);
		sb.append(_FIX_PACK_DIR);
		sb.append(liferayVersion);
		sb.append("/public-components.txt");

		String key = HttpUtil.URLtoString(sb.toString(), true);

		String[] publicComponents = DownloadsUtil.getPublicComponents(
			_URL_PREFIX + StringPool.SLASH + key, liferayVersion);

		if (ArrayUtil.contains(publicComponents, componentName)) {
			return false;
		}

		return true;
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			String fileName = ParamUtil.getString(portletRequest, "fileName");

			if ((fileName.indexOf(StringPool.DOUBLE_PERIOD) != -1) ||
				!fileName.startsWith(StringPool.SLASH)) {

				return false;
			}

			PortletPreferences preferences = portletRequest.getPreferences();

			String code = ParamUtil.getString(portletRequest, "code");

			if (isGuestAccess(preferences, fileName, code)) {
				return true;
			}

			if (!themeDisplay.isSignedIn()) {
				return false;
			}

			if (isPrivateFixPack(fileName)) {
				return false;
			}

			if (isCustomerAccess(themeDisplay, preferences, fileName)) {
				return true;
			}

			if (isTrial(themeDisplay, preferences, fileName)) {
				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	protected boolean isTrial(
			ThemeDisplay themeDisplay, PortletPreferences preferences,
			String fileName)
		throws PortalException {

		String trialPatternString = preferences.getValue("trialPattern", null);

		if (Validator.isNull(trialPatternString)) {
			return false;
		}

		if ((_trialPattern == null) ||
			!trialPatternString.equals(_trialPatternString)) {

			_trialPatternString = trialPatternString;

			_trialPattern = Pattern.compile(trialPatternString);
		}

		Matcher matcher = _trialPattern.matcher(fileName);

		if (!matcher.matches()) {
			return false;
		}

		if (fileName.startsWith("/trial/sync/")) {
			if (AgreementUtil.hasLiferaySyncEULA(themeDisplay.getUserId())) {
				return true;
			}
		}
		else {
			if (AgreementUtil.hasTrialEULA(themeDisplay.getUserId())) {
				return true;
			}
		}

		return false;
	}

	protected boolean requiresStudioEULA(
		ActionRequest actionRequest, String fileName) {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			PortletPreferences preferences = actionRequest.getPreferences();

			String portletResource = ParamUtil.getString(
				actionRequest, "portletResource");

			if (Validator.isNotNull(portletResource)) {
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(
					actionRequest, portletResource);
			}

			String fileDirectory = preferences.getValue("fileDirectory", null);

			if (!fileName.startsWith(fileDirectory)) {
				return false;
			}

			User user = themeDisplay.getUser();

			ExpandoBridge expandoBridge = user.getExpandoBridge();

			if (expandoBridge.hasAttribute("osbCustomerESA") ||
				expandoBridge.hasAttribute("osbESA")) {

				String[] fileNameArray = fileName.split(StringPool.SLASH);

				if (fileNameArray.length >= 3) {
					int osbStudioMajorVersion = GetterUtil.getInteger(
						fileNameArray[2].substring(0, 1));

					if ((osbStudioMajorVersion >= 3) &&
						(fileNameArray[3].startsWith(
							"LiferayDeveloperStudio") ||
						 fileNameArray[3].startsWith(
							 "liferay-developer-studio"))) {

						return false;
					}
				}
			}

			String[] osbStudioEula = (String[])expandoBridge.getAttribute(
				"osbCustomerStudioEULA");

			if ((osbStudioEula == null) || (osbStudioEula.length < 4)) {
				osbStudioEula = (String[])expandoBridge.getAttribute(
					"osbStudioEULA");
			}

			if ((osbStudioEula == null) || (osbStudioEula.length < 4)) {
				return true;
			}

			double osbStudioEulaVersionAccepted = GetterUtil.getDouble(
				osbStudioEula[3]);
			double osbStudioEulaVersionRequired = GetterUtil.getDouble(
				preferences.getValue(
					"studioEulaVersionRequired_" + osbStudioEula[2], null));

			if (osbStudioEulaVersionAccepted >= osbStudioEulaVersionRequired) {
				return false;
			}
		}
		catch (Exception e) {
		}

		return true;
	}

	protected void sendAudit(ActionRequest actionRequest, String fileName) {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"layoutURL", PortalUtil.getLayoutFullURL(themeDisplay));

			AuditMessage auditMessage = new AuditMessage(
				ActionKeys.VIEW, OSBConstants.COMPANY_ID, user.getUserId(),
				user.getFullName(), DownloadsPortlet.class.getName(),
				"fileName", fileName, null, jsonObject);

			AuditRouterUtil.route(auditMessage);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final String _CODE_SALT = "L1f3R@y";

	private static final String _FIX_PACK_DIR = "/fix-packs/";

	private static final String _URL_PREFIX = "http://downloads.liferay.com";

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadsPortlet.class);

	private static Pattern _customerAccessPattern;
	private static String _customerAccessPatternString;
	private static Pattern _guestAccessPattern;
	private static String _guestAccessPatternString;
	private static Pattern _trialPattern;
	private static String _trialPatternString;

}