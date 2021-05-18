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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.DXPClient;
import com.liferay.osb.asah.common.entity.ExperimentVariantMetric;
import com.liferay.osb.asah.common.http.ExperimentHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.ExperimentStatus;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.Range;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class ExperimentNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		JSONArrayIterator.of(
			"experiments", faroInfoElasticsearchInvoker, this::_updateExperiment
		).setQueryBuilder(
			QueryBuilders.termQuery(
				"status", ExperimentStatus.RUNNING.toString())
		).setStopOnExceptions(
			false
		).iterate();
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private ExperimentVariantMetric _findControlExperimentVariantMetric(
		List<ExperimentVariantMetric> experimentVariantMetrics) {

		Stream<ExperimentVariantMetric> stream =
			experimentVariantMetrics.stream();

		Optional<ExperimentVariantMetric>
			controlExperimentVariantMetricsOptional = stream.filter(
				ExperimentVariantMetric::isControl
			).findFirst();

		return controlExperimentVariantMetricsOptional.orElseThrow(
			IllegalStateException::new);
	}

	private ExperimentVariantMetric _getWinnerExperimentVariantMetric(
			String metric, JSONArray variantMetricsJSONArray)
		throws Exception {

		List<ExperimentVariantMetric> experimentVariantMetrics =
			_toExperimentVariantMetrics(variantMetricsJSONArray);

		ExperimentVariantMetric controlExperimentVariantMetric =
			_findControlExperimentVariantMetric(experimentVariantMetrics);

		Range<Double> controlConfidenceIntervalRange =
			controlExperimentVariantMetric.getConfidenceIntervalRange();

		List<ExperimentVariantMetric> improvedExperimentVariantMetrics =
			new ArrayList<>();
		List<ExperimentVariantMetric> tiedExperimentVariantMetrics =
			new ArrayList<>();

		for (ExperimentVariantMetric experimentVariantMetric :
				experimentVariantMetrics) {

			if (experimentVariantMetric.isControl()) {
				continue;
			}

			Range<Double> variantConfidenceIntervalRange =
				experimentVariantMetric.getConfidenceIntervalRange();

			if (_hasExperimentVariantMetricImproved(
					controlConfidenceIntervalRange, metric,
					variantConfidenceIntervalRange)) {

				improvedExperimentVariantMetrics.add(experimentVariantMetric);
			}
			else if (variantConfidenceIntervalRange.isOverlappedBy(
						controlConfidenceIntervalRange)) {

				tiedExperimentVariantMetrics.add(experimentVariantMetric);
			}
		}

		if (!improvedExperimentVariantMetrics.isEmpty()) {
			if (Objects.equals(metric, "BOUNCE_RATE")) {
				return Collections.min(
					improvedExperimentVariantMetrics,
					Comparator.comparing(ExperimentVariantMetric::getMedian));
			}

			return Collections.max(
				improvedExperimentVariantMetrics,
				Comparator.comparing(ExperimentVariantMetric::getMedian));
		}

		if (tiedExperimentVariantMetrics.isEmpty()) {
			return controlExperimentVariantMetric;
		}

		return null;
	}

	private boolean _hasExperimentVariantMetricImproved(
		Range<Double> controlConfidenceIntervalRange, String metric,
		Range<Double> variantConfidenceIntervalRange) {

		if (Objects.equals(metric, "BOUNCE_RATE") &&
			variantConfidenceIntervalRange.isBeforeRange(
				controlConfidenceIntervalRange)) {

			return true;
		}

		if (variantConfidenceIntervalRange.isAfterRange(
				controlConfidenceIntervalRange)) {

			return true;
		}

		return false;
	}

	private void _putOrReplaceMetric(
		JSONArray metricsJSONArray, JSONObject newMetricsJSONObject) {

		OffsetDateTime newProcessedOffsetDateTime = OffsetDateTime.parse(
			newMetricsJSONObject.getString("processedDate"));

		LocalDate newProcessedLocalDate =
			newProcessedOffsetDateTime.toLocalDate();

		for (int i = 0; i < metricsJSONArray.length(); i++) {
			JSONObject metricsJSONObject = metricsJSONArray.getJSONObject(i);

			OffsetDateTime processedOffsetDateTime = OffsetDateTime.parse(
				metricsJSONObject.getString("processedDate"));

			LocalDate processedLocalDate =
				processedOffsetDateTime.toLocalDate();

			if (processedLocalDate.isEqual(newProcessedLocalDate)) {
				metricsJSONArray.put(i, newMetricsJSONObject);

				return;
			}
		}

		metricsJSONArray.put(newMetricsJSONObject);
	}

	private List<ExperimentVariantMetric> _toExperimentVariantMetrics(
			JSONArray experimentVariantMetricsJSONArray)
		throws Exception {

		TypeFactory typeFactory = TypeFactory.defaultInstance();

		return _objectMapper.readValue(
			experimentVariantMetricsJSONArray.toString(),
			typeFactory.constructCollectionType(
				List.class, ExperimentVariantMetric.class));
	}

	private JSONObject _updateExperiment(JSONObject experimentJSONObject)
		throws Exception {

		JSONArray metricsJSONArray = experimentJSONObject.optJSONArray(
			"metrics");

		if (metricsJSONArray == null) {
			metricsJSONArray = new JSONArray();
		}

		String experimentId = experimentJSONObject.getString("id");

		JSONObject metricsJSONObject =
			_experimentHttp.getExperimentMetricsJSONObject(experimentId);

		_putOrReplaceMetric(metricsJSONArray, metricsJSONObject);

		experimentJSONObject.put("metrics", metricsJSONArray);

		if (metricsJSONObject.optLong("estimatedDaysLeft", 1) <= 0) {
			experimentJSONObject.put(
				"finishedDate", DateUtil.toUTCString(new Date()));

			JSONObject goalJSONObject = experimentJSONObject.getJSONObject(
				"goal");

			ExperimentVariantMetric experimentVariantMetric =
				_getWinnerExperimentVariantMetric(
					goalJSONObject.getString("metric"),
					metricsJSONObject.getJSONArray("variantMetrics"));

			ExperimentStatus experimentStatus =
				ExperimentStatus.FINISHED_WINNER;

			String winnerDXPVariantId = null;

			if (experimentVariantMetric == null) {
				experimentStatus = ExperimentStatus.FINISHED_NO_WINNER;
			}
			else {
				winnerDXPVariantId = experimentVariantMetric.getDXPVariantId();

				experimentJSONObject.put(
					"winnerDXPVariantId", winnerDXPVariantId);
			}

			experimentJSONObject.put("status", experimentStatus);

			_dxpClient.updateDXPExperimentStatus(
				experimentJSONObject.getLong("dataSourceId"), experimentId,
				experimentStatus, winnerDXPVariantId);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Updated metrics for experiment " + experimentId);
		}

		return faroInfoElasticsearchInvoker.update(
			"experiments", experimentJSONObject);
	}

	private static final Log _log = LogFactory.getLog(ExperimentNanite.class);

	@Autowired
	private DXPClient _dxpClient;

	@Autowired
	private ExperimentHttp _experimentHttp;

	@Autowired
	private ObjectMapper _objectMapper;

}