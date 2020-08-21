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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class StaleDynamicIndividualSegmentsNanite extends BaseNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		JSONObject osbAsahMarkerJSONObject = getOSBAsahMarkerJSONObject();

		String lastRunDateString = osbAsahMarkerJSONObject.optString(
			"lastRunDate", null);

		if (lastRunDateString != null) {
			Date lastRunDate = DateUtil.toUTCDate(lastRunDateString);

			if (!lastRunDate.before(DateUtil.newDayDate())) {
				return;
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < _FILTER_STRINGS.length; i++) {
			String filterString = _FILTER_STRINGS[i];

			sb.append("contains(filter, '");
			sb.append(filterString);
			sb.append("')");

			if (i < (_FILTER_STRINGS.length - 1)) {
				sb.append(" or ");
			}
		}

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", DateUtil.newDayDateString()
			).put(
				"removeFilter", sb.toString()
			));

		osbAsahMarkerJSONObject.put("lastRunDate", DateUtil.newDayDateString());

		faroInfoElasticsearchInvoker.update(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(StaleDynamicIndividualSegmentsNanite.class);
	}

	private static final String[] _FILTER_STRINGS = {
		"activities/last30Days eq", "activities/last7Days eq",
		"activities/lastYear eq", "activities/today eq", "gt ''last24Hours''",
		"gt ''last28Days''", "gt ''last30Days''", "gt ''last7Days''",
		"gt ''last90Days''", "gt ''yesterday''"
	};

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

}