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

import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.util.CSVUtil;

import java.io.File;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matthew Kong
 */
@RequestMapping("/suppressions")
@RestController
public class SuppressionsRestController extends BaseRestController {

	@GetMapping("/logs")
	public ResponseEntity downloadLogs(
			@RequestParam(name = "filter", required = false)
				String filterString)
		throws Exception {

		File file = CSVUtil.createCSVFile(
			"suppressions", faroInfoElasticsearchInvoker, _fieldNames,
			"suppression-logs-", new File(_tempPath),
			FilterStringToQueryBuilderConverter.convert(filterString));

		return toDownloadResponse(file, "suppression-logs.csv");
	}

	private static final Map<String, String> _fieldNames =
		new LinkedHashMap<String, String>() {
			{
				put("createDate", "Suppression Date");
				put("dataControlTaskBatchId", "Request ID");
				put("dataControlTaskCreateDate", "Request Date");
				put("dataControlTaskStatus", "Request Status");
				put("emailAddress", "Email");
			}
		};

	@Value("${osb.asah.backend.temp.path:/temp}")
	private String _tempPath;

}