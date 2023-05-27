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
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class StaticMembershipMigrationUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		int count = 0;

		List<Segment> segments = _segmentDog.getSegments(
			count, 50, Segment.Type.STATIC);

		while (!segments.isEmpty()) {
			for (Segment segment : segments) {
				Long segmentId = segment.getId();

				if (segmentId == null) {
					continue;
				}

				List<BQMembership> bqMemberships =
					_bqMembershipRepository.findBySegmentIdAndStatus(
						segmentId, "ACTIVE", PageRequest.of(0, 1));

				if (!bqMemberships.isEmpty()) {
					if (_log.isInfoEnabled()) {
						_log.info(
							String.format(
								"Skipping segment %d as it already has " +
									"memberships",
								segmentId));
					}

					continue;
				}

				int individualsCount = _addBQMemberships(
					segment.getChannelId(), segmentId);

				_updateExistingStaticBQMembershipChanges(
					individualsCount, segmentId);
			}

			segments = _segmentDog.getSegments(
				++count, 50, Segment.Type.STATIC);
		}
	}

	private int _addBQMemberships(Long channelId, Long segmentId) {
		List<String> individualIds = _getIndividualIds(segmentId);

		for (String individualId : individualIds) {
			List<String> bqIdentities = _bqIdentityRepository.getBQIdentityIds(
				individualId);

			List<BQMembership> bqMemberships = new ArrayList<>();

			for (String bqIdentity : bqIdentities) {
				BQMembership bqMembership = new BQMembership();

				bqMembership.setChannelId(channelId);
				bqMembership.setCreateDate(DateUtil.newDayDate());
				bqMembership.setIdentityId(bqIdentity);
				bqMembership.setIndividualId(individualId);
				bqMembership.setModifiedDate(DateUtil.newDayDate());
				bqMembership.setSegmentId(segmentId);
				bqMembership.setStatus("ACTIVE");

				bqMemberships.add(bqMembership);
			}

			_bqMembershipRepository.insertAll(bqMemberships);
		}

		return individualIds.size();
	}

	private List<String> _getIndividualIds(Long segmentId) {
		JSONArray individualsJSONArray = _elasticsearchInvoker.get(
			"individuals",
			QueryBuilders.termQuery("individualSegmentIds", segmentId));

		return JSONUtil.toStringList(
			individualsJSONArray, "emailAddressHashed");
	}

	private void _updateExistingStaticBQMembershipChanges(
		int individualsCount, Long segmentId) {

		List<BQMembershipChange> bqMembershipChanges =
			_bqMembershipChangeRepository.findBySegmentId(segmentId);

		_bqMembershipChangeRepository.deleteBySegmentIdIn(
			Collections.singletonList(segmentId));

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			bqMembershipChange.setIndividualsCount((long)individualsCount);
		}

		_bqMembershipChangeRepository.insertAll(bqMembershipChanges);
	}

	private static final Log _log = LogFactory.getLog(
		StaticMembershipMigrationUpgradeStep.class);

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQMembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private SegmentDog _segmentDog;

}