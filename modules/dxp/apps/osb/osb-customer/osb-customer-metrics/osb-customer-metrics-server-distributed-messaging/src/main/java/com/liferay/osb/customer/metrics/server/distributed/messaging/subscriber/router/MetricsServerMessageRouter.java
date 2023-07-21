/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.server.distributed.messaging.subscriber.router;

import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskArticleTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskGroupTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskOrganizationTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskTicketEventTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskTicketMetricsTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskTicketTransformer;
import com.liferay.osb.customer.metrics.sync.zendesk.transformer.ZendeskUserTransformer;
import com.liferay.osb.customer.zendesk.message.subscriber.ZendeskWebServiceMessageSubscriber;
import com.liferay.osb.distributed.messaging.subscribing.router.BaseMessageRouter;
import com.liferay.osb.distributed.messaging.subscribing.router.MessageRouter;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MessageRouter.class)
public class MetricsServerMessageRouter extends BaseMessageRouter {

	@Reference(unbind = "-")
	protected void setZendeskArticleTransformer(
		ZendeskArticleTransformer zendeskArticleTransformer,
		Map<String, Object> properties) {

		addRoute(zendeskArticleTransformer, properties);
	}

	@Reference(unbind = "-")
	protected void setZendeskGroupTransformer(
		ZendeskGroupTransformer zendeskGroupTransformer,
		Map<String, Object> properties) {

		addRoute(zendeskGroupTransformer, properties);
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

	@Reference(unbind = "-")
	protected void setZendeskWebServiceMessageSubscriber(
		ZendeskWebServiceMessageSubscriber zendeskWebServiceMessageSubscriber,
		Map<String, Object> properties) {

		addRoute(zendeskWebServiceMessageSubscriber, properties);
	}

}