<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/loader/init.jsp" %>

<aui:script>
	Liferay.provide(
		Liferay.Util,
		'openKaleoDesignerPortlet',
		function(config) {
			var A = AUI();

			var kaleoURL = Liferay.PortletURL.createURL('<%= PortletURLFactoryUtil.create(request, "2_WAR_kaleodesignerportlet", themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>');

			kaleoURL.setParameter('mvcPath', '/designer/edit_kaleo_draft_definition.jsp');
			kaleoURL.setParameter('availableFields', config.availableFields);
			kaleoURL.setParameter('availablePropertyModels', config.availablePropertyModels);
			kaleoURL.setParameter('ddmStructureId', config.ddmStructureId);
			kaleoURL.setParameter('draftVersion', config.draftVersion);
			kaleoURL.setParameter('kaleoProcessId', config.kaleoProcessId);
			kaleoURL.setParameter('name', config.name);
			kaleoURL.setParameter('openerWindowName', config.openerWindowName);
			kaleoURL.setParameter('portletResourceNamespace', config.portletResourceNamespace);
			kaleoURL.setParameter('propertiesSaveCallback', config.propertiesSaveCallback);
			kaleoURL.setParameter('refreshOpenerOnClose', config.refreshOpenerOnClose);
			kaleoURL.setParameter('saveCallback', config.saveCallback);
			kaleoURL.setParameter('version', config.version);
			kaleoURL.setWindowState('pop_up');

			config.uri = kaleoURL.toString();

			var dialogConfig = config.dialog;

			if (!dialogConfig) {
				var region = A.one(Liferay.Util.getOpener()).get('region');

				dialogConfig = {
					destroyOnHide: true
				};

				config.dialog = dialogConfig;
			}

			if (!('align' in dialogConfig)) {
				dialogConfig.align = Liferay.Util.Window.ALIGN_CENTER;
			}

			var dialogIframeConfig = config.dialogIframe;

			if (!dialogIframeConfig) {
				dialogIframeConfig = {
					closeOnEscape: false
				};

				config.dialogIframe = dialogIframeConfig;
			}

			Liferay.Util.openWindow(config);
		},
		['liferay-portlet-url']
	);
</aui:script>