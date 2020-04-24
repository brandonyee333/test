import {fromJS} from 'immutable';

const LoopConfig = window.LoopConstants;

const COVER_IMAGE_URL = '/loop-portlet/images/cover_images/cover_image_1_web.jpg';

function seedToBool(n) {
	return n % 2 == 0;
}

function getDivision(seed = 0, divisionType = LoopConfig.types.team) {
	return {
		coverImageData: {
			imageURL_raw: COVER_IMAGE_URL,
			imageURL_thumbnail: COVER_IMAGE_URL,
			imageURL_web: COVER_IMAGE_URL
		},
		displayURL: `/divisions/${seed}`,
		entityClassNameId: LoopConfig.classNameIds.divisions,
		entityClassPK: seed,
		followersCount: 232,
		following: false,
		loopParticipantAssignmentsCount: 220,
		name: `Division${seed}`,
		notifying: false,
		profileImageData: {
			imageURL_raw: '',
			imageURL_web: ''
		},
		type: divisionType
	};
}

function getImagePayload() {
	return {
		imageData: [
			{
				imageURL_web: COVER_IMAGE_URL,
				name: 1
			},
			{
				imageURL_web: '/loop-portlet/images/cover_images/cover_image_2_web.jpg',
				name: 2
			}
		],
		type: 'image'
	};
}

function getJobTitle(seed = 0) {
	return {
		displayURL: `/web/guest/home/-/loop/job_titles/jobTitles${seed}`,
		entityClassNameId: LoopConfig.classNameIds.jobTitles,
		entityClassPK: seed,
		name: `jobTitle${seed}`,
		type: LoopConfig.types.jobTitle
	};
}

function getLinkPayload() {
	return {
		linkData: {
			shortURL: 'liferay.com',
			title: 'Liferay',
			url: 'http://www.liferay.com',
			videoURL: '#'
		},
		type: 'link'
	};
}

function getNotification(seed = 0) {
	return {
		displayCompositeJSONObject: getPerson(seed),
		displayJSONObject: {
			entityClassNameId: 3,
			entityClassPK: 100
		},
		displayURL: '',
		id: seed,
		message: 'Bacon ipsum dolor amet rump beef ribs andouille bacon...',
		private: seedToBool(seed),
		read: !seedToBool(seed),
		summary: `<a href=\"/web/guest/home/-/loop/people/_joe.bloggs\">Joe Bloggs${seed} <\/a> shared a post with <a href=\"/web/guest/home/-/loop/company/_Everyone\">Everyone<\/a>.`,
		timestamp: 1461178559579
	};
}

function getPage(seed = 0, permissions = true) {
	const time = 1483982880145;

	return {
		classNameId: LoopConfig.classNameIds.divisions,
		classPK: seed,
		createTime: time - 1000 * 86400,
		creatorClassPK: seed,
		displayURL: '/web/guest/home/-/loop/departments/_test/pages/test',
		entityClassNameId: LoopConfig.classNameIds.divisions,
		entityClassPK: seed,
		foreignEntity: LoopAssets.getDivision(seed),
		modifiedTime: time,
		payload: {
			creator: seed,
			message: `test content${seed}`,
			rawMessage: `raw test content${seed}`
		},
		permissionDelete: permissions,
		permissionEdit: permissions,
		permissionUpdate: permissions,
		title: `testing test${seed}`
	};
}

function getPerson(seed = 0) {
	return {
		coverImageData: {
			imageURL_raw: COVER_IMAGE_URL,
			imageURL_thumbnail: COVER_IMAGE_URL,
			imageURL_web: COVER_IMAGE_URL
		},
		displayURL: `/people/${seed}`,
		emailAddress: `${seed}test@test.com`,
		entityClassNameId: LoopConfig.classNameIds.people,
		entityClassPK: seed,
		firstName: `Joe${seed}`,
		followersCount: 13,
		following: false,
		inactive: false,
		jobTitle: 'Test Engineer',
		lastName: 'Bloggs',
		locationName: 'Diamond Bar, USA',
		loopParticipantAssignmentsCount: 7,
		name: `Joe Bloggs${seed}`,
		notifying: false,
		preferredName: '',
		profileImageData: {
			imageURL_raw: '',
			imageURL_web: ''
		},
		type: LoopConfig.types.person
	};
}

