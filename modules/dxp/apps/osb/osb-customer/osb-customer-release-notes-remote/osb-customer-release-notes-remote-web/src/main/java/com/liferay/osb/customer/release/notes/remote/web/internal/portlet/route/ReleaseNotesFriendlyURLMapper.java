/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.release.notes.remote.web.internal.portlet.route;

import com.liferay.osb.customer.release.notes.remote.web.internal.constants.ReleaseNotesPortletKeys;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Andrew Madrazo
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/release-notes-friendly-url-routes.xml",
		"javax.portlet.name=" + ReleaseNotesPortletKeys.RELEASE_NOTES
	},
	service = FriendlyURLMapper.class
)
public class ReleaseNotesFriendlyURLMapper extends DefaultFriendlyURLMapper {

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	private static final String _MAPPING = "release_notes";

}