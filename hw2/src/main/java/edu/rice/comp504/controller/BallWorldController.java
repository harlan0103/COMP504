package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.DispatchAdapter;
import edu.rice.comp504.model.ABall;

import static spark.Spark.*;


/**
 * The controller creates the adapter and sets up the communication between the adapter and the view as well as the
 * adapter and the model
 */
public class BallWorldController {

    public static void main(String[] args) {
        staticFiles.location("/public");
        // dispatch adapter is instantiated in the controller
        DispatchAdapter adapter = new DispatchAdapter();

        final Gson gson = new Gson();
        // The only one endpoint for all ball types
        get("/ball/:kind", (request, response) -> {
            ABall ball = adapter.loadBall(request.params(":kind"));
            return gson.toJson(ball);
        });

        get("/update", (request, response) -> {
            adapter.updateBallWorld();
            return gson.toJson(adapter);
        });

        get("/clear", (request, response) -> {
            adapter.clear();
            return gson.toJson(adapter);
        });
    }
}
