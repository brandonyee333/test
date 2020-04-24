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

package com.liferay.osb.testray.web.internal.portlet.route;

import com.liferay.alloy.mvc.AlloyFriendlyURLMapper;
import com.liferay.osb.testray.web.constants.TestrayPortletKeys;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml?controller=projects",
		"javax.portlet.name=" + TestrayPortletKeys.TESTRAY,
		"name=osb-testray-friendly-url-mapper"
	},
	service = FriendlyURLMapper.class
)
public class TestrayFriendlyURLMapper extends AlloyFriendlyURLMapper {

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	private static final String _MAPPING = "testray";

}