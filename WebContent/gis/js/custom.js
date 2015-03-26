// Custom from other lib not use JSLint format
OpenLayers.Control.CustomWMSGetFeatureInfo = OpenLayers.Class(OpenLayers.Control.WMSGetFeatureInfo, {
    findLayers: function() {
        var candidates = this.layers || this.map.layers;
        var layers = [];
        var layer, url;
        for(var i=0, len=candidates.length; i<len; ++i) {
            layer = candidates[i];
            if(layer instanceof OpenLayers.Layer.WMS && !layer.disablePrint &&
                // Begin Custom Here!!
                // Old Code
                // (!this.queryVisible || layer.getVisibility())) {
                // New Code
                (!this.queryVisible || layer.getVisibility()) &&
                (layer.alwaysInRange || layer.inRange)) {
                // End Custom Here!!
                url = OpenLayers.Util.isArray(layer.url) ? layer.url[0] : layer.url;
                // if the control was not configured with a url, set it
                // to the first layer url
                if(this.drillDown === false && !this.url) {
                    this.url = url;
                }
                if(this.drillDown === true || this.urlMatches(url)) {
                    layers.push(layer);
                }
            }
        }
        return layers;
    },
    request: function(clickPosition, options) {
        var layers = this.findLayers();
        if(layers.length == 0) {
            this.events.triggerEvent("nogetfeatureinfo");
            // Reset the cursor.
            OpenLayers.Element.removeClass(this.map.viewPortDiv, "olCursorWait");
            return;
        }

        options = options || {};
        if(this.drillDown === false) {
            var wmsOptions = this.buildWMSOptions(this.url, layers,
                clickPosition, layers[0].params.FORMAT); 
            var request = OpenLayers.Request.GET(wmsOptions);

            if (options.hover === true) {
                this.hoverRequest = request;
            }
        } else {
            this._requestCount = 0;
            this._numRequests = 0;
            this.features = [];
            // group according to service url to combine requests
            var services = {}, url;
            for(var i=0, len=layers.length; i<len; i++) {
                var layer = layers[i];
                var service, found = false;
                // Begin Custom Here!!
                // Old Code
                // url = OpenLayers.Util.isArray(layer.url) ? layer.url[0] : layer.url;
                // New Code
                url = layer.urlGetFeature ? OpenLayers.Util.isArray(layer.urlGetFeature) ? layer.urlGetFeature[0] : layer.urlGetFeature : OpenLayers.Util.isArray(layer.url) ? layer.url[0] : layer.url;
                // End Custom Here!! 
                if(url in services) {
                    services[url].push(layer);
                } else {
                    this._numRequests++;
                    services[url] = [layer];
                }
            }
            var layers;
            for (var url in services) {
                layers = services[url];
                var wmsOptions = this.buildWMSOptions(url, layers, 
                    clickPosition, layers[0].params.FORMAT);
                OpenLayers.Request.GET(wmsOptions); 
            }
        }
    },
    buildWMSOptions: function(url, layers, clickPosition, format) {
        var layerNames = [], styleNames = [];
        for (var i = 0, len = layers.length; i < len; i++) { 
            // Begin Custom Here!!
            // Old Code
            // layerNames = layerNames.concat(layers[i].params.LAYERS);
            // New Code
            layerNames = layers[i].nameGetFeature?layerNames.concat(layers[i].nameGetFeature):layerNames.concat(layers[i].params.LAYERS);
            // End Custom Here!!
            styleNames = styleNames.concat(this.getStyleNames(layers[i]));
        }
        var firstLayer = layers[0];
        // use the firstLayer's projection if it matches the map projection -
        // this assumes that all layers will be available in this projection
        var projection = this.map.getProjection();
        var layerProj = firstLayer.projection;
        if (layerProj && layerProj.equals(this.map.getProjectionObject())) {
            projection = layerProj.getCode();
        }
        var params = OpenLayers.Util.extend({
            service: "WMS",
            version: firstLayer.params.VERSION,
            request: "GetFeatureInfo",
            layers: layerNames,
            query_layers: layerNames,
            styles: styleNames,
            bbox: this.map.getExtent().toBBOX(null,
                firstLayer.reverseAxisOrder()),
            feature_count: this.maxFeatures,
            height: this.map.getSize().h,
            width: this.map.getSize().w,
            format: format,
            info_format: firstLayer.params.INFO_FORMAT || this.infoFormat
        }, (parseFloat(firstLayer.params.VERSION) >= 1.3) ?
            {
                crs: projection,
                i: parseInt(clickPosition.x),
                j: parseInt(clickPosition.y)
            } :
            {
                srs: projection,
                x: parseInt(clickPosition.x),
                y: parseInt(clickPosition.y)
            }
        );
        OpenLayers.Util.applyDefaults(params, this.vendorParams);
        return {
            url: url,
            params: OpenLayers.Util.upperCaseObject(params),
            callback: function(request) {
                this.handleResponse(clickPosition, request, url);
            },
            scope: this
        };
    },
    triggerGetFeatureInfo: function(request, xy, features) {
        // Begin Custom Here!!
        // Old Code
        // this.events.triggerEvent("getfeatureinfo", {
        //     text: request.responseText,
        //     features: features,
        //     request: request,
        //     xy: xy
        // });
        // New Code
        if(OpenLayers.Util.isArray(request)) {
            var responseText = "";
            for(var index=0; index<request.length; index++) {
                responseText += request[index].responseText;
            }
            this.events.triggerEvent("getfeatureinfo", {
                text: responseText.replace(",\nnull]\n[",",").replace("[\nnull]","").replace(",\nnull",""),
                features: features,
                request: request[0],
                xy: xy
            });
        } else {
            this.events.triggerEvent("getfeatureinfo", {
                text: request.responseText,
                features: features,
                request: request,
                xy: xy
            });
        }
        // End Custom Here!!

        // Reset the cursor.
        OpenLayers.Element.removeClass(this.map.viewPortDiv, "olCursorWait");
    },
    handleResponse: function(xy, request, url) {
        var doc = request.responseXML;
        if(!doc || !doc.documentElement) {
            doc = request.responseText;
        }
        var features = this.format.read(doc);
        if (this.drillDown === false) {
            this.triggerGetFeatureInfo(request, xy, features);
        } else {
            // Begin Custom Here!!
            // Old Code
            // this._requestCount++;
            // if (this.output === "object") {
            //     this._features = (this._features || []).concat(
            //         {url: url, features: features}
            //     );
            // } else {
            // this._features = (this._features || []).concat(features);
            // }
            // if (this._requestCount === this._numRequests) {
            //     this.triggerGetFeatureInfo(request, xy, this._features.concat());
            //     delete this._features;
            //     delete this._requestCount;
            //     delete this._numRequests;
            // }
            // New Code
            this._requestCount++;
            if (this.output === "object") {
                this._features = (this._features || []).concat(
                    {url: url, features: features}
                );
            } else {
            this._features = (this._features || []).concat(features);
            this._request = (this._request || []).concat(request);
            }
            if (this._requestCount === this._numRequests) {
                this.triggerGetFeatureInfo(this._request, xy, this._features.concat());
                delete this._features;
                delete this._requestCount;
                delete this._numRequests;
                delete this._request;
            }
            // End Custom Here!!
        }
    }
});

