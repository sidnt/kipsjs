package index
import org.querki.jquery._
import scala.util.matching.Regex

object validations {

  def isRadioButtonOn(radioName:String): Boolean = {
    $("input[name="+radioName+"]:checked").value == "on"
  }

  def isTextInputFilled(textInputId:String): Boolean = {
    //$("input[name="+textInputName+"]").value != ""
    $("#"+textInputId).value != ""
  }

  def isValidEmail(emailCandidate:String):Boolean = {
    if (emailCandidate.trim.contains(" ")) false
    else {
      val emailRegex = new Regex("""(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])""")
      val emails = (emailRegex findAllIn emailCandidate).toList
      if (emails.length != 1 ) false
      else true
    }
  }


  def isTenDigitPhoneNr(phNo:Int): Boolean = {
    def noOfDigitsIn(n:Int):Int = {
      def iter(n:Int, nd:Int):Int = {
        if (n/10 == 0) nd
        else iter(n/10, nd+1)
      }
      iter(n,1)
    }
    noOfDigitsIn(phNo) == 10
  }

}