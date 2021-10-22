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

package com.liferay.osb.asah.upgrade.v3_0_1;

import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class EventDefinitionUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		for (String defaultEventDefinitionName : _defaultEventDefinitionNames) {
			Optional<EventDefinition> eventDefinitionOptional =
				_eventDefinitionRepository.findByName(
					defaultEventDefinitionName);

			if (eventDefinitionOptional.isPresent()) {
				EventDefinition eventDefinition = eventDefinitionOptional.get();

				if (!Objects.equals(
						EventDefinition.Type.DEFAULT,
						eventDefinition.getType())) {

					eventDefinition.setType(EventDefinition.Type.DEFAULT);

					_eventDefinitionRepository.save(eventDefinition);

					if (_log.isDebugEnabled()) {
						_log.debug(
							"Update event definition with name " +
								defaultEventDefinitionName);
					}
				}
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"There is no event definition with name " +
							defaultEventDefinitionName);
				}
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		EventDefinitionUpgradeStep.class);

	private final List<String> _defaultEventDefinitionNames = Arrays.asList(
		"assetClicked", "assetDepthReached", "assetDownloaded",
		"assetSubmitted", "assetViewed", "blogClicked", "blogDepthReached",
		"blogViewed", "ctaClicked", "documentDownloaded", "documentPreviewed",
		"fieldBlurred", "fieldFocused", "formSubmitted", "formViewed",
		"pageDepthReached", "pageLoaded", "pageRead", "pageUnloaded",
		"pageViewed", "posted", "shared", "tabBlurred", "tabFocused", "vote",
		"webContentClicked", "webContentViewed");

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

}