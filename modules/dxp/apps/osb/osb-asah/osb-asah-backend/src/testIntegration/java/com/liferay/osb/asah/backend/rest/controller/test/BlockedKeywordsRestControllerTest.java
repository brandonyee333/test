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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.rest.controller.BlockedKeywordsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dto.BlockedKeywordDTO;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
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

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteBlockedKeywords() {
		_blockedKeywordsRestController.deleteBlockedKeywords(
			Arrays.asList(351238757269547424L, 351238757269547425L));

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
	@Test
	public void testGetBlockedKeyword() {
		BlockedKeywordDTO blockedKeywordDTO =
			_blockedKeywordsRestController.getBlockedKeyword(
				351238757269547424L);

		Assert.assertEquals(
			"2019-01-02T17:09:07.666Z",
			DateUtil.toUTCString(blockedKeywordDTO.getCreateDate()));
		Assert.assertEquals("351238757269547424", blockedKeywordDTO.getId());
		Assert.assertEquals("liferay", blockedKeywordDTO.getKeyword());
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetBlockedKeywordsFilter() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_filter.json", this),
			_objectMapper.convertValue(
				_blockedKeywordsRestController.getBlockedKeywordDTOsPageDTO(
					"web", 0, 2, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_filter_number.json",
				this),
			_objectMapper.convertValue(
				_blockedKeywordsRestController.getBlockedKeywordDTOsPageDTO(
					"1500", 0, 1, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_filter_number.json",
				this),
			_objectMapper.convertValue(
				_blockedKeywordsRestController.getBlockedKeywordDTOsPageDTO(
					"1500", 0, 1, null),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetBlockedKeywordsPagination() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_page_0.json", this),
			_objectMapper.convertValue(
				_blockedKeywordsRestController.getBlockedKeywordDTOsPageDTO(
					null, 0, 2, null),
				JSONObject.class),
			false);
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_page_1.json", this),
			_objectMapper.convertValue(
				_blockedKeywordsRestController.getBlockedKeywordDTOsPageDTO(
					null, 1, 2, null),
				JSONObject.class),
			false);
	}

	@ElasticsearchIndex(
		name = "blocked-keywords", resourcePath = "blocked_keywords.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetBlockedKeywordsSort() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_sort_date.json", this),
			_objectMapper.convertValue(
				_blockedKeywordsRestController.getBlockedKeywordDTOsPageDTO(
					null, 0, 2, new String[] {"createDate,desc"}),
				JSONObject.class),
			false);

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/expected_blocked_keywords_sort_keyword.json",
				this),
			_objectMapper.convertValue(
				_blockedKeywordsRestController.getBlockedKeywordDTOsPageDTO(
					null, 0, 8, new String[] {"keyword.raw,asc"}),
				JSONObject.class),
			false);
	}

	@Test
	public void testPostBlockedKeywords() {
		BlockedKeywordDTO blockedKeywordDTO =
			_blockedKeywordsRestController.postBlockedKeywords(
				JSONUtil.put(
					"keywords", JSONUtil.putAll("Liferay", "DXP", "Portal", " ")
				).toString());

		Assert.assertTrue(blockedKeywordDTO.getSucceeded());

		List<BlockedKeywordDTO> blockedKeywordDTOs =
			blockedKeywordDTO.getBlockedKeywordDTOs();

		Assert.assertEquals(
			blockedKeywordDTOs.toString(), 3, blockedKeywordDTOs.size());

		_assertBlockedKeyword(blockedKeywordDTOs, "liferay");
		_assertBlockedKeyword(blockedKeywordDTOs, "dxp");
		_assertBlockedKeyword(blockedKeywordDTOs, "portal");
	}

	@Test
	public void testPostEmptyBlockedKeywords() {
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
	public void testPostExistingBlockedKeywords() {
		BlockedKeywordDTO blockedKeywordDTO =
			_blockedKeywordsRestController.postBlockedKeywords(
				JSONUtil.put(
					"keywords", JSONUtil.putAll("Liferay", "AC", "Portal")
				).toString());

		List<BlockedKeywordDTO> blockedKeywordDTOs =
			blockedKeywordDTO.getBlockedKeywordDTOs();

		Assert.assertEquals(
			blockedKeywordDTOs.toString(), 3, blockedKeywordDTOs.size());

		Assert.assertFalse(_isDuplicate(blockedKeywordDTOs, "ac"));
		Assert.assertTrue(_isDuplicate(blockedKeywordDTOs, "liferay"));
		Assert.assertTrue(_isDuplicate(blockedKeywordDTOs, "portal"));
	}

	private void _assertBlockedKeyword(
		List<BlockedKeywordDTO> blockedKeywordDTOs, String expectedKeyword) {

		BlockedKeywordDTO actualBlockedKeywordDTO = _getBlockedKeyword(
			blockedKeywordDTOs, expectedKeyword);

		Assert.assertNotNull(
			"Blocked keyword not found", actualBlockedKeywordDTO);
		Assert.assertEquals(
			expectedKeyword, actualBlockedKeywordDTO.getKeyword());
		Assert.assertTrue(actualBlockedKeywordDTO.getCreateDate() != null);
		Assert.assertTrue(actualBlockedKeywordDTO.getId() != null);
	}

	private BlockedKeywordDTO _getBlockedKeyword(
		List<BlockedKeywordDTO> blockedKeywordDTOs, String keyword) {

		for (BlockedKeywordDTO blockedKeywordDTO : blockedKeywordDTOs) {
			if (Objects.equals(blockedKeywordDTO.getKeyword(), keyword)) {
				return blockedKeywordDTO;
			}
		}

		return null;
	}

	private boolean _isDuplicate(
		List<BlockedKeywordDTO> blockedKeywordDTOs, String keyword) {

		BlockedKeywordDTO blockedKeywordDTO = _getBlockedKeyword(
			blockedKeywordDTOs, keyword);

		Assert.assertNotNull("Blocked keyword not found", blockedKeywordDTO);

		return BooleanUtils.toBoolean(blockedKeywordDTO.getDuplicate());
	}

	@Autowired
	private BlockedKeywordsRestController _blockedKeywordsRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}