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
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
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
public class ElasticsearchDataSourceRepositoryImpl
	extends BaseElasticsearchRepository<DataSource, Long>
	implements DataSourceRepository {

	@Override
	public long countDataSources(
		List<Long> channelIds, String credentialType, List<String> names,
		String providerType, List<String> searchNames, List<String> states,
		Boolean url, Boolean workspaceURL) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_getBoolQueryBuilder(
				channelIds, credentialType, names, providerType, searchNames,
				states, url, workspaceURL));
	}

	@Override
	public boolean existsByFaroBackendSecuritySignature(
		String faroBackendSecuritySignature) {

		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			QueryBuilders.termQuery(
				"faroBackendSecuritySignature", faroBackendSecuritySignature));
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
	public boolean existsByProviderType(String providerType) {
		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("provider.type", providerType)
			).filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.termQuery("details.sitesSelected", true)
				).should(
					QueryBuilders.existsQuery(
						"provider.analyticsConfiguration.sites")
				).should(
					QueryBuilders.termQuery(
						"provider.analyticsConfiguration.enableAllSites", true)
				)
			));
	}

	@Override
	public List<DataSource> findAll(Pageable pageable) {
		return findByProviderType(null, pageable);
	}

	@Override
	public List<DataSource> findByCredentialType(
		String credentialType, Pageable pageable) {

		return findByCredentialTypeAndProviderType(
			credentialType, null, pageable);
	}

	@Override
	public List<DataSource> findByCredentialTypeAndProviderType(
		String credentialType, String providerType, Pageable pageable) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (credentialType != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("credentials.type", credentialType));
		}

		if (providerType != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"provider.type", providerType.toUpperCase()));
		}

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(boolQueryBuilder);
						searchSourceBuilder.size(pageable.getPageSize());

						Stream.of(
							pageable.getSort()
						).flatMap(
							Sort::stream
						).findFirst(
						).ifPresent(
							sort -> {
								SortOrder sortOrder = SortOrder.ASC;

								if (sort.isDescending()) {
									sortOrder = SortOrder.DESC;
								}

								searchSourceBuilder.sort(
									SortBuilderUtil.fieldSort(
										sort.getProperty(), sortOrder));
							}
						);
					})));
	}

	@Override
	public List<DataSource> findByProviderType(String providerType) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termQuery(
					"provider.type", providerType.toUpperCase())));
	}

	@Override
	public List<DataSource> findByProviderType(
		String providerType, Pageable pageable) {

		return findByCredentialTypeAndProviderType(
			null, providerType, pageable);
	}

	@Override
	public List<DataSource> findByProviderTypeAndStatus(
		String providerType, String status) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("provider.type", providerType)
				).filter(
					QueryBuilders.termQuery("status", status)
				)));
	}

	@Override
	public List<DataSource> searchDataSources(
		List<Long> channelIds, String credentialType, List<String> names,
		String providerType, List<String> searchNames, List<String> states,
		Boolean url, Boolean workspaceURL, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());
			collectionGetResponse.setQueryBuilder(
				_getBoolQueryBuilder(
					channelIds, credentialType, names, providerType,
					searchNames, states, url, workspaceURL));
			collectionGetResponse.setSize(pageable.getPageSize());

			List<String> sorts = new ArrayList<>();

			for (Sort.Order order : pageable.getSort()) {
				StringBuilder sb = new StringBuilder();

				String property = order.getProperty();

				if (Objects.equals(property, "createDate")) {
					property = "dateCreated";
				}
				else if (Objects.equals(property, "credentialType")) {
					property = "credentials.type";
				}
				else if (Objects.equals(property, "providerType")) {
					property = "provider.type";
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
	protected String getCollectionName() {
		return "data-sources";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private BoolQueryBuilder _getBoolQueryBuilder(
		List<Long> channelIds, String credentialType, List<String> names,
		String providerType, List<String> searchNames, List<String> states,
		Boolean url, Boolean workspaceURL) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (CollectionUtils.isNotEmpty(channelIds)) {
			boolQueryBuilder.filter(
				QueryBuilders.termsQuery("channelId", channelIds));
		}

		if (StringUtils.isNotEmpty(credentialType)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("credentials.type", credentialType));
		}

		if (CollectionUtils.isNotEmpty(names)) {
			boolQueryBuilder.filter(QueryBuilders.termsQuery("name", names));
		}

		if (StringUtils.isNotEmpty(providerType)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("provider.type", providerType));
		}

		if (CollectionUtils.isNotEmpty(searchNames)) {
			for (String searchName : searchNames) {
				boolQueryBuilder.filter(
					QueryBuilders.regexpQuery(
						"name",
						FilterStringToQueryBuilderConverter.
							buildIgnoreCaseRegExp(searchName)));
			}
		}

		if (CollectionUtils.isNotEmpty(states)) {
			BoolQueryBuilder stateBoolQueryBuilder = QueryBuilders.boolQuery();

			for (String state : states) {
				stateBoolQueryBuilder.should(
					QueryBuilders.termQuery("state", state));
			}

			boolQueryBuilder.filter(stateBoolQueryBuilder);
		}

		if ((url != null) && url) {
			boolQueryBuilder.filter(QueryBuilders.termsQuery("url", ""));
		}

		if ((workspaceURL != null) && workspaceURL) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("workspaceURL")));
		}

		return boolQueryBuilder;
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchDataSourceRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}