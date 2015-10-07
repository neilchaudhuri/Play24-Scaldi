package modules

import controllers.{DevMailer, ProdMailer, Mailer}
import http.MyErrorHandler
import play.api.http.HttpErrorHandler
import play.api.routing.Router
import scaldi.Module
import scaldi.play.condition._

class MyModule extends Module {
  bind[Mailer] when inProdMode to new ProdMailer
  bind[Mailer] when (inDevMode or inTestMode) to new DevMailer

  //bind[HttpErrorHandler] to injected [MyErrorHandler] ('router -> injectProvider[Router])
}
