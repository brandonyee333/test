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

package com.liferay.osb.asah.upgrade.v3_0_5;

import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class VoteEventDefinitionUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		EventDefinition voteEventDefinitionLowerCase =
			_eventDefinitionDog.fetchEventDefinitionByName("vote");

		EventDefinition voteEventDefinitionUpperCase =
			_eventDefinitionDog.fetchEventDefinitionByName("VOTE");

		if (voteEventDefinitionUpperCase == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"No VOTE events found. Renaming vote event definition to " +
						"VOTE.");
			}

			_updateNames(voteEventDefinitionLowerCase, "VOTE");

			_eventDefinitionRepository.save(voteEventDefinitionLowerCase);
		}
		else {
			if (_eventDog.countEvents(voteEventDefinitionLowerCase.getId()) >
					0) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Found both VOTE and vote events. Swapping " +
							"definition types.");
				}

				voteEventDefinitionUpperCase.setType(
					EventDefinition.Type.DEFAULT);
				voteEventDefinitionLowerCase.setBlocked(false);

				_eventDefinitionRepository.save(voteEventDefinitionUpperCase);

				voteEventDefinitionLowerCase.setType(
					EventDefinition.Type.CUSTOM);

				_eventDefinitionRepository.save(voteEventDefinitionLowerCase);
			}
			else {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Found VOTE events only. Removing vote event " +
							"definition.");
				}

				_eventDefinitionDog.deleteEventDefinitionById(
					voteEventDefinitionLowerCase.getId());

				if (voteEventDefinitionUpperCase.isBlocked()) {
					voteEventDefinitionUpperCase.setBlocked(false);
					voteEventDefinitionUpperCase.setBlockedLastSeenDate(null);
					voteEventDefinitionUpperCase.setBlockedLastSeenURL(null);
					voteEventDefinitionUpperCase.setBlockedReasonType(null);
					voteEventDefinitionUpperCase.setDisplayName(
						_getDisplayName(
							"VOTE", voteEventDefinitionUpperCase.getId()));
				}
				else {
					_updateNames(voteEventDefinitionUpperCase, "VOTE");
				}

				voteEventDefinitionUpperCase.setHidden(
					voteEventDefinitionLowerCase.isHidden());
				voteEventDefinitionUpperCase.setType(
					EventDefinition.Type.DEFAULT);

				_eventDefinitionRepository.save(voteEventDefinitionUpperCase);

				_namedParameterJdbcTemplate.batchUpdate(
					_SQL_DELETE_EVENT_DEFINITION_EVENT_ATTRIBUTE_DEFINITION,
					SqlParameterSourceUtils.createBatch(
						Collections.singletonList(
							Collections.singletonMap(
								"eventDefinitionId",
								voteEventDefinitionLowerCase.getId()))));
			}
		}
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

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
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

	private static final Log _log = LogFactory.getLog(
		CommentPostedEventDefinitionUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@Autowired
	private EventDog _eventDog;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

}