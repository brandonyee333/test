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

import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.repository.RunLogRepository;
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
public class RunLogMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_upgradeCollection(_dxpRawElasticsearchInvoker, "osbasahdxpraw");
		_upgradeCollection(_faroInfoElasticsearchInvoker, "osbasahfaroinfo");
	}

	private List<Long> _getRunLogIds(boolean retry) {
		try {
			return _namedParameterJdbcTemplate.queryForList(
				"SELECT id FROM runlog", Collections.emptyMap(), Long.class);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug("Select runlog IDs query failed", exception);
			}

			if (retry) {
				_log.error("Retrying...");

				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException interruptedException) {
					_log.error(interruptedException, interruptedException);
				}

				return _getRunLogIds(false);
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
			ElasticsearchInvoker elasticsearchInvoker, String sourceName)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(String.format("Migrating %s", sourceName));
		}

		JSONArrayIterator.of(
			"run-logs", elasticsearchInvoker,
			jsonObject -> {
				RunLog runLog = _objectMapper.convertValue(
					jsonObject, RunLog.class);

				runLog.setIsNew(Boolean.TRUE);

				_runLogRepository.save(runLog);

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("id", _getRunLogIds(true)))
		).iterate();
	}

	private static final Log _log = LogFactory.getLog(
		RunLogMigrationUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RunLogRepository _runLogRepository;

}