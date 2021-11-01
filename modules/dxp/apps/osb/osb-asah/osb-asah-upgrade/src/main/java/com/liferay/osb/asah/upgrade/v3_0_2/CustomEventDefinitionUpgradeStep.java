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

package com.liferay.osb.asah.upgrade.v3_0_2;

import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class CustomEventDefinitionUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_mergeCommentPostedEventDefinition();

		_swapVoteEventDefinition();
	}

	private String _getDisplayName(String displayName, Long eventDefinitionId) {
		int nameCount = 0;
		String originalName = displayName;

		while (true) {
			EventDefinition eventDefinition =
				_eventDefinitionDog.fetchEventDefinitionByDisplayName(
					displayName);

			if ((eventDefinition == null) ||
				eventDefinitionId.equals(eventDefinition.getId())) {

				break;
			}

			displayName = String.format("%s (%d)", originalName, ++nameCount);
		}

		return displayName;
	}

	private List<Long> _getEventAttributeDefinitionIds(
		EventDefinition eventDefinition) {

		List<EventAttributeDefinition> eventAttributeDefinitions =
			_eventAttributeDefinitionDog.
				getEventAttributeDefinitionsByEventDefinitionId(
					eventDefinition.getId());

		Stream<EventAttributeDefinition> stream =
			eventAttributeDefinitions.stream();

		return stream.map(
			EventAttributeDefinition::getId
		).collect(
			Collectors.toList()
		);
	}

	private EventDefinition _getEventDefinition(String eventDefinitionName) {
		EventDefinition eventDefinition =
			_eventDefinitionDog.fetchEventDefinitionByName(eventDefinitionName);

		if (eventDefinition == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Unable to find %s event definition. Skipping " +
							"upgrade for %s",
						eventDefinitionName,
						ProjectIdThreadLocal.getProjectId()));
			}

			return null;
		}

		return eventDefinition;
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _mergeCommentPostedEventDefinition() {
		EventDefinition commentPostedEventDefinition = _getEventDefinition(
			"commentPosted");

		if (commentPostedEventDefinition == null) {
			return;
		}

		EventDefinition postedEventDefinition = _getEventDefinition("posted");

		if (postedEventDefinition == null) {
			return;
		}

		try {
			List<Map<String, Object>> eventDefinitionIds =
				new ArrayList<Map<String, Object>>() {
					{
						add(
							new HashMap<String, Object>() {
								{
									put(
										"commentPostedEventDefinition",
										commentPostedEventDefinition.getId());
									put(
										"globalEventAttributeDefinitionIds",
										_getEventAttributeDefinitionIds(
											postedEventDefinition));
									put(
										"postedEventDefinitionId",
										postedEventDefinition.getId());
								}
							});
					}
				};

			_namedParameterJdbcTemplate.batchUpdate(
				_SQL_UPDATE_EVENT_DEFINITION_EVENT_ATTRIBUTE_DEFINITION,
				SqlParameterSourceUtils.createBatch(
					eventDefinitionIds.toArray(new HashMap[0])));

			_namedParameterJdbcTemplate.batchUpdate(
				_SQL_UPDATE_EVENT,
				SqlParameterSourceUtils.createBatch(
					eventDefinitionIds.toArray(new HashMap[0])));

			_namedParameterJdbcTemplate.batchUpdate(
				_SQL_DELETE_EVENT_DEFINITION_EVENT_ATTRIBUTE_DEFINITION,
				SqlParameterSourceUtils.createBatch(
					Collections.singletonList(
						Collections.singletonMap(
							"eventDefinitionId",
							commentPostedEventDefinition.getId()))));

			_eventDefinitionDog.deleteEventDefinitionById(
				commentPostedEventDefinition.getId());
		}
		catch (Exception exception) {
			_log.error(
				"Unable to merge commentPosted events to posted events",
				exception);
		}
	}

	private void _swapVoteEventDefinition() {
		EventDefinition voteEventDefinition1 =
			_eventDefinitionDog.fetchEventDefinitionByName("vote");

		EventDefinition voteEventDefinition2 =
			_eventDefinitionDog.fetchEventDefinitionByName("VOTE");

		if (voteEventDefinition2 == null) {
			_updateNames(voteEventDefinition1, "VOTE");

			_eventDefinitionRepository.save(voteEventDefinition1);
		}
		else {
			if (_eventDog.countEvents(voteEventDefinition1.getId()) > 0) {
				voteEventDefinition2.setType(EventDefinition.Type.DEFAULT);
				voteEventDefinition1.setBlocked(false);

				_eventDefinitionRepository.save(voteEventDefinition2);

				voteEventDefinition1.setType(EventDefinition.Type.CUSTOM);

				_eventDefinitionRepository.save(voteEventDefinition1);
			}
			else {
				_eventDefinitionDog.deleteEventDefinitionById(
					voteEventDefinition1.getId());

				if (voteEventDefinition2.isBlocked()) {
					voteEventDefinition2.setBlocked(false);
					voteEventDefinition2.setBlocked(false);
					voteEventDefinition2.setBlockedLastSeenDate(null);
					voteEventDefinition2.setBlockedLastSeenURL(null);
					voteEventDefinition2.setBlockedReasonType(null);
					voteEventDefinition2.setDisplayName(
						_getDisplayName("VOTE", voteEventDefinition2.getId()));
				}
				else {
					_updateNames(voteEventDefinition2, "VOTE");
				}

				voteEventDefinition2.setHidden(voteEventDefinition1.isHidden());
				voteEventDefinition2.setType(EventDefinition.Type.DEFAULT);

				_eventDefinitionRepository.save(voteEventDefinition2);

				_namedParameterJdbcTemplate.batchUpdate(
					_SQL_DELETE_EVENT_DEFINITION_EVENT_ATTRIBUTE_DEFINITION,
					SqlParameterSourceUtils.createBatch(
						Collections.singletonList(
							Collections.singletonMap(
								"eventDefinitionId",
								voteEventDefinition1.getId()))));
			}
		}
	}

	private void _updateNames(EventDefinition eventDefinition, String newName) {
		String oldName = eventDefinition.getName();

		eventDefinition.setName(newName);

		String displayName = eventDefinition.getDisplayName();

		Pattern pattern = Pattern.compile(oldName + " \\(\\d+\\)");

		Matcher matcher = pattern.matcher(displayName);

		if (displayName.equals(oldName) || matcher.matches()) {
			eventDefinition.setDisplayName(
				_getDisplayName(newName, eventDefinition.getId()));
		}
	}

	private static final String
		_SQL_DELETE_EVENT_DEFINITION_EVENT_ATTRIBUTE_DEFINITION =
			"DELETE FROM eventdefinitioneventattributedefinition WHERE " +
				"eventDefinitionId = :eventDefinitionId";

	private static final String _SQL_UPDATE_EVENT =
		"UPDATE event SET eventDefinitionId = :postedEventDefinitionId WHERE " +
			"eventDefinitionId = :commentPostedEventDefinition";

	private static final String
		_SQL_UPDATE_EVENT_DEFINITION_EVENT_ATTRIBUTE_DEFINITION =
			"UPDATE eventdefinitioneventattributedefinition SET " +
				"eventDefinitionId = :postedEventDefinitionId WHERE " +
					"eventDefinitionId = :commentPostedEventDefinition AND " +
						"eventAttributeDefinitionId NOT IN " +
							"(:globalEventAttributeDefinitionIds)";

	private static final Log _log = LogFactory.getLog(
		CustomEventDefinitionUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@Autowired
	private EventDog _eventDog;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

}