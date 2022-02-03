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
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Bowerman
 */
public abstract class BaseScoresNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		try {
			setRunning(true);

			AsahMarker asahMarker = getAsahMarker();

			if (contextJSONObject != null) {
				String processDayDateString = contextJSONObject.optString(
					"processDay", null);

				if (processDayDateString != null) {
					run(processDayDateString);

					_updateLastSuccessfulDayDate(
						asahMarker, processDayDateString);

					return;
				}
			}

			String lastSuccessfulDayDateString =
				_getLastSuccessfulDayDateString(asahMarker, contextJSONObject);

			if (lastSuccessfulDayDateString == null) {
				lastSuccessfulDayDateString =
					_faroInfoActivityDog.getFirstDayDateString();

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

				if (_log.isDebugEnabled()) {
					Class<?> clazz = getClass();

					_log.debug(
						String.format(
							"Processed %s for %s in %d ms",
							clazz.getSimpleName(), lastSuccessfulDayDateString,
							System.currentTimeMillis() - start));
				}

				if (isInterrupted()) {
					return;
				}

				_updateLastSuccessfulDayDate(
					asahMarker, lastSuccessfulDayDateString);

				lastSuccessfulDayDateString = DateUtil.addDays(
					lastSuccessfulDayDateString, 1);
			}
		}
		finally {
			setRunning(false);
		}
	}

	protected void afterOSBAsahMarkerUpdated(
		String lastSuccessfulDayDateString) {
	}

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
				catch (Exception exception) {
					return exception;
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

	private String _getLastSuccessfulDayDateString(
			AsahMarker asahMarker, JSONObject contextJSONObject)
		throws Exception {

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		String lastSuccessfulDayDateString =
			asahMarkerContextJSONObject.optString("lastSuccessfulDay", null);

		if (contextJSONObject != null) {
			synchronized (this) {
				if (lastSuccessfulDayDateString != null) {
					String reprocessDayDateString = contextJSONObject.optString(
						"reprocessDay", null);

					if (reprocessDayDateString == null) {
						return lastSuccessfulDayDateString;
					}

					long deltaMilliseconds = DateUtil.getDeltaMilliseconds(
						reprocessDayDateString, lastSuccessfulDayDateString);

					if (deltaMilliseconds > 0) {
						if (isRunning()) {
							setInterrupted(true);

							while (isRunning()) {
							}

							setInterrupted(false);
						}

						lastSuccessfulDayDateString = reprocessDayDateString;

						asahMarkerContextJSONObject.put(
							"lastSuccessfulDay", lastSuccessfulDayDateString);

						asahMarkerDog.updateAsahMarker(
							asahMarker, WeDeployDataService.OSB_ASAH_FARO_INFO);
					}
				}
			}
		}

		return lastSuccessfulDayDateString;
	}

	private void _updateLastSuccessfulDayDate(
		AsahMarker asahMarker, String lastSuccessfulDayDateString) {

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		asahMarkerContextJSONObject.put(
			"lastSuccessfulDay", lastSuccessfulDayDateString);

		asahMarkerDog.updateAsahMarker(
			asahMarker, WeDeployDataService.OSB_ASAH_FARO_INFO);

		afterOSBAsahMarkerUpdated(lastSuccessfulDayDateString);
	}

	private static final Log _log = LogFactory.getLog(BaseScoresNanite.class);

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

}