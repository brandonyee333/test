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

package com.liferay.osb.asah.backend.model;

import com.liferay.osb.asah.backend.test.util.BaseEnumTestCase;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class DocumentLibraryMetricTypeTest
	extends BaseEnumTestCase<DocumentLibraryMetricType> {

	@Test
	public void testComments() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.of("commentsMetric");

		Assert.assertEquals(
			DocumentLibraryMetricType.COMMENTS, documentLibraryMetricType);
	}

	@Test
	public void testCommentsFieldName() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.COMMENTS;

		Assert.assertEquals(
			"comments", documentLibraryMetricType.getFieldName());
	}

	@Test
	public void testCommentsTrendClassificationOrder() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.COMMENTS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			documentLibraryMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testDownloads() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.of("downloadsMetric");

		Assert.assertEquals(
			DocumentLibraryMetricType.DOWNLOADS, documentLibraryMetricType);
	}

	@Test
	public void testDownloadsFieldName() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.DOWNLOADS;

		Assert.assertEquals(
			"downloads", documentLibraryMetricType.getFieldName());
	}

	@Test
	public void testDownloadsTrendClassificationOrder() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.DOWNLOADS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			documentLibraryMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testPreviews() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.of("previewsMetric");

		Assert.assertEquals(
			DocumentLibraryMetricType.PREVIEWS, documentLibraryMetricType);
	}

	@Test
	public void testPreviewsFieldName() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.PREVIEWS;

		Assert.assertEquals(
			"previews", documentLibraryMetricType.getFieldName());
	}

	@Test
	public void testPreviewsTrendClassificationOrder() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.PREVIEWS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			documentLibraryMetricType.getTrendClassificationOrder());
	}

	@Test
	public void testRatings() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.of("ratingsMetric");

		Assert.assertEquals(
			DocumentLibraryMetricType.RATINGS, documentLibraryMetricType);
	}

	@Test
	public void testRatingsFieldName() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.RATINGS;

		Assert.assertEquals(
			"ratingsScore", documentLibraryMetricType.getFieldName());
	}

	@Test
	public void testRatingsTrendClassificationOrder() {
		DocumentLibraryMetricType documentLibraryMetricType =
			DocumentLibraryMetricType.RATINGS;

		Assert.assertEquals(
			TrendClassification.Order.ASC,
			documentLibraryMetricType.getTrendClassificationOrder());
	}

	@Override
	protected Class<? extends Enum<?>> getClazz() {
		return DocumentLibraryMetricType.class;
	}

}