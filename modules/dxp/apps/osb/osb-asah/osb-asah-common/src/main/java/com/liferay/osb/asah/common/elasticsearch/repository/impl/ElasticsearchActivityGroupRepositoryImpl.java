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
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.ActivityGroup;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchActivityGroupRepositoryImpl
	extends BaseElasticsearchRepository<ActivityGroup>
	implements ActivityGroupRepository {

	@Override
	public long countActivityGroups(
		Long channelId, Date endDayDate, Date startDayDate, Long ownerId) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getBoolQueryBuilder(channelId, endDayDate, startDayDate, ownerId));
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
		Long channelId, Date endDayDate, Date startDayDate, Long ownerId,
		Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());
			collectionGetResponse.setQueryBuilder(
				_getBoolQueryBuilder(
					channelId, endDayDate, startDayDate, ownerId));
			collectionGetResponse.setSize(pageable.getPageSize());

			List<String> sorts = new ArrayList<>();

			for (Sort.Order order : pageable.getSort()) {
				StringBuilder sb = new StringBuilder();

				String property = order.getProperty();

				if (Objects.equals(property, "startDate")) {
					property = "startTime";
				}

				sb.append(property);
				sb.append(",");

				if (order.isAscending()) {
					sb.append("asc");
				}
				else {
					sb.append("desc");
				}

				sorts.add(sb.toString());
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

	private BoolQueryBuilder _getBoolQueryBuilder(
		Long channelId, Date endDayDate, Date startDayDate, Long ownerId) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (Objects.nonNull(channelId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"channelId", String.valueOf(channelId)));
		}

		if (Objects.nonNull(endDayDate)) {
			RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
				"day");

			rangeQueryBuilder.lt(DateUtil.toUTCString(endDayDate));
			rangeQueryBuilder.timeZone(TimeZoneDogUtil.getTimeZoneId());

			boolQueryBuilder.filter(rangeQueryBuilder);
		}

		if (Objects.nonNull(startDayDate)) {
			RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
				"day");

			rangeQueryBuilder.gte(DateUtil.toUTCString(startDayDate));
			rangeQueryBuilder.timeZone(TimeZoneDogUtil.getTimeZoneId());

			boolQueryBuilder.filter(rangeQueryBuilder);
		}

		if (Objects.nonNull(ownerId)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId)));
		}

		return boolQueryBuilder;
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchActivityGroupRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}