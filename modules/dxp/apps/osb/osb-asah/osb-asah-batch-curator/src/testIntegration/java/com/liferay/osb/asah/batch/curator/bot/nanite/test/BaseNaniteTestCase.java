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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;

import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseNaniteTestCase {

	@Before
	public void setUp() throws Exception {
		dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	protected ElasticsearchInvoker dxpRawElasticsearchInvoker;
	protected ElasticsearchInvoker faroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}