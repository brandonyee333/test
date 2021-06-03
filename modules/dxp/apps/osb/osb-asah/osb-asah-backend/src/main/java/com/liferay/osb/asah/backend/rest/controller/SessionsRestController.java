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

import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rachael Koestartyo
 */
@RequestMapping("/sessions")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.SessionsRestController"
)
public class SessionsRestController extends BaseRestController {

	@GetMapping("/values")
	public String getSessionValues(
			@RequestParam String fieldName,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(required = false) String value)
		throws Exception {

		QueryBuilder queryBuilder = null;

		if (StringUtils.isNotBlank(filterString)) {
			queryBuilder = FilterStringToQueryBuilderConverter.convert(
				filterString.replace("context/", ""));
		}

		fieldName = fieldName.replace("context/", "");

		if (_contextFieldNames.contains(fieldName)) {
			fieldName = fieldName + "s";
		}

		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new TermsAggregationTransformationJSONArrayFunction(
				value, fieldName,
				MultiBucketsAggregation.Bucket::getKeyAsString);

		return toTransformationGetResponse(
			null, "user-sessions", cerebroInfoElasticsearchInvoker, page,
			queryBuilder, size, null, new String[] {"_key", "asc"}, null,
			transformationJSONArrayFunction, "session-values");
	}

	private final List<String> _contextFieldNames = Arrays.asList(
		"canonicalUrl", "referrer", "url");

}