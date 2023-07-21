/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.loader.modules.extender.npm;

import java.util.Collection;

/**
 * @author Iván Zaera Avellón
 */
public interface NPMRegistry {

	public JSModule getJSModule(String identifier);

	public JSPackage getJSPackage(String identifier);

	public Collection<JSPackage> getJSPackages();

	public JSModule getResolvedJSModule(String identifier);

	public Collection<JSModule> getResolvedJSModules();

	public Collection<JSPackage> getResolvedJSPackages();

	public JSPackage resolveJSPackageDependency(
		JSPackageDependency jsPackageDependency);

}