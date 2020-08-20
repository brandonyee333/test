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

import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.BlogMetric;
import com.liferay.osb.asah.backend.model.BlogMetricType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.backend.model.PageMetricType;
import com.liferay.osb.asah.backend.model.SiteMetric;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Lino Alves
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class MetricDogTest {

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "asset_metric_blog_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testBlogMetricShouldContainURL() {
		AssetMetric assetMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"1", AssetType.BLOG, null, TimeRange.LAST_7_DAYS, null));

		Assert.assertNotNull(assetMetric);
		Assert.assertEquals("1", assetMetric.getAssetId());

		List<String> urls = assetMetric.getURLs();

		Assert.assertEquals(urls.toString(), 1, urls.size());
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "asset_metric_blog_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testBlogMetricShouldReturnEmptyListIfNoURLsAreFetched() {
		AssetMetric assetMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"1", AssetType.BLOG, null, TimeRange.LAST_24_HOURS, null));

		Assert.assertNotNull(assetMetric);
		Assert.assertEquals("1", assetMetric.getAssetId());

		List<String> urls = assetMetric.getURLs();

		Assert.assertTrue(urls.isEmpty());
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "asset_metric_blog_average_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testBlogRatingAverage() {
		BlogMetric blogMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"1", AssetType.BLOG, null, TimeRange.LAST_24_HOURS, null),
			new HashSet<String>() {
				{
					add(BlogMetricType.RATINGS.getName());
				}
			});

		Assert.assertNotNull(blogMetric);

		Metric ratingsMetric = blogMetric.getRatingsMetric();

		Assert.assertEquals(0.8, ratingsMetric.getValue(), 0.01);
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "asset_metric_blog_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testBlogRatingMetricShouldNotBeNegative() {
		BlogMetric blogMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"2", AssetType.BLOG, null, TimeRange.LAST_24_HOURS, null),
			new HashSet<String>() {
				{
					add(BlogMetricType.RATINGS.getName());
				}
			});

		Assert.assertNotNull(blogMetric);

		Metric ratingsMetric = blogMetric.getRatingsMetric();

		Assert.assertEquals(0, ratingsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testCTAClicksMetrics() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.CLICK_THROUGH_PROBABILITY.getName());
					add(PageMetricType.CLICK_THROUGH_RATE.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric ctpMetric = pageMetric.getCTPMetric();

		Assert.assertEquals(2, ctpMetric.getValue(), 0);

		Metric ctrMetric = pageMetric.getCTRMetric();

		Assert.assertEquals(3, ctrMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "custom-assets", resourcePath = "custom_assets_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testCustomAssetDefaultMetric() {
		String assetId =
			"e131fabc648f00a7ccb6601acf6bfa831ee195d84126ca2f90eae1d4e9d863a9";

		AssetMetric assetMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				assetId, AssetType.CUSTOM, null, TimeRange.LAST_24_HOURS,
				null));

		Assert.assertNotNull(assetMetric);

		Assert.assertEquals(assetId, assetMetric.getAssetId());

		Metric metric = assetMetric.getDefaultMetric();

		Assert.assertEquals(7, metric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricLast7Days() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(9, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricLast24Hours() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_24_HOURS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(0, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricLast28Days() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_28_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(11, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricLast30Days() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_30_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(11, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricLast90Days() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_90_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(14, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricLast180Days() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_180_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(15, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricLastCustomRange() {
		LocalDateTime localDateTime = LocalDateTime.now();

		LocalDateTime endLocalDateTime = localDateTime.minusDays(20);

		LocalDateTime startLocalDateTime = endLocalDateTime.minusDays(180);

		TimeRange timeRange = TimeRange.of(
			endLocalDateTime, startLocalDateTime);

		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(null, AssetType.PAGE, null, timeRange, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(6, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricLastYear() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_YEAR, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(17, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetAssetMetricYesterday() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.YESTERDAY, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(6, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "journals", resourcePath = "asset_metric_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testJournalDefaultMetric() {
		AssetMetric assetMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"1", AssetType.JOURNAL, null, TimeRange.LAST_7_DAYS, null));

		Assert.assertNotNull(assetMetric);

		Assert.assertEquals("1", assetMetric.getAssetId());

		Metric metric = assetMetric.getDefaultMetric();

		Assert.assertEquals(2, metric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "journals", resourcePath = "asset_metric_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testJournalMetrics() {
		Set<String> set = new HashSet<>();

		Collections.addAll(set, "1", "2", "3");

		List<AssetMetric> assetMetrics = _metricDog.getAssetMetrics(
			set,
			_createSearchQuery(
				null, AssetType.JOURNAL, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(JournalMetricType.VIEWS.getName());
				}
			},
			10, null, 0);

		Assert.assertEquals(assetMetrics.toString(), 3, assetMetrics.size());

		for (AssetMetric assetMetric : assetMetrics) {
			Assert.assertTrue(set.contains(assetMetric.getAssetId()));
		}
	}

	@ElasticsearchIndex(
		name = "journals", resourcePath = "asset_metric_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testJournalMetricsCount() {
		int assetMetricsCount = _metricDog.getAssetMetricsCount(
			_createSearchQuery(
				null, AssetType.JOURNAL, null, TimeRange.LAST_7_DAYS, null));

		Assert.assertEquals(4, assetMetricsCount);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPageAssetIdsFilter() {
		Set<String> assetIds = Collections.singleton(
			"http://192.168.108.90:8080/");

		List<PageMetric> pageMetrics = _metricDog.getAssetMetrics(
			assetIds, new SearchQueryContext(),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			},
			1000, null, 0);

		Assert.assertNotNull(pageMetrics);

		Assert.assertEquals(1, pageMetrics.size(), 0);

		PageMetric pageMetric = pageMetrics.get(0);

		Assert.assertTrue(assetIds.contains(pageMetric.getAssetId()));

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(6, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPagesExperimentFilter() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, "10", TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(3, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPagesMaxScrollDepthMetric() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.MAX_SCROLL_DEPTH.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric maxScrollDepthMetric = pageMetric.getMaxScrollDepthMetric();

		Assert.assertEquals(100, maxScrollDepthMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPagesVariantFilter() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_7_DAYS, "2"),
			new HashSet<String>() {
				{
					add(PageMetricType.VIEWS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric viewsMetric = pageMetric.getViewsMetric();

		Assert.assertEquals(2, viewsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testPagesVisitorMetrics() {
		PageMetric pageMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				null, AssetType.PAGE, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(PageMetricType.VISITORS.getName());
				}
			});

		Assert.assertNotNull(pageMetric);

		Metric visitorsMetric = pageMetric.getVisitorsMetric();

		Assert.assertEquals(4, visitorsMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testSessionsMetrics() {
		SiteMetric siteMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"1", AssetType.SITE, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(SiteMetricType.SESSION_DURATION.getName());
					add(SiteMetricType.SESSIONS.getName());
					add(SiteMetricType.SESSIONS_PER_VISITOR.getName());
				}
			});

		Assert.assertNotNull(siteMetric);

		Metric sessionDurationMetric = siteMetric.getSessionDurationMetric();

		Assert.assertEquals(445229, sessionDurationMetric.getValue(), 0);

		Metric sessionsMetric = siteMetric.getSessionsMetric();

		Assert.assertEquals(5, sessionsMetric.getValue(), 0);

		Metric sessionsPerVisitorMetric =
			siteMetric.getSessionsPerVisitorMetric();

		Assert.assertEquals(
			1.6666666666666667, sessionsPerVisitorMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testSiteBounceRateMetric() {
		SiteMetric siteMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"1", AssetType.SITE, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(SiteMetricType.BOUNCE_RATE.getName());
				}
			});

		Assert.assertNotNull(siteMetric);

		Metric bounceRateMetric = siteMetric.getBounceRateMetric();

		Assert.assertEquals(
			0.16666666666666666, bounceRateMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testSiteEngagementMetric() {
		SiteMetric siteMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"1", AssetType.SITE, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(SiteMetricType.ENGAGEMENT.getName());
				}
			});

		Assert.assertNotNull(siteMetric);

		Metric engagementMetric = siteMetric.getEngagementMetric();

		Assert.assertEquals(
			0.09893977777777777, engagementMetric.getValue(), 0);
	}

	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testSiteVisitorsMetrics() {
		SiteMetric siteMetric = _metricDog.getAssetMetric(
			_createSearchQuery(
				"1", AssetType.SITE, null, TimeRange.LAST_7_DAYS, null),
			new HashSet<String>() {
				{
					add(SiteMetricType.ANONYMOUS_VISITORS.getName());
					add(SiteMetricType.KNOWN_VISITORS.getName());
					add(SiteMetricType.VISITORS.getName());
				}
			});

		Assert.assertNotNull(siteMetric);

		Metric anonymousVisitorsMetric =
			siteMetric.getAnonymousVisitorsMetric();

		Assert.assertEquals(2, anonymousVisitorsMetric.getValue(), 0);

		Metric knownVisitorsMetric = siteMetric.getKnownVisitorsMetric();

		Assert.assertEquals(1, knownVisitorsMetric.getValue(), 0);

		Metric visitorsMetric = siteMetric.getVisitorsMetric();

		Assert.assertEquals(3, visitorsMetric.getValue(), 0);
	}

	private SearchQueryContext _createSearchQuery(
		String assetId, AssetType assetType, String experimentId,
		TimeRange timeRange, String variantId) {

		return new SearchQueryContext(assetId, assetType) {
			{
				setExperimentId(experimentId);
				setTimeRange(timeRange);
				setVariantId(variantId);
			}
		};
	}

	@Autowired
	private MetricDog _metricDog;

}