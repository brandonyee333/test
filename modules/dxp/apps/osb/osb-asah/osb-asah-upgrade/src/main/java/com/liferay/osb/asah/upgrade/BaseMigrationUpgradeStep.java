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

package com.liferay.osb.asah.upgrade;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public abstract class BaseMigrationUpgradeStep implements UpgradeStep {

	public final boolean isSequenceSync() {
		if (_getSequenceNextValue() > _getLatestId(true)) {
			return true;
		}

		return false;
	}

	@Override
	public void upgrade(String version) throws Exception {
		JSONArrayIterator.of(
			getElasticsearchCollectionName(), _faroInfoElasticsearchInvoker,
			jsonObject -> {
				Consumer<Object> consumer = getConsumer();

				consumer.accept(jsonObject);

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.rangeQuery(
				"id"
			).gt(
				_getLatestId(true)
			)
		).iterate();

		_syncSequenceStart();
	}

	protected abstract Consumer<Object> getConsumer();

	protected abstract String getElasticsearchCollectionName();

	protected abstract String getSelectLatestIdSQL();

	protected abstract String getSequenceName();

	private Long _getLatestId(boolean retry) {
		try {
			List<Long> latestIds = _namedParameterJdbcTemplate.queryForList(
				getSelectLatestIdSQL(), Collections.emptyMap(), Long.class);

			if ((latestIds == null) || latestIds.isEmpty()) {
				return 0L;
			}

			if (_log.isWarnEnabled()) {
				_log.warn("Select latest ID query returns more than one row");
			}

			return latestIds.get(0);
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

				return _getLatestId(false);
			}

			return 0L;
		}
	}

	private Long _getSequenceNextValue() {
		return _namedParameterJdbcTemplate.queryForObject(
			"SELECT nextval(:sequenceName) nextValue",
			Collections.singletonMap("sequenceName", getSequenceName()),
			Long.class);
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _syncSequenceStart() {
		Long currentValue = _getLatestId(true) + 1;

		_namedParameterJdbcTemplate.queryForObject(
			"SELECT setval(:sequenceName, :currentValue, true)",
			new HashMap<String, Object>() {
				{
					put("currentValue", currentValue);
					put("sequenceName", getSequenceName());
				}
			},
			Long.class);
	}

	private static final Log _log = LogFactory.getLog(
		BaseMigrationUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

}