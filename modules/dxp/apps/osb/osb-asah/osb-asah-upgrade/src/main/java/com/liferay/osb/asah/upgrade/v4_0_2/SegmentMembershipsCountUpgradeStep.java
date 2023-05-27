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

package com.liferay.osb.asah.upgrade.v4_0_2;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.BQMembershipChangeDog;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class SegmentMembershipsCountUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		for (Segment segment : _segmentRepository.findAll()) {
			Long segmentId = segment.getId();

			if (Objects.isNull(segmentId)) {
				if (_log.isWarnEnabled()) {
					_log.warn("Skipping segment with null ID");
				}

				continue;
			}

			BQMembershipChange bqMembershipChange = _findBQMembershipChange(
				segmentId);

			if (bqMembershipChange == null) {
				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"No membership changes found. Skipping segment %d.",
							segmentId));
				}

				continue;
			}

			Date startDate = DateUtil.addDays(
				bqMembershipChange.getCreateDate(), -30);

			_populateSegmentMembershipsCount(
				bqMembershipChange.getCreateDate(), bqMembershipChange,
				segment.getCreateDate(), startDate);
		}
	}

	private BQMembershipChange _findBQMembershipChange(Long segmentId) {
		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.findBySegmentId(segmentId);

		Stream<BQMembershipChange> bqMembershipChangesStream =
			bqMembershipChanges.stream();

		Optional<BQMembershipChange> bqMembershipChangeOptional =
			bqMembershipChangesStream.filter(
				bqMembershipChange -> bqMembershipChange.getCreateDate() != null
			).min(
				Comparator.comparing(BQMembershipChange::getCreateDate)
			);

		return bqMembershipChangeOptional.orElse(null);
	}

	private void _populateSegmentMembershipsCount(
		Date endDate, BQMembershipChange lastBQMembershipChange,
		Date segmentCreateDate, Date startDate) {

		while (startDate.before(endDate)) {
			BQMembershipChange bqMembershipChange = new BQMembershipChange();

			bqMembershipChange.setCreateDate(startDate);
			bqMembershipChange.setIdentitiesCount(
				lastBQMembershipChange.getIdentitiesCount());
			bqMembershipChange.setIndividualsCount(
				lastBQMembershipChange.getIndividualsCount());
			bqMembershipChange.setSegmentId(
				lastBQMembershipChange.getSegmentId());

			if (startDate.before(segmentCreateDate)) {
				bqMembershipChange.setIdentitiesCount(0L);
				bqMembershipChange.setIndividualsCount(0L);
			}

			_bqMembershipChangeDog.addBQMembershipChange(bqMembershipChange);

			startDate = DateUtil.addDays(startDate, 1);
		}
	}

	private static final Log _log = LogFactory.getLog(
		SegmentMembershipsCountUpgradeStep.class);

	@Autowired
	private BQMembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}