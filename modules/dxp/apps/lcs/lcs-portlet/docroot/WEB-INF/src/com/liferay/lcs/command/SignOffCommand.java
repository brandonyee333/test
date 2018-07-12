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

import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.lcs.util.LCSPortletPreferencesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Igor Beslic
 */
public class SignOffCommand implements Command<SignOffCommandMessage> {

	@Override
	public void execute(SignOffCommandMessage signOffCommandMessage) {
		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler(5);

			sb.append("Command message: {deregister=");
			sb.append(signOffCommandMessage.isDeregister());
			sb.append(", invalidateToken=");
			sb.append(signOffCommandMessage.isInvalidateToken());
			sb.append("}");

			_log.debug(sb.toString());
		}

		if (signOffCommandMessage.isDeregister() ||
			signOffCommandMessage.isInvalidateToken()) {

			LCSPortletPreferencesUtil.removeCredentials();

			_lcsClusterEntryTokenAdvisor.deleteLCSCLusterEntryTokenFile();

			if (signOffCommandMessage.isDeregister()) {
				_keyGenerator.clearCache();
			}
		}

		_lcsConnectionManager.stop(true, true, false);

		if (_log.isDebugEnabled()) {
			_log.debug("Signed off server from LCS");
		}
	}

	public void setKeyGenerator(KeyGenerator keyGenerator) {
		_keyGenerator = keyGenerator;
	}

	public void setLCSClusterEntryTokenAdvisor(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
	}

	public void setLCSConnectionManager(
		LCSConnectionManager lcsConnectionManager) {

		_lcsConnectionManager = lcsConnectionManager;
	}

	private static final Log _log = LogFactoryUtil.getLog(SignOffCommand.class);

	private KeyGenerator _keyGenerator;
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private LCSConnectionManager _lcsConnectionManager;

}