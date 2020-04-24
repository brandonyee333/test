import Component, {Config} from 'metal-jsx';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import ErrorWrapper from './ErrorWrapper';
import LoopConstants from '../lib/loop-constants';
import Post from './Post';
import {addAlert, alertTypes} from '../actions/alerts';
import {fetchPost} from '../actions/posts';
import {getPost, getRootPostId} from '../lib/selectors';
import {setActiveTabIndex, setPageTitle} from '../actions/toolbar';

class SinglePostView extends Component {
	created() {
		this.handleDelete = this.handleDelete.bind(this);
	}

	attached() {
		const {setActiveTabIndex, setPageTitle} = this.props;

		setPageTitle(Liferay.Language.get('post'));

		setActiveTabIndex(null);
	}

	detached() {
		if (this._request && this._request.cancel) {
			this._request.cancel();
		}
	}

	fetchPost_() {
		const {addAlert, fetchPost, router} = this.props;

		if (this._request && this._request.cancel) {
			this._request.cancel();
		}

		const {focusedId} = router.params;

		this._request = fetchPost(Number(focusedId)).then(
			() => {
				this.state.error_ = false;
			}
		).catch(
			({message}) => {
				this.setState(
					{error_: true},
					() => {
						if (message) {
							addAlert(
								{
									alertType: alertTypes.ERROR,
									message
								}
							);
						}
					}
				);
			}
		);
	}

	handleDelete() {
		Liferay.Loop.SPA.navigate(LoopConstants.urls.home);

		this.props.addAlert(
			{
				alertType: alertTypes.SUCCESS,
				message: Liferay.Language.get('post-was-successfully-deleted')
			}
		);
	}

	syncRouter(newVal, prevVal) {
		if (!prevVal || prevVal.params.focusedId !== newVal.params.focusedId) {
			this.fetchPost_();
		}
	}

	render() {
		const {
			props: {
				creatorIMap,
				id,
				loading,
				messageInfoIMap,
				postIMap,
				router
			},
			state: {error_}
		} = this;

		const {focusedId} = router.params;

		return (
			<ErrorWrapper elementClasses="single-post-view-container" error={error_} loading={loading && postIMap.isEmpty()}>
				<Post
					childFeedId={id}
					creatorIMap={creatorIMap}
					focusedId={Number(focusedId)}
					id={id}
					messageInfoIMap={messageInfoIMap}
					onDelete={this.handleDelete}
					postIMap={postIMap}
				/>
			</ErrorWrapper>
		);
	}
}

const STORE = {
	creatorIMap: Config.instanceOf(Map),
	fetchPost: Config.func(),
	id: Config.number(),
	loading: Config.bool(),
	messageInfoIMap: Config.instanceOf(Map).value(Map()),
	postIMap: Config.instanceOf(Map),
	setActiveTabIndex: Config.func(),
	setPageTitle: Config.func()
};

SinglePostView.PROPS = {
	...STORE,
	router: Config.object()
};

SinglePostView.STATE = {
	error_: Config.value(false)
};

export default connect(
	(state, {router}) => {
		const focusedId = Number(router.params.focusedId);

		const id = getRootPostId(state, focusedId);

		const {creatorIMap, messageInfoIMap, postIMap} = getPost(state, id);

		return {
			creatorIMap,
			id,
			loading: state.getIn(['posts', focusedId, 'loading'], true),
			messageInfoIMap,
			postIMap
		};
	},
	{
		addAlert,
		fetchPost,
		setActiveTabIndex,
		setPageTitle
	},
)(SinglePostView);