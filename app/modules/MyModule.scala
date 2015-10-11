package modules

import controllers.{StaticAssetResolver, DevMailer, ProdMailer, Mailer}
import http.MyErrorHandler
import play.api.http.HttpErrorHandler
import play.api.routing.Router
import scaldi.Module
import scaldi.play.condition._

class MyModule extends Module {
  bind[Mailer] when inProdMode to new ProdMailer
  bind[Mailer] when (inTestMode or inDevMode) to new DevMailer
  bind[StaticAssetResolver] to new StaticAssetResolver

  bind[HttpErrorHandler] to injected [MyErrorHandler] ('router -> injectProvider[Router])
}
