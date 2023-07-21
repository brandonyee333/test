/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.portlet.ControlPanelEntry;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

/**
 * @author     Brian Wing Shun Chan
 * @author     Shuyang Zhou
 * @author     Peter Fellwock
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class DefaultControlPanelEntryFactory {

	public static ControlPanelEntry getInstance() {
		return ServiceTrackerHolder._serviceTracker.getService();
	}

	private static class ServiceTrackerHolder {

		private static final ServiceTracker
			<ControlPanelEntry, ControlPanelEntry> _serviceTracker;

		static {
			Registry registry = RegistryUtil.getRegistry();

			Filter filter = registry.getFilter(
				"(&(!(javax.portlet.name=*))(objectClass=" +
					ControlPanelEntry.class.getName() + "))");

			_serviceTracker = registry.trackServices(filter);

			_serviceTracker.open();
		}

	}

}