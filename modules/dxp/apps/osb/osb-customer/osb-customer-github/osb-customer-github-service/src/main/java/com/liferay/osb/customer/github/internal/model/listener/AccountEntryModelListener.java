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

package com.liferay.osb.customer.github.internal.model.listener;

import com.liferay.osb.customer.admin.constants.WorkflowConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.github.constants.GitHubConstants;
import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.osb.customer.github.service.CollaboratorLocalService;
import com.liferay.osb.customer.github.web.service.GitHubWebService;
import com.liferay.osb.customer.koroneiki.constants.ProductPurchaseConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountEntryModelListener extends BaseModelListener<AccountEntry> {

	@Override
	public void onAfterRemove(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			List<Collaborator> collaborators =
				_collaboratorLocalService.getCollaborators(
					accountEntry.getAccountEntryId());

			for (Collaborator collaborator : collaborators) {
				_collaboratorLocalService.deleteCollaborator(collaborator);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			List<ProductPurchase> productPurchases =
				_accountReader.getProductPurchases(
					accountEntry.getKoroneikiAccountKey());

			String state = _accountReader.getSubscriptionState(
				productPurchases);

			if ((accountEntry.getStatus() ==
					WorkflowConstants.STATUS_APPROVED) &&
				state.equals(ProductPurchaseConstants.STATE_ACTIVE)) {

				List<Collaborator> collaborators =
					_collaboratorLocalService.getCollaborators(
						accountEntry.getAccountEntryId());

				for (Collaborator collaborator : collaborators) {
					collaborator.setStatus(WorkflowConstants.STATUS_PENDING);

					JSONObject userJSONObject = _gitHubWebService.fetchUser(
						collaborator.getGitHubUserName());

					if (userJSONObject != null) {
						JSONObject jsonObject =
							_gitHubWebService.addCollaborator(
								collaborator.getGitHubUserName());

						if (jsonObject != null) {
							collaborator.setStatus(
								WorkflowConstants.STATUS_APPROVED);
						}

						_collaboratorLocalService.updateCollaborator(
							collaborator);
					}
					else {
						_collaboratorLocalService.deleteCollaborator(
							collaborator);
					}
				}
			}
			else if ((accountEntry.getStatus() ==
						WorkflowConstants.STATUS_CLOSED) ||
					 !state.equals(ProductPurchaseConstants.STATE_ACTIVE)) {

				List<Collaborator> collaborators =
					_collaboratorLocalService.getCollaborators(
						accountEntry.getAccountEntryId());

				for (Collaborator collaborator : collaborators) {
					List<Collaborator> userCollaborators =
						_collaboratorLocalService.getCollaborators(
							collaborator.getGitHubUserName(),
							GitHubConstants.STATUSES_ACTIVE);

					if ((userCollaborators.size() == 1) &&
						userCollaborators.contains(collaborator)) {

						_gitHubWebService.deleteCollaborator(
							collaborator.getGitHubUserName());
					}

					collaborator.setStatus(WorkflowConstants.STATUS_CLOSED);

					_collaboratorLocalService.updateCollaborator(collaborator);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ModelListenerException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountEntryModelListener.class);

	@Reference
	private AccountReader _accountReader;

	@Reference
	private CollaboratorLocalService _collaboratorLocalService;

	@Reference
	private GitHubWebService _gitHubWebService;

}