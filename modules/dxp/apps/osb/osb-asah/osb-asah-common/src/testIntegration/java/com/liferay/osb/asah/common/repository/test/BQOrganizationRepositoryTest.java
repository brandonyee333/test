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

import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

/**
 * @author Rachael Koestartyo
 */
@Import(JDBCTestConfiguration.class)
public class BQOrganizationRepositoryTest {

	@BeforeEach
	public void setUp() {
		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("channel1");

		channel1.setId(11L);
		channel1.setIsNew(Boolean.TRUE);

		_channelRepository.save(channel1);

		dataSource1.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource1.setId(1L);
		dataSource1.setIsNew(Boolean.TRUE);
		dataSource1.setProviderType("LIFERAY");
		dataSource1.setState("READY");
		dataSource1.setStatus("STARTED");
		dataSource1.setURL("");

		_dataSourceRepository.save(dataSource1);

		BQOrganization bqOrganization = new BQOrganization();

		bqOrganization.setCreateDate(new Date());

		bqOrganization.setDataSourceId(dataSource1.getId());
		bqOrganization.setId(RandomTestUtil.randomUUID());
		bqOrganization.setModifiedDate(new Date());
		bqOrganization.setName("Organization 1");
		bqOrganization.setOrganizationId(123L);
		bqOrganization.setParentOrganizationId(0L);

		_bqOrganizationRepository.insert(bqOrganization);
	}

	@AfterEach
	public void tearDown() {
		_channelRepository.deleteAll();
		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testCountByName() {
		Assertions.assertEquals(
			1, _bqOrganizationRepository.countByName("Org"));
	}

	@Test
	public void testFindByDataSourceIdAndOrganizationId() {
		Assertions.assertNotNull(
			_bqOrganizationRepository.findByDataSourceIdAndOrganizationId(
				1L, 123L));
	}

	@Test
	public void testFindByDataSourceIdAndOrganizationIdIn() {
		List<BQOrganization> bqOrganizations =
			_bqOrganizationRepository.findByDataSourceIdAndOrganizationIdIn(
				1L, Collections.singleton(123L));

		Assertions.assertEquals(1, bqOrganizations.size());
	}

	@Test
	public void testFindByName() {
		List<BQOrganization> bqOrganizations =
			_bqOrganizationRepository.findByName("Org", PageRequest.of(0, 10));

		Assertions.assertEquals(1, bqOrganizations.size());
	}

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}