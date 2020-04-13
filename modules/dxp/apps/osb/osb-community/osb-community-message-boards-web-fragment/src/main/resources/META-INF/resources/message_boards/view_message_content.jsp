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

<%@ include file="/message_boards/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = redirect;

MBMessageDisplay messageDisplay = (MBMessageDisplay)request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE_DISPLAY);

MBMessage message = messageDisplay.getMessage();

MBCategory category = messageDisplay.getCategory();

MBThread thread = messageDisplay.getThread();

if (Validator.isNull(redirect)) {
	PortletURL backPortletURL = renderResponse.createRenderURL();

	if ((category == null) || (category.getCategoryId() == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID)) {
		backPortletURL.setParameter("mvcRenderCommandName", "/message_boards/view");
	}
	else {
		backPortletURL.setParameter("mvcRenderCommandName", "/message_boards/view_category");
		backPortletURL.setParameter("mbCategoryId", String.valueOf(category.getCategoryId()));
	}

	backURL = backPortletURL.toString();
}

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

if (portletTitleBasedNavigation) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(backURL);

	renderResponse.setTitle(message.getSubject());
}
%>

<c:if test="<%= !portletTitleBasedNavigation %>">
	<h3><%= HtmlUtil.escape(message.getSubject()) %></h3>

	<div class="thread-controls">
		<div class="thread-actions">
			<liferay-ui:icon-list>
				<c:if test="<%= MBCategoryPermission.contains(permissionChecker, scopeGroupId, (category != null) ? category.getCategoryId() : MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, ActionKeys.ADD_MESSAGE) %>">
					<portlet:renderURL var="addMessageURL">
						<portlet:param name="mvcRenderCommandName" value="/message_boards/edit_message" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="mbCategoryId" value="<%= (category != null) ? String.valueOf(category.getCategoryId()) : String.valueOf(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						iconCssClass="icon-plus"
						message="post-new-thread"
						url="<%= addMessageURL %>"
					/>
				</c:if>

				<c:if test="<%= !thread.isLocked() && MBMessagePermission.contains(permissionChecker, message, ActionKeys.PERMISSIONS) %>">

					<%
					MBMessage rootMessage = null;

					if (message.isRoot()) {
						rootMessage = message;
					}
					else {
						rootMessage = MBMessageLocalServiceUtil.getMessage(message.getRootMessageId());
					}
					%>

					<liferay-security:permissionsURL
						modelResource="<%= MBMessage.class.getName() %>"
						modelResourceDescription="<%= rootMessage.getSubject() %>"
						resourcePrimKey="<%= String.valueOf(thread.getRootMessageId()) %>"
						var="permissionsURL"
						windowState="<%= LiferayWindowState.POP_UP.toString() %>"
					/>

					<liferay-ui:icon
						iconCssClass="icon-lock"
						message="permissions"
						method="get"
						url="<%= permissionsURL %>"
						useDialog="<%= true %>"
					/>
				</c:if>

				<c:if test="<%= enableRSS && MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW) %>">
					<liferay-ui:rss
						delta="<%= rssDelta %>"
						displayStyle="<%= rssDisplayStyle %>"
						feedType="<%= rssFeedType %>"
						url="<%= MBUtil.getRSSURL(plid, 0, message.getThreadId(), 0, themeDisplay) %>"
					/>
				</c:if>

				<c:if test="<%= MBMessagePermission.contains(permissionChecker, message, ActionKeys.SUBSCRIBE) && (mbGroupServiceSettings.isEmailMessageAddedEnabled() || mbGroupServiceSettings.isEmailMessageUpdatedEnabled()) %>">
					<c:choose>
						<c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), MBThread.class.getName(), message.getThreadId()) %>">
							<portlet:actionURL name="/message_boards/edit_message" var="unsubscribeURL">
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								iconCssClass="icon-remove-sign"
								message="unsubscribe"
								url="<%= unsubscribeURL %>"
							/>
						</c:when>
						<c:otherwise>
							<portlet:actionURL name="/message_boards/edit_message" var="subscribeURL">
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								iconCssClass="icon-ok-sign"
								message="subscribe"
								url="<%= subscribeURL %>"
							/>
						</c:otherwise>
					</c:choose>
				</c:if>

				<c:if test="<%= MBCategoryPermission.contains(permissionChecker, scopeGroupId, (category != null) ? category.getCategoryId() : MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, ActionKeys.LOCK_THREAD) %>">
					<c:choose>
						<c:when test="<%= thread.isLocked() %>">
							<portlet:actionURL name="/message_boards/edit_message" var="unlockThreadURL">
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNLOCK %>" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="threadId" value="<%= String.valueOf(message.getThreadId()) %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								iconCssClass="icon-unlock"
								message="unlock-thread"
								url="<%= unlockThreadURL %>"
							/>
						</c:when>
						<c:otherwise>
							<portlet:actionURL name="/message_boards/edit_message" var="lockThreadURL">
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.LOCK %>" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="threadId" value="<%= String.valueOf(message.getThreadId()) %>" />
							</portlet:actionURL>

							<liferay-ui:icon
								iconCssClass="icon-lock"
								message="lock-thread"
								url="<%= lockThreadURL %>"
							/>
						</c:otherwise>
					</c:choose>
				</c:if>

				<c:if test="<%= MBCategoryPermission.contains(permissionChecker, scopeGroupId, (category != null) ? category.getCategoryId() : MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, ActionKeys.MOVE_THREAD) %>">
					<portlet:renderURL var="editThreadURL">
						<portlet:param name="mvcRenderCommandName" value="/message_boards/move_thread" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="mbCategoryId" value="<%= (category != null) ? String.valueOf(category.getCategoryId()) : String.valueOf(MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) %>" />
						<portlet:param name="threadId" value="<%= String.valueOf(message.getThreadId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						iconCssClass="icon-move"
						message="move-thread"
						url="<%= editThreadURL %>"
					/>
				</c:if>
			</liferay-ui:icon-list>
		</div>

		<div class="clear"></div>
	</div>
