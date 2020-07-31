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

package com.liferay.osb.asah.common.model;

import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Leslie Wong
 */
public class AnalyticsEvents {

	public AnalyticsEvents() {
		_interactionsCount = 0;
		_pageViewsCount = 0;
	}

	public AnalyticsEvents(List<AnalyticsEvent> analyticsEvents) {
		addAnalyticsEventsList(analyticsEvents);
	}

	public void addAnalyticsEventsList(
		List<AnalyticsEvent> analyticsEventsList) {

		_analyticsEvents.addAll(analyticsEventsList);

		for (AnalyticsEvent analyticsEvent : analyticsEventsList) {
			if (!_nonInteractionEvents.contains(analyticsEvent.getEventId())) {
				_interactionsCount++;
			}

			if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
				Objects.equals(analyticsEvent.getEventId(), "pageViewed")) {

				_pageViewsCount++;
			}

			Map<String, Object> context = analyticsEvent.getContext();

			String referrer = MapUtil.getString(context, "referrer");

			if (!StringUtil.isNull(referrer)) {
				_referrers.add(referrer);
			}

			String url = MapUtil.getString(context, "url");

			if (!StringUtil.isNull(url)) {
				_urls.add(url);
			}
		}
	}

	public List<AnalyticsEvent> getAnalyticsEventsList() {
		return _analyticsEvents;
	}

	public AnalyticsEvent getFirstAnalyticsEvent() {
		return _analyticsEvents.get(0);
	}

	public long getInteractionsCount() {
		return _interactionsCount;
	}

	public AnalyticsEvent getLastAnalyticsEvent() {
		return _analyticsEvents.get(_analyticsEvents.size() - 1);
	}

	public long getPageViewsCount() {
		return _pageViewsCount;
	}

	public Set<String> getReferrers() {
		return _referrers;
	}

	public int getSize() {
		return _analyticsEvents.size();
	}

	public Set<String> getUrls() {
		return _urls;
	}

	private static final Set<String> _nonInteractionEvents =
		new HashSet<String>() {
			{
				add("blogViewed");
				add("documentPreviewed");
				add("formViewed");
				add("pageLoaded");
				add("pageUnloaded");
				add("pageViewed");
				add("webContentViewed");
			}
		};

	private final List<AnalyticsEvent> _analyticsEvents = new ArrayList<>();
	private long _interactionsCount;
	private long _pageViewsCount;
	private final Set<String> _referrers = new HashSet<>();
	private final Set<String> _urls = new HashSet<>();

}