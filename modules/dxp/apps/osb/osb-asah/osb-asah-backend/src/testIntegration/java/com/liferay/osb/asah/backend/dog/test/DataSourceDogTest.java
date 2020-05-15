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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.DataSourceDog;
import com.liferay.osb.asah.backend.model.DataSource;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.elasticsearch.search.sort.SortOrder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class DataSourceDogTest {

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetAllDataSources() {
		List<DataSource> dataSources = _dataSourceDog.getDataSources(
			null, null, null, null);

		Assert.assertEquals(dataSources.toString(), 4, dataSources.size());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetFilteredDataSources() {
		List<DataSource> dataSources = _dataSourceDog.getDataSources(
			"Token Authentication",
			SortBuilderUtil.fieldSort("dateModified", SortOrder.DESC), 1,
			"LIFERAY");

		Assert.assertEquals(dataSources.toString(), 1, dataSources.size());

		DataSource dataSource = dataSources.get(0);

		Assert.assertEquals("400", dataSource.getId());
		Assert.assertEquals("Liferay 3", dataSource.getName());
		Assert.assertEquals("http://portal:8083", dataSource.getUrl());
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

}