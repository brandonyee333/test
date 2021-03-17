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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.backend.rest.controller.AdminRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.List;

import org.everit.json.schema.ValidationException;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ActiveProfiles({"AdminRestControllerTest", "test"})
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@EnableCaching
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class AdminRestControllerTest {

	@Test
	public void testClearCache() {
		Cache cache1 = _cacheManager.getCache(
			ProjectIdThreadLocal.getProjectId() + "#test");

		Assert.assertNotNull(cache1);

		cache1.put("foo", "bar");

		Cache cache2 = _cacheManager.getCache("test2#test");

		Assert.assertNotNull(cache2);

		cache2.put("foo", "bar");

		_adminRestController.clearCache();

		Assert.assertNull(cache1.get("foo"));
		Assert.assertNotNull(cache2.get("foo"));
	}

	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteData() {
		_adminRestController.deleteData(
			"accounts", String.valueOf(WeDeployDataService.OSB_ASAH_FARO_INFO));

		Assert.assertEquals(0, _elasticsearchInvoker.count("accounts", null));
	}

	@Test
	public void testPostData() throws Exception {
		_adminRestController.postData(
			"accounts", String.valueOf(WeDeployDataService.OSB_ASAH_FARO_INFO),
			ResourceUtil.readResourceToString(
				"dependencies/osbasahfaroinfo/accounts_1.json", this));

		Assert.assertEquals(3, _elasticsearchInvoker.count("accounts", null));
	}

	@Test
	public void testPostTask() {
		_adminRestController.postTask(
			"IndividualInterestScoresNanite",
			String.valueOf(
				JSONUtil.put(
					"dataSourceId", "123"
				).put(
					"type", "nanite"
				)));

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		Assert.assertEquals(asahTasks.toString(), 1, asahTasks.size());

		AsahTask asahTask = asahTasks.get(0);

		Assert.assertEquals(
			"IndividualInterestScoresNanite", asahTask.getClassName());
		Assert.assertNull(asahTask.getCronExpression());
		Assert.assertNotNull(asahTask.getId());

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"type", "nanite"
			),
			asahTask.getContextJSONObject(), false);
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testRun() {
		JSONArray jsonArray = new JSONArray();

		JSONArray osbAsahMarkersJSONArray = _elasticsearchInvoker.get(
			"OSBAsahMarkers");

		for (int i = 0; i < osbAsahMarkersJSONArray.length(); i++) {
			JSONObject osbAsahMarkerJSONObject =
				osbAsahMarkersJSONArray.getJSONObject(i);

			jsonArray.put(osbAsahMarkerJSONObject.getString("id"));
		}

		_adminRestController.run(jsonArray.toString());

		Assert.assertEquals(
			0, _elasticsearchInvoker.count("OSBAsahMarkers", null));
	}

	@Test(expected = ValidationException.class)
	public void testRunWithInvalidSchema() {
		JSONArray jsonArray = new JSONArray();

		jsonArray.put(
			JSONUtil.put(
				RandomTestUtil.randomString(), RandomTestUtil.randomString()));

		_adminRestController.run(jsonArray.toString());
	}

	@Profile("AdminRestControllerTest")
	@TestConfiguration
	public static class AdminRestControllerTestConfiguration {

		@Bean
		@Primary
		public CacheManager cacheManager() {
			return new ConcurrentMapCacheManager("test#test", "test2#test");
		}

	}

	@Autowired
	private AdminRestController _adminRestController;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private CacheManager _cacheManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}