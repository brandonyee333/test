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
import com.liferay.osb.asah.common.model.Suppression;
import com.liferay.osb.asah.common.repository.SuppressionRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
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
public class ElasticsearchSuppressionRepositoryImpl
	extends BaseElasticsearchRepository<Suppression, Long>
	implements SuppressionRepository {

	@Override
	public long countByEmailAddressContainingIgnoreCase(String emailAddress) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), _buildQueryBuilder(emailAddress));
	}

	@Override
	public List<Suppression> findAll(Pageable pageable) {
		return findByEmailAddressContainingIgnoreCase(null, pageable);
	}

	@Override
	public List<Suppression> findByEmailAddressContainingIgnoreCase(
		String emailAddress, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_buildQueryBuilder(emailAddress));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	protected String getCollectionName() {
		return "suppressions";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private QueryBuilder _buildQueryBuilder(String emailAddress) {
		if (StringUtils.isBlank(emailAddress)) {
			return QueryBuilders.matchAllQuery();
		}

		return BoolQueryBuilderUtil.filter(
			BoolQueryBuilderUtil.should(
				QueryBuilders.queryStringQuery(
					String.format(
						"%s:*%s*", "emailAddress",
						QueryUtil.escapeKeywords(emailAddress)))
			).should(
				QueryBuilders.matchQuery(
					"emailAddress", emailAddress
				).fuzziness(
					Fuzziness.AUTO
				)
			));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}