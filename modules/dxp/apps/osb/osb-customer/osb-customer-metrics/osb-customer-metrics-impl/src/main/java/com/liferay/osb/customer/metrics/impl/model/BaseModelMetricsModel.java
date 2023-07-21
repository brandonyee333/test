/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.impl.model;

import com.liferay.osb.customer.metrics.impl.util.MetricsBaseModelUtil;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.rabbitmq.MessageFactory;
import com.liferay.osb.customer.metrics.rabbitmq.constants.RoutingKeys;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.portal.kernel.exception.SystemException;
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

		Message message = messageFactory.createDropMessage(
			modelClass.getName());

		messagePublisher.publish(RoutingKeys.METRICS_DROP, message);
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
			Message message = messageFactory.createUpdateMessage(
				model.getModelClassName(), model);

			messagePublisher.publish(RoutingKeys.METRICS_UPDATE, message);
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