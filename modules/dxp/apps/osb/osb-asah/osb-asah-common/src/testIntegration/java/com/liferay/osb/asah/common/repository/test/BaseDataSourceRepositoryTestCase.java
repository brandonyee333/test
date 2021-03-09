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
import com.liferay.osb.asah.common.model.DataSourceOrganization;
import com.liferay.osb.asah.common.model.DataSourceSite;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Inácio Nery
 */
public abstract class BaseDataSourceRepositoryTestCase
	extends BaseRepositoryTestCase<DataSource> {

	@Before
	public void setUp() {
		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("Liferay Brazil");

		channel1.setId(1L);
		channel1.setIsNew(true);

		channel1 = _channelRepository.save(channel1);

		dataSource1.setChannelId(channel1.getId());

		dataSource1.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");

		DataSourceOrganization dataSourceOrganization1 =
			new DataSourceOrganization();

		dataSourceOrganization1.setEnableAllChildren(true);
		dataSourceOrganization1.setOrganizationId(1L);
		dataSourceOrganization1.setOrganizationIds(SetUtil.of(3L, 4L));

		DataSourceOrganization dataSourceOrganization2 =
			new DataSourceOrganization();

		dataSourceOrganization2.setEnableAllChildren(false);
		dataSourceOrganization2.setOrganizationId(5L);
		dataSourceOrganization2.setOrganizationIds(SetUtil.of(6L, 7L));

		dataSource1.setDataSourceOrganizations(
			SetUtil.of(dataSourceOrganization1, dataSourceOrganization2));

		dataSource1.setProviderType("LIFERAY");
		dataSource1.setState("READY");
		dataSource1.setStatus("STARTED");
		dataSource1.setURL("");

		_dataSources.add(_dataSourceRepository.save(dataSource1));

		DataSource dataSource2 = new DataSource("Liferay USA");

		dataSource2.setCredentialType("Basic Authentication");

		Channel channel2 = new Channel("Liferay USA");

		channel2.setId(2L);
		channel2.setIsNew(true);

		channel2 = _channelRepository.save(channel2);

		dataSource2.setChannelId(channel2.getId());

		dataSource2.setProviderType("CSV");
		dataSource2.setState("CREDENTIALS_VALID");
		dataSource2.setURL("http://portal:8080");
		dataSource2.setWorkspaceURL("http://localhost:8080/workspace/10000");

		_dataSources.add(_dataSourceRepository.save(dataSource2));

		DataSource dataSource3 = new DataSource("Third Party");

		dataSource3.setCredentialType("OAuth 2 Authentication");

		Channel channel3 = new Channel("Third Party");

		channel3.setId(3L);
		channel3.setIsNew(true);

		channel3 = _channelRepository.save(channel3);

		dataSource3.setChannelId(channel3.getId());

		dataSource3.setProviderType("SALESFORCE");
		dataSource3.setState("CREDENTIALS_INVALID");
		dataSource3.setWorkspaceURL("");

		_dataSources.add(_dataSourceRepository.save(dataSource3));
	}

	@Test
	public void testCountDataSources() {
		Assert.assertEquals(
			3,
			_dataSourceRepository.countDataSources(
				null, null, null, null, null, null, null, null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				Arrays.asList(1L), null, null, null, null, null, null, null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				null, "Token Authentication", null, null, null, null, null,
				null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				null, null, Arrays.asList("Liferay Brazil"), null, null, null,
				null, null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				null, null, null, "LIFERAY", null, null, null, null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				null, null, null, null, Arrays.asList("Liferay", "Brazil"),
				null, null, null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				null, null, null, null, null, Arrays.asList("READY"), null,
				null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				null, null, null, null, null, null, true, null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				null, null, null, null, null, null, null, true));

		Assert.assertEquals(
			2,
			_dataSourceRepository.countDataSources(
				Arrays.asList(1L, 2L), null, null, null, null, null, null,
				null));

		Assert.assertEquals(
			0,
			_dataSourceRepository.countDataSources(
				Arrays.asList(4L), null, null, null, null, null, null, null));

		Assert.assertEquals(
			0,
			_dataSourceRepository.countDataSources(
				null, null, Arrays.asList("Liferay"), null, null, null, null,
				null));

		Assert.assertEquals(
			2,
			_dataSourceRepository.countDataSources(
				null, null, null, null, Arrays.asList("Liferay"), null, null,
				null));

		Assert.assertEquals(
			2,
			_dataSourceRepository.countDataSources(
				null, null, null, null, null,
				Arrays.asList("CREDENTIALS_INVALID", "CREDENTIALS_VALID"), null,
				null));

		Assert.assertEquals(
			0,
			_dataSourceRepository.countDataSources(
				null, null, null, null, null,
				Arrays.asList("IN_PROGRESS_DELETING"), null, null));

		Assert.assertEquals(
			3,
			_dataSourceRepository.countDataSources(
				null, null, null, null, null, null, false, null));

		Assert.assertEquals(
			3,
			_dataSourceRepository.countDataSources(
				null, null, null, null, null, null, null, false));
	}

	@Test
	public void testExistsByFaroBackendSecuritySignature() {
		Assert.assertTrue(
			_dataSourceRepository.existsByFaroBackendSecuritySignature(
				"faroBackendSecuritySignature"));
	}

	@Test
	public void testExistsByIdNotAndName() {
		DataSource dataSource1 = _dataSources.get(0);

		Assert.assertEquals(
			false,
			_dataSourceRepository.existsByIdNotAndName(
				dataSource1.getId(), "Liferay Brazil"));

		DataSource dataSource2 = _dataSources.get(1);

		Assert.assertEquals(
			true,
			_dataSourceRepository.existsByIdNotAndName(
				dataSource2.getId(), "Liferay Brazil"));
	}

	@Test
	public void testExistsByName() {
		Assert.assertTrue(_dataSourceRepository.existsByName("Liferay Brazil"));
	}

	@Test
	public void testExistsByProviderType() {
		Assert.assertFalse(
			_dataSourceRepository.existsByProviderType("LIFERAY"));

		DataSource dataSource = _dataSources.get(0);

		dataSource.setEnableAllSites(true);

		_dataSourceRepository.save(dataSource);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));

		dataSource.setEnableAllSites(false);
		dataSource.setSitesSelected(true);

		_dataSourceRepository.save(dataSource);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));

		DataSourceSite dataSourceSite = new DataSourceSite();

		dataSourceSite.setSiteId(1L);

		dataSource.setDataSourceSites(Collections.singleton(dataSourceSite));

		dataSource.setSitesSelected(false);

		_dataSourceRepository.save(dataSource);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));
	}

	@Override
	@Test
	public void testFindAll() {
		super.testFindAll();

		Assert.assertEquals(
			_dataSources,
			_dataSourceRepository.findAll(
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id")))));
	}

	@Test
	public void testFindByCredentialType() {
		List<DataSource> dataSources =
			_dataSourceRepository.findByCredentialType(
				"Token Authentication",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));
	}

	@Test
	public void testFindByCredentialTypeAndProviderType() {
		List<DataSource> dataSources =
			_dataSourceRepository.findByCredentialTypeAndProviderType(
				"Token Authentication", "LIFERAY",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));
	}

	@Test
	public void testFindByProviderType1() {
		List<DataSource> dataSources = _dataSourceRepository.findByProviderType(
			"LIFERAY");

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));
	}

	@Test
	public void testFindByProviderType2() {
		List<DataSource> dataSources = _dataSourceRepository.findByProviderType(
			"LIFERAY", PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));
	}

	@Test
	public void testFindByProviderTypeAndStatus() {
		List<DataSource> dataSources =
			_dataSourceRepository.findByProviderTypeAndStatus(
				"LIFERAY", "STARTED");

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));
	}

	@Test
	public void testSearchDataSources() {
		List<DataSource> dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 3, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			Arrays.asList(1L), null, null, null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, "Token Authentication", null, null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, Arrays.asList("Liferay Brazil"), null, null, null, null,
			null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, "LIFERAY", null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, Arrays.asList("Liferay", "Brazil"), null,
			null, null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, Arrays.asList("READY"), null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, null, true, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, null, null, true,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		assertModel(dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			Arrays.asList(1L, 2L), null, null, null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 2, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			Arrays.asList(4L), null, null, null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 0, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, Arrays.asList("Liferay"), null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 0, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, Arrays.asList("Liferay"), null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 2, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null,
			Arrays.asList("CREDENTIALS_INVALID", "CREDENTIALS_VALID"), null,
			null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 2, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, Arrays.asList("IN_PROGRESS_DELETING"),
			null, null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 0, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, null, false, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 3, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, null, null, false,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 3, dataSources.size());
	}

	@Override
	protected void assertModel(DataSource dataSource) {
		Assert.assertNotNull(dataSource);
		Assert.assertNotNull(dataSource.getId());

		DataSource expectedDataSource = _dataSources.get(0);

		Assert.assertEquals(
			expectedDataSource.getChannelId(), dataSource.getChannelId());
		Assert.assertEquals(
			expectedDataSource.getCredentialType(),
			dataSource.getCredentialType());
		Assert.assertEquals(
			expectedDataSource.getDataSourceOrganizations(),
			dataSource.getDataSourceOrganizations());
		Assert.assertEquals(
			expectedDataSource.getFaroBackendSecuritySignature(),
			dataSource.getFaroBackendSecuritySignature());
		Assert.assertEquals(expectedDataSource.getName(), dataSource.getName());
		Assert.assertEquals(
			expectedDataSource.getProviderType(), dataSource.getProviderType());
		Assert.assertEquals(
			expectedDataSource.getState(), dataSource.getState());
		Assert.assertEquals(
			expectedDataSource.getStatus(), dataSource.getStatus());
		Assert.assertEquals(expectedDataSource.getURL(), dataSource.getURL());
	}

	@Override
	protected CrudRepository<DataSource, Long> getCrudRepository() {
		return _dataSourceRepository;
	}

	@Override
	protected List<DataSource> getModels() {
		return _dataSources;
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private final List<DataSource> _dataSources = new ArrayList<>();

}