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

package com.liferay.osb.loop.constants;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Calvin Keum
 * @author Timothy Bell
 */
public class LoopConstants {

	public static final int ASSET_ENTRY_SET_CHILD_ASSET_ENTRY_SETS_LIMIT = 3;

	public static final int ASSET_ENTRY_SET_LIKED_PARTICIPANTS_LIMIT = 6;

	public static final String ENCODE_TOKEN = "%~{}~%";

	public static final String IMAGE_TYPE_RAW = "raw";

	public static final String IMAGE_TYPE_WEB = "web";

	public static final String LABEL_DEPARTMENT = "department";

	public static final String LABEL_LOCATION = "location";

	public static final String LABEL_ROOT = "root";

	public static final String LABEL_TEAM = "team";

	public static final int LOOP_TOPIC_NAME_MAX_LENGTH = 75;

	public static final String PAYLOAD_TYPE_IMAGE = "image";

	public static final String PAYLOAD_TYPE_LINK = "link";

	public static final String PAYLOAD_TYPE_TEXT = "text";

	public static final int QUERY_TYPE_AT = 1;

	public static final int QUERY_TYPE_HASHTAG = 2;

	public static final int TYPE_LOOP_AUDIT_ENTRY = 9;

	public static final int TYPE_LOOP_DIVISION = 7;

	public static final int TYPE_LOOP_DIVISION_DEPARTMENT = 2;

	public static final int TYPE_LOOP_DIVISION_LOCATION = 4;

	public static final int TYPE_LOOP_DIVISION_ROOT = 1;

	public static final int TYPE_LOOP_DIVISION_TEAM = 3;

	public static final int TYPE_LOOP_JOB_TITLE = 8;

	public static final int TYPE_LOOP_PERSON = 5;

	public static final int TYPE_LOOP_TOPIC = 6;

	public static final int TYPE_REMOVED = -1;

	public static final String URL_LOOP_ASSET_ENTRY_SET_PAGE = "/pages/";

	public static final String URL_LOOP_ASSET_ENTRY_SET_POST = "/-/loop/feed/";

	public static final String URL_LOOP_DIVISION_DEPARTMENT =
		"/-/loop/departments/";

	public static final String URL_LOOP_DIVISION_LOCATION =
		"/-/loop/locations/";

	public static final String URL_LOOP_DIVISION_ROOT = "/-/loop/company/";

	public static final String URL_LOOP_DIVISION_TEAM = "/-/loop/teams/";

	public static final String URL_LOOP_FEED = "/-/loop/feed/";

	public static final String URL_LOOP_JOB_TITLES = "/-/loop/job_titles/";

	public static final String URL_LOOP_PEOPLE = "/-/loop/people/";

	public static final String URL_LOOP_REGEX_URL_NAME =
		"\\/-\\/loop\\/\\w+\\/_([%|\\-|\\+|\\w]+)";

	public static final String URL_LOOP_TOPIC = "/-/loop/topics/";

	public static final String URL_TOKEN_HELP_ARTICLE_ID = StringPool.UNDERLINE;

	public static final String URL_TOKEN_LOOP_DIVISION_NAME =
		StringPool.UNDERLINE;

	public static final String URL_TOKEN_LOOP_JOB_TITLES_NAME =
		StringPool.UNDERLINE;

	public static final String URL_TOKEN_LOOP_PEOPLE_NAME =
		StringPool.UNDERLINE;

	public static final String URL_TOKEN_LOOP_TOPIC_NAME = StringPool.UNDERLINE;

}