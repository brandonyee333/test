import Component from 'metal-jsx';

import TinyLinkPreview from '../TinyLinkPreview';
import ElementContainer from './ElementContainer';
import Kit from './Kit';

const linkData = {
	shortURL: 'liferay.com',
	title: 'Liferay',
	url: 'http://www.liferay.com'
};

const styles = {
	maxWidth: '260px'
};

class TinyLinkPreviewKit extends Component {
	render() {
		return (
			<Kit header="TinyLinkPreview">
				<ElementContainer header="With Image" style={styles}>
					<TinyLinkPreview
						linkData={{
							...linkData,
							imageURL: '/loop-portlet/images/cover_images/cover_image_2_web.jpg'
						}}
					/>
				</ElementContainer>

				<ElementContainer header="No Image" style={styles}>
					<TinyLinkPreview linkData={linkData} />
				</ElementContainer>
			</Kit>
		);
	}
}

export default TinyLinkPreviewKit;