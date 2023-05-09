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

import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.dog.BQEventDog;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthew Kong
 */
@RequestMapping(
	produces = "application/json", value = "/api/1.0/document-libraries"
)
@RestController
public class DocumentLibrariesRestController extends BaseRestController {

	@GetMapping("/download-count")
	public String getDownloadsCount(
		String assetId, @RequestParam(required = false) String channelId,
		@RequestParam(required = false) String dataSourceId,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "endDate", required = false)
		LocalDate endLocalDate,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "startDate", required = false)
		LocalDate startLocalDate) {

		return String.valueOf(
			_bqEventDog.countBQEvents(
				"Document", assetId, Long.valueOf(channelId),
				Long.valueOf(dataSourceId), endLocalDate, "documentDownloaded",
				startLocalDate));
	}

	@GetMapping("/preview-count")
	public String getPreviewsCount(
		String assetId, @RequestParam(required = false) String channelId,
		@RequestParam(required = false) String dataSourceId,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "endDate", required = false)
		LocalDate endLocalDate,
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		@RequestParam(name = "startDate", required = false)
		LocalDate startLocalDate) {

		return String.valueOf(
			_bqEventDog.countBQEvents(
				"Document", assetId, Long.valueOf(channelId),
				Long.valueOf(dataSourceId), endLocalDate, "documentPreviewed",
				startLocalDate));
	}

	@Autowired
	private BQEventDog _bqEventDog;

}