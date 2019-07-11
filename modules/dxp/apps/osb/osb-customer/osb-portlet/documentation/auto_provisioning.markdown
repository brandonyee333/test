# Auto Provisioning

### General Information

* requires integration from customer.liferay.com, web.liferay.com, and Zendesk
  * creates an OSB project and order workflow tasks on customer.liferay.com
  * validates and handles corp projects via integration to web.liferay.com
  * once workflow updates offerings on an OSB project, the project is then synced to Zendesk through our model listeners

#### RabbitMQ Debugger Tab

##### General Information

* Routing Key
  * dossiera.provisioning.create
    * used for creating new projects and opportunities/orders
  * dossiera.provisioning.update
    * used for updating project information

##### RabbitMQ fields

* "_account": OSB AccountEntry / Project
  * "_dossieraAccountKey": should be unique
  * "_name": must be the same as the current project unless doing an update
* "_bundledProducts": OSB OrderEntry
  * "_purchasedProducts": OSB OfferingEntry
    * "_name" : OSB ProductEntry, use a Dossiera ID mapping, but if not available, use the name of the product
    * "_productType": Salesforce product types
      * Possible Values: "Add to", "New", "Renewal", "Renewal Downgrade", "Renewal Migration", "Renewal Upgrade"
* "_contacts": should contain existing users
  * "_role": role of the user, if testing Analytics Cloud, a user should have the "Analytics Cloud Owner" role
* "_partnerAccount": OSB PartnerEntry
  * "_dossieraAccountKey": should be unique and match the value on Customer (identifier to grab the OSB partner)
* "_project": CorpProject
  * "_dossieraAccountKey": should be unique
  * "_dossieraProjectKey": should be unique and match the value on WEB
  * "_name": should be the same as the WEB corp project value
* "_salesforceAccountKey": should be unique
* "_salesforceOpportunityKey": should be unique to create a new OSB OrderEntry
* "_salesforceOpportunityType": Salesforce opportunity types
  * Possible Values: "Existing Business", "New Business", "New Project Existing Business", "Renewal"
* "_salesforceProjectKey": should be unique to the value on WEB's corp project

##### Example Messages

**_dossiera.provisioning.create_** message:

`
{
  "_account": {
    "_dossieraAccountKey": "autoaccount",
    "_industry": "Engineering",
    "_name": "Auto Test Account"
  },
  "_billingAddress": {
    "_city": "Diamond Bar",
    "_country": "Australia",
    "_dossieraAddressKey": "35772475",
    "_postalCode": "",
    "_region": "",
    "_street": "Test Street",
    "_type": "Billing"
  },
  "_bundledProducts": [
    {
      "_currencyIsoCode": "USD",
      "_endDay": 21,
      "_endMonth": 2,
      "_endYear": 2022,
      "_name": "Liferay DXP Subscription - Gold Prod Sizing 1",
      "_purchasedProducts": [
        {
          "_dossieraPurchasedProductKey": "35776520",
          "_endDay": 21,
          "_endMonth": 2,
          "_endYear": 2022,
          "_environment": "Production",
          "_name": "Liferay Digital Enterprise - Prod Sizing 1",
          "_productType": "New",
          "_quantity": 1,
          "_sizing": "Sizing 1",
          "_startDay": 22,
          "_startMonth": 2,
          "_startYear": 2018
        },
        {
          "_dossieraPurchasedProductKey": "35776670",
          "_endDay": 21,
          "_endMonth": 2,
          "_endYear": 2022,
          "_name": "Developer Services",
          "_productType": "New",
          "_startDay": 22,
          "_startMonth": 2,
          "_startYear": 2018
        },
        {
          "_dossieraPurchasedProductKey": "35776718",
          "_endDay": 21,
          "_endMonth": 2,
          "_endYear": 2022,
          "_name": "Gold Support",
          "_startDay": 22,
          "_startMonth": 2,
          "_startYear": 2018
        }
      ],
      "_quantity": 2,
      "_salesforceOpportunityLineItemKey": "00k4B000002dSx4QAE",
      "_startDay": 22,
      "_startMonth": 2,
      "_startYear": 2018,
      "_totalPrice": 23500.0
    }
  ],
  "_contacts": [
    {
      "_firstName": "Amos",
      "_lastName": "Fong",
      "_emailAddress": "amos.fong@liferay.com.broken",
    }
  ],
  "_owner": {
    "_emailAddress": "is-sales-qa@liferay.com",
    "_firstName": "Sales",
    "_lastName": "QA"
  },
  "_partnerFirstLineSupport": false,
      "_partnerAccount": {
      "_description": "GLOBAL QUARK S.A. de C.V. / RFC: GQU070813TP8 /",
      "_dossieraAccountKey": "0017000000pF9OcAAK",
      "_industry": "Consulting/Market Research",
      "_name": "Global Quark S.A. de C.V.",
      "_partnerships": [
        {
          "_level": "Platinum",
          "_territory": "Mexico",
          "_type": "Service Partner"
        }
      ]
    },
  "_project": {
    "_dossieraAccountKey": "autodossieraaccountkey1234",
    "_dossieraProjectKey": "autodossieraprojectkey1234",
    "_name": "Auto Test Project",
    "_supportRegion": "Americas"
  },
  "_salesforceAccountKey": "autosfaccountkey12344",
  "_salesforceOpportunityKey": "opp1key12345",
  "_salesforceOpportunityType": "New Project Existing Business",
  "_salesforceOpportunitySoldBy": "Liferay Brazil",
  "_salesforceOpportunityStageName": "Closed Won",
  "_salesforceOwnerUserKey": "00570000003VFOJAA4",
  "_salesforceProjectKey": "autoprojectkey123",
  "_salesforceOpportunityProductFamily": "S"
}
`

