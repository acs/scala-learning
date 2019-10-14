package acs

import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.MustMatchers
import org.scalatest.WordSpec
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.mockito.MockitoSugar
import org.scalatest.time.Millis
import org.scalatest.time.Seconds
import org.scalatest.time.Span


class MockitoTest extends WordSpec with ScalaFutures with MockitoSugar with MustMatchers {

  implicit val patience: PatienceConfig = PatienceConfig(Span(0.6, Seconds), interval = Span(10, Millis))

  trait Baz {
    def traitMethod(defaultArg: Int = 30, anotherDefault: String = "hola"): Int = ???
  }

  "mock[T]" should {
    "not fail with default arguments in traits" in {
      val aMock = mock[Baz]

      when(aMock.traitMethod(any[Int], any[String])) thenReturn 69

      aMock.traitMethod() mustBe 69

      // verify(aMock).traitMethod(0, "")
    }

    "create correctly case classes" in {
      // https://stackoverflow.com/questions/7084309/partial-emma-code-coverage-in-scala-case-class-for-intellij-idea
      // -10-5
      val e = Employee(40, "Ana Luz")
      Employee.unapply(e).get mustBe((40, "Ana Luz"))

      val r = Role("admin", "sales")
      Role.unapply(r).get mustBe(("admin", "sales"))

      val er = EmployeeWithRole(e.id, e.name, r)
      EmployeeWithRole.unapply(er).get mustBe((e.id, e.name, r))

      er mustEqual EmployeeWithRole(e.id, e.name, r)
    }

    "the cloud algorithm must be executed in a controlled time" in {
      // Await.result(Cloud.runAlgorithm(10), 10 seconds)

      whenReady(Cloud.runAlgorithm(10)) {
        result => result mustEqual 10 + 10
      }
    }
  }
}