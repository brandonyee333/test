/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.portlet.action;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.kernel.exception.NoSuchEntryException;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.web.constants.BlogsWebKeys;
import com.liferay.blogs.web.internal.BlogsItemSelectorHelper;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.blogs.service.permission.BlogsEntryPermission;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_AGGREGATOR,
		"mvc.command.name=/blogs/edit_entry"
	},
	service = MVCRenderCommand.class
)
public class EditEntryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		try {
			ActionUtil.getEntry(renderRequest);

			BlogsEntry entry = (BlogsEntry)renderRequest.getAttribute(
				WebKeys.BLOGS_ENTRY);

			if (entry != null) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)renderRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				BlogsEntryPermission.check(
					themeDisplay.getPermissionChecker(), entry,
					ActionKeys.UPDATE);
			}

			renderRequest.setAttribute(
				BlogsWebKeys.BLOGS_ITEM_SELECTOR_HELPER,
				_blogsItemSelectorHelper);
		}
		catch (Exception e) {
			if (e instanceof NoSuchEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return "/blogs/error.jsp";
			}

			throw new PortletException(e);
		}

		return "/blogs/edit_entry.jsp";
	}

	@Reference(unbind = "-")
	public void setItemSelectorHelper(
		BlogsItemSelectorHelper blogsItemSelectorHelper) {

		_blogsItemSelectorHelper = blogsItemSelectorHelper;
	}

	private BlogsItemSelectorHelper _blogsItemSelectorHelper;

}