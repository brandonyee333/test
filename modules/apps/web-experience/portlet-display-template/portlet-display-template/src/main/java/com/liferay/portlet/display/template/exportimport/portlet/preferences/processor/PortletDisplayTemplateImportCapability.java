/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.display.template.exportimport.portlet.preferences.processor;

import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.template.TemplateHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.display.template.PortletDisplayTemplateUtil;

import java.util.Map;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(
	immediate = true,
	service = {Capability.class, PortletDisplayTemplateImportCapability.class}
)
public class PortletDisplayTemplateImportCapability implements Capability {

	@Override
	public PortletPreferences process(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			return importDisplayStyle(
				portletDataContext, portletDataContext.getPortletId(),
				portletPreferences);
		}
		catch (Exception e) {
			return portletPreferences;
		}
	}

	protected long getClassNameId(
		PortletDataContext portletDataContext, String portletId) {

		Portlet portlet = _portletLocalService.getPortletById(
			portletDataContext.getCompanyId(), portletId);

		TemplateHandler templateHandler = portlet.getTemplateHandlerInstance();

		if (templateHandler == null) {
			return 0;
		}

		String className = templateHandler.getClassName();

		return portal.getClassNameId(className);
	}

	protected String getDisplayStyle(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) {

		try {
			Portlet portlet = _portletLocalService.getPortletById(
				portletDataContext.getCompanyId(), portletId);

			if (Validator.isNotNull(portlet.getTemplateHandlerInstance())) {
				return portletPreferences.getValue("displayStyle", null);
			}
		}
		catch (Exception e) {
		}

		return null;
	}

	protected long getDisplayStyleGroupId(
		PortletDataContext portletDataContext, String portletId,
		PortletPreferences portletPreferences) {

		try {
			Portlet portlet = _portletLocalService.getPortletById(
				portletDataContext.getCompanyId(), portletId);

			if (Validator.isNotNull(portlet.getTemplateHandlerInstance())) {
				return GetterUtil.getLong(
					portletPreferences.getValue("displayStyleGroupId", null));
			}
		}
		catch (Exception e) {
		}

		return 0;
	}

	protected PortletPreferences importDisplayStyle(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		PortletPreferences processedPreferences = portletPreferences;

		String displayStyle = getDisplayStyle(
			portletDataContext, portletId, portletPreferences);

		if (Validator.isNull(displayStyle) ||
			!displayStyle.startsWith(
				PortletDisplayTemplateManager.DISPLAY_STYLE_PREFIX)) {

			return processedPreferences;
		}

		StagedModelDataHandlerUtil.importReferenceStagedModels(
			portletDataContext, DDMTemplate.class);

		long displayStyleGroupId = getDisplayStyleGroupId(
			portletDataContext, portletId, portletPreferences);

		Map<Long, Long> groupIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Group.class);

		long groupId = MapUtil.getLong(
			groupIds, displayStyleGroupId, displayStyleGroupId);

		DDMTemplate ddmTemplate =
			PortletDisplayTemplateUtil.getPortletDisplayTemplateDDMTemplate(
				groupId, getClassNameId(portletDataContext, portletId),
				displayStyle, false);

		if (ddmTemplate != null) {
			portletPreferences.setValue(
				"displayStyleGroupId",
				String.valueOf(ddmTemplate.getGroupId()));
		}
		else {
			portletPreferences.setValue(
				"displayStyleGroupId", StringPool.BLANK);
		}

		return processedPreferences;
	}

	@Reference(unbind = "-")
	protected void setPortletLocalService(
		PortletLocalService portletLocalService) {

		_portletLocalService = portletLocalService;
	}

	@Reference
	protected Portal portal;

	private PortletLocalService _portletLocalService;

}