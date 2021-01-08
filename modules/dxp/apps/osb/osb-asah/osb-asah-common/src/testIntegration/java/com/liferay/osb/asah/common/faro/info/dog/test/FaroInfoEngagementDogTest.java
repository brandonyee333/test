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

package com.liferay.osb.asah.common.faro.info.dog.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoEngagementDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoEngagementDogTest extends BaseFaroInfoDogTestCase {

	@Test
	public void testDeleteScore() {
		String dayDateString = DateUtil.newDayDateString();

		for (int i = 0; i < 5; i++) {
			elasticsearchInvoker.add(
				"engagements",
				JSONUtil.put(
					"dateRecorded", dayDateString
				).put(
					"ownerId", "123"
				).put(
					"ownerType", "individual"
				));
		}

		_faroInfoEngagementDog.deleteScore(dayDateString, "123", "individual");

		Assert.assertFalse(
			elasticsearchInvoker.exists(
				"engagements",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("dateRecorded", dayDateString)
				).filter(
					QueryBuilders.termQuery("ownerId", "123")
				).filter(
					QueryBuilders.termQuery("ownerType", "individual")
				)));
	}

	@Test
	public void testSaveEngagement() {
		String dayDateString = DateUtil.newDayDateString();

		_faroInfoEngagementDog.saveEngagement(
			dayDateString, RandomTestUtil.randomEmailAddress(),
			JSONUtil.put(
				JSONUtil.putAll(
					RandomTestUtil.randomId(), RandomTestUtil.randomId())),
			RandomTestUtil.randomFullName(), "345", "individual", 0);

		Assert.assertEquals(
			1,
			elasticsearchInvoker.count(
				"engagements",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("dateRecorded", dayDateString)
				).filter(
					QueryBuilders.termQuery("ownerId", "345")
				).filter(
					QueryBuilders.termQuery("ownerType", "individual")
				).filter(
					QueryBuilders.termQuery("score", 0)
				)));

		_faroInfoEngagementDog.saveEngagement(
			dayDateString, RandomTestUtil.randomEmailAddress(),
			JSONUtil.put(
				JSONUtil.putAll(
					RandomTestUtil.randomId(), RandomTestUtil.randomId())),
			RandomTestUtil.randomFullName(), "345", "individual", 1);

		Assert.assertEquals(
			0,
			elasticsearchInvoker.count(
				"engagements",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("dateRecorded", dayDateString)
				).filter(
					QueryBuilders.termQuery("ownerId", "345")
				).filter(
					QueryBuilders.termQuery("ownerType", "individual")
				).filter(
					QueryBuilders.termQuery("score", 0)
				)));

		Assert.assertEquals(
			1,
			elasticsearchInvoker.count(
				"engagements",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("dateRecorded", dayDateString)
				).filter(
					QueryBuilders.termQuery("ownerId", "345")
				).filter(
					QueryBuilders.termQuery("ownerType", "individual")
				).filter(
					QueryBuilders.termQuery("score", 1)
				)));
	}

	@Autowired
	private FaroInfoEngagementDog _faroInfoEngagementDog;

}