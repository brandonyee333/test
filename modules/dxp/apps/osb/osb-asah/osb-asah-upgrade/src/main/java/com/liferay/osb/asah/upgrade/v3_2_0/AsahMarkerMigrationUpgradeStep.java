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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class AsahMarkerMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		_migrateCollection(
			_cerebroInfoElasticsearchInvoker, "osbasahcerebroinfo");
		_migrateCollection(_dxpRawElasticsearchInvoker, "osbasahdxpraw");
		_migrateCollection(_faroInfoElasticsearchInvoker, "osbasahfaroinfo");
		_migrateCollection(
			_salesforceRawElasticsearchInvoker, "osbasahsalesforceraw");
	}

	private List<String> _getMigratedIds(boolean retry) {
		String query = "SELECT id FROM asahmarker";

		try {
			List<String> ids = _namedParameterJdbcTemplate.queryForList(
				query, Collections.emptyMap(), String.class);

			if (!ids.isEmpty() && _log.isWarnEnabled()) {
				_log.warn("Select latest ID query returns more than one row");
			}

			return ids;
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Select latest ID query failed", exception);
			}

			if (retry) {
				_log.error("Retrying...");

				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return _getMigratedIds(false);
			}

			return Collections.emptyList();
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _migrateCollection(
		ElasticsearchInvoker elasticsearchInvoker, String sourceName) {

		List<String> migratedIds = _getMigratedIds(true);

		if (_log.isDebugEnabled()) {
			_log.debug(String.format("Migrating %s", sourceName));
		}

		JSONArray objectJSONArray = elasticsearchInvoker.get(
			"OSBAsahMarkers",
			Collections.singletonList(
				SortBuilderUtil.fieldSort("id", SortOrder.ASC)),
			QueryBuilders.boolQuery(
			).mustNot(
				QueryBuilders.termsQuery("id", migratedIds)
			));

		objectJSONArray.forEach(
			object -> {
				AsahMarker asahMarker = _objectMapper.convertValue(
					object, AsahMarker.class);

				asahMarker.setIsNew(Boolean.TRUE);

				_asahMarkerRepository.save(asahMarker);
			});
	}

	private static final Log _log = LogFactory.getLog(
		AsahMarkerMigrationUpgradeStep.class);

	@Autowired
	private AsahMarkerRepository _asahMarkerRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DataSource _dataSource;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

	@Autowired
	private ObjectMapper _objectMapper;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}