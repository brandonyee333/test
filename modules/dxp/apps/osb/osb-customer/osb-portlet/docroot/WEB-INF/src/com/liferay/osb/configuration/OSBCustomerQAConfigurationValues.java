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