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

import com.liferay.osb.asah.backend.dto.IndividualDTO;
import com.liferay.osb.asah.backend.dto.MembershipChangeDTO;
import com.liferay.osb.asah.backend.dto.MembershipDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Membership;
import com.liferay.osb.asah.common.entity.MembershipChange;
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
import java.util.HashMap;
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
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	public PageDTO<IndividualDTO> getIndividualDTOsPageDTOs(
		@PathVariable Long id,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(required = false) Boolean includeAnonymousUsers,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toIndividualDTOPageDTO(
			_individualDog.searchIndividualsPage(
				filterString, includeAnonymousUsers, id, page, size, sorts));
	}

	@GetMapping(params = "!apply", value = "/{id}/membership-changes")
	public PageDTO<MembershipChangeDTO> getMembershipChangeDTOsPageDTOs(
		@PathVariable Long id, @RequestParam(required = false) String expand,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<MembershipChange> membershipChangesPages =
			_membershipChangeDog.searchMembershipChangesPages(
				filterString, id, page, size, sorts);

		if (StringUtils.isEmpty(expand)) {
			return _toMembershipChangeDTOsPageDTO(membershipChangesPages);
		}

		List<MembershipChange> membershipChanges =
			membershipChangesPages.getContent();

		Set<MembershipChangeDTO> membershipChangeDTOs = new LinkedHashSet<>();

		Stream<MembershipChange> stream = membershipChanges.stream();

		stream.forEachOrdered(
			membershipChange -> membershipChangeDTOs.add(
				new MembershipChangeDTO(membershipChange)));

		Map<Long, JSONObject> accountNamesJSONObjects = new HashMap<>();

		String[] expandParts = expand.split(",");

		for (String expandPart : expandParts) {
			if (expandPart.equals("account-names")) {
				accountNamesJSONObjects =
					_membershipChangeDog.getAccountNamesJSONObjects(
						membershipChanges);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + expandPart);
			}
		}

		for (MembershipChangeDTO membershipChangeDTO : membershipChangeDTOs) {
			Map<String, Object> expandMap = new HashMap<>();

			JSONObject accountNamesJSONObject = accountNamesJSONObjects.get(
				Long.valueOf(membershipChangeDTO.getId()));

			if (accountNamesJSONObject != null) {
				expandMap.put(
					"account-names",
					accountNamesJSONObject.getJSONArray("account-names"));
			}

			if (!expandMap.isEmpty()) {
				membershipChangeDTO.setEmbedded(expandMap);
			}
		}

		return _toMembershipChangeDTOsPageDTO(
			new MembershipChangeDTO(membershipChangeDTOs),
			membershipChangesPages);
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
	public PageDTO<MembershipDTO> getMembershipDTOsPageDTO(
		@PathVariable Long id,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		if (!segmentDog.isIncludeAnonymousUsers(id)) {
			List<Long> individualIds = new ArrayList<>();

			BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("demographics.email")
			).filter(
				QueryBuilders.termsQuery(
					"individualSegmentIds", String.valueOf(id))
			);

			if (StringUtils.isNotEmpty(filterString)) {
				boolQueryBuilder.filter(
					FilterStringToQueryBuilderConverter.convert(filterString));
			}

			SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
				"individuals",
				searchSourceBuilder -> {
					searchSourceBuilder.aggregation(
						AggregationBuilders.terms(
							"ids"
						).field(
							"id"
						).size(
							Integer.MAX_VALUE
						));
					searchSourceBuilder.query(boolQueryBuilder);
					searchSourceBuilder.size(0);
				});

			Aggregations aggregations = searchResponse.getAggregations();

			Terms terms = aggregations.get("ids");

			for (Terms.Bucket bucket : terms.getBuckets()) {
				individualIds.add(Long.valueOf(bucket.getKeyAsString()));
			}

			return _toMembershipDTOsPageDTO(
				membershipDog.getMembershipsPage(
					individualIds, id, "ACTIVE", page, size, sorts));
		}

		return _toMembershipDTOsPageDTO(
			membershipDog.getMembershipsPage(id, "ACTIVE", page, size, sorts));
	}

	@GetMapping("/{id}")
	public SegmentDTO getSegmentDTO(
			@PathVariable Long id,
			@RequestParam(required = false) String expand)
		throws Exception {

		Segment segment = _resetIndividualsCount(segmentDog.getSegment(id));

		List<MembershipChange> membershipsChanges =
			_membershipChangeDog.getLastFrom30DaysByIndividualSegmentsId(
				Collections.singletonList(segment.getId()));

		if (!membershipsChanges.isEmpty()) {
			_updateSegmentCounts(membershipsChanges.get(0), segment);
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
	public PageDTO<SegmentDTO> getSegmentDTOsPageDTOs(
		@RequestParam(required = false) Long dataSourceId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<Segment> segmentsPage = segmentDog.searchSegmentsPage(
			dataSourceId, filterString, page, Math.max(1, size), sorts);

		if (segmentsPage.getTotalElements() > 0) {
			Stream<Segment> stream = segmentsPage.stream();

			Map<Long, Segment> segmentsMap = stream.collect(
				Collectors.toMap(Segment::getId, this::_resetIndividualsCount));

			List<MembershipChange> membershipsChanges =
				_membershipChangeDog.getLastFrom30DaysByIndividualSegmentsId(
					new ArrayList<>(segmentsMap.keySet()));

			for (MembershipChange membershipChange : membershipsChanges) {
				_updateSegmentCounts(
					membershipChange,
					segmentsMap.get(membershipChange.getIndividualSegmentId()));
			}
		}

		return toSegmentDTOPageDTO(segmentsPage);
	}

	@GetMapping(params = "apply", value = "/{id}/individuals")
	public PageDTO<TransformationDTO> getTransformationDTOsPageDTO(
		@PathVariable Long id, @RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(required = false) Boolean includeAnonymousUsers,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOsPageDTO(
			"individual-transformations",
			_individualDog.getTransformationsPage(
				apply, null, filterString, includeAnonymousUsers, id, page,
				size));
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOsPageDTO(
		@RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOsPageDTO(
			"individual-segment-transformations",
			_segmentDog.getTransformationsPage(
				apply, filterString, page, size));
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
			Membership membership = objectMapper.convertValue(
				new JSONObject(json), Membership.class);

			membership.setIndividualSegmentId(id);

			if (_isMember(
					membership.getIndividualId(),
					membership.getIndividualSegmentId())) {

				return null;
			}

			membership.setCreateDate(date);
			membership.setModifiedDate(date);

			membership = membershipDog.addMembership(membership);

			membership.setId(null);

			JSONObject membershipJSONObject = objectMapper.convertValue(
				membership, JSONObject.class);

			return membershipJSONObject.toString();
		}

		Stream<Object> stream = JSONUtil.toObjectStream(new JSONArray(json));

		List<Membership> memberships = stream.map(
			jsonObject -> objectMapper.convertValue(
				jsonObject, Membership.class)
		).map(
			membership -> {
				membership.setCreateDate(date);
				membership.setIndividualSegmentId(id);
				membership.setModifiedDate(date);

				return membership;
			}
		).filter(
			membership -> !_isMember(
				membership.getIndividualId(),
				membership.getIndividualSegmentId())
		).collect(
			Collectors.toList()
		);

		if (memberships.isEmpty()) {
			return null;
		}

		memberships = membershipDog.addMemberships(memberships);

		memberships.forEach(membership -> membership.setId(null));

		JSONArray membershipJSONArray = JSONUtil.toJSONArray(
			memberships,
			membership -> objectMapper.convertValue(
				membership, JSONObject.class));

		return membershipJSONArray.toString();
	}

	@PostMapping
	public SegmentDTO postSegment(@RequestBody SegmentDTO segmentDTO) {
		segmentDTO.setActivitiesCount(0L);

		Date now = new Date();

		segmentDTO.setCreateDate(now);

		segmentDTO.setId(null);

		segmentDTO.setModifiedDate(now);

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
		JSONObject referencedObjectsJSONObject) {

		if ((referencedIds == null) || referencedIds.isEmpty()) {
			referencedObjectsJSONObject.put(collectionName, new JSONArray());

			return;
		}

		JSONArray jsonArray = null;

		if (DXPEntity.Type.ofCollectionName(collectionName) != null) {
			Stream<Long> stream = referencedIds.stream();

			List<? extends DXPEntity> dxpEntities =
				_dxpEntityDog.findByAfterAndFieldsAndType(
					null,
					Collections.singletonMap(
						"id",
						stream.map(
							String::valueOf
						).collect(
							Collectors.toList()
						)),
					0, DXPEntity.Type.ofCollectionName(collectionName));

			jsonArray = new JSONArray(
				ListUtil.map(
					dxpEntities,
					dxpEntity -> _objectMapper.convertValue(
						dxpEntity, JSONObject.class)));
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
		segment.setAnonymousIndividualCount(0L);
		segment.setIndividualCount(0L);
		segment.setKnownIndividualCount(0L);

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

	private PageDTO<MembershipChangeDTO> _toMembershipChangeDTOsPageDTO(
		MembershipChangeDTO membershipChangeDTO,
		Page<MembershipChange> membershipChangesPage) {

		return new PageDTO<>(
			"_embedded", membershipChangeDTO, membershipChangesPage.getNumber(),
			membershipChangesPage.getSize(),
			membershipChangesPage.getTotalElements(),
			membershipChangesPage.getTotalPages());
	}

	private PageDTO<MembershipChangeDTO> _toMembershipChangeDTOsPageDTO(
		Page<MembershipChange> membershipChangesPage) {

		return new PageDTO<>(
			"_embedded",
			new MembershipChangeDTO(membershipChangesPage.getContent()),
			membershipChangesPage.getNumber(), membershipChangesPage.getSize(),
			membershipChangesPage.getTotalElements(),
			membershipChangesPage.getTotalPages());
	}

	private PageDTO<MembershipDTO> _toMembershipDTOsPageDTO(
		Page<Membership> membershipsPage) {

		return new PageDTO<>(
			"_embedded", new MembershipDTO(membershipsPage.getContent()),
			membershipsPage.getNumber(), membershipsPage.getSize(),
			membershipsPage.getTotalElements(),
			membershipsPage.getTotalPages());
	}

	private PageDTO<TransformationDTO> _toTransformationDTOsPageDTO(
		String transformationKey, Page<Transformation> transformations) {

		return _toTransformationDTOsPageDTO(
			new TransformationDTO(
				transformationKey, transformations.getContent()),
			transformations);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOsPageDTO(
		TransformationDTO transformationDTO,
		Page<Transformation> transformations) {

		return new PageDTO<>(
			"_embedded", transformationDTO, transformations.getNumber(),
			transformations.getSize(), transformations.getTotalElements(),
			transformations.getTotalPages());
	}

	private void _updateSegmentCounts(
		MembershipChange membershipChange, Segment segment) {

		if (membershipChange == null) {
			return;
		}

		segment.setAnonymousIndividualCount(
			membershipChange.getIndividualsCount() -
				membershipChange.getKnownIndividualsCount());
		segment.setIndividualCount(membershipChange.getIndividualsCount());
		segment.setKnownIndividualCount(
			membershipChange.getKnownIndividualsCount());
	}

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentsRestController.class);

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}