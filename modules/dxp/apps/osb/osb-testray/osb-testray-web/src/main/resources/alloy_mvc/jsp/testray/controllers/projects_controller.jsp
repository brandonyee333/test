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
		setAlloyServiceInvokerClass(TestrayProject.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name"}, parameterTypes = {String.class})
	public void add() throws Exception {
		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.createTestrayProject(0);

		_validateAdd();

		updateModel(testrayProject);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayProjectJSONObject(testrayProject));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	public void create() throws Exception {
		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.createTestrayProject(0);

		renderRequest.setAttribute("testrayProject", testrayProject);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void delete() throws Exception {
		TestrayProject testrayProject = _fetchTestrayProject();

		_validateDelete(testrayProject);

		TestrayProjectLocalServiceUtil.deleteTestrayProject(testrayProject);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void deleteTree() throws Exception {
		TestrayProject testrayProject = _fetchTestrayProject();

		_validateDeleteTree(testrayProject);

		TestrayProjectUtil.deleteTestrayProject(testrayProject);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayProject testrayProject = _fetchTestrayProject();

		_validateEdit(testrayProject);

		renderRequest.setAttribute("testrayProject", testrayProject);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "start"}, parameterTypes = {Integer.class, Integer.class, Integer.class, Integer.class})
	public void index() throws Exception {
		if (isRespondingTo("json")) {
			int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

			List<TestrayProject> testrayProjects = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, startAndEnd[0], startAndEnd[1]);

			respondWith(_getTestrayProjectsJSONArray(testrayProjects));

			return;
		}

		_search();
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name"}, parameterTypes = {Long.class, String.class})
	public void update() throws Exception {
		TestrayProject testrayProject = _fetchTestrayProject();

		_validateUpdate(testrayProject);

		updateModel(testrayProject);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayProjectJSONObject(testrayProject));

			return;
		}

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			redirectTo(redirect);
		}
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id"}, parameterTypes = {String.class})
	public void view() throws Exception {
		TestrayProject testrayProject = _fetchTestrayProject();

		_validateView(testrayProject);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayProjectJSONObject(testrayProject));

			return;
		}

		renderRequest.setAttribute("testrayProject", testrayProject);
	}

	private TestrayProject _fetchTestrayProject() throws Exception {
		TestrayProject testrayProject = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			List<TestrayProject> testrayProjects = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", id.substring(1)});

			if (!testrayProjects.isEmpty()) {
				testrayProject = testrayProjects.get(0);
			}
		}

		return testrayProject;
	}

	private JSONObject _getTestrayProjectJSONObject(TestrayProject testrayProject) throws Exception {
		TestrayProjectComposite testrayProjectComposite = new TestrayProjectComposite(testrayProject);

		return testrayProjectComposite.getJSONObject();
	}

	private JSONArray _getTestrayProjectsJSONArray(List<TestrayProject> testrayProjects) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayProject testrayProject : testrayProjects) {
			jsonArray.put(_getTestrayProjectJSONObject(testrayProject));
		}

		return jsonArray;
	}

	private void _search() throws Exception {
		SearchContainer<TestrayProject> searchContainer = new SearchContainer<TestrayProject>(portletRequest, portletURL, null, null);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayProjectModelImpl.TABLE_NAME, orderByCol, orderByType.equals("asc"));

		List<TestrayProject> testrayProjects = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, searchContainer.getStart(), searchContainer.getEnd(), obc);

		renderRequest.setAttribute("testrayProjects", testrayProjects);

		long testrayProjectsCount = alloyServiceInvoker.executeDynamicQueryCount(new Object[] {"groupId", themeDisplay.getScopeGroupId()});

		renderRequest.setAttribute("testrayProjectsCount", testrayProjectsCount);
	}

	private void _validateAdd() throws Exception {
		_validateName();
	}

	private void _validateDelete(TestrayProject testrayProject) throws Exception {
		_validateTestrayProject(testrayProject);

		AlloyServiceInvoker testrayBuildAlloyServiceInvoker = new AlloyServiceInvoker(TestrayBuild.class.getName());

		long testrayBuildsCount = testrayBuildAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayProjectId", testrayProject.getTestrayProjectId()});

		if (testrayBuildsCount > 0) {
			throw new AlloyException("the-project-cannot-be-deleted-because-it-has-associated-builds", false);
		}
	}

	private void _validateDeleteTree(TestrayProject testrayProject) throws Exception {
		_validateTestrayProject(testrayProject);

		if (!PortletPropsValues.TESTRAY_DELETE_TREE_ENABLED) {
			throw new AlloyException("project-delete-tree-is-not-enabled", false);
		}
	}

	private void _validateEdit(TestrayProject testrayProject) throws Exception {
		_validateTestrayProject(testrayProject);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-project-name-is-invalid", false);
		}

		List<TestrayProject> testrayProjects = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", name});

		if (!testrayProjects.isEmpty()) {
			long testrayProjectId = ParamUtil.getLong(request, "id");

			TestrayProject testrayProject = testrayProjects.get(0);

			if (testrayProject.getTestrayProjectId() != testrayProjectId) {
				throw new AlloyException("the-project-name-already-exists", false);
			}
		}
	}

	private void _validateTestrayProject(TestrayProject testrayProject) throws Exception {
		if ((testrayProject == null) || testrayProject.isNew()) {
			String id = ParamUtil.getString(request, "id");

			if (Validator.isNumber(id)) {
				throw new AlloyException(translate("the-project-with-id-x-does-not-exist", GetterUtil.getLong(id)), false);
			}
			else if (id.indexOf(StringPool.UNDERLINE) == 0) {
				throw new AlloyException(translate("the-project-with-name-x-does-not-exist", id.substring(1)), false);
			}
		}
	}

	private void _validateUpdate(TestrayProject testrayProject) throws Exception {
		_validateTestrayProject(testrayProject);

		_validateName();
	}

	private void _validateView(TestrayProject testrayProject) throws Exception {
		_validateTestrayProject(testrayProject);
	}

}
%>