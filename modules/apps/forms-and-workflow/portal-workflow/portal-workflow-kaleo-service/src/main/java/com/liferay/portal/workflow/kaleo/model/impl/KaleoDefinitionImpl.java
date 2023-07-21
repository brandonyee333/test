/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.model.KaleoNode;
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalServiceUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoDefinitionImpl extends KaleoDefinitionBaseImpl {

	public KaleoDefinitionImpl() {
	}

	@Override
	public KaleoNode getKaleoStartNode() throws PortalException {
		return KaleoNodeLocalServiceUtil.getKaleoNode(getStartKaleoNodeId());
	}

	@Override
	public boolean hasIncompleteKaleoInstances() {
		int count = KaleoInstanceLocalServiceUtil.getKaleoInstancesCount(
			getKaleoDefinitionId(), false);

		if (count > 0) {
			return true;
		}

		return false;
	}

}