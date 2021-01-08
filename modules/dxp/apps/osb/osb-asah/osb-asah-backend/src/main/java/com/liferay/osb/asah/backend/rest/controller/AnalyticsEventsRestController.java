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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.backend.rest.response.embedded.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;

import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthew Kong
 */
@RequestMapping("/analytics-events")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.AnalyticsEventsRestController"
)
public class AnalyticsEventsRestController extends BaseRestController {

	@GetMapping("/values")
	public String getAnalyticsEventValues(
			@RequestParam String fieldName,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(required = false) String value)
		throws Exception {

		return toTransformationGetResponse(
			null, "analytics-events", cerebroRawElasticsearchInvoker, page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			null, new String[] {"_key", "asc"}, null,
			new TermsAggregationTransformationJSONArrayFunction(
				value, fieldName,
				MultiBucketsAggregation.Bucket::getKeyAsString),
			"analytics-event-values");
	}

}