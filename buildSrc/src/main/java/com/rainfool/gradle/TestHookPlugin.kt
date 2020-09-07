package com.rainfool.gradle

import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 *
 * @author krystian
 */
class TestHookPlugin : Plugin<Project> {

    companion object {
        private const val TASK_NAME = "dexBuilderDebug"
    }

    override fun apply(project: Project) {
        System.out.println("========================")
        System.out.println("Hello gradle plugin!")
        System.out.println("========================")
        project.afterEvaluate { project ->
            project.tasks.getByName(TASK_NAME).actions.add(Action {
                println("add my own step from plugin")
                //Get the inputs of this task
                project.tasks.getByName(TASK_NAME).inputs.files.forEach { element1 ->
                    println("inputs $element1")
                }
                //Get the outputs of this task
                project.tasks.getByName(TASK_NAME).outputs.files.forEach { element ->
                    val file = File(element.toString())
                    val files = file.listFiles()
                    val files2 = files[0].listFiles()
                    val dexfilepath = files2[0]
                    println("Outputs Dex file's path: " + dexfilepath)
                    //Modify the dex 可先注释掉
//                    testRewrite(dexfilepath)
                }
            })
        }
    }
}
