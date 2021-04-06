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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoAccountDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.ChannelDataSource;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.security.Encryptor;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.security.KeyPair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 * @author André Miranda
 */
@Component
public class DataSourceDog {

	public DataSource addDataSource(DataSource dataSource) {
		dataSource.setName(_getDataSourceName(dataSource.getName()));

		String providerType = dataSource.getProviderType();

		dataSource = _dataSourceRepository.save(dataSource);

		if (Objects.equals(providerType, "CSV")) {
			dataSource.setState("READY");
		}
		else if (Objects.equals(providerType, "LIFERAY")) {
			_addDefaultChannel(dataSource);
			_updateTokenDataSourceCredentials(dataSource);
		}
		else if (Objects.equals(providerType, "SALESFORCE")) {
			_salesforceExtractorConfigurationDog.addConfiguration(dataSource);
		}

		return _dataSourceRepository.save(dataSource);
	}

	public void deleteDataSource(
			DataSource dataSource,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		_clearDataSource(
			dataSource, processedCountMonitorConsumer, queueMonitorConsumer);

		Long dataSourceId = dataSource.getId();

		if (dataSourceId == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to delete a data source without ID");
		}

		_deleteFieldMappings(
			dataSourceId, processedCountMonitorConsumer, queueMonitorConsumer);

		_dataSourceRepository.deleteById(dataSourceId);

		if (Objects.equals(dataSource.getProviderType(), "LIFERAY") ||
			(dataSource.getEnableAllSites() != null)) {

			_nanitesHttp.refreshAnalytics();
		}
	}

	public void deleteDataSources() throws Exception {
		for (DataSource dataSource : getDataSources()) {
			deleteDataSource(dataSource, null, null);
		}
	}

	public DataSource disconnectDataSource(Long dataSourceId) throws Exception {
		DataSource dataSource = getDataSource(dataSourceId);

		if (Objects.equals(dataSource.getState(), "DISCONNECTED") &&
			Objects.equals(dataSource.getStatus(), "INACTIVE")) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Data source already disconnected");
		}

		_clearChannels(dataSourceId);

		dataSource.setContactsSelected(false);
		dataSource.setFaroBackendSecuritySignature(null);
		dataSource.setPrivateKey(null);
		dataSource.setPublicKey(null);
		dataSource.setSitesSelected(false);
		dataSource.setState("DISCONNECTED");
		dataSource.setStatus("INACTIVE");

