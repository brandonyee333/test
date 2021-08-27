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

import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.CSVIndividualRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.Repository;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseCSVIndividualRepositoryTestCase
	extends BaseRepositoryTestCase<CSVIndividual, Long> {

	@Before
	public void setUp() {
		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("channel1");

		channel1.setId(11L);
		channel1.setIsNew(true);

		_channelRepository.save(channel1);

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(1L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");
		dataSource.setState("READY");
		dataSource.setStatus("STARTED");
		dataSource.setURL("");

		_dataSourceRepository.save(dataSource);

		setUpRepository(
			new CSVIndividual(1L, JSONUtil.put("givenName", "Bennie")),
			new CSVIndividual(1L, JSONUtil.put("givenName", "Ellie")));

		_csvIndividual1 = entityModels.get(0);
		_csvIndividual2 = entityModels.get(1);
	}

	@Override
	public void tearDown() {
		super.tearDown();

		_channelRepository.deleteAll();
		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testFindByDataSourceIdAndFieldKeyEquals() {
		Assert.assertEquals(
			Arrays.asList(_csvIndividual2),
			_csvIndividualRepository.findByDataSourceIdAndFieldKeyEquals(
				1L, "givenName", "Ellie"));
	}

	@Override
	protected Repository<CSVIndividual, Long> getRepository() {
		return _csvIndividualRepository;
	}

	@Autowired
	private ChannelRepository _channelRepository;

	private CSVIndividual _csvIndividual1;
	private CSVIndividual _csvIndividual2;

	@Autowired
	private CSVIndividualRepository _csvIndividualRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}