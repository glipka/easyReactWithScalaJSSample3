/*
 # Copyright 2016 Georges Lipka
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
*/
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
import org.scalajs.dom.raw.HTMLInputElement

import com.glipka.easyReactJS.otherComponents._

@js.native
trait JSPropsSelect2Creatable[P] extends js.Object {
  def key: UndefOr[String] = js.native
  def ref: UndefOr[js.Function] = js.native
  def children: PropsChildren = js.native
  val label: js.Dynamic = js.native
  val hint: js.Dynamic= js.native
}

@ScalaJSDefined
class JSStateSelect2Creatable(
  val multi: Boolean,
  val multiValue: js.Array[js.Any],
  val options: js.Array[js.Dynamic],
  val value: js.Any)
    extends js.Object {}

@ScalaJSDefined
class Select2Creatable(val props: JSPropsSelect2Creatable[Any]) extends Component[JSPropsSelect2Creatable[Any], JSStateSelect2Creatable](props) {
  this.state = new JSStateSelect2Creatable(true, js.Array(),
    js.Array(js.Dynamic.literal("value" -> "R", "label" -> "Red"),
      js.Dynamic.literal("value" -> "G", "label" -> "Green"),
      js.Dynamic.literal("value" -> "B", "label" -> "Blue")), "")

  override def componentWillMount: Unit = {}

  val handleOnChange: js.Function = (value: js.Array[js.Any]) => {
    val multi = this.state.multi
    if (multi) {
      this.setState(new JSStateSelect2Creatable(true, value, state.options, state.value))
    } else {
      this.setState(new JSStateSelect2Creatable(false, state.multiValue, state.options, value))
    }
  }

  @XmlToCreatElement(true)
  override def render(): Any = {

    <Panel header="Demonstration of Creatable" bsStyle="primary" collapsible={ true } defaultExpanded={ true }>
      <div className="section">
        <h3 className="section-heading">{if (!isUndefined(props.label)) {this.props.label.toString }}</h3>
        <Creatable multi={ this.state.multi } options={ this.state.options } onChange={ this.handleOnChange.bind(this) } value={ if (state.multi) { state.multiValue } else { state.value } }/>
        <div className="hint">{if (!isUndefined(props.hint)) {this.props.hint} }</div>
        <div className="checkbox-list">
          <label className="checkbox">
            <input type="radio" className="checkbox-control" checked={ state.multi } onChange={ () => this.setState(new JSStateSelect2Creatable(true, state.multiValue, state.options, state.value)) }/>
            <span className="checkbox-label">Multiselect</span>
          </label>
          <label className="checkbox">
            <input type="radio" className="checkbox-control" checked={ !state.multi } onChange={ () => this.setState(new JSStateSelect2Creatable(false, state.multiValue, state.options, state.value)) }/>
            <span className="checkbox-label">Single Value</span>
          </label>
        </div>
      </div>
    </Panel>

  }

}

  

  














