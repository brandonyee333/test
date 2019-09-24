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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.exception.CompressionException;
import com.liferay.lcs.client.internal.util.LCSPatcherUtil;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.platform.gateway.LCSGatewayException;
import com.liferay.lcs.messaging.SendPatchesCommandMessage;
import com.liferay.lcs.messaging.SendPatchesResponseMessage;
import com.liferay.lcs.util.LCSConstants;
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
 * @author Igor Beslic
 */
public class SendPatchesTask extends BaseTask {

	public SendPatchesTask(
		LCSGatewayClient lcsGatewayClient,
		SendPatchesCommandMessage sendPatchesCommandMessage) {

		_lcsGatewayClient = lcsGatewayClient;
		_sendPatchesCommandMessage = sendPatchesCommandMessage;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public void doRun() throws CompressionException, LCSGatewayException {
		if (!LCSPatcherUtil.isConfigured()) {
			_log.error(
				"Aborting patch information sending. The patching tool is " +
					"not configured.");

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

		if (installedHashCode.equals(
				_sendPatchesCommandMessage.getHashCode())) {

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting patch information sending. Installed patch " +
						"matches available patch.");
			}

			return;
		}

		Map<String, Integer> patchIdsStatuses = new HashMap<>();

		for (String patch : installedPatches) {
			patchIdsStatuses.put(patch, LCSConstants.PATCHES_INSTALLED);
		}

		SendPatchesResponseMessage sendPatchesResponseMessage =
			_getSendPatchesResponseMessage(
				installedHashCode, _sendPatchesCommandMessage.getKey(),
				patchIdsStatuses);

		_lcsGatewayClient.sendMessage(sendPatchesResponseMessage);
	}

	@Override
	public TaskType getTaskType() {
		return TaskType.COMMAND;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	private SendPatchesResponseMessage _getSendPatchesResponseMessage(
		String hashCode, String key, Map<String, Integer> patchIdsStatuses) {

		SendPatchesResponseMessage sendPatchesResponseMessage =
			new SendPatchesResponseMessage();

		sendPatchesResponseMessage.setCreateTime(System.currentTimeMillis());
		sendPatchesResponseMessage.setFixedIssues(
			ListUtil.fromArray(LCSPatcherUtil.getFixedIssues()));
		sendPatchesResponseMessage.setHashCode(hashCode);
		sendPatchesResponseMessage.setKey(key);
		sendPatchesResponseMessage.setPatchIdsStatuses(patchIdsStatuses);

		return sendPatchesResponseMessage;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SendPatchesTask.class);

	private final LCSGatewayClient _lcsGatewayClient;
	private final SendPatchesCommandMessage _sendPatchesCommandMessage;

}