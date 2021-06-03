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
import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.repository.DataSourceFieldMappingRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchDataSourceFieldMappingRepositoryImpl
	implements DataSourceFieldMappingRepository {

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(DataSourceFieldMapping dataSourceFieldMapping) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(
		Iterable<? extends DataSourceFieldMapping> dataSourceFieldMappings) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Long dataSourceFieldMappingId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsById(Long dataSourceFieldMappingIds) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<DataSourceFieldMapping> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<DataSourceFieldMapping> findAllById(
		Iterable<Long> dataSourceFieldMappingIds) {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<DataSourceFieldMapping> findByDataSourceId(Long dataSourceId) {
		List<DataSourceFieldMapping> dataSourceFieldMappings =
			new ArrayList<>();

		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"field-mappings",
			QueryBuilders.existsQuery("dataSourceFieldNames." + dataSourceId));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject fieldMappingJSONObject = jsonArray.getJSONObject(i);

			JSONObject dataSourceFieldNamesJSONObject =
				fieldMappingJSONObject.optJSONObject("dataSourceFieldNames");

			for (String key : dataSourceFieldNamesJSONObject.keySet()) {
				dataSourceFieldMappings.add(
					new DataSourceFieldMapping(
						Long.valueOf(key), fieldMappingJSONObject.getLong("id"),
						dataSourceFieldNamesJSONObject.getString(key)));
			}
		}

		return dataSourceFieldMappings;
	}

	@Override
	public List<DataSourceFieldMapping> findByFieldMappingId(
		Long fieldMappingId) {

		JSONObject fieldMappingJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"field-mappings", String.valueOf(fieldMappingId));

		if (fieldMappingJSONObject == null) {
			return Collections.emptyList();
		}

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.optJSONObject("dataSourceFieldNames");

		if (dataSourceFieldNamesJSONObject == null) {
			return Collections.emptyList();
		}

		List<DataSourceFieldMapping> dataSourceFieldMappings =
			new ArrayList<>();

		for (String key : dataSourceFieldNamesJSONObject.keySet()) {
			dataSourceFieldMappings.add(
				new DataSourceFieldMapping(
					Long.valueOf(key), fieldMappingJSONObject.getLong("id"),
					dataSourceFieldNamesJSONObject.getString(key)));
		}

		return dataSourceFieldMappings;
	}

	@Override
	public Optional<DataSourceFieldMapping> findById(
		Long dataSourceFieldMappingId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends DataSourceFieldMapping> S save(S entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends DataSourceFieldMapping> Iterable<S> saveAll(
		Iterable<S> entities) {

		throw new UnsupportedOperationException();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}