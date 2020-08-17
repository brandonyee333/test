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

import com.liferay.osb.asah.backend.rest.controller.InterestsRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;

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
 * @author Victor Oliveira
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class InterestsRestControllerTest {

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetInterestKeywords() throws Exception {

		// No search

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_interest_keywords.json", this),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_interestsRestController.getInterestKeywords(null, 0, 20)),
				"JSONObject/_embedded", "JSONArray/interest-keywords"),
			false);

		// Search

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_interest_keywords_search.json", this),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_interestsRestController.getInterestKeywords(
						"compel", 0, 20)),
				"JSONObject/_embedded", "JSONArray/interest-keywords"),
			false);
	}

	@Test
	public void testGetInterestKeywordsNoAssets() throws Exception {
		JSONArray keywordsJSONArray = (JSONArray)JSONUtil.getValue(
			new JSONObject(
				_interestsRestController.getInterestKeywords(null, 0, 20)),
			"JSONObject/_embedded", "JSONArray/interest-keywords");

		Assert.assertEquals(0, keywordsJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetInterests() throws Exception {
		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_interests.json", this),
			(JSONArray)JSONUtil.getValue(
				new JSONObject(
					_interestsRestController.getInterests(
						null, 0, 20, null, null)),
				"JSONObject/_embedded", "JSONArray/interests"),
			false);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "interest-topics", resourcePath = "interest_topics.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetTerms() throws Exception {
		_assertTermsByTopic(
			new JSONObject(
				_interestsRestController.getTerms(
					3, 0.01, 3, "953be104-5540-abf8-59b8-55f895200acc")),
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_interest_topics_per_user.json", this));
	}

	@Test
	public void testGetTermsOverLengthLimit() throws Exception {
		try {
			_interestsRestController.getTerms(100, 0.01, 11, "xxx-yyy-zzz");
		}
		catch (OSBAsahException osbae) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, osbae.getHttpStatus());
			Assert.assertEquals(
				"termsPerTopic * topicsLength must not exceed 1000",
				osbae.getMessage());
		}
	}

	@ElasticsearchIndex(
		name = "interest-topics", resourcePath = "interest_topics.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetTermsRelated() throws Exception {
		_assertTerms(
			new JSONObject(
				_interestsRestController.getTermsRelated(
					0, 5, Arrays.asList("javascript", "php"), 0.01)),
			"jquery", "html", "sql", "mysql", "java");
	}

	@Test
	public void testGetTermsRelatedOverPageLimit() throws Exception {
		try {
			_interestsRestController.getTermsRelated(
				10, 15, Collections.emptyList(), 0.01);
		}
		catch (OSBAsahException osbae) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, osbae.getHttpStatus());
			Assert.assertEquals(
				"page * size + size must not exceed 100", osbae.getMessage());
		}
	}

	@ElasticsearchIndex(
		name = "interest-topics", resourcePath = "interest_topics.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetTermsRelatedPaged() throws Exception {
		_assertTerms(
			new JSONObject(
				_interestsRestController.getTermsRelated(
					1, 5, Arrays.asList("javascript", "php"), 0.01)),
			"css", "canvas", "sql-server", "python", "multithreading");
	}

	@ElasticsearchIndex(
		name = "interest-topics", resourcePath = "interest_topics.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetTermsRelatedWithSizeGreaterThanTheDefaultBucketSize()
		throws Exception {

		_assertTerms(
			new JSONObject(
				_interestsRestController.getTermsRelated(
					0, 10, Arrays.asList("javascript", "php"), 0.01)),
			"jquery", "html", "css", "sql", "mysql", "sql-server", "java",
			"android", "multithreading", "net");
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetTermsWithNoIndividualInterestTerms() throws Exception {
		_assertTermsByTopic(
			new JSONObject(
				_interestsRestController.getTerms(
					3, 0.01, 3, "5666a530-7021-11e9-9f43-21e859277abc")),
			new JSONArray());
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "interests", resourcePath = "interests.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetTermsWithNoTopicsRelatedToIndividualInterestTerms()
		throws Exception {

		_assertTermsByTopic(
			new JSONObject(
				_interestsRestController.getTerms(
					3, 0.01, 3, "953be104-5540-abf8-59b8-55f895200acc")),
			new JSONArray());
	}

	@Test
	public void testGetTermsWithUnknownIndividual() throws Exception {
		try {
			_interestsRestController.getTerms(3, 0.01, 3, "xxx-yyy-zzz");
		}
		catch (OSBAsahException osbae) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, osbae.getHttpStatus());
			Assert.assertEquals(
				"There is no individual with user ID xxx-yyy-zzz",
				osbae.getMessage());
		}
	}

	private void _assertTerms(
		JSONObject responseJSONObject, String... expectedTerms) {

		JSONArray interestTopicsJSONArray = (JSONArray)JSONUtil.getValue(
			responseJSONObject, "JSONObject/_embedded",
			"JSONArray/interest-terms");

		for (int i = 0; i < interestTopicsJSONArray.length(); i++) {
			Assert.assertEquals(
				expectedTerms[i], interestTopicsJSONArray.getString(i));
		}
	}

	private void _assertTermsByTopic(
		JSONObject responseJSONObject, JSONArray expectedTerms) {

		JSONAssert.assertEquals(
			expectedTerms,
			(JSONArray)JSONUtil.getValue(
				responseJSONObject, "JSONObject/_embedded",
				"JSONArray/interest-topics"),
			false);
	}

	@Autowired
	private InterestsRestController _interestsRestController;

}