		return _dataSourceRepository.save(dataSource);
	}

	public boolean existsDataSource(String faroBackendSecuritySignature) {
		return _dataSourceRepository.existsByFaroBackendSecuritySignature(
			faroBackendSecuritySignature);
	}

	public DataSource fetchDataSource(Long dataSourceId) {
		Optional<DataSource> dataSourceOptional =
			_dataSourceRepository.findById(dataSourceId);

		return dataSourceOptional.orElse(null);
	}

	public Long getChannelId(Long dataSourceId) {
		DataSource dataSource = fetchDataSource(dataSourceId);

		if (dataSource == null) {
			return null;
		}

		return dataSource.getChannelId();
	}

	public DataSource getDataSource(Long dataSourceId) {
		Optional<DataSource> dataSourceOptional =
			_dataSourceRepository.findById(dataSourceId);

		return dataSourceOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no data source with ID " + dataSourceId));
	}

	public String getDataSourceName(Long dataSourceId) {
		DataSource dataSource = getDataSource(dataSourceId);

		return dataSource.getName();
	}

	public List<DataSource> getDataSources() {
		return IterableUtils.toList(_dataSourceRepository.findAll());
	}

	public List<DataSource> getDataSources(List<Long> dataSourceIds) {
		return IterableUtils.toList(
			_dataSourceRepository.findAllById(dataSourceIds));
	}

	public List<DataSource> getDataSources(String providerType) {
		return _dataSourceRepository.findByProviderType(providerType);
	}

	public List<DataSource> getDataSources(String providerType, String status) {
		return _dataSourceRepository.findByProviderTypeAndStatus(
			providerType, status);
	}

	public List<DataSource> getDataSources(
		String credentialType, String providerType, Integer size, Sort sort) {

		if ((credentialType != null) && (providerType != null)) {
			return _dataSourceRepository.findByCredentialTypeAndProviderType(
				credentialType, providerType, _getPageable(size, sort));
		}

		if (credentialType != null) {
			return _dataSourceRepository.findByCredentialType(
				credentialType, _getPageable(size, sort));
		}

		if (providerType != null) {
			return _dataSourceRepository.findByProviderType(
				providerType, _getPageable(size, sort));
		}

		return _dataSourceRepository.findAll(_getPageable(size, sort));
	}

	public Page<DataSource> getDataSourcesPage(
		String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_dataSourceRepository.searchDataSources(filterString, pageRequest),
			pageRequest,
			() -> _dataSourceRepository.countDataSources(filterString));
	}

	public boolean isAnalyticsConfigured() {
		return _dataSourceRepository.existsByProviderType("LIFERAY");
	}

	public DataSource patchDataSource(DataSource dataSource) {
		Long id = dataSource.getId();

		if (id == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to patch a data source without ID");
		}

		DataSource existingDataSource = getDataSource(id);

		BeanUtils.copyProperties(dataSource, existingDataSource);

		return updateDataSourceConfiguration(existingDataSource);
	}

	public DataSource updateDataSource(DataSource dataSource) {
		return _dataSourceRepository.save(dataSource);
	}

	public DataSource updateDataSourceConfiguration(DataSource dataSource) {
		String name = dataSource.getName();

		if ((name != null) &&
			_dataSourceRepository.existsByIdNotAndName(
				dataSource.getId(), name)) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Duplicate data source name " + name);
		}

		String providerType = dataSource.getProviderType();

		if (providerType.equals("LIFERAY")) {
			_updateTokenDataSourceCredentials(dataSource);
		}
		else if (providerType.equals("SALESFORCE")) {
			_salesforceExtractorConfigurationDog.updateConfiguration(
				dataSource);
		}

		return _dataSourceRepository.save(dataSource);
	}

	public DataSource updateDataSourceDetails(
			Long dataSourceId, Boolean contactsSelected, Boolean sitesSelected)
		throws Exception {

		DataSource dataSource = getDataSource(dataSourceId);

		Boolean oldSitesSelected = dataSource.getSitesSelected();

		if (contactsSelected != null) {
			dataSource.setContactsSelected(contactsSelected);
		}

		if (sitesSelected != null) {
			dataSource.setSitesSelected(sitesSelected);
		}

		dataSource = _dataSourceRepository.save(dataSource);

		if (BooleanUtils.isTrue(dataSource.getSitesSelected()) &&
			BooleanUtils.isNotTrue(oldSitesSelected)) {

			_nanitesHttp.refreshAnalytics();

			_asahTaskDog.scheduleAsahTask(
				"IndividualSegmentActivityFieldsNanite", null);
		}

		return dataSource;
	}

	private void _addDefaultChannel(DataSource dataSource) {
		Channel channel = _channelDog.addChannel(
			Collections.singletonMap(
				dataSource.getId(), Collections.emptySet()),
			dataSource.getName(), true);

		dataSource.setChannelId(channel.getId());
	}

	private void _clearChannels(Long dataSourceId) {
		for (Channel channel : _channelDog.getChannels(dataSourceId)) {
			Set<ChannelDataSource> channelDataSources = Stream.of(
				channel
			).map(
				Channel::getChannelDataSources
			).flatMap(
				Set::stream
			).filter(
				channelDataSource -> !Objects.equals(
					channelDataSource.getDataSourceId(), dataSourceId)
			).collect(
				Collectors.toSet()
			);

			channel.setChannelDataSources(channelDataSources);

			_channelDog.update(channel);
		}
	}

	private void _clearDataSource(
			DataSource dataSource,
			Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		Long dataSourceId = dataSource.getId();

		_deleteData(dataSource);

		_deleteRunLogs(dataSource);

		JSONArrayIterator.of(
			"accounts", _elasticsearchInvoker,
			accountJSONObject -> {
				try {
					_faroInfoAccountDog.deleteAccount(accountJSONObject);
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId))
		).iterate();

		Date deletionDate = dataSource.getDeletionDate();

		JSONArrayIterator.of(
			"individuals", _elasticsearchInvoker,
			individualJSONObject -> {
				try {
					JSONArray dataSourceIndividualPKsJSONArray =
						individualJSONObject.getJSONArray(
							"dataSourceIndividualPKs");

					if (dataSourceIndividualPKsJSONArray.length() == 1) {
						_faroInfoIndividualDog.deleteIndividual(
							deletionDate, individualJSONObject.getString("id"));
					}
					else {
						_faroInfoIndividualDog.removeDataSourceIndividualPKs(
							individualJSONObject, dataSourceId);

						_faroInfoIndividualDog.updateIndividual(
							null, _getEmptyDataJSONObject(dataSource),
							dataSource, individualJSONObject);
					}
				}
				catch (Exception e) {
					return e;
				}

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				QueryBuilders.termQuery(
					"dataSourceIndividualPKs.dataSourceId",
					String.valueOf(dataSourceId)),
				ScoreMode.None)
		).iterate();
	}

	private void _deleteAccountReferences(Long dataSourceId) throws Exception {
		JSONArrayIterator.of(
			"individuals", _elasticsearchInvoker,
			individualJSONObject -> {
				JSONObject modifiedJSONObject = new JSONObject();

				JSONArray dataSourceAccountPKsJSONArray =
					individualJSONObject.getJSONArray("dataSourceAccountPKs");

				Iterator<Object> iterator =
					dataSourceAccountPKsJSONArray.iterator();

				while (iterator.hasNext()) {
					JSONObject jsonObject = (JSONObject)iterator.next();

					if (Objects.equals(
							jsonObject.getLong("dataSourceId"), dataSourceId)) {

						iterator.remove();
					}
				}

				modifiedJSONObject.put(
					"dataSourceAccountPKs", dataSourceAccountPKsJSONArray);

				_elasticsearchInvoker.update(
					"individuals", individualJSONObject.getString("id"),
					modifiedJSONObject);

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.nestedQuery(
				"dataSourceAccountPKs",
				QueryBuilders.termQuery(
					"dataSourceAccountPKs.dataSourceId",
					String.valueOf(dataSourceId)),
				ScoreMode.None)
		).iterate();
	}

	private void _deleteData(DataSource dataSource) throws Exception {
		Long dataSourceId = dataSource.getId();

		String providerType = dataSource.getProviderType();

		if (providerType.equals("CSV")) {
			_deleteData(
				dataSourceId, "dataSourceId", _elasticsearchInvoker,
				"csv-individuals");
		}
		else if (providerType.equals("LIFERAY")) {
			_deleteData(
				dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker, "OSBAsahMarkers", "audit-events",
				"groups", "organizations", "roles", "teams", "user-groups",
				"users");
			_deleteData(
				dataSourceId, "dataSourceId", _elasticsearchInvoker,
				"organizations");
			_deleteIndividualReferences(dataSourceId);
		}
		else if (providerType.equals("SALESFORCE")) {
			_deleteAccountReferences(dataSourceId);
			_deleteData(
				dataSourceId, "osbAsahDataSourceId",
				_salesforceElasticsearchInvoker, "Account", "Contact", "Lead",
				"OSBAsahMarkers", "individuals");
			_deleteData(
				dataSourceId, "dataSourceId", _elasticsearchInvoker, "fields");
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Raw data for data source " + dataSourceId +
					" with unknown provider type " + providerType +
						" will not be deleted");
		}
	}

	private void _deleteData(
		Long dataSourceId, String dataSourceIdFieldName,
		ElasticsearchInvoker elasticsearchInvoker, String... collectionNames) {

		elasticsearchInvoker.deleteByQuery(
			QueryBuilders.termQuery(
				dataSourceIdFieldName, String.valueOf(dataSourceId)),
			true, collectionNames);
	}

	private void _deleteFieldMappings(
			Long dataSourceId, Consumer<Integer> processedCountMonitorConsumer,
			Consumer<Integer> queueMonitorConsumer)
		throws Exception {

		List<Long> disabledFieldMappingIds = new ArrayList<>();

		JSONArrayIterator.of(
			"field-mappings", _elasticsearchInvoker,
			fieldMappingJSONObject -> {
				fieldMappingJSONObject =
					_faroInfoFieldMappingDog.removeDataSourceFieldName(
						fieldMappingJSONObject, dataSourceId);

				JSONObject dataSourceFieldNamesJSONObject =
					fieldMappingJSONObject.getJSONObject(
						"dataSourceFieldNames");

				if ((dataSourceFieldNamesJSONObject.length() == 0) &&
					_faroInfoFieldMappingDog.deleteFieldMapping(
						fieldMappingJSONObject)) {

					disabledFieldMappingIds.add(
						fieldMappingJSONObject.getLong("id"));
				}

				return null;
			}
		).setMonitoringConsumers(
			processedCountMonitorConsumer, queueMonitorConsumer
		).setQueryBuilder(
			QueryBuilders.existsQuery("dataSourceFieldNames." + dataSourceId)
		).iterate();

		_segmentDog.disableDynamicSegments(
			dataSourceId, disabledFieldMappingIds);
	}

	private void _deleteIndividualReferences(Long dataSourceId)
		throws Exception {

		_deleteIndividualReferences(
			"groupIds",
			_getDataIds(
				"groups", dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker));
		_deleteIndividualReferences(
			"organizationIds",
			_getDataIds(
				"organizations", dataSourceId, "dataSourceId",
				_elasticsearchInvoker));
		_deleteIndividualReferences(
			"roleIds",
			_getDataIds(
				"roles", dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker));
		_deleteIndividualReferences(
			"teamIds",
			_getDataIds(
				"teams", dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker));
		_deleteIndividualReferences(
			"userGroupIds",
			_getDataIds(
				"user-groups", dataSourceId, "osbAsahDataSourceId",
				_dxpRawElasticsearchInvoker));
	}

	private void _deleteIndividualReferences(String fieldName, List<String> ids)
		throws Exception {

		JSONArrayIterator.of(
			"individuals", _elasticsearchInvoker,
			individualJSONObject -> {
				JSONObject modifiedJSONObject = new JSONObject();

				JSONArray idsJSONArray = individualJSONObject.getJSONArray(
					fieldName);

				Iterator<Object> iterator = idsJSONArray.iterator();

				while (iterator.hasNext()) {
					String id = (String)iterator.next();

					if (ids.contains(id)) {
						iterator.remove();
					}
				}

				modifiedJSONObject.put(fieldName, idsJSONArray);

				_elasticsearchInvoker.update(
					"individuals", individualJSONObject.getString("id"),
					modifiedJSONObject);

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.termsQuery(fieldName, ids)
		).iterate();
	}

	private void _deleteRunLogs(DataSource dataSource) {
		QueryBuilder queryBuilder = QueryBuilders.termQuery(
			"dataSourceId", String.valueOf(dataSource.getId()));

		_elasticsearchInvoker.delete("run-logs", queryBuilder);

		String providerType = dataSource.getProviderType();

		if (providerType.equals("LIFERAY")) {
			_dxpRawElasticsearchInvoker.delete("run-logs", queryBuilder);
		}
		else if (providerType.equals("SALESFORCE")) {
			_salesforceElasticsearchInvoker.delete("run-logs", queryBuilder);
		}
	}

	private List<String> _getDataIds(
		String collectionName, Long dataSourceId, String dataSourceIdFieldName,
		ElasticsearchInvoker elasticsearchInvoker) {

		return JSONUtil.toStringList(
			elasticsearchInvoker.get(
				collectionName,
				QueryBuilders.termQuery(
					dataSourceIdFieldName, String.valueOf(dataSourceId))),
			"id");
	}

	private String _getDataSourceName(String name) {
		int nameCount = 0;
		String originalName = name;

		while (_dataSourceRepository.existsByName(name)) {
			name = String.format("%s (%d)", originalName, ++nameCount);
		}

		return name;
	}

	private JSONObject _getEmptyDataJSONObject(DataSource dataSource) {
		String providerType = dataSource.getProviderType();

		if (providerType.equals("CSV")) {
			return JSONUtil.put("fields", new JSONObject());
		}
		else if (providerType.equals("LIFERAY")) {
			return JSONUtil.put("contact", new JSONObject());
		}
		else if (providerType.equals("SALESFORCE")) {
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Invalid provider type " + providerType + " for data source " +
					dataSource.getId());
		}

		return new JSONObject();
	}

	private Pageable _getPageable(Integer size, Sort sort) {
		if (size == null) {
			size = 10;
		}

		if (sort == null) {
			sort = Sort.unsorted();
		}

		return PageRequest.of(0, size, sort);
	}

	private Sort _getSort(String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return Sort.by(Sort.Order.asc("id"));
		}

		List<Sort.Order> orders = new ArrayList<>();

		for (int i = 0; i < (sorts.length - 1); i = i + 2) {
			String sort = sorts[i];

			if (sort.contains("credentials")) {
				sort = "credentialType";
			}

			if (sort.contains("dateCreated")) {
				sort = "createDate";
			}
			else if (sort.contains("provider")) {
				sort = "providerType";
			}

			if (Objects.equals(sorts[i + 1], "asc")) {
				orders.add(Sort.Order.asc(sort));
			}
			else {
				orders.add(Sort.Order.desc(sort));
			}
		}

		return Sort.by(orders);
	}

	private void _updateTokenDataSourceCredentials(DataSource dataSource) {
		if (!Objects.equals(
				dataSource.getCredentialType(), "Token Authentication")) {

			return;
		}

		dataSource.setState("CREDENTIALS_VALID");
		dataSource.setStatus("ACTIVE");

		try {
			if (StringUtils.isBlank(dataSource.getPrivateKey())) {
				KeyPair keyPair = _encryptor.generateKeyPair();

				dataSource.setPrivateKey(
					_encryptor.encrypt(
						dataSource.getURL(),
						_encryptor.encode(keyPair.getPrivate())));
				dataSource.setPublicKey(_encryptor.encode(keyPair.getPublic()));
			}

			if (StringUtils.isBlank(
					dataSource.getFaroBackendSecuritySignature())) {

				dataSource.setFaroBackendSecuritySignature(
					String.valueOf(UUID.randomUUID()));
			}
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(DataSourceDog.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private Encryptor _encryptor;

	@Autowired
	private FaroInfoAccountDog _faroInfoAccountDog;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private NanitesHttp _nanitesHttp;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceElasticsearchInvoker;

	@Autowired
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

	@Autowired
	private SegmentDog _segmentDog;

}