GeoExt.data.CustomPrintProvider = Ext.extend(GeoExt.data.PrintProvider, {
    print: function(map, pages, options) {
        if(map instanceof GeoExt.MapPanel) {
            map = map.map;
        }
        pages = pages instanceof Array ? pages : [pages];
        options = options || {};
        if(this.fireEvent("beforeprint", this, map, pages, options) === false) {
            return;
        }

        var jsonData = Ext.apply({
            units: map.getUnits(),
            srs: map.baseLayer.projection.getCode(),
            layout: this.layout.get("name"),
            dpi: this.dpi.get("value")
        }, this.customParams);

        var pagesLayer = pages[0].feature.layer;
        var encodedLayers = [];

        // ensure that the baseLayer is the first one in the encoded list
        var layers = map.layers.concat();
        layers.remove(map.baseLayer);
        layers.unshift(map.baseLayer);

        Ext.each(layers, function(layer){
            if(layer !== pagesLayer && layer.getVisibility() === true) {
                var enc = this.encodeLayer(layer);
                enc && encodedLayers.push(enc);
            }
        }, this);
        jsonData.layers = encodedLayers;

        var encodedPages = [];
        Ext.each(pages, function(page) {
            encodedPages.push(Ext.apply({
                center: [page.center.lon, page.center.lat],
                scale: page.scale.get("value"),
                rotation: page.rotation
            }, page.customParams));
        }, this);
        jsonData.pages = encodedPages;

        if (options.overview) {
            var encodedOverviewLayers = [];
            Ext.each(options.overview.layers, function(layer) {
                var enc = this.encodeLayer(layer);
                enc && encodedOverviewLayers.push(enc);
            }, this);
            jsonData.overviewLayers = encodedOverviewLayers;
        }

        if(options.legend) {
            var legend = options.legend;
            var rendered = legend.rendered;
            if (!rendered) {
                legend = legend.cloneConfig({
                    renderTo: document.body,
                    hidden: true
                });
            }
            var encodedLegends = [];
            legend.items && legend.items.each(function(cmp) {
                if(!cmp.hidden) {
                    var encFn = this.encoders.legends[cmp.getXType()];

                    // MapFish Print doesn't currently support per-page
                    // legends, so we use the scale of the first page.
                    // Begin Custom Here!!
                    // Old Code
                    // encodedLegends = encodedLegends.concat(
                    //     encFn.call(this, cmp, jsonData.pages[0].scale));
                    // New Code
                    if(encFn)
                        encodedLegends = encodedLegends.concat(encFn.call(this, cmp, jsonData.pages[0].scale));
                    // End Custom Here!!

                }
            }, this);
            if (!rendered) {
                legend.destroy();
            }
            jsonData.legends = encodedLegends;
        }

        if(this.method === "GET") {
            var url = Ext.urlAppend(this.capabilities.printURL,
                "spec=" + encodeURIComponent(Ext.encode(jsonData)));
            this.download(url);
        } else {
            Ext.Ajax.request({
                url: this.capabilities.createURL,
				//url: "http://cld.drr.go.th/geoserver/pdf/create.json",
                timeout: this.timeout,
                jsonData: jsonData,
                headers: {"Content-Type": "application/json; charset=" + this.encoding},
                success: function(response) {
                    var url = Ext.decode(response.responseText).getURL;
                    this.download(url);
                },
                failure: function(response) {
                    this.fireEvent("printexception", this, response);
                },
                params: this.initialConfig.baseParams,
                scope: this
            });
        }
    },
    encoders: {
        "layers": {
            // Begin Custom Here!!
            // New Code
            "Google": function(layer) {
                return GeoExt.data.PrintProvider.encodeGoogleLayer(layer);
            },
            "GoogleNG": function(layer) {
                return GeoExt.data.PrintProvider.encodeGoogleLayer(layer);
            },
            // End Custom Here!!
            "Layer": function(layer) {
                var enc = {};
                if (layer.options && layer.options.maxScale) {
                    enc.minScaleDenominator = layer.options.maxScale;
                }
                if (layer.options && layer.options.minScale) {
                    enc.maxScaleDenominator = layer.options.minScale;
                }
                return enc;
            },
            "WMS": function(layer) {
                var enc = this.encoders.layers.HTTPRequest.call(this, layer);
                Ext.apply(enc, {
                    type: 'WMS',
                    layers: [layer.params.LAYERS].join(",").split(","),
                    format: layer.params.FORMAT,
                    styles: [layer.params.STYLES].join(",").split(",")
                });
                var param;
                for(var p in layer.params) {
                    param = p.toLowerCase();
                    if(!layer.DEFAULT_PARAMS[param] &&
                    "layers,styles,width,height,srs".indexOf(param) == -1) {
                        if(!enc.customParams) {
                            enc.customParams = {};
                        }
                        enc.customParams[p] = layer.params[p];
                    }
                }
                return enc;
            },
            "OSM": function(layer) {
                var enc = this.encoders.layers.TileCache.call(this, layer);
                return Ext.apply(enc, {
                    type: 'OSM',
                    baseURL: enc.baseURL.substr(0, enc.baseURL.indexOf("$")),
                    extension: "png"
                });
            },
            "TMS": function(layer) {
                var enc = this.encoders.layers.TileCache.call(this, layer);
                return Ext.apply(enc, {
                    type: 'TMS',
                    format: layer.type
                });
            },
            "TileCache": function(layer) {
                var enc = this.encoders.layers.HTTPRequest.call(this, layer);
                return Ext.apply(enc, {
                    type: 'TileCache',
                    layer: layer.layername,
                    maxExtent: layer.maxExtent.toArray(),
                    tileSize: [layer.tileSize.w, layer.tileSize.h],
                    extension: layer.extension,
                    resolutions: layer.serverResolutions || layer.resolutions
                });
            },
            "WMTS": function(layer) {
                var enc = this.encoders.layers.HTTPRequest.call(this, layer);
                return Ext.apply(enc, {
                    type: 'WMTS',
                    layer: layer.layer,
                    version: layer.version,
                    requestEncoding: layer.requestEncoding,
                    tileOrigin: [layer.tileOrigin.lon, layer.tileOrigin.lat],
                    tileSize: [layer.tileSize.w, layer.tileSize.h],
                    style: layer.style,
                    formatSuffix: layer.formatSuffix,
                    dimensions: layer.dimensions,
                    params: layer.params,
                    maxExtent: (layer.tileFullExtent != null) ? layer.tileFullExtent.toArray() : layer.maxExtent.toArray(),
                    matrixSet: layer.matrixSet,
                    zoomOffset: layer.zoomOffset,
                    resolutions: layer.serverResolutions || layer.resolutions
                });
            },
            "KaMapCache": function(layer) {
                var enc = this.encoders.layers.KaMap.call(this, layer);
                return Ext.apply(enc, {
                    type: 'KaMapCache',
                    // group param is mandatory when using KaMapCache
                    group: layer.params['g'],
                    metaTileWidth: layer.params['metaTileSize']['w'],
                    metaTileHeight: layer.params['metaTileSize']['h']
                });
            },
            "KaMap": function(layer) {
                var enc = this.encoders.layers.HTTPRequest.call(this, layer);
                return Ext.apply(enc, {
                    type: 'KaMap',
                    map: layer.params['map'],
                    extension: layer.params['i'],
                    // group param is optional when using KaMap
                    group: layer.params['g'] || "",
                    maxExtent: layer.maxExtent.toArray(),
                    tileSize: [layer.tileSize.w, layer.tileSize.h],
                    resolutions: layer.serverResolutions || layer.resolutions
                });
            },
            "HTTPRequest": function(layer) {
                var enc = this.encoders.layers.Layer.call(this, layer);
                return Ext.apply(enc, {
                    // Begin Custom Here!!
                    // Old Code
                    // baseURL: this.getAbsoluteUrl(layer.url instanceof Array ?
                    //     layer.url[0] : layer.url),
                    // New Code
                    baseURL: this.getAbsoluteUrl(layer.printURL?layer.printURL instanceof Array?layer.printURL[0]:layer.printURL:layer.url instanceof Array ? layer.url[0] : layer.url),
                    // End Custom Here!!
                    opacity: (layer.opacity != null) ? layer.opacity : 1.0,
                    singleTile: layer.singleTile
                });
            },
            "Image": function(layer) {
                var enc = this.encoders.layers.Layer.call(this, layer);
                return Ext.apply(enc, {
                    type: 'Image',
                    baseURL: this.getAbsoluteUrl(layer.getURL(layer.extent)),
                    opacity: (layer.opacity != null) ? layer.opacity : 1.0,
                    extent: layer.extent.toArray(),
                    pixelSize: [layer.size.w, layer.size.h],
                    name: layer.name
                });
            },
            "Vector": function(layer) {
                if(layer.asWMS) {
                    var enc = this.encoders.layers.HTTPRequest.call(this, layer);
                    Ext.apply(enc, {
                        type: 'WMS',
                        layers: [layer.wms.LAYERS].join(",").split(","),
                        format: layer.wms.FORMAT,
                        styles: [layer.wms.STYLES].join(",").split(",")
                    });
                    return enc;
                }
                if(!layer.features.length || layer.notPrint) {
                    return;
                }

                var encFeatures = [];
                var encStyles = {};
                var features = layer.features;
                var featureFormat = new OpenLayers.Format.GeoJSON();
                var styleFormat = new OpenLayers.Format.JSON();
                var nextId = 1;
                var styleDict = {};
                var feature, style, dictKey, dictItem, styleName;
                for(var i=0, len=features.length; i<len; ++i) {
                    feature = features[i];
                    style = feature.style || layer.style ||
                    layer.styleMap.createSymbolizer(feature,
                        feature.renderIntent);
                    dictKey = styleFormat.write(style);
                    dictItem = styleDict[dictKey];
                    if(dictItem) {
                        //this style is already known
                        styleName = dictItem;
                    } else {
                        //new style
                        styleDict[dictKey] = styleName = nextId++;
                        if(style.externalGraphic) {
                            encStyles[styleName] = Ext.applyIf({
                                externalGraphic: this.getAbsoluteUrl(
                                    style.externalGraphic)}, style);
                        } else {
                            encStyles[styleName] = style;
                        }
                    }
                    var featureGeoJson = featureFormat.extract.feature.call(
                        featureFormat, feature);

                    featureGeoJson.properties = OpenLayers.Util.extend({
                        _gx_style: styleName
                    }, featureGeoJson.properties);

                    encFeatures.push(featureGeoJson);
                }
                var enc = this.encoders.layers.Layer.call(this, layer);
                return Ext.apply(enc, {
                    type: 'Vector',
                    styles: encStyles,
                    styleProperty: '_gx_style',
                    geoJson: {
                        type: "FeatureCollection",
                        features: encFeatures
                    },
                    name: layer.name,
                    opacity: (layer.opacity != null) ? layer.opacity : 1.0
                });
            },
            "Markers": function(layer) {
                var features = [];
                for (var i=0, len=layer.markers.length; i<len; i++) {
                    var marker = layer.markers[i];
                    var geometry = new OpenLayers.Geometry.Point(marker.lonlat.lon, marker.lonlat.lat);
                    var style = {externalGraphic: marker.icon.url,
                        graphicWidth: marker.icon.size.w, graphicHeight: marker.icon.size.h,
                        graphicXOffset: marker.icon.offset.x, graphicYOffset: marker.icon.offset.y};
                    var feature = new OpenLayers.Feature.Vector(geometry, {}, style);
                    features.push(feature);
            }
                var vector = new OpenLayers.Layer.Vector(layer.name);
                vector.addFeatures(features);
                var output = this.encoders.layers.Vector.call(this, vector);
                vector.destroy();
                return output;
            }
        },
        "legends": {
            "gx_wmslegend": function(legend, scale) {
                var enc = this.encoders.legends.base.call(this, legend);
                var icons = [];
                for(var i=1, len=legend.items.getCount(); i<len; ++i) {
                    var url = legend.items.get(i).url;
                    if(legend.useScaleParameter === true &&
                       url.toLowerCase().indexOf(
                           'request=getlegendgraphic') != -1) {
                        var split = url.split("?");
                        var params = Ext.urlDecode(split[1]);
                        params['SCALE'] = scale;
                        url = split[0] + "?" + Ext.urlEncode(params);
                    }
                    icons.push(this.getAbsoluteUrl(url));
                }
                enc[0].classes[0] = {
                    name: "",
                    icons: icons
                };
                return enc;
            },
            "gx_urllegend": function(legend) {
                var enc = this.encoders.legends.base.call(this, legend);
                enc[0].classes.push({
                    name: "",
                    icon: this.getAbsoluteUrl(legend.items.get(1).url)
                });
                return enc;
            },
            "base": function(legend){
                return [{
                    name: legend.getLabel(),
                    classes: []
                }];
            }
        }
    }
});

