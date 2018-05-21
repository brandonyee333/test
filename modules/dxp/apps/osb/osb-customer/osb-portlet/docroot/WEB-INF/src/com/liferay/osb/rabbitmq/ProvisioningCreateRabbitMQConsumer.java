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

package com.liferay.osb.rabbitmq;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.util.SalesforceConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ProvisioningCreateRabbitMQConsumer
	extends ProvisioningRabbitMQConsumer {

	public ProvisioningCreateRabbitMQConsumer() {
	}

	protected void doParse(JSONObject jsonObject) throws PortalException {
		String salesforceOpportunityStageName = jsonObject.getString(
			"_salesforceOpportunityStageName");

		if (!salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_WON)) {

			throw new WorkflowException("Opportunity is not closed won");
		}

		String salesforceOpportunityKey = jsonObject.getString(
			"_salesforceOpportunityKey");

		CorpProject corpProject = parseCorpProject(jsonObject);
		List<OrderEntry> orderEntries = parseOrderEntries(jsonObject);

		AccountEntry accountEntry = parseAccountEntry(
			jsonObject, corpProject, orderEntries);

		PartnerEntry partnerEntry = parsePartnerEntry(jsonObject);
		Address address = parseAddress(jsonObject);
		AccountWorker accountWorker = parseAccountWorker(
			jsonObject, accountEntry);
		ArrayList<User> users = parseUsers(jsonObject);

		ServiceContext serviceContext = createServiceContext(jsonObject);

		if (hasUnlimitedEnterpriseWide(orderEntries)) {
			long classNameId = PortalUtil.getClassNameId(AccountEntry.class);

			List<ExternalIdMapper> externalIdMappers =
				ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
					classNameId,
					ExternalIdMapperConstants.TYPE_EWSA_DOSSIERA_ACCOUNT_KEY,
					corpProject.getDossieraProjectKey());

			for (ExternalIdMapper externalIdMapper : externalIdMappers) {
				AccountEntry curAccountEntry =
					AccountEntryLocalServiceUtil.getAccountEntry(
						externalIdMapper.getClassPK());

				OrderEntryLocalServiceUtil.addOrderEntriesWithWorkflow(
					salesforceOpportunityKey, curAccountEntry, null,
					curAccountEntry.getPartnerEntry(),
					curAccountEntry.getAddress(), null, orderEntries,
					serviceContext);
			}
		}
		else if (accountEntry.getAccountEntryId() > 0) {
			OrderEntryLocalServiceUtil.addOrderEntriesWithWorkflow(
				salesforceOpportunityKey, accountEntry, corpProject,
				partnerEntry, address, accountWorker, orderEntries,
				serviceContext);
		}
		else {
			AccountEntryLocalServiceUtil.addAccountEntryWithWorkflow(
				salesforceOpportunityKey, accountEntry, corpProject,
				partnerEntry, address, accountWorker, orderEntries, users,
				serviceContext);
		}
	}

}