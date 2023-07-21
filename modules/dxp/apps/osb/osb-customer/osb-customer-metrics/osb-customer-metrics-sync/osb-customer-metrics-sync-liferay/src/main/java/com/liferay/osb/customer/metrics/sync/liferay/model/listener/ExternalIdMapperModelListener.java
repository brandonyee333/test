/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model.listener;

import com.liferay.osb.customer.admin.model.ExternalIdMapper;
import com.liferay.osb.customer.metrics.impl.model.BaseMetricsModelListener;
import com.liferay.osb.customer.metrics.sync.liferay.constants.ClassNameConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelListener.class)
public class ExternalIdMapperModelListener
	extends BaseMetricsModelListener<ExternalIdMapper> {

	@Override
	public boolean ignoreUpdate(ExternalIdMapper externalIdMapper)
		throws ModelListenerException {

		if (ArrayUtil.contains(
				_OSB_CLASS_NAME_IDS, externalIdMapper.getClassNameId())) {

			return false;
		}

		return true;
	}

	private static final long[] _OSB_CLASS_NAME_IDS = {
		ClassNameConstants.ACCOUNT_ENTRY_CLASS_NAME_ID,
		ClassNameConstants.PARTNER_ENTRY_CLASS_NAME_ID
	};

}