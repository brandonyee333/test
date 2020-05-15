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

import com.liferay.osb.asah.backend.rest.response.embedded.BlockedKeywordEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@RequestMapping("/blocked-keywords")
@RestController
public class BlockedKeywordsRestController extends BaseRestController {

	@DeleteMapping
	public void deleteBlockedKeywords(@RequestBody List<String> ids)
		throws OSBAsahException {

		if (ids.isEmpty()) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Empty blocked keyword IDs");
		}

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		ids.forEach(
			id -> elasticsearchBulkRequestBuilder.delete(
				"blocked-keywords", id));

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		BulkResponse bulkResponse = elasticsearchBulkRequestBuilder.get();

		if (bulkResponse.hasFailures()) {
			throw new OSBAsahException(
				HttpStatus.INTERNAL_SERVER_ERROR,
				bulkResponse.buildFailureMessage());
		}
	}

	@GetMapping("/{id}")
	public String getBlockedKeyword(@PathVariable String id) throws Exception {
		return toItemGetResponse(
			"blocked-keywords",
			new BlockedKeywordEmbeddedJSONObjectCreator(
				faroInfoElasticsearchInvoker),
			id);
	}

	@GetMapping
	public String getBlockedKeywords(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		if ((queryBuilder != null) && ArrayUtils.isEmpty(sorts)) {
			sorts = new String[] {"_score,desc"};
		}

		return toCollectionGetResponse(
			"blocked-keywords",
			new BlockedKeywordEmbeddedJSONObjectCreator(
				faroInfoElasticsearchInvoker),
			page, queryBuilder, size, sorts);
	}

	@PostMapping
	public String postBlockedKeywords(@RequestBody String json)
		throws Exception {

		JSONObject jsonObject = new JSONObject(json);

		Set<String> keywords = JSONUtil.toStringSet(
			jsonObject.getJSONArray("keywords"));

		Stream<String> stream = keywords.stream();

		keywords = stream.map(
			String::trim
		).map(
			String::toLowerCase
		).filter(
			StringUtils::isNotEmpty
		).collect(
			Collectors.toSet()
		);

		if (keywords.isEmpty()) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Empty keywords");
		}

		JSONArray duplicateKeywordsJSONArray = _getDuplicateKeywordsJSONArray(
			keywords);

		JSONArray blockedKeywordsJSONArray = _createBlockedKeywordsJSONArray(
			duplicateKeywordsJSONArray, keywords);

		if (blockedKeywordsJSONArray.length() == 0) {
			return JSONUtil.put(
				"blocked-keywords", duplicateKeywordsJSONArray
			).put(
				"succeeded", false
			).toString();
		}

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		for (int i = 0; i < blockedKeywordsJSONArray.length(); i++) {
			elasticsearchBulkRequestBuilder.add(
				"blocked-keywords", blockedKeywordsJSONArray.getJSONObject(i));
		}

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		BulkResponse bulkResponse = elasticsearchBulkRequestBuilder.get();

		BulkItemResponse[] bulkResponseItems = bulkResponse.getItems();

		for (int i = 0; i < blockedKeywordsJSONArray.length(); i++) {
			BulkItemResponse bulkItemResponse = bulkResponseItems[i];

			JSONObject blockedKeywordJSONObject =
				blockedKeywordsJSONArray.getJSONObject(i);

			if (bulkItemResponse.isFailed()) {
				blockedKeywordJSONObject.remove("createDate");
			}
			else {
				blockedKeywordJSONObject.put("id", bulkItemResponse.getId());
			}
		}

		return JSONUtil.put(
			"blocked-keywords",
			JSONUtil.concat(
				duplicateKeywordsJSONArray, blockedKeywordsJSONArray)
		).put(
			"succeeded", !bulkResponse.hasFailures()
		).toString();
	}

	private JSONArray _createBlockedKeywordsJSONArray(
		JSONArray duplicateKeywordsJSONArray, Set<String> keywords) {

		String dateString = DateUtil.newDateString();

		Set<String> duplicateKeywords = JSONUtil.toStringSet(
			duplicateKeywordsJSONArray, "keyword");

		Stream<String> keywordsStream = keywords.stream();

		return keywordsStream.filter(
			keyword -> !duplicateKeywords.contains(keyword)
		).map(
			keyword -> JSONUtil.put(
				"createDate", dateString
			).put(
				"dataSourceIds", new JSONArray()
			).put(
				"duplicate", false
			).put(
				"keyword", keyword
			)
		).collect(
			JSONUtil.createCollector()
		);
	}

	private JSONArray _getDuplicateKeywordsJSONArray(Set<String> keywords) {
		JSONArray jsonArray = faroInfoElasticsearchInvoker.get(
			"blocked-keywords",
			QueryBuilders.termsQuery("keyword.raw", keywords));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			jsonObject.put("duplicate", true);
		}

		return jsonArray;
	}

}