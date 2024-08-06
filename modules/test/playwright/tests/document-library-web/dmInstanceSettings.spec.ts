import {expect, mergeTests} from '@playwright/test';

import {isolatedSiteTest} from '../../fixtures/isolatedSiteTest';
import {loginTest} from '../../fixtures/loginTest';
import {documentLibraryPagesTest} from './fixtures/documentLibraryPagesTest';

export const CheckOverwriteMaxSizetest = mergeTests(
    documentLibraryPagesTest,
	isolatedSiteTest,
	loginTest()
);

CheckOverwriteMaxSizetest('LPD-17827 Updating Maximum File Upload Size at Instance level, not overrides site configuration', 
    async ({
        fileSizeLimitsInstanceSettingsPage,
        fileSizeLimitsSiteSettingsPage,
        page,
	 
    }) => {

        await fileSizeLimitsInstanceSettingsPage.goto();
        await fileSizeLimitsInstanceSettingsPage.modifyInputAndSave('Maximum File Upload Size','2000');
        await fileSizeLimitsSiteSettingsPage.goto();
        await fileSizeLimitsSiteSettingsPage.modifyInputAndSave('Maximum File Upload Size','1000');
        await fileSizeLimitsInstanceSettingsPage.goto();
        await fileSizeLimitsInstanceSettingsPage.modifyInputAndSave('Maximum File Upload Size','2000');
        await fileSizeLimitsSiteSettingsPage.goto();

        await expect(page.getByLabel('Maximum File Upload Size')).toHaveValue('1000');

});
