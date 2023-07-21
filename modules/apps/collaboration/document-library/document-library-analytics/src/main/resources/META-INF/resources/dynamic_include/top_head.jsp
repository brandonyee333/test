<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/dynamic_include/init.jsp" %>

<script data-senna-track="temporary" type="text/javascript">
	if (window.Analytics) {
		window.<%= DocumentLibraryAnalyticsConstants.JS_PREFIX %>isViewFileEntry = false;
	}
</script>

<aui:script require="metal-dom/src/all/dom as dom,metal-uri/src/Uri" sandbox="<%= true %>">
	var Uri = metalUriSrcUri.default;
	var pathnameRegexp = /\/documents\/(\d+)\/(\d+)\/(.+)\/(.+)/;

	var downloadClickHandler = dom.delegate(
		document.body,
		'click',
		'a',
		function(event) {
			if (window.Analytics) {
				var anchor = event.delegateTarget;
				var uri = new Uri(anchor.href);

				var match = pathnameRegexp.exec(uri.getPathname());

				if (match) {
					Analytics.send(
						'documentDownloaded',
						'Document',
						{
							fileEntryId: uri.getParameterValue('fileEntryId'),
							fileEntryUUID: match[4],
							groupId: match[1],
							preview: !!window.<%= DocumentLibraryAnalyticsConstants.JS_PREFIX %>isViewFileEntry,
							title: decodeURIComponent(match[3].replace(/\+/ig, ' ')),
							version: uri.getParameterValue('version')
						}
					);
				}
			}
		}
	);

	var onDestroyPortlet = function() {
		downloadClickHandler.removeListener()
		Liferay.detach('destroyPortlet', onDestroyPortlet);
	}

	Liferay.on('destroyPortlet', onDestroyPortlet);
</aui:script>