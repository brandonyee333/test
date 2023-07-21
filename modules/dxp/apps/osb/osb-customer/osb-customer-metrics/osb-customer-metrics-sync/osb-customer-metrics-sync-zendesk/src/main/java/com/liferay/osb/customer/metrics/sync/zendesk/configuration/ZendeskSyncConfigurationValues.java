/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.zendesk.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class ZendeskSyncConfigurationValues {

	public static final String METRICS_RABBITMQ_EXCHANGE_NAME =
		GetterUtil.getString(
			ZendeskSyncConfigurationUtil.get("metrics.rabbitmq.exchange.name"));

	public static final String ZENDESK_DATABASE_SCHEMA_NAME =
		GetterUtil.getString(
			ZendeskSyncConfigurationUtil.get("zendesk.database.schema.name"));

	public static final String ZENDESK_SERVICE_RABBITMQ_EXCHANGE_NAME =
		GetterUtil.getString(
			ZendeskSyncConfigurationUtil.get(
				"zendesk.service.rabbitmq.exchange.name"));

}