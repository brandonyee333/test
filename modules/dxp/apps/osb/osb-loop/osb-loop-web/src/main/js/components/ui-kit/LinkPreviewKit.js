import Component from 'metal-jsx';

import LinkPreview from '../link-preview';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

const linkData = {
	description: 'Liferay: Put people at the heart of your business',
	imageURL: '/loop-portlet/images/cover_images/cover_image_2_web.jpg',
	shortURL: 'www.liferay.com',
	title: 'Liferay',
	url: 'http://www.liferay.com'
};

class LinkPreviewKit extends Component {
	render() {
		return (
			<Kit header="LinkPreview">
				<ElementContainer header="With Image">
					<LinkPreview linkData={linkData} />
				</ElementContainer>

				<ElementContainer header="No Image">
					<LinkPreview
						linkData={{
							...linkData,
							imageURL: ''
						}}
					/>
				</ElementContainer>

				<ElementContainer header="With Video">
					<LinkPreview
						linkData={{
							description: 'Liferay Digital Experience Platform If you need additional assistance, contact sales@liferay.com. To download the latest 30-day trial of Liferay DXP\'s Digita...',
							imageURL: 'https://i.ytimg.com/vi/izSOXcQnfyM/maxresdefault.jpg',
							shortURL: 'www.youtube.com',
							title: 'How to Deploy Your Liferay DXP Key',
							url: 'https://www.youtube.com/watch?v=izSOXcQnfyM',
							videoURL: 'https://www.youtube.com/embed/izSOXcQnfyM'
						}}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default LinkPreviewKit;