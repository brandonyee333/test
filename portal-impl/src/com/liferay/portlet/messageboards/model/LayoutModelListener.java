/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.messageboards.model;

import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Layout;

/**
 * @author Eduardo García
 */
public class LayoutModelListener extends BaseModelListener<Layout> {

	@Override
	public void onAfterCreate(Layout layout) throws ModelListenerException {
		super.onAfterCreate(layout);
	}

	@Override
	public void onBeforeRemove(Layout layout) throws ModelListenerException {
		try {
			CommentManagerUtil.deleteDiscussion(
				Layout.class.getName(), layout.getPlid());
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}