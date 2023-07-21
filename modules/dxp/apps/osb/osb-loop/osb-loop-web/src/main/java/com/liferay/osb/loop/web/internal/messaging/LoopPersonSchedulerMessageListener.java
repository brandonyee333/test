/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.messaging;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.BaseAlloyControllerImpl;
import com.liferay.alloy.mvc.MockAlloyControllerImpl;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Timothy Bell
 */
public class LoopPersonSchedulerMessageListener extends BaseMessageListener {

	public static LoopPersonSchedulerMessageListener getInstance(
		AlloyController alloyController) {

		_instance.setAlloyController(
			new MockAlloyControllerImpl(
				(BaseAlloyControllerImpl)alloyController));

		return _instance;
	}

	public void setAlloyController(AlloyController alloyController) {
		_alloyController = alloyController;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		LoopPersonUtil.cleanDLFileEntries();
	}

	private static final LoopPersonSchedulerMessageListener _instance =
		new LoopPersonSchedulerMessageListener();

	private AlloyController _alloyController;

}