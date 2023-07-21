/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.processor.distributed.messaging.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class MetricsConfigurationValues {

	public static final String[] CLASSNAME_MAPPINGS =
		MetricsConfigurationUtil.getArray("classname.mappings");

	public static final String[] DATABASE_SCHEMA_EMOJI_SUPPORTED_NAMES =
		MetricsConfigurationUtil.getArray(
			"database.schema.emoji.supported.names");

	public static final String DATABASE_SCHEMA_NAME = GetterUtil.getString(
		MetricsConfigurationUtil.get("database.schema.name"));

	public static final String ERROR_EMAIL = GetterUtil.getString(
		MetricsConfigurationUtil.get("error.email"));

	public static final long ERROR_NOTIFY_ATTEMPT_THRESHOLD =
		GetterUtil.getInteger(
			MetricsConfigurationUtil.get("error.notify.attempt.threshold"));

	public static final long ERROR_SLEEP_MILLIS = GetterUtil.getLong(
		MetricsConfigurationUtil.get("error.sleep.millis"));

}