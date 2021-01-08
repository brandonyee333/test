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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.rest.response.DeleteResponse;
import com.liferay.osb.asah.common.rest.response.ItemGetResponse;
import com.liferay.osb.asah.common.rest.response.PatchResponse;
import com.liferay.osb.asah.common.rest.response.PostResponse;
import com.liferay.osb.asah.common.rest.response.PutResponse;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.embedded.EmbeddedJSONObjectCreator;

import java.io.File;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseRestController {

	@PostConstruct
	public void init() {
		cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
		salesforceRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forSalesforceRaw();
	}

	protected String toCollectionGetResponse(
			String collectionName,
			EmbeddedJSONObjectCreator embeddedJSONObjectCreator, int page,
			QueryBuilder queryBuilder, int size, String[] sorts)
		throws Exception {

		return _toCollectionGetResponse(
			collectionName, null, null, embeddedJSONObjectCreator, null, page,
			queryBuilder, size, sorts);
	}

	protected String toCollectionGetResponse(
			String collectionName,
			EmbeddedJSONObjectCreator embeddedJSONObjectCreator,
			List<FieldSortBuilder> fieldSortBuilders, int page,
			QueryBuilder queryBuilder, int size, String[] sorts)
		throws Exception {

		return _toCollectionGetResponse(
			collectionName, null, null, embeddedJSONObjectCreator,
			fieldSortBuilders, page, queryBuilder, size, sorts);
	}

	protected String toCollectionGetResponse(
			String collectionName, JSONArray embeddedJSONArray, int page,
			QueryBuilder queryBuilder, int size)
		throws Exception {

		return _toCollectionGetResponse(
			collectionName, embeddedJSONArray, null, null, null, page,
			queryBuilder, size, null);
	}

	protected String toCollectionGetResponse(
			String collectionName, JSONArray embeddedJSONArray,
			String embeddedJSONArrayKey, int page, QueryBuilder queryBuilder,
			int size)
		throws Exception {

		return _toCollectionGetResponse(
			collectionName, embeddedJSONArray, embeddedJSONArrayKey, null, null,
			page, queryBuilder, size, null);
	}

	protected String toDeleteResponse(String collectionName, String id) {
		DeleteResponse deleteResponse = new DeleteResponse();

		deleteResponse.setCollectionName(collectionName);
		deleteResponse.setElasticsearchInvoker(faroInfoElasticsearchInvoker);
		deleteResponse.setId(id);

		return deleteResponse.respond();
	}

	protected ResponseEntity toDownloadResponse(File file, String fileName) {
		if (!file.exists()) {
			return toNotFoundResponse();
		}

		ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();

		bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		bodyBuilder.header(
			HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + fileName + "\"");

		return bodyBuilder.body(new FileSystemResource(file.getAbsolutePath()));
	}

	protected String toItemGetResponse(
			String collectionName,
			EmbeddedJSONObjectCreator embeddedJSONObjectCreator, String id)
		throws Exception {

		ItemGetResponse itemGetResponse = new ItemGetResponse();

		itemGetResponse.setCollectionName(collectionName);
		itemGetResponse.setElasticsearchInvoker(faroInfoElasticsearchInvoker);
		itemGetResponse.setEmbeddedJSONObjectCreator(embeddedJSONObjectCreator);
		itemGetResponse.setId(id);

		return itemGetResponse.respond();
	}

	protected String toItemGetResponse(String collectionName, String id)
		throws Exception {

		return toItemGetResponse(collectionName, null, id);
	}

	protected ResponseEntity toNotFoundResponse() {
		ResponseEntity.HeadersBuilder headersBuilder =
			ResponseEntity.notFound();

		return headersBuilder.build();
	}

	protected String toPatchResponse(
			String collectionName, String id, String json)
		throws Exception {

		PatchResponse patchResponse = new PatchResponse();

		patchResponse.setCollectionName(collectionName);
		patchResponse.setElasticsearchInvoker(faroInfoElasticsearchInvoker);
		patchResponse.setId(id);
		patchResponse.setJSON(json);

		return patchResponse.respond();
	}

	protected String toPostResponse(String collectionName, String json)
		throws Exception {

		PostResponse postResponse = new PostResponse();

		postResponse.setCollectionName(collectionName);
		postResponse.setElasticsearchInvoker(faroInfoElasticsearchInvoker);
		postResponse.setJSON(json);

		return postResponse.respond();
	}

	protected String toPutResponse(
			String collectionName, String id, String json)
		throws Exception {

		PutResponse putResponse = new PutResponse();

		putResponse.setCollectionName(collectionName);
		putResponse.setElasticsearchInvoker(faroInfoElasticsearchInvoker);
		putResponse.setId(id);
		putResponse.setJSON(json);

		return putResponse.respond();
	}

	protected String toTransformationGetResponse(
			String collectionName, int page, QueryBuilder queryBuilder,
			int size, Map<String, String> sortFieldMappings, String[] sorts,
			TransformationJSONArrayFunction transformationJSONArrayFunction,
			String transformationName)
		throws Exception {

		return toTransformationGetResponse(
			null, collectionName, faroInfoElasticsearchInvoker, page,
			queryBuilder, size, sortFieldMappings, sorts, null,
			transformationJSONArrayFunction, transformationName);
	}

	protected String toTransformationGetResponse(
			String apply, String collectionName,
			ElasticsearchInvoker elasticsearchInvoker, int page,
			QueryBuilder queryBuilder, int size,
			Map<String, String> sortFieldMappings, String[] sorts,
			String supportedFieldName,
			TransformationJSONArrayFunction transformationJSONArrayFunction,
			String transformationName)
		throws Exception {

		if ((supportedFieldName != null) &&
			(transformationJSONArrayFunction == null)) {

			throw new IllegalArgumentException();
		}

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setApply(apply);
		transformationGetResponse.setCollectionName(collectionName);
		transformationGetResponse.setElasticsearchInvoker(elasticsearchInvoker);
		transformationGetResponse.setPage(page);
		transformationGetResponse.setQueryBuilder(queryBuilder);
		transformationGetResponse.setSize(size);
		transformationGetResponse.setSorts(sortFieldMappings, sorts);
		transformationGetResponse.setSupportedFieldName(supportedFieldName);
		transformationGetResponse.setTransformationJSONArrayFunction(
			transformationJSONArrayFunction);
		transformationGetResponse.setTransformationName(transformationName);

		return transformationGetResponse.respond();
	}

	protected String toTransformationGetResponse(
			String apply, String collectionName, int page,
			QueryBuilder queryBuilder, int size, String supportedFieldName,
			TransformationJSONArrayFunction transformationJSONArrayFunction,
			String transformationName)
		throws Exception {

		return toTransformationGetResponse(
			apply, collectionName, faroInfoElasticsearchInvoker, page,
			queryBuilder, size, null, null, supportedFieldName,
			transformationJSONArrayFunction, transformationName);
	}

	protected ElasticsearchInvoker cerebroRawElasticsearchInvoker;
	protected ElasticsearchInvoker dxpRawElasticsearchInvoker;
	protected ElasticsearchInvoker faroInfoElasticsearchInvoker;
	protected ElasticsearchInvoker salesforceRawElasticsearchInvoker;

	private String _toCollectionGetResponse(
			String collectionName, JSONArray embeddedJSONArray,
			String embeddedJSONArrayKey,
			EmbeddedJSONObjectCreator embeddedJSONObjectCreator,
			List<FieldSortBuilder> fieldSortBuilders, int page,
			QueryBuilder queryBuilder, int size, String[] sorts)
		throws Exception {

		CollectionGetResponse collectionGetResponse =
			new CollectionGetResponse();

		collectionGetResponse.setCollectionName(collectionName);
		collectionGetResponse.setElasticsearchInvoker(
			faroInfoElasticsearchInvoker);
		collectionGetResponse.setEmbeddedJSONArray(embeddedJSONArray);
		collectionGetResponse.setEmbeddedJSONArrayKey(embeddedJSONArrayKey);
		collectionGetResponse.setEmbeddedJSONObjectCreator(
			embeddedJSONObjectCreator);
		collectionGetResponse.setFieldSortBuilders(fieldSortBuilders);
		collectionGetResponse.setPage(page);
		collectionGetResponse.setQueryBuilder(queryBuilder);
		collectionGetResponse.setSize(size);
		collectionGetResponse.setSorts(sorts);

		return collectionGetResponse.respond();
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}