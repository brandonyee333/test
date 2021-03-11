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

		Channel channel1 = _addChannel(1, "Liferay Brazil");

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

		DataSource dataSource2 = new DataSource("Liferay USA");

		dataSource2.setCredentialType("Basic Authentication");

		Channel channel2 = _addChannel(2, "Liferay USA");

		dataSource2.setChannelId(channel2.getId());

		dataSource2.setProviderType("CSV");
		dataSource2.setState("CREDENTIALS_VALID");
		dataSource2.setURL("http://portal:8080");
		dataSource2.setWorkspaceURL("http://localhost:8080/workspace/10000");

		DataSource dataSource3 = new DataSource("Third Party");

		dataSource3.setCredentialType("OAuth 2 Authentication");

		Channel channel3 = _addChannel(3, "Third Party");

		dataSource3.setChannelId(channel3.getId());

		dataSource3.setProviderType("SALESFORCE");
		dataSource3.setState("CREDENTIALS_INVALID");
		dataSource3.setWorkspaceURL("");

		setUpRepository(dataSource1, dataSource2, dataSource3);

		_dataSources = new ArrayList<>(entityModels);
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

		DataSource dataSource1 = _dataSources.get(0);

		dataSource1.setEnableAllSites(true);

		_dataSourceRepository.save(dataSource1);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));

		dataSource1.setEnableAllSites(false);
		dataSource1.setSitesSelected(true);

		_dataSourceRepository.save(dataSource1);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));

		DataSourceSite dataSourceSite = new DataSourceSite();

		dataSourceSite.setSiteId(1L);

		dataSource1.setDataSourceSites(Collections.singleton(dataSourceSite));

		dataSource1.setSitesSelected(false);

		_dataSourceRepository.save(dataSource1);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));
	}

	@Test
	public void testFindAll() {
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

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));
	}

	@Test
	public void testFindByCredentialTypeAndProviderType() {
		List<DataSource> dataSources =
			_dataSourceRepository.findByCredentialTypeAndProviderType(
				"Token Authentication", "LIFERAY",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));
	}

	@Test
	public void testFindByProviderType1() {
		List<DataSource> dataSources = _dataSourceRepository.findByProviderType(
			"LIFERAY");

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));
	}

	@Test
	public void testFindByProviderType2() {
		List<DataSource> dataSources = _dataSourceRepository.findByProviderType(
			"LIFERAY", PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));
	}

	@Test
	public void testFindByProviderTypeAndStatus() {
		List<DataSource> dataSources =
			_dataSourceRepository.findByProviderTypeAndStatus(
				"LIFERAY", "STARTED");

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));
	}

	@Test
	public void testSearchDataSources() {
		List<DataSource> dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 3, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			Arrays.asList(1L), null, null, null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, "Token Authentication", null, null, null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, Arrays.asList("Liferay Brazil"), null, null, null, null,
			null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, "LIFERAY", null, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, Arrays.asList("Liferay", "Brazil"), null,
			null, null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, Arrays.asList("READY"), null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, null, true, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			null, null, null, null, null, null, null, true,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

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
	protected CrudRepository<DataSource, Long> getCrudRepository() {
		return _dataSourceRepository;
	}

	private Channel _addChannel(long id, String name) {
		Channel channel = new Channel(name);

		channel.setId(id);
		channel.setIsNew(true);

		return _channelRepository.save(channel);
	}

	private void _assertDataSourceEquals(
		DataSource expectedDataSource, DataSource actualDataSource) {

		Assert.assertEquals(
			expectedDataSource.getChannelId(), actualDataSource.getChannelId());
		Assert.assertEquals(
			expectedDataSource.getCredentialType(),
			actualDataSource.getCredentialType());
		Assert.assertEquals(
			expectedDataSource.getDataSourceOrganizations(),
			actualDataSource.getDataSourceOrganizations());
		Assert.assertEquals(
			expectedDataSource.getFaroBackendSecuritySignature(),
			actualDataSource.getFaroBackendSecuritySignature());
		Assert.assertEquals(
			expectedDataSource.getName(), actualDataSource.getName());
		Assert.assertEquals(
			expectedDataSource.getProviderType(),
			actualDataSource.getProviderType());
		Assert.assertEquals(
			expectedDataSource.getState(), actualDataSource.getState());
		Assert.assertEquals(
			expectedDataSource.getStatus(), actualDataSource.getStatus());
		Assert.assertEquals(
			expectedDataSource.getURL(), actualDataSource.getURL());
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private List<DataSource> _dataSources;

}