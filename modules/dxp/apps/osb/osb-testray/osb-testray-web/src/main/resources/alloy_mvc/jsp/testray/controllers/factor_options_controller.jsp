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

<%@ include file="/alloy_mvc/jsp/util/testray_factor_option_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends TestrayAlloyControllerImpl {

	public AlloyControllerImpl() {
		setAlloyServiceInvokerClass(TestrayFactorOption.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name", "testrayFactorCategoryId", "testrayFactorCategoryName"}, parameterTypes = {String.class, Long.class, String.class})
	public void add() throws Exception {
		TestrayFactorOption testrayFactorOption = TestrayFactorOptionLocalServiceUtil.createTestrayFactorOption(0);

		_validateAdd();

		_setTestrayFactorCategory(testrayFactorOption);

		updateModel(testrayFactorOption);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayFactorOptionJSONObject(testrayFactorOption));

			return;
		}

		setPollIndexStateProperties(Field.ENTRY_CLASS_PK, testrayFactorOption.getTestrayFactorOptionId());

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		TestrayFactorOption testrayFactorOption = TestrayFactorOptionLocalServiceUtil.createTestrayFactorOption(0);

		renderRequest.setAttribute("testrayFactorOption", testrayFactorOption);

		_setTestrayFactorCategoriesParameter();
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "testrayFactorCategoryId"}, parameterTypes = {String.class, Long.class})
	public void delete() throws Exception {
		TestrayFactorOption testrayFactorOption = _fetchTestrayFactorOption();

		_validateDelete(testrayFactorOption);

		TestrayFactorOptionLocalServiceUtil.deleteTestrayFactorOption(testrayFactorOption);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayFactorOption testrayFactorOption = _fetchTestrayFactorOption();

		_validateEdit(testrayFactorOption);

		renderRequest.setAttribute("testrayFactorOption", testrayFactorOption);

		_setTestrayFactorCategoriesParameter();
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "orderByCol", "orderByType", "start", "testrayFactorCategoryId"}, parameterTypes = {Integer.class, Integer.class, Integer.class, String.class, String.class, Integer.class, Long.class})
	public void index() throws Exception {
		String orderByCol = ParamUtil.getString(request, "orderByCol", "testrayFactorCategoryName_sortable");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		Sort[] sorts = {
			new Sort(orderByCol, orderByType.equals("desc")),
			new Sort("testrayFactorCategoryName_sortable", false),
			new Sort("name_sortable", false)
		};

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		AlloySearchResult alloySearchResult = search(null, null, sorts, startAndEnd[0], startAndEnd[1]);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayFactorOptionsJSONArray(TestrayUtil.getSubclassList(alloySearchResult.getBaseModels(), TestrayFactorOption.class)));

			return;
		}

		renderRequest.setAttribute("alloySearchResult", alloySearchResult);

		renderRequest.setAttribute("testrayFactorOptionComposites", TestrayCompositeUtil.getComposites(alloySearchResult.getBaseModels(), TestrayFactorOptionComposite.class));
	}

	public void select() throws Exception {
		_validateSelect();

		String className = ParamUtil.getString(request, "className");
		long classPK = ParamUtil.getLong(request, "classPK");

		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		if (classPK == 0) {
			className = TestrayRoutine.class.getName();

			classPK = testrayRoutineId;
		}

		Set<Long> selectedTestrayFactorOptionIds = SetUtil.fromCollection(TestrayFactorUtil.getTestrayFactorOptionIds(className, classPK));

		boolean multiselect = ParamUtil.getBoolean(request, "multiselect");

		if (multiselect) {
			selectedTestrayFactorOptionIds.addAll(TestrayFactorUtil.getTestrayFactorOptionIds(TestrayRoutine.class.getName(), testrayRoutineId));
		}

		portletRequest.setAttribute("selectedTestrayFactorOptionIds", selectedTestrayFactorOptionIds);

		List<Long> testrayFactorCategoryIds = ListUtil.toList(ParamUtil.getLongValues(request, "testrayFactorCategoryIds"));

		Map<TestrayFactorCategory, List<TestrayFactorOption>> testrayFactorCategoryMap = TestrayFactorCategoryUtil.getTestrayFactorCategoryMap(className, classPK, testrayFactorCategoryIds);

		portletRequest.setAttribute("testrayFactorCategoryMap", testrayFactorCategoryMap);

		if (renderRequest == null) {
			render("factor_options/select");
		}
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name", "testrayFactorCategoryId"}, parameterTypes = {String.class, String.class, Long.class})
	public void update() throws Exception {
		TestrayFactorOption testrayFactorOption = _fetchTestrayFactorOption();

		_validateUpdate(testrayFactorOption);

		updateModel(testrayFactorOption);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayFactorOptionJSONObject(testrayFactorOption));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id", "testrayFactorCategoryId"}, parameterTypes = {String.class, Long.class})
	public void view() throws Exception {
		TestrayFactorOption testrayFactorOption = _fetchTestrayFactorOption();

		if (isRespondingTo("json")) {
			respondWith(_getTestrayFactorOptionJSONObject(testrayFactorOption));

			return;
		}
	}

	@Override
	protected Indexer buildIndexer() {
		return TestrayFactorOptionIndexer.getInstance();
	}

	private TestrayFactorOption _fetchTestrayFactorOption() throws Exception {
		TestrayFactorOption testrayFactorOption = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayFactorOption = TestrayFactorOptionLocalServiceUtil.fetchTestrayFactorOption(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayFactorCategoryId = ParamUtil.getLong(request, "testrayFactorCategoryId");

			List<TestrayFactorOption> testrayFactorOptions = alloyServiceInvoker.executeDynamicQuery(new Object[] {"testrayFactorCategoryId", testrayFactorCategoryId, "name", id.substring(1)});

			if (!testrayFactorOptions.isEmpty()) {
				testrayFactorOption = testrayFactorOptions.get(0);
			}
		}

		return testrayFactorOption;
	}

	private TestrayFactorCategory _getTestrayFactorCategory(String testrayFactorCategoryName) throws Exception {
		AlloyServiceInvoker testrayFactorCategoryAlloyServiceInvoker = new AlloyServiceInvoker(TestrayFactorCategory.class.getName());

		List<TestrayFactorCategory> testrayFactorCategories = testrayFactorCategoryAlloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", testrayFactorCategoryName});

		if (!testrayFactorCategories.isEmpty()) {
			return testrayFactorCategories.get(0);
		}

		return null;
	}

	private JSONObject _getTestrayFactorOptionJSONObject(TestrayFactorOption testrayFactorOption) throws Exception {
		TestrayFactorOptionComposite testrayFactorOptionComposite = new TestrayFactorOptionComposite(testrayFactorOption);

		return testrayFactorOptionComposite.getJSONObject();
	}

	private JSONArray _getTestrayFactorOptionsJSONArray(List<TestrayFactorOption> testrayFactorOptions) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayFactorOption testrayFactorOption : testrayFactorOptions) {
			jsonArray.put(_getTestrayFactorOptionJSONObject(testrayFactorOption));
		}

		return jsonArray;
	}

	private void _setTestrayFactorCategoriesParameter() throws Exception {
		AlloyServiceInvoker testrayFactorCategoryAlloyServiceInvoker = new AlloyServiceInvoker(TestrayFactorCategory.class.getName());

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayFactorCategoryModelImpl.TABLE_NAME, "name", true);

		List<TestrayFactorCategory> testrayFactorCategories = testrayFactorCategoryAlloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		renderRequest.setAttribute("testrayFactorCategories", testrayFactorCategories);
	}

	private void _setTestrayFactorCategory(TestrayFactorOption testrayFactorOption) throws Exception {
		long testrayFactorCategoryId = ParamUtil.getLong(request, "testrayFactorCategoryId", -1);

		if (testrayFactorCategoryId >= 0) {
			testrayFactorOption.setTestrayFactorCategoryId(testrayFactorCategoryId);

			return;
		}

		String testrayFactorCategoryName = ParamUtil.getString(request, "testrayFactorCategoryName", null);

		if (testrayFactorCategoryName != null) {
			TestrayFactorCategory testrayFactorCategory = _getTestrayFactorCategory(testrayFactorCategoryName);

			testrayFactorOption.setTestrayFactorCategoryId(testrayFactorCategory.getTestrayFactorCategoryId());
		}
	}

	private void _validateAdd() throws Exception {
		_validateName();
		_validateTestrayFactorCategory();
	}

	private void _validateDelete(TestrayFactorOption testrayFactorOption) throws Exception {
		_validateTestrayFactorOption(testrayFactorOption);

		AlloyServiceInvoker testrayFactorAlloyServiceInvoker = new AlloyServiceInvoker(TestrayFactor.class.getName());

		long testrayFactorsCount = testrayFactorAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayFactorOptionId", testrayFactorOption.getTestrayFactorOptionId()});

		if (testrayFactorsCount > 0) {
			throw new AlloyException("the-option-cannot-be-deleted-because-it-has-associated-test-structures", false);
		}
	}

	private void _validateEdit(TestrayFactorOption testrayFactorOption) throws Exception {
		_validateTestrayFactorOption(testrayFactorOption);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-option-name-is-invalid", false);
		}

		List<TestrayFactorOption> testrayFactorOptions = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", name});

		if (!testrayFactorOptions.isEmpty()) {
			long testrayFactorOptionId = ParamUtil.getLong(request, "id");

			TestrayFactorOption testrayFactorOption = testrayFactorOptions.get(0);

			if (testrayFactorOption.getTestrayFactorOptionId() != testrayFactorOptionId) {
				throw new AlloyException("the-option-name-already-exists", false);
			}
		}
	}

	private void _validateSelect() throws Exception {
		String className = ParamUtil.getString(request, "className");

		if (className.equals(TestrayRoutine.class.getName())) {
			long[] testrayFactorCategoryIds = ParamUtil.getLongValues(request, "testrayFactorCategoryIds");

			if (testrayFactorCategoryIds.length == 0) {
				throw new AlloyException("please-choose-at-least-one-category", false);
			}
		}
		else {
			long classPK = ParamUtil.getLong(request, "classPK");

			if (classPK == 0) {
				className = TestrayRoutine.class.getName();

				classPK = ParamUtil.getLong(request, "testrayRoutineId");
			}

			List<TestrayFactor> testrayFactors = TestrayFactorUtil.getTestrayFactors(className, classPK);

			if (testrayFactors.isEmpty()) {
				throw new AlloyException("please-select-a-default-stack-for-the-routine-first", false);
			}
		}
	}

	private void _validateTestrayFactorCategory() throws Exception {
		long testrayFactorCategoryId = ParamUtil.getLong(request, "testrayFactorCategoryId", -1);
		String testrayFactorCategoryName = ParamUtil.getString(request, "testrayFactorCategoryName", null);

		if (testrayFactorCategoryId >= 0) {
			_validateTestrayFactorCategoryId(testrayFactorCategoryId);
		}
		else if (testrayFactorCategoryName != null) {
			_validateTestrayFactorCategoryName(testrayFactorCategoryName);
		}
		else {
			throw new AlloyException("the-category-is-invalid", false);
		}
	}

	private void _validateTestrayFactorCategoryId(long testrayFactorCategoryId) throws Exception {
		TestrayFactorCategory testrayFactorCategory = TestrayFactorCategoryLocalServiceUtil.fetchTestrayFactorCategory(testrayFactorCategoryId);

		if (testrayFactorCategory == null) {
			throw new AlloyException(translate("the-category-with-id-x-does-not-exist", testrayFactorCategoryId), false);
		}
	}

	private void _validateTestrayFactorCategoryName(String testrayFactorCategoryName) throws Exception {
		TestrayFactorCategory testrayFactorCategory = _getTestrayFactorCategory(testrayFactorCategoryName);

		if (testrayFactorCategory == null) {
			throw new AlloyException(translate("the-category-with-name-x-does-not-exist", testrayFactorCategoryName), false);
		}
	}

	private void _validateTestrayFactorOption(TestrayFactorOption testrayFactorOption) throws Exception {
		if (testrayFactorOption != null) {
			return;
		}

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			throw new AlloyException(translate("the-option-with-id-x-does-not-exist", id), false);
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			long testrayFactorCategoryId = ParamUtil.getLong(request, "testrayFactorCategoryId");

			TestrayFactorCategory testrayFactorCategory = TestrayFactorCategoryLocalServiceUtil.fetchTestrayFactorCategory(testrayFactorCategoryId);

			if (testrayFactorCategory == null) {
				throw new AlloyException(translate("the-category-with-id-x-does-not-exist", testrayFactorCategoryId), false);
			}
			else {
				throw new AlloyException(translate("the-option-x-does-not-exist-in-category-x", id.substring(1), testrayFactorCategory.getName()), false);
			}
		}
		else if (Validator.isNotNull(id)) {
			throw new AlloyException(translate("the-option-identifier-x-is-invalid", id), false);
		}
		else {
			throw new AlloyException("an-option-id-or-name-is-required");
		}
	}

	private void _validateUpdate(TestrayFactorOption testrayFactorOption) throws Exception {
		_validateTestrayFactorOption(testrayFactorOption);

		_validateName();
		_validateTestrayFactorCategory();
	}

}
%>