function getPost(seed = 0, payload = {}, stringify = false) {
	const time = 1483982880145;

	payload = {
		bookmarked: false,
		creator: {
			id: seed,
			schema: 'people'
		},
		geolocation: {
			latitude: 33,
			locationName: '',
			longitude: 33
		},
		likedParticipants: {
			liked: true,
			participants: fromJS([1])
		},
		linkData: '',
		message: `<p>${seed}Lorem ipsum dolor sit amet, <a class="mention" href="#">Joe Bloggs</a> assa sit amet ultricies rutrum. Aenean finibus <a class="mention" href="#">Joe Bloggs</a> felis, eget varius mauris scelerisque quis. Pellentesque nisl tortor, semper at libero sit amet, consequat aliquet nulla. Morbi sit amet malesuada lacus. Aenean rutrum sit amet nulla id dictum. Phasellus tempor vel nisl eget scelerisque. Curabitur ut convallis enim. Aliquam fermentum vehicula velit a maximus. Donec varius dictum orci, commodo efficitur arcu fringilla vitae. Donec risus orci, imperdiet a placerat at, luctus rhoncus sem. Cras suscipit vulputate ligula. In at enim nisl.</p>`,
		rawMessage: `${seed}Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam bibendum massa sit amet ultricies rutrum. Aenean finibus egestas felis, eget varius mauris scelerisque quis. Pellentesque nisl tortor, semper at libero sit amet, consequat aliquet nulla. Morbi sit amet malesuada lacus. Aenean rutrum sit amet nulla id dictum. Phasellus tempor vel nisl eget scelerisque. Curabitur ut convallis enim. Aliquam fermentum vehicula velit a maximus. Donec varius dictum orci, commodo efficitur arcu fringilla vitae. Donec risus orci, imperdiet a placerat at, luctus rhoncus sem. Cras suscipit vulputate ligula. In at enim nisl.`,
		sharedTo: [],
		stickyTime: 0,
		truncated: false,
		type: 'text',
		...payload
	};

	if (stringify) {
		payload = JSON.stringify(payload);
	}

	return {
		assetEntryId: 0,
		assetEntrySetId: seed,
		assetEntrySetLikesCount: seed,
		childAssetEntrySets: [],
		childAssetEntrySetsCount: 0,
		companyId: 20155,
		createTime: time - 1000 * 86400,
		creatorClassNameId: LoopConfig.classNameIds.people,
		creatorClassPK: seed,
		creatorName: `Test Test${seed}`,
		displayURL: '/web/guest/home/-/loop/feed/0',
		entityClassNameId: LoopConfig.classNameIds.posts,
		entityClassPK: seed,
		following: seedToBool(seed),
		modifiedTime: time,
		notifying: !seedToBool(seed),
		parentAssetEntrySetId: 0,
		payload,
		permissionDelete: true,
		permissionEdit: true,
		permissionUpdate: true,
		privateAssetEntrySet: false,
		type: 0,
		userId: seed
	};
}

function getPostImage(seed, payload, stringify) {
	return getPost(
		seed,
		{
			...getImagePayload(),
			...payload
		},
		stringify
	);
}

function getPostLink(seed, payload, stringify) {
	return getPost(
		seed,
		{
			...getLinkPayload(),
			...payload
		},
		stringify
	);
}

function getTopic(seed = 0) {
	return {
		childLoopTopicsCount: 0,
		coverImageData: {
			imageURL_raw: COVER_IMAGE_URL,
			imageURL_thumbnail: COVER_IMAGE_URL,
			imageURL_web: COVER_IMAGE_URL
		},
		description: `description for${seed}`,
		displayURL: `/topics/${seed}`,
		entityClassNameId: LoopConfig.classNameIds.topics,
		entityClassPK: seed,
		expert: false,
		followersCount: 42,
		following: false,
		loopTopicAssignmentsCount: 16,
		mergeTime: 0,
		name: `Topic${seed}`,
		notifying: false,
		profileImageData: {
			imageURL_raw: '',
			imageURL_web: ''
		},
		type: LoopConfig.types.topic
	};
}

export default {
	getDivision,
	getJobTitle,
	getNotification,
	getPage,
	getPerson,
	getPost,
	getPostImage,
	getPostLink,
	getTopic
};