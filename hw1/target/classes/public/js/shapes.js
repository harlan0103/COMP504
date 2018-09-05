'use strict'

//app to draw polymorphic shapes on canvas
var app;

function createApp(canvas) {
    var c = canvas.getContext("2d");

    var drawCircle = function(centreX, centreY, radius, color) {
        c.beginPath();
        // Method to draw a circle
        c.arc(centreX, centreY, radius, 0, 2*Math.PI);
        // Fill in color
        colorFill(color);
        c.stroke();
    }

    // Function to draw rectangle
    var drawRectangle = function(startX, startY, width, height, color){
        c.beginPath();
        // Method to draw a rectangle
        c.rect(startX, startY, width, height);
        colorFill(color);
        c.stroke();
    }

    // Function to draw triangle
    var drawTriangle = function(x1, y1, x2, y2, x3, y3, color){
        c.beginPath();
        // Method to draw a triangle
        c.moveTo(x1, y1);
        c.lineTo(x2, y2);
        c.lineTo(x3, y3);
        c.closePath();
        colorFill(color);
        c.stroke();
    }

    // Function to draw a star
    var drawStar = function(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, color){
        c.beginPath();
        // Path for a star is point 1-3-5-2-4
        c.moveTo(x1, y1);
        c.lineTo(x3, y3);
        c.lineTo(x5, y5);
        c.lineTo(x2, y2);
        c.lineTo(x4, y4);
        c.closePath();
        colorFill(color);
        c.stroke();
    }

    // Function to draw a pentagon
    var drawPentagon = function(x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, color){
        c.beginPath();
        c.moveTo(x1, y1);
        c.lineTo(x2, y2);
        c.lineTo(x3, y3);
        c.lineTo(x4, y4);
        c.lineTo(x5, y5);
        c.closePath();
        colorFill(color);
        c.stroke();
    }

    // Function to fill color in shapes
    var colorFill = function(color){
        c.fillStyle = color;
        // Set outline color
        c.strokeStyle = color;
        c.fill();
    }

    var clear = function() {
        c.clearRect(0, 0, canvas.width, canvas.height);
    }

    return {
        drawCircle: drawCircle,
        drawRectangle: drawRectangle,
        drawTriangle: drawTriangle,
        drawStar: drawStar,
        drawPentagon: drawPentagon,
        clear: clear
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    $("#btn-circle").click(createCircle);
    //When click rectangle button call this method
    $("#btn-rectangle").click(createRectangle);
    //When click triangle button call this method
    $("#btn-triangle").click(createTriangle);
    //When click star button call this method
    $("#btn-star").click(createStar);
    //When click pentagon button call this method
    $("#btn-pentagon").click(createPentagon);
    //When click combo button call this method
    $("#btn-combo").click(combo);
    //Clear out
    $("#btn-clear").click(clear);
}

/**
 * Create a circle at a location on the canvas
 */
function createCircle() {
    $.get("/shape/circle", function (data, status) {
        // Get data in json format
        var circleData = eval(data);
        // Show data in console
        console.log(circleData);
        app.drawCircle(circleData.loc.x, circleData.loc.y, circleData.radius, circleData.color);
    }, "json");
}

/**
 * Create a rectangle at a location on the canvas
 */
function createRectangle(){
    $.get("/shape/rectangle", function (data, status) {
        var rectangleData = eval(data);
        console.log(rectangleData);
        app.drawRectangle(rectangleData.loc.x, rectangleData.loc.y, rectangleData.width, rectangleData.height, rectangleData.color);
    }, "json");
}

/**
* Create a triangle at a location on the canvas
*/
function createTriangle(){
    $.get("/shape/triangle", function (data, status) {
        var triangleData = eval(data);
        console.log(triangleData);
        app.drawTriangle(triangleData.loc.x, triangleData.loc.y, triangleData.x2, triangleData.y2, triangleData.x3, triangleData.y3, triangleData.color);
    }, "json");
}

/**
 * Create a star at a location on the canvas
*/
function createStar(){
    $.get("/shape/star", function (data, status) {
        var starData = eval(data);
        console.log(starData);
        app.drawStar(starData.loc.x, starData.loc.y, starData.x2, starData.y2, starData.x3, starData.y3, starData.x4, starData.y4, starData.x5, starData.y5, starData.color);
    }, "json");
}

/**
* Create a pentagon at a location on the canvas
*/
function createPentagon(){
    $.get("/shape/pentagon", function (data, status) {
        var pentagonData = eval(data);
        console.log(pentagonData);
        app.drawPentagon(pentagonData.loc.x, pentagonData.loc.y, pentagonData.x2, pentagonData.y2, pentagonData.x3, pentagonData.y3, pentagonData.x4, pentagonData.y4, pentagonData.x5, pentagonData.y5, pentagonData.color);
    }, "json");
}

/**
 * Create a function calls all shape drawing function together
 */
function combo(){
    createCircle();
    createRectangle();
    createTriangle();
    createStar();
    createPentagon();
}

/**
* Function for redirect
*/
function redirectBack(){
    $.get("/canvas", function (data, status) {
        // When type endpoint /canvas, remove all the shapes on the canvas
        app.clear();
    }, "json");
}

/**
 * Clear the canvas
 */
function clear() {
    app.clear();
}