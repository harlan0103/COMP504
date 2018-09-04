'use strict'

//app to draw polymorphic shapes on canvas
var app;

function createApp(canvas) {
    var c = canvas.getContext("2d");

    var drawCircle = function(centreX, centreY, radius) {
        // To draw a circle, set start angle as 0 and end circle as 2*Math.PI
        c.beginPath();
        c.arc(centreX, centreY, radius, 0, 2*Math.PI);
        c.stroke();
    }

    var clear = function() {
        // The coordinate(0,0) is the left corner of the canvas
        c.clearRect(0, 0, canvas.width, canvas.height);
    }

    return {
        drawCircle: drawCircle,
        clear: clear
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    // When click circle btn, call "createCircle()"
    $("#btn-circle").click(createCircle);
    // When click clear btn, call "clear()"
    $("#btn-clear").click(clear);
}

/**
 * Create a circle at a location on the canvas
 */
function createCircle() {
    $.get("/shape/circle", function (data, status) {
        app.drawCircle();
    }, "json");
}

/**
 * Clear the canvas
 */
function clear() {
    app.clear();
}