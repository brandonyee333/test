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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.dto.BQMembershipChangeDTO;
import com.liferay.osb.asah.backend.dto.BQMembershipDTO;
import com.liferay.osb.asah.backend.dto.IndividualDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.rest.response.function.MembershipChangesHistogramTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.annotation.Cacheable;
import com.liferay.osb.asah.common.spring.annotation.SuppressErrorLogging;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.ResourceNotFoundException;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 * @author Rachael Koestartyo
 */
@RequestMapping(
	produces = "application/json", value = "/api/1.0/individual-segments"
)
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualSegmentsRestController"
)
public class IndividualSegmentsRestController extends BaseRestController {

	@GetMapping(params = "!apply", value = "/{id}/individuals")
	@SuppressErrorLogging(ResourceNotFoundException.class)
	public PageDTO<IndividualDTO> getIndividualDTOPageDTO(
		@PathVariable Long id,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(required = false) Boolean includeAnonymousUsers,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toIndividualDTOPageDTO(
			_individualDog.searchIndividualPage(
				filterString, includeAnonymousUsers, id, page, size, sorts));
	}

	@GetMapping(params = "!apply", value = "/{id}/membership-changes")
	public PageDTO<BQMembershipChangeDTO> getMembershipChangeDTOPageDTO(
		@PathVariable Long id, @RequestParam(required = false) String expand,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<BQMembershipChange> bqMembershipChangesPages =
			_bqMembershipChangeDog.searchBQMembershipChangePages(
				filterString, id, page, size, sorts);

		if (StringUtils.isEmpty(expand)) {
			return _toMembershipChangeDTOPageDTO(bqMembershipChangesPages);
		}

		List<BQMembershipChange> bqMembershipChanges =
			bqMembershipChangesPages.getContent();

		Set<BQMembershipChangeDTO> bqMembershipChangeDTOs =
			new LinkedHashSet<>();

		Stream<BQMembershipChange> stream = bqMembershipChanges.stream();

		stream.forEachOrdered(
			membershipChange -> bqMembershipChangeDTOs.add(
				new BQMembershipChangeDTO(membershipChange)));

		return _toMembershipChangeDTOPageDTO(
			new BQMembershipChangeDTO(bqMembershipChangeDTOs),
			bqMembershipChangesPages);
	}

