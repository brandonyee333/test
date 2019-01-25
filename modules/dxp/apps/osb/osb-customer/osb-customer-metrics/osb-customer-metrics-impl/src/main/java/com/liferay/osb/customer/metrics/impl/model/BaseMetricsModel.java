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
import com.liferay.osb.customer.metrics.impl.internal.rabbitmq.MessageFactory;
import com.liferay.osb.customer.metrics.impl.internal.rabbitmq.MessagePublisherUtil;
import com.liferay.osb.customer.metrics.impl.internal.util.MetricsModelUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
public abstract class BaseMetricsModel<T extends BaseModel<T>>
	implements MetricsModel<T> {

	public void deleteAll() throws Exception {
		Class<T> modelClass = getModelClass();

		JSONObject jsonObject = messageFactory.createDropJSONObject(
			modelClass.getName());

		messagePublisherUtil.sendMessage(
			MetricsConstants.ACTION_DROP, jsonObject);
	}

	public Map<String, Object> getAttributes(BaseModel<T> model) {
		return model.getModelAttributes();
	}

	public String[] getMappingTables() {
		throw new UnsupportedOperationException();
	}

	public List<Map<String, String>> getMappingValues(
		T model, String mappingTable) {

		throw new UnsupportedOperationException();
	}

	public abstract Class<T> getModelClass();

	public boolean hasMapping() {
		return false;
	}

	public void resyncAll() throws Exception {
		List<BaseModel<?>> models = metricsModelUtil.getModelList(this);

		for (BaseModel<?> model : models) {
			JSONObject jsonObject = messageFactory.createUpdateJSONObject(
				model);

			messagePublisherUtil.sendMessage(
				MetricsConstants.ACTION_UPDATE, jsonObject);
		}
	}

	@Reference(unbind = "-")
	protected void setMessageFactory(MessageFactory messageFactory) {
		this.messageFactory = messageFactory;
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

	protected MessageFactory messageFactory;
	protected MessagePublisherUtil messagePublisherUtil;
	protected MetricsModelUtil metricsModelUtil;

}