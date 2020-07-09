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

<%@ page import="com.liferay.osb.customer.admin.constants.LicenseEntryConstants" %><%@
page import="com.liferay.osb.customer.admin.constants.ProductEntryConstants" %><%@
page import="com.liferay.osb.customer.admin.constants.WorkflowConstants" %><%@
page import="com.liferay.osb.customer.admin.model.LicenseEntry" %><%@
page import="com.liferay.osb.customer.admin.model.ProductEntry" %><%@
page import="com.liferay.osb.customer.admin.service.LicenseEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.admin.service.ProductEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.constants.OSBActionKeys" %><%@
page import="com.liferay.osb.customer.constants.OSBCustomerConstants" %><%@
page import="com.liferay.osb.customer.koroneiki.service.permission.AccountPermission" %><%@
page import="com.liferay.osb.customer.koroneiki.web.service.AccountWebService" %><%@
page import="com.liferay.osb.customer.koroneiki.web.service.ProductConsumptionWebService" %><%@
page import="com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService" %><%@
page import="com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService" %><%@
page import="com.liferay.osb.customer.license.constants.LicenseKeyConstants" %><%@
page import="com.liferay.osb.customer.license.exception.DuplicateHostNameException" %><%@
page import="com.liferay.osb.customer.license.exception.DuplicateIPAddressException" %><%@
page import="com.liferay.osb.customer.license.exception.DuplicateMACAddressException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeyDescriptionException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeyHostNameException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeyIPAddressException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeyMACAddressException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeyMaxServersException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeyOwnerException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeyProductVersionException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeyServerIdException" %><%@
page import="com.liferay.osb.customer.license.exception.LicenseKeySetNameException" %><%@
page import="com.liferay.osb.customer.license.exception.MaximumLicenseKeyException" %><%@
page import="com.liferay.osb.customer.license.model.LicenseKey" %><%@
page import="com.liferay.osb.customer.license.model.LicenseKeySet" %><%@
page import="com.liferay.osb.customer.license.service.LicenseKeyLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.license.service.LicenseKeyServiceUtil" %><%@
page import="com.liferay.osb.customer.license.service.LicenseKeySetLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.license.service.LicenseKeySetServiceUtil" %><%@
page import="com.liferay.osb.customer.license.service.permission.LicenseKeyPermission" %><%@
page import="com.liferay.osb.customer.license.service.permission.LicenseKeySetPermission" %><%@
page import="com.liferay.osb.customer.license.util.LicenseUtil" %><%@
page import="com.liferay.osb.customer.license.web.internal.configuration.LicenseWebConfigurationValues" %><%@
page import="com.liferay.osb.customer.license.web.internal.display.context.ProductPurchaseDisplay" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.AccountDisplayTerms" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.AccountSearch" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.AccountSearchTerms" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.LicenseKeyDisplayTerms" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.LicenseKeySearch" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.LicenseKeySearchTerms" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.ProductPurchaseResultRowSplitter" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.UserDisplayTerms" %><%@
page import="com.liferay.osb.customer.license.web.internal.search.UserSearch" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase" %><%@
page import="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.CustomSQLParam" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.ListType" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.service.ListTypeServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.OrganizationLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Time" %><%@
page import="com.liferay.portal.kernel.util.TimeZoneUtil" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.comparator.UserFirstNameComparator" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.LinkedHashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
AccountPermission accountPermission = (AccountPermission)request.getAttribute(AccountPermission.class.getName());
AccountWebService accountWebService = (AccountWebService)request.getAttribute(AccountWebService.class.getName());
ProductConsumptionWebService productConsumptionWebService = (ProductConsumptionWebService)request.getAttribute(ProductConsumptionWebService.class.getName());
ProductPurchaseViewWebService productPurchaseViewWebService = (ProductPurchaseViewWebService)request.getAttribute(ProductPurchaseViewWebService.class.getName());
ProductPurchaseWebService productPurchaseWebService = (ProductPurchaseWebService)request.getAttribute(ProductPurchaseWebService.class.getName());

String currentURL = PortalUtil.getCurrentURL(request);

Format longDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.LONG, locale, timeZone);

Format longDateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(DateFormat.LONG, DateFormat.LONG, locale, timeZone);

Format longDateFormatTime = FastDateFormatFactoryUtil.getTime(DateFormat.LONG, locale, timeZone);
%>