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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.BQMembershipChangeDog;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v4_0_2.SegmentMembershipsCountUpgradeStep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class SegmentMembershipsCountUpgradeStepTest
	implements OSBAsahTestExecutionListenersContext,
			   OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() {
		ProjectIdThreadLocal.setProjectId("test");
	}

	@AfterEach
	public void tearDown() {
		try {
			List<Long> segmentIds = new ArrayList<>();

			for (Segment segment : _segmentRepository.findAll()) {
				segmentIds.add(segment.getId());

				_segmentRepository.delete(segment);
			}

			_bqMembershipChangeDog.deleteBQMembershipChanges(segmentIds);
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	@Test
	public void testUpgrade1() {
		Date date = DateUtil.toUTCDate("2023-04-01T00:00:00.000Z");

		Segment segment = new Segment();

		segment.setIsNew(Boolean.TRUE);
		segment.setCreateDate(date);

		segment = _segmentRepository.save(segment);

		BQMembershipChange bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setIdentitiesCount(50L);
		bqMembershipChange.setIndividualsCount(27L);
		bqMembershipChange.setSegmentId(segment.getId());
		bqMembershipChange.setCreateDate(
			DateUtil.toUTCDate("2023-05-20T00:00:00.000Z"));

		_bqMembershipChangeDog.addBQMembershipChange(bqMembershipChange);

		bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setIdentitiesCount(47L);
		bqMembershipChange.setIndividualsCount(20L);
		bqMembershipChange.setSegmentId(segment.getId());
		bqMembershipChange.setCreateDate(
			DateUtil.toUTCDate("2023-05-15T00:00:00.000Z"));

		_bqMembershipChangeDog.addBQMembershipChange(bqMembershipChange);

		bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setIdentitiesCount(24L);
		bqMembershipChange.setIndividualsCount(15L);
		bqMembershipChange.setSegmentId(segment.getId());
		bqMembershipChange.setCreateDate(
			DateUtil.toUTCDate("2023-05-05T00:00:00.000Z"));

		_bqMembershipChangeDog.addBQMembershipChange(bqMembershipChange);

		_segmentMembershipsCountUpgradeStep.upgrade("");

		Long segmentId = segment.getId();

		Assertions.assertNotNull(segmentId);

		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.findBySegmentId(segmentId);

		Assertions.assertEquals(33, bqMembershipChanges.size());

		int count = 0;

		for (BQMembershipChange actualBQMembershipChange :
				bqMembershipChanges) {

			Date actualCreateDate = actualBQMembershipChange.getCreateDate();

			if (actualCreateDate.after(bqMembershipChange.getCreateDate())) {
				continue;
			}

			Assertions.assertEquals(
				bqMembershipChange.getIdentitiesCount(),
				actualBQMembershipChange.getIdentitiesCount());
			Assertions.assertEquals(
				bqMembershipChange.getIndividualsCount(),
				actualBQMembershipChange.getIndividualsCount());

			count++;
		}

		Assertions.assertEquals(31, count);
	}

	@Test
	public void testUpgradeNoBQMembershipChange() {
		Segment segment = new Segment();

		segment.setIsNew(Boolean.TRUE);
		segment.setCreateDate(DateUtil.toUTCDate("2023-05-01T00:00:00.000Z"));

		segment = _segmentRepository.save(segment);

		_segmentMembershipsCountUpgradeStep.upgrade("");

		Long segmentId = segment.getId();

		Assertions.assertNotNull(segmentId);

		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.findBySegmentId(segmentId);

		Assertions.assertTrue(bqMembershipChanges.isEmpty());
	}

	@Autowired
	private BQMembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private SegmentMembershipsCountUpgradeStep
		_segmentMembershipsCountUpgradeStep;

	@Autowired
	private SegmentRepository _segmentRepository;

}