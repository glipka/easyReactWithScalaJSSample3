package examples.basicRouter.components

import com.glipka.easyReactJS.react._
import org.scalajs.dom._
import org.scalajs.dom
import scalajs.js
import scalajs.js._
import scala.scalajs.js.annotation.ScalaJSDefined
import scala.scalajs.js.JSConverters._
import com.glipka.easyReactJS.reactRouter._
import com.glipka.easyReactJS.reactRouter.ReactRouter._
import com.glipka.easyReactJS.react.xml.XmlToCreatElement
import com.glipka.easyReactJS.reactRouterBootstrap._
import com.glipka.easyReactJS.reactRouterBootstrap.ReactRouterBootstrap._
import com.glipka.easyReactJS.reactBootstrap._
import com.glipka.easyReactJS.reactBootstrap.ReactBootstrap._
import dom.ext._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import org.scalajs.dom.raw.XMLHttpRequest
import com.glipka.easyReactJS.otherComponents.Griddle
import com.glipka.easyReactJS.otherComponents.OtherComponents._
import scala.scalajs.js.Dynamic._

@js.native
trait JSPropsJson1Components[P] extends js.Object {
  def key: UndefOr[String] = js.native
  def ref: UndefOr[js.Function] = js.native
  def children: PropsChildren = js.native
}

@ScalaJSDefined
class JSStateJson1(val table: js.Array[Dynamic]) extends js.Object {

}

@ScalaJSDefined
class Json1(val props: JSPropsJson1Components[Any]) extends Component[JSPropsJson1Components[Any], JSStateJson1](props) {
this.state = new JSStateJson1(js.Array())
  var columnMeta = js.Array(
    literal(
      "columnName" -> "userId", 
      "order" -> 1,
      "locked" -> false,
      "visible" -> true,
      "displayName" -> "USERID"),
    literal(
      "columnName" -> "id",
      "order" -> 2,
      "locked" -> false,
      "visible" -> true,
      "displayName" -> "ID"),
    literal(
      "columnName" -> "title",
      "order" -> 3,
      "locked" -> false,
      "visible" -> true,
      "displayName" -> "TITLE"),
    literal(
      "columnName" -> "body",
      "order" -> 4,
      "locked" -> false,
      "visible" -> true,
      "displayName" -> "BODY"))

  override def componentWillMount: Unit = {
    val url = "https://jsonplaceholder.typicode.com/posts"

    Ajax.get(url).onSuccess {
      case xhr: XMLHttpRequest =>
        val responseText = js.JSON.parse(xhr.responseText).asInstanceOf[js.Array[Dynamic]]
       
        setState(new JSStateJson1(responseText))

      case _ => println("erreur parsing")

    }
      

  }

  override def componentWillReceiveProps(nextProps: JSPropsJson1Components[Any], nextContext: js.Any) {}
  val styleRed = js.Dynamic.literal("color" -> "red")

  @XmlToCreatElement(true)
  override def render(): Any = {
    <Panel header="List of Users" bsStyle="primary" collapsible={ true } defaultExpanded={ true }>
      <Griddle_ results={ this.state.table } tableClassName="table" showFilter={ true } showSettings={ true } columns={ js.Array("userId", "id", "title", "body") }/>
    </Panel>

  }

}

  

  














