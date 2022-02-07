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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.util.WeDeployServiceThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchRunLogRepositoryImpl implements RunLogRepository {

	@Override
	public long count() {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		return elasticsearchInvoker.count(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public void delete(RunLog runLog) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		elasticsearchInvoker.delete(
			_getCollectionName(), String.valueOf(runLog.getId()));
	}

	@Override
	public void deleteAll() {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		elasticsearchInvoker.delete(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public void deleteAll(Iterable<? extends RunLog> runLogs) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		Stream<? extends RunLog> stream = StreamSupport.stream(
			runLogs.spliterator(), false);

		elasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				stream.map(
					RunLog::getId
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, _getCollectionName());
	}

	@Override
	public void deleteByDataSourceId(Long dataSourceId) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		elasticsearchInvoker.delete(
			_getCollectionName(),
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId)));
	}

	@Override
	public void deleteById(Long id) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		elasticsearchInvoker.delete(_getCollectionName(), id.toString());
	}

	@Override
	public boolean existsById(Long id) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		return elasticsearchInvoker.exists(_getCollectionName(), id.toString());
	}

	@Override
	public Iterable<RunLog> findAll() {
		return findAll(Sort.by("id"));
	}

	@Override
	public Page<RunLog> findAll(Pageable pageable) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		return PageableExecutionUtils.getPage(
			_toRunLogs(
				elasticsearchInvoker.get(
					_getCollectionName(),
					_getFieldSortBuilders(pageable.getSort()),
					(int)pageable.getOffset(), pageable.getPageSize())),
			pageable, this::count);
	}

	@Override
	public Iterable<RunLog> findAll(Sort sort) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		return _toRunLogs(
			elasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(sort)));
	}

	@Override
	public Iterable<RunLog> findAllById(Iterable<Long> ids) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		Stream<Long> stream = StreamSupport.stream(ids.spliterator(), false);

		return _toRunLogs(
			elasticsearchInvoker.get(
				_getCollectionName(),
				QueryBuilders.termsQuery(
					"id",
					stream.map(
						String::valueOf
					).collect(
						Collectors.toList()
					))));
	}

	@Override
	public Optional<RunLog>
		findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
			@Nullable Long dataSourceId, String naniteClassName,
			@Nullable String status) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("naniteClassName", naniteClassName));

		if (StringUtils.isNotBlank(status)) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("status", status));
		}

		if (dataSourceId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"dataSourceId", dataSourceId.toString()));
		}
		else {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("dataSourceId")));
		}

		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		JSONObject jsonObject = elasticsearchInvoker.fetch(
			_getCollectionName(), boolQueryBuilder,
			SortBuilderUtil.fieldSort("dateLogged", SortOrder.DESC), null,
			null);

		return Optional.ofNullable(
			jsonObject
		).map(
			this::_toRunLog
		);
	}

	@Override
	public Optional<RunLog> findById(Long id) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		return Optional.ofNullable(
			elasticsearchInvoker.fetch(_getCollectionName(), id.toString())
		).map(
			this::_toRunLog
		);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends RunLog> S save(S runLog) {
		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		JSONObject jsonObject = _toJSONObject(runLog);

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		return (S)_toRunLog(
			elasticsearchInvoker.add(_getCollectionName(), jsonObject));
	}

	@Override
	public <S extends RunLog> Iterable<S> saveAll(Iterable<S> runLogs) {
		List<S> list = new ArrayList<>();

		JSONArray jsonArray = new JSONArray();

		ElasticsearchInvoker elasticsearchInvoker =
			_resolveElasticsearchInvoker();

		runLogs.forEach(
			runLog -> {
				JSONObject jsonObject = _toJSONObject(runLog);

				String id = jsonObject.optString(
					"id", _timeOrderedUuidGenerator.generateId());

				jsonObject.put("id", id);

				jsonArray.put(jsonObject);

				list.add((S)_toRunLog(jsonObject));
			});

		elasticsearchInvoker.add(_getCollectionName(), jsonArray);

		return list;
	}

	private String _getCollectionName() {
		return "run-logs";
	}

	private List<FieldSortBuilder> _getFieldSortBuilders(Sort sort) {
		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();

		if ((sort == null) || sort.isUnsorted()) {
			fieldSortBuilders.add(SortBuilderUtil.fieldSort("id"));

			return fieldSortBuilders;
		}

		for (Sort.Order order : sort.toList()) {
			String fieldName = order.getProperty();

			fieldName = fieldName.replace('/', '.');

			SortOrder sortOrder = SortOrder.ASC;

			if (order.isDescending()) {
				sortOrder = SortOrder.DESC;
			}

			fieldSortBuilders.add(
				SortBuilderUtil.fieldSort(fieldName, sortOrder));
		}

		return fieldSortBuilders;
	}

	private ElasticsearchInvoker _resolveElasticsearchInvoker() {
		WeDeployDataService weDeployDataService =
			WeDeployServiceThreadLocal.getWeDeployDataService();

		if (weDeployDataService == null) {
			throw new IllegalStateException("WeDeploy data service is null");
		}

		if (weDeployDataService == WeDeployDataService.OSB_ASAH_DXP_RAW) {
			return _dxpRawElasticsearchInvoker;
		}

		if (weDeployDataService == WeDeployDataService.OSB_ASAH_FARO_INFO) {
			return _faroInfoElasticsearchInvoker;
		}

		if (weDeployDataService ==
				WeDeployDataService.OSB_ASAH_SALESFORCE_RAW) {

			return _salesforceRawElasticsearchInvoker;
		}

		throw new IllegalStateException(
			"Unexpected WeDeploy data service value " + weDeployDataService);
	}

	private JSONObject _toJSONObject(RunLog runLog) {
		return _objectMapper.convertValue(runLog, JSONObject.class);
	}

	private RunLog _toRunLog(JSONObject jsonObject) {
		return _objectMapper.convertValue(jsonObject, RunLog.class);
	}

	private List<RunLog> _toRunLogs(JSONArray jsonArray) {
		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> _toRunLog((JSONObject)object)
		).collect(
			Collectors.toList()
		);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}