</c:if>

<div class="thread-container">

	<%
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("messageId", String.valueOf(message.getMessageId()));

	SearchContainer searchContainer = null;

	MBTreeWalker treeWalker = messageDisplay.getTreeWalker();

	List<MBMessage> messages = null;

	boolean viewableThread = false;

	if (treeWalker != null) {
		messages = new ArrayList<MBMessage>();

		messages.addAll(treeWalker.getMessages());

		messages = ListUtil.sort(messages, new MessageCreateDateComparator(true));
	}

	int cur = 0;
	int delta = SearchContainer.DEFAULT_DELTA;

	if (request.getParameter(SearchContainer.DEFAULT_CUR_PARAM) == null) {
		int positionInThread = MBMessageLocalServiceUtil.getPositionInThread(message.getMessageId());

		if (positionInThread > delta) {
			cur = (int)Math.ceil(((double)positionInThread) / delta);
		}
		else {
			cur = SearchContainer.DEFAULT_CUR;
		}
	}

	searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, cur, delta, portletURL, null, null);

	int total = MBMessageServiceUtil.getThreadMessagesCount(message.getGroupId(), category.getCategoryId(), message.getThreadId(), WorkflowConstants.STATUS_ANY);

	searchContainer.setTotal(total);

	messages = MBMessageServiceUtil.getThreadMessages(message.getGroupId(), category.getCategoryId(), message.getThreadId(), WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd());

	searchContainer.setResults(messages);

	viewableThread = GetterUtil.getBoolean((String)request.getAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_VIEWABLE_THREAD));
	%>

	<c:if test="<%= searchContainer.getTotal() > searchContainer.getDelta() %>">
		<div class="taglib-search-iterator-page-iterator-top">
			<liferay-ui:search-paginator
				id="pageIteratorTop"
				searchContainer="<%= searchContainer %>"
			/>
		</div>
	</c:if>

	<%
	int depth = 0;

	for (int i = 0; i < messages.size(); i++) {
		message = messages.get(i);

		if (!MBUtil.isViewableMessage(themeDisplay, message)) {
			continue;
		}

		viewableThread = true;

		request.setAttribute("edit-message.jsp-showDeletedAttachmentsFileEntries", Boolean.TRUE);
		request.setAttribute("edit-message.jsp-showPermanentLink", Boolean.TRUE);
		request.setAttribute("edit-message.jsp-showRecentPosts", Boolean.TRUE);
		request.setAttribute("edit_message.jsp-category", category);
		request.setAttribute("edit_message.jsp-depth", depth);
		request.setAttribute("edit_message.jsp-editable", Boolean.TRUE);
		request.setAttribute("edit_message.jsp-message", message);
		request.setAttribute("edit_message.jsp-thread", thread);
	%>

		<liferay-util:include page="/message_boards/view_thread_message.jsp" servletContext="<%= application %>" />

	<%
	}
	%>

	<c:if test="<%= searchContainer.getTotal() > searchContainer.getDelta() %>">
		<div class="taglib-search-iterator-page-iterator-bottom">
			<liferay-ui:search-paginator
				id="pageIteratorBottom"
				searchContainer="<%= searchContainer %>"
			/>
		</div>
	</c:if>

	<c:if test="<%= !viewableThread %>">
		<div class="alert alert-danger">
			<liferay-ui:message key="you-do-not-have-permission-to-access-the-requested-resource" />
		</div>
	</c:if>
</div>

<aui:script sandbox="<%= true %>">
	$('#<portlet:namespace />moreMessages').on(
		'click',
		function(event) {
			var form = $('#<portlet:namespace />fm');

			var data = Liferay.Util.ns(
				'<portlet:namespace />',
				{
					index: form.fm('index').val(),
					rootIndexPage: form.fm('rootIndexPage').val()
				}
			);

			<portlet:resourceURL id="/message_boards/get_messages" var="getMessagesURL">
				<portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>" />
			</portlet:resourceURL>

			$.ajax(
				'<%= getMessagesURL.toString() %>',
				{
					data: data,
					success: function(data) {
						$('#<portlet:namespace />messageContainer').append(data);
					}
				}
			);
		}
	);
</aui:script>