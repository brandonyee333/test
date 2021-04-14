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
import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.model.PropertyFilter;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchAssetRepositoryImpl
	extends BaseElasticsearchRepository<Asset, Long>
	implements AssetRepository {

	@Override
	public long countAssets(
		String assetType, String keyword,
		List<PropertyFilter> propertyFilters) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_buildQueryBuilder(assetType, keyword, propertyFilters));
	}

	@Override
	public List<Asset> searchAssets(
		String assetType, String keyword, List<PropertyFilter> propertyFilters,
		Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_buildQueryBuilder(
								assetType, keyword, propertyFilters));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	protected String getCollectionName() {
		return "assets";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private void _addBoolQueryBuilderPropertyFilters(
		BoolQueryBuilder boolQueryBuilder,
		List<PropertyFilter> propertyFilters) {

		if ((propertyFilters == null) || propertyFilters.isEmpty()) {
			return;
		}

		for (PropertyFilter propertyFilter : propertyFilters) {
			QueryBuilder propertyQueryBuilder = _buildPropertyQueryBuilder(
				propertyFilter);

			boolQueryBuilder.filter(propertyQueryBuilder);
		}
	}

	private QueryBuilder _buildPropertyQueryBuilder(
		PropertyFilter propertyFilter) {

		QueryBuilder queryBuilder = _buildPropertyQueryBuilder(
			propertyFilter.getOperator(), propertyFilter.getPropertyName(),
			propertyFilter.getPropertyValue());

		if (propertyFilter.isNegate()) {
			queryBuilder = BoolQueryBuilderUtil.mustNot(queryBuilder);
		}

		List<PropertyFilter> propertyFilters =
			propertyFilter.getPropertyFilters();

		if (propertyFilters.isEmpty()) {
			return queryBuilder;
		}

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			queryBuilder);

		_addBoolQueryBuilderPropertyFilters(
			boolQueryBuilder, propertyFilter.getPropertyFilters());

		return boolQueryBuilder;
	}

	private QueryBuilder _buildPropertyQueryBuilder(
		String operator, String propertyName, String propertyValue) {

		if (Objects.equals(propertyName, "title")) {
			propertyName = "name";
		}

		if (Objects.equals(operator, "~")) {
			return QueryBuilders.regexpQuery(propertyName, propertyValue);
		}

		return QueryBuilders.termQuery(propertyName, propertyValue);
	}

	private QueryBuilder _buildQueryBuilder(
		String assetType, String keywords,
		List<PropertyFilter> propertyFilters) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("assetType", assetType));

		if (StringUtils.isNotBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.wildcardQuery(
						"canonicalUrl", "*" + keywords + "*")
				).should(
					QueryBuilders.queryStringQuery(
						String.format(
							"name:*%1$s* OR description:*%1$s*",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.multiMatchQuery(
						keywords, "name", "description"
					).fuzziness(
						Fuzziness.AUTO
					)
				).should(
					QueryBuilders.wildcardQuery("url", "*" + keywords + "*")
				));
		}

		_addBoolQueryBuilderPropertyFilters(boolQueryBuilder, propertyFilters);

		return boolQueryBuilder;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}