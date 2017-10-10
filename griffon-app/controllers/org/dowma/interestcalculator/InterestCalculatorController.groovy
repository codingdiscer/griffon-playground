package org.dowma.interestcalculator

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading
import javafx.fxml.FXML
import javafx.scene.control.TextField

import javax.annotation.Nonnull

@ArtifactProviderFor(GriffonController)
class InterestCalculatorController {

    @MVCMember @Nonnull InterestCalculatorModel model

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void calcPayments() {
        println "inside calcPayments!!, model=${model};  model.termInYears=${model.termInYears}"
        model.amountBorrowed = model.termInYears
    }

}