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

package com.liferay.osb.customer.web.internal.portlet.action;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.osb.customer.web.internal.constants.OSBCustomerConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderConstants;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"mvc.command.name=/journal/select_user"
	},
	service = MVCRenderCommand.class
)
public class OSBJournalSelectUserMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		renderRequest.setAttribute(
			"OSB_ROLE_LIFERAY_EMPLOYEE_ID",
			OSBCustomerConstants.ROLE_LIFERAY_EMPLOYEE_ID);

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher("/select_user.jsp");

		try {
			HttpServletRequest httpServletRequest =
				PortalUtil.getHttpServletRequest(renderRequest);
			HttpServletResponse httpServletResponse =
				PortalUtil.getHttpServletResponse(renderResponse);

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception e) {
			throw new PortletException("Unable to include select_user.jsp", e);
		}

		return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
	}

	@Reference(target = "(osgi.web.symbolicname=com.liferay.osb.customer.web)")
	protected ServletContext servletContext;

}