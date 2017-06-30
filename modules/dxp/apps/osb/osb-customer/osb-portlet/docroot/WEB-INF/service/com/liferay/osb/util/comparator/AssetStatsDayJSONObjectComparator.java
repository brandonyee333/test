/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.util.comparator;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Ryan Park
 */
public class AssetStatsDayJSONObjectComparator extends OrderByComparator {

	public AssetStatsDayJSONObjectComparator(String key) {
		this(key, true);
	}

	public AssetStatsDayJSONObjectComparator(String key, boolean asc) {
		_asc = asc;
		_key = key;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		JSONObject jsonObject1 = (JSONObject)obj1;
		JSONObject jsonObject2 = (JSONObject)obj2;

		double value =
			jsonObject1.getDouble(_key) - jsonObject2.getDouble(_key);

		if (!_asc) {
			value = value * -1;
		}

		if (value > 0) {
			return 1;
		}
		else if (value < 0) {
			return -1;
		}

		return 0;
	}

	@Override
	public boolean isAscending() {
		return _asc;
	}

	private boolean _asc;
	private String _key;

}