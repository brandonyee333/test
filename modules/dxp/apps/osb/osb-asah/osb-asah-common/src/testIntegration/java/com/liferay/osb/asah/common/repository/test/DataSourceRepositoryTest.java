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

import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.DataSourceSite;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Inácio Nery
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class DataSourceRepositoryTest {

	@Before
	public void setUp() {
		DataSource dataSource = new DataSource("name");

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setProviderType("LIFERAY");
		dataSource.setStatus("STARTED");
		dataSource.setType("Token Authentication");

		_dataSource = _dataSourceRepository.save(dataSource);
	}

	@After
	public void tearDown() {
		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testCount() {
		Assert.assertEquals(1, _dataSourceRepository.count());
	}

	@Test
	public void testDelete() {
		_dataSourceRepository.delete(_dataSource);

		Assert.assertEquals(0, _dataSourceRepository.count());
	}

	@Test
	public void testDeleteAll1() {
		_dataSourceRepository.deleteAll();

		Assert.assertEquals(0, _dataSourceRepository.count());
	}

	@Test
	public void testDeleteAll2() {
		Iterable<DataSource> dataSources = new ArrayList<>(
			Arrays.asList(_dataSource));

		_dataSourceRepository.deleteAll(dataSources);

		Assert.assertEquals(0, _dataSourceRepository.count());
	}

	@Test
	public void testDeleteById() {
		Long id = _dataSource.getId();

		Assert.assertNotNull(id);

		_dataSourceRepository.deleteById(id);

		Assert.assertEquals(0, _dataSourceRepository.count());
	}

	@Test
	public void testExistsByFaroBackendSecuritySignature() {
		Assert.assertTrue(
			_dataSourceRepository.existsByFaroBackendSecuritySignature(
				"faroBackendSecuritySignature"));
	}

	@Test
	public void testExistsById() {
		Long id = _dataSource.getId();

		Assert.assertNotNull(id);

		Assert.assertTrue(_dataSourceRepository.existsById(id));
	}

	@Test
	public void testExistsByIdNotAndName() {
		DataSource dataSource = _dataSourceRepository.save(
			new DataSource("dataSource"));

		Assert.assertEquals(
			true,
			_dataSourceRepository.existsByIdNotAndName(
				dataSource.getId(), "name"));

		Assert.assertEquals(
			false,
			_dataSourceRepository.existsByIdNotAndName(
				_dataSource.getId(), "name"));
	}

	@Test
	public void testExistsByName() {
		Assert.assertTrue(_dataSourceRepository.existsByName("name"));
	}

	@Test
	public void testExistsByProviderType() {
		Assert.assertFalse(
			_dataSourceRepository.existsByProviderType("LIFERAY"));

		_dataSource.setEnableAllSites(true);

		_dataSourceRepository.save(_dataSource);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));

		_dataSource.setEnableAllSites(false);
		_dataSource.setSitesSelected(true);

		_dataSourceRepository.save(_dataSource);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));

		DataSourceSite dataSourceSite = new DataSourceSite();

		dataSourceSite.setSiteId(1L);

		_dataSource.setDataSourceSites(Collections.singleton(dataSourceSite));

		_dataSource.setSitesSelected(false);

		_dataSourceRepository.save(_dataSource);

		Assert.assertTrue(
			_dataSourceRepository.existsByProviderType("LIFERAY"));
	}

	@Test
	public void testFindAll1() {
		Assert.assertEquals(
			Arrays.asList(_dataSource), _dataSourceRepository.findAll());
	}

	@Test
	public void testFindAll2() {
		Assert.assertEquals(
			Arrays.asList(_dataSource),
			_dataSourceRepository.findAll(PageRequest.of(0, 1)));
	}

	@Test
	public void testFindAllById() {
		Assert.assertEquals(
			Arrays.asList(_dataSource),
			_dataSourceRepository.findAllById(
				Arrays.asList(_dataSource.getId())));
	}

	@Test
	public void testFindById() {
		Long id = _dataSource.getId();

		Assert.assertNotNull(id);

		Optional<DataSource> dataSourceOptional =
			_dataSourceRepository.findById(id);

		Assert.assertTrue(dataSourceOptional.isPresent());

		_assertDataSource(dataSourceOptional.get());
	}

	@Test
	public void testFindByProviderType1() {
		List<DataSource> dataSources = _dataSourceRepository.findByProviderType(
			"LIFERAY");

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSource(dataSources.get(0));
	}

	@Test
	public void testFindByProviderType2() {
		List<DataSource> dataSources = _dataSourceRepository.findByProviderType(
			"LIFERAY", PageRequest.of(0, 1));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSource(dataSources.get(0));
	}

	@Test
	public void testFindByProviderTypeAndStatus() {
		List<DataSource> dataSources =
			_dataSourceRepository.findByProviderTypeAndStatus(
				"LIFERAY", "STARTED");

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSource(dataSources.get(0));
	}

	@Test
	public void testFindByProviderTypeAndType() {
		List<DataSource> dataSources =
			_dataSourceRepository.findByProviderTypeAndType(
				"LIFERAY", "Token Authentication", PageRequest.of(0, 1));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSource(dataSources.get(0));
	}

	@Test
	public void testFindByType() {
		List<DataSource> dataSources = _dataSourceRepository.findByType(
			"Token Authentication", PageRequest.of(0, 1));

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		_assertDataSource(dataSources.get(0));
	}

	@Test
	public void testSave() {
		_assertDataSource(_dataSource);
	}

	@Test
	public void testSaveAll() {
		List<DataSource> dataSources = Arrays.asList(_dataSource);

		Assert.assertEquals(
			dataSources, _dataSourceRepository.saveAll(dataSources));
	}

	private void _assertDataSource(DataSource dataSource) {
		Assert.assertNotNull(dataSource);
		Assert.assertNotNull(dataSource.getId());
		Assert.assertEquals(
			"faroBackendSecuritySignature",
			dataSource.getFaroBackendSecuritySignature());
		Assert.assertEquals("name", dataSource.getName());
		Assert.assertEquals("LIFERAY", dataSource.getProviderType());
		Assert.assertEquals("STARTED", dataSource.getStatus());
		Assert.assertEquals("Token Authentication", dataSource.getType());
	}

	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}