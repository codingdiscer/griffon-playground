package org.dowma

import griffon.core.artifact.GriffonModel
import griffon.transform.FXObservable
import griffon.metadata.ArtifactProviderFor

@ArtifactProviderFor(GriffonModel)
class GriffonPlaygroundModel {
    @FXObservable String clickCount = "0"
}