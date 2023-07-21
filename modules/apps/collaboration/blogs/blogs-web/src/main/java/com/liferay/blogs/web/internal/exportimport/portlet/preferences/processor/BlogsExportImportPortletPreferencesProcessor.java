/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalService;
import com.liferay.exportimport.kernel.lar.ExportImportHelper;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.blogs.service.permission.BlogsPermission;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(
	immediate = true, property = "javax.portlet.name=" + BlogsPortletKeys.BLOGS,
	service = ExportImportPortletPreferencesProcessor.class
)
public class BlogsExportImportPortletPreferencesProcessor
	implements ExportImportPortletPreferencesProcessor {

	@Override
	public List<Capability> getExportCapabilities() {
		return ListUtil.toList(
			new Capability[] {_blogsPortletDisplayTemplateExportCapability});
	}

	@Override
	public List<Capability> getImportCapabilities() {
		return ListUtil.toList(
			new Capability[] {_blogsPortletDisplayTemplateImportCapability});
	}

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		if (!_exportImportHelper.isExportPortletData(portletDataContext) ||
			!portletDataContext.getBooleanParameter(
				_blogsPortletDataHandler.getNamespace(), "entries")) {

			return portletPreferences;
		}

		try {
			portletDataContext.addPortletPermissions(
				BlogsPermission.RESOURCE_NAME);
		}
		catch (PortalException pe) {
			throw new PortletDataException(pe);
		}

		String portletId = portletDataContext.getPortletId();

		ActionableDynamicQuery actionableDynamicQuery =
			_blogsEntryLocalService.getExportActionableDynamicQuery(
				portletDataContext);

		actionableDynamicQuery.setPerformActionMethod(
			(BlogsEntry blogsEntry) ->
				StagedModelDataHandlerUtil.exportReferenceStagedModel(
					portletDataContext, portletId, blogsEntry));

		try {
			actionableDynamicQuery.performActions();
		}
		catch (PortalException pe) {
			throw new PortletDataException(pe);
		}

		return portletPreferences;
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		if (!portletDataContext.getBooleanParameter(
				_blogsPortletDataHandler.getNamespace(), "entries")) {

			return portletPreferences;
		}

		try {
			portletDataContext.importPortletPermissions(
				BlogsPermission.RESOURCE_NAME);
		}
		catch (PortalException pe) {
			throw new PortletDataException(pe);
		}

		Element entriesElement = portletDataContext.getImportDataGroupElement(
			BlogsEntry.class);

		List<Element> entryElements = entriesElement.elements();

		for (Element entryElement : entryElements) {
			StagedModelDataHandlerUtil.importStagedModel(
				portletDataContext, entryElement);
		}

		return portletPreferences;
	}

	@Reference(unbind = "-")
	protected void setBlogsEntryLocalService(
		BlogsEntryLocalService blogsEntryLocalService) {

		_blogsEntryLocalService = blogsEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setBlogsPortletDisplayTemplateExportCapability(
		BlogsPortletDisplayTemplateExportCapability
			blogsPortletDisplayTemplateExportCapability) {

		_blogsPortletDisplayTemplateExportCapability =
			blogsPortletDisplayTemplateExportCapability;
	}

	@Reference(unbind = "-")
	protected void setBlogsPortletDisplayTemplateImportCapability(
		BlogsPortletDisplayTemplateImportCapability
			blogsPortletDisplayTemplateImportCapability) {

		_blogsPortletDisplayTemplateImportCapability =
			blogsPortletDisplayTemplateImportCapability;
	}

	private BlogsEntryLocalService _blogsEntryLocalService;

	@Reference(target = "(javax.portlet.name=" + BlogsPortletKeys.BLOGS + ")")
	private PortletDataHandler _blogsPortletDataHandler;

	private BlogsPortletDisplayTemplateExportCapability
		_blogsPortletDisplayTemplateExportCapability;
	private BlogsPortletDisplayTemplateImportCapability
		_blogsPortletDisplayTemplateImportCapability;

	@Reference
	private ExportImportHelper _exportImportHelper;

}