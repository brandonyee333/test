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

<%@ include file="/account_entry_details/init.jsp" %>

<%
AccountEntry accountEntry = accountEntryViewDisplayContext.getAccountEntry();

long accountEntryId = accountEntry.getAccountEntryId();

PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-ui:search-container
	emptyResultsMessage="no-liferay-contacts-were-found"
	headerNames="name,role,email,contact-number"
	iteratorURL="<%= portletURL %>"
>
	<liferay-ui:search-container-results>

		<%
		results = AccountWorkerLocalServiceUtil.getAccountWorkers(accountEntryId);

		searchContainer.setTotal(results.size());
		searchContainer.setResults(results);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.osb.model.AccountWorker"
		modelVar="accountWorker"
	>

		<%
		User curUser = UserLocalServiceUtil.getUser(accountWorker.getUserId());

		String contactNumber = StringPool.BLANK;

		for (Phone phone : curUser.getPhones()) {
			if (phone.isPrimary()) {
				contactNumber = phone.getNumber();

				if (Validator.isNotNull(phone.getExtension())) {
					contactNumber += " ext: " + phone.getExtension();
				}

				break;
			}
		}
		%>

		<liferay-ui:search-container-column-text
			cssClass="semibold"
			name="name"
			value="<%= curUser.getFullName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="role"
		>
			<c:if test="<%= Validator.isNotNull(accountWorker.getRoleLabel()) %>">
				<%= LanguageUtil.get(request, accountWorker.getRoleLabel()) %>
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="email"
			value="<%= curUser.getEmailAddress() %>"
		/>

		<liferay-ui:search-container-column-text
			name="contact-number"
			value="<%= contactNumber %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
	/>
</liferay-ui:search-container>