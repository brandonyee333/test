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

	public static final boolean DEVELOPER_MODE_ENABLED = GetterUtil.getBoolean(
		PortletProps.get(PortletPropsKeys.DEVELOPER_MODE_ENABLED));

	public static final boolean DEVELOPER_UPGRADE_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.DEVELOPER_UPGRADE_ENABLED));

	public static final String[] FILE_REPOSITORY_IDS = PortletProps.getArray(
		PortletPropsKeys.FILE_REPOSITORY_IDS);

	public static final boolean QA_INFRASTRUCTURE_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.QA_INFRASTRUCTURE_ENABLED));

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

	public static final boolean REMOTE_REST_SERVICE_API_WEB_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_WEB_ENABLED));

	public static final String REMOTE_REST_SERVICE_API_WEB_ERROR_EMAIL_ADDRESS =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.
					REMOVE_REST_SERVICE_API_WEB_ERROR_EMAIL_ADDRESS));

	public static final String REMOTE_REST_SERVICE_API_WEB_TOKEN =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_WEB_TOKEN));

	public static final String SUPPORT_EMAIL_ADDRESS_FROM = PortletProps.get(
		PortletPropsKeys.SUPPORT_EMAIL_ADDRESS_FROM);

	public static final String WEB_API_TOKEN = PortletProps.get(
		PortletPropsKeys.WEB_API_TOKEN);

	public static final String WEB_DOMAIN_NAME = PortletProps.get(
		PortletPropsKeys.WEB_DOMAIN_NAME);

}