<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.osb.customer.admin.constants.ProductEntryConstants" %><%@
page import="com.liferay.osb.customer.admin.model.AccountEntry" %><%@
page import="com.liferay.osb.customer.admin.model.ProductEntry" %><%@
page import="com.liferay.osb.customer.admin.service.AccountEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.admin.service.ProductEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.admin.util.comparator.AccountEntryNameComparator" %><%@
page import="com.liferay.osb.customer.admin.util.comparator.ProductEntryNameComparator" %><%@
page import="com.liferay.osb.customer.constants.OSBActionKeys" %><%@
page import="com.liferay.osb.customer.koroneiki.constants.ProductConstants" %><%@
page import="com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService" %><%@
page import="com.liferay.osb.customer.provisioning.web.internal.constants.CustomerProvisioningPortletKeys" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.ListType" %><%@
page import="com.liferay.portal.kernel.service.ListTypeServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.PortletPermissionUtil" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PrefsParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.HashSet" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.util.Set" %><%@
page import="java.util.TreeSet" %>

<%@ page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
ProductPurchaseViewWebService productPurchaseViewWebService = (ProductPurchaseViewWebService)request.getAttribute(ProductPurchaseViewWebService.class.getName());
%>