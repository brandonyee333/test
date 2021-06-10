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

package com.liferay.osb.customer.zendesk.message.subscriber;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.ProjectSolution;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ProjectSolutionLocalService;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkDomain;
import com.liferay.osb.koroneiki.phloem.rest.client.constants.ExternalLinkEntityName;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=dossiera.provisioning.create",
	service = DossieraMessageSubscriber.class
)
public class DossieraMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	protected void doReceive(JSONObject jsonObject) throws Exception {
		JSONObject projectJSONObject = jsonObject.getJSONObject("_project");

		String salesforceProjectKey = projectJSONObject.getString(
			"_salesforceProjectKey");

		if (Validator.isNull(salesforceProjectKey)) {
			return;
		}

		ProjectSolution projectSolution =
			_projectSolutionLocalService.fetchProjectSolution(
				salesforceProjectKey);

		String projectSolutionValue = projectJSONObject.getString(
			"_projectSolution");

		if ((projectSolution == null) &&
			Validator.isNull(projectSolutionValue)) {

			return;
		}

		if (projectSolution == null) {
			_projectSolutionLocalService.addProjectSolution(
				salesforceProjectKey, projectSolutionValue);
		}
		else {
			if (Validator.isNull(projectSolutionValue)) {
				_projectSolutionLocalService.deleteProjectSolution(
					salesforceProjectKey);
			}
			else {
				_projectSolutionLocalService.updateProjectSolution(
					salesforceProjectKey, projectSolutionValue);
			}
		}

		List<Account> accounts = _accountWebService.getAccounts(
			ExternalLinkDomain.SALESFORCE,
			ExternalLinkEntityName.SALESFORCE_PROJECT, salesforceProjectKey, 1,
			1000);

		for (Account account : accounts) {
			AccountEntry accountEntry =
				_accountEntryLocalService.fetchKoroneikiAccountEntry(
					account.getKey());

			if (accountEntry != null) {
				_accountSynchronizer.update(account, accountEntry);
			}
		}
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ProjectSolutionLocalService _projectSolutionLocalService;

}