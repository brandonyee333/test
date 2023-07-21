/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.impl.model;

import com.liferay.osb.customer.metrics.rabbitmq.MessageFactory;
import com.liferay.osb.customer.metrics.rabbitmq.constants.RoutingKeys;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.portal.kernel.exception.ModelListenerException;
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
			Message message = messageFactory.createRemoveMessage(
				model.getModelClassName(), model);

			messagePublisher.publish(RoutingKeys.METRICS_REMOVE, message);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void sendUpdateMetricsMessage(BaseModel<T> model) {
		try {
			Message message = messageFactory.createUpdateMessage(
				model.getModelClassName(), model);

			messagePublisher.publish(RoutingKeys.METRICS_UPDATE, message);
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
	protected void setMessagePublisher(MessagePublisher messagePublisher) {
		this.messagePublisher = messagePublisher;
	}

	protected MessageFactory messageFactory;
	protected MessagePublisher messagePublisher;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMetricsModelListener.class);

}