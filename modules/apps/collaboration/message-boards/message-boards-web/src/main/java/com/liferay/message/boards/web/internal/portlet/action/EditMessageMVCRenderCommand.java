/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.portlet.action;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.web.constants.MBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portlet.messageboards.service.permission.MBMessagePermission;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(
	property = {
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS,
		"javax.portlet.name=" + MBPortletKeys.MESSAGE_BOARDS_ADMIN,
		"mvc.command.name=/message_boards/edit_message"
	},
	service = MVCRenderCommand.class
)
public class EditMessageMVCRenderCommand extends GetMessageMVCRenderCommand {

	@Override
	protected void checkPermissions(
			PermissionChecker permissionChecker, MBMessage message)
		throws PortalException {

		MBMessagePermission.check(
			permissionChecker, message, ActionKeys.UPDATE);
	}

	@Override
	protected String getPath() {
		return "/message_boards/edit_message.jsp";
	}

}