'use strict'

//app to draw polymorphic shapes on canvas
var app;
var shapeInterval;

function createApp(canvas) {
    var c = canvas.getContext("2d");

    /*
    * Receive data collect from adapter
    * */
    var drawShape = function(data){
        for(var i = 0; i < data.obs.length; i++){
            if(data.obs[i].type == "Ball"){
                app.drawBall(data.obs[i].loc.x, data.obs[i].loc.y, data.obs[i].size, data.obs[i].color);
            }
            else{
                app.drawRectangle(data.obs[i].loc.x, data.obs[i].loc.y, data.obs[i].size, data.obs[i].color);
            }
        }
    }

    /*
    * To draw a all based on the params
    * */
    var drawBall = function(x, y, radius, color) {
        //console.log(x + ";" + y + ";" + radius + ";" + color);
        c.beginPath();
        c.fillStyle = color;
        c.arc(x, y, radius, 0, 2*Math.PI);
        c.fill();
    }

    /*
    * To draw a rectangle based on the params
    * */
    var drawRectangle = function(x, y, side, color){
        //console.log(x + ";" + y + ";" + radius + ";" + color);
        c.beginPath();
        c.fillStyle = color;
        c.fillRect(x, y, side, side);
        c.fill();
    }

    /*
    * Update shape status
    * */
    var updateShapeWorld = function(data){
        app.clear();
        app.drawShape(data);
    }

    /*
    * Clear the canvas
    * */
    var clear = function() {
        c.clearRect(0, 0, canvas.width, canvas.height);
    }


    return {
        drawBall: drawBall,
        drawRectangle: drawRectangle,
        drawShape: drawShape,
        updateShapeWorld: updateShapeWorld,
        clear: clear,
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    canvasDims();
    // Draw normal shape based on user select
    $("#btn-normalShape").click(loadPaintObj);
    $("#btn-switcherShape").click(loadSwitcherObj);
    $("#btn-switch").click(switchStrategy);
    $("#btn-clear").click(clear);

    // Update shape world every 0.1 second
    shapeInterval = setInterval(updateBallWorld, 100);
}

/**
 * load a paintobj at a location on the canvas
 */
function loadPaintObj() {
    // Get the selected shape and selected strategy
    var shape = readShape();
    var strategy = readStrategy();
    $.post("/load", {
        // post shape and strategy params to the controller
        "shape": shape,
        "strategy": strategy,
        "switch": "false"
    }, function (data, status) {
        app.drawShape(data);
    }, "json");
}

/**
 * load a switcher object on the canvas
 */
function loadSwitcherObj() {
    // Get the selected shape and selected strategy
    var shape = readShape();
    var strategy = readStrategy();
    $.post("/load", {
        // post shape and strategy params to the controller
        "shape": shape,
        "strategy": strategy,
        "switch": "true"
    }, function (data, status) {
        app.drawShape(data);
    }, "json");
}

/**
 * Switch strategy for all switcher objects of right type
 */
function switchStrategy() {
    // Get the selected shape and selected strategy
    var shape = readShape();
    var strategy = readStrategy();
    $.post("/switch", {
        "shape": shape,
        "strategy": strategy
    }, function (data, status) {
        app.updateShapeWorld(data)
    }, "json");
}

function updateBallWorld() {
    $.get("/update", function(data, status) {
        //console.log(data);
        app.updateShapeWorld(data);
    }, "json");
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

/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear");
    app.clear();
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

/*
* @readShape
* Read all selected options from multiple select
* Then create a string containing all selected options
* Return to loadBall function and transfer options to controller
* */
function readShape(){
    var shape = "";
    var options = document.getElementById("shapeSelector");
    // For each selected shape
    for(var i = 0; i < options.length; i++){
        // Push every selected option into the array
        if(options.options[i].selected == true){
            // Add to the shape string
            shape += options.options[i].text + " ";
        }
    }
    return shape;
}