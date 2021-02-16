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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchRepository;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.Channel;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Marcellus Tavares
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ElasticsearchRepositoryTest {

	@Test
	public void testAdd() {
		ElasticsearchRepository<Channel> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"channels", _faroInfoElasticsearchInvoker, Channel.class,
				_objectMapper);

		Channel channel = elasticsearchRepository.add(new Channel("name"));

		Assert.assertNotNull(channel.getId());
		Assert.assertEquals("name", channel.getName());
	}

	@Test
	public void testFetchFirst() {
		ElasticsearchRepository<Channel> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"channels", _faroInfoElasticsearchInvoker, Channel.class,
				_objectMapper);

		Channel channel = elasticsearchRepository.add(new Channel("name1"));

		elasticsearchRepository.add(new Channel("name2"));

		Channel returnedChannel = elasticsearchRepository.fetchFirst(
			searchSourceBuilder -> searchSourceBuilder.sort(
				SortBuilderUtil.fieldSort("id", SortOrder.ASC)));

		Assert.assertEquals(channel.getId(), returnedChannel.getId());
	}

	@Test
	public void testGet() {
		ElasticsearchRepository<Channel> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"channels", _faroInfoElasticsearchInvoker, Channel.class,
				_objectMapper);

		Channel channel = elasticsearchRepository.add(new Channel("name"));

		Channel returnedChannel = elasticsearchRepository.get(
			String.valueOf(channel.getId()));

		Assert.assertEquals(channel.getId(), returnedChannel.getId());
	}

	@Test
	public void testSearch() {
		ElasticsearchRepository<Channel> elasticsearchRepository =
			new ElasticsearchRepository<>(
				"channels", _faroInfoElasticsearchInvoker, Channel.class,
				_objectMapper);

		Channel channel1 = elasticsearchRepository.add(new Channel("name1"));

		Channel channel2 = elasticsearchRepository.add(new Channel("name2"));

		Channel channel3 = elasticsearchRepository.add(new Channel("name3"));

		ResultBag<Channel> resultBag = elasticsearchRepository.search(
			QueryBuilders.matchAllQuery(), 20, Sort.desc("id"), 0);

		Assert.assertEquals(3, resultBag.getTotal());
		Assert.assertEquals(
			Arrays.asList(channel3, channel2, channel1),
			resultBag.getResults());
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}