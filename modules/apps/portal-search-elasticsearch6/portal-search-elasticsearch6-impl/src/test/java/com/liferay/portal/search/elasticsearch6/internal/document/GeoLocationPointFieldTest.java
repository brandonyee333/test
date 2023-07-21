/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.document;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.geolocation.GeoLocationPoint;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.search.elasticsearch6.internal.ElasticsearchIndexingFixture;
import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch6.internal.connection.IndexCreator;
import com.liferay.portal.search.elasticsearch6.internal.connection.IndicesAdminClientSupplier;
import com.liferay.portal.search.elasticsearch6.internal.connection.LiferayIndexCreationHelper;
import com.liferay.portal.search.elasticsearch6.internal.index.LiferayDocumentTypeFactory;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;
import com.liferay.portal.search.test.util.indexing.BaseIndexingTestCase;
import com.liferay.portal.search.test.util.indexing.DocumentCreationHelpers;
import com.liferay.portal.search.test.util.indexing.IndexingFixture;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class GeoLocationPointFieldTest extends BaseIndexingTestCase {

	@Test
	public void testCustomField() throws Exception {
		assertGeoLocationPointField(_CUSTOM_FIELD);
	}

	@Test
	public void testDefaultField() throws Exception {
		assertGeoLocationPointField(Field.GEO_LOCATION);
	}

	@Test
	public void testDefaultTemplate() throws Exception {
		assertGeoLocationPointField(_CUSTOM_FIELD.concat("_geolocation"));
	}

	protected void assertGeoLocationPointField(final String fieldName)
		throws Exception {

		final double latitude = randomLatitude();
		final double longitude = randomLongitude();

		addDocument(
			DocumentCreationHelpers.singleGeoLocation(
				fieldName, latitude, longitude));

		Document document = IdempotentRetryAssert.retryAssert(
			10, TimeUnit.SECONDS,
			new Callable<Document>() {

				@Override
				public Document call() throws Exception {
					return searchOneDocument();
				}

			});

		Field field = document.getField(fieldName);

		GeoLocationPoint geoLocationPoint = field.getGeoLocationPoint();

		Assert.assertEquals(latitude, geoLocationPoint.getLatitude(), 0);
		Assert.assertEquals(longitude, geoLocationPoint.getLongitude(), 0);
	}

	@Override
	protected IndexingFixture createIndexingFixture() throws Exception {
		ElasticsearchFixture elasticsearchFixture = new ElasticsearchFixture(
			GeoLocationPointFieldTest.class.getSimpleName());

		IndexCreator indexCreator = new IndexCreator(elasticsearchFixture);

		indexCreator.setIndexCreationHelper(
			new CustomFieldLiferayIndexCreationHelper(elasticsearchFixture));

		return new ElasticsearchIndexingFixture(
			elasticsearchFixture, BaseIndexingTestCase.COMPANY_ID,
			indexCreator);
	}

	protected int randomLatitude() {
		return RandomTestUtil.randomInt(90, 180) - 90;
	}

	protected int randomLongitude() {
		return RandomTestUtil.randomInt(180, 360) - 180;
	}

	protected Document searchOneDocument() throws Exception {
		Hits hits = search(createSearchContext());

		Document[] documents = hits.getDocs();

		Assert.assertEquals(Arrays.toString(documents), 1, documents.length);

		return documents[0];
	}

	private static final String _CUSTOM_FIELD = "customField";

	private static class CustomFieldLiferayIndexCreationHelper
		extends LiferayIndexCreationHelper {

		public CustomFieldLiferayIndexCreationHelper(
			IndicesAdminClientSupplier indicesAdminClientSupplier) {

			super(indicesAdminClientSupplier);
		}

		@Override
		public void whenIndexCreated(String indexName) {
			super.whenIndexCreated(indexName);

			LiferayDocumentTypeFactory liferayDocumentTypeFactory =
				getLiferayDocumentTypeFactory();

			String source = StringBundler.concat(
				"{ \"properties\": { \"", _CUSTOM_FIELD, "\" : { \"fields\": ",
				"{ \"geopoint\" : { \"store\": true, \"type\": \"keyword\" } ",
				"}, \"store\": true, \"type\": \"geo_point\" } } }");

			liferayDocumentTypeFactory.addTypeMappings(indexName, source);
		}

	}

}