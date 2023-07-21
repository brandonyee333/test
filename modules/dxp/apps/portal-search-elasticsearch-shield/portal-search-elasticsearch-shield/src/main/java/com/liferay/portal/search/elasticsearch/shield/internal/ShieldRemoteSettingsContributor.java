/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.shield.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.search.elasticsearch.settings.BaseSettingsContributor;
import com.liferay.portal.search.elasticsearch.settings.ClientSettingsHelper;
import com.liferay.portal.search.elasticsearch.settings.SettingsContributor;
import com.liferay.portal.search.elasticsearch.shield.configuration.ShieldConfiguration;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author André de Oliveira
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.shield.configuration.ShieldConfiguration",
	immediate = true, property = "operation.mode=REMOTE",
	service = SettingsContributor.class
)
public class ShieldRemoteSettingsContributor extends BaseSettingsContributor {

	public ShieldRemoteSettingsContributor() {
		super(1);
	}

	@Override
	public void populate(ClientSettingsHelper clientSettingsHelper) {
		if (!shieldConfiguration.requiresAuthentication()) {
			return;
		}

		clientSettingsHelper.addPlugin("org.elasticsearch.shield.ShieldPlugin");

		configureAuthentication(clientSettingsHelper);
		configureSSL(clientSettingsHelper);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		shieldConfiguration = ConfigurableUtil.createConfigurable(
			ShieldConfiguration.class, properties);
	}

	protected void configureAuthentication(
		ClientSettingsHelper clientSettingsHelper) {

		String user =
			shieldConfiguration.username() + ":" +
				shieldConfiguration.password();

		clientSettingsHelper.put("shield.user", user);
	}

	protected void configureSSL(ClientSettingsHelper clientSettingsHelper) {
		if (!shieldConfiguration.requiresSSL()) {
			return;
		}

		clientSettingsHelper.put("shield.http.ssl", "true");

		String sslKeystoreKeyPassword =
			shieldConfiguration.sslKeystoreKeyPassword();

		if (sslKeystoreKeyPassword != null) {
			clientSettingsHelper.put(
				"shield.ssl.keystore.key_password", sslKeystoreKeyPassword);
		}

		clientSettingsHelper.put(
			"shield.ssl.keystore.password",
			shieldConfiguration.sslKeystorePassword());
		clientSettingsHelper.put(
			"shield.ssl.keystore.path", shieldConfiguration.sslKeystorePath());

		String sslTruststorePassword =
			shieldConfiguration.sslTruststorePassword();
		String sslTruststorePath = shieldConfiguration.sslTruststorePath();

		if ((sslTruststorePath != null) && (sslTruststorePassword != null)) {
			clientSettingsHelper.put(
				"shield.ssl.truststore.password",
				shieldConfiguration.sslTruststorePassword());
			clientSettingsHelper.put(
				"shield.ssl.truststore.path",
				shieldConfiguration.sslTruststorePath());
		}

		clientSettingsHelper.put("shield.transport.ssl", "true");
	}

	protected volatile ShieldConfiguration shieldConfiguration;

}