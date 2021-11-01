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
import com.liferay.osb.asah.common.repository.Repository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Robson Pastor
 */
public abstract class BaseInterestRepositoryTestCase
	extends BaseRepositoryTestCase<Interest, Long> {

	@Before
	public void setUp() {
		Interest interest1 = new Interest();

		interest1.setName("clicks-and-mortar e-tailers");
		interest1.setOwnerId(374790569167317525L);
		interest1.setOwnerType("individual");
		interest1.setRecordedDate(
			DateUtil.toUTCDate("2021-09-12T00:00:00.000Z"));
		interest1.setScore(1.7676619176489945);
		interest1.setViews(1L);

		Interest interest2 = new Interest();

		interest2.setName("javascript");
		interest2.setOwnerId(374790575409131096L);
		interest2.setOwnerType("individual");
		interest2.setRecordedDate(
			DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));
		interest2.setScore(2.614959778036198);
		interest2.setViews(2L);

		Interest interest3 = new Interest();

		interest3.setName("compelling metrics");
		interest3.setOwnerId(374790572703144534L);
		interest3.setOwnerType("individual");
		interest3.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));
		interest3.setScore(0.7702225204735745);
		interest3.setViews(3L);

		Interest interest4 = new Interest();

		interest4.setName("sales");
		interest4.setOwnerId(374790572703144534L);
		interest4.setOwnerType("individual");
		interest4.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));
		interest4.setScore(1.4546849849874945);
		interest4.setViews(4L);

		Interest interest5 = new Interest();

		interest5.setName("sales");
		interest5.setOwnerId(374790572703144535L);
		interest5.setOwnerType("individual");
		interest5.setRecordedDate(
			DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));
		interest5.setScore(1.4546849849874945);
		interest5.setViews(5L);

		setUpRepository(interest1, interest2, interest3, interest4, interest5);
	}

	@Test
	public void testCountByFilterStringAndScore() {
		Assert.assertEquals(
			4,
			_interestRepository.countByFilterStringAndScoreGreaterThanEqual(
				new FilterHelper(null), 1.4546849849874945));
	}

	@Test
	public void testCountByOwnerIdAndOwnerType() {
		Assert.assertEquals(
			2,
			_interestRepository.countByOwnerIdAndOwnerType(
				374790572703144534L, "individual"));
	}

	@Test
	public void testDeleteByNameAndRecordedDateGreaterThanEqual() {
		_interestRepository.deleteByNameAndRecordedDateGreaterThanEqual(
			"individual", DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assert.assertEquals(5, _interestRepository.count());
	}

	@Test
	public void testDeleteByOwnerIdAndOwnerType() {
		_interestRepository.deleteByOwnerIdAndOwnerType(
			Long.valueOf(374790572703144534L), "individual");

		Assert.assertEquals(3, _interestRepository.count());
	}

	@Test
	public void testDeleteByOwnerTypeAndRecordedDateLessThanEqual() {
		_interestRepository.deleteByOwnerTypeAndRecordedDateLessThanEqual(
			"individual", DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"));

		Assert.assertEquals(3, _interestRepository.count());
	}

	@Test
	public void testFindByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween() {
		List<Interest> interests =
			_interestRepository.
				findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
					"sales", 374790572703144535L, "individual",
					DateUtil.toUTCDate("2021-09-13T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assert.assertEquals(interests.toString(), 1, interests.size());
		Assert.assertEquals(Arrays.asList(entityModels.get(4)), interests);

		interests =
			_interestRepository.
				findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
					"sales", 374790572703144535L, "individual",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"));

		Assert.assertEquals(interests.toString(), 1, interests.size());
		Assert.assertEquals(Arrays.asList(entityModels.get(4)), interests);

		interests =
			_interestRepository.
				findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
					"sales", 374790572703144535L, "individual",
					DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"));

		Assert.assertEquals(interests.toString(), 1, interests.size());
		Assert.assertEquals(Arrays.asList(entityModels.get(4)), interests);

		interests =
			_interestRepository.
				findByNameAndOwnerIdAndOwnerTypeAndRecordedDateBetween(
					"sales", 374790572703144535L, "individual",
					DateUtil.toUTCDate("2021-09-15T00:00:00.000Z"),
					DateUtil.toUTCDate("2021-09-16T00:00:00.000Z"));

		Assert.assertEquals(interests.toString(), 0, interests.size());
	}

	@Test
	public void testFindByOwnerIdAndOwnerType() {
		List<Interest> interests =
			_interestRepository.findByOwnerIdAndOwnerType(
				374790572703144534L, "individual", PageRequest.of(0, 10));

		Assert.assertEquals(interests.toString(), 2, interests.size());
		Assert.assertEquals(
			Arrays.asList(entityModels.get(2), entityModels.get(3)), interests);
	}

	@Test
	public void testFindByOwnerTypeAndRecordedDate() {
		List<Interest> interests =
			_interestRepository.findByOwnerTypeAndRecordedDate(
				null, "individual",
				DateUtil.toUTCDate("2021-09-14T00:00:00.000Z"), 10);

		Assert.assertEquals(interests.toString(), 3, interests.size());
		Assert.assertEquals(
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

		Assert.assertEquals(entityModels.get(1), interest);
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

		Assert.assertEquals(distributions.toString(), 2, distributions.size());

		Distribution distribution = distributions.get(0);

		Assert.assertEquals(
			distribution.toString(), 2, (int)distribution.getCount());
		Assert.assertEquals(
			distribution.toString(), Arrays.asList("sales"),
			distribution.getValues());
	}

	@Test
	public void testGetTopNamesByOwnerIdAndOwnerType() {
		List<String> names =
			_interestRepository.getTopNamesByOwnerIdAndOwnerType(
				374790572703144534L, "individual", 10);

		Assert.assertEquals(
			names.toString(), Arrays.asList("sales", "compelling metrics"),
			names);
	}

	@Override
	protected Repository<Interest, Long> getRepository() {
		return _interestRepository;
	}

	@Autowired
	private InterestRepository _interestRepository;

}