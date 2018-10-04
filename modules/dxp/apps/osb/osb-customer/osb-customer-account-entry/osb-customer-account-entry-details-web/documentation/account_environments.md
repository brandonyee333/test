# Account Environment Configurations

### Viewing Environment Configurations

#### Basic Case
By default all of the following fields show up when a user is looking at a particular environment configuration, even if no value is presented for that field:
* Environment name
* Product
* Liferay version
* Operating Systems
* Java Version
* Application Server
* Database
* Browser
* Portal ext properties file
* Patch info file

#### Special Cases
* Only product version _6.2_ and above will have _Cloud Services_ field.
* Only _DXP_ products will the _Search_ field.
We are only displaying these special fields when there is value associated with it. 

---

### Add New Environment Configurations
By default the user will be able to fill in the environment _Name_ and choose a _Product_ that they have purchased. Once a _Product_ was selected, they can choose the corresponding _Liferay Version_ for that product. The options for the rest of the fields will populate according to the _Product_ and _Liferay Version_ selected. 

#### Basic Case
The available fields for adding a new environment configuration are:
* Environment name
* Product
* Liferay version
* Operating Systems
* Java Version
* Application Server
* Database
* Browser
* Portal ext properties file
* Patch info file

#### Special Cases
##### *Search Option*

For any _DXP_ product, the user will see an additional search multi-select field.

Based on the offering, a customer could have the _Standard Search_ option or the _Enterprise Search_ option. The multi select field will populate different values based on the type of search the currently selected _Product_ is associated with.  

This field is a nonrequired field.

##### *Cloud Services*

For any _Liferay Version_ 6.2 and above, there will be an additional field for _Cloud Services_.
	
This field is a nonrequired field.

##### *Other Operating Systems*

A user could select `Other` as an option for _Operating System_. When that happens, a _Custom Operating SYstem_ input field will appear for the user to clarify.

This field is a nonrequired field.

#### Form Validation
The required fields for adding a new environment configuration are:
* Environment name
* Product
* Liferay version
* Operating Systems
* Java Version
* Application Server
* Database
* Portal ext properties file
* Patch info file