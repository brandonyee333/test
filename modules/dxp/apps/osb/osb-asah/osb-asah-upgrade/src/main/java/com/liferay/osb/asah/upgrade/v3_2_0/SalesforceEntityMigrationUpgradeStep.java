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

package com.liferay.osb.asah.upgrade.v3_2_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.repository.SalesforceEntityRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class SalesforceEntityMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		_upgradeCollection(SalesforceEntity.Type.ACCOUNT);
		_upgradeCollection(SalesforceEntity.Type.CONTACT);
		_upgradeCollection(SalesforceEntity.Type.INDIVIDUAL);
		_upgradeCollection(SalesforceEntity.Type.LEAD);
	}

	private List<Long> _getDataSourceIds(SalesforceEntity.Type type) {
		List<Long> ids = new ArrayList<>();

		SearchResponse searchResponse =
			_salesforceRawElasticsearchInvoker.search(
				type.getValue(),
				searchSourceBuilder -> {
					searchSourceBuilder.aggregation(
						AggregationBuilders.terms(
							"dataSourceIds"
						).field(
							"dataSourceId"
						).size(
							Integer.MAX_VALUE
						));
					searchSourceBuilder.size(0);
				});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return ids;
		}

		Terms terms = aggregations.get("dataSourceIds");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			ids.add(Long.valueOf(bucket.getKeyAsString()));
		}

		return ids;
	}

	private List<String> _getSalesforceEntityIds(
		Long dataSourceId, boolean retry, SalesforceEntity.Type type) {

		try {
			return _namedParameterJdbcTemplate.queryForList(
				"SELECT id FROM salesforceentity WHERE datasourceid = " +
					":dataSourceId AND type = :type",
				new HashMap<String, Object>() {
					{
						put("dataSourceId", dataSourceId);
						put("type", type.getValue());
					}
				},
				String.class);
		}
		catch (Exception exception) {
			_log.error("Select Sales IDs query failed", exception);

			if (retry) {
				_log.error("Retrying...");

				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return _getSalesforceEntityIds(dataSourceId, false, type);
			}

			return Collections.emptyList();
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _migrateDatasource(
		Long dataSourceId, SalesforceEntity.Type type) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "dataSourceId", String.valueOf(dataSourceId));

		BoolQueryBuilderUtil.mustNot(
			QueryBuilders.termsQuery(
				"id", _getSalesforceEntityIds(dataSourceId, true, type)));

		JSONArray objectJSONArray = _salesforceRawElasticsearchInvoker.get(
			type.getValue(),
			Arrays.asList(SortBuilderUtil.fieldSort("id", SortOrder.ASC)),
			boolQueryBuilder);

		objectJSONArray.forEach(
			object -> {
				SalesforceEntity salesforceEntity = _objectMapper.convertValue(
					object, SalesforceEntity.class);

				salesforceEntity.setIsNew(Boolean.TRUE);
				salesforceEntity.setType(type);

				_salesforceEntityRepository.save(salesforceEntity);
			});
	}

	private void _upgradeCollection(SalesforceEntity.Type type) {
		List<Long> dataSourceIds = _getDataSourceIds(type);

		for (Long dataSourceId : dataSourceIds) {
			_migrateDatasource(dataSourceId, type);
		}
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceEntityMigrationUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SalesforceEntityRepository _salesforceEntityRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}