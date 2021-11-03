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

package com.liferay.osb.asah.stream.curator.bot.nanite.form.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.form.FormNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.test.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class FormNaniteTest extends BaseNaniteTestCase {

	@ElasticsearchIndex(
		name = "forms", resourcePath = "form_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_FORM,
		resourcePath = "analytics_events_form_channel_2.json"
	)
	@Test
	public void testFormAbandonmentsCount1() throws Exception {
		runNanite();

		JSONObject jsonObject = _cerebroInfoElasticsearchInvoker.get(
			"forms", "1");

		Assert.assertEquals(0, jsonObject.getInt("abandonments"));
	}

	@ElasticsearchIndex(
		name = "forms", resourcePath = "form_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_FORM,
		resourcePath = "analytics_events_form_channel_2.json"
	)
	@Test
	public void testFormAbandonmentsCount2() throws Exception {
		runNanite();

		JSONObject jsonObject = _cerebroInfoElasticsearchInvoker.get(
			"forms", "1");

		Assert.assertEquals(1, jsonObject.getInt("abandonments"));
	}

	@ElasticsearchIndex(
		name = "forms", resourcePath = "form_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_FORM,
		resourcePath = "analytics_events_form_channel_1.json"
	)
	@Test
	public void testFormMetrics() throws Exception {
		runNanite();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/expected_form_info.json", this),
			_cerebroInfoElasticsearchInvoker.get("forms"), false);
	}

	@Override
	protected Nanite getNanite() {
		return _formNanite;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FormNanite _formNanite;

}