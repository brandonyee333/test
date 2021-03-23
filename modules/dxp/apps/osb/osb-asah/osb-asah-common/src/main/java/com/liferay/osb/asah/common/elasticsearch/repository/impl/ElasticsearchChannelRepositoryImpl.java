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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

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
public class ElasticsearchChannelRepositoryImpl
	extends BaseElasticsearchRepository<Channel, Long>
	implements ChannelRepository {

	@Override
	public long countByNameContainingIgnoreCase(String name) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			QueryUtil.buildSearchQueryBuilder("name.search", name));
	}

	@Override
	public void deleteByIdIn(Set<Long> ids) {
		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				Stream.of(
					ids
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
	public boolean existsByIdNotAndName(Long id, String name) {
		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("name", name)
			).mustNot(
				QueryBuilders.termQuery("id", String.valueOf(id))
			));
	}

	@Override
	public boolean existsByName(String name) {
		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(), QueryBuilders.termQuery("name", name));
	}

	@Override
	public List<Channel> findAll(Pageable pageable) {
		return findByNameContainingIgnoreCase(null, pageable);
	}

	@Override
	public List<Channel> findByDataSourceId(Long dataSourceId) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSources.id", String.valueOf(dataSourceId)))));
	}

	@Override
	public List<Channel> findByDataSourceIdAndGroupIds(
		Long dataSourceId, Set<Long> groupsIds) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSources.id", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termsQuery(
						"dataSources.groupIds",
						Stream.of(
							groupsIds
						).flatMap(
							Set::stream
						).map(
							String::valueOf
						).collect(
							Collectors.toList()
						))
				)));
	}

	@Override
	public List<Channel> findByNameContainingIgnoreCase(
		String name, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());
			collectionGetResponse.setQueryBuilder(
				QueryUtil.buildSearchQueryBuilder("name.search", name));
			collectionGetResponse.setSize(pageable.getPageSize());

			List<String> sorts = new ArrayList<>();

			for (Sort.Order order : pageable.getSort()) {
				StringBuilder sb = new StringBuilder();

				sb.append(order.getProperty());

				if (Objects.equals(order.getProperty(), "name")) {
					sb.append(".sort");
				}

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
	protected String getCollectionName() {
		return "channels";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchChannelRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}