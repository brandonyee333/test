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

package com.liferay.osb.asah.upgrade.v3_2_0.test;

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.SequenceUpgradeStep;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @author Robson Pastor
 */
public class SequenceUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() {
		ProjectIdThreadLocal.setProjectId("test");

		Channel channel = new Channel();

		channel.setId(2432354534L);
		channel.setIsNew(Boolean.TRUE);

		_channelRepository.save(channel);

		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setId(32132132L);
		dataSource.setIsNew(Boolean.TRUE);
		dataSource.setProviderType("LIFERAY");

		_dataSourceRepository.save(dataSource);
	}

	@Test
	public void testUpgrade() throws Exception {
		_sequenceUpgradeStep.upgrade("");

		Assertions.assertTrue(_isSequenceSync("channel_id_seq", "channel"));
		Assertions.assertTrue(
			_isSequenceSync("datasource_id_seq", "datasource"));
	}

	private Long _getSequenceNextValue(String sequenceName) {
		return _namedParameterJdbcTemplate.queryForObject(
			"SELECT nextval(:sequenceName) nextValue",
			Collections.singletonMap("sequenceName", sequenceName), Long.class);
	}

	private Long _getTableMaxId(String tableName) {
		return _namedParameterJdbcTemplate.queryForObject(
			"SELECT COALESCE(MAX(id), 1) FROM " + tableName,
			Collections.emptyMap(), Long.class);
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private boolean _isSequenceSync(String sequenceName, String tableName) {
		if (_getSequenceNextValue(sequenceName) > _getTableMaxId(tableName)) {
			return true;
		}

		return false;
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private javax.sql.DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

	@Autowired
	private SequenceUpgradeStep _sequenceUpgradeStep;

}