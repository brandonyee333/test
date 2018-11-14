# OSB Downloads Display

### Set up
##### OSB Downloads Display Porlet

First place the _OSB Downloads Display_ portlet on the page. Click on the ellipsis inside the portlet's topbar, and open _Configuration_.

In the _Setup_ tab, use the _DDM Structure Key_ dropdown and select _OSB-CUSTOMER-THEME--DOWNLOAD_. Save the configuration.

##### Downloads Porlet

There is another portlet that is just named _Downloads_. Place it on the same page as the _OSB Downloads Display_ portlet and follow the same instructions as the other portlet to open _Configuration_.

Inside the _Setup_ tab, do the following in the _ESA_, _Evaluation EULA_, and _Studio EULA_ tabs:
1. Leave _Language_ as _English (United States)_ unless told otherwise
2. _URL_ will be an address to the EULA's agreement content, which for testing purposes can be _http://localhost:8080?test=test_
3. _Version Displayed_ should be 1.0
4. _Required to Accept_ should be 1.0

##### Custom Fields

Go to _Control Panel_ > _Configuration_ > _Custom Fields_ > _User_. Use the _+_ button on the bottom right to add __three__ custom fields:

Each custom field entry contains two fields: _Key_ and _Type_.

Make a custom field for 1. _osbCustomerESA_, 2. _osbCustomerEvaulationEULA_, and 3. _osbCustomerStudioEULA_. All three custom fields will have type _Group of Text Values_.

After this three part setup is complete, any _Journal Articles_ created using web content should appear in the _OSB Downloads Display_ portlet. On how to create _Journal Articles_, please scorll down to __Add New Journal Article Downloads__.

---

### Viewing Journal Article Downloads
##### Multiple View

By default the _OSB Downloads Display_ portlet will show two filters, _Product_ and _File Type_ which a user can use to filter through the entire list of _Journal Articles_. If there are no existing _Journal Articles_, the results area will show an empty message, indicating that the content collection is empty.

If there are existing _Journal Articles_, the results section will render a table with two columns: _Released_ and _Name_.
* _Released_ corresponds to the _Release Date_ field of the _OSB Customer Theme - Download_ structure.
* _Name_ includes the following information:
	* Title
	* Link (repeatable)
	* Alert
	* Additional Notes
	* Download Dropdown (if more than one download is available)
	* Download Button
	* Download Details (usually in label/value pairs)

##### Testing Single View

Zendesk will have links that point to a single _Journal Article_. To test the view on a local instance not connected to Zendesk, a user can append the following to the current URL in the browser's address bar:

```?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_mvcRenderCommandName=%2Fview&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_journalArticleResourcePrimKey=[primkey_here]```

where __[primkey_here]__ is replaced with the _Journal Article's_ primkey. To obtain the primkey, a user should use the inspector tool to inspect the specific _Journal Article_ that he/she wants to see a single view for. The _<div>_ with the class _downloads_ will have an id that contains a namespace with a number appended at the end. The user should take just the number and use it as the primkey. Navigating to the new URL using the primkey should should show just that one specific _Journal Article_.

---

### Add New Journal Article Downloads
Using Liferay DXP's _Control Panel_ > _Content_ > _Web Content_, a user will be able to create a _Journal Article_ that can be searched for using the filters in _OSB Downloads Display_ portlet. Inside the _OSB Cutomer Theme - Download_ structure, a user has access to the following fields:

* Title
* Summary
* Product
* File Type
* Release Date
* Download Number
* Link (repeatable)
	* Link URL
* Alert Message
* Additional Notes
* Download Group (repeatable; determines number of download groups in dropdown)
	* Download Name (repeatable; determines number of downloads in each download group)
		* Download URL
		* Download Detail (repeatable; each download usually includes 2 Label/Value pairs)
			* Detail Label (e.g., File Size)
			* Detail Value (e.g., MD5)
* Required Agreement

After creating a _Journal Article_, the user should be able to see it in the _OSB Downloads Display_ portlet.

---

### EULA

The _Required Agreement_ field will determine whether a user will need to accept a particular set of terms and agreements before having access to a download. The options for this dropdown field are:

* None
* ESA
* Evaluation EULA
* Studio EULA

If a user chooses any option other than _None_, the user will trigger a modal upon clicking the blue _Download_ button of a _Journal Article_ in the _OSB Download Display_ portlet. The modal will ask the user to read terms and conditions and click a checkbox that indicates that the he/she agrees to said terms and conditions.

As long as the terms and agreement checkbox inside the EULA modal is not selected, the _Download_ button will be disabled.

Upon clicking the checkbox the _Download_ button inside the modal will be enabled and the user will have access to the download. Afer this initial download, the user will not have to accept the terms and conditions again--clicking on the initial _Download_ button will trigger the download immediately without triggering the modal.

### Further Testing
When testing the EULA modal, accepting the agreements for any type of EULA will make it so that the modal will never show up for any download in any journal article that is of that EULA type. If more testing is necessary and the modal must be accessed, please go to:

_Control Panel_ > _Users and Organizations_ > _User (currently signed in user)_ > _Miscellaneous (tab)_ and make sure the following boxes are empty:

1. _osbCustomerESA_
2. _osbCustomerEvaulationEULA_
3. _osbCustomerStudioEULA_

After saving, terms and conditions for _ESA_, _Evaluation EULA_, and _Studio EULA_ will have to be accepted again in order to proceed to download.