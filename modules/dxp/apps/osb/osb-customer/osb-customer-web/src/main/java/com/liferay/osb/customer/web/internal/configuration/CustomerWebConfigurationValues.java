/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.web.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class CustomerWebConfigurationValues {

	public static final String REMOTE_REST_SERVICE_API_DXP_CLOUD_HOST =
		GetterUtil.getString(
			CustomerWebConfigurationUtil.get(
				"remote.rest.service.api.dxp.cloud.host"));

	public static final String REMOTE_REST_SERVICE_API_DXP_CLOUD_PAGE_ID =
		GetterUtil.getString(
			CustomerWebConfigurationUtil.get(
				"remote.rest.service.api.dxp.cloud.page.id"));

	public static final int REMOTE_REST_SERVICE_API_DXP_CLOUD_PORT =
		GetterUtil.getInteger(
			CustomerWebConfigurationUtil.get(
				"remote.rest.service.api.dxp.cloud.port"));

	public static final String REMOTE_REST_SERVICE_API_DXP_CLOUD_PROTOCOL =
		GetterUtil.getString(
			CustomerWebConfigurationUtil.get(
				"remote.rest.service.api.dxp.cloud.protocol"));

	public static final String REMOTE_REST_SERVICE_API_DXP_CLOUD_TOKEN =
		GetterUtil.getString(
			CustomerWebConfigurationUtil.get(
				"remote.rest.service.api.dxp.cloud.token"));

}