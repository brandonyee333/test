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

package com.liferay.osb.asah.upgrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@Profile("!test")
public class Upgrade implements CommandLineRunner {

	@Override
	public void run(String... args) {
		if (_upgradeProcessRunner != null) {
			_upgradeProcessRunner.run();
		}

		if (_verifyProcessRunner != null) {
			_verifyProcessRunner.run();
		}
	}

	@Autowired(required = false)
	private UpgradeProcessRunner _upgradeProcessRunner;

	@Autowired(required = false)
	private VerifyProcessRunner _verifyProcessRunner;

}