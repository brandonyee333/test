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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.page.PageReferrerDog;
import com.liferay.osb.asah.backend.model.PageReferrerMetric;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gabriel Ibson
 */
public class PageReferrerDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "page-referrers",
		resourcePath = "page_referrers_acquisitions_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testAcquisitionChannels() {
		Map<String, Double> acquisitionChannels =
			_pageReferrerDog.getAcquisitionChannels(
				new SearchQueryContext() {
					{
						setCanonicalUrl("http://liferay.com/home");
						setInterval(Interval.DAY.getKey());
						setRangeKey(7);
					}
				});

		Assertions.assertEquals(4, acquisitionChannels.get("direct"), 0);
		Assertions.assertEquals(2, acquisitionChannels.get("paid"), 0);
		Assertions.assertEquals(3, acquisitionChannels.get("referral"), 0);

		acquisitionChannels = _pageReferrerDog.getAcquisitionChannels(
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com/home");
					setInterval(Interval.DAY.getKey());
					setRangeKey(30);
				}
			});

		Assertions.assertEquals(4, acquisitionChannels.get("direct"), 0);
		Assertions.assertEquals(2, acquisitionChannels.get("paid"), 0);
		Assertions.assertEquals(4, acquisitionChannels.get("referral"), 0);
		Assertions.assertEquals(5, acquisitionChannels.get("social"), 0);
	}

	@ElasticsearchIndex(
		name = "page-referrers", resourcePath = "page_referrers_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPageReferrerHosts() {
		Map<String, Double> pageReferrers = _pageReferrerDog.getPageReferrers(
			"referrerHost",
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com/home");
					setInterval(Interval.DAY.getKey());
					setRangeKey(7);
				}
			},
			10);

		Assertions.assertEquals(
			3, pageReferrers.size(), pageReferrers.toString());
		Assertions.assertEquals(3, pageReferrers.get("www.facebook.com"), 0);
		Assertions.assertEquals(6, pageReferrers.get("www.google.com"), 0);
		Assertions.assertEquals(1, pageReferrers.get("www.latimes.com"), 0);

		pageReferrers = _pageReferrerDog.getPageReferrers(
			"referrerHost",
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com/home");
					setInterval(Interval.DAY.getKey());
					setRangeKey(30);
				}
			},
			10);

		Assertions.assertEquals(
			3, pageReferrers.size(), pageReferrers.toString());
		Assertions.assertEquals(3, pageReferrers.get("www.facebook.com"), 0);
		Assertions.assertEquals(6, pageReferrers.get("www.google.com"), 0);
		Assertions.assertEquals(6, pageReferrers.get("www.latimes.com"), 0);
	}

	@ElasticsearchIndex(
		name = "page-referrers", resourcePath = "page_referrers_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPageReferrerMetrics() {
		List<PageReferrerMetric> pageReferrerMetrics =
			_pageReferrerDog.getPageReferrerMetrics(
				new SearchQueryContext() {
					{
						setCanonicalUrl("http://liferay.com/home");
						setChannelId("1");
						setDataSourceId("438676646604695862");
					}
				});

		Assertions.assertEquals(
			2, pageReferrerMetrics.size(), pageReferrerMetrics.toString());

		PageReferrerMetric pageReferrerMetric = pageReferrerMetrics.get(0);

		DogTestUtil.assertMetric(8, pageReferrerMetric.getAccessMetric());
	}

	@ElasticsearchIndex(
		name = "page-referrers", resourcePath = "page_referrers_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPageReferrers() {
		Map<String, Double> pageReferrers = _pageReferrerDog.getPageReferrers(
			"referrerCanonicalUrl",
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com/home");
					setInterval(Interval.DAY.getKey());
					setRangeKey(7);
				}
			},
			10);

		Assertions.assertEquals(
			4, pageReferrers.size(), pageReferrers.toString());
		Assertions.assertEquals(
			3, pageReferrers.get("https://www.facebook.com"), 0);
		Assertions.assertEquals(
			4, pageReferrers.get("https://www.google.com"), 0);
		Assertions.assertEquals(
			2, pageReferrers.get("https://www.google.com/test"), 0);
		Assertions.assertEquals(
			1, pageReferrers.get("https://www.latimes.com"), 0);

		pageReferrers = _pageReferrerDog.getPageReferrers(
			"referrerCanonicalUrl",
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com/home");
					setInterval(Interval.DAY.getKey());
					setRangeKey(30);
				}
			},
			10);

		Assertions.assertEquals(
			4, pageReferrers.size(), pageReferrers.toString());
		Assertions.assertEquals(
			3, pageReferrers.get("https://www.facebook.com"), 0);
		Assertions.assertEquals(
			4, pageReferrers.get("https://www.google.com"), 0);
		Assertions.assertEquals(
			2, pageReferrers.get("https://www.google.com/test"), 0);
		Assertions.assertEquals(
			6, pageReferrers.get("https://www.latimes.com"), 0);
	}

	@ElasticsearchIndex(
		name = "page-referrers",
		resourcePath = "page_referrers_social_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testSocialPageReferrers() {
		Map<String, Double> socialReferrers =
			_pageReferrerDog.getSocialPageReferrers(
				new SearchQueryContext() {
					{
						setCanonicalUrl("http://liferay.com/home");
						setInterval(Interval.DAY.getKey());
						setRangeKey(7);
					}
				});

		Assertions.assertEquals(3, socialReferrers.get("facebook"), 0);
		Assertions.assertEquals(4, socialReferrers.get("other"), 0);
		Assertions.assertEquals(6, socialReferrers.get("twitter"), 0);
	}

	@Autowired
	private PageReferrerDog _pageReferrerDog;

}