/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.verify.test.BaseVerifyProcessTestCase;
import com.liferay.portlet.ratings.util.test.RatingsTestUtil;
import com.liferay.ratings.kernel.model.RatingsEntry;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsEntryLocalServiceUtil;
import com.liferay.ratings.kernel.service.RatingsStatsLocalServiceUtil;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alberto Chaparro
 */
public class VerifyRatingsTest extends BaseVerifyProcessTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_active = CacheRegistryUtil.isActive();

		CacheRegistryUtil.setActive(false);

		_ratingsStats = RatingsTestUtil.addStats(_CLASS_NAME, _CLASS_PK);
	}

	@After
	@Override
	public void tearDown() throws Exception {
		super.tearDown();

		List<RatingsEntry> ratingEntries =
			RatingsEntryLocalServiceUtil.getEntries(_CLASS_NAME, _CLASS_PK);

		for (RatingsEntry ratingsEntry : ratingEntries) {
			RatingsEntryLocalServiceUtil.deleteRatingsEntry(ratingsEntry);
		}

		CacheRegistryUtil.setActive(_active);
	}

	@Test
	public void testVerifyStatsWithEntries() throws Exception {
		int totalEntries = RandomTestUtil.randomInt(1, 10);
		double totalScore = 0.0;

		for (int i = 0; i < totalEntries; i++) {
			totalScore += addVote();
		}

		doVerify();

		RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(
			_CLASS_NAME, _CLASS_PK);

		Assert.assertEquals(totalEntries, ratingsStats.getTotalEntries());
		Assert.assertEquals(totalScore, ratingsStats.getTotalScore(), 0.0001);
		Assert.assertEquals(
			totalScore / totalEntries, ratingsStats.getAverageScore(), 0.0001);
	}

	@Test
	public void testVerifyStatsWithNoEntries() throws Exception {
		doVerify();

		RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(
			_CLASS_NAME, _CLASS_PK);

		Assert.assertEquals(0, ratingsStats.getTotalEntries());
		Assert.assertEquals(0.0, ratingsStats.getTotalScore(), 0.0001);
		Assert.assertEquals(0.0, ratingsStats.getAverageScore(), 0.0001);
	}

	protected double addVote() throws Exception {
		double score = RandomTestUtil.randomDouble();
		User user = UserTestUtil.addUser();

		RatingsTestUtil.addEntry(
			_CLASS_NAME, _CLASS_PK, score, user.getUserId());

		return score;
	}

	@Override
	protected VerifyProcess getVerifyProcess() {
		return new VerifyRatings();
	}

	private static final String _CLASS_NAME = VerifyRatingsTest.class.getName();

	private static final int _CLASS_PK = 1;

	private boolean _active;

	@DeleteAfterTestRun
	private RatingsStats _ratingsStats;

}