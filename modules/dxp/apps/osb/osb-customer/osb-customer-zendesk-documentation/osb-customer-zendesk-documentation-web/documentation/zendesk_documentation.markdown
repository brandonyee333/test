# OSB Zendesk Documentation

### Documentation List

* https://github.com/liferay/liferay-docs
* https://github.com/liferay/liferay-docs-commerce
* https://github.com/liferay/liferay-docs-dxp-cloud

### OSB Documentation Admin

#### Category Menu

* Import Documentation Guide
* Import Translation
* Remove
  * only viewable if an official documentation guide has not been imported

#### Category Fields

* "Documentation Guide Zip File": name of the ZIP file after building the documentation
* "Documentation Original URL": relative URL found in all the documentation articles; it helps to generate links to other Zendesk articles when it iterates during import
  * generally all articles within a specific documentation will share the same relative URL, e.g., for the 7.2 User Admin guide most URLs will start with _/docs/7-2/user/-/knowledge_base/u/_ other URLs found may be references other documentation
  * https://docs.google.com/spreadsheets/d/165Nio1Qtwr-Tz6k-r1dV2wsXApW6nxhUedoeG2LlE7o/edit?ts=5cb46a6f#gid=0
* "Article Labels": labels for Zendesk to attach to each article
  * The labels should not contain any commas but each value should be on a new line. The labels are provided by Support.
* "Zendesk ID": the Zendesk category ID that the articles will import into
  * can be found as a Zendesk Admin (_Zendesk Guide Admin_ > _Arrange Content_ icon > select or create a category you want to import articles into > ID is in the URL (https://{zendesk-site}/knowledge/arrange/categories/{zendesk-category-id}?brand_id={brand-id}))
  * if you are given a link, can view articles within the category, category ID is in the URL (https://{zendesk-site}/hc/en-us/categories/{zendesk-category-id})
* "Zendesk User Segment ID": set a Zendesk User Segment ID for the articles to only be visible to a certain Zendesk user group
  * can be found as a Zendesk Admin (_Zendesk Guide Admin_ > _User Permissions_ icon > select or create a User Segment > ID is in the URL (https://{zendesk-site}/knowledge/user_segments/{zendesk-user-segment-id}?brand_id={brand-id}))

### Documentation Troubleshooting

#### Broken Links

Broken links may need to be investigated with a specific check or a combination of them. Initial check can usually be done on the article itself by checking the article create date within Zendesk or see if the link relative URL format is correct. If the article create date is recent and the URL format is correct, generally a re-import should resolve the issue. (Pending https://issues.liferay.com/browse/LHC-1151)

##### Documentation Build Check

1. fork and checkout the latest repository source
2. in the terminal, navigate to the directory of the documentation you want to build, you will need to make sure you're in the same folder as the "articles" and "articles-dxp" directories
* changing directory in terminal: "cd {directory-path}"
* check the files and folders in current directory: "ls"
3. run "ant dist-dxp" as the command will build dxp articles if there are any available
* if the build fails, may need to notify Documentation Team to fix the build error before we can attempt another article import
  * need to make sure the failure mentions specific intros, markdowns, and items that are missing
4. if the build is successful, you will find the ZIP article file in the "dist" folder

##### Documentation Original URL Check

1. navigate to _Control Panel_ > _Configuration_ > _OSB Documentation Admin_
2. find the documentation category of the reported broken link article
3. check if the Documentation Original URL is in the correct relative URL format (may change depending on Documentation Team's updates)
* update the URL if needed, a new import of the articles is necessary if immediate changes are needed

##### Documentation Source Check

1. navigate to the correct branch of the documentation repository on GitHub
2. find and select the article markdown that contains the broken link
3. click on "Raw" for the GitHub page so you can see the article without any styling or formatting
* check if the URL is in the correct relative URL format
* if the URL needs to be updated, notify Documentation Team to fix the link before we can re-import the articles
4. click on "History" for the GitHub page so you can see the commit history
* check when the last change to the link or article was made
* if the change was made after a scheduled import, a new import of the articles is necessary if immediate changes are needed