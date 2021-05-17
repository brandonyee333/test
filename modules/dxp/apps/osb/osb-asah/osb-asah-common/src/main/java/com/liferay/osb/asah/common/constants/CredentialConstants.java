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

package com.liferay.osb.asah.common.constants;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Inácio Nery
 */
public class CredentialConstants {

	public static final String ELASTICSEARCH_KEY_PASSPHRASE;

	public static final String ELASTICSEARCH_PASSWORD;

	public static final String ELASTICSEARCH_USER;

	public static final String POSTGRESQL_DB;

	public static final String POSTGRESQL_PASSWORD;

	public static final String POSTGRESQL_USER;

	public static final String TRINO_USER;

	private static String _getCredential(
		String credentialKey, String defaultValue) {

		String credential = defaultValue;

		String overrideCredential = System.getenv(credentialKey);

		if (overrideCredential != null) {
			credential = overrideCredential;
		}

		if (_log.isInfoEnabled()) {
			if (credential != null) {
				_log.info(credentialKey + " found");
			}
			else {
				_log.info(credentialKey + " not found");
			}
		}

		return credential;
	}

	private static final Log _log = LogFactory.getLog(
		CredentialConstants.class);

	static {
		ELASTICSEARCH_KEY_PASSPHRASE = _getCredential(
			"ELASTICSEARCH_KEY_PASSPHRASE", null);
		ELASTICSEARCH_PASSWORD = _getCredential("ELASTICSEARCH_PASSWORD", null);
		ELASTICSEARCH_USER = _getCredential("ELASTICSEARCH_USER", null);
		POSTGRESQL_DB = _getCredential("POSTGRESQL_DB", "osbasah");
		POSTGRESQL_PASSWORD = _getCredential("POSTGRESQL_PASSWORD", "password");
		POSTGRESQL_USER = _getCredential("POSTGRESQL_USER", "postgres");
		TRINO_USER = _getCredential("TRINO_USER", "trino");
	}

}