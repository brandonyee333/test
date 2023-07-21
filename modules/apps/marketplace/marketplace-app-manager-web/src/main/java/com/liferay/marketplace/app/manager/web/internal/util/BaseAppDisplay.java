/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.app.manager.web.internal.util;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;

/**
 * @author Ryan Park
 */
public abstract class BaseAppDisplay implements AppDisplay {

	@Override
	public void addBundle(Bundle bundle) {
		_moduleGroupDisplays = null;

		_bundles.add(bundle);
	}

	@Override
	public int compareTo(AppDisplay appDisplay) {
		if (appDisplay == null) {
			return -1;
		}

		String title = getTitle();

		return title.compareToIgnoreCase(appDisplay.getTitle());
	}

	@Override
	public List<Bundle> getBundles() {
		return _bundles;
	}

	@Override
	public List<ModuleGroupDisplay> getModuleGroupDisplays() {
		if (_moduleGroupDisplays == null) {
			_moduleGroupDisplays =
				ModuleGroupDisplayFactoryUtil.getModuleGroupDisplays(this);
		}

		return _moduleGroupDisplays;
	}

	@Override
	public int getState() {
		List<Bundle> bundles = getBundles();

		if (bundles.isEmpty()) {
			return Bundle.UNINSTALLED;
		}

		int state = Bundle.ACTIVE;

		for (Bundle bundle : bundles) {
			if (BundleUtil.isFragment(bundle)) {
				continue;
			}

			int bundleState = bundle.getState();

			if (state > bundleState) {
				state = bundleState;
			}
		}

		return state;
	}

	@Override
	public boolean hasModuleGroups() {
		List<ModuleGroupDisplay> moduleGroupDisplays = getModuleGroupDisplays();

		if (moduleGroupDisplays.isEmpty()) {
			return false;
		}
		else if (moduleGroupDisplays.size() > 1) {
			return true;
		}

		ModuleGroupDisplay moduleGroupDisplay = moduleGroupDisplays.get(0);

		String title = moduleGroupDisplay.getTitle();

		if (title.equals(
				ModuleGroupDisplay.MODULE_GROUP_TITLE_INDEPENDENT_MODULES)) {

			return false;
		}

		return true;
	}

	private final List<Bundle> _bundles = new ArrayList<>();
	private List<ModuleGroupDisplay> _moduleGroupDisplays;

}