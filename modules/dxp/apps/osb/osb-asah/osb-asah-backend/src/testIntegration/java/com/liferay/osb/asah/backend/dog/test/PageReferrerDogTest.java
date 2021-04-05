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

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.page.PageReferrerDog;
import com.liferay.osb.asah.backend.model.Interval;
import com.liferay.osb.asah.backend.model.PageReferrerMetric;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gabriel Ibson
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class PageReferrerDogTest {

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

		Assert.assertEquals(4, acquisitionChannels.get("direct"), 0);
		Assert.assertEquals(2, acquisitionChannels.get("paid"), 0);
		Assert.assertEquals(3, acquisitionChannels.get("referral"), 0);

		acquisitionChannels = _pageReferrerDog.getAcquisitionChannels(
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com/home");
					setInterval(Interval.DAY.getKey());
					setRangeKey(30);
				}
			});

		Assert.assertEquals(4, acquisitionChannels.get("direct"), 0);
		Assert.assertEquals(2, acquisitionChannels.get("paid"), 0);
		Assert.assertEquals(4, acquisitionChannels.get("referral"), 0);
		Assert.assertEquals(5, acquisitionChannels.get("social"), 0);
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

		Assert.assertEquals(pageReferrers.toString(), 3, pageReferrers.size());
		Assert.assertEquals(3, pageReferrers.get("www.facebook.com"), 0);
		Assert.assertEquals(6, pageReferrers.get("www.google.com"), 0);
		Assert.assertEquals(1, pageReferrers.get("www.latimes.com"), 0);

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

		Assert.assertEquals(pageReferrers.toString(), 3, pageReferrers.size());
		Assert.assertEquals(3, pageReferrers.get("www.facebook.com"), 0);
		Assert.assertEquals(6, pageReferrers.get("www.google.com"), 0);
		Assert.assertEquals(6, pageReferrers.get("www.latimes.com"), 0);
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

		Assert.assertEquals(
			pageReferrerMetrics.toString(), 2, pageReferrerMetrics.size());

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

		Assert.assertEquals(pageReferrers.toString(), 4, pageReferrers.size());
		Assert.assertEquals(
			3, pageReferrers.get("https://www.facebook.com"), 0);
		Assert.assertEquals(4, pageReferrers.get("https://www.google.com"), 0);
		Assert.assertEquals(
			2, pageReferrers.get("https://www.google.com/test"), 0);
		Assert.assertEquals(1, pageReferrers.get("https://www.latimes.com"), 0);

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

		Assert.assertEquals(pageReferrers.toString(), 4, pageReferrers.size());
		Assert.assertEquals(
			3, pageReferrers.get("https://www.facebook.com"), 0);
		Assert.assertEquals(4, pageReferrers.get("https://www.google.com"), 0);
		Assert.assertEquals(
			2, pageReferrers.get("https://www.google.com/test"), 0);
		Assert.assertEquals(6, pageReferrers.get("https://www.latimes.com"), 0);
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

		Assert.assertEquals(3, socialReferrers.get("facebook"), 0);
		Assert.assertEquals(4, socialReferrers.get("other"), 0);
		Assert.assertEquals(6, socialReferrers.get("twitter"), 0);
	}

	@Autowired
	private PageReferrerDog _pageReferrerDog;

}