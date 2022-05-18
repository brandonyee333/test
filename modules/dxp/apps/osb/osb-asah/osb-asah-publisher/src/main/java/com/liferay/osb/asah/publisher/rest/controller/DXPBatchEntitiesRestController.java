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

package com.liferay.osb.asah.publisher.rest.controller;

import com.liferay.osb.asah.common.antivirus.ClamAVScanner;
import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.storage.Storage;
import com.liferay.osb.asah.common.storage.StorageConfiguration;
import com.liferay.osb.asah.common.storage.StorageFactory;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.publisher.messaging.DXPEntitiesChannels;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Riccardo Ferrari
 */
@CrossOrigin
@RequestMapping("/dxp-batch-entities")
@RestController
public class DXPBatchEntitiesRestController {

	@GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> get(
			@RequestHeader(value = HeaderConstants.DATA_SOURCE_ID) String
				dataSourceId,
			@RequestParam("resourceName") String resourceName,
			@RequestHeader(required = false, value = "If-Modified-Since") String
				ifModifiedSince)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Received download request for resource: " + resourceName);
		}

		Storage storage = _storageFactory.getStorage(
			_getStorageConfiguration(dataSourceId));

		File file = storage.readSparkJobResult(
			_parseDate(ifModifiedSince), resourceName);

		if (file == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();

		bodyBuilder.headers(
			new HttpHeaders() {
				{
					add(
						HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=" + resourceName + ".zip");
					add(
						HttpHeaders.CONTENT_LENGTH,
						String.valueOf(file.length()));
				}
			});

		return bodyBuilder.body(new FileSystemResource(file));
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> post(
			@RequestHeader(value = HeaderConstants.DATA_SOURCE_ID) String
				dataSourceId,
			@RequestPart(value = "file") List<MultipartFile> multipartFiles,
			@RequestPart(required = false, value = "uploadType") String
				uploadType)
		throws Exception {

		for (MultipartFile multipartFile : multipartFiles) {
			String name = multipartFile.getOriginalFilename();

			if (_log.isDebugEnabled()) {
				_log.debug("Received upload request " + name);
			}

			if (_clamAVScanner != null) {
				_clamAVScanner.scan(multipartFile.getInputStream());
			}

			ZipInputStream zipInputStream = new ZipInputStream(
				multipartFile.getInputStream());

			zipInputStream.getNextEntry();

			boolean success = _publishMessages(
				dataSourceId, name, zipInputStream, uploadType);

			_messageBus.sendMessage(
				Channel.COMPOSER,
				JSONUtil.put(
					"datasourceId", dataSourceId
				).put(
					"projectId", ProjectIdThreadLocal.getProjectId()
				).put(
					"resourceName", name
				).put(
					"uploadComplete", success
				).put(
					"uploadTime", DateUtil.toUTCString(new Date())
				).put(
					"uploadType", (uploadType != null) ? uploadType : "FULL"
				).toString());

			if (!success) {
				return new ResponseEntity(
					Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return ResponseEntity.ok(Collections.emptyList());
	}

	private StorageConfiguration _getStorageConfiguration(
		String googleBucketFolder) {

		StorageConfiguration.Builder builder = StorageConfiguration.builder(
			String.format(
				"%s/batch-%d",
				StringUtils.replace(
					_dxpBatchEntitiesStoragePathTemplate, "{projectId}",
					ProjectIdThreadLocal.getProjectId()),
				System.currentTimeMillis()));

		builder.googleBucket(
			StringUtils.replace(
				_dxpBatchEntitiesBucketTemplate, "{region}",
				System.getenv("LCP_PROJECT_CLUSTER")));
		builder.googleBucketFolder(googleBucketFolder);

		return builder.build();
	}

	private Date _parseDate(String dateString) {
		try {
			Instant instant = Instant.from(
				_dateTimeFormatter.parse(dateString));

			ZonedDateTime zonedDateTime = instant.atZone(ZoneOffset.UTC);

			Date date = Date.from(zonedDateTime.toInstant());

			if (_log.isDebugEnabled()) {
				_log.debug("Resource modified date: " + date);
			}

			return date;
		}
		catch (DateTimeParseException dateTimeParseException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to parse last modified date",
					dateTimeParseException);
			}

			return null;
		}
	}

	private boolean _publishMessages(
		String dataSourceId, String resourceName, InputStream inputStream,
		String uploadType) {

		Channel channel = _dxpEntitiesChannels.getChannel(resourceName);

		Map<String, String> messageAttributes = new HashMap<>();

		messageAttributes.put("dataSourceId", dataSourceId);
		messageAttributes.put("projectId", ProjectIdThreadLocal.getProjectId());
		messageAttributes.put("resourceName", resourceName);
		messageAttributes.put("uploadTime", DateUtil.toUTCString(new Date()));
		messageAttributes.put(
			"uploadType", (uploadType != null) ? uploadType : "FULL");

		boolean status = false;

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

			long count = 1;

			String line = bufferedReader.readLine();

			while (line != null) {
				String nextLine = bufferedReader.readLine();

				messageAttributes.put("count", String.valueOf(count));
				messageAttributes.put(
					"last", (nextLine == null) ? "true" : "false");

				_messageBus.sendMessage(channel, line, messageAttributes);

				count += 1;

				line = nextLine;
			}

			status = true;
		}
		catch (IOException ioException) {
			_log.error(ioException, ioException);
		}

		return status;
	}

	private static final Log _log = LogFactory.getLog(
		DXPBatchEntitiesRestController.class);

	private static final DateTimeFormatter _dateTimeFormatter =
		DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz");

	@Autowired(required = false)
	private ClamAVScanner _clamAVScanner;

	@Value(
		"${osb.asah.dxp.batch.entities.google.bucket:analytics-cloud-dxp-batch-entities-{region}}"
	)
	private String _dxpBatchEntitiesBucketTemplate;

	@Value(
		"${osb.asah.dxp.batch.entities.storage.path:/storage/{projectId}/dxp_batch_entities.json"
	)
	private String _dxpBatchEntitiesStoragePathTemplate;

	@Autowired
	private DXPEntitiesChannels _dxpEntitiesChannels;

	@Autowired
	private MessageBus _messageBus;

	@Autowired
	private StorageFactory _storageFactory;

}