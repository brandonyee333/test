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

package com.liferay.osb.customer.metrics.impl.model;

import com.liferay.osb.customer.metrics.api.constants.MetricsConstants;
import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.impl.internal.util.MessagePublisherUtil;
import com.liferay.osb.customer.metrics.impl.internal.util.MetricsModelUtil;
import com.liferay.osb.customer.metrics.impl.internal.util.ModelJSONUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.StringUtil;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
public abstract class BaseMetricsModel<T extends BaseModel<T>>
	implements MetricsModel<T> {

	public void deleteAll(String modelClassName) throws Exception {
		BaseModel<?> model = metricsModelUtil.getBaseModel(modelClassName);

		JSONObject jsonObject = modelJSONUtil.getMetricsJSONObject(
			model, MetricsConstants.ACTION_DROP);

		messagePublisherUtil.sendEventNotification(
			MetricsConstants.ACTION_DROP, jsonObject);

		List<BaseModel<?>> models = metricsModelUtil.getModelList(model);

		for (BaseModel<?> baseModel : models) {
			jsonObject = modelJSONUtil.getMetricsJSONObject(
				baseModel, MetricsConstants.ACTION_UPDATE);

			messagePublisherUtil.sendEventNotification(
				MetricsConstants.ACTION_UPDATE, jsonObject);
		}
	}

	public Map<String, String> getMappingTables(BaseModel<T> model)
		throws Exception {

		List<String> mappingTableNames = new ArrayList<>();

		Class<T> modelImplClass = metricsModelUtil.getModelImplClass(model);

		for (Field field : modelImplClass.getFields()) {
			String name = field.getName();

			if (name.startsWith("MAPPING_TABLE") && name.endsWith("NAME")) {
				mappingTableNames.add((String)field.get(null));
			}
		}

		for (String mappingTableName : mappingTableNames) {
			Field field = modelImplClass.getField(
				"MAPPING_TABLE_" + StringUtil.toUpperCase(mappingTableName) +
					"_COLUMNS");

			Object[][] columns = (Object[][])field.get(null);

			for (Object[] column : columns) {
				String columnName = (String)column[0];

				if (!columnName.equals(
						metricsModelUtil.getModelPrimaryKeyName(model)) &&
					!columnName.equals("companyId")) {

					_mappingTables.put(mappingTableName, columnName);
				}
			}
		}

		return _mappingTables;
	}

	public List<String> getMappingValues(BaseModel<T> model) {
		return _mappingValues;
	}

	public boolean hasMapping() {
		return false;
	}

	public void resyncAll(String modelClassName) throws Exception {
		BaseModel<?> model = metricsModelUtil.getBaseModel(modelClassName);

		List<BaseModel<?>> models = metricsModelUtil.getModelList(model);

		for (BaseModel<?> baseModel : models) {
			JSONObject jsonObject = modelJSONUtil.getMetricsJSONObject(
				baseModel, MetricsConstants.ACTION_UPDATE);

			messagePublisherUtil.sendEventNotification(
				MetricsConstants.ACTION_UPDATE, jsonObject);
		}
	}

	public Map<String, Object> transformAttributes(BaseModel<T> model) {
		return model.getModelAttributes();
	}

	@Reference(unbind = "-")
	protected void setMessagePublisherUtil(
		MessagePublisherUtil messagePublisherUtil) {

		this.messagePublisherUtil = messagePublisherUtil;
	}

	@Reference(unbind = "-")
	protected void setMetricsModelUtil(MetricsModelUtil metricsModelUtil) {
		this.metricsModelUtil = metricsModelUtil;
	}

	@Reference(unbind = "-")
	protected void setModelJSONUtil(ModelJSONUtil modelJSONUtil) {
		this.modelJSONUtil = modelJSONUtil;
	}

	protected MessagePublisherUtil messagePublisherUtil;
	protected MetricsModelUtil metricsModelUtil;
	protected ModelJSONUtil modelJSONUtil;

	private final Map<String, String> _mappingTables = new HashMap<>();
	private final List<String> _mappingValues = new ArrayList<>();

}