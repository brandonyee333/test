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

import com.liferay.osb.asah.common.dog.BQTeamDog;
import com.liferay.osb.asah.common.entity.BQTeam;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQTeamRepository;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Marcos Martins
 */
@BQSQLResource(resourcePath = "test_bq_team_dog.sql")
public class BQTeamDogTest extends BaseBQDXPEntityDogTestCase {

	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testGetBQTeamPage() {
		Page<BQTeam> bqTeamPage = _bqTeamDog.getBQTeamPage(
			11L, null, 10, Sort.asc("name"), 0);

		Assertions.assertEquals(2, bqTeamPage.getTotalElements());

		List<BQTeam> bqTeams = bqTeamPage.getContent();

		Assertions.assertEquals(2, bqTeams.size(), bqTeams.toString());

		bqTeamPage = _bqTeamDog.getBQTeamPage(
			11L, "Test", 10, Sort.asc("name"), 0);

		Assertions.assertEquals(1, bqTeamPage.getTotalElements());

		bqTeams = bqTeamPage.getContent();

		Assertions.assertEquals(1, bqTeams.size(), bqTeams.toString());
	}

	@Autowired
	private BQTeamDog _bqTeamDog;

	@Autowired
	private BQTeamRepository _bqTeamRepository;

}