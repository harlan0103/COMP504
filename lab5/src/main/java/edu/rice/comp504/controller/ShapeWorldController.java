package edu.rice.comp504.controller;

import edu.rice.comp504.model.paintobj.APaintObject;
import com.google.gson.Gson;
import edu.rice.comp504.model.DispatcherAdapter;

import java.awt.*;

import static spark.Spark.*;

/**
 * The shape world controllers communicates to the model make and update paint object requests from the view and
 * communicates to the view new paint objects and updates to the existing paint objects
 */
public class ShapeWorldController {

    /**
     * Entry point into application.
     * @param args  The command line arguments.
     */
    public static void main(String[] args) {
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatcherAdapter dis = new DispatcherAdapter();

        get("/load/:type", (request, response) -> {
            String type  = request.params(":type");
            APaintObject po = dis.loadPaintObj(type);
            return gson.toJson(po);
        });


        get("/update", (request, response) -> {
            dis.updateBallWorld();
           return gson.toJson(dis);
        });

        get("/canvas", (request, response) -> {
            dis.setCanvasDims(new Point(Integer.parseInt(request.queryParams("width")),
                    Integer.parseInt(request.queryParams("height"))));
            return gson.toJson(dis);
        });

        get("/clear", (request, response) -> {
            dis.clear();
            return "removed all balls and inner walls in paint obj world";
        });

    }
}