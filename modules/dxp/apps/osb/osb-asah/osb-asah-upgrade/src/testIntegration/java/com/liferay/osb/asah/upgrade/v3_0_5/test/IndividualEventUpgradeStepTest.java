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

package com.liferay.osb.asah.upgrade.v3_0_5.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.ChannelDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_0_5.IndividualEventUpgradeStep;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Inácio Nery
 */
public class IndividualEventUpgradeStepTest
	implements OSBAsahTestExecutionListenersContext,
			   OSBAsahUpgradeSpringTestContext {

	@Test
	public void testUpgrade() throws Exception {
		DataSource dataSource = new DataSource();

		dataSource.setName("Test Data Source");
		dataSource.setProviderType("LIFERAY");

		dataSource = _dataSourceDog.addDataSource(dataSource);

		Channel channel = _channelDog.fetchDefaultChannel(dataSource.getId());

		Individual individual = _individualDog.addIndividual(
			channel.getId(), dataSource,
			DigestUtils.sha256Hex("test@liferay.com"), "123");

		Field field = new Field();

		field.setContext("demographics");
		field.setDataSourceId(dataSource.getId());
		field.setDataSourceName(dataSource.getName());
		field.setFieldType("Text");
		field.setName("email");
		field.setOwnerId(individual.getId());
		field.setOwnerType("individual");
		field.setSourceName("emailAddress");
		field.setValue("test@liferay.com");

		field = _fieldRepository.save(field);

		individual.setFields(Collections.singleton(field));

		individual = _individualDog.updateIndividual(individual);

		Event event1 = _addEvent(
			channel.getId(), dataSource.getId(), individual.getId());

		Event event2 = _addEvent(channel.getId(), dataSource.getId(), 456L);

		List<Event> events = _eventDog.searchEvents(
			channel.getId(), individual.getId(), null, 0, 10,
			TimeRange.LAST_24_HOURS);

		Assertions.assertEquals(Arrays.asList(event1), events);

		_individualEventUpgradeStep.upgrade("");

		event2.setIndividualId(individual.getId());

		events = _eventDog.searchEvents(
			channel.getId(), individual.getId(), null, 0, 10,
			TimeRange.LAST_24_HOURS);

		Assertions.assertEquals(Arrays.asList(event2, event1), events);
	}

	private Event _addEvent(
		Long channelId, Long dataSourceId, Long individualId) {

		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName("pageViewed");

		return _eventDog.addEvent(
			RandomTestUtil.randomUUID(), "Page", channelId,
			DateUtil.newDayDate(), dataSourceId, Collections.emptySet(),
			DateUtil.newDayDate(), eventDefinition.getId(), individualId,
			"sessionId", RandomTestUtil.randomUUID());
	}

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDog _eventDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private IndividualEventUpgradeStep _individualEventUpgradeStep;

}