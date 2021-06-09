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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.entity.DataControlTask;
import com.liferay.osb.asah.common.repository.DataControlTaskRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchDataControlTaskRepositoryImpl
	extends BaseElasticsearchRepository<DataControlTask, Long>
	implements DataControlTaskRepository {

	@Override
	public long countDataControlTasks(
		Long batchId, String emailAddress, Date startCreateDate,
		List<String> statuses, List<String> types) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_buildQueryBuilder(
				batchId, emailAddress, startCreateDate, statuses, types));
	}

	@Override
	public List<DataControlTask> searchDataControlTasks(
		Long batchId, String emailAddress, Date startCreateDate,
		List<String> statuses, List<String> types, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_buildQueryBuilder(
								batchId, emailAddress, startCreateDate,
								statuses, types));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	protected String getCollectionName() {
		return "data-control-tasks";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private QueryBuilder _buildQueryBuilder(
		Long batchId, String emailAddress, Date startCreateDate,
		List<String> statuses, List<String> types) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		if (batchId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termsQuery("batchId", batchId.toString()));
		}

		if (!StringUtils.isBlank(emailAddress)) {
			boolQueryBuilder.filter(
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

		if (startCreateDate != null) {
			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"createDate"
				).gte(
					DateUtil.toUTCString(startCreateDate)
				));
		}

		if ((statuses != null) && !statuses.isEmpty()) {
			BoolQueryBuilder statusBoolQueryBuilder = QueryBuilders.boolQuery();

			for (String status : statuses) {
				statusBoolQueryBuilder.should(
					QueryBuilders.termsQuery("status", status));
			}

			boolQueryBuilder.filter(statusBoolQueryBuilder);
		}

		if ((types != null) && !types.isEmpty()) {
			BoolQueryBuilder typesBoolQueryBuilder = QueryBuilders.boolQuery();

			for (String type : types) {
				typesBoolQueryBuilder.should(
					QueryBuilders.termsQuery("type", type));
			}

			boolQueryBuilder.filter(typesBoolQueryBuilder);
		}

		return boolQueryBuilder;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}