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

package com.liferay.osb.customer.github.internal.messaging;

import com.liferay.osb.customer.github.configuration.GitHubConfigurationValues;
import com.liferay.osb.customer.github.constants.GitHubConstants;
import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.osb.customer.github.service.CollaboratorLocalService;
import com.liferay.osb.customer.github.web.service.GitHubWebService;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.SchedulerEntry;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactory;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, service = CheckDeletedCollaboratorsMessageListener.class
)
public class CheckDeletedCollaboratorsMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, 1, TimeUnit.WEEK);

		SchedulerEntry schedulerEntry = new SchedulerEntryImpl(
			className, trigger);

		_schedulerEngineHelper.register(
			this, schedulerEntry, DestinationNames.SCHEDULER_DISPATCH);
	}

	@Deactivate
	protected void deactivate() {
		_schedulerEngineHelper.unregister(this);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		if (!GitHubConfigurationValues.GITHUB_FEATURE_ENABLED) {
			return;
		}

		Set<String> excludeTeamMemberUserNames = new HashSet<>();

		for (String teamSlug :
				GitHubConfigurationValues.EXCLUDE_GITHUB_TEAM_SLUGS) {

			excludeTeamMemberUserNames.addAll(
				_gitHubWebService.getTeamMemberUserNames(teamSlug));
		}

		Set<String> gitHubCollaboratorUserNames =
			_gitHubWebService.getCollaboratorUserNames();

		for (String userName : gitHubCollaboratorUserNames) {
			if (userName.equals(
					GitHubConfigurationValues.
						REMOTE_REST_SERVICE_API_GITHUB_REPOSITORY_OWNER) ||
				ArrayUtil.contains(
					GitHubConfigurationValues.EXCLUDE_GITHUB_USERNAMES,
					userName)) {

				continue;
			}

			if (excludeTeamMemberUserNames.contains(userName)) {
				continue;
			}

			JSONObject jsonObject =
				_gitHubWebService.fetchLiferayOrganizationMembership(userName);

			if (jsonObject != null) {
				continue;
			}

			List<Collaborator> collaborators =
				_collaboratorLocalService.getCollaborators(
					userName, GitHubConstants.STATUSES_ACTIVE);

			if (collaborators.isEmpty()) {
				_gitHubWebService.deleteCollaborator(userName);
			}
		}
	}

	@Reference
	private CollaboratorLocalService _collaboratorLocalService;

	@Reference
	private GitHubWebService _gitHubWebService;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private TriggerFactory _triggerFactory;

}