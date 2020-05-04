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

import com.liferay.osb.asah.common.json.JSONUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoCSVIndividualDog extends BaseFaroInfoDog {

	public void addCSVIndividuals(JSONArray csvIndividualsJSONArray) {
		if (csvIndividualsJSONArray.length() == 0) {
			return;
		}

		elasticsearchInvoker.add("csv-individuals", csvIndividualsJSONArray);

		JSONObject csvIndividualJSONObject =
			csvIndividualsJSONArray.getJSONObject(0);

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"CSVIndividualsNanite",
			JSONUtil.put(
				"dataSourceId",
				csvIndividualJSONObject.getString("dataSourceId")
			).put(
				"type", "reprocess"
			));
	}

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

}