package index
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.scalajs.js
import io.udash.wrappers.jquery._
import org.scalajs.dom, dom.document
import org.querki.jquery._
import validations._
import scalatags.JsDom.all._

object HWApp {
  def main(args: Array[String]): Unit = {
    println("Hello world!aa")
    //jQ("#submitButton").click(validateForm())
    $("#submitButton").click(() => validate())
    //jQ("#submitButton").on("click", validateForm())

    // val idsOfTextInputsToValidate = Seq("fname", "lname", "email", "confirmEmail")
    // val idsOfNumberInputsToValidate = Seq("phone")
    // val idsOfRadioInputsToValidate = Seq("gender")

    // val textInputs = idsOfTextInputsToValidate.map(id => $("#"+id).value))
    // val providedInputsWithValidationFlag = textInputs.map(ti => (ti, isTextInputFilled(ti))
    
  }

  @JSExportTopLevel("makeRequest")
  def makeRequest() = {
    val xhr = new dom.XMLHttpRequest()
    xhr.open("GET",
      "https://reqres.in/api/users/10"
    )
    xhr.onload = (e: dom.Event) => {
      if (xhr.status == 200) {
        val json = js.JSON.parse(
          xhr.responseText
        )
        val displayFrag = div(
          img(src:=json.data.avatar),
          p(json.data.first_name.toString),
          p(json.data.last_name.toString),
        )
        document.write(displayFrag.toString)
        // target.appendChild(
        //   pre(xhr.responseText).render
        // )
      }
    }
    xhr.send()
  }

  
  
  def validate() = {
    val idsToUnredden = Seq("fname", "lname", "email", "confirmEmail", "gender", "phone")
    idsToUnredden.foreach(unredden(_))
    var allValid:Boolean = true
    if($("#fname").value == "") {redden("fname"); allValid=false;} else {
      val email = $("#email").value.toString
      if(isValidEmail(email)) {
        if(email != $("#confirmEmail").value) { // primary email valid, both emails don't match
          redden("confirmEmail"); allValid = false
        } else { //both emails valid
          //validate gender
          if(isRadioButtonOn("genderRadios")) {
            //radio button is on; check phone
            val phNr = {
              val dynNr = $("input[name=phonenumber]").value.asInstanceOf[String]
              if( dynNr == "") 0 else dynNr.toInt
            }
            if ( isTenDigitPhoneNr ( phNr ) ) { 
            //if (isTenDigitPhoneNr( $("#phone").value.asInstanceOf[Int] ) ) {
              //phone number is valid
              allValid = true 
            } else {
              //phone number invalide
              allValid = false
              redden("phone")
            }
          } else {
            //radio button is off invalid form
            allValid = false; redden("gender")
          }
        }
      }
      else {
        //primary email isn't valid
        redden("email"); allValid = false
      }
    }
    if(allValid) makeRequest()
    allValid
  }
  
  def validateForm():Unit = {
    println("starting validation of the form")
  }

  def isGenderProvided(): Boolean = {
    $("input[name=genderRadios]:checked").value == "on"
  }

  @JSExportTopLevel("greet")
  def greet(): Unit = {
    println("greetings!")
    jQ("body").append("<p>Sweet Leaping Jesus!</p>")
  }

  @JSExportTopLevel("redden")
  def redden(id:String): Unit = {
    jQ(s"#${id}").addClass("redBoundary")
    //jQ(s"#${id}").css("borderColor","red")
  }

  @JSExportTopLevel("unredden")
  def unredden(id:String): Unit = {
    jQ(s"#${id}").removeClass("redBoundary")
    //jQ(s"#${id}").css("borderColor","red")
  }

  @JSExportTopLevel("isEmpty")
  def isEmpty(id:String) = {
    if(jQ(s"#${id}").value == "") redden(id)
  }

  @JSExportTopLevel("echo")
  def echo(id:String) = {
    println(jQ(s"#${id}").value)
  }
}