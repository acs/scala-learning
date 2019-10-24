package acs.samples

object OptionSample extends App {
  // Playing with Option/Some
  // And Option[A] container could contains Some[A] or None

  def checkNull(nullVal: Boolean): Any = {
    // Bad practice to have null around our code
    if (nullVal) null
    else false
  }

  def checkNullOption(nullVal: Boolean): Option[Boolean] = {
    // Safe to encapsulate null in Option: Option(null) = None
    // But this won't work because Option[Null] type can not be converted to Option[Boolean]
    // if (nullVal) Option(null)
    // But with this conversion it works as we want
    if (nullVal) Option(null).getOrElse(None)
    else Some(false):Option[Boolean]
  }

  def checkNoneOption(noneVal: Boolean): Option[Boolean] = {
    if (noneVal) None
    else Some(false)
  }

  println(checkNullOption(true).getOrElse(println("There is a null")))
}
