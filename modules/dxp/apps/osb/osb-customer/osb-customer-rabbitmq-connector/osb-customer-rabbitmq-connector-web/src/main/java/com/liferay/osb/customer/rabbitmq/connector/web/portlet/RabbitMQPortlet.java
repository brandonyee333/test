/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.rabbitmq.portlet;

import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterLinkUtil;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.messaging.proxy.MessageValuesThreadLocal;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.rabbitmq.connection.RabbitMQConnectionManager;
import com.liferay.rabbitmq.service.ConsumerManagerLocalServiceUtil;
import com.liferay.rabbitmq.util.PortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Amos Fong
 */
public class RabbitMQPortlet extends MVCPortlet {

	public void activateConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String rabbitMQConsumerKey = ParamUtil.getString(
			actionRequest, "rabbitMQConsumerKey");

		ConsumerManagerLocalServiceUtil.activateConsumer(rabbitMQConsumerKey);

		MessageValuesThreadLocal.setValue(
			ClusterLinkUtil.CLUSTER_FORWARD_MESSAGE, true);

		MethodHandler invokeMethodHandler = new MethodHandler(
			_invokeMethodKey, false, PortletKeys.RAABIT_MQ,
			_activateConsumerMethodKey, new Object[] {rabbitMQConsumerKey});

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			invokeMethodHandler, true);

		ClusterExecutorUtil.execute(clusterRequest);
	}

	public void consumeMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		ConsumerManagerLocalServiceUtil.consumeMessage();
	}

	public void deactivateConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String rabbitMQConsumerKey = ParamUtil.getString(
			actionRequest, "rabbitMQConsumerKey");

		ConsumerManagerLocalServiceUtil.deactivateConsumer(rabbitMQConsumerKey);

		MessageValuesThreadLocal.setValue(
			ClusterLinkUtil.CLUSTER_FORWARD_MESSAGE, true);

		MethodHandler invokeMethodHandler = new MethodHandler(
			_invokeMethodKey, false, PortletKeys.RAABIT_MQ,
			_deactivateConsumerMethodKey, new Object[] {rabbitMQConsumerKey});

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			invokeMethodHandler, true);

		ClusterExecutorUtil.execute(clusterRequest);
	}

	public void restart(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		RabbitMQConnectionManager connectionManager =
			RabbitMQConnectionManager.getInstance();

		connectionManager.reconnect();

		ConsumerManagerLocalServiceUtil.resetChannels();
	}

	private final MethodKey _activateConsumerMethodKey = new MethodKey(
		ConsumerManagerLocalServiceUtil.class.getName(), "activateConsumer",
		String.class);
	private final MethodKey _deactivateConsumerMethodKey = new MethodKey(
		ConsumerManagerLocalServiceUtil.class.getName(), "deactivateConsumer",
		String.class);
	private MethodKey _invokeMethodKey = new MethodKey(
		"com.liferay.portal.kernel.util.PortletClassInvoker", "invoke",
		boolean.class, String.class, MethodKey.class, Object[].class);

}