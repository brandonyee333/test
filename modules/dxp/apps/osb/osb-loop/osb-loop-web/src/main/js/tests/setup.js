jest.unmock('../tests/loop-assets');
jest.unmock('./fake-date');

const classNameIds = {
	divisions: 1,
	jobTitles: 2,
	people: 3,
	posts: 4,
	topics: 5
};

window.Liferay = {
	Language: {
		get: key => key
	},
	Loop: {
		SPA: {
			navigate: jest.fn()
		}
	},
	on: jest.fn(),
	Store: {
		get: jest.fn()
	},
	zIndex: {
		OVERLAY: 1000,
		WINDOW: 1200
	}
};

window.AUI = () => (
	{
		on: jest.fn()
	}
);

window.LoopConstants = {
	autocompleteMaxResults: 5,
	classNameIds,
	currentPerson: {
		displayURL: '',
		entityClassNameId: classNameIds.people,
		entityClassPK: 100,
		name: 'Joe Bloggs',
		profileImageData: {
			imageURL_raw: '',
			imageURL_web: ''
		},
		type: 23
	},
	departmentSubtypes: {
		functional: 1,
		none: 0,
		regional: 2
	},
	employmentTypes: [],
	followingType: {
		full: 1,
		limited: 2,
		unfollowing: 0
	},
	locationSubtypes: {
		office: 0,
		region: 1,
		remote: 2
	},
	loopStreamAliasIds: {
		announcements: 2,
		announcementsSticky: 3,
		bookmarks: 1,
		following: 0,
		privatePosts: 4
	},
	markdownToken: '%~{}~%',
	namespace: 'unit_tests_namespace_',
	notificationTypes: {
		email: 2,
		inApp: 1,
		none: 0
	},
	payloadTypes: {
		image: 'image',
		link: 'link',
		text: 'text'
	},
	phoneTypes: {
		business: 0,
		businessFax: 1,
		mobile: 2,
		other: 3,
		pager: 4,
		personal: 5,
		personalFax: 6,
		tty: 7
	},
	postTypes: {
		announcement: 1,
		post: 0
	},
	queryTypes: {
		'@': 1,
		'#': 2
	},
	socialUrls: {},
	status: {
		any: -1,
		approved: 0,
		inactive: 5
	},
	types: {
		department: 20,
		jobTitle: 21,
		location: 22,
		person: 23,
		removed: 24,
		root: 25,
		team: 26,
		topic: 27
	},
	uploadSettings: {
		maxFileCount: 10000,
		maxFileSize: 2
	},
	urls: {
		admin: '/web/guest/home/-/loop/admin',
		company: '/web/guest/home/-/loop/company',
		departments: '/web/guest/home/-/loop/departments',
		divisions: '/web/guest/home/-/loop/groups',
		feed: '/web/guest/home/-/loop/feed',
		groups: '/web/guest/home/-/loop/groups',
		help: '/web/guest/home/-/loop/feed',
		home: '/web/guest/home/-/loop',
		jobTitles: '/web/guest/home/-/loop/job_titles',
		locations: '/web/guest/home/-/loop/locations',
		people: '/web/guest/home/-/loop/people',
		search: '/web/guest/home/-/loop/home/search',
		teams: '/web/guest/home/-/loop/teams',
		topics: '/web/guest/home/-/loop/topics',
		uiKit: '/web/guest/home/-/loop/home/uiKit'
	}
};

window.LoopAssets = require('../tests/loop-assets').default;

window.themeDisplay = {
	getPortalURL() {
		return '';
	}
};

const createRangeStub = jest.fn().mockReturnValue(
	{
		cloneRange: jest.fn().mockReturnThis(),
		setEnd: jest.fn(),
		setStart: jest.fn()
	}
);

document.createRange = createRangeStub;

window.getSelection = jest.fn().mockReturnValue(
	{
		addRange: jest.fn(),
		getRangeAt: createRangeStub,
		removeAllRanges: jest.fn()
	}
);

const {useFakeDate, useRealDate} = require('./fake-date');

window.useFakeDate = useFakeDate;
window.useRealDate = useRealDate;