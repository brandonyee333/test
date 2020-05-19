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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rachael Koestartyo
 */
@RequestMapping("/definitions")
@RestController
public class DefinitionsRestController extends BaseRestController {

	@GetMapping("/individual-attributes")
	public String getIndividualAttributes(
			@RequestParam(required = false) String name)
		throws Exception {

		return toCollectionGetResponse(
			"field-mappings", null, 0, _getQueryBuilder(name),
			(int)faroInfoElasticsearchInvoker.count(
				"field-mappings", _getQueryBuilder(name)),
			new String[] {"fieldName", "asc"});
	}

	private QueryBuilder _getQueryBuilder(String name) {
		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("ownerType", "individual"));

		if (StringUtils.isNotBlank(name)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "fieldName",
							QueryUtil.escapeKeywords(name)))
				).should(
					QueryBuilders.matchQuery(
						"fieldName", name
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		return boolQueryBuilder;
	}

}