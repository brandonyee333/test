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

package com.liferay.pulpo.connector.de.assets.properties.provider.internal;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.pulpo.connector.de.assets.properties.provider.AssetConnectorPropertiesProvider;
import com.liferay.pulpo.connector.de.assets.properties.provider.BaseAssetConnectorPropertiesProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pavel Savinov
 */
@Component(service = AssetConnectorPropertiesProvider.class)
public class BlogsEntryAssetConnectorPropertiesProvider
	extends BaseAssetConnectorPropertiesProvider
	implements AssetConnectorPropertiesProvider {

	@Override
	public String getClassName() {
		return BlogsEntry.class.getName();
	}

	@Override
	public JSONArray getProperties(AssetEntry assetEntry) {
		JSONArray propertiesJSONArray = JSONFactoryUtil.createJSONArray();

		AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

		if (assetRenderer == null) {
			return propertiesJSONArray;
		}

		BlogsEntry blogsEntry = (BlogsEntry)assetRenderer.getAssetObject();

		addProperty(
			propertiesJSONArray, "content",
			HtmlUtil.extractText(blogsEntry.getContent()));
		addProperty(
			propertiesJSONArray, "coverImageCaption",
			blogsEntry.getCoverImageCaption());
		addProperty(
			propertiesJSONArray, "subtitle",
			HtmlUtil.extractText(blogsEntry.getSubtitle()));

		addExpandoValues(propertiesJSONArray, assetEntry);

		return propertiesJSONArray;
	}

}