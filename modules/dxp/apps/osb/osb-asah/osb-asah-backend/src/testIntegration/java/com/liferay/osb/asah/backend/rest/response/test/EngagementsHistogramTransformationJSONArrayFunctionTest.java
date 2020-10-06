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

package com.liferay.osb.asah.backend.rest.response.test;

import com.liferay.osb.asah.backend.rest.response.EngagementsHistogramTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.Collections;

import org.json.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@ElasticsearchIndex(
	name = "engagements", resourcePath = "engagements.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class EngagementsHistogramTransformationJSONArrayFunctionTest {

	@Test
	public void testWithIndividualSegmentId() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new EngagementsHistogramTransformationJSONArrayFunction(
				true, "123");

		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_engagements_histogram.json",
						this))),
			transformationJSONArrayFunction.apply(
				"engagements", "day", _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), "dateRecorded", null),
			true);
	}

	@Test
	public void testWithoutIndividualSegmentId() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new EngagementsHistogramTransformationJSONArrayFunction(true, null);

		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies" +
							"/expected_engagements_histogram_no_segment_id." +
								"json",
						this))),
			transformationJSONArrayFunction.apply(
				"engagements", "day", _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), "dateRecorded", null),
			true);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}