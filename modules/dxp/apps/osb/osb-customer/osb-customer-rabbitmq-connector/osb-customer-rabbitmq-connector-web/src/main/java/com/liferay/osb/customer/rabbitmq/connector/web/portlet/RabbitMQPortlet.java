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

package com.liferay.osb.customer.rabbitmq.connector.web.portlet;

import com.liferay.osb.customer.rabbitmq.connector.processor.RabbitMQProcessorRegistry;
import com.liferay.osb.customer.rabbitmq.connector.service.ConsumerManagerLocalService;
import com.liferay.osb.customer.rabbitmq.connector.web.internal.constants.RabbitMQPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletClassInvoker;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 * @author Vishal Reddy
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-rabbitmq",
		"com.liferay.portlet.display-category=category.osb",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"javax.portlet.display-name=OSB RabbitMQ Connector",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RabbitMQPortletKeys.RABBIT_MQ,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class RabbitMQPortlet extends MVCPortlet {

	public void activateRabbitMQProcessor(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String rabbitMQProcessorKey = ParamUtil.getString(
			actionRequest, "rabbitMQProcessorKey");

		_rabbitMQProcessorRegistry.activateRabbitMQProcessor(
			rabbitMQProcessorKey);

		/*MessageValuesThreadLocal.setValue(
			ClusterLinkUtil.CLUSTER_FORWARD_MESSAGE, true);

		MethodHandler invokeMethodHandler = new MethodHandler(
			_invokeMethodKey, false, RabbitMQPortletKeys.RABBIT_MQ,
			_addConsumerMethodKey, new Object[] {rabbitMQProcessorKey});

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			invokeMethodHandler, true);

		ClusterExecutorUtil.execute(clusterRequest);*/
	}

	public void consumeMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		_consumerManagerLocalService.consumeMessage();
	}

	public void deactivateRabbitMQProcessor(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String rabbitMQProcessorKey = ParamUtil.getString(
			actionRequest, "rabbitMQProcessorKey");

		_rabbitMQProcessorRegistry.deactivateRabbitMQProcessor(
			rabbitMQProcessorKey);

		/*MessageValuesThreadLocal.setValue(
			ClusterLinkUtil.CLUSTER_FORWARD_MESSAGE, true);

		MethodHandler invokeMethodHandler = new MethodHandler(
			_invokeMethodKey, false, RabbitMQPortletKeys.RABBIT_MQ,
			_deactivateConsumerMethodKey, new Object[] {rabbitMQConsumerKey});

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			invokeMethodHandler, true);

		ClusterExecutorUtil.execute(clusterRequest);*/
	}

	public void restart(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		_consumerManagerLocalService.reconnect();

		_consumerManagerLocalService.resetChannels();
	}

	@Reference(unbind = "-")
	protected void setConsumerManagerLocalService(
		ConsumerManagerLocalService consumerManagerLocalService) {

		_consumerManagerLocalService = consumerManagerLocalService;
	}

	@Reference(unbind = "-")
	protected void setRabbitMQProcessorRegistry(
		RabbitMQProcessorRegistry rabbitMQProcessorRegistry) {

		_rabbitMQProcessorRegistry = rabbitMQProcessorRegistry;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RabbitMQPortlet.class);

	private final MethodKey _activateConsumerMethodKey = new MethodKey(
		ConsumerManagerLocalService.class, "activateConsumer", String.class);
	private ConsumerManagerLocalService _consumerManagerLocalService;
	private final MethodKey _deactivateConsumerMethodKey = new MethodKey(
		ConsumerManagerLocalService.class, "deactivateConsumer", String.class);
	private final MethodKey _invokeMethodKey = new MethodKey(
		PortletClassInvoker.class, "invoke", boolean.class, String.class,
		MethodKey.class, Object[].class);
	private RabbitMQProcessorRegistry _rabbitMQProcessorRegistry;

}