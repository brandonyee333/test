import Component, {Config} from 'metal-jsx';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import Button from '../Button';
import Checkbox from '../Checkbox';
import InputList from '../InputList';
import LoopConstants from '../../lib/loop-constants';
import Modal from '../modal';
import sendRequest from '../../lib/request';
import {addAlert, alertTypes} from '../../actions/alerts';
import {clearLists} from '../../actions/lists';
import {lang} from '../../lib/lang-util';

class MergeTopicsModal extends Component {
	created() {
		bindAll(
			this,
			'handleInputChange_',
			'handleSelect_',
			'handleSubmit_'
		);
	}

	handleCheckboxChange_(index) {
		return () => {
			const boxes = this.state.checkboxes_;

			boxes[index] = !boxes[index];

			this.state.checkboxes_ = boxes;
		};
	}

	handleInputChange_(query) {
		return sendRequest(
			{
				controller: 'home',
				controllerMethod: 'search.json',
				data: {
					keywords: query,
					searchLimit: 5,
					type: LoopConstants.types.topic
				}
			}
		);
	}

	handleSelect_(items) {
		this.state.parentTopic_ = items[0];
	}

	handleSubmit_() {
		const {addAlert, clearLists, topic} = this.props;

		const {displayURL, entityClassPK, name} = this.state.parentTopic_;

		sendRequest(
			{
				controller: 'topics',
				controllerMethod: 'setParent.json',
				data: {
					childLoopTopicIds: [topic.entityClassPK],
					parentLoopTopicId: entityClassPK
				}
			}
		).then(
			() => {
				clearLists();

				Liferay.Loop.SPA.navigate(displayURL);

				addAlert(
					{
						alertType: alertTypes.SUCCESS,
						message: lang(Liferay.Language.get('you-successfully-merged-x-into-x'), [topic.name, name])
					}
				);
			}
		);
	}

	render() {
		const {hideModal, topic} = this.props;
		const {checkboxes_, parentTopic_} = this.state;

		const childName = <a href={topic.displayURL} target="_blank">{topic.name}</a>;
		const parentName = <a href={parentTopic_ && parentTopic_.displayURL} target="_blank">{parentTopic_ && parentTopic_.name}</a>;

		return (
			<div class="merge-topics-modal-container">
				<Modal.Header onClose={hideModal}>
					{Liferay.Language.get('merge-with-another-topic')}
				</Modal.Header>

				<Modal.Body>
					{!parentTopic_ &&
						<InputList
							dataSource={this.handleInputChange_}
							label={Liferay.Language.get('topic')}
							onChange={this.handleSelect_}
							placeholder={Liferay.Language.get('select-topic')}
						/>
					}

					{parentTopic_ &&
						<div class="description">
							<div>
								{
									lang(
										Liferay.Language.get('are-you-sure-you-want-to-merge-x-into-x-all-data-related-to-x-will-be-moved'),
										[childName, parentName]
									)
								}
							</div>

							<Checkbox
								checked={checkboxes_[0]}
								label={lang(Liferay.Language.get('x-page-will-be-removed'), [childName])}
								onChange={this.handleCheckboxChange_(0)}
							/>
							<Checkbox
								checked={checkboxes_[1]}
								label={lang(Liferay.Language.get('all-of-its-current-mentions-will-now-redirect-to-x'), [parentName])}
								onChange={this.handleCheckboxChange_(1)}
							/>
							<Checkbox
								checked={checkboxes_[2]}
								label={lang(Liferay.Language.get('all-of-its-followers/experts-will-be-moved-to-x'), [parentName])}
								onChange={this.handleCheckboxChange_(2)}
							/>
						</div>
					}
				</Modal.Body>

				<Modal.Footer>
					<Button onClick={hideModal} role="secondary">{Liferay.Language.get('cancel')}</Button>

					<Button disabled={!checkboxes_.every(value => value)} onClick={this.handleSubmit_} role="primary">{Liferay.Language.get('merge')}</Button>
				</Modal.Footer>
			</div>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	clearLists: Config.func()
};

MergeTopicsModal.PROPS = {
	...STORE,
	hideModal: Config.func(),
	topic: Config.object()
};

MergeTopicsModal.STATE = {
	checkboxes_: Config.value([false, false, false]),
	parentTopic_: Config.value(null)
};

export default connect(
	null,
	{
		addAlert,
		clearLists
	}
)(MergeTopicsModal);