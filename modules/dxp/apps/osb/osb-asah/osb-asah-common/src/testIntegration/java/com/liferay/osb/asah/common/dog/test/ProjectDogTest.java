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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilders;

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
		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		elasticsearchInvoker.delete("projects", QueryBuilders.matchAllQuery());

		elasticsearchInvoker.add("projects", JSONUtil.put("id", "project1"));
		elasticsearchInvoker.add("projects", JSONUtil.put("id", "project2"));
		elasticsearchInvoker.add("projects", JSONUtil.put("id", "project3"));

		_projectDog.addConsumer(_consumer);

		ReflectionTestUtils.setField(_projectDog, "_nanitesHttp", _nanitesHttp);
		ReflectionTestUtils.setField(
			_projectDog, "_postgreSQLSchemaManager", _postgreSQLSchemaManager);
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

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		Assertions.assertArrayEquals(
			new String[] {"project1", "project2", "project3", "project4"},
			JSONUtil.toStringArray(
				elasticsearchInvoker.get(
					"projects",
					Arrays.asList(SortBuilderUtil.fieldSort("id.keyword")),
					new String[] {"id"}),
				"id"));
	}

	@Test
	public void testDeleteProject() {
		_projectDog.deleteProject("project2");

		Mockito.verify(
			_nanitesHttp, Mockito.times(1)
		).removeSchedule();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		Assertions.assertArrayEquals(
			new String[] {"project1", "project3"},
			JSONUtil.toStringArray(
				elasticsearchInvoker.get(
					"projects",
					Arrays.asList(SortBuilderUtil.fieldSort("id.keyword")),
					new String[] {"id"}),
				"id"));
	}

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

	@Mock
	private Consumer<String> _consumer;

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	@MockBean
	private NanitesHttp _nanitesHttp;

	@MockBean
	private PostgreSQLSchemaManager _postgreSQLSchemaManager;

	@Autowired
	private ProjectDog _projectDog;

}