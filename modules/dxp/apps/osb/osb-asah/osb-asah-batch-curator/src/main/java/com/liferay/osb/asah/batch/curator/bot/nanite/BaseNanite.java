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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

import javax.annotation.PostConstruct;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 */
public abstract class BaseNanite implements Nanite {

	@PostConstruct
	public void init() {
		faroInfoElasticsearchInvoker =
			elasticsearchInvokerFactory.forFaroInfo();
	}

	@Override
	public boolean isLogRunEnabled() {
		return false;
	}

	protected JSONObject getOSBAsahMarkerJSONObject() {
		Class<?> clazz = getClass();

		JSONObject osbAsahMarkerJSONObject = faroInfoElasticsearchInvoker.fetch(
			"OSBAsahMarkers", clazz.getSimpleName());

		if (osbAsahMarkerJSONObject == null) {
			osbAsahMarkerJSONObject = new JSONObject();

			osbAsahMarkerJSONObject.put("id", clazz.getSimpleName());
			osbAsahMarkerJSONObject.put("type", "nanite");

			faroInfoElasticsearchInvoker.add(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);
		}

		return osbAsahMarkerJSONObject;
	}

	protected void monitorProcessedCount(int count) {
		Class<?> clazz = getClass();

		Counter.Child child = _processedCounter.labels(clazz.getSimpleName());

		child.inc(count);
	}

	protected void monitorQueueSize(int size) {
		Class<?> clazz = getClass();

		Gauge.Child child = _queueSizeGauge.labels(clazz.getSimpleName());

		child.set(size);
	}

	@Autowired
	protected ElasticsearchInvokerFactory elasticsearchInvokerFactory;

	protected ElasticsearchInvoker faroInfoElasticsearchInvoker;

	private static final Counter _processedCounter = PrometheusUtil.counter(
		"faro_curator_processed_count", "The number of objects curated",
		"nanite");
	private static final Gauge _queueSizeGauge = PrometheusUtil.gauge(
		"faro_curator_queue_size", "The number of objects queued to be curated",
		"nanite");

}