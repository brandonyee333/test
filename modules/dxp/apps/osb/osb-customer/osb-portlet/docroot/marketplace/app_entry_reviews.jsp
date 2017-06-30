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

<%@ include file="/marketplace/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, mvcPathParam);

boolean displayReviewForm = false;

if (mvcPath.equals("/marketplace/edit_app_entry_review.jsp")) {
	displayReviewForm = true;
}

long appEntryId = ParamUtil.getLong(request, "appEntryId");
long appVersionId = ParamUtil.getLong(request, "appVersionId");

MBMessageDisplay mbMessageDisplay = MBMessageLocalServiceUtil.getDiscussionMessageDisplay(themeDisplay.getUserId(), OSBConstants.GROUP_GUEST_ID, AppEntry.class.getName(), appEntryId, WorkflowConstants.STATUS_ANY, "flat");

MBThread mbThread = mbMessageDisplay.getThread();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter(mvcPathParam, "/marketplace/view_app_entry.jsp");
portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
portletURL.setParameter("appVersionId", String.valueOf(appVersionId));
%>

<div class="app-entry-reviews">
	<div class="callout-b-head">
		<div class="callout-content">
			<h2 class="title">
				<liferay-ui:message key="customer-ratings" />
			</h2>
		</div>
	</div>

	<div class="callout-e pseudo-portlet-content">
		<div class="error-container"></div>

		<c:if test="<%= OSBAppEntryPermission.contains(themeDisplay.getPermissionChecker(), appEntryId, OSBActionKeys.REVIEW_APP) %>">

			<%
			MBMessage userMBMessage = MarketplaceUtil.getUserDiscussionMessage(themeDisplay.getUserId(), AppEntry.class.getName(), appEntryId);
			%>

			<a class="btn user-review-action <%= displayReviewForm ? "active" : StringPool.BLANK %>" href="javascript:;"><liferay-ui:message key='<%= userMBMessage == null ? "write-a-review" : "edit-your-review" %>' /></a>
		</c:if>

		<div class="average-score">
			<div class="rating">

				<%
				RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(AppEntry.class.getName(), appEntryId);
				%>

				<div>
					<liferay-ui:message key="average" />

					(<%= ratingsStats.getTotalEntries() %> <%= LanguageUtil.get(pageContext, (ratingsStats.getTotalEntries() == 1) ? "vote" : "votes") %>)
				</div>

				<div>

					<%
					for (int i = 1; i <= _NUMBER_OF_STARS; i++) {
					%>

						<span class="aui-rating-element <%= (i <= ratingsStats.getAverageScore()) ? "aui-rating-element-on" : StringPool.BLANK %>"></span>

					<%
					}
					%>

				</div>
			</div>
		</div>

		<c:if test="<%= OSBAppEntryPermission.contains(themeDisplay.getPermissionChecker(), appEntryId, OSBActionKeys.REVIEW_APP) %>">
			<div class="user-review <%= displayReviewForm ? StringPool.BLANK : "aui-helper-hidden" %>">

				<%
				MBMessage userMBMessage = MarketplaceUtil.getUserDiscussionMessage(themeDisplay.getUserId(), AppEntry.class.getName(), appEntryId);

				MBTreeWalker mbTreeWalker = mbMessageDisplay.getTreeWalker();

				MBMessage rootMBMessage = null;

				if (mbTreeWalker != null) {
					rootMBMessage = mbTreeWalker.getRoot();
				}
				else {
					rootMBMessage = MBMessageLocalServiceUtil.getMessage(mbThread.getRootMessageId());
				}
				%>

				<div class="rating">

					<%
					double userScore = 0.0;

					RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.fetchEntry(themeDisplay.getUserId(), AppEntry.class.getName(), appEntryId);

					if (ratingsEntry != null) {
						userScore = ratingsEntry.getScore();
					}
					%>

					<aui:input name="rating" type="hidden" value="<%= userScore %>" />

					<div>
						<strong><liferay-ui:message key="rating" /></strong>
					</div>

					<div>

						<%
						for (int i = 1; i <= _NUMBER_OF_STARS; i++) {
						%>

							<span class="aui-rating-element <%= (i <= userScore) ? "aui-rating-element-on" : StringPool.BLANK %>"></span>

						<%
						}
						%>

					</div>
				</div>

				<%
				String messageBody = StringPool.BLANK;

				if (userMBMessage != null) {
					messageBody = userMBMessage.getBody();
				}
				%>

				<aui:input cssClass="review" name="review" type="textarea" value="<%= messageBody %>" wrap="soft" />

				<div>
					<a class="btn" data-root-mbmessage-id="<%= rootMBMessage.getMessageId() %>" href="javascript:;" id="<portlet:namespace />updateAppEntryReview" href="javascript:;">
						<liferay-ui:message key="save" />
					</a>
				</div>
			</div>
		</c:if>

		<div class="reviews">

			<%
			SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, 0, SearchContainer.DEFAULT_DELTA, portletURL, null, null);

			int status = WorkflowConstants.STATUS_APPROVED;

			if (permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId())) {
				status = WorkflowConstants.STATUS_ANY;
			}

			List<MBMessage> mbMessages = MarketplaceUtil.getAppEntryReviews(mbThread.getThreadId(), status, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(mbMessages);

			int total = MarketplaceUtil.getAppEntryReviewsCount(mbThread.getThreadId(), status);

			searchContainer.setTotal(total);

			for (MBMessage mbMessage : mbMessages) {
			%>

				<div class="post" data-messageId="<%= mbMessage.getMessageId() %>">
					<div class="metadata">
						<span class="name">
							<strong><%= HtmlUtil.escape(PortalUtil.getUserName(mbMessage.getUserId(), mbMessage.getUserName())) %></strong>
						</span>

						<span class="time">
							<%= mediumDateFormatDate.format(mbMessage.getModifiedDate()) %>
						</span>

						<c:if test="<%= permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId()) %>">
							<div class="portlet-msg-error approve<%= mbMessage.getStatus() == WorkflowConstants.STATUS_APPROVED ? " aui-helper-hidden" : StringPool.BLANK %>">
								<a href="javascript:;" onClick="<portlet:namespace />updateReviewStatus(<%= mbMessage.getMessageId() %>, <%= WorkflowConstants.STATUS_APPROVED %>)"><liferay-ui:message key="this-review-was-rejected" /></a>
							</div>
						</c:if>

						<div class="actions fr <%= mbMessage.getStatus() == WorkflowConstants.STATUS_APPROVED ? "" : "aui-helper-hidden" %>">
							<span class="flag">
								<a href="javascript:;" onClick="<portlet:namespace />flagReview(<%= mbMessage.getMessageId() %>)"><liferay-ui:message key="flag" /></a>
							</span>

							<c:if test="<%= permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId()) %>">
								<span class="deny">
									<a href="javascript:;" onClick="<portlet:namespace />updateReviewStatus(<%= mbMessage.getMessageId() %>, <%= WorkflowConstants.STATUS_DENIED %>)"><liferay-ui:message key="reject-review" /></a>
								</span>
							</c:if>
						</div>
					</div>

					<%
					RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.fetchEntry(mbMessage.getUserId(), AppEntry.class.getName(), appEntryId);
					%>

					<c:if test="<%= ratingsEntry != null %>">
						<div class="rating">

							<%
							for (int i = 1; i <= _NUMBER_OF_STARS; i++) {
							%>

								<span class="aui-rating-element <%= (i <= ratingsEntry.getScore()) ? "aui-rating-element-on" : StringPool.BLANK %>"></span>

							<%
							}
							%>

						</div>
					</c:if>

					<div class="content">

						<%
						String messageBody = BBCodeTranslatorUtil.getHTML(mbMessage.getBody());

						messageBody = StringUtil.replace(messageBody, "@theme_images_path@/emoticons", themeDisplay.getPathThemeImages() + "/emoticons");
						%>

						<%= messageBody %>
					</div>
				</div>

			<%
			}
			%>

			<c:if test="<%= searchContainer.getTotal() <= 0 %>">
				<liferay-ui:message key="there-are-no-reviews" />
			</c:if>

			<c:if test="<%= searchContainer.getTotal() > searchContainer.getDelta() %>">
				<div class="taglib-search-iterator-page-iterator-bottom">
					<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
				</div>
			</c:if>
		</div>
	</div>
</div>

<c:if test="<%= permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId()) %>">
	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />updateReviewStatus',
			function(mbMessageId, status) {
				var A = AUI();

				A.io.request(
					'<liferay-portlet:actionURL name="updateDiscussionMessageStatus" />',
					{
						data: {
							<portlet:namespace />mbMessageId: mbMessageId,
							<portlet:namespace />status: status
						},
						dataType: 'json',
						method: 'post',
						on: {
							start: function(event, id, obj) {
								var container = A.one('.review.container');

								if (status == <%= WorkflowConstants.STATUS_APPROVED %>) {
									container.one('.post[data-messageId="' + mbMessageId + '"] .approve').hide();
									container.one('.post[data-messageId="' + mbMessageId + '"] .actions').show();
								}
								else {
									container.one('.post[data-messageId="' + mbMessageId + '"] .approve').show();
									container.one('.post[data-messageId="' + mbMessageId + '"] .actions').hide();
								}
							}
						}
					}
				);
			},
			['aui-io']
		);
	</aui:script>
