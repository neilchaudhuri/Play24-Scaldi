package controllers

trait Mailer {
  def mail()
}

class DevMailer extends Mailer{
  override def mail() = println("Mailed in Dev")
}

class ProdMailer extends Mailer{
  override def mail() = println("Mailed in Prod")
}
