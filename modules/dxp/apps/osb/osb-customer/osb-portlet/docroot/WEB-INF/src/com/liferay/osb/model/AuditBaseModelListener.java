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

package com.liferay.osb.model;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.osb.downloads.portlet.DownloadsPortlet;
import com.liferay.osb.securitypatch.portlet.SecurityPatchPortlet;
import com.liferay.osb.service.AuditActionLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.security.audit.AuditEvent;

import java.util.Map;

/**
 * @author Amos Fong
 */
public class AuditBaseModelListener<T extends BaseModel<T>>
	extends BaseModelListener<T> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		updateAuditAction(
			PortalUtil.getClassNameId(associationClassName),
			GetterUtil.getLong(associationClassPK), GetterUtil.getLong(classPK),
			AuditActionConstants.ACTION_UPDATE);
	}

	@Override
	public void onAfterCreate(T model) throws ModelListenerException {
		if (updateModel(model)) {
			updateAuditAction(model, AuditActionConstants.ACTION_UPDATE);
		}
	}

	@Override
	public void onAfterRemove(T model) throws ModelListenerException {
		updateAuditAction(model, AuditActionConstants.ACTION_REMOVE);
	}

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		updateAuditAction(
			PortalUtil.getClassNameId(associationClassName),
			GetterUtil.getLong(associationClassPK), GetterUtil.getLong(classPK),
			AuditActionConstants.ACTION_REMOVE);
	}

	@Override
	public void onAfterUpdate(T model) throws ModelListenerException {
		if (updateModel(model)) {
			updateAuditAction(model, AuditActionConstants.ACTION_UPDATE);
		}
	}

	protected void updateAuditAction(
		long classNameId, long classPK, long mappingClassPK, int action) {

		try {
			AuditActionLocalServiceUtil.updateAuditAction(
				classNameId, classPK, mappingClassPK, action);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	protected void updateAuditAction(T model, int action) {
		long classNameId = PortalUtil.getClassNameId(model.getModelClassName());
		long classPK = GetterUtil.getLong(model.getPrimaryKeyObj());

		updateAuditAction(classNameId, classPK, 0, action);
	}

	protected boolean updateModel(T model) {
		String modelClassName = model.getModelClassName();

		if (modelClassName.equals(AuditEvent.class.getName())) {
			Map<String, Object> attributes = model.getModelAttributes();

			String className = (String)attributes.get("className");

			if (ArrayUtil.contains(_AUDIT_EVENT_CLASS_NAMES, className)) {
				return true;
			}

			return false;
		}
		else if (modelClassName.equals(ExpandoValue.class.getName())) {
			Map<String, Object> attributes = model.getModelAttributes();

			long columnId = (Long)attributes.get("columnId");

			if (ArrayUtil.contains(_EXPANDO_COLUMN_IDS, columnId)) {
				return true;
			}

			return false;
		}

		return true;
	}

	private static final String[] _AUDIT_EVENT_CLASS_NAMES = {
		BlogsEntry.class.getName(), DownloadsPortlet.class.getName(),
		SecurityPatchPortlet.class.getName()
	};

	private static final long[] _EXPANDO_COLUMN_IDS = {
		OSBConstants.EXPANDO_OSB_COMPANY_COLUMN_ID,
		OSBConstants.EXPANDO_OSB_COUNTRY_COLUMN_ID
	};

	private static final Log _log = LogFactoryUtil.getLog(
		AuditBaseModelListener.class);

}