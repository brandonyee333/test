/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.server.distributed.messaging.publisher;

import com.liferay.osb.customer.metrics.server.distributed.messaging.publisher.broker.LegacyEntityMessageBroker;
import com.liferay.osb.customer.metrics.server.distributed.messaging.publisher.broker.LegacyMetricsMessageBroker;
import com.liferay.osb.customer.metrics.server.distributed.messaging.publisher.broker.LegacyZendeskServiceMessageBroker;
import com.liferay.osb.distributed.messaging.publishing.BaseMessagePublisher;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = MessagePublisher.class)
public class MetricsServerMessagePublisher extends BaseMessagePublisher {

	@Reference(unbind = "-")
	protected void setLegacyEntityMessageBroker(
		LegacyEntityMessageBroker legacyEntityMessageBroker,
		Map<String, Object> properties) {

		addMessageBroker(legacyEntityMessageBroker, properties);
	}

	@Reference(unbind = "-")
	protected void setLegacyMetricsMessageBroker(
		LegacyMetricsMessageBroker legacyMetricsMessageBroker,
		Map<String, Object> properties) {

		addMessageBroker(legacyMetricsMessageBroker, properties);
	}

	@Reference(unbind = "-")
	protected void setLegacyZendeskServiceMessageBroker(
		LegacyZendeskServiceMessageBroker legacyZendeskServiceMessageBroker,
		Map<String, Object> properties) {

		addMessageBroker(legacyZendeskServiceMessageBroker, properties);
	}

}