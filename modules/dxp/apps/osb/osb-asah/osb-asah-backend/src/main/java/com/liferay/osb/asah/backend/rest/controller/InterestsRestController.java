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

import com.liferay.osb.asah.backend.dto.InterestDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.annotation.Cacheable;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.jetbrains.annotations.NotNull;

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
@RequestMapping("/interests")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.InterestsRestController"
)
@SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
public class InterestsRestController
	extends com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.
				InterestsRestController {

	@GetMapping("/{id}")
	public InterestDTO getInterestDTO(
		@PathVariable Long id, @RequestParam(required = false) String expand) {

		return _createInterestDTO(
			_containsPageVisited(expand), _getDaysRange(expand),
			interestDog.getInterest(id));
	}

	@Cacheable
	@GetMapping(params = "!apply")
	public PageDTO<InterestDTO> getInterestDTOsPageDTO(
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(required = false) String expand,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toPageDTO(
			expand,
			interestDog.getInterestPage(
				filterString, _getScore(), page, size, sorts));
	}

	@GetMapping("/keywords")
	public String getInterestKeywords(
			@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return JSONUtil.put(
			"_embedded",
			JSONUtil.put(
				"interest-keywords",
				_assetDog.getKeywords(name, page, size, Sort.asc("_key")))
		).put(
			"page",
			getPageJSONObject(page, size, _assetDog.getKeywordsCount(name))
		).toString();
	}

	@Cacheable
	@GetMapping(params = "apply")
	public String getInterestTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return JSONUtil.put(
			"_embedded",
			JSONUtil.put(
				"interest-transformations",
				interestDog.getTransformations(apply, filterString, page, size))
		).put(
			"page", getPageJSONObject(page, size, size)
		).toString();
	}

	private boolean _containsPageVisited(String expand) {
		if (StringUtils.isEmpty(expand)) {
			return false;
		}

		Set<String> expandParts = new HashSet<>(
			Arrays.asList(expand.split(",")));

		return expandParts.contains("pages-visited");
	}

	@NotNull
	private InterestDTO _createInterestDTO(
		boolean addPageVisited, int days, Interest interest) {

		InterestDTO interestDTO = new InterestDTO(interest);

		Map<String, Object> embedded = new HashMap<>();

		if (days > 0) {
			LocalDateTime endDayLocalDateTime = DateUtil.newDayLocalDateTime(
				TimeZoneDogUtil.getZoneId());

			embedded.put(
				"interest-aggregation-last-" + days + "-days",
				_getInterestAggregations(
					endDayLocalDateTime, interest.getName(),
					interest.getOwnerId(), interest.getOwnerType(),
					endDayLocalDateTime.plusDays(1 - days)));
		}

		if (addPageVisited) {
			embedded.put("pages-visited", _getVisitedPages(interest));
		}

		interestDTO.setEmbedded(embedded);

		return interestDTO;
	}

	private Set<InterestDTO> _createInterestDTOs(
		String expand, List<Interest> interests) {

		int days = _getDaysRange(expand);

		boolean addPageVisited = _containsPageVisited(expand);

		Set<InterestDTO> interestDTOs = new HashSet<>();

		for (Interest interest : interests) {
			interestDTOs.add(
				_createInterestDTO(addPageVisited, days, interest));
		}

		return interestDTOs;
	}

	private int _getDaysRange(String expand) {
		if (StringUtils.isEmpty(expand)) {
			return 0;
		}

		Set<String> expandParts = new HashSet<>(
			Arrays.asList(expand.split(",")));

		if (expandParts.contains("interest-aggregation-last-30-days")) {
			return 30;
		}
		else if (expandParts.contains("interest-aggregation-last-60-days")) {
			return 60;
		}
		else if (expand.contains("interest-aggregation-last-90-days")) {
			return 90;
		}

		return 0;
	}

	private List<Map<String, Object>> _getInterestAggregations(
		LocalDateTime endDayLocalDateTime, String name, Long ownerId,
		String ownerType, LocalDateTime startDayLocalDateTime) {

		List<Map<String, Object>> interestAggregations = new ArrayList<>();

		List<Interest> interests = interestDog.getInterests(
			name, ownerId, ownerType, DateUtil.toUTCDate(startDayLocalDateTime),
			DateUtil.toUTCDate(endDayLocalDateTime));

		Map<LocalDateTime, Interest> interestMap = new HashMap<>();

		for (Interest interest : interests) {
			interestMap.put(
				DateUtil.toLocalDateTime(
					interest.getRecordedDate(), TimeZoneDogUtil.getZoneId()),
				interest);
		}

		LocalDateTime currentDayLocalDateTime = startDayLocalDateTime;

		while (currentDayLocalDateTime.compareTo(endDayLocalDateTime) <= 0) {
			Interest interest = interestMap.get(currentDayLocalDateTime);

			Map<String, Object> item = new HashMap<>();

			item.put("intervalInitDate", currentDayLocalDateTime.toString());

			if (interest != null) {
				item.put("scoreAvg", interest.getScore());
				item.put("totalElements", 1);
				item.put("viewsSum", interest.getViews());
			}
			else {
				item.put("scoreAvg", 0.0);
				item.put("totalElements", 0);
				item.put("viewsSum", 0);
			}

			interestAggregations.add(item);

			currentDayLocalDateTime = currentDayLocalDateTime.plusDays(1);
		}

		return interestAggregations;
	}

	private Double _getScore() {
		AsahMarker asahMarker = asahMarkerDog.fetchAsahMarker(
			"InterestThresholdScoreNanite",
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (asahMarker == null) {
			return null;
		}

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		if (asahMarkerContextJSONObject.has("score")) {
			return asahMarkerContextJSONObject.getDouble("score");
		}

		return null;
	}

	private List<Object> _getVisitedPages(Interest interest) {
		return JSONUtil.toObjectList(
			faroInfoElasticsearchInvoker.get(
				"visited-pages",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"day", DateUtil.toUTCString(interest.getRecordedDate()))
				).filter(
					QueryBuilders.termQuery("interestName", interest.getName())
				).filter(
					QueryBuilders.termQuery("ownerId", interest.getOwnerId())
				).filter(
					QueryBuilders.termQuery(
						"ownerType", interest.getOwnerType())
				)));
	}

	private PageDTO<InterestDTO> _toPageDTO(
		InterestDTO interestDTO, Page<Interest> interestPage) {

		return new PageDTO<>(
			"_embedded", interestDTO, interestPage.getNumber(),
			interestPage.getSize(), interestPage.getTotalElements(),
			interestPage.getTotalPages());
	}

	private PageDTO<InterestDTO> _toPageDTO(
		String expand, Page<Interest> interestPage) {

		return _toPageDTO(
			new InterestDTO(
				_createInterestDTOs(expand, interestPage.getContent())),
			interestPage);
	}

	@Autowired
	private AssetDog _assetDog;

}