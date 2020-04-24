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

package com.liferay.osb.loop.web.internal.portlet.route;

import com.liferay.alloy.mvc.AlloyFriendlyURLMapper;
import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.osb.loop.web.internal.util.LoopUtil;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Timothy Bell
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml?controller=home",
		"javax.portlet.name=" + LoopPortletKeys.LOOP,
		"name=osb-loop-friendly-url-mapper"
	},
	service = FriendlyURLMapper.class
)
public class LoopFriendlyURLMapper extends AlloyFriendlyURLMapper {

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	@Override
	protected void buildRouteParameters(
		LiferayPortletURL liferayPortletURL,
		Map<String, String> routeParameters) {

		super.buildRouteParameters(liferayPortletURL, routeParameters);

		populateIdParameter(routeParameters, true);
	}

	protected void populateIdParameter(
		Map<String, String> routeParameters, boolean escape) {

		if (!routeParameters.containsKey(_ID_PARAMETER)) {
			return;
		}

		String value = routeParameters.get(_ID_PARAMETER);

		if (escape) {
			value = LoopUtil.escapeName(value);
		}
		else {
			value = LoopUtil.unescapeName(value);
		}

		routeParameters.put(_ID_PARAMETER, value);
	}

	@Override
	protected void populateParams(
		Map<String, String[]> parameterMap, String namespace,
		Map<String, String> routeParameters) {

		populateIdParameter(routeParameters, false);

		super.populateParams(parameterMap, namespace, routeParameters);
	}

	private static final String _ID_PARAMETER = "id";

	private static final String _MAPPING = "loop";

}