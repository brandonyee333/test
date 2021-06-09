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
import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.repository.CSVIndividualRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchCSVIndividualRepositoryImpl
	extends BaseElasticsearchRepository<CSVIndividual, Long>
	implements CSVIndividualRepository {

	@Override
	public long countByDataSourceId(Long dataSourceId) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId)));
	}

	@Override
	public void deleteByDataSourceId(Long dataSourceId) {
		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId)),
			true, getCollectionName());
	}

	@Override
	public void deleteByDataSourceIdAndDataSourceIndividualPKIn(
		Long dataSourceId, List<String> dataSourceIndividualPKs) {

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		for (String dataSourceIndividualPK : dataSourceIndividualPKs) {
			boolQueryBuilder.should(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPK", dataSourceIndividualPK)
				));
		}

		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(), boolQueryBuilder);
	}

	@Override
	public List<CSVIndividual> findByDataSourceId(
		Long dataSourceId, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.termQuery(
								"dataSourceId", String.valueOf(dataSourceId)));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<CSVIndividual> findByDataSourceIdAndFieldKeyEquals(
		Long dataSourceId, String fieldKey, String fieldValue) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termQuery("fields." + fieldKey, fieldValue)
				)));
	}

	@Override
	protected String getCollectionName() {
		return "csv-individuals";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}