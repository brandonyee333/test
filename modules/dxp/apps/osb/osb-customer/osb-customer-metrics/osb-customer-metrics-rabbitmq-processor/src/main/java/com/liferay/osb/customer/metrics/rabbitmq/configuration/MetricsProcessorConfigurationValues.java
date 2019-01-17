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

package com.liferay.osb.customer.metrics.rabbitmq.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class MetricsProcessorConfigurationValues {

	public static final String[] CLASSNAME_MAPPINGS =
		MetricsProcessorConfigurationUtil.getArray("classname.mappings");

	public static final String DATABASE_SCHEMA_NAME = GetterUtil.getString(
		MetricsProcessorConfigurationUtil.get("database.schema.name"));

	public static final String ERROR_EMAIL = GetterUtil.getString(
		MetricsProcessorConfigurationUtil.get("error.email"));

	public static final long ERROR_NOTIFY_ATTEMPT_THRESHOLD =
		GetterUtil.getInteger(
			MetricsProcessorConfigurationUtil.get(
				"error.notify.attempt.threshold"));

	public static final long ERROR_SLEEP_MILLIS = GetterUtil.getLong(
		MetricsProcessorConfigurationUtil.get("error.sleep.millis"));

	public static final String METRICS_RABBITMQ_EXCHANGE_NAME =
		GetterUtil.getString(
			MetricsProcessorConfigurationUtil.get(
				"metrics.rabbitmq.exchange.name"));

}