	@Cacheable
	@GetMapping(params = "apply", value = "/{id}/membership-changes")
	public String getMembershipChangeTransformations(
			@PathVariable String id, @RequestParam String apply,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "true") boolean includeToday,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			apply, "membership-changes", page,
			_getMembershipChangesQueryBuilder(filterString, id), size,
			"dateChanged",
			new MembershipChangesHistogramTransformationJSONArrayFunction(
				includeToday),
			"membership-change-transformations");
	}

	@GetMapping("/{id}/memberships")
	public PageDTO<BQMembershipDTO> getMembershipDTOPageDTO(
		@PathVariable Long id,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		if (!segmentDog.isIncludeAnonymousUsers(id)) {
			return _toMembershipDTOPageDTO(
				membershipDog.getBQMembershipPage(
					_individualDog.getKnownIndividualIds(filterString, id), id,
					"ACTIVE", page, size, sorts));
		}

		return _toMembershipDTOPageDTO(
			membershipDog.getBQMembershipPage(id, "ACTIVE", page, size, sorts));
	}

	@GetMapping("/{id}")
	public SegmentDTO getSegmentDTO(
			@PathVariable Long id,
			@RequestParam(required = false) String expand)
		throws Exception {

		Segment segment = segmentDog.getSegment(id);

		if (segment.getType() == Segment.Type.DYNAMIC) {
			segment = _resetIndividualsCount(segmentDog.getSegment(id));

			List<BQMembershipChange> bqMembershipsChanges =
				_bqMembershipChangeDog.getLastBeforeTodayByIndividualSegmentsId(
					Collections.singletonList(segment.getId()));

			if (!bqMembershipsChanges.isEmpty()) {
				_updateSegmentCounts(bqMembershipsChanges.get(0), segment);
			}

			segment.setActiveIndividualsCount(
				_individualDog.countActiveIndividualsFromLast30DaysBySegment(
					segment));
		}

		SegmentDTO segmentDTO = new SegmentDTO(segment);

		if (StringUtils.isNotEmpty(expand)) {
			String[] expandParts = expand.split(",");

			for (String expandPart : expandParts) {
				if (expandPart.equals("referenced-objects")) {
					segmentDTO.setEmbedded(
						Collections.singletonMap(
							"referenced-objects",
							_getReferencedObjectsJSONObject(segment)));
				}
				else if (_log.isWarnEnabled()) {
					_log.warn("Invalid expand: " + expandPart);
				}
			}
		}

		return segmentDTO;
	}

	@GetMapping(params = "!apply")
	public PageDTO<SegmentDTO> getSegmentDTOPageDTO(
		@RequestParam(required = false) Long dataSourceId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<Segment> segmentsPage = segmentDog.searchSegmentPage(
			dataSourceId, filterString, page, Math.max(1, size), sorts);

		if (segmentsPage.getTotalElements() > 0) {
			Stream<Segment> stream = segmentsPage.stream();

			Map<Long, Segment> segmentsMap = stream.filter(
				segment -> segment.getType() == Segment.Type.DYNAMIC
			).collect(
				Collectors.toMap(Segment::getId, this::_resetIndividualsCount)
			);

			List<BQMembershipChange> bqMembershipsChanges =
				_bqMembershipChangeDog.getLastBeforeTodayByIndividualSegmentsId(
					new ArrayList<>(segmentsMap.keySet()));

			for (BQMembershipChange bqMembershipChange : bqMembershipsChanges) {
				_updateSegmentCounts(
					bqMembershipChange,
					segmentsMap.get(
						bqMembershipChange.getIndividualSegmentId()));
			}
		}

		return toSegmentDTOPageDTO(segmentsPage);
	}

	@GetMapping(params = "apply", value = "/{id}/individuals")
	public PageDTO<TransformationDTO> getTransformationDTOPageDTO(
		@PathVariable Long id, @RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(required = false) Boolean includeAnonymousUsers,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOPageDTO(
			"individual-transformations",
			_individualDog.getTransformationPage(
				apply, null, filterString, includeAnonymousUsers, id, page,
				size));
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOPageDTO(
		@RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOPageDTO(
			"individual-segment-transformations",
			_segmentDog.getTransformationPage(apply, filterString, page, size));
	}

	@PostMapping("/{id}/memberships")
	public String postMembership(
			@PathVariable Long id, @RequestBody String json)
		throws Exception {

		SegmentDTO segmentDTO = getSegmentDTO(id, null);

		if (!Objects.equals(segmentDTO.getType(), "STATIC") ||
			!Objects.equals(segmentDTO.getStatus(), "ACTIVE")) {

			throw new Exception(
				"Membership POST requests can only be made to an active " +
					"static individual segment");
		}

		Date date = new Date();

		if (json.startsWith("{")) {
			BQMembership bqMembership = objectMapper.convertValue(
				new JSONObject(json), BQMembership.class);

			bqMembership.setIndividualSegmentId(id);

			if (_isMember(
					bqMembership.getIndividualId(),
					bqMembership.getIndividualSegmentId())) {

				return null;
			}

			bqMembership.setCreateDate(date);
			bqMembership.setModifiedDate(date);

			bqMembership = membershipDog.addBQMembership(bqMembership);

			bqMembership.setId(null);

			JSONObject bqMembershipJSONObject = objectMapper.convertValue(
				bqMembership, JSONObject.class);

			return bqMembershipJSONObject.toString();
		}

		Stream<Object> stream = JSONUtil.toObjectStream(new JSONArray(json));

		List<BQMembership> bqMemberships = stream.map(
			jsonObject -> objectMapper.convertValue(
				jsonObject, BQMembership.class)
		).map(
			bqMembership -> {
				bqMembership.setCreateDate(date);
				bqMembership.setIndividualSegmentId(id);
				bqMembership.setModifiedDate(date);

				return bqMembership;
			}
		).filter(
			membership -> !_isMember(
				membership.getIndividualId(),
				membership.getIndividualSegmentId())
		).collect(
			Collectors.toList()
		);

		if (bqMemberships.isEmpty()) {
			return null;
		}

		bqMemberships = membershipDog.addBQMemberships(bqMemberships);

		bqMemberships.forEach(bqMembership -> bqMembership.setId(null));

		JSONArray bqMembershipJSONArray = JSONUtil.toJSONArray(
			bqMemberships,
			membership -> objectMapper.convertValue(
				membership, JSONObject.class));

		return bqMembershipJSONArray.toString();
	}

	@PostMapping
	public SegmentDTO postSegment(@RequestBody SegmentDTO segmentDTO) {
		segmentDTO.setActivitiesCount(0L);

		Date date = DateUtil.newDate();

		segmentDTO.setCreateDate(date);

		segmentDTO.setId(null);

		segmentDTO.setModifiedDate(date);

		return objectMapper.convertValue(
			segmentDog.addSegment(
				objectMapper.convertValue(segmentDTO, Segment.class)),
			SegmentDTO.class);
	}

	protected PageDTO<SegmentDTO> toSegmentDTOPageDTO(
		Page<Segment> segmentsPage) {

		return new PageDTO<>(
			"_embedded", new SegmentDTO(segmentsPage.getContent()),
			segmentsPage.getNumber(), segmentsPage.getSize(),
			segmentsPage.getTotalElements(), segmentsPage.getTotalPages());
	}

	@Autowired
	protected MembershipDog membershipDog;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected SegmentDog segmentDog;

	private void _addReferencedObject(
			String collectionName, Set<Long> referencedIds,
			JSONObject referencedObjectsJSONObject)
		throws Exception {

		if (CollectionUtils.isEmpty(referencedIds)) {
			referencedObjectsJSONObject.put(collectionName, new JSONArray());

			return;
		}

		JSONArray jsonArray = null;

		if (!collectionName.equals("organizations") &&
			(DXPEntity.Type.ofCollectionName(collectionName) != null)) {

			List<? extends DXPEntity> dxpEntities =
				_dxpEntityDog.findByFieldsAndType(
					Collections.singletonMap("id", referencedIds),
					DXPEntity.Type.ofCollectionName(collectionName));

			jsonArray = JSONUtil.toJSONArray(
				dxpEntities,
				dxpEntity -> _objectMapper.convertValue(
					dxpEntity, JSONObject.class));
		}
		else {
			jsonArray = faroInfoElasticsearchInvoker.get(
				collectionName,
				QueryBuilders.termsQuery(
					"id", ListUtil.map(referencedIds, String::valueOf)));
		}

		referencedObjectsJSONObject.put(collectionName, jsonArray);
	}

	private QueryBuilder _getMembershipChangesQueryBuilder(
		String filterString, String individualSegmentId) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"individualSegmentId", individualSegmentId));

		Segment segment = segmentDog.getSegment(
			Long.valueOf(individualSegmentId));

		if (!BooleanUtils.toBoolean(segment.getIncludeAnonymousUsers())) {
			boolQueryBuilder.filter(
				QueryBuilders.existsQuery("individualEmail"));
		}

		if (StringUtils.isEmpty(filterString)) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(
			FilterStringToQueryBuilderConverter.convert(filterString));
	}

	private JSONObject _getReferencedObjectsJSONObject(Segment segment)
		throws Exception {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(
			"assets",
			JSONUtil.toJSONArray(
				_assetDog.getAssets(segment.getReferencedAssetIds()),
				asset -> objectMapper.convertValue(asset, JSONObject.class)));
		jsonObject.put(
			"field-mappings",
			JSONUtil.toJSONArray(
				_fieldMappingDog.getFieldMappings(
					segment.getReferencedFieldMappingIds()),
				fieldMapping -> objectMapper.convertValue(
					fieldMapping, JSONObject.class)));

		_addReferencedObject(
			"groups", segment.getReferencedGroupIds(), jsonObject);
		_addReferencedObject(
			"organizations", segment.getReferencedOrganizationIds(),
			jsonObject);
		_addReferencedObject(
			"roles", segment.getReferencedRoleIds(), jsonObject);
		_addReferencedObject(
			"teams", segment.getReferencedTeamIds(), jsonObject);
		_addReferencedObject(
			"user-groups", segment.getReferencedUserGroupIds(), jsonObject);
		_addReferencedObject(
			"users", segment.getReferencedUserIds(), jsonObject);

		return jsonObject;
	}

	private boolean _isMember(Long individualId, Long individualSegmentId) {
		if (membershipDog.isMember(individualId, individualSegmentId)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not adding membership because individual " + individualId +
						" is already a member of individual segment " +
							individualSegmentId);
			}

			return true;
		}

		return false;
	}

	private Segment _resetIndividualsCount(Segment segment) {
		segment.setAnonymousIndividualsCount(0L);
		segment.setIndividualsCount(0L);
		segment.setKnownIndividualsCount(0L);

		return segment;
	}

	private PageDTO<IndividualDTO> _toIndividualDTOPageDTO(
		Page<Individual> individualsPage) {

		return new PageDTO<>(
			"_embedded", new IndividualDTO(individualsPage.getContent()),
			individualsPage.getNumber(), individualsPage.getSize(),
			individualsPage.getTotalElements(),
			individualsPage.getTotalPages());
	}

	private PageDTO<BQMembershipChangeDTO> _toMembershipChangeDTOPageDTO(
		BQMembershipChangeDTO bqMembershipChangeDTO,
		Page<BQMembershipChange> bqMembershipChangesPage) {

		return new PageDTO<>(
			"_embedded", bqMembershipChangeDTO,
			bqMembershipChangesPage.getNumber(),
			bqMembershipChangesPage.getSize(),
			bqMembershipChangesPage.getTotalElements(),
			bqMembershipChangesPage.getTotalPages());
	}

	private PageDTO<BQMembershipChangeDTO> _toMembershipChangeDTOPageDTO(
		Page<BQMembershipChange> bqMembershipChangesPage) {

		return new PageDTO<>(
			"_embedded",
			new BQMembershipChangeDTO(bqMembershipChangesPage.getContent()),
			bqMembershipChangesPage.getNumber(),
			bqMembershipChangesPage.getSize(),
			bqMembershipChangesPage.getTotalElements(),
			bqMembershipChangesPage.getTotalPages());
	}

	private PageDTO<BQMembershipDTO> _toMembershipDTOPageDTO(
		Page<BQMembership> bqMembershipsPage) {

		return new PageDTO<>(
			"_embedded", new BQMembershipDTO(bqMembershipsPage.getContent()),
			bqMembershipsPage.getNumber(), bqMembershipsPage.getSize(),
			bqMembershipsPage.getTotalElements(),
			bqMembershipsPage.getTotalPages());
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		String transformationKey, Page<Transformation> transformations) {

		return _toTransformationDTOPageDTO(
			new TransformationDTO(
				transformationKey, transformations.getContent()),
			transformations);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		TransformationDTO transformationDTO,
		Page<Transformation> transformations) {

		return new PageDTO<>(
			"_embedded", transformationDTO, transformations.getNumber(),
			transformations.getSize(), transformations.getTotalElements(),
			transformations.getTotalPages());
	}

	private void _updateSegmentCounts(
		BQMembershipChange bqMembershipChange, Segment segment) {

		if (bqMembershipChange == null) {
			return;
		}

		segment.setAnonymousIndividualsCount(
			bqMembershipChange.getIndividualsCount() -
				bqMembershipChange.getKnownIndividualsCount());
		segment.setIndividualsCount(bqMembershipChange.getIndividualsCount());
		segment.setKnownIndividualsCount(
			bqMembershipChange.getKnownIndividualsCount());
	}

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentsRestController.class);

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private MembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}