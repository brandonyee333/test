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
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author André Miranda
 */
@Import(JDBCTestConfiguration.class)
public class CustomAssetDashboardRepositoryTest
	extends BaseRepositoryTestCase<CustomAssetDashboard, String> {

	@BeforeEach
	public void setUp() {
		CustomAssetDashboard customAssetDashboard1 = new CustomAssetDashboard();

		customAssetDashboard1.setAssetId("1");
		customAssetDashboard1.setAssetTitle("Gartner Banner 2021");
		customAssetDashboard1.setCreateDate(new Date());
		customAssetDashboard1.setCategory("default");
		customAssetDashboard1.setChannelId(1L);
		customAssetDashboard1.setDataSourceId(1L);
		customAssetDashboard1.setId("1");
		customAssetDashboard1.setIsNew(Boolean.TRUE);

		CustomAssetDashboard customAssetDashboard2 = new CustomAssetDashboard();

		customAssetDashboard2.setAssetId("2");
		customAssetDashboard2.setAssetTitle("Home page logo");
		customAssetDashboard2.setCreateDate(new Date());
		customAssetDashboard2.setCategory("default");
		customAssetDashboard2.setChannelId(1L);
		customAssetDashboard2.setDataSourceId(1L);
		customAssetDashboard2.setId("2");
		customAssetDashboard2.setIsNew(Boolean.TRUE);

		CustomAssetDashboard customAssetDashboard3 = new CustomAssetDashboard();

		customAssetDashboard3.setAssetId("3");
		customAssetDashboard3.setAssetTitle("Page Footer");
		customAssetDashboard3.setCreateDate(new Date());
		customAssetDashboard3.setCategory("default");
		customAssetDashboard3.setChannelId(1L);
		customAssetDashboard3.setDataSourceId(1L);
		customAssetDashboard3.setId("3");
		customAssetDashboard3.setIsNew(Boolean.TRUE);

		CustomAssetDashboard customAssetDashboard4 = new CustomAssetDashboard();

		customAssetDashboard4.setAssetId("4");
		customAssetDashboard4.setAssetTitle("Navigation Content");
		customAssetDashboard4.setCreateDate(new Date());
		customAssetDashboard4.setCategory("default");
		customAssetDashboard4.setChannelId(2L);
		customAssetDashboard4.setDataSourceId(2L);
		customAssetDashboard4.setId("4");
		customAssetDashboard4.setIsNew(Boolean.TRUE);

		CustomAssetDashboard customAssetDashboard5 = new CustomAssetDashboard();

		customAssetDashboard5.setAssetId("5");
		customAssetDashboard5.setAssetTitle("16-654-jmtr_jun2022");
		customAssetDashboard5.setCreateDate(new Date());
		customAssetDashboard5.setCategory("default");
		customAssetDashboard5.setChannelId(3L);
		customAssetDashboard5.setDataSourceId(3L);
		customAssetDashboard5.setId("5");
		customAssetDashboard5.setIsNew(Boolean.TRUE);

		CustomAssetDashboard customAssetDashboard6 = new CustomAssetDashboard();

		customAssetDashboard6.setAssetId("6");
		customAssetDashboard6.setAssetTitle("16-654-jmtr.jun2022");
		customAssetDashboard6.setCreateDate(new Date());
		customAssetDashboard6.setCategory("default");
		customAssetDashboard6.setChannelId(4L);
		customAssetDashboard6.setDataSourceId(4L);
		customAssetDashboard6.setId("6");
		customAssetDashboard6.setIsNew(Boolean.TRUE);

		CustomAssetDashboard customAssetDashboard7 = new CustomAssetDashboard();

		customAssetDashboard7.setAssetId("7");
		customAssetDashboard7.setAssetTitle("16-654-jmtr/jun2022");
		customAssetDashboard7.setCreateDate(new Date());
		customAssetDashboard7.setCategory("default");
		customAssetDashboard7.setChannelId(4L);
		customAssetDashboard7.setDataSourceId(4L);
		customAssetDashboard7.setId("7");
		customAssetDashboard7.setIsNew(Boolean.TRUE);

		setUpRepository(
			customAssetDashboard1, customAssetDashboard2, customAssetDashboard3,
			customAssetDashboard4, customAssetDashboard5, customAssetDashboard6,
			customAssetDashboard7);
	}

	@Test
	public void testCountCustomAssetDashboards() {
		Assertions.assertEquals(
			3,
			_customAssetDashboardRepository.countCustomAssetDashboards(
				1L, null));
		Assertions.assertEquals(
			2,
			_customAssetDashboardRepository.countCustomAssetDashboards(
				1L, "page"));
		Assertions.assertEquals(
			1,
			_customAssetDashboardRepository.countCustomAssetDashboards(
				2L, null));
		Assertions.assertEquals(
			0,
			_customAssetDashboardRepository.countCustomAssetDashboards(
				5L, null));
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
		Pageable pageable = PageRequest.of(
			0, 10, Sort.by(Sort.Order.asc("id")));

		List<CustomAssetDashboard> customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				1L, null, pageable);

		Assertions.assertEquals(
			3, customAssetDashboards.size(), customAssetDashboards.toString());

		customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				1L, "banner", pageable);

		Assertions.assertEquals(
			1, customAssetDashboards.size(), customAssetDashboards.toString());

		CustomAssetDashboard customAssetDashboard = customAssetDashboards.get(
			0);

		Assertions.assertEquals("1", customAssetDashboard.getAssetId());

		customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				3L, "jun", pageable);

		Assertions.assertEquals(
			1, customAssetDashboards.size(), customAssetDashboards.toString());

		customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				3L, "-", pageable);

		Assertions.assertEquals(
			1, customAssetDashboards.size(), customAssetDashboards.toString());

		customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				3L, "_", pageable);

		Assertions.assertEquals(
			1, customAssetDashboards.size(), customAssetDashboards.toString());

		customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				4L, ".", pageable);

		Assertions.assertEquals(
			1, customAssetDashboards.size(), customAssetDashboards.toString());

		customAssetDashboards =
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				4L, "/", pageable);

		Assertions.assertEquals(
			1, customAssetDashboards.size(), customAssetDashboards.toString());
	}

	@Override
	protected PagingAndSortingRepository<CustomAssetDashboard, String>
		getPagingAndSortingRepository() {

		return _customAssetDashboardRepository;
	}

	@Autowired
	private CustomAssetDashboardRepository _customAssetDashboardRepository;

}