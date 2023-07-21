/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.taglib.util.IncludeTag;
import com.liferay.taglib.util.PortalIncludeUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 * @author Wilson S. Man
 */
public class InputPermissionsTag extends IncludeTag {

	public static String doTag(
			String formName, String modelName, PageContext pageContext)
		throws Exception {

		return doTag(_PAGE, formName, modelName, pageContext);
	}

	public static String doTag(
			String page, String formName, String modelName,
			PageContext pageContext)
		throws Exception {

		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		request.setAttribute("liferay-ui:input-permissions:formName", formName);

		if (modelName != null) {
			List<String> supportedActions =
				ResourceActionsUtil.getModelResourceActions(modelName);
			List<String> groupDefaultActions =
				ResourceActionsUtil.getModelResourceGroupDefaultActions(
					modelName);
			List<String> guestDefaultActions =
				ResourceActionsUtil.getModelResourceGuestDefaultActions(
					modelName);
			List<String> guestUnsupportedActions =
				ResourceActionsUtil.getModelResourceGuestUnsupportedActions(
					modelName);

			request.setAttribute(
				"liferay-ui:input-permissions:groupDefaultActions",
				groupDefaultActions);
			request.setAttribute(
				"liferay-ui:input-permissions:guestDefaultActions",
				guestDefaultActions);
			request.setAttribute(
				"liferay-ui:input-permissions:guestUnsupportedActions",
				guestUnsupportedActions);
			request.setAttribute(
				"liferay-ui:input-permissions:modelName", modelName);
			request.setAttribute(
				"liferay-ui:input-permissions:supportedActions",
				supportedActions);
		}

		PortalIncludeUtil.include(pageContext, page);

		return StringPool.BLANK;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(getPage(), _formName, _modelName, pageContext);

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setModelName(String modelName) {
		_modelName = modelName;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	private static final String _PAGE =
		"/html/taglib/ui/input_permissions/page.jsp";

	private String _formName = "fm";
	private String _modelName;

}