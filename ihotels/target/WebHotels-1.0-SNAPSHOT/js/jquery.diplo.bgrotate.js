/* Simple jQuery background image rotator plug-in by Dan 'Diplo' Booth */
(function($)
{
	$.fn.extend({
		bgrotate: function(options)
		{
			var defaults = {
				delay: 1000,
				images: ["img1.jpg", "img2.jpg", "img3.gif", "img4.jpg", "img5.jpg", "img6.jpg"],
				imagedir: "/images/bkg/"
			}

			var o = $.extend(defaults, options);
			var $obj = $(this);
			var cache = [];
			var i = 0;
			var preCache = true;

			return this.each(function()
			{
				setInterval(function() { setBack($obj, o.images, o.imagedir) }, o.delay);
			});

			function setBack(elem, backgrounds, imagedir)
			{
				elem.css("background-image", "url(" + imagedir + backgrounds[i] + ")");
				i++;
				if (i == backgrounds.length)
				{
					i = 0;
					preCache = false;
				}
				if (preCache)
				{
					var cacheImage = document.createElement('img');
					cacheImage.src = imagedir + backgrounds[i];
					cache.push(cacheImage);
				}
			}
		}
	});
})(jQuery);