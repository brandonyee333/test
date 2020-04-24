import Component from 'metal-jsx';

import ElementContainer from './ElementContainer';
import Kit from './Kit';
import PostContent from '../PostContent';

const MESSAGE = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur ligula erat, commodo laoreet tellus efficitur, convallis porta nulla. Sed porta, lorem vel consequat fermentum, turpis ligula pretium purus, malesuada ullamcorper ante leo ut massa. Aliquam lobortis ligula a odio bibendum luctus. Nullam molestie efficitur elit, a tincidunt ipsum laoreet in. Nullam eu imperdiet nisl. Aenean fermentum lobortis aliquet. Nulla odio ex, venenatis at maximus sit amet, ultrices a magna. Donec id nisl at sapien commodo finibus sit amet eget orci.';

class PostContentKit extends Component {
	render() {
		return (
			<Kit header="PostContent">
				<ElementContainer header="text only">
					<PostContent
						message={MESSAGE}
						truncated={true}
					/>
				</ElementContainer>

				<ElementContainer header="text and image">
					<PostContent
						imageData={[
							{
								imageURL_full: '/loop-portlet/images/cover_images/cover_image_1_web.jpg',
								imageURL_web: '/loop-portlet/images/cover_images/cover_image_1_web.jpg',
								mimeType: 'image/jpeg'
							}
						]}
						message={MESSAGE}
						truncated={true}
					/>
				</ElementContainer>

				<ElementContainer header="text and link">
					<PostContent
						linkData={{
							description: 'Liferay: Put people at the heart of your business',
							imageURL: '/loop-portlet/images/cover_images/cover_image_2_web.jpg',
							shortURL: 'www.liferay.com',
							title: 'Liferay',
							url: 'http://www.liferay.com'
						}}
						message={MESSAGE}
						truncated={false}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default PostContentKit;