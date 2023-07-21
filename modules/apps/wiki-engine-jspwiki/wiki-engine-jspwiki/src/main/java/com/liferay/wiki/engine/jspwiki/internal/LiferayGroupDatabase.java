/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.jspwiki.internal;

import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.auth.authorize.Group;
import com.ecyrd.jspwiki.auth.authorize.GroupDatabase;

import java.security.Principal;

import java.util.Properties;

/**
 * @author Jorge Ferrer
 */
public class LiferayGroupDatabase implements GroupDatabase {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void commit() {
	}

	@Override
	public void delete(Group group) {
	}

	@Override
	public Group[] groups() {
		return new Group[0];
	}

	@Override
	public void initialize(WikiEngine engine, Properties props) {
	}

	@Override
	public void save(Group group, Principal modifier) {
	}

}