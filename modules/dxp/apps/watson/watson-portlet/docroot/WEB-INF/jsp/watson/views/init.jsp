<%--
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
--%>

<%@ include file="/WEB-INF/jsp/util/init.jsp" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%@
taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><%@
taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/alloy-permission" prefix="alloyPermission" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.bean.ConstantsBeanFactoryUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %>

<%!
public class AlloyLanguageUtil extends LanguageUtil {

	public AlloyLanguageUtil(HttpServletRequest request) {
		_request = request;
	}

	public String format(String pattern) {
		return format(pattern, new Object[0]);
	}

	public String format(String pattern, Object[] arguments) {
		return LanguageUtil.format(_request, pattern, arguments);
	}

	public String formatUnicode(String pattern) {
		return formatUnicode(pattern, new Object[0]);
	}

	public String formatUnicode(String pattern, Object[] arguments) {
		return UnicodeLanguageUtil.format(_request, pattern, arguments);
	}

	public String getLanguageId() {
		return LanguageUtil.getLanguageId(_request);
	}

	private HttpServletRequest _request;

}
%>

<c:set value="<%= new AlloyLanguageUtil(request) %>" var="AlloyLanguageUtil" />

<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(PropsKeys.class) %>" var="PropsKeys" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(WatsonHistory.class) %>" var="WatsonHistoryConstants" />
<c:set value="<%= ConstantsBeanFactoryUtil.getConstantsBean(WatsonIncident.class) %>" var="WatsonIncidentConstants" />

<c:set value="<%= new ClassNameLocalServiceUtil() %>" var="ClassNameLocalService" />
<c:set value="<%= new CountryServiceUtil() %>" var="CountryServiceUtil" />
<c:set value="<%= new PropsUtil() %>" var="PropsUtil" />
<c:set value="<%= new WatsonActivity() %>" var="WatsonActivity" />
<c:set value="<%= new WatsonAddress() %>" var="WatsonAddress" />
<c:set value="<%= new WatsonIncident() %>" var="WatsonIncident" />
<c:set value="<%= new WatsonListType() %>" var="WatsonListType" />
<c:set value="<%= new WatsonListTypeRel() %>" var="WatsonListTypeRel" />
<c:set value="<%= new WatsonPerson() %>" var="WatsonPerson" />
<c:set value="<%= new WatsonRelationship() %>" var="WatsonRelationship" />
<c:set value="<%= new WatsonResource() %>" var="WatsonResource" />
<c:set value="<%= new WatsonVehicle() %>" var="WatsonVehicle" />

<liferay-util:html-top>
	<script data-senna-track="permanent" src="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathContext(request) + "/js/dist/bundle.nocsf.js") %>" type="text/javascript"></script>

	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</liferay-util:html-top>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
PortletMode portletMode = liferayPortletRequest.getPortletMode();
WindowState windowState = liferayPortletRequest.getWindowState();

PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();

String layoutURL = PortalUtil.getLayoutURL(layout, themeDisplay);
%>

<%@ include file="/WEB-INF/jsp/watson/views/config/_input_config.jsp" %>

<%
PortletURL expiredSessionPortletURL = PortletURLFactoryUtil.create(request, "com_liferay_login_web_portlet_LoginPortlet", themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

expiredSessionPortletURL.setParameter("mvcRenderCommandName", "/login/login");
expiredSessionPortletURL.setParameter("saveLastPath", Boolean.FALSE.toString());
expiredSessionPortletURL.setParameter("sessionExpired", Boolean.TRUE.toString());
expiredSessionPortletURL.setWindowState(LiferayWindowState.MAXIMIZED);
%>

<script>
	var baseURL = '<portlet:renderURL><portlet:param name="controller" value="incidents" /><portlet:param name="action" value="index" /><portlet:param name="tabs1" value="" /></portlet:renderURL>';

	var protocolPlusHost = window.location.protocol + '//' + window.location.host;

	var basePath = baseURL.substring(protocolPlusHost.length);

	var currentLanguageId = Liferay.ThemeDisplay.getLanguageId();

	if (currentLanguageId) {
		currentLanguageId = currentLanguageId.indexOf('en') > -1 ? 'en_US' : 'th';
	}

	var otherLanguageId = currentLanguageId.indexOf('en') > -1 ? 'th' : 'en_US';

	var config = {
		currentLanguageId: currentLanguageId,
		currentUser: ${currentWatsonUser},
		defaultNavigation: [
			{
				entries: [
					{
						href: '<portlet:renderURL><portlet:param name="controller" value="incidents" /><portlet:param name="action" value="create" /><portlet:param name="tabs1" value="" /></portlet:renderURL>',
						text: '+ ${AlloyLanguageUtil.formatUnicode("create")}'
					}
				],
				href: '<portlet:renderURL><portlet:param name="controller" value="incidents" /><portlet:param name="action" value="index" /><portlet:param name="tabs1" value="" /></portlet:renderURL>',
				text: '${AlloyLanguageUtil.formatUnicode("incidents")}'
			}
		],
		namespace: '${renderResponse.namespace}',
		otherLanguageId: otherLanguageId,
		uploadSettings: {
			acceptedTypes: {
				9557: {
					type: '.doc, .docx, .gif, .jpg, .odb, .odf, .odg, .odp, .ods, .odt, .pdf, .png, .ppt, .pptx, .rtf, .tar, .tiff, .tgz, .txt, .xls, .xlsx, .zip'
				},
				9558: {
					type: 'audio/*'
				},
				9559: {
					type: 'video/*'
				},
				9560: {
					type: 'image/*'
				},
				9561: {
					type: '.doc, .docx, .gif, .jpg, .odb, .odf, .odg, .odp, .ods, .odt, .pdf, .png, .ppt, .pptx, .rtf, .tar, .tiff, .tgz, .txt, .xls, .xlsx, .zip'
				}
			},
			maxFileCount: ${25},
			maxFileSize: ${PropsUtil.get(PropsKeys.DL_FILE_MAX_SIZE)}
		},
		urls: {
			addresses: '<portlet:renderURL><portlet:param name="controller" value="addresses" /><portlet:param name="action" value="index" /></portlet:renderURL>',
			basePath: basePath,
			baseURL: baseURL,
			histories: '<portlet:renderURL><portlet:param name="controller" value="histories" /><portlet:param name="action" value="index" /></portlet:renderURL>',
			incidents: '<portlet:renderURL><portlet:param name="controller" value="incidents" /><portlet:param name="action" value="index" /></portlet:renderURL>',
			list_types: '<portlet:renderURL><portlet:param name="controller" value="list_type" /><portlet:param name="action" value="index" /></portlet:renderURL>',
			people: '<portlet:renderURL><portlet:param name="controller" value="people" /><portlet:param name="action" value="index" /></portlet:renderURL>',
			relationships: '<portlet:renderURL><portlet:param name="controller" value="relationships" /><portlet:param name="action" value="index" /></portlet:renderURL>',
			resources: '<portlet:renderURL><portlet:param name="controller" value="resources" /><portlet:param name="action" value="index" /></portlet:renderURL>',
			sessionExpired: '<%= expiredSessionPortletURL.toString() %>',
			vehicles: '<portlet:renderURL><portlet:param name="controller" value="vehicles" /><portlet:param name="action" value="index" /></portlet:renderURL>'
		}
	};

	Object.assign(window.WatsonConstants, config);
</script>