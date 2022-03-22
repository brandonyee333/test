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

import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.function.UnsafeFunction;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilder;

import org.json.JSONArray;

import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
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

		return fileName.toLowerCase() + "_";
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

		Mockito.when(
			elasticsearchInvoker.get(
				ArgumentMatchers.eq("individuals"),
				ArgumentMatchers.any(QueryBuilder.class))
		).thenReturn(
			new JSONArray()
		);

		if (nanite instanceof BaseNanite) {
			IndividualDog individualDog = Mockito.mock(IndividualDog.class);

			ReflectionTestUtils.setField(
				nanite, "_individualDog", individualDog);

			Mockito.when(
				individualDog.existsById(ArgumentMatchers.anyLong())
			).thenReturn(
				true
			);

			Mockito.when(
				individualDog.fetchIndividual(
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

		List<AnalyticsEvent> analyticsEvents = AnalyticsEvent.toAnalyticsEvents(
			ResourceUtil.readResourceToString(fileName + "raw.json", nanite));

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		List<Message<AnalyticsEvent>> messages = stream.map(
			analyticsEvent -> new Message<>(
				RandomTestUtil.randomUUID(), Collections.emptyMap(),
				RandomTestUtil.randomUUID(), analyticsEvent)
		).collect(
			Collectors.toList()
		);

		Mockito.when(
			messageSubscriber.pullMessages(
				ArgumentMatchers.anyInt(),
				ArgumentMatchers.any(UnsafeFunction.class))
		).thenReturn(
			messages, Collections.emptyList()
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