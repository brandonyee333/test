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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoActivitiesFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@Repository
public class ElasticsearchActivityGroupRepositoryImpl
	extends BaseElasticsearchRepository<ActivityGroup, Long>
	implements ActivityGroupRepository {

	@Override
	public long countActivityGroups(String filterString) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			FilterStringToQueryBuilderConverter.convert(
				filterString, _faroInfoActivitiesFilterStringConverterHelper));
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
			true, getCollectionName());
	}

	@Override
	public ActivityGroup
		findByActivityTypeAndChannelIdAndDataSourceIdAndDayDateAndUserId(
			String activityType, Long channelId, Long dataSourceId,
			Date dayDate, String userId) {

		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
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
			this::toEntity
		).orElse(
			null
		);
	}

	@Override
	public List<ActivityGroup> searchActivityGroups(
		String filterString, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());

			QueryBuilder queryBuilder =
				FilterStringToQueryBuilderConverter.convert(
					filterString,
					_faroInfoActivitiesFilterStringConverterHelper);

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

			return toList(embeddedJSONObject.getJSONArray(getCollectionName()));
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
			getCollectionName());
	}

	@Override
	protected String getCollectionName() {
		return "activity-groups";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchActivityGroupRepositoryImpl.class);

	@Autowired
	private FaroInfoActivitiesFilterStringConverterHelper
		_faroInfoActivitiesFilterStringConverterHelper;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}