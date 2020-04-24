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
		setAlloyServiceInvokerClass(TestrayProductVersion.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void add() throws Exception {
		TestrayProductVersion testrayProductVersion = TestrayProductVersionLocalServiceUtil.createTestrayProductVersion(0);

		_validateAdd();

		updateModel(testrayProductVersion);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayProductVersionJSONObject(testrayProductVersion));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		TestrayProductVersion testrayProductVersion = TestrayProductVersionLocalServiceUtil.createTestrayProductVersion(0);

		testrayProductVersion.setTestrayProjectId(testrayProjectId);

		renderRequest.setAttribute("testrayProductVersion", testrayProductVersion);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void delete() throws Exception {
		TestrayProductVersion testrayProductVersion = _fetchTestrayProductVersion();

		_validateDelete(testrayProductVersion);

		TestrayProductVersionLocalServiceUtil.deleteTestrayProductVersion(testrayProductVersion);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayProductVersion testrayProductVersion = _fetchTestrayProductVersion();

		_validateEdit(testrayProductVersion);

		renderRequest.setAttribute("testrayProductVersion", testrayProductVersion);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "start", "testrayProjectId"}, parameterTypes = {Integer.class, Integer.class, Integer.class, Integer.class, Long.class})
	public void index() throws Exception {
		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		List<TestrayProductVersion> testrayProductVersions = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId}, startAndEnd[0], startAndEnd[1]);

		renderRequest.setAttribute("testrayProductVersions", testrayProductVersions);

		if (isRespondingTo("json")) {
			_validateTestrayProject();

			respondWith(_getTestrayProductVersionsJSONArray(testrayProductVersions));

			return;
		}

		long testrayProductVersionsCount = alloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayProjectId", testrayProjectId});

		renderRequest.setAttribute("testrayProductVersionsCount", testrayProductVersionsCount);

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.getTestrayProject(testrayProjectId);

		renderRequest.setAttribute("testrayProject", testrayProject);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name", "testrayProjectId"}, parameterTypes = {String.class, String.class, Long.class})
	public void update() throws Exception {
		TestrayProductVersion testrayProductVersion = _fetchTestrayProductVersion();

		_validateUpdate(testrayProductVersion);

		updateModel(testrayProductVersion);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayProductVersionJSONObject(testrayProductVersion));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id", "testrayProjectId"}, parameterTypes = {String.class, Long.class})
	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		TestrayProductVersion testrayProductVersion = _fetchTestrayProductVersion();

		_validateView(testrayProductVersion);

		respondWith(_getTestrayProductVersionJSONObject(testrayProductVersion));
	}

	private TestrayProductVersion _fetchTestrayProductVersion() throws Exception {
		TestrayProductVersion testrayProductVersion = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayProductVersion = TestrayProductVersionLocalServiceUtil.fetchTestrayProductVersion(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

			List<TestrayProductVersion> testrayProductVersions = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", id.substring(1)});

			if (!testrayProductVersions.isEmpty()) {
				testrayProductVersion = testrayProductVersions.get(0);
			}
		}

		return testrayProductVersion;
	}

	private JSONObject _getTestrayProductVersionJSONObject(TestrayProductVersion testrayProductVersion) throws Exception {
		TestrayProductVersionComposite testrayProductVersionComposite = new TestrayProductVersionComposite(testrayProductVersion);

		return testrayProductVersionComposite.getJSONObject();
	}

	private JSONArray _getTestrayProductVersionsJSONArray(List<TestrayProductVersion> testrayProductVersions) throws Exception {
		JSONArray testrayProductVersionsJSONArray = JSONFactoryUtil.createJSONArray();

		for (TestrayProductVersion testrayProductVersion : testrayProductVersions) {
			testrayProductVersionsJSONArray.put(_getTestrayProductVersionJSONObject(testrayProductVersion));
		}

		return testrayProductVersionsJSONArray;
	}

	private void _validateAdd() throws Exception {
		_validateName();

		_validateTestrayProject();
	}

	private void _validateDelete(TestrayProductVersion testrayProductVersion) throws Exception {
		_validateTestrayProductVersion(testrayProductVersion);

		AlloyServiceInvoker testrayBuildAlloyServiceInvoker = new AlloyServiceInvoker(TestrayBuild.class.getName());

		long testrayBuildsCount = testrayBuildAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayProductVersionId", testrayProductVersion.getTestrayProductVersionId()});

		if (testrayBuildsCount > 0) {
			throw new AlloyException("the-product-version-cannot-be-deleted-because-it-has-associated-builds", false);
		}
	}

	private void _validateEdit(TestrayProductVersion testrayProductVersion) throws Exception {
		_validateTestrayProductVersion(testrayProductVersion);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-product-version-name-is-invalid", false);
		}

		List<TestrayProductVersion> testrayProductVersions = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayProjectId", testrayProjectId, "name", name});

		if (!testrayProductVersions.isEmpty()) {
			long testrayProductVersionId = ParamUtil.getLong(request, "id");

			TestrayProductVersion testrayProductVersion = testrayProductVersions.get(0);

			if (testrayProductVersion.getTestrayProductVersionId() != testrayProductVersionId) {
				throw new AlloyException("the-product-version-name-already-exists", false);
			}
		}
	}

	private void _validateTestrayProductVersion(TestrayProductVersion testrayProductVersion) throws Exception {
		if ((testrayProductVersion == null) || testrayProductVersion.isNew()) {
			long testrayProductVersionId = ParamUtil.getLong(request, "id");

			throw new AlloyException(translate("the-product-version-with-id-x-does-not-exist", testrayProductVersionId), false);
		}
	}

	private void _validateTestrayProject() throws Exception {
		long testrayProjectId = ParamUtil.getLong(request, "testrayProjectId");

		TestrayProject testrayProject = TestrayProjectLocalServiceUtil.fetchTestrayProject(testrayProjectId);

		if (testrayProject == null) {
			throw new AlloyException(translate("the-project-with-id-x-does-not-exist", testrayProjectId), false);
		}
	}

	private void _validateUpdate(TestrayProductVersion testrayProductVersion) throws Exception {
		_validateTestrayProductVersion(testrayProductVersion);

		_validateName();
	}

	private void _validateView(TestrayProductVersion testrayProductVersion) throws Exception {
		_validateTestrayProductVersion(testrayProductVersion);
	}

}
%>