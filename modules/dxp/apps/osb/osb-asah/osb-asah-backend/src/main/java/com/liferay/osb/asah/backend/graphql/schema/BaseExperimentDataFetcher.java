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

package com.liferay.osb.asah.backend.graphql.schema;

import com.liferay.osb.asah.backend.model.ExperimentSettings;
import com.liferay.osb.asah.common.model.DXPVariantSettings;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Marcellus Tavares
 */
public class BaseExperimentDataFetcher {

	protected ExperimentSettings createExperimentSettings(
		Map<String, Object> experimentSettingsMap) {

		if (experimentSettingsMap == null) {
			return null;
		}

		ExperimentSettings experimentSettings = new ExperimentSettings();

		experimentSettings.setConfidenceLevel(
			(Double)experimentSettingsMap.get("confidenceLevel"));
		experimentSettings.setDXPVariantsSettings(
			_createDXPVariantSettings(
				(List<Map<String, Object>>)experimentSettingsMap.get(
					"dxpVariantsSettings")));

		return experimentSettings;
	}

	private List<DXPVariantSettings> _createDXPVariantSettings(
		List<Map<String, Object>> dxpVariantsSettingsMap) {

		Stream<Map<String, Object>> stream = dxpVariantsSettingsMap.stream();

		return stream.map(
			dxpVariantSettingsMap -> new DXPVariantSettings(
				(boolean)dxpVariantSettingsMap.get("control"),
				(String)dxpVariantSettingsMap.get("dxpVariantId"),
				(Double)dxpVariantSettingsMap.get("trafficSplit"))
		).collect(
			Collectors.toList()
		);
	}

}