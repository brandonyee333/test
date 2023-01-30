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
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.parser.FilterStringParser;
import com.liferay.osb.asah.common.postgresql.converter.helper.SegmentFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.ListUtil;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
 */
@Component
public class SegmentDog {

	public Segment addSegment(
		Date createDate, String filterString, Date modifiedDate, String name,
		String scope, String type, String status) {

		Segment segment = new Segment();

		segment.setCreateDate(createDate);
		segment.setFilter(_parseFilterString(filterString));
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

		segment.setFilter(_parseFilterString(segment.getFilter()));

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

		if (segments.isEmpty()) {
			return;
		}

		_segmentRepository.deleteAll(segments);

		_asahTaskDog.scheduleAsahTask(
			"DeleteIndividualSegmentTasksNanite",
			JSONUtil.put(
				"individualSegmentIds",
				JSONUtil.toJSONArray(segments, Segment::getId)));
	}

	public void disableDynamicSegments(
		Long dataSourceId, List<String> fieldMappingIds) {

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

	public Date getLastActivityDate(Segment segment) {

		// TODO Implement operation

		return null;
	}

	public Map<Long, Date> getLastActivityDates(List<Segment> segments) {
		Map<Long, Date> lastActivityDateMap = new HashMap();

		for (Segment segment : segments) {
			lastActivityDateMap.put(
				segment.getId(), getLastActivityDate(segment));
		}

		return lastActivityDateMap;
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
		String filterString, String state, String status, int page, int size) {

		return _segmentRepository.searchSegments(
			filterString, state, status, PageRequest.of(page, size));
	}

	public Map<String, JSONObject> getSegmentsJSONObjects(
			List<Individual> individuals)
		throws Exception {

		Map<String, JSONObject> segmentsJSONObjects = new HashMap<>();

		for (Individual individual : individuals) {
			segmentsJSONObjects.put(
				individual.getId(),
				JSONUtil.put(
					"individual-segments",
					JSONUtil.toJSONArray(
						getSegments(
							_bqMembershipDog.getSegmentIds(
								String.valueOf(individual.getId()))),
						segment -> _objectMapper.convertValue(
							segment, JSONObject.class))));
		}

		return segmentsJSONObjects;
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

	public List<Segment> searchDynamicSegments(
		String filterString, Boolean includeAnonymousUsers, int page,
		Set<Long> segmentIds, int size,
		com.liferay.osb.asah.common.model.Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return _segmentRepository.searchDynamicSegments(
			new FilterHelper(filterString), includeAnonymousUsers, pageRequest,
			segmentIds);
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

		List<Long> fieldMappingIds = Collections.emptyList();

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
			_channelRepository.findByDataSourceId(dataSourceId),
			Channel::getId);

		FilterHelper filterHelper = new FilterHelper(
			null, filterString,
			new SegmentFilterStringConverterHelper(_bqMembershipChangeDog));

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_segmentRepository.searchSegments(
				channelIds, filterHelper, pageRequest),
			pageRequest,
			() -> _segmentRepository.countSegments(channelIds, filterHelper));
	}

	public Page<Segment> searchSegmentPage(
		String filterString, String identityId, int page, int size,
		String[] sorts) {

		List<Map<String, Long>> segmentIdIdentityCounts =
			_bqMembershipDog.getActiveSegmentIds(identityId);

		FilterHelper filterHelper = new FilterHelper(filterString);

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_segmentRepository.searchSegments(
				filterHelper, segmentIdIdentityCounts, pageRequest),
			pageRequest,
			() -> _segmentRepository.countSegments(
				filterHelper, segmentIdIdentityCounts));
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
		segment.setReferencedFieldMappingIds(
			SetUtil.map(
				referencedObjectIds.get("referencedFieldMappingIds"),
				String::valueOf));
	}

	public Segment updateSegment(Segment partialSegment, Long segmentId) {
		return _updateSegment(getSegment(segmentId), partialSegment);
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
		Long assetId, Map<String, Set<String>> referencedObjectSets) {

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

		String fieldName = fieldNameParts[1];

		referencedFieldMappingIds.add(fieldName);

		return null;
	}

	private Exception _addReferencedId(
		String collectionName, String id,
		Map<String, Set<String>> referencedObjectSets) {

		Set<String> referencedAssetDataSourceIds = referencedObjectSets.get(
			"referencedAssetDataSourceIds");

		if (collectionName.equalsIgnoreCase("organizations") ||
			(DXPEntity.Type.ofCollectionName(collectionName) == null)) {

			BQOrganization bqOrganization =
				_bqOrganizationDog.getBQOrganization(id);

			referencedAssetDataSourceIds.add(
				String.valueOf(bqOrganization.getDataSourceId()));
		}
		else {
			DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
				Collections.singletonMap("id", Long.valueOf(id)),
				DXPEntity.Type.ofCollectionName(collectionName));

			referencedAssetDataSourceIds.add(
				String.valueOf(dxpEntity.getDataSourceId()));
		}

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

	private String _parseFilterString(String filterString) {
		if (filterString != null) {
			Matcher matcher = _pattern.matcher(filterString);

			while (matcher.find()) {
				if (_log.isWarnEnabled()) {
					_log.warn("Invalid value: " + matcher.group());
				}

				BigDecimal bigDecimal = new BigDecimal(matcher.group());

				BigDecimal maxIntegerBigDecimal = BigDecimal.valueOf(
					Integer.MAX_VALUE);

				if (bigDecimal.compareTo(maxIntegerBigDecimal) > 0) {
					filterString = StringUtils.replace(
						filterString, matcher.group(),
						String.valueOf(Integer.MAX_VALUE));
				}
			}
		}

		return filterString;
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
				return _addAssetReferenceId(assetId, referencedObjectSets);
			}

			DXPEntity.Type dxpEntityType = DXPEntity.Type.ofIndividualFieldName(
				terms[0]);

			if (dxpEntityType != null) {
				return _addReferencedId(
					dxpEntityType.getCollectionName(),
					StringUtil.unquote(terms[2]), referencedObjectSets);
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

		if (Objects.equals(name, "activities.filter") ||
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
				_parseFilterString(existingSegment.getFilter()));

			existingSegment = _segmentRepository.save(existingSegment);

			_addAsahTask(existingSegment);
		}

		return existingSegment;
	}

	private static final String[] _REFERENCED_OBJECT_NAMES = {
		"referencedAssetDataSourceIds", "referencedFieldMappingIds"
	};

	private static final Log _log = LogFactory.getLog(SegmentDog.class);

	private static final Pattern _pattern = Pattern.compile(
		"(?<=[ ])[0-9]+[.]{0,1}[0-9]*(([e][+]){1}[0-9]+){0,1}");

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private BQMembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private BQOrganizationDog _bqOrganizationDog;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentRepository _segmentRepository;

}