**_dossiera.provisioning.update_** message:

`
{
  "_account": {
    "_dossieraAccountKey": "autoaccount",
    "_industry": "Engineering",
    "_name": "Auto Test Account"
  },
  "_billingAddress": {
    "_city": "Diamond Bar",
    "_country": "Australia",
    "_dossieraAddressKey": "35772475",
    "_postalCode": "",
    "_region": "",
    "_street": "Test Street",
    "_type": "Billing"
  },
  "_bundledProducts": [
    {
      "_currencyIsoCode": "USD",
      "_endDay": 21,
      "_endMonth": 2,
      "_endYear": 2022,
      "_name": "Liferay DXP Subscription - Gold Prod Sizing 1",
      "_purchasedProducts": [
        {
          "_dossieraPurchasedProductKey": "35776520",
          "_endDay": 21,
          "_endMonth": 2,
          "_endYear": 2022,
          "_environment": "Production",
          "_name": "Liferay Digital Enterprise - Prod Sizing 1",
          "_productType": "New",
          "_quantity": 1,
          "_sizing": "Sizing 1",
          "_startDay": 22,
          "_startMonth": 2,
          "_startYear": 2018
        },
        {
          "_dossieraPurchasedProductKey": "35776670",
          "_endDay": 21,
          "_endMonth": 2,
          "_endYear": 2022,
          "_name": "Developer Services",
          "_productType": "New",
          "_startDay": 22,
          "_startMonth": 2,
          "_startYear": 2018
        },
        {
          "_dossieraPurchasedProductKey": "35776718",
          "_endDay": 21,
          "_endMonth": 2,
          "_endYear": 2022,
          "_name": "Gold Support",
          "_startDay": 22,
          "_startMonth": 2,
          "_startYear": 2018
        }
      ],
      "_quantity": 2,
      "_salesforceOpportunityLineItemKey": "00k4B000002dSx4QAE",
      "_startDay": 22,
      "_startMonth": 2,
      "_startYear": 2018,
      "_totalPrice": 23500.0
    }
  ],
  "_contacts": [
    {
      "_firstName": "Amos",
      "_lastName": "Fong",
      "_emailAddress": "amos.fong@liferay.com.broken",
    }
  ],
  "_owner": {
    "_emailAddress": "is-sales-qa@liferay.com",
    "_firstName": "Sales",
    "_lastName": "QA"
  },
  "_partnerFirstLineSupport": false,
      "_partnerAccount": {
      "_description": "GLOBAL QUARK S.A. de C.V. / RFC: GQU070813TP8 /",
      "_dossieraAccountKey": "0017000000pF9OcAAK",
      "_industry": "Consulting/Market Research",
      "_name": "Global Quark S.A. de C.V.",
      "_partnerships": [
        {
          "_level": "Platinum",
          "_territory": "Mexico",
          "_type": "Service Partner"
        }
      ]
    },
  "_project": {
    "_dossieraAccountKey": "autodossieraaccountkey1234",
    "_dossieraProjectKey": "autodossieraprojectkey1234",
    "_name": "Auto Test Project",
    "_supportRegion": "Americas"
  },
  "_salesforceAccountKey": "autosfaccountkey12344",
  "_salesforceOpportunityKey": "opp1key12345",
  "_salesforceOpportunityType": "Renewal",
  "_salesforceOpportunitySoldBy": "Liferay Brazil",
  "_salesforceOpportunityStageName": "Closed Won",
  "_salesforceOwnerUserKey": "00570000003VFOJAA4",
  "_salesforceProjectKey": "autoprojectkey123",
  "_salesforceOpportunityProductFamily": "S"
}
`