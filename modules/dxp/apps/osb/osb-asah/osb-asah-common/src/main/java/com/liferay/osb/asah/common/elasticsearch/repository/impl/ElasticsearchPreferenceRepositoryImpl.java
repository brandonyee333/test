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
import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.PreferenceRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@Repository
public class ElasticsearchPreferenceRepositoryImpl
	extends BaseElasticsearchRepository<Preference, Long>
	implements PreferenceRepository {

	@Override
	public void delete(Preference preference) {
		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(), String.valueOf(preference.getKey()));
	}

	@Override
	public void deleteAll(Iterable<? extends Preference> preferences) {
		Stream<? extends Preference> stream = StreamSupport.stream(
			preferences.spliterator(), false);

		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				stream.map(
					Preference::getKey
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, getCollectionName());
	}

	@Override
	public void deleteById(Long preferenceId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsById(Long preferenceId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Preference> findAllById(Iterable<Long> preferenceIds) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Optional<Preference> findById(Long preferenceId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Preference findByKey(String key) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(getCollectionName(), key)
		).map(
			this::toEntity
		).orElse(
			null
		);
	}

	@Override
	protected String getCollectionName() {
		return "preferences";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@Override
	protected Preference toEntity(JSONObject jsonObject) {
		return new Preference(
			jsonObject.getString("id"), jsonObject.getString("value"));
	}

	@Override
	protected JSONObject toJSONObject(Preference preference) {
		return JSONUtil.put(
			"id", preference.getKey()
		).put(
			"value", preference.getValue()
		);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}