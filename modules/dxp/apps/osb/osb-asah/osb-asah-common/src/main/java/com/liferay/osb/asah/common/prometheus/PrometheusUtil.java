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

package com.liferay.osb.asah.common.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.Histogram;

/**
 * @author Marcellus Tavares
 */
public class PrometheusUtil {

	public static Counter counter(
		String name, String help, String... labelNames) {

		Counter.Builder builder = Counter.build();

		builder.help(help);
		builder.labelNames(labelNames);
		builder.name(name);

		return builder.register();
	}

	public static Gauge gauge(String name, String help, String... labelNames) {
		Gauge.Builder builder = Gauge.build();

		builder.help(help);
		builder.labelNames(labelNames);
		builder.name(name);

		return builder.register();
	}

	public static Histogram histogram(
		String name, String help, String... labelNames) {

		Histogram.Builder builder = Histogram.build();

		builder.help(help);
		builder.labelNames(labelNames);
		builder.name(name);

		return builder.register();
	}

}