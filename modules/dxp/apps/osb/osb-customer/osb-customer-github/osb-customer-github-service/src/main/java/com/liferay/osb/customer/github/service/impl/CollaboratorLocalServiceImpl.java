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

package com.liferay.osb.customer.github.service.impl;

import com.liferay.osb.customer.github.exception.CollaboratorEmailAddressException;
import com.liferay.osb.customer.github.exception.CollaboratorFullNameException;
import com.liferay.osb.customer.github.exception.CollaboratorGitHubUserNameException;
import com.liferay.osb.customer.github.exception.DuplicateCollaboratorException;
import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.osb.customer.github.service.base.CollaboratorLocalServiceBaseImpl;
import com.liferay.osb.customer.github.web.service.GitHubWebService;
import com.liferay.osb.exception.RequiredAccountEntryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;

/**
 * @author Jenny Chen
 */
public class CollaboratorLocalServiceImpl
	extends CollaboratorLocalServiceBaseImpl {

	@Override
	public Collaborator addCollaborator(
			long userId, long accountEntryId, String emailAddress,
			String fullName, String gitHubUserName)
		throws PortalException {

		validate(accountEntryId, emailAddress, fullName, gitHubUserName);

		long collaboratorId = counterLocalService.increment();

		Collaborator collaborator = collaboratorPersistence.create(
			collaboratorId);

		collaborator.setUserId(userId);
		collaborator.setCreateDate(new Date());
		collaborator.setAccountEntryId(accountEntryId);
		collaborator.setEmailAddress(emailAddress);
		collaborator.setFullName(fullName);
		collaborator.setGitHubUserName(gitHubUserName);
		collaborator.setStatus(WorkflowConstants.STATUS_PENDING);

		JSONObject jsonObject = _gitHubWebService.addCollaborator(
			gitHubUserName);

		if (jsonObject != null) {
			collaborator.setStatus(WorkflowConstants.STATUS_APPROVED);
		}

		return collaboratorPersistence.update(collaborator);
	}

	@Override
	public Collaborator deleteCollaborator(Collaborator collaborator)
		throws PortalException {

		List<Collaborator> collaborators =
			collaboratorPersistence.findByGitHubUserName(
				collaborator.getGitHubUserName());

		if (collaborators.size() == 1) {
			_gitHubWebService.deleteCollaborator(
				collaborator.getGitHubUserName());
		}

		return collaboratorPersistence.remove(collaborator);
	}

	@Override
	public Collaborator deleteCollaborator(long collaboratorId)
		throws PortalException {

		Collaborator collaborator = getCollaborator(collaboratorId);

		return deleteCollaborator(collaborator);
	}

	public List<Collaborator> getCollaborators(long accountEntryId) {
		return collaboratorPersistence.findByAccountEntryId(accountEntryId);
	}

	protected void validate(
			long accountEntryId, String emailAddress, String fullName,
			String gitHubUserName)
		throws PortalException {

		if (accountEntryId <= 0) {
			throw new RequiredAccountEntryException();
		}

		if (Validator.isNull(emailAddress)) {
			throw new CollaboratorEmailAddressException();
		}

		if (Validator.isNull(fullName)) {
			throw new CollaboratorFullNameException();
		}

		if (Validator.isNull(gitHubUserName)) {
			throw new CollaboratorGitHubUserNameException();
		}

		Collaborator collaborator = collaboratorPersistence.fetchByAEI_GHUN(
			accountEntryId, gitHubUserName);

		if (collaborator != null) {
			throw new DuplicateCollaboratorException();
		}
	}

	@ServiceReference(type = GitHubWebService.class)
	private GitHubWebService _gitHubWebService;

}