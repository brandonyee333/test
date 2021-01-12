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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.UserDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class UserDogTest {

	@ElasticsearchIndex(
		name = "journals", resourcePath = "user_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAnonymousUsersCount() {
		long anonymousUsersCount = _userDog.getAnonymousUsersCount(
			JournalMetricType.VIEWS, _searchQueryContext);

		Assert.assertEquals(2, anonymousUsersCount);
	}

	@ElasticsearchIndex(
		name = "individuals",
		resourcePath = "user_journal_individuals_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "journals", resourcePath = "user_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetKnownUsersCount() {
		long knownUsersCount = _userDog.getKnownUsersCount(
			JournalMetricType.VIEWS, _searchQueryContext);

		Assert.assertEquals(4, knownUsersCount);
	}

	@ElasticsearchIndex(
		name = "individuals",
		resourcePath = "user_journal_individuals_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "journals", resourcePath = "user_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetNonsegmentedKnownUsersCount() {
		long nonsegmentedKnownUsersCount =
			_userDog.getNonsegmentedKnownUsersCount(
				JournalMetricType.VIEWS, _searchQueryContext);

		Assert.assertEquals(2, nonsegmentedKnownUsersCount);
	}

	@ElasticsearchIndex(
		name = "individuals",
		resourcePath = "user_journal_individuals_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "journals", resourcePath = "user_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetSegmentedKnownUsersCount() {
		long segmentedKnownUsersCount = _userDog.getSegmentedKnownUsersCount(
			JournalMetricType.VIEWS, _searchQueryContext);

		Assert.assertEquals(2, segmentedKnownUsersCount);
	}

	private SearchQueryContext _searchQueryContext = new SearchQueryContext(
		"1", AssetType.JOURNAL) {

		{
			setTimeRange(TimeRange.LAST_7_DAYS);
		}
	};

	@Autowired
	private UserDog _userDog;

}