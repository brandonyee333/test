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
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.function.UnsafeFunction;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;

import java.util.Collections;

import org.elasticsearch.index.query.QueryBuilder;

import org.json.JSONArray;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

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
				ArgumentMatchers.eq(nanite.getCollectionName()),
				ArgumentMatchers.any(QueryBuilder.class))
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

		if (ReflectionUtils.findField(clazz, "_faroInfoElasticsearchInvoker") !=
				null) {

			ReflectionTestUtils.setField(
				nanite, "_faroInfoElasticsearchInvoker", elasticsearchInvoker);
		}

		Mockito.when(
			elasticsearchInvoker.get(
				ArgumentMatchers.eq("individuals"),
				ArgumentMatchers.any(QueryBuilder.class))
		).thenReturn(
			new JSONArray()
		);

		if (nanite instanceof BaseNanite) {
			FaroInfoIndividualDog faroInfoIndividualDog = Mockito.mock(
				FaroInfoIndividualDog.class);

			ReflectionTestUtils.setField(
				nanite, "_faroInfoIndividualDog",
				Mockito.mock(FaroInfoIndividualDog.class));

			Mockito.when(
				elasticsearchInvoker.exists(
					ArgumentMatchers.eq("individuals"),
					ArgumentMatchers.isNull(String.class))
			).thenReturn(
				true
			);

			Mockito.when(
				faroInfoIndividualDog.getIndividualJSONObject(
					ArgumentMatchers.anyLong(), ArgumentMatchers.anyString())
			).thenReturn(
				null
			);
		}

		String fileName = getJSONFileName(clazz);

		MessageSubscriber messageSubscriber = Mockito.mock(
			MessageSubscriber.class);

		ReflectionTestUtils.setField(
			nanite, "_messageSubscriber", messageSubscriber);

		Mockito.when(
			messageSubscriber.pullMessages(
				ArgumentMatchers.anyInt(),
				ArgumentMatchers.any(UnsafeFunction.class))
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
			ArgumentMatchers.eq(nanite.getCollectionName()),
			argumentCaptor.capture()
		);

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				fileName + "info_new.json", nanite),
			argumentCaptor.getValue(), false);
	}

}