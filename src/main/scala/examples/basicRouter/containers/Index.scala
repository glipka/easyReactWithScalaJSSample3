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
package examples.basicRouter.containers

import com.glipka.easyReactJS.react._
import com.glipka.easyReactJS.redux._
import com.glipka.easyReactJS.redux.Redux._
import com.glipka.easyReactJS.reactRedux._
import com.glipka.easyReactJS.reactRedux.ReactRedux._

import org.scalajs.dom._
import scalajs.js
import scalajs.js._
import com.glipka.easyReactJS.react.ComponentClass
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.JSApp

import scala.scalajs.js.annotation.ScalaJSDefined
import com.glipka.easyReactJS.react.ReactTypedConstructor
import scala.scalajs.js.annotation.JSExportAll
import com.glipka.easyReactJS.reactRouter.ReactRouter._
import com.glipka.easyReactJS.reactRouter._
import com.glipka.easyReactJS.react.xml.XmlToCreatElement  
import examples.basicRouter.components._
 
import com.glipka.easyReactJS.reduxForm._
import com.glipka.easyReactJS.reduxForm.ReduxForm._

@JSExport("App3")
object Index extends JSApp {

  override def main() {

    val styleRed = js.Dynamic.literal("color" -> "red")
    @XmlToCreatElement(true)
    val home: js.Function = () => {
      <div>
        <h2>Page Principale</h2>
      </div>
    }

    @XmlToCreatElement
    val noMatch: js.Function = (location: js.Dynamic) => { // revoir reactRouter.location
      <div>
        <h3>No match for <code>{ location.location.pathname }</code></h3>
      </div>
    }

    @XmlToCreatElement(true)
    val router = <BrowserRouter history={ browserHistory }>
                   <div>
                     <div>
                       <h2>Some usages of Ajax</h2>
                       <ul role="nav">
                         <li><Link to="/json1" activeStyle={ styleRed }>Get Ajax and Results in Griddle </Link></li>                    
                       </ul>
                     </div>
                     <Match exactly={ true } pattern="/" component={ home }/>
                     <Match pattern="/json1" component={Json1}/>
                     
                     <Miss component={ noMatch }/>
                   </div>
                 </BrowserRouter>

    ReactDOM.render(router, document.getElementById("content"));

  }

} 
 
 