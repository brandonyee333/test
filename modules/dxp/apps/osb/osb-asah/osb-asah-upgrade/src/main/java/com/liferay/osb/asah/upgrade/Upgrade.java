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

import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@MonolithExclude
public class Upgrade implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Upgrade started");
		}

		_upgradeProcessRunner.run();

		if (_log.isInfoEnabled()) {
			_log.info("Upgrade finished");
		}
	}

	private static final Log _log = LogFactory.getLog(Upgrade.class);

	@Autowired
	private UpgradeProcessRunner _upgradeProcessRunner;

}