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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.FilterUtil;
import com.liferay.osb.asah.common.faro.info.dog.BaseFaroInfoDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoAccountDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPEntityType;
import com.liferay.osb.asah.common.model.Segment;
import com.liferay.osb.asah.common.parser.FilterStringParser;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.annotation.CacheEvict;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

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
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class SegmentDog extends BaseFaroInfoDog {

	public Segment addSegment(
			Long activitiesCount, Date createDate, String filter,
			Date modifiedDate, String name, String scope, String type,
			String status)
		throws Exception {

		Segment segment = new Segment();

		segment.setActivitiesCount(activitiesCount);
		segment.setCreateDate(createDate);
		segment.setFilter(filter);
		segment.setModifiedDate(modifiedDate);
		segment.setName(name);
		segment.setScope(scope);
		segment.setType(Segment.Type.valueOf(type));
		segment.setStatus(status);

		return addSegment(segment);
	}

	public Segment addSegment(Segment segment) throws Exception {
		setReferencedFields(segment);

		if ((segment.getType() == null) ||
			(segment.getType() == Segment.Type.DYNAMIC)) {

			segment.setState("IN_PROGRESS");
		}
		else {
			segment.setState("READY");
		}

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
			JSONUtil.put("individualSegmentId", String.valueOf(segmentId)));
	}

	public void deleteSegments(Set<Long> channelIds) {
		_segmentRepository.deleteByChannelIdIn(channelIds);
	}

	public void disableDynamicSegments(
			DXPEntityType dxpEntityType, Long segmentId)
		throws Exception {

		if (Objects.isNull(segmentId)) {
			return;
		}

		List<Segment> segments = _segmentRepository.searchSegments(
			dxpEntityType, segmentId, "DISABLED", Segment.Type.DYNAMIC);

		for (Segment segment : segments) {
			segment.setState("DISABLED");

			_updateSegment(segment, segment);
		}
	}

	public void disableDynamicSegments(
			Long dataSourceId, List<Long> fieldMappingIds)
		throws Exception {

		if ((dataSourceId == null) && fieldMappingIds.isEmpty()) {
			return;
		}

		List<Segment> segments;

		if ((dataSourceId != null) && !fieldMappingIds.isEmpty()) {
			segments =
				_segmentRepository.
					findByReferencedAssetDataSourceIdsOrReferencedFieldMappingIdsInAndStateNotAndType(
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
					findByReferencedFieldMappingIdsInAndStateNotAndType(
						fieldMappingIds, "DISABLED", Segment.Type.DYNAMIC);
		}
		else {
			segments = _segmentRepository.findByStateNotAndType(
				"DISABLED", Segment.Type.DYNAMIC);
		}

		for (Segment segment : segments) {
			segment.setState("DISABLED");

			_updateSegment(segment, segment);
		}
	}

	public boolean existsSegment(Long segmentId) {
		return _segmentRepository.existsById(segmentId);
	}

	public Segment fetchSegment(String name, String status) {
		Optional<Segment> segmentOptional =
			_segmentRepository.findByNameAndStatus(name, status);

		return segmentOptional.orElse(null);
	}

	public List<Long> getReferencedAssetIds() throws Exception {
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
		if (segmentIds == null) {
			return Collections.emptyList();
		}

		if (Objects.nonNull(channelId)) {
			return _segmentRepository.findNameByChannelIdAndIdInAndStatus(
				channelId, new ArrayList<>(segmentIds), "ACTIVE");
		}

		return _segmentRepository.findNameByIdInAndStatus(
			new ArrayList<>(segmentIds), "ACTIVE");
	}

	public Page<Segment> getSegmentPage(Long segmentId, int size, Sort sort) {
		return PageableExecutionUtils.getPage(
			_segmentRepository.findByIdAfter(
				segmentId, PageRequest.of(0, size, sort)),
			PageRequest.of(0, size, sort),
			() -> _segmentRepository.countByIdAfter(segmentId));
	}

	public List<Segment> getSegments(int page, int size) {
		return _segmentRepository.findAll(PageRequest.of(page, size));
	}

	public List<Segment> getSegments(List<Long> segmentIds) {
		return IterableUtils.toList(_segmentRepository.findAllById(segmentIds));
	}

	public List<Segment> getSegments(
		String filter, String state, String status, int page, int size) {

		return _segmentRepository.searchSegments(
			filter, state, status, PageRequest.of(page, size));
	}

	public Page<Segment> getSegmentsPage(int page, int size) {
		return PageableExecutionUtils.getPage(
			_segmentRepository.findAll(PageRequest.of(page, size)),
			PageRequest.of(page, size), _segmentRepository::count);
	}

	public boolean isIncludeAnonymousUsers(Long segmentId) {
		Segment segment = getSegment(segmentId);

		return BooleanUtils.toBooleanDefaultIfNull(
			segment.getIncludeAnonymousUsers(), false);
	}

	public Segment replaceSegment(Segment segment) throws Exception {
		Long segmentId = segment.getId();

		if (segmentId == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Unable to replace a segment without ID");
		}

		_replaceAccount(segment);

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

	public List<Segment> searchDynamicSegments(
		String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return _segmentRepository.searchDynamicSegments(
			filterString, pageRequest);
	}

	public Page<Segment> searchPreviewDisabledSegmentsPage(
		Long dataSourceId, String filterString, int page, int size,
		String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_segmentRepository.searchPreviewDisabledSegments(
				dataSourceId, filterString, pageRequest),
			pageRequest,
			() -> _segmentRepository.countPreviewDisabledSegments(
				dataSourceId, filterString));
	}

	public Page<Segment> searchSegmentsPage(
		Long dataSourceId, String filterString, int page, int size,
		String[] sorts) {

		PageRequest pageRequest = PageRequest.of(page, size, _getSort(sorts));

		return PageableExecutionUtils.getPage(
			_segmentRepository.searchSegments(
				dataSourceId, filterString, pageRequest),
			pageRequest,
			() -> _segmentRepository.countSegments(dataSourceId, filterString));
	}

	@CacheEvict("getReferencedAssetIds")
	public void setReferencedFields(Segment segment) throws Exception {
		Map<String, Set<String>> referencedObjectIds = _getReferencedObjectIds(
			segment.getFilter(), null);

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

	public Segment updateSegment(
			long individualCount, long knownIndividualCount, Long segmentId)
		throws Exception {

		Segment segment = new Segment();

		segment.setAnonymousIndividualCount(
			individualCount - knownIndividualCount);
		segment.setIndividualCount(knownIndividualCount);
		segment.setKnownIndividualCount(knownIndividualCount);

		return _updateSegment(getSegment(segmentId), segment);
	}

	public Segment updateSegment(Segment partialSegment, Long segmentId)
		throws Exception {

		return _updateSegment(getSegment(segmentId), partialSegment);
	}

	public void updateSegments(Long activitiesCount) {
		_segmentRepository.updateActivitiesCountAndRemoveLastActivityDate(
			activitiesCount);
	}

	private static String _getAssetId(String[] terms) {
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

		return activityKey.substring(activityKey.lastIndexOf("#") + 1);
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

		JSONObject fieldMappingJSONObject =
			_faroInfoFieldMappingDog.fetchFieldMappingJSONObject(
				context, fieldName, ownerType);

		if (fieldMappingJSONObject == null) {
			return new Exception(
				"Unable to get field mapping with field name " + fieldName);
		}

		referencedFieldMappingIds.add(fieldMappingJSONObject.getString("id"));

		return null;
	}

	private Exception _addReferencedId(
		String collectionName, String dataSourceIdFieldName,
		ElasticsearchInvoker elasticsearchInvoker, String id, String key,
		Map<String, Set<String>> referencedObjectSets) {

		Set<String> referencedIds = referencedObjectSets.get(key);

		referencedIds.add(id);

		Set<String> referencedAssetDataSourceIds = referencedObjectSets.get(
			"referencedAssetDataSourceIds");

		JSONObject referencedJSONObject = elasticsearchInvoker.get(
			collectionName, id);

		referencedAssetDataSourceIds.add(
			referencedJSONObject.getString(dataSourceIdFieldName));

		return null;
	}

	private String _getAccountId(Segment segment) {
		if (!Objects.equals(segment.getStatus(), "INACTIVE")) {
			return null;
		}

		String name = segment.getName();

		if (!name.startsWith(_ACCOUNT_PREFIX)) {
			return null;
		}

		return name.substring(_ACCOUNT_PREFIX.length());
	}

	private Map<String, Set<String>> _getReferencedObjectIds(
		String filterString, String outerFunctionName) {

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
				catch (Exception e) {
					return e;
				}

				return null;
			},
			logicalOperator -> null,
			terms -> _processLogicalOperator(
				outerFunctionName, referencedObjectSets, terms),
			functionData -> _processStringFunction(
				functionData, outerFunctionName, referencedObjectSets));

		if (referencedObjectIds == null) {
			referencedObjectIds = new HashMap<>();

			for (String referencedObjectName : _REFERENCED_OBJECT_NAMES) {
				referencedObjectIds.put(referencedObjectName, new HashSet<>());
			}

			return referencedObjectIds;
		}

		return referencedObjectIds;
	}

	private Sort _getSort(String[] sorts) {
		if (ArrayUtils.isEmpty(sorts)) {
			return Sort.by(Sort.Order.desc("id"));
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

	private Exception _processLogicalOperator(
		String outerFunctionName, Map<String, Set<String>> referencedObjectSets,
		String[] terms) {

		if (StringUtils.equals(outerFunctionName, "organizations.filter")) {
			return _processOrganizationLogicalOperator(
				referencedObjectSets, terms);
		}

		try {
			String assetId = _getAssetId(terms);

			if (assetId != null) {
				return _addReferencedId(
					"assets", "dataSourceId", elasticsearchInvoker, assetId,
					"referencedAssetIds", referencedObjectSets);
			}

			DXPEntityType dxpEntityType = DXPEntityType.ofIndividualFieldName(
				terms[0]);

			if (dxpEntityType != null) {
				if (dxpEntityType.isOrganization()) {
					return _addReferencedId(
						dxpEntityType.getCollectionName(), "dataSourceId",
						elasticsearchInvoker, StringUtil.unquote(terms[2]),
						dxpEntityType.getIndividualSegmentFieldName(),
						referencedObjectSets);
				}

				return _addReferencedId(
					dxpEntityType.getCollectionName(), "osbAsahDataSourceId",
					_dxpRawElasticsearchInvoker, StringUtil.unquote(terms[2]),
					dxpEntityType.getIndividualSegmentFieldName(),
					referencedObjectSets);
			}

			return _addReferencedFieldMappingId(
				terms[0], null,
				referencedObjectSets.get("referencedFieldMappingIds"));
		}
		catch (Exception e) {
			return e;
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
				"organizations", "dataSourceId", elasticsearchInvoker,
				StringUtil.unquote(terms[2]), "referencedOrganizationIds",
				referencedObjectSets);
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
			catch (Exception e) {
				return e;
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
		String accountId = _getAccountId(segment);

		if (accountId == null) {
			return;
		}

		JSONObject accountJSONObject = _faroInfoAccountDog.getAccountJSONObject(
			accountId);

		if (Objects.nonNull(segment.getActivitiesCount())) {
			JSONArray activitiesCountsJSONArray =
				_faroInfoIndividualDog.getActivitiesCountsJSONArray(
					segment.getIncludeAnonymousUsers(), segment.getId());

			if (activitiesCountsJSONArray != null) {
				accountJSONObject.put(
					"activitiesCounts", activitiesCountsJSONArray);
			}
		}

		if (Objects.nonNull(segment.getActivitiesCount())) {
			JSONArray individualCountsJSONArray =
				_faroInfoIndividualDog.getIndividualCountsJSONArray(
					segment.getIncludeAnonymousUsers(), segment.getId());

			if (individualCountsJSONArray != null) {
				accountJSONObject.put(
					"individualCounts", individualCountsJSONArray);
			}
		}

		accountJSONObject.put(
			"activeIndividualCount", segment.getActiveIndividualCount());
		accountJSONObject.put("activitiesCount", segment.getActivitiesCount());
		accountJSONObject.put("individualCount", segment.getIndividualCount());

		if (Objects.nonNull(segment.getLastActivityDate())) {
			accountJSONObject.put(
				"lastActivityDate",
				DateUtil.toUTCString(segment.getLastActivityDate()));
		}
		else {
			accountJSONObject.put("lastActivityDate", (Object)null);
		}

		_faroInfoAccountDog.replaceAccount(accountJSONObject);
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

	private void _updateAccount(
		Segment existingSegment, Segment partialSegment) {

		String accountId = _getAccountId(existingSegment);

		if (accountId == null) {
			return;
		}

		JSONObject partialAccountJSONObject = new JSONObject();

		if (Objects.nonNull(partialSegment.getActivitiesCount())) {
			Boolean includeAnonymousUsers =
				(partialSegment.getIncludeAnonymousUsers() != null) ?
					partialSegment.getIncludeAnonymousUsers() :
						existingSegment.getIncludeAnonymousUsers();

			JSONArray activitiesCountsJSONArray =
				_faroInfoIndividualDog.getActivitiesCountsJSONArray(
					includeAnonymousUsers, existingSegment.getId());

			if (activitiesCountsJSONArray != null) {
				partialAccountJSONObject.put(
					"activitiesCounts", activitiesCountsJSONArray);
			}
		}

		if (Objects.nonNull(partialSegment.getActivitiesCount())) {
			Boolean includeAnonymousUsers =
				(partialSegment.getIncludeAnonymousUsers() != null) ?
					partialSegment.getIncludeAnonymousUsers() :
						existingSegment.getIncludeAnonymousUsers();

			JSONArray individualCountsJSONArray =
				_faroInfoIndividualDog.getIndividualCountsJSONArray(
					includeAnonymousUsers, existingSegment.getId());

			if (individualCountsJSONArray != null) {
				partialAccountJSONObject.put(
					"individualCounts", individualCountsJSONArray);
			}
		}

		if (!Objects.equals(
				existingSegment.getActiveIndividualCount(),
				partialSegment.getActiveIndividualCount())) {

			partialAccountJSONObject.put(
				"activeIndividualCount",
				partialSegment.getActiveIndividualCount());
		}
		else {
			partialAccountJSONObject.put("activeIndividualCount", (Object)null);
		}

		if (!Objects.equals(
				existingSegment.getActivitiesCount(),
				partialSegment.getActivitiesCount())) {

			partialAccountJSONObject.put(
				"activitiesCount", partialSegment.getActivitiesCount());
		}
		else {
			partialAccountJSONObject.put("activitiesCount", (Object)null);
		}

		if (!Objects.equals(
				existingSegment.getIndividualCount(),
				partialSegment.getIndividualCount())) {

			partialAccountJSONObject.put(
				"individualCount", partialSegment.getIndividualCount());
		}
		else {
			partialAccountJSONObject.put("individualCount", (Object)null);
		}

		if (!Objects.equals(
				existingSegment.getLastActivityDate(),
				partialSegment.getLastActivityDate())) {

			partialAccountJSONObject.put(
				"lastActivityDate",
				DateUtil.toUTCString(partialSegment.getLastActivityDate()));
		}
		else {
			partialAccountJSONObject.put("lastActivityDate", (Object)null);
		}

		if (partialAccountJSONObject.length() > 0) {
			_faroInfoAccountDog.updateAccount(
				accountId, partialAccountJSONObject);
		}
	}

	private void _updateMemberships(Segment segment) throws Exception {
		if (Objects.equals(segment.getType(), Segment.Type.DYNAMIC)) {
			_addAsahTask(segment);

			return;
		}

		List<Long> individualIds = _membershipDog.getActiveIndividualIds(
			segment.getId());

		for (Long individualId : individualIds) {
			JSONObject individualJSONObject =
				_faroInfoIndividualDog.fetchIndividualJSONObject(
					String.valueOf(individualId));

			if (individualJSONObject == null) {
				continue;
			}

			JSONArray channelIdsJSONArray = individualJSONObject.optJSONArray(
				"channelIds");

			if ((channelIdsJSONArray != null) &&
				!JSONUtil.hasValue(
					channelIdsJSONArray,
					String.valueOf(segment.getChannelId()))) {

				_membershipDog.deactivateMembership(
					new Date(), individualId, segment.getId());
			}
		}
	}

	private Segment _updateSegment(
			Segment existingSegment, Segment partialSegment)
		throws Exception {

		_updateAccount(existingSegment, partialSegment);

		if ((Objects.isNull(partialSegment.getFilter()) ||
			 Objects.equals(
				 existingSegment.getFilter(), partialSegment.getFilter())) &&
			(Objects.isNull(partialSegment.getIncludeAnonymousUsers()) ||
			 Objects.equals(
				 existingSegment.getIncludeAnonymousUsers(),
				 partialSegment.getIncludeAnonymousUsers()))) {

			BeanUtils.copyProperties(partialSegment, existingSegment);

			existingSegment = _segmentRepository.save(existingSegment);
		}
		else {
			setReferencedFields(partialSegment);

			_setState(partialSegment);

			BeanUtils.copyProperties(partialSegment, existingSegment);

			existingSegment.setFilter(partialSegment.getFilter());
			existingSegment.setIncludeAnonymousUsers(
				partialSegment.getIncludeAnonymousUsers());

			existingSegment = _segmentRepository.save(existingSegment);

			_addAsahTask(existingSegment);
		}

		return existingSegment;
	}

	private static final String _ACCOUNT_PREFIX = "Account: ";

	private static final String[] _REFERENCED_OBJECT_NAMES = {
		"referencedAssetDataSourceIds", "referencedAssetIds",
		"referencedFieldMappingIds", "referencedGroupIds",
		"referencedOrganizationIds", "referencedRoleIds", "referencedTeamIds",
		"referencedUserGroupIds", "referencedUserIds"
	};

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private FaroInfoAccountDog _faroInfoAccountDog;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Value("${osb.asah.data.dog.query.response.threshold:10000}")
	private int _queryResponseThreshold;

	@Autowired
	private SegmentRepository _segmentRepository;

}