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
import com.liferay.osb.asah.common.entity.BQIndividualInterestScore;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.postgresql.converter.helper.InterestFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualInterestScoreRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Robson Pastor
 */
public class BQIndividualInterestScoreRepositoryTest
	extends BaseRepositoryTestCase<BQIndividualInterestScore, Long> {

	@BeforeEach
	public void setUp() {
		BQIndividualInterestScore bqIndividualInterestScore1 =
			new BQIndividualInterestScore();

		bqIndividualInterestScore1.setIdentityId("374790569167317525");
		bqIndividualInterestScore1.setInterestScore(1.767661917648994);
		bqIndividualInterestScore1.setKeyword("clicks-and-mortar e-tailers");
		bqIndividualInterestScore1.setRecordedDate(
			DateUtil.toUTCDate("2021-09-12T00:00:00.000Z"));

		BQIdentity bqIdentity1 = new BQIdentity();

		bqIdentity1.setId("374790569167317525");
		bqIdentity1.setIndividualId("374790569167317525");
		bqIdentity1.setIsNew(true);

		_bqIdentityRepository.save(bqIdentity1);

		BQIndividualInterestScore bqIndividualInterestScore2 =
			new BQIndividualInterestScore();

		bqIndividualInterestScore2.setIdentityId("374790575409131096");
		bqIndividualInterestScore2.setInterestScore(2.61495977803619);
		bqIndividualInterestScore2.setKeyword("javascript");
		bqIndividualInterestScore2.setRecordedDate(
			DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		BQIdentity bqIdentity2 = new BQIdentity();

		bqIdentity2.setId("374790575409131096");
		bqIdentity2.setIndividualId("374790575409131096");
		bqIdentity2.setIsNew(true);

		_bqIdentityRepository.save(bqIdentity2);

		BQIndividualInterestScore bqIndividualInterestScore3 =
			new BQIndividualInterestScore();

		bqIndividualInterestScore3.setIdentityId("374790572703144534");
		bqIndividualInterestScore3.setInterestScore(0.770222520473574);
		bqIndividualInterestScore3.setKeyword("compelling metrics");
		bqIndividualInterestScore3.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		BQIdentity bqIdentity3 = new BQIdentity();

		bqIdentity3.setId("374790572703144534");
		bqIdentity3.setIndividualId("374790572703144534");
		bqIdentity3.setIsNew(true);

		_bqIdentityRepository.save(bqIdentity3);

		BQIndividualInterestScore bqIndividualInterestScore4 =
			new BQIndividualInterestScore();

		bqIndividualInterestScore4.setIdentityId("374790572703144534");
		bqIndividualInterestScore4.setInterestScore(1.454684984987494);
		bqIndividualInterestScore4.setKeyword("sales");
		bqIndividualInterestScore4.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		BQIndividualInterestScore bqIndividualInterestScore5 =
			new BQIndividualInterestScore();

		bqIndividualInterestScore5.setIdentityId("374790572703144535");
		bqIndividualInterestScore5.setInterestScore(1.454684984987494);
		bqIndividualInterestScore5.setKeyword("sales");
		bqIndividualInterestScore5.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		BQIdentity bqIdentity5 = new BQIdentity();

		bqIdentity5.setId("374790572703144535");
		bqIdentity5.setIndividualId("374790572703144535");
		bqIdentity5.setIsNew(true);

		_bqIdentityRepository.save(bqIdentity5);

		setUpRepository(
			bqIndividualInterestScore1, bqIndividualInterestScore2,
			bqIndividualInterestScore3, bqIndividualInterestScore4,
			bqIndividualInterestScore5);

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(new Date());
		bqMembership.setIndividualId("374790569167317525");
		bqMembership.setModifiedDate(new Date());
		bqMembership.setSegmentId(123L);
		bqMembership.setStatus("ACTIVE");

		_bqMembershipRepository.save(bqMembership);
	}

	@Test
	public void testCountByFilterStringAndScore() {
		Assertions.assertEquals(
			4,
			_bqIndividualInterestScoreRepository.
				countByFilterStringAndScoreGreaterThanEqual(
					new FilterHelper(null), 1.454684984987494));
	}

	@Test
	public void testCountByIndividualId() {
		Assertions.assertEquals(
			2,
			_bqIndividualInterestScoreRepository.countByIndividualId(
				"374790572703144534"));
	}

	@Test
	public void testDeleteByNameAndRecordedDateGreaterThanEqual() {
		_bqIndividualInterestScoreRepository.
			deleteByKeywordAndRecordedDateGreaterThanEqual(
				"individual", DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(
			5, _bqIndividualInterestScoreRepository.count());
	}

	@Test
	public void testDeleteByRecordedDate() {
		_bqIndividualInterestScoreRepository.deleteByRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			2, _bqIndividualInterestScoreRepository.count());
	}

	@Test
	public void testDeleteByRecordedDateLessThanEqual() {
		_bqIndividualInterestScoreRepository.deleteByRecordedDateLessThanEqual(
			DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(
			3, _bqIndividualInterestScoreRepository.count());
	}

	@Test
	public void testFindByIndividualId() {
		List<BQIndividualInterestScore> bqIndividualInterestScores =
			_bqIndividualInterestScoreRepository.findByIndividualId(
				"374790572703144534", PageRequest.of(0, 10));

		Assertions.assertEquals(
			2, bqIndividualInterestScores.size(),
			bqIndividualInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(entityModels.get(2), entityModels.get(3)),
			bqIndividualInterestScores);
	}

	@Test
	public void testFindByNameAndIndividualIdAndRecordedDateBetween() {
		List<BQIndividualInterestScore> bqIndividualInterestScores =
			_bqIndividualInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			1, bqIndividualInterestScores.size(),
			bqIndividualInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(entityModels.get(4)), bqIndividualInterestScores);

		bqIndividualInterestScores =
			_bqIndividualInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			1, bqIndividualInterestScores.size(),
			bqIndividualInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(entityModels.get(4)), bqIndividualInterestScores);

		bqIndividualInterestScores =
			_bqIndividualInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"));

		Assertions.assertEquals(
			1, bqIndividualInterestScores.size(),
			bqIndividualInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(entityModels.get(4)), bqIndividualInterestScores);

		bqIndividualInterestScores =
			_bqIndividualInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-16T00:00:00.000Z"));

		Assertions.assertEquals(
			0, bqIndividualInterestScores.size(),
			bqIndividualInterestScores.toString());
	}

	@Test
	public void testFindByRecordedDate() {
		List<BQIndividualInterestScore> bqIndividualInterestScores =
			_bqIndividualInterestScoreRepository.findByRecordedDate(
				null, DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"), 10);

		Assertions.assertEquals(
			3, bqIndividualInterestScores.size(),
			bqIndividualInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(
				entityModels.get(2), entityModels.get(3), entityModels.get(4)),
			bqIndividualInterestScores);
	}

	@Test
	public void testFindIndividualIdsByFilterStringAndIndividualId() {
		List<String> individualIds =
			_bqIndividualInterestScoreRepository.
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
		BQIndividualInterestScore bqIndividualInterestScore =
			_bqIndividualInterestScoreRepository.
				getByIndividualIdAndKeywordAndRecordedDate(
					"374790575409131096", "javascript",
					DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(entityModels.get(1), bqIndividualInterestScore);
	}

	@Test
	public void testGetInterestTransformationsByDay() {
		List<Map<String, Object>> transformations =
			_bqIndividualInterestScoreRepository.getTransformations(
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
			_bqIndividualInterestScoreRepository.getTransformations(
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
	public void testGetTopNamesByIndividualId() {
		List<String> names =
			_bqIndividualInterestScoreRepository.getTopKeywordsByIndividualId(
				"374790572703144534", 10);

		Assertions.assertEquals(
			Arrays.asList("sales", "compelling metrics"), names,
			names.toString());
	}

	@Override
	protected PagingAndSortingRepository<BQIndividualInterestScore, Long>
		getPagingAndSortingRepository() {

		return _bqIndividualInterestScoreRepository;
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

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@Autowired
	private BQIndividualInterestScoreRepository
		_bqIndividualInterestScoreRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private InterestFilterStringConverterHelper
		_interestFilterStringConverterHelper;

}