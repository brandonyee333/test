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
		setAlloyServiceInvokerClass(User.class);
		setPermissioned(true);
	}

	public void add() throws Exception {
		_validateAdd();

		String emailAddress = ParamUtil.getString(request, "emailAddress");
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		String password1 = ParamUtil.getString(request, "password1");
		String password2 = ParamUtil.getString(request, "password2");
		String screenName = ParamUtil.getString(request, "screenName");

		Calendar calendar = CalendarFactoryUtil.getCalendar();

		User user = UserLocalServiceUtil.addUser(themeDisplay.getUserId(), themeDisplay.getCompanyId(), false, password1, password2, false, screenName, emailAddress, 0, StringPool.BLANK, themeDisplay.getLocale(), firstName, StringPool.BLANK, lastName, 0, 0, true, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR), StringPool.BLANK, new long[0], new long[0], new long[0], new long[0], true, new ServiceContext());

		long[] selUserRoleIds = ParamUtil.getLongValues(request, "selUserRoleIds");

		RoleLocalServiceUtil.addUserRoles(user.getUserId(), selUserRoleIds);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void create() throws Exception {
		User user = UserLocalServiceUtil.createUser(0);

		renderRequest.setAttribute("selUser", user);

		AlloyServiceInvoker roleAlloyServiceInvoker = new AlloyServiceInvoker(Role.class.getName());

		List<Role> roles = roleAlloyServiceInvoker.executeDynamicQuery(new Object[] {"companyId", themeDisplay.getCompanyId(), "type", RoleConstants.TYPE_REGULAR, "subtype", TestrayUserConstants.ROLE_SUBTYPE_TESTRAY});

		renderRequest.setAttribute("roles", roles);
	}

	public void delete() throws Exception {
		User user = _fetchUser();

		_validateDelete(user);

		UserLocalServiceUtil.deleteUser(user);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void edit() throws Exception {
		User user = _fetchUser();

		_validateEdit(user);

		renderRequest.setAttribute("selUser", user);

		AlloyServiceInvoker roleAlloyServiceInvoker = new AlloyServiceInvoker(Role.class.getName());

		List<Role> roles = roleAlloyServiceInvoker.executeDynamicQuery(new Object[] {"companyId", themeDisplay.getCompanyId(), "type", RoleConstants.TYPE_REGULAR, "subtype", TestrayUserConstants.ROLE_SUBTYPE_TESTRAY});

		roles = new ArrayList<Role>(roles);

		roles.sort(new TestrayRoleComparator());

		renderRequest.setAttribute("roles", roles);

		renderRequest.setAttribute("selUserRoleIds", ListUtil.toList(user.getRoleIds()));
	}

	public void editPassword() throws Exception {
		User user = _fetchUser();

		_validateEditPassword(user);

		renderRequest.setAttribute("selUser", user);

		render("users/edit_password");
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"cur", "delta", "end", "name", "start"}, parameterTypes = {Integer.class, Integer.class, Integer.class, String.class, Integer.class})
	public void index() throws Exception {
		String name = ParamUtil.getString(request, "name");

		int[] startAndEnd = TestrayUtil.getStartAndEnd(request, portletRequest, portletURL);

		Sort[] sorts = {
			new Sort("firstName", false),
			new Sort("lastName", false)
		};

		Hits hits = UserLocalServiceUtil.search(themeDisplay.getCompanyId(), name, WorkflowConstants.STATUS_ANY, null, startAndEnd[0], startAndEnd[1], sorts);

		List<User> users = UsersAdminUtil.getUsers(hits);

		if (isRespondingTo("json")) {
			respondWith(_getUsersJSONArray(users));
		}
		else {
			renderRequest.setAttribute("users", users);
			renderRequest.setAttribute("usersCount", hits.getLength());
		}
	}

	public void select() throws Exception {
		String submitAction = ParamUtil.getString(request, "submitAction", "updateUser");

		renderRequest.setAttribute("submitAction", submitAction);

		String submitController = ParamUtil.getString(request, "submitController", "case_results");

		renderRequest.setAttribute("submitController", submitController);

		String submitIdName = ParamUtil.getString(request, "submitIdName");

		renderRequest.setAttribute("submitIdName", submitIdName);

		long submitIdValue = ParamUtil.getLong(request, "submitIdValue");

		renderRequest.setAttribute("submitIdValue", submitIdValue);

		TestrayUtil.setPortletURL(request, portletURL, "submitAction", "submitController", "submitIdName", "submitIdValue");

		String keywords = ParamUtil.getString(request, "keywords");

		SearchContainer<User> searchContainer = new SearchContainer<User>(portletRequest, portletURL, null, null);

		Sort[] sorts = {
			new Sort("firstName", false),
			new Sort("lastName", false)
		};

		Hits hits = UserLocalServiceUtil.search(themeDisplay.getCompanyId(), keywords, WorkflowConstants.STATUS_APPROVED, null, searchContainer.getStart(), searchContainer.getEnd(), sorts);

		renderRequest.setAttribute("users", UsersAdminUtil.getUsers(hits));
		renderRequest.setAttribute("usersCount", hits.getLength());
	}

	public void update() throws Exception {
		User user = _fetchUser();

		_validateUpdate(user);

		String emailAddress = ParamUtil.getString(request, "emailAddress");
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		String screenName = ParamUtil.getString(request, "screenName");

		Contact contact = user.getContact();

		Date birthdate = user.getBirthday();

		Calendar birthdateCalendar = CalendarFactoryUtil.getCalendar(birthdate.getTime());

		UserLocalServiceUtil.updateUser(user.getUserId(), null, null, null, false, null, null, screenName, emailAddress, user.getFacebookId(), user.getOpenId(), true, null, user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(), user.getComments(), firstName, user.getMiddleName(), lastName, contact.getPrefixId(), contact.getSuffixId(), user.isMale(), birthdateCalendar.get(Calendar.MONTH), birthdateCalendar.get(Calendar.DAY_OF_MONTH), birthdateCalendar.get(Calendar.YEAR), contact.getSmsSn(), contact.getFacebookSn(), contact.getJabberSn(), contact.getSkypeSn(), contact.getTwitterSn(), user.getJobTitle(), user.getGroupIds(), user.getOrganizationIds(), user.getRoleIds(), null, null, new ServiceContext());

		int status = WorkflowConstants.STATUS_APPROVED;

		boolean active = ParamUtil.getBoolean(request, "active", user.getStatus() == 0);

		if (!active) {
			status = WorkflowConstants.STATUS_INACTIVE;
		}

		if (status != user.getStatus()) {
			UserLocalServiceUtil.updateStatus(user.getUserId(), status, new ServiceContext());
		}

		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);

		String avatarFileName = uploadPortletRequest.getFileName("avatar");

		if (Validator.isNotNull(avatarFileName)) {
			File avatarFile = uploadPortletRequest.getFile("avatar");

			_validateAvatarFile(avatarFile);

			UserLocalServiceUtil.updatePortrait(user.getUserId(), FileUtil.getBytes(avatarFile));
		}

		if (TestrayPermission.contains(themeDisplay, user, "permissions")) {
			AlloyServiceInvoker roleAlloyServiceInvoker = new AlloyServiceInvoker(Role.class.getName());

			DynamicQuery roleDynamicQuery = roleAlloyServiceInvoker.buildDynamicQuery(new Object[] {"companyId", themeDisplay.getCompanyId(), "type", RoleConstants.TYPE_REGULAR, "subtype", TestrayUserConstants.ROLE_SUBTYPE_TESTRAY});

			Property roleIdProperty = PropertyFactoryUtil.forName("roleId");

			roleDynamicQuery.add(roleIdProperty.in(user.getRoleIds()));

			Projection roleIdProjection = ProjectionFactoryUtil.property("roleId");

			roleDynamicQuery.setProjection(roleIdProjection);

			long[] userRoleIds = ArrayUtil.toLongArray(roleAlloyServiceInvoker.executeDynamicQuery(roleDynamicQuery));

			long[] selUserRoleIds = ParamUtil.getLongValues(request, "selUserRoleIds");

			if ((userRoleIds.length != selUserRoleIds.length) || !ArrayUtil.containsAll(userRoleIds, selUserRoleIds)) {
				RoleLocalServiceUtil.deleteUserRoles(user.getUserId(), userRoleIds);

				RoleLocalServiceUtil.addUserRoles(user.getUserId(), selUserRoleIds);
			}
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void updatePassword() throws Exception {
		User user = _fetchUser();

		_validateUpdatePassword(user);

		String password1 = ParamUtil.getString(request, "password1");
		String password2 = ParamUtil.getString(request, "password2");

		UserLocalServiceUtil.updatePassword(user.getUserId(), password1, password2, false, true);

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	@JSONWebServiceMethod(lifecycle = PortletRequest.RENDER_PHASE, parameterNames = {"id"}, parameterTypes = {Long.class})
	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		User user = _fetchUser();

		_validateView(user);

		respondWith(_getUserJSONObject(user));
	}

	private User _fetchUser() throws Exception {
		long userId = ParamUtil.getLong(request, "id");

		return UserLocalServiceUtil.fetchUser(userId);
	}

	private JSONObject _getUserJSONObject(User user) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (user.getStatus() == 0) {
			jsonObject.put("active", true);
		}
		else {
			jsonObject.put("active", false);
		}

		jsonObject.put("avatarURL", themeDisplay.getURLPortal() + user.getPortraitURL(themeDisplay));
		jsonObject.put("emailAddress", user.getEmailAddress());
		jsonObject.put("firstName", user.getFirstName());
		jsonObject.put("fullName", user.getFullName());
		jsonObject.put("lastName", user.getLastName());

		JSONArray rolesJSONArray = JSONFactoryUtil.createJSONArray();

		for (Role role : user.getRoles()) {
			if (!Objects.equals(role.getSubtype(), TestrayUserConstants.ROLE_SUBTYPE_TESTRAY)) {
				continue;
			}

			JSONObject roleJSONObject = JSONFactoryUtil.createJSONObject();

			roleJSONObject.put("name", role.getName());
			roleJSONObject.put("roleId", role.getRoleId());

			rolesJSONArray.put(roleJSONObject);
		}

		jsonObject.put("roles", rolesJSONArray);

		jsonObject.put("screenName", user.getScreenName());
		jsonObject.put("userId", user.getUserId());

		return jsonObject;
	}

	private JSONArray _getUsersJSONArray(List<User> users) throws Exception {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (User user : users) {
			jsonArray.put(_getUserJSONObject(user));
		}

		return jsonArray;
	}

	private void _validateAdd() throws Exception {
		_validateEmailAddress(0);
		_validateFirstName();
		_validateLastName();
		_validatePasswords();
		_validateRoleIds();
		_validateScreenName(0);
	}

	private void _validateDelete(User user) throws Exception {
		TestrayValidator.validateBaseModel(request, user, "the-user-with-id-x-does-not-exist", "id");
	}

	private void _validateAvatarFile(File avatarFile) throws Exception {
		String extension = StringPool.PERIOD + FileUtil.getExtension(avatarFile.getName());

		if (!ArrayUtil.contains(PortletPropsValues.IMAGE_EXTENSIONS, extension)) {
			throw new AlloyException("image-type-is-invalid", false);
		}
	}

	private void _validateEdit(User user) throws Exception {
		TestrayValidator.validateBaseModel(request, user, "the-user-with-id-x-does-not-exist", "id");
	}

	private void _validateEditPassword(User user) throws Exception {
		TestrayValidator.validateBaseModel(request, user, "the-user-with-id-x-does-not-exist", "id");
	}

	private void _validateEmailAddress(long userId) throws Exception {
		String emailAddress = ParamUtil.getString(request, "emailAddress");

		if (Validator.isNull(emailAddress) || !Validator.isEmailAddress(emailAddress)) {
			throw new AlloyException("you-must-provide-a-valid-email-address", false);
		}

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(themeDisplay.getCompanyId(), emailAddress);

		if ((user != null) && (userId != user.getUserId())) {
			throw new AlloyException(TestrayValidator.translate(request, "a-user-with-email-address-x-already-exists", emailAddress), false);
		}
	}

	private void _validateFirstName() throws Exception {
		String firstName = ParamUtil.getString(request, "firstName");

		if (Validator.isNull(firstName)) {
			throw new AlloyException("you-must-provide-a-first-name", false);
		}
	}

	private void _validateLastName() throws Exception {
		String lastName = ParamUtil.getString(request, "lastName");

		if (Validator.isNull(lastName)) {
			throw new AlloyException("you-must-provide-a-last-name", false);
		}
	}

	private void _validatePasswords() throws Exception {
		String password1 = ParamUtil.getString(request, "password1");
		String password2 = ParamUtil.getString(request, "password2");

		if (Validator.isNull(password1) || Validator.isNull(password2)) {
			throw new AlloyException("you-must-provide-and-confirm-a-password", false);
		}
		else if (!password1.equals(password2)) {
			throw new AlloyException("passwords-do-not-match", false);
		}
	}

	private void _validateRoleIds() throws Exception {
		long[] selUserRoleIds = ParamUtil.getLongValues(request, "selUserRoleIds");

		if (ArrayUtil.isEmpty(selUserRoleIds)) {
			throw new AlloyException("please-select-one-or-more-roles", false);
		}

		for (long selUserRoleId : selUserRoleIds) {
			Role selUserRole = RoleLocalServiceUtil.fetchRole(selUserRoleId);

			if (selUserRole == null) {
				throw new AlloyException(TestrayValidator.translate(request, "role-with-id-x-does-not-exist", selUserRoleId), false);
			}
		}
	}

	private void _validateScreenName(long userId) throws Exception {
		String screenName = ParamUtil.getString(request, "screenName");

		if (Validator.isNull(screenName)) {
			throw new AlloyException("you-must-provide-a-screen-name", false);
		}

		User user = UserLocalServiceUtil.fetchUserByScreenName(themeDisplay.getCompanyId(), screenName);

		if ((user != null) && (userId != user.getUserId())) {
			throw new AlloyException(TestrayValidator.translate(request, "a-user-with-screen-name-x-already-exists", screenName), false);
		}
	}

	private void _validateUpdate(User user) throws Exception {
		TestrayValidator.validateBaseModel(request, user, "the-user-with-id-x-does-not-exist", "id");

		_validateEmailAddress(user.getUserId());
		_validateFirstName();
		_validateLastName();
		_validateRoleIds();
		_validateScreenName(user.getUserId());
	}

	private void _validateUpdatePassword(User user) throws Exception {
		TestrayValidator.validateBaseModel(request, user, "the-user-with-id-x-does-not-exist", "id");

		_validatePasswords();
	}

	private void _validateView(User user) throws Exception {
		TestrayValidator.validateBaseModel(request, user, "the-user-with-id-x-does-not-exist", "id");
	}

}
%>