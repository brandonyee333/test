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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1;

import com.liferay.osb.asah.backend.dog.GeolocationDog;
import com.liferay.osb.asah.backend.dog.HistogramDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.page.PageDog;
import com.liferay.osb.asah.backend.dog.page.PageReferrerDog;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.PageMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.time.LocalDate;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shinn Lok
 * @author André Miranda
 */
@RequestMapping(produces = "application/json", value = "/api/1.0/pages")
@RestController
public class PagesRestController extends BaseRestController {

	@GetMapping("/acquisition-channels")
	public Map<String, Double> getAcquisitionChannels(
		@RequestParam String canonicalURL,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "endDate", required = false)
			LocalDate endLocalDate,
		@RequestParam(defaultValue = "D") String interval,
		@RequestParam(defaultValue = "7") int rangeKey,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "startDate", required = false)
			LocalDate startLocalDate) {

		if (StringUtils.isBlank(canonicalURL)) {
			return Collections.emptyMap();
		}

		return _pageReferrerDog.getAcquisitionChannels(
			new SearchQueryContext() {
				{
					setCanonicalUrl(canonicalURL);
					setIncludeActiveSessions(true);
					setInterval(interval);
					setRangeKey(rangeKey);

					if ((endLocalDate != null) && (startLocalDate != null)) {
						setTimeRange(
							TimeRange.of(endLocalDate, startLocalDate));
					}
				}
			});
	}

	@GetMapping("/geolocations")
	public String getGeolocations(@RequestParam String canonicalURL) {
		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setCanonicalUrl(canonicalURL);

		return String.valueOf(
			new JSONArray(
				_geolocationDog.getGeolocationMetrics(
					PageMetricType.VIEWS, searchQueryContext)));
	}

	@GetMapping("/page-referrer-hosts")
	public Map<String, Double> getPageReferrerHosts(
		@RequestParam String canonicalURL,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "endDate", required = false)
			LocalDate endLocalDate,
		@RequestParam(defaultValue = "D") String interval,
		@RequestParam(defaultValue = "7") int rangeKey,
		@RequestParam(defaultValue = "30") int size,
		@RequestParam(name = "startDate", required = false)
			LocalDate startLocalDate) {

		if (StringUtils.isBlank(canonicalURL)) {
			return Collections.emptyMap();
		}

		return _pageReferrerDog.getPageReferrers(
			"referrerHost",
			new SearchQueryContext() {
				{
					setCanonicalUrl(canonicalURL);
					setInterval(interval);
					setRangeKey(rangeKey);

					if ((endLocalDate != null) && (startLocalDate != null)) {
						setTimeRange(
							TimeRange.of(endLocalDate, startLocalDate));
					}
				}
			},
			size);
	}

	@GetMapping("/page-referrers")
	public Map<String, Double> getPageReferrers(
		@RequestParam String canonicalURL,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "endDate", required = false)
			LocalDate endLocalDate,
		@RequestParam(defaultValue = "D") String interval,
		@RequestParam(defaultValue = "7") int rangeKey,
		@RequestParam(defaultValue = "30") int size,
		@RequestParam(name = "startDate", required = false)
			LocalDate startLocalDate) {

		if (StringUtils.isBlank(canonicalURL)) {
			return Collections.emptyMap();
		}

		return _pageReferrerDog.getPageReferrers(
			"referrerCanonicalUrl",
			new SearchQueryContext() {
				{
					setCanonicalUrl(canonicalURL);
					setInterval(interval);
					setRangeKey(rangeKey);

					if ((endLocalDate != null) && (startLocalDate != null)) {
						setTimeRange(
							TimeRange.of(endLocalDate, startLocalDate));
					}
				}
			},
			size);
	}

	@GetMapping("/read-count")
	public String getReadCount(@RequestParam String canonicalURL) {
		return String.valueOf(
			_pageDog.getMetricValue(
				Optional.of(canonicalURL), Optional.empty(),
				PageMetricType.READS, Optional.empty(), Optional.empty()));
	}

	@GetMapping("/read-counts")
	public String getReadCounts(
		@RequestParam String canonicalURL,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "endDate")
			LocalDate endLocalDate,
		@RequestParam String interval,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "startDate")
			LocalDate startLocalDate) {

		return _getHistogramMetrics(
			canonicalURL, endLocalDate, interval, PageMetricType.READS,
			startLocalDate);
	}

	@GetMapping("/social-page-referrers")
	public Map<String, Double> getSocialPageReferrers(
		@RequestParam String canonicalURL,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "endDate", required = false)
			LocalDate endLocalDate,
		@RequestParam(defaultValue = "D") String interval,
		@RequestParam(defaultValue = "7") int rangeKey,
		@RequestParam(name = "startDate", required = false)
			LocalDate startLocalDate) {

		if (StringUtils.isBlank(canonicalURL)) {
			return Collections.emptyMap();
		}

		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setCanonicalUrl(canonicalURL);
		searchQueryContext.setInterval(interval);
		searchQueryContext.setRangeKey(rangeKey);

		return _pageReferrerDog.getSocialPageReferrers(
			new SearchQueryContext() {
				{
					setCanonicalUrl(canonicalURL);
					setInterval(interval);
					setRangeKey(rangeKey);

					if ((endLocalDate != null) && (startLocalDate != null)) {
						setTimeRange(
							TimeRange.of(endLocalDate, startLocalDate));
					}
				}
			});
	}

	@GetMapping("/view-count")
	public String getViewCount(@RequestParam String canonicalURL) {
		return String.valueOf(
			_pageDog.getMetricValue(
				Optional.of(canonicalURL), Optional.empty(),
				PageMetricType.VIEWS, Optional.empty(), Optional.empty()));
	}

	@GetMapping("/view-counts")
	public String getViewCounts(
		@RequestParam String canonicalURL,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "endDate")
			LocalDate endLocalDate,
		@RequestParam String interval,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "startDate")
			LocalDate startLocalDate) {

		return _getHistogramMetrics(
			canonicalURL, endLocalDate, interval, PageMetricType.VIEWS,
			startLocalDate);
	}

	private String _getHistogramMetrics(
		String canonicalUrl, LocalDate endLocalDate, String interval,
		PageMetricType pageMetricType, LocalDate startLocalDate) {

		HistogramMetricBag histogramMetricBag =
			_histogramDog.getHistogramMetricBag(
				true, pageMetricType,
				new SearchQueryContext() {
					{
						setCanonicalUrl(canonicalUrl);
						setIncludeActiveSessions(true);
						setInterval(interval);
						setTimeRange(
							TimeRange.of(endLocalDate, startLocalDate));
					}
				});

		return JSONUtil.put(
			"histogram", histogramMetricBag.getMetrics()
		).put(
			"value",
			_pageDog.getMetricValue(
				Optional.of(canonicalUrl),
				Optional.of(startLocalDate.toString()), pageMetricType,
				Optional.of(endLocalDate.toString()), Optional.empty())
		).toString();
	}

	@Autowired
	private GeolocationDog _geolocationDog;

	@Autowired
	private HistogramDog _histogramDog;

	@Autowired
	private PageDog _pageDog;

	@Autowired
	private PageReferrerDog _pageReferrerDog;

}