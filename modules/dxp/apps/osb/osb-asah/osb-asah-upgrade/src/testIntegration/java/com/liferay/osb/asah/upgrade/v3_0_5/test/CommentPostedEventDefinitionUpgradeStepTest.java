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
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.entity.EventDefinitionEventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventRepository;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v3_0_5.CommentPostedEventDefinitionUpgradeStep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
public class CommentPostedEventDefinitionUpgradeStepTest {

	@Test
	public void testUpgrade() throws Exception {
		_eventStorageDog.store(
			_createAnalyticsEvent("commentPosted", Collections.emptyMap()),
			"1");

		_commentPostedEventDefinitionUpgradeStep.upgrade("");

		Assert.assertNull(
			_eventDefinitionDog.fetchEventDefinitionByName("commentPosted"));

		EventDefinition postedEventDefinition = _getEventDefinition("posted");

		Assert.assertEquals(
			1, _eventDog.countEvents(postedEventDefinition.getId()));
	}

	@Test
	public void testUpgradeCommentPostedWithProperties() throws Exception {
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

		EventDefinition commentPostedEventDefinition = _getEventDefinition(
			"commentPosted");

		Long commentPostedEventDefinitionId =
			commentPostedEventDefinition.getId();

		Assert.assertNotNull(commentPostedEventDefinitionId);

		Assert.assertEquals(
			commentPostedEventDefinitionId, event.getEventDefinitionId());

		Assert.assertEquals(
			1, _eventDog.countEvents(commentPostedEventDefinitionId));

		EventDefinition postedEventDefinition = _getEventDefinition("posted");

		Assert.assertNotEquals(
			postedEventDefinition.getId(), event.getEventDefinitionId());

		Assert.assertEquals(
			0, _eventDog.countEvents(postedEventDefinition.getId()));

		_commentPostedEventDefinitionUpgradeStep.upgrade("");

		Optional<EventDefinition> commentPostedEventDefinitionOptional =
			_eventDefinitionRepository.findById(commentPostedEventDefinitionId);

		Assert.assertFalse(commentPostedEventDefinitionOptional.isPresent());

		Long eventId = event.getId();

		Assert.assertNotNull(eventId);

		Optional<Event> eventOptional = _eventRepository.findById(eventId);

		Assert.assertTrue(eventOptional.isPresent());

		event = eventOptional.get();

		postedEventDefinition = _getEventDefinition("posted");

		Assert.assertEquals(
			postedEventDefinition.getId(), event.getEventDefinitionId());

		_assertEventAttributeDefinition(
			commentPostedEventDefinitionId,
			new ArrayList<String>() {
				{
					add("className");
					add("classPK");
					add("commentId");
					add("text");
				}
			},
			postedEventDefinition.getId());
	}

	@Test
	public void testUpgradeNoCommentPosted() throws Exception {
		_eventStorageDog.store(
			_createAnalyticsEvent("posted", Collections.emptyMap()), "1");
		_eventStorageDog.store(
			_createAnalyticsEvent("posted", Collections.emptyMap()), "2");

		_commentPostedEventDefinitionUpgradeStep.upgrade("");

		EventDefinition eventDefinition = _getEventDefinition("posted");

		Assert.assertEquals("posted", eventDefinition.getDisplayName());
		Assert.assertEquals("posted", eventDefinition.getName());
		Assert.assertEquals(
			EventDefinition.Type.DEFAULT, eventDefinition.getType());
		Assert.assertTrue(eventDefinition.isHidden());
		Assert.assertEquals(2, _eventDog.countEvents(eventDefinition.getId()));
	}

	private void _assertEventAttributeDefinition(
		Long commentPostedEventDefinitionId, List<String> names,
		Long postedEventDefinitionId) {

		for (String name : names) {
			EventAttributeDefinition eventAttributeDefinition =
				_getEventAttributeDefinition(name);

			Set<EventDefinitionEventAttributeDefinition>
				eventDefinitionEventAttributeDefinitions =
					eventAttributeDefinition.
						getEventDefinitionEventAttributeDefinitions();

			boolean found = false;

			for (EventDefinitionEventAttributeDefinition
					eventDefinitionEventAttributeDefinition :
						eventDefinitionEventAttributeDefinitions) {

				Long eventDefinitionId =
					eventDefinitionEventAttributeDefinition.
						getEventDefinitionId();

				Assert.assertNotEquals(
					commentPostedEventDefinitionId, eventDefinitionId);

				if (eventDefinitionId.equals(postedEventDefinitionId)) {
					found = true;
				}
			}

			Assert.assertTrue(
				String.format(
					"Unable to find posted event definition for %s event " +
						"attribute definition",
					name),
				found);
		}
	}

	private AnalyticsEvent _createAnalyticsEvent(
		String eventId, Map<String, String> eventProperties) {

		AnalyticsEvent analyticsEvent = new AnalyticsEvent();

		analyticsEvent.setApplicationId("Blog");
		analyticsEvent.setChannelId("1");
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

	private EventAttributeDefinition _getEventAttributeDefinition(String name) {
		Optional<EventAttributeDefinition> eventAttributeDefinitionOptional =
			_eventAttributeDefinitionRepository.findByName(name);

		Assert.assertTrue(eventAttributeDefinitionOptional.isPresent());

		return eventAttributeDefinitionOptional.get();
	}

	private EventDefinition _getEventDefinition(String name) {
		Optional<EventDefinition> eventDefinitionOptional =
			_eventDefinitionRepository.findByName(name);

		Assert.assertTrue(eventDefinitionOptional.isPresent());

		return eventDefinitionOptional.get();
	}

	@Autowired
	private CommentPostedEventDefinitionUpgradeStep
		_commentPostedEventDefinitionUpgradeStep;

	@Autowired
	private EventAttributeDefinitionRepository
		_eventAttributeDefinitionRepository;

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