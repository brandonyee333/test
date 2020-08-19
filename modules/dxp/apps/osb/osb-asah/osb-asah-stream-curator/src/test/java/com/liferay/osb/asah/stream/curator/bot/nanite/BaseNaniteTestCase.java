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
import com.liferay.osb.asah.common.function.UnsafeFunction;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import java.util.Collections;

import org.elasticsearch.index.query.QueryBuilder;

import org.json.JSONArray;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Inácio Nery
 */
public abstract class BaseNaniteTestCase {

	protected String getJSONFileName(Class<? extends Nanite> clazz) {
		String className = clazz.getSimpleName();

		String fileName = className.substring(0, className.length() - 6);

		fileName = fileName.replaceAll("(.)(\\p{Upper})", "$1_$2");

		fileName = fileName.toLowerCase() + "_";

		return fileName;
	}

	protected void prepare(
			ElasticsearchInvoker elasticsearchInvoker, Nanite nanite,
			String fileName)
		throws Exception {

		Mockito.when(
			elasticsearchInvoker.get(
				Mockito.eq(nanite.getCollectionName()),
				Mockito.any(QueryBuilder.class))
		).thenReturn(
			new JSONArray(
				ResourceUtil.readResourceToString(
					fileName + "info_old.json", nanite))
		);
	}

	protected void test(Class<? extends Nanite> clazz) throws Exception {
		Nanite nanite = clazz.newInstance();

		ElasticsearchInvoker elasticsearchInvoker = Mockito.mock(
			ElasticsearchInvoker.class);

		ReflectionTestUtils.setField(
			nanite, "_cerebroInfoElasticsearchInvoker", elasticsearchInvoker);

		String fileName = getJSONFileName(clazz);

		MessageSubscriber messageSubscriber = Mockito.mock(
			MessageSubscriber.class);

		ReflectionTestUtils.setField(
			nanite, "_messageSubscriber", messageSubscriber);

		Mockito.when(
			messageSubscriber.pullMessages(
				Mockito.anyInt(), Mockito.any(UnsafeFunction.class))
		).thenReturn(
			AnalyticsEvent.toAnalyticsEvents(
				ResourceUtil.readResourceToString(
					fileName + "raw.json", nanite)),
			Collections.emptyList()
		);

		prepare(elasticsearchInvoker, nanite, fileName);

		nanite.run();

		verify(elasticsearchInvoker, nanite, fileName);
	}

	protected void verify(
			ElasticsearchInvoker elasticsearchInvoker, Nanite nanite,
			String fileName)
		throws Exception {

		ArgumentCaptor<JSONArray> argumentCaptor = ArgumentCaptor.forClass(
			JSONArray.class);

		Mockito.verify(
			elasticsearchInvoker, Mockito.times(1)
		).save(
			Mockito.eq(nanite.getCollectionName()), argumentCaptor.capture()
		);

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				fileName + "info_new.json", nanite),
			argumentCaptor.getValue(), false);
	}

}