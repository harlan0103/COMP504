'use strict'

//app to draw polymorphic shapes on canvas
var app;

//id of current interval
var intervalID;

/**
 * Draw and clear line on a the canvas
 * @param canvas  The canvas used to draw a line
 * @returns {{drawLine: drawLine, clear: clear}}
 */
function createApp(canvas) {
    var c = canvas.getContext("2d");

    var drawLine = function(startX, startY, endX, endY) {
        c.beginPath();
        c.moveTo(startX, startY);
        c.lineTo(endX, endY);
        c.stroke();
    }

    var clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    }

    return {
        drawLine: drawLine,
        clear: clear
    }
}

/**
 * Setup event handling for buttons
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));

    $("#btn-line").click(createLine);
    $("#btn-move").click(moveLine);
    $("#btn-reset").click(resetLine);
}

/**
 * Create a line at a location on the canvas
 */
function createLine() {
    $.get("/line", function (data, status) {
       //TODO: draw a line that does not move
        app.clear();
        var newline = eval(data);
        app.drawLine(newline.startX, newline.startY, newline.endX, newline.endY);
    }, "json");
}

/**
 * Move a line on the canvas
 */
var interval
function moveLine() {
    //TODO: move the line every .2 seconds by setting an interval
    interval = setInterval(updateLine, 200);
}

/**
 * Update a line on the canvas
 */
function updateLine() {
    $.get("/update", function (data, status) {
       //TODO: update the line position
        app.clear();
        var newline = eval(data);
        app.drawLine(newline.startX, newline.startY, newline.endX, newline.endY);
    }, "json");
}

/**
 * Reset canvas
 */
function resetLine() {
    $.get("/reset", function (data, status) {
        //TODO: reset the canvas, no line should appear
        app.clear();
        clearInterval(interval);
    }, "json");


}
/**
 * Clear the canvas
 */
function clear() {
    app.clear();
}