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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.DataSourceIndividualRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.IndividualChannelRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class IndividualDog extends BaseFaroInfoDog {

	public boolean addDataSourceAccountPKs(
		Set<String> accountPKs, Long dataSourceId, Individual individual) {

		Set<Individual.DataSourceAccountPK> dataSourceAccountPKs =
			individual.getDataSourceAccountPKs();

		Stream<Individual.DataSourceAccountPK> stream =
			dataSourceAccountPKs.stream();

		if (stream.noneMatch(
				dataSourceAccountPK -> Objects.equals(
					dataSourceId, dataSourceAccountPK.getDataSourceId()))) {

			dataSourceAccountPKs.add(
				new Individual.DataSourceAccountPK(
					new DataSourceIndividual(
						accountPKs, dataSourceId, individual.getId(),
						Collections.emptySet())));
		}
		else {
			stream = dataSourceAccountPKs.stream();

			stream.filter(
				dataSourceAccountPK -> Objects.equals(
					dataSourceId, dataSourceAccountPK.getDataSourceId())
			).forEach(
				dataSourceAccountPK -> dataSourceAccountPK.setAccountPKs(
					accountPKs)
			);
		}

		individual.setDataSourceAccountPKs(dataSourceAccountPKs);

		return true;
	}

	public boolean addDataSourceIndividualPK(
		String dataId, Long dataSourceId, Individual individual) {

		Set<Individual.DataSourceIndividualPK> dataSourceIndividualPKs =
			individual.getDataSourceIndividualPKs();

		Stream<Individual.DataSourceIndividualPK> stream =
			dataSourceIndividualPKs.stream();

		if (stream.noneMatch(
				dataSourceIndividualPK -> Objects.equals(
					dataSourceId, dataSourceIndividualPK.getDataSourceId()))) {

			dataSourceIndividualPKs.add(
				new Individual.DataSourceIndividualPK(
					new DataSourceIndividual(
						Collections.emptySet(), dataSourceId,
						individual.getId(), Collections.singleton(dataId))));
		}
		else {
			Map<Long, Set<String>> individualPKsMap =
				FaroInfoIndividualUtil.getIndividualPKs(
					individual.getDataSourceIndividualPKs());

			Set<String> individualPKs = individualPKsMap.get(dataSourceId);

			if (individualPKs.contains(dataId)) {
				return false;
			}

			individualPKs.add(dataId);

			stream = dataSourceIndividualPKs.stream();

			stream.filter(
				dataSourceIndividualPK -> Objects.equals(
					dataSourceId, dataSourceIndividualPK.getDataSourceId())
			).forEach(
				dataSourceIndividualPK ->
					dataSourceIndividualPK.setIndividualPKs(individualPKs)
			);
		}

		individual.setDataSourceIndividualPKs(dataSourceIndividualPKs);

		return true;
	}

	public Individual addIndividual(
		Individual individual, boolean updateMemberships) {

		Set<Field> fields = individual.getFields();

		if (CollectionUtils.isNotEmpty(fields)) {
			Stream<Field> stream = fields.stream();

			Field emailField = stream.filter(
				field -> Objects.equals(field.getName(), "email")
			).findFirst(
			).orElse(
				null
			);

			if (emailField != null) {
				String emailAddress = StringUtils.lowerCase(
					String.valueOf(emailField.getValue()));

				if (StringUtils.isNotBlank(emailAddress)) {
					individual.setEmailAddressHashed(
						DigestUtils.sha256Hex(emailAddress));
				}
			}
		}

		individual = _individualRepository.save(individual);

		if (updateMemberships) {
			_asahTaskDog.scheduleAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					"dateModified",
					DateUtil.toUTCString(individual.getModifiedDate())
				).put(
					"individualJSONObject",
					_objectMapper.convertValue(individual, JSONObject.class)
				));
		}

		return populateIndividual(individual);
	}

	public Individual addIndividual(
		Long channelId, DataSource dataSource, String emailAddressHashed,
		String userId) {

		Set<Long> channelIds = new HashSet<>();

		if (Objects.nonNull(channelId)) {
			channelIds.add(channelId);
		}

		Date date = new Date();

		Individual individual = new Individual();

		individual.setChannelIds(channelIds);
		individual.setCreateDate(date);
		individual.setDataSourceIndividuals(
			Collections.singleton(
				new DataSourceIndividual(
					Collections.emptySet(), dataSource.getId(), null,
					Collections.singleton(userId))));
		individual.setEmailAddressHashed(emailAddressHashed);
		individual.setModifiedDate(date);
		individual.setSegmentIds(Collections.emptySet());

		return addIndividual(individual, false);
	}

	public Individual addIndividual(
			String dataId, JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		List<Field> fields = _fieldDog.buildFields(
			"demographics", dataJSONObject, dataSource, null, "individual");

		Stream<Field> stream = fields.stream();

		if (stream.noneMatch(
				field -> Objects.equals(field.getName(), "email"))) {

			return null;
		}

		DataSourceIndividual dataSourceIndividual = new DataSourceIndividual();

		dataSourceIndividual.setDataSourceId(dataSource.getId());

		String providerType = dataSource.getProviderType();

		if (providerType.equals("SALESFORCE")) {
			JSONArray dataAccountPKsJSONArray = dataJSONObject.optJSONArray(
				"accountPKs");

			if (dataAccountPKsJSONArray != null) {
				dataSourceIndividual.setAccountPKs(
					JSONUtil.toStringSet(dataAccountPKsJSONArray));
			}
		}

		if (dataId != null) {
			dataSourceIndividual.setIndividualPKs(
				Collections.singleton(dataId));
		}

		Date date = new Date();

		Individual individual = new Individual();

		individual.setDataSourceIndividuals(
			Collections.singleton(dataSourceIndividual));
		individual.setCreateDate(date);
		individual.setModifiedDate(date);
		individual.setFirstEnrichmentDate(date);
		individual.setSegmentIds(Collections.emptySet());

		_updateIndividualAssociations(dataJSONObject, individual);

		individual = _individualRepository.save(individual);

		List<Field> customFields = _fieldDog.addFields(
			"custom", dataJSONObject, dataSource, individual.getId(),
			"individual");

		individual.setCustomFields(new HashSet<>(customFields));

		fields = _fieldDog.addFields(
			"demographics", dataJSONObject, dataSource, individual.getId(),
			"individual");

		individual.setFields(new HashSet<>(fields));

		return addIndividual(individual, true);
	}

	public Individual addIndividualAssociation(
		DXPEntity.Type dxpEntityType, Long id, Individual individual) {

		Set<Long> associatedIds = new HashSet<>();

		associatedIds.add(id);

		if (dxpEntityType.isGroup()) {
			associatedIds.addAll(individual.getGroupIds());

			individual.setGroupIds(associatedIds);
		}
		else if (dxpEntityType.isOrganization()) {
			associatedIds.addAll(individual.getOrganizationIds());

			individual.setOrganizationIds(associatedIds);
		}
		else if (dxpEntityType.isRole()) {
			associatedIds.addAll(individual.getRoleIds());

			individual.setRoleIds(associatedIds);
		}
		else if (dxpEntityType.isTeam()) {
			associatedIds.addAll(individual.getTeamIds());

			individual.setTeamIds(associatedIds);
		}
		else if (dxpEntityType.isUserGroup()) {
			associatedIds.addAll(individual.getUserGroupIds());

			individual.setUserGroupIds(associatedIds);
		}

		_individualRepository.updateAssociatedIds(
			dxpEntityType.getIndividualFieldName(), associatedIds,
			individual.getId());

		return individual;
	}

	public Individual addIndividualAssociation(
		long classPK, Long dataSourceId, DXPEntity.Type dxpEntityType,
		Individual individual) {

		if (individual == null) {
			return null;
		}

		Set<Long> associatedIds = getAssociatedIds(
			dataSourceId, dxpEntityType, Collections.singletonList(classPK));

		if (associatedIds.isEmpty()) {
			return individual;
		}

		return addIndividualAssociation(
			dxpEntityType,
			Long.valueOf(String.valueOf(CollectionUtils.get(associatedIds, 0))),
			individual);
	}

	public void addIndividualSegmentIds(
		List<Long> individualIds, Long individualSegmentId) {

		for (Long individualId : individualIds) {
			addSegmentId(individualId, individualSegmentId);
		}
	}

	public Individual addSegmentId(Long individualId, Long segmentId) {
		Individual individual = fetchIndividual(individualId);

		if (individual == null) {
			return null;
		}

		Set<Long> segmentIds = individual.getSegmentIds();

		segmentIds.add(segmentId);

		return updateIndividual(individualId, individual, false);
	}

	public long countIndividuals(List<Long> individualIds) {
		return countIndividuals(individualIds, null);
	}

	public long countIndividuals(List<Long> individualIds, String keywords) {
		return _individualRepository.countByIdsInAndKeywords(
			individualIds, keywords);
	}

	public long countIndividuals(
		Long channelId, String filterString, Boolean includeAnonymousUsers) {

		return _individualRepository.countIndividuals(
			channelId, filterString, includeAnonymousUsers, null, null);
	}

	public long countIndividuals(String query, Long segmentId) {
		return _individualRepository.countByQueryAndSegmentId(query, segmentId);
	}

	public void deleteIndividual(Date deletionDate, Long individualId) {
		_fieldRepository.deleteByOwnerIdAndOwnerType(
			individualId, "individual");

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("ownerId", individualId)
		).filter(
			QueryBuilders.termQuery("ownerType", "individual")
		);

		elasticsearchInvoker.delete("interests", boolQueryBuilder);
		elasticsearchInvoker.delete("visited-pages", boolQueryBuilder);

		_membershipDog.deactivateMemberships(deletionDate, individualId);

		_membershipChangeDog.updateMembershipChangeIndividualDeleted(
			Boolean.TRUE, individualId);

		_individualRepository.deleteById(individualId);
	}

	public Individual deleteIndividualAssociation(
		long classPK, Long dataSourceId, DXPEntity.Type dxpEntityType,
		Individual individual) {

		if (individual == null) {
			return null;
		}

		Long individualId = individual.getId();

		if (individualId == null) {
			return individual;
		}

		Set<Long> ids = new HashSet<>();

		if (dxpEntityType.isGroup()) {
			ids = individual.getGroupIds();
		}
		else if (dxpEntityType.isOrganization()) {
			ids = individual.getOrganizationIds();
		}
		else if (dxpEntityType.isRole()) {
			ids = individual.getRoleIds();
		}
		else if (dxpEntityType.isTeam()) {
			ids = individual.getTeamIds();
		}
		else if (dxpEntityType.isUserGroup()) {
			ids = individual.getUserGroupIds();
		}

		if (CollectionUtils.isEmpty(ids)) {
			return null;
		}

		Set<Long> associatedIds = getAssociatedIds(
			dataSourceId, dxpEntityType, Collections.singletonList(classPK));

		if (associatedIds.isEmpty()) {
			return individual;
		}

		ids.removeAll(associatedIds);

		_individualRepository.updateAssociatedIds(
			dxpEntityType.getIndividualFieldName(), ids, individualId);

		return fetchIndividual(individualId);
	}

	public boolean existsByChannelIdAndFilterStringAndId(
		Long channelId, String filterString, Long individualId) {

		if (channelId == null) {
			return _individualRepository.existsByFilterStringAndId(
				filterString, individualId);
		}

		return _individualRepository.existsByChannelIdAndFilterStringAndId(
			channelId, filterString, individualId);
	}

	public boolean existsByDataSourceIndividualPK(
		Long dataSourceId, Long individualId) {

		Individual individual = fetchIndividual(individualId);

		Set<Individual.DataSourceIndividualPK> dataSourceIndividualPKs =
			individual.getDataSourceIndividualPKs();

		Stream<Individual.DataSourceIndividualPK> stream =
			dataSourceIndividualPKs.stream();

		return stream.anyMatch(
			dataSourceIndividualPK -> Objects.equals(
				dataSourceId, dataSourceIndividualPK.getDataSourceId()));
	}

	public boolean existsById(Long id) {
		return _individualRepository.existsById(id);
	}

	public Individual fetchIndividual(Long individualId) {
		Optional<Individual> individualOptional =
			_individualRepository.findById(individualId);

		return populateIndividual(individualOptional.orElse(null));
	}

	public Individual fetchIndividual(Long dataSourceId, String userId) {
		Optional<Individual> individualOptional =
			_individualRepository.findByDataSourceIdAndIndividualPK(
				dataSourceId, userId);

		return populateIndividual(individualOptional.orElse(null));
	}

	public Individual
		fetchIndividualByAssociationIdNotAndDataSourceIdAndIndividualPK(
			Long associationId, Long dataSourceId, String fieldName,
			String individualPK) {

		Optional<Individual> individualOptional =
			_individualRepository.
				findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
					associationId, dataSourceId, fieldName, individualPK);

		return populateIndividual(individualOptional.orElse(null));
	}

	public Individual fetchIndividualByEmailAddress(String emailAddress) {
		Optional<Individual> individualOptional =
			_individualRepository.findByEmailAddress(emailAddress);

		return populateIndividual(individualOptional.orElse(null));
	}

	public Individual fetchIndividualByEmailAddressHashed(
		String emailAddressHashed) {

		Optional<Individual> individualOptional =
			_individualRepository.findByEmailAddressHashed(emailAddressHashed);

		return populateIndividual(individualOptional.orElse(null));
	}

	public Individual fetchIndividualByEmailAddressOrEmailAddressHashed(
		String emailAddress, String emailAddressHashed) {

		Optional<Individual> individualOptional =
			_individualRepository.findByEmailAddressOrEmailAddressHashed(
				emailAddress, emailAddressHashed);

		return populateIndividual(individualOptional.orElse(null));
	}

	public List<Individual.ActivitiesCount> getActivitiesCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		return _individualRepository.findActivitiesCounts(
			includeAnonymousUsers, segmentId);
	}

	public Set<Long> getAssociatedIds(
		Long dataSourceId, DXPEntity.Type dxpEntityType, List<Long> classPKs) {

		if (dxpEntityType.isOrganization()) {
			List<Organization> organizations =
				_organizationDog.findByDataSourceIdAndOrganizationPKIn(
					dataSourceId, classPKs);

			Stream<Organization> stream = organizations.stream();

			return stream.map(
				Organization::getId
			).collect(
				Collectors.toSet()
			);
		}

		List<? extends DXPEntity> dxpEntities =
			_dxpEntityDog.findByAfterAndFieldsAndType(
				null,
				new HashMap<String, Object>() {
					{
						put(
							"fields." + dxpEntityType.getIdFieldName(),
							classPKs);
					}
				},
				0, dxpEntityType);

		Stream<? extends DXPEntity> stream = dxpEntities.stream();

		return stream.map(
			DXPEntity::getId
		).collect(
			Collectors.toSet()
		);
	}

	public Page<Distribution> getDistributionsPage(
		String fieldName, String fieldType, @Nullable String filterString,
		int numberOfBins, int size, String[] sorts) {

		if (fieldType.equals("Number")) {
			size = numberOfBins;
		}

		PageRequest pageRequest = PageRequest.of(
			0, size,
			SortUtil.getSort(Sort.by(Sort.Order.desc("count")), sorts));

		List<Distribution> distributions =
			_individualRepository.getIndividualDistributions(
				fieldName, fieldType, filterString, pageRequest);

		return PageableExecutionUtils.getPage(
			distributions, pageRequest, distributions::size);
	}

	public List<Long> getIdsBySegmentId(Long segmentId) {
		return _individualRepository.findIdsByAnySegmentIds(segmentId);
	}

	public Individual getIndividual(Long id) {
		Optional<Individual> individualOptional =
			_individualRepository.findById(id);

		return populateIndividual(
			individualOptional.orElseThrow(
				() -> new OSBAsahException(
					HttpStatus.BAD_REQUEST,
					"There is no individual with ID " + id)));
	}

	public Individual getIndividual(Long channelId, Long individualId)
		throws Exception {

		Individual individual = fetchIndividual(individualId);

		if (individual == null) {
			throw new Exception(
				"Unable to find individual with ID " + individualId);
		}

		return populateIndividual(channelId, individual);
	}

	public Map<Long, Long> getIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		return _individualRepository.findIndividualCounts(
			includeAnonymousUsers, segmentId);
	}

	public String getIndividualName(Long individualId) {
		Individual individual = fetchIndividual(individualId);

		if (individual == null) {
			return null;
		}

		return FaroInfoIndividualUtil.getIndividualName(individual);
	}

	public List<Individual> getIndividuals(
		Long dataSourceId, int page, int size, Sort sort) {

		List<Individual> individuals = _individualRepository.findByDataSourceId(
			dataSourceId, PageRequest.of(page, size, sort));

		return ListUtil.map(individuals, this::populateIndividual);
	}

	public List<Individual> getIndividuals(
		String query, Long segmentId, int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		List<Individual> individuals =
			_individualRepository.findByQueryAndSegmentId(
				query, segmentId, pageRequest);

		return ListUtil.map(
			individuals, individual -> populateIndividual(individual));
	}

	public List<Individual> getIndividualsBySegmentId(Long segmentId) {
		List<Individual> individuals =
			_individualRepository.findByAnySegmentIds(segmentId);

		return ListUtil.map(individuals, this::populateIndividual);
	}

	public long getKnownIndividualCount(List<Long> ids) {
		return _individualRepository.countKnownIndividuals(ids);
	}

	public long getKnownIndividualCount(Long segmentId) {
		return _individualRepository.countKnownIndividuals(segmentId);
	}

	public Page<Transformation> getTransformationsPage(
		String apply, @Nullable Long channelId, @Nullable String filterString,
		Boolean includeAnonymousUsers, @Nullable Long segmentId, int page,
		int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_individualRepository.getIndividualTransformations(
				apply, channelId, filterString, includeAnonymousUsers,
				_getSegmentChannelId(segmentId), segmentId, pageRequest);

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	@PostConstruct
	public void init() {
		String[] collections = JSONUtil.toStringArray(
			_elasticsearchIndexManager.getCollectionsJSONArray(
				WeDeployDataService.OSB_ASAH_CEREBRO_INFO));

		_collections = ArrayUtils.remove(
			collections, ArrayUtils.indexOf(collections, "user-sessions"));
	}

	public Individual populateIndividual(Individual individual) {
		if (individual == null) {
			return null;
		}

		List<Field> fields = _fieldDog.getOwnerIdFields(
			"demographics", individual.getId());

		Stream<Field> stream = fields.stream();

		Set<Field> fieldSet = stream.collect(Collectors.toSet());

		individual.setDemographics(new Individual.Demographics(fieldSet));
		individual.setFields(fieldSet);

		List<Field> customFields = _fieldDog.getOwnerIdFields(
			"custom", individual.getId());

		stream = customFields.stream();

		Set<Field> customFieldSet = stream.collect(Collectors.toSet());

		individual.setCustomDemographics(
			new Individual.Demographics(customFieldSet));
		individual.setCustomFields(customFieldSet);

		List<DataSourceIndividual> dataSourceIndividuals =
			_dataSourceIndividualRepository.findByIndividualId(
				individual.getId());

		individual.setDataSourceIndividuals(
			new HashSet<>(dataSourceIndividuals));

		List<IndividualChannel> individualChannels =
			_individualChannelRepository.findByIndividualId(individual.getId());

		individual.setIndividualChannels(new HashSet<>(individualChannels));

		Set<Long> channelIds = individual.getChannelIds();

		for (IndividualChannel individualChannel : individualChannels) {
			channelIds.add(individualChannel.getChannelId());
		}

		return individual;
	}

	public Individual populateIndividual(
		Long channelId, Individual individual) {

		Individual populatedIndividual = populateIndividual(individual);

		Set<Individual.ActivitiesCount> activitiesCounts =
			individual.getActivitiesCounts();

		Stream<Individual.ActivitiesCount> activitiesCountsStream =
			activitiesCounts.stream();

		Optional<Individual.ActivitiesCount> activitiesCountOptional =
			activitiesCountsStream.filter(
				activityCount -> Objects.equals(
					activityCount.getChannelId(), channelId)
			).findFirst();

		activitiesCountOptional.ifPresent(
			individualActivitiesCount -> {
				populatedIndividual.setActivitiesCount(
					individualActivitiesCount.getActivitiesCount());
				populatedIndividual.setActivitiesCounts(
					Collections.singleton(individualActivitiesCount));
			});

		Set<Individual.LastActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		Stream<Individual.LastActivityDate> lastActivityDatesStream =
			lastActivityDates.stream();

		Optional<Individual.LastActivityDate> lastActivityDateOptional =
			lastActivityDatesStream.filter(
				lastActivityDate -> Objects.equals(
					lastActivityDate.getChannelId(), channelId)
			).findFirst();

		lastActivityDateOptional.ifPresent(
			individualLastActivityDate -> {
				populatedIndividual.setLastActivityDate(
					individualLastActivityDate.getLastActivityDate());
				populatedIndividual.setLastActivityDates(
					Collections.singleton(individualLastActivityDate));
			});

		return populatedIndividual;
	}

	public Individual removeDataSourceIndividualPKs(
		Individual individual, Long dataSourceId) {

		Set<DataSourceIndividual> dataSourceIndividuals =
			individual.getDataSourceIndividuals();

		Iterator<DataSourceIndividual> iterator =
			dataSourceIndividuals.iterator();

		while (iterator.hasNext()) {
			DataSourceIndividual dataSourceIndividual = iterator.next();

			if (Objects.equals(
					dataSourceId, dataSourceIndividual.getDataSourceId())) {

				iterator.remove();

				break;
			}
		}

		individual.setDataSourceIndividuals(dataSourceIndividuals);

		return updateIndividual(individual);
	}

	public Individual removeSegmentId(Individual individual, Long segmentId) {
		if (individual == null) {
			return null;
		}

		Long individualId = individual.getId();

		if (individualId == null) {
			return individual;
		}

		Set<Long> segmentIds = individual.getSegmentIds();

		Iterator<Long> iterator = segmentIds.iterator();

		while (iterator.hasNext()) {
			if (Objects.equals(segmentId, iterator.next())) {
				iterator.remove();

				break;
			}
		}

		return updateIndividual(individualId, individual, false);
	}

	public Individual removeSegmentId(Long individualId, Long segmentId) {
		return removeSegmentId(fetchIndividual(individualId), segmentId);
	}

	public List<Individual> searchIndividuals(
		List<Long> individualIds, String keywords, int size, int start) {

		List<Individual> individuals =
			_individualRepository.findByIdsInAndKeywords(
				individualIds, _escapeKeywords(keywords),
				PageRequest.of(start, size));

		return ListUtil.map(individuals, this::populateIndividual);
	}

	public List<Individual> searchIndividuals(
		Long channelId, Boolean includeAnonymousUsers, Long segmentId, int page,
		int size, String[] sorts) {

		return ListUtil.map(
			_individualRepository.searchIndividuals(
				channelId, null, includeAnonymousUsers,
				_getSegmentChannelId(segmentId), segmentId,
				PageRequest.of(page, size, _getSort(sorts))),
			individual -> populateIndividual(channelId, individual));
	}

	public List<Individual> searchIndividuals(
		Long channelId, String filterString, Boolean includeAnonymousUsers,
		int page, int size, String[] sorts) {

		return ListUtil.map(
			_individualRepository.searchIndividuals(
				channelId, filterString, includeAnonymousUsers, null, null,
				PageRequest.of(page, size, _getSort(sorts))),
			individual -> populateIndividual(channelId, individual));
	}

	public Page<Individual> searchIndividualsPage(
		Long channelId, String filterString, Boolean includeAnonymousUsers,
		int page, int size, String[] sorts) {

		return PageableExecutionUtils.getPage(
			searchIndividuals(
				channelId, filterString, includeAnonymousUsers, page, size,
				sorts),
			PageRequest.of(page, size, _getSort(sorts)),
			() -> countIndividuals(
				channelId, filterString, includeAnonymousUsers));
	}

	public Page<Individual> searchIndividualsPage(
		String filterString, Boolean includeAnonymousUsers, Long segmentId,
		int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		Long segmentChannelId = _getSegmentChannelId(segmentId);

		List<Individual> individuals = _individualRepository.searchIndividuals(
			null, filterString, includeAnonymousUsers, segmentChannelId,
			segmentId, pageRequest);

		return PageableExecutionUtils.getPage(
			ListUtil.map(individuals, this::populateIndividual), pageRequest,
			() -> _individualRepository.countIndividuals(
				null, filterString, includeAnonymousUsers, segmentChannelId,
				segmentId));
	}

	public void updateDynamicMemberships(Date modifiedDate, Segment segment) {
		Long individualSegmentId = segment.getId();

		_updateDynamicAddMemberships(
			segment.getChannelId(), true, segment.getFilter(),
			BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
			individualSegmentId, modifiedDate);
		updateDynamicRemoveMemberships(
			segment.getChannelId(), segment.getFilter(),
			BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
			individualSegmentId, modifiedDate);
	}

	public void updateDynamicRemoveMemberships(
		Date modifiedDate, Segment segment) {

		updateDynamicRemoveMemberships(
			segment.getChannelId(), segment.getFilter(),
			BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
			segment.getId(), modifiedDate);
	}

	public void updateDynamicRemoveMemberships(
		Long channelId, String filterString, Boolean includeAnonymousUsers,
		Long individualSegmentId, Date modifiedDate) {

		for (Membership membership :
				_membershipDog.getMemberships(individualSegmentId, "ACTIVE")) {

			if (_individualRepository.
					existsByChannelIdAndFilterStringAndIncludeAnonymousUsersAndId(
						channelId, filterString, includeAnonymousUsers,
						membership.getIndividualId())) {

				continue;
			}

			_membershipDog.deactivateMembership(modifiedDate, membership);
		}
	}

	public Individual updateIndividual(Individual individual) {
		individual = _individualRepository.save(individual);

		Long individualId = individual.getId();

		if (individualId == null) {
			return individual;
		}

		return fetchIndividual(individualId);
	}

	public Individual updateIndividual(
		Long individualId, Individual partialIndividual,
		boolean updateMemberships) {

		Individual existingIndividual = fetchIndividual(individualId);

		String oldIndividualName = null;

		if (updateMemberships) {
			oldIndividualName = getIndividualName(individualId);
		}

		_setFirstEnrichmentDate(existingIndividual);

		if (CollectionUtils.isNotEmpty(existingIndividual.getFields()) &&
			CollectionUtils.isEmpty(partialIndividual.getFields())) {

			partialIndividual.setFields(existingIndividual.getFields());
		}

		if (CollectionUtils.isNotEmpty(existingIndividual.getCustomFields()) &&
			CollectionUtils.isEmpty(partialIndividual.getCustomFields())) {

			partialIndividual.setCustomFields(
				existingIndividual.getCustomFields());
		}

		if (CollectionUtils.isNotEmpty(
				partialIndividual.getActivitiesCounts())) {

			existingIndividual.setActivitiesCounts(
				partialIndividual.getActivitiesCounts());
		}

		BeanUtils.copyProperties(partialIndividual, existingIndividual);

		Individual individual = _individualRepository.save(existingIndividual);

		if (updateMemberships) {
			_individualModified(individual, oldIndividualName);
		}

		return populateIndividual(individual);
	}

	public Individual updateIndividual(
			String dataId, JSONObject dataJSONObject, DataSource dataSource,
			Individual individual)
		throws Exception {

		Long individualId = individual.getId();

		if (individualId == null) {
			return individual;
		}

		Long dataSourceId = dataSource.getId();

		if ((dataId != null) && !dataId.equals(String.valueOf(individualId))) {
			addDataSourceIndividualPK(dataId, dataSourceId, individual);
		}

		boolean dataAccountPKsUpdated = false;

		String providerType = dataSource.getProviderType();

		if (providerType.equals("SALESFORCE")) {
			JSONArray dataAccountPKsJSONArray = dataJSONObject.optJSONArray(
				"accountPKs");

			if (dataAccountPKsJSONArray != null) {
				Set<String> accountPKs = JSONUtil.toStringSet(
					dataAccountPKsJSONArray);

				addDataSourceAccountPKs(accountPKs, dataSourceId, individual);

				dataAccountPKsUpdated = true;
			}
		}

		List<Field> previousFields = _fieldDog.getOwnerIdFields(
			"demographics", individualId);

		_fieldDog.updateFields(
			"custom", dataJSONObject, dataSource, individual, "individual",
			"demographics", "email");
		_fieldDog.updateFields(
			"demographics", dataJSONObject, dataSource, individual,
			"individual", "demographics", "email");

		List<Field> fields =
			_fieldRepository.
				findByContextAndOwnerIdGroupByMaxModifiedDateAndName(
					"demographics", individualId);

		Stream<Field> stream = fields.stream();

		individual.setFields(stream.collect(Collectors.toSet()));

		individual.setModifiedDate(new Date());

		individual = populateIndividual(_individualRepository.save(individual));

		List<Field> emailFields =
			_fieldRepository.findByContextAndNameAndOwnerIdAndOwnerType(
				"demographics", "email", individualId, "individual");

		if (emailFields.isEmpty()) {
			deleteIndividual(new Date(), individualId);

			return null;
		}

		String oldIndividualName = getIndividualName(individualId);

		_setFirstEnrichmentDate(individual);

		JSONObject fieldsJSONObject = _fieldDog.getFieldsJSONObject(
			"demographics", dataJSONObject, dataSource);

		if ((fieldsJSONObject.names() != null) &&
			((previousFields.size() != fields.size()) ||
			 !previousFields.containsAll(fields) ||
			 !fields.containsAll(previousFields) || dataAccountPKsUpdated)) {

			individual.setLastEnrichmentDate(new Date());
		}

		Stream<Field> fieldsStream = fields.stream();
		Stream<Field> previousFieldsStream = previousFields.stream();

		if (previousFieldsStream.noneMatch(
				field -> Objects.equals(field.getName(), "email")) &&
			fieldsStream.anyMatch(
				field -> Objects.equals(field.getName(), "email"))) {

			_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("individualId", individualId)
				).filter(
					BoolQueryBuilderUtil.shouldNot(
						QueryBuilders.existsQuery("knownIndividual")
					).should(
						QueryBuilders.termQuery("knownIndividual", false)
					)
				),
				true,
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					"ctx._source.knownIndividual = true;",
					Collections.emptyMap()),
				_collections);
		}

		_updateIndividualAssociations(dataJSONObject, individual);

		individual = populateIndividual(_individualRepository.save(individual));

		_individualModified(individual, oldIndividualName);

		return individual;
	}

	private String _escapeKeywords(String keywords) {
		if (StringUtils.isEmpty(keywords)) {
			return keywords;
		}

		IntStream intStream = keywords.codePoints();

		return intStream.mapToObj(
			c -> (char)c
		).map(
			c -> {
				if (_CHARACTERS_TO_BE_ESCAPED_IN_QUERY_STRING.indexOf(c) >= 0) {
					return "\\" + c;
				}

				return String.valueOf(c);
			}
		).collect(
			Collectors.joining()
		);
	}

	private Long _getSegmentChannelId(Long segmentId) {
		return Optional.ofNullable(
			segmentId
		).map(
			_segmentDog::fetchSegment
		).map(
			Segment::getChannelId
		).orElse(
			null
		);
	}

	private Sort _getSort(String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return Sort.by(Sort.Order.asc("id"));
		}

		List<Sort.Order> orders = new ArrayList<>();

		for (int i = 0; i < sorts.length; i++) {
			String sort = sorts[i];

			String order = null;

			String[] properties = sort.split(",");

			if (properties.length == 1) {
				order = sorts[++i];
			}
			else {
				order = properties[1];
			}

			if (Objects.equals(order, "asc")) {
				orders.add(Sort.Order.asc(properties[0]));
			}
			else {
				orders.add(Sort.Order.desc(properties[0]));
			}
		}

		return Sort.by(orders);
	}

	private void _individualModified(
		Individual individual, String oldIndividualName) {

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified",
				DateUtil.toUTCString(individual.getModifiedDate())
			).put(
				"individualJSONObject",
				_objectMapper.convertValue(individual, JSONObject.class)
			));

		String newIndividualName = FaroInfoIndividualUtil.getIndividualName(
			individual);

		if (!Objects.equals(oldIndividualName, newIndividualName)) {
			_membershipChangeDog.updateIndividualNameForIndividual(
				individual.getId(), newIndividualName);
		}
	}

	private void _setFirstEnrichmentDate(Individual individual) {
		if (_fieldRepository.existsByNameAndOwnerId(
				"email", individual.getId())) {

			return;
		}

		individual.setFirstEnrichmentDate(new Date());
	}

	private void _updateDynamicAddMemberships(
		Long channelId, boolean checkMemberExists, String filterString,
		Boolean includeAnonymousUsers, Long individualSegmentId,
		Date modifiedDate) {

		int page = 0;

		List<Individual> individuals = searchIndividuals(
			channelId, filterString, includeAnonymousUsers, page, 500, null);

		while (!individuals.isEmpty()) {
			for (Individual individual : individuals) {
				Long individualId = individual.getId();

				if (checkMemberExists &&
					_membershipDog.isMember(
						individualId, individualSegmentId)) {

					continue;
				}

				_membershipDog.addMembership(
					modifiedDate, individualId, individualSegmentId,
					modifiedDate, "ACTIVE");
			}

			individuals = searchIndividuals(
				channelId, filterString, includeAnonymousUsers, ++page, 500,
				null);
		}
	}

	private void _updateIndividualAssociations(
		JSONObject dataJSONObject, Individual individual) {

		JSONObject membershipsJSONObject = dataJSONObject.optJSONObject(
			"memberships");

		if (membershipsJSONObject == null) {
			return;
		}

		for (String type : membershipsJSONObject.keySet()) {
			DXPEntity.Type dxpEntityType = DXPEntity.Type.of(type);

			if (dxpEntityType == null) {
				continue;
			}

			Set<Long> associatedIds = getAssociatedIds(
				dataJSONObject.getLong("osbAsahDataSourceId"), dxpEntityType,
				JSONUtil.toLongList(membershipsJSONObject.getJSONArray(type)));

			if (dxpEntityType.isGroup()) {
				individual.setGroupIds(associatedIds);
			}
			else if (dxpEntityType.isOrganization()) {
				individual.setOrganizationIds(associatedIds);
			}
			else if (dxpEntityType.isRole()) {
				individual.setRoleIds(associatedIds);
			}
			else if (dxpEntityType.isTeam()) {
				individual.setTeamIds(associatedIds);
			}
			else if (dxpEntityType.isUserGroup()) {
				individual.setUserGroupIds(associatedIds);
			}
		}
	}

	private static final String _CHARACTERS_TO_BE_ESCAPED_IN_QUERY_STRING =
		"+-=&&||><!(){}[]^\"~*?:\\/";

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	private String[] _collections;

	@Autowired
	private DataSourceIndividualRepository _dataSourceIndividualRepository;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualChannelRepository _individualChannelRepository;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private SegmentDog _segmentDog;

}