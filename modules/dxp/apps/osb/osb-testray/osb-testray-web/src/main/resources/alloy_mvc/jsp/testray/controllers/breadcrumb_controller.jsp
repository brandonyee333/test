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

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"buildName", "routineName", "caseResultName", "projectName", "runName"}, parameterTypes = {String.class, String.class, String.class, String.class, String.class})
	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		_validateSearch();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String action = "index";
		String controller = null;
		Object[] parameters = null;

		String testrayProjectName = ParamUtil.getString(request, "projectName");
		String testrayRoutineName = ParamUtil.getString(request, "routineName");
		String testrayBuildName = ParamUtil.getString(request, "buildName");
		long testrayRunNumber = ParamUtil.getLong(request, "runName", -1);
		String testrayCaseName = ParamUtil.getString(request, "caseResultName");

		BaseModel<?> baseModel = _getBaseModel(testrayProjectName, testrayRoutineName, testrayBuildName, testrayRunNumber, testrayCaseName);

		if (baseModel instanceof TestrayBuild) {
			controller = "case_results";
			parameters = new Object[] {"testrayBuildId", BeanPropertiesUtil.getLong(baseModel, "testrayBuildId")};
		}
		else if (baseModel instanceof TestrayRoutine) {
			controller = "builds";
			parameters = new Object[] {"testrayRoutineId", BeanPropertiesUtil.getLong(baseModel, "testrayRoutineId")};
		}
		else if (baseModel instanceof TestrayCaseResult) {
			action = "view";
			controller = "case_results";
			parameters = new Object[] {"id", BeanPropertiesUtil.getLong(baseModel, "testrayCaseResultId")};
		}
		else if (baseModel instanceof TestrayProject) {
			controller = "routines";
			parameters = new Object[] {"testrayProjectId", BeanPropertiesUtil.getLong(baseModel, "testrayProjectId")};
		}
		else if (baseModel instanceof TestrayRun) {
			controller = "case_results";
			parameters = new Object[] {"testrayBuildId", BeanPropertiesUtil.getLong(baseModel, "testrayBuildId"), "testrayRunId", BeanPropertiesUtil.getLong(baseModel, "testrayRunId"), "statuses", StringUtil.merge(TestrayCaseResultConstants.STATUSES_NOT_PASSED)};
		}

		PortletURL portletURL = getPortletURL(controller, action, portletRequest.getPortletMode(), PortletRequest.RENDER_PHASE, WindowState.NORMAL, parameters);

		jsonObject.put("url", portletURL.toString());

		respondWith(jsonObject);
	}

	private <T> T _fetchBaseModel(Class<T> clazz, Object... properties) throws Exception {
		AlloyServiceInvoker alloyServiceInvoker = new AlloyServiceInvoker(clazz.getName());

		properties = ArrayUtil.append(properties, new Object[] {"groupId", themeDisplay.getScopeGroupId()});

		List<T> baseModels = alloyServiceInvoker.executeDynamicQuery(properties);

		if (baseModels.isEmpty()) {
			return null;
		}

		return baseModels.get(0);
	}

	private BaseModel<?> _getBaseModel(String testrayProjectName, String testrayRoutineName, String testrayBuildName, long testrayRunNumber, String testrayCaseName) throws Exception {
		TestrayProject testrayProject = _fetchBaseModel(TestrayProject.class, "name", testrayProjectName);

		if (testrayProject == null) {
			throw new AlloyException(translate("there-is-no-project-with-name-x", testrayProjectName), false);
		}
		else if (Validator.isNull(testrayRoutineName)) {
			return testrayProject;
		}

		TestrayRoutine testrayRoutine = _fetchBaseModel(TestrayRoutine.class, "testrayProjectId", testrayProject.getTestrayProjectId(), "name", testrayRoutineName);

		if (testrayRoutine == null) {
			throw new AlloyException(translate("there-is-no-routine-with-name-x-in-project-x", testrayRoutineName, testrayProjectName), false);
		}
		else if (Validator.isNull(testrayBuildName)) {
			return testrayRoutine;
		}

		TestrayBuild testrayBuild = _fetchBaseModel(TestrayBuild.class, "testrayRoutineId", testrayRoutine.getTestrayRoutineId(), "name", testrayBuildName);

		if (testrayBuild == null) {
			throw new AlloyException(translate("there-is-no-build-with-name-x-in-routine-x", testrayBuildName, testrayRoutineName), false);
		}
		else if (testrayRunNumber < 0) {
			return testrayBuild;
		}

		TestrayRun testrayRun = _fetchBaseModel(TestrayRun.class, "testrayBuildId", testrayBuild.getTestrayBuildId(), "number", testrayRunNumber);

		if (testrayRun == null) {
			throw new AlloyException(translate("there-is-no-run-with-number-x-in-build-x", testrayRunNumber, testrayBuildName), false);
		}
		else if (Validator.isNull(testrayCaseName)) {
			return testrayRun;
		}

		TestrayCase testrayCase = _fetchBaseModel(TestrayCase.class, "testrayProjectId", testrayProject.getTestrayProjectId(), "name", testrayCaseName);

		if (testrayCase == null) {
			throw new AlloyException(translate("there-is-no-case-result-with-name-x-in-run-x", testrayCaseName, testrayRunNumber));
		}

		TestrayCaseResult testrayCaseResult = _fetchBaseModel(TestrayCaseResult.class, "testrayRunId", testrayRun.getTestrayRunId(), "testrayCaseId", testrayCase.getTestrayCaseId());

		if (testrayCaseResult == null) {
			throw new AlloyException(translate("there-is-no-case-result-with-name-x-in-run-x", testrayCaseName, testrayRunNumber), false);
		}

		return testrayCaseResult;
	}

	private void _validateSearch() throws Exception {
		String testrayProjectName = ParamUtil.getString(request, "projectName");

		if (Validator.isNull(testrayProjectName)) {
			throw new AlloyException("the-project-name-is-invalid", false);
		}
	}

}
%>