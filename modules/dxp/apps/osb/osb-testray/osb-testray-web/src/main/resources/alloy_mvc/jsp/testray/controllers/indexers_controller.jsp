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

<%@ include file="/alloy_mvc/jsp/testray/controllers/init.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {}, parameterTypes = {})
	public void executeRollingReindex() throws Exception {
		TestrayIndexerUtil.executeReindex(themeDisplay.getCompanyId());

		if (isRespondingTo("json")) {
			respondWith("the-reindex-is-now-running");

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {}, parameterTypes = {})
	public void executeSingleReindex() throws Exception {
		String className = ParamUtil.getString(request, "className");

		TestrayIndexerUtil.executeReindex(themeDisplay.getCompanyId(), className);

		if (isRespondingTo("json")) {
			respondWith("the-reindex-is-now-running");

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void index() throws Exception {
		Set<Indexer<?>> indexers = IndexerRegistryUtil.getIndexers();

		Set<Indexer<?>> testrayIndexers = new HashSet<Indexer<?>>();

		for (Indexer<?> indexer : indexers) {
			String indexerClassName = indexer.getClassName();

			if (indexerClassName.startsWith("com.liferay.osb.testray")) {
				testrayIndexers.add(indexer);
			}
		}

		renderRequest.setAttribute("indexers", testrayIndexers);
	}

}
%>