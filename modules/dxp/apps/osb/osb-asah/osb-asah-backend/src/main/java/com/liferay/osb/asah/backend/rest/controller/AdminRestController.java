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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author Matthew Kong
 */
@ConditionalOnProperty("osb.asah.backend.admin.rest.controller.enabled")
@RequestMapping("/admin")
@RestController
public class AdminRestController extends BaseRestController {

	@DeleteMapping("/cache")
	public void clearCache() {
		for (String cacheName : _cacheManager.getCacheNames()) {
			Cache cache = _cacheManager.getCache(cacheName);

			cache.clear();
		}
	}

	@DeleteMapping("/data/{weDeployDataServiceName}/{collectionName}")
	public void deleteData(
		@PathVariable String collectionName,
		@PathVariable String weDeployDataServiceName) {

		ElasticsearchInvoker elasticsearchInvoker = _elasticsearchInvokers.get(
			weDeployDataServiceName);

		elasticsearchInvoker.delete(
			collectionName, QueryBuilders.matchAllQuery());
	}

	@GetMapping("/snapshot")
	public ResponseEntity downloadSnapshot() throws Exception {
		File file = _createSnapshot("elasticsearch-snapshot.zip");

		return toDownloadResponse(file, file.getName());
	}

	@Override
	@PostConstruct
	public void init() {
		for (WeDeployDataService weDeployDataService :
				WeDeployDataService.values()) {

			_elasticsearchInvokers.put(
				weDeployDataService.toString(),
				_elasticsearchInvokerFactory.forWeDeployDataService(
					weDeployDataService));
		}

		Class<?> clazz = getClass();

		try (InputStream inputStream = clazz.getResourceAsStream(
				"nanite_list_schema.json")) {

			_naniteListSchema = SchemaLoader.load(
				new JSONObject(new JSONTokener(inputStream)));
		}
		catch (IOException ioe) {
			_log.error(ioe);

			throw new IllegalStateException(
				"Unable to read nanite list schema", ioe);
		}
	}

	@PostMapping("/data/{weDeployDataServiceName}/{collectionName}")
	public void postData(
		@PathVariable String collectionName,
		@PathVariable String weDeployDataServiceName,
		@RequestBody String json) {

		ElasticsearchInvoker elasticsearchInvoker = _elasticsearchInvokers.get(
			weDeployDataServiceName);

		elasticsearchInvoker.add(collectionName, new JSONArray(json));

		if (collectionName.equals("data-sources")) {
			_nanitesHttp.refreshAnalytics();
		}
	}

	@PostMapping("/nanites/{className}")
	public void postTask(
		@PathVariable String className,
		@RequestBody(required = false) String json) {

		JSONObject jsonObject = null;

		if (json != null) {
			jsonObject = new JSONObject(json);
		}

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(className, jsonObject);
	}

	@PostMapping("/nanites")
	public void run(@RequestBody String json) {
		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		JSONArray jsonArray = new JSONArray(json);

		_naniteListSchema.validate(jsonArray);

		elasticsearchInvoker.delete(
			"OSBAsahMarkers",
			QueryBuilders.termsQuery("id", JSONUtil.toStringList(jsonArray)));

		_nanitesHttp.run(jsonArray);
	}

	private File _createSnapshot(String fileName) throws Exception {
		try (FileOutputStream fileOutputStream = new FileOutputStream(
				fileName)) {

			try (ZipOutputStream zipOutputStream = new ZipOutputStream(
					fileOutputStream)) {

				for (WeDeployDataService weDeployDataService :
						WeDeployDataService.values()) {

					_saveWeDeployDataService(
						weDeployDataService, zipOutputStream);
				}
			}
		}

		return new File(fileName);
	}

	private void _saveWeDeployDataService(
			WeDeployDataService weDeployDataService,
			ZipOutputStream zipOutputStream)
		throws Exception {

		ElasticsearchInvoker elasticsearchInvoker = _elasticsearchInvokers.get(
			weDeployDataService.toString());

		JSONArray collectionsJSONArray =
			_elasticsearchIndexManager.getCollectionsJSONArray(
				weDeployDataService);

		for (int i = 0; i < collectionsJSONArray.length(); i++) {
			String collectionName = collectionsJSONArray.getString(i);

			String indexName = ElasticsearchIndexUtil.getIndexName(
				collectionName, weDeployDataService.toString());

			AtomicInteger atomicInteger = new AtomicInteger();

			JSONArrayIterator.of(
				collectionName, elasticsearchInvoker, null
			).setBatchSize(
				10000
			).setProcessJSONArrayUnsafeFunction(
				jsonArray -> {
					if (jsonArray.length() == 0) {
						return null;
					}

					String fileName =
						indexName + "_" + atomicInteger.getAndIncrement() +
							".json";

					zipOutputStream.putNextEntry(new ZipEntry(fileName));

					String json = jsonArray.toString();

					zipOutputStream.write(
						json.getBytes(StandardCharsets.UTF_8));

					zipOutputStream.closeEntry();

					if (_log.isInfoEnabled()) {
						_log.info(
							"Copied " + jsonArray.length() + " objects to " +
								fileName);
					}

					return null;
				}
			).iterate();
		}
	}

	private static final Log _log = LogFactory.getLog(
		AdminRestController.class);

	@Autowired
	private CacheManager _cacheManager;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private final Map<String, ElasticsearchInvoker> _elasticsearchInvokers =
		new HashMap<>();

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	private Schema _naniteListSchema;

	@Autowired
	private NanitesHttp _nanitesHttp;

}