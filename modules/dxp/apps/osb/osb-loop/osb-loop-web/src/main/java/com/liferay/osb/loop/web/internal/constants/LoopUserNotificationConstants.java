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

package com.liferay.osb.loop.web.internal.constants;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Timothy Bell
 */
public class LoopUserNotificationConstants {

	public static final String ACTION_PAGE = "page";

	public static final String ACTION_POST = "post";

	public static final String ACTION_REPLIES = "replies";

	public static final String CATEGORY_GENERAL = "general";

	public static final String CATEGORY_GROUPS_AND_TOPICS = "groups-and-topics";

	public static final String CATEGORY_LIKES_COMMENTS_AND_REPLIES =
		"likes-comments-and-replies";

	public static final String CONTROLLER_DESTINATION_NAME =
		"liferay/loop_email_notification";

	public static final int DELIVERY_TYPE_EMAIL = 2;

	public static final int DELIVERY_TYPE_IN_APP = 1;

	public static final int DELIVERY_TYPE_NONE = 0;

	public static final int DELIVERY_TYPE_PUSH = 3;

	public static final int[] DELIVERY_TYPES = {
		DELIVERY_TYPE_EMAIL, DELIVERY_TYPE_IN_APP, DELIVERY_TYPE_PUSH
	};

	public static final String LABEL_EMAIL = "email";

	public static final String LABEL_IN_APP = "inApp";

	public static final String LABEL_PUSH = "push";

	public static final String[] LABELS_DELIVERY_TYPE = {
		LABEL_EMAIL, LABEL_IN_APP, LABEL_PUSH
	};

	public static final String[] SETTING_CATEGORIES = {
		CATEGORY_GENERAL, CATEGORY_LIKES_COMMENTS_AND_REPLIES,
		CATEGORY_GROUPS_AND_TOPICS
	};

	public static final String SETTING_KEY_COMMENTS_ON_MY_POSTS =
		"commentsOnMyPosts";

	public static final String SETTING_KEY_FOLLOWING = "following";

	public static final String SETTING_KEY_GROUP_MEMBER = "groupMember";

	public static final String SETTING_KEY_LIKED_COMMENTS_AND_REPLIES =
		"likedCommentsAndReplies";

	public static final String SETTING_KEY_LIKED_POST = "likedPost";

	public static final String SETTING_KEY_MENTIONED = "mentioned";

	public static final String SETTING_KEY_POSTS_I_COMMENTED_ON =
		"postsICommentedOn";

	public static final String SETTING_KEY_REPLIES_TO_MY_COMMENTS =
		"repliesToMyComments";

	public static final String SETTING_KEY_SHOW_WARNING = "showWarning";

	public static final String SETTING_KEY_TOPIC_EXPERT = "topicExpert";

	public static final String[] SETTING_KEYS = {
		SETTING_KEY_COMMENTS_ON_MY_POSTS, SETTING_KEY_FOLLOWING,
		SETTING_KEY_GROUP_MEMBER, SETTING_KEY_LIKED_COMMENTS_AND_REPLIES,
		SETTING_KEY_LIKED_POST, SETTING_KEY_MENTIONED,
		SETTING_KEY_POSTS_I_COMMENTED_ON, SETTING_KEY_REPLIES_TO_MY_COMMENTS,
		SETTING_KEY_SHOW_WARNING, SETTING_KEY_TOPIC_EXPERT
	};

	public static final String[] SETTING_KEYS_CATEGORY_GENERAL = {
		SETTING_KEY_MENTIONED, SETTING_KEY_FOLLOWING
	};

	public static final String[] SETTING_KEYS_CATEGORY_GROUPS_AND_TOPICS = {
		SETTING_KEY_GROUP_MEMBER, SETTING_KEY_TOPIC_EXPERT
	};

	public static final String[]
		SETTING_KEYS_CATEGORY_LIKES_COMMENTS_AND_REPLIES = {
			SETTING_KEY_LIKED_POST, SETTING_KEY_LIKED_COMMENTS_AND_REPLIES,
			SETTING_KEY_COMMENTS_ON_MY_POSTS, SETTING_KEY_POSTS_I_COMMENTED_ON,
			SETTING_KEY_REPLIES_TO_MY_COMMENTS
		};

	public static final String[] SETTING_KEYS_TYPE_BOOLEAN = {
		SETTING_KEY_SHOW_WARNING
	};