GeoExt.tree.CustomOverlayLayerContainer = Ext.extend(GeoExt.tree.OverlayLayerContainer, {
    constructor: function(config) {
        config = Ext.applyIf(config || {}, {
            text: this.text
        });
        config.loader = Ext.applyIf(config.loader || {}, {
            filter: function(record){
                var layer = record.getLayer();
                // Begin Custom Here!!
                // Old Code
                // return layer.displayInLayerSwitcher === true &&
                // layer.isBaseLayer === false;
                // New Code
                return layer.isBaseLayer === false;
                // End Custom Here!!
            }
        });

        GeoExt.tree.OverlayLayerContainer.superclass.constructor.call(this,
            config);
    }
});

Ext.tree.TreePanel.nodeTypes.gx_customoverlaylayercontainer = GeoExt.tree.CustomOverlayLayerContainer;

GeoExt.data.CustomPrintPage = Ext.extend(GeoExt.data.PrintPage, {
    updateFeature: function(geometry, mods) {
        var f = this.feature;
        var modified = f.geometry !== geometry;
        geometry.id = f.geometry.id;
        f.geometry = geometry;

        if(!this._updating) {
            for(var property in mods) {
                if(mods[property] === this[property]) {
                    delete mods[property];
                } else {
                    this[property] = mods[property];
                    modified = true;
                }
            }
            Ext.apply(this, mods);

            f.layer && f.layer.drawFeature(f);
            modified && this.fireEvent("change", this, mods);
        }
    }
});

