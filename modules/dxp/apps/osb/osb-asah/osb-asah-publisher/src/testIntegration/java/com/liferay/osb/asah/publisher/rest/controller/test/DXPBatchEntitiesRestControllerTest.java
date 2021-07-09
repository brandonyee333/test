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

package com.liferay.osb.asah.publisher.rest.controller.test;

import com.liferay.osb.asah.common.constants.HeaderConstants;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.storage.Storage;
import com.liferay.osb.asah.common.storage.StorageConfiguration;
import com.liferay.osb.asah.common.storage.StorageFactory;
import com.liferay.osb.asah.publisher.rest.controller.DXPBatchEntitiesRestController;
import com.liferay.osb.asah.publisher.spring.OSBAsahPublisherSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.cache.OSBAsahRedisEnabledTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.io.File;
import java.io.InputStream;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.zip.ZipInputStream;

import org.assertj.core.api.Assertions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Riccardo Ferrari
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		OSBAsahPublisherSpringBootApplication.class,
		OSBAsahRedisEnabledTestConfiguration.class
	},
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class DXPBatchEntitiesRestControllerTest {

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetNoContent() {
		Mockito.when(
			_storage.readSparkJobResult(
				ArgumentMatchers.any(Date.class), ArgumentMatchers.anyString())
		).thenReturn(
			null
		);

		Mockito.when(
			_storageFactory.getStorage(
				ArgumentMatchers.any(StorageConfiguration.class))
		).thenReturn(
			_storage
		);

		ResponseEntity<Resource> responseEntity = _exchange(_getHttpHeaders());

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(204)
		);

		Assertions.assertThat(
			responseEntity.getBody()
		).isNull();
	}

	@Test
	public void testGetStatusCode200() throws Exception {
		Mockito.when(
			_storage.readSparkJobResult(
				ArgumentMatchers.any(Date.class), ArgumentMatchers.anyString())
		).thenReturn(
			File.createTempFile(RandomTestUtil.randomString(), null)
		);

		Mockito.when(
			_storageFactory.getStorage(
				ArgumentMatchers.any(StorageConfiguration.class))
		).thenReturn(
			_storage
		);

		ResponseEntity<Resource> responseEntity = _exchange(_getHttpHeaders());

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(200)
		);
	}

	@Test
	public void testGetStatusCode400() throws Exception {
		Mockito.when(
			_storage.readSparkJobResult(
				ArgumentMatchers.any(Date.class), ArgumentMatchers.anyString())
		).thenReturn(
			File.createTempFile(RandomTestUtil.randomString(), null)
		);

		Mockito.when(
			_storageFactory.getStorage(
				ArgumentMatchers.any(StorageConfiguration.class))
		).thenReturn(
			_storage
		);

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("If-Modified-Since", _getModifiedSince());

		ResponseEntity<Resource> responseEntity = _exchange(httpHeaders);

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(400)
		);
	}

	@Test
	public void testGetWithInvalidIfModifiedSince() throws Exception {
		Mockito.when(
			_storage.readSparkJobResult(
				ArgumentMatchers.any(), ArgumentMatchers.anyString())
		).thenReturn(
			File.createTempFile(RandomTestUtil.randomString(), null)
		);

		Mockito.when(
			_storageFactory.getStorage(
				ArgumentMatchers.any(StorageConfiguration.class))
		).thenReturn(
			_storage
		);

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(HeaderConstants.DATA_SOURCE_ID, "test-data-source-id");
		httpHeaders.add("If-Modified-Since", DateUtil.newDateString());

		ResponseEntity<Resource> responseEntity = _exchange(httpHeaders);

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(200)
		);
	}

	@Test
	public void testPost() throws Exception {
		Mockito.when(
			_storage.write(ArgumentMatchers.any(InputStream.class))
		).thenReturn(
			true
		);

		Mockito.when(
			_storageFactory.getStorage(
				ArgumentMatchers.any(StorageConfiguration.class))
		).thenReturn(
			_storage
		);

		MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();

		multipartBodyBuilder.part("file", _getInputStream());

		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(
			String.format("http://localhost:%s/dxp-batch-entities", _serverPort)
		).build();

		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(HeaderConstants.DATA_SOURCE_ID, "test-data-source-id");
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		ResponseEntity<Resource> responseEntity = _testRestTemplate.exchange(
			uriComponents.toString(), HttpMethod.POST,
			new HttpEntity<>(multipartBodyBuilder.build(), httpHeaders),
			Resource.class);

		Assertions.assertThat(
			responseEntity.getStatusCode()
		).isEqualTo(
			HttpStatus.valueOf(200)
		);
	}

	private ResponseEntity<Resource> _exchange(HttpHeaders httpHeaders) {
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(
			String.format("http://localhost:%s/dxp-batch-entities", _serverPort)
		).queryParam(
			"resourceName", RandomTestUtil.randomString()
		).build();

		return _testRestTemplate.exchange(
			uriComponents.toString(), HttpMethod.GET,
			new HttpEntity<>(null, httpHeaders), Resource.class);
	}

	private HttpHeaders _getHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add(HeaderConstants.DATA_SOURCE_ID, "test-data-source-id");
		httpHeaders.add("If-Modified-Since", _getModifiedSince());

		return httpHeaders;
	}

	private InputStream _getInputStream() throws Exception {
		ClassPathResource classPathResource = new ClassPathResource(
			"dependencies/products.json", getClass());

		ZipInputStream zipInputStream = new ZipInputStream(
			classPathResource.getInputStream());

		return zipInputStream;
	}

	private String _getModifiedSince() {
		LocalDateTime localDateTime = LocalDateTime.now();

		Instant instant = localDateTime.toInstant(ZoneOffset.UTC);

		return _dateTimeFormatter.format(
			instant.atZone(ZoneId.systemDefault()));
	}

	private static final DateTimeFormatter _dateTimeFormatter =
		DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzz");

	@Autowired
	@InjectMocks
	private DXPBatchEntitiesRestController _dxpBatchEntitiesRestController;

	@LocalServerPort
	private int _serverPort;

	@Mock
	private Storage _storage;

	@Mock
	private StorageFactory _storageFactory;

	@Autowired
	private TestRestTemplate _testRestTemplate;

}