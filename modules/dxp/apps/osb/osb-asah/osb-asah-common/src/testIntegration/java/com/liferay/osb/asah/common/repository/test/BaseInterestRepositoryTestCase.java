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
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Robson Pastor
 */
public abstract class BaseInterestRepositoryTestCase
	extends BaseRepositoryTestCase<Interest, Long> {

	@BeforeEach
	public void setUp() {
		Interest interest1 = new Interest();

		interest1.setName("clicks-and-mortar e-tailers");
		interest1.setOwnerId(374790569167317525L);
		interest1.setOwnerType("individual");
		interest1.setRecordedDate(
			DateUtil.toUTCDate("2021-09-12T00:00:00.000Z"));
		interest1.setScore(1.767661917648994);
		interest1.setViews(1L);

		Interest interest2 = new Interest();

		interest2.setName("javascript");
		interest2.setOwnerId(374790575409131096L);
		interest2.setOwnerType("individual");
		interest2.setRecordedDate(
			DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));
		interest2.setScore(2.61495977803619);
		interest2.setViews(2L);

		Interest interest3 = new Interest();

		interest3.setName("compelling metrics");
		interest3.setOwnerId(374790572703144534L);
		interest3.setOwnerType("individual");
		interest3.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));
		interest3.setScore(0.770222520473574);
		interest3.setViews(3L);

		Interest interest4 = new Interest();

		interest4.setName("sales");
		interest4.setOwnerId(374790572703144534L);
		interest4.setOwnerType("individual");
		interest4.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));
		interest4.setScore(1.454684984987494);
		interest4.setViews(4L);

		Interest interest5 = new Interest();

		interest5.setName("sales");
		interest5.setOwnerId(374790572703144535L);
		interest5.setOwnerType("individual");
		interest5.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));
		interest5.setScore(1.454684984987494);
		interest5.setViews(5L);

		setUpRepository(interest1, interest2, interest3, interest4, interest5);
	}

	@Test
	public void testCountByFilterStringAndScore() {
		Assertions.assertEquals(
			4,
			_interestRepository.countByFilterStringAndScoreGreaterThanEqual(
				new FilterHelper(null), 1.454684984987494));
	}

	@Test
	public void testCountByOwnerIdAndOwnerType() {
		Assertions.assertEquals(
			2,
			_interestRepository.countByOwnerIdAndOwnerType(
				374790572703144534L, "individual"));
	}

	@Test
	public void testCountInterestDistributions() {
		List<Long> ownerIds = Arrays.asList(
			374790572703144534L, 374790572703144535L);

		Assertions.assertEquals(
			2,
			_interestRepository.countInterestDistributions(
				null, ownerIds, "individual",
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"), null));
	}

	@Test
	public void testDeleteByNameAndRecordedDateGreaterThanEqual() {
		_interestRepository.deleteByNameAndRecordedDateGreaterThanEqual(
			"individual", DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(5, _interestRepository.count());
	}

	@Test
	public void testDeleteByOwnerIdInAndOwnerType() {
		Assertions.assertEquals(
			5, _interestRepository.count(), _interestRepository.toString());

		List<Long> ownerIds = Arrays.asList(
			374790572703144534L, 374790572703144535L);

		List<Interest> interests = IterableUtils.toList(
			_interestRepository.findAll());

		Stream<Interest> stream = interests.stream();

		Assertions.assertTrue(
			stream.anyMatch(
				interest -> ownerIds.contains(interest.getOwnerId())));

		_interestRepository.deleteByOwnerIdInAndOwnerType(
			ownerIds, "individual");

		interests = IterableUtils.toList(_interestRepository.findAll());

		stream = interests.stream();

		Assertions.assertFalse(
			stream.anyMatch(
				interest -> ownerIds.contains(interest.getOwnerId())));
	}

	@Test
	public void testDeleteByOwnerTypeAndRecordedDate() {
		_interestRepository.deleteByOwnerTypeAndRecordedDate(
			"individual", DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(2, _interestRepository.count());
	}

	@Test
	public void testDeleteByOwnerTypeAndRecordedDateLessThanEqual() {
		_interestRepository.deleteByOwnerTypeAndRecordedDateLessThanEqual(
			"individual", DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(3, _interestRepository.count());
	}

	@Test
	public void testFindByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween() {
		List<Interest> interests =
			_interestRepository.
				findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
					"sales", 374790572703144535L, "individual",
					DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(1, interests.size(), interests.toString());
		Assertions.assertEquals(Arrays.asList(entityModels.get(4)), interests);

		interests =
			_interestRepository.
				findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
					"sales", 374790572703144535L, "individual",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(1, interests.size(), interests.toString());
		Assertions.assertEquals(Arrays.asList(entityModels.get(4)), interests);

		interests =
			_interestRepository.
				findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
					"sales", 374790572703144535L, "individual",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"));

		Assertions.assertEquals(1, interests.size(), interests.toString());
		Assertions.assertEquals(Arrays.asList(entityModels.get(4)), interests);

		interests =
			_interestRepository.
				findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
					"sales", 374790572703144535L, "individual",
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-16T00:00:00.000Z"));

		Assertions.assertEquals(0, interests.size(), interests.toString());
	}

	@Test
	public void testFindByOwnerIdAndOwnerType() {
		List<Interest> interests =
			_interestRepository.findByOwnerIdAndOwnerType(
				374790572703144534L, "individual", PageRequest.of(0, 10));

		Assertions.assertEquals(2, interests.size(), interests.toString());
		Assertions.assertEquals(
			Arrays.asList(entityModels.get(2), entityModels.get(3)), interests);
	}

	@Test
	public void testFindByOwnerTypeAndRecordedDate() {
		List<Interest> interests =
			_interestRepository.findByOwnerTypeAndRecordedDate(
				null, "individual",
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"), 10);

		Assertions.assertEquals(3, interests.size(), interests.toString());
		Assertions.assertEquals(
			Arrays.asList(
				entityModels.get(2), entityModels.get(3), entityModels.get(4)),
			interests);
	}

	@Test
	public void testGetByNameAndOwnerIdAndOwnerTypeAndRecordedDate() {
		Interest interest =
			_interestRepository.getByNameAndOwnerIdAndOwnerTypeAndRecordedDate(
				"javascript", 374790575409131096L, "individual",
				DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assertions.assertEquals(entityModels.get(1), interest);
	}

	@Test
	public void testGetInterestDistributions() {
		List<Long> ownerIds = Arrays.asList(
			374790572703144534L, 374790572703144535L);

		List<Distribution> distributions =
			_interestRepository.getInterestDistributions(
				null, ownerIds, "individual",
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"), null,
				PageRequest.of(0, 10, Sort.desc("count")));

		Assertions.assertEquals(
			2, distributions.size(), distributions.toString());

		Distribution distribution = distributions.get(0);

		Assertions.assertEquals(
			2, (int)distribution.getCount(), distribution.toString());
		Assertions.assertEquals(
			Arrays.asList("sales"), distribution.getValues(),
			distribution.toString());
	}

	@Test
	public void testGetInterestTransformationsByDay() {
		List<Map<String, Object>> transformations =
			_interestRepository.getTransformations(
				DateUtil.toUTCDate("2021-09-11T00:00:00.000Z"), null, "day",
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			4, transformations.size(), transformations.toString());

		_assertTransformation(
			"2021-09-11T00:00:00.000Z", 0, 0, 0, transformations.get(0));
		_assertTransformation(
			"2021-09-12T00:00:00.000Z", 1.767661917648994, 1, 1,
			transformations.get(1));
		_assertTransformation(
			"2021-09-13T00:00:00.000Z", 2.61495977803619, 2, 1,
			transformations.get(2));
		_assertTransformation(
			"2021-09-14T00:00:00.000Z", 1.2265308301495208, 12, 3,
			transformations.get(3));
	}

	@Test
	public void testGetInterestTransformationsByWeek() {
		List<Map<String, Object>> transformations =
			_interestRepository.getTransformations(
				DateUtil.toUTCDate("2021-09-01T00:00:00.000Z"), null, "week",
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assertions.assertEquals(
			3, transformations.size(), transformations.toString());
		_assertTransformation(
			"2021-08-30T00:00:00.000Z", 0D, 0D, 0, transformations.get(0));
		_assertTransformation(
			"2021-09-06T00:00:00.000Z", 1.767661917648994D, 1D, 1,
			transformations.get(1));
		_assertTransformation(
			"2021-09-13T00:00:00.000Z", 1.573638067121188D, 14D, 4,
			transformations.get(2));
	}

	@Test
	public void testGetTopNamesByOwnerIdAndOwnerType() {
		List<String> names =
			_interestRepository.getTopNamesByOwnerIdAndOwnerType(
				374790572703144534L, "individual", 10);

		Assertions.assertEquals(
			Arrays.asList("sales", "compelling metrics"), names,
			names.toString());
	}

	@Override
	protected PagingAndSortingRepository<Interest, Long>
		getPagingAndSortingRepository() {

		return _interestRepository;
	}

	private void _assertTransformation(
		String intervalInitDate, double scoreAvg, double viewsSum,
		long totalElements, Map<String, Object> transformation) {

		Assertions.assertEquals(
			intervalInitDate, transformation.get("intervalInitDate"));
		Assertions.assertEquals(scoreAvg, transformation.get("scoreAvg"));
		Assertions.assertEquals(
			totalElements, transformation.get("totalElements"));
		Assertions.assertEquals(viewsSum, transformation.get("viewsSum"));
	}

	@Autowired
	private InterestRepository _interestRepository;

}