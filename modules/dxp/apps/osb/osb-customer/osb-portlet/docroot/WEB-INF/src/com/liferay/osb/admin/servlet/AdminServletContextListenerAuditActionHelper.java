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

package com.liferay.osb.admin.servlet;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.model.AuditBaseModelListener;
import com.liferay.osb.util.PortletPropsKeys;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Amos Fong
 */
public class AdminServletContextListenerAuditActionHelper {

	public static void setup() {
		String[] auditActionModels = PortletProps.getArray(
			PortletPropsKeys.AUDIT_ACTION_MODELS);

		for (String auditActionModel : auditActionModels) {
			int pos = auditActionModel.indexOf(StringPool.SLASH);

			String servletContext = auditActionModel.substring(0, pos);
			String modelClassName = auditActionModel.substring(pos + 1);

			BasePersistence persistence = getPersistence(
				servletContext, modelClassName);

			persistence.registerListener(new AuditBaseModelListener());
		}
	}

	protected static BasePersistence<?> getPersistence(
		String servletContext, String modelClassName) {

		int pos = modelClassName.lastIndexOf(CharPool.PERIOD);

		String entityName = modelClassName.substring(pos + 1);

		pos = modelClassName.lastIndexOf(".model.");

		StringBundler sb = new StringBundler(4);

		sb.append(modelClassName.substring(0, pos));
		sb.append(".service.persistence.");
		sb.append(entityName);
		sb.append("Persistence");

		if (Validator.isNull(servletContext)) {
			return (BasePersistence<?>)PortalBeanLocatorUtil.locate(
				sb.toString());
		}
		else {
			return (BasePersistence<?>)PortletBeanLocatorUtil.locate(
				servletContext, sb.toString());
		}
	}

}