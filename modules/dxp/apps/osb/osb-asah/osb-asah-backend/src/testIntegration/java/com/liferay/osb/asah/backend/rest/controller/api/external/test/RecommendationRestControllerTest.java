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

package com.liferay.osb.asah.backend.rest.controller.api.external.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.rest.controller.api.external.RecommendationRestController;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.ItemRecommendationRepository;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.repository.JobRunRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Marcellus Tavares
 */
public class RecommendationRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		RequestContextHolder.setRequestAttributes(
			new ServletRequestAttributes(new MockHttpServletRequest()));
	}

	@AfterEach
	public void tearDown() {
		RequestContextHolder.resetRequestAttributes();
	}

	@RepositoryResource(
		repositoryClass = ItemRecommendationRepository.class,
		resourcePath = "osbasahfaroinfo/recommended_items_info.json"
	)
	@RepositoryResource(
		repositoryClass = JobRepository.class,
		resourcePath = "osbasahfaroinfo/jobs.json"
	)
	@RepositoryResource(
		repositoryClass = JobRunRepository.class,
		resourcePath = "osbasahfaroinfo/job_runs.json"
	)
	@Test
	public void testGetPageRecommendationEntityModel() {
		EntityModel<?> pageRecommendationEntityModel =
			_recommendationRestController.getPageRecommendationEntityModel(
				JSONUtil.put(
					"modelId", "1"
				).put(
					"url", "https://page-a"
				).toString());

		Assertions.assertNotNull(pageRecommendationEntityModel);
	}

	@Autowired
	private RecommendationRestController _recommendationRestController;

}