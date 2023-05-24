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

package com.liferay.osb.asah.upgrade.v4_0_1;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.BQMembershipChangeDog;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class SegmentMembershipsCountUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		for (Segment segment : _segmentRepository.findAll()) {
			BQMembershipChange lastBQMembershipChange =
				_bqMembershipChangeDog.getLastBQMembershipChangeBySegmentId(
					segment.getId());

			Date startDate = DateUtil.addDays(
				lastBQMembershipChange.getCreateDate(), -30);

			if (startDate.before(segment.getCreateDate())) {
				startDate = segment.getCreateDate();
			}

			_populateSegmentMembershipsCount(
				lastBQMembershipChange.getCreateDate(), lastBQMembershipChange,
				startDate);
		}
	}

	private void _populateSegmentMembershipsCount(
		Date endDate, BQMembershipChange lastBQMembershipChange,
		Date startDate) {

		while (startDate.before(endDate)) {
			BQMembershipChange bqMembershipChange = new BQMembershipChange();

			bqMembershipChange.setCreateDate(startDate);
			bqMembershipChange.setIdentitiesCount(
				lastBQMembershipChange.getIdentitiesCount());
			bqMembershipChange.setIndividualsCount(
				lastBQMembershipChange.getIndividualsCount());
			bqMembershipChange.setSegmentId(
				lastBQMembershipChange.getSegmentId());

			_bqMembershipChangeDog.addBQMembershipChange(bqMembershipChange);

			startDate = DateUtil.addDays(startDate, 1);
		}
	}

	@Autowired
	private BQMembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private SegmentRepository _segmentRepository;

}