'use strict'

//app to draw polymorphic shapes on canvas
var app;
var ballInterval;

function createApp(canvas) {
    var c = canvas.getContext("2d");

    /**
     * drawBall
     * Draw a ball shape based on the passed value
     * */
    var drawBall = function(x, y, radius, color) {
        c.beginPath();
        c.fillStyle = color;
        c.arc(x, y, radius, 0, 2*Math.PI);
        c.fill();
    }

    /**
     * Clear out the canvas
     * */
    var clear = function() {
        c.clearRect(0, 0, canvas.width, canvas.height);
    }

    /**
     * Update Ball World
     * */
    var updateBallWorld = function(data){
        app.clear();
        for(var i = 0; i < data.obs.length; i++){
            var ball = data.obs[i];
            app.drawBall(ball.loc.x, ball.loc.y, ball.radius, ball.color);
        }

    }

    return {
        drawBall: drawBall,
        clear: clear,
        updateBallWorld: updateBallWorld
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    canvasDims();  //   Pass dimension value when initialized
    $("#btn-load").click(loadBall);
    $("#btn-load-switcher").click(loadSwitchBall);
    $("#btn-switch").click(switchStrategy);
    $("#btn-clear").click(clear);
    ballInterval = setInterval(updateBallWorld, 100);

}

/**
 * load a paint at a location on the canvas
 */
function loadBall() {
    // Get the selected strategy
    var strategy = readStrategy();
    $.post("/load", {
        // post shape and strategy params to the controller
        "strategy": strategy,
        "switch": "false"
    }, function (data, status) {
        //console.log(data);
        app.drawBall(data.loc.x, data.loc.y, data.radius, data.color);
    }, "json");
}

/**
 * load a paint at a location on the canvas
 */
function loadSwitchBall() {
    // Get the selected strategy
    var strategy = readStrategy();
    $.post("/load", {
        // post shape and strategy params to the controller
        "strategy": strategy,
        "switch": "true"
    }, function (data, status) {
        //console.log(data);
        app.drawBall(data.loc.x, data.loc.y, data.radius, data.color);
    }, "json");
}

function switchStrategy() {
    var strategy = readStrategy();
    $.post("/switch", {
        "strategy": strategy
    }, function (data, status) {
        app.updateBallWorld(data);
    }, "json");
}

function updateBallWorld() {
    $.get("/update", function(data, status) {
        console.log(data);
        app.updateBallWorld(data);
    }, "json");
}
/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear");
    app.clear();
}

/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    var canvas = document.querySelector("canvas")
    $.get("/canvas", {
        // Return the canvas width and height to the controller
        "width": canvas.width,
        "height": canvas.height
    });
}

/*
* @readStrategy
* Read all selected options from multiple select
* Then create a string containing all selected options
* Return to loadBall function and transfer options to controller
* */
function readStrategy(){
    var strategy = "";
    var options = document.getElementById("strategySelector");
    // For each selected strategy
    for(var i = 0; i < options.length; i++){
        // Push every selected option into the array
        if(options.options[i].selected == true) {
            // Add to the strategy string
            strategy += options.options[i].text + " ";
        }
    }
    //console.log(strategy);
    return strategy;
}