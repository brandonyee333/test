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

import com.liferay.osb.asah.backend.rest.controller.BlockedKeywordsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class BlockedKeywordsRestControllerTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteBlockedKeywords() {
		_blockedKeywordsRestController.deleteBlockedKeywords(
			Arrays.asList("351238757269547424", "351238757269547425"));

		Assert.assertFalse(
			_elasticsearchInvoker.exists(
				"blocked-keywords", "351238757269547424"));
		Assert.assertFalse(
			_elasticsearchInvoker.exists(
				"blocked-keywords", "351238757269547425"));
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteBlockedKeywordsWithEmptyIDs() {
		try {
			_blockedKeywordsRestController.deleteBlockedKeywords(
				Collections.emptyList());
		}
		catch (OSBAsahException osbae) {
			Assert.assertEquals(
				"Empty blocked keyword IDs", osbae.getMessage());
		}
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetBlockedKeyword() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keyword.json", this),
			_blockedKeywordsRestController.getBlockedKeyword(
				"351238757269547424"),
			false);
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetBlockedKeywordsFilter() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_filter.json", this),
			_blockedKeywordsRestController.getBlockedKeywords(
				"contains(keyword.raw, 'web')", 0, 2, null),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_filter_number.json",
				this),
			_blockedKeywordsRestController.getBlockedKeywords(
				"contains(keyword.raw, '1500')", 0, 1, null),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_filter_number.json",
				this),
			_blockedKeywordsRestController.getBlockedKeywords(
				"contains(keyword.raw, '1500s')", 0, 1, null),
			false);
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetBlockedKeywordsPagination() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_page_0.json", this),
			_blockedKeywordsRestController.getBlockedKeywords(null, 0, 2, null),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_page_1.json", this),
			_blockedKeywordsRestController.getBlockedKeywords(null, 1, 2, null),
			false);
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetBlockedKeywordsSort() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_sort_date.json", this),
			_blockedKeywordsRestController.getBlockedKeywords(
				null, 0, 2, new String[] {"createDate,desc"}),
			false);

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_sort_keyword.json",
				this),
			_blockedKeywordsRestController.getBlockedKeywords(
				null, 0, 8, new String[] {"keyword.raw,asc"}),
			false);
	}

	@Test
	public void testPostBlockedKeywords() throws Exception {
		JSONObject responseJSONObject = new JSONObject(
			_blockedKeywordsRestController.postBlockedKeywords(
				JSONUtil.put(
					"keywords", JSONUtil.putAll("Liferay", "DXP", "Portal", " ")
				).toString()));

		Assert.assertTrue(responseJSONObject.getBoolean("succeeded"));

		JSONArray blockedKeywordsJSONArray = responseJSONObject.getJSONArray(
			"blocked-keywords");

		Assert.assertEquals(3, blockedKeywordsJSONArray.length());

		_assertBlockedKeyword(blockedKeywordsJSONArray, "liferay");
		_assertBlockedKeyword(blockedKeywordsJSONArray, "dxp");
		_assertBlockedKeyword(blockedKeywordsJSONArray, "portal");
	}

	@Test
	public void testPostEmptyBlockedKeywords() throws Exception {
		try {
			_blockedKeywordsRestController.postBlockedKeywords(
				JSONUtil.put(
					"keywords", new JSONArray()
				).toString());
		}
		catch (OSBAsahException osbae) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, osbae.getHttpStatus());
			Assert.assertEquals("Empty keywords", osbae.getMessage());
		}
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testPostExistingBlockedKeywords() throws Exception {
		JSONObject jsonObject = new JSONObject(
			_blockedKeywordsRestController.postBlockedKeywords(
				JSONUtil.put(
					"keywords", JSONUtil.putAll("Liferay", "AC", "Portal")
				).toString()));

		JSONArray blockedKeywordsJSONArray = jsonObject.getJSONArray(
			"blocked-keywords");

		Assert.assertEquals(3, blockedKeywordsJSONArray.length());

		Assert.assertFalse(_isDuplicate(blockedKeywordsJSONArray, "ac"));
		Assert.assertTrue(_isDuplicate(blockedKeywordsJSONArray, "liferay"));
		Assert.assertTrue(_isDuplicate(blockedKeywordsJSONArray, "portal"));
	}

	private void _assertBlockedKeyword(
		JSONArray blockedKeywordsJSONArray, String expectedKeyword) {

		JSONObject actualBlockedKeywordJSONObject = _getBlockedKeyword(
			blockedKeywordsJSONArray, expectedKeyword);

		Assert.assertNotNull(
			"Blocked keyword not found", actualBlockedKeywordJSONObject);
		Assert.assertEquals(
			expectedKeyword,
			actualBlockedKeywordJSONObject.getString("keyword"));
		Assert.assertTrue(actualBlockedKeywordJSONObject.has("createDate"));
		Assert.assertTrue(actualBlockedKeywordJSONObject.has("id"));
	}

	private JSONObject _getBlockedKeyword(
		JSONArray blockedKeywordsJSONArray, String keyword) {

		for (int i = 0; i < blockedKeywordsJSONArray.length(); i++) {
			JSONObject blockedKeywordsJSONObject =
				blockedKeywordsJSONArray.getJSONObject(i);

			if (Objects.equals(
					keyword, blockedKeywordsJSONObject.getString("keyword"))) {

				return blockedKeywordsJSONObject;
			}
		}

		return null;
	}

	private boolean _isDuplicate(
		JSONArray blockedKeywordsJSONArray, String keyword) {

		JSONObject jsonObject = _getBlockedKeyword(
			blockedKeywordsJSONArray, keyword);

		Assert.assertNotNull("Blocked keyword not found", jsonObject);

		return jsonObject.getBoolean("duplicate");
	}

	@Autowired
	private BlockedKeywordsRestController _blockedKeywordsRestController;

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}