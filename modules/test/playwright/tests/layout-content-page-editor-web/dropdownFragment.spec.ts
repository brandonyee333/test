/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {featureFlagsTest} from '../../fixtures/featureFlagsTest';
import {isolatedSiteTest} from '../../fixtures/isolatedSiteTest';
import {loginTest} from '../../fixtures/loginTest';
import {pageEditorPagesTest} from '../../fixtures/pageEditorPagesTest';
import getRandomString from '../../utils/getRandomString';
import getFragmentDefinition from './utils/getFragmentDefinition';
import getPageDefinition from './utils/getPageDefinition';

const test = mergeTests(
	apiHelpersTest,
	featureFlagsTest({
		'LPS-178052': true,
	}),
	isolatedSiteTest,
	loginTest(),
	pageEditorPagesTest
);

const VIEWPORTS_VALUES = {
	'Desktop': '280px',
	'Landscape Phone': '0px',
	'Portrait Phone': '0px',
	'Tablet': '0px',
};

const ENTER_KEY = 'Enter';

test('Check dropdown menu is displayed correctly in all resolutions', async ({
	apiHelpers,
	pageEditorPage,
	site,
}) => {
	const dropdownId = getRandomString();

	await test.step('Create dropdown fragment and change the configuration', async () => {
		const fragmentDefinition = getFragmentDefinition({
			id: dropdownId,
			key: 'BASIC_COMPONENT-dropdown',
		});

		const layout = await apiHelpers.headlessDelivery.createSitePage({
			pageDefinition: getPageDefinition([fragmentDefinition]),
			siteId: site.id,
			title: getRandomString(),
		});

		await pageEditorPage.goto(layout, site.friendlyUrlPath);

		await pageEditorPage.selectFragment(dropdownId);

		const dropdownFragment = await pageEditorPage.getFragment(dropdownId);

		const dropdownButton = await dropdownFragment.locator(
			'.dropdown-fragment-toggle'
		);

		await expect(
			dropdownButton.getByText('Dropdown', {exact: true})
		).toBeVisible();

		await pageEditorPage.selectFragment(dropdownId);

		await pageEditorPage.changeFragmentConfiguration({
			fieldLabel: 'CSS Classes',
			fragmentId: dropdownId,
			tab: 'Advanced',
			value: 'd-flex',
		});

		await pageEditorPage.changeFragmentConfiguration({
			fieldLabel: 'CSS Classes',
			fragmentId: dropdownId,
			tab: 'Advanced',
			value: 'justify-content-end',
		});
	});

	await test.step('Check right position in each viewport', async () => {
		for (const [viewport, value] of Object.entries(VIEWPORTS_VALUES)) {
			const isDesktop = viewport === 'Desktop';

			if (!isDesktop) {
				await pageEditorPage.switchViewport(viewport as Viewport);
			}

			await pageEditorPage.selectFragment(dropdownId, isDesktop);

			const dropdownFragment = await pageEditorPage.getFragment(
				dropdownId,
				isDesktop
			);

			const dropdownButton = await dropdownFragment.locator(
				'.dropdown-fragment-toggle'
			);

			// Open dropdown

			await dropdownButton.press(ENTER_KEY);

			const dropdownMenu = await dropdownFragment.locator(
				'.dropdown-fragment-menu'
			);

			await dropdownMenu
				.getByText('Place fragments or widgets here.')
				.waitFor();

			// Check right style

			expect(
				await dropdownMenu.evaluate((element) => {
					return window
						.getComputedStyle(element)
						.getPropertyValue('right');
				})
			).toBe(value);

			// Close dropdown

			await dropdownButton.press(ENTER_KEY);

			await dropdownMenu.waitFor({state: 'hidden'});
		}
	});
});
