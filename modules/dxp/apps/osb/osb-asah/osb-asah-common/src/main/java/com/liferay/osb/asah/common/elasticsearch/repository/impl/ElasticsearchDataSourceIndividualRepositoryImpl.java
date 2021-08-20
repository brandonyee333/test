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
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceIndividualRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@Repository
public class ElasticsearchDataSourceIndividualRepositoryImpl
	implements DataSourceIndividualRepository {

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(DataSourceIndividual dataSourceIndividual) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(
		Iterable<? extends DataSourceIndividual> dataSourceIndividuals) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<DataSourceIndividual> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<DataSourceIndividual> findAllById(Iterable<Long> ids) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<DataSourceIndividual> findById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<DataSourceIndividual> findByIndividualId(Long individualId) {
		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.get(
			"individuals", String.valueOf(individualId));

		Map<String, DataSourceIndividual> dataSourceIndividualsMap =
			new HashMap<>();

		JSONArray dataSourceAccountPKsJSONArray =
			individualJSONObject.optJSONArray("dataSourceAccountPKs");

		if (dataSourceAccountPKsJSONArray == null) {
			dataSourceAccountPKsJSONArray = new JSONArray();
		}

		for (int i = 0; i < dataSourceAccountPKsJSONArray.length(); i++) {
			JSONObject jsonObject = dataSourceAccountPKsJSONArray.getJSONObject(
				i);

			String dataSourceId = jsonObject.getString("dataSourceId");

			DataSourceIndividual dataSourceIndividual =
				new DataSourceIndividual();

			dataSourceIndividual.setAccountPKs(
				JSONUtil.toStringSet(jsonObject.optJSONArray("accountPKs")));
			dataSourceIndividual.setDataSourceId(Long.valueOf(dataSourceId));
			dataSourceIndividual.setIndividualId(individualId);

			dataSourceIndividualsMap.put(dataSourceId, dataSourceIndividual);
		}

		JSONArray dataSourceIndividualPKsJSONArray =
			individualJSONObject.optJSONArray("dataSourceIndividualPKs");

		if (dataSourceIndividualPKsJSONArray == null) {
			dataSourceIndividualPKsJSONArray = new JSONArray();
		}

		for (int i = 0; i < dataSourceIndividualPKsJSONArray.length(); i++) {
			JSONObject jsonObject =
				dataSourceIndividualPKsJSONArray.getJSONObject(i);

			String dataSourceId = jsonObject.getString("dataSourceId");

			DataSourceIndividual dataSourceIndividual =
				new DataSourceIndividual();

			if (dataSourceIndividualsMap.containsKey(dataSourceId)) {
				dataSourceIndividual = dataSourceIndividualsMap.get(
					dataSourceId);
			}
			else {
				dataSourceIndividual.setDataSourceId(
					Long.valueOf(dataSourceId));
				dataSourceIndividual.setIndividualId(individualId);
			}

			dataSourceIndividual.setIndividualPKs(
				JSONUtil.toStringSet(jsonObject.optJSONArray("individualPKs")));

			dataSourceIndividualsMap.put(dataSourceId, dataSourceIndividual);
		}

		return new ArrayList<>(dataSourceIndividualsMap.values());
	}

	@Override
	public List<DataSourceIndividual> findByIndividualIdIn(
		Collection<Long> individualIds) {

		List<DataSourceIndividual> dataSourceIndividuals = new ArrayList<>();

		for (Long individualId : individualIds) {
			dataSourceIndividuals.addAll(findByIndividualId(individualId));
		}

		return dataSourceIndividuals;
	}

	@Override
	public <S extends DataSourceIndividual> S save(S dataSourceIndividual) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends DataSourceIndividual> Iterable<S> saveAll(
		Iterable<S> dataSourceIndividuals) {

		throw new UnsupportedOperationException();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}