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

import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.BlockedKeyword;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.CustomAssetDashboardRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.ExperimentRepository;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;
import com.liferay.osb.asah.common.repository.MembershipRepository;
import com.liferay.osb.asah.common.repository.PreferenceRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
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

import org.apache.commons.beanutils.BeanUtils;
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
import org.springframework.data.repository.CrudRepository;
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

		if (collectionName.equals("accounts")) {
			_accountRepository.deleteAll();
		}
		else if (collectionName.equals("activity-groups")) {
			_activityGroupRepository.deleteAll();
		}
		else if (collectionName.equals("blocked-keywords")) {
			_blockedKeywordRepository.deleteAll();
		}
		else if (collectionName.equals("channels")) {
			_channelRepository.deleteAll();
		}
		else if (collectionName.equals("custom-asset-dashboards")) {
			_customAssetDashboardRepository.deleteAll();
		}
		else if (collectionName.equals("data-sources")) {
			_dataSourceRepository.deleteAll();
		}
		else if (collectionName.equals("experiments")) {
			_experimentRepository.deleteAll();
		}
		else if (collectionName.equals("individual-segments")) {
			_segmentRepository.deleteAll();
		}
		else if (collectionName.equals("membership-changes")) {
			_membershipChangeRepository.deleteAll();
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
		catch (IOException ioException) {
			_log.error(ioException, ioException);

			throw new IllegalStateException(
				"Unable to read nanite list schema", ioException);
		}
	}

	@PostMapping("/data/{weDeployDataServiceName}/{collectionName}")
	public void postData(
			@PathVariable String collectionName,
			@PathVariable String weDeployDataServiceName,
			@RequestBody String json)
		throws Exception {

		if (collectionName.equals("accounts")) {
			_addEntities(_accountRepository, json, Account.class);
		}
		else if (collectionName.equals("activity-groups")) {
			_addEntities(_activityGroupRepository, json, ActivityGroup.class);
		}
		else if (collectionName.equals("blocked-keywords")) {
			_addEntities(_blockedKeywordRepository, json, BlockedKeyword.class);
		}
		else if (collectionName.equals("channels")) {
			_addEntities(_channelRepository, json, Channel.class);
		}
		else if (collectionName.equals("custom-asset-dashboards")) {
			_addEntities(
				_customAssetDashboardRepository, json,
				CustomAssetDashboard.class);
		}
		else if (collectionName.equals("data-sources")) {
			_addEntities(_dataSourceRepository, json, DataSource.class);
		}
		else if (collectionName.equals("experiments")) {
			_addEntities(_experimentRepository, json, Experiment.class);
		}
		else if (collectionName.equals("individual-segments")) {
			_addEntities(_segmentRepository, json, Segment.class);
		}
		else if (collectionName.equals("membership-changes")) {
			_addEntities(
				_membershipChangeRepository, json, MembershipChange.class);
		}
		else if (collectionName.equals("memberships")) {
			_addEntities(_membershipRepository, json, Membership.class);
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

		_asahTaskDog.scheduleAsahTask(className, jsonObject);
	}

	@PostMapping("/nanites")
	public void run(@RequestBody String json) {
		JSONArray jsonArray = new JSONArray(json);

		_naniteListSchema.validate(jsonArray);

		asahMarkerDog.deleteAsahMarkers(JSONUtil.toStringList(jsonArray));

		_nanitesHttp.run(jsonArray);
	}

	private <T, ID> void _addEntities(
			CrudRepository<T, ID> crudRepository, String json,
			Class<T> modelClass)
		throws Exception {

		JSONArray jsonArray = new JSONArray(json);

		for (int i = 0; i < jsonArray.length(); i++) {
			T t = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), modelClass);

			BeanUtils.setProperty(t, "isNew", true);

			crudRepository.save(t);
		}
	}

	private void _addPreferences(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String id = jsonObject.getString("id");

			Preference preference = new Preference(
				id, jsonObject.getString("value"));

			if (!_preferenceRepository.existsById(id)) {
				preference.setIsNew(true);
			}

			_preferenceRepository.save(preference);
		}
	}

	private File _createSnapshot(String fileName) throws Exception {
		try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			ZipOutputStream zipOutputStream = new ZipOutputStream(
				fileOutputStream)) {

			for (WeDeployDataService weDeployDataService :
					WeDeployDataService.values()) {

				_saveWeDeployDataService(weDeployDataService, zipOutputStream);
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
	private AccountRepository _accountRepository;

	@Autowired
	private ActivityGroupRepository _activityGroupRepository;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private BlockedKeywordRepository _blockedKeywordRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private CustomAssetDashboardRepository _customAssetDashboardRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	private final Map<String, ElasticsearchInvoker> _elasticsearchInvokers =
		new HashMap<>();

	@Autowired
	private ExperimentRepository _experimentRepository;

	@Autowired
	private MembershipChangeRepository _membershipChangeRepository;

	@Autowired
	private MembershipRepository _membershipRepository;

	private Schema _naniteListSchema;

	@Autowired
	private NanitesHttp _nanitesHttp;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private PreferenceRepository _preferenceRepository;

	@Autowired
	private SegmentRepository _segmentRepository;

}