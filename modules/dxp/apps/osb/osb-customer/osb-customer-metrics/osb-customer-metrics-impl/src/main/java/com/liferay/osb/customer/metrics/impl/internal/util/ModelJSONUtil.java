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

package com.liferay.osb.customer.metrics.impl.internal.util;

import com.liferay.osb.customer.metrics.api.constants.MetricsConstants;
import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.api.model.MetricsModelRegistry;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelJSONUtil.class)
public class ModelJSONUtil {

	public String formatSingular(String s) {
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

	public JSONObject getMetricsJSONObject(
			BaseModel<?> model, String routingKey)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (routingKey.equals(MetricsConstants.ACTION_DROP)) {
			jsonObject = _getDropJSONObject(model);
		}
		else if (routingKey.equals(MetricsConstants.ACTION_REMOVE)) {
			jsonObject = _getRemoveJSONObject(model);
		}
		else if (routingKey.equals(MetricsConstants.ACTION_UPDATE)) {
			jsonObject = _getUpdateJSONObject(model);
		}

		return jsonObject;
	}

	private JSONObject _getDropJSONObject(BaseModel<?> model) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

		String modelName = _metricsModelUtil.getModelName(
			model.getModelClassName());

		modelJSONObject.put("name", modelName);

		jsonObject.put("model", modelJSONObject);

		MetricsModel metricsModel = _metricsModelRegistry.getMetricsModel(
			model.getModelClassName());

		if ((metricsModel != null) && metricsModel.hasMapping()) {
			JSONArray mappingTablesJSONArray =
				JSONFactoryUtil.createJSONArray();

			Map<String, String> mappingTables = metricsModel.getMappingTables(
				model);

			for (Map.Entry<String, String> entry : mappingTables.entrySet()) {
				String key = entry.getKey();

				int pos = key.lastIndexOf(CharPool.UNDERLINE);

				String mappingTableName = formatSingular(
					key.substring(pos + 1));

				JSONObject mappingTableJSONObject =
					JSONFactoryUtil.createJSONObject();

				mappingTableJSONObject.put("name", mappingTableName);

				mappingTablesJSONArray.put(mappingTableJSONObject);
			}

			jsonObject.put("mappings", mappingTablesJSONArray);
		}

		return jsonObject;
	}

	private JSONObject _getRemoveJSONObject(BaseModel<?> model)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

		String modelName = _metricsModelUtil.getModelName(
			model.getModelClassName());

		modelJSONObject.put("name", modelName);

		JSONObject attributesJSONObject = JSONFactoryUtil.createJSONObject();

		Map<String, Object> attributes = model.getModelAttributes();

		String modelPrimaryKeyName = _metricsModelUtil.getModelPrimaryKeyName(
			model);

		attributesJSONObject.put(
			modelPrimaryKeyName,
			String.valueOf(attributes.get(modelPrimaryKeyName)));

		modelJSONObject.put("values", attributesJSONObject);

		jsonObject.put("model", modelJSONObject);

		MetricsModel metricsModel = _metricsModelRegistry.getMetricsModel(
			model.getModelClassName());

		if ((metricsModel != null) && metricsModel.hasMapping()) {
			JSONArray mappingTablesJSONArray =
				JSONFactoryUtil.createJSONArray();

			Map<String, String> mappingTables = metricsModel.getMappingTables(
				model);

			for (Map.Entry<String, String> entry : mappingTables.entrySet()) {
				String key = entry.getKey();

				int pos = key.lastIndexOf(CharPool.UNDERLINE);

				String mappingTableName = formatSingular(
					key.substring(pos + 1));

				JSONObject mappingTableJSONObject =
					JSONFactoryUtil.createJSONObject();

				mappingTableJSONObject.put("name", mappingTableName);

				mappingTablesJSONArray.put(mappingTableJSONObject);
			}

			jsonObject.put("mappings", mappingTablesJSONArray);
		}

		return jsonObject;
	}

	private JSONObject _getUpdateJSONObject(BaseModel<?> model)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject modelJSONObject = JSONFactoryUtil.createJSONObject();

		String modelName = _metricsModelUtil.getModelName(
			model.getModelClassName());

		modelJSONObject.put("name", modelName);

		JSONObject attributesJSONObject = JSONFactoryUtil.createJSONObject();

		Map<String, Object> attributes = model.getModelAttributes();

		MetricsModel metricsModel = _metricsModelRegistry.getMetricsModel(
			model.getModelClassName());

		if (metricsModel != null) {
			attributes = metricsModel.transformAttributes(model);
		}

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String lowerCaseKey = StringUtil.lowerCase(entry.getKey());

			if (lowerCaseKey.endsWith(StringUtil.lowerCase("userId")) &&
				(metricsModel != null)) {

				if (entry.getValue() instanceof Long) {
					attributesJSONObject.put(
						entry.getKey(), String.valueOf(entry.getValue()));
				}
				else {
					JSONObject uuidJSONObject =
						JSONFactoryUtil.createJSONObject();

					uuidJSONObject.put(
						"uuid_", String.valueOf(entry.getValue()));

					attributesJSONObject.put(entry.getKey(), uuidJSONObject);
				}
			}
			else {
				attributesJSONObject.put(
					entry.getKey(), String.valueOf(entry.getValue()));
			}
		}

		modelJSONObject.put("values", attributesJSONObject);

		jsonObject.put("model", modelJSONObject);

		if ((metricsModel != null) && metricsModel.hasMapping()) {
			JSONArray mappingTablesJSONArray =
				JSONFactoryUtil.createJSONArray();

			Map<String, String> mappingTables = metricsModel.getMappingTables(
				model);

			String modelPrimaryKeyName =
				_metricsModelUtil.getModelPrimaryKeyName(model);

			for (Map.Entry<String, String> entry : mappingTables.entrySet()) {
				String key = entry.getKey();

				int pos = key.lastIndexOf(CharPool.UNDERLINE);

				String mappingTableName = formatSingular(
					key.substring(pos + 1));

				JSONObject mappingTableJSONObject =
					JSONFactoryUtil.createJSONObject();

				mappingTableJSONObject.put("name", mappingTableName);

				JSONArray mappingTableValuesJSONArray =
					JSONFactoryUtil.createJSONArray();

				String mappingColumnName = entry.getValue();

				List<String> mappingTableValues = metricsModel.getMappingValues(
					model);

				for (String mappingTableValue : mappingTableValues) {
					JSONObject mappingTableValueJSONObject =
						JSONFactoryUtil.createJSONObject();

					mappingTableValueJSONObject.put(
						mappingColumnName, mappingTableValue);

					mappingTableValueJSONObject.put(
						modelPrimaryKeyName,
						String.valueOf(attributes.get(modelPrimaryKeyName)));

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

	@Reference
	private MetricsModelUtil _metricsModelUtil;

}