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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Ivica Cardic
 */
@Import(JDBCTestConfiguration.class)
public class BQIndividualRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BQSQLResource(resourcePath = "test_bq_individual_repository.sql")
	@Test
	public void testCountBQIndividuals() {
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				null, 11L, null, null, null, null, _SEGMENT_ID));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				null, 11L, null, "all", null, null, _SEGMENT_ID));
		Assertions.assertEquals(
			0,
			_bqIndividualRepository.countBQIndividuals(
				null, 11L, null, "fail", null, null, _SEGMENT_ID));
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testCountIndividualFieldValuesDemographics() {
		Assertions.assertEquals(
			3,
			_bqIndividualRepository.countIndividualFieldValuesDemographics(
				11L, "firstName", null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countIndividualFieldValuesDemographics(
				11L, "jobTitle", null));
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository.sql")
	@Test
	public void testSearchBQIndividuals() {
		List<Individual> individuals =
			_bqIndividualRepository.searchBQIndividuals(
				null, 11L, null, "all", null,
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))), null,
				null);

		Assertions.assertEquals(2, individuals.size(), individuals.toString());

		Individual individual = individuals.get(0);

		Assertions.assertEquals(4L, individual.getActivitiesCount());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2022-12-17T23:59:59.999Z"),
			individual.getLastActivityDate());
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testSearchBQIndividualsActivitiesFilter() {
		String assetId =
			"da70dfa4d9f95ac979f921e8e623358236313f334afcd06cddf8a5621cf6a1e9";

		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(activities.filterByCount(filter='(activityKey eq " +
					"''WebContent#webContentViewed#" + assetId + "'' and day " +
						"eq ''2022-12-16'')', operator='ge', value=1))",
				false, null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(not(activities.filterByCount(filter='(activityKey eq " +
					"''WebContent#webContentViewed#" + assetId + "'' and day " +
						"eq ''2022-12-16'')', operator='ge', value=1)))",
				false, null));
		Assertions.assertEquals(
			0,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(activities.filterByCount(filter='(activityKey eq " +
					"''WebContent#webContentViewed#" + assetId + "'' and day " +
						"gt ''2022-12-17'')', operator='ge', value=1))",
				false, null));
		Assertions.assertEquals(
			3,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(not(activities.filterByCount(filter='(activityKey eq " +
					"''WebContent#webContentViewed#" + assetId + "'' and day " +
						"gt ''2022-12-17'')', operator='ge', value=1)))",
				false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(activities.filterByCount(filter='(activityKey eq " +
					"''WebContent#webContentViewed#" + assetId + "'' and day " +
						"lt ''2022-12-17'')', operator='ge', value=1))",
				false, null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(not(activities.filterByCount(filter='(activityKey eq " +
					"''WebContent#webContentViewed#" + assetId + "'' and day " +
						"lt ''2022-12-17'')', operator='ge', value=1)))",
				false, null));

		assetId =
			"0e12831a7047f759733b21f028525039607350b1b1b4fe904595427e72ea0d9b";

		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(activities.filterByCount(filter='(activityKey eq ''Blog#" +
					"commentPosted#" + assetId + "'' and day lt ''" +
						"2022-12-17'')', operator='ge', value=1))",
				false, null));
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testSearchBQIndividualsCustomFieldFilter() {
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L, "contains(custom/Favorite_Food/value, 'Rice')", false,
				null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "not contains(custom/Favorite_Food/value, 'Rice')", false,
				null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "contains(custom/Favorite_Number/value, 3)", false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "not contains(custom/Favorite_Number/value, 5)", false,
				null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Favorite_Number/value ge 3", false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"custom/Favorite_Number/value gt 2 and " +
					"custom/Favorite_Number/value lt 3",
				false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Favorite_Number/value gt 3", false, null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Favorite_Number/value le 4", false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Favorite_Number/value lt 2", false, null));
		Assertions.assertEquals(
			0,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Hobbies/value eq 'ing'", false, null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Hobbies/value eq 'Exercise'", false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Hobbies/value ne 'Exercise'", false, null));
		Assertions.assertEquals(
			3,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Hobbies/value ne 'ing'", false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Salary/value ge 120000.30", false, null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Salary/value gt 100000", false, null));
		Assertions.assertEquals(
			0,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Salary/value le 100000.00", false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Salary/value lt 100001", false, null));
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testSearchBQIndividualsDateCustomFieldFilter() {
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Joined_Date/value eq '2022-04-30'", false, null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Joined_Date/value ge '2022-04-30'", false, null));
		Assertions.assertEquals(
			3,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Joined_Date/value gt '2022-01-01'", false, null));
		Assertions.assertEquals(
			3,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Joined_Date/value le '2022-06-01'", false, null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L, "custom/Joined_Date/value lt '2022-05-03'", false, null));
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_2.sql")
	@Test
	public void testSearchBQIndividualsIndividualSegmentIdsFilter() {
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "individualSegmentIds eq '1'", false, null));

		List<Individual> individuals =
			_bqIndividualRepository.searchBQIndividuals(
				11L, "individualSegmentIds eq '1'", PageRequest.of(0, 5), null);

		Individual individual = individuals.get(0);

		Assertions.assertEquals(
			"5970d88ec4ed505177361de1b17a3f2debf7c4f630c14f075a823ec97942692a",
			individual.getEmailAddressHashed());
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testSearchBQIndividualsInterestFilter() {
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(interests.filter(filter='(name eq ''analytics'' and score " +
					"eq ''true'')'))",
				false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"(interests.filter(filter='(name eq ''analytics'' and score " +
					"eq ''false'')'))",
				false, null));
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testSearchBQIndividualsOrganizationsFilter() {

		// Custom fields

		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"custom/Organization/value eq 'Developer' and " +
					"organizations.filter(filter='(id eq " +
						"''23k92323l923lf0as'')')",
				false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"custom/Birth_Country/value eq 'England' and " +
					"custom/Zip_Code/value eq 91765 and userGroupIds eq '" +
						"newr87232kjhdsf89'",
				false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(custom/Organization_Type" +
					"/value eq ''test'')')",
				false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(custom/Divisions/value ge 35)')",
				false, null));
		Assertions.assertEquals(
			0,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(custom/Divisions/value gt 35)')",
				false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(custom/Year/value le 2023)')",
				false, null));
		Assertions.assertEquals(
			0,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(custom/Year/value lt 2023)')",
				false, null));

		// Hierarchy path known/unknown

		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L, "(organizations.filter(filter='(hierarchyPath ne null)'))",
				false, null));
		Assertions.assertEquals(
			2,
			_bqIndividualRepository.countBQIndividuals(
				11L, "(organizations.filter(filter='(hierarchyPath eq null)'))",
				false, null));

		// Modified date

		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(modifiedDate eq " +
					"''2022-12-18'')')",
				false, null));
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(modifiedDate gt " +
					"''2022-12-17'')')",
				false, null));
		Assertions.assertEquals(
			0,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(modifiedDate lt " +
					"''2022-12-18'')')",
				false, null));

		// Name

		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(name eq ''Organization 1'')')",
				false, null));
		Assertions.assertEquals(
			0,
			_bqIndividualRepository.countBQIndividuals(
				11L,
				"organizations.filter(filter='(name ne ''Organization 1'')')",
				false, null));
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testSearchIndividualFieldValuesDemographics() {
		PageRequest pageRequest = PageRequest.of(0, 10);

		Assertions.assertEquals(
			Arrays.asList("Joe", "Marcus", "Nina"),
			_bqIndividualRepository.searchIndividualFieldValuesDemographics(
				11L, "firstName", null, pageRequest));
		Assertions.assertEquals(
			Arrays.asList("Engineer"),
			_bqIndividualRepository.searchIndividualFieldValuesDemographics(
				11L, "jobTitle", null, pageRequest));
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testSearchIndividualFieldValuesDemographicsUnknownChannel() {
		Assertions.assertEquals(
			Collections.emptyList(),
			_bqIndividualRepository.searchIndividualFieldValuesDemographics(
				42L, "firstName", null, PageRequest.of(0, 10)));
	}

	private static final Long _SEGMENT_ID = 11L;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

}