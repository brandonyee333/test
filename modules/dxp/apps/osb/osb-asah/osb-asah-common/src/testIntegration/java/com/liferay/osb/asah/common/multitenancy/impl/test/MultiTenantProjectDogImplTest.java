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

package com.liferay.osb.asah.common.multitenancy.impl.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.multitenancy.impl.MultiTenantProjectDogImpl;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author André Miranda
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class MultiTenantProjectDogImplTest {

	@Before
	public void setUp() throws Exception {
		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		elasticsearchInvoker.delete("projects", QueryBuilders.matchAllQuery());

		elasticsearchInvoker.add("projects", JSONUtil.put("id", "project1"));
		elasticsearchInvoker.add("projects", JSONUtil.put("id", "project2"));
		elasticsearchInvoker.add("projects", JSONUtil.put("id", "project3"));

		_multiTenantProjectDogImpl = new MultiTenantProjectDogImpl(
			_postCreationConsumer, _projectRepository);

		ReflectionTestUtils.setField(
			_multiTenantProjectDogImpl, "_nanitesHttp", _nanitesHttp);
	}

	@Test
	public void testAddProject() {
		_multiTenantProjectDogImpl.addProject(new Project("project4"));

		Mockito.verify(
			_postCreationConsumer, Mockito.times(1)
		).accept(
			ArgumentMatchers.eq("project4")
		);
		Mockito.verify(
			_nanitesHttp, Mockito.times(1)
		).rescheduleNanites();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		Assert.assertArrayEquals(
			new String[] {"project1", "project2", "project3", "project4"},
			JSONUtil.toStringArray(elasticsearchInvoker.get("projects"), "id"));
	}

	@Test
	public void testDeleteProject() {
		_multiTenantProjectDogImpl.deleteProject("project2");

		Mockito.verify(
			_nanitesHttp, Mockito.times(1)
		).removeSchedule();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerManager.getGlobalElasticsearchInvoker();

		Assert.assertArrayEquals(
			new String[] {"project1", "project3"},
			JSONUtil.toStringArray(elasticsearchInvoker.get("projects"), "id"));
	}

	@Test
	public void testGetProjects() throws Exception {
		List<Project> projects = _multiTenantProjectDogImpl.getProjects();

		Stream<Project> stream = projects.stream();

		Assert.assertArrayEquals(
			new String[] {"project1", "project2", "project3"},
			stream.map(
				Project::getId
			).sorted(
			).toArray());
	}

	@Autowired
	private ElasticsearchInvokerManager _elasticsearchInvokerManager;

	private MultiTenantProjectDogImpl _multiTenantProjectDogImpl;

	@MockBean
	private NanitesHttp _nanitesHttp;

	@Mock
	private Consumer<String> _postCreationConsumer;

	@Autowired
	private ProjectRepository _projectRepository;

}