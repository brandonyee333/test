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

package com.liferay.osb.asah.stream.curator.bot.nanite;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Shinn Lok
 */
public abstract class BaseNaniteTestCase {

	public void setUp(Nanite nanite) {
		ElasticsearchInvoker elasticsearchInvoker = Mockito.mock(
			ElasticsearchInvoker.class);

		ReflectionTestUtils.setField(
			nanite, "_faroInfoElasticsearchInvoker", elasticsearchInvoker);

		Mockito.when(
			elasticsearchInvoker.exists(
				ArgumentMatchers.eq("individuals"),
				ArgumentMatchers.isNull(String.class))
		).thenReturn(
			true
		);
	}

}