/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.persistence.upgrade;

import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.saml.persistence.upgrade.v1_0_0.UpgradeSamlSpAuthRequest;
import com.liferay.saml.persistence.upgrade.v1_0_0.UpgradeSamlSpMessage;

import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class SamlServiceUpgrade implements UpgradeStepRegistrator {

	public void register(Registry registry) {
		registry.register(
			"com.liferay.saml.persistence.service", "0.0.1", "1.0.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.saml.persistence.service", "1.0.0", "1.1.0",
			new com.liferay.saml.persistence.upgrade.v1_0_0.
				UpgradeSamlIdpSpSession(),
			new UpgradeSamlSpAuthRequest(), new UpgradeSamlSpMessage(),
			new com.liferay.saml.persistence.upgrade.v1_0_0.
				UpgradeSamlSpSession());

		registry.register(
			"com.liferay.saml.persistence.service", "1.1.0", "1.1.1",
			new com.liferay.saml.persistence.upgrade.v1_1_0.
				UpgradeSamlSpSession());

		registry.register(
			"com.liferay.saml.persistence.service", "1.1.1", "1.1.2",
			new com.liferay.saml.persistence.upgrade.v1_1_1.
				UpgradeSamlSpSession());

		registry.register(
			"com.liferay.saml.persistence.service", "1.1.2", "1.1.3",
			new com.liferay.saml.persistence.upgrade.v1_1_2.
				UpgradeSamlSpIdpConnection());

		registry.register(
			"com.liferay.saml.persistence.service", "1.1.3", "1.1.4",
			new com.liferay.saml.persistence.upgrade.v1_1_3.
				UpgradeClassNames());
	}

}