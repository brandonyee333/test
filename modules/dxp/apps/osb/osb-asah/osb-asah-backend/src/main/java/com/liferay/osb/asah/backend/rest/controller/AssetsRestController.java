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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping("/assets")
@RestController
public class AssetsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public String getAsset(@PathVariable String id) throws Exception {
		return toItemGetResponse("assets", id);
	}

	@GetMapping(params = "!apply")
	public String getAssets(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"assets", null, page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			sorts);
	}

	@GetMapping(params = "apply")
	public String getAssetTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"assets", page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"asset-transformations");
	}

}