package com.kanodoe.talk.kitchen.employee.service;

import com.kanodoe.talk.kitchen.employee.dao.EmployeeDAO;
import com.kanodoe.talk.kitchen.employee.model.Employee;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(Vertx vertx, JsonObject config) {
        this.employeeDAO = new EmployeeDAO(vertx, config);
    }

    @Override
    public EmployeeService getEmployees (Handler<AsyncResult<List<Employee>>> resultHandler) {

        employeeDAO.getEmployees(resultHandler);
        return this;
    }
}
