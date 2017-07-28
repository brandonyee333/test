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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Amos Fong
 */
public class PartnerEntryImpl extends PartnerEntryBaseImpl {

	public PartnerEntryImpl() {
	}

	public List<AccountEntry> getAccountEntries() {
		return AccountEntryLocalServiceUtil.getPartnerAccountEntries(
			getPartnerEntryId());
	}

	public List<PartnerEntry> getChildPartnerEntries(boolean recursive) {
		return PartnerEntryLocalServiceUtil.getChildPartnerEntries(
			getPartnerEntryId(), recursive);
	}

	public PartnerEntry getParentPartnerEntry() throws PortalException {
		if (getParentPartnerEntryId() <= 0) {
			return null;
		}

		return PartnerEntryLocalServiceUtil.getPartnerEntry(
			getParentPartnerEntryId());
	}

	public List<PartnerWorker> getPartnerWorkers() {
		return PartnerWorkerLocalServiceUtil.getPartnerWorkers(
			getPartnerEntryId());
	}

	public String getStatusLabel() {
		return WorkflowConstants.getStatusLabel(getStatus());
	}

	public SupportRegion getSupportRegion() {
		List<SupportRegion> supportRegions =
			SupportRegionLocalServiceUtil.getPartnerEntrySupportRegions(
				getPartnerEntryId());

		if (!supportRegions.isEmpty()) {
			return supportRegions.get(0);
		}

		return null;
	}

	public long[] getSupportRegionIds() {
		List<SupportRegion> supportRegions =
			SupportRegionLocalServiceUtil.getPartnerEntrySupportRegions(
				getPartnerEntryId());

		long[] supportRegionIds = new long[supportRegions.size()];

		for (int i = 0; i < supportRegions.size(); i++) {
			SupportRegion supportRegion = supportRegions.get(i);

			supportRegionIds[i] = supportRegion.getSupportRegionId();
		}

		return supportRegionIds;
	}

}