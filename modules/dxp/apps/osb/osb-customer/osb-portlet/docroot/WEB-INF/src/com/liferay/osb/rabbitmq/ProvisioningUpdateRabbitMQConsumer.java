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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class ProvisioningUpdateRabbitMQConsumer
	extends ProvisioningRabbitMQConsumer {

	public ProvisioningUpdateRabbitMQConsumer() {
	}

	protected void doParse(JSONObject jsonObject) throws PortalException {
		String salesforceOpportunityKey = jsonObject.getString(
			"_salesforceOpportunityKey");

		CorpProject corpProject = parseCorpProject(jsonObject);
		List<OrderEntry> orderEntries = parseOrderEntries(jsonObject);

		AccountEntry accountEntry = parseAccountEntry(
			jsonObject, corpProject, orderEntries);

		PartnerEntry partnerEntry = parsePartnerEntry(jsonObject);
		Address address = parseAddress(jsonObject);

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

				AccountEntryLocalServiceUtil.updateAccountEntryWithWorkflow(
					salesforceOpportunityKey, curAccountEntry,
					curAccountEntry.getPartnerEntry(),
					curAccountEntry.getAddress(), orderEntries, serviceContext);
			}
		}
		else {
			AccountEntryLocalServiceUtil.updateAccountEntryWithWorkflow(
				salesforceOpportunityKey, accountEntry, partnerEntry, address,
				orderEntries, serviceContext);
		}
	}

}