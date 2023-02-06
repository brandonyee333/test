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

package com.liferay.osb.asah.common.postgresql.converter.helper;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rachael Koestartyo
 */
public class AssetFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public Map<String, String> getFieldNameConversionMap() {
		Map<String, String> map = new HashMap<>();

		map.put("applicationId", "assetType");
		map.put("eventId", "assetId");
		map.put("views", "count");

		return map;
	}

}