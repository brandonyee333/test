import LoopConstants from '../lib/loop-constants';
import {fetchDivision} from './divisions';
import {fetchPerson} from './people';
import {fetchPost} from './posts';
import {fetchTopic} from './topics';

const {classNameIds} = LoopConstants;

const FETCH_ACTIONS_MAP = {
	[classNameIds.divisions]: fetchDivision,
	[classNameIds.people]: fetchPerson,
	[classNameIds.posts]: fetchPost,
	[classNameIds.topics]: fetchTopic
};

export function fetchEntity(classNameId, classPK) {
	return FETCH_ACTIONS_MAP[classNameId](classPK);
}