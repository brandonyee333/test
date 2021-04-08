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
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.dog.RunLogger;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 */
public abstract class BaseNanite implements Nanite {

	@Override
	public boolean isLogRunEnabled() {
		return false;
	}

	@Override
	public void logCompleted(
		String asahTaskId, JSONObject contextJSONObject, long duration) {

		if (isLogRunEnabled()) {
			if (!StringUtil.isNull(asahTaskId)) {
				_runLogger.log(
					null, this, false, "COMPLETED",
					faroInfoElasticsearchInvoker, "OSBAsahTaskId", asahTaskId);
			}
			else {
				_runLogger.log(
					null, this, true, "COMPLETED",
					faroInfoElasticsearchInvoker);
			}
		}
		else {
			StringBuilder sb = new StringBuilder();

			sb.append("Completed ");

			Class<?> clazz = getClass();

			sb.append(clazz.getSimpleName());

			if (contextJSONObject != null) {
				sb.append(" with context ");
				sb.append(contextJSONObject);
			}

			sb.append(" in ");
			sb.append(duration);
			sb.append("ms");

			_log(sb.toString());
		}
	}

	@Override
	public void logFailed(
		String asahTaskId, JSONObject contextJSONObject, long duration,
		Throwable throwable) {

		if (isLogRunEnabled()) {
			if (!StringUtil.isNull(asahTaskId)) {
				_runLogger.log(
					null, this, false, "FAILED", faroInfoElasticsearchInvoker,
					"OSBAsahTaskId", asahTaskId, "failureReason",
					ExceptionUtils.getStackTrace(throwable));
			}
			else {
				_runLogger.log(
					null, this, true, "FAILED", faroInfoElasticsearchInvoker,
					"failureReason", ExceptionUtils.getStackTrace(throwable));
			}
		}
	}

	@Override
	public void logStart(JSONObject contextJSONObject) {
		if (isLogRunEnabled()) {
			_runLogger.log(null, this, "STARTED", faroInfoElasticsearchInvoker);
		}
		else {
			StringBuilder sb = new StringBuilder();

			sb.append("Running ");

			Class<?> clazz = getClass();

			sb.append(clazz.getSimpleName());

			if (contextJSONObject != null) {
				sb.append(" with context ");
				sb.append(contextJSONObject);
			}

			_log(sb.toString());
		}
	}

	protected abstract Log getLog();

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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	protected ElasticsearchInvoker faroInfoElasticsearchInvoker;

	private void _log(String message) {
		Log log = getLog();

		if (log.isInfoEnabled()) {
			log.info(message);
		}
	}

	private static final Counter _processedCounter = PrometheusUtil.counter(
		"faro_curator_processed_count", "The number of objects curated",
		"nanite");
	private static final Gauge _queueSizeGauge = PrometheusUtil.gauge(
		"faro_curator_queue_size", "The number of objects queued to be curated",
		"nanite");

	@Autowired
	private RunLogger _runLogger;

}