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

package com.liferay.osb.asah.upgrade.v4_0_2.test;

import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_2.StaticMembershipMigrationUpgradeStep;

import java.util.List;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Leslie Wong
 */
public class StaticMembershipMigrationUpgradeStepTest
	implements OSBAsahTestExecutionListenersContext,
			   OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/individuals_index_configuration.json", this),
			"test_osbasahfaroinfo_individuals");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_individuals_alias",
			"test_osbasahfaroinfo_individuals");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete("test_osbasahfaroinfo_individuals");
	}

	@BQSQLResource(
		resourcePath = "test_static_membership_migration_upgrade_step_bq.sql"
	)
	@SQLResource(
		resourcePath = "test_static_membership_migration_upgrade_step.sql"
	)
	@Test
	public void testUpgrade() throws Exception {
		JSONArray individualsJSONArray = new JSONArray();

		individualsJSONArray.put(
			JSONUtil.put(
				"emailAddressHashed", "id1"
			).put(
				"individualSegmentIds", JSONUtil.putAll("12345")
			));
		individualsJSONArray.put(
			JSONUtil.put(
				"emailAddressHashed", "id2"
			).put(
				"individualSegmentIds", JSONUtil.putAll("12345", "67890")
			));
		individualsJSONArray.put(
			JSONUtil.put(
				"emailAddressHashed", "id3"
			).put(
				"individualSegmentIds", JSONUtil.putAll("12345", "67890")
			));

		_elasticsearchInvoker.add("individuals", individualsJSONArray);

		_staticMembershipMigrationUpgradeStep.upgrade("");

		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findBySegmentIdAndStatus(
				12345L, "ACTIVE", PageRequest.of(0, 20));

		Assertions.assertEquals(5L, bqMemberships.size());

		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.findBySegmentId(12345L);

		Assertions.assertEquals(24, bqMembershipChanges.size());

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			Assertions.assertEquals(3, bqMembershipChange.getIdentitiesCount());
			Assertions.assertEquals(
				3, bqMembershipChange.getIndividualsCount());
		}

		Assertions.assertEquals(
			0, _bqMembershipRepository.countBySegmentId(67890L));
		Assertions.assertEquals(
			0, _bqMembershipChangeRepository.countBySegmentId(67890L));
	}

	@BQSQLResource(
		resourcePath = "test_static_membership_migration_upgrade_step_bq.sql"
	)
	@SQLResource(
		resourcePath = "test_static_membership_migration_upgrade_step.sql"
	)
	@Test
	public void testUpgradeRerun() throws Exception {
		JSONArray individualsJSONArray = new JSONArray();

		individualsJSONArray.put(
			JSONUtil.put(
				"emailAddressHashed", "id1"
			).put(
				"individualSegmentIds", JSONUtil.putAll("12345")
			));
		individualsJSONArray.put(
			JSONUtil.put(
				"emailAddressHashed", "id2"
			).put(
				"individualSegmentIds", JSONUtil.putAll("12345", "67890")
			));
		individualsJSONArray.put(
			JSONUtil.put(
				"emailAddressHashed", "id3"
			).put(
				"individualSegmentIds", JSONUtil.putAll("12345", "67890")
			));

		_elasticsearchInvoker.add("individuals", individualsJSONArray);

		_staticMembershipMigrationUpgradeStep.upgrade("");

		List<BQMembership> bqMemberships =
			_bqMembershipRepository.findBySegmentIdAndStatus(
				12345L, "ACTIVE", PageRequest.of(0, 20));

		Assertions.assertEquals(5L, bqMemberships.size());

		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.findBySegmentId(12345L);

		Assertions.assertEquals(24, bqMembershipChanges.size());

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			Assertions.assertEquals(3, bqMembershipChange.getIdentitiesCount());
			Assertions.assertEquals(
				3, bqMembershipChange.getIndividualsCount());
		}

		_staticMembershipMigrationUpgradeStep.upgrade("");

		Assertions.assertEquals(5L, bqMemberships.size());

		bqMembershipChanges = _bqMembershipChangeRepository.findBySegmentId(
			12345L);

		Assertions.assertEquals(24, bqMembershipChanges.size());

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			Assertions.assertEquals(3, bqMembershipChange.getIdentitiesCount());
			Assertions.assertEquals(
				3, bqMembershipChange.getIndividualsCount());
		}
	}

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private StaticMembershipMigrationUpgradeStep
		_staticMembershipMigrationUpgradeStep;

}