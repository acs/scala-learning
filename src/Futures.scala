import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


// http://engineering.monsanto.com/2015/06/15/implicits-futures/
case class Employee(id: Int, name: String)

case class Role(name: String, department: String)

case class EmployeeWithRole(id: Int, name: String, role: Role)

trait EmployeeGrabberBabber {

  def rawEmployee(id: Int): Employee

  def rawRole(e: Employee): Role

  def employee(id: Int)(implicit e: ExecutionContext): Future[Employee]

  def role(employee: Employee)(implicit e: ExecutionContext): Future[Role]
}

/* This is test code that fakes some delays. Don't sleep on threads on production code! */
object EmployeeDAO extends EmployeeGrabberBabber {

  private def delay[T, V](f: (T) => V, millis: Int)(t: T)(implicit ec: ExecutionContext): Future[V] = {
    Future {
      Thread.sleep(millis)
      f(t)
    }
  }

  def rawEmployee(id: Int) = new Employee(id, "Bob" + id)

  def rawRole(e: Employee) = new Role(s"{whatever ${e.name} does", "Accounting")

  def employee(id: Int)(implicit e: ExecutionContext) = delay(rawEmployee _, 200)(id)(e)

  def role(employee: Employee)(implicit e: ExecutionContext) = delay(rawRole _, 100)(employee)(e)
}

object Futures extends App {

  println("Learning Scala Futures ...")

  println(s"A sync Employee is ${EmployeeDAO.rawEmployee(10)}")

  // Not using implicits for ExecutionContext in Future
  val ec1 = scala.concurrent.ExecutionContext.Implicits.global
  val bigEmployee: Future[EmployeeWithRole] =
    EmployeeDAO.employee(100)(ec1).flatMap { e =>
      EmployeeDAO.role(e)(ec1).map { r =>
        EmployeeWithRole(e.id, e.name, r)
      }(ec1)
    }(ec1)
  println(s"An async Employee is ${bigEmployee}")

  // Not using implicits for ExecutionContext in Future
  // Not needed this implicit if we just import it with:
  // import scala.concurrent.ExecutionContext.Implicits.global
  // But we can define to "ec" the ExecutionContext that we desire
  // implicit val ec = scala.concurrent.ExecutionContext.Implicits.global
  val bigEmployeeImpl: Future[EmployeeWithRole] =
    EmployeeDAO.employee(100).flatMap { e =>
      EmployeeDAO.role(e).map { r =>
        EmployeeWithRole(e.id, e.name, r)
      }
    }
  println(s"An async Employee with implicits is ${bigEmployee}")

  // And with for-comprehension
  val employeeWithRole = for {employee <- EmployeeDAO.employee(200)
                              role <- EmployeeDAO.role(employee)}
                              yield EmployeeWithRole(employee.id, employee.name, role)

}
