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

package com.liferay.lcs.security;

import com.liferay.portal.kernel.util.GetterUtil;

import java.security.KeyStore;

import java.util.Enumeration;

/**
 * @author Igor Beslic
 */
public class KeyStoreAdvisor {

	public String getKeyAlias(
			int buildNumber, String keyAlias, KeyStore keyStore)
		throws Exception {

		if (buildNumber == 0) {
			return keyAlias;
		}

		Enumeration<String> aliases = keyStore.aliases();

		int compatibleNumberSuffix = 0;

		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();

			if (alias.equals(keyAlias)) {
				continue;
			}

			if (!alias.startsWith(keyAlias)) {
				continue;
			}

			int aliasNumberSuffix = GetterUtil.getInteger(
				alias.substring(keyAlias.length()));

			if (buildNumber == aliasNumberSuffix) {
				return alias;
			}

			if (buildNumber < aliasNumberSuffix) {
				continue;
			}

			if ((buildNumber > aliasNumberSuffix) &&
				(compatibleNumberSuffix < aliasNumberSuffix)) {

				compatibleNumberSuffix = aliasNumberSuffix;

				continue;
			}
		}

		if (compatibleNumberSuffix == 0) {
			return keyAlias;
		}

		return keyAlias + compatibleNumberSuffix;
	}

	public String getLatestKeyAlias(String keyAlias, KeyStore keyStore)
		throws Exception {

		Enumeration<String> aliases = keyStore.aliases();

		int latestNumberSuffix = 0;

		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();

			if (alias.equals(keyAlias)) {
				continue;
			}

			if (!alias.startsWith(keyAlias)) {
				continue;
			}

			int aliasNumberSuffix = GetterUtil.getInteger(
				alias.substring(keyAlias.length()));

			if (latestNumberSuffix < aliasNumberSuffix) {
				latestNumberSuffix = aliasNumberSuffix;
			}
		}

		if (latestNumberSuffix == 0) {
			return keyAlias;
		}

		return keyAlias + latestNumberSuffix;
	}

}