/** api: property[GOOGLE_ENCODE_LAYER_TYPE]
*  ``String`` the type used to encode google layer, by default it's 'TiledGoogle',
*  set it to 'Google' if you want to be a Google Maps API Premier customers.
*  See: ttps://code.google.com/intl/fr-CH/apis/maps/documentation/staticmaps/.
*/
GeoExt.data.PrintProvider.GOOGLE_ENCODE_LAYER_TYPE = 'TiledGoogle';

/** private: static method[encodeGoogleLayer]
 *  :param layer: ``Layer``
 *  :return: ``Object``
 *
 * Encode a Google layer for the print.
 */
GeoExt.data.PrintProvider.encodeGoogleLayer = function(layer) {
    var type = 'roadmap';
    if (layer.type == google.maps.MapTypeId.SATELLITE) {
        type = 'satellite';
    }
    else if (layer.type == google.maps.MapTypeId.HYBRID) {
        type = 'hybrid';
    }
    else if (layer.type == google.maps.MapTypeId.TERRAIN) {
        type = 'terrain';
    }

    return {
        type: GeoExt.data.PrintProvider.GOOGLE_ENCODE_LAYER_TYPE,
        baseURL: 'http://maps.google.com/maps/api/staticmap',
        customParams: {
            "language" : "th"
        },
        extension: 'png',
        format: 'png32',
        maptype: type,
        maxExtent: layer.maxExtent.toArray(),
        opacity: (layer.opacity != null) ? layer.opacity : 1.0,
        resolutions: layer.resolutions,
        sensor: 'false',
        tileSize: [256, 256]
    };
};

