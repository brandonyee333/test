<%--
  ~ ===========================================================================
  ~ IBA CZ Confidential
  ~ © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
  ~ The source code for this program is not published or otherwise
  ~ divested of its trade secrets.
  ~
  ~ ===========================================================================
  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%@ page import="com.liferay.portal.model.Layout" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeProperties" %>
<%@ page import="com.liferay.portal.model.LayoutSet" %>

<aui:fieldset>
    <%
        Layout selLayout = ((Layout)request.getAttribute("edit_pages.jsp-selLayout"));

        LayoutSet layoutSet = selLayout.isPrivateLayout()
                ? selLayout.getGroup().getPrivateLayoutSet()
                : selLayout.getGroup().getPublicLayoutSet();

        String allow = layoutSet.getSettingsProperties().getProperty("offline-cache-manifest-allow", "");

        UnicodeProperties selLayoutTypeSettingsProperties = selLayout.getTypeSettingsProperties();
        String disableOfflineCache = selLayoutTypeSettingsProperties.getProperty("offline-cache-manifest-disable", "false");
    %>

    <c:set var="allow"><%= allow %></c:set>

    <c:choose>
        <c:when test="${'true' eq allow}">
            <aui:input name="TypeSettingsProperties--offline-cache-manifest-disable--" type="checkbox" value="<%= disableOfflineCache %>" />
        </c:when>
        <c:otherwise>
            <div class="portlet-msg-info"><liferay-ui:message key="offline-cache-manifest-cache-is-not-active-for-this-set" /></div>
        </c:otherwise>
    </c:choose>
</aui:fieldset>
