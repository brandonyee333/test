/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.service.impl;

import com.liferay.osb.customer.admin.constants.WorkflowConstants;
import com.liferay.osb.customer.admin.exception.RequiredAccountEntryException;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.github.constants.GitHubConstants;
import com.liferay.osb.customer.github.exception.CollaboratorEmailAddressException;
import com.liferay.osb.customer.github.exception.CollaboratorFullNameException;
import com.liferay.osb.customer.github.exception.CollaboratorGitHubUserNameException;
import com.liferay.osb.customer.github.exception.DuplicateCollaboratorException;
import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.osb.customer.github.service.base.CollaboratorLocalServiceBaseImpl;
import com.liferay.osb.customer.github.web.service.GitHubWebService;
import com.liferay.osb.customer.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
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

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			accountEntryId);

		try {
			List<ProductPurchase> productPurchases =
				_accountReader.getProductPurchases(
					accountEntry.getKoroneikiAccountKey());

			String state = _accountReader.getSubscriptionState(
				productPurchases);

			if ((accountEntry.getStatus() == WorkflowConstants.STATUS_CLOSED) ||
				!state.equals(ProductPurchaseConstants.STATE_ACTIVE)) {

				_gitHubWebService.getUser(gitHubUserName);

				collaborator.setStatus(WorkflowConstants.STATUS_CLOSED);
			}
			else {
				JSONObject jsonObject = _gitHubWebService.addCollaborator(
					gitHubUserName);

				if (jsonObject != null) {
					collaborator.setStatus(WorkflowConstants.STATUS_APPROVED);
				}
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		return collaboratorPersistence.update(collaborator);
	}

	@Override
	public Collaborator deleteCollaborator(Collaborator collaborator)
		throws PortalException {

		List<Collaborator> collaborators = getCollaborators(
			collaborator.getGitHubUserName(), GitHubConstants.STATUSES_ACTIVE);

		if ((collaborators.size() == 1) &&
			collaborators.contains(collaborator)) {

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

	public List<Collaborator> getCollaborators(int status, int start, int end) {
		return collaboratorPersistence.findByStatus(status, start, end);
	}

	public List<Collaborator> getCollaborators(long accountEntryId) {
		return collaboratorPersistence.findByAccountEntryId(accountEntryId);
	}

	public List<Collaborator> getCollaborators(String gitHubUserName) {
		return collaboratorPersistence.findByGitHubUserName(gitHubUserName);
	}

	public List<Collaborator> getCollaborators(
		String gitHubUserName, int[] statuses) {

		return collaboratorPersistence.findByGHUN_S(gitHubUserName, statuses);
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

	@ServiceReference(type = AccountEntryLocalService.class)
	private AccountEntryLocalService _accountEntryLocalService;

	@ServiceReference(type = AccountReader.class)
	private AccountReader _accountReader;

	@ServiceReference(type = GitHubWebService.class)
	private GitHubWebService _gitHubWebService;

}