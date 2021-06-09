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
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.repository.SalesforceAuditEventRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchSalesforceAuditEventRepositoryImpl
	extends BaseElasticsearchRepository<SalesforceAuditEvent, Long>
	implements SalesforceAuditEventRepository {

	@Override
	public long countByDataSourceIdAndEntityTypeNameIn(
		Long dataSourceId, List<String> entityTypeNames) {

		return _salesforceRawElasticsearchInvoker.count(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"osbAsahDataSourceId", String.valueOf(dataSourceId))
			).filter(
				QueryBuilders.termsQuery("typeName", entityTypeNames)
			));
	}

	@Override
	public List<SalesforceAuditEvent> findByDataSourceIdAndEntityTypeName(
		Long dataSourceId, String entityTypeName, Pageable pageable) {

		return toList(
			new JSONArray(
				_salesforceRawElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery(
									"osbAsahDataSourceId",
									String.valueOf(dataSourceId))
							).filter(
								QueryBuilders.termQuery(
									"typeName", entityTypeName)
							));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	protected String getCollectionName() {
		return "audit-events";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _salesforceRawElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}