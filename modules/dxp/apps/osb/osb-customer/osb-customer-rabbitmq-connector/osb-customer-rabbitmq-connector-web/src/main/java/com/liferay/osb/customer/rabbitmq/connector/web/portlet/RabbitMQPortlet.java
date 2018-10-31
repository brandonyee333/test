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

package com.liferay.osb.customer.rabbitmq.connector.web.portlet;

import com.liferay.osb.customer.rabbitmq.connector.connection.ConnectionManager;
import com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerManager;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouterRegistry;
import com.liferay.osb.customer.rabbitmq.connector.web.internal.constants.RabbitMQPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

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
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RabbitMQPortletKeys.RABBIT_MQ,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class RabbitMQPortlet extends MVCPortlet {

	public void activateConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String queue = ParamUtil.getString(actionRequest, "queue");

		MessageRouter messageRouter = _messageRouterRegistry.getMessageRouter(
			queue);

		_consumerManager.addConsumer(queue, messageRouter);

		/*MessageValuesThreadLocal.setValue(
			ClusterLinkUtil.CLUSTER_FORWARD_MESSAGE, true);

		MethodHandler invokeMethodHandler = new MethodHandler(
			_invokeMethodKey, false, RabbitMQPortletKeys.RABBIT_MQ,
			_addConsumerMethodKey, new Object[] {messageProcessorKey});

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			invokeMethodHandler, true);

		ClusterExecutorUtil.execute(clusterRequest);*/
	}

	public void consumeMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		_consumerManager.consumeMessage();
	}

	public void deactivateConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String queue = ParamUtil.getString(actionRequest, "queue");

		_consumerManager.deleteConsumer(queue);

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

		_connectionManager.reconnect();

		_consumerManager.resetChannels();
	}

	/*private final MethodKey _activateConsumerMethodKey = new MethodKey(
		ConsumerManager.class, "activateConsumer", String.class);*/

	@Reference
	private ConnectionManager _connectionManager;

	@Reference
	private ConsumerManager _consumerManager;

	/*private final MethodKey _deactivateConsumerMethodKey = new MethodKey(
		ConsumerManager.class, "deactivateConsumer", String.class);
	private final MethodKey _invokeMethodKey = new MethodKey(
		PortletClassInvoker.class, "invoke", boolean.class, String.class,
		MethodKey.class, Object[].class);*/

	@Reference
	private MessageRouterRegistry _messageRouterRegistry;

}