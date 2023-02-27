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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.entity.BQIdentityInterestScore;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.postgresql.converter.helper.InterestFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.BQIdentityInterestScoreRepository;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Robson Pastor
 */
public class BQIdentityInterestScoreRepositoryTest {

	@BeforeEach
	public void setUp() {
		_bqIdentityInterestScore1 = new BQIdentityInterestScore();

		_bqIdentityInterestScore1.setIdentityId("374790569167317525");
		_bqIdentityInterestScore1.setInterested(Boolean.TRUE);
		_bqIdentityInterestScore1.setInterestScore(1.767661917648994);
		_bqIdentityInterestScore1.setKeyword("clicks-and-mortar e-tailers");
		_bqIdentityInterestScore1.setRecordedDate(
			DateUtil.toUTCDate("2021-09-12T00:00:00.000Z"));

		_bqIdentityInterestScoreRepository.insert(_bqIdentityInterestScore1);

		BQIdentity bqIdentity1 = new BQIdentity();

		bqIdentity1.setId("374790569167317525");
		bqIdentity1.setIndividualId("374790569167317525");

		_bqIdentityRepository.insert(bqIdentity1);

		_bqIdentityInterestScore2 = new BQIdentityInterestScore();

		_bqIdentityInterestScore2.setIdentityId("374790575409131096");
		_bqIdentityInterestScore2.setInterested(Boolean.TRUE);
		_bqIdentityInterestScore2.setInterestScore(2.61495977803619);
		_bqIdentityInterestScore2.setKeyword("javascript");
		_bqIdentityInterestScore2.setRecordedDate(
			DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		_bqIdentityInterestScoreRepository.insert(_bqIdentityInterestScore2);

		BQIdentity bqIdentity2 = new BQIdentity();

		bqIdentity2.setId("374790575409131096");
		bqIdentity2.setIndividualId("374790575409131096");

		_bqIdentityRepository.insert(bqIdentity2);

		_bqIdentityInterestScore3 = new BQIdentityInterestScore();

		_bqIdentityInterestScore3.setIdentityId("374790572703144534");
		_bqIdentityInterestScore3.setInterested(Boolean.FALSE);
		_bqIdentityInterestScore3.setInterestScore(0.770222520473574);
		_bqIdentityInterestScore3.setKeyword("compelling metrics");
		_bqIdentityInterestScore3.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		_bqIdentityInterestScoreRepository.insert(_bqIdentityInterestScore3);

		BQIdentity bqIdentity3 = new BQIdentity();

		bqIdentity3.setId("374790572703144534");
		bqIdentity3.setIndividualId("374790572703144534");

		_bqIdentityRepository.insert(bqIdentity3);

		_bqIdentityInterestScore4 = new BQIdentityInterestScore();

		_bqIdentityInterestScore4.setIdentityId("374790572703144534");
		_bqIdentityInterestScore4.setInterested(Boolean.TRUE);
		_bqIdentityInterestScore4.setInterestScore(1.454684984987494);
		_bqIdentityInterestScore4.setKeyword("sales");
		_bqIdentityInterestScore4.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		_bqIdentityInterestScoreRepository.insert(_bqIdentityInterestScore4);

		_bqIdentityInterestScore5 = new BQIdentityInterestScore();

		_bqIdentityInterestScore5.setIdentityId("374790572703144535");
		_bqIdentityInterestScore5.setInterested(Boolean.TRUE);
		_bqIdentityInterestScore5.setInterestScore(1.454684984987494);
		_bqIdentityInterestScore5.setKeyword("sales");
		_bqIdentityInterestScore5.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		_bqIdentityInterestScoreRepository.insert(_bqIdentityInterestScore5);

		BQIdentity bqIdentity5 = new BQIdentity();

		bqIdentity5.setId("374790572703144535");
		bqIdentity5.setIndividualId("374790572703144535");

		_bqIdentityRepository.insert(bqIdentity5);

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(new Date());
		bqMembership.setIndividualId("374790569167317525");
		bqMembership.setModifiedDate(new Date());
		bqMembership.setSegmentId(123L);
		bqMembership.setStatus("ACTIVE");

		_bqMembershipRepository.insert(bqMembership);
	}

	@Test
	public void testCountByFilterString() {
		Assertions.assertEquals(
			4,
			_bqIdentityInterestScoreRepository.countByFilterString(
				new FilterHelper(null)));
	}

	@Test
	public void testCountByIndividualId() {
		Assertions.assertEquals(
			2,
			_bqIdentityInterestScoreRepository.countByIndividualId(
				"374790572703144534"));
	}

	@Test
	public void testCountKeywords() {
		Assertions.assertEquals(
			4, _bqIdentityInterestScoreRepository.countKeywords(null));
	}

	@Test
	public void testCountKeywordsWithFilter() {
		Assertions.assertEquals(
			2, _bqIdentityInterestScoreRepository.countKeywords("le"));
	}

	@Test
	public void testDeleteByNameAndRecordedDateGreaterThanEqual() {
		_bqIdentityInterestScoreRepository.
			deleteByKeywordAndRecordedDateGreaterThanEqual(
				"individual", DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(5, _bqIdentityInterestScoreRepository.count());
	}

	@Test
	public void testDeleteByRecordedDate() {
		_bqIdentityInterestScoreRepository.deleteByRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(2, _bqIdentityInterestScoreRepository.count());
	}

	@Test
	public void testDeleteByRecordedDateLessThanEqual() {
		_bqIdentityInterestScoreRepository.deleteByRecordedDateLessThanEqual(
			DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(3, _bqIdentityInterestScoreRepository.count());
	}

	@Test
	public void testFindByIndividualId() {
		List<BQIdentityInterestScore> bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.findByIndividualId(
				"374790572703144534", PageRequest.of(0, 10));

		Assertions.assertEquals(
			2, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(_bqIdentityInterestScore3, _bqIdentityInterestScore4),
			bqIdentityInterestScores);
	}

	@Test
	public void testFindByNameAndIndividualIdAndRecordedDateBetween() {
		List<BQIdentityInterestScore> bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			1, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(_bqIdentityInterestScore5), bqIdentityInterestScores);

		bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			1, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(_bqIdentityInterestScore5), bqIdentityInterestScores);

		bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"));

		Assertions.assertEquals(
			1, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());

		bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-16T00:00:00.000Z"));

		Assertions.assertEquals(
			0, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(
				_bqIdentityInterestScore3, _bqIdentityInterestScore4,
				_bqIdentityInterestScore5),
			bqIdentityInterestScores);
	}

	@Test
	public void testFindByRecordedDate() {
		List<BQIdentityInterestScore> bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.findByRecordedDate(
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"), 10);

		Assertions.assertEquals(
			3, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());
	}

	@Test
	public void testFindIndividualIdsByFilterStringAndIndividualId() {
		List<String> individualIds =
			_bqIdentityInterestScoreRepository.
				findIndividualIdsByFilterStringAndIndividualId(
					new FilterHelper(
						null, "(name eq 'sales')",
						_interestFilterStringConverterHelper),
					"374790572703144534");

		Assertions.assertEquals(
			Arrays.asList("374790572703144534"), individualIds,
			individualIds.toString());
	}

	@Test
	public void testGetByNameAndIndividualIdAndRecordedDate() {
		BQIdentityInterestScore bqIdentityInterestScore =
			_bqIdentityInterestScoreRepository.
				getByIndividualIdAndKeywordAndRecordedDate(
					"374790575409131096", "javascript",
					DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(
			_bqIdentityInterestScore2, bqIdentityInterestScore);
	}

	@Test
	public void testGetInterestTransformationsByDay() {
		List<Map<String, Object>> transformations =
			_bqIdentityInterestScoreRepository.getTransformations(
				DateUtil.toUTCDate("2021-09-11T00:00:00.000Z"), null, "day",
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			4, transformations.size(), transformations.toString());

		_assertTransformation(
			"2021-09-11T00:00:00.000Z", 0, 0, transformations.get(0));
		_assertTransformation(
			"2021-09-12T00:00:00.000Z", 1.767661917648994, 1,
			transformations.get(1));
		_assertTransformation(
			"2021-09-13T00:00:00.000Z", 2.61495977803619, 1,
			transformations.get(2));
		_assertTransformation(
			"2021-09-14T00:00:00.000Z", 1.2265308301495208, 3,
			transformations.get(3));
	}

	@Test
	public void testGetInterestTransformationsByWeek() {
		List<Map<String, Object>> transformations =
			_bqIdentityInterestScoreRepository.getTransformations(
				DateUtil.toUTCDate("2021-09-01T00:00:00.000Z"), null, "week",
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			3, transformations.size(), transformations.toString());
		_assertTransformation(
			"2021-08-29T00:00:00.000Z", 0D, 0, transformations.get(0));
		_assertTransformation(
			"2021-09-05T00:00:00.000Z", 0D, 0, transformations.get(1));
		_assertTransformation(
			"2021-09-12T00:00:00.000Z", 1.6124428372267494, 5,
			transformations.get(2));
	}

	@Test
	public void testGetKeywords() {
		Assertions.assertEquals(
			new ArrayList<String>() {
				{
					add("clicks-and-mortar e-tailers");
					add("compelling metrics");
					add("javascript");
					add("sales");
				}
			},
			_bqIdentityInterestScoreRepository.getKeywords(
				null, PageRequest.of(0, 20)));
	}

	@Test
	public void testGetKeywordsPagination() {
		Assertions.assertEquals(
			new ArrayList<String>() {
				{
					add("javascript");
					add("sales");
				}
			},
			_bqIdentityInterestScoreRepository.getKeywords(
				null, PageRequest.of(1, 2)));
	}

	@Test
	public void testGetKeywordsWithFilter() {
		Assertions.assertEquals(
			new ArrayList<String>() {
				{
					add("clicks-and-mortar e-tailers");
					add("sales");
				}
			},
			_bqIdentityInterestScoreRepository.getKeywords(
				"le", PageRequest.of(0, 20)));
	}

	@Test
	public void testGetTopNamesByIndividualId() {
		List<String> names =
			_bqIdentityInterestScoreRepository.getTopKeywordsByIndividualId(
				"374790572703144534", 10);

		Assertions.assertEquals(
			Arrays.asList("sales", "compelling metrics"), names,
			names.toString());
	}

	private void _assertTransformation(
		String intervalInitDate, double scoreAvg, long totalElements,
		Map<String, Object> transformation) {

		Assertions.assertEquals(
			intervalInitDate, transformation.get("intervalInitDate"));
		Assertions.assertEquals(scoreAvg, transformation.get("scoreAvg"));
		Assertions.assertEquals(
			totalElements, transformation.get("totalElements"));
	}

	private BQIdentityInterestScore _bqIdentityInterestScore1;
	private BQIdentityInterestScore _bqIdentityInterestScore2;
	private BQIdentityInterestScore _bqIdentityInterestScore3;
	private BQIdentityInterestScore _bqIdentityInterestScore4;
	private BQIdentityInterestScore _bqIdentityInterestScore5;

	@Autowired
	private BQIdentityInterestScoreRepository
		_bqIdentityInterestScoreRepository;

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private InterestFilterStringConverterHelper
		_interestFilterStringConverterHelper;

}