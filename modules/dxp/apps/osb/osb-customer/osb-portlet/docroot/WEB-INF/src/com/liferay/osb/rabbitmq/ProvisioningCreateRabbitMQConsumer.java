/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.rabbitmq;

import com.liferay.compat.portal.util.PortalUtil;
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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ProvisioningCreateRabbitMQConsumer
	extends ProvisioningRabbitMQConsumer {

	public ProvisioningCreateRabbitMQConsumer() {
	}

	protected void doParse(JSONObject jsonObject)
		throws PortalException, SystemException {

		String salesforceOpportunityStageName = jsonObject.getString(
			"_salesforceOpportunityStageName");

		if (!salesforceOpportunityStageName.equals(
				SalesforceConstants.OPPORTUNITY_STAGE_CLOSED_WON)) {

			throw new WorkflowException("Opportunity is not closed won.");
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