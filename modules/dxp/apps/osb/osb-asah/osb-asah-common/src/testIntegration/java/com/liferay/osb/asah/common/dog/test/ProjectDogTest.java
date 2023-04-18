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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author André Miranda
 */
public class ProjectDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_projectDog.addConsumer(_consumer);

		ReflectionTestUtils.setField(_projectDog, "_nanitesHttp", _nanitesHttp);
		ReflectionTestUtils.setField(
			_projectDog, "_postgreSQLSchemaManager", _postgreSQLSchemaManager);
	}

	@AfterEach
	public void tearDown() {
		_projectRepository.deleteAll();

		ProjectIdThreadLocal.setGlobalContext(false);
	}

	@Test
	public void testAddProject() {
		_projectDog.addProject(new Project("project4"));

		Mockito.verify(
			_consumer, Mockito.times(1)
		).accept(
			ArgumentMatchers.eq("project4")
		);
		Mockito.verify(
			_nanitesHttp, Mockito.times(1)
		).rescheduleNanites();

		ProjectIdThreadLocal.setGlobalContext(true);

		Assertions.assertTrue(_projectRepository.existsById("project4"));
	}

	@SQLResource(resourcePath = "test_projects.sql")
	@Test
	public void testDeleteProject() {
		_projectDog.deleteProject(false, "project2");

		Mockito.verify(
			_nanitesHttp, Mockito.times(1)
		).removeSchedule();

		ProjectIdThreadLocal.setGlobalContext(true);

		Assertions.assertTrue(_projectRepository.existsById("project1"));
		Assertions.assertFalse(_projectRepository.existsById("project2"));
		Assertions.assertTrue(_projectRepository.existsById("project3"));
	}

	@SQLResource(resourcePath = "test_projects.sql")
	@Test
	public void testGetProjects() {
		List<Project> projects = _projectDog.getProjects();

		Stream<Project> stream = projects.stream();

		Assertions.assertArrayEquals(
			new String[] {"project1", "project2", "project3"},
			stream.map(
				Project::getId
			).sorted(
			).toArray());
	}

	@SQLResource(resourcePath = "test_projects.sql")
	@Test
	public void testUpdateVersion() {
		_projectDog.updateVersion("project1", "5.0.0");

		Project project1 = _projectDog.getProject("project1");

		Assertions.assertEquals("5.0.0", project1.getVersion());

		Project project2 = _projectDog.getProject("project2");

		Assertions.assertEquals("4.0.0", project2.getVersion());
	}

	@Mock
	private Consumer<String> _consumer;

	@MockBean
	private NanitesHttp _nanitesHttp;

	@MockBean
	private PostgreSQLSchemaManager _postgreSQLSchemaManager;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private ProjectRepository _projectRepository;

}