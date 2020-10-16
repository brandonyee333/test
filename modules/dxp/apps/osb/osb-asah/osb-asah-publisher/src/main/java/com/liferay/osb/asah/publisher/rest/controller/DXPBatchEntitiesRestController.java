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

import com.liferay.osb.asah.common.storage.Storage;
import com.liferay.osb.asah.common.storage.StorageConfiguration;
import com.liferay.osb.asah.common.storage.StorageFactory;

import java.io.File;

import java.util.Collections;
import java.util.Date;
import java.util.zip.ZipInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
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
			@RequestHeader(required = false, value = "OSB-Asah-Data-Source-ID")
				String dataSourceId,
			@RequestParam("resourceName") String resourceName,
			@DateTimeFormat(pattern = "EEE, dd MMM yyyy HH:mm:ss zzz")
			@RequestHeader(required = false, value = "If-Modified-Since")
				Date resourceLastModifiedDate)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Received download request for resource: " + resourceName);
		}

		Storage storage = _storageFactory.getStorage(
			_getStorageConfiguration(dataSourceId, resourceName));

		File file = storage.readSparkJobResult(
			resourceLastModifiedDate, resourceName);

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
			@RequestHeader(required = false, value = "OSB-Asah-Data-Source-ID")
				String dataSourceId,
			@RequestParam("file") MultipartFile multipartFile)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Received upload request " +
					multipartFile.getOriginalFilename());
		}

		Storage storage = _storageFactory.getStorage(
			_getStorageConfiguration(
				dataSourceId, multipartFile.getOriginalFilename()));

		ZipInputStream zipInputStream = new ZipInputStream(
			multipartFile.getInputStream());

		zipInputStream.getNextEntry();

		if (!storage.write(zipInputStream)) {
			return new ResponseEntity(
				Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return ResponseEntity.ok(Collections.emptyList());
	}

	private StorageConfiguration _getStorageConfiguration(
		String dataSourceId, String resourceName) {

		StorageConfiguration.Builder builder = StorageConfiguration.builder(
			_dxpBatchEntitiesStoragePath + "/" + resourceName);

		builder.googleBucket(_dxpBatchEntitiesBucket);
		builder.googleBucketFolder(dataSourceId);

		return builder.build();
	}

	private static final Log _log = LogFactory.getLog(
		DXPBatchEntitiesRestController.class);

	@Value(
		"${osb.asah.dxp.batch.entities.google.bucket:analytics-cloud-dxp-batch-entities}"
	)
	private String _dxpBatchEntitiesBucket;

	@Value("${osb.asah.dxp.batch.entities.storage.path:/tmp/}")
	private String _dxpBatchEntitiesStoragePath;

	@Autowired
	private StorageFactory _storageFactory;

}