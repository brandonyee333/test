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
		setAlloyServiceInvokerClass(TestrayRoutine.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"defaultTestrayFactors", "name", "testrayProjectId", }, parameterTypes = {String.class, String.class, Long.class})
	public void add() throws Exception {
		TestrayRoutine testrayRoutine = TestrayRoutineLocalServiceUtil.createTestrayRoutine(0);

		_validateAdd();

		updateModel(testrayRoutine);

		if (isRespondingTo("json")) {
			String defaultTestrayFactors = ParamUtil.getString(request, "defaultTestrayFactors");

			TestrayFactorUtil.addTestrayFactors(this, TestrayRoutine.class.getName(), testrayRoutine.getTestrayRoutineId(), defaultTestrayFactors);

			respondWith(_getTestrayRoutineJSONObject(testrayRoutine));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		TestrayRoutine testrayRoutine = TestrayRoutineLocalServiceUtil.createTestrayRoutine(0);

		testrayRoutine.setTestrayProjectId(testrayProjectId);

		renderRequest.setAttribute("testrayRoutine", testrayRoutine);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name"}, parameterTypes = {Long.class, String.class})
	public void delete() throws Exception {
		TestrayRoutine testrayRoutine = _fetchTestrayRoutine();

		_validateDelete(testrayRoutine);

		TestrayRoutineUtil.deleteTestrayRoutine(testrayRoutine);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		PortletURL viewTestrayRoutinesURL = getPortletURL("routines", "index", portletRequest.getPortletMode(), PortletRequest.RENDER_PHASE, WindowState.NORMAL, new Object[] {"testrayProjectId", testrayRoutine.getTestrayProjectId()});

		addOpenerSuccessMessageRedirect(viewTestrayRoutinesURL.toString());

		render("close");
	}

	public void deleteConfirm() throws Exception {
		TestrayRoutine testrayRoutine = _fetchTestrayRoutine();

		renderRequest.setAttribute("testrayRoutine", testrayRoutine);

		AlloyServiceInvoker testrayBuildAlloyServiceInvoker = new AlloyServiceInvoker(TestrayBuild.class.getName());

		long testrayBuildsCount = testrayBuildAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayRoutineId", testrayRoutine.getTestrayRoutineId()});

		renderRequest.setAttribute("testrayBuildsCount", testrayBuildsCount);

		render("routines/delete_confirm");
	}

	public void edit() throws Exception {
		TestrayRoutine testrayRoutine = _fetchTestrayRoutine();

		_validateEdit(testrayRoutine);

		renderRequest.setAttribute("testrayRoutine", testrayRoutine);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "orderByCol", "orderByType", "start", "testrayProjectId"}, parameterTypes = {Integer.class, Integer.class, Integer.class, String.class, String.class, Integer.class, Long.class})
	public void index() throws Exception {
		_validateProject(testrayProjectId);

		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayCaseResultConstants", getConstantsBean(TestrayCaseResultConstants.class));
		renderRequest.setAttribute("TestrayRoutineConstants", getConstantsBean(TestrayRoutineConstants.class));

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayRoutineModelImpl.TABLE_NAME, orderByCol, orderByType.equals("asc"));

		List<TestrayRoutine> testrayRoutines = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId}, startAndEnd[0], startAndEnd[1], obc);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRoutinesJSONArray(testrayRoutines));

			return;
		}

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		renderRequest.setAttribute("testrayCasePriorities", ListUtil.toList(priorities));

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));

		long[] testrayCaseTypeIds = ParamUtil.getLongValues(request, "testrayCaseTypeId");

		testrayCaseResultProperties.put("testrayCaseTypeId", StringUtil.merge(testrayCaseTypeIds));

		renderRequest.setAttribute("testrayCaseTypeIds", ListUtil.toList(testrayCaseTypeIds));

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		List<TestrayRoutineComposite> testrayRoutineComposites = TestrayCompositeUtil.getComposites(testrayRoutines, TestrayRoutineComposite.class, new Class<?>[] {TestrayRoutine.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties});

		renderRequest.setAttribute("testrayRoutineComposites", testrayRoutineComposites);

		long testrayRoutinesCount = alloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayProjectId", testrayProjectId});

		renderRequest.setAttribute("testrayRoutinesCount", testrayRoutinesCount);

		AlloyServiceInvoker testrayCaseTypeAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCaseType.class.getName());

		OrderByComparator testrayCaseTypesOBC = OrderByComparatorFactoryUtil.create(TestrayCaseTypeModelImpl.TABLE_NAME, "name", true);

		List<TestrayCaseType> testrayCaseTypes = testrayCaseTypeAlloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, testrayCaseTypesOBC);

		renderRequest.setAttribute("testrayCaseTypes", testrayCaseTypes);

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.getTestrayProject(testrayProjectId);

		renderRequest.setAttribute("testrayProject", testrayProject);

		List<TestrayTeam> testrayTeams = TestrayTeamUtil.getTestrayTeams(testrayProjectId);

		renderRequest.setAttribute("testrayTeams", testrayTeams);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"defaultTestrayFactors", "id", "name", "testrayProjectId"}, parameterTypes = {String.class, Long.class, String.class, Long.class})
	public void update() throws Exception {
		TestrayRoutine testrayRoutine = _fetchTestrayRoutine();

		_validateUpdate(testrayRoutine);

		updateModel(testrayRoutine);

		if (isRespondingTo("json")) {
			String defaultTestrayFactors = ParamUtil.getString(request, "defaultTestrayFactors", null);

			if (defaultTestrayFactors != null) {
				TestrayFactorUtil.deleteTestrayFactors(TestrayRoutine.class.getName(), testrayRoutine.getTestrayRoutineId());

				TestrayFactorUtil.addTestrayFactors(this, TestrayRoutine.class.getName(), testrayRoutine.getTestrayRoutineId(), defaultTestrayFactors);
			}

			respondWith(_getTestrayRoutineJSONObject(testrayRoutine));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void view() throws Exception {
		TestrayRoutine testrayRoutine = _fetchTestrayRoutine();

		_validateView(testrayRoutine);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayRoutineJSONObject(testrayRoutine));

			return;
		}
	}

	private TestrayRoutine _fetchTestrayRoutine() throws Exception {
		TestrayRoutine testrayRoutine = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayRoutine = TestrayRoutineLocalServiceUtil.fetchTestrayRoutine(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			List<TestrayRoutine> testrayRoutines = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", id.substring(1)});

			if (!testrayRoutines.isEmpty()) {
				testrayRoutine = testrayRoutines.get(0);
			}
		}

		return testrayRoutine;
	}

	private JSONObject _getTestrayRoutineJSONObject(TestrayRoutine testrayRoutine) throws Exception {
		TestrayRoutineComposite testrayRoutineComposite = new TestrayRoutineComposite(testrayRoutine, themeDisplay);

		return testrayRoutineComposite.getJSONObject();
	}

	private JSONArray _getTestrayRoutinesJSONArray(List<TestrayRoutine> testrayRoutines) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayRoutine testrayRoutine : testrayRoutines) {
			jsonArray.put(_getTestrayRoutineJSONObject(testrayRoutine));
		}

		return jsonArray;
	}

	private void _validateAdd() throws Exception {
		_validateName();
		_validateNameRequired();
		_validateProject();

		if (!isRespondingTo("json")) {
			return;
		}

		_validateDefaultTestrayFactors();
	}

	private void _validateDefaultTestrayFactors() throws Exception {
		String defaultTestrayFactors = ParamUtil.getString(request, "defaultTestrayFactors", null);

		if ((defaultTestrayFactors != null) && defaultTestrayFactors.equals(StringPool.BLANK)) {
			throw new AlloyException("please-choose-at-least-one-category", false);
		}

		TestrayValidator.validateTestrayFactorsJSONArray(defaultTestrayFactors, request);
	}

	private void _validateDelete(TestrayRoutine testrayRoutine) throws Exception {
		_validateTestrayRoutine(testrayRoutine);

		AlloyServiceInvoker testrayBuildAlloyServiceInvoker = new AlloyServiceInvoker(TestrayBuild.class.getName());

		long testrayBuildsCount = testrayBuildAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayRoutineId", testrayRoutine.getTestrayRoutineId()});

		if (testrayBuildsCount > 0) {
			if (isRespondingTo("json") && !PortletPropsValues.TESTRAY_DELETE_TREE_ENABLED) {
				throw new AlloyException("delete-tree-is-disabled", false);
			}

			String name = ParamUtil.getString(request, "name");

			if (name.equals(StringPool.BLANK)) {
				throw new AlloyException("the-routine-cannot-be-deleted-because-it-has-associated-builds", false);
			}
			else if (!name.equals(testrayRoutine.getName())) {
				throw new AlloyException("routine-name-does-not-match-id-provided", false);
			}
		}
	}

	private void _validateEdit(TestrayRoutine testrayRoutine) throws Exception {
		_validateTestrayRoutine(testrayRoutine);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name", null);

		if (name == null) {
			return;
		}

		if (name.equals(StringPool.BLANK)) {
			throw new AlloyException("the-routine-name-is-invalid", false);
		}

		List<TestrayRoutine> testrayRoutines = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", name});

		if (!testrayRoutines.isEmpty()) {
			long testrayRoutineId = ParamUtil.getLong(request, "id");

			TestrayRoutine testrayRoutine = testrayRoutines.get(0);

			if (testrayRoutine.getTestrayRoutineId() != testrayRoutineId) {
				throw new AlloyException("the-routine-name-already-exists", false);
			}
		}
	}

	private void _validateNameRequired() throws Exception {
		String name = ParamUtil.getString(request, "name", null);

		if (name == null) {
			throw new AlloyException("the-routine-name-is-invalid", false);
		}
	}

	private void _validateProject() throws Exception {
		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

		_validateProject(testrayProjectId);
	}

	private void _validateProject(long testrayProjectId) throws Exception {
		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

		if (testrayProject == null) {
			throw new AlloyException(translate("the-project-with-id-x-does-not-exist", testrayProjectId), false);
		}
	}

	private void _validateTestrayRoutine(TestrayRoutine testrayRoutine) throws Exception {
		if ((testrayRoutine == null) || testrayRoutine.isNew()) {
			String id = ParamUtil.getString(request, "id");

			if (Validator.isNumber(id)) {
				throw new AlloyException(translate("the-routine-with-id-x-does-not-exist", GetterUtil.getLong(id)), false);
			}
			else if (id.indexOf(StringPool.UNDERLINE) == 0) {
				long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

				throw new AlloyException(translate("the-routine-with-name-x-does-not-exist-for-the-project-with-id-x", id.substring(1), testrayProjectId), false);
			}
		}
	}

	private void _validateUpdate(TestrayRoutine testrayRoutine) throws Exception {
		_validateTestrayRoutine(testrayRoutine);

		_validateName();

		if (!isRespondingTo("json")) {
			return;
		}

		_validateDefaultTestrayFactors();
	}

	private void _validateView(TestrayRoutine testrayRoutine) throws Exception {
		_validateTestrayRoutine(testrayRoutine);
	}

}
%>