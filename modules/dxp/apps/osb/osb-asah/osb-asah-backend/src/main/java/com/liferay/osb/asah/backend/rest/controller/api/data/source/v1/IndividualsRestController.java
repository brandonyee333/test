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

import com.liferay.osb.asah.backend.dto.IndividualDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Transformation;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping(produces = "application/json", value = "/api/1.0/individuals")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.IndividualsRestController"
)
public class IndividualsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public IndividualDTO getIndividualDTO(
			@PathVariable Long id,
			@RequestParam(required = false) Long channelId,
			@RequestParam(required = false) String expand)
		throws Exception {

		Individual individual = _individualDog.getIndividual(channelId, id);

		IndividualDTO individualDTO = new IndividualDTO(individual);

		if (StringUtils.isEmpty(expand)) {
			return individualDTO;
		}

		Map<String, Object> expandMap = new HashMap<>();

		String[] expandParts = expand.split(",");

		for (String expandPart : expandParts) {
			if (expandPart.equals("account-names")) {
				Map<Long, JSONObject> accountNamesJSONObjects =
					_accountDog.getAccountNamesJSONObjects(
						Collections.singletonList(individual));

				JSONObject jsonObject = accountNamesJSONObjects.get(
					individual.getId());

				expandMap.put(expandPart, jsonObject.get(expandPart));
			}
			else if (expandPart.equals("accounts")) {
				Map<Long, JSONObject> accountsJSONObjects =
					_accountDog.getAccountsJSONObjects(
						Collections.singletonList(individual));

				JSONObject jsonObject = accountsJSONObjects.get(
					individual.getId());

				expandMap.put(expandPart, jsonObject.get(expandPart));
			}
			else if (expandPart.equals("data-sources")) {
				Map<Long, JSONObject> dataSourcesJSONObjects =
					_dataSourceDog.getDataSourcesJSONObjects(
						Collections.singletonList(individual));

				JSONObject jsonObject = dataSourcesJSONObjects.get(
					individual.getId());

				expandMap.put(expandPart, jsonObject.get(expandPart));
			}
			else if (expandPart.equals("individual-segments")) {
				Map<Long, JSONObject> segmentsJSONObjects =
					_segmentDog.getSegmentsJSONObjects(
						Collections.singletonList(individual));

				JSONObject jsonObject = segmentsJSONObjects.get(
					individual.getId());

				expandMap.put(expandPart, jsonObject.get(expandPart));
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + expandPart);
			}
		}

		if (!expandMap.isEmpty()) {
			individualDTO.setEmbedded(expandMap);
		}

		return individualDTO;
	}

	@GetMapping(params = "!apply")
	public PageDTO<IndividualDTO> getIndividualDTOPageDTO(
			@RequestParam(required = false) Long channelId,
			@RequestParam(required = false) String expand,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "false", required = false) boolean
				includeAnonymousUsers,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		Page<Individual> individualPage = _individualDog.searchIndividualPage(
			channelId, filterString, includeAnonymousUsers, page,
			Math.max(1, size), sorts);

		if (StringUtils.isEmpty(expand)) {
			return _toIndividualDTOPageDTO(individualPage);
		}

		List<Individual> individuals = individualPage.getContent();

		Set<IndividualDTO> individualDTOs = new LinkedHashSet<>();

		Stream<Individual> stream = individuals.stream();

		stream.forEachOrdered(
			individual -> individualDTOs.add(new IndividualDTO(individual)));

		Map<Long, JSONObject> accountNamesJSONObjects = new HashMap<>();
		Map<Long, JSONObject> accountsJSONObjects = new HashMap<>();
		Map<Long, JSONObject> dataSourcesJSONObjects = new HashMap<>();
		Map<Long, JSONObject> segmentsJSONObjects = new HashMap<>();

		String[] expandParts = expand.split(",");

		for (String expandPart : expandParts) {
			if (expandPart.equals("account-names")) {
				accountNamesJSONObjects =
					_accountDog.getAccountNamesJSONObjects(individuals);
			}
			else if (expandPart.equals("accounts")) {
				accountsJSONObjects = _accountDog.getAccountsJSONObjects(
					individuals);
			}
			else if (expandPart.equals("data-sources")) {
				dataSourcesJSONObjects =
					_dataSourceDog.getDataSourcesJSONObjects(individuals);
			}
			else if (expandPart.equals("individual-segments")) {
				segmentsJSONObjects = _segmentDog.getSegmentsJSONObjects(
					individuals);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + expandPart);
			}
		}

		for (IndividualDTO individualDTO : individualDTOs) {
			Map<String, Object> expandMap = new HashMap<>();

			JSONObject accountNameJSONObject = accountNamesJSONObjects.get(
				Long.valueOf(individualDTO.getId()));

			if (accountNameJSONObject != null) {
				expandMap.put(
					"account-names",
					accountNameJSONObject.get("account-names"));
			}

			JSONObject accountJSONObject = accountsJSONObjects.get(
				Long.valueOf(individualDTO.getId()));

			if (accountJSONObject != null) {
				expandMap.put("accounts", accountJSONObject.get("accounts"));
			}

			JSONObject dataSourceJSONObject = dataSourcesJSONObjects.get(
				Long.valueOf(individualDTO.getId()));

			if (dataSourceJSONObject != null) {
				expandMap.put(
					"data-sources", dataSourceJSONObject.get("data-sources"));
			}

			JSONObject segmentJSONObject = segmentsJSONObjects.get(
				Long.valueOf(individualDTO.getId()));

			if (segmentJSONObject != null) {
				expandMap.put(
					"individual-segments",
					segmentJSONObject.get("individual-segments"));
			}

			if (!expandMap.isEmpty()) {
				individualDTO.setEmbedded(expandMap);
			}
		}

		return _toPageDTO(new IndividualDTO(individualDTOs), individualPage);
	}

	@GetMapping("/count")
	public long getIndividualsCount(
		@RequestParam(defaultValue = "false", required = false) boolean
			includeAnonymousUsers) {

		return _individualDog.countIndividuals(
			null, null, includeAnonymousUsers);
	}

	@GetMapping("/{id}/individual-segments")
	public PageDTO<SegmentDTO> getSegmentDTOPageDTO(
		@PathVariable Long id, @RequestParam(required = false) String expand,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<Segment> segmentsPage = _segmentDog.searchSegmentPage(
			filterString, id, page, Math.max(1, size), sorts);

		if (StringUtils.isEmpty(expand)) {
			return _toSegmentDTOPageDTO(segmentsPage);
		}

		List<Segment> segments = segmentsPage.getContent();

		Map<Long, BQMembershipChange> bqMembershipChangeMap =
			_membershipChangeDog.getBQMembershipChanges(segments);

		Set<SegmentDTO> segmentDTOs = new LinkedHashSet<>();

		Stream<Segment> stream = segments.stream();

		stream.forEachOrdered(
			segment -> segmentDTOs.add(
				new SegmentDTO(
					bqMembershipChangeMap.getOrDefault(segment.getId(), null),
					segment)));

		Map<Long, JSONObject> membershipsJSONObjects = new HashMap<>();

		String[] expandParts = expand.split(",");

		for (String expandPart : expandParts) {
			if (expandPart.equals("active-membership")) {
				membershipsJSONObjects =
					_membershipDog.getMembershipsJSONObjects(
						id, segmentsPage.getContent());
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + expandPart);
			}
		}

		for (SegmentDTO segmentDTO : segmentDTOs) {
			Map<String, Object> expandMap = new HashMap<>();

			JSONObject membershipJSONObject = membershipsJSONObjects.get(
				Long.valueOf(segmentDTO.getId()));

			if (membershipJSONObject != null) {
				expandMap.put(
					"active-membership",
					membershipJSONObject.getJSONObject("active-membership"));
			}

			if (!expandMap.isEmpty()) {
				segmentDTO.setEmbedded(expandMap);
			}
		}

		return _toSegmentDTOPageDTO(new SegmentDTO(segmentDTOs), segmentsPage);
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getTransformationDTOPageDTO(
		@RequestParam String apply,
		@RequestParam(required = false) Long channelId,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "false", required = false) boolean
			includeAnonymousUsers,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOPageDTO(
			_individualDog.getTransformationPage(
				apply, channelId, filterString, includeAnonymousUsers, null,
				page, size));
	}

	private PageDTO<IndividualDTO> _toIndividualDTOPageDTO(
		Page<Individual> individualsPage) {

		return new PageDTO<>(
			"_embedded", new IndividualDTO(individualsPage.getContent()),
			individualsPage.getNumber(), individualsPage.getSize(),
			individualsPage.getTotalElements(),
			individualsPage.getTotalPages());
	}

	private PageDTO<IndividualDTO> _toPageDTO(
		IndividualDTO individualDTO, Page<Individual> individualsPage) {

		return new PageDTO<>(
			"_embedded", individualDTO, individualsPage.getNumber(),
			individualsPage.getSize(), individualsPage.getTotalElements(),
			individualsPage.getTotalPages());
	}

	private PageDTO<SegmentDTO> _toSegmentDTOPageDTO(
		Page<Segment> segmentsPage) {

		List<Segment> segments = segmentsPage.getContent();

		return new PageDTO<>(
			"_embedded",
			new SegmentDTO(
				_membershipChangeDog.getBQMembershipChanges(segments),
				segments),
			segmentsPage.getNumber(), segmentsPage.getSize(),
			segmentsPage.getTotalElements(), segmentsPage.getTotalPages());
	}

	private PageDTO<SegmentDTO> _toSegmentDTOPageDTO(
		SegmentDTO segmentDTO, Page<Segment> segmentsPage) {

		return new PageDTO<>(
			"_embedded", segmentDTO, segmentsPage.getNumber(),
			segmentsPage.getSize(), segmentsPage.getTotalElements(),
			segmentsPage.getTotalPages());
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		Page<Transformation> transformationsPage) {

		return _toTransformationDTOPageDTO(
			"individual-transformations", transformationsPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		String transformationKey, Page<Transformation> transformationsPage) {

		return _toTransformationDTOPageDTO(
			new TransformationDTO(
				transformationKey, transformationsPage.getContent()),
			transformationsPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOPageDTO(
		TransformationDTO transformationDTO,
		Page<Transformation> transformationsPage) {

		return new PageDTO<>(
			"_embedded", transformationDTO, transformationsPage.getNumber(),
			transformationsPage.getSize(),
			transformationsPage.getTotalElements(),
			transformationsPage.getTotalPages());
	}

	private static final Log _log = LogFactory.getLog(
		IndividualsRestController.class);

	@Autowired
	private AccountDog _accountDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private SegmentDog _segmentDog;

}