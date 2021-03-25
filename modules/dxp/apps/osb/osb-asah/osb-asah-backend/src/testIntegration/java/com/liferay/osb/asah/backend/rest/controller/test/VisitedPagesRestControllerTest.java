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

import com.liferay.osb.asah.backend.rest.controller.VisitedPagesRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class VisitedPagesRestControllerTest {

	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited_pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetVisitedPage() throws Exception {
		JSONAssert.assertEquals(
			_elasticsearchInvoker.fetch("visited-pages", "356972058733468926"),
			new JSONObject(
				_visitedPagesRestController.getVisitedPages(
					"356972058733468926")),
			false);
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited_pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetVisitedPages() throws Exception {

		// Not visited pages

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_visited_pages_not_visited.json", this),
			new JSONObject(
				_visitedPagesRestController.getVisitedPages(
					null, 356970616429554152L, "individual", 0, 20,
					new String[] {"title", "asc"}, false)),
			false);

		// Not visited pages with interest

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_visited_pages_not_visited_interest.json",
				this),
			new JSONObject(
				_visitedPagesRestController.getVisitedPages(
					"interestName eq 'compelling action-items'",
					356970540447478387L, "individual", 0, 20,
					new String[] {"title", "asc"}, false)),
			false);

		// Visited pages

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_visited_pages_visited.json", this),
			new JSONObject(
				_visitedPagesRestController.getVisitedPages(
					null, 356970527927171432L, "individual", 0, 20,
					new String[] {"title", "asc"}, true)),
			false);

		// Visited pages with interest

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/expected_visited_pages_visited_interest.json",
				this),
			new JSONObject(
				_visitedPagesRestController.getVisitedPages(
					"interestName eq 'revolutionary ROI'", 356970527927171432L,
					"individual", 0, 20, new String[] {"title", "asc"}, true)),
			false);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private VisitedPagesRestController _visitedPagesRestController;

}