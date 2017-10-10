package org.dowma

import griffon.core.artifact.GriffonController
import griffon.core.controller.ControllerAction
import griffon.inject.MVCMember
import griffon.metadata.ArtifactProviderFor
import griffon.transform.Threading
import javax.annotation.Nonnull

@ArtifactProviderFor(GriffonController)
class GriffonPlaygroundController {
    @MVCMember @Nonnull
    GriffonPlaygroundModel model

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void click() {
        int count = model.clickCount.toInteger()
        model.clickCount = String.valueOf(count + 1)
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    void goToInterestCalculator() {

        getApplication

        int count = model.clickCount.toInteger()
        model.clickCount = String.valueOf(count + 1)
    }


}