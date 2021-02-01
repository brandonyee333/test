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
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Preference;
import com.liferay.osb.asah.common.repository.PreferenceRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchPreferenceRepositoryImpl
	implements PreferenceRepository {

	@Override
	public long count() {
		return _faroInfoElasticsearchInvoker.count(
			"preferences", QueryBuilders.matchAllQuery());
	}

	@Override
	public void delete(Preference preference) {
		_faroInfoElasticsearchInvoker.delete(
			"preferences", String.valueOf(preference.getKey()));
	}

	@Override
	public void deleteAll() {
		_faroInfoElasticsearchInvoker.delete(
			"preferences", QueryBuilders.matchAllQuery());
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
			true, "preferences");
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
	public List<Preference> findAll() {
		return _toPreferences(_faroInfoElasticsearchInvoker.get("preferences"));
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
			_faroInfoElasticsearchInvoker.fetch("preferences", key)
		).map(
			this::_toPreference
		).orElse(
			null
		);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Preference save(Preference preference) {
		return _toPreference(
			_faroInfoElasticsearchInvoker.add(
				"preferences",
				JSONUtil.put(
					"id", preference.getKey()
				).put(
					"value", preference.getValue()
				)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends Preference> Iterable<S> saveAll(Iterable<S> preferences) {
		Stream<S> stream = StreamSupport.stream(
			preferences.spliterator(), false);

		return (Iterable<S>)stream.map(
			this::save
		).collect(
			Collectors.toList()
		);
	}

	private Preference _toPreference(JSONObject preferenceJSONObject) {
		return new Preference(
			preferenceJSONObject.getString("id"),
			preferenceJSONObject.getString("value"));
	}

	private List<Preference> _toPreferences(JSONArray preferencesJSONArray) {
		List<Preference> preferences = new ArrayList<>();

		for (int i = 0; i < preferencesJSONArray.length(); i++) {
			try {
				preferences.add(
					_toPreference(preferencesJSONArray.getJSONObject(i)));
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}

		return preferences;
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchPreferenceRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}