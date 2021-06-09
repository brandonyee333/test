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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.BlockedKeyword;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

/**
 * @author André Miranda
 */
@Repository
public class ElasticsearchBlockedKeywordRepositoryImpl
	extends BaseElasticsearchRepository<BlockedKeyword, Long>
	implements BlockedKeywordRepository {

	@Override
	public long countByKeywordContainingIgnoreCase(String keyword) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			QueryUtil.buildSearchQueryBuilder("keyword.raw", keyword));
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
	public List<BlockedKeyword> findAll(Pageable pageable) {
		return findByKeywordContainingIgnoreCase(null, pageable);
	}

	@Override
	public List<BlockedKeyword> findByKeywordContainingIgnoreCase(
		String keyword, Pageable pageable) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (keyword != null) {
			boolQueryBuilder.filter(
				QueryUtil.buildSearchQueryBuilder("keyword.raw", keyword));
		}

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.from(
							pageable.getPageNumber() * pageable.getPageSize());
						searchSourceBuilder.query(boolQueryBuilder);
						searchSourceBuilder.size(pageable.getPageSize());

						Stream.of(
							pageable.getSort()
						).flatMap(
							Sort::stream
						).findFirst(
						).ifPresent(
							sort -> {
								String property = sort.getProperty();

								if (Objects.equals(property, "keyword")) {
									property = "keyword.raw";
								}

								SortOrder sortOrder = SortOrder.ASC;

								if (sort.isDescending()) {
									sortOrder = SortOrder.DESC;
								}

								searchSourceBuilder.sort(
									SortBuilderUtil.fieldSort(
										property, sortOrder));
							}
						);
					})));
	}

	@Override
	public List<BlockedKeyword> findByKeywordIn(Set<String> keywords) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termsQuery("keyword.raw", keywords)));
	}

	@Override
	protected String getCollectionName() {
		return "blocked-keywords";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}