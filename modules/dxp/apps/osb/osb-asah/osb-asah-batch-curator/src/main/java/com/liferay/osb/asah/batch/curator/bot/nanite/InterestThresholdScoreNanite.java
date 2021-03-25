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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.bot.nanite.arm.InterestScoreArm;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoAssetDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoInterestDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Segment;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class InterestThresholdScoreNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		JSONObject osbAsahMarkerJSONObject = getOSBAsahMarkerJSONObject();

		String lastSuccessfulDayDateString = osbAsahMarkerJSONObject.optString(
			"lastSuccessfulDay", null);

		if (lastSuccessfulDayDateString != null) {
			Date lastSuccessfulDayDate = DateUtil.toUTCDate(
				lastSuccessfulDayDateString);

			if (!lastSuccessfulDayDate.before(DateUtil.newDayDate())) {
				return;
			}
		}

		List<String> keywords = _faroInfoAssetDog.getKeywords();

		if (keywords.isEmpty()) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to find keywords to calculate threshold");
			}

			return;
		}

		double score = Double.MAX_VALUE;

		for (String keyword : keywords) {
			score = Math.min(
				score, _interestScoreArm.computeThresholdScore(keyword));
		}

		_faroInfoInterestDog.addOrUpdateInterestThreshold(score);

		int page = 0;

		List<Segment> segments = _segmentDog.getSegments(
			".*interests.filter\\(filter='.*score eq ''true''.*", "READY",
			"ACTIVE", page, 500);

		while (!segments.isEmpty()) {
			for (Segment segment : segments) {
				_asahTaskDog.scheduleAsahTask(
					"UpdateDynamicMembershipsNanite",
					JSONUtil.put(
						"dateModified", DateUtil.newDateString()
					).put(
						"individualSegmentJSONObject",
						_objectMapper.convertValue(segment, JSONObject.class)
					));
			}

			segments = _segmentDog.getSegments(
				".*interests.filter\\(filter='.*score eq ''true''.*", "READY",
				"ACTIVE", ++page, 500);
		}

		_faroInfoInterestDog.clearCache();
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private static final Log _log = LogFactory.getLog(
		InterestThresholdScoreNanite.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private FaroInfoAssetDog _faroInfoAssetDog;

	@Autowired
	private FaroInfoInterestDog _faroInfoInterestDog;

	@Autowired
	private InterestScoreArm _interestScoreArm;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}