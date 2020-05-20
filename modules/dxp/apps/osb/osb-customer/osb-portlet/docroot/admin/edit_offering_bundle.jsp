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

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long offeringBundleId = ParamUtil.getLong(request, "offeringBundleId");

OfferingBundle offeringBundle = OfferingBundleLocalServiceUtil.fetchOfferingBundle(offeringBundleId);

String offeringDefinitionIds = StringPool.BLANK;

List<OfferingDefinition> offeringDefinitions = new ArrayList<OfferingDefinition>();

if (offeringBundle != null) {
	offeringDefinitions = offeringBundle.getOfferingDefinitions();

	for (OfferingDefinition offeringDefinition : offeringDefinitions) {
		offeringDefinitionIds += offeringDefinition.getOfferingDefinitionId() + StringPool.COMMA;
	}
}
%>

<portlet:actionURL name="updateOfferingBundle" var="updateOfferingBundleURL">
	<portlet:param name="mvcPath" value="/admin/edit_offering_bundle.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateOfferingBundleURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="offeringBundleId" type="hidden" value="<%= offeringBundleId %>" />
	<aui:input name="offeringDefinitionIds" type="hidden" value="<%= offeringDefinitionIds %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="offering-bundle"
	/>

	<liferay-ui:error exception="<%= DuplicateOfferingBundleException.class %>" message="please-enter-a-unique-name" />
	<liferay-ui:error exception="<%= OfferingBundleNameException.class %>" message="please-enter-a-valid-name" />

	<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="name" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= offeringBundle %>"
					field="name"
					model="<%= OfferingBundle.class %>"
				/>
			</td>
		</tr>
	</table>

	<br />

	<liferay-ui:tabs
		names="offerings"
	/>

	<div>
		<portlet:renderURL var="selectOfferingDefinitionURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/admin/select_offering_definition.jsp" />
			<portlet:param name="callback" value="selectOfferingDefinition" />
		</portlet:renderURL>

		<%
		String taglibSelectOfferingDefinition = "var offeringWindow = window.open('" + selectOfferingDefinitionURL + "', 'offering', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); offeringWindow.focus();";
		%>

		<aui:button onClick="<%= taglibSelectOfferingDefinition %>" value="add-offering" />
	</div>

	<br />

	<liferay-ui:search-container
		headerNames="product,product-description,sla,licenses,tickets,"
		id="offering"
	>
		<liferay-ui:search-container-results
			results="<%= offeringDefinitions %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.OfferingDefinition"
			escapedModel="<%= true %>"
			keyProperty="offeringDefinitionId"
			modelVar="offeringDefinition"
		>
			<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_offering_definition.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="offeringDefinitionId" value="<%= String.valueOf(offeringDefinition.getOfferingDefinitionId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="product"
				property="productEntry.name"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="product-description"
				property="productDescription"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="sla"
				property="supportResponse.name"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="licenses"
				property="licensesLabel"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="tickets"
				property="supportTicketsLabel"
				translate="<%= true %>"
			/>

			<liferay-ui:search-container-column-text>
				<aui:button onClick='<%= renderResponse.getNamespace() + "removeRow('offeringDefinitionIds', '" + offeringDefinition.getOfferingDefinitionId() +"', '" + renderResponse.getNamespace() + "offeringSearchContainer', this);" %>' value="remove" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>

	<br />

	<aui:button type="submit" value="save" />

	<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
</aui:form>

<aui:script>
	function <portlet:namespace />addColumn(row, html) {
		var cell = row.insertCell(-1);

		cell.innerHTML = html;
	}

	function <portlet:namespace />removeRow(inputName, value, tableId, row) {
		var values = document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value;

		values = values.replace(value + ',', '');

		document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value = values;

		var table = document.getElementById(tableId).getElementsByTagName('tbody')[0];

		table.removeChild(row.parentNode.parentNode);
	}

	function <portlet:namespace />selectOfferingDefinition(offeringDefinitionId, offeringDefinitionFieldValues) {
		<portlet:namespace />selectRow('offeringDefinitionIds', offeringDefinitionId, '<portlet:namespace />offeringSearchContainer', [offeringDefinitionFieldValues[1], offeringDefinitionFieldValues[4], offeringDefinitionFieldValues[3], offeringDefinitionFieldValues[6], offeringDefinitionFieldValues[9]]);
	}

	function <portlet:namespace />selectRow(inputName, value, tableId, columnValues) {
		var values = document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value;

		if (values.indexOf(value + ',') == -1) {
			values += value + ',';

			document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value = values;

			var table = document.getElementById(tableId);

			table.parentNode.parentNode.className = 'lfr-search-container';

			var tBody = table.getElementsByTagName('tbody')[0];

			var row = tBody.insertRow(-1);

			row.className = 'results-row';

			for (i = 0; i < columnValues.length; i++) {
				<portlet:namespace />addColumn(row, columnValues[i]);
			}

			<portlet:namespace />addColumn(row, '<input class="btn btn-default" onClick="<portlet:namespace />removeRow(\'' + inputName + '\', \'' + value + '\', \'' + tableId + '\', this);" type="button" value="<liferay-ui:message key="remove" />" />');
		}
	}
</aui:script>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>