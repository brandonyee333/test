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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONArrayIterator;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 */
public abstract class BaseScoresNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		setRunning(true);

		if (contextJSONObject != null) {
			String processDayDateString = contextJSONObject.optString(
				"processDay", null);

			if (processDayDateString != null) {
				run(processDayDateString);

				return;
			}
		}

		JSONObject osbAsahMarkerJSONObject = getOSBAsahMarkerJSONObject();

		String lastSuccessfulDayDateString = _getLastSuccessfulDayDateString(
			contextJSONObject, osbAsahMarkerJSONObject);

		if (lastSuccessfulDayDateString == null) {
			lastSuccessfulDayDateString = _getFirstDayDateString();

			if (lastSuccessfulDayDateString == null) {
				return;
			}
		}
		else {
			lastSuccessfulDayDateString = DateUtil.addDays(
				lastSuccessfulDayDateString, 1);
		}

		String currentDayDateString = DateUtil.newDayDateString();

		while (lastSuccessfulDayDateString.compareTo(currentDayDateString) <
					0) {

			long start = System.currentTimeMillis();

			run(lastSuccessfulDayDateString);

			if (_log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				_log.info(
					String.format(
						"Processed %s for %s in %d ms", clazz.getSimpleName(),
						lastSuccessfulDayDateString,
						System.currentTimeMillis() - start));
			}

			if (isInterrupted()) {
				setRunning(false);

				return;
			}

			osbAsahMarkerJSONObject.put(
				"lastSuccessfulDay", lastSuccessfulDayDateString);

			faroInfoElasticsearchInvoker.update(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);

			afterOSBAsahMarkerUpdated(lastSuccessfulDayDateString);

			lastSuccessfulDayDateString = DateUtil.addDays(
				lastSuccessfulDayDateString, 1);
		}

		setRunning(false);
	}

	protected void afterOSBAsahMarkerUpdated(
		String lastSuccessfulDayDateString) {
	}

	protected abstract Log getLog();

	protected boolean isInterrupted() {
		return false;
	}

	protected boolean isRunning() {
		return false;
	}

	protected abstract void process(
			Map<String, Object> context, String dayDateString,
			JSONObject jsonObject)
		throws Exception;

	protected void run(
			Map<String, Object> context, String collectionName,
			String dayDateString, QueryBuilder queryBuilder)
		throws Exception {

		JSONArrayIterator.of(
			collectionName, faroInfoElasticsearchInvoker,
			jsonObject -> {
				try {
					if (isInterrupted()) {
						return JSONArrayIterator.INTERRUPT;
					}

					process(context, dayDateString, jsonObject);
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setMonitoringConsumers(
			this::monitorProcessedCount, this::monitorQueueSize
		).setQueryBuilder(
			queryBuilder
		).iterate();
	}

	protected abstract void run(String dayDateString) throws Exception;

	protected void setInterrupted(boolean interrupted) {
	}

	protected void setRunning(boolean running) {
	}

	private String _getFirstDayDateString() {
		JSONArray activitiesJSONArray = new JSONArray(
			faroInfoElasticsearchInvoker.get(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort(SortBuilderUtil.fieldSort("day"));
				}));

		if (activitiesJSONArray.length() == 0) {
			return null;
		}

		JSONObject activityJSONObject = activitiesJSONArray.getJSONObject(0);

		return activityJSONObject.getString("day");
	}

	private String _getLastSuccessfulDayDateString(
			JSONObject contextJSONObject, JSONObject osbAsahMarkerJSONObject)
		throws Exception {

		String lastSuccessfulDayDateString = osbAsahMarkerJSONObject.optString(
			"lastSuccessfulDay", null);

		if (contextJSONObject != null) {
			synchronized (this) {
				String reprocessDayDateString = contextJSONObject.getString(
					"reprocessDay");

				if ((lastSuccessfulDayDateString != null) &&
					(DateUtil.getDeltaMilliseconds(
						reprocessDayDateString, lastSuccessfulDayDateString) >
							0)) {

					if (isRunning()) {
						setInterrupted(true);

						while (isRunning()) {
						}

						setInterrupted(false);
					}

					lastSuccessfulDayDateString = reprocessDayDateString;

					osbAsahMarkerJSONObject.put(
						"lastSuccessfulDay", lastSuccessfulDayDateString);

					faroInfoElasticsearchInvoker.update(
						"OSBAsahMarkers", osbAsahMarkerJSONObject);
				}
			}
		}

		return lastSuccessfulDayDateString;
	}

	private static final Log _log = LogFactory.getLog(BaseScoresNanite.class);

}