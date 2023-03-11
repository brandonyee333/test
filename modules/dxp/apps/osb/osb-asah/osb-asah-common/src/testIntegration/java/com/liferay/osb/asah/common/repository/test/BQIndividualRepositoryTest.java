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
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

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
public class BQIndividualRepositoryTest {

	@BQSQLResource(resourcePath = "test_bq_individual_repository.sql")
	@Test
	public void testCountBQIndividuals() {
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				null, 11L, null, null, null, _SEGMENT_ID));
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

	private static final Long _SEGMENT_ID = 11L;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

}