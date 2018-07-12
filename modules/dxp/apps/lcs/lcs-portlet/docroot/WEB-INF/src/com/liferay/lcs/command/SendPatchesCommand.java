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

package com.liferay.lcs.command;

import com.liferay.lcs.messaging.SendPatchesCommandMessage;
import com.liferay.lcs.messaging.SendPatchesResponseMessage;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.LCSPatcherUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class SendPatchesCommand implements Command<SendPatchesCommandMessage> {

	@Override
	public void execute(SendPatchesCommandMessage sendPatchesCommandMessage) {
		if (!LCSPatcherUtil.isConfigured()) {
			if (_log.isWarnEnabled()) {
				_log.warn("Patcher is not configured. Unable to send patches.");
			}

			return;
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Executing send patches command");
		}

		String[] installedPatches = LCSPatcherUtil.getInstalledPatches();

		StringBundler sb = new StringBundler(installedPatches.length + 1);

		if (installedPatches.length > 0) {
			Arrays.sort(installedPatches);

			for (String patch : installedPatches) {
				sb.append(DigesterUtil.digestHex(Digester.MD5, patch));
			}
		}

		sb.append(
			DigesterUtil.digestHex(
				Digester.MD5, String.valueOf(LCSPatcherUtil.isConfigured())));
		sb.append(
			DigesterUtil.digestHex(
				Digester.MD5,
				String.valueOf(LCSPatcherUtil.getPatchingToolVersion())));

		String installedHashCode = DigesterUtil.digestHex(
			Digester.MD5, sb.toString());

		if (installedHashCode.equals(sendPatchesCommandMessage.getHashCode())) {
			if (_log.isTraceEnabled()) {
				_log.trace("Installed patches match available patches");
			}

			return;
		}

		Map<String, Integer> patchIdsStatuses = new HashMap<>();

		for (String patch : installedPatches) {
			patchIdsStatuses.put(patch, LCSConstants.PATCHES_INSTALLED);
		}

		SendPatchesResponseMessage sendPatchesResponseMessage =
			_getSendPatchesResponseMessage(
				sendPatchesCommandMessage, installedHashCode, patchIdsStatuses);

		try {
			_lcsConnectionManager.sendMessage(sendPatchesResponseMessage);
		}
		catch (Exception e) {
			_log.error("Unable to send installed patches statuses", e);
		}
	}

	public void setLCSConnectionManager(
		LCSConnectionManager lcsConnectionManager) {

		_lcsConnectionManager = lcsConnectionManager;
	}

	private SendPatchesResponseMessage _getSendPatchesResponseMessage(
		SendPatchesCommandMessage sendPatchesCommandMessage, String hashCode,
		Map<String, Integer> patchIdsStatuses) {

		SendPatchesResponseMessage sendPatchesResponseMessage =
			new SendPatchesResponseMessage();

		sendPatchesResponseMessage.setCreateTime(System.currentTimeMillis());
		sendPatchesResponseMessage.setFixedIssues(
			ListUtil.fromArray(LCSPatcherUtil.getFixedIssues()));
		sendPatchesResponseMessage.setHashCode(hashCode);
		sendPatchesResponseMessage.setKey(sendPatchesCommandMessage.getKey());
		sendPatchesResponseMessage.setPatchIdsStatuses(patchIdsStatuses);

		return sendPatchesResponseMessage;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SendPatchesCommand.class);

	private LCSConnectionManager _lcsConnectionManager;

}