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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.RunLog;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahPostgreSQLSpring4ClassRunner;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@DirtiesContext
@RunWith(OSBAsahPostgreSQLSpring4ClassRunner.class)
@SpringBootTest(properties = "osb.asah.postgresql.enabled=true")
public class RunLogRepositoryTest extends BaseRepositoryTestCase<RunLog> {

	@Before
	public void setUp() {
		RunLog runLog = new RunLog();

		DataSource dataSource = _addDataSource();

		runLog.setDataSourceId(dataSource.getId());

		runLog.setDateLogged(new Date());
		runLog.setNaniteClassName("IndividualNanite");

		setUpRepository(runLog);

		_runLog = entityModels.get(0);
	}

	@Test
	public void testFindRunLogByDataSourceIdAndNaniteClassName1() {
		Optional<RunLog> runLogOptional =
			_runLogRepository.
				findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
					Optional.of(_runLog.getDataSourceId()), "IndividualNanite",
					Optional.empty());

		Assert.assertTrue(runLogOptional.isPresent());
		Assert.assertEquals(_runLog, runLogOptional.get());
	}

	@Test
	public void testFindRunLogByDataSourceIdAndNaniteClassName2() {
		Optional<RunLog> runLogOptional =
			_runLogRepository.
				findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
					Optional.of(_runLog.getDataSourceId()), "SegmentNanite",
					Optional.empty());

		Assert.assertFalse(runLogOptional.isPresent());
	}

	@Test
	public void testFindRunLogByNaniteClassName() {
		Optional<RunLog> runLogOptional =
			_runLogRepository.
				findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
					Optional.empty(), "IndividualNanite", Optional.empty());

		Assert.assertTrue(runLogOptional.isPresent());
		Assert.assertEquals(_runLog, runLogOptional.get());
	}

	protected CrudRepository<RunLog, Long> getCrudRepository() {
		return _runLogRepository;
	}

	private DataSource _addDataSource() {
		DataSource dataSource = new DataSource("Third Party");

		dataSource.setCredentialType("OAuth 2 Authentication");

		Channel channel = _channelRepository.save(new Channel("Channel"));

		dataSource.setChannelId(channel.getId());

		dataSource.setProviderType("SALESFORCE");
		dataSource.setState("CREDENTIALS_INVALID");
		dataSource.setWorkspaceURL("");

		return _dataSourceRepository.save(dataSource);
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private RunLog _runLog;

	@Autowired
	private RunLogRepository _runLogRepository;

}