import {combineReducers} from 'redux-immutable';

import alerts from './alerts';
import dirtyState from './dirty-state';
import divisions from './divisions';
import feeds from './feeds';
import followers from './followers';
import following from './following';
import jobTitles from './job-titles';
import lists from './lists';
import modals from './modals';
import normalizer from './normalizer';
import notifying from './notifying';
import overlays from './overlays';
import pages from './pages';
import people from './people';
import posts from './posts';
import search from './search';
import toolbar from './toolbar';
import topics from './topics';
import {composeReducers} from '../lib/util';

export default composeReducers(
	normalizer,
	followers,
	following,
	notifying,
	combineReducers(
		{
			alerts,
			dirtyState,
			divisions,
			feeds,
			jobTitles,
			lists,
			modals,
			overlays,
			pages,
			people,
			posts,
			search,
			toolbar,
			topics
		}
	)
);