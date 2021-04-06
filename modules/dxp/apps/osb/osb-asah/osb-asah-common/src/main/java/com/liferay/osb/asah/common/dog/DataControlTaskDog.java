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

package com.liferay.osb.asah.common.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.model.DataControlTask;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.impl.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.model.DataControlTaskType;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;

import java.nio.file.Path;

import java.time.LocalDate;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DataControlTaskDog {

	public boolean addDataControlTasks(
		List<String> emailAddresses, Path path, String ownerId,
		List<String> types) {

		if (path != null) {
			File file = path.toFile();

			if (!file.exists()) {
				return false;
			}

			emailAddresses = _readFile(file);
		}

		JSONArray jsonArray = new JSONArray();

		String batchId = _timeOrderedUuidGenerator.generateId();
		String dateString = DateUtil.newUTCDateString();

		for (String emailAddress : emailAddresses) {
			for (String type : types) {
				if (type.equals(DataControlTaskType.UNSUPPRESS.toString())) {
					_updateSuppression(batchId, emailAddress, dateString);
				}

				jsonArray.put(
					JSONUtil.put(
						"batchId", batchId
					).put(
						"createDate", dateString
					).put(
						"emailAddress", emailAddress
					).put(
						"ownerId", ownerId
					).put(
						"status", DataControlTaskStatus.PENDING.toString()
					).put(
						"type", type
					));
			}
		}

		return _faroInfoElasticsearchInvoker.add(
			"data-control-tasks", jsonArray);
	}

	public ResultBag<DataControlTask> getDataControlTaskResultBag(
		String batchId, String keywords, Integer rangeKey, int size, Sort sort,
		int start, List<String> statuses, List<String> types) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (!StringUtils.isBlank(batchId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termsQuery("batchId", batchId));
		}

		if (!StringUtils.isBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "emailAddress",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.matchQuery(
						"emailAddress", keywords
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		if (rangeKey != null) {
			LocalDate localDate = LocalDate.now(_timeZoneDog.getZoneId());

			localDate = localDate.minusDays(rangeKey);

			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"createDate"
				).gte(
					localDate.toString()
				).timeZone(
					_timeZoneDog.getTimeZoneId()
				));
		}

		if ((statuses != null) && !statuses.isEmpty()) {
			BoolQueryBuilder statusBoolQueryBuilder = QueryBuilders.boolQuery();

			for (String status : statuses) {
				statusBoolQueryBuilder.should(
					QueryBuilders.termsQuery("status", status));
			}

			boolQueryBuilder.filter(statusBoolQueryBuilder);
		}

		if ((types != null) && !types.isEmpty()) {
			BoolQueryBuilder typesBoolQueryBuilder = QueryBuilders.boolQuery();

			for (String type : types) {
				typesBoolQueryBuilder.should(
					QueryBuilders.termsQuery("type", type));
			}

			boolQueryBuilder.filter(typesBoolQueryBuilder);
		}

		return DogUtil.createResultBag(
			DataControlTask.class, _objectMapper,
			_dataDog.querySearchHits(
				"data-control-tasks", _faroInfoElasticsearchInvoker,
				_buildSearchSourceBuilder(
					boolQueryBuilder, size, sort, start)));
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		QueryBuilder queryBuilder, int size, Sort sort, int start) {

		FieldSortBuilder fieldSortBuilder = SortBuilderUtil.fieldSort(sort);

		SearchSourceBuilder searchSourceBuilder =
			DogUtil.buildSearchSourceBuilder(
				fieldSortBuilder, queryBuilder, size, start);

		SortOrder sortOrder = SortOrder.DESC;

		if (StringUtils.contains(fieldSortBuilder.getFieldName(), "Date")) {
			sortOrder = fieldSortBuilder.order();
		}

		return searchSourceBuilder.sort(
			SortBuilderUtil.fieldSort("id", sortOrder));
	}

	private List<String> _readFile(File file) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();

		csvParserSettings.setHeaderExtractionEnabled(false);

		CsvParser csvParser = new CsvParser(csvParserSettings);

		return ListUtil.map(csvParser.parseAll(file), row -> row[0]);
	}

	private void _updateSuppression(
		String batchId, String emailAddress, String dateString) {

		JSONObject suppressionJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"suppressions",
			QueryBuilders.termsQuery("emailAddress", emailAddress));

		if (suppressionJSONObject == null) {
			return;
		}

		suppressionJSONObject.put("dataControlTaskBatchId", batchId);
		suppressionJSONObject.put("dataControlTaskCreateDate", dateString);
		suppressionJSONObject.put(
			"dataControlTaskStatus", DataControlTaskStatus.PENDING.toString());

		_faroInfoElasticsearchInvoker.update(
			"suppressions", suppressionJSONObject);
	}

	@Autowired
	private DataDog _dataDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

	@Autowired
	private TimeZoneDog _timeZoneDog;

}