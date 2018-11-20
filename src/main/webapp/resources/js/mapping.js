

$(document).ready(function () {

    jQuery( ".drag-drop.subjects" ).sortable({
        cursor: "move",
        tolerance:"pointer",
        connectWith: ".drag-drop",
       receive: function(event, ui) {
    	   //showLoading();
           jQuery.ajax({
               url: deleteSubjectUrl,
               data:"subjectId="+ui.item.attr('id'),
               complete:function(data){
            	   jQuery.jGrowl(data.responseText);
            	   //hideLoading();
               }
           });          
       }
    });

    jQuery( ".drag-drop.faculty" ).sortable({
        cursor: "move",
        tolerance:"pointer",
        connectWith: ".drag-drop",
        receive: function(event, ui) {
        	jQuery.ajax({
               url: addSubjectUrl,
               data:"facultyId="+jQuery(this).attr('id')+"&subjectId="+ui.item.attr('id'),
               complete:function(data){
            	   jQuery.jGrowl(data.responseText);
            	   //hideLoading();
               }
           });           
        }
    });
});
