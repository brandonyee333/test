# OSB Zendesk Documentation

### Set up Zendesk Categories

Go to _Control Panel_ > _Configuration_ > _OSB Documentation Admin_.

The Zendesk categories must be created or edited before official documentation import.

#### Existing Category
1. Select a Zendesk cateory.
2. Under "Documentation Original URL", look through the documentation for links to other articles in the ZIP. These will help generate Zendesk links during import. The URL for 7.2 should look like, e.g., _/docs/7-2/deploy/-/knowledge_base/d/_
3. Under "Article Labels", provide labels that will be sent to Zendesk. Labels should not contain any commas. Each should be on a new line. This is provided by Support.
4. Under "Zendesk User Segment ID", if articles should be visible to everyone, keep the default value of 0. If only specific groups are allowed to view the articles within the category, set the Zendesk User Segment ID. The Zendesk User Segment ID can be found in the URL by navigating to _Zendesk Guide_ > _User Permissions_ icon > select or create a User Segment. The URL should look like, e.g., _https://{zendesk-site}/knowledge/user_segments/{zendesk-user-segment-id}?brand_id={brand-id}_.

#### New Category

1. Click the _+_ button to create a new Zendesk category.
2. Under "Documentation Guide Zip File", put the file name of the ZIP file we build and deploy to initiate documentation import. After creation, this field is disabled.
3. Under "Documentation Original URL", look through the documentation for links to other articles in the ZIP. These will help generate Zendesk links during import. The URL for 7.2 should look like, e.g., _/docs/7-2/deploy/-/knowledge_base/d/_
4. Under "Article Labels", provide labels that will be sent to Zendesk. Labels should not contain any commas. Each should be on a new line. This is provided by Support.
5. Under "Zendesk ID", navigate to _Zendesk Guide_ > _Arrange Content_ icon to see the list of Zendesk Categories > select the category you want to import articles into. The Zendesk ID is the Zendesk Category ID which can be found in the URL. The URL should look like, e.g., _https://{zendesk-site}/knowledge/arrange/categories/{zendesk-category-id}?brand_id={brand-id}_. After creation, this field is disabled.
6. Under "Zendesk User Segment ID", if articles should be visible to everyone, keep the default value of 0. If only specific groups are allowed to view the articles within the category, set the Zendesk User Segment ID. The Zendesk User Segment ID can be found in the URL by navigating to _Zendesk Guide_ > _User Permissions_ icon > select or create a User Segment. The URL should look like, e.g., _https://{zendesk-site}/knowledge/user_segments/{zendesk-user-segment-id}?brand_id={brand-id}_.