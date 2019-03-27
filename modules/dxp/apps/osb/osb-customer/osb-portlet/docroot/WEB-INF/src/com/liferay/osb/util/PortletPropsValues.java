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

package com.liferay.osb.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Haote Chou
 */
public class PortletPropsValues {

	public static final String AUTOMATIC_PROVISIONING_ERROR_EMAIL_ADDRESS =
		PortletProps.get(
			PortletPropsKeys.AUTOMATIC_PROVISIONING_ERROR_EMAIL_ADDRESS);

	public static final String CORP_PROJECT_LINK = PortletProps.get(
		PortletPropsKeys.CORP_PROJECT_LINK);

	public static final boolean DEVELOPER_UPGRADE_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.DEVELOPER_UPGRADE_ENABLED));

	public static final String[]
		PROVISIONING_OPPORTUNITY_PRODUCT_FAMILY_TOKENS = PortletProps.getArray(
			PortletPropsKeys.PROVISIONING_OPPORTUNITY_PRODUCT_FAMILY_TOKENS);

	public static final boolean REMOTE_JSON_SERVICE_API_LCS_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.REMOTE_JSON_SERVICE_API_LCS_ENABLED));

	public static final String REMOTE_JSON_SERVICE_API_LCS_TOKEN =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_JSON_SERVICE_API_LCS_TOKEN));

	public static final boolean REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_HOST =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_HOST));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_PORT =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_PORT));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_PROTOCOL =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_PROTOCOL));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_TOKEN =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_TOKEN));

	public static final String REMOTE_REST_SERVICE_API_WEB_ERROR_EMAIL_ADDRESS =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.
					REMOTE_REST_SERVICE_API_WEB_ERROR_EMAIL_ADDRESS));

	public static final String REMOTE_REST_SERVICE_API_WEB_TOKEN =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_WEB_TOKEN));

	public static final String SUPPORT_EMAIL_ADDRESS_FROM = PortletProps.get(
		PortletPropsKeys.SUPPORT_EMAIL_ADDRESS_FROM);

	public static final boolean SYNCHRONIZE_USERS_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.SYNCHRONIZE_USERS_ENABLED));

}