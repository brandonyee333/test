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
				request, cdnBaseURL.concat(_postfix).concat("/css/main.css"));

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