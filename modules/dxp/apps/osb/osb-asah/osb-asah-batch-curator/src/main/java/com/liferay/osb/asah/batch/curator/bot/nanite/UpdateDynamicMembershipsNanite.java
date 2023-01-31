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

import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Segment;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class UpdateDynamicMembershipsNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		int page = 0;

		while (true) {
			Page<Segment> segmentPage = _segmentDog.getSegmentPage(page++, 50);

			if (segmentPage.isEmpty()) {
				break;
			}

			_updateSegmentsMemberships(segmentPage.getContent());
		}
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private void _updateSegmentMemberships(Segment segment) {
		String filterString = segment.getFilter();
		Long segmentId = segment.getId();

		try {
			_bqMembershipDog.updateBQMemberships(filterString, segmentId);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Memberships updated successfully for Segment ID %s, " +
							"filterString %s ",
						segmentId, filterString));
			}
		}
		catch (Exception exception) {
			_log.error(
				String.format(
					"Unable to update memberships for Segment ID %s, " +
						"filterString %s ",
					segmentId, filterString));
		}
	}

	private void _updateSegmentsMemberships(List<Segment> segments) {
		for (Segment segment : segments) {
			_updateSegmentMemberships(segment);
		}
	}

	private static final Log _log = LogFactory.getLog(
		UpdateDynamicMembershipsNanite.class);

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private SegmentDog _segmentDog;

}