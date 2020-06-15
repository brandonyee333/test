/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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