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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.DuplicatePartnerEntryCodeException;
import com.liferay.osb.exception.DuplicatePartnerEntryDossieraAccountKeyException;
import com.liferay.osb.exception.PartnerEntryCodeException;
import com.liferay.osb.exception.PartnerEntryDossieraAccountKeyException;
import com.liferay.osb.exception.PartnerEntryParentPartnerEntryException;
import com.liferay.osb.exception.RequiredPartnerEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.base.PartnerEntryLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class PartnerEntryLocalServiceImpl
	extends PartnerEntryLocalServiceBaseImpl {

	public PartnerEntry addPartnerEntry(
			long userId, long parentPartnerEntryId, String dossieraAccountKey,
			String code, String notes, long[] supportRegionIds)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(
			0, parentPartnerEntryId, dossieraAccountKey, code,
			WorkflowConstants.STATUS_APPROVED);

		long partnerEntryId = counterLocalService.increment();

		PartnerEntry partnerEntry = partnerEntryPersistence.create(
			partnerEntryId);

		partnerEntry.setCompanyId(OSBConstants.COMPANY_ID);
		partnerEntry.setUserId(user.getUserId());
		partnerEntry.setUserName(user.getFullName());
		partnerEntry.setCreateDate(now);
		partnerEntry.setModifiedUserId(user.getUserId());
		partnerEntry.setModifiedUserName(user.getFullName());
		partnerEntry.setModifiedDate(now);
		partnerEntry.setParentPartnerEntryId(parentPartnerEntryId);
		partnerEntry.setDossieraAccountKey(dossieraAccountKey);
		partnerEntry.setCode(code);
		partnerEntry.setNotes(notes);
		partnerEntry.setStatus(WorkflowConstants.STATUS_APPROVED);

		partnerEntryPersistence.update(partnerEntry);

		if (ArrayUtil.isNotEmpty(supportRegionIds)) {
			partnerEntryPersistence.addSupportRegions(
				partnerEntryId, supportRegionIds);
		}

		return partnerEntry;
	}

	@Override
	public PartnerEntry deletePartnerEntry(long partnerEntryId)
		throws PortalException {

		if (accountEntryPersistence.countByPartnerEntryId(partnerEntryId) > 0) {
			throw new RequiredPartnerEntryException(
				RequiredPartnerEntryException.REFERENCED_ACCOUNT_ENTRY);
		}

		if (partnerEntryPersistence.countByParentPartnerEntryId(
				partnerEntryId) > 0) {

			throw new RequiredPartnerEntryException(
				RequiredPartnerEntryException.REFERENCED_PARTNER_ENTRY);
		}

		if (partnerWorkerPersistence.countByPartnerEntryId(partnerEntryId) >
				0) {

			throw new RequiredPartnerEntryException(
				RequiredPartnerEntryException.REFERENCED_PARTNER_WORKER);
		}

		return partnerEntryPersistence.remove(partnerEntryId);
	}

	public PartnerEntry fetchPartnerEntry(String dossieraAccountKey) {
		return partnerEntryPersistence.fetchByDossieraAccountKey(
			dossieraAccountKey);
	}

	public List<PartnerEntry> getChildPartnerEntries(
		long partnerEntryId, boolean recursive) {

		List<PartnerEntry> childPartnerEntries =
			partnerEntryPersistence.findByParentPartnerEntryId(partnerEntryId);

		if (!recursive) {
			return childPartnerEntries;
		}

		List<PartnerEntry> partnerEntries = new ArrayList<>();

		for (PartnerEntry childPartnerEntry : childPartnerEntries) {
			partnerEntries.add(childPartnerEntry);
			partnerEntries.addAll(
				getChildPartnerEntries(
					childPartnerEntry.getPartnerEntryId(), recursive));
		}

		return partnerEntries;
	}

	public PartnerEntry getPartnerEntryByCode(String code)
		throws PortalException {

		return partnerEntryPersistence.findByCode(code);
	}

	public List<PartnerEntry> getUserPartnerEntries(
		long userId, int start, int end) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("accountEntryMembership", Long.valueOf(userId));

		return partnerEntryFinder.findByKeywords(null, params, start, end);
	}

	public List<PartnerEntry> search(
		String code, int[] statuses, LinkedHashMap<String, Object> params,
		boolean andOperator, int start, int end) {

		return partnerEntryFinder.findByC_S(
			code, statuses, params, andOperator, start, end);
	}

	public List<PartnerEntry> search(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end) {

		return partnerEntryFinder.findByKeywords(keywords, params, start, end);
	}

	public int searchCount(
		String code, int[] statuses, LinkedHashMap<String, Object> params,
		boolean andOperator) {

		return partnerEntryFinder.countByC_S(
			code, statuses, params, andOperator);
	}

	public int searchCount(
		String keywords, LinkedHashMap<String, Object> params) {

		return partnerEntryFinder.countByKeywords(keywords, params);
	}

	public PartnerEntry updatePartnerEntry(
			long userId, long partnerEntryId, String dossieraAccountKey,
			String code, String notes, int status, long[] supportRegionIds)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(partnerEntryId, 0, dossieraAccountKey, code, status);

		PartnerEntry partnerEntry = partnerEntryPersistence.findByPrimaryKey(
			partnerEntryId);

		String oldDossieraAccountKey = partnerEntry.getDossieraAccountKey();
		int oldStatus = partnerEntry.getStatus();

		partnerEntry.setModifiedUserId(userId);
		partnerEntry.setModifiedUserName(user.getFullName());
		partnerEntry.setModifiedDate(now);
		partnerEntry.setDossieraAccountKey(dossieraAccountKey);
		partnerEntry.setCode(code);
		partnerEntry.setNotes(notes);
		partnerEntry.setStatus(status);

		partnerEntryPersistence.update(partnerEntry);

		if (oldDossieraAccountKey != dossieraAccountKey) {
			partnerWorkerLocalService.syncPartnerWorkers(
				partnerEntryId, oldDossieraAccountKey, dossieraAccountKey);
		}

		if ((oldStatus != status) &&
			(status == WorkflowConstants.STATUS_INACTIVE)) {

			closePartnerEntry(user, partnerEntryId);
		}

		if (supportRegionIds != null) {
			partnerEntryPersistence.setSupportRegions(
				partnerEntryId, supportRegionIds);
		}

		return partnerEntry;
	}

	protected void closePartnerEntry(User user, long partnerEntryId)
		throws PortalException {

		List<AccountEntry> accountEntries =
			accountEntryPersistence.findByPartnerEntryId(partnerEntryId);

		for (AccountEntry accountEntry : accountEntries) {
			accountEntry.setModifiedUserId(user.getUserId());
			accountEntry.setModifiedUserName(user.getFullName());
			accountEntry.setModifiedDate(new Date());
			accountEntry.setPartnerEntryId(partnerEntryId);
			accountEntry.setPartnerManagedSupport(false);

			accountEntryPersistence.update(accountEntry);
		}

		List<PartnerEntry> childPartnerEntries =
			partnerEntryPersistence.findByParentPartnerEntryId(partnerEntryId);

		for (PartnerEntry childPartnerEntry : childPartnerEntries) {
			updatePartnerEntry(
				user.getUserId(), childPartnerEntry.getPartnerEntryId(),
				childPartnerEntry.getDossieraAccountKey(),
				childPartnerEntry.getCode(), childPartnerEntry.getNotes(),
				WorkflowConstants.STATUS_INACTIVE, null);
		}
	}

	protected void validate(
			long partnerEntryId, long parentPartnerEntryId,
			String dossieraAccountKey, String code, int status)
		throws PortalException {

		if (parentPartnerEntryId > 0) {
			if (partnerEntryId == parentPartnerEntryId) {
				throw new PartnerEntryParentPartnerEntryException();
			}

			PartnerEntry parentPartnerEntry =
				partnerEntryPersistence.findByPrimaryKey(parentPartnerEntryId);

			if (parentPartnerEntry == null) {
				throw new PartnerEntryParentPartnerEntryException();
			}
		}

		if (Validator.isNotNull(dossieraAccountKey)) {
			if (parentPartnerEntryId > 0) {
				throw new PartnerEntryParentPartnerEntryException();
			}

			PartnerEntry partnerEntry =
				partnerEntryPersistence.fetchByDossieraAccountKey(
					dossieraAccountKey);

			if ((partnerEntry != null) &&
				(partnerEntry.getPartnerEntryId() != partnerEntryId)) {

				throw new DuplicatePartnerEntryDossieraAccountKeyException();
			}
		}

		if (!remoteCorpEntryLocalService.hasCorpEntry(dossieraAccountKey)) {
			throw new PartnerEntryDossieraAccountKeyException();
		}

		if (Validator.isNull(code)) {
			throw new PartnerEntryCodeException();
		}

		PartnerEntry partnerEntry = partnerEntryPersistence.fetchByCode(code);

		if ((partnerEntry != null) &&
			(partnerEntry.getPartnerEntryId() != partnerEntryId)) {

			throw new DuplicatePartnerEntryCodeException();
		}
	}

}