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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchRepository;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.Preference;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Arrays;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ElasticsearchRepositoryTest {

	@Test
	public void testAdd() {
		ElasticsearchRepository<MyPreference> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"preferences", _faroInfoElasticsearchInvoker,
				MyPreference.class);

		MyPreference myPreference = elasticsearchRepository.add(
			new MyPreference("key", "value"));

		Assert.assertNotNull(myPreference.getId());
		Assert.assertEquals("key", myPreference.getKey());
		Assert.assertEquals("value", myPreference.getValue());
	}

	@Test
	public void testFetchFirst() {
		ElasticsearchRepository<MyPreference> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"preferences", _faroInfoElasticsearchInvoker,
				MyPreference.class);

		MyPreference myPreference1 = elasticsearchRepository.add(
			new MyPreference("key1", "value1"));

		elasticsearchRepository.add(new MyPreference("key2", "value2"));

		MyPreference returnedMyPreference = elasticsearchRepository.fetchFirst(
			searchSourceBuilder -> searchSourceBuilder.sort(
				SortBuilderUtil.fieldSort("id", SortOrder.ASC)));

		Assert.assertEquals(
			myPreference1.getId(), returnedMyPreference.getId());
	}

	@Test
	public void testGet() {
		ElasticsearchRepository<MyPreference> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"preferences", _faroInfoElasticsearchInvoker,
				MyPreference.class);

		MyPreference myPreference = elasticsearchRepository.add(
			new MyPreference("key1", "value1"));

		MyPreference returnedMyPreference = elasticsearchRepository.get(
			myPreference.getId());

		Assert.assertEquals(myPreference.getId(), returnedMyPreference.getId());
	}

	@Test
	public void testSearch() {
		ElasticsearchRepository<MyPreference> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"preferences", _faroInfoElasticsearchInvoker,
				MyPreference.class);

		MyPreference myPreference1 = elasticsearchRepository.add(
			new MyPreference("key1", "value1"));
		MyPreference myPreference2 = elasticsearchRepository.add(
			new MyPreference("key2", "value2"));
		MyPreference myPreference3 = elasticsearchRepository.add(
			new MyPreference("key3", "value3"));

		ResultBag<MyPreference> resultBag = elasticsearchRepository.search(
			QueryBuilders.matchAllQuery(), 20, Sort.desc("id"), 0);

		Assert.assertEquals(3, resultBag.getTotal());
		Assert.assertEquals(
			Arrays.asList(myPreference3, myPreference2, myPreference1),
			resultBag.getResults());
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private static class MyPreference extends Preference {

		public MyPreference() {
			super(null, null);
		}

		public MyPreference(String key, String value) {
			super(key, value);
		}

	}

}