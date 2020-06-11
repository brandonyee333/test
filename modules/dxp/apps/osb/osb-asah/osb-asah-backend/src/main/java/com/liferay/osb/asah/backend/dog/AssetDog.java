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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.PageAsset;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class AssetDog {

	public ResultBag<PageAsset> getPageAssetResultBag(
		String keywords, String propertyFilterString, int size,
		Map<String, String> sort, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"assets", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				SortBuilderUtil.fieldSort(sort),
				_buildQueryBuilder("Page", keywords, propertyFilterString),
				size, start));

		return DogUtil.createResultBag(PageAsset.class, searchHits);
	}

	private QueryBuilder _buildQueryBuilder(String propertyFilterString) {
		List<String> tokens = _getFilterTokens(propertyFilterString);

		if (tokens.size() != 3) {
			throw new IllegalArgumentException(
				"Invalid filter " + propertyFilterString);
		}

		if (Objects.equals(tokens.get(1), "~")) {
			return QueryBuilders.regexpQuery(tokens.get(0), tokens.get(2));
		}

		return QueryBuilders.termQuery(tokens.get(0), tokens.get(2));
	}

	private QueryBuilder _buildQueryBuilder(
		String assetType, String keywords, String propertyFilterString) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("assetType", assetType));

		if (StringUtils.isNotBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
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

		if (StringUtils.isNotBlank(propertyFilterString)) {
			boolQueryBuilder.filter(_buildQueryBuilder(propertyFilterString));
		}

		return boolQueryBuilder;
	}

	private List<String> _getFilterTokens(String eventContextFilterString) {
		List<String> tokens = new ArrayList<>();

		for (String token : eventContextFilterString.split(" ")) {
			if (!StringUtils.isBlank(token)) {
				tokens.add(token);
			}
		}

		return tokens;
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}