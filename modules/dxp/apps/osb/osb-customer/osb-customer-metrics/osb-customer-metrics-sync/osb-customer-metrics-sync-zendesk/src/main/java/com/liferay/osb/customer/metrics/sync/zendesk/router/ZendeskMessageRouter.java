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

package com.liferay.osb.customer.metrics.sync.zendesk.router;

import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskArticleTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskOrganizationTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskTicketEventTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskTicketMetricsTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskTicketTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskUserTransformer;
import com.liferay.osb.customer.rabbitmq.connector.router.BaseMessageRouter;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "queue=is_zendesk_metrics_queue",
	service = MessageRouter.class
)
public class ZendeskMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setZendeskArticleTransformer(
		ZendeskArticleTransformer zendeskArticleTransformer,
		Map<String, Object> properties) {

		addRoute(zendeskArticleTransformer, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskOrganizationTransformer(
		ZendeskOrganizationTransformer zendeskOrganizationTransformer,
		Map<String, Object> properties) {

		addRoute(zendeskOrganizationTransformer, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskTicketEventTransformer(
		ZendeskTicketEventTransformer zendeskTicketEventTransformer,
		Map<String, Object> properties) {

		addRoute(zendeskTicketEventTransformer, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskTicketMetricsTransformer(
		ZendeskTicketMetricsTransformer zendeskTicketMetricsTransformer,
		Map<String, Object> properties) {

		addRoute(zendeskTicketMetricsTransformer, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskTicketTransformer(
		ZendeskTicketTransformer zendeskTicketTransformer,
		Map<String, Object> properties) {

		addRoute(zendeskTicketTransformer, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskUserTransformer(
		ZendeskUserTransformer zendeskUserTransformer,
		Map<String, Object> properties) {

		addRoute(zendeskUserTransformer, properties);
	}

}