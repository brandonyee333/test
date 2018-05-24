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

package com.liferay.watson.web.internal.portlet.route;

import com.liferay.alloy.mvc.AlloyFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.watson.web.constants.WatsonPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * @author Steven Smith
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml?controller=incidents",
		"javax.portlet.name=" + WatsonPortletKeys.WATSON,
		"name=watson-friendly-url-mapper"
	},
	service = FriendlyURLMapper.class
)
public class WatsonFriendlyURLMapper extends AlloyFriendlyURLMapper {

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	private static final String _MAPPING = "watson";

}