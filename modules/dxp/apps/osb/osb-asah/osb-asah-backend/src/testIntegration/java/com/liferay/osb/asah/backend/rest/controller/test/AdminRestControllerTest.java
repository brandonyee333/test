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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.rest.controller.AdminRestController;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.List;

import org.everit.json.schema.ValidationException;

import org.json.JSONArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * @author Vishal Reddy
 */
public class AdminRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testClearCache() {
		Cache cache1 = _cacheManager.getCache(
			ProjectIdThreadLocal.getProjectId() + "#test");

		Assertions.assertNotNull(cache1);

		cache1.put("foo", "bar");

		Cache cache2 = _cacheManager.getCache("test2#test");

		Assertions.assertNotNull(cache2);

		cache2.put("foo", "bar");

		_adminRestController.clearCache();

		Assertions.assertNull(cache1.get("foo"));
		Assertions.assertNotNull(cache2.get("foo"));
	}

	@Test
	public void testPostTask() {
		_adminRestController.postTask(
			"IndividualInterestScoresNanite",
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"type", "nanite"
			).toString());

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		Assertions.assertEquals(1, asahTasks.size(), asahTasks.toString());

		AsahTask asahTask = asahTasks.get(0);

		Assertions.assertEquals(
			"IndividualInterestScoresNanite", asahTask.getClassName());
		Assertions.assertNull(asahTask.getCronExpression());
		Assertions.assertNotNull(asahTask.getId());

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dataSourceId", "123"
			).put(
				"type", "nanite"
			),
			asahTask.getContextJSONObject(), false);
	}

	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahfaroinfo/osbasahmarkers.json"
	)
	@Test
	public void testRun() {
		Iterable<AsahMarker> asahMarkers = _asahMarkerRepository.findAll();

		JSONArray jsonArray = new JSONArray();

		for (AsahMarker asahMarker : asahMarkers) {
			jsonArray.put(asahMarker.getId());
		}

		_adminRestController.run(jsonArray.toString());

		Assertions.assertEquals(0, _asahMarkerRepository.count());
	}

	@Test
	public void testRunWithInvalidSchema() {
		JSONArray jsonArray = new JSONArray();

		jsonArray.put(
			JSONUtil.put(
				RandomTestUtil.randomString(), RandomTestUtil.randomString()));

		Assertions.assertThrows(
			ValidationException.class,
			() -> _adminRestController.run(jsonArray.toString()));
	}

	@Autowired
	private AdminRestController _adminRestController;

	@Autowired
	private AsahMarkerRepository _asahMarkerRepository;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private CacheManager _cacheManager;

}