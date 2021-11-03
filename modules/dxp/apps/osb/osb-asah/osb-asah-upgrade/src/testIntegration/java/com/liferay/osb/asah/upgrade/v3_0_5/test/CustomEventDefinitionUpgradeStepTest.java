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
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.dog.EventStorageDog;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v3_0_5.CustomEventDefinitionUpgradeStep;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class CustomEventDefinitionUpgradeStepTest {

	@SQLResource(resourcePath = "custom_event_definition_upgrade_step_test.sql")
	@Test
	public void testUpgrade() throws Exception {
		_eventStorageDog.store(
			_createAnalyticsEvent("commentPosted", Collections.emptyMap()),
			"1");
		_eventStorageDog.store(
			_createAnalyticsEvent("VOTE", Collections.emptyMap()), "1");

		EventDefinition voteEventDefinition = _getEventDefinition("VOTE");

		Assert.assertEquals(
			EventDefinition.Type.CUSTOM, voteEventDefinition.getType());

		_customEventDefinitionUpgradeStep.upgrade("");

		Assert.assertNull(
			_eventDefinitionDog.fetchEventDefinitionByName("commentPosted"));

		EventDefinition postedEventDefinition = _getEventDefinition("posted");

		Assert.assertEquals(
			1, _eventDog.countEvents(postedEventDefinition.getId()));

		Assert.assertNull(
			_eventDefinitionDog.fetchEventDefinitionByName("vote"));

		voteEventDefinition = _getEventDefinition("VOTE");

		Assert.assertEquals(
			EventDefinition.Type.DEFAULT, voteEventDefinition.getType());

		Assert.assertEquals(
			1, _eventDog.countEvents(voteEventDefinition.getId()));
	}

	@SQLResource(resourcePath = "custom_event_definition_upgrade_step_test.sql")
	@Test
	public void testUpgradeCommentPosted() throws Exception {
		Event event = _eventStorageDog.store(
			_createAnalyticsEvent(
				"commentPosted",
				new HashMap<String, String>() {
					{
						put("className", "com.liferay.blogs.model.BlogsEntry");
						put("classPK", "41543");
						put("commentId", "42658");
						put("text", "Test");
					}
				}),
			"1");

		EventDefinition postedEventDefinition = _getEventDefinition("posted");

		Assert.assertNotEquals(
			postedEventDefinition.getId(), event.getEventDefinitionId());

		Assert.assertEquals(
			0, _eventDog.countEvents(postedEventDefinition.getId()));

		_customEventDefinitionUpgradeStep.upgrade("");

		Long eventId = event.getId();

		Assert.assertNotNull(eventId);

		Optional<Event> eventOptional = _eventRepository.findById(eventId);

		Assert.assertTrue(eventOptional.isPresent());

		event = eventOptional.get();

		postedEventDefinition = _getEventDefinition("posted");

		Assert.assertEquals(
			postedEventDefinition.getId(), event.getEventDefinitionId());
	}

	@SQLResource(resourcePath = "custom_event_definition_upgrade_step_test.sql")
	@Test
	public void testUpgradeNoCommentPosted() throws Exception {
		_eventStorageDog.store(
			_createAnalyticsEvent("posted", Collections.emptyMap()), "1");
		_eventStorageDog.store(
			_createAnalyticsEvent("posted", Collections.emptyMap()), "2");

		_customEventDefinitionUpgradeStep.upgrade("");

		EventDefinition eventDefinition = _getEventDefinition("posted");

		Assert.assertEquals("posted", eventDefinition.getDisplayName());
		Assert.assertEquals("posted", eventDefinition.getName());
		Assert.assertEquals(
			EventDefinition.Type.DEFAULT, eventDefinition.getType());
		Assert.assertTrue(eventDefinition.isHidden());
		Assert.assertEquals(2, _eventDog.countEvents(eventDefinition.getId()));
	}

	@SQLResource(resourcePath = "custom_event_definition_upgrade_step_test.sql")
	@Test
	public void testUpgradeNoVote1() throws Exception {
		Assert.assertNotNull(
			_eventDefinitionDog.fetchEventDefinitionByName("vote"));

		Assert.assertNull(
			_eventDefinitionDog.fetchEventDefinitionByName("VOTE"));

		_customEventDefinitionUpgradeStep.upgrade("");

		Assert.assertNull(
			_eventDefinitionDog.fetchEventDefinitionByName("vote"));

		Assert.assertNotNull(
			_eventDefinitionDog.fetchEventDefinitionByName("VOTE"));
	}

	@SQLResource(resourcePath = "custom_event_definition_upgrade_step_test.sql")
	@Test
	public void testUpgradeNoVote2() throws Exception {
		_eventStorageDog.store(
			_createAnalyticsEvent("VOTE", Collections.emptyMap()), "1");

		Assert.assertNotNull(
			_eventDefinitionDog.fetchEventDefinitionByName("vote"));

		EventDefinition voteEventDefinition = _getEventDefinition("VOTE");

		Assert.assertEquals("VOTE (1)", voteEventDefinition.getDisplayName());

		_customEventDefinitionUpgradeStep.upgrade("");

		Assert.assertNull(
			_eventDefinitionDog.fetchEventDefinitionByName("vote"));

		voteEventDefinition = _getEventDefinition("VOTE");

		Assert.assertEquals(
			1, _eventDog.countEvents(voteEventDefinition.getId()));
		Assert.assertEquals("VOTE", voteEventDefinition.getDisplayName());
		Assert.assertEquals(
			EventDefinition.Type.DEFAULT, voteEventDefinition.getType());
	}

	@SQLResource(resourcePath = "custom_event_definition_upgrade_step_test.sql")
	@Test
	public void testUpgradePropertiesMaintained() throws Exception {
		_eventStorageDog.store(
			_createAnalyticsEvent("VOTE", Collections.emptyMap()), "1");

		EventDefinition eventDefinition = _getEventDefinition("VOTE");

		_eventDefinitionDog.blockEventDefinitions(
			Collections.singletonList(eventDefinition.getId()));

		_customEventDefinitionUpgradeStep.upgrade("");

		eventDefinition = _getEventDefinition("VOTE");

		Assert.assertEquals(
			EventDefinition.Type.DEFAULT, eventDefinition.getType());
		Assert.assertFalse(eventDefinition.isBlocked());
		Assert.assertTrue(eventDefinition.isHidden());
	}

	@SQLResource(resourcePath = "custom_event_definition_upgrade_step_test.sql")
	@Test
	public void testUpgradeWithVote() throws Exception {
		_eventStorageDog.store(
			_createAnalyticsEvent("VOTE", Collections.emptyMap()), "1");
		_eventStorageDog.store(
			_createAnalyticsEvent("vote", Collections.emptyMap()), "1");

		EventDefinition voteEventDefinition1 = _getEventDefinition("vote");

		Assert.assertEquals("vote", voteEventDefinition1.getDisplayName());
		Assert.assertEquals(
			EventDefinition.Type.DEFAULT, voteEventDefinition1.getType());

		EventDefinition voteEventDefinition2 = _getEventDefinition("VOTE");

		Assert.assertEquals("VOTE (1)", voteEventDefinition2.getDisplayName());
		Assert.assertEquals(
			EventDefinition.Type.CUSTOM, voteEventDefinition2.getType());

		_customEventDefinitionUpgradeStep.upgrade("");

		voteEventDefinition1 = _getEventDefinition("vote");

		Assert.assertEquals("vote", voteEventDefinition1.getDisplayName());
		Assert.assertEquals(
			EventDefinition.Type.CUSTOM, voteEventDefinition1.getType());

		voteEventDefinition2 = _getEventDefinition("VOTE");

		Assert.assertEquals("VOTE (1)", voteEventDefinition2.getDisplayName());
		Assert.assertEquals(
			EventDefinition.Type.DEFAULT, voteEventDefinition2.getType());
	}

	private AnalyticsEvent _createAnalyticsEvent(
		String eventId, Map<String, String> eventProperties) {

		AnalyticsEvent analyticsEvent = new AnalyticsEvent();

		analyticsEvent.setApplicationId("Blog");
		analyticsEvent.setChannelId("1");
		analyticsEvent.setClientIP("localhost");
		analyticsEvent.setContext(
			new HashMap<String, String>() {
				{
					put("canonicalUrl", "localhost:8080");
					put("contentLanguageId", "en-US");
					put("description", "");
					put("devicePixelRatio", "2");
					put("experienceId", "");
					put("groupId", "20121");
					put("keywords", "");
					put("languageId", "en-US");
					put("referrer", "localhost:8080");
					put("screenHeight", "932");
					put("screenWidth", "1340");
					put("timezoneOffset", "-7:00");
					put("title", "Test Title");
					put("url", "localhost:8080");
					put(
						"userAgent",
						"Mozilla/5.0 (X11; Fedora; Linux x86_64; rv:92.0) " +
							"Gecko/20100101 Firefox/92.0");
					put("variantId", "");
				}
			});
		analyticsEvent.setCreateDate(DateUtil.newDate());
		analyticsEvent.setDataSourceId("1");
		analyticsEvent.setEventDate(DateUtil.newDate());
		analyticsEvent.setEventId(eventId);
		analyticsEvent.setEventProperties(eventProperties);
		analyticsEvent.setId(String.valueOf(UUID.randomUUID()));
		analyticsEvent.setIndividualId("1");
		analyticsEvent.setKnownIndividual(true);
		analyticsEvent.setProjectId("test");
		analyticsEvent.setProjectTimeZoneId("UTC");
		analyticsEvent.setUserId(String.valueOf(UUID.randomUUID()));

		return analyticsEvent;
	}

	private EventDefinition _getEventDefinition(String name) {
		Optional<EventDefinition> eventDefinitionOptional =
			_eventDefinitionRepository.findByName(name);

		Assert.assertTrue(eventDefinitionOptional.isPresent());

		return eventDefinitionOptional.get();
	}

	@Autowired
	private CustomEventDefinitionUpgradeStep _customEventDefinitionUpgradeStep;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@Autowired
	private EventDog _eventDog;

	@Autowired
	private EventRepository _eventRepository;

	@Autowired
	private EventStorageDog _eventStorageDog;

}