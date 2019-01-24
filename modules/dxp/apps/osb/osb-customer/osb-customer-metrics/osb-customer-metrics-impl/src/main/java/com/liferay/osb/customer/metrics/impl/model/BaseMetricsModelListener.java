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
import com.liferay.osb.customer.metrics.impl.internal.rabbitmq.MessageFactory;
import com.liferay.osb.customer.metrics.impl.internal.rabbitmq.MessagePublisherUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
public abstract class BaseMetricsModelListener<T extends BaseModel<T>>
	extends BaseModelListener<T> {

	@Override
	public void onAfterCreate(T model) throws ModelListenerException {
		if (!ignoreUpdate(model)) {
			sendUpdateMetricsMessage(model);
		}
	}

	@Override
	public void onAfterRemove(T model) throws ModelListenerException {
		if (!ignoreUpdate(model)) {
			sendRemoveMetricsMessage(model);
		}
	}

	@Override
	public void onAfterUpdate(T model) throws ModelListenerException {
		if (!ignoreUpdate(model)) {
			sendUpdateMetricsMessage(model);
		}
	}

	protected boolean ignoreAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		return false;
	}

	protected boolean ignoreUpdate(T model) throws ModelListenerException {
		return false;
	}

	protected void sendRemoveMetricsMessage(BaseModel<T> model) {
		try {
			JSONObject jsonObject = messageFactory.createRemoveJSONObject(
				model);

			messagePublisherUtil.sendMessage(
				MetricsConstants.ACTION_REMOVE, jsonObject);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void sendUpdateMetricsMessage(BaseModel<T> model) {
		try {
			JSONObject jsonObject = messageFactory.createUpdateJSONObject(
				model);

			messagePublisherUtil.sendMessage(
				MetricsConstants.ACTION_UPDATE, jsonObject);
		}
		catch (Exception e) {
			_log.error(e, e);
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

	protected MessageFactory messageFactory;
	protected MessagePublisherUtil messagePublisherUtil;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMetricsModelListener.class);

}