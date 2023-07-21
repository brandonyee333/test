/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.display.context;

import com.liferay.image.gallery.display.kernel.display.context.IGDisplayContextFactory;
import com.liferay.image.gallery.display.kernel.display.context.IGViewFileVersionDisplayContext;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerList;
import com.liferay.osgi.service.tracker.collections.list.ServiceTrackerListFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.repository.model.FileVersion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Iván Zaera
 */
@Component(service = IGDisplayContextProvider.class)
public class IGDisplayContextProvider {

	public IGViewFileVersionDisplayContext
		getIGViewFileVersionActionsDisplayContext(
			HttpServletRequest request, HttpServletResponse response,
			FileShortcut fileShortcut) {

		try {
			IGViewFileVersionDisplayContext igViewFileVersionDisplayContext =
				new DefaultIGViewFileVersionDisplayContext(
					request, response, fileShortcut);

			if (fileShortcut == null) {
				return igViewFileVersionDisplayContext;
			}

			for (IGDisplayContextFactory igDisplayContextFactory :
					_igDisplayContextFactories) {

				igViewFileVersionDisplayContext =
					igDisplayContextFactory.getIGViewFileVersionDisplayContext(
						igViewFileVersionDisplayContext, request, response,
						fileShortcut);
			}

			return igViewFileVersionDisplayContext;
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	public IGViewFileVersionDisplayContext
		getIGViewFileVersionActionsDisplayContext(
			HttpServletRequest request, HttpServletResponse response,
			FileVersion fileVersion) {

		try {
			IGViewFileVersionDisplayContext igViewFileVersionDisplayContext =
				new DefaultIGViewFileVersionDisplayContext(
					request, response, fileVersion);

			if (fileVersion == null) {
				return igViewFileVersionDisplayContext;
			}

			for (IGDisplayContextFactory igDisplayContextFactory :
					_igDisplayContextFactories) {

				igViewFileVersionDisplayContext =
					igDisplayContextFactory.getIGViewFileVersionDisplayContext(
						igViewFileVersionDisplayContext, request, response,
						fileVersion);
			}

			return igViewFileVersionDisplayContext;
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_igDisplayContextFactories = ServiceTrackerListFactory.open(
			bundleContext, IGDisplayContextFactory.class);
	}

	@Deactivate
	protected void deactivate(BundleContext bundleContext) {
		_igDisplayContextFactories.close();
	}

	private ServiceTrackerList<IGDisplayContextFactory, IGDisplayContextFactory>
		_igDisplayContextFactories;

}