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

package com.liferay.osb.model.impl;

// import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
@JSON(strict = true)
public class OrderEntryImpl extends OrderEntryBaseImpl {

	public OrderEntryImpl() {
	}

	public AccountEntry getAccountEntry() throws PortalException {
		return AccountEntryLocalServiceUtil.getAccountEntry(
			getAccountEntryId());
	}

	@JSON
	public List<OfferingEntry> getOfferingEntries() {
		if (_offeringEntries == null) {
			_offeringEntries =
				OfferingEntryLocalServiceUtil.getOrderEntryOfferingEntries(
					getOrderEntryId());
		}

		return _offeringEntries;
	}

	public String getSalesforceOpportunityKey() {
		long classNameId = PortalUtil.getClassNameId(
			OrderEntry.class.getName());

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, getOrderEntryId(),
				ExternalIdMapperConstants.TYPE_SALESFORCE);

		if (!externalIdMappers.isEmpty()) {
			ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

			return externalIdMapper.getExternalId();
		}
		else {
			return StringPool.BLANK;
		}
	}

	public String getStatusLabel() {
		return WorkflowConstants.getStatusLabel(getStatus());
	}

	public void setOfferingEntries(List<OfferingEntry> offeringEntries) {
		_offeringEntries = offeringEntries;
	}

	private List<OfferingEntry> _offeringEntries;

}