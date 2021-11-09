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

import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CommentPostedEventDefinitionUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
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
		CommentPostedEventDefinitionUpgradeStep.class);

	@Autowired
	private DataSource _dataSource;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;

}