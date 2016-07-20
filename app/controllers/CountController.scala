package controllers

import java.math.BigInteger
import javax.inject._
import play.api._
import play.api.mvc._

import services.Counter

/**
 * This controller demonstrates how to use dependency injection to
 * bind a component into a controller class. The class creates an
 * `Action` that shows an incrementing count to users. The [[Counter]]
 * object is injected by the Guice dependency injection system.
 */
@Singleton
class CountController @Inject() (counter: Counter) extends Controller {

  /**
   * Create an action that responds with the [[Counter]]'s current
   * count. The result is plain text. This `Action` is mapped to
   * `GET /count` requests by an entry in the `routes` config file.
   */
  def count = Action {
    var count1: Int=0;
    try{
      count1 = counter.nextCount()
      println(count1)

      val factorial1: BigInt = factorial( BigInteger.valueOf(count1))
      Ok(factorial1.toString)
    }catch {
      case ex:Exception=> {
        count1=0;
        InternalServerError("505")
      }


    }
  }

  def factorial(a: BigInteger): BigInt = {
    var n=a;
    var result = BigInteger.ONE;
    while (!n.equals(BigInteger.ZERO)) {
      result = result.multiply(n)
      n = n.subtract(BigInteger.ONE)
    }
    result
  }



}
