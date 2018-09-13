'use strict'

//app to draw polymorphic shapes on canvas
var app;

/**
 * Creates a an object that has functions that draw and clear objects in the BallWorld
 * @param canvas  The canvas to draw balls on
 * @returns {{drawBall: drawBall, clear: clear}}
 */
function createApp(canvas) {
    var c = canvas.getContext("2d");

    /*
    * @drawBall: function to draw a ball based on the data passed in
    * */
    var drawBall = function(data) {
        console.log(data);
        c.beginPath();
        c.fillStyle = data.color;
        c.arc(data.loc.x, data.loc.y, data.radius, 0, 2*Math.PI);
        c.fill();
    }

    /*
    * @clear: function to clear the canvas
    * */
    var clear = function() {
        console.log("clear canvas");
        c.clearRect(0, 0, canvas.width, canvas.height);
    }


    /*
    * @update: function to update the existing ball
    * */
    var update = function(data){
        console.log(data);
        clear();
        console.log(data.obs.length);
        for(var i = 0; i < data.obs.length; i++){
            console.log(data.obs[i]);
            drawBall(data.obs[i]);
        }
    }

    return {
        drawBall: drawBall,
        clear: clear,
        update: update
    }
}

/**
 * When the window loads, get the app that can draw and clear balls on the canvas
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));

    $("#btn-clear").click(clear);
    $("#btn-straight").click("straight", createBall);
    $("#btn-vertical").click("vertical", createBall);
    $("#btn-horizontal").click("horizontal", createBall);
    $("#btn-rotating").click("rotating", createBall);
    $("#btn-diagonal").click("diagonal", createBall);
    $("#btn-sizechange").click("sizechange", createBall);
    $("#btn-colorchange").click("colorchange", createBall);
    // Update every 0.1 seconds
    setInterval(updateBallWorld, 100);
}

/**
 * Create a ball at a location on the canvas
 */
function createBall(kind) {
    $.get("/ball/" + kind.data, function (data, status) {
        console.log(kind.data);
        app.drawBall(data);
    }, "json");
}

/**
 * Update all the balls in the BallWorld
 */
function updateBallWorld() {
    $.get("/update", function(data, status) {
        console.log("update ball");
        app.update(data);
    }, "json");
}

/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear");
}