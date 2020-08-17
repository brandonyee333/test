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

import com.liferay.osb.asah.backend.dog.SegmentDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.BlogMetricType;
import com.liferay.osb.asah.backend.model.FormMetricType;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.Segment;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.ResultBag;
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
 * @author Lino Alves
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class SegmentDogTest {

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "segment_blogs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testBlogViewsSegmentMetrics() {
		ResultBag<Metric> resultBag = _segmentDog.getSegmentMetricResultBag(
			BlogMetricType.VIEWS, _createSearchQuery("1", AssetType.BLOG));

		List<Metric> segmentMetrics = resultBag.getResults();

		Assert.assertEquals(
			segmentMetrics.toString(), 2, segmentMetrics.size());

		Assert.assertEquals(2, resultBag.getTotal());

		DogTestUtil.assertMetric(1, segmentMetrics, "CEO");
		DogTestUtil.assertMetric(2, segmentMetrics, "Developer");
	}

	@ElasticsearchIndex(
		name = "forms", resourcePath = "segment_forms_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testFormViewsSegmentMetrics() {
		ResultBag<Metric> resultBag = _segmentDog.getSegmentMetricResultBag(
			FormMetricType.VIEWS, _createSearchQuery("1", AssetType.FORM));

		List<Metric> segmentMetrics = resultBag.getResults();

		Assert.assertEquals(
			segmentMetrics.toString(), 3, segmentMetrics.size());

		Assert.assertEquals(3, resultBag.getTotal());

		DogTestUtil.assertMetric(1, segmentMetrics, "Developer");
		DogTestUtil.assertMetric(2, segmentMetrics, "Manager");
		DogTestUtil.assertMetric(1, segmentMetrics, "Marketeer");
	}

	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "individual_segments_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetSegments() {
		List<Segment> segments = _segmentDog.getSegments(
			"338511398116723457", "338511451975440187");

		Assert.assertEquals(segments.toString(), 2, segments.size());

		_assertSegment(2, "Test Segment 0", segments.get(0));
		_assertSegment(1, "Test Segment 2", segments.get(1));
	}

	@ElasticsearchIndex(
		name = "journals", resourcePath = "segment_journal_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testJournalViewsSegmentMetrics() {
		ResultBag<Metric> resultBag = _segmentDog.getSegmentMetricResultBag(
			JournalMetricType.VIEWS,
			_createSearchQuery("1", AssetType.JOURNAL));

		List<Metric> segmentMetrics = resultBag.getResults();

		Assert.assertEquals(
			segmentMetrics.toString(), 15, segmentMetrics.size());

		Assert.assertEquals(19, resultBag.getTotal());

		DogTestUtil.assertMetric(13, segmentMetrics, "S");
		DogTestUtil.assertMetric(13, segmentMetrics, "Q");
		DogTestUtil.assertMetric(13, segmentMetrics, "R");
		DogTestUtil.assertMetric(12, segmentMetrics, "P");
		DogTestUtil.assertMetric(11, segmentMetrics, "O");
		DogTestUtil.assertMetric(10, segmentMetrics, "L");
		DogTestUtil.assertMetric(10, segmentMetrics, "M");
		DogTestUtil.assertMetric(9, segmentMetrics, "K");
		DogTestUtil.assertMetric(8, segmentMetrics, "A");
		DogTestUtil.assertMetric(8, segmentMetrics, "J");
		DogTestUtil.assertMetric(7, segmentMetrics, "H");
		DogTestUtil.assertMetric(7, segmentMetrics, "I");
		DogTestUtil.assertMetric(6, segmentMetrics, "G");
		DogTestUtil.assertMetric(5, segmentMetrics, "others");
	}

	private void _assertSegment(
		long expectedSegmentIndividualCount, String expectedSegmentName,
		Segment actualSegment) {

		Assert.assertEquals(expectedSegmentName, actualSegment.getName());
		Assert.assertEquals(
			expectedSegmentIndividualCount, actualSegment.getIndividualCount());
	}

	private SearchQueryContext _createSearchQuery(
		String assetId, AssetType assetType) {

		return new SearchQueryContext(assetId, assetType) {
			{
				setTimeRange(TimeRange.LAST_7_DAYS);
			}
		};
	}

	@Autowired
	private SegmentDog _segmentDog;

}