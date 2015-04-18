(function() {
	
	var thumbsSpacing = 15;

	jQuery('.filter').css('margin-bottom', thumbsSpacing + 'px');
	jQuery('.thumbnail').addClass('showThumb').addClass('fancybox').attr('rel', 'group');

	jQuery('a.sortLink').on('click', function(e) {
		e.preventDefault();
		jQuery('a.sortLink').removeClass('selected');
		jQuery(this).addClass('selected');

		var category = jQuery(this).data('category');
		filterThumbs(category);
	});

	positionThumbs();
	setInterval(checkViewport, 750);

	function checkViewport() {

		var photosWidth = jQuery('.photos').width(),
			thumbsContainerWidth = jQuery('.thumbnail_wrap').width(),
			thumbnailWidth = jQuery('a.thumbnail:first-child').outerWidth();

		if ( photosWidth < thumbsContainerWidth ) {
			positionThumbs();
		}

		if ( (photosWidth - thumbnailWidth) > thumbsContainerWidth ) {
			positionThumbs();
		}
	}

	function filterThumbs(category) {
		
		jQuery('a.thumbnail').each(function() {
			var thumbCategory = jQuery(this).data('categories');

			if ( category === 'all' ) {
				jQuery(this).addClass('showThumb').removeClass('hideThumb').attr('rel', 'group');
			} else {
				if ( thumbCategory.indexOf(category) !== -1 ) {
					jQuery(this).addClass('showThumb').removeClass('hideThumb').attr('rel', 'group');
				} else {
					jQuery(this).addClass('hideThumb').removeClass('showThumb').attr('rel', 'none');
				}
			}
		});

		positionThumbs();

	}

	function positionThumbs() {

		jQuery('a.thumbnail.hideThumb').animate({
			'opacity': 0
		}, 500, function() {
			jQuery(this).css({
				'display': 'none',
				'top': '0px',
				'left': '0px'
			});
		});

		var containerWidth = jQuery('.photos').width(),
			thumbRow = 0,
			thumbColumn = 0,
			thumbWidth = jQuery('.thumbnail img:first-child').outerWidth() + thumbsSpacing,
			thumbHeight = jQuery('.thumbnail img:first-child').outerHeight() + thumbsSpacing,
			maxColumns = Math.floor( containerWidth / thumbWidth );

		jQuery('a.thumbnail.showThumb').each(function(index){
			var remainder = ( index%maxColumns ) / 100,
				maxIndex = 0;

			if( remainder === 0 ) {
				if( index !== 0 ) {
					thumbRow += thumbHeight;
				}
				thumbColumn = 0;
			} else {
				thumbColumn += thumbWidth;
			}

			jQuery(this).css('display', 'block').animate({
				'opacity': 1,
				'top': thumbRow + 'px',
				'left': thumbColumn + 'px'
			}, 500);

			var newWidth = thumbColumn + thumbWidth,
				newHeight = thumbRow + thumbHeight;
			jQuery('.thumbnail_wrap').css({
				'width': newWidth + 'px',
				'height': newHeight + 'px'
			});
		});

		findFancyBoxLinks();
	}

	function findFancyBoxLinks() {

		jQuery('a.fancybox[rel="group"]').fancybox({
			'transitionIn' : 'elastic',
			'transitionOut' : 'elastic',
			'titlePosition' : 'over',
			'speedIn' : 500,
			'overlayColor' : '#000',
			'padding' : 0,
			'overlayOpacity' : .75
		});
	}

})();