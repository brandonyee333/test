/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.subscription.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.blogs.test.util.BlogsTestUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.mail.MailMessage;
import com.liferay.portal.test.mail.MailServiceTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SynchronousMailTestRule;
import com.liferay.portlet.blogs.constants.BlogsConstants;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Sergio González
 */
@RunWith(Arquillian.class)
@Sync
public class BlogsSubscriptionEmailEntryUpdatedCommentTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), SynchronousMailTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addGroupUser(_group, RoleConstants.SITE_MEMBER);
	}

	@Test
	public void testEmailEntryUpdatedNotSentIfNotSpecified() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		BlogsTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.ADD);

		BlogsEntry entry = BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), serviceContext);

		BlogsEntryLocalServiceUtil.subscribe(
			_user.getUserId(), _group.getGroupId());

		BlogsTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.UPDATE);

		serviceContext.setAttribute(
			"emailEntryUpdatedComment", "This entry was updated.");

		BlogsEntryLocalServiceUtil.updateEntry(
			TestPropsValues.getUserId(), entry.getEntryId(),
			StringUtil.randomString(), StringUtil.randomString(),
			serviceContext);

		Assert.assertEquals(0, MailServiceTestUtil.getInboxSize());
	}

	@Test
	public void testEmailEntryUpdatedSentWithEmailEntryUpdatedComment()
		throws Exception {

		setUpBlogsGroupServiceSettings();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		BlogsTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.ADD);

		BlogsEntry entry = BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), serviceContext);

		BlogsEntryLocalServiceUtil.subscribe(
			_user.getUserId(), _group.getGroupId());

		BlogsTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.UPDATE);

		serviceContext.setAttribute(
			"emailEntryUpdatedComment", "This entry was updated.");
		serviceContext.setAttribute(
			"sendEmailEntryUpdated", Boolean.TRUE.toString());

		BlogsEntryLocalServiceUtil.updateEntry(
			TestPropsValues.getUserId(), entry.getEntryId(),
			StringUtil.randomString(), StringUtil.randomString(),
			serviceContext);

		MailMessage message = MailServiceTestUtil.getLastMailMessage();

		Assert.assertEquals("This entry was updated.", message.getBody());
	}

	@Test
	public void testEmailEntryUpdatedSentWithEmptyEmailEntryUpdatedComment()
		throws Exception {

		setUpBlogsGroupServiceSettings();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		BlogsTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.ADD);

		BlogsEntry entry = BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), serviceContext);

		BlogsEntryLocalServiceUtil.subscribe(
			_user.getUserId(), _group.getGroupId());

		BlogsTestUtil.populateNotificationsServiceContext(
			serviceContext, Constants.UPDATE);

		serviceContext.setAttribute(
			"sendEmailEntryUpdated", Boolean.TRUE.toString());

		BlogsEntryLocalServiceUtil.updateEntry(
			TestPropsValues.getUserId(), entry.getEntryId(),
			StringUtil.randomString(), StringUtil.randomString(),
			serviceContext);

		MailMessage message = MailServiceTestUtil.getLastMailMessage();

		Assert.assertEquals(message.getBody(), StringPool.NEW_LINE);
	}

	protected void setUpBlogsGroupServiceSettings() throws Exception {
		Settings settings = SettingsFactoryUtil.getSettings(
			new GroupServiceSettingsLocator(
				_group.getGroupId(), BlogsConstants.SERVICE_NAME));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		String subscriptionBodyPreferencesKey =
			LocalizationUtil.getLocalizedName(
				"emailEntryUpdatedBody",
				LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

		modifiableSettings.setValue(
			subscriptionBodyPreferencesKey, "[$BLOGS_ENTRY_UPDATE_COMMENT$]");

		modifiableSettings.store();
	}

	@DeleteAfterTestRun
	private Group _group;

	@DeleteAfterTestRun
	private User _user;

}