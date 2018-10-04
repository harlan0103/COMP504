'use strict'

//app to draw polymorphic shapes on canvas
var app;
var shapeInterval;

function createApp(canvas) {
    var c = canvas.getContext("2d");

    /*
    * Receive data collect from adapter
    * */
    var getShape = function(data){
        console.log(data);
        for(var i = 0; i < data.obs.length; i++){
            console.log(data.obs[i]);
            if(data.obs[i].type == "CompositeObject"){
                //console.log("it is composite");
                app.drawComposite(data.obs[i].children);
            }
            else{
                // For single object selected
                var shape = data.obs[i];
                app.drawShape(shape);
            }
        }
    }

    /*
    * To draw shapes based on the shape param
    * */
    var drawShape = function(shape){
        console.log(shape.type);
        if(shape.type == "Ball"){
            app.drawBall(shape.loc.x, shape.loc.y, shape.radius, shape.color);
        }
        else if(shape.type == "Rectangle"){
            app.drawRectangle(shape.loc.x, shape.loc.y, shape.side, shape.color);
        }
        else if(shape.type == "Fish"){
            // Draw fish img based on the flip status
            app.drawFish(shape.loc.x, shape.loc.y, shape.imgWidth, shape.imgHeight, shape.flip, shape.angle);
        }
        else if(shape.type == "Diamond"){
            // Draw a diamond shape
            var locX = shape.loc.x;
            var locY = shape.loc.y;
            var radius = shape.radius;
            c.beginPath();
            c.moveTo(locX, locY - radius);
            c.lineTo(locX - radius, locY);
            c.lineTo(locX, locY + radius);
            c.lineTo(locX + radius, locY);
            c.closePath();
            c.fillStyle = shape.color;
            c.fill();
        }
        else if(shape.type == "Triangle"){
            // Draw a triangle shape
            var locX = shape.loc.x;
            var locY = shape.loc.y;
            var side = shape.side;
            c.beginPath();
            c.moveTo(locX + side / 2, locY);
            c.lineTo(locX, locY + side);
            c.lineTo(locX + side, locY + side);
            c.closePath();
            c.fillStyle = shape.color;
            c.fill();
        }
        else if(shape.type == "Pentagon"){
            // Draw a hexagon shape
            var locX = shape.loc.x;
            var locY = shape.loc.y;
            var side = shape.side;
            c.beginPath();
            c.moveTo(locX + side / 2, locY);
            c.lineTo(locX, locY + side / 3);
            c.lineTo(locX + side / 5, locY + side);
            c.lineTo(locX + side / 5 * 4, locY + side);
            c.lineTo(locX + side, locY + side / 3);
            c.closePath();
            c.fillStyle = shape.color;
            c.fill();
        }
        else if(shape.type == "Hexagon"){
            // Draw a hexagon shape
            var locX = shape.loc.x;
            var locY = shape.loc.y;
            var side = shape.side;
            c.beginPath();
            c.moveTo(locX + side / 2, locY);
            c.lineTo(locX, locY + side / 4);
            c.lineTo(locX, locY + side / 4 * 3);
            c.lineTo(locX + side / 2, locY + side);
            c.lineTo(locX + side, locY + side / 4 * 3);
            c.lineTo(locX + side, locY + side / 4);
            c.closePath();
            c.fillStyle = shape.color;
            c.fill();
        }
        else if(shape.type == "Octagon"){
            // Draw a octagon shape
            var locX = shape.loc.x;
            var locY = shape.loc.y;
            var side = shape.side;
            c.beginPath();
            c.moveTo(locX + side / 4, locY);
            c.lineTo(locX, locY + side / 4);
            c.lineTo(locX, locY + side / 4 * 3);
            c.lineTo(locX + side / 4, locY + side);
            c.lineTo(locX + side / 4 * 3, locY + side);
            c.lineTo(locX + side, locY + side / 4 * 3);
            c.lineTo(locX + side, locY + side / 4);
            c.lineTo(locX + side / 4 * 3, locY);
            c.closePath();
            c.fillStyle = shape.color;
            c.fill();
        }
        else{

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
    * To draw a fish img based on the params
    * */
    var drawFish = function(locX, locY, imgWidth, imgHeight, flip, angle){
        var img = new Image();
        if(flip == true){
            img.src = "./fish_flip.png";
        }
        else{
            img.src = "./fish.png";
        }
        var x = locX + imgWidth / 2;
        var y = locY + imgHeight / 2;
        // To rotate the fish
        c.save();
        c.translate(x, y);
        c.rotate(angle);
        c.drawImage(img, locX - x, locY - y, imgWidth, imgHeight);
        c.restore();
    }

    /*
   * Draw a composite shapes
   * Check the shape type of each shape
   * And draw them
   * */
    var drawComposite = function(data){
        console.log(data);
        for(var i = 0; i < data.length; i++){
            var shape = data[i];
            app.drawShape(shape);
        }
    }

    /*
    * Update shape status
    * */
    var updateShapeWorld = function(data){
        app.clear();
        app.getShape(data);
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
        getShape:getShape,
        drawFish: drawFish,
        drawComposite: drawComposite,
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
        app.getShape(data);
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
        app.getShape(data);
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