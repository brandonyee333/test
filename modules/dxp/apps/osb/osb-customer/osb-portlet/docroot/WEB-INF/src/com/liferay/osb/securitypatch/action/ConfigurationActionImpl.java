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

package com.liferay.osb.securitypatch.action;

import com.liferay.osb.service.SecurityPatchLocalServiceUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadServletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.ValidatorException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alan Zhang
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/security_patch/configuration.jsp";
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		PortletPreferences preferences = actionRequest.getPreferences();

		try {
			updateSecurityPatch(actionRequest, actionResponse, preferences);
		}
		catch (ValidatorException ve) {
			SessionErrors.add(
				actionRequest, ValidatorException.class.getName(), ve);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			LiferayPortletConfig liferayPortletConfig =
				(LiferayPortletConfig)portletConfig;

			SessionMessages.add(
				actionRequest,
				liferayPortletConfig.getPortletId() +
					SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
				portletResource);

			SessionMessages.add(
				actionRequest,
				liferayPortletConfig.getPortletId() +
					SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
		}
	}

	protected void parseCSV(long userId, String portletId, File file)
		throws Exception {

		SecurityPatchLocalServiceUtil.deleteSecurityPatches(portletId);

		FileInputStream fileInputStream = new FileInputStream(file);

		UnsyncBufferedReader bufferedReader = new UnsyncBufferedReader(
			new InputStreamReader(fileInputStream));

		/* Needs refactor
		 *
		 * try {
			String line = null;

			while (Validator.isNotNull(line = bufferedReader.readLine())) {
				String[] values = StringUtil.split(line);

				if (values.length == 3) {
					long ticketAttachmentId = GetterUtil.getLong(values[0]);
					int envLFR = GetterUtil.getInteger(values[1]);
					String fileName = GetterUtil.getString(values[2]);

					SecurityPatchLocalServiceUtil.addSecurityPatch(
						userId, portletId, envLFR, fileName);
				}
				else if (values.length == 4) {
					long accountEntryId = GetterUtil.getLong(values[0]);
					String name = GetterUtil.getString(values[1]);
					int envLFR = GetterUtil.getInteger(values[2]);
					String fileName = GetterUtil.getString(values[3]);

					SecurityPatchLocalServiceUtil.addSecurityPatch(
						userId, accountEntryId, 0, portletId, envLFR, name,
						fileName);
				}
			}
		}
		catch (NoSuchListTypeException nslte) {
			Class<?> clazz = nslte.getClass();

			throw new ValidatorException(clazz.getName(), null);
		}
		finally {
			bufferedReader.close();

			StreamUtil.cleanUp(fileInputStream);
		}*/
	}

	protected void updateSecurityPatch(
			ActionRequest actionRequest, ActionResponse actionResponse,
			PortletPreferences preferences)
		throws Exception {

		UploadServletRequest uploadServletRequest =
			PortalUtil.getUploadServletRequest(
				PortalUtil.getHttpServletRequest(actionRequest));

		try {
			String portletResource = ParamUtil.getString(
				actionRequest, "portletResource");

			String name = ParamUtil.getString(actionRequest, "name");
			String releaseNotesURL = ParamUtil.getString(
				actionRequest, "releaseNotesURL");
			String footerText = ParamUtil.getString(
				actionRequest, "footerText");

			String fileParam = actionResponse.getNamespace() + "file";

			String fileName = uploadServletRequest.getFileName(fileParam);
			File file = uploadServletRequest.getFile(fileParam);

			validate(name, fileName, file);

			preferences.setValue("name", name);
			preferences.setValue("releaseNotesURL", releaseNotesURL);
			preferences.setValue("footerText", footerText);

			if (Validator.isNotNull(fileName)) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)actionRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				parseCSV(themeDisplay.getUserId(), portletResource, file);

				preferences.setValue("fileName", fileName);
			}

			preferences.store();
		}
		finally {
			uploadServletRequest.cleanUp();
		}
	}

	protected void validate(String name, String fileName, File file)
		throws ValidatorException {

		if (Validator.isNull(name)) {
			throw new ValidatorException("please-enter-a-valid-name", null);
		}

		if ((file != null) && Validator.isNotNull(fileName)) {
			String extension = FileUtil.getExtension(fileName);

			if (!extension.equals("csv")) {
				throw new ValidatorException("please-enter-a-valid-file", null);
			}
		}
	}

}