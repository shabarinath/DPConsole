function initRichEditor(elementId,width,height) {
	var w; if(width){ w = width; }else {w ='600px;';}
	var h; if(height){ h = height; }else {h ='300px';}
	tinymce.init({
	    selector: "textarea#"+elementId,
	    theme: "modern",
	    width: w,
	    height:h,
	    browser_spellcheck : true,
	    entity_encoding : "named",
    	verify_html : true,
    	cleanup : true,
	    
	    plugins: [
	                "advlist autolink autosave link image lists charmap print preview hr anchor pagebreak",
	                "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
	                "table contextmenu directionality emoticons textcolor paste textcolor"
	             ],
	
	    toolbar1: "newdocument | bold italic underline strikethrough subscript superscript | alignleft aligncenter alignright alignjustify | formatselect fontselect fontsizeselect",
	    toolbar2: "cut copy paste | searchreplace | bullist numlist | outdent indent blockquote | undo redo | link unlink image media code | inserttime preview",
	    toolbar3: "removeformat | table | hr forecolor backcolor | charmap emoticons | print fullscreen | ltr rtl | visualchars visualblocks nonbreaking pagebreak restoredraft",

	    image_advtab: true,

	    image_list: [
	                 {title: 'Close', value: '/resources/img/close.png'},
	                 {title: 'Poly', value: '/resources/img/poly.png'}
	             ],

	    menubar: false,
	    toolbar_items_size: 'small'
	});
}