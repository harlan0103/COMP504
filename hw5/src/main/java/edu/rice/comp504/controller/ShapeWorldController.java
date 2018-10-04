package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.DispatcherAdapter;
import edu.rice.comp504.model.paintobj.APaintObject;


import java.awt.*;

import static spark.Spark.*;


public class ShapeWorldController {

    public static void main(String[] args) {
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatcherAdapter dis = new DispatcherAdapter();

        /*
        * @/load
        * When user press make normal shape button
        * Get user selected options and post the request params to the controller
        * */
        post("/load", (request, response) -> {
            // Create a new shape with strategy and shape
            dis.loadPaintObj(request.queryParams("strategy"), request.queryParams("shape"), request.queryParams("switch"));
            return gson.toJson(dis);
        });

        post("/switch", (request, response) -> {
            String shapeSelect = request.queryParams("shape");
            String strategySelect = request.queryParams("strategy");
            // Call the switchStrategy method
            dis.switchStrategy(strategySelect, shapeSelect);
            return gson.toJson(dis);
        });

        get("/update", (request, response) -> {
            dis.updateBallWorld();
            return gson.toJson(dis);
        });

        /*
        * @/canvas
        * Get the canvas width and height from view.js
        * */
        get("/canvas", (request, response) -> {
            // Get the width and height from canvas when loaded
            int width = Integer.parseInt(request.queryParams("width"));
            int height = Integer.parseInt(request.queryParams("height"));
            // Clear out when refresh the page
            dis.clear();
            Point dim = new Point(width, height);
            // Set the dimension info to the adapter
            dis.setCanvasDims(dim);
            return gson.toJson(dis);
        });

        get("/clear", (request, response) -> {
           dis.clear();
           return gson.toJson(dis);
        });
    }
}
