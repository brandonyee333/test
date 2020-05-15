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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.util.CSVUtil;
import com.liferay.osb.asah.common.zip.ZipFileBuilder;

import java.io.File;
import java.io.FileInputStream;

import java.util.LinkedHashMap;
import java.util.Map;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Matthew Kong
 */
@RequestMapping("/data-control-tasks")
@RestController
public class DataControlTasksRestController extends BaseRestController {

	@GetMapping("/{id}")
	public ResponseEntity download(@PathVariable String id) {
		JSONObject dataControlTaskJSONObject =
			faroInfoElasticsearchInvoker.fetch(
				"data-control-tasks",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termsQuery("id", id)
				).filter(
					QueryBuilders.termsQuery(
						"status", DataControlTaskStatus.COMPLETED.toString())
				));

		if (dataControlTaskJSONObject == null) {
			return toNotFoundResponse();
		}

		File file = new File(
			_exportPath + "/" + dataControlTaskJSONObject.getString("id") +
				".zip");

		if (!file.exists()) {
			return toNotFoundResponse();
		}

		return toDownloadResponse(
			file, _getExportFileName(dataControlTaskJSONObject, file));
	}

	@GetMapping
	public ResponseEntity downloadBatch(
			@RequestParam("filter") String filterString)
		throws Exception {

		File file = File.createTempFile(
			"data-control-export-", ".zip", new File(_tempPath));

		file.deleteOnExit();

		JSONArray dataControlTasksJSONArray = faroInfoElasticsearchInvoker.get(
			"data-control-tasks",
			BoolQueryBuilderUtil.filter(
				FilterStringToQueryBuilderConverter.convert(filterString)
			).filter(
				QueryBuilders.termsQuery(
					"status", DataControlTaskStatus.COMPLETED.toString())
			));

		ZipFileBuilder zipFileBuilder = new ZipFileBuilder(file);

		for (int i = 0; i < dataControlTasksJSONArray.length(); i++) {
			JSONObject dataControlTaskJSONObject =
				dataControlTasksJSONArray.getJSONObject(i);

			File dataControlTaskFile = new File(
				_exportPath + "/" + dataControlTaskJSONObject.getString("id") +
					".zip");

			if (!dataControlTaskFile.exists()) {
				continue;
			}

			zipFileBuilder.addToZip(
				_getExportFileName(
					dataControlTaskJSONObject, dataControlTaskFile),
				zipOutputStream -> {
					try (FileInputStream fileInputStream = new FileInputStream(
							dataControlTaskFile)) {

						byte[] buffer = new byte[1024];

						int length = fileInputStream.read(buffer);

						while (length > 0) {
							zipOutputStream.write(buffer, 0, length);

							length = fileInputStream.read(buffer);
						}
					}
				});
		}

		zipFileBuilder.build();

		return toDownloadResponse(file, file.getName());
	}

	@GetMapping("/logs")
	public ResponseEntity downloadLogs(
			@RequestParam(name = "filter", required = false)
				String filterString)
		throws Exception {

		File file = CSVUtil.createCSVFile(
			"data-control-tasks", faroInfoElasticsearchInvoker, _fieldNames,
			"data-control-task-logs-", new File(_tempPath),
			FilterStringToQueryBuilderConverter.convert(filterString));

		return toDownloadResponse(file, "data-control-task-logs.csv");
	}

	@PostMapping
	public String upload(@RequestParam MultipartFile multipartFile)
		throws Exception {

		File file = new File(
			_tempPath + "/data-control-task-" + System.currentTimeMillis() +
				".csv");

		file.deleteOnExit();

		multipartFile.transferTo(file);

		return file.getName();
	}

	private String _getExportFileName(
		JSONObject dataControlTaskJSONObject, File file) {

		return dataControlTaskJSONObject.getString("emailAddress") + "-" +
			file.getName();
	}

	private static final Map<String, String> _fieldNames =
		new LinkedHashMap<String, String>() {
			{
				put("batchId", "Request ID");
				put("completeDate", "Complete Date");
				put("createDate", "Request Date");
				put("emailAddress", "Email");
				put("startDate", "Process Date");
				put("status", "Request Status");
				put("type", "Request Type");
			}
		};

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPath;

	@Value("${osb.asah.backend.temp.path:/temp}")
	private String _tempPath;

}