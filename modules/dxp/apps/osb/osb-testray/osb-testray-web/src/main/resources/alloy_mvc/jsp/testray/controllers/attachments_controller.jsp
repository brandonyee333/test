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

	@JSONWebServiceMethod(lifecycle = PortletRequest.ACTION_PHASE, parameterNames = {"file", "name", "testrayCaseResultId"}, parameterTypes = {File.class, String.class, String.class})
	public void add() throws Exception {
		UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(portletRequest);

		_validateAdd(uploadPortletRequest);

		File file = uploadPortletRequest.getFile("file");

		File attachmentFile = TestrayAttachmentUtil.writeFile(file);

		Path attachmentsPath = Paths.get(PortletPropsValues.TESTRAY_AUTOMATION_ATTACHMENT_PATH);

		Path attachmentPath = attachmentsPath.relativize(attachmentFile.toPath());

		long testrayCaseResultId = ParamUtil.getLong(uploadPortletRequest, "testrayCaseResultId");

		if (testrayCaseResultId > 0) {
			TestrayCaseResult testrayCaseResult = TestrayCaseResultLocalServiceUtil.getTestrayCaseResult(testrayCaseResultId);

			String name = ParamUtil.getString(uploadPortletRequest, "name");

			TestrayCaseResultUtil.putAttachment(this, testrayCaseResult, name, attachmentPath.toString());
		}

		WindowState windowState = actionRequest.getWindowState();

		if (isRespondingTo()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("path", attachmentPath.toString());

			respondWith(jsonObject);
		}
		else if (windowState.equals(LiferayWindowState.POP_UP)) {
			setOpenerSuccessMessage();

			render("close");
		}
		else {
			addSuccessMessage();

			String redirect = ParamUtil.getString(uploadPortletRequest, "redirect");

			redirectTo(redirect);
		}
	}

	private void _validateAdd(UploadPortletRequest uploadPortletRequest) throws Exception {
		File file = uploadPortletRequest.getFile("file");

		if ((file == null) || (file.length() == 0)) {
			throw new AlloyException("please-supply-a-nonempty-file", false);
		}

		String name = ParamUtil.getString(uploadPortletRequest, "name");
		long testrayCaseResultId = ParamUtil.getLong(uploadPortletRequest, "testrayCaseResultId");

		if (testrayCaseResultId > 0) {
			TestrayValidator.validateClassIdentifier(uploadPortletRequest, TestrayCaseResult.class, "the-case-result-with-id-x-does-not-exist", testrayCaseResultId);

			if (Validator.isNull(name)) {
				throw new AlloyException("please-supply-a-name", false);
			}

			TestrayCaseResult testrayCaseResult = TestrayCaseResultLocalServiceUtil.getTestrayCaseResult(testrayCaseResultId);

			if (Validator.isNotNull(testrayCaseResult.getAttachments())) {
				JSONObject attachmentsJSONObject = JSONFactoryUtil.createJSONObject(testrayCaseResult.getAttachments());

				if (attachmentsJSONObject.has(name)) {
					throw new AlloyException("an-attachment-with-the-supplied-name-already-exists", false);
				}
			}
		}
		else if (Validator.isNotNull(name)) {
			throw new AlloyException("please-supply-a-case-result-id", false);
		}
	}

}
%>