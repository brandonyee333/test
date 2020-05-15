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

import com.liferay.osb.asah.backend.rest.response.ActivitiesAssetTransformationJSONArrayFunction;
import com.liferay.osb.asah.backend.rest.response.ActivitiesHistogramTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoActivitiesFilterStringConverterHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@RequestMapping("/activities")
@RestController
public class ActivitiesRestController extends BaseRestController {

	@GetMapping(params = "!apply")
	public String getActivities(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"activities", null, page,
			FilterStringToQueryBuilderConverter.convert(
				filterString, _faroInfoActivitiesFilterStringConverterHelper),
			size, sorts);
	}

	@GetMapping("/{id}")
	public String getActivity(@PathVariable String id) throws Exception {
		return toItemGetResponse("activities", id);
	}

	@Cacheable(
		condition = "!#includeToday", value = "getActivityTransformations"
	)
	@GetMapping(params = "apply")
	public String getActivityTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "true") boolean includeToday,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			apply, "activities", page,
			FilterStringToQueryBuilderConverter.convert(
				filterString, _faroInfoActivitiesFilterStringConverterHelper),
			size, "day",
			new ActivitiesHistogramTransformationJSONArrayFunction(
				includeToday),
			"activity-transformations");
	}

	@GetMapping("/assets")
	public String getAssetTransformations(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toTransformationGetResponse(
			null, "activities", faroInfoElasticsearchInvoker, page,
			FilterStringToQueryBuilderConverter.convert(
				filterString, _faroInfoActivitiesFilterStringConverterHelper),
			size, null, sorts, null,
			new ActivitiesAssetTransformationJSONArrayFunction(),
			"asset-transformations");
	}

	@Autowired
	private FaroInfoActivitiesFilterStringConverterHelper
		_faroInfoActivitiesFilterStringConverterHelper;

}