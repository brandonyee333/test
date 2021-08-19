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

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.storage.Storage;
import com.liferay.osb.asah.common.storage.StorageConfiguration;
import com.liferay.osb.asah.common.storage.StorageFactory;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.io.File;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Collections;
import java.util.Date;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import org.springframework.web.bind.annotation.RestController;

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
			HttpServletRequest httpServletRequest)
		throws Exception {

		ServletFileUpload servletFileUpload = new ServletFileUpload();

		long maxSize = 2L * 1024 * 1024 * 1024;

		servletFileUpload.setFileSizeMax(maxSize);
		servletFileUpload.setSizeMax(maxSize);

		FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(
			httpServletRequest);

		while (fileItemIterator.hasNext()) {
			FileItemStream fileItemStream = fileItemIterator.next();

			String name = fileItemStream.getName();

			if (_log.isDebugEnabled()) {
				_log.debug("Received upload request " + name);
			}

			Storage storage = _storageFactory.getStorage(
				_getStorageConfiguration(dataSourceId + "/" + name));

			ZipInputStream zipInputStream = new ZipInputStream(
				fileItemStream.openStream());

			zipInputStream.getNextEntry();

			boolean success = storage.write(zipInputStream);

			storage.close();

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

	private static final Log _log = LogFactory.getLog(
		DXPBatchEntitiesRestController.class);

	private static final DateTimeFormatter _dateTimeFormatter =
		DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz");

	@Value(
		"${osb.asah.dxp.batch.entities.google.bucket:analytics-cloud-dxp-batch-entities-{region}}"
	)
	private String _dxpBatchEntitiesBucketTemplate;

	@Value(
		"${osb.asah.dxp.batch.entities.storage.path:/storage/{projectId}/dxp_batch_entities.json"
	)
	private String _dxpBatchEntitiesStoragePathTemplate;

	@Autowired
	private StorageFactory _storageFactory;

}