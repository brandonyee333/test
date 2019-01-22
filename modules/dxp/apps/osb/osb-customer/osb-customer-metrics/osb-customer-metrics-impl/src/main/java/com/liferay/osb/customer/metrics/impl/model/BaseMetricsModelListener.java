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
import com.liferay.osb.customer.metrics.api.model.MetricsModelListener;
import com.liferay.osb.customer.metrics.impl.internal.util.MessagePublisherUtil;
import com.liferay.osb.customer.metrics.impl.internal.util.ModelJSONUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
public abstract class BaseMetricsModelListener<T extends BaseModel<T>>
	implements MetricsModelListener<T> {

	@Override
	public boolean ignoreAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		return false;
	}

	@Override
	public boolean ignoreUpdate(T model) throws ModelListenerException {
		return false;
	}

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	public void onAfterCreate(T model) throws ModelListenerException {
		if (!ignoreUpdate(model)) {
			sendMetricsMessage(model, MetricsConstants.ACTION_UPDATE);
		}
	}

	@Override
	public void onAfterRemove(T model) throws ModelListenerException {
		if (!ignoreUpdate(model)) {
			sendMetricsMessage(model, MetricsConstants.ACTION_REMOVE);
		}
	}

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(T model) throws ModelListenerException {
		if (!ignoreUpdate(model)) {
			sendMetricsMessage(model, MetricsConstants.ACTION_UPDATE);
		}
	}

	@Override
	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	public void onBeforeCreate(T model) throws ModelListenerException {
	}

	@Override
	public void onBeforeRemove(T model) throws ModelListenerException {
	}

	@Override
	public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	public void onBeforeUpdate(T model) throws ModelListenerException {
	}

	protected void sendMetricsMessage(BaseModel<T> model, String routingKey) {
		try {
			JSONObject jsonObject = modelJSONUtil.getMetricsJSONObject(
				model, routingKey);

			messagePublisherUtil.sendEventNotification(routingKey, jsonObject);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Reference(unbind = "-")
	protected void setMessagePublisherUtil(
		MessagePublisherUtil messagePublisherUtil) {

		this.messagePublisherUtil = messagePublisherUtil;
	}

	@Reference(unbind = "-")
	protected void setModelJSONUtil(ModelJSONUtil modelJSONUtil) {
		this.modelJSONUtil = modelJSONUtil;
	}

	protected MessagePublisherUtil messagePublisherUtil;
	protected ModelJSONUtil modelJSONUtil;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMetricsModelListener.class);

}