// gx_urllegend old version error when use scale
// Ext.extend(GeoExt.data.LayerReader, Ext.data.DataReader, {
// 
//     /** private: property[totalRecords]
//      *  ``Integer``
//      */
//     totalRecords: null,
// 
//     /** api: method[readRecords]
//      *  :param layers: ``Array(OpenLayers.Layer)`` List of layers for creating
//      *      records.
//      *  :return: ``Object``  An object with ``records`` and ``totalRecords``
//      *      properties.
//      *
//      *  From an array of ``OpenLayers.Layer`` objects create a data block
//      *  containing :class:`GeoExt.data.LayerRecord` objects.
//      */
//     readRecords : function(layers) {
//         var records = [];
//         if(layers) {
//             var recordType = this.recordType, fields = recordType.prototype.fields;
//             var i, lenI, j, lenJ, layer, values, field, v;
//             for(i = 0, lenI = layers.length; i < lenI; i++) {
//                 layer = layers[i];
//                 values = {};
//                 for(j = 0, lenJ = fields.length; j < lenJ; j++){
//                     field = fields.items[j];
//                     v = layer[field.mapping || field.name] ||
//                         field.defaultValue;
//                     v = field.convert(v);
//                     values[field.name] = v;
//                 }
//                 // Begin Custom Here!!
//                 // New Code
//                 if(layer instanceof OpenLayers.Layer.WMS && layer.legendURL) {
//                     values.legendURL = layer.legendURL;
//                 }
//                 // End Custom Here!!
//                 values.layer = layer;
//                 records[records.length] = new recordType(values, layer.id);
//             }
//         }
//         return {
//             records: records,
//             totalRecords: this.totalRecords != null ? this.totalRecords : records.length
//         };
//     }
// });

