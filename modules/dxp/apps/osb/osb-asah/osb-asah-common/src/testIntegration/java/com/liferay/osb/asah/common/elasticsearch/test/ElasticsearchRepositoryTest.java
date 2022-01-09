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

package com.liferay.osb.asah.common.elasticsearch.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchRepository;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcellus Tavares
 */
public class ElasticsearchRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testAdd() {
		ElasticsearchRepository<Interest> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"interests", _faroInfoElasticsearchInvoker, Interest.class,
				_objectMapper);

		Interest interest = new Interest();

		interest.setName("name");

		interest = elasticsearchRepository.add(interest);

		Assertions.assertNotNull(interest.getId());
		Assertions.assertEquals("name", interest.getName());
	}

	@Test
	public void testFetchFirst() {
		ElasticsearchRepository<Interest> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"interests", _faroInfoElasticsearchInvoker, Interest.class,
				_objectMapper);

		Interest interest1 = new Interest();

		interest1.setName("name1");

		interest1 = elasticsearchRepository.add(interest1);

		Interest interest2 = new Interest();

		interest2.setName("name2");

		elasticsearchRepository.add(interest2);

		Interest returnedInterest = elasticsearchRepository.fetchFirst(
			searchSourceBuilder -> searchSourceBuilder.sort(
				SortBuilderUtil.fieldSort("id", SortOrder.ASC)));

		Assertions.assertEquals(interest1.getId(), returnedInterest.getId());
	}

	@Test
	public void testGet() {
		ElasticsearchRepository<Interest> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"interests", _faroInfoElasticsearchInvoker, Interest.class,
				_objectMapper);

		Interest interest = new Interest();

		interest.setName("name");

		interest = elasticsearchRepository.add(interest);

		Interest returnedInterest = elasticsearchRepository.get(
			String.valueOf(interest.getId()));

		Assertions.assertEquals(interest.getId(), returnedInterest.getId());
	}

	@Test
	public void testSearch() {
		ElasticsearchRepository<Interest> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"interests", _faroInfoElasticsearchInvoker, Interest.class,
				_objectMapper);

		Interest interest1 = new Interest();

		interest1.setName("name1");

		interest1 = elasticsearchRepository.add(interest1);

		Interest interest2 = new Interest();

		interest2.setName("name2");

		interest2 = elasticsearchRepository.add(interest2);

		Interest interest3 = new Interest();

		interest3.setName("name3");

		interest3 = elasticsearchRepository.add(interest3);

		ResultBag<Interest> resultBag = elasticsearchRepository.search(
			QueryBuilders.matchAllQuery(), 20, Sort.desc("id"), 0);

		Assertions.assertEquals(3, resultBag.getTotal());
		Assertions.assertEquals(
			Arrays.asList(interest3, interest2, interest1),
			resultBag.getResults());
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}