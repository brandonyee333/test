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

import com.liferay.osb.model.AuditEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.base.AuditEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alan Zhang
 */
public class AuditEntryLocalServiceImpl extends AuditEntryLocalServiceBaseImpl {

	public AuditEntry addAuditEntry(
			long userId, String userName, Date createDate, long classNameId,
			long classPK, long auditSetId, long fieldClassNameId,
			long fieldClassPK, int action, int field, int visibility,
			String oldLabel, String oldValue, String newLabel, String newValue)
		throws PortalException {

		boolean i18n = isI18n(field);

		return addAuditEntry(
			userId, userName, createDate, classNameId, classPK, auditSetId,
			fieldClassNameId, fieldClassPK, action, field, visibility, oldLabel,
			oldValue, newLabel, newValue, i18n, true);
	}

	public AuditEntry addAuditEntry(
			long userId, String userName, Date createDate, long classNameId,
			long classPK, long auditSetId, long fieldClassNameId,
			long fieldClassPK, int action, int field, int visibility,
			String oldLabel, String oldValue, String newLabel, String newValue,
			boolean i18n, boolean trackChange)
		throws PortalException {

		long previousAuditEntryId = 0;

		if (trackChange) {
			AuditEntry previousAuditEntry = getLastAuditEntry(
				fieldClassNameId, fieldClassPK, field);

			if (previousAuditEntry != null) {
				previousAuditEntryId = previousAuditEntry.getAuditEntryId();

				if (Validator.isNull(oldLabel) && Validator.isNull(oldValue)) {
					oldLabel = previousAuditEntry.getNewLabel();
					oldValue = previousAuditEntry.getNewValue();
				}
			}
		}

		if (auditSetId <= 0) {
			auditSetId = getNextAuditSetId(classNameId, classPK);
		}

		long auditEntryId = counterLocalService.increment();

		AuditEntry auditEntry = auditEntryPersistence.create(auditEntryId);

		auditEntry.setUserId(userId);
		auditEntry.setUserName(userName);
		auditEntry.setCreateDate(createDate);
		auditEntry.setClassNameId(classNameId);
		auditEntry.setClassPK(classPK);
		auditEntry.setPreviousAuditEntryId(previousAuditEntryId);
		auditEntry.setAuditSetId(auditSetId);
		auditEntry.setFieldClassNameId(fieldClassNameId);
		auditEntry.setFieldClassPK(fieldClassPK);
		auditEntry.setAction(action);
		auditEntry.setField(field);
		auditEntry.setVisibility(visibility);
		auditEntry.setOldLabel(oldLabel);
		auditEntry.setOldValue(oldValue);
		auditEntry.setNewLabel(newLabel);
		auditEntry.setNewValue(newValue);
		auditEntry.setI18n(i18n);

		return auditEntryPersistence.update(auditEntry);
	}

	public List<AuditEntry> getAuditEntries(Date createDate, long classNameId) {
		return auditEntryPersistence.findByGtCD_C(createDate, classNameId);
	}

	public List<AuditEntry> getAuditEntries(
		long classNameId, long classPK, int[] visibilities) {

		return auditEntryPersistence.findByC_C_V(
			classNameId, classPK, visibilities, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public List<List<AuditEntry>> getAuditEntrySets(
		long classNameId, long classPK, int[] visibilities) {

		Map<Long, List<AuditEntry>> auditEntrySets = new LinkedHashMap<>();

		List<AuditEntry> auditEntries = getAuditEntries(
			classNameId, classPK, visibilities);

		for (AuditEntry auditEntry : auditEntries) {
			List<AuditEntry> auditEntrySet = auditEntrySets.get(
				auditEntry.getAuditSetId());

			if (auditEntrySet != null) {
				auditEntrySet.add(auditEntry);
			}
			else {
				auditEntrySet = new ArrayList<>();

				auditEntrySet.add(auditEntry);

				auditEntrySets.put(auditEntry.getAuditSetId(), auditEntrySet);
			}
		}

		return new ArrayList<>(auditEntrySets.values());
	}

	public AuditEntry getLastAuditEntry(
		long fieldClassNameId, long fieldClassPK, int field) {

		return auditEntryPersistence.fetchByFC_FC_F_Last(
			fieldClassNameId, fieldClassPK, field, null);
	}

	public AuditEntry getLastAuditEntry(
		long classNameId, long classPK, int field, int action) {

		return auditEntryPersistence.fetchByC_C_F_A_Last(
			classNameId, classPK, field, action, null);
	}

	public long getNextAuditSetId(long classNameId, long classPK) {
		StringBundler sb = new StringBundler(5);

		sb.append(AuditEntry.class.getName());
		sb.append(StringPool.POUND);
		sb.append(classNameId);
		sb.append(StringPool.POUND);
		sb.append(classPK);

		return counterLocalService.increment(sb.toString());
	}

	public long getNextAuditSetId(String className, long classPK) {
		long classNameId = classNameLocalService.getClassNameId(className);

		return getNextAuditSetId(classNameId, classPK);
	}

	protected boolean isI18n(int field) {
		if (ArrayUtil.contains(AuditEntryConstants.FIELDS_I18N, field)) {
			return true;
		}
		else {
			return false;
		}
	}

}