import dom from 'metal-dom';

import ImageViewer, {Image} from '../ImageViewer';

const IMAGE_ARRAY = [
	{
		imageURL_full: 'full1',
		imageURL_web: 'web1',
		mimeType: 'image/jpeg'
	},
	{
		imageURL_full: 'full2',
		imageURL_web: 'web2',
		mimeType: 'image/jpeg'
	}
];

const SINGLE_IMAGE = {
	imageURL_full: 'full1',
	imageURL_web: 'web1',
	mimeType: 'image/jpeg'
};

const SINGLE_IMAGE_GIF = {
	imageURL_full: 'full1',
	imageURL_web: 'web1',
	mimeType: 'image/GIF'
};

describe(
	'Image',
	() => {
		let component;

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
				component = new Image(
					{
						image: SINGLE_IMAGE
					}
				);

				component.state.loading_ = false;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders an error message',
			() => {
				component = new Image(
					{
						image: SINGLE_IMAGE
					}
				);

				component.state.error_ = true;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders a loading indicator',
			() => {
				component = new Image(
					{
						image: SINGLE_IMAGE
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders in fullscreen within an img tag',
			() => {
				component = new Image(
					{
						fullScreen: true,
						image: SINGLE_IMAGE
					}
				);

				component.state.loading_ = false;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders a GIF as a background image without autoplay',
			() => {
				component = new Image(
					{
						image: SINGLE_IMAGE_GIF
					}
				);

				component.state.loading_ = false;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders a GIF within the img tag, full screen, and with autoplay',
			() => {
				component = new Image(
					{
						fullScreen: true,
						image: SINGLE_IMAGE_GIF
					}
				);

				component.state.loading_ = false;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should use image and not background on image wrapper',
			() => {
				component = new Image(
					{
						hideBackground: true,
						image: SINGLE_IMAGE
					}
				);

				component.state.loading_ = false;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should hide image and display background on image wrapper',
			() => {
				component = new Image(
					{
						hideBackground: false,
						image: SINGLE_IMAGE
					}
				);

				component.state.loading_ = false;

				jest.runAllTimers();

				expect(component).toMatchSnapshot();
			}
		);
	}
);

describe(
	'ImageViewer',
	() => {
		let component;

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
				component = new ImageViewer(
					{
						items: IMAGE_ARRAY
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'renders a single image',
			() => {
				component = new ImageViewer(
					{
						items: [SINGLE_IMAGE]
					}
				);

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should return next index',
			() => {
				component = new ImageViewer(
					{
						items: IMAGE_ARRAY
					}
				);

				component.state.currentIndex_ = 0;
				expect(component.getNextIndex_(1)).toBe(1);

				component.state.currentIndex_ = 1;
				expect(component.getNextIndex_(1)).toBe(0);

				component.state.currentIndex_ = 0;
				expect(component.getNextIndex_(-1)).toBe(1);

				component.state.currentIndex_ = 1;
				expect(component.getNextIndex_(-1)).toBe(0);
			}
		);

		it(
			'should set maximized_ to true',
			() => {
				component = new ImageViewer(
					{
						items: IMAGE_ARRAY
					}
				);

				expect(component.state.maximized_).toBe(false);

				component.handleMaximizeClick_()();

				expect(component.state.maximized_).toBe(true);
			}
		);

		it(
			'should set maximized_ to false',
			() => {
				component = new ImageViewer(
					{
						items: IMAGE_ARRAY
					}
				);

				component.state.maximized_ = true;

				const eventObj1 = {
					delegateTarget: 2,
					target: 1
				};

				const eventObj2 = {
					delegateTarget: 1,
					target: 1
				};

				component.handleMinimizeClick_(eventObj1);
				expect(component.state.maximized_).toBe(true);

				component.handleMinimizeClick_(eventObj2);
				expect(component.state.maximized_).toBe(false);
			}
		);

		it(
			'should set index to 0 when new items length is changed',
			() => {
				component = new ImageViewer(
					{
						items: IMAGE_ARRAY
					}
				);

				component.state.currentIndex_ = 1;
				component.props.items = [...IMAGE_ARRAY, ...IMAGE_ARRAY];

				jest.runAllTimers();

				expect(component.state.currentIndex_).toBe(0);

				component.state.currentIndex_ = 1;
				component.props.items = IMAGE_ARRAY;

				jest.runAllTimers();

				expect(component.state.currentIndex_).toBe(0);
			}
		);

		it(
			'should call handleImageNavigate_ when the left arrow key is pressed',
			() => {
				const spy = jest.fn();

				component = new ImageViewer(
					{
						items: IMAGE_ARRAY
					}
				);

				component.handleMaximizeClick_()();

				component.handleImageNavigate_ = spy;

				dom.triggerEvent(
					document,
					'keyup',
					{
						keyCode: 37
					}
				);

				expect(spy).toBeCalled();
			}
		);

		it(
			'should not call handleImageNavigate_ when the left arrow key is pressed',
			() => {
				const spy = jest.fn();

				component = new ImageViewer(
					{
						items: [SINGLE_IMAGE]
					}
				);

				component.handleMaximizeClick_()();

				component.handleImageNavigate_ = spy;

				dom.triggerEvent(
					document,
					'keyup',
					{
						keyCode: 37
					}
				);

				expect(spy).not.toBeCalled();
			}
		);

		it(
			'should call handleImageNavigate_ when the right arrow key is pressed',
			() => {
				const spy = jest.fn();

				component = new ImageViewer(
					{
						items: IMAGE_ARRAY
					}
				);

				component.handleMaximizeClick_()();

				component.handleImageNavigate_ = spy;

				dom.triggerEvent(
					document,
					'keyup',
					{
						keyCode: 39
					}
				);

				expect(spy).toBeCalled();
			}
		);

		it(
			'should not call handleImageNavigate_ when the right arrow key is pressed',
			() => {
				const spy = jest.fn();

				component = new ImageViewer(
					{
						items: [SINGLE_IMAGE]
					}
				);

				component.handleMaximizeClick_()();

				component.handleImageNavigate_ = spy;

				dom.triggerEvent(
					document,
					'keyup',
					{
						keyCode: 39
					}
				);

				expect(spy).not.toBeCalled();
			}
		);

		it(
			'should minimize when ESC key is pressed',
			() => {
				component = new ImageViewer(
					{
						items: IMAGE_ARRAY
					}
				);

				component.handleMaximizeClick_()();

				expect(component.state.maximized_).toBe(true);

				dom.triggerEvent(
					document,
					'keyup',
					{
						keyCode: 27
					}
				);

				expect(component.state.maximized_).toBe(false);
			}
		);
	}
);