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

import com.liferay.osb.asah.backend.rest.response.NumbersDistributionTransformationJSONArrayFunction;
import com.liferay.osb.asah.backend.rest.response.embedded.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;

import java.util.HashMap;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping("/individuals")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.IndividualsRestController"
)
@SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
public class IndividualsRestController
	extends com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.
				IndividualsRestController {

	@GetMapping("/distribution")
	public String getIndividualsDistribution(
			@RequestParam String fieldMappingId,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "10") int numberOfBins,
			@RequestParam(defaultValue = "100") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.fetch(
			"field-mappings", fieldMappingId);

		if (fieldMappingJSONObject == null) {
			throw new IllegalArgumentException(
				"Invalid field mapping ID " + fieldMappingId);
		}

		String ownerType = fieldMappingJSONObject.getString("ownerType");

		if (!ownerType.equals("individual")) {
			throw new IllegalArgumentException(
				"Unable to use non-individual field " +
					fieldMappingJSONObject.getString("fieldName") + " to " +
						"distribute individuals");
		}

		String fieldName =
			"demographics." + fieldMappingJSONObject.getString("fieldName") +
				".value";

		String fieldType = fieldMappingJSONObject.getString("fieldType");

		TransformationJSONArrayFunction transformationJSONArrayFunction = null;

		if (fieldType.equals("Number")) {
			transformationJSONArrayFunction =
				new NumbersDistributionTransformationJSONArrayFunction();

			size = numberOfBins;
		}
		else {
			transformationJSONArrayFunction =
				new TermsAggregationTransformationJSONArrayFunction(
					null, fieldName,
					bucket -> JSONUtil.put(
						"count", bucket.getDocCount()
					).put(
						"values", JSONUtil.put(bucket.getKeyAsString())
					));
		}

		return toTransformationGetResponse(
			null, "individuals", faroInfoElasticsearchInvoker, 0,
			FilterStringToQueryBuilderConverter.convert(
				filterString, _faroInfoIndividualsFilterStringConverterHelper),
			size,
			new HashMap<String, String>() {
				{
					put("count", "_count");
					put("name", "_key");
				}
			},
			sorts, fieldName, transformationJSONArrayFunction,
			"individuals-distribution-transformations");
	}

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

}