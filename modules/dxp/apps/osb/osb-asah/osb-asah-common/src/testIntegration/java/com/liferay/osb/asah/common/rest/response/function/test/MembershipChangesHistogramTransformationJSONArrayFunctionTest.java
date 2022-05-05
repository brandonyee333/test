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

package com.liferay.osb.asah.common.rest.response.function.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.MembershipChangesHistogramTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.Collections;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Vishal Reddy
 */
public class MembershipChangesHistogramTransformationJSONArrayFunctionTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Disabled
	@RepositoryResource(
		repositoryClass = BQMembershipChangeRepository.class,
		resourcePath = "osbasahfaroinfo/bq_membership_changes.json"
	)
	@Test
	public void test() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new MembershipChangesHistogramTransformationJSONArrayFunction(true);

		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies" +
							"/expected_bq_membership_changes_histogram.json",
						this))),
			transformationJSONArrayFunction.apply(
				"membership-changes", "day", _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), "dateChanged",
				QueryBuilders.termQuery(
					"individualSegmentId", "346306743994746064")),
			false);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}