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

	public static final String DOSSIERA_ACCOUNT_LINK = PortletProps.get(
		PortletPropsKeys.DOSSIERA_ACCOUNT_LINK);

	public static final boolean REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_TOKEN =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_TOKEN));

	public static final String
		REMOTE_REST_SERVICE_API_KORONEIKI_ERROR_EMAIL_ADDRESS =
			GetterUtil.getString(
				PortletProps.get(
					PortletPropsKeys.
						REMOTE_REST_SERVICE_API_KORONEIKI_ERROR_EMAIL_ADDRESS));

	public static final String REMOTE_REST_SERVICE_API_KORONEIKI_TOKEN =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_KORONEIKI_TOKEN));

	public static final String REMOTE_REST_SERVICE_API_WEB_ERROR_EMAIL_ADDRESS =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.
					REMOTE_REST_SERVICE_API_WEB_ERROR_EMAIL_ADDRESS));

	public static final String REMOTE_REST_SERVICE_API_WEB_TOKEN =
		GetterUtil.getString(
			PortletProps.get(
				PortletPropsKeys.REMOTE_REST_SERVICE_API_WEB_TOKEN));

}