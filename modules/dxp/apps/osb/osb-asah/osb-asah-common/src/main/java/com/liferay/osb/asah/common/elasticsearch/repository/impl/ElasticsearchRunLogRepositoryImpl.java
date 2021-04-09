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
import com.liferay.osb.asah.common.model.RunLog;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.util.WeDeployServiceThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchRunLogRepositoryImpl
	extends BaseElasticsearchRepository<RunLog, Long>
	implements RunLogRepository {

	@Override
	public Optional<RunLog>
		findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
			Optional<Long> dataSourceIdOptional, String naniteClassName,
			Optional<String> statusOptional) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("naniteClassName", naniteClassName));

		if (statusOptional.isPresent()) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("status", statusOptional.get()));
		}

		if (dataSourceIdOptional.isPresent()) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"dataSourceId", dataSourceIdOptional.get()));
		}
		else {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("dataSourceId")));
		}

		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		JSONObject jsonObject = elasticsearchInvoker.fetch(
			"run-logs", boolQueryBuilder,
			SortBuilderUtil.fieldSort("dateLogged", SortOrder.DESC), null,
			null);

		return Optional.ofNullable(
			jsonObject
		).map(
			this::toEntity
		);
	}

	@Override
	protected String getCollectionName() {
		return "run-logs";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _resolveElasticsearchInvoker();
	}

	private ElasticsearchInvoker _resolveElasticsearchInvoker() {
		WeDeployDataService weDeployDataService =
			WeDeployServiceThreadLocal.getWeDeployDataService();

		if (weDeployDataService == null) {
			throw new IllegalStateException("WeDeployDataService is null");
		}

		if (weDeployDataService == WeDeployDataService.OSB_ASAH_DXP_RAW) {
			return _dxpRawElasticsearchInvoker;
		}
		else if (weDeployDataService ==
					WeDeployDataService.OSB_ASAH_FARO_INFO) {

			return _faroInfoElasticsearchInvoker;
		}
		else if (weDeployDataService ==
					WeDeployDataService.OSB_ASAH_SALESFORCE_RAW) {

			return _salesforceRawElasticsearchInvoker;
		}
		else {
			throw new IllegalStateException(
				"Unexpected WeDeployDataService value " + weDeployDataService);
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}