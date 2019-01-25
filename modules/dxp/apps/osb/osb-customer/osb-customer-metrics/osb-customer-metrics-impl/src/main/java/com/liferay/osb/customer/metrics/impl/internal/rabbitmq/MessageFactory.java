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

package com.liferay.osb.customer.metrics.impl.internal.rabbitmq;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.api.model.MetricsModelRegistry;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MessageFactory.class)
public class MessageFactory {

	public JSONObject createDropJSONObject(String modelClassName)
		throws Exception {

		MetricsModel metricsModel = _metricsModelRegistry.getMetricsModel(
			modelClassName);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

		modelJSONObject.put("name", metricsModel.getModelName());

		jsonObject.put("model", modelJSONObject);

		if (metricsModel.hasMapping()) {
			JSONArray mappingTablesJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (String mappingTable : metricsModel.getMappingTables()) {
				JSONObject mappingTableJSONObject =
					JSONFactoryUtil.createJSONObject();

				mappingTableJSONObject.put("name", mappingTable);

				mappingTablesJSONArray.put(mappingTableJSONObject);
			}

			jsonObject.put("mappings", mappingTablesJSONArray);
		}

		return jsonObject;
	}

	public JSONObject createRemoveJSONObject(
			String modelClassName, Object model)
		throws Exception {

		MetricsModel metricsModel = _metricsModelRegistry.getMetricsModel(
			modelClassName);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

		modelJSONObject.put("name", metricsModel.getModelName());

		JSONObject attributesJSONObject = JSONFactoryUtil.createJSONObject();

		String modelPrimaryKeyName = metricsModel.getModelPrimaryKeyName();

		Map<String, Object> attributes = metricsModel.getAttributes(model);

		attributesJSONObject.put(
			modelPrimaryKeyName,
			String.valueOf(attributes.get(modelPrimaryKeyName)));

		modelJSONObject.put("values", attributesJSONObject);

		jsonObject.put("model", modelJSONObject);

		if (metricsModel.hasMapping()) {
			JSONArray mappingTablesJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (String mappingTable : metricsModel.getMappingTables()) {
				JSONObject mappingTableJSONObject =
					JSONFactoryUtil.createJSONObject();

				mappingTableJSONObject.put("name", mappingTable);

				mappingTablesJSONArray.put(mappingTableJSONObject);
			}

			jsonObject.put("mappings", mappingTablesJSONArray);
		}

		return jsonObject;
	}

	public JSONObject createUpdateJSONObject(
			String modelClassName, Object model)
		throws Exception {

		MetricsModel metricsModel = _metricsModelRegistry.getMetricsModel(
			modelClassName);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

		modelJSONObject.put("name", metricsModel.getModelName());

		JSONObject attributesJSONObject = JSONFactoryUtil.createJSONObject();

		Map<String, Object> attributes = metricsModel.getAttributes(model);

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String lowerCaseKey = StringUtil.lowerCase(entry.getKey());

			if (lowerCaseKey.endsWith("classnameid")) {
				JSONObject classNameJSONObject =
					JSONFactoryUtil.createJSONObject();

				classNameJSONObject.put("value", entry.getValue());

				attributesJSONObject.put(entry.getKey(), classNameJSONObject);
			}
			else if ((lowerCaseKey.endsWith("classpk") ||
					  lowerCaseKey.endsWith("userid")) &&
					 !(entry.getValue() instanceof Long)) {

				JSONObject uuidJSONObject = JSONFactoryUtil.createJSONObject();

				uuidJSONObject.put("uuid_", String.valueOf(entry.getValue()));

				attributesJSONObject.put(entry.getKey(), uuidJSONObject);
			}
			else {
				attributesJSONObject.put(
					entry.getKey(), String.valueOf(entry.getValue()));
			}
		}

		modelJSONObject.put("values", attributesJSONObject);

		jsonObject.put("model", modelJSONObject);

		if (metricsModel.hasMapping()) {
			JSONArray mappingTablesJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (String mappingTable : metricsModel.getMappingTables()) {
				JSONObject mappingTableJSONObject =
					JSONFactoryUtil.createJSONObject();

				mappingTableJSONObject.put("name", mappingTable);

				JSONArray mappingTableValuesJSONArray =
					JSONFactoryUtil.createJSONArray();

				List<Map<String, String>> mappingTableValues =
					metricsModel.getMappingValues(model, mappingTable);

				for (Map<String, String> mappingTableValue :
						mappingTableValues) {

					JSONObject mappingTableValueJSONObject =
						JSONFactoryUtil.createJSONObject();

					for (Map.Entry<String, String> mappingTableValueEntry :
							mappingTableValue.entrySet()) {

						mappingTableValueJSONObject.put(
							mappingTableValueEntry.getKey(),
							mappingTableValueEntry.getValue());
					}

					mappingTableValuesJSONArray.put(
						mappingTableValueJSONObject);
				}

				mappingTableJSONObject.put(
					"values", mappingTableValuesJSONArray);

				mappingTablesJSONArray.put(mappingTableJSONObject);
			}

			jsonObject.put("mappings", mappingTablesJSONArray);
		}

		return jsonObject;
	}

	protected String formatSingular(String s) {
		if (Validator.isNull(s)) {
			return s;
		}

		if (s.endsWith("ses")) {
			s = s.substring(0, s.length() - 3) + "s";
		}
		else if (s.endsWith("ies")) {
			s = s.substring(0, s.length() - 3) + "y";
		}
		else {
			s = s.substring(0, s.length() - 1);
		}

		return s;
	}

	@Reference
	private MetricsModelRegistry _metricsModelRegistry;

}