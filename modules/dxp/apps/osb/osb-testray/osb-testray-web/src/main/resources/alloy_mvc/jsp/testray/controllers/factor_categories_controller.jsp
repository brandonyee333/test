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
		setAlloyServiceInvokerClass(TestrayFactorCategory.class);
		setPermissioned(true);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"name"}, parameterTypes = {String.class})
	public void add() throws Exception {
		TestrayFactorCategory testrayFactorCategory = TestrayFactorCategoryLocalServiceUtil.createTestrayFactorCategory(0);

		_validateAdd();

		updateModel(testrayFactorCategory);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayFactorCategoryJSONObject(testrayFactorCategory));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		TestrayFactorCategory testrayFactorCategory = TestrayFactorCategoryLocalServiceUtil.createTestrayFactorCategory(0);

		renderRequest.setAttribute("testrayFactorCategory", testrayFactorCategory);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name"}, parameterTypes = {Long.class, String.class})
	public void delete() throws Exception {
		TestrayFactorCategory testrayFactorCategory = _fetchTestrayFactorCategory();

		_validateDelete(testrayFactorCategory);

		TestrayFactorCategoryLocalServiceUtil.deleteTestrayFactorCategory(testrayFactorCategory);

		if (isRespondingTo("json")) {
			respondWith(JSONFactoryUtil.getNullJSON());

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		TestrayFactorCategory testrayFactorCategory = _fetchTestrayFactorCategory();

		_validateEdit(testrayFactorCategory);

		renderRequest.setAttribute("testrayFactorCategory", testrayFactorCategory);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "orderByCol", "orderByType", "start"}, parameterTypes = {Integer.class, Integer.class, Integer.class, String.class, String.class, Integer.class})
	public void index() throws Exception {
		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		String orderByCol = ParamUtil.getString(request, "orderByCol", "name");

		renderRequest.setAttribute("orderByCol", orderByCol);

		String orderByType = ParamUtil.getString(request, "orderByType", "asc");

		renderRequest.setAttribute("orderByType", orderByType);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayFactorCategoryModelImpl.TABLE_NAME, orderByCol, orderByType.equals("asc"));

		List<TestrayFactorCategory> testrayFactorCategories = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, startAndEnd[0], startAndEnd[1], obc);

		renderRequest.setAttribute("testrayFactorCategories", testrayFactorCategories);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayFactorCategoriesJSONArray(testrayFactorCategories));

			return;
		}

		long testrayFactorCategoriesCount = alloyServiceInvoker.executeDynamicQueryCount(new Object[] {"groupId", themeDisplay.getScopeGroupId()});

		renderRequest.setAttribute("testrayFactorCategoriesCount", testrayFactorCategoriesCount);
	}

	public void select() throws Exception {
		long testrayRoutineId = ParamUtil.getLong(request, "testrayRoutineId");

		renderRequest.setAttribute("testrayRoutineId", testrayRoutineId);

		List<KeyValuePair> currentTestrayFactorCategories = new ArrayList<KeyValuePair>();

		List<TestrayFactor> testrayFactors = TestrayFactorUtil.getTestrayFactors(TestrayRoutine.class.getName(), testrayRoutineId);

		for (TestrayFactor testrayFactor : testrayFactors) {
			currentTestrayFactorCategories.add(new KeyValuePair(String.valueOf(testrayFactor.getTestrayFactorCategoryId()), testrayFactor.getTestrayFactorCategoryName()));
		}

		renderRequest.setAttribute("currentTestrayFactorCategories", currentTestrayFactorCategories);

		List<KeyValuePair> availableTestrayFactorCategories = new ArrayList<KeyValuePair>();

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(TestrayFactorCategoryModelImpl.TABLE_NAME, "name", true);

		List<TestrayFactorCategory> testrayFactorCategories = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId()}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);

		for (TestrayFactorCategory testrayFactorCategory : testrayFactorCategories) {
			availableTestrayFactorCategories.add(new KeyValuePair(String.valueOf(testrayFactorCategory.getTestrayFactorCategoryId()), testrayFactorCategory.getName()));
		}

		availableTestrayFactorCategories.removeAll(currentTestrayFactorCategories);

		renderRequest.setAttribute("availableTestrayFactorCategories", availableTestrayFactorCategories);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"id", "name"}, parameterTypes = {Long.class, String.class})
	public void update() throws Exception {
		TestrayFactorCategory testrayFactorCategory = _fetchTestrayFactorCategory();

		_validateUpdate(testrayFactorCategory);

		updateModel(testrayFactorCategory);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayFactorCategoryJSONObject(testrayFactorCategory));

			return;
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id", "name"}, parameterTypes = {Long.class, String.class})
	public void view() throws Exception {
		TestrayFactorCategory testrayFactorCategory = _fetchTestrayFactorCategory();

		_validateTestrayFactorCategory(testrayFactorCategory);

		if (isRespondingTo("json")) {
			respondWith(_getTestrayFactorCategoryJSONObject(testrayFactorCategory));

			return;
		}
	}

	private TestrayFactorCategory _fetchTestrayFactorCategory() throws Exception {
		TestrayFactorCategory testrayFactorCategory = null;

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			testrayFactorCategory = TestrayFactorCategoryLocalServiceUtil.fetchTestrayFactorCategory(GetterUtil.getLong(id));
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			List<TestrayFactorCategory> testrayFactorCategories = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", id.substring(1)});

			if (!testrayFactorCategories.isEmpty()) {
				testrayFactorCategory = testrayFactorCategories.get(0);
			}
		}

		return testrayFactorCategory;
	}

	private JSONArray _getTestrayFactorCategoriesJSONArray(List<TestrayFactorCategory> testrayFactorCategories) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TestrayFactorCategory testrayFactorCategory : testrayFactorCategories) {
			jsonArray.put(_getTestrayFactorCategoryJSONObject(testrayFactorCategory));
		}

		return jsonArray;
	}

	private JSONObject _getTestrayFactorCategoryJSONObject(TestrayFactorCategory testrayFactorCategory) throws Exception {
		TestrayFactorCategoryComposite testrayFactorCategoryComposite = new TestrayFactorCategoryComposite(testrayFactorCategory);

		return testrayFactorCategoryComposite.getJSONObject();
	}

	private void _validateAdd() throws Exception {
		_validateName();
	}

	private void _validateDelete(TestrayFactorCategory testrayFactorCategory) throws Exception {
		_validateTestrayFactorCategory(testrayFactorCategory);

		AlloyServiceInvoker testrayFactorOptionAlloyServiceInvoker = new AlloyServiceInvoker(TestrayFactorOption.class.getName());

		long testrayFactorOptionsCount = testrayFactorOptionAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayFactorCategoryId", testrayFactorCategory.getTestrayFactorCategoryId()});

		if (testrayFactorOptionsCount > 0) {
			throw new AlloyException("the-category-cannot-be-deleted-because-it-has-associated-options", false);
		}

		AlloyServiceInvoker testrayFactorAlloyServiceInvoker = new AlloyServiceInvoker(TestrayFactor.class.getName());

		long testrayFactorsCount = testrayFactorAlloyServiceInvoker.executeDynamicQueryCount(new Object[] {"testrayFactorCategoryId", testrayFactorCategory.getTestrayFactorCategoryId()});

		if (testrayFactorsCount > 0) {
			throw new AlloyException("the-category-cannot-be-deleted-because-it-has-associated-test-structures", false);
		}
	}

	private void _validateEdit(TestrayFactorCategory testrayFactorCategory) throws Exception {
		_validateTestrayFactorCategory(testrayFactorCategory);
	}

	private void _validateName() throws Exception {
		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			throw new AlloyException("the-category-name-is-invalid", false);
		}

		List<TestrayFactorCategory> testrayFactorCategories = alloyServiceInvoker.executeDynamicQuery(new Object[] {"groupId", themeDisplay.getScopeGroupId(), "name", name});

		if (!testrayFactorCategories.isEmpty()) {
			long testrayFactorCategoryId = ParamUtil.getLong(request, "id");

			TestrayFactorCategory testrayFactorCategory = testrayFactorCategories.get(0);

			if (testrayFactorCategory.getTestrayFactorCategoryId() != testrayFactorCategoryId) {
				throw new AlloyException("the-category-name-already-exists", false);
			}
		}
	}

	private void _validateTestrayFactorCategory(TestrayFactorCategory testrayFactorCategory) throws Exception {
		if (testrayFactorCategory != null) {
			return;
		}

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			throw new AlloyException(translate("the-category-with-id-x-does-not-exist", id), false);
		}
		else if (id.indexOf(StringPool.UNDERLINE) == 0) {
			throw new AlloyException(translate("the-category-with-name-x-does-not-exist", id.substring(1)), false);
		}
		else if (Validator.isNotNull(id)) {
			throw new AlloyException(translate("the-category-identifier-x-is-invalid", id), false);
		}
		else {
			throw new AlloyException("a-category-id-or-name-is-required");
		}
	}

	private void _validateUpdate(TestrayFactorCategory testrayFactorCategory) throws Exception {
		_validateTestrayFactorCategory(testrayFactorCategory);

		_validateName();
	}

}
%>