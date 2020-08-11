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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.arm.URLArm;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;

import org.hamcrest.Matchers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Vishal Reddy
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class URLArmTest {

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetTotalKeywordViews() {
		Assert.assertEquals(
			4,
			_urlArm.getTotalKeywordViews(
				DateUtil.newDayDateString(),
				new ArrayList<String>() {
					{
						add("url1");
					}
				}));
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetTotalViews() {
		Assert.assertEquals(
			5, _urlArm.getTotalPageViews(DateUtil.newDayDateString()));
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetURLs() {
		Assert.assertThat(
			_urlArm.getURLs("hello"),
			Matchers.containsInAnyOrder("url1", "url2", "url3"));
	}

	@Autowired
	private URLArm _urlArm;

}