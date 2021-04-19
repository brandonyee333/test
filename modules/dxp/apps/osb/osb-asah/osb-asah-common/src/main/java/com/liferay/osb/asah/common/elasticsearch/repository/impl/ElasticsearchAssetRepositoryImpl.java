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

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
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
	public long countByAssetType(String assetType) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("assetType", assetType)));
	}

	@Override
	public long countByAssetTypeAndFilterStringAndKeyword(
		@Nullable String assetType, @Nullable String filterString,
		@Nullable String keyword) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_buildQueryBuilder(assetType, filterString, keyword));
	}

	@Override
	public long countByFilterString(@Nullable String filterString) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), _buildQueryBuilder(filterString));
	}

	@Override
	public List<Asset> findByAssetType(String assetType, Pageable pageable) {
		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"assetType", assetType)));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<Asset> findByAssetTypeAndFilterStringAndKeyword(
		@Nullable String assetType, @Nullable String filterString,
		@Nullable String keyword, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_buildQueryBuilder(
								assetType, filterString, keyword));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public Optional<Asset> findByDataSourceAssetPKAndDataSourceId(
		String dataSourceAssetPK, Long dataSourceId) {

		JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dataSourceAssetPK", dataSourceAssetPK)
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			));

		return Optional.ofNullable(
			jsonObject
		).map(
			this::toEntity
		);
	}

	@Override
	public List<Asset> findByFilterString(
		@Nullable String filterString, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_buildQueryBuilder(filterString));

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

	private QueryBuilder _buildQueryBuilder(String filterString) {
		if (StringUtils.isEmpty(filterString)) {
			return QueryBuilders.matchAllQuery();
		}

		return FilterStringToQueryBuilderConverter.convert(
			filterString, _assetFilterStringConverterHelper);
	}

	private QueryBuilder _buildQueryBuilder(
		String assetType, String filterString, String keywords) {

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

		if (StringUtils.isNotEmpty(filterString)) {
			boolQueryBuilder.filter(
				FilterStringToQueryBuilderConverter.convert(
					filterString, _assetFilterStringConverterHelper));
		}

		return boolQueryBuilder;
	}

	private final AssetFilterStringConverterHelper
		_assetFilterStringConverterHelper =
			new AssetFilterStringConverterHelper();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private static class AssetFilterStringConverterHelper
		extends DefaultFilterStringConverterHelper {

		@Override
		public QueryBuilder getCustomFunctionQueryBuilder(
			List<String> arguments, String customFunctionName,
			boolean negated) {

			if (!customFunctionName.equalsIgnoreCase("similarTo")) {
				return null;
			}

			Map<String, String> fieldNames = getFieldNameConversionMap();

			String fieldName = toFieldName(
				fieldNames.getOrDefault(arguments.get(0), arguments.get(0)));

			QueryBuilder queryBuilder = QueryBuilders.regexpQuery(
				fieldName,
				StringUtil.unquoteAndDecodeInnerQuotes(arguments.get(1)));

			if (negated) {
				return BoolQueryBuilderUtil.mustNot(queryBuilder);
			}

			return queryBuilder;
		}

		@Override
		public Map<String, String> getFieldNameConversionMap() {
			return new HashMap<String, String>() {
				{
					put("title", "name");
				}
			};
		}

	}

}