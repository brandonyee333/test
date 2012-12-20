<%--
  ~ ===========================================================================
  ~ IBA CZ Confidential
  ~ © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
  ~ The source code for this program is not published or otherwise
  ~ divested of its trade secrets.
  ~
  ~ ===========================================================================
  --%>
<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%@ page import="com.liferay.portal.model.LayoutSet" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeProperties" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="java.io.File" %>

<aui:fieldset style="width: 48em;">
    <div style="float:right;">
        <input type="button" id="_156_cacheManifestRegenerateButton" value="<liferay-ui:message key="offline-cache-manifest-regenerate-manifest" />" />
    </div>


    <%
        LayoutSet layoutSet = ((LayoutSet)request.getAttribute("edit_pages.jsp-selLayoutSet"));

        UnicodeProperties selLayoutTypeSettingsProperties = layoutSet.getSettingsProperties();
        String allowOfflineCache = selLayoutTypeSettingsProperties.getProperty("offline-cache-manifest-allow", "false");
        String cachedFolders = selLayoutTypeSettingsProperties.getProperty("offline-cache-manifest-folders", "");

        String sectionCache = selLayoutTypeSettingsProperties.getProperty("offline-cache-manifest-cache-section", "");
        String sectionNetwork = selLayoutTypeSettingsProperties.getProperty("offline-cache-manifest-network-section", "");
        String sectionFallback = selLayoutTypeSettingsProperties.getProperty("offline-cache-manifest-fallback-section", "");
    %>

    <aui:input name="TypeSettingsProperties--offline-cache-manifest-allow--" type="checkbox" value="<%= allowOfflineCache %>" />

    <input type="hidden" name="_156_TypeSettingsProperties--offline-cache-manifest-folders--" id="_156_TypeSettingsProperties_offline_cache_manifest_folders" value="<%= cachedFolders.replace("\n", "").replace("\"", "&quot;") %>" />

    <aui:input name="TypeSettingsProperties--offline-cache-manifest-cache-section--" type="textarea" value="<%= sectionCache %>" rows="8" cols="80" />
    <aui:input name="TypeSettingsProperties--offline-cache-manifest-network-section--" type="textarea" value="<%= sectionNetwork %>" rows="8" cols="80" />
    <aui:input name="TypeSettingsProperties--offline-cache-manifest-fallback-section--" type="textarea" value="<%= sectionFallback %>" rows="8" cols="80" />
</aui:fieldset>

<script type="text/javascript" src="/js/applicationCacheManifestRegenerate.js"></script>
<script type="text/javascript" src="/js/applicationCacheManifestFoldersTable.js"></script>
<script type="text/javascript">
    AUI().use('iba-applicationCacheManifestRegenerate', function(A) {
        new A.ApplicationCacheManifestRegenerate({
            button: "#_156_cacheManifestRegenerateButton",
            dialogTitle: '<liferay-ui:message key="offline-cache-manifest-regenerate-manifest" />',
            confirmationDialogContent: '<liferay-ui:message key="offline-cache-manifest-regenerate-manifest-unsaved-changes-notification" />',
            regenerationSuccessMsg: '<liferay-ui:message key="offline-cache-manifest-regenerate-manifest-success" />',
            regenerationFailureMsg: '<liferay-ui:message key="offline-cache-manifest-regenerate-manifest-failure" />',
            regenerationOfAllSuccessMsg: '<liferay-ui:message key="offline-cache-manifest-regenerate-all-manifests-success" />',
            regenerationOfAllFailureMsg: '<liferay-ui:message key="offline-cache-manifest-regenerate-all-manifests-failure" />',

            regenerateLabel: '<liferay-ui:message key="offline-cache-manifest-regenerate-manifest-regenerate" />',
            regenerateAllLabel: '<liferay-ui:message key="offline-cache-manifest-regenerate-manifest-regenerate-all" />',
            okLabel: '<liferay-ui:message key="ok" />',
            cancelLabel: '<liferay-ui:message key="cancel" />',

            layoutSetId: <%= ((LayoutSet)request.getAttribute("edit_pages.jsp-selLayoutSet")).getLayoutSetId() %>,
            regenerateManifestUrl: '/c/group_pages/regenerate_manifest',
            regenerateAllManifestsUrl: '/c/group_pages/regenerate_all_manifests'
        }).render();
    });

    AUI().use('iba-applicationCacheManifestFoldersTable', function(A) {
        new A.ApplicationCacheManifestFoldersTable({
            valueInput: '#_156_TypeSettingsProperties_offline_cache_manifest_folders',

            dialogTitle: '<liferay-ui:message key="offline-cache-manifest-folders" />',
            tableLabel: '<liferay-ui:message key="offline-cache-manifest-folders" />',
            addLabel: '<liferay-ui:message key="add" />',
            editLabel: '<liferay-ui:message key="edit" />',
            okLabel: '<liferay-ui:message key="ok" />',
            cancelLabel: '<liferay-ui:message key="cancel" />',
            deleteLabel: '<liferay-ui:message key="delete" />',
            noFoldersMessage: '<liferay-ui:message key="offline-cache-manifest-no-folders" />',
            folderUrlPrefixLabel: '<liferay-ui:message key="offline-cache-manifest-folder-url-prefix" />',
            folderLocationLabel: '<liferay-ui:message key="offline-cache-manifest-folder-location" />',
            locationNote: '<liferay-ui:message key="offline-cache-manifest-folder-location-note" />',

            portalWebDir: '<%= new File(PortalUtil.getPortalWebDir()).getParent() %>'
        }).render();
    });
</script>