	public static final String[] SETTING_KEYS_TYPE_JSON_OBJECT = {
		SETTING_KEY_COMMENTS_ON_MY_POSTS, SETTING_KEY_FOLLOWING,
		SETTING_KEY_GROUP_MEMBER, SETTING_KEY_LIKED_COMMENTS_AND_REPLIES,
		SETTING_KEY_LIKED_POST, SETTING_KEY_MENTIONED,
		SETTING_KEY_POSTS_I_COMMENTED_ON, SETTING_KEY_REPLIES_TO_MY_COMMENTS,
		SETTING_KEY_TOPIC_EXPERT
	};

	public static final int TYPE_ANNOUNCEMENT = 9;

	public static final int TYPE_COMMENTED_ON_MY_POST = 3;

	public static final int TYPE_COMMENTED_ON_SUBSCRIBED_POST = 8;

	public static final int TYPE_FOLLOWING_CREATOR_COMMENTS_AND_REPLIES = 11;

	public static final int TYPE_FOLLOWING_CREATOR_POSTS = 7;

	public static final int TYPE_FOLLOWING_DIVISION_MENTIONED = 5;

	public static final int TYPE_FOLLOWING_ENTITY_MENTIONED = 1;

	public static final int TYPE_FOLLOWING_ENTITY_PAGE_CREATED = 12;

	public static final int TYPE_FOLLOWING_PERSON_MENTIONED = 6;

	public static final int TYPE_FOLLOWING_TOPIC_MENTIONED = 4;

	public static final int TYPE_LIKED = 10;

	public static final int TYPE_MENTIONED_DIRECTLY = 2;

	public static String[] getCategorySettingKeys(String category) {
		if (category.equals(CATEGORY_GENERAL)) {
			return SETTING_KEYS_CATEGORY_GENERAL;
		}
		else if (category.equals(CATEGORY_GROUPS_AND_TOPICS)) {
			return SETTING_KEYS_CATEGORY_GROUPS_AND_TOPICS;
		}
		else if (category.equals(CATEGORY_LIKES_COMMENTS_AND_REPLIES)) {
			return SETTING_KEYS_CATEGORY_LIKES_COMMENTS_AND_REPLIES;
		}

		return new String[0];
	}

	public static String getLabel(int deliveryType) {
		String label = StringPool.BLANK;

		if (deliveryType == DELIVERY_TYPE_EMAIL) {
			label = LABEL_EMAIL;
		}
		else if (deliveryType == DELIVERY_TYPE_IN_APP) {
			label = LABEL_IN_APP;
		}
		else if (deliveryType == DELIVERY_TYPE_PUSH) {
			label = LABEL_PUSH;
		}

		return label;
	}

	public static String getSettingLabel(String key) {
		if (key.equals(SETTING_KEY_COMMENTS_ON_MY_POSTS)) {
			return "comments-on-my-posts";
		}
		else if (key.equals(SETTING_KEY_FOLLOWING)) {
			return "anything-i-am-following";
		}
		else if (key.equals(SETTING_KEY_GROUP_MEMBER)) {
			return "groups-i-am-a-member-of";
		}
		else if (key.equals(SETTING_KEY_LIKED_COMMENTS_AND_REPLIES)) {
			return "likes-on-my-comments-and-replies";
		}
		else if (key.equals(SETTING_KEY_LIKED_POST)) {
			return "likes-on-my-posts";
		}
		else if (key.equals(SETTING_KEY_MENTIONED)) {
			return "anything-i-have-been-mentioned-in";
		}
		else if (key.equals(SETTING_KEY_POSTS_I_COMMENTED_ON)) {
			return "posts-i-commented-on";
		}
		else if (key.equals(SETTING_KEY_REPLIES_TO_MY_COMMENTS)) {
			return "replies-to-my-comments";
		}
		else if (key.equals(SETTING_KEY_TOPIC_EXPERT)) {
			return "topics-i-am-an-expert-of";
		}

		return StringPool.BLANK;
	}

	public static boolean isGroupedComments(int notificationType) {
		if ((notificationType == TYPE_COMMENTED_ON_MY_POST) ||
			(notificationType == TYPE_COMMENTED_ON_SUBSCRIBED_POST) ||
			(notificationType == TYPE_FOLLOWING_CREATOR_COMMENTS_AND_REPLIES)) {

			return true;
		}

		return false;
	}

	public static boolean isGroupedPush(int notificationType) {
		if ((notificationType == TYPE_COMMENTED_ON_MY_POST) ||
			(notificationType == TYPE_COMMENTED_ON_SUBSCRIBED_POST) ||
			(notificationType == TYPE_LIKED)) {

			return true;
		}

		return false;
	}

}