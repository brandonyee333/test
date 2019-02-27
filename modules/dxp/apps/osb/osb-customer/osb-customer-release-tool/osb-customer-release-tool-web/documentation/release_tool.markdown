# OSB Release Tool

### Set up
#### OSB Release Tool Portlet

Place the _OSB Release Tool_ portlet on a page (not to be confused with _Release Notes_ portet).

---

#### Creating Fix Pack Subcategory
When logged in as an admin, go to _Control Panel_ and click _Go to Other Site_. Navigate to the _Global_ website and enter _Control Panel_ again. Navigate to _Content_ > _Categories_ > _Fix Packs_. There should be categories for particular _Liferay Versions_. Depending on what type of _Fix Pack_ should be made, select the appropriate _Liferay Version_. Once inside, there will be an option to create a _subcategory_. Fields are:

* Name
* Key/Value pair

The name should starts with 'Fix Pack ' followed by a number (e.g., _Fix Pack 45_). Only one key/value pair should be entered. _Key_ should be 'version' and _Value_ should be the number of the _Fix Pack_ (following the previous example, '45'). Save the _subcategory_ and continue following the instructions under _Fix Pack Creation_.

---

#### Fix Pack Creation
Go to _Control Panel_ > _Content_ > _Web Content_. Create a web content using the _OSB Customer Theme - Fix Pack_ structure. The available fields are:

* Title (should be the name of the fix pack)
* Release Date
* Highlights (a container, not a field)
	* Key Highlights
	* Important Changes
	* Known Issues
	* Security

In addition to these fields, there is an option to select a _Fix Pack Subcategory_. Please look at the _Creating a Fix Pack Subcategory_ section of the documentation before proceeding. Under the _Metadata_ section (the section may need to be uncollapsed), click the _Select_ under _Fix Packs (Global)_. This will trigger a popup that shows a folder structure. Expand the folder of whichever _Liferay Version_ the _Fix Pack_ is meant to be associated with, then select the _Fix Pack Version_ (which should match the _Title_ of the web content).

After creating a _Fix Pack_, the user should be able to see it in the _OSB Release Tool_ portlet.

---

#### OSB Customer Theme - Fix Pack.ftl
_Fix Packs_ depend on markup that is written in a Freemarker template. To view the template, go to _Control Panel_ > _Content_ > _Web Content_. Click on the ellipsis on top right corner of the page to trigger the dropdown. Click on _Templates_ and searfch for _OSB Customer Theme - Fix Pack_. The markup includes:

* Fix Pack version
* Liferay version associated with the fix pack
* If no highlights, a "No hilights for this release." message
	* Otherwise, a header and body for _Key Highlights_, _Important Changes_, _Known Issues_, and _Security_ (whichever ones have been filled out when creating the _Fix Pack_ web content)