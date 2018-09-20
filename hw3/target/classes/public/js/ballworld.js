'use strict'

//app to draw polymorphic shapes on canvas
var app;
var ballInterval;

/**
 * Application returns object that draws on or clears the canvas
 */
function createApp(canvas) {
    var c = canvas.getContext("2d");

    /*
    * @drawBall: function to draw a ball based on the data passed in
    * */
    var drawBall = function(x, y, radius, color) {
        //console.log(x + " " + y + " " + radius + " " + color);
        c.beginPath();
        c.fillStyle = color;
        c.arc(x, y, radius, 0, 2*Math.PI);
        c.fill();
    }

    var updateBallWorld = function(data){
        clear();
        //console.log(data.obs);
        for(var i = 0; i < data.obs.length; i++){
            drawBall(data.obs[i].loc.x, data.obs[i].loc.y, data.obs[i].radius, data.obs[i].color);
        }
    }

    /*
    * @Clear the canvas
    * */
    var clear = function() {
        //console.log("clear canvas");
        c.clearRect(0, 0, canvas.width, canvas.height);
    }


    return {
        drawBall: drawBall,
        clear: clear,
        updateBallWorld: updateBallWorld
    }
}

/**
 * Setup button event listeners and update BallWorld interval
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    // Get canvas width and height when the page is load
    canvasDims();
    //console.log("here");
    /*When user click 'Make normal ball', call loadBall method*/
    $("#btn-load").click(loadBall);
    $("#btn-clear").click(clear);
    $("#btn-switcherBall").click(loadSwitcher);
    $("#btn-switch").click(switchStrategy);

    // Update ball world every 0.1 second
    ballInterval = setInterval(updateBallWorld, 100);
}




/**
 * load a ball at a location on the canvas
 */
function loadBall() {
    // Get the selected strategy
    var strategy = readSelect();
    $.post("/load", {"strategy": strategy}, function (data, status) {
        //console.log(data);
        app.drawBall(data.loc.x, data.loc.y, data.radius, data.color);
    }, "json");
}

/**
 * load a switcher ball at a location on the canvas
 */
function loadSwitcher() {
    // Get the selected strategy
    var strategy = readSelect();
    $.post("/load/switcher", {"strategy": strategy}, function (data, status) {
        app.drawBall(data.loc.x, data.loc.y, data.radius, data.color);
    }, "json");
}

/**
 * Switch strategies for all the switcher strategy balls
 */
function switchStrategy() {
    // Get the selected strategy
    var strategy = readSelect();
    $.post("/switch", {"strategy": strategy}, function (data, status) {
        //console.log(data);
        app.updateBallWorld(data);
    }, "json");
}

/**
 * Update all the balls in the BallWorld
 */
function updateBallWorld() {
    $.get("/update", function(data, status) {
        app.updateBallWorld(data);
    }, "json");
}

/**
 * Dimensions of Canvas
 */
function canvasDims() {
    var canvas = document.querySelector("canvas");
    $.get("/canvas", {
        // Get the parameter of canvas width and height
        width: canvas.width,
        height: canvas.height
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
* @readSelect
* Read all selected options from multiple select
* Then create a string containing all selected options
* Return to loadBall function and transfer options to controller
* */
function readSelect(){
    var strategy = " ";
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