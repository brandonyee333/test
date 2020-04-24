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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.service.AssetEntrySetLocalServiceUtil;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetParticipantInfoUtil;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.web.internal.composite.LoopDivisionComposite;
import com.liferay.osb.loop.web.internal.composite.LoopPersonComposite;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.osb.loop.web.internal.image.LoopImageURL;
import com.liferay.osb.loop.web.internal.image.LoopImageURLFactory;
import com.liferay.osb.loop.web.internal.notifications.LoopUserNotificationEventUtil;
import com.liferay.petra.content.ContentUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.Format;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

/**
 * @author Timothy Bell
 */
public class LoopEmailNotificationUtil {

	public static void sendActivityLogEmail(
		String subjectName, String message, JSONObject jsonObject) {

		String[] emailAddresses =
			LoopWebConfigurationValues.LOOP_EMAIL_ADDRESSES_ADMIN;

		if (emailAddresses.length == 0) {
			return;
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		StringBundler sb = new StringBundler(4);

		if (Validator.isNotNull(message)) {
			sb.append(message);
			sb.append("<br />");
			sb.append("<br />");
		}

		sb.append(jsonObject.toString());

		subscriptionSender.setBody(sb.toString());

		subscriptionSender.setCompanyId(PortalUtil.getDefaultCompanyId());
		subscriptionSender.setFrom(
			LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_FROM_ADDRESS,
			LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_FROM_NAME);
		subscriptionSender.setHtmlFormat(false);

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		subscriptionSender.setMailId(
			"loop_activity_log_entry", dateFormat.format(new Date()));

		sb = new StringBundler(4);

		sb.append("[Loop Portlet] ");
		sb.append(subjectName);
		sb.append(" Activity Log ");

		dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

		sb.append(dateFormat.format(new Date()));

		subscriptionSender.setSubject(sb.toString());

		for (String emailAddress : emailAddresses) {
			subscriptionSender.addRuntimeSubscribers(
				emailAddress, emailAddress);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendEmailNotifications(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			List<Long> userIds, int notificationType)
		throws Exception {

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		String body = getBody(themeDisplay, assetEntrySet);

		subscriptionSender.setBody(styleBody(body));

		subscriptionSender.setCompanyId(assetEntrySet.getCompanyId());

		AssetEntrySet parentAssetEntrySet = assetEntrySet;

		String message = LoopUserNotificationEventUtil.getMessage(
			PortalUtil.getClassNameId(AssetEntrySet.class),
			assetEntrySet.getAssetEntrySetId(),
			LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_MESSAGE_LENGTH,
			false);

		if (assetEntrySet.getParentAssetEntrySetId() > 0) {
			parentAssetEntrySet =
				AssetEntrySetLocalServiceUtil.getAssetEntrySet(
					assetEntrySet.getParentAssetEntrySetId());

			subscriptionSender.setContextAttributes(
				"[$COMMENT_CREATOR_DISPLAY_URL$]",
				getCreatorDisplayURL(
					themeDisplay, assetEntrySet.getCreatorClassNameId(),
					assetEntrySet.getCreatorClassPK()),
				"[$COMMENT_CREATOR_NAME$]",
				getCreatorName(
					assetEntrySet.getCreatorClassNameId(),
					assetEntrySet.getCreatorClassPK()),
				"[$COMMENT_CREATOR_PROFILE_IMAGE_URL$]",
				getCreatorProfileImageURL(
					assetEntrySet.getCreatorClassNameId(),
					assetEntrySet.getCreatorClassPK()));

			String commentMessage = message;

			subscriptionSender.setContextAttribute(
				"[$COMMENT_MESSAGE$]", commentMessage, false);

			int childAssetEntrySetsCount =
				parentAssetEntrySet.getChildAssetEntrySetsCount();

			if (childAssetEntrySetsCount > 1) {
				String pattern = "read-x-more-comment";

				if (childAssetEntrySetsCount > 2) {
					pattern = "read-x-more-comments";
				}

				subscriptionSender.setContextAttribute(
					"[$READ_MORE_TEXT$]",
					themeDisplay.translate(
						pattern, childAssetEntrySetsCount - 1));
			}

			message = LoopUserNotificationEventUtil.getTruncatedMessage(
				parentAssetEntrySet);
		}

		subscriptionSender.setContextAttribute("[$MESSAGE$]", message, false);

		subscriptionSender.setContextAttributes(
			"[$ADD_COMMENT_TEXT$]",
			themeDisplay.translate("view-post-or-comment-on-loop"),
			"[$CREATOR_DISPLAY_URL$]",
			getCreatorDisplayURL(
				themeDisplay, parentAssetEntrySet.getCreatorClassNameId(),
				parentAssetEntrySet.getCreatorClassPK()),
			"[$CREATOR_NAME$]",
			getCreatorName(
				parentAssetEntrySet.getCreatorClassNameId(),
				parentAssetEntrySet.getCreatorClassPK()),
			"[$CREATOR_PROFILE_IMAGE_URL$]",
			getCreatorProfileImageURL(
				parentAssetEntrySet.getCreatorClassNameId(),
				parentAssetEntrySet.getCreatorClassPK()),
			"[$FEEDBACK_BUTTON_TEXT$]",
			themeDisplay.translate(
				"share-to-x",
				LoopWebConfigurationValues.LOOP_TOPIC_FEEDBACK_NAME),
			"[$FEEDBACK_DISPLAY_URL$]",
			LoopTopicUtil.fetchLoopTopicURL(
				themeDisplay,
				LoopWebConfigurationValues.LOOP_TOPIC_FEEDBACK_NAME),
			"[$FEEDBACK_TEXT$]",
			themeDisplay.translate("do-you-have-any-feedback"), "[$LOOP_ICON$]",
			themeDisplay.getPathContext() + _LOOP_ICON,
			"[$NOTIFICATION_DISPLAY_URL$]",
			LoopUtil.getDisplayURL(
				themeDisplay.getCompanyId(),
				PortalUtil.getClassNameId(AssetEntrySet.class),
				assetEntrySet.getAssetEntrySetId()),
			"[$SUMMARY$]",
			getSummary(themeDisplay, assetEntrySet, notificationType));

		subscriptionSender.setFrom(
			LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_FROM_ADDRESS,
			getCreatorName(
				assetEntrySet.getCreatorClassNameId(),
				assetEntrySet.getCreatorClassPK()) + StringPool.SPACE +
					"(LOOP)");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId(
			"loop_entry", parentAssetEntrySet.getAssetEntrySetId());
		subscriptionSender.setSubject(
			getSubject(themeDisplay, parentAssetEntrySet, notificationType));

		for (long userId : userIds) {
			User user = UserLocalServiceUtil.getUser(userId);

			subscriptionSender.addRuntimeSubscribers(
				user.getEmailAddress(), user.getFullName());
		}

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendExceptionEmail(
		String message, JSONObject jsonObject) {

		String[] emailAddresses =
			LoopWebConfigurationValues.LOOP_EMAIL_ADDRESSES_ADMIN;

		if (emailAddresses.length == 0) {
			return;
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		StringBundler sb = new StringBundler(8);

		sb.append("Exception message:");
		sb.append("<br />");
		sb.append(message);
		sb.append("<br />");
		sb.append("<br />");
		sb.append("Failed message content:");

		if (jsonObject != null) {
			sb.append("<br />");
			sb.append(jsonObject.toString());
		}

		subscriptionSender.setBody(sb.toString());

		subscriptionSender.setCompanyId(PortalUtil.getDefaultCompanyId());
		subscriptionSender.setFrom(
			LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_FROM_ADDRESS,
			LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_FROM_NAME);
		subscriptionSender.setHtmlFormat(true);

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		subscriptionSender.setMailId(
			"loop_entry", dateFormat.format(new Date()));

		dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

		subscriptionSender.setSubject(
			"[Loop Portlet] HRIS Sync Error " + dateFormat.format(new Date()));

		for (String emailAddress : emailAddresses) {
			subscriptionSender.addRuntimeSubscribers(
				emailAddress, emailAddress);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected static String getAttachedContentTemplate(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet)
		throws Exception {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		String type = payloadJSONObject.getString("type");

		if (Objects.equals(type, "text")) {
			return StringPool.BLANK;
		}

		String content = StringPool.BLANK;
		String icon = StringPool.BLANK;

		String iconDimensions = "36px";

		if (assetEntrySet.getParentAssetEntrySetId() > 0) {
			iconDimensions = "24px";
		}

		if (Objects.equals(type, "image")) {
			String pattern = "view-x-attached-images";

			int count = getImageCount(assetEntrySet);

			if (count == 1) {
				pattern = "view-x-attached-image";
			}

			content = getLink(
				"[$NOTIFICATION_DISPLAY_URL$]",
				themeDisplay.translate(pattern, String.valueOf(count)));

			icon = _CAMERA_ICON;
		}
		else if (Objects.equals(type, "link")) {
			StringBundler sb = new StringBundler(3);

			sb.append(
				getLink(
					"[$NOTIFICATION_DISPLAY_URL$]",
					themeDisplay.translate("view-attached-link")));
			sb.append("<br />");

			String url = getLinkDataEntry(assetEntrySet, "url");

			sb.append(getLink(url, url));

			content = sb.toString();

			icon = _LINK_ICON;
		}

		String attachedContent = StringUtil.replace(
			ContentUtil.get(
				_CLASS_LOADER,
				LoopWebConfigurationValues.
					LOOP_EMAIL_NOTIFICATION_ATTACHED_CONTENT),
			"[$CONTENT$]", content);

		attachedContent = StringUtil.replace(
			attachedContent, "[$ICON$]", themeDisplay.getPathContext() + icon);
		attachedContent = StringUtil.replace(
			attachedContent, "[$ICON_DIMENSIONS$]", iconDimensions);

		return attachedContent;
	}

	protected static String getBody(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet)
		throws Exception {

		String emailNotificationBodyTemplate = ContentUtil.get(
			_CLASS_LOADER,
			LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_BODY);

		String emailNotificationFeedBackTemplate = StringPool.BLANK;

		if (LoopWebConfigurationValues.
				LOOP_EMAIL_NOTIFICATION_FEEDBACK_ENABLED) {

			emailNotificationFeedBackTemplate = ContentUtil.get(
				_CLASS_LOADER,
				LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_FEEDBACK);
		}

		emailNotificationBodyTemplate = StringUtil.replace(
			emailNotificationBodyTemplate, "[$FEEDBACK$]",
			emailNotificationFeedBackTemplate);

		String emailNotificationCommentTemplate = StringPool.BLANK;

		AssetEntrySet parentAssetEntrySet = assetEntrySet;

		long parentAssetEntrySetId = assetEntrySet.getParentAssetEntrySetId();

		if (parentAssetEntrySetId > 0) {
			emailNotificationCommentTemplate = ContentUtil.get(
				_CLASS_LOADER,
				LoopWebConfigurationValues.LOOP_EMAIL_NOTIFICATION_COMMENT);

			emailNotificationCommentTemplate = StringUtil.replace(
				emailNotificationCommentTemplate,
				"[$COMMENT_ATTACHED_CONTENT$]",
				getAttachedContentTemplate(themeDisplay, assetEntrySet));

			String emailNotificationReadMoreLinkTemplate = StringPool.BLANK;

			parentAssetEntrySet =
				AssetEntrySetLocalServiceUtil.getAssetEntrySet(
					parentAssetEntrySetId);

			if (parentAssetEntrySet.getChildAssetEntrySetsCount() > 1) {
				emailNotificationReadMoreLinkTemplate = ContentUtil.get(
					_CLASS_LOADER,
					LoopWebConfigurationValues.
						LOOP_EMAIL_NOTIFICATION_READ_MORE_LINK);
			}

			emailNotificationCommentTemplate = StringUtil.replace(
				emailNotificationCommentTemplate, "[$COMMENT_READ_MORE_LINK$]",
				emailNotificationReadMoreLinkTemplate);
		}

		emailNotificationBodyTemplate = StringUtil.replace(
			emailNotificationBodyTemplate, "[$ATTACHED_CONTENT$]",
			getAttachedContentTemplate(themeDisplay, parentAssetEntrySet));

		return StringUtil.replace(
			emailNotificationBodyTemplate, "[$COMMENT$]",
			emailNotificationCommentTemplate);
	}

	protected static String getCreatorDisplayURL(
			ThemeDisplay themeDisplay, long creatorClassNameId,
			long creatorClassPK)
		throws Exception {

		if (creatorClassNameId == PortalUtil.getClassNameId(LoopPerson.class)) {
			LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
				creatorClassPK, themeDisplay);

			return loopPersonComposite.getDisplayURL();
		}

		if (creatorClassNameId == PortalUtil.getClassNameId(
				LoopDivision.class)) {

			LoopDivisionComposite loopDivisionComposite =
				new LoopDivisionComposite(creatorClassPK, themeDisplay);

			return loopDivisionComposite.getDisplayURL();
		}

		return StringPool.BLANK;
	}

	protected static String getCreatorName(
			long creatorClassNameId, long creatorClassPK)
		throws Exception {

		if (creatorClassNameId == PortalUtil.getClassNameId(LoopPerson.class)) {
			LoopPersonComposite loopPersonComposite = new LoopPersonComposite(
				creatorClassPK, null);

			return loopPersonComposite.getName();
		}

		if (creatorClassNameId == PortalUtil.getClassNameId(
				LoopDivision.class)) {

			LoopDivisionComposite loopDivisionComposite =
				new LoopDivisionComposite(creatorClassPK, null);

			return loopDivisionComposite.getName();
		}

		return StringPool.BLANK;
	}

	protected static String getCreatorProfileImageURL(
			long creatorClassNameId, long creatorClassPK)
		throws Exception {

		LoopImageURL loopImageURL = LoopImageURLFactory.createLoopImageURL(
			creatorClassNameId, creatorClassPK, "profileImagePayload", "email");

		return loopImageURL.getImageURL();
	}

	protected static int getImageCount(AssetEntrySet assetEntrySet)
		throws Exception {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		if (!Objects.equals(payloadJSONObject.getString("type"), "image")) {
			return 0;
		}

		JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
			"imageData");

		return imageDataJSONArray.length();
	}

	protected static String getLink(String url, String name) {
		StringBundler sb = new StringBundler(5);

		sb.append("<a href=\"");
		sb.append(url);
		sb.append("\">");
		sb.append(HtmlUtil.escape(name));
		sb.append("</a>");

		return sb.toString();
	}

	protected static String getLinkDataEntry(
			AssetEntrySet assetEntrySet, String name)
		throws Exception {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		if (!Objects.equals(payloadJSONObject.getString("type"), "link")) {
			return StringPool.BLANK;
		}

		JSONObject linkDataJSONObject = payloadJSONObject.getJSONObject(
			"linkData");

		if ((linkDataJSONObject == null) || linkDataJSONObject.isNull(name)) {
			return StringPool.BLANK;
		}

		return linkDataJSONObject.getString(name);
	}

	protected static String getSubject(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			int notificationType)
		throws Exception {

		if ((notificationType ==
				LoopUserNotificationConstants.TYPE_ANNOUNCEMENT) ||
			(notificationType ==
				LoopUserNotificationConstants.
					TYPE_FOLLOWING_ENTITY_PAGE_CREATED)) {

			return StringUtil.shorten(
				assetEntrySet.getTitle(),
				LoopWebConfigurationValues.
					LOOP_EMAIL_NOTIFICATION_SUBJECT_MAX_CHAR_COUNT);
		}

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			assetEntrySet.getPayload());

		String message = payloadJSONObject.getString("message");

		message = HtmlUtil.extractText(
			LoopMarkdownUtil.interpretTokens(
				PortalUtil.getDefaultCompanyId(), message,
				payloadJSONObject.getJSONArray(
					LoopAssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO),
				false));

		message = StringUtil.shorten(
			message,
			LoopWebConfigurationValues.
				LOOP_EMAIL_NOTIFICATION_SUBJECT_MAX_CHAR_COUNT);

		if (Validator.isNull(message)) {
			Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss z", TimeZone.getTimeZone("GMT"));

			message =
				getSummary(themeDisplay, assetEntrySet, notificationType) +
					StringPool.SPACE +
						format.format(assetEntrySet.getCreateTime());
		}

		return message;
	}

	protected static String getSummary(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet,
			int notificationType)
		throws Exception {

		return themeDisplay.translate(
			LoopUserNotificationEventUtil.getNotificationMessagePattern(
				assetEntrySet.getLevel(), notificationType,
				LoopUserNotificationConstants.DELIVERY_TYPE_EMAIL),
			AssetEntrySetParticipantInfoUtil.getParticipantName(
				assetEntrySet.getCreatorClassNameId(),
				assetEntrySet.getCreatorClassPK()));
	}

	protected static String styleBody(String body) throws Exception {
		String themeEmailCSS = StringUtil.read(
			_CLASS_LOADER.getResourceAsStream(_LOOP_EMAIL_CSS));

		return LoopSassUtil.injectCSS(body, themeEmailCSS);
	}

	private static final String _CAMERA_ICON =
		"/META-INF/resources/images/email_notification_images/camera_icon.jpg";

	private static final ClassLoader _CLASS_LOADER =
		LoopEmailNotificationUtil.class.getClassLoader();

	private static final String _LINK_ICON =
		"/META-INF/resources/images/email_notification_images/link_icon.jpg";

	private static final String _LOOP_EMAIL_CSS =
		"/META-INF/resources/dist/email.css";

	private static final String _LOOP_ICON =
		"/META-INF/resources/images/email_notification_images/loop_icon.jpg";

}