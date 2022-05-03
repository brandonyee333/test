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

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.InterestRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.IndividualIdThreadLocal;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
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
import java.util.function.Function;
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

		Set<Field> customFields = individual.getCustomFields();

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
					JSONUtil.put("id", individual.getId())
				));
		}

		if (CollectionUtils.isNotEmpty(fields)) {
			individual.setCustomFields(customFields);
			individual.setFields(fields);

			return individual;
		}

		return _populateIndividual(individual);
	}

	public Individual addIndividual(
		Long channelId, Long dataSourceId, String emailAddressHashed,
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
					Collections.emptySet(), dataSourceId, null,
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

	public void addSegmentId(List<Individual> individuals, Long segmentId) {
		for (Individual individual : individuals) {
			Set<Long> segmentIds = individual.getSegmentIds();

			segmentIds.add(segmentId);
		}

		_individualRepository.saveAll(individuals);
	}

	public Individual addSegmentId(Long individualId, Long segmentId) {
		Individual individual = fetchIndividual(individualId);

		if (individual == null) {
			return null;
		}

		Set<Long> segmentIds = individual.getSegmentIds();

		segmentIds.add(segmentId);

		return _populateIndividual(_individualRepository.save(individual));
	}

	public long countActiveIndividualsFromLast30DaysBySegment(Segment segment) {
		Date date = DateUtil.newDayDate();

		Date monthAgoDate = DateUtil.addDays(date, -31);
		Date yesterdayDate = DateUtil.newEndOfDayDate(
			DateUtil.addDays(date, -1));

		return _individualRepository.
			countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
				segment.getChannelId(), yesterdayDate, yesterdayDate,
				Collections.singletonList(segment.getId()), monthAgoDate,
				monthAgoDate);
	}

	public long countIndividuals(List<Long> individualIds) {
		return countIndividuals(individualIds, null);
	}

	public long countIndividuals(List<Long> individualIds, String keywords) {
		return _individualRepository.countByIdInAndKeywords(
			individualIds, keywords);
	}

	public long countIndividuals(
		Long channelId, String filterString, Boolean includeAnonymousUsers) {

		return _individualRepository.countIndividuals(
			channelId,
			new FilterHelper(
				_faroInfoIndividualsFilterStringConverterHelper, filterString,
				_individualsFilterStringConverterHelper),
			includeAnonymousUsers, null, null);
	}

	public long countIndividuals(String query, Long segmentId) {
		List<String> fieldNames =
			_fieldMappingRepository.
				findFieldNameByContextAndFieldTypeAndOwnerType(
					"demographics", "Text", "individual");

		return _individualRepository.countByFieldNamesAndQueryAndSegmentId(
			fieldNames, query, segmentId);
	}

	public void deleteIndividual(Date deletionDate, Long individualId) {
		_fieldRepository.deleteByOwnerIdAndOwnerType(
			individualId, "individual");

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("ownerId", String.valueOf(individualId))
		).filter(
			QueryBuilders.termQuery("ownerType", "individual")
		);

		elasticsearchInvoker.delete("visited-pages", boolQueryBuilder);

		_interestRepository.deleteByOwnerIdInAndOwnerType(
			Collections.singletonList(individualId), "individual");

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

	public void deleteIndividuals(
		Date deletionDate, List<Individual> individuals) {

		List<Long> individualIds = ListUtil.map(individuals, Individual::getId);

		_fieldRepository.deleteByOwnerIdInAndOwnerType(
			individualIds, "individual");

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termsQuery(
				"ownerId", ListUtil.map(individualIds, String::valueOf))
		).filter(
			QueryBuilders.termQuery("ownerType", "individual")
		);

		elasticsearchInvoker.delete("interests", boolQueryBuilder);
		elasticsearchInvoker.delete("visited-pages", boolQueryBuilder);

		_membershipDog.deactivateMembershipByIndividuals(
			deletionDate, individuals);

		_membershipChangeDog.updateMembershipChangeIndividualDeleted(
			Boolean.TRUE, individualIds);

		_individualRepository.deleteByIdIn(individualIds);
	}

	public boolean existsByChannelIdAndFilterStringAndId(
		Long channelId, String filterString, Long individualId) {

		try {
			IndividualIdThreadLocal.setIndividualId(individualId);

			if (channelId == null) {
				return _individualRepository.existsByFilterStringAndId(
					new FilterHelper(
						_faroInfoIndividualsFilterStringConverterHelper,
						filterString, _individualsFilterStringConverterHelper),
					individualId);
			}

			return _individualRepository.existsByChannelIdAndFilterStringAndId(
				channelId,
				new FilterHelper(
					_faroInfoIndividualsFilterStringConverterHelper,
					filterString, _individualsFilterStringConverterHelper),
				individualId);
		}
		finally {
			IndividualIdThreadLocal.remove();
		}
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

		return _populateIndividual(individualOptional.orElse(null));
	}

	public Individual fetchIndividual(Long dataSourceId, String userId) {
		Individual individual =
			_individualRepository.findByDataSourceIdAndIndividualPK(
				dataSourceId, userId);

		return _populateIndividual(individual);
	}

	public Individual
		fetchIndividualByAssociationIdNotAndDataSourceIdAndIndividualPK(
			Long associationId, Long dataSourceId, String fieldName,
			String individualPK) {

		Individual individual =
			_individualRepository.
				findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
					associationId, dataSourceId, fieldName, individualPK);

		return _populateIndividual(individual);
	}

	public Individual fetchIndividualByEmailAddress(String emailAddress) {
		Individual individual = _individualRepository.findByEmailAddress(
			emailAddress);

		return _populateIndividual(individual);
	}

	public Individual fetchIndividualByEmailAddressHashed(
		String emailAddressHashed) {

		Individual individual = _individualRepository.findByEmailAddressHashed(
			emailAddressHashed);

		return _populateIndividual(individual);
	}

	public Individual fetchIndividualByEmailAddressOrEmailAddressHashed(
		String emailAddress, String emailAddressHashed) {

		if (StringUtils.isBlank(emailAddress)) {
			return _individualRepository.findByEmailAddressHashed(
				emailAddressHashed);
		}

		Individual individual = _individualRepository.findByEmailAddress(
			emailAddress);

		if (individual == null) {
			return _individualRepository.findByEmailAddressHashed(
				emailAddressHashed);
		}

		return individual;
	}

	public Iterable<Individual> fetchIndividuals(Set<Long> individualIds) {
		return _individualRepository.findAllById(individualIds);
	}

	public List<String> getAccountPKs(
		Long channelId, Long individualSegmentId) {

		return _individualRepository.findAccountPKsByChannelIdAndSegmentId(
			channelId, individualSegmentId);
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
			_dxpEntityDog.findByFieldsAndType(
				new HashMap<String, Object>() {
					{
						put(
							"fields." + dxpEntityType.getIdFieldName(),
							classPKs);
					}
				},
				dxpEntityType);

		Stream<? extends DXPEntity> stream = dxpEntities.stream();

		return stream.map(
			DXPEntity::getId
		).collect(
			Collectors.toSet()
		);
	}

	public Page<Distribution> getDistributionPage(
		String fieldName, String fieldType, String filterString,
		int numberOfBins, int size, String[] sorts) {

		if (fieldType.equals("Number")) {
			size = numberOfBins;
		}

		PageRequest pageRequest = PageRequest.of(
			0, size,
			SortUtil.getSort(Sort.by(Sort.Order.desc("count")), sorts));

		List<Distribution> distributions =
			_individualRepository.getIndividualDistributions(
				fieldName, fieldType,
				new FilterHelper(
					_faroInfoIndividualsFilterStringConverterHelper,
					filterString, _individualsFilterStringConverterHelper),
				pageRequest);

		return PageableExecutionUtils.getPage(
			distributions, pageRequest, distributions::size);
	}

	public List<Long> getIdsBySegmentId(Long segmentId) {
		return _individualRepository.findIdsByAnySegmentIds(segmentId);
	}

	public Individual getIndividual(Long id) {
		Optional<Individual> individualOptional =
			_individualRepository.findById(id);

		return _populateIndividual(
			individualOptional.orElseThrow(
				() -> new OSBAsahException(
					HttpStatus.BAD_REQUEST,
					"There is no individual with ID " + id)));
	}

	public Individual getIndividual(Long channelId, Long individualId) {
		return _populateIndividual(channelId, getIndividual(individualId));
	}

	public Map<Long, Long> getIndividualCounts(
		boolean includeAnonymousUsers, Long segmentId) {

		return _individualRepository.findIndividualCounts(
			includeAnonymousUsers, segmentId);
	}

	public List<Long> getIndividualIds(
		Long channelId, Date lastActivityDate, Long segmentId) {

		return _individualRepository.
			findIdsByAnyChannelIdsAndLastActivityDateAfterAndAnySegmentIds(
				channelId, lastActivityDate, segmentId);
	}

	public String getIndividualName(Long individualId) {
		Individual individual = fetchIndividual(individualId);

		if (individual == null) {
			return null;
		}

		return FaroInfoIndividualUtil.getIndividualName(individual);
	}

	public Page<Individual> getIndividualPage(
		Long individualId, int size, Sort sort) {

		List<Individual> individuals = ListUtil.map(
			_individualRepository.findByIdAfter(
				individualId, PageRequest.of(0, size, sort)),
			this::_populateIndividual);

		return PageableExecutionUtils.getPage(
			individuals, PageRequest.of(0, size, sort),
			() -> _individualRepository.countByIdAfter(individualId));
	}

	public Page<Individual> getIndividualPage(
		String query, Long segmentId, int page, int size) {

		return PageableExecutionUtils.getPage(
			getIndividuals(query, segmentId, page, size),
			PageRequest.of(page, size),
			() -> countIndividuals(query, segmentId));
	}

	public List<Individual> getIndividuals(
		String query, Long segmentId, int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		List<String> fieldNames =
			_fieldMappingRepository.
				findFieldNameByContextAndFieldTypeAndOwnerType(
					"demographics", "Text", "individual");

		List<Individual> individuals =
			_individualRepository.findByFieldNamesAndQueryAndSegmentId(
				fieldNames, query, segmentId, pageRequest);

		return ListUtil.map(individuals, this::_populateIndividual);
	}

	public List<Individual> getIndividualsBySegmentId(Long segmentId) {
		List<Individual> individuals = _individualRepository.findBySegmentIds(
			segmentId);

		return ListUtil.map(individuals, this::_populateIndividual);
	}

	public Set<String> getIndividualUserIds(@Nullable Long id) {
		if (id == null) {
			return Collections.emptySet();
		}

		Individual individual = fetchIndividual(id);

		if (individual == null) {
			return Collections.emptySet();
		}

		Set<String> userIds = new HashSet<>();

		Set<Individual.DataSourceIndividualPK> dataSourceIndividualPKs =
			individual.getDataSourceIndividualPKs();

		for (Individual.DataSourceIndividualPK dataSourceIndividualPK :
				dataSourceIndividualPKs) {

			userIds.addAll(dataSourceIndividualPK.getIndividualPKs());
		}

		return userIds;
	}

	public List<Long> getKnownIndividualIds(
		String filterString, Long segmentId) {

		return _individualRepository.findKnownIndividualIds(
			new FilterHelper(
				_faroInfoIndividualsFilterStringConverterHelper, filterString,
				_individualsFilterStringConverterHelper),
			segmentId);
	}

	public long getKnownIndividualsCount(List<Long> ids) {
		return _individualRepository.countKnownIndividualsByIdIn(ids);
	}

	public long getKnownIndividualsCount(Long segmentId) {
		return _individualRepository.countKnownIndividualsByAnySegmentIds(
			segmentId);
	}

	public Page<Transformation> getTransformationPage(
		String apply, Long channelId, String filterString,
		Boolean includeAnonymousUsers, Long segmentId, int page, int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_individualRepository.getIndividualTransformations(
				apply, channelId,
				new FilterHelper(
					_faroInfoIndividualsFilterStringConverterHelper,
					filterString, _individualsFilterStringConverterHelper),
				includeAnonymousUsers, _getSegmentChannelId(segmentId),
				segmentId, pageRequest);

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

	public Individual removeDemographics(Individual individual) {
		_fieldDog.deleteFields("demographics", individual.getId());

		individual.setFields(Collections.emptySet());

		return updateIndividual(individual);
	}

	public void removeSegmentId(Long segmentId) {
		_individualRepository.removeSegmentId(segmentId);
	}

	public void removeSegmentId(Long individualId, Long segmentId) {
		_individualRepository.removeSegmentIdByIdIn(
			Collections.singleton(individualId), segmentId);
	}

	public void removeSegmentId(Set<Long> individualIds, Long segmentId) {
		_individualRepository.removeSegmentIdByIdIn(individualIds, segmentId);
	}

	public void removeSegmentIds(List<Long> segmentIds) {
		_individualRepository.removeSegmentIds(segmentIds);
	}

	public Page<Individual> searchIndividualPage(
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

	public Page<Individual> searchIndividualPage(
		String filterString, Boolean includeAnonymousUsers, Long segmentId,
		int page, int size, String[] sorts) {

		FilterHelper filterHelper = new FilterHelper(
			_faroInfoIndividualsFilterStringConverterHelper, filterString,
			_individualsFilterStringConverterHelper);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		Long segmentChannelId = _getSegmentChannelId(segmentId);

		List<Individual> individuals = _individualRepository.searchIndividuals(
			null, filterHelper, includeAnonymousUsers, segmentChannelId,
			segmentId, pageRequest);

		return PageableExecutionUtils.getPage(
			ListUtil.map(individuals, this::_populateIndividual), pageRequest,
			() -> _individualRepository.countIndividuals(
				null, filterHelper, includeAnonymousUsers, segmentChannelId,
				segmentId));
	}

	public List<Individual> searchIndividuals(
		Date date, Long individualId, int size) {

		return _individualRepository.
			findAnonymousByCreateDateAndLastActivityDateAndId(
				date, individualId, size);
	}

	public List<Individual> searchIndividuals(
		List<Long> individualIds, String keywords, int size, int start) {

		List<Individual> individuals =
			_individualRepository.findByIdInAndKeywords(
				individualIds, _escapeKeywords(keywords),
				PageRequest.of(start, size));

		return ListUtil.map(individuals, this::_populateIndividual);
	}

	public List<Individual> searchIndividuals(
		Long channelId, Boolean includeAnonymousUsers, Long segmentId, int page,
		int size, String[] sorts) {

		return ListUtil.map(
			_individualRepository.searchIndividuals(
				channelId,
				new FilterHelper(
					_faroInfoIndividualsFilterStringConverterHelper, null,
					_individualsFilterStringConverterHelper),
				includeAnonymousUsers, _getSegmentChannelId(segmentId),
				segmentId, PageRequest.of(page, size, _getSort(sorts))),
			individual -> _populateIndividual(
				channelId, _populateIndividual(individual)));
	}

	public List<Individual> searchIndividuals(
		Long dataSourceId, Long individualId, int size) {

		return _individualRepository.searchIndividuals(
			dataSourceId, individualId, size);
	}

	public List<Individual> searchIndividuals(
		Long channelId, String filterString, Boolean includeAnonymousUsers,
		int page, int size, String[] sorts) {

		return ListUtil.map(
			_individualRepository.searchIndividuals(
				channelId,
				new FilterHelper(
					_faroInfoIndividualsFilterStringConverterHelper,
					filterString, _individualsFilterStringConverterHelper),
				includeAnonymousUsers, null, null,
				PageRequest.of(page, size, _getSort(sorts))),
			individual -> _populateIndividual(
				channelId, _populateIndividual(individual)));
	}

	public List<Individual> searchIndividuals(
		Long channelId, String filterString, List<Long> individualIds) {

		return _individualRepository.findByChannelIdAndFilterStringAndIdIn(
			channelId,
			new FilterHelper(
				_faroInfoIndividualsFilterStringConverterHelper, filterString,
				_individualsFilterStringConverterHelper),
			individualIds);
	}

	public void updateDataSourceName(Long dataSourceId, String dataSourceName) {
		_individualRepository.updateDataSourceNameByDataSourceId(
			dataSourceId, dataSourceName);
	}

	public void updateDynamicMemberships(Date modifiedDate, Segment segment) {
		Long individualSegmentId = segment.getId();

		FilterHelper filterHelper = new FilterHelper(
			_faroInfoIndividualsFilterStringConverterHelper,
			segment.getFilter(), _individualsFilterStringConverterHelper);

		_updateDynamicAddMemberships(
			segment.getChannelId(), filterHelper,
			BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
			individualSegmentId, modifiedDate);
		_updateDynamicRemoveMemberships(
			segment.getChannelId(), filterHelper,
			BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
			individualSegmentId, modifiedDate);
	}

	public void updateDynamicRemoveMemberships(
		Date modifiedDate, Segment segment) {

		FilterHelper filterHelper = new FilterHelper(
			_faroInfoIndividualsFilterStringConverterHelper,
			segment.getFilter(), _individualsFilterStringConverterHelper);

		_updateDynamicRemoveMemberships(
			segment.getChannelId(), filterHelper,
			BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
			segment.getId(), modifiedDate);
	}

	public Individual updateIndividual(Individual individual) {
		return _populateIndividual(_individualRepository.save(individual));
	}

	public void updateIndividual(
			JSONObject dataJSONObject, DataSource dataSource,
			List<Individual> individuals)
		throws Exception {

		Long dataSourceId = dataSource.getId();

		boolean dataAccountPKsUpdated = false;

		String providerType = dataSource.getProviderType();

		if (providerType.equals("SALESFORCE")) {
			JSONArray dataAccountPKsJSONArray = dataJSONObject.optJSONArray(
				"accountPKs");

			if (dataAccountPKsJSONArray != null) {
				Set<String> accountPKs = JSONUtil.toStringSet(
					dataAccountPKsJSONArray);

				for (Individual individual : individuals) {
					_addDataSourceAccountPKs(
						accountPKs, dataSourceId, individual);
				}

				dataAccountPKsUpdated = true;
			}
		}

		List<Long> individualIds = ListUtil.map(individuals, Individual::getId);

		List<Field> previousFields = _fieldDog.getFields(
			"demographics", individualIds);

		_fieldDog.updateFields(
			"custom", dataJSONObject, dataSource, individualIds, "individual");
		_fieldDog.updateFields(
			"demographics", dataJSONObject, dataSource, individualIds,
			"individual");

		List<Individual> deletedIndividuals = new ArrayList<>();
		List<Individual> updatedIndividuals = new ArrayList<>();

		for (Individual individual : individuals) {
			List<Field> fields = _fieldDog.getOwnerIdFields(
				"demographics", individual.getId());

			Stream<Field> stream = fields.stream();

			Map<Long, List<Field>> individualIdByField = stream.collect(
				Collectors.groupingBy(Field::getOwnerId));

			List<String> names = ListUtil.map(
				individualIdByField.get(individual.getId()), Field::getName);

			if (!names.contains("email")) {
				deletedIndividuals.add(individual);
			}
			else {
				individual.setFields(
					new HashSet<>(individualIdByField.get(individual.getId())));

				individual.setModifiedDate(new Date());

				updatedIndividuals.add(individual);
			}
		}

		if (!updatedIndividuals.isEmpty()) {
			_individualRepository.saveAll(updatedIndividuals);
		}

		if (!deletedIndividuals.isEmpty()) {
			deleteIndividuals(new Date(), deletedIndividuals);
		}

		List<Field> fields = _fieldDog.getFields("demographics", individualIds);

		JSONObject fieldsJSONObject = _fieldDog.getFieldsJSONObject(
			"demographics", dataJSONObject, dataSource);

		if ((fieldsJSONObject.names() != null) &&
			((previousFields.size() != fields.size()) ||
			 !previousFields.containsAll(fields) ||
			 !fields.containsAll(previousFields) || dataAccountPKsUpdated)) {

			for (Individual individual : updatedIndividuals) {
				individual.setLastEnrichmentDate(new Date());
			}
		}

		Stream<Field> fieldsStream = fields.stream();
		Stream<Field> previousFieldsStream = previousFields.stream();

		if (previousFieldsStream.noneMatch(
				field -> Objects.equals(field.getName(), "email")) &&
			fieldsStream.anyMatch(
				field -> Objects.equals(field.getName(), "email"))) {

			String projectId = ProjectIdThreadLocal.getProjectId();

			_boundedExecutor.runAsync(
				() -> {
					ProjectIdThreadLocal.setProjectId(projectId);

					_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termsQuery(
								"individualId",
								ListUtil.map(
									updatedIndividuals,
									individual -> String.valueOf(
										individual.getId())))
						).filter(
							BoolQueryBuilderUtil.shouldNot(
								QueryBuilders.existsQuery("knownIndividual")
							).should(
								QueryBuilders.termQuery(
									"knownIndividual", false)
							)
						),
						true,
						new Script(
							Script.DEFAULT_SCRIPT_TYPE,
							Script.DEFAULT_SCRIPT_LANG,
							"ctx._source.knownIndividual = true;",
							Collections.emptyMap()),
						_collections);
				});
		}

		_updateIndividualAssociations(dataJSONObject, updatedIndividuals);

		_individualRepository.saveAll(updatedIndividuals);

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"individualsJSONArray",
				JSONUtil.toJSONArray(updatedIndividuals, Individual::getId)));
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

		return _populateIndividual(individual);
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

				_addDataSourceAccountPKs(accountPKs, dataSourceId, individual);

				dataAccountPKsUpdated = true;
			}
		}

		_setFirstEnrichmentDate(individual);

		List<Field> previousFields = _fieldDog.getOwnerIdFields(
			"demographics", individualId);

		if (dataJSONObject.has("expando")) {
			_fieldDog.updateFields(
				"custom", dataJSONObject, dataSource,
				Collections.singletonList(individualId), "individual");
		}

		_fieldDog.updateFields(
			"demographics", dataJSONObject, dataSource,
			Collections.singletonList(individualId), "individual");

		List<Field> fields = _fieldDog.getOwnerIdFields(
			"demographics", individualId);

		Stream<Field> stream = fields.stream();

		individual.setFields(stream.collect(Collectors.toSet()));

		individual.setModifiedDate(new Date());

		individual = _populateIndividual(
			_individualRepository.save(individual));

		List<Field> emailFields =
			_fieldRepository.findByContextAndNameAndOwnerIdAndOwnerType(
				"demographics", "email", individualId, "individual");

		if (emailFields.isEmpty()) {
			deleteIndividual(new Date(), individualId);

			return null;
		}

		String oldIndividualName = getIndividualName(individualId);

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

			String projectId = ProjectIdThreadLocal.getProjectId();

			_boundedExecutor.runAsync(
				() -> {
					ProjectIdThreadLocal.setProjectId(projectId);

					_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"individualId", individualId)
						).filter(
							BoolQueryBuilderUtil.shouldNot(
								QueryBuilders.existsQuery("knownIndividual")
							).should(
								QueryBuilders.termQuery(
									"knownIndividual", false)
							)
						),
						true,
						new Script(
							Script.DEFAULT_SCRIPT_TYPE,
							Script.DEFAULT_SCRIPT_LANG,
							"ctx._source.knownIndividual = true;",
							Collections.emptyMap()),
						_collections);
				});
		}

		_updateIndividualAssociations(dataJSONObject, individual);

		Set<Field> customFields = individual.getCustomFields();

		individual = _individualRepository.save(individual);

		individual.setCustomFields(customFields);

		_individualModified(individual, oldIndividualName);

		return individual;
	}

	private void _addDataSourceAccountPKs(
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

	private List<Membership> _getDeactivateMemberships(
		List<Individual> individuals, List<Membership> memberships) {

		List<Long> individualIds = ListUtil.map(individuals, Individual::getId);

		Stream<Membership> stream = memberships.stream();

		return stream.filter(
			membership -> !individualIds.contains(membership.getIndividualId())
		).collect(
			Collectors.toList()
		);
	}

	private Set<Field> _getFields(String context, Long individualId) {
		Set<Field> fields = new HashSet<>();

		List<FieldMapping> fieldMappings =
			_fieldMappingRepository.findByContextAndDataSourceIdAndOwnerType(
				context, null, "individual");

		Stream<FieldMapping> stream = fieldMappings.stream();

		Map<String, FieldMapping> fieldMappingsMap = stream.collect(
			Collectors.toMap(
				FieldMapping::getDisplayName, Function.identity(),
				(existing, replacement) -> replacement));

		for (Field field : _fieldDog.getOwnerIdFields(context, individualId)) {
			if (!fieldMappingsMap.containsKey(field.getName())) {
				continue;
			}

			fields.add(field);
		}

		return fields;
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
				"individualJSONObject", JSONUtil.put("id", individual.getId())
			));

		String newIndividualName = FaroInfoIndividualUtil.getIndividualName(
			individual);

		if (!Objects.equals(oldIndividualName, newIndividualName)) {
			_membershipChangeDog.updateIndividualNameForIndividual(
				individual.getId(), newIndividualName);
		}
	}

	private Individual _populateIndividual(Individual individual) {
		if (individual == null) {
			return null;
		}

		individual.setCustomFields(_getFields("custom", individual.getId()));
		individual.setFields(_getFields("demographics", individual.getId()));

		return individual;
	}

	private Individual _populateIndividual(
		Long channelId, Individual individual) {

		if (individual == null) {
			return null;
		}

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
				individual.setActivitiesCount(
					individualActivitiesCount.getActivitiesCount());
				individual.setActivitiesCounts(
					Collections.singleton(individualActivitiesCount));
			});

		Set<Individual.ActivityDate> lastActivityDates =
			individual.getLastActivityDates();

		Stream<Individual.ActivityDate> lastActivityDatesStream =
			lastActivityDates.stream();

		Optional<Individual.ActivityDate> lastActivityDateOptional =
			lastActivityDatesStream.filter(
				lastActivityDate -> Objects.equals(
					lastActivityDate.getChannelId(), channelId)
			).findFirst();

		lastActivityDateOptional.ifPresent(
			individualLastActivityDate -> {
				individual.setLastActivityDate(
					individualLastActivityDate.getActivityDate());
				individual.setLastActivityDates(
					Collections.singleton(individualLastActivityDate));
			});

		return individual;
	}

	private void _setFirstEnrichmentDate(Individual individual) {
		if (_fieldRepository.existsByNameAndOwnerId(
				"email", individual.getId())) {

			return;
		}

		individual.setFirstEnrichmentDate(new Date());
	}

	private void _updateDynamicAddMemberships(
		Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, Long individualSegmentId,
		Date modifiedDate) {

		Long currentIndividualId = null;

		while (true) {
			List<Individual> individuals =
				_individualRepository.searchIndividuals(
					channelId, filterHelper, currentIndividualId,
					includeAnonymousUsers, 10000);

			if (individuals.isEmpty()) {
				break;
			}

			Individual lastIndividual = individuals.get(individuals.size() - 1);

			currentIndividualId = lastIndividual.getId();

			List<Long> individualIds = _membershipDog.isMember(
				ListUtil.map(individuals, Individual::getId),
				individualSegmentId);

			Stream<Individual> stream = individuals.stream();

			individuals = stream.filter(
				individual -> !individualIds.contains(individual.getId())
			).collect(
				Collectors.toList()
			);

			_membershipDog.addMemberships(
				modifiedDate, individuals, individualSegmentId);
		}
	}

	private void _updateDynamicRemoveMemberships(
		Long channelId, FilterHelper filterHelper,
		Boolean includeAnonymousUsers, Long individualSegmentId,
		Date modifiedDate) {

		Long currentMembershipId = null;

		while (true) {
			List<Membership> memberships = _membershipDog.searchMemberships(
				individualSegmentId, currentMembershipId, 10000, "ACTIVE");

			if (memberships.isEmpty()) {
				break;
			}

			Membership lastMembership = memberships.get(memberships.size() - 1);

			currentMembershipId = lastMembership.getId();

			List<Long> individualIds = ListUtil.map(
				memberships, Membership::getIndividualId);

			List<Individual> individuals =
				_individualRepository.searchIndividuals(
					channelId, filterHelper, individualIds,
					includeAnonymousUsers);

			_membershipDog.deactivateMemberships(
				modifiedDate,
				_getDeactivateMemberships(individuals, memberships));
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

	private void _updateIndividualAssociations(
		JSONObject dataJSONObject, List<Individual> individuals) {

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
				for (Individual individual : individuals) {
					individual.setGroupIds(associatedIds);
				}
			}
			else if (dxpEntityType.isOrganization()) {
				for (Individual individual : individuals) {
					individual.setOrganizationIds(associatedIds);
				}
			}
			else if (dxpEntityType.isRole()) {
				for (Individual individual : individuals) {
					individual.setRoleIds(associatedIds);
				}
			}
			else if (dxpEntityType.isTeam()) {
				for (Individual individual : individuals) {
					individual.setTeamIds(associatedIds);
				}
			}
			else if (dxpEntityType.isUserGroup()) {
				for (Individual individual : individuals) {
					individual.setUserGroupIds(associatedIds);
				}
			}
		}
	}

	private static final String _CHARACTERS_TO_BE_ESCAPED_IN_QUERY_STRING =
		"+-=&&||><!(){}[]^\"~*?:\\/";

	@Autowired
	private AsahTaskDog _asahTaskDog;

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(10, 1);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	private String[] _collections;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

	@Autowired
	private InterestRepository _interestRepository;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private SegmentDog _segmentDog;

}