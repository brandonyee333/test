/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.google.docs.internal.display.context;

import com.liferay.document.library.display.context.DLFilePicker;
import com.liferay.document.library.google.docs.internal.util.FreeMarkerRenderer;
import com.liferay.document.library.google.docs.internal.util.GoogleDocsConfigurationHelper;
import com.liferay.document.library.google.docs.internal.util.GoogleDocsConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public class GoogleDocsDLFilePicker implements DLFilePicker {

	public GoogleDocsDLFilePicker(
		String namespace, String onFilePickCallback,
		ThemeDisplay themeDisplay) {

		_namespace = namespace;
		_onFilePickCallback = onFilePickCallback;

		_googleDocsConfigurationHelper = new GoogleDocsConfigurationHelper(
			themeDisplay.getCompanyId());
	}

	@Override
	public String getDescriptionFieldName() {
		return GoogleDocsConstants.DDM_FIELD_NAME_DESCRIPTION;
	}

	@Override
	public String getIconFieldName() {
		return GoogleDocsConstants.DDM_FIELD_NAME_ICON_URL;
	}

	@Override
	public String getJavaScript() throws PortalException {
		try {
			FreeMarkerRenderer freeMarkerRenderer = new FreeMarkerRenderer(
				"com/liferay/document/library/google/docs/internal/display" +
					"/context/dependencies/google_file_picker.ftl");

			freeMarkerRenderer.setAttribute(
				"googleAppsAPIKey",
				_googleDocsConfigurationHelper.getGoogleAppsAPIKey());
			freeMarkerRenderer.setAttribute(
				"googleClientId",
				_googleDocsConfigurationHelper.getGoogleClientId());
			freeMarkerRenderer.setAttribute("namespace", _namespace);
			freeMarkerRenderer.setAttribute(
				"onFilePickCallback", _onFilePickCallback);

			return freeMarkerRenderer.render();
		}
		catch (IOException ioe) {
			throw new PortalException(ioe);
		}
		catch (TemplateException te) {
			throw new PortalException(te);
		}
	}

	@Override
	public String getJavaScriptModuleName() {
		return "FilePicker";
	}

	@Override
	public String getOnClickCallback() {
		return "openPicker";
	}

	@Override
	public String getTitleFieldName() {
		return GoogleDocsConstants.DDM_FIELD_NAME_NAME;
	}

	private final GoogleDocsConfigurationHelper _googleDocsConfigurationHelper;
	private final String _namespace;
	private final String _onFilePickCallback;

}