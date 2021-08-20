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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class DataSourceDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testAddDataSourceWithDefaultChannel() {
		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Assert.assertNotNull(
			_dataSourceDog.getDefaultChannelId(dataSource.getId()));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testChannelsCleared() {
		_dataSourceDog.disconnectDataSource(405201047787757795L);

		Assert.assertEquals(
			0,
			faroInfoElasticsearchInvoker.count(
				"channels",
				QueryBuilders.termQuery(
					"dataSources.id", "405201047787757795")));

		Assert.assertEquals(
			2,
			faroInfoElasticsearchInvoker.count(
				"channels",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery(
						"dataSources.id", "405201047787757795")
				).filter(
					QueryBuilders.existsQuery("dataSources")
				)));
	}

	@Test
	public void testDisconnectDataSource() {
		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource(
				"Token Authentication", "Liferay", "http://localhost:8080"));

		Assert.assertEquals("CREDENTIALS_VALID", dataSource.getState());
		Assert.assertEquals("ACTIVE", dataSource.getStatus());

		dataSource = _dataSourceDog.disconnectDataSource(dataSource.getId());

		Assert.assertEquals("DISCONNECTED", dataSource.getState());
		Assert.assertEquals("INACTIVE", dataSource.getStatus());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpgradeFromOAuthToToken() {
		DataSource liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource(
			"Token Authentication", "Liferay", "http://localhost:8080");

		liferayDataSource.setId(405201047787757796L);

		DataSource dataSource = _dataSourceDog.patchDataSource(
			liferayDataSource);

		Assert.assertNotNull(dataSource.getPrivateKey());
		Assert.assertNotNull(dataSource.getPublicKey());
		Assert.assertEquals(
			"Token Authentication", dataSource.getCredentialType());
	}

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

}