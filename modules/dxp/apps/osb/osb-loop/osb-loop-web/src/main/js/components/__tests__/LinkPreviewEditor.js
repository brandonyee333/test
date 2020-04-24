jest.unmock('../../actions/overlays');

import Component from 'metal-jsx';
import dom from 'metal-dom';
import {Provider} from 'metal-redux';

import fetch from '../../lib/fetch';
import LinkPreviewEditor from '../LinkPreviewEditor';
import mockStore from '../../tests/mock-store';
import {getURL, isValidURL} from '../../lib/util';

class LinkPreviewEditorExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<LinkPreviewEditor {...this.otherProps()} ref="linkPreviewEditor" />
			</Provider>
		);
	}
}

describe(
	'LinkPreviewEditor',
	() => {
		let component;

		const linkData = {
			description: 'Liferay: Put people at the heart of your business',
			imageURL: '/image2.jpeg',
			imageURLs: [
				'/image1.jpeg',
				'/image2.jpeg',
				'/image3.jpeg'
			],
			multipleImages: true,
			shortURL: 'www.liferay.com',
			title: 'Liferay',
			url: 'http://www.liferay.com'
		};

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new LinkPreviewEditorExample({linkData});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should set currentIndex correctly based on initial linkData',
			() => {
				component = new LinkPreviewEditorExample({linkData});

				const linkPreviewEditorComponent = component.components.linkPreviewEditor;

				expect(linkPreviewEditorComponent.state.currentIndex_).toBe(1);
			}
		);

		it(
			'should change imageURL when controls are clicked',
			done => {
				const onDataChange = data => {
					expect(data.imageURL).toBe(linkData.imageURLs[2]);

					done();
				};

				component = new LinkPreviewEditorExample({linkData, onDataChange});

				expect(linkData.imageURL).toBe(linkData.imageURLs[1]);

				dom.triggerEvent(component.element.querySelector('.next-image'), 'click');
			}
		);

		it(
			'should display placeholder preview on error',
			done => {
				const url = 'http://www.liferay.com';

				fetch.mockResponseValue({success: false});

				isValidURL.mockReturnValue(true);

				getURL.mockReturnValue(new URL(url));

				const onDataChange = linkData => {
					const linkPreviewEditorComponent = component.components.linkPreviewEditor;

					expect(linkData).not.toBeNull();

					linkPreviewEditorComponent.setState(
						{
							inputValue: url,
							linkData
						}
					);

					expect(linkPreviewEditorComponent.state.error_).toBe(true);

					expect(linkPreviewEditorComponent.element.querySelector('.short-url').innerHTML).toContain('liferay');

					done();
				};

				component = new LinkPreviewEditorExample({inputValue: url, linkData, onDataChange});

				jest.runAllTimers();
			}
		);
	}
);