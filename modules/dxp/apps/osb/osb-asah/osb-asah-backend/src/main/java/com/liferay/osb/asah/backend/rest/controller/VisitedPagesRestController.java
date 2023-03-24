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

import com.liferay.osb.asah.common.dog.VisitedPagesDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		throw new OSBAsahException(
			HttpStatus.BAD_REQUEST, "Unable to process request");
	}

	@GetMapping
	public String getVisitedPages(
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam String ownerId, @RequestParam String ownerType,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts,
			@RequestParam(defaultValue = "true") boolean visitedPages)
		throws Exception {

		long totalElements;

		if (visitedPages) {
			totalElements = _visitedPagesDog.countActivePages(
				filterString, ownerId, ownerType);
		}
		else {
			totalElements = _visitedPagesDog.countInactivePages(
				filterString, ownerId, ownerType);
		}

		return JSONUtil.put(
			"_embedded",
			JSONUtil.put(
				"visited-pages-transformation",
				_visitedPagesDog.getVisitedPagesTransformations(
					filterString, ownerId, ownerType, page, size, sorts,
					visitedPages))
		).put(
			"page", getPageJSONObject(page, size, totalElements)
		).toString();
	}

	@Autowired
	private VisitedPagesDog _visitedPagesDog;

}