// gx_wmslegend bug fix**
GeoExt.CustomWMSLegend = Ext.extend(GeoExt.WMSLegend, {
    getLegendUrl: function(layerName, layerNames) {
        var rec = this.layerRecord;
        var url;
        var styles = rec && rec.get("styles");
        var layer = rec.getLayer();
        layerNames = layerNames || [layer.params.LAYERS].join(",").split(",");

        var styleNames = layer.params.STYLES &&
                             [layer.params.STYLES].join(",").split(",");
        var idx = layerNames.indexOf(layerName);
        var styleName = styleNames && styleNames[idx];
        // check if we have a legend URL in the record's
        // "styles" data field
        if(styles && styles.length > 0) {
            if(styleName) {
                Ext.each(styles, function(s) {
                    url = (s.name == styleName && s.legend) && s.legend.href;
                    return !url;
                });
            } else if(this.defaultStyleIsFirst === true && !styleNames &&
                      !layer.params.SLD && !layer.params.SLD_BODY) {
                url = styles[0].legend && styles[0].legend.href;
            }
        }
        // Begin Custom Here!!
        // Old Code
        //
        // if(!url) {
        //     url = layer.getFullRequestString({
        //         REQUEST: "GetLegendGraphic",
        //         WIDTH: null,
        //         HEIGHT: null,
        //         EXCEPTIONS: "application/vnd.ogc.se_xml",
        //         LAYER: layerName,
        //         LAYERS: null,
        //         STYLE: (styleName !== '') ? styleName: null,
        //         STYLES: null,
        //         SRS: null,
        //         FORMAT: null,
        //         TIME: null
        //     });
        // }
        // New Code
        if (layer.legendURL) {
            url = layer.legendURL;
        } else if(!url) {
            url = layer.getFullRequestString({
                REQUEST: "GetLegendGraphic",
                WIDTH: null,
                HEIGHT: null,
                EXCEPTIONS: "application/vnd.ogc.se_xml",
                LAYER: layerName,
                LAYERS: null,
                STYLE: (styleName !== '') ? styleName: null,
                STYLES: null,
                SRS: null,
                FORMAT: null,
                TIME: null
            });
        }
        // End Custom Here!!
        if (url.toLowerCase().indexOf("request=getlegendgraphic") != -1) {
            if (url.toLowerCase().indexOf("format=") == -1) {
                url = Ext.urlAppend(url, "FORMAT=image/gif");
            }
            // add scale parameter - also if we have the url from the record's
            // styles data field and it is actually a GetLegendGraphic request.
            if (this.useScaleParameter === true) {
                var scale = layer.map.getScale();
                url = Ext.urlAppend(url, "SCALE=" + scale);
            }
        }
        var params = Ext.apply({}, this.baseParams);
        if (layer.params._OLSALT) {
            // update legend after a forced layer redraw
            params._OLSALT = layer.params._OLSALT;
        }
        url = Ext.urlAppend(url, Ext.urlEncode(params));

        return url;
    }
});
GeoExt.CustomWMSLegend.supports = function(layerRecord) {
    return layerRecord.getLayer() instanceof OpenLayers.Layer.WMS ? 1 : 0;
};
// Begin Custom Here!!
// Old Code
// 
// New Code
//
// End Custom Here!!
GeoExt.LayerLegend.types["gx_wmslegend"] = GeoExt.CustomWMSLegend;
Ext.reg('gx_wmslegend', GeoExt.CustomWMSLegend);

