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
import com.liferay.osb.asah.common.elasticsearch.FilterUtil;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.parser.FilterStringParser;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class SegmentDog extends BaseFaroInfoDog {

	public Segment addSegment(
		Date createDate, String filter, Date modifiedDate, String name,
		String scope, String type, String status) {

		Segment segment = new Segment();

		segment.setCreateDate(createDate);
		segment.setFilter(_parserFilter(filter));
		segment.setModifiedDate(modifiedDate);
		segment.setName(name);
		segment.setScope(scope);
		segment.setType(Segment.Type.valueOf(type));
		segment.setStatus(status);

		return addSegment(segment);
	}

	public Segment addSegment(Segment segment) {
		setReferencedFields(segment);

		if ((segment.getType() == null) ||
			(segment.getType() == Segment.Type.DYNAMIC)) {

			segment.setState("IN_PROGRESS");
		}
		else {
			segment.setState("READY");
		}

		segment.setFilter(_parserFilter(segment.getFilter()));

		segment = _segmentRepository.save(segment);

		_addAsahTask(segment);

		return segment;
	}

	public void assignChannel(Long channelId, Long segmentId) throws Exception {
		Segment segment = getSegment(segmentId);

		if (segment.getChannelId() != null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Individual segment already assigned to property: " +
					segment.getChannelId());
		}

		segment.setChannelId(channelId);

		segment = _segmentRepository.save(segment);

		_updateMemberships(segment);
	}

	public void deleteSegment(Long segmentId) {
		_segmentRepository.deleteById(segmentId);

		_asahTaskDog.scheduleAsahTask(
			"DeleteIndividualSegmentTasksNanite",
			JSONUtil.put("individualSegmentIds", JSONUtil.put(segmentId)));
	}

	public void deleteSegments(Set<Long> channelIds) throws Exception {
		List<Segment> segments = new ArrayList<>();

		int page = 0;

		while (true) {
			List<Segment> curSegments = _segmentRepository.findByChannelIdIn(
				channelIds, PageRequest.of(page++, 500));

			if (curSegments.isEmpty()) {
				break;
			}

			segments.addAll(curSegments);
		}

		_segmentRepository.deleteAll(segments);

		_asahTaskDog.scheduleAsahTask(
			"DeleteIndividualSegmentTasksNanite",
			JSONUtil.put(
				"individualSegmentIds",
				JSONUtil.toJSONArray(segments, Segment::getId)));
	}

	public void disableDynamicSegments(
		Long dxpEntityId, DXPEntity.Type dxpEntityType) {

		if (Objects.isNull(dxpEntityId)) {
			return;
		}

		List<Segment> segments = _segmentRepository.searchSegments(
			dxpEntityId, dxpEntityType, "DISABLED", Segment.Type.DYNAMIC);

		for (Segment segment : segments) {
			segment.setState("DISABLED");
		}

		_segmentRepository.saveAll(segments);
	}

	public void disableDynamicSegments(
		Long dataSourceId, List<Long> fieldMappingIds) {

		if ((dataSourceId == null) && fieldMappingIds.isEmpty()) {
			return;
		}

		List<Segment> segments;

		if ((dataSourceId != null) && !fieldMappingIds.isEmpty()) {
			segments =
				_segmentRepository.
					findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdInAndStateNotAndType(
						dataSourceId, fieldMappingIds, "DISABLED",
						Segment.Type.DYNAMIC);
		}
		else if (dataSourceId != null) {
			segments =
				_segmentRepository.
					findByReferencedAssetDataSourceIdsAndStateNotAndType(
						dataSourceId, "DISABLED", Segment.Type.DYNAMIC);
		}
		else if (!fieldMappingIds.isEmpty()) {
			segments =
				_segmentRepository.
					findByReferencedFieldMappingIdInAndStateNotAndType(
						fieldMappingIds, "DISABLED", Segment.Type.DYNAMIC);
		}
		else {
			segments = _segmentRepository.findByStateNotAndType(
				"DISABLED", Segment.Type.DYNAMIC);
		}

		for (Segment segment : segments) {
			segment.setState("DISABLED");
		}

		if (!segments.isEmpty()) {
			_segmentRepository.saveAll(segments);
		}
	}

	public boolean existsSegment(Long segmentId) {
		return _segmentRepository.existsById(segmentId);
	}

	public Segment fetchSegment(Long segmentId) {
		Optional<Segment> segmentOptional = _segmentRepository.findById(
			segmentId);

		return segmentOptional.orElse(null);
	}

	public Segment fetchSegment(String name, String status) {
		Optional<Segment> segmentOptional =
			_segmentRepository.findByNameAndStatus(name, status);

		return segmentOptional.orElse(null);
	}

	public List<Long> getReferencedAssetIds() {
		return _segmentRepository.findReferencedAssetIds();
	}

	public Segment getSegment(Long segmentId) {
		Optional<Segment> segmentOptional = _segmentRepository.findById(
			segmentId);

		return segmentOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no Segment with ID " + segmentId));
	}

	public Segment getSegment(String name, String status) {
		Optional<Segment> segmentOptional =
			_segmentRepository.findByNameAndStatus(name, status);

		return segmentOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no Segment with Name " + name + " and Status " +
					status));
	}

	public List<Long> getSegmentIds(List<String> names, String status) {
		return _segmentRepository.findIdByNameInAndStatus(names, status);
	}

	public List<String> getSegmentNames(Long channelId, Set<Long> segmentIds) {
		if (segmentIds.isEmpty()) {
			return Collections.emptyList();
		}

		if (Objects.nonNull(channelId)) {
			return _segmentRepository.findNameByChannelIdAndIdInAndStatus(
				channelId, new ArrayList<>(segmentIds), "ACTIVE");
		}

		return _segmentRepository.findNameByIdInAndStatus(
			new ArrayList<>(segmentIds), "ACTIVE");
	}

	public Page<Segment> getSegmentPage(
		Date fromCreateDate, Long segmentId, int size, Sort sort,
		Date toCreateDate) {

		return PageableExecutionUtils.getPage(
			_segmentRepository.findByCreateDateBetweenAndIdAfter(
				fromCreateDate, toCreateDate, segmentId,
				PageRequest.of(0, size, sort)),
			PageRequest.of(0, size, sort),
			() -> _segmentRepository.countByCreateDateBetweenAndIdAfter(
				fromCreateDate, toCreateDate, segmentId));
	}

	public Page<Segment> getSegmentPage(int page, int size) {
		return _segmentRepository.findAll(PageRequest.of(page, size));
	}

	public List<Segment> getSegments(Iterable<Long> segmentIds) {
		return IterableUtils.toList(_segmentRepository.findAllById(segmentIds));
	}

	public List<Segment> getSegments(String name, int page, int size) {
		return _segmentRepository.findByChannelIdIsNotNullOrNameStartingWith(
			name, PageRequest.of(page, size));
	}

	public List<Segment> getSegments(
		String filter, String state, String status, int page, int size) {

		return _segmentRepository.searchSegments(
			filter, state, status, PageRequest.of(page, size));
	}

	public Map<Long, JSONObject> getSegmentsJSONObjects(
			List<Individual> individuals)
		throws Exception {

		Map<Long, JSONObject> segmentsJSONObjects = new HashMap<>();

		for (Individual individual : individuals) {
			List<Long> segmentIds = _membershipDog.getIndividualSegmentIds(
				individual.getId());

			segmentsJSONObjects.put(
				individual.getId(),
				JSONUtil.put(
					"individual-segments",
					JSONUtil.toJSONArray(
						getSegments(segmentIds),
						segment -> _objectMapper.convertValue(
							segment, JSONObject.class))));
		}

		return segmentsJSONObjects;
	}

	public Page<Transformation> getTransformationPage(
			Long accountId, String apply, @Nullable String filterString,
			int page, int size)
		throws Exception {

		Segment segment = fetchSegment("Account: " + accountId, "INACTIVE");

		if (segment == null) {
			throw new Exception(
				"Unable to find individual segment associated with account " +
					accountId);
		}

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_segmentRepository.getSegmentTransformations(
				apply, new FilterHelper(filterString), pageRequest,
				_getIndividualSegmentIds(segment.getId()));

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	public Page<Transformation> getTransformationPage(
		String apply, @Nullable String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_segmentRepository.getSegmentTransformations(
				apply, new FilterHelper(filterString), pageRequest, null);

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	public boolean isIncludeAnonymousUsers(Long segmentId) {
		Segment segment = getSegment(segmentId);

		return BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers());
	}

	public Segment replaceSegment(Segment segment) {
		Long segmentId = segment.getId();

		if (segmentId == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to replace a segment without ID");
		}

		Segment existingSegment = getSegment(segmentId);

		if (Objects.isNull(segment.getFilter()) ||
			Objects.equals(existingSegment.getFilter(), segment.getFilter())) {

			_segmentRepository.save(segment);
		}
		else {
			setReferencedFields(segment);

			_setState(segment);

			_segmentRepository.save(segment);

			_addAsahTask(segment);
		}

		return segment;
	}

	public Page<Segment> searchAccountSegmentPage(
		Long accountId, @Nullable String filterString, int page, int size,
		@Nullable String[] sorts) {

		Segment segment = fetchSegment("Account: " + accountId, "INACTIVE");

		if (segment == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to find individual segment associated with account " +
					accountId);
		}

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_segmentRepository.searchSegments(
				filterHelper, _getIndividualSegmentIds(segment.getId()),
				pageRequest),
			pageRequest,
			() -> _segmentRepository.countSegments(
				filterHelper, _getIndividualSegmentIds(segment.getId())));
	}

	public List<Segment> searchDynamicSegments(
		Set<Individual.DataSourceAccountPK> dataSourceAccountPKs,
		String filterString, Boolean includeAnonymousUsers, int page,
		Set<Long> segmentIds, int size,
		com.liferay.osb.asah.common.model.Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return _segmentRepository.searchDynamicSegments(
			dataSourceAccountPKs, new FilterHelper(filterString),
			includeAnonymousUsers, pageRequest, segmentIds);
	}

	public List<Segment> searchDynamicSegments(
		String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return _segmentRepository.searchDynamicSegments(
			new FilterHelper(filterString), pageRequest);
	}

	public Page<Segment> searchPreviewDisabledSegmentPage(
		Long dataSourceId, String filterString, int page, int size,
		String[] sorts) {

		List<Long> fieldMappingIds = _fieldMappingDog.getFieldMappingIds(
			dataSourceId);

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_segmentRepository.searchPreviewDisabledSegments(
				fieldMappingIds, dataSourceId, filterHelper, pageRequest),
			pageRequest,
			() -> _segmentRepository.countPreviewDisabledSegments(
				fieldMappingIds, dataSourceId, filterHelper));
	}

	public Page<Segment> searchSegmentPage(
		Long dataSourceId, String filterString, int page, int size,
		String[] sorts) {

		List<Long> channelIds = ListUtil.map(
			_channelDog.getChannels(dataSourceId), Channel::getId);

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_segmentRepository.searchSegments(
				channelIds, filterHelper, pageRequest),
			pageRequest,
			() -> _segmentRepository.countSegments(channelIds, filterHelper));
	}

	public Page<Segment> searchSegmentPage(
		String filterString, Long individualId, int page, int size,
		String[] sorts) {

		List<Long> segmentIds = _membershipDog.getActiveIndividualSegmentIds(
			individualId);

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_segmentRepository.searchSegments(
				filterHelper, segmentIds, pageRequest),
			pageRequest,
			() -> _segmentRepository.countSegments(filterHelper, segmentIds));
	}

	public void setReferencedFields(Segment segment) {
		Map<String, Set<String>> referencedObjectIds = _getReferencedObjectIds(
			segment.getFilter(), null);

		if (referencedObjectIds.isEmpty()) {
			return;
		}

		segment.setReferencedAssetDataSourceIds(
			SetUtil.map(
				referencedObjectIds.get("referencedAssetDataSourceIds"),
				Long::valueOf));
		segment.setReferencedAssetIds(
			SetUtil.map(
				referencedObjectIds.get("referencedAssetIds"), Long::valueOf));
		segment.setReferencedFieldMappingIds(
			SetUtil.map(
				referencedObjectIds.get("referencedFieldMappingIds"),
				Long::valueOf));
		segment.setReferencedGroupIds(
			SetUtil.map(
				referencedObjectIds.get("referencedGroupIds"), Long::valueOf));
		segment.setReferencedOrganizationIds(
			SetUtil.map(
				referencedObjectIds.get("referencedOrganizationIds"),
				Long::valueOf));
		segment.setReferencedRoleIds(
			SetUtil.map(
				referencedObjectIds.get("referencedRoleIds"), Long::valueOf));
		segment.setReferencedTeamIds(
			SetUtil.map(
				referencedObjectIds.get("referencedTeamIds"), Long::valueOf));
		segment.setReferencedUserGroupIds(
			SetUtil.map(
				referencedObjectIds.get("referencedUserGroupIds"),
				Long::valueOf));
		segment.setReferencedUserIds(
			SetUtil.map(
				referencedObjectIds.get("referencedUserIds"), Long::valueOf));
	}

	public Segment updateSegment(Long segmentId) {
		ReentrantLock reentrantLock = KeyReentrantLock.getReentrantLock(
			getClass(), ProjectIdThreadLocal.getProjectId(), segmentId);

		try {
			reentrantLock.lock();

			Segment existingSegment = getSegment(segmentId);

			long knownIndividualsCount =
				_individualDog.getKnownIndividualsCount(segmentId);

			long individualsCount = 0;

			if (BooleanUtils.toBoolean(
					existingSegment.getIncludeAnonymousUsers())) {

				individualsCount = _membershipDog.getIndividualsCount(
					segmentId);
			}
			else {
				individualsCount = knownIndividualsCount;
			}

			if (!Objects.equals(
					existingSegment.getAnonymousIndividualsCount(),
					individualsCount - knownIndividualsCount) ||
				!Objects.equals(
					existingSegment.getIndividualsCount(), individualsCount) ||
				!Objects.equals(
					existingSegment.getKnownIndividualsCount(),
					knownIndividualsCount)) {

				existingSegment.setAnonymousIndividualsCount(
					individualsCount - knownIndividualsCount);
				existingSegment.setIndividualsCount(individualsCount);
				existingSegment.setKnownIndividualsCount(knownIndividualsCount);

				return _segmentRepository.save(existingSegment);
			}

			return existingSegment;
		}
		finally {
			reentrantLock.unlock();
		}
	}

	public Segment updateSegment(Segment partialSegment, Long segmentId) {
		return _updateSegment(getSegment(segmentId), partialSegment);
	}

	public void updateSegments(Long activitiesCount) {
		_segmentRepository.updateActivitiesCountAndRemoveLastActivityDate(
			activitiesCount);
	}

	public Segment updateSegmentState(Long segmentId, String state) {
		Segment segment = getSegment(segmentId);

		segment.setState(state);

		return _segmentRepository.save(segment);
	}

	private void _addAsahTask(Segment segment) {
		if (Objects.equals(segment.getType(), Segment.Type.DYNAMIC)) {
			_asahTaskDog.scheduleAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					"dateModified",
					DateUtil.toUTCString(segment.getModifiedDate())
				).put(
					"individualSegmentJSONObject",
					_objectMapper.convertValue(segment, JSONObject.class)
				));
		}
	}

	private Exception _addAssetReferenceId(
		Long assetId, String key,
		Map<String, Set<String>> referencedObjectSets) {

		Set<String> referencedIds = referencedObjectSets.get(key);

		referencedIds.add(String.valueOf(assetId));

		Set<String> referencedAssetDataSourceIds = referencedObjectSets.get(
			"referencedAssetDataSourceIds");

		Asset asset = _assetDog.getAsset(assetId);

		referencedAssetDataSourceIds.add(
			String.valueOf(asset.getDataSourceId()));

		return null;
	}

	private Exception _addReferencedFieldMappingId(
		String fullFieldName, String ownerType,
		Set<String> referencedFieldMappingIds) {

		String[] fieldNameParts = fullFieldName.split("/");

		if (fieldNameParts.length < 2) {
			return null;
		}

		String context = fieldNameParts[0];

		if (ownerType == null) {
			if (context.equals("custom") || context.equals("demographics")) {
				ownerType = "individual";
			}
			else if (context.equals("organization")) {
				ownerType = "account";
			}
			else {
				return null;
			}
		}

		String fieldName = fieldNameParts[1];

		FieldMapping fieldMapping = _fieldMappingDog.fetchFieldMapping(
			context, fieldName, ownerType);

		if (fieldMapping == null) {
			return new Exception(
				"Unable to get field mapping with field name " + fieldName);
		}

		referencedFieldMappingIds.add(String.valueOf(fieldMapping.getId()));

		return null;
	}

	private Exception _addReferencedId(
		String collectionName, String id, String key,
		Map<String, Set<String>> referencedObjectSets) {

		Set<String> referencedIds = referencedObjectSets.get(key);

		referencedIds.add(id);

		Set<String> referencedAssetDataSourceIds = referencedObjectSets.get(
			"referencedAssetDataSourceIds");

		JSONObject referencedJSONObject = null;

		if (collectionName.equalsIgnoreCase("organizations") ||
			(DXPEntity.Type.ofCollectionName(collectionName) == null)) {

			referencedJSONObject = elasticsearchInvoker.get(collectionName, id);
		}
		else {
			referencedJSONObject = _objectMapper.convertValue(
				_dxpEntityDog.fetchByFieldsAndType(
					Collections.singletonMap("id", Long.valueOf(id)),
					DXPEntity.Type.ofCollectionName(collectionName)),
				JSONObject.class);
		}

		referencedAssetDataSourceIds.add(
			String.valueOf(referencedJSONObject.getLong("dataSourceId")));

		return null;
	}

	private Long _getAssetId(String[] terms) {
		if (terms.length != 3) {
			throw new IllegalArgumentException(
				"Invalid terms length: " + terms.length);
		}

		String fieldName = terms[0];
		String operator = terms[1];

		if ((!fieldName.startsWith("activities/") &&
			 !fieldName.equals("activityKey")) ||
			(!operator.equalsIgnoreCase("eq") &&
			 !operator.equalsIgnoreCase("ne"))) {

			return null;
		}

		String activityKey = StringUtil.unquote(terms[2]);

		return Long.valueOf(
			activityKey.substring(activityKey.lastIndexOf("#") + 1));
	}

	private List<Long> _getIndividualSegmentIds(Long segmentId) {
		List<Long> individualIds = _individualDog.getIdsBySegmentId(segmentId);

		if (individualIds.isEmpty()) {
			return Collections.emptyList();
		}

		return _membershipDog.getActiveIndividualSegmentIds(individualIds);
	}

	private Map<String, Set<String>> _getReferencedObjectIds(
		String filterString, String outerFunctionName) {

		if (filterString == null) {
			return Collections.emptyMap();
		}

		Map<String, Set<String>> referencedObjectSets = new HashMap<>();

		for (String referencedObjectName : _REFERENCED_OBJECT_NAMES) {
			referencedObjectSets.put(referencedObjectName, new HashSet<>());
		}

		Map<String, Set<String>> referencedObjectIds = FilterStringParser.parse(
			filterString, () -> referencedObjectSets,
			innerFilterString -> {
				try {
					Map<String, Set<String>> innerReferencedObjectIds =
						_getReferencedObjectIds(
							innerFilterString, outerFunctionName);

					Set<Map.Entry<String, Set<String>>> entrySet =
						referencedObjectSets.entrySet();

					Stream<Map.Entry<String, Set<String>>> stream =
						entrySet.stream();

					stream.forEach(
						entry -> {
							Set<String> values = entry.getValue();

							values.addAll(
								innerReferencedObjectIds.get(entry.getKey()));
						});
				}
				catch (Exception exception) {
					return exception;
				}

				return null;
			},
			logicalOperator -> null,
			terms -> _processLogicalOperator(
				outerFunctionName, referencedObjectSets, terms),
			functionData -> _processStringFunction(
				functionData, outerFunctionName, referencedObjectSets));

		if ((referencedObjectIds == null) || referencedObjectIds.isEmpty()) {
			referencedObjectIds = new HashMap<>();

			for (String referencedObjectName : _REFERENCED_OBJECT_NAMES) {
				referencedObjectIds.put(referencedObjectName, new HashSet<>());
			}
		}

		return referencedObjectIds;
	}

	private String _parserFilter(String filter) {
		if (filter != null) {
			Matcher matcher = _pattern.matcher(filter);

			while (matcher.find()) {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid value: " + matcher.group());
				}

				BigDecimal bigDecimal = new BigDecimal(matcher.group());

				BigDecimal maxIntegerBigDecimal = BigDecimal.valueOf(
					Integer.MAX_VALUE);

				if (bigDecimal.compareTo(maxIntegerBigDecimal) > 0) {
					filter = StringUtils.replace(
						filter, matcher.group(),
						String.valueOf(Integer.MAX_VALUE));
				}
			}
		}

		return filter;
	}

	private Exception _processLogicalOperator(
		String outerFunctionName, Map<String, Set<String>> referencedObjectSets,
		String[] terms) {

		if (StringUtils.equals(outerFunctionName, "organizations.filter")) {
			return _processOrganizationLogicalOperator(
				referencedObjectSets, terms);
		}

		try {
			Long assetId = _getAssetId(terms);

			if (assetId != null) {
				return _addAssetReferenceId(
					assetId, "referencedAssetIds", referencedObjectSets);
			}

			DXPEntity.Type dxpEntityType = DXPEntity.Type.ofIndividualFieldName(
				terms[0]);

			if (dxpEntityType != null) {
				return _addReferencedId(
					dxpEntityType.getCollectionName(),
					StringUtil.unquote(terms[2]),
					dxpEntityType.getIndividualSegmentFieldName(),
					referencedObjectSets);
			}

			return _addReferencedFieldMappingId(
				terms[0], null,
				referencedObjectSets.get("referencedFieldMappingIds"));
		}
		catch (Exception exception) {
			return exception;
		}
	}

	private Exception _processOrganizationLogicalOperator(
		Map<String, Set<String>> referencedObjectSets, String[] terms) {

		if (terms.length != 3) {
			return null;
		}

		String fieldName = terms[0];

		if (fieldName.equals("id") || fieldName.equals("parentId")) {
			return _addReferencedId(
				"organizations", StringUtil.unquote(terms[2]),
				"referencedOrganizationIds", referencedObjectSets);
		}

		return _addReferencedFieldMappingId(
			fieldName, "organization",
			referencedObjectSets.get("referencedFieldMappingIds"));
	}

	private Exception _processStringFunction(
		Object[] functionData, String outerFunctionName,
		Map<String, Set<String>> referencedObjectSets) {

		List<String> arguments = (List<String>)functionData[0];

		String name = (String)functionData[2];

		if (Objects.equals(name, "accounts.filter") ||
			Objects.equals(name, "accounts.filterByCount") ||
			Objects.equals(name, "activities.filter") ||
			Objects.equals(name, "activities.filterByCount") ||
			Objects.equals(name, "organizations.filter")) {

			String[] argumentNames = null;

			if (name.contains("filterByCount")) {
				argumentNames = new String[] {"filter", "operator", "value"};
			}
			else {
				argumentNames = new String[] {"filter"};
			}

			String[] argumentValues = FilterUtil.getArgumentValues(
				arguments, argumentNames);

			try {
				if (Objects.equals(name, "organizations.filter")) {
					outerFunctionName = name;
				}

				Map<String, Set<String>> referencedObjectIds =
					_getReferencedObjectIds(
						StringUtil.unquoteAndDecodeInnerQuotes(
							argumentValues[0]),
						outerFunctionName);

				Set<Map.Entry<String, Set<String>>> entrySet =
					referencedObjectSets.entrySet();

				Stream<Map.Entry<String, Set<String>>> stream =
					entrySet.stream();

				stream.forEach(
					entry -> {
						Set<String> values = entry.getValue();

						values.addAll(referencedObjectIds.get(entry.getKey()));
					});
			}
			catch (Exception exception) {
				return exception;
			}
		}
		else if (Objects.equals(name, "contains") ||
				 Objects.equals(name, "endsWith") ||
				 Objects.equals(name, "startsWith")) {

			if (arguments.size() != 2) {
				return new IllegalArgumentException(
					"Expected 2 arguments for " + name + " function, got " +
						arguments.size() + " instead: " + arguments);
			}

			String ownerType = null;

			if (StringUtils.equals(outerFunctionName, "organizations.filter")) {
				ownerType = "organization";
			}

			return _addReferencedFieldMappingId(
				arguments.get(0), ownerType,
				referencedObjectSets.get("referencedFieldMappingIds"));
		}

		return null;
	}

	private void _replaceAccount(Segment segment) {
		if (!Objects.equals(segment.getStatus(), "INACTIVE")) {
			return;
		}

		String name = segment.getName();

		if (!name.startsWith(_ACCOUNT_PREFIX)) {
			return;
		}

		Account account = _accountDog.getAccount(
			Long.valueOf(name.substring(_ACCOUNT_PREFIX.length())), null);

		account.setActiveIndividualsCount(segment.getActiveIndividualsCount());
		account.setActivitiesCount(segment.getActivitiesCount());

		if (Objects.nonNull(segment.getActivitiesCount())) {
			List<Individual.ActivitiesCount> individualActivitiesCounts =
				_individualDog.getActivitiesCounts(
					BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
					segment.getId());

			if (!individualActivitiesCounts.isEmpty()) {
				Set<Account.AccountActivityCount> activitiesCounts =
					new HashSet<>();

				for (Individual.ActivitiesCount individualActivitiesCount :
						individualActivitiesCounts) {

					activitiesCounts.add(
						new Account.AccountActivityCount(
							individualActivitiesCount.getActivitiesCount(),
							individualActivitiesCount.getChannelId()));
				}

				account.setActivitiesCounts(activitiesCounts);
			}
		}

		account.setIndividualsCount(segment.getIndividualsCount());

		if (Objects.nonNull(segment.getIndividualsCount())) {
			Map<Long, Long> channelIndividualCounts =
				_individualDog.getIndividualCounts(
					BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers()),
					segment.getId());

			if (!channelIndividualCounts.isEmpty()) {
				Set<Account.AccountIndividualCount> individualsCounts =
					new HashSet<>();

				for (Map.Entry<Long, Long> entry :
						channelIndividualCounts.entrySet()) {

					individualsCounts.add(
						new Account.AccountIndividualCount(
							entry.getKey(), entry.getValue()));
				}

				account.setIndividualsCounts(individualsCounts);
			}
		}

		_accountDog.updateAccount(account);
	}

	private void _setState(Segment segment) {
		if ((segment.getType() == null) ||
			Objects.equals(segment.getType(), Segment.Type.DYNAMIC)) {

			segment.setState("IN_PROGRESS");
		}
		else {
			segment.setState("READY");
		}
	}

	private void _updateMemberships(Segment segment) {
		if (Objects.equals(segment.getType(), Segment.Type.DYNAMIC)) {
			_addAsahTask(segment);

			return;
		}

		List<Long> individualIds = _membershipDog.getActiveIndividualIds(
			segment.getId());

		for (Long individualId : individualIds) {
			Individual individual = _individualDog.fetchIndividual(
				individualId);

			if (individual == null) {
				continue;
			}

			if (!CollectionUtils.containsAny(
					individual.getChannelIds(), segment.getChannelId())) {

				_membershipDog.deactivateBQMembership(
					new Date(), individualId, segment.getId());
			}
		}
	}

	private Segment _updateSegment(
		Segment existingSegment, Segment partialSegment) {

		if (StringUtils.isNotEmpty(partialSegment.getFilter()) &&
			Objects.equals(
				existingSegment.getFilter(), partialSegment.getFilter()) &&
			!Objects.isNull(partialSegment.getIncludeAnonymousUsers()) &&
			Objects.equals(
				existingSegment.getIncludeAnonymousUsers(),
				partialSegment.getIncludeAnonymousUsers())) {

			existingSegment.setModifiedDate(partialSegment.getModifiedDate());
			existingSegment.setName(partialSegment.getName());
			existingSegment.setState(partialSegment.getState());
			existingSegment.setStatus(partialSegment.getStatus());

			existingSegment = _segmentRepository.save(existingSegment);
		}
		else {
			setReferencedFields(partialSegment);

			_setState(partialSegment);

			BeanUtils.copyProperties(partialSegment, existingSegment);

			existingSegment.setFilter(
				_parserFilter(existingSegment.getFilter()));

			existingSegment = _segmentRepository.save(existingSegment);

			_addAsahTask(existingSegment);
		}

		_replaceAccount(existingSegment);

		return existingSegment;
	}

	private static final String _ACCOUNT_PREFIX = "Account: ";

	private static final String[] _REFERENCED_OBJECT_NAMES = {
		"referencedAssetDataSourceIds", "referencedAssetIds",
		"referencedFieldMappingIds", "referencedGroupIds",
		"referencedOrganizationIds", "referencedRoleIds", "referencedTeamIds",
		"referencedUserGroupIds", "referencedUserIds"
	};

	private static final Log _log = LogFactory.getLog(SegmentDog.class);

	private static final Pattern _pattern = Pattern.compile(
		"(?<=[ ])[0-9]+[.]{0,1}[0-9]*(([e][+]){1}[0-9]+){0,1}");

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Value("${osb.asah.data.dog.query.response.threshold:10000}")
	private int _queryResponseThreshold;

	@Autowired
	private SegmentRepository _segmentRepository;

}