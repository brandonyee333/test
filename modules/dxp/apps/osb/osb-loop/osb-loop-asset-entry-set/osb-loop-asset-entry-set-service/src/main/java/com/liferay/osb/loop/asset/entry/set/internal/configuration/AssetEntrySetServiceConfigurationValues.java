/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.internal.configuration;

/**
 * @author Timothy Bell
 */
public class AssetEntrySetServiceConfigurationValues {

	public static final String[] ASSET_ENTRY_SET_IMAGE_EXTENSIONS =
		AssetEntrySetServiceConfigurationUtil.getArray(
			AssetEntrySetServiceConfigurationKeys.
				ASSET_ENTRY_SET_IMAGE_EXTENSIONS);

	public static final String[] ASSET_ENTRY_SET_IMAGE_TYPES =
		AssetEntrySetServiceConfigurationUtil.getArray(
			AssetEntrySetServiceConfigurationKeys.ASSET_ENTRY_SET_IMAGE_TYPES);

	public static final String[] ASSET_ENTRY_SET_SHARED_TO_JSON_OBJECT_KEYS =
		AssetEntrySetServiceConfigurationUtil.getArray(
			AssetEntrySetServiceConfigurationKeys.
				ASSET_ENTRY_SET_SHARED_TO_JSON_OBJECT_KEYS);

	public static final String GEONAMES_URL =
		AssetEntrySetServiceConfigurationUtil.get(
			AssetEntrySetServiceConfigurationKeys.GEONAMES_URL);

}