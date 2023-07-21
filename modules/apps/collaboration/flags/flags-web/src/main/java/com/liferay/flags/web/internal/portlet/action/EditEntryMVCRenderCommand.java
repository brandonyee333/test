/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.flags.web.internal.portlet.action;

import com.liferay.flags.web.internal.constants.FlagsPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Julio Camarero
 * @author Peter Fellwock
 */
@Component(
	property = {
		"javax.portlet.name=" + FlagsPortletKeys.FLAGS,
		"mvc.command.name=/flags/edit_entry"
	},
	service = MVCRenderCommand.class
)
public class EditEntryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		return "/edit_entry.jsp";
	}

}