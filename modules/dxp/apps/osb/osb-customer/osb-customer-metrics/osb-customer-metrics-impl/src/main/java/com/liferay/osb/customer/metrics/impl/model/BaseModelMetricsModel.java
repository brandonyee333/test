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

import com.liferay.osb.customer.metrics.impl.internal.util.MetricsBaseModelUtil;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.rabbitmq.MessageFactory;
import com.liferay.osb.customer.metrics.rabbitmq.MessagePublisher;
import com.liferay.osb.customer.metrics.rabbitmq.constants.RoutingKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.CharPool;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
public abstract class BaseModelMetricsModel<T extends BaseModel<T>>
	implements MetricsModel<T> {

	public boolean allowDeleteAll() {
		return true;
	}

	public void deleteAll() throws Exception {
		Class<T> modelClass = getModelClass();

		JSONObject jsonObject = messageFactory.createDropJSONObject(
			modelClass.getName());

		messagePublisher.sendMessage(RoutingKeys.METRICS_DROP, jsonObject);
	}

	public Map<String, Object> getAttributes(T model) {
		return model.getModelAttributes();
	}

	public String[] getMappingTables() {
		throw new UnsupportedOperationException();
	}

	public List<Map<String, Object>> getMappingValues(T model) {
		throw new UnsupportedOperationException();
	}

	public abstract Class<T> getModelClass();

	public String getModelName() {
		Class<T> clazz = getModelClass();

		String modelClassName = clazz.getName();

		int pos = modelClassName.lastIndexOf(CharPool.PERIOD);

		return modelClassName.substring(pos + 1);
	}

	public String getModelPrimaryKeyName() {
		try {
			if (modelPrimaryKeyName == null) {
				modelPrimaryKeyName =
					metricsBaseModelUtil.getModelPrimaryKeyName(this);
			}
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		return modelPrimaryKeyName;
	}

	public boolean hasMapping() {
		return false;
	}

	public void resyncAll() throws Exception {
		List<BaseModel<?>> models = metricsBaseModelUtil.getModelList(this);

		for (BaseModel<?> model : models) {
			JSONObject jsonObject = messageFactory.createUpdateJSONObject(
				model.getModelClassName(), model);

			messagePublisher.sendMessage(
				RoutingKeys.METRICS_UPDATE, jsonObject);
		}
	}

	@Reference(unbind = "-")
	protected void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}

	@Reference(unbind = "-")
	protected void setMessagePublisher(MessagePublisher messagePublisher) {
		this.messagePublisher = messagePublisher;
	}

	@Reference(unbind = "-")
	protected void setMetricsBaseModelUtil(
		MetricsBaseModelUtil metricsBaseModelUtil) {

		this.metricsBaseModelUtil = metricsBaseModelUtil;
	}

	protected MessageFactory messageFactory;
	protected MessagePublisher messagePublisher;
	protected MetricsBaseModelUtil metricsBaseModelUtil;
	protected String modelPrimaryKeyName;

}