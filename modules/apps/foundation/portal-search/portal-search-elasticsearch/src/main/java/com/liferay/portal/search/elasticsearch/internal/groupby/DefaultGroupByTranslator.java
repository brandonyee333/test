/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.elasticsearch.internal.groupby;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.GeoDistanceSort;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.geolocation.GeoLocationPoint;
import com.liferay.portal.kernel.search.highlight.HighlightUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.search.groupby.GroupByRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = GroupByTranslator.class)
public class DefaultGroupByTranslator implements GroupByTranslator {

	@Override
	public void translate(
		SearchRequestBuilder searchRequestBuilder, SearchContext searchContext,
		GroupByRequest groupByRequest) {

		TermsBuilder termsBuilder = AggregationBuilders.terms(
			GROUP_BY_AGGREGATION_PREFIX + groupByRequest.getField());

		termsBuilder = termsBuilder.field(groupByRequest.getField());

		int termsSize = GetterUtil.getInteger(groupByRequest.getTermsSize());

		if (termsSize > 0) {
			termsBuilder.size(termsSize);
		}

		addTermsSorts(termsBuilder, groupByRequest);

		TopHitsBuilder topHitsBuilder = getTopHitsBuilder(
			searchContext, groupByRequest);

		termsBuilder.subAggregation(topHitsBuilder);

		searchRequestBuilder.addAggregation(termsBuilder);
	}

	protected void addDocsSorts(TopHitsBuilder topHitsBuilder, Sort[] sorts) {
		if (ArrayUtil.isEmpty(sorts)) {
			return;
		}

		Set<String> sortFieldNames = new HashSet<>(sorts.length);

		for (Sort sort : sorts) {
			if (sort == null) {
				continue;
			}

			String sortFieldName = DocumentImpl.getSortFieldName(
				sort, _ELASTICSEARCH_SCORE_FIELD);

			if (sortFieldNames.contains(sortFieldName)) {
				continue;
			}

			sortFieldNames.add(sortFieldName);

			SortOrder sortOrder = SortOrder.ASC;

			if (sort.isReverse() ||
				sortFieldName.equals(_ELASTICSEARCH_SCORE_FIELD)) {

				sortOrder = SortOrder.DESC;
			}

			SortBuilder sortBuilder = null;

			if (sortFieldName.equals(_ELASTICSEARCH_SCORE_FIELD)) {
				sortBuilder = SortBuilders.scoreSort();
			}
			else if (sort.getType() == Sort.GEO_DISTANCE_TYPE) {
				GeoDistanceSort geoDistanceSort = (GeoDistanceSort)sort;

				GeoDistanceSortBuilder geoDistanceSortBuilder =
					SortBuilders.geoDistanceSort(sortFieldName);

				geoDistanceSortBuilder.geoDistance(GeoDistance.DEFAULT);

				for (GeoLocationPoint geoLocationPoint :
						geoDistanceSort.getGeoLocationPoints()) {

					geoDistanceSortBuilder.point(
						geoLocationPoint.getLatitude(),
						geoLocationPoint.getLongitude());
				}

				Collection<String> geoHashes = geoDistanceSort.getGeoHashes();

				if (!geoHashes.isEmpty()) {
					geoDistanceSort.addGeoHash(
						geoHashes.toArray(new String[0]));
				}

				sortBuilder = geoDistanceSortBuilder;
			}
			else {
				FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort(
					sortFieldName);

				fieldSortBuilder.unmappedType("string");

				sortBuilder = fieldSortBuilder;
			}

			sortBuilder.order(sortOrder);

			topHitsBuilder.addSort(sortBuilder);
		}
	}

	protected void addHighlightedField(
		TopHitsBuilder topHitsBuilder, QueryConfig queryConfig,
		String fieldName) {

		topHitsBuilder.addHighlightedField(
			fieldName, queryConfig.getHighlightFragmentSize(),
			queryConfig.getHighlightSnippetSize());

		String localizedFieldName = DocumentImpl.getLocalizedName(
			queryConfig.getLocale(), fieldName);

		topHitsBuilder.addHighlightedField(
			localizedFieldName, queryConfig.getHighlightFragmentSize(),
			queryConfig.getHighlightSnippetSize());
	}

	protected void addHighlights(
		TopHitsBuilder topHitsBuilder, QueryConfig queryConfig) {

		if (!queryConfig.isHighlightEnabled()) {
			return;
		}

		for (String highlightFieldName : queryConfig.getHighlightFieldNames()) {
			addHighlightedField(
				topHitsBuilder, queryConfig, highlightFieldName);
		}

		topHitsBuilder.setHighlighterPostTags(
			HighlightUtil.HIGHLIGHT_TAG_CLOSE);
		topHitsBuilder.setHighlighterPreTags(HighlightUtil.HIGHLIGHT_TAG_OPEN);
		topHitsBuilder.setHighlighterRequireFieldMatch(
			queryConfig.isHighlightRequireFieldMatch());
	}

	protected void addSelectedFields(
		TopHitsBuilder topHitsBuilder, QueryConfig queryConfig) {

		String[] selectedFieldNames = queryConfig.getSelectedFieldNames();

		if (ArrayUtil.isEmpty(selectedFieldNames)) {
			topHitsBuilder.addField(StringPool.STAR);
		}
		else {
			for (String selectedFieldName : selectedFieldNames) {
				topHitsBuilder.addField(selectedFieldName);
			}
		}
	}

	protected void addTermsSorts(
		TermsBuilder termsBuilder, GroupByRequest groupByRequest) {

		Sort[] sorts = groupByRequest.getTermsSorts();

		if (ArrayUtil.isEmpty(sorts)) {
			return;
		}

		Set<String> sortFieldNames = new HashSet<>(sorts.length);

		List<Terms.Order> bucketOrders = new ArrayList<>(sorts.length);

		for (Sort sort : sorts) {
			if (sort == null) {
				continue;
			}

			String sortFieldName = sort.getFieldName();

			if (sortFieldNames.contains(sortFieldName)) {
				continue;
			}

			sortFieldNames.add(sortFieldName);

			if (sortFieldName.equals("_count")) {
				bucketOrders.add(Terms.Order.count(!sort.isReverse()));
			}
			else if (sortFieldName.equals("_key")) {
				bucketOrders.add(Terms.Order.term(!sort.isReverse()));
			}

			if (!bucketOrders.isEmpty()) {
				termsBuilder.order(Terms.Order.compound(bucketOrders));
			}
		}
	}

	protected TopHitsBuilder getTopHitsBuilder(
		SearchContext searchContext, GroupByRequest groupByRequest) {

		TopHitsBuilder topHitsBuilder = AggregationBuilders.topHits(
			TOP_HITS_AGGREGATION_NAME);

		int docsStart = GetterUtil.getInteger(groupByRequest.getDocsStart());

		if (docsStart > 0) {
			topHitsBuilder.setFrom(docsStart);
		}

		int docsSize = GetterUtil.getInteger(groupByRequest.getDocsSize());

		if (docsSize > 0) {
			topHitsBuilder.setSize(docsSize);
		}

		addDocsSorts(topHitsBuilder, groupByRequest.getDocsSorts());

		addHighlights(topHitsBuilder, searchContext.getQueryConfig());
		addSelectedFields(topHitsBuilder, searchContext.getQueryConfig());

		return topHitsBuilder;
	}

	private static final String _ELASTICSEARCH_SCORE_FIELD = "_score";

}