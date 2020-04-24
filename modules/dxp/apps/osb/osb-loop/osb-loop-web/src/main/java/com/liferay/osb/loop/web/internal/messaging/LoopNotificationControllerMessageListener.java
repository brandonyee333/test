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

package com.liferay.osb.loop.web.internal.messaging;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.BaseAlloyControllerImpl;
import com.liferay.alloy.mvc.MockAlloyControllerImpl;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.web.internal.util.LoopEmailNotificationUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;

/**
 * @author Timothy Bell
 */
public class LoopNotificationControllerMessageListener
	extends BaseMessageListener {

	public static LoopNotificationControllerMessageListener getInstance(
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
		ThemeDisplay themeDisplay = (ThemeDisplay)message.get("themeDisplay");

		AssetEntrySet assetEntrySet = (AssetEntrySet)message.get(
			"assetEntrySet");

		String userStringIds = GetterUtil.getString(message.get("userIds"));

		long[] userIds = GetterUtil.getLongValues(userStringIds.split(","));

		int notificationType = GetterUtil.getInteger(
			message.get("notificationType"));

		LoopEmailNotificationUtil.sendEmailNotifications(
			themeDisplay, assetEntrySet, ListUtil.toList(userIds),
			notificationType);
	}

	private static final LoopNotificationControllerMessageListener _instance =
		new LoopNotificationControllerMessageListener();

	private AlloyController _alloyController;

}