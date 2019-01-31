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

package com.liferay.osb.customer.metrics.zendesk.sync.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class ZendeskSyncConfigurationValues {

	public static final String ZENDESK_SERVICE_RABBITMQ_EXCHANGE_NAME =
		GetterUtil.getString(
			ZendeskSyncConfigurationUtil.get(
				"zendesk.service.rabbitmq.exchange.name"));

}