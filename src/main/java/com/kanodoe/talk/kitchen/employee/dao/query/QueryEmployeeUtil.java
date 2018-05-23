package com.kanodoe.talk.kitchen.employee.dao.query;

import static com.kanodoe.talk.kitchen.employee.util.Constant.DB_SCHEMA;

public class QueryEmployeeUtil {

    private QueryEmployeeUtil() {
        throw new IllegalAccessError(QueryEmployeeUtil.class.toString());
    }

    private static class QSELECT {

        private StringBuilder select = new StringBuilder();
        private StringBuilder postSelect = new StringBuilder();

        private StringBuilder getSelect() {

            select.append("  SELECT ");
            select.append("  employee.id, ");
            select.append("  employee.first_name,  ");
            select.append("  employee.second_name,  ");
            select.append("  employee.last_name,  ");
            select.append("  employee.second_last_name,  ");
            select.append("  employee.email,  ");
            select.append("  employee.gender,  ");
            select.append("  employee.phone_country,  ");
            select.append("  employee.phone_region,  ");
            select.append("  employee.phone_number,  ");
            select.append("  employee.country,  ");
            select.append("  employee.state,  ");
            select.append("  employee.city,  ");
            select.append("  employee.address,  ");
            select.append("  employee.entry_date,  ");
            select.append("  employee.category,  ");
            select.append("  employee.level,  ");
            select.append("  employee.is_active  ");
            select.append("  FROM ");
            select.append(DB_SCHEMA);
            select.append(".kt_employees employee ");
            return select;
        }

        private QSELECT postSelect(StringBuilder where) {
            this.postSelect.append(" ").append(where);

            return this;
        }

        @Override
        public String toString() {
            return this.getSelect().append(this.postSelect).toString();
        }
    }

    public static String getEmployeesSql () {
        return new QSELECT().toString();
    }

}
