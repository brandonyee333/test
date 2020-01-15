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

<liferay-ui:search-container
	emptyResultsMessage="no-liferay-contacts-were-found"
	headerNames="name,role,email,contact-number"
>
	<liferay-ui:search-container-results>

		<%
		List<Contact> contacts = accountEntryViewDisplayContext.getLiferayContacts();

		searchContainer.setTotal(contacts.size());
		searchContainer.setResults(contacts);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact"
		modelVar="koroneikiContact"
	>

		<%
		String contactNumber = StringPool.BLANK;

		try {
			User curUser = UserLocalServiceUtil.getUserByUuidAndCompanyId(koroneikiContact.getUuid(), themeDisplay.getCompanyId());

			for (Phone phone : curUser.getPhones()) {
				if (phone.isPrimary()) {
					contactNumber = phone.getNumber();

					if (Validator.isNotNull(phone.getExtension())) {
						contactNumber += " ext: " + phone.getExtension();
					}

					break;
				}
			}
		}
		catch (NoSuchUserException nsue) {
		}
		%>

		<liferay-ui:search-container-column-text
			cssClass="semi-bold"
			name="name"
			value="<%= PortalUtil.getFullName(koroneikiContact.getFirstName(), koroneikiContact.getMiddleName(), koroneikiContact.getLastName()) %>"
		/>

		<liferay-ui:search-container-column-text
			name="role"
		>

			<%
			ContactRole[] contactRoles = koroneikiContact.getContactRoles();

			if (contactRoles != null) {
				for (ContactRole contactRole : contactRoles) {
					String name = contactRole.getName();

					if (name.equals(ContactRoleConstants.NAME_MEMBER)) {
						continue;
					}
			%>

					<%= HtmlUtil.escape(name) %><br />

			<%
				}
			}
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="email"
			value="<%= koroneikiContact.getEmailAddress() %>"
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