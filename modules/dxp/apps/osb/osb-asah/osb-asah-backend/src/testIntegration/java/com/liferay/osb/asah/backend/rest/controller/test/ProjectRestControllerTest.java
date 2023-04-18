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
import com.liferay.osb.asah.backend.dto.ProjectDTO;
import com.liferay.osb.asah.backend.dto.ProjectDetailDTO;
import com.liferay.osb.asah.backend.rest.controller.ProjectsRestController;
import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Riccardo Ferrari
 */
public class ProjectRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources_6.json"
	)
	@Test
	public void testGetProjectDetails() {
		List<ProjectDetailDTO> projectDetails =
			_projectsRestController.getProjectDetails();

		Assertions.assertEquals(1, projectDetails.size());

		ProjectDetailDTO projectDetailDTO = projectDetails.get(0);

		Assertions.assertTrue(projectDetailDTO.getAccountsSelected());
		Assertions.assertFalse(projectDetailDTO.getCommerceChannelsSelected());
		Assertions.assertTrue(projectDetailDTO.getContactsSelected());
		Assertions.assertTrue(projectDetailDTO.getSitesSelected());
	}

	@Test
	public void testGetProjectDTO() {
		List<ProjectDTO> projectDTOs = _projectsRestController.getProjectDTOs();

		Assertions.assertEquals(1, projectDTOs.size());

		ProjectDTO projectDTO = projectDTOs.get(0);

		Assertions.assertEquals("test", projectDTO.getId());
		Assertions.assertEquals(
			ReleaseInfo.getVersion(), projectDTO.getVersion());
	}

	@BeforeEach
	protected void setUp() {
		_projectDog.addProject("test");

		ProjectIdThreadLocal.setProjectId("test");
	}

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private ProjectsRestController _projectsRestController;

}