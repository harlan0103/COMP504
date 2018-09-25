'use strict'


var app;//app to draw polymorphic shapes on canvas
var intervalID;//id of current interval

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

    var updateLine = function(data){
        clear();
        for(var i = 0; i < data.obs.length; i++){
            drawLine(data.obs[i].startLine.x, data.obs[i].startLine.y, data.obs[i].endLine.x, data.obs[i].endLine.y);
        }
    }

    var clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    }

    return {
        drawLine: drawLine,
        clear: clear,
        updateLine:updateLine
    }
}

/**
 * Setup event handling for buttons
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));

    clearInterval(intervalID);
    intervalID = setInterval(updateLine, 200);

    $("#btn-line").click(createMovingLine);
    $("#btn-switch").click(switchStrategy);
    $("#btn-reset").click(resetLine);
}

/**
 * Create a line at a location on the canvas
 */
function createMovingLine() {
    $.get("/line", function (data, status) {
        //TODO: Implement me
        console.log(data);
        app.drawLine(data.startLine.x, data.startLine.y, data.endLine.x, data.endLine.y);
    }, "json");

}

/**
 * Move a line on the canvas
 */
function switchStrategy() {
    $.get("/switch", function (data, status) {
        app.updateLine(data);
    }, "json");
}

/**
 * Update a line on the canvas
 */
function updateLine() {
    $.get("/update", function (data, status) {
        //TODO: Implement me
        console.log(data);
        app.updateLine(data);
    }, "json");

}

/**
 * Reset canvas
 */
function resetLine() {
    $.get("/reset", function (data, status) {
        app.clear();
    }, "json");


}
/**
 * Clear the canvas
 */
function clear() {
    app.clear();
}