</c:if>

<c:if test="<%= OSBAppEntryPermission.contains(themeDisplay.getPermissionChecker(), appEntryId, OSBActionKeys.REVIEW_APP) %>">
	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />flagReview',
			function(mbMessageId) {
				var A = AUI();

				var width = 435;

				var popup = new A.Dialog(
					{
						destroyOnClose: true,
						draggable: true,
						modal: true,
						resizable: false,
						stack: true,
						title: '<liferay-ui:message key="report-abuse" />',
						width: width,
						x: document.documentElement.clientWidth/2 - width/2,
						y: 300
					}
				).plug(
					A.Plugin.IO,
					{
						data: {
							<portlet:namespace />className: '<%= MBMessage.class.getName() %>',
							<portlet:namespace />classPK: mbMessageId,
							<portlet:namespace />contentTitle: '',
							<portlet:namespace />contentURL: '<%= HtmlUtil.escapeJS(PortalUtil.getPortalURL(request) + PortalUtil.getCurrentURL(request)) %>',
							<portlet:namespace />reportedUserId: '<%= themeDisplay.getUserId() %>'
						},
						uri: '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="<%= mvcPathParam %>" value="/marketplace/report_abuse.jsp" /></liferay-portlet:renderURL>'
					}
				).render();
			},
			['aui-io']
		);
	</aui:script>

	<aui:script use="aui-base">
		var appEntryReview = A.one('.marketplace .app-entry-reviews');

		var userReview = appEntryReview.one('.user-review');
		var userReviewAction = appEntryReview.one('.user-review-action');

		userReviewAction.on(
			'click',
			function(event) {
				userReview.removeClass('aui-helper-hidden');
				userReviewAction.addClass('active');
			}
		);

		var userRating = appEntryReview.one('.user-review .rating');

		var userRatingInput = userRating.one('input[name=<portlet:namespace />rating]');
		var userRatingStars = userRating.all('.aui-rating-element');

		userRating.delegate(
			'click',
			function(event) {
				var elementOn = true;

				userRatingStars.each(
					function(node, index) {
						if (elementOn) {
							node.addClass('aui-rating-element-on');
						}
						else {
							node.removeClass('aui-rating-element-on');
						}

						if (node.compareTo(event.currentTarget)) {
							elementOn = false;

							userRatingInput.set('value', index + 1);
						}
					}
				);
			},
			'.aui-rating-element'
		);

		userRatingStars.on(
			'mouseover',
			function(event) {
				var elementHover = true;

				userRatingStars.each(
					function(node, index) {
						if (elementHover) {
							node.addClass('aui-rating-element-hover');
						}
						else {
							node.removeClass('aui-rating-element-hover');
						}

						if (node.compareTo(event.currentTarget)) {
							elementHover = false;
						}
					}
				);
			}
		);

		userRating.on(
			'mouseout',
			function(event) {
				userRatingStars.each(
					function(node) {
						node.removeClass('aui-rating-element-hover');
					}
				);
			}
		);

		appEntryReview.delegate(
			'click',
			function(event) {
				var instance = this;

				event.preventDefault();

				var reloadAppEntryReviews = function() {
					var appEntryReviewsParentNode = appEntryReview.get('parentNode');

					A.io.request(
						'<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace/app_entry_reviews.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /></portlet:renderURL>',
						{
							method: 'get',
							dataType: 'text/html',
							on: {
								start: function() {
									appEntryReviewsParentNode.empty();

									appEntryReviewsParentNode.addClass('loading-animation');
								},

								success: function(event, id, obj) {
									var content = this.get('responseData');

									appEntryReviewsParentNode.removeClass("loading-animation");

									A.DOM.addHTML(appEntryReviewsParentNode, content, null);

									appEntryReviewsParentNode.all('script').each(
										function(scriptNode) {
											eval(scriptNode.getContent());
										}
									);
								}
							}
						}
					);
				};

				A.io.request(
					'<liferay-portlet:actionURL name="updateAppEntryReview" />',
					{
						data: {
							<portlet:namespace />appEntryId: <%= String.valueOf(appEntryId) %>,
							<portlet:namespace />parentMessageId: instance.getAttribute('data-root-mbmessage-id'),
							<portlet:namespace />threadId: <%= mbThread.getThreadId() %>,
							<portlet:namespace />review: appEntryReview.one('#<portlet:namespace />review').val()
						},
						dataType: 'json',
						method: 'post',
						on: {
							success: function(event, id, obj) {
								var data = this.get('responseData');

								if (!data.message || (data.message != 'success')) {
									var errorContainer = A.one('.app-entry-reviews .error-container');

									errorContainer.empty();

									var message = '<liferay-ui:message key="an-unexpected-error-has-occurred" />';

									if (data.exception) {
										if (data.exception === "<%= AssetEntryReviewLengthException.class.getName() %>") {
											message = '<liferay-ui:message arguments="<%= MarketplaceUtil.ASSET_ENTRY_REVIEW_MAX_LENGTH %>" key="please-enter-a-message-shorter-than-x-characters" unicode="<%= true %>" />';
										}
										else if (data.exception === "<%= MessageBodyException.class.getName() %>") {
											message = '<liferay-ui:message key="please-enter-a-valid-message" unicode="<%= true %>" />';
										}
										else if (data.exception === "<%= RestrictedReviewException.class.getName() %>") {
											message = '<liferay-ui:message key="you-have-been-restricted-from-reviewing-this-app" unicode="<%= true %>" />';
										}
									}

									var messageNode = A.Node.create('<div class="portlet-msg-error">' + message + '</div>');

									errorContainer.append(messageNode);
								}
								else {
									reloadAppEntryReviews();
								}
							}
						}
					}
				);
			},
			'#<portlet:namespace />updateAppEntryReview'
		);
	</aui:script>
</c:if>

<%!
private static final int _NUMBER_OF_STARS = 5;
%>