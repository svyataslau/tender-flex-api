package com.tenderflex.tenderflex.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.tenderflex.tenderflex.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    //that's the reason why we could use gueva ---> too much code
//    STUDENT(new HashSet<>()),
//    ADMIN(new HashSet<>(){{
//        add(COURSE_READ);
//        add(COURSE_WRITE);
//        add(STUDENT_READ);
//        add(STUDENT_WRITE);
//    }}),
//    ADMINTRAINEE(new HashSet<>(){{
//        add(COURSE_READ);
//        add(STUDENT_READ);
//    }});
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
