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

package com.liferay.osb.asah.stream.curator.bot.nanite.individual.test;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.http.QueueHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.individual.IndividualNanite;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.apache.commons.codec.digest.DigestUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class IndividualNaniteTest {

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "blog_info_old.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_1_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testIndividualResolution() throws Exception {
		Mockito.when(
			_queueHttp.getMessagesCount(Mockito.anyString())
		).thenReturn(
			1
		);

		Mockito.when(
			_queueHttp.getMessages(Mockito.anyString())
		).thenReturn(
			JSONUtil.put(
				"messages",
				JSONUtil.putAll(
					JSONUtil.put(
						"message",
						JSONUtil.put(
							"analyticsData", new JSONObject()
						).put(
							"channelId", "1"
						).put(
							"dataSourceId", "1"
						).put(
							"emailAddressHashed",
							DigestUtils.sha256Hex("john@liferay.com")
						).put(
							"userId", "1"
						).toString()),
					JSONUtil.put(
						"message",
						JSONUtil.put(
							"analyticsData", new JSONObject()
						).put(
							"channelId", "2"
						).put(
							"dataSourceId", "2"
						).put(
							"emailAddressHashed",
							DigestUtils.sha256Hex("jane@liferay.com")
						).put(
							"userId", "1"
						).toString()))
			).toString()
		);

		_individualNanite.run();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get("blogs");

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/osbasahcerebroinfo/blog_info_new.json", this),
			jsonArray, false);
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testMergeIndividual() {
		Mockito.when(
			_queueHttp.getMessagesCount(Mockito.anyString())
		).thenReturn(
			1
		);

		Mockito.when(
			_queueHttp.getMessages(Mockito.anyString())
		).thenReturn(
			JSONUtil.put(
				"messages",
				JSONUtil.putAll(
					JSONUtil.put(
						"message",
						JSONUtil.put(
							"analyticsData", new JSONObject()
						).put(
							"channelId", "1"
						).put(
							"dataSourceId", "1"
						).put(
							"emailAddressHashed",
							DigestUtils.sha256Hex("john@liferay.com")
						).put(
							"userId", "2"
						).toString()))
			).toString()
		);

		Assert.assertTrue(
			_faroInfoElasticsearchInvoker.exists("individuals", "200"));

		_individualNanite.run();

		Assert.assertFalse(
			_faroInfoElasticsearchInvoker.exists("individuals", "200"));

		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery(
				"demographics.email.value", "john@liferay.com"));

		JSONArray activitiesCounts = individualJSONObject.getJSONArray(
			"activitiesCounts");

		JSONObject activitiesCount = JSONUtil.find(
			activitiesCounts, "channelId", "1");

		Assert.assertEquals(2, activitiesCount.get("activitiesCount"));

		activitiesCount = JSONUtil.find(activitiesCounts, "channelId", "2");

		Assert.assertEquals(1, activitiesCount.get("activitiesCount"));

		Assert.assertFalse(
			_cerebroInfoElasticsearchInvoker.exists(
				"pages",
				BoolQueryBuilderUtil.shouldNot(
					QueryBuilders.termQuery("individualId", "100")
				).should(
					QueryBuilders.termQuery("knownIndividual", false)
				)));
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "blog_info_old.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_3_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testSkipUpdatePageAndAsset() {
		Mockito.when(
			_queueHttp.getMessagesCount(Mockito.anyString())
		).thenReturn(
			1
		);

		Mockito.when(
			_queueHttp.getMessages(Mockito.anyString())
		).thenReturn(
			JSONUtil.put(
				"messages",
				JSONUtil.putAll(
					JSONUtil.put(
						"message",
						JSONUtil.put(
							"analyticsData", new JSONObject()
						).put(
							"channelId", "1"
						).put(
							"dataSourceId", "1"
						).put(
							"emailAddressHashed",
							DigestUtils.sha256Hex("john@liferay.com")
						).put(
							"userId", "1"
						).toString()),
					JSONUtil.put(
						"message",
						JSONUtil.put(
							"analyticsData", new JSONObject()
						).put(
							"channelId", "2"
						).put(
							"dataSourceId", "2"
						).put(
							"emailAddressHashed",
							DigestUtils.sha256Hex("jane@liferay.com")
						).put(
							"userId", "1"
						).toString()))
			).toString()
		);

		_individualNanite.run();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get("blogs");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject blogJSONObject = jsonArray.getJSONObject(i);

			Assert.assertFalse(blogJSONObject.getBoolean("knownIndividual"));
		}
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "suppressions", resourcePath = "suppressions.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testSuppressedUserUpdate() {
		Mockito.when(
			_queueHttp.getMessagesCount(Mockito.anyString())
		).thenReturn(
			1
		);

		Mockito.when(
			_queueHttp.getMessages(Mockito.anyString())
		).thenReturn(
			JSONUtil.put(
				"messages",
				JSONUtil.putAll(
					JSONUtil.put(
						"message",
						JSONUtil.put(
							"analyticsData", new JSONObject()
						).put(
							"channelId", "1"
						).put(
							"dataSourceId", "1"
						).put(
							"emailAddressHashed",
							DigestUtils.sha256Hex("john@liferay.com")
						).put(
							"userId", "2"
						).toString()))
			).toString()
		);

		_individualNanite.run();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get(
			"user-sessions");

		Assert.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assert.assertEquals("200", jsonObject.get("individualId"));
		Assert.assertEquals("2", jsonObject.get("userId"));

		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery(
				"demographics.email.value", "john@liferay.com"));

		Assert.assertFalse(individualJSONObject.has("lastEnrichmentDate"));
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_2_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testUserSessionUpdate() {
		Mockito.when(
			_queueHttp.getMessagesCount(Mockito.anyString())
		).thenReturn(
			1
		);

		Mockito.when(
			_queueHttp.getMessages(Mockito.anyString())
		).thenReturn(
			JSONUtil.put(
				"messages",
				JSONUtil.putAll(
					JSONUtil.put(
						"message",
						JSONUtil.put(
							"analyticsData", new JSONObject()
						).put(
							"channelId", "1"
						).put(
							"dataSourceId", "1"
						).put(
							"emailAddressHashed",
							DigestUtils.sha256Hex("john@liferay.com")
						).put(
							"userId", "2"
						).toString()))
			).toString()
		);

		_individualNanite.run();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get(
			"user-sessions");

		Assert.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assert.assertEquals("100", jsonObject.get("individualId"));
		Assert.assertEquals("2", jsonObject.get("userId"));

		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery(
				"demographics.email.value", "john@liferay.com"));

		Assert.assertTrue(individualJSONObject.has("lastEnrichmentDate"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private IndividualNanite _individualNanite;

	@MockBean
	private QueueHttp _queueHttp;

}