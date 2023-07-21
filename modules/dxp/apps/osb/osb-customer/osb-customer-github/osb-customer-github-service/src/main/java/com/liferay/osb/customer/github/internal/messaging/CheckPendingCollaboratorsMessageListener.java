/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.internal.messaging;

import com.liferay.osb.customer.github.configuration.GitHubConfigurationValues;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, service = CheckPendingCollaboratorsMessageListener.class
)
public class CheckPendingCollaboratorsMessageListener
	extends BaseMessageListener {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		Trigger trigger = _triggerFactory.createTrigger(
			className, className, null, null, 12, TimeUnit.HOUR);

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

		List<Collaborator> collaborators =
			_collaboratorLocalService.getCollaborators(
				WorkflowConstants.STATUS_PENDING, 0, 50);

		for (Collaborator collaborator : collaborators) {
			JSONObject jsonObject = _gitHubWebService.addCollaborator(
				collaborator.getGitHubUserName());

			if (jsonObject == null) {
				break;
			}

			collaborator.setStatus(WorkflowConstants.STATUS_APPROVED);

			_collaboratorLocalService.updateCollaborator(collaborator);
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