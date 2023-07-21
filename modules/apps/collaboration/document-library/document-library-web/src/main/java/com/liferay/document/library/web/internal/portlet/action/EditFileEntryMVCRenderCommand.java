/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.portlet.action;

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portlet.documentlibrary.service.permission.DLFileEntryPermission;

import org.osgi.service.component.annotations.Component;

/**
 * @author Iván Zaera
 */
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
		"mvc.command.name=/document_library/edit_file_entry"
	},
	service = MVCRenderCommand.class
)
public class EditFileEntryMVCRenderCommand
	extends GetFileEntryMVCRenderCommand {

	@Override
	protected void checkPermissions(
			PermissionChecker permissionChecker, FileEntry fileEntry)
		throws PortalException {

		DLFileEntryPermission.check(
			permissionChecker, fileEntry, ActionKeys.UPDATE);
	}

	@Override
	protected String getPath() {
		return "/document_library/edit_file_entry.jsp";
	}

}