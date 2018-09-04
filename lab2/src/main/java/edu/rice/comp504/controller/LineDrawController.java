package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.MovingLine;

import static spark.Spark.*;


/**
 * Line draw controller is responsible for interfacing between the model and view for drawing a moving line
 */
public class LineDrawController {

    public static void main(String[] args) {
        staticFileLocation("/public");
        Gson gson = new Gson();
        MovingLine line = new MovingLine(20);

        get("/line", (req, res) -> {
            //TODO: draw the line at the original location
            line.resetPos();
            return gson.toJson(line);
        });

        get("/reset", (req, res) -> {
            //TODO: reset the line position
            line.resetPos();
            return gson.toJson(line);
        });

        get("/update", (req, res) ->  {
            //TODO: update the line position
            line.update();
            return gson.toJson(line);
        });

    }
}
