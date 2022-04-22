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

package com.liferay.osb.asah.test.util.spring;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.TestContext;

/**
 * @author Marcos Martins
 */
@TestComponent
public class OSBAsahRepositoryTestExecutionListener
	extends BaseOSBAsahTestExecutionListener {

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		Class<?> clazz = testContext.getTestClass();

		RepositoryResource[] repositoryResources = clazz.getAnnotationsByType(
			RepositoryResource.class);

		for (RepositoryResource repositoryResource : repositoryResources) {
			_clearRepository(
				applicationContext, repositoryResource.repositoryClass());
		}

		ProjectIdThreadLocal.remove();
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		Set<RepositoryResource> repositoryResources =
			AnnotatedElementUtils.findMergedRepeatableAnnotations(
				testContext.getTestMethod(), RepositoryResource.class);

		for (RepositoryResource repositoryResource : repositoryResources) {
			_clearRepository(
				applicationContext, repositoryResource.repositoryClass());
		}

		ProjectIdThreadLocal.remove();
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		super.beforeTestClass(testContext);

		ProjectIdThreadLocal.setProjectId("test");

		Class<?> clazz = testContext.getTestClass();

		RepositoryResource[] repositoryResources = clazz.getAnnotationsByType(
			RepositoryResource.class);

		if (repositoryResources.length == 0) {
			return;
		}

		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		for (RepositoryResource repositoryResource : repositoryResources) {
			_prepareRepository(applicationContext, clazz, repositoryResource);
		}
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		Set<RepositoryResource> repositoryResources =
			AnnotatedElementUtils.findMergedRepeatableAnnotations(
				testContext.getTestMethod(), RepositoryResource.class);

		for (RepositoryResource repositoryResource : repositoryResources) {
			_prepareRepository(
				applicationContext, testContext.getTestClass(),
				repositoryResource);
		}
	}

	private void _clearRepository(
		ApplicationContext applicationContext, Class<?> repositoryClass) {

		PagingAndSortingRepository pagingAndSortingRepository =
			(PagingAndSortingRepository)applicationContext.getBean(
				repositoryClass);

		pagingAndSortingRepository.deleteAll();
	}

	private Class<?> _getEntityClass(Class<?> repositoryClass) {
		Type[] types = repositoryClass.getGenericInterfaces();

		ParameterizedType parameterizedType = null;

		for (Type type : types) {
			if (type instanceof ParameterizedType) {
				parameterizedType = (ParameterizedType)type;
			}
		}

		if (parameterizedType == null) {
			throw new IllegalArgumentException();
		}

		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

		return (Class<?>)actualTypeArguments[0];
	}

	private void _prepareRepository(
			ApplicationContext applicationContext, Class<?> clazz,
			RepositoryResource repositoryResource)
		throws Exception {

		PagingAndSortingRepository pagingAndSortingRepository =
			(PagingAndSortingRepository)applicationContext.getBean(
				repositoryResource.repositoryClass());

		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/" + repositoryResource.resourcePath(),
					clazz)));

		jsonArray.forEach(
			object -> {
				JSONObject jsonObject = (JSONObject)object;

				if (!jsonObject.has("isNew")) {
					jsonObject.put("isNew", true);
				}
			});

		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Class<?> entityClass = _getEntityClass(
			repositoryResource.repositoryClass());

		pagingAndSortingRepository.saveAll(
			stream.map(
				object -> _objectMapper.convertValue(object, entityClass)
			).collect(
				Collectors.toList()
			));
	}

	@Autowired
	private ObjectMapper _objectMapper;

}