var mapPanel;
Ext.onReady(function(){

	//===== Define Projection & map extent =====
	var mapExtent  = new OpenLayers.Bounds(10836245.2935552, 625851.090063557, 11759450.7862112, 2328135.29927052);  // South-East Asian Boundary
    var epsg900913 = new OpenLayers.Projection("EPSG:900913");
    //var epsg4326   = new OpenLayers.Projection("EPSG:4326");

	//===== Define Layers =====
	var layers = [
			new OpenLayers.Layer.Google("Google Roadmap",{
				numZoomLevels   : 19,
				maxResolution   : 4891.969809375,
				isBaseLayer		: true
			}),
			new OpenLayers.Layer.Google("Google Satellite", {
				type	: google.maps.MapTypeId.SATELLITE,
				numZoomLevels	: 19,
				maxResolution   : 4891.969809375,
				isBaseLayer		: true
			}),
			new OpenLayers.Layer.Google("Google Hybrid", {
				type	: google.maps.MapTypeId.HYBRID,
				numZoomLevels	: 19,
				maxResolution   : 4891.969809375,
				isBaseLayer		: true
			}),
			new OpenLayers.Layer.Google("Google Terrain", {
				type	: google.maps.MapTypeId.TERRAIN ,
				numZoomLevels	: 19,
				maxResolution   : 4891.969809375,
				isBaseLayer		: true
			}),
			new OpenLayers.Layer.WMS("แปลงที่ดิน",
                    "http://mapdb.pte.co.th/geoserver/ProtoPj/wms",
                    {
						layers: "ProtoPj:gis_province",
	                    format: "image/png",
	                    transparent: true
                    },{
                    	isBaseLayer: false,
                    	visibility : false,
                    	transitionEffect: "resize"
                    }
			),
			new OpenLayers.Layer.WMS("พื้นที่แบ่งเช่า",
                    "http://mapdb.pte.co.th/geoserver/ProtoPj/wms",
                    {
						layers: "ProtoPj:ProtoPj_amphoe_all",
						transparent: true,
	                    format: "image/png"
                    },{
                    	isBaseLayer : false,
                    	visibility : false,
                    	minScale : 866688,
                    	transitionEffect: "resize"
                    }
			)
	];

	//===== Tree Panel Layers =====
	//create our own layer node UI class, using the TreeNodeUIEventMixin
    var layerNodeUI = Ext.extend(GeoExt.tree.LayerNodeUI, new GeoExt.tree.TreeNodeUIEventMixin());
	var treeConfig = [{
			nodeType	: "gx_baselayercontainer",
			expanded: true, 
			singleClickExpand: true
		},{
			text : "แปลงที่ดิน / พื้นที่แบ่งเช่า",
			nodeType : "gx_overlaylayercontainer",
			expanded: true,
			singleClickExpand: true,
			layerStore: new GeoExt.data.LayerStore({
                layers: [
                    layers[4],
                    layers[5]
                ]
            })
		}];

	var treePanel = new Ext.tree.TreePanel({
		border	: true,
		region	: "west",
		title	: "Layers",
		width	: 200,
		split	: true,
		collapsible: true,
        collapseMode: "mini",
        autoScroll: true,
        loader: new Ext.tree.TreeLoader({
            applyLoader: false,
            uiProviders: {
                "layernodeui": layerNodeUI
            }
        }),
		root: new Ext.tree.AsyncTreeNode({
			children	: treeConfig
		}),
		rootVisible: false
	});

	 treePanel.getSelectionModel().on("beforeselect", function(sm, node) {
        if (node.isLeaf()) {
            node.layer.setVisibility();
        }
        return false;
    });
	
	//===== Openlayermap =====
	var openlayersMap = new OpenLayers.Map({
        projection          : epsg900913,
        units               : "m",
        numZoomLevels       : 18,
        maxResolution       : 156543.0339,
        maxExtent           : new OpenLayers.Bounds(-20037508, -20037508, 20037508, 20037508.34),
		layers				: layers
    });

	//===== Toolbar and Action =====
	var zmExtentAction = new GeoExt.Action({
		iconCls     : "icon-zoom-extent",
        tooltip     : "Extent",
		control		: new OpenLayers.Control.ZoomToMaxExtent()
	});

	var toolbar = new Ext.Toolbar([zmExtentAction]);


	var mapPanel = new GeoExt.MapPanel({
		id		: "ogis-map",
		region  : "center",
		extent	: mapExtent,
		map		: openlayersMap,
		tbar	: toolbar,
		xtype	: "gx_mappanel",
		split: true
	});


/*	//===== Viewport for view map =====
	var extPanel = new Ext.Viewport({
        layout      : "border",
        hideBorders : true,
		items		: [treePanel, mapPanel]
    });
	//*/
	
	extPanel = new Ext.Panel({
        id		: "Panel",
        layout	: "border",
        renderTo: "view",
        height	: jQuery(window).height()-120,
        items	: [treePanel, mapPanel]


    });
	
	jQuery( window ).resize(function() {
		console.debug('win resize');
		extPanel.setHeight(jQuery(window).height()-120);
	});
});