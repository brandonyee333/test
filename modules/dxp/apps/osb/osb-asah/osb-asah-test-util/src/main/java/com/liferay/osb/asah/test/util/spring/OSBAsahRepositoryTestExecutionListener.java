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

import com.liferay.osb.asah.common.repository.Repository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author Marcos Martins
 */
public class OSBAsahRepositoryTestExecutionListener
	extends AbstractTestExecutionListener {

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		Class<?> clazz = testContext.getTestClass();

		RepositoryResource[] repositoryResources = clazz.getAnnotationsByType(
			RepositoryResource.class);

		for (RepositoryResource repositoryResource : repositoryResources) {
			_clearRepository(
				applicationContext, repositoryResource.repositoryClass());
		}
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		ApplicationContext applicationContext =
			testContext.getApplicationContext();

		Set<RepositoryResource> repositoryResources =
			AnnotatedElementUtils.findMergedRepeatableAnnotations(
				testContext.getTestMethod(), RepositoryResource.class);

		for (RepositoryResource repositoryResource : repositoryResources) {
			_clearRepository(
				applicationContext, repositoryResource.repositoryClass());
		}
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
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

		Repository repository = (Repository)applicationContext.getBean(
			repositoryClass);

		repository.deleteAll();
	}

	private Class<?> _getEntityClass(Class<?> repositoryClass) {
		Type[] types = repositoryClass.getGenericInterfaces();

		ParameterizedType parameterizedType = (ParameterizedType)types[0];

		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

		return (Class<?>)actualTypeArguments[0];
	}

	private void _prepareRepository(
			ApplicationContext applicationContext, Class<?> clazz,
			RepositoryResource repositoryResource)
		throws Exception {

		Repository repository = (Repository)applicationContext.getBean(
			repositoryResource.repositoryClass());

		JSONArray jsonArray = new JSONArray(
			ResourceUtil.readResourceToString(
				"dependencies/" + repositoryResource.resourcePath(), clazz));

		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Class<?> entityClass = _getEntityClass(
			repositoryResource.repositoryClass());

		repository.saveAll(
			stream.map(
				object -> _objectMapper.convertValue(object, entityClass)
			).collect(
				Collectors.toList()
			));
	}

	@Autowired
	private ObjectMapper _objectMapper;

}