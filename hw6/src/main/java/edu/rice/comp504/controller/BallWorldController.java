package edu.rice.comp504.controller;

import edu.rice.comp504.model.DispatchAdapter;

import com.google.gson.Gson;
import edu.rice.comp504.model.paint.Ball;

import java.awt.*;

import static spark.Spark.*;

/**
 * The controller that creates the dispatch adapter and defines the REST end points
 */
public class BallWorldController {

    public static void main(String[] args) {
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();

        /*
        * load a ball with user selected strategy
        * */
        post("/load", (request, response) -> {
            // Create a new shape with strategy and shape
            Ball newBall = dis.loadBall(request.queryParams("strategy"), request.queryParams("switch"));
            return gson.toJson(newBall);
        });

        post("/switch", (request, response) -> {
            dis.switchStrategy(request.queryParams("strategy"));
            return gson.toJson(dis);
        });

        get("/update", (request, response) -> {
            dis.updateBallWorld();
            return gson.toJson(dis);
        });

        get("/clear", (request, response) -> {
            dis.clear();
            return gson.toJson(dis);
        });

        /*
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

    }
}
