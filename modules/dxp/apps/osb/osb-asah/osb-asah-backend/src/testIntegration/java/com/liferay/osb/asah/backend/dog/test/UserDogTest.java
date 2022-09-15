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

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.UserDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.repository.CrudBQJournalRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author André Miranda
 */
@Disabled
public class UserDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = CrudBQJournalRepository.class,
		resourcePath = "osbasahcereroinfo/user_journal_info.json"
	)
	@Test
	public void testGetAnonymousUsersCount() {
		Assertions.assertEquals(
			2,
			_userDog.getAnonymousUsersCount(
				JournalMetricType.VIEWS, _searchQueryContext));
	}

	@RepositoryResource(
		repositoryClass = CrudBQJournalRepository.class,
		resourcePath = "osbasahcereroinfo/user_journal_info.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/user_journal_individuals_info.json"
	)
	@Test
	public void testGetKnownUsersCount() {
		Assertions.assertEquals(
			4,
			_userDog.getKnownUsersCount(
				JournalMetricType.VIEWS, _searchQueryContext));
	}

	@RepositoryResource(
		repositoryClass = CrudBQJournalRepository.class,
		resourcePath = "osbasahcereroinfo/user_journal_info.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/user_journal_individuals_info.json"
	)
	@Test
	public void testGetSegmentedIndividualsCount() {
		Assertions.assertEquals(
			1,
			_userDog.getSegmentedIndividualsCount(
				JournalMetricType.VIEWS, _searchQueryContext));
	}

	private final SearchQueryContext _searchQueryContext =
		new SearchQueryContext("1", AssetType.JOURNAL) {
			{
				setTimeRange(TimeRange.LAST_7_DAYS);
			}
		};

	@Autowired
	private UserDog _userDog;

}