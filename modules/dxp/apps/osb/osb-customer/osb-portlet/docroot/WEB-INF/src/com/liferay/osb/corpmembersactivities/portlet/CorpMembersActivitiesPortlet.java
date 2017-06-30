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

package com.liferay.osb.corpmembersactivities.portlet;

import com.liferay.compat.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.kernel.util.WebKeys;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.corpmembersactivities.util.CorpMembersActivitiesUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.Portal;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.service.SocialActivityInterpreterLocalServiceUtil;
import com.liferay.util.RSSUtil;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.io.FeedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Rachael Koestartyo
 * @author Peter Shin
 */
public class CorpMembersActivitiesPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = GetterUtil.getString(
				resourceRequest.getResourceID());

			if (resourceID.equals("rss")) {
				serveRSS(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected String exportToRSS(
			String name, String description, String type, double version,
			String feedURL, List<SocialActivity> activities,
			ThemeDisplay themeDisplay)
		throws Exception {

		SyndFeed syndFeed = new SyndFeedImpl();

		syndFeed.setDescription(description);

		List<SyndEntry> syndEntries = new ArrayList<SyndEntry>();

		syndFeed.setEntries(syndEntries);

		for (SocialActivity activity : activities) {
			SocialActivityFeedEntry activityFeedEntry =
				SocialActivityInterpreterLocalServiceUtil.interpret(
					activity, themeDisplay);

			if (activityFeedEntry == null) {
				continue;
			}

			SyndContent syndContent = new SyndContentImpl();

			syndContent.setType(RSSUtil.ENTRY_TYPE_DEFAULT);
			syndContent.setValue(activityFeedEntry.getBody());

			SyndEntry syndEntry = new SyndEntryImpl();

			syndEntry.setDescription(syndContent);

			if (Validator.isNotNull(activityFeedEntry.getLink())) {
				syndEntry.setLink(activityFeedEntry.getLink());
			}

			syndEntry.setPublishedDate(new Date(activity.getCreateDate()));
			syndEntry.setTitle(
				HtmlUtil.extractText(activityFeedEntry.getTitle()));

			syndEntries.add(syndEntry);
		}

		syndFeed.setFeedType(RSSUtil.getFeedType(type, version));

		List<SyndLink> syndLinks = new ArrayList<SyndLink>();

		syndFeed.setLinks(syndLinks);

		SyndLink selfSyndLink = new SyndLinkImpl();

		syndLinks.add(selfSyndLink);

		selfSyndLink.setHref(feedURL);
		selfSyndLink.setRel("self");

		syndFeed.setPublishedDate(new Date());
		syndFeed.setTitle(name);
		syndFeed.setUri(feedURL);

		String rss = StringPool.BLANK;

		try {
			rss = RSSUtil.export(syndFeed);
		}
		catch (FeedException fe) {
			throw new SystemException(fe);
		}

		return rss;
	}

	protected void serveRSS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletPreferences portletPreferences =
			resourceRequest.getPreferences();

		int max = GetterUtil.getInteger(
			portletPreferences.getValue("max", "10"));

		Group scopeGroup = themeDisplay.getScopeGroup();

		List<SocialActivity> activities =
			CorpMembersActivitiesUtil.getSocialActivities(scopeGroup, 0, max);

		String feedURL =
			PortalUtil.getLayoutFullURL(themeDisplay) +
				Portal.FRIENDLY_URL_SEPARATOR + "corp_members_activities/rss";
		String title = scopeGroup.getDescriptiveName(themeDisplay.getLocale());

		String rss = exportToRSS(
			title, title, RSSUtil.TYPE_DEFAULT, RSSUtil.VERSION_DEFAULT,
			feedURL, activities, themeDisplay);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, null,
			rss.getBytes(StringPool.UTF8), ContentTypes.TEXT_XML_UTF8);
	}

}