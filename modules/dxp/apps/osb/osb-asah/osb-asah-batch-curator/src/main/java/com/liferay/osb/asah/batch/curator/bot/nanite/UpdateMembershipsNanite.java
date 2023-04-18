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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.dog.BQMembershipChangeDog;
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Segment;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class UpdateMembershipsNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		_updateDynamicSegmentMemberships();
		_updateStaticSegmentMembershipChanges();
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private void _updateDynamicSegmentMemberships() {
		int page = 0;

		while (true) {
			List<Segment> segments = _segmentDog.getSegments(
				page++, 50, Segment.Type.DYNAMIC);

			if (segments.isEmpty()) {
				break;
			}

			segments.forEach(this::_updateDynamicSegmentMemberships);
		}
	}

	private void _updateDynamicSegmentMemberships(Segment segment) {
		String filterString = segment.getFilter();
		Long segmentId = segment.getId();

		try {
			_bqMembershipDog.updateBQMemberships(
				segment.getChannelId(), filterString,
				segment.getIncludeAnonymousUsers(), segmentId);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Updated memberships successfully for segment ID %s " +
							"and filter %s",
						segmentId, filterString));
			}
		}
		catch (Exception exception) {
			_log.error(
				String.format(
					"Unable to update memberships for segment ID %s and " +
						"filter %s",
					segmentId, filterString));
		}
	}

	private void _updateStaticSegmentMembershipChanges() {
		int page = 0;

		while (true) {
			List<Segment> segments = _segmentDog.getSegments(
				page++, 50, Segment.Type.STATIC);

			if (segments.isEmpty()) {
				break;
			}

			segments.forEach(this::_updateStaticSegmentMembershipChanges);
		}
	}

	private void _updateStaticSegmentMembershipChanges(Segment segment) {
		Long segmentId = segment.getId();

		try {
			_bqMembershipChangeDog.addBQMembershipChange(segmentId);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Updated membership changes successfully for segment " +
							"ID %s",
						segmentId));
			}
		}
		catch (Exception exception) {
			_log.error(
				String.format(
					"Unable to update membership changes for segment ID %s",
					segmentId));
		}
	}

	private static final Log _log = LogFactory.getLog(
		UpdateMembershipsNanite.class);

	@Autowired
	private BQMembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private SegmentDog _segmentDog;

}