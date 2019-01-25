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
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.User;
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
			Object obj = entry.getValue();

			if (obj instanceof ClassName) {
				ClassName className = (ClassName)obj;

				JSONObject classNameJSONObject =
					JSONFactoryUtil.createJSONObject();

				classNameJSONObject.put("value", className.getValue());

				attributesJSONObject.put(entry.getKey(), classNameJSONObject);
			}
			else if (obj instanceof User) {
				User user = (User)obj;

				JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

				userJSONObject.put("uuid_", user.getUuid());

				attributesJSONObject.put(entry.getKey(), userJSONObject);
			}
			else {
				attributesJSONObject.put(entry.getKey(), String.valueOf(obj));
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

	@Reference
	private MetricsModelRegistry _metricsModelRegistry;

}