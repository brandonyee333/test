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

package com.liferay.osb.asah.stream.curator.bot.nanite.session;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.util.Map;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author André Miranda
 */
public class SessionNaniteTest extends BaseNaniteTestCase {

	@Test
	public void testSessionNanite() throws Exception {
		test(SessionNanite.class);
	}

	@Override
	protected void prepare(
			ElasticsearchInvoker elasticsearchInvoker, Nanite nanite,
			String fileName)
		throws Exception {

		Mockito.when(
			elasticsearchInvoker.fetch(
				Mockito.eq(nanite.getCollectionName()),
				Mockito.any(QueryBuilder.class), Mockito.anyString(),
				Mockito.anyString())
		).thenReturn(
			new JSONObject(
				ResourceUtil.readResourceToString(
					fileName + "info_old.json", nanite))
		);

		ReflectionTestUtils.setField(
			nanite, "_sessionUpdateScriptSource",
			ScriptUtil.loadScriptSource(
				SessionNanite.class, "session_update_script.painless"));
	}

	@Override
	protected void verify(
		ElasticsearchInvoker elasticsearchInvoker, Nanite nanite,
		String fileName) {

		ArgumentCaptor<Script> argumentCaptor = ArgumentCaptor.forClass(
			Script.class);

		Mockito.verify(
			elasticsearchInvoker, Mockito.times(1)
		).update(
			Mockito.eq(nanite.getCollectionName()), Mockito.anyString(),
			argumentCaptor.capture()
		);

		Script script = argumentCaptor.getValue();

		Map<String, Object> params = script.getParams();

		Assert.assertEquals(params.toString(), 8, params.size());
	}

}