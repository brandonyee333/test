/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.filter;

import com.liferay.portal.kernel.search.filter.GeoDistanceRangeFilter;
import com.liferay.portal.kernel.search.geolocation.GeoLocationPoint;

import org.elasticsearch.index.query.GeoDistanceRangeQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = GeoDistanceRangeFilterTranslator.class)
public class GeoDistanceRangeFilterTranslatorImpl
	implements GeoDistanceRangeFilterTranslator {

	@Override
	public QueryBuilder translate(
		GeoDistanceRangeFilter geoDistanceRangeFilter) {

		GeoDistanceRangeQueryBuilder geoDistanceRangeQueryBuilder =
			QueryBuilders.geoDistanceRangeQuery(
				geoDistanceRangeFilter.getField());

		geoDistanceRangeQueryBuilder.from(
			String.valueOf(geoDistanceRangeFilter.getLowerBoundGeoDistance()));
		geoDistanceRangeQueryBuilder.includeLower(
			geoDistanceRangeFilter.isIncludesLower());
		geoDistanceRangeQueryBuilder.includeUpper(
			geoDistanceRangeFilter.isIncludesUpper());

		GeoLocationPoint pinGeoLocationPoint =
			geoDistanceRangeFilter.getPinGeoLocationPoint();

		geoDistanceRangeQueryBuilder.point(
			pinGeoLocationPoint.getLatitude(),
			pinGeoLocationPoint.getLongitude());

		geoDistanceRangeQueryBuilder.to(
			String.valueOf(geoDistanceRangeFilter.getUpperBoundGeoDistance()));

		return geoDistanceRangeQueryBuilder;
	}

}