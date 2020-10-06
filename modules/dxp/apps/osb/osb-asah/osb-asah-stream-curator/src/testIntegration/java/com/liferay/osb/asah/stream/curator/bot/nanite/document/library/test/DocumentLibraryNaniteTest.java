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

package com.liferay.osb.asah.stream.curator.bot.nanite.document.library.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.bot.nanite.document.library.DocumentLibraryNanite;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.elasticsearch.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Pedro Queiroz
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class DocumentLibraryNaniteTest extends BaseNaniteTestCase {

	@Before
	public void setUp() {
		setUp(_documentLibraryNanite);
	}

	@ElasticsearchIndex(
		name = "document-libraries",
		resourcePath = "document_library_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_DOCUMENT,
		resourcePath = "analytics_events_document_channel_2.json"
	)
	@Test
	public void testDocumentLibraryMetrics() throws Exception {
		_documentLibraryNanite.run();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_document_library_info.json", this),
			_cerebroInfoElasticsearchInvoker.get("document-libraries"), false);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_DOCUMENT,
		resourcePath = "analytics_events_document_channel_1.json"
	)
	@Test
	public void testDocumentLibraryRatingsMetric() {
		_documentLibraryNanite.run();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get(
			"document-libraries");

		Assert.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assert.assertEquals(1, jsonObject.getInt("ratings"));
		Assert.assertEquals(0.6, jsonObject.getDouble("ratingsScore"), 0);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DocumentLibraryNanite _documentLibraryNanite;

}