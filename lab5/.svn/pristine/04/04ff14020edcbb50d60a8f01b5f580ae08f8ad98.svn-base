'use strict'

//app to draw polymorphic shapes on canvas
var app;

function createApp(canvas) {
    var c = canvas.getContext("2d");

    var drawBall = function(x, y, radius, color) {
        c.fillStyle = color;
        c.beginPath();
        c.arc(x, y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    }

    var drawWall = function(startX, startY, length) {
        c.beginPath();
        c.moveTo(startX, startY);
        c.lineTo(startX, startY + length);
        c.stroke();
    }

    var updateShapeWorld = function(data){
        clear();
        for(var i = 0; i < data.obs.length; i ++){
            if(data.obs[i].type == "ball"){
                drawBall(data.obs[i].loc.x, data.obs[i].loc.y, data.obs[i].radius, data.obs[i].color);
            }
            else if(data.obs[i].type == "wall"){
                drawWall(data.obs[i].loc.x, data.obs[i].loc.y, data.obs[i].len);
            }
        }
    }

    var clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    }


    return {
        drawBall: drawBall,
        drawWall: drawWall,
        updateShapeWorld: updateShapeWorld,
        clear: clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}


/**
 * Entry point into app
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));

    canvasDims();
    setInterval(updatePaintObjWorld, 100);

    $("#btn-load-ball").click(loadBall);
    $("#btn-load-wall").click(loadWall);
    $("#btn-clear").click(clear);
}

/**
 * load a ball at a location on the canvas
 */
function loadBall() {

    $.get("/load/ball", function (data, status) {
        app.drawBall(data.loc.x, data.loc.y, data.radius, data.color);
    }, "json");
}

/**
 * load a wall at a location on the canvas
 */
function loadWall() {

    $.get("/load/wall", function (data, status) {
        app.drawWall(data.loc.x, data.loc.y, data.len);
    }, "json");
}

/**
 *   update the ball and inner walls
 */
function updatePaintObjWorld() {
    $.get("/update", function(data, status) {
        //TODO update the world the latest object information from the model via the controller.
        app.updateShapeWorld(data);
    }, "json");
}


/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.get("/canvas", {height: app.dims.height, width: app.dims.width});
}

/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear");
    app.clear();
}