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

package com.liferay.osb.marketplace.social;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;
import com.liferay.portlet.social.model.BaseSocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;

/**
 * @author Douglas Wong
 */
public class MarketplaceActivityInterpreter
	extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected SocialActivityFeedEntry doInterpret(
			SocialActivity activity, ThemeDisplay themeDisplay)
		throws Exception {

		String creatorUserName = getUserName(
			activity.getUserId(), themeDisplay);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), themeDisplay);

		int activityType = activity.getType();

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			activity.getClassPK());

		if ((activityType == MarketplaceActivityKeys.ADD_RATING) ||
			(activityType == MarketplaceActivityKeys.ADD_REVIEW) ||
			(activityType == MarketplaceActivityKeys.UPDATE_REVIEW)) {

			Group group = themeDisplay.getScopeGroup();

			if (appEntry.isDeveloperEntryCorpEntry() && group.isUser() &&
				(group.getClassPK() == appEntry.getUserId()) &&
				(group.getClassPK() != activity.getUserId())) {

				return null;
			}

			receiverUserName = appEntry.getDeveloperEntryName();
		}

		String titlePattern = null;

		int score = 0;

		if (activityType == MarketplaceActivityKeys.ADD_APP_ENTRY) {
			titlePattern = "activity-marketplace-add-app-entry";
		}
		else if (activityType == MarketplaceActivityKeys.ADD_RATING) {
			titlePattern = "activity-marketplace-add-rating";

			RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.getEntry(
				activity.getUserId(), AppEntry.class.getName(),
				activity.getClassPK());

			score = (int)ratingsEntry.getScore();
		}
		else if (activityType == MarketplaceActivityKeys.ADD_REVIEW) {
			titlePattern = "activity-marketplace-add-review";
		}
		else if (activityType ==
					MarketplaceActivityKeys.UDPATE_APP_ENTRY) {

			titlePattern = "activity-marketplace-update-app-entry";
		}
		else if (activityType == MarketplaceActivityKeys.UPDATE_REVIEW) {
			titlePattern = "activity-marketplace-update-review";
		}

		// Link

		StringBundler sb = new StringBundler(3);

		sb.append(
			PortalUtil.getLayoutFullURL(
				OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE));
		sb.append("/-/mp/application/");
		sb.append(activity.getClassPK());

		String link = sb.toString();

		// Title

		String extractedTitle = HtmlUtil.extractText(appEntry.getTitle());

		String entryTitle = wrapLink(link, HtmlUtil.escape(extractedTitle));

		Object[] titleArguments =
			{creatorUserName, receiverUserName, entryTitle, score};

		String title = themeDisplay.translate(titlePattern, titleArguments);

		// Body

		String body = StringPool.BLANK;

		return new SocialActivityFeedEntry(link, title, body);
	}

	private static final String[] _CLASS_NAMES = new String[] {
		AppEntry.class.getName()
	};

}