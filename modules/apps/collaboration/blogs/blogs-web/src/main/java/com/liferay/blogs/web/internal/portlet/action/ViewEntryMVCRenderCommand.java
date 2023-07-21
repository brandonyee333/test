/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.portlet.action;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.kernel.exception.NoSuchEntryException;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.util.PropsValues;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
		"javax.portlet.name=" + BlogsPortletKeys.BLOGS_AGGREGATOR,
		"mvc.command.name=/blogs/view_entry"
	},
	service = MVCRenderCommand.class
)
public class ViewEntryMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		long assetCategoryId = ParamUtil.getLong(renderRequest, "categoryId");
		String assetCategoryName = ParamUtil.getString(renderRequest, "tag");

		if ((assetCategoryId > 0) || Validator.isNotNull(assetCategoryName)) {
			return "/blogs/view.jsp";
		}

		try {
			ActionUtil.getEntry(renderRequest);

			if (PropsValues.BLOGS_PINGBACK_ENABLED) {
				BlogsEntry entry = (BlogsEntry)renderRequest.getAttribute(
					WebKeys.BLOGS_ENTRY);

				if ((entry != null) && entry.isAllowPingbacks()) {
					HttpServletResponse response =
						_portal.getHttpServletResponse(renderResponse);

					response.addHeader(
						"X-Pingback",
						_portal.getPortalURL(renderRequest) +
							"/xmlrpc/pingback");
				}
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchEntryException ||
				e instanceof PrincipalException) {

				SessionErrors.add(renderRequest, e.getClass());

				return "/blogs/error.jsp";
			}

			throw new PortletException(e);
		}

		return "/blogs/view_entry.jsp";
	}

	@Reference
	private Portal _portal;

}