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

package com.liferay.osb.asah.common.elasticsearch.impl;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.elasticsearch.ClientUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.io.InputStream;

import java.nio.charset.Charset;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.exists.AliasesExistResponse;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.template.get.GetIndexTemplatesRequestBuilder;
import org.elasticsearch.action.admin.indices.template.get.GetIndexTemplatesResponse;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequestBuilder;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.cluster.metadata.AliasMetadata;
import org.elasticsearch.cluster.metadata.IndexTemplateMetadata;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class ElasticsearchIndexManagerImpl
	implements ElasticsearchIndexManager {

	@Override
	public void addAlias(String indexAlias, String indexName) {
		if (_log.isInfoEnabled()) {
			_log.info("Adding alias " + indexAlias);
		}

		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		try {
			ClientUtil.waitForConnection(_client);

			ActionFuture<AcknowledgedResponse> actionFuture =
				indicesAdminClient.aliases(
					new IndicesAliasesRequest() {
						{
							addAliasAction(
								new AliasActions(AliasActions.Type.ADD) {
									{
										alias(indexAlias);
										index(indexName);
									}
								});
						}
					});

			actionFuture.get();
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to add alias for " + indexName);
			}
		}
	}

	@Override
	public void addTemplates() {
		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		Map<String, JSONObject> configurationJSONObjects = new HashMap<>();

		for (WeDeployDataService weDeployDataService :
				WeDeployDataService.values()) {

			JSONArray collectionNamesJSONArray =
				_indicesJSONObject.optJSONArray(weDeployDataService.toString());

			for (int i = 0; i < collectionNamesJSONArray.length(); i++) {
				String collectionName = collectionNamesJSONArray.getString(i);

				configurationJSONObjects.put(
					weDeployDataService.toString() + "_" + collectionName,
					new JSONObject(
						readIndexConfiguration(
							collectionName, weDeployDataService)));
			}
		}

		Set<String> templateNames = configurationJSONObjects.keySet();

		for (IndexTemplateMetadata indexTemplateMetadata :
				_getTemplates(
					indicesAdminClient, templateNames.toArray(new String[0]))) {

			Integer version = indexTemplateMetadata.getVersion();

			if ((version != null) &&
				(version >= ReleaseInfo.getSchemaVersion())) {

				configurationJSONObjects.remove(
					indexTemplateMetadata.getName());
			}
		}

		for (Map.Entry<String, JSONObject> entry :
				configurationJSONObjects.entrySet()) {

			String templateName = entry.getKey();
			JSONObject configurationJSONObject = entry.getValue();

			_addTemplate(
				indicesAdminClient, templateName,
				Collections.singletonList("*_" + templateName),
				configurationJSONObject.toMap());
		}
	}

	@Override
	public boolean aliasExists(String indexAlias) {
		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		ClientUtil.waitForConnection(_client);

		ActionFuture<AliasesExistResponse> actionFuture =
			indicesAdminClient.aliasesExist(new GetAliasesRequest(indexAlias));

		AliasesExistResponse aliasesExistResponse = actionFuture.actionGet();

		return aliasesExistResponse.exists();
	}

	@Override
	public void checkIndices() {
		if (_environment.acceptsProfiles("test")) {
			_setIndexTestTemplate();
		}

		for (WeDeployDataService weDeployDataService :
				WeDeployDataService.values()) {

			JSONArray collectionNamesJSONArray =
				_indicesJSONObject.optJSONArray(weDeployDataService.toString());

			if (collectionNamesJSONArray == null) {
				continue;
			}

			for (int i = 0; i < collectionNamesJSONArray.length(); i++) {
				String collectionName = collectionNamesJSONArray.getString(i);

				String indexAlias = _getIndexAlias(
					weDeployDataService, collectionName);

				if (aliasExists(indexAlias)) {
					_aliases.put(
						getIndexName(collectionName, weDeployDataService),
						indexAlias);
				}
			}
		}
	}

	@Override
	public void clear(String... indexAliases) {
		for (String indexAlias : indexAliases) {
			String indexName = getIndexName(indexAlias);

			if (!exists(indexName)) {
				continue;
			}

			_clearIndex(indexName);
		}
	}

	@Override
	public void clearIndices() {
		for (WeDeployDataService weDeployDataService :
				WeDeployDataService.values()) {

			_clearIndices(weDeployDataService);
		}
	}

	@Override
	public void create(
		boolean addAlias, String configurationSource, String indexName) {

		if (_log.isInfoEnabled()) {
			_log.info("Creating index " + indexName);
		}

		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		CreateIndexRequestBuilder createIndexRequestBuilder =
			indicesAdminClient.prepareCreate(indexName);

		createIndexRequestBuilder.setSource(
			configurationSource, XContentType.JSON);

		ClientUtil.waitForConnection(_client);

		createIndexRequestBuilder.get();

		if (addAlias) {
			addAlias(indexName + "_alias", indexName);
		}
	}

	@Override
	public void create(String configurationSource, String indexName) {
		create(false, configurationSource, indexName);
	}

	@Override
	public void delete(String... indexNames) {
		for (String indexName : indexNames) {
			if (!exists(indexName)) {
				continue;
			}

			_deleteIndex(indexName);
		}
	}

	@Override
	public boolean exists(String indexName) {
		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		IndicesExistsRequestBuilder indicesExistsRequestBuilder =
			indicesAdminClient.prepareExists(indexName);

		ClientUtil.waitForConnection(_client);

		IndicesExistsResponse indicesExistsResponse =
			indicesExistsRequestBuilder.get();

		return indicesExistsResponse.isExists();
	}

	@Override
	public Map<String, String> getAliases() {
		return _aliases;
	}

	@Override
	public JSONArray getCollectionsJSONArray(
		WeDeployDataService weDeployDataService) {

		return _indicesJSONObject.optJSONArray(weDeployDataService.toString());
	}

	@Override
	public Map<String, Object> getIndexMappings(
		String collectionName, WeDeployDataService weDeployDataService) {

		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		ClientUtil.waitForConnection(_client);

		String indexName = getIndexName(collectionName, weDeployDataService);

		ActionFuture<GetMappingsResponse> actionFuture =
			indicesAdminClient.getMappings(
				new GetMappingsRequest() {
					{
						indices(indexName);
					}
				});

		GetMappingsResponse getMappingsResponse = actionFuture.actionGet();

		ImmutableOpenMap<String, ImmutableOpenMap<String, MappingMetadata>>
			mappings = getMappingsResponse.getMappings();

		ImmutableOpenMap<String, MappingMetadata> immutableOpenMap =
			mappings.get(indexName);

		MappingMetadata mappingMetadata = immutableOpenMap.get(collectionName);

		return mappingMetadata.getSourceAsMap();
	}

	public String getIndexName(String indexAlias) {
		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		ClientUtil.waitForConnection(_client);

		ActionFuture<GetAliasesResponse> actionFuture =
			indicesAdminClient.getAliases(new GetAliasesRequest(indexAlias));

		GetAliasesResponse getAliasesResponse = actionFuture.actionGet();

		ImmutableOpenMap<String, List<AliasMetadata>> immutableOpenMap =
			getAliasesResponse.getAliases();

		Iterator<String> iterator = immutableOpenMap.keysIt();

		while (iterator.hasNext()) {
			String indexName = iterator.next();

			List<AliasMetadata> aliasMetadatas = immutableOpenMap.get(
				indexName);

			if (aliasMetadatas == null) {
				continue;
			}

			for (AliasMetadata aliasMetadata : aliasMetadatas) {
				if (StringUtils.equals(indexAlias, aliasMetadata.alias())) {
					return indexName;
				}
			}
		}

		return indexAlias.replace("_alias", "");
	}

	@Override
	public String getIndexName(
		String collectionName, WeDeployDataService weDeployDataService) {

		return ElasticsearchIndexUtil.getIndexName(
			collectionName, getIndexNamespace(weDeployDataService));
	}

	@Override
	public String getIndexNamespace(WeDeployDataService weDeployDataService) {
		String lcpProjectId = ServiceConstants.LCP_PROJECT_ID;

		if (_environment.acceptsProfiles("test")) {
			lcpProjectId = "test";
		}

		return lcpProjectId + "_" + weDeployDataService.toString();
	}

	@Override
	public String readIndexConfiguration(
		String collectionName, WeDeployDataService weDeployDataService) {

		try {
			String resourceName =
				StringUtils.replace(collectionName.toLowerCase(), "-", "_") +
					"_index_configuration.json";

			return ResourceUtil.readResourceToString(
				"com/liferay/osb/asah/common/elasticsearch/dependencies/" +
					weDeployDataService.toString() + "/" + resourceName);
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public boolean updateMapping(
		String collectionName, String mappingSource, String mappingType,
		WeDeployDataService weDeployDataService) {

		String indexName = ElasticsearchIndexUtil.getIndexName(
			collectionName, getIndexNamespace(weDeployDataService));

		String indexAlias = ElasticsearchIndexUtil.getIndexAlias(
			collectionName, getIndexNamespace(weDeployDataService));

		if (aliasExists(indexAlias)) {
			indexName = getIndexName(indexAlias);
		}

		if (!exists(indexName)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to find index " + indexName);
			}

			return false;
		}

		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		PutMappingRequestBuilder putMappingRequestBuilder =
			indicesAdminClient.preparePutMapping(indexName);

		putMappingRequestBuilder.setSource(mappingSource, XContentType.JSON);
		putMappingRequestBuilder.setType(mappingType);

		ClientUtil.waitForConnection(_client);

		AcknowledgedResponse acknowledgedResponse =
			putMappingRequestBuilder.get();

		return acknowledgedResponse.isAcknowledged();
	}

	private void _addTemplate(
		IndicesAdminClient indicesAdminClient, String templateName,
		List<String> templatePatterns, Map<String, Object> templateSource) {

		PutIndexTemplateRequestBuilder putIndexTemplateRequestBuilder =
			indicesAdminClient.preparePutTemplate(templateName);

		putIndexTemplateRequestBuilder.addAlias(new Alias("{index}_alias"));

		if (templatePatterns != null) {
			putIndexTemplateRequestBuilder.setPatterns(templatePatterns);
		}

		if (templateSource != null) {
			putIndexTemplateRequestBuilder.setSource(templateSource);
		}

		putIndexTemplateRequestBuilder.setVersion(
			ReleaseInfo.getSchemaVersion());

		ClientUtil.waitForConnection(_client);

		putIndexTemplateRequestBuilder.get();
	}

	private void _clearIndex(String indexName) {
		DeleteByQueryRequestBuilder deleteByQueryRequestBuilder =
			new DeleteByQueryRequestBuilder(
				_adminClient.indices(), DeleteByQueryAction.INSTANCE);

		deleteByQueryRequestBuilder = deleteByQueryRequestBuilder.filter(
			QueryBuilders.matchAllQuery());

		deleteByQueryRequestBuilder.refresh(true);
		deleteByQueryRequestBuilder.source(indexName);

		ClientUtil.waitForConnection(_client);

		deleteByQueryRequestBuilder.get();
	}

	private void _clearIndices(WeDeployDataService weDeployDataService) {
		JSONArray jsonArray = _indicesJSONObject.optJSONArray(
			weDeployDataService.toString());

		if (jsonArray == null) {
			return;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			String indexAlias = _getIndexAlias(
				weDeployDataService, jsonArray.getString(i));

			clear(indexAlias);
		}
	}

	private void _deleteIndex(String indexName) {
		if (_log.isInfoEnabled()) {
			_log.info("Deleting index " + indexName);
		}

		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		DeleteIndexRequestBuilder deleteIndexRequestBuilder =
			indicesAdminClient.prepareDelete(indexName);

		ClientUtil.waitForConnection(_client);

		deleteIndexRequestBuilder.get();
	}

	private String _getIndexAlias(
		WeDeployDataService weDeployDataService, String collectionName) {

		return ElasticsearchIndexUtil.getIndexAlias(
			collectionName, getIndexNamespace(weDeployDataService));
	}

	private List<IndexTemplateMetadata> _getTemplates(
		IndicesAdminClient indicesAdminClient, String... templateNames) {

		GetIndexTemplatesRequestBuilder getIndexTemplatesRequestBuilder =
			indicesAdminClient.prepareGetTemplates(templateNames);

		ClientUtil.waitForConnection(_client);

		GetIndexTemplatesResponse getIndexTemplatesResponse =
			getIndexTemplatesRequestBuilder.get();

		return getIndexTemplatesResponse.getIndexTemplates();
	}

	@PostConstruct
	private void _init() {
		_client = _elasticsearchConnection.getClient();

		_adminClient = _client.admin();

		_indicesJSONObject = new JSONObject(_readIndices());
	}

	private String _readIndices() {
		try {
			Class<?> clazz = getClass();

			InputStream inputStream = clazz.getResourceAsStream(
				"/elasticsearch_indices.json");

			return IOUtils.toString(inputStream, Charset.defaultCharset());
		}
		catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private void _setIndexTestTemplate() {
		IndicesAdminClient indicesAdminClient = _adminClient.indices();

		PutIndexTemplateRequestBuilder putIndexTemplateRequestBuilder =
			indicesAdminClient.preparePutTemplate("test-template");

		putIndexTemplateRequestBuilder.setSettings(
			new HashMap<String, Object>() {
				{
					put("number_of_replicas", 0);
					put("number_of_shards", 1);
				}
			});

		putIndexTemplateRequestBuilder.setPatterns(
			Collections.singletonList("*"));

		ClientUtil.waitForConnection(_client);

		putIndexTemplateRequestBuilder.get();
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchIndexManagerImpl.class);

	private AdminClient _adminClient;
	private final Map<String, String> _aliases = new ConcurrentHashMap<>();
	private Client _client;

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private Environment _environment;

	private JSONObject _indicesJSONObject;

}