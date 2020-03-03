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

<%@ include file="/alloy_mvc/jsp/watson/controllers/init.jspf" %>

<%@ include file="/alloy_mvc/jsp/watson/indexers/relationship_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonRelationship.getBaseModelClass());
		setPermissioned(false);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonRelationship watsonRelationship = WatsonRelationship.create(request);

		watsonRelationship.add();

		respondWith(WatsonRelationship.getAsJSONObject(watsonRelationship));
	}

	public void create() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}
	}

	public void delete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonRelationshipId = ParamUtil.getLong(request, "id");

		WatsonRelationship watsonRelationship = WatsonRelationship.fetch(watsonRelationshipId);

		watsonRelationship.delete();

		respondWith(WatsonRelationship.getAsJSONObject(watsonRelationship));
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		if (!WatsonPermission.check(user, watsonIncidentId, Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		List<WatsonRelationship> watsonRelationships = WatsonRelationship.getIncidentRelationships(watsonIncidentId);

		JSONObject watsonRelationshipsJSONObject = JSONFactoryUtil.createJSONObject();

		watsonRelationshipsJSONObject.put("id", watsonIncidentId);
		watsonRelationshipsJSONObject.put("modifiedDateTimeStamp", new Date());
		watsonRelationshipsJSONObject.put("watsonIncidentId", watsonIncidentId);
		watsonRelationshipsJSONObject.put("watsonRelationships", WatsonRelationship.getAsJSONArray(watsonRelationships));

		respondWith(watsonRelationshipsJSONObject);
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

		if (!WatsonPermission.check(user, watsonIncidentId, Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive");
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		List<WatsonRelationship> watsonRelationships = WatsonIncident.getWatsonRelationships(watsonIncidentId, includeInactive, null, start, end);

		respondWith(WatsonRelationship.getAsJSONArray(watsonRelationships));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

		if (!WatsonPermission.check(user, watsonIncidentId, Constants.UPDATE) || WatsonIncident.hasDisabled(user.getUserId(), WatsonIncident.getIncidentStatus(watsonIncidentId))) {
			this.edit();

			return;
		}

		JSONArray watsonRelationshipJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "watsonRelationships"));

		Object[] generatedWatsonRelationships = WatsonRelationship.generateValidationData(request, watsonIncidentId, watsonRelationshipJSONArray);

		List<WatsonRelationship> watsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[0];

		List<WatsonRelationship> removableWatsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[1];

		JSONObject errorObject = WatsonRelationship.validateRelationships(watsonRelationships);

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject();

		responseJSONObject.put("id", watsonIncidentId);
		responseJSONObject.put("modifiedDateTimeStamp", new Date());
		responseJSONObject.put("watsonIncidentId", watsonIncidentId);

		if (errorObject.length() > 0) {
			responseJSONObject.put("errors", errorObject);

			responseJSONObject.put("model", WatsonRelationship.getAsJSONArray(watsonRelationships));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("relationships-were-not-saved"), responseJSONObject);

			return;
		}

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		responseJSONObject.put("watsonRelationships", WatsonRelationship.getAsJSONArray(watsonRelationships));

		respondWith(translate("relationships-saved-successfully"), responseJSONObject);
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonRelationshipId = ParamUtil.getLong(request, "id");

		WatsonRelationship watsonRelationship = WatsonRelationship.fetch(watsonRelationshipId);

		respondWith(WatsonRelationship.getAsJSONObject(watsonRelationship));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonRelationshipIndexer.getInstance();
	}

}
%>