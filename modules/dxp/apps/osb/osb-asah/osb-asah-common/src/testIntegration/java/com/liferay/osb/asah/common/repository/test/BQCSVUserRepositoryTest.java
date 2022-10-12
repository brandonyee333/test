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

import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQCSVUserRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Marcellus Tavares
 */
@Import(JDBCTestConfiguration.class)
public class BQCSVUserRepositoryTest
	extends BaseRepositoryTestCase<BQCSVUser, Long> {

	@BeforeEach
	public void setUp() {
		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("channel1");

		channel1.setId(11L);
		channel1.setIsNew(Boolean.TRUE);

		_channelRepository.save(channel1);

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(1L);
		dataSource.setIsNew(Boolean.TRUE);
		dataSource.setProviderType("LIFERAY");
		dataSource.setState("READY");
		dataSource.setStatus("STARTED");
		dataSource.setURL("");

		_dataSourceRepository.save(dataSource);

		BQCSVUser bqCSVUser1 = new BQCSVUser(
			1L,
			JSONUtil.putAll(
				JSONUtil.put(
					"name", "givenName"
				).put(
					"value", "Bennie"
				),
				JSONUtil.put(
					"name", "age"
				).put(
					"value", "20"
				)));

		bqCSVUser1.setDataSourceUserPK("1");

		BQCSVUser bqCSVUser2 = new BQCSVUser(
			1L,
			JSONUtil.putAll(
				JSONUtil.put(
					"name", "givenName"
				).put(
					"value", "Ellie"
				)));

		bqCSVUser2.setDataSourceUserPK("2");

		setUpRepository(bqCSVUser1, bqCSVUser2);

		_bqCSVUser1 = entityModels.get(0);
		_bqCSVUser2 = entityModels.get(1);
	}

	@AfterEach
	public void tearDown() {
		super.tearDown();

		_channelRepository.deleteAll();
		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testCountByDataSourceId() {
		Assertions.assertEquals(
			2L, _bqCSVUserRepository.countByDataSourceId(1L));
	}

	@Test
	public void testDeleteByDataSourceId() {
		_bqCSVUserRepository.deleteByDataSourceId(1L);

		Assertions.assertEquals(
			0L, _bqCSVUserRepository.countByDataSourceId(1L));
	}

	@Test
	public void testDeleteByDataSourceIdAndDataSourceUserPKIn() {
		_bqCSVUserRepository.deleteByDataSourceIdAndDataSourceUserPKIn(
			1L, Arrays.asList("1", "2"));

		Assertions.assertEquals(
			0L, _bqCSVUserRepository.countByDataSourceId(1L));
	}

	@Test
	public void testFindByDataSourceId() {
		List<BQCSVUser> bqCSVIdentitiesById =
			_bqCSVUserRepository.findByDataSourceId(1L, PageRequest.of(0, 10));

		Assertions.assertEquals(2L, bqCSVIdentitiesById.size());
	}

	@Override
	protected PagingAndSortingRepository<BQCSVUser, Long>
		getPagingAndSortingRepository() {

		return _bqCSVUserRepository;
	}

	private BQCSVUser _bqCSVUser1;
	private BQCSVUser _bqCSVUser2;

	@Autowired
	private BQCSVUserRepository _bqCSVUserRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}