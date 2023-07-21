/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.comment.notifications.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.message.boards.kernel.model.MBDiscussion;
import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.model.MBMessageConstants;
import com.liferay.message.boards.kernel.model.MBMessageDisplay;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBDiscussionLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.mail.MailServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SynchronousMailTestRule;
import com.liferay.portlet.messageboards.util.test.MBTestUtil;
import com.liferay.portlet.notifications.test.BaseUserNotificationTestCase;

import java.util.List;

import javax.portlet.PortletPreferences;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Roberto Díaz
 * @author Sergio González
 */
@RunWith(Arquillian.class)
public class CommentUserNotificationTest extends BaseUserNotificationTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), SynchronousMailTestRule.INSTANCE);

	@Override
	public void setUp() throws Exception {
		super.setUp();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId());

		_entry = BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), serviceContext);
	}

	@Test
	public void testAddUserNotificationWhenDiscussionEmailPortalPropertyDisabled()
		throws Exception {

		PortletPreferences portletPreferences =
			givenThatDiscussionEmailCommentsAddedIsDisabled();

		try {
			subscribeToContainer();

			BaseModel<?> baseModel = addBaseModel();

			Assert.assertEquals(0, MailServiceTestUtil.getInboxSize());

			List<JSONObject> userNotificationEventsJSONObjects =
				getUserNotificationEventsJSONObjects(
					user.getUserId(), (Long)baseModel.getPrimaryKeyObj());

			Assert.assertEquals(
				userNotificationEventsJSONObjects.toString(), 1,
				userNotificationEventsJSONObjects.size());

			for (JSONObject userNotificationEventsJSONObject :
					userNotificationEventsJSONObjects) {

				Assert.assertTrue(
					isValidUserNotificationEventObject(
						(Long)baseModel.getPrimaryKeyObj(),
						userNotificationEventsJSONObject));
				Assert.assertEquals(
					UserNotificationDefinition.NOTIFICATION_TYPE_ADD_ENTRY,
					userNotificationEventsJSONObject.getInt(
						"notificationType"));
			}
		}
		finally {
			restorePortletPreferences(portletPreferences);
		}
	}

	@Test
	public void testUpdateUserNotificationWhenDiscussionEmailPortalPropertyDisabled()
		throws Exception {

		PortletPreferences portletPreferences =
			givenThatDiscussionEmailCommentsAddedIsDisabled();

		try {
			BaseModel<?> baseModel = addBaseModel();

			subscribeToContainer();

			BaseModel<?> updatedBasemodel = updateBaseModel(baseModel);

			Assert.assertEquals(0, MailServiceTestUtil.getInboxSize());

			List<JSONObject> userNotificationEventsJSONObjects =
				getUserNotificationEventsJSONObjects(
					user.getUserId(),
					(Long)updatedBasemodel.getPrimaryKeyObj());

			Assert.assertEquals(
				userNotificationEventsJSONObjects.toString(), 1,
				userNotificationEventsJSONObjects.size());

			for (JSONObject userNotificationEventsJSONObject :
					userNotificationEventsJSONObjects) {

				Assert.assertTrue(
					isValidUserNotificationEventObject(
						(Long)baseModel.getPrimaryKeyObj(),
						userNotificationEventsJSONObject));
				Assert.assertEquals(
					UserNotificationDefinition.NOTIFICATION_TYPE_UPDATE_ENTRY,
					userNotificationEventsJSONObject.getInt(
						"notificationType"));
			}
		}
		finally {
			restorePortletPreferences(portletPreferences);
		}
	}

	@Override
	protected BaseModel<?> addBaseModel() throws Exception {
		MBMessageDisplay messageDisplay =
			MBMessageLocalServiceUtil.getDiscussionMessageDisplay(
				TestPropsValues.getUserId(), group.getGroupId(),
				BlogsEntry.class.getName(), _entry.getEntryId(),
				WorkflowConstants.STATUS_APPROVED);

		MBThread thread = messageDisplay.getThread();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId());

		MBTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.ADD);

		return MBMessageLocalServiceUtil.addDiscussionMessage(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			group.getGroupId(), BlogsEntry.class.getName(), _entry.getEntryId(),
			thread.getThreadId(), MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(50),
			serviceContext);
	}

	@Override
	protected String getPortletId() {
		return "com_liferay_comment_web_portlet_CommentPortlet";
	}

	protected PortletPreferences
			givenThatDiscussionEmailCommentsAddedIsDisabled()
		throws Exception {

		PortletPreferences portletPreferences = PrefsPropsUtil.getPreferences(
			user.getCompanyId(), false);

		portletPreferences.setValue(
			PropsKeys.DISCUSSION_EMAIL_COMMENTS_ADDED_ENABLED,
			Boolean.FALSE.toString());

		portletPreferences.store();

		return portletPreferences;
	}

	@Override
	protected boolean isValidUserNotificationEventObject(
			long baseEntryId, JSONObject userNotificationEventJSONObject)
		throws Exception {

		long classPK = userNotificationEventJSONObject.getLong("classPK");

		MBMessage mbMessage = MBMessageLocalServiceUtil.getMessage(baseEntryId);

		if (!mbMessage.isDiscussion()) {
			return false;
		}

		MBDiscussion mbDiscussion =
			MBDiscussionLocalServiceUtil.getThreadDiscussion(
				mbMessage.getThreadId());

		if (mbDiscussion.getDiscussionId() != classPK) {
			return false;
		}

		return true;
	}

	protected void restorePortletPreferences(
			PortletPreferences portletPreferences)
		throws Exception {

		portletPreferences.reset(
			PropsKeys.DISCUSSION_EMAIL_COMMENTS_ADDED_ENABLED);

		portletPreferences.store();
	}

	@Override
	protected void subscribeToContainer() throws Exception {
		MBDiscussionLocalServiceUtil.subscribeDiscussion(
			user.getUserId(), group.getGroupId(), BlogsEntry.class.getName(),
			_entry.getEntryId());
	}

	@Override
	protected BaseModel<?> updateBaseModel(BaseModel<?> baseModel)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId());

		MBTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.UPDATE);

		return MBMessageLocalServiceUtil.updateDiscussionMessage(
			TestPropsValues.getUserId(), (Long)baseModel.getPrimaryKeyObj(),
			BlogsEntry.class.getName(), _entry.getEntryId(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(50),
			serviceContext);
	}

	private BlogsEntry _entry;

}