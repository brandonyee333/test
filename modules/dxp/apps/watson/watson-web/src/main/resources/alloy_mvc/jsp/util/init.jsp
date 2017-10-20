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

<%@ page import="com.liferay.alloy.mvc.AlloyController" %><%@
page import="com.liferay.alloy.mvc.AlloyPermission" %><%@
page import="com.liferay.alloy.mvc.BaseAlloyControllerImpl" %><%@
page import="com.liferay.alloy.mvc.BaseAlloyIndexer" %><%@
page import="com.liferay.counter.kernel.service.CounterLocalServiceUtil" %><%@
page import="com.liferay.document.library.kernel.model.DLFileEntry" %><%@
page import="com.liferay.document.library.kernel.service.DLAppLocalServiceUtil" %><%@
page import="com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil" %><%@
page import="com.liferay.document.library.kernel.util.DLUtil" %><%@
page import="com.liferay.document.library.kernel.util.DLValidatorUtil" %><%@
page import="com.liferay.dynamic.data.mapping.kernel.DDMFormFieldValue" %><%@
page import="com.liferay.dynamic.data.mapping.kernel.DDMFormValues" %><%@
page import="com.liferay.dynamic.data.mapping.kernel.Value" %><%@
page import="com.liferay.mail.kernel.model.MailMessage" %><%@
page import="com.liferay.mail.kernel.service.MailServiceUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.DynamicQuery" %><%@
page import="com.liferay.portal.kernel.dao.orm.Order" %><%@
page import="com.liferay.portal.kernel.dao.orm.OrderFactoryUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.Projection" %><%@
page import="com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil" %><%@
page import="com.liferay.portal.kernel.exception.ImageResolutionException" %><%@
page import="com.liferay.portal.kernel.exception.PortalException" %><%@
page import="com.liferay.portal.kernel.exception.SystemException" %><%@
page import="com.liferay.portal.kernel.image.ImageBag" %><%@
page import="com.liferay.portal.kernel.image.ImageToolUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.metadata.RawMetadataProcessor" %><%@
page import="com.liferay.portal.kernel.metadata.RawMetadataProcessorUtil" %><%@
page import="com.liferay.portal.kernel.model.AuditedModel" %><%@
page import="com.liferay.portal.kernel.model.BaseModel" %><%@
page import="com.liferay.portal.kernel.model.ClassName" %><%@
page import="com.liferay.portal.kernel.model.Country" %><%@
page import="com.liferay.portal.kernel.model.Repository" %><%@
page import="com.liferay.portal.kernel.model.Role" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil" %><%@
page import="com.liferay.portal.kernel.repository.model.FileEntry" %><%@
page import="com.liferay.portal.kernel.search.BooleanClause" %><%@
page import="com.liferay.portal.kernel.search.BooleanClauseFactoryUtil" %><%@
page import="com.liferay.portal.kernel.search.BooleanClauseOccur" %><%@
page import="com.liferay.portal.kernel.search.BooleanQuery" %><%@
page import="com.liferay.portal.kernel.search.Document" %><%@
page import="com.liferay.portal.kernel.search.Field" %><%@
page import="com.liferay.portal.kernel.search.Hits" %><%@
page import="com.liferay.portal.kernel.search.Indexer" %><%@
page import="com.liferay.portal.kernel.search.Query" %><%@
page import="com.liferay.portal.kernel.search.SearchContext" %><%@
page import="com.liferay.portal.kernel.search.Sort" %><%@
page import="com.liferay.portal.kernel.search.Summary" %><%@
page import="com.liferay.portal.kernel.search.TermRangeQuery" %><%@
page import="com.liferay.portal.kernel.search.WildcardQuery" %><%@
page import="com.liferay.portal.kernel.search.generic.BooleanQueryImpl" %><%@
page import="com.liferay.portal.kernel.search.generic.MatchQuery" %><%@
page import="com.liferay.portal.kernel.search.generic.TermRangeQueryImpl" %><%@
page import="com.liferay.portal.kernel.search.generic.WildcardQueryImpl" %><%@
page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.CountryServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.ServiceContext" %><%@
page import="com.liferay.portal.kernel.service.ServiceContextThreadLocal" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.servlet.BrowserSnifferUtil" %><%@
page import="com.liferay.portal.kernel.servlet.HttpHeaders" %><%@
page import="com.liferay.portal.kernel.upload.UploadPortletRequest" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.CharPool" %><%@
page import="com.liferay.portal.kernel.util.DateUtil" %><%@
page import="com.liferay.portal.kernel.util.FileUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.KeyValuePair" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.MimeTypesUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.UnicodeProperties" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.watson.model.WatsonIncident" %><%@
page import="com.liferay.watson.model.WatsonListType" %><%@
page import="com.liferay.watson.model.WatsonListTypeRel" %><%@
page import="com.liferay.watson.model.impl.WatsonActivityImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonAddressImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonHistoryImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonIncidentImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonIncidentRelImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonListTypeImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonListTypeRelImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonPersonImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonRelationshipImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonResourceImpl" %><%@
page import="com.liferay.watson.model.impl.WatsonVehicleImpl" %><%@
page import="com.liferay.watson.web.constants.RoleConstants" %><%@
page import="com.liferay.watson.web.constants.WatsonPortletKeys" %>

<%@ page import="java.awt.Rectangle" %><%@
page import="java.awt.geom.AffineTransform" %><%@
page import="java.awt.image.AffineTransformOp" %><%@
page import="java.awt.image.BufferedImage" %><%@
page import="java.awt.image.RenderedImage" %>

<%@ page import="java.io.File" %><%@
page import="java.io.IOException" %>

<%@ page import="java.lang.reflect.Method" %>

<%@ page import="java.nio.charset.StandardCharsets" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Collection" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.HashSet" %><%@
page import="java.util.Iterator" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %><%@
page import="java.util.Set" %>

<%@ page import="javax.imageio.ImageIO" %>

<%@ page import="javax.mail.internet.InternetAddress" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletResponse" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<%@ page import="org.apache.commons.csv.CSVFormat" %><%@
page import="org.apache.commons.csv.CSVParser" %><%@
page import="org.apache.commons.csv.CSVRecord" %>

<%@ include file="/alloy_mvc/jsp/util/file_uploader_util.jspf" %>
<%@ include file="/alloy_mvc/jsp/util/migration_util.jspf" %>
<%@ include file="/alloy_mvc/jsp/util/watson_alloy_controller_impl.jspf" %>
<%@ include file="/alloy_mvc/jsp/util/watson_permission.jspf" %>
<%@ include file="/alloy_mvc/jsp/util/watson_util.jspf" %>
<%@ include file="/alloy_mvc/jsp/util/workflow_util.jspf" %>

<%@ include file="/alloy_mvc/jsp/watson/models/activity_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/activity_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/address_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/address_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/history_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/history_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/incident_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/incident_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/incident_rel_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/incident_rel_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/list_type_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/list_type_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/list_type_rel_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/list_type_rel_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/person_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/person_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/relationship_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/relationship_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/resource_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/resource_base_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/vehicle_model.jspf" %>
<%@ include file="/alloy_mvc/jsp/watson/models/base/vehicle_base_model.jspf" %>