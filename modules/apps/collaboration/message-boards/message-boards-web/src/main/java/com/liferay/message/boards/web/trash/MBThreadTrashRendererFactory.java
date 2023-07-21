/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.trash;

import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBThreadLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.trash.TrashRenderer;
import com.liferay.portal.kernel.trash.TrashRendererFactory;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.message.boards.kernel.model.MBThread",
	service = TrashRendererFactory.class
)
public class MBThreadTrashRendererFactory implements TrashRendererFactory {

	@Override
	public TrashRenderer getTrashRenderer(long classPK) throws PortalException {
		MBThread thread = _mbThreadLocalService.getThread(classPK);

		MBThreadTrashRenderer mbThreadTrashRenderer = new MBThreadTrashRenderer(
			thread);

		mbThreadTrashRenderer.setServletContext(_servletContext);

		return mbThreadTrashRenderer;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.message.boards.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference(unbind = "-")
	protected void setMBThreadLocalService(
		MBThreadLocalService mbThreadLocalService) {

		_mbThreadLocalService = mbThreadLocalService;
	}

	private MBThreadLocalService _mbThreadLocalService;
	private ServletContext _servletContext;

}