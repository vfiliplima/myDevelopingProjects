var API_URL = "http://127.0.0.1:8080/prankguru/api/prank"

$(document).ready(function() {
    getPranks();
    submitPrank();
    search();
    store();
    $('#playButton').hide();
    document.getElementById("my_audio").play();
    document.getElementById('stopButton').onclick = function() {
        var sounds = document.getElementsByTagName('audio');
        for(i=0; i<sounds.length; i++) {
            sounds[i].pause();
        }
        $('#stopButton').hide();
        $('#playButton').show();
    };
    document.getElementById('playButton').onclick = function() {
        var sounds = document.getElementsByTagName('audio');
        for(i=0; i<sounds.length; i++) {
            sounds[i].play();
        }
        $('#stopButton').show();
        $('#playButton').hide();
    };
});



function getPranks() {
    $.ajax({
        url: API_URL, 
        async: true,
        success: printPranks,
        error: errorCallback
    });
};



function errorCallback(request, status, error){
    console.log("fail");
}


function printPranks(data) {
var counter = 0;
    $.each(data, function(i, prank) {

        console.log(data)
        
        
        if(counter = 0) {

            $('.pranks').append(
   
             '<div class="w3-third w3-col w3-container s4 w3-margin-bottom">'+
             '<iframe src="' + prank.url +'" style="width:100%" alt="URL" class="w3-hover-opacity"></iframe>'+
             '<div class="w3-container w3-white">'+
             '<p> '+ prank.prankName +'</p>'+
             '<button id="prank'+prank.id+'" type="button" class="btn btn-outline-dark">See prank details</button>'+
             '</div>'
             );
             counter++;
    
           } else if (counter = 1) {
            $('.pranks').append(
                '<div class="w3-third w3-col w3-container s4 w3-margin-bottom">'+
             '<iframe src="' + prank.url +'" style="width:100%"  alt="URL"  class="w3-hover-opacity"></iframe>'+
             '<div class="w3-container w3-white">'+
             '<p> '+ prank.prankName +'</p>'+
             '<button id="prank'+prank.id+'" type="button" class="btn btn-outline-dark">See prank details</button>'+
             '</div>') 
                counter++
           } else {
            $('.pranks').append(
                '<div class="w3-third w3-col s4 w3-container">'+
             '<iframe src="' + prank.url +'" style="width:100%" alt="URL" class="w3-hover-opacity"></iframe>'+
             '<div class="w3-container w3-white">'+
             '<p> '+ prank.prankName +'</p>'+
             '<button id="prank'+prank.id+'" type="button" class="btn btn-outline-dark">See prank details</button>'+
             '</div>') 
                counter = 0;
           }


        
           $('#prank' + prank.id).click(function() {

            window.location = 'prank.html?url='+prank.id;
           });
    });
};

var store = function() {
    $('#btn-store').click(function() {

        window.location = 'store.html';
       });
}


var search = function () {
    
    $('#prankSearch').click(function(event){
       $('.pranks').empty(); 
        $.ajax({
            url: API_URL + '/' + $('#environmentSearch').val() + '/' + $('#difficultySearch').val() + '/' + $('#participantsSearch').val(),
            type: 'GET',
            success: printPranks,
            error: errorCallback
        })
    })
}


var submitPrank = function() {
    $('#submitNewPrank').click(function(event){
        $.ajax({
            url: API_URL + '/addprank',
            type: 'POST',
            data: JSON.stringify({
                id: 0, 
                prankName: $('#in-prankName').val(),
                description: $('#in-description').val(),
                participants: $('#in-participants').val(),
                environment: $('#in-environment').val(),
                difficulty: $('#in-difficulty').val(),
                url: $('#in-url').val() 
            }),
            contentType: 'application/json',
            success: function() {
                $('.pranks').empty();
                getPranks();
            },
            error: errorCallback
        })
    })
}