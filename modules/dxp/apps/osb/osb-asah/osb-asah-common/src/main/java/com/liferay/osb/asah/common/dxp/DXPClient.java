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

package com.liferay.osb.asah.common.dxp;

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.ExperimentStatus;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class DXPClient extends BaseDXPClient {

	public JSONObject deleteDXPExperiment(
		String dataSourceId, String experimentId) {

		String path = String.format(
			"/o/segments-asah/v1.0/experiments/%s", experimentId);

		return deleteJSONObject(
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId), path);
	}

	public JSONObject runDXPExperiment(
		Double confidenceLevel, String dataSourceId,
		List<DXPVariantSettings> dxpVariantSettingsList, String experimentId) {

		String path = String.format(
			"/o/segments-asah/v1.0/experiments/%s/run", experimentId);

		JSONArray experimentVariantsJSONArray = new JSONArray();

		Stream<DXPVariantSettings> stream = dxpVariantSettingsList.stream();

		stream.map(
			dxpVariantSettings -> JSONUtil.put(
				"id", dxpVariantSettings.getDXPVariantId()
			).put(
				"trafficSplit", dxpVariantSettings.getTrafficSplit()
			)
		).forEach(
			experimentVariantsJSONArray::put
		);

		return postJSONObject(
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId), path,
			JSONUtil.put(
				"confidenceLevel", confidenceLevel
			).put(
				"experimentVariants", experimentVariantsJSONArray
			));
	}

	public JSONObject updateDXPExperimentStatus(
		String dataSourceId, String experimentId,
		ExperimentStatus experimentStatus, String dxpVariantId) {

		String path = String.format(
			"/o/segments-asah/v1.0/experiments/%s/status", experimentId);

		JSONObject bodyJSONObject = JSONUtil.put("status", experimentStatus);

		if (StringUtils.isNotEmpty(dxpVariantId)) {

			// This is the winner variant when the experiment is finished and
			// the published one when the experiment is completed

			bodyJSONObject.put("winnerVariantId", dxpVariantId);
		}

		return postJSONObject(
			_faroInfoDataSourceDog.getDataSourceJSONObject(dataSourceId), path,
			bodyJSONObject);
	}

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}