package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.ball.Ball;

import java.awt.*;

import static spark.Spark.*;

/**
 * The ball world controller provides the interface between the view and the dispatch adapter. It creates
 * the dispatch adapter and sets the communication between the dispatch adapter and the model.
 */
public class BallWorldController {

    /**
     * Entrypoint to local server
     * @param args  arguments passed to the entrypoint (normally from the command line)
     */
    public static void main(String[] args) {
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatchAdapter dis = new DispatchAdapter();


        post("/load", (request, response) -> {
            // Create a ball based on passing param
            Ball newBall = dis.loadBall(request.body(), false);
            return gson.toJson(newBall);
        });


        post("/load/switcher", (request, response) -> {
            // Create a ball based on passing param
            Ball newBall = dis.loadBall(request.body(), true);
            return gson.toJson(newBall);
        });

        post("/switch", (request, response) -> {
            // pass the strategy to the switchStrategy function
            dis.switchStrategy(request.body());
            return gson.toJson(dis);
        });

        get("/update", (request, response) -> {
            dis.updateBallWorld();
            return gson.toJson(dis);
        });

        get("/canvas", (request, response) -> {
            // Get the width and height from canvas when loaded
            int width = Integer.parseInt(request.queryParams("width"));
            int height = Integer.parseInt(request.queryParams("height"));
            //System.out.println("width: " + width + ", height: " + height);
            dis.clear();
            Point dim = new Point(width, height);
            dis.setCanvasDims(dim);
            return gson.toJson(dis);
        });

        get("/clear", (request, response) -> {
            dis.clear();
            return gson.toJson(dis);
        });
    }
}