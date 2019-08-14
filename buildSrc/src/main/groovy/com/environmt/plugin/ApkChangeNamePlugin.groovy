package com.environmt.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class ApkChangeNamePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {

        def extension = project.extensions.create('greeting', EnvironMnt)
//
//        project.greeting {
//            doLast {
//                println '地址' + extension.BASE_URL
//            }
//        }
//        def environmnt = new EnvironMnt()
//        environmnt.config_environ()
//        println("apply project : " + environmnt.getBASE_URL())
    }

}