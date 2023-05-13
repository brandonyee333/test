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
import com.liferay.osb.asah.backend.dog.PageReferrerDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.PageReferrerMetric;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.repository.CrudBQPageRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gabriel Ibson
 */
public class PageReferrerDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BQSQLResource(resourcePath = "page_referrers_events.sql")
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

		Assertions.assertEquals(3, acquisitionChannels.get("direct"), 0);

		acquisitionChannels = _pageReferrerDog.getAcquisitionChannels(
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com");
					setDataSourceId("1");
					setInterval(Interval.DAY.getKey());
					setRangeKey(7);
				}
			});

		Assertions.assertEquals(3, acquisitionChannels.get("direct"), 0);
	}

	@BQSQLResource(resourcePath = "page_referrers_events.sql")
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
			1, pageReferrers.size(), pageReferrers.toString());

		pageReferrers = _pageReferrerDog.getPageReferrers(
			"referrerHost",
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com");
					setDataSourceId("1");
					setInterval(Interval.DAY.getKey());
					setRangeKey(7);
				}
			},
			10);

		Assertions.assertEquals(
			1, pageReferrers.size(), pageReferrers.toString());
	}

	@BQSQLResource(resourcePath = "page_referrers_events.sql")
	@Disabled
	@RepositoryResource(
		repositoryClass = CrudBQPageRepository.class,
		resourcePath = "osbasahcerebroinfo/pages_info_2.json"
	)
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

	@BQSQLResource(resourcePath = "page_referrers_events.sql")
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
			1, pageReferrers.size(), pageReferrers.toString());

		pageReferrers = _pageReferrerDog.getPageReferrers(
			"referrerCanonicalUrl",
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com");
					setDataSourceId("1");
					setInterval(Interval.DAY.getKey());
					setRangeKey(7);
				}
			},
			10);

		Assertions.assertEquals(
			1, pageReferrers.size(), pageReferrers.toString());
	}

	@BQSQLResource(resourcePath = "page_referrers_events.sql")
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
			1, socialReferrers.size(), socialReferrers.toString());

		socialReferrers = _pageReferrerDog.getSocialPageReferrers(
			new SearchQueryContext() {
				{
					setCanonicalUrl("http://liferay.com");
					setDataSourceId("1");
					setInterval(Interval.DAY.getKey());
					setRangeKey(7);
				}
			});

		Assertions.assertEquals(
			1, socialReferrers.size(), socialReferrers.toString());
	}

	@Autowired
	private PageReferrerDog _pageReferrerDog;

}