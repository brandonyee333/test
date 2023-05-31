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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.dog.BQMembershipIndividualDog;
import com.liferay.osb.asah.common.entity.BQMembershipIndividual;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Marcellus Tavares
 */
public class BQMembershipIndividualDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BQSQLResource(resourcePath = "test_bq_update_membership_individuals.sql")
	@Test
	public void testUpdateMembershipIndividuals1() {
		_bqMembershipIndividualDog.updateMembershipIndividuals();

		Page<BQMembershipIndividual> membershipIndividualPage =
			_bqMembershipIndividualDog.getMembershipIndividualPage(
				0, 1L, 10, new String[] {"dateModified", "desc"});

		Assertions.assertEquals(2, membershipIndividualPage.getTotalElements());

		List<BQMembershipIndividual> membershipIndividuals =
			membershipIndividualPage.getContent();

		BQMembershipIndividual membershipIndividual1 =
			membershipIndividuals.get(0);

		Assertions.assertEquals("A", membershipIndividual1.getIndividualId());

		List<BQMembershipIndividual.DataSourceUser> dataSourceUsers1 =
			membershipIndividual1.getDataSourceUsers();

		Assertions.assertEquals(2, dataSourceUsers1.size());

		BQMembershipIndividual membershipIndividual2 =
			membershipIndividuals.get(1);

		Assertions.assertEquals("C", membershipIndividual2.getIndividualId());

		Assertions.assertEquals(
			Arrays.asList(
				new BQMembershipIndividual.DataSourceUser(1L, "uuid-3")),
			membershipIndividual2.getDataSourceUsers());
	}

	@BQSQLResource(resourcePath = "test_bq_update_membership_individuals.sql")
	@Test
	public void testUpdateMembershipIndividuals2() {
		_bqMembershipIndividualDog.updateMembershipIndividuals(2L);

		Page<BQMembershipIndividual> membershipIndividualPage =
			_bqMembershipIndividualDog.getMembershipIndividualPage(
				0, 2L, 10, new String[] {"dateModified", "desc"});

		Assertions.assertEquals(1, membershipIndividualPage.getTotalElements());
	}

	@Autowired
	private BQMembershipIndividualDog _bqMembershipIndividualDog;

}