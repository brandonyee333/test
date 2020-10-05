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

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoPreferenceDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.Preference;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNanite;
import com.liferay.osb.asah.stream.curator.model.page.Page;
import com.liferay.osb.asah.stream.curator.model.page.PageScroll;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	public void init() {
		super.init();
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

			_setPageEngagementScore(oldPage);

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

		_setPageEngagementScore(page);
		_setPageSearchTerm(page);
		_setPageTitle(analyticsEvent, page);
	}

	private double _computeEngagementScore(Page page) {
		double interactionScore = _computeInteractionScore(page);
		double viewScore = _computeViewScore(page);

		return ((interactionScore * 4) + viewScore) / 5.0;
	}

	private double _computeFormScore(Page page) {
		if (page.getFormSubmissions() >= 1) {
			return 1.0;
		}

		return 0.0;
	}

	private double _computeInteractionScore(Page page) {
		double formScore = _computeFormScore(page);
		double readTimeScore = _computeReadTimeScore(page);

		Optional<PageScroll> pageScrollOptional = _getMaxPageScrollOptional(
			page.getPageScrolls());

		double scrollDepthScore = _computeScrollDepthScore(pageScrollOptional);
		double scrollSpeedScore = _computeScrollSpeedScore(
			page, pageScrollOptional);

		if (formScore == -1.0) {
			return (scrollSpeedScore + scrollDepthScore + readTimeScore) / 3.0;
		}

		double score =
			scrollSpeedScore + scrollDepthScore + readTimeScore +
				(3 * formScore);

		return score / 6.0;
	}

	private long _computeReadTime(Date firstEventDate, Date lastEventDate) {
		if ((firstEventDate != null) && (lastEventDate != null)) {
			return lastEventDate.getTime() - firstEventDate.getTime();
		}

		return 0;
	}

	private double _computeReadTimeScore(Page page) {
		double readTime = _computeReadTime(
			page.getFirstEventDate(), page.getLastEventDate());

		double score = readTime / _AVERAGE_READ_TIME;

		if (score > 1.0) {
			return 1.0;
		}
		else if (score < 0.0) {
			return 0.0;
		}

		return score;
	}

	private double _computeScrollDepthScore(
		Optional<PageScroll> maxPageScrollOptional) {

		int maxScrollDepth = _getMaxScrollDepth(maxPageScrollOptional);

		return maxScrollDepth / 100.0;
	}

	private double _computeScrollSpeedScore(
		Page page, Optional<PageScroll> maxPageScrollOptional) {

		int maxScrollDepth = _getMaxScrollDepth(maxPageScrollOptional);

		if (!_averageScrollTime.containsKey(maxScrollDepth)) {
			return 0.0;
		}

		long maxScrollTime = _getMaxScrollTime(
			page.getFirstEventDate(), maxPageScrollOptional);

		Double score = maxScrollTime / _averageScrollTime.get(maxScrollDepth);

		if (score > 1.0) {
			return 1.0;
		}
		else if (score < 0.0) {
			return 0.0;
		}

		return score;
	}

	private double _computeViewScore(Page page) {
		long views = page.getDirectAccess() + page.getIndirectAccess();

		double score = views / _AVERAGE_VIEWS;

		if (score >= 1.0) {
			return 1.0;
		}

		return 0.0;
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

	private Optional<PageScroll> _getMaxPageScrollOptional(
		Set<PageScroll> pageScrolls) {

		Stream<PageScroll> stream = pageScrolls.stream();

		return stream.max(Comparator.comparing(PageScroll::getDepth));
	}

	private int _getMaxScrollDepth(Optional<PageScroll> maxPageScrollOptional) {
		return maxPageScrollOptional.map(
			PageScroll::getDepth
		).orElse(
			0
		);
	}

	private long _getMaxScrollTime(
		Date firstEventDate, Optional<PageScroll> maxPageScrollOptional) {

		return maxPageScrollOptional.map(
			PageScroll::getEventDate
		).map(
			pageScrollEventDate -> _computeReadTime(
				firstEventDate, pageScrollEventDate)
		).orElse(
			0L
		);
	}

	private Set<String> _getSearchQueryStrings() {
		Preference preference = _faroInfoPreferenceDog.getPreference(
			"search-query-strings");

		Set<String> searchQueryStrings = _searchQueryStringsMap.get(
			preference.getValue());

		if (searchQueryStrings != null) {
			return searchQueryStrings;
		}

		searchQueryStrings = JSONUtil.toStringSet(
			new JSONArray(preference.getValue()));

		searchQueryStrings.add("q");

		_searchQueryStringsMap.clear();

		_searchQueryStringsMap.put(preference.getValue(), searchQueryStrings);

		return searchQueryStrings;
	}

	private Page _setPageEngagementScore(Page page) {
		double enagementScore = _computeEngagementScore(page);

		page.setEngagementScore(enagementScore);

		return page;
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
		catch (Exception e) {
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

	private static final double _AVERAGE_READ_TIME = 600000.0;

	private static final double _AVERAGE_VIEWS = 30.0;

	private static final Log _log = LogFactory.getLog(PageNanite.class);

	private static final Map<Integer, Double> _averageScrollTime =
		new HashMap<Integer, Double>() {
			{
				put(25, 15000.0);
				put(50, 20000.0);
				put(75, 25000.0);
				put(100, 30000.0);
			}
		};

	@Autowired
	private FaroInfoPreferenceDog _faroInfoPreferenceDog;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_PAGE)
	private MessageSubscriber _messageSubscriber;

	private final Map<String, Set<String>> _searchQueryStringsMap =
		new HashMap<>();

}