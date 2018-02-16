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

package com.liferay.osb.configuration;

/**
 * @author Jenny Chen
 */
public class OSBCustomerQAConfigurationValues {

	public static final String[] OSB_QA_ACCOUNT_CUSTOMER_EMAIL_ADDRESSES =
		OSBCustomerQAConfigurationUtil.getArray(
			OSBCustomerQAConfigurationKeys.
				OSB_QA_ACCOUNT_CUSTOMER_EMAIL_ADDRESSES);

	public static final String[] OSB_QA_ACCOUNT_ENTRY_CODES =
		OSBCustomerQAConfigurationUtil.getArray(
			OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENTRY_CODES);

	public static final String[] OSB_QA_ACCOUNT_ENVIRONMENT_NAMES =
		OSBCustomerQAConfigurationUtil.getArray(
			OSBCustomerQAConfigurationKeys.OSB_QA_ACCOUNT_ENVIRONMENT_NAMES);

	public static final String[] OSB_QA_ACCOUNT_WORKER_EMAIL_ADDRESSES =
		OSBCustomerQAConfigurationUtil.getArray(
			OSBCustomerQAConfigurationKeys.
				OSB_QA_ACCOUNT_WORKER_EMAIL_ADDRESSES);

	public static final String[] OSB_QA_PARTNER_ENTRY_CODES =
		OSBCustomerQAConfigurationUtil.getArray(
			OSBCustomerQAConfigurationKeys.OSB_QA_PARTNER_ENTRY_CODES);

	public static final String[] OSB_QA_PARTNER_WORKER_EMAIL_ADDRESSES =
		OSBCustomerQAConfigurationUtil.getArray(
			OSBCustomerQAConfigurationKeys.
				OSB_QA_PARTNER_WORKER_EMAIL_ADDRESSES);

	public static final String[] OSB_QA_SUPPORT_WORKER_EMAIL_ADDRESSES =
		OSBCustomerQAConfigurationUtil.getArray(
			OSBCustomerQAConfigurationKeys.
				OSB_QA_SUPPORT_WORKER_EMAIL_ADDRESSES);

	public static final String[] OSB_QA_USER_EMAIL_ADDRESSES =
		OSBCustomerQAConfigurationUtil.getArray(
			OSBCustomerQAConfigurationKeys.OSB_QA_USER_EMAIL_ADDRESSES);

}