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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.rest.controller.DataControlTasksRestController;
import com.liferay.osb.asah.common.repository.DataControlTaskRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;

/**
 * @author Robson Pastor
 */
public class DataControlTasksRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = DataControlTaskRepository.class,
		resourcePath = "osbasahfaroinfo/data_control_tasks.json"
	)
	@Test
	public void testDownloadLogs() throws Exception {
		ResponseEntity responseEntity =
			_dataControlTasksRestController.downloadLogs("");

		FileSystemResource fileSystemResource =
			(FileSystemResource)responseEntity.getBody();

		Assertions.assertNotNull(fileSystemResource);
		Assertions.assertEquals(
			ResourceUtil.readResourceToString(
				"dependencies/data_control_tasks.csv", this),
			IOUtils.toString(
				fileSystemResource.getInputStream(), StandardCharsets.UTF_8));
	}

	@Autowired
	private DataControlTasksRestController _dataControlTasksRestController;

}