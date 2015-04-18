(function() {
	
	var thumbsSpacing = 15;

	$('.filter').css('margin-bottom', thumbsSpacing + 'px');
	$('.thumbnail').addClass('showThumb').addClass('fancybox').attr('rel', 'group');

	$('a.sortLink').on('click', function(e) {
		e.preventDefault();
		$('a.sortLink').removeClass('selected');
		$(this).addClass('selected');

		var category = $(this).data('category');
		filterThumbs(category);
	});

	positionThumbs();
	setInterval(checkViewport, 750);

	function checkViewport() {

		var photosWidth = $('.photos').width(),
			thumbsContainerWidth = $('.thumbnail_wrap').width(),
			thumbnailWidth = $('a.thumbnail:first-child').outerWidth();

		if ( photosWidth < thumbsContainerWidth ) {
			positionThumbs();
		}

		if ( (photosWidth - thumbnailWidth) > thumbsContainerWidth ) {
			positionThumbs();
		}
	}

	function filterThumbs(category) {
		
		$('a.thumbnail').each(function() {
			var thumbCategory = $(this).data('categories');

			if ( category === 'all' ) {
				$(this).addClass('showThumb').removeClass('hideThumb').attr('rel', 'group');
			} else {
				if ( thumbCategory.indexOf(category) !== -1 ) {
					$(this).addClass('showThumb').removeClass('hideThumb').attr('rel', 'group');
				} else {
					$(this).addClass('hideThumb').removeClass('showThumb').attr('rel', 'none');
				}
			}
		});

		positionThumbs();

	}

	function positionThumbs() {

		$('a.thumbnail.hideThumb').animate({
			'opacity': 0
		}, 500, function() {
			$(this).css({
				'display': 'none',
				'top': '0px',
				'left': '0px'
			});
		});

		var containerWidth = $('.photos').width(),
			thumbRow = 0,
			thumbColumn = 0,
			thumbWidth = $('.thumbnail img:first-child').outerWidth() + thumbsSpacing,
			thumbHeight = $('.thumbnail img:first-child').outerHeight() + thumbsSpacing,
			maxColumns = Math.floor( containerWidth / thumbWidth );

		$('a.thumbnail.showThumb').each(function(index){
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

			$(this).css('display', 'block').animate({
				'opacity': 1,
				'top': thumbRow + 'px',
				'left': thumbColumn + 'px'
			}, 500);

			var newWidth = thumbColumn + thumbWidth,
				newHeight = thumbRow + thumbHeight;
			$('.thumbnail_wrap').css({
				'width': newWidth + 'px',
				'height': newHeight + 'px'
			});
		});

		findFancyBoxLinks();
	}

	function findFancyBoxLinks() {

		$('a.fancybox[rel="group"]').fancybox({
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