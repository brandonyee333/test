AUI.add('iba-applicationCacheManifestRegenerate', function(A) {
    COMP_NAME = 'applicationCacheManifestRegenerate';

    BOUNDING_BOX = 'boundingBox';
    CONTENT_BOX = 'contentBox';
    BUTTON = 'button';

    DIALOG_TITLE = 'dialogTitle';
    CONFIRMATION_DIALOG_CONTENT = 'confirmationDialogContent';
    MANIFEST_REGENERATION_SUCCESS_MSG = 'regenerationSuccessMsg';
    MANIFEST_REGENERATION_FAILURE_MSG = 'regenerationFailureMsg';
    ALL_MANIFESTS_REGENERATION_SUCCESS_MSG = 'regenerationOfAllSuccessMsg';
    ALL_MANIFESTS_REGENERATION_FAILURE_MSG = 'regenerationOfAllFailureMsg';

    REGENERATE_LABEL = 'regenerateLabel';
    REGENERATE_ALL_LABEL = 'regenerateAllLabel';
    CANCEL_LABEL = 'cancelLabel';
    OK_LABEL = 'okLabel';

    LAYOUT_SET_ID = 'layoutSetId';
    REGENERATE_MANIFEST_URL = 'regenerateManifestUrl';
    REGENERATE_ALL_MANIFESTS_URL = 'regenerateAllManifestsUrl';

    /**
     * This component allows adding dialog for regenerating of the manifest.
     *
     * @class ApplicationCacheManifestRegenerate
     * @constructor
     * @extends Component
     * @param config {Object} Object literal specifying widget configuration properties.
     */
    var ApplicationCacheManifestRegenerate = A.Component.create(
        {
            NAME: COMP_NAME,
            ATTRS: {
                /**
                 * Button selector.
                 *
                 * @attribute button
                 * @type String
                 */
                button: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Title of all dialogs.
                 *
                 * @attribute dialogTitle
                 * @type String
                 */
                dialogTitle: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Content of a dialog for confirmation.
                 *
                 * @attribute confirmationDialogContent
                 * @type String
                 */
                confirmationDialogContent: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Success message for regeneration of a single manifest.
                 *
                 * @attribute regenerationSuccessMsg
                 * @type String
                 */
                regenerationSuccessMsg: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Failure message for regeneration of a single manifest.
                 *
                 * @attribute regenerationFailureMsg
                 * @type String
                 */
                regenerationFailureMsg: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Success message for regeneration of all manifests.
                 *
                 * @attribute regenerationSuccessMsg
                 * @type String
                 */
                regenerationOfAllSuccessMsg: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Failure message for regeneration of all manifests.
                 *
                 * @attribute regenerationOfAllFailureMsg
                 * @type String
                 */
                regenerationOfAllFailureMsg: {
                    value: null,
                    validator: A.Lang.isString
                },


                /**
                 * Label for regenerate button.
                 *
                 * @attribute regenerateLabel
                 * @type String
                 */
                regenerateLabel: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Label for regenerate all button.
                 *
                 * @attribute regenerateAllLabel
                 * @type String
                 */
                regenerateAllLabel: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Label for OK button.
                 *
                 * @attribute okLabel
                 * @default 'OK'
                 * @type String
                 */
                okLabel: {
                    value: 'OK',
                    validator: A.Lang.isString
                },

                /**
                 * Label for CANCEL button.
                 *
                 * @attribute cancelLabel
                 * @default 'OK'
                 * @type String
                 */
                cancelLabel: {
                    value: 'Cancel',
                    validator: A.Lang.isString
                },


                /**
                 * Layout set ID..
                 *
                 * @attribute layoutSetId
                 * @type Number
                 */
                layoutSetId: {
                    value: null,
                    validator: A.Lang.isNumber
                },

                /**
                 * URL for regenerating manifest.
                 *
                 * @attribute regenerateManifestUrl
                 * @type String
                 */
                regenerateManifestUrl: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * URL for regenerating all manifests.
                 *
                 * @attribute regenerateAllManifestsUrl
                 * @type String
                 */
                regenerateAllManifestsUrl: {
                    value: null,
                    validator: A.Lang.isString
                }
            },
            prototype: {
                initializer: function() {
                    var instance = this;

                    instance._button = A.one(instance.get(BUTTON));

                    return instance;
                },
                renderUI: function() {
                    var instance = this;

                    var dialog = new A.Dialog({
                        bodyContent: '<div>' + instance.get(CONFIRMATION_DIALOG_CONTENT) + '</div>',
                        centered: true,
                        draggable: false,
                        modal: true,
                        resizable: false,
                        height: 200,
                        width: 500,
                        title: instance.get(DIALOG_TITLE),
                        buttons: [
                            {
                                label: instance.get(REGENERATE_LABEL),
                                handler: function() {
                                    A.io.request(instance.get(REGENERATE_MANIFEST_URL), {
                                        method: 'POST',
                                        data: {
                                            layoutSetId: instance.get(LAYOUT_SET_ID)
                                        },
                                        on: {
                                            success: function() {
                                                var dialog = new A.Dialog({
                                                    bodyContent: '<div class="portlet-msg-success">' + instance.get(MANIFEST_REGENERATION_SUCCESS_MSG) + '</div>',
                                                    centered: true,
                                                    resizable: false,
                                                    title: instance.get(DIALOG_TITLE),
                                                    buttons: [
                                                        {
                                                            label: instance.get(OK_LABEL),
                                                            handler: function() {
                                                                this.destroy();
                                                            }
                                                        }
                                                    ]
                                                }).render();
                                            },
                                            failure: function() {
                                                var dialog = new A.Dialog({
                                                    bodyContent: '<div class="portlet-msg-error">' + instance.get(MANIFEST_REGENERATION_FAILURE_MSG) + '</div>',
                                                    centered: true,
                                                    resizable: false,
                                                    title: instance.get(DIALOG_TITLE),
                                                    buttons: [
                                                        {
                                                            label: instance.get(OK_LABEL),
                                                            handler: function() {
                                                                this.destroy();
                                                            }
                                                        }
                                                    ]
                                                }).render();
                                            }
                                        }
                                    });

                                    this.close();
                                }
                            },
                            {
                                label: instance.get(REGENERATE_ALL_LABEL),
                                handler: function() {
                                    A.io.request(instance.get(REGENERATE_ALL_MANIFESTS_URL), {
                                        method: 'POST',
                                        on: {
                                            success: function() {
                                                var dialog = new A.Dialog({
                                                    bodyContent: '<div class="portlet-msg-success">' + instance.get(ALL_MANIFESTS_REGENERATION_SUCCESS_MSG) + '</div>',
                                                    centered: true,
                                                    resizable: false,
                                                    title: instance.get(DIALOG_TITLE),
                                                    buttons: [
                                                        {
                                                            label: instance.get(OK_LABEL),
                                                            handler: function() {
                                                                this.destroy();
                                                            }
                                                        }
                                                    ]
                                                }).render();
                                            },
                                            failure: function() {
                                                var dialog = new A.Dialog({
                                                    bodyContent: '<div class="portlet-msg-error">' + instance.get(ALL_MANIFESTS_REGENERATION_FAILURE_MSG) + '</div>',
                                                    centered: true,
                                                    resizable: false,
                                                    title: instance.get(DIALOG_TITLE),
                                                    buttons: [
                                                        {
                                                            label: instance.get(OK_LABEL),
                                                            handler: function() {
                                                                this.destroy();
                                                            }
                                                        }
                                                    ]
                                                }).render();
                                            }
                                        }
                                    });

                                    this.close();
                                }
                            },
                            {
                                label: instance.get(CANCEL_LABEL),
                                handler: function() {
                                    this.close();
                                }
                            }
                        ]
                    }).render();
                    dialog.hide();

                    instance._button.on('click', function(event) {
                        dialog.show();
                    });
                }
            }
        });

    A.ApplicationCacheManifestRegenerate = ApplicationCacheManifestRegenerate;

}, '1.0.0', { skinnable:false, requires:['event', 'node', 'aui-dialog', 'aui-io-request'] });
