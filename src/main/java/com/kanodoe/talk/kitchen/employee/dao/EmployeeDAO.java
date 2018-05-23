package com.kanodoe.talk.kitchen.employee.dao;

import com.kanodoe.talk.kitchen.employee.dao.query.QueryEmployeeUtil;
import com.kanodoe.talk.kitchen.employee.model.Employee;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDAO extends JdbcRepositoryWrapper {

    public EmployeeDAO (Vertx vertx, JsonObject config) {
        super(vertx, config);
    }


    public EmployeeDAO getEmployees (Handler<AsyncResult<List<Employee>>> resultHandler) {

        this.retrieveAll(QueryEmployeeUtil.getEmployeesSql())
                .map(rawList -> rawList.stream()
                        .map(Employee::new)
                        .collect(Collectors.toList())
                )
                .setHandler(resultHandler);
        return this;

    }
}
