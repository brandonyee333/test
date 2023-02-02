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
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.BQIdentityDog;
import com.liferay.osb.asah.common.dog.BQMembershipChangeDog;
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.spring.annotation.Cacheable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
public class IndividualSegmentsRestController {

	@GetMapping(params = "!apply", value = "/{id}/individuals")
	public PageDTO<IndividualDTO> getIndividualDTOPageDTO(
		@PathVariable Long id,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(required = false) Boolean includeAnonymousUsers,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		// TODO Implement operation

		return _toIndividualDTOPageDTO(Page.empty());
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

		// TODO Implement Membership Changes histogram

		return null;
	}

	@GetMapping("/{id}/memberships")
	public PageDTO<BQMembershipDTO> getMembershipDTOPageDTO(
		@PathVariable Long id,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		if (!segmentDog.isIncludeAnonymousUsers(id)) {

			// TODO Change getBQMembershipPage to use filterString instead of
			//  individualIds

			return _toMembershipDTOPageDTO(
				bqMembershipDog.getBQMembershipPage(
					Collections.emptyList(), id, "ACTIVE", page, size, sorts));
		}

		return _toMembershipDTOPageDTO(
			bqMembershipDog.getBQMembershipPage(
				id, "ACTIVE", page, size, sorts));
	}

	@GetMapping("/{id}")
	public SegmentDTO getSegmentDTO(
			@PathVariable Long id,
			@RequestParam(required = false) String expand)
		throws Exception {

		Segment segment = segmentDog.getSegment(id);

		SegmentDTO segmentDTO = new SegmentDTO(null, null, segment);

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

		return toSegmentDTOPageDTO(segmentsPage);
	}

	@GetMapping(params = "apply", value = "/{id}/individuals")
	public PageDTO<TransformationDTO> getTransformationDTOPageDTO(
		@PathVariable Long id, @RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(required = false) Boolean includeAnonymousUsers,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		// TODO Implement operation

		return _toTransformationDTOPageDTO(
			"individual-transformations", Page.empty());
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOPageDTO(
		@RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOPageDTO(
			"individual-segment-transformations",
			segmentDog.getTransformationPage(apply, filterString, page, size));
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

			bqMembership.setSegmentId(id);

			if (_isMember(
					bqMembership.getIdentityId(),
					bqMembership.getSegmentId())) {

				return null;
			}

			bqMembership.setCreateDate(date);
			bqMembership.setModifiedDate(date);

			bqMembership = bqMembershipDog.addBQMembership(bqMembership);

			bqMembership.setId(null);

			JSONObject bqMembershipJSONObject = objectMapper.convertValue(
				bqMembership, JSONObject.class);

			return bqMembershipJSONObject.toString();
		}

		List<BQMembership> bqMemberships = new ArrayList<>();

		JSONArray jsonArray = new JSONArray(json);

		for (int i = 0; i < jsonArray.length(); i++) {
			BQMembershipDTO bqMembershipDTO = objectMapper.convertValue(
				jsonArray.getJSONObject(i), BQMembershipDTO.class);

			for (String identityId :
					_bqIdentityDog.getIdentityIds(
						bqMembershipDTO.getIndividualId())) {

				BQMembership bqMembership = new BQMembership();

				bqMembership.setCreateDate(date);
				bqMembership.setModifiedDate(date);
				bqMembership.setSegmentId(id);
				bqMembership.setIndividualId(bqMembershipDTO.getIndividualId());
				bqMembership.setIdentityId(identityId);
				bqMembership.setStatus(bqMembershipDTO.getStatus());

				bqMemberships.add(bqMembership);
			}
		}

		if (bqMemberships.isEmpty()) {
			return null;
		}

		bqMemberships = bqMembershipDog.addBQMemberships(bqMemberships);

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

		List<Segment> segments = segmentsPage.getContent();

		return new PageDTO<>(
			"_embedded",
			new SegmentDTO(
				_bqMembershipChangeDog.getBQMembershipChanges(segments),
				segmentDog.getLastActivityDates(segments), segments),
			segmentsPage.getNumber(), segmentsPage.getSize(),
			segmentsPage.getTotalElements(), segmentsPage.getTotalPages());
	}

	@Autowired
	protected BQMembershipDog bqMembershipDog;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected SegmentDog segmentDog;

	private JSONObject _getReferencedObjectsJSONObject(Segment segment)
		throws Exception {

		JSONObject jsonObject = new JSONObject();

		jsonObject.put(
			"assets",
			JSONUtil.toJSONArray(
				_assetDog.getAssets(segment.getReferencedAssetIds()),
				asset -> objectMapper.convertValue(asset, JSONObject.class)));

		// TODO Add BQFieldMapping references

		jsonObject.put("field-mappings", new JSONArray());
		jsonObject.put("groups", Collections.emptyList());
		jsonObject.put("organizations", Collections.emptyList());
		jsonObject.put("roles", Collections.emptyList());
		jsonObject.put("teams", Collections.emptyList());
		jsonObject.put("user-groups", Collections.emptyList());
		jsonObject.put("users", Collections.emptyList());

		return jsonObject;
	}

	private boolean _isMember(String identityId, Long segmentId) {
		if (bqMembershipDog.isMember(identityId, segmentId)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Not adding membership because identity " + identityId +
						" is already a member of individual segment " +
							segmentId);
			}

			return true;
		}

		return false;
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

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentsRestController.class);

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private BQIdentityDog _bqIdentityDog;

	@Autowired
	private BQMembershipChangeDog _bqMembershipChangeDog;

}