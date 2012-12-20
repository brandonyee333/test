AUI.add('iba-applicationCacheManifestFoldersTable', function(A) {
    COMP_NAME = 'applicationCacheManifestFoldersTable';

    BOUNDING_BOX = 'boundingBox';
    CONTENT_BOX = 'contentBox';
    VALUE_INPUT = 'valueInput';

    TABLE_LABEL = 'tableLabel';
    ADD_BUTTON_LABEL = 'addLabel';
    EDIT_BUTTON_LABEL = 'editLabel';
    DELETE_BUTTON_LABEL = 'deleteLabel';
    NO_FOLDERS_MESSAGE = 'noFoldersMessage';
    CANCEL_LABEL = 'cancelLabel';
    OK_LABEL = 'okLabel';
    DIALOG_TITLE = 'dialogTitle';

    FOLDER_URL_PREFIX_LABEL = 'folderUrlPrefixLabel';
    FOLDER_LOCATION_LABEL = 'folderLocationLabel';
    LOCATION_NOTE = 'locationNote';

    PORTAL_WEB_DIR = 'portalWebDir';

    /**
     * This component renders interactive table for manifest folders.
     *
     * @class ApplicationCacheManifestRegenerate
     * @constructor
     * @extends Component
     * @param config {Object} Object literal specifying widget configuration properties.
     */
    var ApplicationCacheManifestFoldersTable = A.Component.create(
        {
            NAME: COMP_NAME,
            ATTRS: {
                /**
                 * Selector for value input.
                 *
                 * @attribute valueInput
                 * @type String
                 */
                valueInput: {
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
                 * Label of the table.
                 *
                 * @attribute tableLabel
                 * @type String
                 */
                tableLabel: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Label for add button.
                 *
                 * @attribute addLabel
                 * @type String
                 */
                addLabel: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Label for edit button.
                 *
                 * @attribute editLabel
                 * @type String
                 */
                editLabel: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Label for delete button.
                 *
                 * @attribute deleteLabel
                 * @type String
                 */
                deleteLabel: {
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
                 * Message for no folders.
                 *
                 * @attribute noFoldersMessage
                 * @type String
                 */
                noFoldersMessage: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Label for folder URL prefix.
                 *
                 * @attribute folderUrlPrefixLabel
                 * @type String
                 */
                folderUrlPrefixLabel: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Label for folder location.
                 *
                 * @attribute folderLocationLabel
                 * @type String
                 */
                folderLocationLabel: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Location note.
                 *
                 * @attribute locationNote
                 * @type String
                 */
                locationNote: {
                    value: null,
                    validator: A.Lang.isString
                },

                /**
                 * Portal web dir.
                 *
                 * @attribute portalWebDir
                 * @type String
                 */
                portalWebDir: {
                    value: null,
                    validator: A.Lang.isString
                }
            },
            prototype: {
                initializer: function() {
                    var instance = this;

                    instance._input = A.one(instance.get(VALUE_INPUT));

                    return instance;
                },
                renderUI: function() {
                    var instance = this;

                    instance._folders = A.Node.create('<div />');
                    instance._addButton = A.Node.create('<input type="button" value="' + instance.get(ADD_BUTTON_LABEL) + '" />');

                    var div = A.Node.create('<div><label class="aui-field-label">' + instance.get(TABLE_LABEL) + '</label></div>');
                    div.append(instance._folders);
                    div.append(instance._addButton);

                    instance._input.insert(div, instance._input);


                    instance._renderManifestTable();

                    var folder = '<div class="cache-manifest-folder-form">'
                        + '<p><label for="_156_cacheManifestURLPrefix">' + this.get(FOLDER_URL_PREFIX_LABEL) + ':</label><br /><input type="text" id="_156_cacheManifestURLPrefix" class="url-prefix" size="60" /></p>'
                        + '<p><label for="_156_cacheManifestFolderLocation">' + this.get(FOLDER_LOCATION_LABEL) + ':</label><br /><input type="text" id="_156_cacheManifestFolderLocation" class="folder-location" size="60" />'
                        + '<br /><em>' + this.get(LOCATION_NOTE) + '</em></p>'
                        + '</div>';

                    instance._input.on('change', function(event) {
                        instance._renderManifestTable();
                    });

                    A.delegate('keyup', function(event) {
                        A.one(".cache-manifest-folder-form").one(".folder-location").val(instance.get(PORTAL_WEB_DIR) + this.val());
                    }, "body", ".cache-manifest-folder-form .url-prefix");

                    instance._addButton.on('click', function(event) {
                        var folderDialog = new A.Dialog({
                            bodyContent: folder,
                            centered: true,
                            draggable: false,
                            modal: true,
                            resizable: false,
                            height: 250,
                            width: 500,
                            title: instance.get(DIALOG_TITLE),
                            buttons: [
                                {
                                    label: instance.get(OK_LABEL),
                                    handler: function() {
                                        var data = instance._getTableData();

                                        data[this.get(BOUNDING_BOX).one(".url-prefix").val()] = this.get(BOUNDING_BOX).one(".folder-location").val();

                                        instance._setTableData(data);

                                        this.destroy();
                                    }
                                },
                                {
                                    label: instance.get(CANCEL_LABEL),
                                    handler: function() {
                                        this.destroy();
                                    }
                                }
                            ]
                        }).render();
                    });

                    A.delegate('click', function(event) {
                        var key = this.get("parentNode").get("parentNode").all("td").item(0).text().trim();
                        var val = this.get("parentNode").get("parentNode").all("td").item(1).text().trim();

                        var folderDialog = new A.Dialog({
                            bodyContent: folder,
                            centered: true,
                            draggable: false,
                            modal: true,
                            resizable: false,
                            height: 250,
                            width: 500,
                            title: instance.get(DIALOG_TITLE),
                            buttons: [
                                {
                                    label: instance.get(OK_LABEL),
                                    handler: function() {
                                        var data = instance._getTableData();

                                        delete data[key];
                                        data[this.get(BOUNDING_BOX).one(".url-prefix").val()] = this.get(BOUNDING_BOX).one(".folder-location").val();

                                        instance._setTableData(data);

                                        this.destroy();
                                    }
                                },
                                {
                                    label: instance.get(CANCEL_LABEL),
                                    handler: function() {
                                        this.destroy();
                                    }
                                }
                            ]
                        }).render();

                        folderDialog.get(BOUNDING_BOX).one(".url-prefix").val(key);
                        folderDialog.get(BOUNDING_BOX).one(".folder-location").val(val);
                    }, instance._folders, ".edit-link");

                    A.delegate('click', function(event) {
                        var key = this.get("parentNode").get("parentNode").all("td").item(0).text().trim();

                        var data = instance._getTableData();

                        delete data[key];

                        instance._setTableData(data);
                    }, instance._folders, ".delete-link");
                },
                _getTableData: function() {
                    var data = this._input.val();

                    if (data) {
                        data = JSON.parse(data);
                    } else {
                        data = {};
                    }

                    return data;
                },
                _setTableData: function(data) {
                    this._input.val(JSON.stringify(data));
                    this._input.simulate('change');
                },
                _renderManifestTable: function() {
                    var data = this._getTableData();

                    if (this._mapSize(data)) {
                        var table = new Array();
                        var i = 0;
                        table[i++] = '<div class="lfr-search-container"><div class="aui-component aui-searchcontainer"><div class="results-grid aui-searchcontainer-content">';
                        table[i++] = '<table class="taglib-search-iterator"><thead><tr class="portlet-section-header results-header"><th>' + this.get(FOLDER_URL_PREFIX_LABEL) + '</th><th>' + this.get(FOLDER_LOCATION_LABEL) + '</th><th style="width: 32px;"></th></tr></thead><tbody>';

                        var ii = 0;
                        for (var key in data) {
                            table[i++] = '<tr class="results-row ' + (ii % 2 == 1 ? "alt" : "") + ' portlet-section-alternate-hover"><td>';
                            table[i++] = key;
                            table[i++] = '</td><td>';
                            table[i++] = data[key];
                            table[i++] = '</td><td>';
                            table[i++] = '<a href="javascript:void(0)" class="edit-link"><img src="/html/themes/control_panel/images/common/edit.png" alt="' + this.get(EDIT_BUTTON_LABEL) + '" title="' + this.get(EDIT_BUTTON_LABEL) + '" /></a>';
                            table[i++] = '<a href="javascript:void(0)" class="delete-link"><img src="/html/themes/control_panel/images/common/delete.png" alt="' + this.get(DELETE_BUTTON_LABEL) + '" title="' + this.get(DELETE_BUTTON_LABEL) + '" /></a>';
                            table[i++] = '</td></tr>';

                            ii++;
                        }

                        table[i++] = '</tbody></table></div></div></div>';

                        this._folders.html(table.join(""));
                    } else {
                        this._folders.html('<div class="portlet-msg-info">' + this.get(NO_FOLDERS_MESSAGE) + '</div>');
                    }
                },
                _mapSize: function(data) {
                    var size = 0, key;
                    for (key in data) {
                        if (data.hasOwnProperty(key)) size++;
                    }

                    return size;
                }
            }
        });

    A.ApplicationCacheManifestFoldersTable = ApplicationCacheManifestFoldersTable;

}, '1.0.0', { skinnable:false, requires:['event', 'node', 'aui-dialog', 'aui-io-request'] });
