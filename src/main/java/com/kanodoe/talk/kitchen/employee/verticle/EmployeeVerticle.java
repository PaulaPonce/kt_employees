package com.kanodoe.talk.kitchen.employee.verticle;

import com.kanodoe.talk.kitchen.employee.service.EmployeeService;
import com.kanodoe.talk.kitchen.employee.service.EmployeeServiceImpl;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeVerticle extends RestAPIVerticle {

    private static final String API_EMPLOYEE = "/api/v1/employees";
    private static final String API_HEALTHCHECK = API_EMPLOYEE + "/healthcheck";

    private EmployeeService employeeService;

    @Override
    public void start() throws Exception {
        super.start();
        this.employeeService = new EmployeeServiceImpl(vertx, config());

        Router router = Router.router(vertx);
        // Body Handler
        router.route().handler(BodyHandler.create());

        this.loadRoute(router);

        this.enableCorsSupport(router);

        int port = config().getInteger("http.port");

        this.createHttpServer(router, port);
    }

    private void loadRoute (Router router) {

        // Healthcheck
        router.get(API_HEALTHCHECK).handler(this::apiHealthCheck);

        // API
        router.get(API_EMPLOYEE).handler(this::apiEmployeeList);
    }

    private void apiHealthCheck (RoutingContext context) {
        context.response().setStatusCode(200).setStatusMessage("OK").end();
    }

    private void apiEmployeeList (RoutingContext context) {
        context.vertx().executeBlocking(future -> employeeService.getEmployees(resultHandler(context, Json::encodePrettily)), false, resultHandler(context, Json::encodePrettily));
    }

}
