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

package com.liferay.osb.asah.upgrade.v3_1_0.helper;

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class EventsUpgradeHelper {

	public void doUpgrade(
		QueryBuilder queryBuilder, File propertiesFile,
		BiConsumer<JSONArray, String> biConsumer) {

		Properties properties = _getProperties(propertiesFile);

		String latestActivityDateString = properties.getProperty(
			ProjectIdThreadLocal.getProjectId() + ".date");

		if (StringUtils.isBlank(latestActivityDateString)) {
			JSONObject activityJSONObject = _faroInfoElasticsearchInvoker.fetch(
				"activities", queryBuilder, SortBuilderUtil.fieldSort("id"),
				null, null);

			if (activityJSONObject == null) {
				if (_log.isInfoEnabled()) {
					_log.info("No matching activities found. Skipping.");
				}

				return;
			}

			latestActivityDateString = activityJSONObject.getString("endTime");
		}

		String latestActivityId = properties.getProperty(
			ProjectIdThreadLocal.getProjectId() + ".id", "0");

		Date date = DateUtil.newDate();

		while (date.after(DateUtil.toUTCDate(latestActivityDateString))) {
			latestActivityId = _upgrade(
				biConsumer, latestActivityId, propertiesFile,
				BoolQueryBuilderUtil.filter(
					queryBuilder
				).filter(
					QueryBuilders.rangeQuery(
						"endTime"
					).gte(
						latestActivityDateString
					).lt(
						DateUtil.addDays(
							latestActivityDateString, _eventsUpgradeBucketSize)
					)
				));

			latestActivityDateString = DateUtil.addDays(
				latestActivityDateString, _eventsUpgradeBucketSize);

			if (date.before(DateUtil.toUTCDate(latestActivityDateString))) {
				latestActivityDateString = DateUtil.toUTCString(date);
			}
		}
	}

	private Properties _getProperties(File propertiesFile) {
		try (FileInputStream fileInputStream = new FileInputStream(
				propertiesFile)) {

			Properties properties = new Properties();

			properties.load(fileInputStream);

			return properties;
		}
		catch (IOException ioException) {
			return new Properties();
		}
	}

	private void _saveProperties(Properties properties, File propertiesFile) {
		try (FileOutputStream fileOutputStream = new FileOutputStream(
				propertiesFile)) {

			properties.store(fileOutputStream, null);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _setBatchSize(
		long newTime, SearchSourceBuilder searchSourceBuilder) {

		double newStatistics = newTime / (double)_customEventUpgradeBatchSize;

		if ((_customEventUpgradeBatchSize < 10000) &&
			(_currentStatistics > (newStatistics * 1.05))) {

			_customEventUpgradeBatchSize += 100;

			_updateBatchSize(newStatistics, searchSourceBuilder);
		}
		else if ((_customEventUpgradeBatchSize > 100) &&
				 (_currentStatistics < (newStatistics * 0.95))) {

			_customEventUpgradeBatchSize -= 100;

			_updateBatchSize(newStatistics, searchSourceBuilder);
		}
	}

	private void _updateBatchSize(
		double newStatistics, SearchSourceBuilder searchSourceBuilder) {

		_currentStatistics = newStatistics;

		searchSourceBuilder.size(_customEventUpgradeBatchSize);

		if (_log.isDebugEnabled()) {
			_log.debug("New batch size : " + _customEventUpgradeBatchSize);
		}
	}

	private String _upgrade(
		BiConsumer<JSONArray, String> biConsumer, String latestActivityId,
		File propertiesFile, QueryBuilder queryBuilder) {

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		searchSourceBuilder.query(queryBuilder);
		searchSourceBuilder.size(_customEventUpgradeBatchSize);
		searchSourceBuilder.sort("id");
		searchSourceBuilder.trackTotalHits(false);

		try {
			while (true) {
				searchSourceBuilder.searchAfter(
					new Object[] {latestActivityId});

				long startTime = System.currentTimeMillis();

				SearchResponse searchResponse =
					_faroInfoElasticsearchInvoker.search(
						"activities", searchSourceBuilder);

				long endTime = System.currentTimeMillis();

				SearchHits searchHits = searchResponse.getHits();

				SearchHit[] hits = searchHits.getHits();

				if (hits.length == 0) {
					break;
				}

				if (_log.isDebugEnabled()) {
					_log.debug(
						"It took " + (endTime - startTime) + "ms to retrieve " +
							hits.length + " activities.");
				}

				_setBatchSize(endTime - startTime, searchSourceBuilder);

				Stream<SearchHit> stream = Arrays.stream(hits);

				JSONArray jsonArray = new JSONArray(
					stream.parallel(
					).map(
						SearchHit::getSourceAsString
					).map(
						JSONObject::new
					).collect(
						Collectors.toList()
					));

				String projectId = ProjectIdThreadLocal.getProjectId();

				_boundedExecutor.runAsync(
					() -> biConsumer.accept(jsonArray, projectId));

				JSONObject jsonObject = jsonArray.getJSONObject(
					jsonArray.length() - 1);

				latestActivityId = jsonObject.getString("id");

				Properties properties = _getProperties(propertiesFile);

				properties.put(
					ProjectIdThreadLocal.getProjectId() + ".date",
					jsonObject.getString("endTime"));
				properties.put(
					ProjectIdThreadLocal.getProjectId() + ".id",
					latestActivityId);

				_saveProperties(properties, propertiesFile);
			}

			_boundedExecutor.awaitPendingTasks();

			return latestActivityId;
		}
		catch (RuntimeException runtimeException) {
			_log.error(runtimeException, runtimeException);

			throw runtimeException;
		}
	}

	private static final Log _log = LogFactory.getLog(
		EventsUpgradeHelper.class);

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(40, 30);
	private double _currentStatistics = 120;

	@Value("${osb.asah.custom.event.upgrade.batch.size:10000}")
	private int _customEventUpgradeBatchSize;

	@Value("${osb.asah.events.upgrade.bucket.size:30}")
	private int _eventsUpgradeBucketSize;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}