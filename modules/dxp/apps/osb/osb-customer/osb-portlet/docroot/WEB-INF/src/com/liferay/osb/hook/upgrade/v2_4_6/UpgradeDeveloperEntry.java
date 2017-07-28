/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v2_4_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;

*/

/**
 * @author Ryan Park
 */
public class UpgradeDeveloperEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	protected void doUpgrade() throws Exception {
		upgradeDeveloperEntry();
	}

	protected void updateCountryId(String country, long countryId)
		throws Exception {

		runSQL(
			"update OSB_DeveloperEntry set countryId = " + countryId +
				" where country = \"" + country + "\" and " +
					"(countryId = 0 or countryId is null)");
	}

	protected void upgradeDeveloperEntry() throws Exception {
		if (!tableHasColumn("OSB_DeveloperEntry", "country")) {
			return;
		}

		updateCountryId("afghanistan", 20);
		updateCountryId("argentina", 29);
		updateCountryId("Armenia", 30);
		updateCountryId("australia", 32);
		updateCountryId("azerbaijan", 34);
		updateCountryId("belgium", 40);
		updateCountryId("brazil", 48);
		updateCountryId("burkina-faso", 52);
		updateCountryId("canada", 1);
		updateCountryId("Chile", 61);
		updateCountryId("china", 2);
		updateCountryId("colombia", 64);
		updateCountryId("costa-rica", 69);
		updateCountryId("czech-republic", 73);
		updateCountryId("ecuador", 78);
		updateCountryId("egypt", 79);
		updateCountryId("finland", 88);
		updateCountryId("france", 3);
		updateCountryId("germany", 4);
		updateCountryId("greece", 96);
		updateCountryId("hungary", 6);
		updateCountryId("india", 108);
		updateCountryId("indonesia", 109);
		updateCountryId("Israel", 7);
		updateCountryId("italy", 8);
		updateCountryId("japan", 9);
		updateCountryId("kazakhstan", 116);
		updateCountryId("kenya", 117);
		updateCountryId("latvia", 123);
		updateCountryId("lithuania", 129);
		updateCountryId("luxembourg", 130);
		updateCountryId("malaysia", 135);
		updateCountryId("mexico", 144);
		updateCountryId("moldova", 146);
		updateCountryId("morocco", 150);
		updateCountryId("netherlands", 11);
		updateCountryId("new-zealand", 158);
		updateCountryId("norway", 164);
		updateCountryId("pakistan", 166);
		updateCountryId("Panama", 169);
		updateCountryId("peru", 172);
		updateCountryId("philippines", 173);
		updateCountryId("poland", 174);
		updateCountryId("portugal", 12);
		updateCountryId("Qatar", 176);
		updateCountryId("romania", 178);
		updateCountryId("russia", 13);
		updateCountryId("Russian Federation", 13);
		updateCountryId("Saudi Arabia", 187);
		updateCountryId("serbia", 189);
		updateCountryId("singapore", 14);
		updateCountryId("slovakia", 192);
		updateCountryId("spain", 15);
		updateCountryId("sweden", 201);
		updateCountryId("switzerland", 202);
		updateCountryId("taiwan", 204);
		updateCountryId("Taiwan, Republic of China", 204);
		updateCountryId("tanzania", 206);
		updateCountryId("thailand", 207);
		updateCountryId("tunisia", 211);
		updateCountryId("turkey", 16);
		updateCountryId("ukraine", 216);
		updateCountryId("United Arab Emirates", 217);
		updateCountryId("united-arab-emirates", 217);
		updateCountryId("united-kingdom", 18);
		updateCountryId("United States", 19);
		updateCountryId("united-states", 19);
		updateCountryId("Viet Nam", 17);
		updateCountryId("vietnam", 17);

		runSQL("alter table OSB_DeveloperEntry drop column country");
	}

}

*/

}