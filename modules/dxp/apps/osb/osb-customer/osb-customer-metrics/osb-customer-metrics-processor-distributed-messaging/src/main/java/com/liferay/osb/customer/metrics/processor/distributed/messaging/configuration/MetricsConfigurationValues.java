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