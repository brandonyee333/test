/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.file.version.discussion.web.internal.display;

import com.liferay.document.library.display.context.BaseDLViewFileVersionDisplayContext;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Adolfo Pérez
 */
public class FileVersionDiscussionDLViewFileVersionDisplayContext
	extends BaseDLViewFileVersionDisplayContext {

	public FileVersionDiscussionDLViewFileVersionDisplayContext(
		DLViewFileVersionDisplayContext dlViewFileVersionDisplayContext,
		HttpServletRequest request, HttpServletResponse response,
		FileVersion fileVersion, ResourceBundleLoader resourceBundleLoader) {

		super(
			_UUID, dlViewFileVersionDisplayContext, request, response,
			fileVersion);

		_resourceBundleLoader = resourceBundleLoader;
	}

	@Override
	public String getDiscussionClassName() {
		return DLFileVersion.class.getName();
	}

	@Override
	public long getDiscussionClassPK() {
		return fileVersion.getFileVersionId();
	}

	@Override
	public String getDiscussionLabel(Locale locale) {
		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale);

		String label = "comments-for-version-x";

		String version = fileVersion.getVersion();

		if (version.equals(DLFileEntryConstants.PRIVATE_WORKING_COPY_VERSION)) {
			label = "comments-for-draft";
		}

		return LanguageUtil.format(
			resourceBundle, label, fileVersion.getVersion());
	}

	private static final UUID _UUID = UUID.fromString(
		"3D93D2E1-4C75-45FC-804C-4BE7E63561C8");

	private final ResourceBundleLoader _resourceBundleLoader;

}