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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.postgresql.converter.helper.DataSourceFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.security.Encryptor;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.security.KeyPair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PreDestroy;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

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
		dataSource.setId(_timeOrderedUuidGenerator.generateIdAsLong());
		dataSource.setIsNew(Boolean.TRUE);
		dataSource.setName(_getDataSourceName(dataSource.getName()));

		dataSource = _dataSourceRepository.save(dataSource);

		dataSource.setIsNew(Boolean.FALSE);

		String providerType = dataSource.getProviderType();

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

	public void deleteDataSource(DataSource dataSource) throws Exception {
		_clearDataSource(dataSource);

		Long dataSourceId = dataSource.getId();

		if (dataSourceId == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to delete a data source without ID");
		}

		_deleteFieldMappings(dataSourceId);

		_dataSourceRepository.deleteById(dataSourceId);
	}

	public void deleteDataSources() throws Exception {
		for (DataSource dataSource : getDataSources()) {
			deleteDataSource(dataSource);
		}
	}

	public DataSource disconnectDataSource(Long dataSourceId) {
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

	public Page<DataSource> getDataSourcePage(
		String filterString, int page, int size, String[] sorts) {

		FilterHelper filterHelper = new FilterHelper(
			_defaultFilterStringConverterHelper, filterString,
			_dataSourceFilterStringConverterHelper);

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_dataSourceRepository.searchDataSources(filterHelper, pageRequest),
			pageRequest,
			() -> _dataSourceRepository.countDataSources(filterHelper));
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

		Page<DataSource> dataSources = _dataSourceRepository.findAll(
			_getPageable(size, sort));

		return dataSources.getContent();
	}

	public Map<Long, JSONObject> getDataSourcesJSONObjects(
			List<Individual> individuals)
		throws Exception {

		Map<Long, JSONObject> dataSourcesJSONObjects = new HashMap<>();

		for (Individual individual : individuals) {
			Set<DataSourceIndividual> dataSourceIndividuals =
				individual.getDataSourceIndividuals();

			List<Long> dataSourceIds = new ArrayList<>();

			for (DataSourceIndividual dataSourceIndividual :
					dataSourceIndividuals) {

				dataSourceIds.add(dataSourceIndividual.getDataSourceId());
			}

			dataSourcesJSONObjects.put(
				individual.getId(),
				JSONUtil.put(
					"data-sources",
					JSONUtil.toJSONArray(
						getDataSources(dataSourceIds),
						dataSource -> _objectMapper.convertValue(
							dataSource, JSONObject.class))));
		}

		return dataSourcesJSONObjects;
	}

	public Long getDefaultChannelId(Long dataSourceId) {
		Channel defaultChannel = _channelDog.fetchDefaultChannel(dataSourceId);

		if (defaultChannel == null) {
			return null;
		}

		return defaultChannel.getId();
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

		String name = existingDataSource.getName();

		BeanUtils.copyProperties(dataSource, existingDataSource);

		if (!StringUtils.equals(dataSource.getName(), name)) {
			String projectId = ProjectIdThreadLocal.getProjectId();

			_boundedExecutor.runAsync(
				() -> {
					ProjectIdThreadLocal.setProjectId(projectId);

					_fieldDog.updateDataSourceName(id, dataSource.getName());

					_individualDog.updateDataSourceName(
						id, dataSource.getName());
				});
		}

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
		Long dataSourceId, Boolean contactsSelected, Boolean sitesSelected) {

		DataSource dataSource = getDataSource(dataSourceId);

		if (contactsSelected != null) {
			dataSource.setContactsSelected(contactsSelected);
		}

		if (sitesSelected != null) {
			dataSource.setSitesSelected(sitesSelected);
		}

		return _dataSourceRepository.save(dataSource);
	}

	private void _addDefaultChannel(DataSource dataSource) {
		_channelDog.addChannel(
			Collections.singletonMap(
				dataSource.getId(), Collections.emptySet()),
			true, dataSource.getName(), true);
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

	private void _clearDataSource(DataSource dataSource) throws Exception {
		Long dataSourceId = dataSource.getId();

		_deleteData(dataSource);

		_deleteRunLogs(dataSource);

		List<Account> accounts = _accountRepository.findAllByDataSourceId(
			dataSourceId);

		accounts.forEach(account -> _accountDog.deleteAccount(account));

		Date deletionDate = dataSource.getDeletionDate();

		Long currentIndividualId = null;

		while (true) {
			List<Individual> individuals = _individualDog.searchIndividuals(
				dataSourceId, currentIndividualId, 10000);

			if (individuals.isEmpty()) {
				break;
			}

			Individual lastIndividual = individuals.get(individuals.size() - 1);

			currentIndividualId = lastIndividual.getId();

			Stream<Individual> stream = individuals.stream();

			List<Individual> singleDataSourceIndividuals = stream.filter(
				individual -> {
					Set<Individual.DataSourceIndividualPK>
						dataSourceIndividualPKs =
							individual.getDataSourceIndividualPKs();

					return dataSourceIndividualPKs.size() == 1;
				}
			).collect(
				Collectors.toList()
			);

			if (!singleDataSourceIndividuals.isEmpty()) {
				_individualDog.deleteIndividuals(
					deletionDate, singleDataSourceIndividuals);

				individuals.removeAll(singleDataSourceIndividuals);
			}

			if (!individuals.isEmpty()) {
				for (Individual individual : individuals) {
					Set<DataSourceIndividual> dataSourceIndividuals =
						individual.getDataSourceIndividuals();

					Iterator<DataSourceIndividual> iterator =
						dataSourceIndividuals.iterator();

					while (iterator.hasNext()) {
						DataSourceIndividual dataSourceIndividual =
							iterator.next();

						if (Objects.equals(
								dataSourceId,
								dataSourceIndividual.getDataSourceId())) {

							iterator.remove();

							break;
						}
					}

					individual.setDataSourceIndividuals(dataSourceIndividuals);
				}

				_individualDog.updateIndividual(
					_getEmptyDataJSONObject(dataSource), dataSource,
					individuals);
			}
		}
	}

	private void _deleteAccountReferences(Long dataSourceId) throws Exception {
		JSONArrayIterator.of(
			"individuals", _elasticsearchInvoker,
			individualJSONObject -> {
				Individual individual = _objectMapper.convertValue(
					individualJSONObject, Individual.class);

				Set<Individual.DataSourceAccountPK> dataSourceAccountPKs =
					individual.getDataSourceAccountPKs();

				Iterator<Individual.DataSourceAccountPK> iterator =
					dataSourceAccountPKs.iterator();

				while (iterator.hasNext()) {
					Individual.DataSourceAccountPK dataSourceAccountPK =
						iterator.next();

					if (Objects.equals(
							dataSourceAccountPK.getDataSourceId(),
							dataSourceId)) {

						iterator.remove();
					}
				}

				individual.setDataSourceAccountPKs(dataSourceAccountPKs);

				_individualDog.updateIndividual(individual);

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
			_csvIndividualDog.deleteCSVIndividuals(dataSourceId);
		}
		else if (providerType.equals("LIFERAY")) {
			_asahMarkerDog.deleteAsahMarker(dataSourceId.toString());

			_deleteData(
				dataSourceId, "groups", "organizations", "roles", "teams",
				"user-groups", "users");
			_deleteIndividualReferences(dataSourceId);
		}
		else if (providerType.equals("SALESFORCE")) {
			_asahMarkerDog.deleteAsahMarker(dataSourceId.toString());

			_deleteAccountReferences(dataSourceId);

			_fieldDog.deleteFields(dataSourceId);
			_salesforceEntityDog.deleteSalesforceEntities(dataSourceId);
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Raw data for data source " + dataSourceId +
					" with unknown provider type " + providerType +
						" will not be deleted");
		}
	}

	private void _deleteData(Long dataSourceId, String... collectionNames) {
		for (String collectionName : collectionNames) {
			if (DXPEntity.Type.ofCollectionName(collectionName) == null) {
				continue;
			}

			_dxpEntityDog.deleteByFieldNameEqualsAndType(
				"dataSourceId", dataSourceId,
				DXPEntity.Type.ofCollectionName(collectionName));

			if (StringUtils.equals(collectionName, "organizations")) {
				_elasticsearchInvoker.deleteByQuery(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId)),
					true, collectionName);
			}
		}
	}

	private void _deleteFieldMappings(Long dataSourceId) {
		List<FieldMapping> fieldMappings = _fieldMappingDog.getFieldMappings(
			dataSourceId);

		_elasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.existsQuery("dataSourceFieldNames." + dataSourceId),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.dataSourceFieldNames.remove(params.dataSourceId)",
				Collections.singletonMap(
					"dataSourceId", String.valueOf(dataSourceId))),
			"field-mappings");

		List<Long> disabledFieldMappingIds = new ArrayList<>();

		for (FieldMapping fieldMapping : fieldMappings) {
			Long fieldMappingId = fieldMapping.getId();

			if (fieldMappingId != null) {
				fieldMapping = _fieldMappingDog.fetchFieldMapping(
					fieldMappingId);
			}

			if (fieldMapping == null) {
				continue;
			}

			Map<String, String> dataSourceFieldNames =
				fieldMapping.getDataSourceFieldNames();

			if (dataSourceFieldNames.isEmpty() &&
				_fieldMappingDog.deleteFieldMapping(fieldMapping)) {

				disabledFieldMappingIds.add(fieldMapping.getId());
			}
		}

		_segmentDog.disableDynamicSegments(
			dataSourceId, disabledFieldMappingIds);
	}

	private void _deleteIndividualReferences(Long dataSourceId)
		throws Exception {

		for (String collectionName :
				Arrays.asList(
					"groups", "organizations", "roles", "teams",
					"user-groups")) {

			DXPEntity.Type dxpEntityType = DXPEntity.Type.ofCollectionName(
				collectionName);

			_deleteIndividualReferences(
				dxpEntityType.getIdFieldName(),
				_getDataIds(collectionName, dataSourceId));
		}
	}

	private void _deleteIndividualReferences(String fieldName, List<Long> ids)
		throws Exception {

		JSONArrayIterator.of(
			"individuals", _elasticsearchInvoker,
			individualJSONObject -> {
				JSONArray idsJSONArray = individualJSONObject.getJSONArray(
					fieldName);

				Iterator<Object> iterator = idsJSONArray.iterator();

				while (iterator.hasNext()) {
					String id = (String)iterator.next();

					if (ids.contains(Long.parseLong(id))) {
						iterator.remove();
					}
				}

				individualJSONObject.put(fieldName, idsJSONArray);

				_individualDog.updateIndividual(
					individualJSONObject.getLong("id"),
					_objectMapper.convertValue(
						individualJSONObject, Individual.class),
					false);

				return null;
			}
		).setQueryBuilder(
			QueryBuilders.termsQuery(fieldName, ids)
		).iterate();
	}

	private void _deleteRunLogs(DataSource dataSource) {
		_runLogDog.deleteRunLogs(
			dataSource.getId(), WeDeployDataService.OSB_ASAH_FARO_INFO);

		String providerType = dataSource.getProviderType();

		if (providerType.equals("LIFERAY")) {
			_runLogDog.deleteRunLogs(
				dataSource.getId(), WeDeployDataService.OSB_ASAH_DXP_RAW);
		}
		else if (providerType.equals("SALESFORCE")) {
			_runLogDog.deleteRunLogs(
				dataSource.getId(),
				WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);
		}
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
	}

	private List<Long> _getDataIds(String collectionName, Long dataSourceId) {
		List<? extends DXPEntity> dxpEntities =
			_dxpEntityDog.findByFieldsAndType(
				Collections.singletonMap("dataSourceId", dataSourceId),
				DXPEntity.Type.ofCollectionName(collectionName));

		Stream<? extends DXPEntity> stream = dxpEntities.stream();

		return stream.map(
			DXPEntity::getId
		).collect(
			Collectors.toList()
		);
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

		if (providerType.equals("LIFERAY")) {
			return JSONUtil.put("contact", new JSONObject());
		}

		if (!providerType.equals("SALESFORCE") && _log.isWarnEnabled()) {
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
	private AccountDog _accountDog;

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(10, 1);

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private CSVIndividualDog _csvIndividualDog;

	@Autowired
	private DataSourceFilterStringConverterHelper
		_dataSourceFilterStringConverterHelper;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private final DefaultFilterStringConverterHelper
		_defaultFilterStringConverterHelper =
			new DefaultFilterStringConverterHelper();

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private Encryptor _encryptor;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RunLogDog _runLogDog;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

	@Autowired
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

	@Autowired
	private SegmentDog _segmentDog;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}