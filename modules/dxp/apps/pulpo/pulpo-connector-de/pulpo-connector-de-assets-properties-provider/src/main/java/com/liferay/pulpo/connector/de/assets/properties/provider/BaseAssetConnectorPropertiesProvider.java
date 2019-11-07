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

package com.liferay.pulpo.connector.de.assets.properties.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Pavel Savinov
 */
public abstract class BaseAssetConnectorPropertiesProvider
	implements AssetConnectorPropertiesProvider {

	protected void addExpandoValues(
		JSONArray propertiesJSONArray, AssetEntry assetEntry) {

		ExpandoBridge expandoBridge = assetEntry.getExpandoBridge();

		Map<String, Serializable> attributes = expandoBridge.getAttributes(
			false);

		for (Map.Entry<String, Serializable> entry : attributes.entrySet()) {
			addProperty(propertiesJSONArray, entry.getKey(), entry.getValue());
		}
	}

	protected void addProperty(
		JSONArray jsonArray, String name, Serializable value) {

		JSONObject propertyJSONObject = JSONFactoryUtil.createJSONObject();

		propertyJSONObject.put("name", name);
		propertyJSONObject.put("value", value);

		jsonArray.put(propertyJSONObject);
	}

}