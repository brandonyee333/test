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

package com.liferay.osb.asah.upgrade.v4_0_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.repository.PreferenceRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;
import com.liferay.osb.asah.upgrade.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.json.JSONArrayIterator;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Leilany Ulisses
 */
@Component
public class PreferenceMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Migrating preferences");
		}

		JSONArrayIterator.of(
			"preferences", _faroInfoElasticsearchInvoker,
			jsonObject -> {
				Preference preference = _objectMapper.convertValue(
					jsonObject, Preference.class);

				preference.setIsNew(Boolean.TRUE);

				_preferenceRepository.save(preference);

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("id", _getPreferenceIds(true)))
		).iterate();
	}

	private List<String> _getPreferenceIds(boolean retry) {
		try {
			return _namedParameterJdbcTemplate.queryForList(
				"SELECT id FROM preference", Collections.emptyMap(),
				String.class);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Select preference IDs query failed", exception);
			}

			if (retry) {
				_log.error("Retrying...");

				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return _getPreferenceIds(false);
			}

			return Collections.emptyList();
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private static final Log _log = LogFactory.getLog(
		PreferenceMigrationUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private PreferenceRepository _preferenceRepository;

}