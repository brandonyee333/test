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

import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.repository.CustomAssetDashboardRepository;
import com.liferay.osb.asah.common.repository.Repository;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author André Miranda
 */
public abstract class BaseCustomAssetDashboardRepositoryTestCase
	extends BaseRepositoryTestCase<CustomAssetDashboard, String> {

	@Before
	public void setUp() {
		CustomAssetDashboard customAssetDashboard1 = new CustomAssetDashboard();

		customAssetDashboard1.setAssetId("1");
		customAssetDashboard1.setAssetTitle("Gartner Banner 2021");
		customAssetDashboard1.setCreateDate(new Date());
		customAssetDashboard1.setCategory("default");
		customAssetDashboard1.setChannelId(1L);
		customAssetDashboard1.setDataSourceId(1L);
		customAssetDashboard1.setId("1");
		customAssetDashboard1.setIsNew(true);

		CustomAssetDashboard customAssetDashboard2 = new CustomAssetDashboard();

		customAssetDashboard2.setAssetId("2");
		customAssetDashboard2.setAssetTitle("Home page logo");
		customAssetDashboard2.setCreateDate(new Date());
		customAssetDashboard2.setCategory("default");
		customAssetDashboard2.setChannelId(1L);
		customAssetDashboard2.setDataSourceId(1L);
		customAssetDashboard2.setId("2");
		customAssetDashboard2.setIsNew(true);

		CustomAssetDashboard customAssetDashboard3 = new CustomAssetDashboard();

		customAssetDashboard3.setAssetId("3");
		customAssetDashboard3.setAssetTitle("Page Footer");
		customAssetDashboard3.setCreateDate(new Date());
		customAssetDashboard3.setCategory("default");
		customAssetDashboard3.setChannelId(1L);
		customAssetDashboard3.setDataSourceId(1L);
		customAssetDashboard3.setId("3");
		customAssetDashboard3.setIsNew(true);

		CustomAssetDashboard customAssetDashboard4 = new CustomAssetDashboard();

		customAssetDashboard4.setAssetId("4");
		customAssetDashboard4.setAssetTitle("Navigation Content");
		customAssetDashboard4.setCreateDate(new Date());
		customAssetDashboard4.setCategory("default");
		customAssetDashboard4.setChannelId(2L);
		customAssetDashboard4.setDataSourceId(2L);
		customAssetDashboard4.setId("4");
		customAssetDashboard4.setIsNew(true);

		setUpRepository(
			customAssetDashboard1, customAssetDashboard2, customAssetDashboard3,
			customAssetDashboard4);
	}

	@Test
	public void testCountCustomAssetDashboards() {
		Assert.assertEquals(
			3,
			_customAssetDashboardRepository.countCustomAssetDashboards(
				1L, null));
		Assert.assertEquals(
			2,
			_customAssetDashboardRepository.countCustomAssetDashboards(
				1L, "page"));
		Assert.assertEquals(
			1,
			_customAssetDashboardRepository.countCustomAssetDashboards(
				2L, null));
		Assert.assertEquals(
			0,
			_customAssetDashboardRepository.countCustomAssetDashboards(
				3L, null));
	}

	@Override
	@Test
	public void testSave() {
		_customAssetDashboardRepository.delete(entityModels.get(0));

		super.testSave();
	}

	@Override
	@Test
	public void testSaveAll() {
		_customAssetDashboardRepository.deleteAll();

		super.testSaveAll();
	}

	@Test
	public void testSearchCustomAssetDashboards() {
		List<CustomAssetDashboard> customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				1L, null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			customAssetDashboards.toString(), 3, customAssetDashboards.size());

		customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				1L, "banner",
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			customAssetDashboards.toString(), 1, customAssetDashboards.size());

		CustomAssetDashboard customAssetDashboard = customAssetDashboards.get(
			0);

		Assert.assertEquals("1", customAssetDashboard.getAssetId());
	}

	@Override
	protected Repository<CustomAssetDashboard, String> getRepository() {
		return _customAssetDashboardRepository;
	}

	@Autowired
	private CustomAssetDashboardRepository _customAssetDashboardRepository;

}