package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.MovingLine;

import static spark.Spark.*;


/**
 * Line draw controller is responsible for interfacing between the model and view for drawing a moving line
 */
public class LineDrawController {

    /**
     * Main entry point into the program
     * @param args Arguments that are normally passed to the executable on the command line
     */
    public static void main(String[] args) {
        staticFileLocation("/public");
        Gson gson = new Gson();
        DispatchAdapter da = new DispatchAdapter();

        get("/line", (req, res) -> {
           MovingLine ml = da.loadLine();
            return gson.toJson(ml);
        });

        get("/reset", (req, res) -> {
            //TODO: Implement me
            da.deleteObservers();
            return gson.toJson(da);
        });

        get("/update", (req, res) ->  {
            //TODO: Implement me
            //System.out.print("Call Update");
            da.updateLineWorld();
            return gson.toJson(da);
        });

        get("/switch", (req, res) ->  {
            //TODO: Implement me
            System.out.println("press switch");
            da.switchStrategy();
            return gson.toJson(da);
        });



    }
}
