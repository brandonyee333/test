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

package com.liferay.osb.asah.common.faro.info.dog;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.annotation.CacheEvict;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoInterestDog extends BaseFaroInfoDog {

	public void addOrUpdateInterestThreshold(double score) {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"InterestThresholdScoreNanite",
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (asahMarker == null) {
			_asahMarkerDog.addAsahMarker(
				new AsahMarker(
					"InterestThresholdScoreNanite",
					JSONUtil.put(
						"lastSuccessfulDay", DateUtil.newDayDateString()
					).put(
						"score", score
					)),
				WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
		else {
			JSONObject asahMarkerContextJSONObject =
				asahMarker.getContextJSONObject();

			asahMarkerContextJSONObject.put(
				"lastSuccessfulDay", DateUtil.newDayDateString());
			asahMarkerContextJSONObject.put("score", score);

			_asahMarkerDog.updateAsahMarker(
				asahMarker, WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
	}

	@CacheEvict({"getInterestDTOPageDTO", "getInterestTransformations"})
	public void clearCache() {
	}

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

}