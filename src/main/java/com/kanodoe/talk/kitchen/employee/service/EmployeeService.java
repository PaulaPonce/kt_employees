package com.kanodoe.talk.kitchen.employee.service;

import com.kanodoe.talk.kitchen.employee.model.Employee;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;

public interface EmployeeService {

    EmployeeService getEmployees (Handler<AsyncResult<List<Employee>>> resultHandler);
}
