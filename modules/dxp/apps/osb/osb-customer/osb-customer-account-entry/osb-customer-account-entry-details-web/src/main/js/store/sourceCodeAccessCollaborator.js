import {Record} from 'immutable';

export const CollaboratorsRecord = Record({
	collaboratorId: null,
	createDate: null,
	deleteCollaboratorURL: '',
	emailAddress: '',
	fullName: '',
	gitHubUserName: ''
});