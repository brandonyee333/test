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
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Account;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchAccountRepositoryImpl
	extends BaseElasticsearchRepository<Account, Long>
	implements AccountRepository {

	@Override
	public long countAccounts(String filterString) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), _getQueryBuilder(filterString));
	}

	@Override
	public List<Account> findAllByDataSourceId(Long dataSourceId) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))));
	}

	@Override
	public Optional<Account> findByAccountPK(String accountPK) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
				QueryBuilders.termQuery("accountPK", accountPK))
		).map(
			this::toEntity
		);
	}

	@Override
	public Optional<Account> findByAccountPKAndDataSourceId(
		String accountPK, Long dataSourceId) {

		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("accountPK", accountPK)
				).filter(
					QueryBuilders.termQuery("dataSourceId", dataSourceId)
				))
		).map(
			this::toEntity
		);
	}

	@Override
	public List<Account> searchAccounts(
		Long channelId, String filterString, Pageable pageable, Sort sort) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setQueryBuilder(
				_getQueryBuilder(filterString));

			if (pageable.isPaged()) {
				collectionGetResponse.setPage(pageable.getPageNumber());
				collectionGetResponse.setSize(pageable.getPageSize());
			}

			collectionGetResponse.setSorts(_getSorts(pageable.getSort()));

			if (channelId == null) {
				JSONObject jsonObject = new JSONObject(
					collectionGetResponse.respond());

				JSONObject embeddedJSONObject = jsonObject.getJSONObject(
					"_embedded");

				return toList(
					embeddedJSONObject.getJSONArray(getCollectionName()));
			}

			List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();
			List<String> newSorts = new ArrayList<>();

			String[] segmentSorts = _getSorts(sort);

			if (segmentSorts.length > 0) {
				List<Pair<String, SortOrder>> sortOrderPairs =
					SortBuilderUtil.getSortOrderPairs(segmentSorts);

				for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
					if (StringUtils.equalsIgnoreCase(
							sortOrderPair.getKey(), "activitiesCount")) {

						fieldSortBuilders.add(
							SortBuilderUtil.buildSort(
								"activitiesCounts.activitiesCount",
								"activitiesCounts",
								QueryBuilders.termQuery(
									"activitiesCounts.channelId", channelId),
								sortOrderPair.getValue()));
					}
					else if (StringUtils.equalsIgnoreCase(
								sortOrderPair.getKey(), "individualCount")) {

						fieldSortBuilders.add(
							SortBuilderUtil.buildSort(
								"individualCounts.individualCount",
								"individualCounts",
								QueryBuilders.termQuery(
									"individualCounts.channelId", channelId),
								sortOrderPair.getValue()));
					}
					else {
						SortOrder sortOrder = sortOrderPair.getValue();

						newSorts.add(sortOrderPair.getKey());
						newSorts.add(sortOrder.toString());
					}
				}
			}

			collectionGetResponse.setFieldSortBuilders(fieldSortBuilders);
			collectionGetResponse.setSorts(newSorts.toArray(new String[0]));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject responseJSONObject = _filterByChannelId(
				String.valueOf(channelId), jsonObject);

			JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
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
		return "accounts";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private JSONObject _filterByChannelId(
		String channelId, JSONObject responseJSONObject) {

		JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
			"_embedded");

		JSONArray accountsJSONArray = embeddedJSONObject.getJSONArray(
			"accounts");

		for (int i = 0; i < accountsJSONArray.length(); i++) {
			JSONObject accountJSONObject = accountsJSONArray.getJSONObject(i);

			JSONArray activitiesCountsJSONArray =
				accountJSONObject.optJSONArray("activitiesCounts");

			if (activitiesCountsJSONArray != null) {
				Map<String, JSONObject> activitiesCounts =
					JSONUtil.toJSONObjectMap(
						accountJSONObject.getJSONArray("activitiesCounts"),
						"channelId");

				JSONObject activitiesCountJSONObject =
					activitiesCounts.getOrDefault(
						channelId, JSONUtil.put("activitiesCount", 0));

				accountJSONObject.put(
					"activitiesCount",
					activitiesCountJSONObject.getInt("activitiesCount"));

				accountJSONObject.remove("activitiesCounts");
			}

			JSONArray individualCountsJSONArray =
				accountJSONObject.optJSONArray("individualCounts");

			if (individualCountsJSONArray != null) {
				Map<String, JSONObject> individualCounts =
					JSONUtil.toJSONObjectMap(
						accountJSONObject.getJSONArray("individualCounts"),
						"channelId");

				JSONObject individualCountJSONObject =
					individualCounts.getOrDefault(
						channelId, JSONUtil.put("individualCount", 0));

				accountJSONObject.put(
					"individualCount",
					individualCountJSONObject.getInt("individualCount"));

				accountJSONObject.remove("individualCounts");
			}
		}

		return responseJSONObject;
	}

	private QueryBuilder _getQueryBuilder(String filterString) {
		return FilterStringToQueryBuilderConverter.convert(filterString);
	}

	private String[] _getSorts(Sort sort) {
		if (sort == null) {
			return null;
		}

		List<String> sorts = new ArrayList<>();

		for (Sort.Order order : sort) {
			StringBuilder sb = new StringBuilder();

			sb.append(order.getProperty());
			sb.append(",");

			if (order.isAscending()) {
				sb.append("asc");
			}
			else {
				sb.append("desc");
			}

			sorts.add(sb.toString());
		}

		return sorts.toArray(new String[0]);
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchAccountRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}