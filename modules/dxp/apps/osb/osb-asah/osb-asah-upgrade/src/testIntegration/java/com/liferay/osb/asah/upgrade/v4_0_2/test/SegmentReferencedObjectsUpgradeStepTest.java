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

import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v4_0_2.SegmentReferencedObjectsUpgradeStep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class SegmentReferencedObjectsUpgradeStepTest
	implements OSBAsahTestExecutionListenersContext,
			   OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() {
		ProjectIdThreadLocal.setProjectId("test");

		List<Segment> segments = new ArrayList<>();

		Segment segment = new Segment();

		String id1 = DigestUtils.sha256Hex("38284");
		String id2 = DigestUtils.sha256Hex("38283");

		segment.setFilter(
			"(activities.filterByCount(filter='(activityKey eq " +
				"''WebContent#webContentViewed#" + id1 + "'' and day gt " +
					"''last24Hours'')', operator='ge', value=1)) and " +
						"(activities.filterByCount(filter='(activityKey eq " +
							"''Page#pageViewed#" + id2 + "'' and day gt " +
								"''last24Hours'')', operator='ge', value=1))");

		segment.setId(1L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 1");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#20121");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#20125");

		segment.setFilter(
			"(groupIds eq '" + id1 + "') and (groupIds ne '" + id2 + "')");

		segment.setId(2L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 2");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#33120");

		segment.setFilter(
			"organizations.filter(filter='(id eq ''" + id1 + "'')')");

		segment.setId(3L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 3");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#20104");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#20108");

		segment.setFilter(
			"(roleIds eq '" + id1 + "') and (roleIds ne '" + id2 + "')");

		segment.setId(4L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 4");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#35472");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#35473");

		segment.setFilter(
			"(teamIds eq '" + id1 + "') and (teamIds ne '" + id2 + "')");

		segment.setId(5L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 5");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#35452");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#35453");

		segment.setFilter(
			"(userGroupIds eq '" + id1 + "') and (userGroupIds ne '" + id2 +
				"')");

		segment.setId(6L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 6");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#35852");

		segment.setFilter("(userId eq '" + id1 + "')");

		segment.setId(7L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 7");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		segment.setFilter(
			"(demographics/emailAddress/value eq 'test@liferay.com' and " +
				"custom/custom_field/value eq 'test')");

		segment.setId(8L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 8");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		_segmentRepository.saveAll(segments);
	}

	@AfterEach
	public void tearDown() {
		_segmentRepository.deleteAll();
	}

	@BQSQLResource(resourcePath = "test_referenced_objects_bq.sql")
	@SQLResource(resourcePath = "test_referenced_objects.sql")
	@Test
	public void testUpgrade() {
		_segmentReferencedObjectsUpgradeStep.upgrade("");

		Optional<Segment> segmentOptional = _segmentRepository.findById(1L);

		Segment segment = segmentOptional.get();

		String id1 = DigestUtils.sha256Hex("38284");
		String id2 = DigestUtils.sha256Hex("38283");

		Set<String> referencedAssetIds = segment.getReferencedAssetIds();

		Assertions.assertTrue(referencedAssetIds.contains(id1));
		Assertions.assertTrue(referencedAssetIds.contains(id2));

		Set<Long> referencedDataSourceIds =
			segment.getReferencedDataSourceIds();

		Assertions.assertTrue(
			referencedDataSourceIds.contains(405057430327289648L));

		segmentOptional = _segmentRepository.findById(2L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#20121");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#20125");

		Set<String> referencedGroupIds = segment.getReferencedGroupIds();

		Assertions.assertTrue(referencedGroupIds.contains(id1));
		Assertions.assertTrue(referencedGroupIds.contains(id2));

		referencedDataSourceIds = segment.getReferencedDataSourceIds();

		Assertions.assertTrue(
			referencedDataSourceIds.contains(405057430327289648L));

		segmentOptional = _segmentRepository.findById(3L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#33120");

		Set<String> referencedOrganizationIds =
			segment.getReferencedOrganizationIds();

		Assertions.assertTrue(referencedOrganizationIds.contains(id1));

		referencedDataSourceIds = segment.getReferencedDataSourceIds();

		Assertions.assertTrue(
			referencedDataSourceIds.contains(405057430327289648L));

		segmentOptional = _segmentRepository.findById(4L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#20104");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#20108");

		Set<String> referencedRoleIds = segment.getReferencedRoleIds();

		Assertions.assertTrue(referencedRoleIds.contains(id1));
		Assertions.assertTrue(referencedRoleIds.contains(id2));

		referencedDataSourceIds = segment.getReferencedDataSourceIds();

		Assertions.assertTrue(
			referencedDataSourceIds.contains(405057430327289648L));

		segmentOptional = _segmentRepository.findById(5L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#35472");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#35473");

		Set<String> referencedTeamIds = segment.getReferencedTeamIds();

		Assertions.assertTrue(referencedTeamIds.contains(id1));
		Assertions.assertTrue(referencedTeamIds.contains(id2));

		referencedDataSourceIds = segment.getReferencedDataSourceIds();

		Assertions.assertTrue(
			referencedDataSourceIds.contains(405057430327289648L));

		segmentOptional = _segmentRepository.findById(6L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#35452");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#35453");

		Set<String> referencedUserGroupIds =
			segment.getReferencedUserGroupIds();

		Assertions.assertTrue(referencedUserGroupIds.contains(id1));
		Assertions.assertTrue(referencedUserGroupIds.contains(id2));

		referencedDataSourceIds = segment.getReferencedDataSourceIds();

		Assertions.assertTrue(
			referencedDataSourceIds.contains(405057430327289648L));

		segmentOptional = _segmentRepository.findById(7L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#35852");

		Set<String> referencedUserIds = segment.getReferencedUserIds();

		Assertions.assertTrue(referencedUserIds.contains(id1));

		referencedDataSourceIds = segment.getReferencedDataSourceIds();

		Assertions.assertTrue(
			referencedDataSourceIds.contains(405057430327289648L));

		segmentOptional = _segmentRepository.findById(8L);

		segment = segmentOptional.get();

		Set<String> referencedFieldMappingFieldNames =
			segment.getReferencedFieldMappingFieldNames();

		Assertions.assertTrue(
			referencedFieldMappingFieldNames.contains("custom_field"));
		Assertions.assertTrue(
			referencedFieldMappingFieldNames.contains("emailAddress"));

		referencedDataSourceIds = segment.getReferencedDataSourceIds();

		Assertions.assertTrue(referencedDataSourceIds.isEmpty());
	}

	@Autowired
	private SegmentReferencedObjectsUpgradeStep
		_segmentReferencedObjectsUpgradeStep;

	@Autowired
	private SegmentRepository _segmentRepository;

}