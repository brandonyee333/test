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
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.lcs.util.LCSPortletPreferencesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Igor Beslic
 */
public class DeregisterCommand implements Command {

	@Override
	public void execute(CommandMessage commandMessage) {
		boolean deregister = Boolean.valueOf(
			(String)commandMessage.get("deregister"));
		boolean invalidateToken = Boolean.valueOf(
			(String)commandMessage.get("invalidateToken"));

		if (_log.isDebugEnabled()) {
			StringBundler sb = new StringBundler(5);

			sb.append("Command message: {deregister=");
			sb.append(deregister);
			sb.append(", invalidateToken=");
			sb.append(invalidateToken);
			sb.append("}");

			_log.debug(sb.toString());
		}

		if (deregister || invalidateToken) {
			LCSPortletPreferencesUtil.removeCredentials();

			_lcsClusterEntryTokenAdvisor.deleteLCSCLusterEntryTokenFile();

			if (deregister) {
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

	private static final Log _log = LogFactoryUtil.getLog(
		DeregisterCommand.class);

	private KeyGenerator _keyGenerator;
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private LCSConnectionManager _lcsConnectionManager;

}