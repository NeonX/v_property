// Openlayers proxy.
//OpenLayers.ProxyHost = "/cgi-bin/proxy.cgi?url=";

var mapPanel;
Ext.onReady(function() {
    // create a map panel with some layers that we will show in our layer tree
    // below.

    // Define projection.
    var epsg900913 = new OpenLayers.Projection("EPSG:900913");
    var epsg4326   = new OpenLayers.Projection("EPSG:4326");
    //var mapExtent  = new OpenLayers.Bounds(10196525.4352859, 556587.116485269, 12594057.6466071, 2406334.17862453)  // South-East Asian Boundary
	var mapExtent  = new OpenLayers.Bounds(10836245.2935552, 625851.090063557, 11759450.7862112, 2328135.29927052)  // South-East Asian Boundary

    // Define layers.
    layers = [
        new OpenLayers.Layer.Google("Google Street", {
            numZoomLevels   : 19,
            maxResolution   : 4891.969809375
        }),
        new OpenLayers.Layer.Google("Google Satellite", {
            type            : google.maps.MapTypeId.SATELLITE,
            numZoomLevels   : 19,
            maxResolution   : 4891.969809375
        })
    ];
   
    var openlayersMap = new OpenLayers.Map({
        projection          : epsg900913,
        displayProjection   : epsg4326,
        units               : "m",
        numZoomLevels       : 18,
        maxResolution       : 156543.0339,
        maxExtent           : new OpenLayers.Bounds(-20037508, -20037508, 20037508, 20037508.34),
        layers              : layers
    });

    var geoextMapPanel = new GeoExt.MapPanel({
        id              : "pte-map",
        border          : true,
        region          : "center",
        bodyCssClass    : "map-background",
        extent          : mapExtent,
        center          : [11195790.897159, 1545113.582848],
        zoom            : 5,
        map             : openlayersMap,
        bbar            : new Ext.ux.StatusBar({
            cls : "status-toolbar",
            id : "map-statusbar",
            statusAlign : "right",
            text : "Pte Engineering Consultants Ltd.",
            items : [
            ]
        })
    });

    var allPanel = new Ext.Panel({
        cls         : "disable-select",
        region      : "center",
        layout      : "border",
        border      : false,
        items       : [geoextMapPanel]
    });

    var extPanel = new Ext.Viewport({
        layout      : "fit",
        hideBorders : true,
        items       : {
            layout              : "border",
            deferredRender      : false,
            items               : [allPanel]
        }
    });
});