import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';
import {noop} from 'lodash';
import {Provider} from 'metal-redux';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';
import {ConnectedPreview as PostPreview} from '../PostPreview';

const CREATOR_ID = 100;

const store = mockStore(
	Map().mergeIn(
		['people', CREATOR_ID, 'data'],
		fromJS(LoopAssets.getPerson(CREATOR_ID))
	)
);

const MESSAGE = 'Lorem ipsum dolor sit amet, has in mundi salutandi. Oratio honestatis pro no, impetus postulant posidonium ex eam. Simul dolores nec et, ut pro omnis primis ponderum. Est ex noster essent, nec delenit inimicus voluptatibus ei. No mel justo mundi appareat. Vix ad tacimates assentior, populo noluisse omittantur ei cum, graeco vulputate eu eam.';

class PostPreviewExample extends Component {
	created() {
		this.handleSubmit_ = this.handleSubmit_.bind(this);
	}

	handleSubmit_() {
		return Promise.resolve([]);
	}

	render() {
		return (
			<Provider store={store}>
				<PostPreview
					{...this.otherProps()}
					creatorClassNameId={LoopConstants.classNameIds.people}
					creatorId={CREATOR_ID}
					hideModal={noop}
					id={0}
					markdown={MESSAGE}
					message={MESSAGE}
					onSubmit={this.handleSubmit_}
				/>
			</Provider>
		);
	}
}

class PostPreviewKit extends Component {
	render() {
		return (
			<Kit header="PreviewWrapper">
				<ElementContainer header="Post Preview">
					<PostPreviewExample
						announcement={false}
						imageData={[]}
						linkData={{}}
						sharedTo={[LoopAssets.getPerson()]}
						stickyTime={0}
						title=""
						type="text"
					/>
				</ElementContainer>

				<ElementContainer header="Announcement Preview">
					<PostPreviewExample
						announcement={true}
						imageData={[]}
						linkData={{}}
						sharedTo={[LoopAssets.getTopic()]}
						stickyTime={5}
						title="Lorem ipsum dolor"
						type="text"
					/>
				</ElementContainer>

				<ElementContainer header="Announcement with Image">
					<PostPreviewExample
						announcement={true}
						imageData={[
							{
								imageURL_full: '/loop-portlet/images/cover_images/cover_image_1_web.jpg',
								imageURL_web: '/loop-portlet/images/cover_images/cover_image_1_web.jpg',
								mimeType: 'image/jpeg'
							}
						]}
						linkData={{}}
						sharedTo={[LoopAssets.getTopic()]}
						stickyTime={0}
						title="Lorem Ipsum"
						type="image"
					/>
				</ElementContainer>

				<ElementContainer header="Post with Link">
					<PostPreviewExample
						announcement={false}
						imageData={[]}
						linkData={{
							description: 'Liferay: Put people at the heart of your business',
							imageURL: '/loop-portlet/images/cover_images/cover_image_2_web.jpg',
							shortURL: 'www.liferay.com',
							title: 'Liferay',
							url: 'http://www.liferay.com'
						}}
						sharedTo={[LoopAssets.getTopic()]}
						stickyTime={0}
						title=""
						type="link"
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default PostPreviewKit;