// [OSM Custom layer here!]
OpenLayers.Layer.OSM.Mapnik = OpenLayers.Class(OpenLayers.Layer.OSM, {
    initialize: function(e, t) {
        var n = ["http://a.tile.openstreetmap.org/${z}/${x}/${y}.png", "http://b.tile.openstreetmap.org/${z}/${x}/${y}.png", "http://c.tile.openstreetmap.org/${z}/${x}/${y}.png"];
        t = OpenLayers.Util.extend({
            numZoomLevels: 19,
            buffer: 0,
            transitionEffect: "resize"
        }, t);
        var r = [e, n, t];
        OpenLayers.Layer.OSM.prototype.initialize.apply(this, r)
    },
    CLASS_NAME: "OpenLayers.Layer.OSM.Mapnik"
});

OpenLayers.Layer.OSM.CycleMap = OpenLayers.Class(OpenLayers.Layer.OSM, {
    initialize: function(e, t) {
        var n = ["http://a.tile.opencyclemap.org/cycle/${z}/${x}/${y}.png", "http://b.tile.opencyclemap.org/cycle/${z}/${x}/${y}.png", "http://c.tile.opencyclemap.org/cycle/${z}/${x}/${y}.png"];
        t = OpenLayers.Util.extend({
            numZoomLevels: 19,
            buffer: 0,
            transitionEffect: "resize"
        }, t);
        var r = [e, n, t];
        OpenLayers.Layer.OSM.prototype.initialize.apply(this, r)
    },
    CLASS_NAME: "OpenLayers.Layer.OSM.CycleMap"
})

