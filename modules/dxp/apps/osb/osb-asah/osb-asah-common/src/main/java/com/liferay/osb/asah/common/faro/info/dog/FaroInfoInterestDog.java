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
import com.liferay.osb.asah.common.json.JSONUtil;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoInterestDog extends BaseFaroInfoDog {

	public void addOrUpdateInterestThreshold(double score) {
		elasticsearchInvoker.update(
			"OSBAsahMarkers", "InterestThresholdScoreNanite",
			JSONUtil.put(
				"lastSuccessfulDay", DateUtil.newDayDateString()
			).put(
				"score", score
			));
	}

	@CacheEvict(
		allEntries = true,
		value = {"getInterests", "getInterestTransformations"}
	)
	public void clearCache() {
	}

}