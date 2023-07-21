/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.publisher.web.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Eudaldo Alonso
 */
public class AssetPublisherWebConfigurationValues {

	public static final int CHECK_INTERVAL = GetterUtil.getInteger(
		AssetPublisherWebConfigurationUtil.get("check.interval"));

	public static final String DISPLAY_STYLE_DEFAULT = GetterUtil.getString(
		AssetPublisherWebConfigurationUtil.get("display.style.default"));

	public static String[] DISPLAY_STYLES =
		AssetPublisherWebConfigurationUtil.getArray("display.styles");

	public static final String DISPLAY_TEMPLATES_CONFIG = GetterUtil.getString(
		AssetPublisherWebConfigurationUtil.get("display.templates.config"));

	public static final boolean DYNAMIC_EXPORT_ENABLED = GetterUtil.getBoolean(
		AssetPublisherWebConfigurationUtil.get("dynamic.export.enabled"));

	public static final int DYNAMIC_EXPORT_LIMIT = GetterUtil.getInteger(
		AssetPublisherWebConfigurationUtil.get("dynamic.export.limit"));

	public static final int DYNAMIC_SUBSCRIPTION_LIMIT = GetterUtil.getInteger(
		AssetPublisherWebConfigurationUtil.get("dynamic.subscription.limit"));

	public static final String EMAIL_ASSET_ENTRY_ADDED_BODY =
		GetterUtil.getString(
			AssetPublisherWebConfigurationUtil.get(
				"email.asset.entry.added.body"));

	public static final boolean EMAIL_ASSET_ENTRY_ADDED_ENABLED =
		GetterUtil.getBoolean(
			AssetPublisherWebConfigurationUtil.get(
				"email.asset.entry.added.enabled"));

	public static final String EMAIL_ASSET_ENTRY_ADDED_SUBJECT =
		GetterUtil.getString(
			AssetPublisherWebConfigurationUtil.get(
				"email.asset.entry.added.subject"));

	public static final String EMAIL_FROM_ADDRESS = GetterUtil.getString(
		AssetPublisherWebConfigurationUtil.get("email.from.address"));

	public static final String EMAIL_FROM_NAME = GetterUtil.getString(
		AssetPublisherWebConfigurationUtil.get("email.from.name"));

	public static final boolean MANUAL_EXPORT_ENABLED = GetterUtil.getBoolean(
		AssetPublisherWebConfigurationUtil.get("manual.export.enabled"));

	public static final boolean PERMISSION_CHECKING_CONFIGURABLE =
		GetterUtil.getBoolean(
			AssetPublisherWebConfigurationUtil.get(
				"permission.checking.configurable"));

	public static String[] QUERY_FORM_CONFIGURATION =
		AssetPublisherWebConfigurationUtil.getArray("query.form.configuration");

	public static final boolean SEARCH_WITH_INDEX = GetterUtil.getBoolean(
		AssetPublisherWebConfigurationUtil.get("search.with.index"));

}