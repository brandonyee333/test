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

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class RunLogRepositoryTest {

	@Before
	public void setUp() {
		RunLog runLog = new RunLog();

		DataSource dataSource = _addDataSource();

		runLog.setDataSourceId(dataSource.getId());

		runLog.setDateLogged(new Date());
		runLog.setNaniteClassName("IndividualNanite");

		_runLog = _runLogRepository.save(runLog);
	}

	@After
	public void tearDown() {
		_runLogRepository.deleteAll();
	}

	@Test
	public void testCount() {
		Assert.assertEquals(1, _runLogRepository.count());
	}

	@Test
	public void testDelete() {
		_runLogRepository.delete(_runLog);

		Assert.assertEquals(0, _runLogRepository.count());
	}

	@Test
	public void testDeleteAll1() {
		_runLogRepository.deleteAll();

		Assert.assertEquals(0, _runLogRepository.count());
	}

	@Test
	public void testDeleteAll2() {
		_runLogRepository.deleteAll(Collections.singletonList(_runLog));

		Assert.assertEquals(0, _runLogRepository.count());
	}

	@Test
	public void testDeleteById() {
		Long id = _runLog.getId();

		Assert.assertNotNull(id);

		_runLogRepository.deleteById(id);

		Assert.assertEquals(0, _runLogRepository.count());
	}

	@Test
	public void testExistsById() {
		Assert.assertTrue(_runLogRepository.existsById(_runLog.getId()));
	}

	@Test
	public void testFindAll() {
		Assert.assertEquals(
			Collections.singletonList(_runLog), _runLogRepository.findAll());
	}

	@Test
	public void testFindAllById() {
		Assert.assertEquals(
			Collections.singletonList(_runLog),
			_runLogRepository.findAllById(
				Collections.singletonList(_runLog.getId())));
	}

	@Test
	public void testFindById() {
		Long id = _runLog.getId();

		Assert.assertNotNull(id);

		Optional<RunLog> modelOptional = _runLogRepository.findById(id);

		Assert.assertTrue(modelOptional.isPresent());
	}

	@Test
	public void testFindRunLogByDataSourceIdAndNaniteClassName1() {
		Optional<RunLog> runLogOptional =
			_runLogRepository.
				findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
					_runLog.getDataSourceId(), "IndividualNanite", null);

		Assert.assertTrue(runLogOptional.isPresent());
		Assert.assertEquals(_runLog, runLogOptional.get());
	}

	@Test
	public void testFindRunLogByDataSourceIdAndNaniteClassName2() {
		Optional<RunLog> runLogOptional =
			_runLogRepository.
				findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
					_runLog.getDataSourceId(), "SegmentNanite", null);

		Assert.assertFalse(runLogOptional.isPresent());
	}

	@Test
	public void testFindRunLogByNaniteClassName() {
		Optional<RunLog> runLogOptional =
			_runLogRepository.
				findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
					null, "IndividualNanite", null);

		Assert.assertTrue(runLogOptional.isPresent());
		Assert.assertEquals(_runLog, runLogOptional.get());
	}

	@Test
	public void testSave() {
		Assert.assertEquals(_runLog, _runLogRepository.save(_runLog));
	}

	@Test
	public void testSaveAll() {
		Assert.assertEquals(
			Collections.singletonList(_runLog),
			_runLogRepository.saveAll(Collections.singletonList(_runLog)));
	}

	private DataSource _addDataSource() {
		DataSource dataSource = new DataSource("Third Party");

		dataSource.setCredentialType("OAuth 2 Authentication");

		_channelRepository.save(new Channel("Channel"));

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