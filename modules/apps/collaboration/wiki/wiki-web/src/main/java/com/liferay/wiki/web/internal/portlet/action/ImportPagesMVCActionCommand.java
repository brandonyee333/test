/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.NotificationThreadLocal;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ProgressTracker;
import com.liferay.portal.kernel.util.ProgressTrackerThreadLocal;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.exception.NoSuchNodeException;
import com.liferay.wiki.service.WikiNodeService;
import com.liferay.wiki.util.WikiCacheHelper;
import com.liferay.wiki.util.WikiCacheThreadLocal;

import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + WikiPortletKeys.WIKI_ADMIN,
		"mvc.command.name=/wiki/import_pages"
	},
	service = MVCActionCommand.class
)
public class ImportPagesMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			importPages(actionRequest, actionResponse);
		}
		catch (Exception e) {
			if (e instanceof NoSuchNodeException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());
			}
			else if (e instanceof PortalException) {
				SessionErrors.add(actionRequest, e.getClass());
			}
			else {
				throw new PortletException(e);
			}
		}
	}

	protected void importPages(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		String importProgressId = ParamUtil.getString(
			uploadPortletRequest, "importProgressId");

		ProgressTracker progressTracker = new ProgressTracker(importProgressId);

		ProgressTrackerThreadLocal.setProgressTracker(progressTracker);

		progressTracker.start(actionRequest);

		long nodeId = ParamUtil.getLong(uploadPortletRequest, "nodeId");
		String importer = ParamUtil.getString(uploadPortletRequest, "importer");

		InputStream[] inputStreams = new InputStream[_MAX_FILE_COUNT];

		try {
			for (int i = 0; i < _MAX_FILE_COUNT; i++) {
				inputStreams[i] = uploadPortletRequest.getFileAsStream(
					"file" + i);
			}

			NotificationThreadLocal.setEnabled(false);
			WikiCacheThreadLocal.setClearCache(false);

			_wikiNodeService.importPages(
				nodeId, importer, inputStreams,
				actionRequest.getParameterMap());
		}
		finally {
			StreamUtil.cleanUp(inputStreams);
		}

		_wikiCacheHelper.clearCache(nodeId);

		progressTracker.finish(actionRequest);
	}

	@Reference(unbind = "-")
	protected void setWikiCacheHelper(WikiCacheHelper wikiCacheHelper) {
		_wikiCacheHelper = wikiCacheHelper;
	}

	@Reference(unbind = "-")
	protected void setWikiNodeService(WikiNodeService wikiNodeService) {
		_wikiNodeService = wikiNodeService;
	}

	private static final int _MAX_FILE_COUNT = 3;

	@Reference
	private Portal _portal;

	private WikiCacheHelper _wikiCacheHelper;
	private WikiNodeService _wikiNodeService;

}