OpenLayers.Layer.OSM.TransportMap = OpenLayers.Class(OpenLayers.Layer.OSM, {
    initialize: function(e, t) {
        var n = ["http://a.tile2.opencyclemap.org/transport/${z}/${x}/${y}.png", "http://b.tile2.opencyclemap.org/transport/${z}/${x}/${y}.png", "http://c.tile2.opencyclemap.org/transport/${z}/${x}/${y}.png"];
        t = OpenLayers.Util.extend({
            numZoomLevels: 19,
            buffer: 0,
            transitionEffect: "resize"
        }, t);
        var r = [e, n, t];
        OpenLayers.Layer.OSM.prototype.initialize.apply(this, r)
    },
    CLASS_NAME: "OpenLayers.Layer.OSM.TransportMap"
});

// [DrawFeature custom here!!!]
OpenLayers.Event.observe(document, "keydown", function(evt) {
    var handled = false;
    switch (evt.keyCode) {
        case 90:
            // z
            if (evt.metaKey || evt.ctrlKey) {
                pteViewer.controls.drawLine.undo();
                handled = true;
            }
            break;
        case 89:
            // y
            if (evt.metaKey || evt.ctrlKey) {
                pteViewer.controls.drawLine.redo();
                handled = true;
            }
            break;
        case 27:
            // esc
            pteViewer.controls.drawLine.cancel();
            if (Ext.getCmp("popupDrawLine")) {
                Ext.getCmp("popupDrawLine").hide();
            }
            handled = true;
            break;
    }
    if (handled) {
        OpenLayers.Event.stop(evt);
    }
});

// [DrawFeature custom here!!!]
OpenLayers.Control.CustomDrawLine = OpenLayers.Class(OpenLayers.Control.DrawFeature, {
    geodesic : true,
    /**
     * Method: getGeometryLength
     *
     * Parameters:
     * geometry - {<OpenLayers.Geometry>}
     * units - {String} Unit abbreviation
     *
     * Returns:
     * {Float} The geometry length in the given units.
     */
    getGeometryLength : function (geometry, units) {
        var length = 0.0,
            geomUnits = "m";
        if(this.geodesic) {
            length = geometry.getGeodesicLength(pteViewer.map.getProjectionObject());
            geomUnits = "m";
        } else {
            length = geometry.getLength();
            geomUnits = this.map.getUnits();
        }

        var inPerDisplayUnit = OpenLayers.INCHES_PER_UNIT[units],
            inPerMapUnit = OpenLayers.INCHES_PER_UNIT[geomUnits];

        length *= (inPerMapUnit / inPerDisplayUnit);

        return length;
    }
});

OpenLayers.Control.CustomModifyFeature = OpenLayers.Class(OpenLayers.Control.ModifyFeature, {

});
