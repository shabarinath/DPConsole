/**
 * User: napster
 * Date: 8/14/11
 * Time: 6:10 PM
 */

function showScrollButtons()
{
    jQuery(".code").each(function(){
        if(this.scrollWidth != this.clientWidth || this.scrollLeft!=0)
        {
            jQuery("#link-right-"+jQuery(this).attr("id")).css({"visibility":"visible"});
            jQuery("#link-left-"+jQuery(this).attr("id")).css({"visibility":"visible"});
        }
        else
        {
            jQuery("#link-right-"+jQuery(this).attr("id")).css({"visibility":"hidden"});
            jQuery("#link-left-"+jQuery(this).attr("id")).css({"visibility":"hidden"});
        }
    });
}

function scrollDiv(div,right)
{
    if(right)
    {
        $(div).scrollLeft($(div).scrollLeft()+100);
    }
    else
    {
        $(div).scrollLeft($(div).scrollLeft()-100);
    }
    showScrollButtons();
}

