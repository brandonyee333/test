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

import com.liferay.osb.asah.backend.rest.response.VisitedPagesTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eddie Olson
 */
@RequestMapping("/pages-visited") // TODO Rename
@RestController
public class VisitedPagesRestController extends BaseRestController {

	@GetMapping("/{id}")
	public String getVisitedPages(@PathVariable String id) throws Exception {
		return toItemGetResponse("visited-pages", id);
	}

	@GetMapping
	public String getVisitedPages(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam Long ownerId, @RequestParam String ownerType,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts,
			@RequestParam(defaultValue = "true") boolean visitedPages)
		throws Exception {

		return toTransformationGetResponse(
			"visited-pages", page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			Collections.singletonMap("title", "name"), sorts,
			new VisitedPagesTransformationJSONArrayFunction(
				_membershipDog, ownerId, ownerType, _segmentDog, visitedPages),
			"visited-pages-transformation");
	}

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private SegmentDog _segmentDog;

}