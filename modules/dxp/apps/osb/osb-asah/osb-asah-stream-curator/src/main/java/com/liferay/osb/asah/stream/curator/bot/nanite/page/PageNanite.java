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

package com.liferay.osb.asah.stream.curator.bot.nanite.page;

import com.liferay.osb.asah.common.dog.PreferenceDog;
import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.page.Page;
import com.liferay.osb.asah.stream.curator.model.page.PageScroll;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Inácio Nery
 */
@Component
public class PageNanite extends BaseNanite<Page> {

	@Override
	public String getCollectionName() {
		return "pages";
	}

	@Override
	protected BinaryOperator<Page> getBinaryOperator() {
		return (Page oldPage, Page newPage) -> {
			mergeModels(oldPage, newPage);

			oldPage.addCTAClicks(newPage.getCTAClicks());
			oldPage.addFormSubmission(newPage.getFormSubmissions());

			NavigableSet<Date> directAccessDates =
				newPage.getDirectAccessDates();

			oldPage.addDirectAccessDates(directAccessDates);

			directAccessDates = oldPage.getDirectAccessDates();

			NavigableSet<Date> indirectAccessDates =
				newPage.getIndirectAccessDates();

			oldPage.addIndirectAccessDates(indirectAccessDates);

			indirectAccessDates = oldPage.getIndirectAccessDates();

			oldPage.addInteractionDates(newPage.getInteractionDates());
			oldPage.addPageScrolls(newPage.getPageScrolls());
			oldPage.addReads(newPage.getReads());
			oldPage.setDirectAccess(directAccessDates.size());
			oldPage.setIndirectAccess(indirectAccessDates.size());

			long views = directAccessDates.size() + indirectAccessDates.size();

			oldPage.setViews(views);

			return oldPage;
		};
	}

	@Override
	protected Predicate<Page> getFilterPredicate() {
		return page -> StringUtils.isNotBlank(page.getURL());
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected MessageSubscriber getMessageSubscriber() {
		return _messageSubscriber;
	}

	@Override
	protected Supplier<Page> getModelSupplier() {
		return Page::new;
	}

	@Override
	protected Function<Page, String> getPrimaryKeyGeneratorFunction() {
		return page -> digest(
			page.getChannelId(), page.getEventDate(), page.getURL(),
			page.getUserId(), page.getVariantId());
	}

	@Override
	protected void setModelCustomProperties(
		AnalyticsEvent analyticsEvent, Page page) {

		if (Objects.equals(analyticsEvent.getApplicationId(), "Form") &&
			Objects.equals(analyticsEvent.getEventId(), "formSubmitted")) {

			page.addFormSubmission(1);

			page.addInteractionDate(analyticsEvent.getEventDate());
		}
		else if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
				 Objects.equals(analyticsEvent.getEventId(), "ctaClicked")) {

			page.addCTAClicks(1);

			page.addInteractionDate(analyticsEvent.getEventDate());
		}
		else if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
				 Objects.equals(
					 analyticsEvent.getEventId(), "pageDepthReached")) {

			int depth = _getDepth(analyticsEvent.getEventProperties());

			page.addPageScroll(
				new PageScroll(depth, analyticsEvent.getEventDate()));

			page.addInteractionDate(analyticsEvent.getEventDate());
		}
		else if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
				 Objects.equals(analyticsEvent.getEventId(), "pageRead")) {

			page.addReads(1);
		}
		else if (Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
				 Objects.equals(analyticsEvent.getEventId(), "pageViewed")) {

			String referrer = MapUtil.getString(
				analyticsEvent.getContext(), "referrer", "");

			if (Objects.equals(referrer, "")) {
				page.addDirectAccessDate(analyticsEvent.getEventDate());
				page.setDirectAccess(1);
			}
			else {
				page.addIndirectAccessDate(analyticsEvent.getEventDate());
				page.setIndirectAccess(1);
			}

			page.setBounce(0);
			page.setEntrances(0);
			page.setExits(0);
			page.setViews(1);
		}
		else {
			page.addInteractionDate(analyticsEvent.getEventDate());
		}

		page.setFirstEventDate(analyticsEvent.getEventDate());

		_setPageSearchTerm(page);
		_setPageTitle(analyticsEvent, page);
	}

	private int _getDepth(Map<String, String> eventProperties) {
		return Optional.ofNullable(
			eventProperties.get("depth")
		).map(
			Integer::valueOf
		).orElse(
			0
		);
	}

	private Set<String> _getSearchQueryStrings() {
		Preference preference = _preferenceDog.getPreference(
			"search-query-strings");

		Set<String> searchQueryStrings = new HashSet<>();

		String preferenceValue = preference.getValue();

		if (!StringUtil.isNull(preferenceValue)) {
			if (_searchQueryStringsMap.containsKey(preferenceValue)) {
				return _searchQueryStringsMap.get(preferenceValue);
			}

			searchQueryStrings = JSONUtil.toStringSet(
				new JSONArray(preferenceValue));
		}

		searchQueryStrings.add("q");

		_searchQueryStringsMap.clear();

		_searchQueryStringsMap.put(preference.getValue(), searchQueryStrings);

		return searchQueryStrings;
	}

	private void _setPageSearchTerm(Page page) {
		try {
			String url = page.getURL();

			if (url.contains("translate.googleusercontent.com") ||
				url.contains("webcache.googleusercontent.com")) {

				url = url.substring(
					url.indexOf("http", url.indexOf("http") + 1));
			}

			UriComponentsBuilder uriComponentsBuilder =
				UriComponentsBuilder.fromHttpUrl(url);

			UriComponents uriComponents = uriComponentsBuilder.build();

			MultiValueMap<String, String> parameters =
				uriComponents.getQueryParams();

			Set<String> searchQueryStrings = _getSearchQueryStrings();

			for (String searchQueryString : searchQueryStrings) {
				String searchTerm = parameters.getFirst(searchQueryString);

				if (searchTerm == null) {
					continue;
				}

				page.setSearchTerm(searchTerm);
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to add search term for page " + page.getId());
			}
		}
	}

	private void _setPageTitle(AnalyticsEvent analyticsEvent, Page page) {
		String title = MapUtil.getString(analyticsEvent.getContext(), "title");

		if (StringUtils.isNotEmpty(title)) {
			page.setTitle(title);
		}
	}

	private static final Log _log = LogFactory.getLog(PageNanite.class);

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_PAGE)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private PreferenceDog _preferenceDog;

	private final Map<String, Set<String>> _searchQueryStringsMap =
		new HashMap<>();

}