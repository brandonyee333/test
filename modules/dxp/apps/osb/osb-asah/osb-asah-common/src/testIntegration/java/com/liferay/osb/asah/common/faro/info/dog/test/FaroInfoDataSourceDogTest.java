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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisDisabledTestConfiguration;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@Import(OSBAsahRedisDisabledTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoDataSourceDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testAddDataSourceWithDefaultChannel() {
		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject());

		Assert.assertNotNull(dataSourceJSONObject.getString("channelId"));
	}

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testChannelsCleared() throws Exception {
		_faroInfoDataSourceDog.disconnectDataSource("405201047787757795");

		Assert.assertEquals(
			0,
			elasticsearchInvoker.count(
				"channels",
				QueryBuilders.termQuery(
					"dataSources.id", "405201047787757795")));

		Assert.assertEquals(
			2,
			elasticsearchInvoker.count(
				"channels",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery(
						"dataSources.id", "405201047787757795")
				).filter(
					QueryBuilders.existsQuery("dataSources")
				)));
	}

	@Test
	public void testDisconnectDataSource() throws Exception {
		JSONObject dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"Token Authentication", "Liferay", "http://localhost:8080"));

		Assert.assertEquals(
			"CREDENTIALS_VALID", dataSourceJSONObject.optString("state"));
		Assert.assertEquals("ACTIVE", dataSourceJSONObject.optString("status"));

		dataSourceJSONObject = _faroInfoDataSourceDog.disconnectDataSource(
			dataSourceJSONObject.getString("id"));

		Assert.assertEquals(
			"DISCONNECTED", dataSourceJSONObject.optString("state"));
		Assert.assertEquals(
			"INACTIVE", dataSourceJSONObject.optString("status"));
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSwitchFromOAuthtoToken() throws Exception {
		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.patchDataSource(
				"405201047787757796",
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
					"Token Authentication", "Liferay",
					"http://localhost:8080"));

		JSONObject credentialsJSONObject = dataSourceJSONObject.getJSONObject(
			"credentials");

		Assert.assertEquals(
			"Token Authentication", credentialsJSONObject.getString("type"));
		Assert.assertNotNull(credentialsJSONObject.getString("privateKey"));
		Assert.assertNotNull(credentialsJSONObject.getString("publicKey"));
		Assert.assertNull(credentialsJSONObject.opt("oAuthAuthorizationURL"));

		ArgumentCaptor<JSONObject> argumentCaptor = ArgumentCaptor.forClass(
			JSONObject.class);

		Mockito.verify(
			_dxpExtractorConfigurationDog, Mockito.times(1)
		).updateConfiguration(
			argumentCaptor.capture()
		);

		dataSourceJSONObject = argumentCaptor.getValue();

		credentialsJSONObject = dataSourceJSONObject.getJSONObject(
			"credentials");

		Assert.assertNull(credentialsJSONObject.opt("oAuthClientId"));
		Assert.assertNull(credentialsJSONObject.opt("oAuthClientSecret"));
		Assert.assertNull(credentialsJSONObject.opt("oAuthOwner"));
		Assert.assertNull(credentialsJSONObject.opt("oAuthRefreshToken"));
	}

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private DXPExtractorConfigurationDog _dxpExtractorConfigurationDog;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}