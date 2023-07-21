/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.file.version.discussion.web.internal.display;

import com.liferay.document.library.display.context.BaseDLDisplayContextFactory;
import com.liferay.document.library.display.context.DLDisplayContextFactory;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = DLDisplayContextFactory.class)
public class FileVersionDiscussionDLViewFileVersionDisplayContextFactory
	extends BaseDLDisplayContextFactory {

	@Override
	public DLViewFileVersionDisplayContext getDLViewFileVersionDisplayContext(
		DLViewFileVersionDisplayContext parentDLViewFileVersionDisplayContext,
		HttpServletRequest request, HttpServletResponse response,
		FileVersion fileVersion) {

		return new FileVersionDiscussionDLViewFileVersionDisplayContext(
			parentDLViewFileVersionDisplayContext, request, response,
			fileVersion,
			ResourceBundleLoaderUtil.
				getResourceBundleLoaderByBundleSymbolicName(
					"com.liferay.document.library.file.version.discussion." +
						"web"));
	}

}