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

package com.liferay.osb.community.generator.site;

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Map;

/**
 * @author Ryan Park
 */
public abstract class BaseSiteGenerator implements SiteGenerator {

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public String getName() {
		return _name;
	}

	protected void activate(Map<String, Object> config) {
		_key = GetterUtil.getString(
			config.get("osb.community.site.generator.key"));
		_name = GetterUtil.getString(
			config.get("osb.community.site.generator.name"));
	}

	private String _key;
	private String _name;

}