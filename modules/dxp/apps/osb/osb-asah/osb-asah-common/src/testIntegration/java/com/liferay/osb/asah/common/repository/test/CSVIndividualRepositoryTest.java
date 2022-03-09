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
public class CSVIndividualRepositoryTest
	extends BaseRepositoryTestCase<CSVIndividual, Long> {

	@BeforeEach
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

		CSVIndividual csvIndividual1 = new CSVIndividual(
			1L, JSONUtil.put("givenName", "Bennie"));

		csvIndividual1.setDataSourceIndividualPK("1");

		CSVIndividual csvIndividual2 = new CSVIndividual(
			1L, JSONUtil.put("givenName", "Ellie"));

		csvIndividual2.setDataSourceIndividualPK("2");

		setUpRepository(csvIndividual1, csvIndividual2);

		_csvIndividual1 = entityModels.get(0);
		_csvIndividual2 = entityModels.get(1);
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
			2L, _csvIndividualRepository.countByDataSourceId(1L));
	}

	@Test
	public void testDeleteByDataSourceId() {
		_csvIndividualRepository.deleteByDataSourceId(1L);

		Assertions.assertEquals(
			0L, _csvIndividualRepository.countByDataSourceId(1L));
	}

	@Test
	public void testDeleteByDataSourceIdAndDataSourceIndividualPKIn() {
		_csvIndividualRepository.
			deleteByDataSourceIdAndDataSourceIndividualPKIn(
				1L, Arrays.asList("1", "2"));

		Assertions.assertEquals(
			0L, _csvIndividualRepository.countByDataSourceId(1L));
	}

	@Test
	public void testFindByDataSourceId() {
		List<CSVIndividual> csvIndividualsById =
			_csvIndividualRepository.findByDataSourceId(
				1L, PageRequest.of(0, 10));

		Assertions.assertEquals(2L, csvIndividualsById.size());
	}

	@Test
	public void testFindByDataSourceIdAndFieldKeyEquals() {
		Assertions.assertEquals(
			Arrays.asList(_csvIndividual2),
			_csvIndividualRepository.findByDataSourceIdAndFieldKeyEquals(
				1L, "givenName", "Ellie"));
	}

	@Override
	protected PagingAndSortingRepository<CSVIndividual, Long>
		getPagingAndSortingRepository() {

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