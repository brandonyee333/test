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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.elasticsearch.index.query.QueryBuilders;

import org.everit.json.schema.ValidationException;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@EnableCaching
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@TestPropertySource(
	properties = "osb.asah.backend.admin.rest.controller.enabled=true"
)
public class AdminRestControllerTest {

	@Test
	public void testClearCache() {
		Mockito.doAnswer(
			invocationOnMock -> _cacheMap.get(
				invocationOnMock.getArgumentAt(0, String.class))
		).when(
			_cacheManager
		).getCache(
			Mockito.anyString()
		);

		Mockito.doAnswer(
			invocationOnMock -> _cacheMap.keySet()
		).when(
			_cacheManager
		).getCacheNames();

		Cache cache = new Cache() {

			@Override
			public void clear() {
				_map.clear();
			}

			@Override
			public void evict(Object key) {
			}

			@Override
			public ValueWrapper get(Object key) {
				return () -> _map.getOrDefault(String.valueOf(key), null);
			}

			@Override
			public <T> T get(Object key, Callable<T> valueLoader) {
				return null;
			}

			@Override
			public <T> T get(Object key, Class<T> type) {
				return null;
			}

			@Override
			public String getName() {
				return null;
			}

			@Override
			public Object getNativeCache() {
				return null;
			}

			@Override
			public void put(Object key, Object value) {
				_map.put(String.valueOf(key), value);
			}

			@Override
			public ValueWrapper putIfAbsent(Object key, Object value) {
				return null;
			}

			private final Map<String, Object> _map = new HashMap<>();

		};

		cache.put("foo", "bar");

		_cacheMap.put("test", cache);

		Cache.ValueWrapper valueWrapper = cache.get("foo");

		Assert.assertNotNull(valueWrapper.get());

		_adminRestController.clearCache();

		valueWrapper = cache.get("foo");

		Assert.assertNull(valueWrapper.get());
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

		JSONAssert.assertEquals(
			JSONUtil.put(
				"className", "IndividualInterestScoresNanite"
			).put(
				"context",
				JSONUtil.put(
					"dataSourceId", "123"
				).put(
					"type", "nanite"
				)
			),
			_elasticsearchInvoker.fetch(
				"OSBAsahTasks",
				QueryBuilders.termQuery(
					"className", "IndividualInterestScoresNanite")),
			false);
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

	@Autowired
	@InjectMocks
	private AdminRestController _adminRestController;

	@Mock
	private CacheManager _cacheManager;

	private final Map<String, Cache> _cacheMap = new HashMap<>();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}