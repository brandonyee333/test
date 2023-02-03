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

import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;
import com.liferay.osb.asah.upgrade.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.elasticsearch.SortBuilderUtil;

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
		_upgradeCollection(
			_cerebroInfoElasticsearchInvoker, "osbasahcerebroinfo");
		_upgradeCollection(_dxpRawElasticsearchInvoker, "osbasahdxpraw");
		_upgradeCollection(_faroInfoElasticsearchInvoker, "osbasahfaroinfo");
	}

	private List<String> _getAsahMarkerIds(boolean retry) {
		try {
			return _namedParameterJdbcTemplate.queryForList(
				"SELECT id FROM asahmarker", Collections.emptyMap(),
				String.class);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Select Asah Marker IDs query failed", exception);
			}

			if (retry) {
				_log.error("Retrying...");

				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return _getAsahMarkerIds(false);
			}

			return Collections.emptyList();
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _upgradeCollection(
		ElasticsearchInvoker elasticsearchInvoker, String sourceName) {

		if (_log.isDebugEnabled()) {
			_log.debug(String.format("Migrating %s", sourceName));
		}

		JSONArray objectJSONArray = elasticsearchInvoker.get(
			"OSBAsahMarkers",
			Collections.singletonList(
				SortBuilderUtil.fieldSort("id", SortOrder.ASC)),
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("id", _getAsahMarkerIds(true))));

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

}