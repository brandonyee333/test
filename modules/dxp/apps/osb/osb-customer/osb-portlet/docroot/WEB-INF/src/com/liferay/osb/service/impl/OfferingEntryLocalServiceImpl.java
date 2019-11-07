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

import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.exception.OfferingEntryQuantityException;
import com.liferay.osb.exception.OfferingEntrySizingException;
import com.liferay.osb.exception.RequiredOfferingEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.service.base.OfferingEntryLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.comparator.OfferingEntrySupportEndDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;

import java.text.Format;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class OfferingEntryLocalServiceImpl
	extends OfferingEntryLocalServiceBaseImpl {

	public OfferingEntry addOfferingEntry(
			long userId, long accountEntryId, long orderEntryId,
			long productEntryId, long supportResponseId,
			String productDescription, int type, int version, boolean licenses,
			long licenseLifetime, long maxConcurrentUsers, long maxUsers,
			boolean supportTickets, long supportLifetime, int sizing,
			int quantity, int status)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
			orderEntryId);

		Date startDate = orderEntry.getStartDate();

		Date supportEndDate = new Date(
			startDate.getTime() + supportLifetime +
				(Time.YEAR * orderEntry.getRenewCount()));

		Date now = new Date();

		validate(accountEntryId, productEntryId, sizing, quantity);

		long offeringEntryId = counterLocalService.increment();

		OfferingEntry offeringEntry = offeringEntryPersistence.create(
			offeringEntryId);

		offeringEntry.setUserId(user.getUserId());
		offeringEntry.setUserName(user.getFullName());
		offeringEntry.setCreateDate(now);
		offeringEntry.setModifiedDate(now);
		offeringEntry.setAccountEntryId(accountEntryId);
		offeringEntry.setOrderEntryId(orderEntryId);
		offeringEntry.setProductEntryId(productEntryId);
		offeringEntry.setSupportResponseId(supportResponseId);
		offeringEntry.setProductDescription(productDescription);
		offeringEntry.setType(type);
		offeringEntry.setVersion(version);
		offeringEntry.setLicenses(licenses);
		offeringEntry.setLicenseLifetime(licenseLifetime);
		offeringEntry.setMaxConcurrentUsers(maxConcurrentUsers);
		offeringEntry.setMaxUsers(maxUsers);
		offeringEntry.setSupportTickets(supportTickets);
		offeringEntry.setSupportLifetime(supportLifetime);
		offeringEntry.setSupportEndDate(supportEndDate);
		offeringEntry.setSizing(sizing);
		offeringEntry.setQuantity(quantity);
		offeringEntry.setStatus(status);

		offeringEntryPersistence.update(offeringEntry);

		accountEntryLocalService.recalculateHighestSupportResponse(
			accountEntryId);

		accountEntryLocalService.updateSupportStatus(accountEntryId);

		return offeringEntry;
	}

	public void checkOfferingEntries() throws Exception {
		Date now = new Date();

		Set<Long> accountEntryIds = new HashSet<>();

		List<OfferingEntry> offeringEntries =
			offeringEntryFinder.findByU_AEI_PEI_T_S_SED(
				0, 0, new long[0],
				new int[] {OfferingEntryConstants.TYPE_REGULAR},
				new int[] {OfferingEntryConstants.STATUS_ACTIVE}, null, now,
				null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (OfferingEntry offeringEntry : offeringEntries) {
			if (isLatestActiveOfferingEntry(offeringEntry)) {
				Date supportEndDate = offeringEntry.getSupportEndDate();

				if ((supportEndDate.getTime() + (30 * Time.DAY)) >=
						now.getTime()) {

					continue;
				}
			}

			try {
				updateStatus(
					OSBConstants.USER_DEFAULT_USER_ID,
					offeringEntry.getOfferingEntryId(),
					OfferingEntryConstants.STATUS_ON_HOLD);

				if (accountEntryIds.contains(
						offeringEntry.getAccountEntryId())) {

					continue;
				}

				try {
					lcsSubscriptionEntryLocalService.syncToLCS(
						offeringEntry.getAccountEntryId());
				}
				catch (Exception e) {
					_log.error(
						"Unable to sync account entry " +
							offeringEntry.getAccountEntryId() + " to LCS",
						e);
				}

				accountEntryIds.add(offeringEntry.getAccountEntryId());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	@Override
	public OfferingEntry deleteOfferingEntry(long offeringEntryId)
		throws PortalException {

		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);

		return deleteOfferingEntry(offeringEntry);
	}

	@Override
	public OfferingEntry deleteOfferingEntry(OfferingEntry offeringEntry)
		throws PortalException {

		if (licenseKeyPersistence.countByOEI_C_A(
				offeringEntry.getOfferingEntryId(), false, true) > 0) {

			throw new RequiredOfferingEntryException();
		}

		offeringEntry = offeringEntryPersistence.remove(offeringEntry);

		accountEntryLocalService.recalculateHighestSupportResponse(
			offeringEntry.getAccountEntryId());

		accountEntryLocalService.updateSupportStatus(
			offeringEntry.getAccountEntryId());

		return offeringEntry;
	}

	public List<OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId) {

		return offeringEntryPersistence.findByAccountEntryId(accountEntryId);
	}

	public int getAccountEntryOfferingEntriesCount(long accountEntryId) {
		return offeringEntryPersistence.countByAccountEntryId(accountEntryId);
	}

	public List<OfferingEntry> getOrderEntryOfferingEntries(long orderEntryId) {
		return offeringEntryPersistence.findByOrderEntryId(orderEntryId);
	}

	public boolean hasActiveSupportOfferingEntry(
		long accountEntryId, boolean ticketSupport) {

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		if (ticketSupport) {
			params.put("validTicket", StringPool.BLANK);
		}

		int count = offeringEntryFinder.countByU_AEI_PEI_T_S_SED(
			0, accountEntryId, new long[0],
			new int[] {OfferingEntryConstants.TYPE_REGULAR},
			new int[] {OfferingEntryConstants.STATUS_ACTIVE}, null, null,
			params, true);

		if (count > 0) {
			return true;
		}

		return false;
	}

	public boolean hasActiveTrialOfferingEntry(long userId) {
		AccountEntry accountEntry =
			accountEntryLocalService.fetchUserTrialAccountEntry(userId);

		if (accountEntry != null) {
			Date now = new Date();

			List<OfferingEntry> offeringEntries =
				getAccountEntryOfferingEntries(
					accountEntry.getAccountEntryId());

			for (OfferingEntry offeringEntry : offeringEntries) {
				if (now.before(offeringEntry.getSupportEndDate())) {
					return true;
				}
			}
		}

		return false;
	}

	public List<OfferingEntry> search(
		long userId, long accountEntryId, int[] types, int[] statuses,
		int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		LinkedHashMap<String, Object> params, boolean andSearch, int start,
		int end, OrderByComparator obc) {

		Date supportEndDateGT = PortalUtil.getDate(
			supportEndDateGTMonth, supportEndDateGTDay, supportEndDateGTYear);
		Date supportEndDateLT = PortalUtil.getDate(
			supportEndDateLTMonth, supportEndDateLTDay, supportEndDateLTYear);

		return search(
			userId, accountEntryId, new long[0], types, statuses,
			supportEndDateGT, supportEndDateLT, params, andSearch, start, end,
			obc);
	}

	public List<OfferingEntry> search(
		long userId, long accountEntryId, long[] productEntryIds, int[] types,
		int[] statuses, Date supportEndDateGT, Date supportEndDateLT,
		LinkedHashMap<String, Object> params, boolean andSearch, int start,
		int end, OrderByComparator obc) {

		return offeringEntryFinder.findByU_AEI_PEI_T_S_SED(
			userId, accountEntryId, productEntryIds, types, statuses,
			supportEndDateGT, supportEndDateLT, params, andSearch, start, end,
			obc);
	}

	public int searchCount(
		long userId, long accountEntryId, int[] types, int[] statuses,
		int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		LinkedHashMap<String, Object> params, boolean andSearch) {

		Date supportEndDateGT = PortalUtil.getDate(
			supportEndDateGTMonth, supportEndDateGTDay, supportEndDateGTYear);
		Date supportEndDateLT = PortalUtil.getDate(
			supportEndDateLTMonth, supportEndDateLTDay, supportEndDateLTYear);

		return offeringEntryFinder.countByU_AEI_PEI_T_S_SED(
			userId, accountEntryId, new long[0], types, statuses,
			supportEndDateGT, supportEndDateLT, params, andSearch);
	}

	public OfferingEntry updateOfferingEntry(
			long userId, long offeringEntryId, long accountEntryId,
			long orderEntryId, long productEntryId, long supportResponseId,
			String productDescription, int type, int version, boolean licenses,
			long licenseLifetime, long maxConcurrentUsers, long maxUsers,
			boolean supportTickets, long supportLifetime, int sizing,
			int quantity)
		throws PortalException {

		OrderEntry orderEntry = orderEntryPersistence.findByPrimaryKey(
			orderEntryId);

		Date startDate = orderEntry.getStartDate();

		Date supportEndDate = new Date(
			startDate.getTime() + supportLifetime +
				(Time.YEAR * orderEntry.getRenewCount()));

		validate(accountEntryId, productEntryId, sizing, quantity);

		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);

		offeringEntry.setModifiedDate(new Date());
		offeringEntry.setAccountEntryId(accountEntryId);
		offeringEntry.setOrderEntryId(orderEntryId);
		offeringEntry.setProductEntryId(productEntryId);
		offeringEntry.setSupportResponseId(supportResponseId);
		offeringEntry.setProductDescription(productDescription);
		offeringEntry.setType(type);
		offeringEntry.setVersion(version);
		offeringEntry.setLicenses(licenses);
		offeringEntry.setLicenseLifetime(licenseLifetime);
		offeringEntry.setMaxConcurrentUsers(maxConcurrentUsers);
		offeringEntry.setMaxUsers(maxUsers);
		offeringEntry.setSupportTickets(supportTickets);
		offeringEntry.setSupportLifetime(supportLifetime);
		offeringEntry.setSupportEndDate(supportEndDate);
		offeringEntry.setSizing(sizing);
		offeringEntry.setQuantity(quantity);

		offeringEntryPersistence.update(offeringEntry);

		accountEntryLocalService.updateSupportStatus(
			offeringEntry.getAccountEntryId());

		return offeringEntry;
	}

	public OfferingEntry updateStatus(
			long userId, long offeringEntryId, int status)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);

		if (offeringEntry.getStatus() == status) {
			return offeringEntry;
		}

		offeringEntry.setStatus(status);

		offeringEntryPersistence.update(offeringEntry);

		accountEntryLocalService.recalculateHighestSupportResponse(
			offeringEntry.getAccountEntryId());

		accountEntryLocalService.updateSupportStatus(
			offeringEntry.getAccountEntryId());

		long classNameId = classNameLocalService.getClassNameId(
			AccountEntry.class.getName());
		long fieldClassNameId = classNameLocalService.getClassNameId(
			OfferingEntry.class.getName());

		StringBundler sb = new StringBundler(6);

		sb.append("ID: ");
		sb.append(offeringEntryId);
		sb.append(", Name: ");

		ProductEntry productEntry = productEntryLocalService.getProductEntry(
			offeringEntry.getProductEntryId());

		sb.append(productEntry.getName());

		sb.append(", Start Date: ");

		String startDate = _dateFormat.format(offeringEntry.getStartDate());

		sb.append(startDate);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), now, classNameId,
			offeringEntry.getAccountEntryId(), 0, fieldClassNameId,
			offeringEntryId, AuditEntryConstants.ACTION_UPDATE,
			AuditEntryConstants.FIELD_STATUS, VisibilityConstants.WORKERS,
			StringPool.BLANK, StringPool.BLANK,
			OfferingEntryConstants.getStatusLabel(status),
			String.valueOf(status), sb.toString());

		return offeringEntry;
	}

	protected boolean isLatestActiveOfferingEntry(OfferingEntry offeringEntry)
		throws PortalException {

		List<OfferingEntry> offeringEntries =
			offeringEntryFinder.findByU_AEI_PEI_T_S_SED(
				0, offeringEntry.getAccountEntryId(),
				new long[] {offeringEntry.getProductEntryId()},
				new int[] {offeringEntry.getType()},
				new int[] {OfferingEntryConstants.STATUS_ACTIVE}, null, null,
				null, true, 0, 1, new OfferingEntrySupportEndDateComparator());

		if (offeringEntries.isEmpty()) {
			return true;
		}

		OfferingEntry latestActiveOfferingEntry = offeringEntries.get(0);

		String key = latestActiveOfferingEntry.getKey();

		if (key.equals(offeringEntry.getKey())) {
			return true;
		}

		return false;
	}

	protected void validate(
			long accountEntryId, long productEntryId, int sizing, int quantity)
		throws PortalException {

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.getRedirectAccountEntryId() > 0) {
			throw new NoSuchAccountEntryException();
		}

		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			productEntryId);

		if ((productEntry.isDigitalEnterprise() || productEntry.isPortal()) &&
			(sizing <= 0)) {

			throw new OfferingEntrySizingException();
		}

		if (quantity <= 0) {
			throw new OfferingEntryQuantityException();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OfferingEntryLocalServiceImpl.class);

	private final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}