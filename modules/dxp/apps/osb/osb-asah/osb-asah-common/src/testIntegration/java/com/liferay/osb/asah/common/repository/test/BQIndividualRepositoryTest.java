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
			1,
			_bqIndividualRepository.countBQIndividuals(
				null, 11L, null, null, null, _SEGMENT_ID));
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
				null, 11L, null, null,
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))), null,
				null);

		Assertions.assertEquals(1, individuals.size(), individuals.toString());

		Individual individual = individuals.get(0);

		Assertions.assertEquals(4L, individual.getActivitiesCount());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2022-12-17T23:59:59.999Z"),
			individual.getLastActivityDate());
	}

	@BQSQLResource(resourcePath = "test_bq_individual_repository_1.sql")
	@Test
	public void testSearchBQIndividualsCustomFieldFilter() {
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
	public void testSearchBQIndividualsOrganizationsFilter() {
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