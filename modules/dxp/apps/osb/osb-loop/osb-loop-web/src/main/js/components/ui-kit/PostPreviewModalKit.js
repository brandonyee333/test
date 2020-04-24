import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';
import {bindAll} from 'lodash';
import {fromJS, Map} from 'immutable';
import {Provider} from 'metal-redux';

import Button from '../Button';
import mockStore from '../../tests/mock-store';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import {Modal} from '../modal';
import {modalTypes} from '../../actions/modals';

const CREATOR_ID = 100;

const store = mockStore(
	Map().mergeIn(
		['people', CREATOR_ID, 'data'],
		fromJS(
			LoopAssets.getPerson(CREATOR_ID)
		)
	)
);

const MESSAGE = 'Lorem ipsum dolor sit amet, has in mundi salutandi. Oratio honestatis pro no, impetus postulant posidonium ex eam. Simul dolores nec et, ut pro omnis primis ponderum. Est ex noster essent, nec delenit inimicus voluptatibus ei. No mel justo mundi appareat. Vix ad tacimates assentior, populo noluisse omittantur ei cum, graeco vulputate eu eam.';

class PostPreviewModalExample extends Component {
	created() {
		bindAll(
			this,
			'handleSubmit_',
			'handleToggleModal_'
		);
	}

	handleSubmit_() {
		return Promise.resolve([]);
	}

	handleToggleModal_() {
		this.state.open_ = !this.state.open_;
	}

	render() {
		const {handleSubmit_, handleToggleModal_, open_} = this.state;
		const {header} = this.props;

		return (
			<Provider store={store}>
				<Modal
					hideModal={handleToggleModal_}
					modalIMap={
						fromJS(
							{
								hideOnBlur: true,
								modalProps: {
									announcement: false,
									creatorClassNameId: LoopConstants.classNameIds.people,
									creatorId: CREATOR_ID,
									hideModal: handleToggleModal_,
									id: 0,
									imageData: [],
									linkData: {},
									message: MESSAGE,
									onSubmit: handleSubmit_,
									sharedTo: [],
									title: '',
									...this.otherProps()
								},
								modalType: modalTypes.POST_PREVIEW,
								open: open_
							}
						)
					}
				/>

				<ElementContainer header={header}>
					<Button onClick={handleToggleModal_} role="primary">{'Show'}</Button>
				</ElementContainer>
			</Provider>
		);
	}
}

PostPreviewModalExample.PROPS = {
	header: Config.string()
};

PostPreviewModalExample.STATE = {
	open_: Config.value(false)
};

class PostPreviewModalKit extends Component {
	render() {
		return (
			<Kit header="PostPreviewModal">
				<PostPreviewModalExample
					header="preview modal for post"
					message="Lorem ipsum dolor sit amet, has in mundi salutandi."
					sharedTo={[LoopAssets.getPerson()]}
				/>

				<PostPreviewModalExample
					header="preview modal for post with link"
					linkData={{
						description: 'Liferay: Put people at the heart of your business',
						imageURL: '/loop-portlet/images/cover_images/cover_image_2_web.jpg',
						shortURL: 'www.liferay.com',
						title: 'Liferay',
						url: 'http://www.liferay.com'
					}}
					sharedTo={[LoopAssets.getPerson()]}
					type="link"
				/>

				<PostPreviewModalExample
					announcement={true}
					header="preview modal for announcement"
					sharedTo={[LoopAssets.getPerson()]}
					stickyTime={5}
					title="Lorem Ipsum Dolor"
				/>

				<PostPreviewModalExample
					announcement={true}
					header="preview modal for announcement with image"
					imageData={[
						{
							imageURL_full: '/loop-portlet/images/cover_images/cover_image_1_web.jpg',
							imageURL_web: '/loop-portlet/images/cover_images/cover_image_1_web.jpg',
							mimeType: 'image/jpeg'
						}
					]}
					sharedTo={[LoopAssets.getTopic()]}
					title="Lorem Ipsum Dolor"
					type="image"
				/>

				<PostPreviewModalExample
					announcement={true}
					header="preview modal for announcement"
					message=""
					title="Lorem Ipsum Dolor"
				/>
			</Kit>
		);
	}
}

export default PostPreviewModalKit;