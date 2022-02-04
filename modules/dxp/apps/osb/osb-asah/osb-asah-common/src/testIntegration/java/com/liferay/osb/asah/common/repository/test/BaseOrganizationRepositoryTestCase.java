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
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.OrganizationRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Rachael Koestartyo
 */
public abstract class BaseOrganizationRepositoryTestCase
	extends BaseRepositoryTestCase<Organization, Long> {

	@BeforeEach
	public void setUp() {
		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("channel1");

		channel1.setId(11L);
		channel1.setIsNew(true);

		_channelRepository.save(channel1);

		dataSource1.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource1.setId(1L);
		dataSource1.setIsNew(true);
		dataSource1.setProviderType("LIFERAY");
		dataSource1.setState("READY");
		dataSource1.setStatus("STARTED");
		dataSource1.setURL("");

		_dataSourceRepository.save(dataSource1);

		Organization organization = new Organization();

		organization.setOrganizationPK(123L);
		organization.setName("Organization 1");
		organization.setDataSourceId(dataSource1.getId());
		organization.setCreateDate(new Date());
		organization.setModifiedDate(new Date());
		organization.setParentOrganizationPK(0L);

		setUpRepository(organization);
	}

	@Override
	public void tearDown() {
		super.tearDown();

		_channelRepository.deleteAll();
		_dataSourceRepository.deleteAll();

		_organizationRepository.deleteAll();
	}

	@Test
	public void testCountByName() {
		Assertions.assertEquals(1, _organizationRepository.countByName("Org"));
	}

	@Test
	public void testFindByDataSourceIdAndOrganizationPK() {
		Assertions.assertNotNull(
			_organizationRepository.findByDataSourceIdAndOrganizationPK(
				1L, 123L));
	}

	@Test
	public void testFindByDataSourceIdAndOrganizationPKIn() {
		List<Organization> organizations =
			_organizationRepository.findByDataSourceIdAndOrganizationPKIn(
				1L, Collections.singleton(123L));

		Assertions.assertEquals(1, organizations.size());
	}

	@Test
	public void testFindByName() {
		List<Organization> organizations = _organizationRepository.findByName(
			"Org", PageRequest.of(0, 10));

		Assertions.assertEquals(1, organizations.size());
	}

	@Override
	protected PagingAndSortingRepository<Organization, Long>
		getPagingAndSortingRepository() {

		return _organizationRepository;
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private OrganizationRepository _organizationRepository;

}