package pl.metastack.metatime

trait Offset[+T <: Component] {
  val component: T

  def format: String =
    if (component.unix().value <= 0) Constants.In + absoluteVal
    else absoluteVal + Constants.Ago

  def absoluteVal: String =
    component match {
      case date: Date => math.abs(date.year) + " year(s), " + math.abs(date.month) +
        " month(s) and " + math.abs(date.day)
      case year: Year => math.abs(year.year) + " year(s)"
      case month: Month => math.abs(month.month) + " month(s)"
      case day: Day => math.abs(day.day) + " day(s)"
      case time: Time => math.abs(time.h) + " hour(s), " + math.abs(time.m) + " minute(s), " +
        math.abs(time.s) + " second(s) and " + math.abs(time.ms) + " millisecond(s)"
      case hour: Hour => math.abs(hour.h) + " hour(s)"
      case minute: Minute => math.abs(minute.m) + " minute(s)"
      case second: Second => math.abs(second.s) + " second(s)"
      case milliseconds: Millisecond => math.abs(milliseconds.ms) + " millisecond(s)"
    }
}

object Offset {
  def apply[T <: Component](value: T): Offset[T] =
    new Offset[T] { override val component = value }
}
