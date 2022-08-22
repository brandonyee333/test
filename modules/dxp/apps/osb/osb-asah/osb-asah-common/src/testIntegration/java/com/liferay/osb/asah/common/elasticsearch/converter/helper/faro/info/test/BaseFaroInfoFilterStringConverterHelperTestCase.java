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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.junit.jupiter.api.BeforeEach;

/**
 * @author Vishal Reddy
 */
public abstract class BaseFaroInfoFilterStringConverterHelperTestCase
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	public abstract FilterStringConverterHelper
		getFilterStringConverterHelper();

	@BeforeEach
	public void setUp() throws Exception {

		// TODO Add  collections

	}

	protected void testFilterString(
			String collection, String filterString,
			String... expectedIndividualIds)
		throws Exception {

		// TODO Assert;

	}

}