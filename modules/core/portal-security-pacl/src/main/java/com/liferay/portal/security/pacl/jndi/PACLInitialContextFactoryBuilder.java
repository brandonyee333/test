/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.jndi;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Hashtable;

import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;

/**
 * @author Brian Wing Shun Chan
 */
public class PACLInitialContextFactoryBuilder
	implements InitialContextFactoryBuilder {

	@Override
	public InitialContextFactory createInitialContextFactory(
		Hashtable<?, ?> environment) {

		if (_log.isDebugEnabled()) {
			_log.debug("Creating " + PACLInitialContextFactory.class.getName());
		}

		return new PACLInitialContextFactory(
			_initialContextFactoryBuilder, environment);
	}

	public void setInitialContextFactoryBuilder(
		InitialContextFactoryBuilder initialContextFactoryBuilder) {

		_initialContextFactoryBuilder = initialContextFactoryBuilder;
	}

	private InitialContextFactoryBuilder _initialContextFactoryBuilder;

	// This must not be static because of LPS-33404

	private final Log _log = LogFactoryUtil.getLog(
		PACLInitialContextFactoryBuilder.class.getName());

}