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
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;
import java.util.stream.Stream;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

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
	extends BaseElasticsearchRepository<DataSource>
	implements DataSourceRepository {

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
	protected String getCollectionName() {
		return "data-sources";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}