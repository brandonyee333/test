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
import com.liferay.osb.asah.backend.model.PageReferrerMetric;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

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
		name = "page-referrers", resourcePath = "page_referrers_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPageReferrerMetrics() {
		List<PageReferrerMetric> pageReferrersMetrics =
			_pageReferrerDog.getPageReferrerMetrics(
				new SearchQueryContext() {
					{
						setCanonicalUrl("http://liferay.com/home");
						setChannelId("1");
						setDataSourceId("438676646604695862");
					}
				});

		Assert.assertEquals(
			pageReferrersMetrics.toString(), 2, pageReferrersMetrics.size());

		PageReferrerMetric pageReferrerMetric = pageReferrersMetrics.get(0);

		DogTestUtil.assertMetric(8, pageReferrerMetric.getAccessMetric());
	}

	@Autowired
	private PageReferrerDog _pageReferrerDog;

}