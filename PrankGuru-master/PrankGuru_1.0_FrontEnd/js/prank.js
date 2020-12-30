var API_URL = "http://127.0.0.1:8080/prankguru/api/prank/";
var idUrl;


$(document).ready(function() {

    var parameters = location.search.substring(1).split("&");

    var temp = parameters[0].split("=");
    idUrl = unescape(temp[1]);

    init();
    submitReview();
});


var init = function() {

      $.ajax({
        url: API_URL + idUrl, 
        async: true,
        success: updatePage,
        error: errorCallback
    });
}

var updatePage = function update(prank) {

    $("#youtube-div").append(
        '<iframe width="100%" height="650" src="' + prank.url + '"></iframe>'
        );
    $("#description-div").append('<p>' + prank.description + '</p>');
    
    var list = prank.reviews;

    for(var i = 0; i < list.length; i++) {

         $('#div-review').append(
            
             '<h4> User: '+list[i].username+'</h4>' +
             '<h6> Title: '+list[i].title+'</h6>' +
             '<p> Stars: '+list[i].stars+'</p>' +
            '<div> Review: '+list[i].content+'</div>' +
            '<hr style="width:200px" class="w3-opacity"></hr>'
        );
    }
};

function errorCallback(request, status, error){
    console.log("fail");
}

var submitReview = function() {
   
    $('#submit-review').click(function(event){
        console.log(  $('#in-username').val())
               console.log($('#in-title').val())
                console.log($('#in-stars').val())
                console.log($('#in-content').val())
        $.ajax({
            url: API_URL + idUrl +'/addreview',
            type: 'POST',
            data: JSON.stringify({
                id: 0, 
                username: $('#in-username').val(),
                title: $('#in-title').val(),
                stars: $('#in-stars').val(),
                content: $('#in-content').val()
            }),
            dataType: 'json',
            contentType: 'application/json',
            success: window.location = 'prank.html?url=' + idUrl,
            error: errorCallback
        })
    })
}
