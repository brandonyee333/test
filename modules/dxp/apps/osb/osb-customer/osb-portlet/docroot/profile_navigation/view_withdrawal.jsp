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

<%@ include file="/profile_navigation/init.jsp" %>

<%
long corpEntryId = ParamUtil.getLong(request, "corpEntryId");
%>

<c:choose>
	<c:when test="<%= themeDisplay.isSignedIn() %>">
		<div id="<portlet:namespace />corpWitdrawalContainer">
			<p>
				<liferay-ui:message key="are-you-sure-you-want-to-leave-this-company" />
			</p>

			<aui:button cssClass="button fr" onClick='<%= renderResponse.getNamespace() + "unsetCorpEntryUser();" %>' value="yes" />
		</div>

		<aui:script>
			Liferay.provide(
				window,
				'<portlet:namespace />unsetCorpEntryUser',
				function() {
					var A = AUI();

					var corpWitdrawalContainer = A.one('#<portlet:namespace />corpWitdrawalContainer');

					A.io.request(
						'<liferay-portlet:actionURL name="unsetCorpEntryUser"><portlet:param name="corpEntryId" value="<%= String.valueOf(corpEntryId) %>" /></liferay-portlet:actionURL>',
						{
							dataType: 'json',
							method: 'post',
							on: {
								success: function(event, id, obj) {
									var response = this.get('responseData');

									A.DialogManager.closeByChild(corpWitdrawalContainer);

									if (window.corpRequestCallback) {
										if (response.message === "success") {
											corpRequestCallback(true);
										}
										else {
											corpRequestCallback(false);
										}
									}
								}
							}
						}
					);
				},
				['aui-base']
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<div>
			<liferay-ui:message key="please-sign-in-to-continue" />
		</div>
	</c:otherwise>
</c:choose>