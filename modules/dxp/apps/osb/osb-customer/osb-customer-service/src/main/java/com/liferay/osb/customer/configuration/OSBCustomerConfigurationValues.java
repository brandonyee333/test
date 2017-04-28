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

package com.liferay.osb.customer.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Amos Fong
 */
public class OSBCustomerConfigurationValues {

	public static final String AUTO_DEPLOYER_EMAIL_FROM_ADDRESS =
		GetterUtil.getString(
			OSBCustomerConfigurationUtil.get(
				"auto.deployer.email.from.address"));

	public static final String AUTO_DEPLOYER_EMAIL_TO_ADDRESS =
		GetterUtil.getString(
			OSBCustomerConfigurationUtil.get("auto.deployer.email.to.address"));

	public static final String AUTO_DEPLOYER_EMAIL_TO_NAME =
		GetterUtil.getString(
			OSBCustomerConfigurationUtil.get("auto.deployer.email.to.name"));

	public static final String LIFERAY_DOCS_BUILD_SCRIPT = GetterUtil.getString(
		OSBCustomerConfigurationUtil.get("liferay.docs.build.script"));

}