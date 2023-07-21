/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.web.internal.portlet.action;

import com.liferay.osb.customer.license.web.internal.constants.CustomerLicensePortletKeys;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + CustomerLicensePortletKeys.LICENSE,
		"mvc.command.name=/license/select_user"
	},
	service = MVCRenderCommand.class
)
public class SelectUserMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		String userParam = ParamUtil.getString(renderRequest, "userParam");

		String userSQL = null;

		if (userParam.equals("licenseKeyCreateUsers")) {
			userSQL = CustomSQLUtil.get(
				getClass(),
				"com.liferay.portal.kernel.service.persistence.UserFinder." +
					"joinByLicenseKeyCreateUser");
		}
		else {
			userSQL = CustomSQLUtil.get(
				getClass(),
				"com.liferay.portal.kernel.service.persistence.UserFinder." +
					"joinByLicenseKeyModifiedUser");
		}

		renderRequest.setAttribute("userSQL", userSQL);

		return "/license/select_user.jsp";
	}

}