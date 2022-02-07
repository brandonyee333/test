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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@Repository
public class ElasticsearchActivityGroupRepositoryImpl
	implements ActivityGroupRepository {

	@Override
	public long count() {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public long countActivityGroups(FilterHelper filterHelper) {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), filterHelper.getQueryBuilder());
	}

	@Override
	public void delete(ActivityGroup activityGroup) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), String.valueOf(activityGroup.getId()));
	}

	@Override
	public void deleteAll() {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public void deleteAll(Iterable<? extends ActivityGroup> activityGroups) {
		Stream<? extends ActivityGroup> stream = StreamSupport.stream(
			activityGroups.spliterator(), false);

		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				stream.map(
					ActivityGroup::getId
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, _getCollectionName());
	}

	@Override
	public void deleteByChannelIdIn(Set<Long> channelIds) {
		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"channelId",
				Stream.of(
					channelIds
				).flatMap(
					Set::stream
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, _getCollectionName());
	}

	@Override
	public void deleteById(Long id) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), id.toString());
	}

	@Override
	public boolean existsById(Long id) {
		return _faroInfoElasticsearchInvoker.exists(
			_getCollectionName(), id.toString());
	}

	@Override
	public Iterable<ActivityGroup> findAll() {
		return findAll(Sort.by("id"));
	}

	@Override
	public Page<ActivityGroup> findAll(Pageable pageable) {
		return PageableExecutionUtils.getPage(
			_toActivityGroups(
				_faroInfoElasticsearchInvoker.get(
					_getCollectionName(),
					_getFieldSortBuilders(pageable.getSort()),
					(int)pageable.getOffset(), pageable.getPageSize())),
			pageable, this::count);
	}

	@Override
	public Iterable<ActivityGroup> findAll(Sort sort) {
		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();

		if (sort.isUnsorted()) {
			fieldSortBuilders.add(SortBuilderUtil.fieldSort("id"));
		}
		else {
			Stream.of(
				sort
			).flatMap(
				Sort::stream
			).forEach(
				order -> {
					SortOrder sortOrder = SortOrder.ASC;

					if (order.isDescending()) {
						sortOrder = SortOrder.DESC;
					}

					fieldSortBuilders.add(
						SortBuilderUtil.fieldSort(
							order.getProperty(), sortOrder));
				}
			);
		}

		return _toActivityGroups(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), fieldSortBuilders));
	}

	@Override
	public Iterable<ActivityGroup> findAllById(Iterable<Long> ids) {
		Stream<Long> stream = StreamSupport.stream(ids.spliterator(), false);

		return _toActivityGroups(
			_faroInfoElasticsearchInvoker.get(
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
	public ActivityGroup
		findByActivityTypeAndChannelIdAndDataSourceIdAndDayDateAndUserId(
			String activityType, Long channelId, Long dataSourceId,
			Date dayDate, String userId) {

		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("activityType", activityType)
				).filter(
					QueryBuilders.termQuery(
						"channelId", String.valueOf(channelId))
				).filter(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termQuery(
						"day", DateUtil.toUTCString(dayDate))
				).filter(
					QueryBuilders.termQuery("userId", userId)
				))
		).map(
			this::_toActivityGroup
		).orElse(
			null
		);
	}

	@Override
	public Optional<ActivityGroup> findById(Long id) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(), id.toString())
		).map(
			this::_toActivityGroup
		);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends ActivityGroup> S save(S activityGroup) {
		JSONObject jsonObject = _toJSONObject(activityGroup);

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		return (S)_toActivityGroup(
			_faroInfoElasticsearchInvoker.add(
				_getCollectionName(), jsonObject));
	}

	@Override
	public <S extends ActivityGroup> Iterable<S> saveAll(
		Iterable<S> activityGroups) {

		List<S> list = new ArrayList<>();

		JSONArray jsonArray = new JSONArray();

		activityGroups.forEach(
			activityGroup -> {
				JSONObject jsonObject = _toJSONObject(activityGroup);

				String id = jsonObject.optString(
					"id", _timeOrderedUuidGenerator.generateId());

				jsonObject.put("id", id);

				jsonArray.put(jsonObject);

				list.add((S)_toActivityGroup(jsonObject));
			});

		_faroInfoElasticsearchInvoker.add(_getCollectionName(), jsonArray);

		return list;
	}

	@Override
	public List<ActivityGroup> searchActivityGroups(
		FilterHelper filterHelper, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(_getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());

			QueryBuilder queryBuilder = filterHelper.getQueryBuilder();

			if (queryBuilder != null) {
				collectionGetResponse.setQueryBuilder(queryBuilder);
			}

			collectionGetResponse.setSize(pageable.getPageSize());

			List<String> sorts = new LinkedList<>();

			for (Sort.Order order : pageable.getSort()) {
				String property = order.getProperty();

				if (Objects.equals(property, "startDate")) {
					property = "startTime";
				}

				sorts.add(property);

				if (order.isAscending()) {
					sorts.add("asc");
				}
				else {
					sorts.add("desc");
				}
			}

			collectionGetResponse.setSorts(sorts.toArray(new String[0]));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return _toActivityGroups(
				embeddedJSONObject.getJSONArray(_getCollectionName()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	public boolean updateOwnerId(Long ownerId, String userId) {
		return _faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("userId", userId), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.ownerId = params.ownerId",
				Collections.singletonMap("ownerId", String.valueOf(ownerId))),
			_getCollectionName());
	}

	private String _getCollectionName() {
		return "activity-groups";
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

	private ActivityGroup _toActivityGroup(JSONObject jsonObject) {
		return _objectMapper.convertValue(jsonObject, ActivityGroup.class);
	}

	private List<ActivityGroup> _toActivityGroups(JSONArray jsonArray) {
		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> _toActivityGroup((JSONObject)object)
		).collect(
			Collectors.toList()
		);
	}

	private JSONObject _toJSONObject(ActivityGroup activityGroup) {
		return _objectMapper.convertValue(activityGroup, JSONObject.class);
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchActivityGroupRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}