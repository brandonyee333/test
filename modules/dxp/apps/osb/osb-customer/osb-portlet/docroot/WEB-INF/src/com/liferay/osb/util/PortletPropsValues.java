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

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Haote Chou
 */
public class PortletPropsValues {

	public static final int APP_ENTRY_ICON_MAX_HEIGHT = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.APP_ENTRY_ICON_MAX_HEIGHT));

	public static final int APP_ENTRY_ICON_MAX_WIDTH = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.APP_ENTRY_ICON_MAX_WIDTH));

	public static final long ASSET_ATTACHMENT_UNASSIGNED_MAX_AGE = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.ASSET_ATTACHMENT_UNASSIGNED_MAX_AGE));

	public static final long ASSET_LICENSE_TRIAL_MAX_SERVERS = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.ASSET_LICENSE_TRIAL_MAX_SERVERS));

	public static final long ASSET_LICENSE_TRIAL_MAX_USERS = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.ASSET_LICENSE_TRIAL_MAX_USERS));

	public static final int ASSET_RECOMMENDATION_SET_MAX_ASSET_AUDITS = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.ASSET_RECOMMENDATION_SET_MAX_ASSET_AUDITS));

	public static final boolean CONTRACT_ENTRY_LOCALIZED_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.CONTRACT_ENTRY_LOCALIZED_ENABLED));

	public static final String[] CORP_ENTRY_LOGO_EXTENSIONS = PortletProps.getArray(PortletPropsKeys.CORP_ENTRY_LOGO_EXTENSIONS);

	public static final int CORP_ENTRY_LOGO_MAX_HEIGHT = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.CORP_ENTRY_LOGO_MAX_HEIGHT));

	public static final int CORP_ENTRY_LOGO_MAX_WIDTH = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.CORP_ENTRY_LOGO_MAX_WIDTH));

	public static final String[] CORP_ENTRY_MERGE_HELPER_CLASS_NAMES = PortletProps.getArray(PortletPropsKeys.CORP_ENTRY_MERGE_HELPER_CLASS_NAMES);

	public static final long[] CORP_ENTRY_ROLE_IDS_DEFAULT = StringUtil.split(PortletProps.get(PortletPropsKeys.CORP_ENTRY_ROLE_IDS_DEFAULT), 0L);

	public static final String[] CORP_GROUP_LOGO_EXTENSIONS = PortletProps.getArray(PortletPropsKeys.CORP_GROUP_LOGO_EXTENSIONS);

	public static final int CORP_GROUP_LOGO_MAX_HEIGHT = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.CORP_GROUP_LOGO_MAX_HEIGHT));

	public static final int CORP_GROUP_LOGO_MAX_WIDTH = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.CORP_GROUP_LOGO_MAX_WIDTH));

	public static final String[] DEVELOPER_ENTRY_LOGO_EXTENSIONS = PortletProps.getArray(PortletPropsKeys.DEVELOPER_ENTRY_LOGO_EXTENSIONS);

	public static final boolean DEVELOPER_MODE_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.DEVELOPER_MODE_ENABLED));

	public static final boolean DEVELOPER_PATHS_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.DEVELOPER_PATHS_ENABLED));

	public static final boolean DEVELOPER_UPGRADE_ENABLED = GetterUtil.getBoolean(PortletProps.get(PortletPropsKeys.DEVELOPER_UPGRADE_ENABLED));

	public static final String ELASTICSEARCH_SEARCH_ENGINE_ID = GetterUtil.getString(PortletProps.get(PortletPropsKeys.ELASTICSEARCH_SEARCH_ENGINE_ID));

	public static final String[] FILE_REPOSITORY_IDS = PortletProps.getArray(PortletPropsKeys.FILE_REPOSITORY_IDS);

	public static final String JIRA_BROWSE_URL = PortletProps.get(PortletPropsKeys.JIRA_BROWSE_URL);

	public static final String KRYTERION_SECURITY_TOKEN = PortletProps.get(PortletPropsKeys.KRYTERION_SECURITY_TOKEN);

	public static final String KRYTERION_URL = PortletProps.get(PortletPropsKeys.KRYTERION_URL);

	public static final String MARKETING_EVENTS_PORTLET_HEADER_CONTENT = PortletProps.get(PortletPropsKeys.MARKETING_EVENTS_PORTLET_HEADER_CONTENT);

	public static final long[] MARKETPLACE_ADD_RESOURCES_IMPORTER_ASSET_CATEGORY_IDS = StringUtil.split(PortletProps.get(PortletPropsKeys.MARKETPLACE_ADD_RESOURCES_IMPORTER_ASSET_CATEGORY_IDS), 0L);

	public static final String MARKETPLACE_APP_AUTO_QA_LOGIN_PASSWORD = PortletProps.get(PortletPropsKeys.MARKETPLACE_APP_AUTO_QA_LOGIN_PASSWORD);

	public static final String MARKETPLACE_APP_AUTO_QA_LOGIN_USERNAME = PortletProps.get(PortletPropsKeys.MARKETPLACE_APP_AUTO_QA_LOGIN_USERNAME);

	public static final String MARKETPLACE_APP_AUTO_QA_TOKEN = PortletProps.get(PortletPropsKeys.MARKETPLACE_APP_AUTO_QA_TOKEN);

	public static final String MARKETPLACE_APP_AUTO_QA_URL = PortletProps.get(PortletPropsKeys.MARKETPLACE_APP_AUTO_QA_URL);

	public static final long MARKETPLACE_APP_ENTRY_ID = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.MARKETPLACE_APP_ENTRY_ID));

	public static final String MARKETPLACE_APP_MANUAL_QA_DIR = PortletProps.get(PortletPropsKeys.MARKETPLACE_APP_MANUAL_QA_DIR);

	public static final double MARKETPLACE_DEVELOPER_PAYMENT_PERCENTAGE = GetterUtil.getDouble(PortletProps.get(PortletPropsKeys.MARKETPLACE_DEVELOPER_PAYMENT_PERCENTAGE));

	public static final double MARKETPLACE_FATCA_WITHHOLDING_PERCENTAGE_DEFAULT = GetterUtil.getDouble(PortletProps.get(PortletPropsKeys.MARKETPLACE_FATCA_WITHHOLDING_PERCENTAGE_DEFAULT));

	public static final String[] MARKETPLACE_GLOBAL_CONTEXT_NAMES = PortletProps.getArray(PortletPropsKeys.MARKETPLACE_GLOBAL_CONTEXT_NAMES);

	public static final String MARKETPLACE_IN_PRODUCT_DOMAIN = PortletProps.get(PortletPropsKeys.MARKETPLACE_IN_PRODUCT_DOMAIN);

	public static final String MARKETPLACE_JARSIGNER_ALIAS = PortletProps.get(PortletPropsKeys.MARKETPLACE_JARSIGNER_ALIAS);

	public static final String MARKETPLACE_JARSIGNER_KEYPASS = PortletProps.get(PortletPropsKeys.MARKETPLACE_JARSIGNER_KEYPASS);

	public static final String MARKETPLACE_JARSIGNER_KEYSTORE = PortletProps.get(PortletPropsKeys.MARKETPLACE_JARSIGNER_KEYSTORE);

	public static final String MARKETPLACE_JARSIGNER_SIGFILE = PortletProps.get(PortletPropsKeys.MARKETPLACE_JARSIGNER_SIGFILE);

	public static final String MARKETPLACE_JARSIGNER_STOREPASS = PortletProps.get(PortletPropsKeys.MARKETPLACE_JARSIGNER_STOREPASS);

	public static final String MARKETPLACE_NOTIFICATION_EMAIL_ADDRESS = PortletProps.get(PortletPropsKeys.MARKETPLACE_NOTIFICATION_EMAIL_ADDRESS);

	public static final long[] MARKETPLACE_PACL_EXEMPT_DEVELOPER_ENTRY_IDS = StringUtil.split(PortletProps.get(PortletPropsKeys.MARKETPLACE_PACL_EXEMPT_DEVELOPER_ENTRY_IDS), 0L);

	public static final String[] MARKETPLACE_PAYOUT_NOTIFICATION_EMAIL_ADDRESSES = PortletProps.getArray(PortletPropsKeys.MARKETPLACE_PAYOUT_NOTIFICATION_EMAIL_ADDRESSES);

	public static final String[] MARKETPLACE_PROJECTS_ONLY_PURCHASE_COUNTRIES = PortletProps.getArray(PortletPropsKeys.MARKETPLACE_PROJECTS_ONLY_PURCHASE_COUNTRIES);

	public static final String[] MARKETPLACE_PURCHASE_NOTIFICATION_EMAIL_ADDRESSES = PortletProps.getArray(PortletPropsKeys.MARKETPLACE_PURCHASE_NOTIFICATION_EMAIL_ADDRESSES);

	public static final String MARKETPLACE_RELEASE_TEAM_TOKEN = PortletProps.get(PortletPropsKeys.MARKETPLACE_RELEASE_TEAM_TOKEN);

	public static final long MARKETPLACE_RESOURCES_IMPORTER_CE_APP_ENTRY_ID = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.MARKETPLACE_RESOURCES_IMPORTER_CE_APP_ENTRY_ID));

	public static final long MARKETPLACE_RESOURCES_IMPORTER_EE_APP_ENTRY_ID = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.MARKETPLACE_RESOURCES_IMPORTER_EE_APP_ENTRY_ID));

	public static final String[] MARKETPLACE_RESTRICTED_COUNTRIES = PortletProps.getArray(PortletPropsKeys.MARKETPLACE_RESTRICTED_COUNTRIES);

	public static final int MARKETPLACE_SALES_ORDER_MAX_AGE = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.MARKETPLACE_SALES_ORDER_MAX_AGE));

	public static final String MARKETPLACE_SO_CE_APP_ENTRY_TITLE = PortletProps.get(PortletPropsKeys.MARKETPLACE_SO_CE_APP_ENTRY_TITLE);

	public static final String MARKETPLACE_SO_EE_APP_ENTRY_TITLE = PortletProps.get(PortletPropsKeys.MARKETPLACE_SO_EE_APP_ENTRY_TITLE);

	public static final String MARKETPLACE_SO_EE_EMAIL_ADDRESS = PortletProps.get(PortletPropsKeys.MARKETPLACE_SO_EE_EMAIL_ADDRESS);

	public static final String MARKETPLACE_SO_EE_EMAIL_BODY = PortletProps.get(PortletPropsKeys.MARKETPLACE_SO_EE_EMAIL_BODY);

	public static final String MARKETPLACE_SO_EE_EMAIL_SUBJECT = PortletProps.get(PortletPropsKeys.MARKETPLACE_SO_EE_EMAIL_SUBJECT);

	public static final String MARKETPLACE_SO_EE_SALES_EMAIL_ADDRESS = PortletProps.get(PortletPropsKeys.MARKETPLACE_SO_EE_SALES_EMAIL_ADDRESS);

	public static final String MARKETPLACE_STORE_COUNTRY_DEFAULT = PortletProps.get(PortletPropsKeys.MARKETPLACE_STORE_COUNTRY_DEFAULT);

	public static final long MARKETPLACE_THEMES_ASSET_CATEGORY_ID = GetterUtil.getLong(PortletProps.get(PortletPropsKeys.MARKETPLACE_THEMES_ASSET_CATEGORY_ID));

	public static final String MARKETPLACE_TWITTER_ACCESS_TOKEN = PortletProps.get(PortletPropsKeys.MARKETPLACE_TWITTER_ACCESS_TOKEN);

	public static final String MARKETPLACE_TWITTER_ACCESS_TOKEN_SECRET = PortletProps.get(PortletPropsKeys.MARKETPLACE_TWITTER_ACCESS_TOKEN_SECRET);

	public static final String MARKETPLACE_TWITTER_CONSUMER_KEY = PortletProps.get(PortletPropsKeys.MARKETPLACE_TWITTER_CONSUMER_KEY);

	public static final String MARKETPLACE_TWITTER_CONSUMER_SECRET = PortletProps.get(PortletPropsKeys.MARKETPLACE_TWITTER_CONSUMER_SECRET);

	public static final String MARKETPLACE_TWITTER_HASHTAG = PortletProps.get(PortletPropsKeys.MARKETPLACE_TWITTER_HASHTAG);

	public static final long[] MARKETPLACE_UNSELECTABLE_ASSET_CATEGORY_IDS = StringUtil.split(PortletProps.get(PortletPropsKeys.MARKETPLACE_UNSELECTABLE_ASSET_CATEGORY_IDS), 0L);

	public static final int MARKETPLACE_WARN_LICENSE_EXPIRATION = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.MARKETPLACE_WARN_LICENSE_EXPIRATION));

	public static final int PROFILE_CORP_APPS_MAX_DISPLAYED = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.PROFILE_CORP_APPS_MAX_DISPLAYED));

	public static final int PROFILE_USER_APPS_MAX_DISPLAYED = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.PROFILE_USER_APPS_MAX_DISPLAYED));

	public static final String PROMETRIC_HOST = PortletProps.get(PortletPropsKeys.PROMETRIC_HOST);

	public static final int PROMETRIC_MAX_ATTEMPTS = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.PROMETRIC_MAX_ATTEMPTS));

	public static final String PROMETRIC_PASSWORD = PortletProps.get(PortletPropsKeys.PROMETRIC_PASSWORD);

	public static final String PROMETRIC_REMOTE_DIR = PortletProps.get(PortletPropsKeys.PROMETRIC_REMOTE_DIR);

	public static final String PROMETRIC_SUBSCRIBER_EMAIL_ADDRESSES = PortletProps.get(PortletPropsKeys.PROMETRIC_SUBSCRIBER_EMAIL_ADDRESSES);

	public static final String PROMETRIC_USERNAME = PortletProps.get(PortletPropsKeys.PROMETRIC_USERNAME);

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

	public static final String TRAINING_CERTIFICATION_EMAIL_ADDRESS = PortletProps.get(PortletPropsKeys.TRAINING_CERTIFICATION_EMAIL_ADDRESS);

	public static final String[] TRAINING_EVENT_LANGUAGE_LOCALES = PortletProps.getArray(PortletPropsKeys.TRAINING_EVENT_LANGUAGE_LOCALES);

	public static final String[] TRAINING_EVENT_LOCALIZED_SLIDES = PortletProps.getArray(PortletPropsKeys.TRAINING_EVENT_LOCALIZED_SLIDES);

	public static final String[] TRAINING_EXAM_IGNORE_SECTION = PortletProps.getArray(PortletPropsKeys.TRAINING_EXAM_IGNORE_SECTION);

	public static final String TRAINING_EXAM_RESULTS_IMPORTER = PortletProps.get(PortletPropsKeys.TRAINING_EXAM_RESULTS_IMPORTER);

	public static final String TRAINING_SURVEY_NOTIFICATION_EMAIL_ADDRESSES = PortletProps.get(PortletPropsKeys.TRAINING_SURVEY_NOTIFICATION_EMAIL_ADDRESSES);

	public static final String TRAINING_SURVEY_STRUCTURE_STORAGE_TYPE = PortletProps.get(PortletPropsKeys.TRAINING_SURVEY_STRUCTURE_STORAGE_TYPE);

	public static final int USER_INACTIVE_NOTIFICATION_BLOG_ENTRIES_DISPLAYED = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.USER_INACTIVE_NOTIFICATION_BLOG_ENTRIES_DISPLAYED));

	public static final int USER_INACTIVE_NOTIFICATION_MB_THREADS_DISPLAYED = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.USER_INACTIVE_NOTIFICATION_MB_THREADS_DISPLAYED));

	public static final int USER_INACTIVE_NOTIFICATION_MB_THREADS_MIN_MESSAGE_COUNT = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.USER_INACTIVE_NOTIFICATION_MB_THREADS_MIN_MESSAGE_COUNT));

	public static final int USER_INACTIVE_NOTIFICATION_PERIOD = GetterUtil.getInteger(PortletProps.get(PortletPropsKeys.USER_INACTIVE_NOTIFICATION_PERIOD));

	public static final String[] VAT_EU_COUNTRIES = PortletProps.getArray(PortletPropsKeys.VAT_EU_COUNTRIES);

	public static final String VERIFY_USER_API_TOKEN = GetterUtil.getString(PortletProps.get(PortletPropsKeys.VERIFY_USER_API_TOKEN));

}