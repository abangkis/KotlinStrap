package net.slowy.kotlinstrap

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.html.*
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.*
import org.slf4j.Logger

fun Application.homeRoute(log: Logger) {
    routing {
        get("/home") {
            call.respondWithBootstrap()
//            call.respondWithHome()
        }
    }
}

suspend fun ApplicationCall.respondWithHome() {
    respondHtmlTemplate(BootstrapTemplate("KotlinStrap", "Kotlin Strap")) {
        menu {
            +"Hello menu"
        }
        content{
            +"My content"
        }
    }

//    respondHtml {
//        head {
//            meta(charset = "utf-8")
//            meta(name = "viewport", content = "width=device-width, initial-scale=1, shrink-to-fit=no")
//            title { +"Tag" }
//            link(rel="stylesheet", href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css")
//        }
//        body {
//            h1 {
//                +"Hello world"
//            }
//
//            div("container") {
//                div("row") {
//                    div("col-md-2") { +"""Column left""" }
//                    div("col-md-auto") { +"""Column right""" }
//                }
//            }
//
//            form {
//                +"Form Input: "
//                textInput(name = "myInput")
//                buttonInput(name = "submit") { print("Submit is clicked: ") }
//            }
//
//            script{src = "https://code.jquery.com/jquery-3.5.1.min.js"}
//            script{src = "https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"}
//            script{src = "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"}
//        }
//    }
}

//class BootstrapTemplate(private val twoColumn: TwoColumnTemplate = TwoColumnTemplate()) : Template<HTML> {
class BootstrapTemplate(val titleString: String, val header: String) : Template<HTML> {
    val menu = Placeholder<FlowContent>()
    val content = Placeholder<FlowContent>()


    override fun HTML.apply() {
//        insert(twoColumn) {}

        head {
            title { +titleString }
            meta(charset = "utf-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1, shrink-to-fit=no")
            link(rel = "stylesheet", href = "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css")
        }
        body {
            div("container") {
                h1 { +header }
                div("row") {
//                    div("col-md-2") { insert(MenuTemplate(menuItem), menu) }
                    div("col-md-2") { insert(menu) }
//                    div("col-md-auto") { insert(content) }
                    div("col-md-auto") { insert(content) }
                }
            }

            script { src = "https://code.jquery.com/jquery-3.5.1.min.js" }
            script { src = "https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" }
            script { src = "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" }
        }

//        insert(twoColumn) {}
    }

}
class TwoColumnTemplate() : Template<HTML> {
    override fun HTML.apply() {
    }
}

//class TwoColumnTemplate(val titleString: String, val menuItem: List<String>) : Template<HTML> {
//    val menu = TemplatePlaceholder<MenuTemplate>()
//    val content = Placeholder<HtmlBlockTag>()
//    override fun HTML.apply() {
//        head {
//            title { +titleString }
//            meta(charset = "utf-8")
//            meta(name = "viewport", content = "width=device-width, initial-scale=1, shrink-to-fit=no")
//            link(rel = "stylesheet", href = "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css")
//        }
//        body {
//
//            div("container") {
//                h1 { +"Tag" }
//                div("row") {
//                    div("col-md-2") { insert(MenuTemplate(menuItem), menu) }
//                    div("col-md-auto") { insert(content) }
//                }
//            }
//
//            script { src = "https://code.jquery.com/jquery-3.5.1.min.js" }
//            script { src = "https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" }
//            script { src = "https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" }
//        }
//    }
//}

class MenuTemplate(private val list: List<String>) : Template<FlowContent> {
    override fun FlowContent.apply() {
        if (list.isNotEmpty()) {
            ul {
                list.forEach {
                    li { +it }
                }
            }
        }
    }
}

//class MenuTemplate(list: List<String>) : Template<FlowContent> {
//    val item = PlaceholderList<UL, FlowContent>()
//    override fun FlowContent.apply() {
//        if (!item.isEmpty()) {
//            ul {
//                each(item) {
//                    li {
//                        if (it.first) b {
//                            insert(it)
//                        } else {
//                            insert(it)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}

