package com.sagar.demo.security.authorities

enum class AppUserPermission(val permission: String) {
    ADMIN_READ("adminRead"),
    ADMIN_WRITE("adminWrite"),
    COURSE_READ("courseRead"),
    COURSE_WRITE("courseWrite")
}