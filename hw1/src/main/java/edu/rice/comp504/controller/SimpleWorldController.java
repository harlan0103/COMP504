package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.*;
import edu.rice.comp504.model.Rectangle;

import java.awt.*;

import static spark.Spark.*;

/**
 * The SimpleWorldController is responsible for interfacing between the view and the model.  The model will determine
 * how shape objects are created.  The view is the browser.  The browser has a canvas that renders the shapes.
 * The controller interacts with the view by receiving REST get requests for various shapes.
 */
public class SimpleWorldController {

    public static void main(String[] args) {
        staticFiles.location("/public");
        Gson gson = new Gson();

        // Create new shape objects
        Circle circle = new Circle(100,100,100,"#FF0000");
        Rectangle rectangle = new Rectangle(200,600,100,50,"#0059FF");
        Triangle triangle = new Triangle(400,20,400,220,700,220, "#00FF2E");
        Star star = new Star(600,300,790,438,718,662,482,662,410,438,"#FFFF00");
        Pentagon pentagon = new Pentagon(250,200,350,300,300,400,200,400,150,300,"#00F7FF");

        // Return the JSON shape representation
        get("/shape/circle", (req, res) ->{
            return gson.toJson(circle);
        });

        get("/shape/rectangle", (req, res) ->{
            return gson.toJson(rectangle);
        });

        get("/shape/triangle", (req, res) ->{
            return gson.toJson(triangle);
        });

        get("/shape/star", (req, res) ->{
            return gson.toJson(star);
        });

        get("/shape/pentagon", (req, res) ->{
            return gson.toJson(pentagon);
        });

        // When enter endpoint canvas, redirect to main page
        redirect.get("/canvas", "/");
    }
}
