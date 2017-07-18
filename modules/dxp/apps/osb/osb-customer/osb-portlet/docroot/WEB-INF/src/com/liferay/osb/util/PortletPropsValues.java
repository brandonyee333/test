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

package com.liferay.osb.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Haote Chou
 */
public class PortletPropsValues {

	public static final long ASSET_LICENSE_TRIAL_MAX_SERVERS = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.ASSET_LICENSE_TRIAL_MAX_SERVERS));

	public static final long ASSET_LICENSE_TRIAL_MAX_USERS = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.ASSET_LICENSE_TRIAL_MAX_USERS));

	public static final boolean DEVELOPER_MODE_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.DEVELOPER_MODE_ENABLED));

	public static final boolean DEVELOPER_PATHS_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.DEVELOPER_PATHS_ENABLED));

	public static final boolean DEVELOPER_UPGRADE_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.DEVELOPER_UPGRADE_ENABLED));

	public static final String ELASTICSEARCH_SEARCH_ENGINE_ID = GetterUtil.getString(PortletProps.get(PortletPropsKeys.ELASTICSEARCH_SEARCH_ENGINE_ID));

	public static final String[] FILE_REPOSITORY_IDS = PortletProps.getArray(PortletPropsKeys.FILE_REPOSITORY_IDS);

	public static final String JIRA_BROWSE_URL = PortletProps.get(PortletPropsKeys.JIRA_BROWSE_URL);

	public static final String KRYTERION_SECURITY_TOKEN = PortletProps.get(PortletPropsKeys.KRYTERION_SECURITY_TOKEN);

	public static final String KRYTERION_URL = PortletProps.get(PortletPropsKeys.KRYTERION_URL);

	public static final String RABBITMQ_MESSAGE_DEAD_LETTER_QUEUE_NAME = PortletProps.get(PortletPropsKeys.RABBITMQ_MESSAGE_DEAD_LETTER_QUEUE_NAME);

	public static final String RABBITMQ_MESSAGE_QUEUE_NAME = PortletProps.get(PortletPropsKeys.RABBITMQ_MESSAGE_QUEUE_NAME);

	public static final boolean REMOTE_JSON_SERVICE_API_LCS_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.REMOTE_JSON_SERVICE_API_LCS_ENABLED));

	public static final String REMOTE_JSON_SERVICE_API_LCS_TOKEN = GetterUtil.getString(PortletProps.get(PortletPropsKeys.REMOTE_JSON_SERVICE_API_LCS_TOKEN));

	public static final boolean REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_HOST = GetterUtil.getString(PortletProps.get(PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_HOST));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_PORT = GetterUtil.getString(PortletProps.get(PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_PORT));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_PROTOCOL = GetterUtil.getString(PortletProps.get(PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_PROTOCOL));

	public static final String REMOTE_REST_SERVICE_API_DOSSIERA_TOKEN = GetterUtil.getString(PortletProps.get(PortletPropsKeys.REMOTE_REST_SERVICE_API_DOSSIERA_TOKEN));

	public static final String SUPPORT_EMAIL_ADDRESS_FROM = PortletProps.get(PortletPropsKeys.SUPPORT_EMAIL_ADDRESS_FROM);

	public static final String SUPPORT_VERSION_2_EMAIL_ADDRESS_FEEDBACK = PortletProps.get(PortletPropsKeys.SUPPORT_VERSION_2_EMAIL_ADDRESS_FEEDBACK);

	public static final String[] TICKET_ATTACHMENT_INDEXING_EXTENSIONS = PortletProps.getArray(PortletPropsKeys.TICKET_ATTACHMENT_INDEXING_EXTENSIONS);

	public static final int TICKET_ATTACHMENT_INDEXING_MAX_SIZE = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.TICKET_ATTACHMENT_INDEXING_MAX_SIZE));

	public static final String[] TICKET_ATTACHMENT_PREVIEW_EXTENSIONS = PortletProps.getArray(PortletPropsKeys.TICKET_ATTACHMENT_PREVIEW_EXTENSIONS);

	public static final String VERIFY_USER_API_TOKEN = GetterUtil.getString(PortletProps.get(PortletPropsKeys.VERIFY_USER_API_TOKEN));

}