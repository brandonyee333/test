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

	public List<AccountEntry> getPartnerManagedAccountEntries() {
		return AccountEntryLocalServiceUtil.getPartnerAccountEntries(
			getPartnerEntryId(), true);
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