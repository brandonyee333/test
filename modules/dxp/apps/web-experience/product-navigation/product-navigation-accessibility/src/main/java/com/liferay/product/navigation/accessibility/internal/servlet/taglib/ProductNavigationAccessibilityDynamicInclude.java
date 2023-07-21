/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.product.navigation.accessibility.internal.servlet.taglib;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.accessibility.internal.configuration.ProductNavigationAccessibilityConfiguration;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Chema Balsas
 */
@Component(
	configurationPid = "com.liferay.product.navigation.accessibility.internal.configuration.ProductNavigationAccessibilityConfiguration",
	immediate = true, service = DynamicInclude.class
)
public class ProductNavigationAccessibilityDynamicInclude
	extends BaseDynamicInclude {

	@Activate
	@Modified
	public void activate(Map<String, Object> properties) {
		productNavigationAccessibilityConfiguration =
			ConfigurableUtil.createConfigurable(
				ProductNavigationAccessibilityConfiguration.class, properties);

		_postfix = _portal.getPathProxy();

		if (_postfix.isEmpty()) {
			_postfix = _servletContext.getContextPath();
		}
		else {
			_postfix = _postfix.concat(_servletContext.getContextPath());
		}
	}

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		if (productNavigationAccessibilityConfiguration.useHighContrast()) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			PrintWriter printWriter = response.getWriter();

			String cdnBaseURL = themeDisplay.getCDNBaseURL();

			String staticResourceURL = _portal.getStaticResourceURL(
				request,
				cdnBaseURL.concat(
					_postfix
				).concat(
					"/css/main.css"
				));

			String content = "<link href=\"".concat(staticResourceURL);

			printWriter.println(
				content.concat("\" rel=\"stylesheet\" type = \"text/css\" />"));
		}
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register(
			"/html/common/themes/top_head.jsp#post");
	}

	protected volatile ProductNavigationAccessibilityConfiguration
		productNavigationAccessibilityConfiguration;

	@Reference
	private Portal _portal;

	private String _postfix;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.product.navigation.accessibility)"
	)
	private ServletContext _servletContext;

}