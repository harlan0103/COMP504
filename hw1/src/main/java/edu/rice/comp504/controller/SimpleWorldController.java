package edu.rice.comp504.controller;

import com.google.gson.Gson;
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

    }
}
