import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import ImageViewer from '../ImageViewer';
import Kit from './Kit';

const COVER_PHOTO_1 = '/loop-portlet/images/cover_images/cover_image_1_web.jpg';

const COVER_PHOTO_2 = '/loop-portlet/images/cover_images/cover_image_2_web.jpg';

const IMAGE_ARRAY = [
	{
		imageURL_full: COVER_PHOTO_1,
		imageURL_web: COVER_PHOTO_1,
		mimeType: 'image/jpeg'
	},
	{
		imageURL_full: '',
		imageURL_web: '',
		mimeType: 'image/jpeg'
	},
	{
		imageURL_full: COVER_PHOTO_2,
		imageURL_web: COVER_PHOTO_2,
		mimeType: 'image/gif'
	},
	{
		imageURL_full: '',
		imageURL_web: '',
		mimeType: 'image/gif'
	}
];

class ImageViewerKit extends Component {
	render() {
		return (
			<Kit header="ImageViewer">
				<ElementContainer header="single image">
					<ImageViewer
						items={[IMAGE_ARRAY[0]]}
					/>
				</ElementContainer>

				<ElementContainer header="multiple images [jpeg, loading jpeg, gif, loading gif]">
					<ImageViewer
						items={IMAGE_ARRAY}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default ImageViewerKit;