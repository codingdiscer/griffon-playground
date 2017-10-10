package org.dowma.interestcalculator

import com.google.common.collect.Lists
import griffon.core.artifact.GriffonModel
import griffon.core.artifact.GriffonView
import griffon.inject.MVCMember
import griffon.javafx.beans.binding.UIThreadAwareBindings
import griffon.metadata.ArtifactProviderFor
import groovy.util.logging.Slf4j
import javafx.beans.property.StringProperty
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.stage.Stage
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView

import javax.annotation.Nonnull
import javafx.scene.paint.Color

import java.lang.reflect.Field
import java.lang.reflect.Method

@ArtifactProviderFor(GriffonView)
@Slf4j
class InterestCalculatorView extends AbstractJavaFXGriffonView {

    @MVCMember @Nonnull FactoryBuilderSupport builder

    @MVCMember @Nonnull InterestCalculatorController controller
    @MVCMember @Nonnull InterestCalculatorModel model


    List<StringProperty> bindings

/*
    @Override
    void initUI() {
        builder.with {
            content = builder.fxml(resource('/org/dowma/interestcalculator/interestCalculator.fxml')) {
                bean(termInYears,
                        text: bind(UIThreadAwareBindings.uiThreadAwareStringProperty(model.getTermInYearsProperty())))

//                inputLabel.text = application.messageSource.getMessage('name.label')
//                bean(input, text: bind(uiInput))
//                bean(output, text: bind(uiOutput))

            }
        }


        connectActions(builder.content, controller)

        // custom build the scene, loading from FXML
        Scene scene = new Scene(new Group());
        scene.setFill(Color.WHITE);
        ((Group) scene.getRoot()).getChildren().addAll(builder.content);

        // prepare the application, tying it to the view
        Stage stage = (Stage) getApplication().createApplicationContainer(Collections.<String, Object> emptyMap());
        stage.setTitle('interest calculator');
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setScene(scene);
        getApplication().getWindowManager().attach("mainWindow", stage)

    }
*/





    @Override
    void initUI() {


        // custom build the scene, loading from FXML
        Scene scene = new Scene(new Group());
        scene.setFill(Color.WHITE);

        Node node = loadFromFXML();
        ((Group) scene.getRoot()).getChildren().addAll(node);
        connectActions(node, controller);


        //println "inside InterestCalculatorView.initUI() : termInYears=${termInYears}"
        println "node=${node}"


        bindings = autoBind(node, model)

        println " model.termInYears=${model.termInYears()}"

        // prepare the application, tying it to the view
        Stage stage = (Stage) getApplication().createApplicationContainer(Collections.<String, Object> emptyMap());
        stage.setTitle('interest calculator');
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setScene(scene);
        getApplication().getWindowManager().attach("mainWindow", stage)
    }


/*
        // this section works.....!!!!
        builder.application(title: application.configuration['application.title'],
                sizeToScene: true, centerOnScreen: true, name: 'mainWindow') {
            scene(fill: WHITE, width: 600, height: 400) {
                gridPane {
                    label(id: 'termInYearsLabel', row: 0, column: 0, text: 'Term in Years')
                    textField(id: 'termInYears', row: 0, column: 1, text: bind(model.termInYearsProperty()))
                    button(row: 1, column: 0, prefWidth: 200, id: 'calcPaymentsActionTarget', calcPaymentsAction)
                }
            }
        }
*/



    List<StringProperty> autoBind(Node node, GriffonModel model) {

        List<StringProperty> bindings = Lists.newArrayList()

        //println "fields for model (${model.class.name})"

        for(Field field : model.class.getDeclaredFields()) {
            if(field.name.endsWith("Prop")) {
                log.debug("field :: ${field.name}, type=${field.getType()}")

                String observableName = field.name.replace('Prop', '')
                log.debug("..will look for UI Node with id : [${observableName}]")


                Node bindableNode = findNode(node, observableName)
                if(bindableNode != null) {
                    log.debug("..found bindable node! :: bindableNode.class=${bindableNode.class.name};  id=${bindableNode.id}; will dynamically call method ${field.name}erty()")

                    String methodName = "${field.name}erty"

                    Method method = model.class.getMethod(methodName, null)

                    StringProperty sp = UIThreadAwareBindings.uiThreadAwareStringProperty(
                            method.invoke(model, null))

                    // the money shot!!
                    (bindableNode as TextField).textProperty().bindBidirectional(sp)

                    bindings.add(sp)
                }
            }

        }


        // return the bindings that were created
        bindings
    }


    Node findNode(Node node, String observableName) {

        Node returnNode = null

        if(node.id == observableName) {
            log.debug("found case where node.id [${node.id}] == observableName [${observableName}]")
            return node
        } else {
            if (node instanceof Parent) {
                for(Node loopNode : (node as Parent).getChildrenUnmodifiable()) {
                    Node tempNode = findNode(loopNode, observableName)
                    if(tempNode != null) {
                        return tempNode
                    }

                }
            }
        }

        returnNode
    }

}