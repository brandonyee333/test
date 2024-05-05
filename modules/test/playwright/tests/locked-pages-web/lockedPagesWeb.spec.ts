/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {lockedPagesTest} from './fixtures/lockedPagesTest';
import {loginTest} from '../../fixtures/loginTest';

const test = mergeTests(
	apiHelpersTest,
	lockedPagesTest,
	loginTest()
);

test('the Locked Pages is shown', async ({lockedPages}) => {
	await lockedPages.goto();

	await expect(lockedPages.pageTitle).toBeVisible();
	await expect(lockedPages.lockedPagesMenuItem).toBeVisible();
});

test('the Locked Pages page is shown', async ({lockedPages}) => {
	await lockedPages.goto();
	await lockedPages.goToLockedPages();

	await expect(lockedPages.lockedPagesTitle).toBeVisible();
});
