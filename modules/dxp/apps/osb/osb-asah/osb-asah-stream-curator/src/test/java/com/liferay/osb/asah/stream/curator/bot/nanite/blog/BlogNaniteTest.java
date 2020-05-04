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

package com.liferay.osb.asah.stream.curator.bot.nanite.blog;

import com.liferay.osb.asah.stream.curator.bot.nanite.BaseNaniteTestCase;
import com.liferay.osb.asah.stream.curator.bot.nanite.blog.click.BlogClickNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.blog.social.share.BlogSocialShareNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.blog.traffic.source.BlogTrafficSourceNanite;

import org.junit.Test;

/**
 * @author Marcellus Tavares
 */
public class BlogNaniteTest extends BaseNaniteTestCase {

	@Test
	public void testBlogClickNanite() throws Exception {
		test(BlogClickNanite.class);
	}

	@Test
	public void testBlogSocialShareNanite() throws Exception {
		test(BlogSocialShareNanite.class);
	}

	@Test
	public void testBlogTrafficSourceNanite() throws Exception {
		test(BlogTrafficSourceNanite.class);
	}

}