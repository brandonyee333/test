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
import com.liferay.osb.asah.test.util.annotation.SQLResource;
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

	@SQLResource(resourcePath = "page_referrers_events.sql")
	@Test
	public void testAcquisitionChannels() {
		Map<String, Double> acquisitionChannels =
			_pageReferrerDog.getAcquisitionChannels(
				new SearchQueryContext() {
					{
						setCanonicalUrl("http://liferay.com");
						setChannelId("1");
						setDataSourceId("1");
						setInterval(Interval.DAY.getKey());
						setRangeKey(7);
					}
				});

		Assertions.assertEquals(1, acquisitionChannels.get("direct"), 0);
	}

	@SQLResource(resourcePath = "page_referrers_events.sql")
	@Test
	public void testPageReferrerHosts() {
		Map<String, Double> pageReferrers = _pageReferrerDog.getPageReferrers(
			"referrerHost",
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com");
					setChannelId("1");
					setDataSourceId("1");
					setInterval(Interval.DAY.getKey());
					setRangeKey(7);
				}
			},
			10);

		Assertions.assertEquals(
			0, pageReferrers.size(), pageReferrers.toString());
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@SQLResource(resourcePath = "page_referrers_events.sql")
	@Test
	public void testPageReferrerMetrics() {
		List<PageReferrerMetric> pageReferrerMetrics =
			_pageReferrerDog.getPageReferrerMetrics(
				new SearchQueryContext() {
					{
						setCanonicalUrl("http://liferay.com");
						setChannelId("1");
						setDataSourceId("1");
					}
				});

		Assertions.assertEquals(
			2, pageReferrerMetrics.size(), pageReferrerMetrics.toString());

		PageReferrerMetric pageReferrerMetric = pageReferrerMetrics.get(0);

		DogTestUtil.assertMetric(2, pageReferrerMetric.getAccessMetric());
	}

	@SQLResource(resourcePath = "page_referrers_events.sql")
	@Test
	public void testPageReferrers() {
		Map<String, Double> pageReferrers = _pageReferrerDog.getPageReferrers(
			"referrerCanonicalUrl",
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com");
					setChannelId("1");
					setDataSourceId("1");
					setInterval(Interval.DAY.getKey());
					setRangeKey(7);
				}
			},
			10);

		Assertions.assertEquals(
			0, pageReferrers.size(), pageReferrers.toString());
	}

	@SQLResource(resourcePath = "page_referrers_events.sql")
	@Test
	public void testSocialPageReferrers() {
		Map<String, Double> socialReferrers =
			_pageReferrerDog.getSocialPageReferrers(
				new SearchQueryContext() {
					{
						setCanonicalUrl("http://liferay.com");
						setChannelId("1");
						setDataSourceId("1");
						setInterval(Interval.DAY.getKey());
						setRangeKey(7);
					}
				});

		Assertions.assertEquals(
			0, socialReferrers.size(), socialReferrers.toString());
	}

	@Autowired
	private PageReferrerDog _pageReferrerDog;

}