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
		setAlloyServiceInvokerClass(TestrayCaseType.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name"}, parameterTypes = {String.class})
	public void add() throws Exception {
		TestrayCaseType testrayCaseType = TestrayCaseTypeLocalServiceUtil.createTestrayCaseType(0);

		_validateAdd();

		updateModel(testrayCaseType);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayCaseTypeJSONObject(testrayCaseType));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		TestrayCaseType testrayCaseType = TestrayCaseTypeLocalServiceUtil.createTestrayCaseType(0);

		renderRequest.setAttribute("testrayCaseType", testrayCaseType);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void delete() throws Exception {
		TestrayCaseType testrayCaseType = _fetchTestrayCaseType();

		_validateDelete(testrayCaseType);

		TestrayCaseTypeLocalServiceUtil.deleteTestrayCaseType(testrayCaseType);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayCaseType testrayCaseType = _fetchTestrayCaseType();

		_validateEdit(testrayCaseType);

		renderRequest.setAttribute("testrayCaseType", testrayCaseType);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "start"}, parameterTypes = {Integer.class, Integer.class, Integer.class, Integer.class})
	public void index() throws Exception {
		SearchContainer<TestrayCaseType> searchContainer = new SearchContainer<TestrayCaseType>(portletRequest, portletURL, null, null);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayCaseTypeModelImpl.TABLE_NAME, orderByCol, orderByType.equals("asc"));

		if (isRespondingTo("json")) {
			int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

			List<TestrayCaseType> testrayCaseTypes = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, startAndEnd[0], startAndEnd[1], obc);

			respondWith(_getTestrayCaseTypesJSONArray(testrayCaseTypes));

			return;
		}

		List<TestrayCaseType> testrayCaseTypes = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, searchContainer.getStart(), searchContainer.getEnd(), obc);

		renderRequest.setAttribute("testrayCaseTypes", testrayCaseTypes);

		long testrayCaseTypesCount = alloyServiceInvoker.executeDynamicQueryCount(new Object[] {"groupId", themeDisplay.getScopeGroupId()});

		renderRequest.setAttribute("testrayCaseTypesCount", testrayCaseTypesCount);
	}

	public void metrics() throws Exception {
		renderRequest.setAttribute("TestrayBuildConstants", getConstantsBean(TestrayBuildConstants.class));
		renderRequest.setAttribute("TestrayCaseConstants", getConstantsBean(TestrayCaseConstants.class));
		renderRequest.setAttribute("TestrayCaseTypeConstants", getConstantsBean(TestrayCaseTypeConstants.class));

		TestrayBuild testrayBuild = TestrayBuildUtil.getTestrayBuild(request);

		TestrayValidator.validateTestrayBuild(request, testrayBuild);

		TestrayBuildComposite testrayBuildComposite = TestrayCompositeUtil.getComposite(TestrayBuildComposite.class, new Class<?>[] {TestrayBuild.class, ThemeDisplay.class}, new Object[] {testrayBuild, themeDisplay});

		renderRequest.setAttribute("testrayBuildComposite", testrayBuildComposite);

		Map<String, Serializable> testrayCaseResultProperties = new HashMap<String, Serializable>();

		testrayCaseResultProperties.put("testrayBuildId", testrayBuild.getTestrayBuildId());

		int[] priorities = ParamUtil.getIntegerValues(request, "priorities");

		List<Integer> testrayCasePriorities = ListUtil.toList(priorities);

		renderRequest.setAttribute("testrayCasePriorities", testrayCasePriorities);

		testrayCaseResultProperties.put("testrayCasePriority", StringUtil.merge(priorities));

		long testrayRunId = ParamUtil.getLong(request, "testrayRunId");

		if (testrayRunId > 0) {
			testrayCaseResultProperties.put("testrayRunId", testrayRunId);
		}

		long testrayTeamId = ParamUtil.getLong(request, "testrayTeamId");

		testrayCaseResultProperties.put("testrayTeamId", testrayTeamId);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayCaseTypeModelImpl.TABLE_NAME, "name", true);

		List<TestrayCaseType> testrayCaseTypes = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		renderRequest.setAttribute("testrayCaseTypeComposites", TestrayCompositeUtil.getComposites(testrayCaseTypes, TestrayCaseTypeComposite.class, new Class<?>[] {TestrayCaseType.class, ThemeDisplay.class, Map.class}, new Object[] {themeDisplay, testrayCaseResultProperties}));

		if (testrayRunId > 0) {
			renderRequest.setAttribute("testrayRunComposite", new TestrayRunComposite(TestrayRunLocalServiceUtil.fetchTestrayRun(testrayRunId), themeDisplay, testrayCaseResultProperties));
		}

		List<TestrayTeam> testrayTeams = TestrayTeamUtil.getTestrayTeams(testrayBuild.getTestrayProjectId());

		renderRequest.setAttribute("testrayTeams", testrayTeams);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name"}, parameterTypes = {Long.class, String.class})
	public void update() throws Exception {
		TestrayCaseType testrayCaseType = _fetchTestrayCaseType();

		_validateUpdate(testrayCaseType);

		updateModel(testrayCaseType);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayCaseTypeJSONObject(testrayCaseType));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id"}, parameterTypes = {String.class})
	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		TestrayCaseType testrayCaseType = _fetchTestrayCaseType();

		_validateView(testrayCaseType);

		respondWith(_getTestrayCaseTypeJSONObject(testrayCaseType));
	}

	private TestrayCaseType _fetchTestrayCaseType() throws Exception {
		TestrayCaseType testrayCaseType = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayCaseType = TestrayCaseTypeLocalServiceUtil.fetchTestrayCaseType(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			List<TestrayCaseType> testrayCaseTypes = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", id.substring(1)});

			if (!testrayCaseTypes.isEmpty()) {
				testrayCaseType = testrayCaseTypes.get(0);
			}
		}

		return testrayCaseType;
	}

	private JSONObject _getTestrayCaseTypeJSONObject(TestrayCaseType testrayCaseType) throws Exception {
		TestrayCaseTypeComposite testrayCaseTypeComposite = new TestrayCaseTypeComposite(testrayCaseType, themeDisplay);

		return testrayCaseTypeComposite.getJSONObject();
	}

	private JSONArray _getTestrayCaseTypesJSONArray(List<TestrayCaseType> testrayCaseTypes) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayCaseType testrayCaseType : testrayCaseTypes) {
			jsonArray.put(_getTestrayCaseTypeJSONObject(testrayCaseType));
		}

		return jsonArray;
	}

	private void _validateAdd() throws Exception {
		_validateName();
	}

	private void _validateDelete(TestrayCaseType testrayCaseType) throws Exception {
		_validateTestrayCaseType(testrayCaseType);

		AlloyServiceInvoker testrayCaseAlloyServiceInvoker = new AlloyServiceInvoker(TestrayCase.class.getName());

		long testrayCasesCount = testrayCaseAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayCaseTypeId", testrayCaseType.getTestrayCaseTypeId()});

		if (testrayCasesCount > 0) {
			throw new AlloyException("the-type-cannot-be-deleted-because-it-has-associated-cases", false);
		}
	}

	private void _validateEdit(TestrayCaseType testrayCaseType) throws Exception {
		_validateTestrayCaseType(testrayCaseType);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-type-name-is-invalid", false);
		}

		List<TestrayCaseType> testrayCaseTypes = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", name});

		if (!testrayCaseTypes.isEmpty()) {
			long testrayCaseTypeId = ParamUtil.getLong(request, "id");

			TestrayCaseType testrayCaseType = testrayCaseTypes.get(0);

			if (testrayCaseType.getTestrayCaseTypeId() != testrayCaseTypeId) {
				throw new AlloyException("the-type-name-already-exists", false);
			}
		}
	}

	private void _validateTestrayCaseType(TestrayCaseType testrayCaseType) throws Exception {
		if ((testrayCaseType == null) || testrayCaseType.isNew()) {
			String id = ParamUtil.getString(request, "id");

			if (Validator.isNumber(id)) {
				throw new AlloyException(translate("the-type-with-id-x-does-not-exist", GetterUtil.getLong(id)), false);
			}
			else if (id.indexOf(StringPool.UNDERLINE) == 0) {
				throw new AlloyException(translate("the-type-with-name-x-does-not-exist", id.substring(1)), false);
			}
		}
	}

	private void _validateUpdate(TestrayCaseType testrayCaseType) throws Exception {
		_validateTestrayCaseType(testrayCaseType);

		_validateName();
	}

	private void _validateView(TestrayCaseType testrayCaseType) throws Exception {
		_validateTestrayCaseType(testrayCaseType);
	}

}
%>