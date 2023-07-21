/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.web.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;

/**
 * @author Lino Alves
 */
public class KaleoDraftDefinitionActivePredicateFilter
	implements PredicateFilter<KaleoDraftDefinition> {

	public KaleoDraftDefinitionActivePredicateFilter(
		int status, KaleoDefinitionLocalService kaleoDefinitionLocalService) {

		_status = status;
		_kaleoDefinitionLocalService = kaleoDefinitionLocalService;
	}

	@Override
	public boolean filter(KaleoDraftDefinition kaleoDraftDefinition) {
		if (_status == WorkflowConstants.STATUS_ANY) {
			return true;
		}

		try {
			KaleoDefinition kaleoDefinition =
				_kaleoDefinitionLocalService.getKaleoDefinition(
					kaleoDraftDefinition.getName(),
					kaleoDraftDefinition.getVersion(),
					ServiceContextThreadLocal.getServiceContext());

			if (_status == WorkflowConstants.STATUS_APPROVED) {
				return kaleoDefinition.isActive();
			}

			return !kaleoDefinition.isActive();
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			if (_status == WorkflowConstants.STATUS_DRAFT) {
				return true;
			}

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KaleoDraftDefinitionActivePredicateFilter.class);

	private final KaleoDefinitionLocalService _kaleoDefinitionLocalService;
	private final int _status;

}