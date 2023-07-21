/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.blogs.model;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;

/**
 * @author Eduardo García
 */
public class GroupModelListener extends BaseModelListener<Group> {

	@Override
	public void onBeforeRemove(Group group) throws ModelListenerException {
		try {
			SubscriptionLocalServiceUtil.deleteSubscriptions(
				group.getCompanyId(), BlogsEntry.class.getName(),
				group.getGroupId());
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}