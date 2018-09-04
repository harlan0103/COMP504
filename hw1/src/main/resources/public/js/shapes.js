'use strict'

//app to draw polymorphic shapes on canvas
var app;

function createApp(canvas) {
    var c = canvas.getContext("2d");

    var drawCircle = function() {
    }

    var clear = function() {
    }

    return {
        drawCircle: drawCircle,
        clear: clear
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    $("#btn-circle").click(createCircle);
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