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

package com.liferay.osb.loop.web.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Timothy Bell
 */
public class LoopWebConfigurationValues {

	public static final int API_VERSION_CURRENT = GetterUtil.getInteger(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.API_VERSION_CURRENT));

	public static final int API_VERSION_MINIMUM = GetterUtil.getInteger(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.API_VERSION_MINIMUM));

	public static final String EMAIL_PREFIX = LoopWebConfigurationUtil.get(
		LoopWebConfigurationKeys.EMAIL_PREFIX);

	public static final String EXTERNAL_FACEBOOK_URL =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.EXTERNAL_FACEBOOK_URL);

	public static final String EXTERNAL_GITHUB_URL =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.EXTERNAL_GITHUB_URL);

	public static final String EXTERNAL_LINKEDIN_URL =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.EXTERNAL_LINKEDIN_URL);

	public static final String EXTERNAL_TWITTER_URL =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.EXTERNAL_TWITTER_URL);

	public static final int FEATURED_CONTENT_MAX_COUNT = GetterUtil.getInteger(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.FEATURED_CONTENT_MAX_COUNT));

	public static final String[] IMAGE_COVER_TYPES =
		LoopWebConfigurationUtil.getArray(
			LoopWebConfigurationKeys.IMAGE_COVER_TYPES);

	public static final String[] IMAGE_EXTENSIONS =
		LoopWebConfigurationUtil.getArray(
			LoopWebConfigurationKeys.IMAGE_EXTENSIONS);

	public static final String[] IMAGE_PROFILE_TYPES =
		LoopWebConfigurationUtil.getArray(
			LoopWebConfigurationKeys.IMAGE_PROFILE_TYPES);

	public static final int LOOP_AUTOCOMPLETE_MAX_RESULTS =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_AUTOCOMPLETE_MAX_RESULTS));

	public static final int LOOP_CHILD_POSTS_DISPLAY_MAX =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_CHILD_POSTS_DISPLAY_MAX));

	public static final String LOOP_CRON_TRIGGER_HOME_CONTROLLER =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_CRON_TRIGGER_HOME_CONTROLLER);

	public static final String LOOP_CRON_TRIGGER_NOTIFICATIONS_CONTROLLER =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.
				LOOP_CRON_TRIGGER_NOTIFICATIONS_CONTROLLER);

	public static final String LOOP_CRON_TRIGGER_PEOPLE_CONTROLLER =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_CRON_TRIGGER_PEOPLE_CONTROLLER);

	public static final String[] LOOP_EMAIL_ADDRESSES_ADMIN =
		LoopWebConfigurationUtil.getArray(
			LoopWebConfigurationKeys.LOOP_EMAIL_ADDRESSES_ADMIN);

	public static final String LOOP_EMAIL_NOTIFICATION_ATTACHED_CONTENT =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_EMAIL_NOTIFICATION_ATTACHED_CONTENT);

	public static final String LOOP_EMAIL_NOTIFICATION_BODY =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_EMAIL_NOTIFICATION_BODY);

	public static final String LOOP_EMAIL_NOTIFICATION_COMMENT =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_EMAIL_NOTIFICATION_COMMENT);

	public static final String LOOP_EMAIL_NOTIFICATION_FEEDBACK =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_EMAIL_NOTIFICATION_FEEDBACK);

	public static final boolean LOOP_EMAIL_NOTIFICATION_FEEDBACK_ENABLED =
		GetterUtil.getBoolean(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					LOOP_EMAIL_NOTIFICATION_FEEDBACK_ENABLED));

	public static final String LOOP_EMAIL_NOTIFICATION_FROM_ADDRESS =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_EMAIL_NOTIFICATION_FROM_ADDRESS);

	public static final String LOOP_EMAIL_NOTIFICATION_FROM_NAME =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_EMAIL_NOTIFICATION_FROM_NAME);

	public static final int LOOP_EMAIL_NOTIFICATION_MESSAGE_LENGTH =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					LOOP_EMAIL_NOTIFICATION_MESSAGE_LENGTH));

	public static final String LOOP_EMAIL_NOTIFICATION_READ_MORE_LINK =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_EMAIL_NOTIFICATION_READ_MORE_LINK);

	public static final int LOOP_EMAIL_NOTIFICATION_SUBJECT_MAX_CHAR_COUNT =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					LOOP_EMAIL_NOTIFICATION_SUBJECT_MAX_CHAR_COUNT));

	public static final int LOOP_FEED_PAGE_DEFAULT_DELTA =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_FEED_PAGE_DEFAULT_DELTA));

	public static final int LOOP_FILE_UPLOADER_COUNT_MAX =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_FILE_UPLOADER_COUNT_MAX));

	public static final String LOOP_HELP_JOURNAL_ARTICLE_ID =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_HELP_JOURNAL_ARTICLE_ID);

	public static final String LOOP_KONAMI_REDIRECT_URL =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_KONAMI_REDIRECT_URL);

	public static final int LOOP_NOTIFICATIONS_TOOLBAR_PAGE_DEFAULT_DELTA =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					LOOP_NOTIFICATIONS_TOOLBAR_PAGE_DEFAULT_DELTA));

	public static final int LOOP_PAGE_DEFAULT_DELTA = GetterUtil.getInteger(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_PAGE_DEFAULT_DELTA));

	public static final String[] LOOP_PERSON_DISABLED_FIELDS_LOOP_PERSON =
		LoopWebConfigurationUtil.getArray(
			LoopWebConfigurationKeys.LOOP_PERSON_DISABLED_FIELDS_LOOP_PERSON);

	public static final String[] LOOP_PERSON_DISABLED_FIELDS_LOOP_PERSON_ADMIN =
		LoopWebConfigurationUtil.getArray(
			LoopWebConfigurationKeys.
				LOOP_PERSON_DISABLED_FIELDS_LOOP_PERSON_ADMIN);

	public static final String LOOP_PERSON_TOP_LEVEL_ICON =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_PERSON_TOP_LEVEL_ICON);

	public static final String LOOP_PERSON_TOP_LEVEL_MANAGER_NAME =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_PERSON_TOP_LEVEL_MANAGER_NAME);

	public static final String[] LOOP_PERSON_TOP_LEVEL_SCREEN_NAMES =
		LoopWebConfigurationUtil.getArray(
			LoopWebConfigurationKeys.LOOP_PERSON_TOP_LEVEL_SCREEN_NAMES);

	public static final boolean LOOP_POST_PRIVACY_CHANGE_ALLOWED =
		GetterUtil.getBoolean(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_POST_PRIVACY_CHANGE_ALLOWED));

	public static final double LOOP_STATS_ENTRY_SCORE_DECAY_MULTIPLIER =
		GetterUtil.getDouble(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					LOOP_STATS_ENTRY_SCORE_DECAY_MULTIPLIER));

	public static final double LOOP_STATS_ENTRY_SCORE_DECAY_THRESHOLD =
		GetterUtil.getDouble(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					LOOP_STATS_ENTRY_SCORE_DECAY_THRESHOLD));

	public static final int LOOP_STATS_ENTRY_SCORE_DECAY_TIME =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_STATS_ENTRY_SCORE_DECAY_TIME));

	public static final double LOOP_STATS_ENTRY_SCORE_VALUE_ADD_POST =
		GetterUtil.getDouble(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					LOOP_STATS_ENTRY_SCORE_VALUE_ADD_POST));

	public static final double LOOP_STATS_ENTRY_SCORE_VALUE_LIKE =
		GetterUtil.getDouble(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_STATS_ENTRY_SCORE_VALUE_LIKE));

	public static final Boolean LOOP_TESTS_ENABLED = GetterUtil.getBoolean(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_TESTS_ENABLED));

	public static final String LOOP_TOPIC_FEEDBACK_NAME =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.LOOP_TOPIC_FEEDBACK_NAME);

	public static final int LOOP_TOPIC_PAGE_DEFAULT_DELTA =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_TOPIC_PAGE_DEFAULT_DELTA));

	public static final boolean LOOP_UI_KIT_PAGE_ENABLED =
		GetterUtil.getBoolean(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_UI_KIT_PAGE_ENABLED));

	public static final int LOOP_USER_NOTIFICATION_CLEAN_TIME =
		GetterUtil.getInteger(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.LOOP_USER_NOTIFICATION_CLEAN_TIME));

	public static final int NEW_DIVISION_AGE_THRESHOLD = GetterUtil.getInteger(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.NEW_DIVISION_AGE_THRESHOLD));

	public static final int NEW_HIRE_AGE_THRESHOLD = GetterUtil.getInteger(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.NEW_HIRE_AGE_THRESHOLD));

	public static final int POLL_INDEX_MAX_COUNT = GetterUtil.getInteger(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.POLL_INDEX_MAX_COUNT));

	public static final int POLL_INDEX_WAIT_INTERVAL = GetterUtil.getInteger(
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.POLL_INDEX_WAIT_INTERVAL));

	public static final boolean RABBIT_MQ_CONSUME_ENABLED =
		GetterUtil.getBoolean(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.RABBIT_MQ_CONSUME_ENABLED));

	public static final String RABBIT_MQ_EXCHANGE_HOSTNAME =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.RABBIT_MQ_EXCHANGE_HOSTNAME);

	public static final String RABBIT_MQ_EXCHANGE_NAME =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.RABBIT_MQ_EXCHANGE_NAME);

	public static final String RABBIT_MQ_EXCHANGE_PASSWORD =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.RABBIT_MQ_EXCHANGE_PASSWORD);

	public static final String RABBIT_MQ_EXCHANGE_USERNAME =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.RABBIT_MQ_EXCHANGE_USERNAME);

	public static final boolean RABBIT_MQ_PUBLISH_ENABLED =
		GetterUtil.getBoolean(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.RABBIT_MQ_PUBLISH_ENABLED));

	public static final String RABBIT_MQ_QUEUE = LoopWebConfigurationUtil.get(
		LoopWebConfigurationKeys.RABBIT_MQ_QUEUE);

	public static final String RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_ADD =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_ADD);

	public static final String RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_DELETE =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_DELETE);

	public static final String
		RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_REMOVE_PARTICIPANT =
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_REMOVE_PARTICIPANT);

	public static final String RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_CHILD =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_CHILD);

	public static final String RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_PARENT =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_PARENT);

	public static final String
		RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_PARTICIPANT =
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_SET_PARTICIPANT);

	public static final String RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_UPDATE =
		LoopWebConfigurationUtil.get(
			LoopWebConfigurationKeys.
				RABBIT_MQ_ROUTING_KEY_LOOP_DIVISION_UPDATE);

	public static final boolean RABBIT_MQ_SYNC_LOOP_PERSON_BIRTHDAY_ENABLED =
		GetterUtil.getBoolean(
			LoopWebConfigurationUtil.get(
				LoopWebConfigurationKeys.
					RABBIT_MQ_SYNC_LOOP_PERSON_BIRTHDAY_ENABLED));

}