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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.SegmentDTO;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Segment;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 * @author Rachael Koestartyo
 */
@RequestMapping("/individual-segments")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.IndividualSegmentsRestController"
)
@SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
public class IndividualSegmentsRestController
	extends com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.
				IndividualSegmentsRestController {

	@PutMapping("/{id}/channel/{channelId}")
	public void assignChannel(
			@PathVariable Long channelId, @PathVariable Long id)
		throws Exception {

		segmentDog.assignChannel(channelId, id);
	}

	@DeleteMapping("/{id}/memberships/{individualId}")
	public void deleteIndividual(
			@PathVariable Long id, @PathVariable Long individualId)
		throws Exception {

		membershipDog.deactivateMembership(new Date(), individualId, id);
	}

	@DeleteMapping("/{id}")
	public void deleteIndividualSegment(@PathVariable Long id) {
		segmentDog.deleteSegment(id);
	}

	@GetMapping("/{id}/accounts")
	public String getAccounts(
			@PathVariable String id,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"accounts", null, page, _getAccountsQueryBuilder(id, filterString),
			size, sorts);
	}

	@GetMapping("/preview-disabled-segments")
	public PageDTO<SegmentDTO> getPreviewDisabledSegmentDTOsPageDTOs(
			@RequestParam Long dataSourceId,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toSegmentDTOPageDTO(
			segmentDog.searchPreviewDisabledSegmentsPage(
				dataSourceId, filterString, page, size, sorts));
	}

	@PutMapping("/{id}")
	public SegmentDTO putIndividualSegment(
			@PathVariable Long id, @RequestBody SegmentDTO segmentDTO)
		throws Exception {

		segmentDTO.setActiveIndividualCount(null);
		segmentDTO.setActivitiesCount(null);
		segmentDTO.setAnonymousIndividualCount(null);
		segmentDTO.setCreateDate(null);
		segmentDTO.setIndividualCount(null);
		segmentDTO.setKnownIndividualCount(null);
		segmentDTO.setModifiedDate(new Date());

		return objectMapper.convertValue(
			segmentDog.updateSegment(
				objectMapper.convertValue(segmentDTO, Segment.class), id),
			SegmentDTO.class);
	}

	private QueryBuilder _getAccountsQueryBuilder(
		String individualSegmentId, String filterString) {

		Set<String> accountPKs = new HashSet<>();

		List<Object> individualDataSourceAccountPKs = JSONUtil.toObjectList(
			faroInfoElasticsearchInvoker.get(
				"individuals",
				QueryBuilders.termQuery(
					"individualSegmentIds", individualSegmentId)),
			"dataSourceAccountPKs");

		for (Object jsonArrayObject : individualDataSourceAccountPKs) {
			JSONArray jsonArray = (JSONArray)jsonArrayObject;

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);

				accountPKs.addAll(
					JSONUtil.toStringList(
						jsonObject.getJSONArray("accountPKs")));
			}
		}

		QueryBuilder queryBuilder = QueryBuilders.termsQuery(
			"accountPK", accountPKs);

		if (StringUtils.isEmpty(filterString)) {
			return queryBuilder;
		}

		return BoolQueryBuilderUtil.filter(
			queryBuilder
		).filter(
			FilterStringToQueryBuilderConverter.convert(filterString)
		);
	}

}