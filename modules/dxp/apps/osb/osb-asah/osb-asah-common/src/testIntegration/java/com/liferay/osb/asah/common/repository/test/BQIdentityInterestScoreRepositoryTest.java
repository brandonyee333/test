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
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Robson Pastor
 */
public class BQIdentityInterestScoreRepositoryTest
	extends BaseRepositoryTestCase<BQIdentityInterestScore, Long> {

	@BeforeEach
	public void setUp() {
		BQIdentityInterestScore bqIdentityInterestScore1 =
			new BQIdentityInterestScore();

		bqIdentityInterestScore1.setIdentityId("374790569167317525");
		bqIdentityInterestScore1.setInterested(Boolean.TRUE);
		bqIdentityInterestScore1.setInterestScore(1.767661917648994);
		bqIdentityInterestScore1.setKeyword("clicks-and-mortar e-tailers");
		bqIdentityInterestScore1.setRecordedDate(
			DateUtil.toUTCDate("2021-09-12T00:00:00.000Z"));

		BQIdentity bqIdentity1 = new BQIdentity();

		bqIdentity1.setId("374790569167317525");
		bqIdentity1.setIndividualId("374790569167317525");
		bqIdentity1.setIsNew(true);

		_bqIdentityRepository.save(bqIdentity1);

		BQIdentityInterestScore bqIdentityInterestScore2 =
			new BQIdentityInterestScore();

		bqIdentityInterestScore2.setIdentityId("374790575409131096");
		bqIdentityInterestScore2.setInterested(Boolean.TRUE);
		bqIdentityInterestScore2.setInterestScore(2.61495977803619);
		bqIdentityInterestScore2.setKeyword("javascript");
		bqIdentityInterestScore2.setRecordedDate(
			DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		BQIdentity bqIdentity2 = new BQIdentity();

		bqIdentity2.setId("374790575409131096");
		bqIdentity2.setIndividualId("374790575409131096");
		bqIdentity2.setIsNew(true);

		_bqIdentityRepository.save(bqIdentity2);

		BQIdentityInterestScore bqIdentityInterestScore3 =
			new BQIdentityInterestScore();

		bqIdentityInterestScore3.setIdentityId("374790572703144534");
		bqIdentityInterestScore3.setInterested(Boolean.FALSE);
		bqIdentityInterestScore3.setInterestScore(0.770222520473574);
		bqIdentityInterestScore3.setKeyword("compelling metrics");
		bqIdentityInterestScore3.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		BQIdentity bqIdentity3 = new BQIdentity();

		bqIdentity3.setId("374790572703144534");
		bqIdentity3.setIndividualId("374790572703144534");
		bqIdentity3.setIsNew(true);

		_bqIdentityRepository.save(bqIdentity3);

		BQIdentityInterestScore bqIdentityInterestScore4 =
			new BQIdentityInterestScore();

		bqIdentityInterestScore4.setIdentityId("374790572703144534");
		bqIdentityInterestScore4.setInterested(Boolean.TRUE);
		bqIdentityInterestScore4.setInterestScore(1.454684984987494);
		bqIdentityInterestScore4.setKeyword("sales");
		bqIdentityInterestScore4.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		BQIdentityInterestScore bqIdentityInterestScore5 =
			new BQIdentityInterestScore();

		bqIdentityInterestScore5.setIdentityId("374790572703144535");
		bqIdentityInterestScore5.setInterested(Boolean.TRUE);
		bqIdentityInterestScore5.setInterestScore(1.454684984987494);
		bqIdentityInterestScore5.setKeyword("sales");
		bqIdentityInterestScore5.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		BQIdentity bqIdentity5 = new BQIdentity();

		bqIdentity5.setId("374790572703144535");
		bqIdentity5.setIndividualId("374790572703144535");
		bqIdentity5.setIsNew(true);

		_bqIdentityRepository.save(bqIdentity5);

		setUpRepository(
			bqIdentityInterestScore1, bqIdentityInterestScore2,
			bqIdentityInterestScore3, bqIdentityInterestScore4,
			bqIdentityInterestScore5);

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(new Date());
		bqMembership.setIndividualId("374790569167317525");
		bqMembership.setModifiedDate(new Date());
		bqMembership.setSegmentId(123L);
		bqMembership.setStatus("ACTIVE");

		_bqMembershipRepository.save(bqMembership);
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
			Arrays.asList(entityModels.get(2), entityModels.get(3)),
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
			Arrays.asList(entityModels.get(4)), bqIdentityInterestScores);

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
			Arrays.asList(entityModels.get(4)), bqIdentityInterestScores);

		bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"));

		Assertions.assertEquals(
			1, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(entityModels.get(4)), bqIdentityInterestScores);

		bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.
				findByIndividualIdAndKeywordAndRecordedDateBetween(
					"374790572703144535", "sales",
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-16T00:00:00.000Z"));

		Assertions.assertEquals(
			0, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());
	}

	@Test
	public void testFindByRecordedDate() {
		List<BQIdentityInterestScore> bqIdentityInterestScores =
			_bqIdentityInterestScoreRepository.findByRecordedDate(
				null, DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"), 10);

		Assertions.assertEquals(
			3, bqIdentityInterestScores.size(),
			bqIdentityInterestScores.toString());
		Assertions.assertEquals(
			Arrays.asList(
				entityModels.get(2), entityModels.get(3), entityModels.get(4)),
			bqIdentityInterestScores);
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

		Assertions.assertEquals(entityModels.get(1), bqIdentityInterestScore);
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

	@Override
	protected PagingAndSortingRepository<BQIdentityInterestScore, Long>
		getPagingAndSortingRepository() {

		return _bqIdentityInterestScoreRepository;
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