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

package com.liferay.product.navigation.control.menu.dxp.theme.contributor.internal;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Julio Camarero
 */
@Component(
	immediate = true,
	property = "type=" + TemplateContextContributor.TYPE_THEME,
	service = TemplateContextContributor.class
)
public class ProductNavigationControlMenuTemplateContextContributor
	implements TemplateContextContributor {

	@Override
	public void prepare(
		Map<String, Object> contextObjects, HttpServletRequest request) {

		if (!isShowControlMenu(request)) {
			return;
		}

		String cssClass = GetterUtil.getString(
			contextObjects.get("bodyCssClass"));

		contextObjects.put("bodyCssClass", cssClass + " has-control-menu");
	}

	protected boolean isShowControlMenu(HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (themeDisplay.isImpersonated()) {
			return true;
		}

		if (!themeDisplay.isSignedIn()) {
			return false;
		}

		User user = themeDisplay.getUser();

		if (!user.isSetupComplete()) {
			return false;
		}

		return true;
	}

}