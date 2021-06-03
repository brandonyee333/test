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

import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceOrganization;
import com.liferay.osb.asah.common.entity.DataSourceSite;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.ArrayList;
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
	extends BaseRepositoryTestCase<DataSource, Long> {

	@Before
	public void setUp() {
		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

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

		dataSource2.setProviderType("CSV");
		dataSource2.setState("CREDENTIALS_VALID");
		dataSource2.setURL("http://portal:8080");
		dataSource2.setWorkspaceURL("http://localhost:8080/workspace/10000");

		DataSource dataSource3 = new DataSource("Third Party");

		dataSource3.setCredentialType("OAuth 2 Authentication");

		dataSource3.setProviderType("SALESFORCE");
		dataSource3.setState("CREDENTIALS_INVALID");
		dataSource3.setWorkspaceURL("");

		setUpRepository(dataSource1, dataSource2, dataSource3);

		_dataSources = new ArrayList<>(entityModels);
	}

	@Test
	public void testCountDataSources() {
		Assert.assertEquals(3, _dataSourceRepository.countDataSources(null));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				"credentials/type eq 'Token Authentication'"));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources("name eq 'Liferay Brazil'"));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				"provider/type eq 'LIFERAY'"));

		Assert.assertEquals(
			1,
			_dataSourceRepository.countDataSources(
				"contains(name, 'Liferay') and contains(name, 'Brazil')"));

		Assert.assertEquals(
			1, _dataSourceRepository.countDataSources("state eq 'READY'"));

		Assert.assertEquals(
			1, _dataSourceRepository.countDataSources("url eq null"));

		Assert.assertEquals(
			1, _dataSourceRepository.countDataSources("workspaceURL eq null"));

		Assert.assertEquals(
			0, _dataSourceRepository.countDataSources("name eq 'Liferay'"));

		Assert.assertEquals(
			2,
			_dataSourceRepository.countDataSources(
				"contains(name, 'Liferay')"));

		Assert.assertEquals(
			2,
			_dataSourceRepository.countDataSources(
				"state eq ['CREDENTIALS_INVALID','CREDENTIALS_VALID']"));

		Assert.assertEquals(
			0,
			_dataSourceRepository.countDataSources(
				"state eq 'IN_PROGRESS_DELETING'"));

		Assert.assertEquals(
			2, _dataSourceRepository.countDataSources("url ne null"));

		Assert.assertEquals(
			2, _dataSourceRepository.countDataSources("workspaceURL ne null"));
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

	@Override
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
			null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 3, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			"credentials/type eq 'Token Authentication'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			"name eq 'Liferay Brazil'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			"provider/type eq 'LIFERAY'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			"contains(name, 'Liferay') and contains(name, 'Brazil')",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			"state eq 'READY'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			"url eq ''", PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			"workspaceURL eq null",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSourceEquals(_dataSources.get(0), dataSources.get(0));

		dataSources = _dataSourceRepository.searchDataSources(
			"name eq 'Liferay'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 0, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			"contains(name, 'Liferay')",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 2, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			"state eq ['CREDENTIALS_INVALID','CREDENTIALS_VALID']",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 2, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			"state eq 'IN_PROGRESS_DELETING'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 0, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			"url ne null",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 2, dataSources.size());

		dataSources = _dataSourceRepository.searchDataSources(
			"workspaceURL ne null",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(dataSources.toString(), 2, dataSources.size());
	}

	@Override
	protected CrudRepository<DataSource, Long> getCrudRepository() {
		return _dataSourceRepository;
	}

	private void _assertDataSourceEquals(
		DataSource expectedDataSource, DataSource actualDataSource) {

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
	private DataSourceRepository _dataSourceRepository;

	private List<DataSource> _dataSources;

}