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

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualSegmentActivityFieldsNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		if (!_dataSourceDog.isAnalyticsConfigured()) {
			if (_isAnalyticsConfigured()) {
				_segmentDog.resetSegments();
			}

			_setAnalyticsConfigured(false);

			return;
		}

		_setAnalyticsConfigured(true);

		int page = 0;

		List<Segment> segments = _segmentDog.getSegments(
			"Account: ", page, 500);

		while (!segments.isEmpty()) {
			for (Segment segment : segments) {
				try {
					_process(segment);
				}
				catch (Exception exception) {
					_log.error(exception, exception);
				}
			}

			segments = _segmentDog.getSegments("Account: ", ++page, 500);
		}
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private Date _getLastActivityDate(
		Long channelId, boolean includeAnonymousUsers, Long segmentId) {

		List<Individual> searchIndividuals = _individualDog.searchIndividuals(
			channelId, includeAnonymousUsers, segmentId, 0, 1,
			new String[] {"lastActivityDate", "desc"});

		if (searchIndividuals.isEmpty()) {
			return null;
		}

		Individual individual = searchIndividuals.get(0);

		for (Individual.ActivityDate lastActivityDate :
				individual.getLastActivityDates()) {

			if (Objects.equals(lastActivityDate.getChannelId(), channelId)) {
				return lastActivityDate.getActivityDate();
			}
		}

		return null;
	}

	private boolean _isAnalyticsConfigured() {
		return _analyticsConfigured.getOrDefault(
			ProjectIdThreadLocal.getProjectId(), false);
	}

	private void _process(Segment segment) {
		if (segment.getLastActivityDate() == null) {
			segment = _segmentDog.updateSegment(segment, segment.getId());
		}

		boolean includeAnonymousUsers = BooleanUtils.toBoolean(
			segment.getIncludeAnonymousUsers());

		Date lastActivityDate = _getLastActivityDate(
			segment.getChannelId(), includeAnonymousUsers, segment.getId());

		if (Objects.nonNull(segment.getLastActivityDate()) &&
			Objects.equals(lastActivityDate, segment.getLastActivityDate())) {

			return;
		}

		if (Objects.nonNull(lastActivityDate)) {
			segment.setLastActivityDate(lastActivityDate);
		}

		_segmentDog.replaceSegment(segment);
	}

	private void _setAnalyticsConfigured(boolean analyticsConfigured) {
		_analyticsConfigured.put(
			ProjectIdThreadLocal.getProjectId(), analyticsConfigured);
	}

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentActivityFieldsNanite.class);

	private final Map<String, Boolean> _analyticsConfigured = new HashMap<>();

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private SegmentDog _segmentDog;

}