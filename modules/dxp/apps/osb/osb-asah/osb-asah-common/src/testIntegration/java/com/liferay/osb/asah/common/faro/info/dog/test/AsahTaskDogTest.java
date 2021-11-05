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

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public class AsahTaskDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void test() {
		_asahTaskDog.scheduleAsahTask("TestNanite", JSONUtil.put("foo", "bar"));

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks();

		Assertions.assertEquals(1, asahTasks.size(), asahTasks.toString());

		AsahTask asahTask = asahTasks.get(0);

		Assertions.assertEquals("TestNanite", asahTask.getClassName());

		JSONObject contextJSONObject = asahTask.getContextJSONObject();

		Assertions.assertEquals("bar", contextJSONObject.getString("foo"));
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

}