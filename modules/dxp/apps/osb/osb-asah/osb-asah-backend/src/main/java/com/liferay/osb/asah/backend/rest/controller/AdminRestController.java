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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.ActivityGroup;
import com.liferay.osb.asah.common.model.BlockedKeyword;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.Membership;
import com.liferay.osb.asah.common.model.Preference;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.MembershipRepository;
import com.liferay.osb.asah.common.repository.PreferenceRepository;
import com.liferay.osb.asah.common.spring.annotation.CacheEvict;
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
import org.springframework.context.annotation.Profile;
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
@Profile("!prod")
@RequestMapping("/admin")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.AdminRestController"
)
public class AdminRestController extends BaseRestController {

	@CacheEvict(evictAll = true)
	@DeleteMapping("/cache")
	public void clearCache() {
	}

	@DeleteMapping("/data/{weDeployDataServiceName}/{collectionName}")
	public void deleteData(
		@PathVariable String collectionName,
		@PathVariable String weDeployDataServiceName) {

		if (collectionName.equals("activity-groups")) {
			_activityGroupRepository.deleteAll();
		}
		else if (collectionName.equals("blocked-keywords")) {
			_blockedKeywordRepository.deleteAll();
		}
		else if (collectionName.equals("channels")) {
			_channelRepository.deleteAll();
		}
		else if (collectionName.equals("data-sources")) {
			_dataSourceRepository.deleteAll();
		}
		else if (collectionName.equals("memberships")) {
			_membershipRepository.deleteAll();
		}
		else if (collectionName.equals("preferences")) {
			_preferenceRepository.deleteAll();
		}
		else {
			ElasticsearchInvoker elasticsearchInvoker =
				_elasticsearchInvokers.get(weDeployDataServiceName);

			elasticsearchInvoker.delete(
				collectionName, QueryBuilders.matchAllQuery());
		}
	}

	@GetMapping("/snapshot")
	public ResponseEntity downloadSnapshot() throws Exception {
		File file = _createSnapshot("elasticsearch-snapshot.zip");

		return toDownloadResponse(file, file.getName());
	}

	@PostConstruct
	public void init() {
		for (WeDeployDataService weDeployDataService :
				WeDeployDataService.values()) {

			_elasticsearchInvokers.put(
				weDeployDataService.toString(),
				_elasticsearchInvokerManager.forWeDeployDataService(
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

		if (collectionName.equals("activity-groups")) {
			_addActivityGroups(new JSONArray(json));
		}
		else if (collectionName.equals("blocked-keywords")) {
			_addBlockedKeywords(new JSONArray(json));
		}
		else if (collectionName.equals("channels")) {
			_addChannels(new JSONArray(json));
		}
		else if (collectionName.equals("data-sources")) {
			_addDataSources(new JSONArray(json));

			_nanitesHttp.refreshAnalytics();
		}
		else if (collectionName.equals("memberships")) {
			_addMemberships(new JSONArray(json));
		}
		else if (collectionName.equals("preferences")) {
			_addPreferences(new JSONArray(json));
		}
		else {
			ElasticsearchInvoker elasticsearchInvoker =
				_elasticsearchInvokers.get(weDeployDataServiceName);

			elasticsearchInvoker.add(collectionName, new JSONArray(json));
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
		JSONArray jsonArray = new JSONArray(json);

		_naniteListSchema.validate(jsonArray);

		_faroElasticsearchInvoker.delete(
			"OSBAsahMarkers",
			QueryBuilders.termsQuery("id", JSONUtil.toStringList(jsonArray)));

		_nanitesHttp.run(jsonArray);
	}

	private void _addActivityGroups(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			ActivityGroup activityGroup = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), ActivityGroup.class);

			activityGroup.setIsNew(true);

			_activityGroupRepository.save(activityGroup);
		}
	}

	private void _addBlockedKeywords(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			BlockedKeyword blockedKeyword = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), BlockedKeyword.class);

			blockedKeyword.setIsNew(true);

			_blockedKeywordRepository.save(blockedKeyword);
		}
	}

	private void _addChannels(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			Channel channel = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), Channel.class);

			channel.setIsNew(true);

			_channelRepository.save(channel);
		}
	}

	private void _addDataSources(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			DataSource dataSource = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), DataSource.class);

			dataSource.setIsNew(true);

			_dataSourceRepository.save(dataSource);
		}
	}

	private void _addMemberships(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			Membership membership = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), Membership.class);

			membership.setIsNew(true);

			_membershipRepository.save(membership);
		}
	}

	private void _addPreferences(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Preference preference = new Preference(
				jsonObject.getString("id"), jsonObject.getString("value"));

			preference.setIsNew(true);

			_preferenceRepository.save(preference);
		}
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
	private ActivityGroupRepository _activityGroupRepository;

	@Autowired
	private BlockedKeywordRepository _blockedKeywordRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	private final Map<String, ElasticsearchInvoker> _elasticsearchInvokers =
		new HashMap<>();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroElasticsearchInvoker;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private MembershipRepository _membershipRepository;

	private Schema _naniteListSchema;

	@Autowired
	private NanitesHttp _nanitesHttp;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private PreferenceRepository _preferenceRepository;

}