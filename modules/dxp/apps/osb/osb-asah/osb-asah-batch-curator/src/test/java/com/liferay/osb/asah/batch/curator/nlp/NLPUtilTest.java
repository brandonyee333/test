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

package com.liferay.osb.asah.batch.curator.nlp;

import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vishal Reddy
 */
public class NLPUtilTest {

	@Test
	public void testGetKeywords() {
		Assert.assertEquals(
			new HashSet<String>() {
				{
					add("EE");
					add("License");
					add("Liferay Portal Enterprise Edition");
					add("Liferay Portal Enterprise Edition License");
				}
			},
			NLPUtil.getKeywords(
				"Liferay Portal Enterprise Edition (EE) License"));
	}

	@Test
	public void testGetKeywords_Description() throws Exception {
		JSONArray descriptionKeywordsJSONArray =
			ResourceUtil.readResourceToJSONArray(
				"keywords_description.json", this);

		_assertScoreWithinBounds(
			_getFScore(descriptionKeywordsJSONArray), 0.75);
	}

	@Test
	public void testGetKeywords_Title() throws Exception {
		JSONArray titleKeywordsJSONArray = ResourceUtil.readResourceToJSONArray(
			"keywords_title.json", this);

		_assertScoreWithinBounds(_getFScore(titleKeywordsJSONArray), 0.725);
	}

	@Test
	public void testIsEnglish_Japanese() {
		List<String> strings = Arrays.asList(
			"liferay 7のservicebuilderと外部データベース",
			"liferay dxp 7.2 service pack1の新しい検索機能 ： 同義語 (シノニム ）",
			"liferay dxp 7.2 とoffice365との統合", "liferay開発", "とoffice365との統合",
			"イントラネット", "デジタルトランスフォーメーション", "技術者向け", "用語解説");

		for (String string : strings) {
			Assert.assertFalse(NLPUtil.isEnglish(string));
		}
	}

	@Test
	public void testIsEnglish_LongPhrase() {
		Assert.assertTrue(
			NLPUtil.isEnglish("She sells seashells by the seashore."));
	}

	@Test
	public void testIsEnglish_ShortPhrase() {
		Assert.assertTrue(NLPUtil.isEnglish("hello world"));
	}

	@Test
	public void testIsNotEnglish_Word() {
		Assert.assertFalse(NLPUtil.isEnglish("khatam"));
	}

	private void _assertScoreWithinBounds(
		double actualScore, double expectedScore) {

		_assertScoreWithinBounds(
			actualScore, expectedScore * _PERCENTAGE_TOLERANCE_LOWER_BOUND,
			expectedScore * _PERCENTAGE_TOLERANCE_UPPER_BOUND);
	}

	private void _assertScoreWithinBounds(
		double score, double lower, double upper) {

		Assert.assertThat(
			score,
			Matchers.allOf(
				Matchers.greaterThanOrEqualTo(lower),
				Matchers.lessThanOrEqualTo(upper)));
	}

	private int _getCorrectKeywordsCount(
		Set<String> actualKeywords, JSONArray expectedKeywordsJSONArray) {

		int count = 0;

		for (String actualKeyword : actualKeywords) {
			String normalizedActualKeyword = _normalizeKeyword(actualKeyword);

			for (int i = 0; i < expectedKeywordsJSONArray.length(); i++) {
				String expectedKeyword = expectedKeywordsJSONArray.getString(i);

				String normalizedExpectedKeyword = _normalizeKeyword(
					expectedKeyword);

				if (normalizedExpectedKeyword.equals(normalizedActualKeyword)) {
					count++;

					break;
				}
			}
		}

		return count;
	}

	private double _getFScore(JSONArray jsonArray) {
		int actualKeywordsCount = 0;
		int correctKeywordsCount = 0;
		int expectedKeywordsCount = 0;

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String text = jsonObject.getString("text");

			Set<String> textKeywords = NLPUtil.getKeywords(text);

			actualKeywordsCount += textKeywords.size();

			JSONArray keywordsJSONArray = jsonObject.getJSONArray("keywords");

			expectedKeywordsCount += keywordsJSONArray.length();

			correctKeywordsCount += _getCorrectKeywordsCount(
				textKeywords, keywordsJSONArray);
		}

		double precision = (double)correctKeywordsCount / actualKeywordsCount;
		double recall = (double)correctKeywordsCount / expectedKeywordsCount;

		return 2.0 * ((precision * recall) / (precision + recall));
	}

	private String _normalizeKeyword(String keyword) {
		String alphanumKeyword = keyword.replaceAll(
			_ALPHA_NUMERIC_SPACE_REGEX, "");

		String lowercaseAlphanumKeyword = alphanumKeyword.toLowerCase();

		return lowercaseAlphanumKeyword.trim();
	}

	private static final String _ALPHA_NUMERIC_SPACE_REGEX = "[^a-zA-Z ]";

	private static final double _PERCENTAGE_TOLERANCE_LOWER_BOUND = .99;

	private static final double _PERCENTAGE_TOLERANCE_UPPER_BOUND = 1.05;

}