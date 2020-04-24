import Clipboard from 'clipboard';
import {bindActionCreators} from 'redux';
import {Map} from 'immutable';

import configureStore from './store/configure-store';

new Clipboard('[data-clipboard-text]');

export const store = configureStore(Map());

export {default as AdminIndex} from './pages/AdminIndex';
export {default as AlertFeed} from './components/AlertFeed';
export {default as AnnouncementCarousel} from './components/AnnouncementCarousel';
export {default as AssetCreator} from './components/AssetCreator';
export {default as BookmarksFeed} from './components/BookmarksFeed';
export {default as DivisionEditPage} from './pages/DivisionEdit';
export {default as DivisionIndex} from './pages/DivisionIndex';
export {default as DivisionProfile} from './pages/division-profile';
export {default as Feed} from './components/Feed';
export {default as FollowButton} from './components/FollowButton';
export {default as FollowersCount} from './components/profile-header/FollowersCount';
export {default as Home} from './pages/Home';
export {default as JobTitlesIndex} from './pages/JobTitlesIndex';
export {default as JobTitlesProfile} from './pages/job-title-profile';
export {default as LoopApp} from './App';
export {default as Modal} from './components/modal';
export {default as OverlayRenderer} from './components/OverlayRenderer';
export {default as Notifications} from './components/notifications';
export {default as PeopleIndex} from './pages/PeopleIndex';
export {default as PersonEditPage} from './pages/PersonEdit';
export {default as PersonProfileMenu} from './components/profile-header/PersonProfileMenu';
export {default as PersonProfile} from './pages/person-profile';
export {default as Search} from './pages/search';
export {default as Sidebar} from './components/Sidebar';
export {default as SinglePostView} from './components/SinglePostView';
export {default as Toolbar} from './components/toolbar';
export {default as Tooltip} from './components/Tooltip';
export {default as TopicEditPage} from './pages/TopicEdit';
export {default as TopicsIndex} from './pages/TopicsIndex';
export {default as TopicProfile} from './pages/topic-profile';

export {default as SPA} from './lib/SPA';

export {hotKeyManager} from './lib/HotKeyManager';

import '../css/main.scss';

import * as AlertActions from './actions/alerts';
import * as DivisionActions from './actions/divisions';
import * as EntityActions from './actions/entities';
import * as FeedActions from './actions/feeds';
import * as JobTitleActions from './actions/job-titles';
import * as ModalActions from './actions/modals';
import * as PersonActions from './actions/people';
import * as PostActions from './actions/posts';
import * as ToolbarActions from './actions/toolbar';
import * as TopicActions from './actions/topics';

export const actions = bindActionCreators(
	{
		...AlertActions,
		...DivisionActions,
		...EntityActions,
		...FeedActions,
		...JobTitleActions,
		...ModalActions,
		...PersonActions,
		...PostActions,
		...ToolbarActions,
		...TopicActions
	},
	store.dispatch
);

import createSessionAlert from './lib/createSessionAlert';
import createShutdownAlert from './lib/createShutdownAlert';

export const sessionAlert = createSessionAlert(actions.addAlert);
export const shutdownAlert = createShutdownAlert(actions.addAlert);

import createRenderer from './components/createRenderer';

export const render = createRenderer(store);