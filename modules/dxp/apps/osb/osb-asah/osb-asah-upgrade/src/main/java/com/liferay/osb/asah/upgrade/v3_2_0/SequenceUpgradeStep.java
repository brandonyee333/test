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

import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class SequenceUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_syncSequenceStart(_getLatestId(true, "channel"), "channel_id_seq");
		_syncSequenceStart(
			_getLatestId(true, "datasource"), "datasource_id_seq");
	}

	private Long _getLatestId(boolean retry, String tableName) {
		try {
			return _namedParameterJdbcTemplate.queryForObject(
				"SELECT coalesce(max(id),1) FROM " + tableName,
				Collections.emptyMap(), Long.class);
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

				return _getLatestId(false, tableName);
			}

			return 0L;
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _syncSequenceStart(Long currentValue, String sequenceName) {
		_namedParameterJdbcTemplate.queryForObject(
			"SELECT setval(:sequenceName, :currentValue, true)",
			new HashMap<String, Object>() {
				{
					put("currentValue", currentValue);
					put("sequenceName", sequenceName);
				}
			},
			Long.class);
	}

	private static final Log _log = LogFactory.getLog(
		SequenceUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

}